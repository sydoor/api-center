package com.lizikj.api.vo.member;

import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 会员账户流水明细
 * Created by liangjiankang on 2017-07-06 15:31:33
 */
@ApiModel(value="会员账户流水明细")
public class MerchantMemberAccountFlowDetailVO extends BaseDTO {
    private static final long serialVersionUID = 133123656306554521L;
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
     * 商户会员id
     */
    @ApiModelProperty(value = "商户会员id")
    private Long merchantMemberId;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "会员名称")
    private String realName;
    @ApiModelProperty(value = "会员卡")
    private String memberNum;

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
    @ApiModelProperty(value = "获取类型 1100收入 1200支出")
    private Long makeType;

    /**
     * 获取来源2100充值  2200 下单奖励 2300 下单消费  2400 订单退款
     */
    @ApiModelProperty(value = "获取来源2100充值  2200 下单奖励 2300 下单消费  2400 订单退款")
    private Long makeSource;

    /**
     * 交易方式 3100积分 3200李子贝
     */
    @ApiModelProperty(value = "交易方式 3100积分 3200李子贝")
    private Long tradeType;

    /**
     * 涉及金额/积分
     */
    @ApiModelProperty(value = "涉及金额/积分")
    private Long amount;

    /**
     * 获得积分
     */
    @ApiModelProperty(value = "涉及金额/积分")
    private Long obtainCredit;

    /**
     * 订单No
     */
    @ApiModelProperty(value = "订单No")
    private String orderNo;

    /**
     * 订单交易版本
     */
    @ApiModelProperty(value = "订单交易版本")
    private Long orderVersion;

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
     * 会员消费记录id
     */
    public Long getFlowDetailId() {
        return flowDetailId;
    }

    /**
     * 会员消费记录id
     * @param flowDetailId the value for member_account_flow_detail.flow_detail_id
     */
    public void setFlowDetailId(Long flowDetailId) {
        this.flowDetailId = flowDetailId;
    }

    /**
     * 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 用户id
     * @param userId the value for member_account_flow_detail.user_id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMerchantMemberId() {
        return merchantMemberId;
    }

    public void setMerchantMemberId(Long merchantMemberId) {
        this.merchantMemberId = merchantMemberId;
    }

    /**
     * 商户id
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * 商户id
     * @param merchantId the value for member_account_flow_detail.merchant_id
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 店铺id
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 店铺id
     * @param shopId the value for member_account_flow_detail.shop_id
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 获取类型 1100收入 1200支出
     */
    public Long getMakeType() {
        return makeType;
    }

    /**
     * 获取类型 1100收入 1200支出
     * @param makeType the value for member_account_flow_detail.make_type
     */
    public void setMakeType(Long makeType) {
        this.makeType = makeType;
    }

    /**
     * 获取来源2100充值  2200 下单奖励 2300 下单消费  2400 订单退款
     */
    public Long getMakeSource() {
        return makeSource;
    }

    /**
     * 获取来源2100充值  2200 下单奖励 2300 下单消费  2400 订单退款
     * @param makeSource the value for member_account_flow_detail.make_source
     */
    public void setMakeSource(Long makeSource) {
        this.makeSource = makeSource;
    }

    /**
     * 交易方式 3100积分 3200李子贝
     */
    public Long getTradeType() {
        return tradeType;
    }

    /**
     * 交易方式 3100积分 3200李子贝
     * @param tradeType the value for member_account_flow_detail.trade_type
     */
    public void setTradeType(Long tradeType) {
        this.tradeType = tradeType;
    }

    /**
     * 涉及金额/积分
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * 涉及金额/积分
     * @param amount the value for member_account_flow_detail.amount
     */
    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 订单交易版本
     */
    public Long getOrderVersion() {
        return orderVersion;
    }

    /**
     * 订单交易版本
     * @param orderVersion the value for member_account_flow_detail.order_version
     */
    public void setOrderVersion(Long orderVersion) {
        this.orderVersion = orderVersion;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime the value for member_account_flow_detail.create_time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     * @param updateTime the value for member_account_flow_detail.update_time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getObtainCredit() {
        return obtainCredit;
    }

    public void setObtainCredit(Long obtainCredit) {
        this.obtainCredit = obtainCredit;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }
}