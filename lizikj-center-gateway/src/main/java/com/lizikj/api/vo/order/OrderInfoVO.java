package com.lizikj.api.vo.order;

import java.util.Date;
import java.util.List;

import com.lizikj.api.vo.order.base.OrderInfoBaseVO;
import com.lizikj.api.vo.order.detail.OrderBuyerInfoVO;
import com.lizikj.api.vo.order.detail.OrderDeskInfoVO;
import com.lizikj.api.vo.order.detail.OrderPayInfoVO;
import com.lizikj.api.vo.order.detail.OrderPersonInfoVO;
import com.lizikj.api.vo.order.detail.OrderPosInfoVO;
import com.lizikj.api.vo.order.detail.OrderShopInfoVO;

import com.lizikj.payment.pay.enums.CirculationTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 订单详情
 *
 * @author zhoufe
 * @date 2017/8/10 18:42
 */
@ApiModel
public class OrderInfoVO extends OrderInfoBaseVO {
	@ApiModelProperty(value = "订单物品")
    private List<OrderItemVO> orderItems;
	
	@ApiModelProperty(value = "优惠信息")
    private List<OrderDiscountVO> orderDiscounts;
	
	@ApiModelProperty(value = "删除物品(转换到OrderInfo模型时有意义)")
    private List<Long> removeOrderItemIds;

	@ApiModelProperty(value = "是否是呼叫服务(入参转换有效)")
    private Boolean wait4Serving;

	@ApiModelProperty(value = "餐桌信息")
    private OrderDeskInfoVO deskInfo;

	@ApiModelProperty(value = "下单人员信息")
    private OrderPersonInfoVO personInfo;

	@ApiModelProperty(value = "买家信息")
    private OrderBuyerInfoVO buyerInfo;

	@ApiModelProperty(value = "店铺信息")
    private OrderShopInfoVO shopInfo;

	@ApiModelProperty(value = "POS信息")
    private OrderPosInfoVO posInfo;

	@ApiModelProperty(value = "支付信息")
    private OrderPayInfoVO payInfo;

	@ApiModelProperty(value = "退款信息")
    private List<OrderRefundReasonInfoVO> refundReasonInfos;

	@ApiModelProperty(value = "退款流水")
	private List<RefundOrderVO> refundOrders;

	@ApiModelProperty(value = "取消订单详情")
	private OrderCancelReasonInfoVO cancelReasonInfo;
    
    @ApiModelProperty(value = "订单取消时间，只有已取消的订单才有")
    private Date cancelTime;

	@ApiModelProperty(value = "撩美味平台金额：补贴金额，进账金额")
	private Long circulationAmount;

    @ApiModelProperty(value = "撩美味平台金额类型：SEPARATE 分账，SUBSIDY 补贴。")
    private CirculationTypeEnum circulationType;

	@ApiModelProperty(value = "撩美味平台补贴或者进账金额：忙时：实际进账金额=平台补贴金额+实收金额；闲时：分账金额")
	private Long subsidyOrIncomeAmount;

	@ApiModelProperty(value = "实际撩美味平台退款返账金额")
	private Long lmwRestoreAmount;

	@ApiModelProperty(value = "限时忙时状态描述：闲时显示：（美食闲时时段撩美味平台合约协议拿货折扣7折）")
	private String idleStatusDescr;

	public List<OrderItemVO> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemVO> orderItems) {
		this.orderItems = orderItems;
	}

	public List<OrderDiscountVO> getOrderDiscounts() {
		return orderDiscounts;
	}

	public void setOrderDiscounts(List<OrderDiscountVO> orderDiscounts) {
		this.orderDiscounts = orderDiscounts;
	}

	public List<Long> getRemoveOrderItemIds() {
		return removeOrderItemIds;
	}

	public void setRemoveOrderItemIds(List<Long> removeOrderItemIds) {
		this.removeOrderItemIds = removeOrderItemIds;
	}

	public Boolean getWait4Serving() {
		return wait4Serving;
	}

	public void setWait4Serving(Boolean wait4Serving) {
		this.wait4Serving = wait4Serving;
	}

	public OrderDeskInfoVO getDeskInfo() {
		return deskInfo;
	}

	public void setDeskInfo(OrderDeskInfoVO deskInfo) {
		this.deskInfo = deskInfo;
	}

	public OrderPersonInfoVO getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(OrderPersonInfoVO personInfo) {
		this.personInfo = personInfo;
	}

	public OrderBuyerInfoVO getBuyerInfo() {
		return buyerInfo;
	}

	public void setBuyerInfo(OrderBuyerInfoVO buyerInfo) {
		this.buyerInfo = buyerInfo;
	}

	public OrderShopInfoVO getShopInfo() {
		return shopInfo;
	}

	public void setShopInfo(OrderShopInfoVO shopInfo) {
		this.shopInfo = shopInfo;
	}

	public OrderPosInfoVO getPosInfo() {
		return posInfo;
	}

	public void setPosInfo(OrderPosInfoVO posInfo) {
		this.posInfo = posInfo;
	}

	public OrderPayInfoVO getPayInfo() {
		return payInfo;
	}

	public void setPayInfo(OrderPayInfoVO payInfo) {
		this.payInfo = payInfo;
	}

	public List<OrderRefundReasonInfoVO> getRefundReasonInfos() {
		return refundReasonInfos;
	}

	public void setRefundReasonInfos(List<OrderRefundReasonInfoVO> refundReasonInfos) {
		this.refundReasonInfos = refundReasonInfos;
	}

	public List<RefundOrderVO> getRefundOrders() {
		return refundOrders;
	}

	public void setRefundOrders(List<RefundOrderVO> refundOrders) {
		this.refundOrders = refundOrders;
	}

	public OrderCancelReasonInfoVO getCancelReasonInfo() {
		return cancelReasonInfo;
	}

	public void setCancelReasonInfo(OrderCancelReasonInfoVO cancelReasonInfo) {
		this.cancelReasonInfo = cancelReasonInfo;
	}

	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

    public Long getCirculationAmount() {
        return circulationAmount;
    }

    public void setCirculationAmount(Long circulationAmount) {
        this.circulationAmount = circulationAmount;
    }

    public CirculationTypeEnum getCirculationType() {
        return circulationType;
    }

    public void setCirculationType(CirculationTypeEnum circulationType) {
        this.circulationType = circulationType;
    }

	public Long getSubsidyOrIncomeAmount() {
		return subsidyOrIncomeAmount;
	}

	public void setSubsidyOrIncomeAmount(Long subsidyOrIncomeAmount) {
		this.subsidyOrIncomeAmount = subsidyOrIncomeAmount;
	}

	public String getIdleStatusDescr() {
		return idleStatusDescr;
	}

	public void setIdleStatusDescr(String idleStatusDescr) {
		this.idleStatusDescr = idleStatusDescr;
	}

	public Long getLmwRestoreAmount() {
		return lmwRestoreAmount;
	}

	public void setLmwRestoreAmount(Long lmwRestoreAmount) {
		this.lmwRestoreAmount = lmwRestoreAmount;
	}
}
