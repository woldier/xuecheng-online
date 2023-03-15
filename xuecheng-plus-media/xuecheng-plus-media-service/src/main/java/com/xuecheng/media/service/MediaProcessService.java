package com.xuecheng.media.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.media.model.po.MediaProcess;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author itcast
 * @since 2023-03-10
 */
public interface MediaProcessService extends IService<MediaProcess> {
    /**
     * @description 分片节点获取任务
     * @param shardTotal  节点总数
     * @param shardindex  当前节点的id
     * @param count  获取的条数
     * @return java.util.List<com.xuecheng.media.model.po.MediaProcess>
     * @author: woldier
     * @date: 2023/3/13 11:05
     */
    List<MediaProcess> selectListByShardIndex(int shardTotal, int shardindex, int count);


    /**
     *  开启一个任务
     * @param id 任务id
     * @return true开启任务成功，false开启任务失败
     */
    public boolean startTask(long id);

    /**
     * @description 保存任务结果
     * @param taskId 任务id
     * @param status 状态
     * @param fileId 文件id
     * @param url 设置url
     * @param errorMsg  错误信息
     * @return void
     * @author: woldier
     * @date: 2023/3/13 11:12
     */
    void saveProcessFinishStatus(Long taskId,String status,String fileId,String url,String errorMsg);



}
