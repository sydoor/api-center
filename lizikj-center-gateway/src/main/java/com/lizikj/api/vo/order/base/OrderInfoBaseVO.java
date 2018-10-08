package com.lizikj.api.vo.order.base;

import com.lizikj.api.vo.order.DiscountTypeVO;
import com.lizikj.common.enums.RemoveStatusEnum;
import com.lizikj.marketing.api.enums.SupportCouponTimeTypeEnum;
import com.lizikj.order.enums.*;
import com.lizikj.payment.pay.enums.PayStatusEnum;
import com.lizikj.payment.refund.enums.RefundStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * 订单详情
 *
 * @author zhoufe
 * @date 2017/8/10 18:42
 */
@ApiModel
public class OrderInfoBaseVO {

    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单编号")
    private String orderNo;


    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序")
    private String sort;

    /**
     * 就餐人数
     */
    @ApiModelProperty(value = "就餐人数")
    private Long peoples;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    //--------------------------------------------
    //  账号设备
    //--------------------------------------------

    /**
     * 店铺ID
     */
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    /**
     * 商户ID
     */
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;

    /**
     * 商圈ID
     */
    @ApiModelProperty(value = "商圈ID")
    private Long tradeAreaId;

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
     * 撩美味的股东会员ID
     */
    @ApiModelProperty(value = "撩美味的股东会员ID")
    private Long partnerMemberId;

    /**
     * 会员手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**
     * snNum号
     */
    @ApiModelProperty(value = "POS设备号")
    private String snNum;

    /**
     * 餐桌ID
     */
    @ApiModelProperty(value = "餐桌ID")
    private Long areaDeskId;


    //--------------------------------------------
    //  时间
    //--------------------------------------------
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 下单时间
     */
    @ApiModelProperty(value = "下单时间")
    private Date orderTime;

    /**
     * 接单时间
     */
    @ApiModelProperty(value = "接单时间")
    private Date recTime;


    /**
     * 支付完成时间：不保存到数据库
     */
    @ApiModelProperty(value = "支付完成时间")
    private Date payFinishTime;

    /**
     * 支付完成收银员：不保存到数据库
     */
    @ApiModelProperty(value = "支付完成收银员")
    private String payFinishCashierName;

    /**
     * 支付完成收银员的角色：不保存到数据库
     */
    @ApiModelProperty(value = "支付完成收银员的角色")
    private String payFinishCashierRoleName;

    /**
     * 退款截止时间：不保存到数据库
     */
    @ApiModelProperty(value = "退款截止时间")
    private Date refundEndTime;


    //--------------------------------------------
    //  状态
    //--------------------------------------------

    /**
     * 订单来源
     */
    @ApiModelProperty(value = "订单来源：见OrderSourceEnum：H5.H5下单(H5) POS.pos主动下单(POS) QUICK.快捷收银台下单 QR_CODE.店铺收款码下单 VALUE_ADD.增值服务订单 RECHARGE.会员充值，LZ_PURCHASE.李子会员购买，SC.小程序，LMW.撩美味 OTHER.其他。")
    private OrderSourceEnum orderSource;

    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态：见：OrderStatusEnum：WAIT_REC.待接单,WAIT_PAY.待结账,FINISHED.已完成,REFUND.退款,CANCEL.取消订单。")
    private OrderStatusEnum orderStatus;

    /**
     * 订单类型
     */
    @ApiModelProperty(value = "订单类型：见：OrderTypeEnum：NORMAL.普通订单,EAT_FIRST.先食后付,PAY_FIRST.先付后食。")
    private OrderTypeEnum orderType;

    /**
     * 订单业务类型
     */
    @ApiModelProperty(value = "订单业务类型：见OrderBizTypeEnum：MERCHANDISE.美食订单，MONEY.快捷收银订单，MEMBER_RECHARGE.会员充值，VALUE_ADD.增值服务。")
    private OrderBizTypeEnum orderBizType;

    /**
     * 订单支付状态
     */
    @ApiModelProperty(value = "订单支付状态：见PayStatusEnum。")
    private PayStatusEnum payStatus;

    /**
     * 订单退款状态
     */
    @ApiModelProperty(value = "订单退款状态：见RefundStatusEnum。")
    private RefundStatusEnum refundStatus;

    /**
     * 是否打包
     */
    @ApiModelProperty(value = "是否打包：见：PackStatusEnum：PACK.是打包,UN_PACK.不打包。")
    private PackStatusEnum packStatus;


