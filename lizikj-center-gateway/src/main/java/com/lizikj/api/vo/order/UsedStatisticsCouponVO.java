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
 * @date 2018/8/29 14:49
 */
@ApiModel
public class UsedStatisticsCouponVO implements Serializable {

    @ApiModelProperty(value = "订单ID")
    private Long orderId;
    //商户名称
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    @ApiModelProperty(value = "商户IDS")
    private List<Long> merchantIds;
    //门店名称
    @ApiModelProperty(value = "门店名称")
    private String shopName;
    @ApiModelProperty(value = "门店ID")
    private Long shopId;
    @ApiModelProperty(value = "门店IDS")
    private List<Long> shopIds;
    //订单号
    @ApiModelProperty(value = "订单号")
    private String orderNo;
    //核销时间
    @ApiModelProperty(value = "核销时间")
    private Date createTime;
    @ApiModelProperty(value = "查询开始时间")
    private Date startCreateTime;
    @ApiModelProperty(value = "查询结束时间")
    private Date endCreateTime;
    //优惠券CODE(ID)
    @ApiModelProperty(value = "优惠券CODE(ID)")
    private String couponCode;
    //优惠券名称
    @ApiModelProperty(value = "优惠券名称")
    private String couponName;
    //微信openid
    @ApiModelProperty(value = "微信openid")
    private String openid;
    //会员手机号
    @ApiModelProperty(value = "会员手机号")
    private String mobile;
    @ApiModelProperty(value = "页码")
    private Integer pageNum;
    @ApiModelProperty(value = "页大小")
    private Integer pageSize;

    @ApiModelProperty(value = "订单来源")
    private OrderSourceEnum orderSource;
    @ApiModelProperty(value = "订单空闲状态")
    private SupportCouponTimeTypeEnum idleStatus;


    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public List<Long> getShopIds() {
        return shopIds;
    }

    public void setShopIds(List<Long> shopIds) {
        this.shopIds = shopIds;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

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

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
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

    public SupportCouponTimeTypeEnum getIdleStatus() {
        return idleStatus;
    }

    public void setIdleStatus(SupportCouponTimeTypeEnum idleStatus) {
        this.idleStatus = idleStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
