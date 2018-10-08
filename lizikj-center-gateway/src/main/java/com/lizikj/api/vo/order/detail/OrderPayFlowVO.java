package com.lizikj.api.vo.order.detail;

import com.lizikj.api.vo.pay.PayFlowAccountDetailVO;
import com.lizikj.common.enums.PaymentTypeEnum;
import com.lizikj.order.enums.OrderBizTypeEnum;
import com.lizikj.payment.pay.enums.PayFlowTypeEnum;
import com.lizikj.payment.pay.enums.PayStatusEnum;
import com.lizikj.payment.pay.enums.RefundEnableEnum;
import com.lizikj.payment.refund.enums.RefundStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author Michael.Huang
 * @date 2017/8/26 16:55
 */
@ApiModel
public class OrderPayFlowVO {
    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
    private String orderNo;

    /**
     * 内部交易号
     */
    @ApiModelProperty(value = "内部交易号")
    private String innerTradeNo;

    /**
     * 外部交易号
     */
    @ApiModelProperty(value = "外部交易号")
    private String outTradeNo;

    /**
     * 支付流水类型  1：正常支付 2：POS支付回调  默认1
     * </br>{@link com.lizikj.payment.pay.enums.PayFlowTypeEnum}
     */
    @ApiModelProperty(value = "支付流水类型：默认:ORDER_CAL,见PayFlowTypeEnum：ORDER_CALL.正常下单的流水,POS_CALL.pos通知支付系统的流水。")
    private PayFlowTypeEnum payFlowType;

    /**
     * 支付通道代码
     */
    @ApiModelProperty(value = "支付通道代码")
    private Byte channelCode;

    /**
     * 支付方式代码
     */
    @ApiModelProperty(value = "支付方式代码：见：PaymentTypeEnum: PAYMENT_TYPE_ALIPAY.支付宝,PAYMENT_TYPE_WECHAT.微信,PAYMENT_TYPE_YINLIANQIANBAO.银联钱包,PAYMENT_TYPE_SWIPECARD.刷卡,PAYMENT_TYPE_CASH.现金,PAYMENT_TYPE_CLOUD.云闪付,PAYMENT_TYPE_QRCODE.付款码,PAYMENT_TYPE_MEMBER.会员,PAYMENT_TYPE_COUPON.优惠券,PAYMENT_TYPE_MEI_TUAN_COUPON.美团券,PAYMENT_TYPE_APP_PAY.APP支付。")
    private PaymentTypeEnum paymentTypeCode;

    /**
     * 支付场景代码
     */
    @ApiModelProperty(value = "支付场景代码")
    private Byte sceneCode;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private Long amount;

    /**
     * 支付状态  0：未支付 1：支付成功 2：支付失败  默认0
     * </br>{@link com.lizikj.payment.pay.enums.PayStatusEnum}
     */
    @ApiModelProperty(value = "支付状态：默认UN_PAY，见PayStatusEnum： UN_PAY.未支付,PAYING.支付进行中,PAY_SUCCESS.支付成功,PAY_FAIL.支付失败。")
    private PayStatusEnum payStatus;

    /**
     * 是否能退款  0：否 1：是  默认1
     * </br>{@link com.lizikj.payment.pay.enums.RefundEnableEnum}
     */
    @ApiModelProperty(value = "是否能退款：默认CAN_REFUND，见RefundEnableEnum：NOT_CAN_REFUND.不能退款,CAN_REFUND.能退款。")
    private RefundEnableEnum refundEnable;

    /**
     * 退款截止时间
     */
    @ApiModelProperty(value = "退款截止时间")
    private Date refundEndTime;

    /**
     * 退款状态  0：未退款 1：退款成功 2：退款失败  默认0
     * </br>{@link com.lizikj.payment.refund.enums.RefundStatusEnum}
     */
    @ApiModelProperty(value = "退款状态：见：RefundStatusEnum：NORMAL.默认状态,APPLY.退款申请,SUCCESS.退款成功,FAIL.退款失败。")
    private RefundStatusEnum refundStatus;

