package com.lizikj.api.vo.order.param;

import com.lizikj.common.enums.RemoveStatusEnum;
import com.lizikj.order.enums.OrderBizTypeEnum;
import com.lizikj.order.enums.OrderSourceEnum;
import com.lizikj.order.enums.OrderStatusEnum;
import com.lizikj.order.enums.OrderTypeEnum;
import com.lizikj.payment.pay.enums.PayStatusEnum;
import com.lizikj.payment.refund.enums.RefundStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhoufe
 * @date 2017/11/15 16:18
 */
@ApiModel
public class QuerySyncOrderParamVO implements Serializable {

    private static final long serialVersionUID = 5248424434343525698L;

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号", required = true)
    private String orderNo;

    /**
     * 订单类型:0.默认1.先食后付款,2.先付后食(只有店铺美食订单才存在先食后付，先付后食情况)
     */
    @ApiModelProperty(value = "订单类型：见：OrderTypeEnum：NORMAL.普通订单,EAT_FIRST.先食后付,PAY_FIRST.先付后食。")
    private OrderTypeEnum orderType;

    /**
     * 订单业务类型：1.美食订单 2.收银订单(包含现金收银，店铺二维码收银)，3.会员充值订单，4.增值服务订单
     */
    @ApiModelProperty(value = "订单业务类型：见OrderBizTypeEnum：MERCHANDISE.美食订单，MONEY.快捷收银订单，MEMBER_RECHARGE.会员充值，VALUE_ADD.增值服务。")
    private OrderBizTypeEnum orderBizType;

    /**
     * 订单状态：11  待接单(WAIT_REC)，21  待结账(WAIT_PAY)，31  已完成(HAD_SUCCESS)，41 退款，81  取消订单(CANCEL)
     */
    @ApiModelProperty(value = "订单状态：见：OrderStatusEnum：WAIT_REC.待接单,WAIT_PAY.待结账,FINISHED.已完成,REFUND.退款,CANCEL.取消订单。")
    private OrderStatusEnum orderStatus;

    /**
     * 支付状态，冗余字段，参考支付系统流水支付状态
     */
    @ApiModelProperty(value = "订单支付状态：见PayStatusEnum。")
    private PayStatusEnum payStatus;

    /**
     * 退款状态，冗余字段，参考支付系统流水退款状态
     */
    @ApiModelProperty(value = "退款状态：见：RefundStatusEnum：NORMAL.默认状态,APPLY.退款申请,SUCCESS.退款成功,FAIL.退款失败。")
    private RefundStatusEnum refundStatus;

    /**
     * 订单来源：1  H5下单(H5)，2  pos主动下单(POS)，3  快捷收银台下单(QUICK)，4  店铺收款码下单(QR_CODE)，5  增值服务订单(VALUE_ADD)，6 会员充值(MEMBER_RECHARGE)
     */
    @ApiModelProperty(value = "订单来源：见OrderSourceEnum：H5.H5下单(H5) POS.pos主动下单(POS) QUICK.快捷收银台下单 QR_CODE.店铺收款码下单 VALUE_ADD.增值服务订单 RECHARGE.会员充值，LZ_PURCHASE.李子会员购买，SC.小程序，LMW.撩美味 OTHER.其他。")
    private OrderSourceEnum orderSource;

    /**
     * 店铺ID
     */
    @ApiModelProperty(value = "店铺ID", required = true)
    private Long shopId;

    /**
     * 商户ID
     */
    @ApiModelProperty(value = "商户ID", required = true)
    private Long merchantId;

    /**
     * 李子会员ID
     */
    @ApiModelProperty(value = "李子会员ID", required = true)
    private Long memberId;

    /**
     * 商户会员ID
     */
    @ApiModelProperty(value = "商户会员ID", required = true)
    private Long merchantMemberId;

    /**
     * 会员手机号
     */
    @ApiModelProperty(value = "会员手机号", required = true)
    private String mobile;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", required = true)
    private Long userId;

    /**
     * snNum号
     */
    @ApiModelProperty(value = "snNum号", required = true)
    private String snNum;


    /**
     * 是否删除：1.已删除，0.未删除
     */
    @ApiModelProperty(value = "删除状态：见：RemoveStatusEnum：UN_REMOVED.未删除,REMOVED.已删除。")
    private RemoveStatusEnum removeStatus;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "下单开始时间")
    private Date startOrderTime;


    @ApiModelProperty(value = "下单结束时间")
    private Date endOrderTime;

    /**
     * 处理标识位，参考SyncOrderDealFlagEnum
     *    NONE(0, "所有信息都未处理"),
     ORDER_ITEM(1 << 1, "订单物品处理标识"),
     DISCOUNT(1 << 2, "优惠处理标识"),
     PAY(1 << 3, "支付处理标识"),
     REFUND(1 << 4, "退款处理标识"),
     */
    @ApiModelProperty(value = "处理标识位，参考SyncOrderDealFlagEnum：" +
            "NONE(0).所有信息都未处理，ORDER_ITEM(2).订单及物品处理标识，" +
            "DISCOUNT(4).优惠处理标识，PAY(8).支付处理标识，REFUND(16).退款处理标识。2147483647.是全部成功。")
    private Integer dealFlag;

    /**
     * 不等于的同步订单
     */
    @ApiModelProperty(value = "查询不等于处理标志位的标志位")
    private Integer notEqualsDealFlag;

    /**
     * 记录同步订单系统处理次数，对于多次尝试未全部处理完的订单，需要人工介入处理
     */
    @ApiModelProperty(value = "记录同步订单系统处理次数，对于多次（见OrderConstant.SYNC_TASK_TIMES_MAX=5）尝试未全部处理完的订单，需要人工介入处理")
    private Integer syncTaskTimes;

    @ApiModelProperty(value = "页码")
    private Integer pageNum;

    @ApiModelProperty(value = "页面大小")
    private Integer pageSize;


    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public OrderTypeEnum getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderTypeEnum orderType) {
        this.orderType = orderType;
    }

    public OrderBizTypeEnum getOrderBizType() {
        return orderBizType;
    }

    public void setOrderBizType(OrderBizTypeEnum orderBizType) {
        this.orderBizType = orderBizType;
    }

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

    public PayStatusEnum getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(PayStatusEnum payStatus) {
        this.payStatus = payStatus;
    }

    public RefundStatusEnum getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(RefundStatusEnum refundStatus) {
        this.refundStatus = refundStatus;
    }

    public OrderSourceEnum getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(OrderSourceEnum orderSource) {
        this.orderSource = orderSource;
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

    public RemoveStatusEnum getRemoveStatus() {
        return removeStatus;
    }

    public void setRemoveStatus(RemoveStatusEnum removeStatus) {
        this.removeStatus = removeStatus;
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

    public Integer getDealFlag() {
        return dealFlag;
    }

    public void setDealFlag(Integer dealFlag) {
        this.dealFlag = dealFlag;
    }

    public Integer getNotEqualsDealFlag() {
        return notEqualsDealFlag;
    }

    public void setNotEqualsDealFlag(Integer notEqualsDealFlag) {
        this.notEqualsDealFlag = notEqualsDealFlag;
    }

    public Integer getSyncTaskTimes() {
        return syncTaskTimes;
    }

    public void setSyncTaskTimes(Integer syncTaskTimes) {
        this.syncTaskTimes = syncTaskTimes;
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
}
