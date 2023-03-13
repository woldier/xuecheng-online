package com.xuecheng.media.service.jobhandler;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author woldier
 * @version 1.0
 * @description 任务处理类
 * @date 2023/3/12 22:03
 **/
@Component
public class SampleJob {
    private static Logger logger = LoggerFactory.getLogger(SampleJob.class);
    /**
    * @description Bean模式
    *
    * @return void
    * @author: woldier
    * @date: 2023/3/12 22:05
    */
    @XxlJob("testJob")
    public void testJob(){
        logger.debug(">>>>>>>>>>>job test");
    }

    @XxlJob("shardingJobHandler")
    public void shardingJobHandler() throws Exception {
        // 分片序号，从0开始
        int shardIndex = XxlJobHelper.getShardIndex();
        // 分片总数
        int shardTotal = XxlJobHelper.getShardTotal();
        logger.info("分片参数：当前分片序号 = {}, 总分片数 = {}", shardIndex, shardTotal);
        logger.info("开始执行第"+shardIndex+"批任务");

    }
}
