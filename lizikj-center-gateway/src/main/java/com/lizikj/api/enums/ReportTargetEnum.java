package com.lizikj.api.enums;

public enum ReportTargetEnum {
    //统计对象（1 所有、2 商户、3 店铺）
    PLATFORM(1,"平台"),
    MERCHANT(2,"商户"),
    SHOP(3,"店铺");

    private int code;
    private String description;

    ReportTargetEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static ReportTargetEnum fromCode(int code){
        for(ReportTargetEnum reportTargetEnum:values()){
            if(reportTargetEnum.getCode() == code){
                return reportTargetEnum;
            }
        }

        return null;
    }
}
