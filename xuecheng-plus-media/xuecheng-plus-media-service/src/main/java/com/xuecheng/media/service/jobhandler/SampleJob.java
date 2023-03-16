package com.xuecheng.media.service.jobhandler;

import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;
import com.xuecheng.base.utils.Mp4VideoUtil;
import com.xuecheng.media.model.dto.MediaProcessStat;
import com.xuecheng.media.model.po.MediaProcess;
import com.xuecheng.media.service.MediaFileService;
import com.xuecheng.media.service.MediaProcessService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author woldier
 * @version 1.0
 * @description 任务处理类
 * @date 2023/3/12 22:03
 **/
@Component
@RequiredArgsConstructor
public class SampleJob {
    private final MediaFileService mediaFileService;
    private final MediaProcessService mediaProcessService;
    private static Logger logger = LoggerFactory.getLogger(SampleJob.class);

    @Value("${videoprocess.ffmpegpath}")
    private String ffmpegpath;

    /**
     * @return void
     * @description 分片模式
     * @author: woldier
     * @date: 2023/3/12 22:05
     */
    @XxlJob("videoJobHandler")
    public void videoJobHandler() throws Exception {
        /*
         * 1. 从数据库获取任务
         * 2. 从minio下载相应的文件
         * 3. 开始转码
         * 4. 向数据库保存操作结果
         * */
        // 分片序号，从0开始
        int shardIndex = XxlJobHelper.getShardIndex();
        // 分片总数
        int shardTotal = XxlJobHelper.getShardTotal();
        //取出cpu核心数作为一次处理数据的条数
        int processors = Runtime.getRuntime().availableProcessors();
        //从数据库取出数据
        List<MediaProcess> mediaProcessList = mediaProcessService.selectListByShardIndex(shardTotal, shardIndex, processors);
        //如果没有数据,直接退出
        if (mediaProcessList.isEmpty()) return;
        int size = mediaProcessList.size();
        //启动size个线程的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(size);
        //计数器
        CountDownLatch countDownLatch = new CountDownLatch(size);

        //开启线程池处理任务
        mediaProcessList.forEach(
                mediaProcess -> {
                    threadPool.execute(() -> {
                                /*
                                 * 1.获取id
                                 * 2.抢占任务
                                 * 3.下载文件
                                 * 4.处理文件
                                 * */
                                Long id = mediaProcess.getId();
                                //抢占任务不成功直接返回
                                if (!mediaProcessService.startTask(id)) {
                                    logger.debug("抢占任务不成功");
                                    return;
                                }
                                //从minio下载文件
                                File minIODownLoad = mediaFileService.minIODownLoad(mediaProcess.getBucket(), mediaProcess.getFilename());
                                if (minIODownLoad == null) {
                                    logger.debug("下载文件失败");
                                    mediaProcessService.saveProcessFinishStatus(mediaProcess.getId(), MediaProcessStat.FAILED.getCode(), mediaProcess.getFileId(), null, "文件下载失败");
                                    return;

                                }
                                //开始处理视频
                                File mp4File = null;
                                try {
                                    mp4File = File.createTempFile("mp4", ".mp4");
                                } catch (Exception e) {
                                    logger.debug("创建转码后文件失败");
                                    mediaProcessService.saveProcessFinishStatus(mediaProcess.getId(), MediaProcessStat.FAILED.getCode(), mediaProcess.getFileId(), null, "转码后创建文件失败");
                                    return;
                                }
                                //视频处理结果
                                String result = "";
                                try {
                                    //开始处理视频
                                    Mp4VideoUtil videoUtil = new Mp4VideoUtil(ffmpegpath, minIODownLoad.getAbsolutePath(), mp4File.getName(), mp4File.getAbsolutePath());
                                    //开始视频转换，成功将返回success
                                    result = videoUtil.generateMp4();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    logger.error("处理视频文件:{},出错:{}", mediaProcess.getFilePath(), e.getMessage());
                                    mediaProcessService.saveProcessFinishStatus(mediaProcess.getId(), MediaProcessStat.FAILED.getCode(), mediaProcess.getFileId(), null, "处理视频文件出错");
                                }
                                if (!result.equals("success")) {
                                    //记录错误信息
                                    logger.error("处理视频失败,视频地址:{},错误信息:{}", mediaProcess.getBucket() + mediaProcess.getFilename(), result);

                                    mediaProcessService.saveProcessFinishStatus(mediaProcess.getId(), MediaProcessStat.FAILED.getCode(), mediaProcess.getFileId(), null, result);

                                }
                                //上传到minio
                                String objectName = getFilePath(mediaProcess.getFileId(), ".mp4");
                                if (!mediaFileService.minIOUpload(mp4File.getPath(), getMimeType(objectName), mediaProcess.getBucket(), objectName)) {
                                    logger.error("上传文件出错");
                                    mediaProcessService.saveProcessFinishStatus(mediaProcess.getId(), MediaProcessStat.FAILED.getCode(), mediaProcess.getFileId(), null, result);
                                }
                                //设置处理结果
                                mediaProcessService.saveProcessFinishStatus(mediaProcess.getId(), MediaProcessStat.SUCCESS.getCode(), mediaProcess.getFileId(), "/"+mediaProcess.getBucket() + "/"+objectName, result);
                                minIODownLoad.delete();
                                mp4File.delete();
                                countDownLatch.countDown();
                            }
                    );

                }

        );
        //等待,给一个充裕的超时时间,防止无限等待，到达超时时间还没有处理完成则结束任务
        countDownLatch.await(30, TimeUnit.MINUTES);
    }


    /**
     * @param fileMd5 原文件md5
     * @param fileExt
     * @return java.lang.String
     * @description 获取对象存储路径
     * @author: woldier
     * @date: 2023/3/15 22:13
     */
    private String getFilePath(String fileMd5, String fileExt) {
        return fileMd5.substring(0, 1) + "/" + fileMd5.substring(1, 2) + "/" + fileMd5 + "/" + fileMd5 + fileExt;
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

}
