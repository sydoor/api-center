package com.lizikj.api.vo.order.param;

import java.util.List;

import com.lizikj.api.vo.order.OrderDiscountVO;
import com.lizikj.order.enums.DiscountTypeNodeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 结账的参数
 *
 * @author zone
 * @date 2017/9/18
 */
@ApiModel
public class OrderPayParamVO {

	@ApiModelProperty(value = "融合支付信息内容")
	private List<PayParamVO> payParamList;

	@ApiModelProperty(value = "订单优惠信息(只需考虑抹零优惠，会员以及折扣等优惠内容不需要传递)")
	private List<OrderDiscountVO> orderDiscountList;

	@ApiModelProperty(value = "订单编号,快捷收银时，无需传递")
	private String orderNo;

	@ApiModelProperty(value = "前台传入订单应付总金额 界面上看到的金额和服务端实际计算的金额有出入时，提醒界面，重新刷新计价 再完成支付")
	private Long totalAmount;

	/**
	 * 商户会员ID,pos支付时才传递，H5请忽略
	 */
	@ApiModelProperty(value = "商户会员ID,pos支付时才传递，H5请忽略")
	private Long merchantMemberId;

	/**
	 * 股东会员ID,pos支付时才传递，H5请忽略
	 */
	@ApiModelProperty(value = "股东会员ID,pos支付时才传递，H5请忽略")
	private Long partnerMemberId;

	@ApiModelProperty(value = "前端选中的优惠方式:DiscountTypeNodeEnum。")
	private DiscountTypeNodeEnum discountSelected;

	/**
	 * 用来检验优惠活动修改，过期等，导致前端传入的结账金额与后端计价金额不相等的校验。已经选择的优惠方式对应的各个优惠金额Json数组,示例：[{"discountSelected":"ZERO","benefitAmount":110}]
	 */
	@ApiModelProperty(value = "用来检验优惠活动修改，过期等，导致前端传入的结账金额与后端计价金额不相等的校验。已经选择的优惠方式对应的各个优惠金额Json数组,示例：[{\"discountSelected\":\"ZERO\",\"benefitAmount\":110}]")
	private String dsAmountJsonArray;

	/**
	 * 现金支付是接收到的金额
	 */
	@ApiModelProperty(value = "现金支付是接收到的金额")
	private Long receiveCashAmount;

	/**
	 * 现金支付是接收到的金额的找零
	 */
	@ApiModelProperty(value = "现金支付是接收到的金额的找零")
	private Long cashChangeAmount;

	public List<PayParamVO> getPayParamList() {
		return payParamList;
	}

	public void setPayParamList(List<PayParamVO> payParamList) {
		this.payParamList = payParamList;
	}

	public List<OrderDiscountVO> getOrderDiscountList() {
		return orderDiscountList;
	}

	public void setOrderDiscountList(List<OrderDiscountVO> orderDiscountList) {
		this.orderDiscountList = orderDiscountList;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
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

	public DiscountTypeNodeEnum getDiscountSelected() {
		return discountSelected;
	}

	public void setDiscountSelected(DiscountTypeNodeEnum discountSelected) {
		this.discountSelected = discountSelected;
	}

	public String getDsAmountJsonArray() {
		return dsAmountJsonArray;
	}

	public void setDsAmountJsonArray(String dsAmountJsonArray) {
		this.dsAmountJsonArray = dsAmountJsonArray;
	}

	public Long getReceiveCashAmount() {
		return receiveCashAmount;
	}

	public void setReceiveCashAmount(Long receiveCashAmount) {
		this.receiveCashAmount = receiveCashAmount;
	}

	public Long getCashChangeAmount() {
		return cashChangeAmount;
	}

	public void setCashChangeAmount(Long cashChangeAmount) {
		this.cashChangeAmount = cashChangeAmount;
	}
}
