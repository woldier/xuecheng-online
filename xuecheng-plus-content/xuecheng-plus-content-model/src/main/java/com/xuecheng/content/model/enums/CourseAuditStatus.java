package com.xuecheng.content.model.enums;

/**
 * @author woldier
 * @version 1.0
 * @description 课程审核状态
 *
 *
 * [{"code":"202001","desc":"审核未通过"},
 * {"code":"202002","desc":"未提交"},
 * {"code":"202003","desc":"已提交"},
 * {"code":"202004","desc":"审核通过"}]
 * @date 2023/3/25 21:31
 **/

public enum CourseAuditStatus {

    NOT_AUDIT("202001","审核未通过"),
    NOT_COMMIT("202002","未提交"),
    COMMIT("202003","已提交"),
    AUDIT("202004","审核通过");

    private String code ;
    private String desc ;

    CourseAuditStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }
}
