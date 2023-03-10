package com.xuecheng.media.model.dto;

/**
 * @author woldier
 * @version 1.0
 * @description 媒体资源类型枚举
 * [{"code":"001001","desc":"图片"},{"code":"001002","desc":"视频"},{"code":"001003","desc":"其它"}]
 * @date 2023/3/10 15:45
 **/
public enum MediaResourceType {
    IMAGE("001001","图片"),
    VIDEO("001002","视频"),
    OTHER("001003","其它")
    ;

    private String code;
    private String description;

    MediaResourceType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }
}
