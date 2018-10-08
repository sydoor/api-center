package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by liangxiaolin on 2017/8/10.
 */
@ApiModel
public class MerchantMemberReportVO {
    /**
     * 统计日期
     */
    @ApiModelProperty(value = "统计日期")
    private Date reportDate;

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
     * 店铺ID
     */
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    /**
     * 店铺名称
     */
    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    /**
     * 新增会员数
     */
    @ApiModelProperty(value = "新增会员数")
    private Integer memberIncreaseNum;
    /**
     * 活跃会员数
     */
    @ApiModelProperty(value = "活跃会员数")
    private Integer memberActiveNum;
    /**
     * 会员消费总次数
     */
    @ApiModelProperty(value = "会员消费总次数")
    private Integer memberConsumeNum;
    /**
     * 会员退款总次数
     */
    @ApiModelProperty(value = "会员退款总次数")
    private Integer memberRefundNum;
    /**
     * 会员总数(冗余)
     */
    @ApiModelProperty(value = "会员总数")
    private Integer memberTotalNum;
    /**
     * 会员消费总额
     */
    @ApiModelProperty(value = "会员消费总额")
    private Long memberAmount;
    /**
     * 会员退款总额
     */
    @ApiModelProperty(value = "会员退款总额")
    private Long memberRefundAmount;
    /**
     * 会员优惠总金额
     */
    @ApiModelProperty(value = "会员优惠总金额")
    private Long memberBenefitAmount;
    /**
     * 会员消费占总营业额比例
     */
    @ApiModelProperty(value = "会员消费占总营业额比例")
    private Integer memberAmountRate;

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getMemberIncreaseNum() {
        return memberIncreaseNum;
    }

    public void setMemberIncreaseNum(Integer memberIncreaseNum) {
        this.memberIncreaseNum = memberIncreaseNum;
    }

    public Integer getMemberActiveNum() {
        return memberActiveNum;
    }

    public void setMemberActiveNum(Integer memberActiveNum) {
        this.memberActiveNum = memberActiveNum;
    }

    public Integer getMemberConsumeNum() {
        return memberConsumeNum;
    }

    public void setMemberConsumeNum(Integer memberConsumeNum) {
        this.memberConsumeNum = memberConsumeNum;
    }

    public Integer getMemberRefundNum() {
        return memberRefundNum;
    }

    public void setMemberRefundNum(Integer memberRefundNum) {
        this.memberRefundNum = memberRefundNum;
    }

    public Integer getMemberTotalNum() {
        return memberTotalNum;
    }

    public void setMemberTotalNum(Integer memberTotalNum) {
        this.memberTotalNum = memberTotalNum;
    }

    public Long getMemberAmount() {
        return memberAmount;
    }

    public void setMemberAmount(Long memberAmount) {
        this.memberAmount = memberAmount;
    }

    public Long getMemberRefundAmount() {
        return memberRefundAmount;
    }

    public void setMemberRefundAmount(Long memberRefundAmount) {
        this.memberRefundAmount = memberRefundAmount;
    }

    public Long getMemberBenefitAmount() {
        return memberBenefitAmount;
    }

    public void setMemberBenefitAmount(Long memberBenefitAmount) {
        this.memberBenefitAmount = memberBenefitAmount;
    }

    public Integer getMemberAmountRate() {
        return memberAmountRate;
    }

    public void setMemberAmountRate(Integer memberAmountRate) {
        this.memberAmountRate = memberAmountRate;
    }

    @Override
    public String toString() {
        return "MerchantMemberReportVO{" +
                "reportDate=" + reportDate +
                ", merchantId=" + merchantId +
                ", merchantName='" + merchantName + '\'' +
                ", shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", memberIncreaseNum=" + memberIncreaseNum +
                ", memberActiveNum=" + memberActiveNum +
                ", memberConsumeNum=" + memberConsumeNum +
                ", memberRefundNum=" + memberRefundNum +
                ", memberTotalNum=" + memberTotalNum +
                ", memberAmount=" + memberAmount +
                ", memberRefundAmount=" + memberRefundAmount +
                ", memberBenefitAmount=" + memberBenefitAmount +
                ", memberAmountRate=" + memberAmountRate +
                '}';
    }
}
