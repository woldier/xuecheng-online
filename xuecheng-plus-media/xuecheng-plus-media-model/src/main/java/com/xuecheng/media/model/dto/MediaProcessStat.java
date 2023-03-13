package com.xuecheng.media.model.dto;

/**
 * @author woldier
 * @version 1.0
 * @description 媒资处理状态枚举
 * 状态,1:未处理，2：处理成功  3处理失败
 * @date 2023/3/13 9:10
 **/
public enum MediaProcessStat {
    Pending ("1","待处理"),
    SUCCESS ("2","处理成功"),
    FAILED ("3","处理失败"),
    PROCESSING("4","处理中")

    ;
    private String code;
    private String description;

    MediaProcessStat(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
