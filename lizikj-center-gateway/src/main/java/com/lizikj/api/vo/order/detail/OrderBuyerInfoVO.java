package com.lizikj.api.vo.order.detail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by zhoufe on 2017-08-8 19:49:54
 */
@ApiModel
public class OrderBuyerInfoVO {


    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**
     * 李子会员id
     */
    @ApiModelProperty(value = "会员ID")
    private Long memberId;

    /**
     * 商户会员id
     */
    @ApiModelProperty(value = "商户会员ID")
    private Long merchantMemberId;

    /**
     * 撩美味的股东会员ID
     */
    @ApiModelProperty(value = "撩美味的股东会员ID")
    private Long partnerMemberId;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;

    /**
     * 会员折扣
     */
    @ApiModelProperty(value = "会员折扣")
    private Double discount;

    /**
     * 一个订单的会员积分
     */
    @ApiModelProperty(value = "会员积分")
    private Long memberPoints;

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getMerchantMemberId() {
        return merchantMemberId;
    }

    public void setMerchantMemberId(Long merchantMemberId) {
        this.merchantMemberId = merchantMemberId;
    }

    public Long getPartnerMemberId() {
        return partnerMemberId;
    }

    public void setPartnerMemberId(Long partnerMemberId) {
        this.partnerMemberId = partnerMemberId;
    }

    public Long getMemberPoints() {
        return memberPoints;
    }

    public void setMemberPoints(Long memberPoints) {
        this.memberPoints = memberPoints;
    }
}
