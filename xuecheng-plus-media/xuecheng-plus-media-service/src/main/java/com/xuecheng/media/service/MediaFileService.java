package com.xuecheng.media.service;

import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.media.model.dto.QueryMediaParamsDto;
import com.xuecheng.media.model.dto.UploadFileParamsDto;
import com.xuecheng.media.model.dto.UploadFileResultDto;
import com.xuecheng.media.model.po.MediaFiles;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Mr.M
 * @version 1.0
 * @description 媒资文件管理业务类
 * @date 2022/9/10 8:55
 */
public interface MediaFileService {

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
    * @description 将上传的文件插入数据库
    * @param companyId
     * @param uploadFileParamsDto
     * @param md5
     * @param bucket
     * @param objectName
    * @return com.xuecheng.media.model.po.MediaFiles
    * @author: woldier
    * @date: 2023/3/10 16:42
    */
     MediaFiles insertMediaFile2DB(Long companyId, UploadFileParamsDto uploadFileParamsDto, String md5, String bucket,String objectName);

}
