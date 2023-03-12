package com.xuecheng.media.api;

import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.media.model.dto.MediaResourceType;
import com.xuecheng.media.model.dto.QueryMediaParamsDto;
import com.xuecheng.media.model.dto.UploadFileParamsDto;
import com.xuecheng.media.model.dto.UploadFileResultDto;
import com.xuecheng.media.model.po.MediaFiles;
import com.xuecheng.media.service.MediaFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author Mr.M
 * @version 1.0
 * @description 媒资文件管理接口
 * @date 2022/9/6 11:29
 */
@Api(value = "媒资文件管理接口", tags = "媒资文件管理接口")
@RestController
public class MediaFilesController {


    @Autowired
    MediaFileService mediaFileService;


    @ApiOperation("媒资列表查询接口")
    @PostMapping("/files")
    public PageResult<MediaFiles> list(PageParams pageParams, @RequestBody QueryMediaParamsDto queryMediaParamsDto) {
        Long companyId = 1232141425L;
        return mediaFileService.queryMediaFiels(companyId, pageParams, queryMediaParamsDto);

    }

    /**
     * @param upload     表单数据
     * @param folder     文件夹名-非必须
     * @param objectName 文件名-非必须
     * @return com.xuecheng.media.model.dto.UploadFileResultDto
     * @description 文件上传
     * @author: woldier
     * @date: 2023/3/9 22:09
     */
    @ApiOperation("文件上传")
    @RequestMapping(value = "/upload/coursefile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    // 定义请求url与可消费的文件操作content-type类型
    public UploadFileResultDto upload(
            @RequestPart("filedata") MultipartFile upload,
            @RequestParam(value = "folder", required = false) String folder,
            @RequestParam(value = "objectName", required = false) String objectName) throws IOException, XueChengPlusException {
        /*
        *对接受到的文件进行处理
         */
        /*产生一个临时文件*/
        File tempFile = File.createTempFile("minio", "temp");
        /*将请求中的表单数据拷贝到临时文件中*/
        upload.transferTo(tempFile);
        /*获取绝对路径*/
        String absolutePath = tempFile.getAbsolutePath();

        /*
        *对上传参数进行处理
         */

        //上传文件参数类
        UploadFileParamsDto uploadFileParamsDto = new UploadFileParamsDto();
        //原始文件名称
        uploadFileParamsDto.setFilename(upload.getOriginalFilename());
        //文件大小
        uploadFileParamsDto.setFileSize(upload.getSize());
        //文件类型
        uploadFileParamsDto.setFileType(MediaResourceType.IMAGE.getCode());




        /*
        * 公司id获取
        * */
        //TODO 硬编码公司id
        Long companyId = getCompanyId();

        UploadFileResultDto uploadFileResultDto = mediaFileService.uploadFile(companyId, uploadFileParamsDto, absolutePath);

        /*删除临时文件*/
        tempFile.deleteOnExit();
        return uploadFileResultDto;
    }

    private long getCompanyId(){
        return 1232141425L;
    }
}
