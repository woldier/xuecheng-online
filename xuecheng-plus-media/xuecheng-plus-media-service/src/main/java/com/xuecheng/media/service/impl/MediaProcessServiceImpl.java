package com.xuecheng.media.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.media.mapper.MediaProcessMapper;
import com.xuecheng.media.model.dto.MediaProcessStat;
import com.xuecheng.media.model.po.MediaFiles;
import com.xuecheng.media.model.po.MediaProcess;
import com.xuecheng.media.model.po.MediaProcessHistory;
import com.xuecheng.media.service.MediaFileService;
import com.xuecheng.media.service.MediaProcessHistoryService;
import com.xuecheng.media.service.MediaProcessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author itcast
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MediaProcessServiceImpl extends ServiceImpl<MediaProcessMapper, MediaProcess> implements MediaProcessService {

    private final MediaProcessHistoryService mediaProcessHistoryService;
    private final MediaFileService mediaFileService;
    /**
     * @description 分片节点获取任务
     * @param shardTotal  节点总数
     * @param shardindex  当前节点的id
     * @param count  获取的条数
     * @return java.util.List<com.xuecheng.media.model.po.MediaProcess>
     * @author: woldier
     * @date: 2023/3/13 11:05
     */
    @Override
    public List<MediaProcess> selectListByShardIndex(int shardTotal, int shardindex, int count) {
        return baseMapper.selectListByShardIndex(shardTotal,shardindex,count);
    }



    /**
     *  开启一个任务
     * @param id 任务id
     * @return true开启任务成功，false开启任务失败
     */
    @Override
    public boolean startTask(long id) {
        if (baseMapper.startTask(id)>0) return true;
        return false;
    }

    /**
     * @description 保存任务结果
     * @param taskId 任务id/
     * @param status 状态
     * @param fileId 文件id
     * @param url 设置url
     * @param errorMsg  错误信息
     * @return void
     * @author: woldier
     * @date: 2023/3/13 11:12
     */
    @Override
    @Transactional
    public void saveProcessFinishStatus(Long taskId, String status, String fileId, String url, String errorMsg) {
        /*
        * 任务处理完成需要更新任务处理结果，
        * 任务执行成功更新视频的URL、及任务处理结果，
        * 将待处理任务记录删除，
        * 同时向历史任务表添加记录
        * */
        MediaProcess mediaProcess = this.getById(taskId);
        if(mediaProcess==null) return; //查询到数据库未空,直接返回
        if(status.equals(MediaProcessStat.SUCCESS.getCode())){//如果执行成功
            //从数据库获取数据

            //设置状态
            mediaProcess.setStatus(MediaProcessStat.SUCCESS.getCode());
            //设置完成时间
            mediaProcess.setFinishDate(LocalDateTime.now());
            //从MediaProcess表删除,并且把数据插入到MediaProcessHistory
            this.removeById(taskId);
            //创建插入到MediaProcessHistory的对象
            MediaProcessHistory mediaProcessHistory = new MediaProcessHistory();
            BeanUtils.copyProperties(mediaProcess,mediaProcessHistory);
            mediaProcessHistory.setUrl(url);
            mediaProcessHistoryService.save(mediaProcessHistory);
            //在MediaFile表中设置Url
            MediaFiles mediaFiles = new MediaFiles();
            mediaFiles.setId(fileId);
            mediaFiles.setUrl(url);
            mediaFileService.updateById(mediaFiles);


        }else if (status.equals(MediaProcessStat.FAILED.getCode())){ //执行失败
            //执行失败,设置状态未失败,设置错误信息,并且将失败次数加1
            baseMapper.updateFailInfo(taskId,MediaProcessStat.FAILED.getCode(), errorMsg);
        }

    }



}
