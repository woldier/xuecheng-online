package com.xuecheng.media.api;

import com.xuecheng.base.exception.CommonError;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.base.model.RestResponse;
import com.xuecheng.media.service.MediaFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.File;
import java.io.IOException;

/**
 * @author woldier
 * @version 1.0
 * @description 大文件上传接口
 * @date 2023/3/11 11:43
 **/
@Api(value = "大文件上传",tags = "大文件上传接口")
@RestController
@RequiredArgsConstructor
@Slf4j
public class BigFileController {

    private final MediaFileService mediaFileService;
    /**
    * @description 文件上传前的检查接口
    * @param fileMd5  文件md5值
    * @return com.xuecheng.base.model.RestResponse
    * @author: woldier
    * @date: 2023/3/11 11:54
    */
    @ApiOperation("检查文件是否存在")
    @PostMapping("/upload/checkfile")
    public RestResponse checkFile(
            @RequestParam(value = "fileMd5")  String  fileMd5
    ){
        return mediaFileService.checkFile(fileMd5);

    }

    /**
    * @description 检查分片是否存在
    * @param fileMd5 文件md5值
     * @param chunk  分片id
    * @return com.xuecheng.base.model.RestResponse
    * @author: woldier
    * @date: 2023/3/11 11:59
    */
    @ApiOperation("检查分片是否存在")
    @PostMapping("/upload/checkchunk")
    public RestResponse checkChunk(
            @RequestParam(value = "fileMd5") String  fileMd5,
            @RequestParam(value = "chunk") Integer chunk
            ){
        return mediaFileService.checkChunk(fileMd5, chunk);
    }


    /**
    * @description 分片上传接口
    * @param file 文件
     * @param fileMd5 分片md5
     * @param chunk  分片id
    * @return com.xuecheng.base.model.RestResponse
    * @author: woldier
    * @date: 2023/3/11 12:04
    */
    @ApiOperation("上传分片")
    @PostMapping("/upload/uploadchunk")
    public RestResponse uploadChunk(
            @RequestParam("file") MultipartFile file,
            @RequestParam("fileMd5")  String fileMd5,
            @RequestParam("chunk")  Integer chunk
            ) throws IOException {
        /*
        * 1.暂存file到本地
        * 2. 调用service服务
        * */
        //创建临时文件
        File tempFile =  File.createTempFile("minio", "temp");
        file.transferTo(tempFile);
        String localFilePath = tempFile.getAbsolutePath();

        RestResponse restResponse = mediaFileService.uploadChuck(fileMd5, chunk, localFilePath);
        //删除临时文件
        if(!tempFile.delete()) log.debug("删除临时文件失败,filePath{}",localFilePath);
        return restResponse;
    }

    /**
    * @description 合并分片
    * @param fileName 文件名
     * @param fileMd5 文件md5值
     * @param chunkTotal  分片总数
    * @return com.xuecheng.base.model.RestResponse
    * @author: woldier
    * @date: 2023/3/11 12:18
    */
    @ApiOperation("合并分片")
    @PostMapping("/upload/mergechunk")
    public RestResponse mergeChunks(
            @RequestParam("fileMd5")  String fileMd5,
            @RequestParam("fileName") String fileName,
            @RequestParam("chunkTotal")  Integer chunkTotal
    ){
        return null;
    }
}
