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
import com.xuecheng.media.model.dto.MediaAuditStatus;
import com.xuecheng.media.model.dto.QueryMediaParamsDto;
import com.xuecheng.media.model.dto.UploadFileParamsDto;
import com.xuecheng.media.model.dto.UploadFileResultDto;
import com.xuecheng.media.model.po.MediaFiles;
import com.xuecheng.media.service.MediaFileService;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
            mediaFiles.setUrl("/" + bucket + "/" + objectName);
            //上传时间,更新时间自动设置
            mediaFiles.setCreateDate(LocalDateTime.now());
            mediaFiles.setCreateDate(LocalDateTime.now());
            //文件状态
            mediaFiles.setStatus("1");
            //审核状态
            mediaFiles.setAuditStatus(MediaAuditStatus.Approved.getCode());

            int insert = mediaFilesMapper.insert(mediaFiles);

            if (insert <= 0) {
                log.debug("向数据库保存文件失败,bucket:{},objectName{}", fileBucket, objectName);
                return null;
            }
            return mediaFiles;
        }
        return files;
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
//        if (mediaFiles != null) {//若不为空
//            String objectName = md5TObjectName(md5);//解析md5值得到存储在minio中的对象,带后缀.mp4
//
//            InputStream objectResponse = null;
//            try {
//                objectResponse = minioClient.getObject(GetObjectArgs.builder().bucket(videoBucket).object(objectName).build());
//                if (objectResponse != null) return RestResponse.success(Boolean.TRUE);
//            } catch (Exception e) {
//
//            }
//            return RestResponse.success(Boolean.FALSE);
//
//        }
        if(mediaFiles!=null){//数据库中查询到不为空
            //获取桶
            String bucket = mediaFiles.getBucket();
            //获取存储路径
            String filePath = mediaFiles.getFilePath();
            try(InputStream inputStream = minioClient.getObject(GetObjectArgs.builder().bucket(bucket).object(filePath).build())){ //通过这种方法创建额流会在try catch后自动释放
                //若input流对象不为空,说明minio中有数据,那么返回存在
                if (inputStream != null) return RestResponse.success(Boolean.TRUE);
            }catch (Exception e){
                log.debug("在minio中获取对象出错bucket:{},objectName{},errInfo{}",bucket,filePath,e.getMessage());
            }
        }

        //查询到数据库为空,或者查询minio报错返回错误
        return RestResponse.success(Boolean.FALSE);
    }

    /**
     * @param md5    md5值
     * @param suffix 文件后缀
     * @return java.lang.String
     * @description 根据md5值得到存储在minio中的对象
     * @author: woldier
     * @date: 2023/3/11 22:57
     */
    private String md5TObjectName(String md5, String suffix) {
        if (suffix.contains(".")) {
            //若传入的后缀带.我们将.去掉
            suffix = suffix.substring(suffix.indexOf("."));
        }
        return md5.substring(0, 1) + "/" + md5.substring(1, 2) + "/" + md5 + "." + suffix;

    }

    /**
     * @param md5
     * @return java.lang.String
     * @description 根据md5值得到存储在minio中的对象, 默认对象后缀为mp4
     * @author: woldier
     * @date: 2023/3/11 23:02
     */
    private String md5TObjectName(String md5) {
        return md5TObjectName(md5, "mp4");
    }

    @Override
    public RestResponse checkChunk(String md5, Integer chunk) {
        return null;
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
     * @param fileName 带后缀文件名
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
}
