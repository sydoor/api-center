package com.lizikj.api.vo.order;

import com.lizikj.api.vo.order.param.SyncOrderContentParamVO;
import com.lizikj.common.enums.RemoveStatusEnum;
import com.lizikj.order.enums.*;
import com.lizikj.payment.pay.enums.PayStatusEnum;
import com.lizikj.payment.refund.enums.RefundStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Model for table -- sync_pos_order_info
 * Created by zhoufe on 2017-9-29 18:25:58
 */
@ApiModel
public class SyncPosOrderInfoVO implements Serializable {

    private static final long serialVersionUID = 627121609798100168L;
    /**
     * 自增ID
     */
    @ApiModelProperty(value = "自增ID", required = true)
    private Long syncPosOrderId;

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
     * 订单总金额
     */
    @ApiModelProperty(value = "订单总金额", required = true)
    private Long totalAmount;

    /**
     * 应付金额
     */
    @ApiModelProperty(value = "应付金额", required = true)
    private Long needAmount;

    /**
     * 成本价
     */
    @ApiModelProperty(value = "成本价", required = true)
    private Long costAmount;

    /**
     * 优惠总金额
     */
    @ApiModelProperty(value = "优惠总金额", required = true)
    private Long benefitAmount;

    /**
     * 已支付金额：回调的金额， 或者真正实收的金额
     */
    @ApiModelProperty(value = "已支付金额：回调的金额， 或者真正实收的金额", required = true)
    private Long payAmount;

    /**
     * 发生退款时，此订单退款的金额
     */
    @ApiModelProperty(value = "发生退款时，此订单退款的金额", required = true)
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
     * 订单排序号：今天该店铺的订单序列号，到00点清零
     */
    @ApiModelProperty(value = "订单排序号：今天该店铺的订单序列号，到00点清零", required = true)
    private String sort;

    /**
     * 餐桌ID
     */
    @ApiModelProperty(value = "餐桌ID", required = true)
    private Long areaDeskId;

    /**
     * 自定义桌牌号：桌台管理关闭，自定义桌牌号开启时从前端传入
     */
    @ApiModelProperty(value = "自定义桌牌号：桌台管理关闭，自定义桌牌号开启时从前端传入", required = true)
    private String customDeskNumber;

    /**
     * 就餐人数
     */
    @ApiModelProperty(value = "就餐人数", required = true)
    private Long peoples;

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
     * 撩美味的股东会员ID
     */
    @ApiModelProperty(value = "商户会员ID")
    private Long partnerMemberId;

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
     * 是否打包：1.是，0.否
     */
    @ApiModelProperty(value = "是否打包：见：PackStatusEnum：PACK.是打包,UN_PACK.不打包。")
    private PackStatusEnum packStatus;

