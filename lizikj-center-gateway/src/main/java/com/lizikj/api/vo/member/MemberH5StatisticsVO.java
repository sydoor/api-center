package com.lizikj.api.vo.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author lxl
 * @Date 2018/1/26 9:57
 */
@ApiModel
public class MemberH5StatisticsVO {
    @ApiModelProperty(value = "商户会员ID")
    private Long merchantMemberId;
    @ApiModelProperty(value = "会员ID")
    private Long memberId;
    @ApiModelProperty(value = "会员卡号")
    private String memberNum;
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    @ApiModelProperty(value = "商户Logo")
    private Long merchantLogoPicId;
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    @ApiModelProperty(value = "会员等级")
    private Integer levelCode;
    @ApiModelProperty(value = "会员等级名")
    private String levelName;
    @ApiModelProperty(value = "会员折扣")
    private Double discount;
    /**
     * 注册时间createDate
     */
    @ApiModelProperty(value = "注册时间createDate")
    private Date registerDate;
    /**
     * 总消费金额
     */
    @ApiModelProperty(value = "总消费金额(元)")
    private Long totalCostAmount;
    /**
     * 累计积分
     */
    @ApiModelProperty(value = "累计积分")
    private Long totalCredit;

    /**
     * 累计使用积分
     */
    @ApiModelProperty(value = "累计使用积分")
    private Long totalUseCredit;
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
     * 累计充值金额
     */
    @ApiModelProperty(value = "累计充值金额（分）")
    private Long totalRechargeAmount;

    /**
     * 累计消费充值金额
     */
    @ApiModelProperty(value = "累计消费充值金额（分）")
    private Long totalCostRechargeAmount;
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
    @ApiModelProperty(value = "累计李子贝")
    private Long totalPlumCoin;
    @ApiModelProperty(value = "累计使用李子贝")
    private Long usedPlumCoin;
    @ApiModelProperty(value = "剩余李子贝")
    private Long currentPlumCoin;
    @ApiModelProperty(value = "优惠券数")
    private Integer couponNum;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "userId")
    private Long userId;

    /**
     * VIP信息，会员资格到期时间
     */
    @ApiModelProperty(value = "会员资格到期时间")
    private Date membershipExpiredTime;

    /**
     * VIP信息，会员资格是否到期
     */
    @ApiModelProperty(value = " 会员资格是否到期 false为到期  true为未到期")
    private Boolean membershipValidStatus;

    /**
     * VIP信息，会员资格剩余天数
     */
    @ApiModelProperty(value = "会员资格剩余天数")
    private Integer membershipDaysRemaining;

    /**
     * 是否新用户
     */
    @ApiModelProperty(value = "是否新用户")
    private boolean newUser;

    public MemberH5StatisticsVO() {
        this.totalCostAmount = 0L;
        this.totalCredit = 0L;
        this.totalUseCredit = 0L;
        this.currentCredit = 0L;
        this.currentAmount = 0L;
        this.totalRechargeAmount = 0L;
        this.totalCostRechargeAmount = 0L;
        this.rechargeTimes = 0L;
        this.costTimes = 0L;
        this.totalPlumCoin = 0L;
        this.usedPlumCoin = 0L;
        this.currentPlumCoin = 0L;
        this.couponNum = 0;
        this.membershipValidStatus = false;
        this.membershipDaysRemaining = 0;
    }

    public Long getMerchantMemberId() {
        return merchantMemberId;
    }

    public void setMerchantMemberId(Long merchantMemberId) {
        this.merchantMemberId = merchantMemberId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Long getTotalCostAmount() {
        return totalCostAmount;
    }

    public void setTotalCostAmount(Long totalCostAmount) {
        this.totalCostAmount = totalCostAmount;
    }

    public Long getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(Long totalCredit) {
        this.totalCredit = totalCredit;
    }

    public Long getTotalUseCredit() {
        return totalUseCredit;
    }

    public void setTotalUseCredit(Long totalUseCredit) {
        this.totalUseCredit = totalUseCredit;
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

    public Long getTotalRechargeAmount() {
        return totalRechargeAmount;
    }

    public void setTotalRechargeAmount(Long totalRechargeAmount) {
        this.totalRechargeAmount = totalRechargeAmount;
    }

    public Long getTotalCostRechargeAmount() {
        return totalCostRechargeAmount;
    }

    public void setTotalCostRechargeAmount(Long totalCostRechargeAmount) {
        this.totalCostRechargeAmount = totalCostRechargeAmount;
    }

    public Long getRechargeTimes() {
        return rechargeTimes;
    }

    public void setRechargeTimes(Long rechargeTimes) {
        this.rechargeTimes = rechargeTimes;
    }

    public Long getCostTimes() {
        return costTimes;
    }

    public void setCostTimes(Long costTimes) {
        this.costTimes = costTimes;
    }

    public Long getTotalPlumCoin() {
        return totalPlumCoin;
    }

    public void setTotalPlumCoin(Long totalPlumCoin) {
        this.totalPlumCoin = totalPlumCoin;
    }

    public Long getUsedPlumCoin() {
        return usedPlumCoin;
    }

    public void setUsedPlumCoin(Long usedPlumCoin) {
        this.usedPlumCoin = usedPlumCoin;
    }

    public Long getCurrentPlumCoin() {
        return currentPlumCoin;
    }

    public void setCurrentPlumCoin(Long currentPlumCoin) {
        this.currentPlumCoin = currentPlumCoin;
    }

    public Integer getCouponNum() {
        return couponNum;
    }

    public void setCouponNum(Integer couponNum) {
        this.couponNum = couponNum;
    }

    public Long getMerchantLogoPicId() {
        return merchantLogoPicId;
    }

    public void setMerchantLogoPicId(Long merchantLogoPicId) {
        this.merchantLogoPicId = merchantLogoPicId;
    }

    public Integer getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(Integer levelCode) {
        this.levelCode = levelCode;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getMembershipExpiredTime() {
		return membershipExpiredTime;
	}

	public void setMembershipExpiredTime(Date membershipExpiredTime) {
		this.membershipExpiredTime = membershipExpiredTime;
	}

	public Boolean getMembershipValidStatus() {
		return membershipValidStatus;
	}

	public void setMembershipValidStatus(Boolean membershipValidStatus) {
		this.membershipValidStatus = membershipValidStatus;
	}

	public Integer getMembershipDaysRemaining() {
		return membershipDaysRemaining;
	}

	public void setMembershipDaysRemaining(Integer membershipDaysRemaining) {
		this.membershipDaysRemaining = membershipDaysRemaining;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean isNewUser() {
		return newUser;
	}

	public void setNewUser(boolean newUser) {
		this.newUser = newUser;
	}
}