    /**
     * 订单关闭状态：1.开启，0.关闭
     */
    @ApiModelProperty(value = "订单关闭状态：见：CloseStatusEnum：OPENED.开启，CLOSED.关闭。")
    private CloseStatusEnum closeStatus;

    /**
     * 是否已开发票：1.已开具，0.未开具
     */
    @ApiModelProperty(value = "是否已开发票：见：InvoiceStatusEnum：YES.已开具，NO.未开具。")
    private InvoiceStatusEnum invoiceStatus;


    /**
     * 删除状态
     */
    @ApiModelProperty(value = "删除状态：见：RemoveStatusEnum：UN_REMOVED.未删除,REMOVED.已删除。")
    private RemoveStatusEnum removeStatus;


    //--------------------------------------------
    //  金额
    //--------------------------------------------

    /**
     * 订单总金额
     */
    @ApiModelProperty(value = "订单总金额")
    private Long totalAmount;

    /**
     * 应付金额
     */
    @ApiModelProperty(value = "应付金额")
    private Long needAmount;

    /**
     * 成本价
     */
    @ApiModelProperty(value = "成本价")
    private Long costAmount;

    /**
     * 优惠总金额
     */
    @ApiModelProperty(value = "优惠总金额")
    private Long benefitAmount;

    /**
     * 已支付金额：回调的金额， 或者真正实收的金额
     */
    @ApiModelProperty(value = "已支付金额：回调的金额， 或者真正实收的金额")
    private Long payAmount;

    /**
     * 发生退款时，此订单退款的金额
     */
    @ApiModelProperty(value = "发生退款时，此订单退款的金额")
    private Long refundAmount;

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

    /**
     * 剁手价
     */
    @ApiModelProperty(value = "订单剁手价")
    private Long minCutAmount;

    /**
     * 砍价标识
     */
    @ApiModelProperty(value = "砍价标识，UNABLE：不能砍价，ENABLE：允许砍价，DOING：砍价进行中", required = true)
    private CutPriceFlagEnum cutPriceFlag;

    /**
     * 前端选中的优惠方式
     */
    @ApiModelProperty(value = "前端选中的优惠方式:见DiscountTypeNodeEnum。")
    private DiscountTypeNodeEnum discountSelected;

    /**
     * 提供优惠选择的列表
     */
    @ApiModelProperty(value = "提供优惠选择的列表")
    private List<DiscountTypeVO> discountTypes;
    /**
     * 是否已经打印小票标志位：见PrintTicketFlagEnum，标志位1为已经上报打印，否则为不打印
     */
    @ApiModelProperty(value = "是否已经打印小票标志位：见PrintTicketFlagEnum，2.点单完成小票，4.会员充值支付完成小票，" +
            "8.结账支付完成小票，16.退款完成小票，32.快键收银支付完成小票，64.饿了么外卖接单完成小票，128.美团外卖接单完成小票。" +
            "如果一次性传多个完成小票：2|4=6")
    private Integer printTicketFlag;

    /**
     * 支付完成时间
     */
    @ApiModelProperty(value = "支付完成时间")
    private Date paySuccessTime;

    /**
     * 退款完成时间
     */
    @ApiModelProperty(value = "退款完成时间")
    private Date refundSuccessTime;


    /**
     * 退款申请时间
     */
    @ApiModelProperty(value = "退款申请时间")
    private Date refundApplyTime;

    /**
     * 忙闲状态：0.无，1.闲时，2.忙时
     */
    @ApiModelProperty(value = "忙闲状态：见SupportCouponTimeTypeEnum：NONE 无，BUSY_TIME 忙时，IDLE_TIME 闲时。")
    private SupportCouponTimeTypeEnum idleStatus;

    @ApiModelProperty(value = "待下单的ID")
    private Long pendingOrderInfoId;

    @ApiModelProperty(value = "待下单的订单的类型 PendingOrderTypeEnum：NONE 无，DELIVERY_BY_CLIENT 到店自取，DELIVERY_BY_MERCHANT 商户配送，FOR_HERE 到店堂食。")
    private PendingOrderTypeEnum pendingOrderType;


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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Long getPeoples() {
        return peoples;
    }