    /**
     * 下单人
     */
    @ApiModelProperty(value = "下单人")
    private Long orderPersonId;

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
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 是否删除：1.已删除，0.未删除
     */
    @ApiModelProperty(value = "删除状态：见：RemoveStatusEnum：UN_REMOVED.未删除,REMOVED.已删除。")
    private RemoveStatusEnum removeStatus;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

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
            "DISCOUNT(4).优惠处理标识，PAY(8).支付处理标识，REFUND(16).退款处理标识。30是全部成功。")
    private Integer dealFlag;

    /**
     * 记录同步订单系统处理次数，对于多次尝试未全部处理完的订单，需要人工介入处理
     */
    @ApiModelProperty(value = "记录同步订单系统处理次数，对于多次（见OrderConstant.SYNC_TASK_TIMES_MAX=5）尝试未全部处理完的订单，需要人工介入处理")
    private Integer syncTaskTimes;

    /**
     * 是否已经打印小票标志位：见PrintTicketFlagEnum，标志位1为已经上报打印，否则为不打印
     */
    @ApiModelProperty(value = "是否已经打印小票标志位：见PrintTicketFlagEnum，2.点单完成小票，4.会员充值支付完成小票，"
            + "8.结账支付完成小票，16.退款完成小票，32.快键收银支付完成小票，64.饿了么外卖接单完成小票，128.美团外卖接单完成小票。"
            + "如果一次性传多个完成小票：2|4=6")
    private Integer printTicketFlag;

    /**
     * 支付完成时间
     */
    @ApiModelProperty(value = "支付完成时间")
    private Date paySuccessTime;

    /**
     * 订单详情内容，包含订单物品信息，优化信息，支付信息，退款信息等
     */
    @ApiModelProperty(value = "订单详情内容，包含订单物品信息，优化信息，支付信息，退款信息等")
    private String content;


    @ApiModelProperty(value = "订单详情内容，包含订单物品信息，优化信息，支付信息，退款信息等")
    private SyncOrderContentParamVO syncOrderContent;

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
     * 自增ID
     */
    public Long getSyncPosOrderId() {
        return syncPosOrderId;
    }

    /**
     * 自增ID
     * @param syncPosOrderId the value for sync_pos_order_info.sync_pos_order_id
     */
    public void setSyncPosOrderId(Long syncPosOrderId) {
        this.syncPosOrderId = syncPosOrderId;
    }

    /**
     * 订单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 订单号
     * @param orderNo the value for sync_pos_order_info.order_no
     */
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

    /**
     * 订单总金额
     */
    public Long getTotalAmount() {
        return totalAmount;
    }

    /**
     * 订单总金额
     * @param totalAmount the value for sync_pos_order_info.total_amount
     */
    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 应付金额
     */
    public Long getNeedAmount() {
        return needAmount;
    }

    /**
     * 应付金额
     * @param needAmount the value for sync_pos_order_info.need_amount
     */
    public void setNeedAmount(Long needAmount) {
        this.needAmount = needAmount;
    }

    /**
     * 成本价
     */
    public Long getCostAmount() {
        return costAmount;
    }

    /**
     * 成本价
     * @param costAmount the value for sync_pos_order_info.cost_amount
     */
    public void setCostAmount(Long costAmount) {
        this.costAmount = costAmount;
    }

    /**
     * 优惠总金额
     */
    public Long getBenefitAmount() {
        return benefitAmount;
    }

    /**
     * 优惠总金额
     * @param benefitAmount the value for sync_pos_order_info.benefit_amount
     */
    public void setBenefitAmount(Long benefitAmount) {
        this.benefitAmount = benefitAmount;
    }

    /**
     * 已支付金额：回调的金额， 或者真正实收的金额
     */
    public Long getPayAmount() {
        return payAmount;
    }

    /**
     * 已支付金额：回调的金额， 或者真正实收的金额
     * @param payAmount the value for sync_pos_order_info.pay_amount
     */
    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * 发生退款时，此订单退款的金额
     */
    public Long getRefundAmount() {
        return refundAmount;
    }

    /**
     * 发生退款时，此订单退款的金额
     * @param refundAmount the value for sync_pos_order_info.refund_amount
     */
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

    /**
     * 订单排序号：今天该店铺的订单序列号，到00点清零
     */
    public String getSort() {
        return sort;
    }

    /**
     * 订单排序号：今天该店铺的订单序列号，到00点清零
     * @param sort the value for sync_pos_order_info.sort
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * 餐桌ID
     */
    public Long getAreaDeskId() {
        return areaDeskId;
    }

    /**
     * 餐桌ID
     * @param areaDeskId the value for sync_pos_order_info.area_desk_id
     */
    public void setAreaDeskId(Long areaDeskId) {
        this.areaDeskId = areaDeskId;
    }

    /**
     * 自定义桌牌号：桌台管理关闭，自定义桌牌号开启时从前端传入
     */
    public String getCustomDeskNumber() {
        return customDeskNumber;
    }

    /**
     * 自定义桌牌号：桌台管理关闭，自定义桌牌号开启时从前端传入
     * @param customDeskNumber the value for sync_pos_order_info.custom_desk_number
     */
    public void setCustomDeskNumber(String customDeskNumber) {
        this.customDeskNumber = customDeskNumber;
    }

    /**
     * 就餐人数
     */
    public Long getPeoples() {
        return peoples;
    }

    /**
     * 就餐人数
     * @param peoples the value for sync_pos_order_info.peoples
     */
    public void setPeoples(Long peoples) {
        this.peoples = peoples;
    }

    /**
     * 店铺ID
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 店铺ID
     * @param shopId the value for sync_pos_order_info.shop_id
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 商户ID
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * 商户ID
     * @param merchantId the value for sync_pos_order_info.merchant_id
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 李子会员ID
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * 李子会员ID
     * @param memberId the value for sync_pos_order_info.member_id
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * 商户会员ID
     */
    public Long getMerchantMemberId() {
        return merchantMemberId;
    }

    /**
     * 商户会员ID
     * @param merchantMemberId the value for sync_pos_order_info.merchant_member_id
     */
    public void setMerchantMemberId(Long merchantMemberId) {
        this.merchantMemberId = merchantMemberId;
    }

    public Long getPartnerMemberId() {
        return partnerMemberId;
    }

    public void setPartnerMemberId(Long partnerMemberId) {
        this.partnerMemberId = partnerMemberId;
    }

    /**
     * 会员手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 会员手机号
     * @param mobile the value for sync_pos_order_info.mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 用户ID
     * @param userId the value for sync_pos_order_info.user_id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * snNum号
     */
    public String getSnNum() {
        return snNum;
    }

    /**
     * snNum号
     * @param snNum the value for sync_pos_order_info.sn_num
     */
    public void setSnNum(String snNum) {
        this.snNum = snNum;
    }

    public PackStatusEnum getPackStatus() {
        return packStatus;
    }

    public void setPackStatus(PackStatusEnum packStatus) {
        this.packStatus = packStatus;
    }

    /**
     * 下单人
     */
    public Long getOrderPersonId() {
        return orderPersonId;
    }

    /**
     * 下单人
     * @param orderPersonId the value for sync_pos_order_info.order_person_id
     */
    public void setOrderPersonId(Long orderPersonId) {
        this.orderPersonId = orderPersonId;
    }

    /**
     * 下单时间
     */
    public Date getOrderTime() {
        return orderTime;
    }

    /**
     * 下单时间
     * @param orderTime the value for sync_pos_order_info.order_time
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * 接单时间
     */
    public Date getRecTime() {
        return recTime;
    }

    /**
     * 接单时间
     * @param recTime the value for sync_pos_order_info.rec_time
     */
    public void setRecTime(Date recTime) {
        this.recTime = recTime;
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

    /**
     * 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark the value for sync_pos_order_info.remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public RemoveStatusEnum getRemoveStatus() {
        return removeStatus;
    }

    public void setRemoveStatus(RemoveStatusEnum removeStatus) {
        this.removeStatus = removeStatus;
    }

    /**
     * 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     * @param updateTime the value for sync_pos_order_info.update_time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime the value for sync_pos_order_info.create_time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 处理标识位，参考SyncOrderDealFlagEnum
     */
    public Integer getDealFlag() {
        return dealFlag;
    }

    /**
     * 处理标识位，参考SyncOrderDealFlagEnum
     * @param dealFlag the value for sync_pos_order_info.deal_flag
     */
    public void setDealFlag(Integer dealFlag) {
        this.dealFlag = dealFlag;
    }

    /**
     * 记录同步订单系统处理次数，对于多次尝试未全部处理完的订单，需要人工介入处理
     */
    public Integer getSyncTaskTimes() {
        return syncTaskTimes;
    }

    /**
     * 记录同步订单系统处理次数，对于多次尝试未全部处理完的订单，需要人工介入处理
     * @param syncTaskTimes the value for sync_pos_order_info.sync_task_times
     */
    public void setSyncTaskTimes(Integer syncTaskTimes) {
        this.syncTaskTimes = syncTaskTimes;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SyncOrderContentParamVO getSyncOrderContent() {
        return syncOrderContent;
    }

    public void setSyncOrderContent(SyncOrderContentParamVO syncOrderContent) {
        this.syncOrderContent = syncOrderContent;
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
}