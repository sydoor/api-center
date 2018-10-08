package com.lizikj.api.enums;

/**
 * @author lxl
 * @Date 2018/3/5 16:51
 */
public enum MemberRoleEnum {
    MERCHANT_MEMBER("merchant_member"),
    TENDER_MEMBER("tender_member");

    private String description;

    MemberRoleEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
