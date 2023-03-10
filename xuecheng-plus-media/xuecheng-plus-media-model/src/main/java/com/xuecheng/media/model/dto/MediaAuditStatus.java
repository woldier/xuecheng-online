package com.xuecheng.media.model.dto;

/**
 * @author woldier
 * @version 1.0
 * @description 审核状态
 * [{"code":"002001","desc":"审核未通过"},
 * {"code":"002002","desc":"未审核"},
 * {"code":"002003","desc":"审核通过"}]
 * @date 2023/3/10 14:55
 **/
public enum MediaAuditStatus {

    NOT_Approved("002001","审核未通过"),

    Not_Audited("002002","未审核"),
    Approved("002003","审核通过");

    private String code;
    private String description;

     MediaAuditStatus(String code, String description){
        this.code = code;
        this.description = description;

    }

    public String getCode() {
        return code;
    }
}
