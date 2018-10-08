package com.lizikj.api.vo.order;

import com.lizikj.api.vo.order.param.OrderInfoParamVO;
import com.lizikj.common.enums.RemoveStatusEnum;
import com.lizikj.order.enums.PendingOrderStatusEnum;
import com.lizikj.order.enums.PendingOrderTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 待下单信息
 *
 * @author zhoufe
 * @date 2018/8/22 14:39
 */
@ApiModel
public class PendingOrderInfoVO implements Serializable {

    @ApiModelProperty(value = "待下单的ID")
    private Long pendingOrderInfoId;

    @ApiModelProperty(value = "商圈ID")
    private Long tradeAreaId;

    @ApiModelProperty(value = "商户ID")
    private Long merchantId;

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "订单ID：下单形成正式订单之后的订单ID")
    private Long orderId;

    @ApiModelProperty(value = "订单号：下单形成正式订单之后的订单号")
    private String orderNo;

    @ApiModelProperty(value = "C端用户ID")
    private Long userId;

    @ApiModelProperty(value = "C端用户会员ID")
    private Long memberId;

    @ApiModelProperty(value = "桌台ID：扫码识别之前是没有的")
    private Long areaDeskId;

    @ApiModelProperty(value = "识别桌台ID的二维码URL")
    private String areaDeskIdQrcodeUrl;

    @ApiModelProperty(value = "待下单状态 PendingOrderStatusEnum：PENDING 待下单，ORDER 已下单，CANCEL 取消。")
    private PendingOrderStatusEnum pendingOrderStatus;

    @ApiModelProperty(value = "待下单的订单的类型 PendingOrderTypeEnum：NONE 无，DELIVERY_BY_CLIENT 到店自取，DELIVERY_BY_MERCHANT 商户配送，FOR_HERE 到店堂食。")
    private PendingOrderTypeEnum pendingOrderType;

    @ApiModelProperty(value = "待下单的参数：该下单参数中没有桌台ID，需要扫码识别桌台ID")
    private OrderInfoParamVO pendingAddOrderParam;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "查询开始时间")
    private Date startCreateTime;

    @ApiModelProperty(value = "查询结束时间")
    private Date endCreateTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "根据下单参数返回的：菜品，价格等信息")
    private OrderInfoVO orderInfo;

    @ApiModelProperty(value = "删除状态")
    private RemoveStatusEnum removeStatus;

    @ApiModelProperty(value = "美食数量")
    private Integer goodsTotal;

    public Long getPendingOrderInfoId() {
        return pendingOrderInfoId;
    }

    public void setPendingOrderInfoId(Long pendingOrderInfoId) {
        this.pendingOrderInfoId = pendingOrderInfoId;
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

    public Long getAreaDeskId() {
        return areaDeskId;
    }

    public void setAreaDeskId(Long areaDeskId) {
        this.areaDeskId = areaDeskId;
    }

    public PendingOrderStatusEnum getPendingOrderStatus() {
        return pendingOrderStatus;
    }

    public void setPendingOrderStatus(PendingOrderStatusEnum pendingOrderStatus) {
        this.pendingOrderStatus = pendingOrderStatus;
    }

    public PendingOrderTypeEnum getPendingOrderType() {
        return pendingOrderType;
    }

    public void setPendingOrderType(PendingOrderTypeEnum pendingOrderType) {
        this.pendingOrderType = pendingOrderType;
    }

    public OrderInfoParamVO getPendingAddOrderParam() {
        return pendingAddOrderParam;
    }

    public void setPendingAddOrderParam(OrderInfoParamVO pendingAddOrderParam) {
        this.pendingAddOrderParam = pendingAddOrderParam;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public OrderInfoVO getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoVO orderInfo) {
        this.orderInfo = orderInfo;
    }

    public RemoveStatusEnum getRemoveStatus() {
        return removeStatus;
    }

    public void setRemoveStatus(RemoveStatusEnum removeStatus) {
        this.removeStatus = removeStatus;
    }

    public Long getTradeAreaId() {
        return tradeAreaId;
    }

    public void setTradeAreaId(Long tradeAreaId) {
        this.tradeAreaId = tradeAreaId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getGoodsTotal() {
        return goodsTotal;
    }

    public void setGoodsTotal(Integer goodsTotal) {
        this.goodsTotal = goodsTotal;
    }

    public String getAreaDeskIdQrcodeUrl() {
        return areaDeskIdQrcodeUrl;
    }

    public void setAreaDeskIdQrcodeUrl(String areaDeskIdQrcodeUrl) {
        this.areaDeskIdQrcodeUrl = areaDeskIdQrcodeUrl;
    }
}
