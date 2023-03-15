package com.xuecheng.media.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuecheng.media.model.po.MediaProcess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author woldier
 */
@Mapper
public interface MediaProcessMapper extends BaseMapper<MediaProcess> {
    /**
    * @description 分片节点获取任务
     * 查询数据库中id % shardTotal =  shardindex 的数据,并且限定数据的状态必须为1或者3
    * @param shardTotal  节点总数
     * @param shardindex  当前节点的id
     * @param count  获取的条数
    * @return java.util.List<com.xuecheng.media.model.po.MediaProcess>
    * @author: woldier
    * @date: 2023/3/13 11:05
    */
    @Select("SELECT t.* FROM media_process t WHERE t.id % #{shardTotal} = #{shardindex} and (t.status='1' or t.status='3') and t.fail_count < 3 limit #{count}")
    List<MediaProcess> selectListByShardIndex(@Param("shardTotal") int shardTotal, @Param("shardindex") int shardindex, @Param("count") int count);


    /**
    * @description 开启一个任务
    * @param  id 任务id
    * @return int 更新的任务数量
    * @author: woldier
    * @date: 2023/3/13 15:08
    */
    @Update("update media_process m set m.status='4' where (m.status='1' or m.status='3') and m.fail_count<3 and m.id=#{id}")
    int startTask(@Param("id") long id);

    /**
    * @description 任务失败的跟新操作 ,数据库的失败次数会自动加一
    * @param id 主键
     * @param status 状态
     * @param errormsg  错误信息
    * @return int
    * @author: woldier
    * @date: 2023/3/15 21:09
    */
    @Update("update media_process m set m.status=#{status} , m.errormsg=#{errormsg} ,  m.failCount=m.failCount+1 where m.id=#{id}")
    int updateFailInfo(@Param("id") long id,@Param("status") String status,@Param("errormsg") String errormsg);
}