    public void setPeoples(Long peoples) {
        this.peoples = peoples;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Long getPartnerMemberId() {
        return partnerMemberId;
    }

    public void setPartnerMemberId(Long partnerMemberId) {
        this.partnerMemberId = partnerMemberId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getRecTime() {
        return recTime;
    }

    public void setRecTime(Date recTime) {
        this.recTime = recTime;
    }

    public OrderSourceEnum getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(OrderSourceEnum orderSource) {
        this.orderSource = orderSource;
    }

    public PackStatusEnum getPackStatus() {
        return packStatus;
    }

    public void setPackStatus(PackStatusEnum packStatus) {
        this.packStatus = packStatus;
    }

    public CloseStatusEnum getCloseStatus() {
        return closeStatus;
    }

    public void setCloseStatus(CloseStatusEnum closeStatus) {
        this.closeStatus = closeStatus;
    }

    public InvoiceStatusEnum getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(InvoiceStatusEnum invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
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

    public RemoveStatusEnum getRemoveStatus() {
        return removeStatus;
    }

    public void setRemoveStatus(RemoveStatusEnum removeStatus) {
        this.removeStatus = removeStatus;
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

    public Long getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(Long costAmount) {
        this.costAmount = costAmount;
    }

    public Long getBenefitAmount() {
        return benefitAmount;
    }

    public void setBenefitAmount(Long benefitAmount) {
        this.benefitAmount = benefitAmount;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public Long getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Long refundAmount) {
        this.refundAmount = refundAmount;
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

    public Long getMinCutAmount() {
        return minCutAmount;
    }

    public void setMinCutAmount(Long minCutAmount) {
        this.minCutAmount = minCutAmount;
    }

    public CutPriceFlagEnum getCutPriceFlag() {
        return cutPriceFlag;
    }

    public void setCutPriceFlag(CutPriceFlagEnum cutPriceFlag) {
        this.cutPriceFlag = cutPriceFlag;
    }

    public OrderBizTypeEnum getOrderBizType() {
        return orderBizType;
    }

    public void setOrderBizType(OrderBizTypeEnum orderBizType) {
        this.orderBizType = orderBizType;
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

    public DiscountTypeNodeEnum getDiscountSelected() {
        return discountSelected;
    }

    public void setDiscountSelected(DiscountTypeNodeEnum discountSelected) {
        this.discountSelected = discountSelected;
    }

    public List<DiscountTypeVO> getDiscountTypes() {
        return discountTypes;
    }

    public void setDiscountTypes(List<DiscountTypeVO> discountTypes) {
        this.discountTypes = discountTypes;
    }

    public Date getPayFinishTime() {
        return payFinishTime;
    }

    public void setPayFinishTime(Date payFinishTime) {
        this.payFinishTime = payFinishTime;
    }

    public String getPayFinishCashierName() {
        return payFinishCashierName;
    }

    public void setPayFinishCashierName(String payFinishCashierName) {
        this.payFinishCashierName = payFinishCashierName;
    }

    public String getPayFinishCashierRoleName() {
        return payFinishCashierRoleName;
    }

    public void setPayFinishCashierRoleName(String payFinishCashierRoleName) {
        this.payFinishCashierRoleName = payFinishCashierRoleName;
    }

    public Date getRefundEndTime() {
        return refundEndTime;
    }

    public void setRefundEndTime(Date refundEndTime) {
        this.refundEndTime = refundEndTime;
    }

    public Integer getPrintTicketFlag() {
        return printTicketFlag;
    }

    public void setPrintTicketFlag(Integer printTicketFlag) {
        this.printTicketFlag = printTicketFlag;
    }

    public Date getPaySuccessTime() {
        return paySuccessTime;
    }

    public void setPaySuccessTime(Date paySuccessTime) {
        this.paySuccessTime = paySuccessTime;
    }

    public Date getRefundSuccessTime() {
        return refundSuccessTime;
    }

    public void setRefundSuccessTime(Date refundSuccessTime) {
        this.refundSuccessTime = refundSuccessTime;
    }

    public Date getRefundApplyTime() {
        return refundApplyTime;
    }

    public void setRefundApplyTime(Date refundApplyTime) {
        this.refundApplyTime = refundApplyTime;
    }

    public Long getTradeAreaId() {
        return tradeAreaId;
    }

    public void setTradeAreaId(Long tradeAreaId) {
        this.tradeAreaId = tradeAreaId;
    }

    public SupportCouponTimeTypeEnum getIdleStatus() {
        return idleStatus;
    }

    public void setIdleStatus(SupportCouponTimeTypeEnum idleStatus) {
        this.idleStatus = idleStatus;
    }

    public Long getPendingOrderInfoId() {
        return pendingOrderInfoId;
    }

    public void setPendingOrderInfoId(Long pendingOrderInfoId) {
        this.pendingOrderInfoId = pendingOrderInfoId;
    }

    public PendingOrderTypeEnum getPendingOrderType() {
        return pendingOrderType;
    }

    public void setPendingOrderType(PendingOrderTypeEnum pendingOrderType) {
        this.pendingOrderType = pendingOrderType;
    }
}
