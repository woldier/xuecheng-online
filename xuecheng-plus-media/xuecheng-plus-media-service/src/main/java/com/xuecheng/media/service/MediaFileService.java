package com.xuecheng.media.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.base.model.RestResponse;
import com.xuecheng.media.model.dto.QueryMediaParamsDto;
import com.xuecheng.media.model.dto.UploadFileParamsDto;
import com.xuecheng.media.model.dto.UploadFileResultDto;
import com.xuecheng.media.model.po.MediaFiles;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Mr.M
 * @version 1.0
 * @description 媒资文件管理业务类
 * @date 2022/9/10 8:55
 */
public interface MediaFileService extends IService<MediaFiles> {

    /**
     * @param pageParams          分页参数
     * @param queryMediaParamsDto 查询条件
     * @return com.xuecheng.base.model.PageResult<com.xuecheng.media.model.po.MediaFiles>
     * @description 媒资文件查询方法
     * @author Mr.M
     * @date 2022/9/10 8:57
     */
    public PageResult<MediaFiles> queryMediaFiels(Long companyId, PageParams pageParams, QueryMediaParamsDto queryMediaParamsDto);

    /**
     * @param companyId           公司ID
     * @param uploadFileParamsDto 上传文件参数类
     * @param LocalFilePath       要上传的文件其本地路径
     * @return com.xuecheng.media.model.dto.UploadFileResultDto
     * @description 上传文件
     * @author: woldier
     * @date: 2023/3/10 13:36
     */
    UploadFileResultDto uploadFile(Long companyId, UploadFileParamsDto uploadFileParamsDto, String LocalFilePath) throws XueChengPlusException;

    /**
     * @param companyId
     * @param uploadFileParamsDto
     * @param md5
     * @param bucket
     * @param objectName
     * @return com.xuecheng.media.model.po.MediaFiles
     * @description 将上传的文件插入数据库
     * @author: woldier
     * @date: 2023/3/10 16:42
     */
    MediaFiles insertMediaFile2DB(Long companyId, UploadFileParamsDto uploadFileParamsDto, String md5, String bucket, String objectName);

    /**
     * @param md5
     * @param bucket
     * @param objectName
     * @param mimeType
     * @return void
     * @description 判断是否是avi格式视频, 若是插入到mediaProcess
     * @author: woldier
     * @date: 2023/3/13 9:22
     */
    void insertMediaProcessTask(String md5, String bucket, String objectName, String mimeType);

    /**
     * @param md5 文件md值
     * @return com.xuecheng.base.model.RestResponse
     * @description 检查文件是否存在
     * @author: woldier
     * @date: 2023/3/11 22:31
     */
    RestResponse checkFile(String md5);


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
    RestResponse checkChunk(String md5, Integer chunk);


    /**
     * @param md5           md值
     * @param chunk         分片id
     * @param localFilePath 本地文件路径
     * @return com.xuecheng.base.model.RestResponse
     * @description 上传文件分块
     * @author: woldier
     * @date: 2023/3/12 10:50
     */
    RestResponse uploadChuck(String md5, Integer chunk, String localFilePath);


    /**
     * @param md5        md5id
     * @param chunkTotal 分块总数
     * @return com.xuecheng.base.model.RestResponse
     * @description 分块合并
     * @author: woldier
     * @date: 2023/3/12 13:01
     */
    RestResponse mergeChunk(String md5, Integer chunkTotal, Long companyId, String fileName) throws IOException;


    /**
     * @param localFilePath 本地文件路径
     * @param fileType      文件类型
     * @param bucket        桶名称
     * @return boolean
     * @description 上传文件到MinIO的方法
     * @author: woldier
     * @date: 2023/3/10 13:33
     */
    public boolean minIOUpload(String localFilePath, String fileType, String bucket, String objectName);


    /***
     * @description minio文件下载 到本地
     * @param bucket 桶
     * @param objectName  对象名
     * @return java.io.File 返回一个文件对象
     * @author: woldier
     * @date: 2023/3/15 21:37
     */
    public File minIODownLoad(String bucket, String objectName);

    /**
     * @param localFilePath 临时文件路径
     * @param objectName    网页名
     * @return void
     * @description 上传文件对象名
     * @author: woldier
     * @date: 2023/3/27 15:31
     */

    void uploadObject(String localFilePath, String objectName);
}
