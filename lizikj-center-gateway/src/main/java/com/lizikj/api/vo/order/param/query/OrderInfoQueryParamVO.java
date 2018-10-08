package com.lizikj.api.vo.order.param.query;

import com.lizikj.marketing.api.enums.SupportCouponTimeTypeEnum;
import com.lizikj.order.enums.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 订单信息查询参数
 *
 * @author Michael.Huang
 * @date 2017/8/26 14:39
 */
@ApiModel
public class OrderInfoQueryParamVO {
	 @ApiParam(name = "currentPage", value = "currentPage", required = true)
	 private int currentPage = 1;
	 @ApiParam(name = "pageSize", value = "pageSize", required = true)
	 private int pageSize = 10;
	
    /**
     * 商户ID
     */
    @ApiModelProperty(value = "商户ID")
    private Long shopId;

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号：模糊查询")
    private String orderNo;

    /**
     * 商户ID
     */
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;

    /**
     * 李子会员ID
     */
    @ApiModelProperty(value = "李子会员ID")
    private Long memberId;

    /**
     * 商户会员ID
     */
    @ApiModelProperty(value = "商户会员ID")
    private Long merchantMemberId;

    /**
     * 会员手机号
     */
    @ApiModelProperty(value = "会员手机号")
    private String mobile;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**
     * snNum号
     */
    @ApiModelProperty(value = "snNum号")
    private String snNum;

    /**
     * 餐桌ID
     */
    @ApiModelProperty(value = "餐桌ID")
    private Long areaDeskId;


    /**
     * 餐桌名称
     */
    @ApiModelProperty(value = "餐桌名称")
    private String areaDeskName;


    //--------------------------------------------
    //  状态
    //--------------------------------------------

    /**
     * 订单来源
     */
    @ApiModelProperty(value = "订单来源")
    private OrderSourceEnum orderSource;

    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态")
    private OrderStatusEnum orderStatus;

    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态List")
    private List<OrderStatusEnum> orderStatusList;

    /**
     * 订单类型
     */
    @ApiModelProperty(value = "订单类型")
    private OrderTypeEnum orderType;


    /**
     * 订单业务类型
     */
    @ApiModelProperty(value = "订单业务类型：见OrderBizTypeEnum。")
    private List<OrderBizTypeEnum> orderBizTypes;

    //--------------------------------------------
    //  时间
    //--------------------------------------------


    /**
     * 接单开始时间
     */
    @ApiModelProperty(value = "接单开始时间")
    private Date startRecTime;

    /**
     * 接单结束时间
     */
    @ApiModelProperty(value = "接单结束时间")
    private Date endRecTime;

    /**
     * 下单开始时间：查询时间段
     */
    @ApiModelProperty(value = "查询时间段开始时间")
    private Date startOrderTime;

    /**
     * 下单结束时间：查询时间段
     */
    @ApiModelProperty(value = "查询时间段结束时间")
    private Date endOrderTime;

    /**
     * 订单创建开始时间
     */
    @ApiModelProperty(value = "订单创建开始时间")
    private Date startCreateTime;

    /**
     * 订单创建结束时间
     */
    @ApiModelProperty(value = "订单创建结束时间")
    private Date endCreateTime;

    /**
     * 订单更新开始时间
     */
    @ApiModelProperty(value = "订单更新开始时间")
    private Date startUpdateTime;

    /**
     * 订单更新结束时间
     */
    @ApiModelProperty(value = "订单更新结束时间")
    private Date endUpdateTime;

    /**
     * 排序数组
     */
    @ApiModelProperty(value = "排序字段：排序的字段属性如：orderStatus，排序方式：OrderByEnum：DESC，ASC")
    private Map<String, OrderByEnum> sortMaps;

    /**
     * 下单人ID
     */
    @ApiModelProperty(value = "下单人ID")
    private Long orderPersonId;

    /**
     * 收银员ID
     */
    @ApiModelProperty(value = "收银员ID：当传入这个参数的时候：查询时间段开始，查询时间段结束时间要求传入")
    private Long cashierId;

    /**
     * 美食ID
     */
    @ApiModelProperty(value = "美食ID：当传入这个参数的时候：查询时间段开始时间，查询时间段结束时间要求传入")
    private String goodsId;

