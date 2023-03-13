package com.xuecheng.media.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.base.model.RestResponse;
import com.xuecheng.media.mapper.MediaFilesMapper;
import com.xuecheng.media.model.dto.*;
import com.xuecheng.media.model.po.MediaFiles;
import com.xuecheng.media.model.po.MediaProcess;
import com.xuecheng.media.service.MediaFileService;
import com.xuecheng.media.service.MediaProcessService;
import io.minio.*;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Streams;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Mr.M
 * @version 1.0
 * @description TODO
 * @date 2022/9/10 8:58
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MediaFileServiceImpl extends ServiceImpl<MediaFilesMapper, MediaFiles> implements MediaFileService {

    /**
     * 线程执行
     */
    final static ExecutorService pool = Executors.newFixedThreadPool(5);
    @Autowired
    private MediaFilesMapper mediaFilesMapper;

    @Value("${minio.bucket.files}")
    private String fileBucket;
    @Value("${minio.bucket.videofiles}")
    private String videoBucket;
    /**
     * minio客户端
     */
    private final MinioClient minioClient;

    private final MediaProcessService mediaProcessService;
    @Override
    public PageResult<MediaFiles> queryMediaFiels(Long companyId, PageParams pageParams, QueryMediaParamsDto queryMediaParamsDto) {

        //构建查询条件对象
        LambdaQueryWrapper<MediaFiles> queryWrapper = new LambdaQueryWrapper<>();

        //分页对象
        Page<MediaFiles> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        // 查询数据内容获得结果
        Page<MediaFiles> pageResult = mediaFilesMapper.selectPage(page, queryWrapper);
        // 获取数据列表
        List<MediaFiles> list = pageResult.getRecords();
        // 获取数据总数
        long total = pageResult.getTotal();
        // 构建结果集
        PageResult<MediaFiles> mediaListResult = new PageResult<>(list, total, pageParams.getPageNo(), pageParams.getPageSize());
        return mediaListResult;

    }


    /**
     * @param companyId           公司ID
     * @param uploadFileParamsDto 上传文件参数类
     * @param localFilePath       要上传的文件其本地路径
     * @return com.xuecheng.media.model.dto.UploadFileResultDto
     * @description 上传文件
     * @author: woldier
     * @date: 2023/3/10 13:36
     */
    @Override
    public UploadFileResultDto uploadFile(Long companyId, UploadFileParamsDto uploadFileParamsDto, String localFilePath) throws XueChengPlusException {
        /*
         * 1.上传文件到minio ，文件路径为 /{桶名}/{年}/{月}/{日}/
         * 2.插入数据库
         * */
        /*通过扩展名获取媒体资源类型*/
        String mimeType = getMimeType(uploadFileParamsDto.getFilename());
        /*组装文件基路径*/
        String basePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"));
        /*由于Minio中同一天的数据保存位置都在同一个文件夹下,为了防止重名现象,不能用原文件名,应该用md5值加后缀*/
        int index = uploadFileParamsDto.getFilename().lastIndexOf(".");
        //文件后缀
        String fileSuffix = uploadFileParamsDto.getFilename().substring(index);
        //文件md5值
        String md5 = getMd5(localFilePath);
        //拼接得到Minio存储路径
        String objectName = basePath + md5 + fileSuffix;
        //上传到minio
        boolean minIOUpload = minIOUpload(localFilePath, mimeType, fileBucket, objectName);
        if (!minIOUpload) XueChengPlusException.cast("MinIO上传出错");
        //上传到数据库
        //MediaFiles files = insertMediaFile2DB(companyId, uploadFileParamsDto, md5, fileBucket, objectName);
        //通过代理对象调用
        MediaFileService proxy = (MediaFileService) AopContext.currentProxy();
        MediaFiles files = proxy.insertMediaFile2DB(companyId, uploadFileParamsDto, md5, fileBucket, objectName);
        //结果为空表示上传失败
        if (files == null) XueChengPlusException.cast("文件上传后保存信息到数据库失败");
        UploadFileResultDto uploadFileResultDto = new UploadFileResultDto();
        BeanUtils.copyProperties(files, uploadFileResultDto);

        return uploadFileResultDto;
    }

    /**
     * @param companyId           公司id
     * @param uploadFileParamsDto 上传参数信息
     * @param md5                 md5
     * @param bucket              桶
     * @param objectName          对象名
     * @return com.xuecheng.media.model.po.MediaFiles
     * @description 插入数据库
     * @author: woldier
     * @date: 2023/3/10 15:21
     */
    @Transactional
    public MediaFiles insertMediaFile2DB(Long companyId, UploadFileParamsDto uploadFileParamsDto, String md5, String bucket, String objectName) {
        /*添加数据库之前,根据md5查询该文件是否已经存在*/
        MediaFiles files = mediaFilesMapper.selectById(md5);
        if (files == null) {
            /*生成数据库entity*/
            MediaFiles mediaFiles = new MediaFiles();
            BeanUtils.copyProperties(uploadFileParamsDto, mediaFiles);
            //设置uploadFileParamsDto中不存在的部分
            //设置id
            mediaFiles.setId(md5);
            //机构id
            mediaFiles.setCompanyId(companyId);
            //bucket
            mediaFiles.setBucket(bucket);
            //存储路径
            mediaFiles.setFilePath(objectName);
            //file_id
            mediaFiles.setFileId(md5);
            //url
            //设置url字段需要判断当前的contentType是否为image或者mp4，只有这两种格式才能设置url直接预览
            String mimeType = getMimeType(uploadFileParamsDto.getFilename());//获取文件content-type
            if(mimeType.contains("image")||mimeType.contains("mp4"))//若为图片或者mp4格式视频设置url
                mediaFiles.setUrl("/" + bucket + "/" + objectName);
            //上传时间,更新时间自动设置
            mediaFiles.setCreateDate(LocalDateTime.now());
            mediaFiles.setCreateDate(LocalDateTime.now());
            //文件状态
            mediaFiles.setStatus("1");
            //审核状态
            mediaFiles.setAuditStatus(MediaAuditStatus.Approved.getCode());

            int insert = mediaFilesMapper.insert(mediaFiles);

            /*
            * 若为avi格式，则插入转码任务
            * */
            getAopContextProxy().insertMediaProcessTask(md5, bucket, objectName, mimeType);


            if (insert <= 0) {
                log.debug("向数据库保存文件失败,bucket:{},objectName{}", fileBucket, objectName);
                return null;
            }
            return mediaFiles;
        }
        return files;
    }

    @Transactional(propagation = Propagation.MANDATORY) //设置传播级别为调用本方法的方法必须具有事务
    public  void insertMediaProcessTask(String md5, String bucket, String objectName, String mimeType) {
        if("video/x-msvideo".equals(mimeType)){
            MediaProcess mediaProcess = new MediaProcess();
            mediaProcess.setBucket(bucket); //设置桶
            mediaProcess.setFilename(objectName); //设置对象名
            mediaProcess.setFileId(md5); //设置md5
            mediaProcess.setCreateDate(LocalDateTime.now());  //设置创建时间
            mediaProcess.setStatus(MediaProcessStat.Pending.getCode()); //设置处理状态
            mediaProcessService.save(mediaProcess);
        }
    }

    /**
     * @param md5 文件md值
     * @return com.xuecheng.base.model.RestResponse
     * @description 检查文件是否存在
     * @author: woldier
     * @date: 2023/3/11 22:31
     */
    @Override
    public RestResponse checkFile(String md5) {
        /*
         * 1.查询md5值数据库
         * 2.若没有数据直接返回false
         * 3.若有则查询minio,查看是否可以get到对象,对于视频文件我们也要将他分文件夹存储,分为两级目录,第一级是md5的第一个字符,第二级是md5的第二个字符
         * 如avsdsdsdsds 为某文件的md值,那么该文件在minio中的存储路径是 a/v/avsdsdsdsds.文件后缀
         * */
        //从数据库查询
        MediaFiles mediaFiles = this.getById(md5);
        if (mediaFiles != null) {//数据库中查询到不为空
            //获取桶
            String bucket = mediaFiles.getBucket();
            //获取存储路径
            String filePath = mediaFiles.getFilePath();
            Boolean status = checkFileInMinio(bucket, filePath);
            if (Boolean.TRUE.equals(status)) return RestResponse.success(Boolean.TRUE);
        }

        //查询到数据库为空,或者查询minio报错返回错误
        return RestResponse.success(Boolean.FALSE);
    }

    /**
     * @param bucket     桶
     * @param objectName 对象路径
     * @return Boolean 若存在返回True 不存在返回False
     * @description 检查minio中是否存在
     * @author: woldier
     * @date: 2023/3/12 10:35
     */
    @Nullable
    private Boolean checkFileInMinio(String bucket, String objectName) {
        return checkFileInMinio(GetObjectArgs.builder().bucket(bucket).object(objectName).build());
    }

    private Boolean checkFileInMinio(GetObjectArgs getObjectArgs) {
        try (InputStream inputStream = minioClient.getObject(getObjectArgs)) { //通过这种方法创建额流会在try catch后自动释放
            //若input流对象不为空,说明minio中有数据,那么返回存在
            if (inputStream != null) return Boolean.TRUE;
        } catch (Exception e) {
            log.debug("在minio中获取对象出错bucket:{},objectName{},errInfo{}", getObjectArgs.bucket(), getObjectArgs.object(), e.getMessage());
        }
        return Boolean.FALSE;
    }

    /**
     * @param bucket     桶
     * @param objectName 对象名
     * @return StatObjectResponse
     * @description 获取minio对象信息
     * 此方法中不会做任何事,而是调用 private long  getObjStatInMinio(StatObjectArgs statObjectArgs)
     * @author: woldier
     * @date: 2023/3/12 15:08
     */
    private StatObjectResponse getObjStatInMinio(String bucket, String objectName) {
        return getObjStatInMinio(StatObjectArgs.builder().bucket(bucket).object(objectName).build());
    }

    /**
     * @param statObjectArgs minio 对象状态参数类
     * @return StatObjectResponse
     * @description 获取minio对象信息
     * @author: woldier
     * @date: 2023/3/12 15:08
     */
    private StatObjectResponse getObjStatInMinio(StatObjectArgs statObjectArgs) {
        StatObjectResponse statObjectResponse = null;
        try {
             statObjectResponse = minioClient.statObject(statObjectArgs);
        } catch (Exception e) {
            log.debug("获取minio对象信息时出错,bucket{},objectName{},errMsg{}", statObjectArgs.bucket(), statObjectArgs.object(), e.getMessage());
        }
        return statObjectResponse;
    }

    /**
     * @param md5 md5值
     * @return java.lang.String
     * @description 根据md5值得到存储在minio中的分片对象文件夹路径
     * 如文件md5为 0dbc6409995eaa9589676c585459e02b 则得到的文件夹路径为 0/d/0dbc6409995eaa9589676c585459e02b/chunk/
     * @author: woldier
     * @date: 2023/3/11 22:57
     */
    private String getChunkFolderByMd5(String md5) {

        return md5.charAt(0) + "/" + md5.charAt(1) + "/" + md5 + "/chunk/";

    }

    private String getFolderByMd5(String md5) {
        return md5.charAt(0) + "/" + md5.charAt(1) + "/"+md5+"/";
    }


    /**
     * @param md5   文件md值
     * @param chunk 分片id
     * @return com.xuecheng.base.model.RestResponse
     * @description 检查文件分片是否存在;
     * 首先分片数据记录并不会存在于数据库中,
     * 因此只能通过访问minio来进行查询,
     * 我们可以通过minio的getobject方法,有则说明可以
     * @author: woldier
     * @date: 2023/3/11 22:34
     */
    @Override
    public RestResponse checkChunk(String md5, Integer chunk) {
        /*
         * 由于分片文件信息不会记录在db中,因此我们之后访问minio进行查询
         * 1.通过md5得到分片文件存放的文件夹
         * 2.将文件夹路径与分片号chunk合并,得到分片路径
         * 例如 分片为23 md5为0dbc6409995eaa9589676c585459e02b
         * 则拼接的对象 路径为 0/d/0dbc6409995eaa9589676c585459e02b/chunk/23
         * 3.通过Minio查看是否可以成功获取若能说明存在返回成功,否则返回失败
         * */
        //获取分片文件夹路径
        String chunkFolder = getChunkFolderByMd5(md5);
        //得到分片存储路径
        String objectName = chunkFolder + chunk;
        Boolean exist = checkFileInMinio(videoBucket, objectName);
        if (Boolean.TRUE.equals(exist)) return RestResponse.success(Boolean.TRUE);

        return RestResponse.success(Boolean.FALSE);
    }

    /**
     * @param md5           md值
     * @param chunk         分片id
     * @param localFilePath 本地文件路径
     * @return com.xuecheng.base.model.RestResponse
     * @description 上传文件分块
     * @author: woldier
     * @date: 2023/3/12 10:50
     */
    @Override
    public RestResponse uploadChuck(String md5, Integer chunk, String localFilePath) {
        /*
         * 1.通过md5获取分片存储文件夹
         * 2.拼接得到分片文件存储路径
         * 2.上传文件到minio
         * */
        //获取文件路径
        String chunkFolder = getChunkFolderByMd5(md5);
        //拼接得到文件存储路径
        String objectName = chunkFolder + chunk;
        //生成文件contentType
        String mimeType = getMimeType(null); //为空会返回
        //上传到服务器
        if (minIOUpload(localFilePath, mimeType, videoBucket, objectName))
            return RestResponse.success(Boolean.TRUE);
        return RestResponse.success(Boolean.FALSE);
    }


    /**
     * @param md5        md5id
     * @param chunkTotal 分块总数
     * @return com.xuecheng.base.model.RestResponse
     * @description 分块合并
     * @author: woldier
     * @date: 2023/3/12 13:01
     */
    @Override
    public RestResponse mergeChunk(String md5, Integer chunkTotal, Long companyId, String fileName) throws IOException {
        /*
         *1.生成分片数组
         * 2.查询分片是否存在
         * 3.合并分片
         * 4.获取合并后文件的信息
         * 5.写入数据库
         * */
        String chunkFolder = getChunkFolderByMd5(md5);
        //生成用于查询是否存在的参数集合
        List<ComposeSource> composeSourceList = Stream.iterate(0, t -> t + 1).limit(chunkTotal).map(
                e -> ComposeSource.builder().bucket(videoBucket).object(chunkFolder + e).build()).collect(Collectors.toList());
        //检查分片是否存在
        for (ComposeSource elem : composeSourceList) {
            if (checkFileInMinio(videoBucket, elem.object()).equals(Boolean.FALSE))
                return RestResponse.success(Boolean.FALSE);
        }
        //合并分片
        //拿到文件存储路径
        String folder = getFolderByMd5(md5); // 得到文件夹
        String suffix = fileName.substring(fileName.lastIndexOf(".")); //得到后缀
        String objectName = folder + md5 + suffix;
        //minio合并,若失败则返回
        if (composeObjectInMinio(videoBucket, objectName, composeSourceList)) RestResponse.success(false);
        long size = -1L;
        File download = File.createTempFile("download",".temp");//创建一个临时文件
        try (
                InputStream getObjectResponse = minioClient.getObject(GetObjectArgs.builder().bucket(videoBucket).object(objectName).build()); //minio文件输入流
                FileOutputStream fileOutputStream = new FileOutputStream(download) //本地文件输出流
        ){
            IOUtils.copy(getObjectResponse,fileOutputStream);  //input -> output
            String downLoadMd5 = DigestUtils.md5Hex(Files.newInputStream(download.toPath())); //得到下载文件的md5值
            if(!downLoadMd5.equals(md5)) { //如果两个md5值不相同
                deleteObjInMinio(videoBucket, objectName); //删除对应文件
                return RestResponse.validfail("合并后的文件md5值与原md5值不一致");
            }

            size = download.length();
            //异步线程将本地文件上传到minio 解决etag不一致问题

            minIOUpload(download.getPath(),getMimeType(fileName),videoBucket,objectName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            boolean delete = download.delete(); //删除临时文件
        }
        //md5值相等说明合并分片成功


        //操作数据库
        MediaFileService currentProxy = (MediaFileService) AopContext.currentProxy();
        UploadFileParamsDto uploadFileParamsDto = new UploadFileParamsDto();
        //设置文件名
        uploadFileParamsDto.setFilename(fileName);
        //设置文件大小
        StatObjectResponse objStatInMinio = getObjStatInMinio(videoBucket, objectName); //得到对象信息
        if (objStatInMinio== null) {
            log.debug("从minio中获取合并后的文件信息失败,bucket{},objectName{}",videoBucket,objectName);
            return RestResponse.success(false);
        }
        uploadFileParamsDto.setFileSize(size);
        //文件类型
        uploadFileParamsDto.setFileType(MediaResourceType.VIDEO.getCode());
        //文件标签
        uploadFileParamsDto.setTags("课程视频");
        currentProxy.insertMediaFile2DB(companyId, uploadFileParamsDto, md5, videoBucket, objectName);


        //删除分片数据

        composeSourceList.forEach(e->{
            deleteObjInMinio(videoBucket,e.object());
        });

        return RestResponse.success(true);
    }
    /**
    * @description 删除对象
    * @param bucket
     * @param objectName
    * @return boolean
    * @author: woldier
    * @date: 2023/3/12 16:40
    */
    private boolean deleteObjInMinio(String bucket, String objectName){
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucket)
                            .object(objectName)
                            .build()
            );
        }
        catch (Exception e){
            log.debug("删除失败,bucket{},objectName{},err{}",bucket,objectName,e.getMessage());
            return false;
        }
        return true;
    }
    /**
     * minio合并文件
     *
     * @param bucket            桶
     * @param objectName        对象名
     * @param composeSourceList 分片信息list
     * @return
     */
    private boolean composeObjectInMinio(String bucket, String objectName, List<ComposeSource> composeSourceList) {
        /*minio文件合并*/
        ComposeObjectArgs composeObjectArgs = ComposeObjectArgs.builder().bucket(bucket).object(objectName).sources(composeSourceList).build();
        try {
            minioClient.composeObject(composeObjectArgs);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("合并数据分片出现错误,bucket{},objectName{},errMsg{}", bucket, objectName, e.getMessage());
            return false;
        }
        return true;
    }

    @NotNull
    private static String getMd5(String localFilePath) throws XueChengPlusException {
        String md5 = null;
        try {
            md5 = DigestUtils.md5Hex(Files.newInputStream(new File(localFilePath).toPath()));
        } catch (IOException e) {
            XueChengPlusException.cast("md5计算时出错");
        }
        return md5;
    }

    /**
     * @param fileName 带后缀文件名 若为空 返回 MediaType.APPLICATION_OCTET_STREAM_VALUE;
     * @return java.lang.String
     * @description 根据文件后缀名获取MimeType
     * @author: woldier
     * @date: 2023/3/10 13:55
     */
    private String getMimeType(String fileName) {
        if (fileName == null) fileName = "";
        ContentInfo contentInfo = ContentInfoUtil.findExtensionMatch(fileName);
        String mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        if (contentInfo != null) mimeType = contentInfo.getMimeType();
        return mimeType;
    }

    /**
     * @param localFilePath 本地文件路径
     * @param fileType      文件类型
     * @param bucket        桶名称
     * @return boolean
     * @description 上传文件到MinIO的方法
     * @author: woldier
     * @date: 2023/3/10 13:33
     */
    private boolean minIOUpload(String localFilePath, String fileType, String bucket, String objectName) {
        /*上传*/
        try {
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket(bucket)  //桶
                            .object(objectName) // 对象名,在桶下存储的文件
                            .filename(localFilePath)  //指定本地文件路径
                            .contentType(fileType) //设置媒体文件类型
                            .build()
            );
        } catch (Exception e) {
            log.error("文件上传到MinIO出错,buckcet:{},path:{},error:{}", bucket, objectName, e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;

    }

    /**
    * @description 获取代理对象
    *
    * @return com.xuecheng.media.service.MediaFileService
    * @author: woldier
    * @date: 2023/3/13 9:20
    */
    private MediaFileService getAopContextProxy(){
        return (MediaFileService) AopContext.currentProxy();
    }

}
