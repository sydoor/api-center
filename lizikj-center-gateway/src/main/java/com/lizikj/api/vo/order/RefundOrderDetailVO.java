package com.lizikj.api.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Model for table -- refund_order_detail
 * created by zhoufe 17:25:08
 */
@ApiModel
public class RefundOrderDetailVO implements Serializable{

	private static final long serialVersionUID = 127203729559269241L;
	/**
	 * 退款订单详情id
	 */
	@ApiModelProperty(value = "退款订单详情ID", required = true)
	private Long refundDetailId;

	/**
	 * 退款id
	 */
	@ApiModelProperty(value = "退款ID", required = true)
	private Long refundId;

	/**
	 * 订单编号
	 */
	@ApiModelProperty(value = "订单号", required = true)
	private String orderNo;

	/**
	 * 内部交易号
	 */
	@ApiModelProperty(value = "内部交易号", required = true)
	private String innerTradeNo;

	/**
	 * 订单业务类型
	 */
	@ApiModelProperty(value = "订单业务类型", required = true)
	private Byte orderBizType;

	/**
	 * 商品id
	 */
	@ApiModelProperty(value = "商品ID", required = true)
	private String goodsId;

	/**
	 * 商品名称(冗余)
	 */
	@ApiModelProperty(value = "商品名称")
	private String goodsName;

	/**
	 * 商品单价(分)
	 */
	@ApiModelProperty(value = "商品单价", required = true)
	private Long goodsAmount;

	/**
	 * 商品数量
	 */
	@ApiModelProperty(value = "商品数量", required = true)
	private Long goodsNum;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "POS创建时间", required = true)
	private Date createTime;

	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "POS更新时间", required = true)
	private Date updateTime;

	/**
	 * 退款订单详情id
	 */
	public Long getRefundDetailId() {
		return refundDetailId;
	}

	/**
	 * 退款订单详情id
	 * 
	 * @param refundDetailId
	 *            the value for refund_order_detail.refund_detail_id
	 */
	public void setRefundDetailId(Long refundDetailId) {
		this.refundDetailId = refundDetailId;
	}

	/**
	 * 退款id
	 */
	public Long getRefundId() {
		return refundId;
	}

	/**
	 * 退款id
	 * 
	 * @param refundId
	 *            the value for refund_order_detail.refund_id
	 */
	public void setRefundId(Long refundId) {
		this.refundId = refundId;
	}

	/**
	 * 订单编号
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * 订单编号
	 * 
	 * @param orderNo
	 *            the value for refund_order_detail.order_no
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * 内部交易号
	 */
	public String getInnerTradeNo() {
		return innerTradeNo;
	}

	/**
	 * 内部交易号
	 * 
	 * @param innerTradeNo
	 *            the value for refund_order_detail.inner_trade_no
	 */
	public void setInnerTradeNo(String innerTradeNo) {
		this.innerTradeNo = innerTradeNo;
	}

	public Byte getOrderBizType() {
		return orderBizType;
	}

	public void setOrderBizType(Byte orderBizType) {
		this.orderBizType = orderBizType;
	}

	/**
	 * 商品id
	 */
	public String getGoodsId() {
		return goodsId;
	}

	/**
	 * 商品id
	 * 
	 * @param goodsId
	 *            the value for refund_order_detail.goods_id
	 */
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	/**
	 * 商品名称(冗余)
	 */
	public String getGoodsName() {
		return goodsName;
	}

	/**
	 * 商品名称(冗余)
	 * 
	 * @param goodsName
	 *            the value for refund_order_detail.goods_name
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**
	 * 商品单价(分)
	 */
	public Long getGoodsAmount() {
		return goodsAmount;
	}

	/**
	 * 商品单价(分)
	 * 
	 * @param goodsAmount
	 *            the value for refund_order_detail.goods_amount
	 */
	public void setGoodsAmount(Long goodsAmount) {
		this.goodsAmount = goodsAmount;
	}

	/**
	 * 商品数量
	 */
	public Long getGoodsNum() {
		return goodsNum;
	}

	/**
	 * 商品数量
	 * 
	 * @param goodsNum
	 *            the value for refund_order_detail.goods_num
	 */
	public void setGoodsNum(Long goodsNum) {
		this.goodsNum = goodsNum;
	}

	/**
	 * 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * 
	 * @param createTime
	 *            the value for refund_order_detail.create_time
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 * 
	 * @param updateTime
	 *            the value for refund_order_detail.update_time
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}