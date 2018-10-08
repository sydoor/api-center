package com.lizikj.api.vo.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liangxiaolin on 2017/8/8.
 */
@ApiModel
public class MerchantMemberCreditSettingVO {
    /**
     * 设置ID
     */
    @ApiModelProperty(value = "设置ID")
    private Long settingId;
    /**
     * 商户ID
     */
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    /**
     * 积分规则 2100 充值获得积分 2300 下单消费奖励积分  2400 下单消费积分
     */
    @ApiModelProperty(value = "积分规则 2100 充值获得积分 2300 下单消费奖励积分  2400 下单消费积分")
    private Integer creditType;
    /**
     * 源值
     */
    @ApiModelProperty(value = "源值")
    private Integer sourceValue;
    /**
     * 获得值
     */
    @ApiModelProperty(value = "获得值")
    private Integer targetValue;
    /**
     * 使用状态 0 正在使用 1 停用
     */
    @ApiModelProperty(value = "使用状态 0 正在使用 1 停用")
    private Boolean status;

    public Long getSettingId() {
        return settingId;
    }

    public void setSettingId(Long settingId) {
        this.settingId = settingId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getCreditType() {
        return creditType;
    }

    public void setCreditType(Integer creditType) {
        this.creditType = creditType;
    }

    public Integer getSourceValue() {
        return sourceValue;
    }

    public void setSourceValue(Integer sourceValue) {
        this.sourceValue = sourceValue;
    }

    public Integer getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(Integer targetValue) {
        this.targetValue = targetValue;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MerchantMemberCreditSettingVO{" +
                "settingId=" + settingId +
                ", merchantId=" + merchantId +
                ", creditType=" + creditType +
                ", sourceValue=" + sourceValue +
                ", targetValue=" + targetValue +
                ", status=" + status +
                '}';
    }
}
