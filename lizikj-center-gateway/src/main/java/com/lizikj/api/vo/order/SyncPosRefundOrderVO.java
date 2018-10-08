package com.lizikj.api.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by zhoufe on 2017-50-26 09:50:22
 */
@ApiModel
public class SyncPosRefundOrderVO implements Serializable{

    private static final long serialVersionUID = -4996242432972580376L;
    /**
     * 退款id
     */
    @ApiModelProperty(value = "退款ID", required = true)
    private Long refundId;

    /**
     * 内部退款编号
     */
    @ApiModelProperty(value = "内部退款编号", required = true)
    private String innerRefundNo;

    /**
     * 外部退款编号
     */
    @ApiModelProperty(value = "外部退款编号", required = true)
    private String outRefundNo;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号", required = true)
    private String orderNo;

    /**
     * 内部交易号
     */
    @ApiModelProperty(value = "内部交易号", required = true)
    private String innerTradeNo;

    /**
     * 商户id
     */
    @ApiModelProperty(value = "商户ID", required = true)
    private Long merchantId;

    /**
     * 商户名称(冗余)
     */
    @ApiModelProperty(value = "商户名称(冗余)", required = true)
    private String merchantName;

    /**
     * 店铺id
     */
    @ApiModelProperty(value = "店铺ID", required = true)
    private Long shopId;

    /**
     * 店铺名称(冗余)
     */
    @ApiModelProperty(value = "店铺名称(冗余)", required = true)
    private String shopName;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户ID", required = true)
    private Long uid;

    /**
     * 李子会员id
     */
    @ApiModelProperty(value = "李子会员ID", required = true)
    private Long memberId;

    /**
     * 收银员ID
     */
    @ApiModelProperty(value = "收银员ID", required = true)
    private Long cashierId;

    /**
     * 商户会员id
     */
    @ApiModelProperty(value = "商户会员ID", required = true)
    private Long merchantMemberId;

    /**
     * 退款金额
     */
    @ApiModelProperty(value = "退款金额", required = true)
    private Long refundAmount;

    /**
     * 退款方式，0=原路退回，1=现金退款
     */
    @ApiModelProperty(value = "退款方式，0=原路退回，1=现金退款", required = true)
    private Byte refundType;


    /**
     * 退款金额方式：1.全额，2部分。
     */
    @ApiModelProperty(value = "退款金额方式：1.全额，2部分。", required = true)
    private Byte refundAmountType;

    /**
     * 支付通道code: 1=钱宝,2=通联3=兴业,4=银盛,5=群迈,6=李子
     */
    @ApiModelProperty(value = "支付通道code: 1=钱宝,2=通联3=兴业,4=银盛,5=群迈,6=李子", required = true)
    private Byte channelCode;

    /**
     * 支付方式code: 1=支付宝,2=微信,3=银联钱包,4=刷卡,5=现金
     */
    @ApiModelProperty(value = "支付方式code: 1=支付宝,2=微信,3=银联钱包,4=刷卡,5=现金", required = true)
    private Byte paymentTypeCode;

    /**
     * 支付场景code: 1=收银台付款码,2=店铺收款码,3=订单小票支付二维码,4=pos扫用户,5=h5点餐在线支付,6=公众号点餐在线支付,7=刷卡
     */
    @ApiModelProperty(value = "支付场景code: 0=默认,1=收银台付款码,2=店铺收款码,3=订单小票支付二维码,4=pos扫用户,5=h5点餐在线支付,6=公众号点餐在线支付,7=刷卡", required = true)
    private Byte sceneCode;

    /**
     * 退款方(如果是商家发起，则不用走退款审核流程)，1=商家发起，2=用户发起
     */
    @ApiModelProperty(value = "退款方(如果是商家发起，则不用走退款审核流程)，1=商家发起，2=用户发起", required = true)
    private Byte refundSource;

    /**
     * 退款状态, 1=退款申请，2=退款成功，3=退款失败
     */
    @ApiModelProperty(value = "退款状态, 1=退款申请，2=退款成功，3=退款失败", required = true)
    private Byte status;

    /**
     * 退款原因
     */
    @ApiModelProperty(value = "退款原因", required = true)
    private String remark;

    /**
     * 疑问订单备注
     */
    @ApiModelProperty(value = "疑问订单备注", required = true)
    private String questionOrderRemark;


    /**
     * 订单类型
     */
    @ApiModelProperty(value = "1.美食订单，2.快捷收银订单，3.会员充值，4.增值服务", required = true)
    private Byte orderBizType;

    /**
     * 退款详情
     */
    //@ApiModelProperty(value = "退款详情", required = true)
    //private List<RefundOrderDetailVO> refundOrderDetails;

    /**
     * 退款原因
     */
    @ApiModelProperty(value = "退款原因", required = true)
    private OrderRefundReasonInfoVO orderRefundReasonInfo;

    /**
     * 退款物品
     */
    @ApiModelProperty(value = "退款物品", required = true)
    private List<RefundOrderItemVO> refundOrderItemList;

    /**
     * 退款id
     */
    public Long getRefundId() {
        return refundId;
    }

    /**
     * 退款id
     * @param refundId the value for refund_order.refund_id
     */
    public void setRefundId(Long refundId) {
        this.refundId = refundId;
    }

    /**
     * 内部退款编号
     */
    public String getInnerRefundNo() {
        return innerRefundNo;
    }

    /**
     * 内部退款编号
     * @param innerRefundNo the value for refund_order.inner_refund_no
     */
    public void setInnerRefundNo(String innerRefundNo) {
        this.innerRefundNo = innerRefundNo;
    }

    /**
     * 外部退款编号
     */
    public String getOutRefundNo() {
        return outRefundNo;
    }

