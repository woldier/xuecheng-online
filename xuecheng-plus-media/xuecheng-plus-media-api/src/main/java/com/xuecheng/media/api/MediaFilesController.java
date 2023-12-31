package com.xuecheng.media.api;

import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;
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
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
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
@Slf4j
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
     * @param upload 表单数据
     * @return com.xuecheng.media.model.dto.UploadFileResultDto
     * @description 文件上传
     * @author: woldier
     * @date: 2023/3/9 22:09
     */
    @ApiOperation("文件上传")
    @RequestMapping(value = "/upload/coursefile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    // 定义请求url与可消费的文件操作content-type类型
    public UploadFileResultDto upload(
            @RequestPart("filedata") MultipartFile upload
    ) throws IOException, XueChengPlusException {
        /*
         *对接受到的文件进行处理
         */
        /*产生一个临时文件*/
        File tempFile = getTempFile(upload);
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

    @NotNull
    private static File getTempFile(MultipartFile upload) throws IOException {
        File tempFile = File.createTempFile("minio", "temp");
        /*将请求中的表单数据拷贝到临时文件中*/
        upload.transferTo(tempFile);
        return tempFile;
    }

    @ApiOperation("静态化网页上传")
    @RequestMapping(value = "/upload/coursehtml", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadHtml(
            @RequestPart("filedata") MultipartFile upload,
            @RequestParam(value = "objectName", required = false) String objectName) throws IOException {
        File tempFile = getTempFile(upload);
        mediaFileService.uploadObject(tempFile.getAbsolutePath(),"course/"+ objectName);
        boolean delete = tempFile.delete();
        if (!delete)
            log.error("删除文件错误{}", tempFile.getAbsolutePath());
    }

    private long getCompanyId() {
        return 1232141425L;
    }


}