    /**
     * 支付完成开始时间
     */
    @ApiModelProperty(value = "支付完成开始时间")
    private Date statPaySuccessTime;

    /**
     * 支付完成结束时间
     */
    @ApiModelProperty(value = "支付完成结束时间")
    private Date endPaySuccessTime;

    /**
     * 忙时闲时状态
     */
    @ApiModelProperty(value = "忙时闲时状态：传值为空就查询全部")
    private SupportCouponTimeTypeEnum idleStatus;


    public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMerchantMemberId() {
        return merchantMemberId;
    }

    public void setMerchantMemberId(Long merchantMemberId) {
        this.merchantMemberId = merchantMemberId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSnNum() {
        return snNum;
    }

    public void setSnNum(String snNum) {
        this.snNum = snNum;
    }

    public Long getAreaDeskId() {
        return areaDeskId;
    }

    public void setAreaDeskId(Long areaDeskId) {
        this.areaDeskId = areaDeskId;
    }

    public OrderSourceEnum getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(OrderSourceEnum orderSource) {
        this.orderSource = orderSource;
    }

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderTypeEnum getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderTypeEnum orderType) {
        this.orderType = orderType;
    }

    public Date getStartRecTime() {
        return startRecTime;
    }

    public void setStartRecTime(Date startRecTime) {
        this.startRecTime = startRecTime;
    }

    public Date getEndRecTime() {
        return endRecTime;
    }

    public void setEndRecTime(Date endRecTime) {
        this.endRecTime = endRecTime;
    }

    public Date getStartOrderTime() {
        return startOrderTime;
    }

    public void setStartOrderTime(Date startOrderTime) {
        this.startOrderTime = startOrderTime;
    }

    public Date getEndOrderTime() {
        return endOrderTime;
    }

    public void setEndOrderTime(Date endOrderTime) {
        this.endOrderTime = endOrderTime;
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

    public Date getStartUpdateTime() {
        return startUpdateTime;
    }

    public void setStartUpdateTime(Date startUpdateTime) {
        this.startUpdateTime = startUpdateTime;
    }

    public Date getEndUpdateTime() {
        return endUpdateTime;
    }

    public void setEndUpdateTime(Date endUpdateTime) {
        this.endUpdateTime = endUpdateTime;
    }

    public String getAreaDeskName() {
        return areaDeskName;
    }

    public void setAreaDeskName(String areaDeskName) {
        this.areaDeskName = areaDeskName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public List<OrderBizTypeEnum> getOrderBizTypes() {
        return orderBizTypes;
    }

    public void setOrderBizTypes(List<OrderBizTypeEnum> orderBizTypes) {
        this.orderBizTypes = orderBizTypes;
    }

    public Map<String, OrderByEnum> getSortMaps() {
        return sortMaps;
    }

    public void setSortMaps(Map<String, OrderByEnum> sortMaps) {
        this.sortMaps = sortMaps;
    }

    public Long getOrderPersonId() {
        return orderPersonId;
    }

    public void setOrderPersonId(Long orderPersonId) {
        this.orderPersonId = orderPersonId;
    }

    public Long getCashierId() {
        return cashierId;
    }

    public void setCashierId(Long cashierId) {
        this.cashierId = cashierId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Date getStatPaySuccessTime() {
        return statPaySuccessTime;
    }

    public void setStatPaySuccessTime(Date statPaySuccessTime) {
        this.statPaySuccessTime = statPaySuccessTime;
    }

    public Date getEndPaySuccessTime() {
        return endPaySuccessTime;
    }

    public void setEndPaySuccessTime(Date endPaySuccessTime) {
        this.endPaySuccessTime = endPaySuccessTime;
    }

    public SupportCouponTimeTypeEnum getIdleStatus() {
        return idleStatus;
    }

    public void setIdleStatus(SupportCouponTimeTypeEnum idleStatus) {
        this.idleStatus = idleStatus;
    }

    public List<OrderStatusEnum> getOrderStatusList() {
        return orderStatusList;
    }

    public void setOrderStatusList(List<OrderStatusEnum> orderStatusList) {
        this.orderStatusList = orderStatusList;
    }
}
