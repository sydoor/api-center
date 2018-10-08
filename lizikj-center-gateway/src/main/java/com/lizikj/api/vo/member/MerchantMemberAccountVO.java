package com.lizikj.api.vo.member;

import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 商户会员账户信息
 * Created by liangjiankang on 2017-07-06 15:31:33
 */
@ApiModel(value="商户会员账户信息")
public class MerchantMemberAccountVO extends BaseDTO {
    private static final long serialVersionUID = -2070699312835946382L;
    /**
     * 用户账务id
     */
    @ApiModelProperty(value = "用户账务id")
    private Long accountId;

    /**
     * 商户id
     */
    @ApiModelProperty(value = "商户id")
    private Long merchantId;

    /**
     * 会员id
     */
    @ApiModelProperty(value = "商户会员id")
    private Long merchantMemberId;

    /**
     * 剩余积分
     */
    @ApiModelProperty(value = "剩余积分")
    private Long currentCredit;
    /**
     * 剩余金额
     */
    @ApiModelProperty(value = "剩余金额")
    private Long currentAmount;
    /**
     * 账户余额
     */
    @ApiModelProperty(value = "账户余额")
    private Long surplusAmount;
    /**
     * 总消费金额
     */
    @ApiModelProperty(value = "总消费金额")
    private Long totalCostAmount;
    /**
     * 累计积分
     */
    @ApiModelProperty(value = "累计积分")
    private Long totalCredit;
    /**
     * 充值次数
     */
    @ApiModelProperty(value = "充值次数")
    private Long rechargeTimes;
    /**
     * 消费次数
     */
    @ApiModelProperty(value = "消费次数")
    private Long costTimes;

    /**
     * 剩余积分
     */
    @ApiModelProperty(value = "剩余积分")
    private Long surplusCredit;

    /**
     * 累计使用积分
     */
    @ApiModelProperty(value = "累计使用积分")
    private Long totalUseCredit;


    /**
     * 用户账务id
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * 用户账务id
     * @param accountId the value for member_account.account_id
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * 商户id
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * 商户id
     * @param merchantId the value for member_account.merchant_id
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }


    /**
     * 累计积分
     */
    public Long getTotalCredit() {
        return totalCredit;
    }

    /**
     * 累计积分
     * @param totalCredit the value for member_account.total_credit
     */
    public void setTotalCredit(Long totalCredit) {
        this.totalCredit = totalCredit;
    }

    /**
     * 累计使用积分
     */
    public Long getTotalUseCredit() {
        return totalUseCredit;
    }

    /**
     * 累计使用积分
     * @param totalUseCredit the value for member_account.total_use_credit
     */
    public void setTotalUseCredit(Long totalUseCredit) {
        this.totalUseCredit = totalUseCredit;
    }

    /**
     * 总消费金额
     */
    public Long getTotalCostAmount() {
        return totalCostAmount;
    }

    /**
     * 总消费金额
     * @param totalCostAmount the value for member_account.total_cost_amount
     */
    public void setTotalCostAmount(Long totalCostAmount) {
        this.totalCostAmount = totalCostAmount;
    }
    /**
     * 充值次数
     */
    public Long getRechargeTimes() {
        return rechargeTimes;
    }

    /**
     * 充值次数
     * @param rechargeTimes the value for member_account.recharge_times
     */
    public void setRechargeTimes(Long rechargeTimes) {
        this.rechargeTimes = rechargeTimes;
    }

    /**
     * 消费次数
     */
    public Long getCostTimes() {
        return costTimes;
    }
    /**
     * 消费次数
     * @param costTimes the value for member_account.cost_times
     */
    public void setCostTimes(Long costTimes) {
        this.costTimes = costTimes;
    }

    public Long getSurplusAmount() {
        return surplusAmount;
    }

    public void setSurplusAmount(Long surplusAmount) {
        this.surplusAmount = surplusAmount;
    }

    public Long getSurplusCredit() {
        return surplusCredit;
    }

    public void setSurplusCredit(Long surplusCredit) {
        this.surplusCredit = surplusCredit;
    }

    public Long getMerchantMemberId() {
        return merchantMemberId;
    }

    public void setMerchantMemberId(Long merchantMemberId) {
        this.merchantMemberId = merchantMemberId;
    }

    public Long getCurrentCredit() {
        return currentCredit;
    }

    public void setCurrentCredit(Long currentCredit) {
        this.currentCredit = currentCredit;
    }

    public Long getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Long currentAmount) {
        this.currentAmount = currentAmount;
    }
}