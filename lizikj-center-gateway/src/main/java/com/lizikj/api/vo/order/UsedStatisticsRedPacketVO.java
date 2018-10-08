package com.lizikj.api.vo.order;

import com.lizikj.marketing.api.enums.SupportCouponTimeTypeEnum;
import com.lizikj.order.enums.OrderSourceEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author zhoufe
 * @date 2018/8/28 15:09
 */
@ApiModel
public class UsedStatisticsRedPacketVO implements Serializable {
    //日期
    @ApiModelProperty(value = "日期")
    private String createTimeFormat;
    @ApiModelProperty(value = "开始时间")
    private Date startCreateTime;
    @ApiModelProperty(value = "结束时间")
    private Date endCreateTime;
    //订单号
    @ApiModelProperty(value = "订单号")
    private String orderNo;
    //忙时/闲时
    @ApiModelProperty(value = "忙时/闲时")
    private SupportCouponTimeTypeEnum idleStatus;
    //商户名称
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    //商户ID
    @ApiModelProperty(value = "商户ID")
    private List<Long> merchantIds;
    //门店名称
    @ApiModelProperty(value = "门店名称")
    private String shopName;
    //店铺ID
    @ApiModelProperty(value = "店铺ID")
    private List<Long> shopIds;
    //微信openid
    @ApiModelProperty(value = "微信openid")
    private String openid;
    //平台会员手机号
    @ApiModelProperty(value = "平台会员手机号")
    private String mobile;
    //订单金额
    @ApiModelProperty(value = "订单金额")
    private Long totalAmount;
    //随机红包金额
    @ApiModelProperty(value = "优惠金额")
    private Long benefitAmount;
    //红包优惠比
    @ApiModelProperty(value = "红包优惠比")
    private Double benefitAmountRate;
    //时间
    @ApiModelProperty(value = "时间")
    private Date createTime;

    @ApiModelProperty(value = "当前页")
    private Integer pageNum;
    @ApiModelProperty(value = "页大小")
    private Integer pageSize;

    @ApiModelProperty(value = "订单来源")
    private OrderSourceEnum orderSource;

    @ApiModelProperty(value = "平台会员ID")
    private Long memberId;

    @ApiModelProperty(value = "分享场景：3是结账前，4是结账后")
    private Integer userSourceScene;

    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(Date startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public Date getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(Date endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public SupportCouponTimeTypeEnum getIdleStatus() {
        return idleStatus;
    }

    public void setIdleStatus(SupportCouponTimeTypeEnum idleStatus) {
        this.idleStatus = idleStatus;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public List<Long> getMerchantIds() {
        return merchantIds;
    }

    public void setMerchantIds(List<Long> merchantIds) {
        this.merchantIds = merchantIds;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<Long> getShopIds() {
        return shopIds;
    }

    public void setShopIds(List<Long> shopIds) {
        this.shopIds = shopIds;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getBenefitAmount() {
        return benefitAmount;
    }

    public void setBenefitAmount(Long benefitAmount) {
        this.benefitAmount = benefitAmount;
    }

    public Double getBenefitAmountRate() {
        return benefitAmountRate;
    }

    public void setBenefitAmountRate(Double benefitAmountRate) {
        this.benefitAmountRate = benefitAmountRate;
    }

    public String getCreateTimeFormat() {
        return createTimeFormat;
    }

    public void setCreateTimeFormat(String createTimeFormat) {
        this.createTimeFormat = createTimeFormat;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public OrderSourceEnum getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(OrderSourceEnum orderSource) {
        this.orderSource = orderSource;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getUserSourceScene() {
        return userSourceScene;
    }

    public void setUserSourceScene(Integer userSourceScene) {
        this.userSourceScene = userSourceScene;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