    /**
     * 交易时间，第三方回调的时候插入
     */
    @ApiModelProperty(value = "交易时间，第三方回调的时候插入")
    private Date tradeTime;

    /**
     * 业务类型,1=点餐订单，2=快捷收银
     * </br>{@link com.lizikj.order.enums.OrderBizTypeEnum}
     */
    @ApiModelProperty(value = "业务类型：见：OrderBizTypeEnum：MERCHANDISE=美食订单,MONEY=快捷收银,MEMBER_RECHARGE=会员充值,VALUE_ADD=增值服务")
    private OrderBizTypeEnum bizType;

    /**
     * 账户详情
     */
    @ApiModelProperty(value = "账户详情")
    private PayFlowAccountDetailVO accountDetail;

    /**
     * 支付版本
     */
    private Long payVersion;

    /**
     * 疑问流水的备注
     */
    @ApiModelProperty(value = "疑问流水的备注：存储json")
    private String questionOrderRemark;

    /**
     * 流水备注
     */
    @ApiModelProperty(value = "流水备注")
    private String flowMemo;
    
    /**
     * 流水业务参数
     */
    @ApiModelProperty(value = "流水业务参数，视具体情况而定,外部收款JSON:参考ShopExternalPaymentMethodVO")
    private String bizData;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getInnerTradeNo() {
        return innerTradeNo;
    }

    public void setInnerTradeNo(String innerTradeNo) {
        this.innerTradeNo = innerTradeNo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Byte getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(Byte channelCode) {
        this.channelCode = channelCode;
    }

    public Byte getSceneCode() {
        return sceneCode;
    }

    public void setSceneCode(Byte sceneCode) {
        this.sceneCode = sceneCode;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Date getRefundEndTime() {
        return refundEndTime;
    }

    public void setRefundEndTime(Date refundEndTime) {
        this.refundEndTime = refundEndTime;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public PayFlowAccountDetailVO getAccountDetail() {
        return accountDetail;
    }

    public void setAccountDetail(PayFlowAccountDetailVO accountDetail) {
        this.accountDetail = accountDetail;
    }

    public PayFlowTypeEnum getPayFlowType() {
        return payFlowType;
    }

    public void setPayFlowType(PayFlowTypeEnum payFlowType) {
        this.payFlowType = payFlowType;
    }

    public PaymentTypeEnum getPaymentTypeCode() {
        return paymentTypeCode;
    }

    public void setPaymentTypeCode(PaymentTypeEnum paymentTypeCode) {
        this.paymentTypeCode = paymentTypeCode;
    }

    public PayStatusEnum getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(PayStatusEnum payStatus) {
        this.payStatus = payStatus;
    }

    public RefundEnableEnum getRefundEnable() {
        return refundEnable;
    }

    public void setRefundEnable(RefundEnableEnum refundEnable) {
        this.refundEnable = refundEnable;
    }

    public RefundStatusEnum getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(RefundStatusEnum refundStatus) {
        this.refundStatus = refundStatus;
    }

    public OrderBizTypeEnum getBizType() {
        return bizType;
    }

    public void setBizType(OrderBizTypeEnum bizType) {
        this.bizType = bizType;
    }

    public Long getPayVersion() {
        return payVersion;
    }

    public void setPayVersion(Long payVersion) {
        this.payVersion = payVersion;
    }

    public String getQuestionOrderRemark() {
        return questionOrderRemark;
    }

    public void setQuestionOrderRemark(String questionOrderRemark) {
        this.questionOrderRemark = questionOrderRemark;
    }

	public String getFlowMemo() {
		return flowMemo;
	}

	public void setFlowMemo(String flowMemo) {
		this.flowMemo = flowMemo;
	}

	public String getBizData() {
		return bizData;
	}

	public void setBizData(String bizData) {
		this.bizData = bizData;
	}
}
