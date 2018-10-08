package com.lizikj.api.vo.member.param;

import com.lizikj.member.enums.FlowSourceTypeEnum;
import com.lizikj.member.enums.FlowTradeTypeEnum;
import com.lizikj.member.enums.FlowTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author lxl
 * @Date 2017/11/28 15:13
 */
@ApiModel
public class MerchantMemberFlowQueryVO {
    /**
     * 会员消费记录id
     */
    @ApiModelProperty(value = "会员消费记录id")
    private Long flowDetailId;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 会员id
     */
    @ApiModelProperty(value = "会员id")
    private Long memberId;

    @ApiModelProperty(value = "商户会员ID")
    private Long merchantMemberId;

    @ApiModelProperty(value = "会员手机号")
    private String mobile;

    /**
     * 商户id
     */
    @ApiModelProperty(value = "商户id")
    private Long merchantId;
    /**
     * 店铺id
     */
    @ApiModelProperty(value = "店铺id")
    private Long shopId;
    /**
     * 获取类型 1100收入 1200支出
     */
    @ApiModelProperty(value = "获取类型")
    private FlowTypeEnum flowTypeEnum;
    /**
     * 获取来源2100充值  2200 下单奖励 2300 下单消费  2400 订单退款
     */
    @ApiModelProperty(value = "取来源")
    private FlowSourceTypeEnum flowSourceTypeEnum;

    /**
     * 交易方式 3100积分 3200李子贝
     */
    @ApiModelProperty(value = "交易方式")
    private FlowTradeTypeEnum flowTradeTypeEnum;

    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单NO")
    private String orderNo;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    public Long getFlowDetailId() {
        return flowDetailId;
    }

    public void setFlowDetailId(Long flowDetailId) {
        this.flowDetailId = flowDetailId;
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

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public FlowTypeEnum getFlowTypeEnum() {
        return flowTypeEnum;
    }

    public void setFlowTypeEnum(FlowTypeEnum flowTypeEnum) {
        this.flowTypeEnum = flowTypeEnum;
    }

    public FlowSourceTypeEnum getFlowSourceTypeEnum() {
        return flowSourceTypeEnum;
    }

    public void setFlowSourceTypeEnum(FlowSourceTypeEnum flowSourceTypeEnum) {
        this.flowSourceTypeEnum = flowSourceTypeEnum;
    }

    public FlowTradeTypeEnum getFlowTradeTypeEnum() {
        return flowTradeTypeEnum;
    }

    public void setFlowTradeTypeEnum(FlowTradeTypeEnum flowTradeTypeEnum) {
        this.flowTradeTypeEnum = flowTradeTypeEnum;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
