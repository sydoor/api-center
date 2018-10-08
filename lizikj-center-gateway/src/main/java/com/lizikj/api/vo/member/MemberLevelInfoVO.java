package com.lizikj.api.vo.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by liangxiaolin on 2017/9/13.
 */
@ApiModel
public class MemberLevelInfoVO {
    /**
     * 商户ID
     */
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    /**
     * 商户名称
     */
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    /**
     * 会员ID
     */
    @ApiModelProperty(value = "会员ID")
    private Long memberId;
    /**
     * 注册时间
     */
    @ApiModelProperty(value = "注册时间")
    private Date registerDate;
    /**
     * 等级名称
     */
    @ApiModelProperty(value = "等级名称")
    private String levelName;
    /**
     * 等级
     */
    @ApiModelProperty(value = "等级")
    private Integer levelCode;
    /**
     * 折扣
     */
    @ApiModelProperty(value = "折扣")
    private Double discount;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(Integer levelCode) {
        this.levelCode = levelCode;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "MemberLevelInfoVO{" +
                "merchantId=" + merchantId +
                ", merchantName='" + merchantName + '\'' +
                ", memberId=" + memberId +
                ", registerDate=" + registerDate +
                ", levelName='" + levelName + '\'' +
                ", levelCode=" + levelCode +
                ", discount=" + discount +
                '}';
    }
}
