package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lxl
 * @Date 2018/1/5 15:34
 */
@ApiModel
public class OrderBenefitReportVO {
    /**
     * 统计时间
     */
    @ApiModelProperty(value = "统计时间")
    private String reportDateStr;
    /**
     * 商户ID
     */
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    /**
     * 商户名称
     */
    @ApiModelProperty(value = "统计时间")
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
     * 优惠类型
     */
    @ApiModelProperty(value = "优惠类型 21  整单折扣（FULL_DIS），22  整单满减（FULL_CUT），31  单品折扣 （SINGLE_DIS），32  分类折扣 （CATEGORY_DIS），33 分时优惠  42  单品砍价  （SINGLE_BARGAIN），43  整单砍价（FULL_BARGAIN），51 会员  ，52 会员等级 ，61 抹零 ， 71  代金券（CASH_COUPON），81 股东会员等级优惠")
    private Integer benefitType;

    /**
     * 总优惠额
     */
    @ApiModelProperty(value = "总优惠额")
    private Long totalBenefitAmount;
    /**
     * 优惠笔数
     */
    @ApiModelProperty(value = "优惠笔数")
    private Integer totalBenefitNum;

    public String getReportDateStr() {
        return reportDateStr;
    }

    public void setReportDateStr(String reportDateStr) {
        this.reportDateStr = reportDateStr;
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

    public Integer getBenefitType() {
        return benefitType;
    }

    public void setBenefitType(Integer benefitType) {
        this.benefitType = benefitType;
    }

    public Long getTotalBenefitAmount() {
        return totalBenefitAmount;
    }

    public void setTotalBenefitAmount(Long totalBenefitAmount) {
        this.totalBenefitAmount = totalBenefitAmount;
    }

    public Integer getTotalBenefitNum() {
        return totalBenefitNum;
    }

    public void setTotalBenefitNum(Integer totalBenefitNum) {
        this.totalBenefitNum = totalBenefitNum;
    }
}
