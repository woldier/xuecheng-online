package com.xuecheng.content.model.enums;

/**
 * @author woldier
 * @version 1.0
 * @description 课程发布状态
 *[{"code":"203001","desc":"未发布"},
 * {"code":"203002","desc":"已发布"},
 * {"code":"203003","desc":"下线"}]
 * @date 2023/3/26 17:51
 **/
public enum CourseStatus{
    PUBLISHED("203001","未发布"),
    NOT_PUBLISH("203002","已发布"),
    OFFLINE("203003","下线")
    ;

    private String code ;
    private String desc ;

    CourseStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