    /**
     * 外部退款编号
     * @param outRefundNo the value for refund_order.out_refund_no
     */
    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    /**
     * 订单编号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 订单编号
     * @param orderNo the value for refund_order.order_no
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
     * @param innerTradeNo the value for refund_order.inner_trade_no
     */
    public void setInnerTradeNo(String innerTradeNo) {
        this.innerTradeNo = innerTradeNo;
    }

    /**
     * 商户id
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * 商户id
     * @param merchantId the value for refund_order.merchant_id
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 商户名称(冗余)
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * 商户名称(冗余)
     * @param merchantName the value for refund_order.merchant_name
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    /**
     * 店铺id
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 店铺id
     * @param shopId the value for refund_order.shop_id
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 店铺名称(冗余)
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * 店铺名称(冗余)
     * @param shopName the value for refund_order.shop_name
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /**
     * 用户id
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 用户id
     * @param uid the value for refund_order.uid
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * 李子会员id
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * 李子会员id
     * @param memberId the value for refund_order.member_id
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * 商户会员id
     */
    public Long getMerchantMemberId() {
        return merchantMemberId;
    }

    /**
     * 商户会员id
     * @param merchantMemberId the value for refund_order.merchant_member_id
     */
    public void setMerchantMemberId(Long merchantMemberId) {
        this.merchantMemberId = merchantMemberId;
    }

    /**
     * 退款金额
     */
    public Long getRefundAmount() {
        return refundAmount;
    }

    /**
     * 退款金额
     * @param refundAmount the value for refund_order.refund_amount
     */
    public void setRefundAmount(Long refundAmount) {
        this.refundAmount = refundAmount;
    }

    /**
     * 退款方式，0=原路退回，1=现金退款
     */
    public Byte getRefundType() {
        return refundType;
    }

    /**
     * 退款方式，0=原路退回，1=现金退款
     * @param refundType the value for refund_order.refund_type
     */
    public void setRefundType(Byte refundType) {
        this.refundType = refundType;
    }

    /**
     * 支付通道code: 1=钱宝,2=通联3=兴业,4=银盛,5=群迈,6=李子
     */
    public Byte getChannelCode() {
        return channelCode;
    }

    /**
     * 支付通道code: 1=钱宝,2=通联3=兴业,4=银盛,5=群迈,6=李子
     * @param channelCode the value for refund_order.channel_code
     */
    public void setChannelCode(Byte channelCode) {
        this.channelCode = channelCode;
    }

    /**
     * 支付方式code: 1=支付宝,2=微信,3=银联钱包,4=刷卡,5=现金
     */
    public Byte getPaymentTypeCode() {
        return paymentTypeCode;
    }

    /**
     * 支付方式code: 1=支付宝,2=微信,3=银联钱包,4=刷卡,5=现金
     * @param paymentTypeCode the value for refund_order.payment_type_code
     */
    public void setPaymentTypeCode(Byte paymentTypeCode) {
        this.paymentTypeCode = paymentTypeCode;
    }

    /**
     * 支付场景code: 1=收银台付款码,2=店铺收款码,3=订单小票支付二维码,4=pos扫用户,5=h5点餐在线支付,6=公众号点餐在线支付,7=刷卡
     */
    public Byte getSceneCode() {
        return sceneCode;
    }

    /**
     * 支付场景code: 1=收银台付款码,2=店铺收款码,3=订单小票支付二维码,4=pos扫用户,5=h5点餐在线支付,6=公众号点餐在线支付,7=刷卡
     * @param sceneCode the value for refund_order.scene_code
     */
    public void setSceneCode(Byte sceneCode) {
        this.sceneCode = sceneCode;
    }

    /**
     * 退款方(如果是商家发起，则不用走退款审核流程)，1=商家发起，2=用户发起
     */
    public Byte getRefundSource() {
        return refundSource;
    }

    /**
     * 退款方(如果是商家发起，则不用走退款审核流程)，1=商家发起，2=用户发起
     * @param refundSource the value for refund_order.refund_source
     */
    public void setRefundSource(Byte refundSource) {
        this.refundSource = refundSource;
    }

    /**
     * 退款状态, 1=退款申请，2=退款成功，3=退款失败
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 退款状态, 1=退款申请，2=退款成功，3=退款失败
     * @param status the value for refund_order.status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 退款原因
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 退款原因
     * @param remark the value for refund_order.remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getQuestionOrderRemark() {
        return questionOrderRemark;
    }

    public void setQuestionOrderRemark(String questionOrderRemark) {
        this.questionOrderRemark = questionOrderRemark;
    }

    public OrderRefundReasonInfoVO getOrderRefundReasonInfo() {
        return orderRefundReasonInfo;
    }

    public void setOrderRefundReasonInfo(OrderRefundReasonInfoVO orderRefundReasonInfo) {
        this.orderRefundReasonInfo = orderRefundReasonInfo;
    }

    public Long getCashierId() {
        return cashierId;
    }

    public void setCashierId(Long cashierId) {
        this.cashierId = cashierId;
    }

    public Byte getRefundAmountType() {
        return refundAmountType;
    }

    public void setRefundAmountType(Byte refundAmountType) {
        this.refundAmountType = refundAmountType;
    }

    public Byte getOrderBizType() {
        return orderBizType;
    }

    public void setOrderBizType(Byte orderBizType) {
        this.orderBizType = orderBizType;
    }

    public List<RefundOrderItemVO> getRefundOrderItemList() {
        return refundOrderItemList;
    }

    public void setRefundOrderItemList(List<RefundOrderItemVO> refundOrderItemList) {
        this.refundOrderItemList = refundOrderItemList;
    }
}