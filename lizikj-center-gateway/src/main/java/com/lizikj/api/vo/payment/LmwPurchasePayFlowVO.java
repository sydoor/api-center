package com.lizikj.api.vo.payment;

import com.lizikj.common.enums.PaymentTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhoufe
 * @date 2018/8/7 11:47
 */
@ApiModel
public class LmwPurchasePayFlowVO implements Serializable {

    //开通时间：2018.09.09 14:14:12
    @ApiModelProperty(value = "开通时间")
    private Date createTime;
    //交易流水号：123123123123123
    @ApiModelProperty(value = "交易流水号")
    private String innerTradeNo;
    //订单号：D123123123123
    @ApiModelProperty(value = "订单号")
    private String orderNo;
    //订单创建时间：2018.09.09 14:14:12
    @ApiModelProperty(value = "订单创建时间")
    private Date orderCreateTime;
    //订单完成时间：2018.09.09 14:14:12
    @ApiModelProperty(value = "订单完成时间")
    private Date orderFinishTime;

    @ApiModelProperty(value = "查询时间段开始")
    private Date startCreateTime;
    @ApiModelProperty(value = "查询时间段结束")
    private Date endCreateTime;

    //交易金额：10元
    @ApiModelProperty(value = "交易金额/100")
    private Long totalAmount;
    //应付金额：10元
    @ApiModelProperty(value = "应付金额/100")
    private Long needAmount;
    //实付金额/交易金额
    @ApiModelProperty(value = "（实付金额/交易金额）/100")
    private Long amount;
    //微信openid
    @ApiModelProperty(value = "微信openid")
    private String openId;
    //会员手机号：13800138000
    @ApiModelProperty(value = "会员手机号")
    private String mobile;
    //支付方式：微信
    @ApiModelProperty(value = "支付方式见PaymentTypeEnum：PAYMENT_TYPE_WECHAT.微信")
    private PaymentTypeEnum paymentTypeCode;
    //用户id
    @ApiModelProperty(value = "用户id")
    private Long userId;
    //平台会员id
    @ApiModelProperty(value = "平台会员id")
    private Long memberId;

    @ApiModelProperty(value = "页码")
    private Integer pageNum;

    @ApiModelProperty(value = "页大小")
    private Integer pageSize;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getInnerTradeNo() {
        return innerTradeNo;
    }

    public void setInnerTradeNo(String innerTradeNo) {
        this.innerTradeNo = innerTradeNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public Date getOrderFinishTime() {
        return orderFinishTime;
    }

    public void setOrderFinishTime(Date orderFinishTime) {
        this.orderFinishTime = orderFinishTime;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getNeedAmount() {
        return needAmount;
    }

    public void setNeedAmount(Long needAmount) {
        this.needAmount = needAmount;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public PaymentTypeEnum getPaymentTypeCode() {
        return paymentTypeCode;
    }

    public void setPaymentTypeCode(PaymentTypeEnum paymentTypeCode) {
        this.paymentTypeCode = paymentTypeCode;
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
}
