package com.xuecheng.content.service;

import com.xuecheng.base.exception.XueChengPlusException;

/**
 * @author woldier
 * @version 1.0
 * @description 课程预发布
 * @date 2023/3/25 18:50
 **/
public interface CoursePublishPreCustomService {
    /**
    * @description 提交审核
    * @param companyId 公司id
     * @param courseId  课程id
    * @return void
    * @author: woldier
    * @date: 2023/3/25 18:55
    */
    void commitAudit(Long companyId,Long courseId) throws XueChengPlusException;
}
