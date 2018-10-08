package com.lizikj.api.vo.member;

import com.lizikj.common.enums.LmwMemberLevelEnum;
import com.lizikj.common.enums.RecommendSourceEnum;
import com.lizikj.common.enums.UserSourceSceneEnum;
import com.lizikj.common.enums.UserSourceSceneTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhoufe
 * @date 2018/8/2 15:48
 */
@ApiModel
public class LmwMemberStatisticsVO implements Serializable {

    /**
     * 会员手机号：13800138000
     */
    @ApiModelProperty(value = "会员手机号")
    private String mobile;
    /**
     * 平台会员ID：12313123
     */
    @ApiModelProperty(value = "平台会员ID")
    private Long memberId;
    /**
     * 第三方会员ID：12313123
     */
    @ApiModelProperty(value = "第三方会员ID")
    private Long userId;
    /**
     * 注册时间：2018.09.09 14:14:12
     */
    @ApiModelProperty(value = "注册时间")
    private Date createTime;
    /**
     * 注册来源：撩美味H5/app/小程序/...
     */
    @ApiModelProperty(value = "注册来源：UserSourceSceneEnum：H5.H5，HCH_SQUARE_QRCODE.花城汇撩美味二维码，MCH_DESK_QRCODE.商户桌台二维码，BEFROE_PAY_QRCODE.结账前分享二维码，BEFROE_AFTER_QRCODE.结账后分享二维码 ")
    private UserSourceSceneEnum userSourceScene;

    /**
     * 注册来源大类：撩美味H5/app/小程序/...
     */
    @ApiModelProperty(value = "注册来源大类：UserSourceSceneTypeEnum： H5.H5，LMW.撩美味。")
    private UserSourceSceneTypeEnum userSourceSceneType;

    /**
     * 推荐来源 0.无，1.好友结账前邀请，2.好友结账后邀请，3.被邀请
     */
    @ApiModelProperty(value = "推荐来源见RecommendSourceEnum：NORMAL.无，BEFORE_PAY_SHARE.好友结账前邀请，AFTER_PAY_SHARE.好友结账后邀请，IVITE_FRIEND.被邀请。")
    private RecommendSourceEnum recommendSource;
    /**
     * 会员等级：VIP会员
     */
    @ApiModelProperty(value = "会员等级")
    private LmwMemberLevelEnum lmwMemberLevel;
    /**
     * VIP到期日：2018.09.09
     */
    @ApiModelProperty(value = "VIP到期日")
    private Date membershipExpiredTime;
    /**
     * 最后一次充值时间：2018.09.09 14:14:12
     */
    @ApiModelProperty(value = "最后一次充值时间")
    private Date lastPurchaseDate;


    @ApiModelProperty(value = "第一次开通时间：显示时候用")
    private Date purchaseDate;

    @ApiModelProperty(value = "开通开始时间：查询时候用")
    private Date purchaseStartDate;

    @ApiModelProperty(value = "开通结束时间：查询时候用")
    private Date purchaseEndDate;

    /**
     * 开通次数：4 次
     */
    @ApiModelProperty(value = "购买次数")
    private Integer purchaseTimes;
    /**
     * 可用券：2 张
     */
    @ApiModelProperty(value = "可用券")
    private Integer availableCouponTotal;
    /**
     * 已用券：34 张
     */
    @ApiModelProperty(value = "已用券")
    private Integer usedCouponTotal;

    /**
     * 可用红包：2 张
     */
    @ApiModelProperty(value = "可用红包")
    private Integer availableRedPacketTotal;
    /**
     * 已用红包：34 张
     */
    @ApiModelProperty(value = "已用红包")
    private Integer usedRedPacketTotal;

    /**
     * 邀请注册：否
     */
    @ApiModelProperty(value = "是否邀请注册")
    private Boolean recommendStatus;
    /**
     * 邀请人会员id：1313123
     */
    @ApiModelProperty(value = "邀请人会员ID")
    private Long recommendUserId;
    /**
     * 积累积分：313
     */
    @ApiModelProperty(value = "积累积分")
    private Long totalCredit;
    /**
     * 剩余积分：313
     */
    @ApiModelProperty(value = "剩余积分")
    private Long currentCredit;
    /**
     * 剩余金额
     */
    @ApiModelProperty(value = "剩余金额")
    private Long currentAmount;
    /**
     * 总消费金额
     */
    @ApiModelProperty(value = "总消费金额")
    private Long totalCostAmount;
    /**
     * 总优惠金额：￥312.21
     */
    @ApiModelProperty(value = "总优惠金额")
    private Long totalDiscountAmount;

    @ApiModelProperty(value = "页码")
    private Integer pageNum;

    @ApiModelProperty(value = "页大小")
    private Integer pageSize;


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public UserSourceSceneEnum getUserSourceScene() {
        return userSourceScene;
    }

    public void setUserSourceScene(UserSourceSceneEnum userSourceScene) {
        this.userSourceScene = userSourceScene;
    }

    public LmwMemberLevelEnum getLmwMemberLevel() {
        return lmwMemberLevel;
    }

    public void setLmwMemberLevel(LmwMemberLevelEnum lmwMemberLevel) {
        this.lmwMemberLevel = lmwMemberLevel;
    }

    public Date getMembershipExpiredTime() {
        return membershipExpiredTime;
    }

    public void setMembershipExpiredTime(Date membershipExpiredTime) {
        this.membershipExpiredTime = membershipExpiredTime;
    }

    public Date getLastPurchaseDate() {
        return lastPurchaseDate;
    }

    public void setLastPurchaseDate(Date lastPurchaseDate) {
        this.lastPurchaseDate = lastPurchaseDate;
    }

    public Integer getPurchaseTimes() {
        return purchaseTimes;
    }

    public void setPurchaseTimes(Integer purchaseTimes) {
        this.purchaseTimes = purchaseTimes;
    }

    public Integer getAvailableCouponTotal() {
        return availableCouponTotal;
    }

    public void setAvailableCouponTotal(Integer availableCouponTotal) {
        this.availableCouponTotal = availableCouponTotal;
    }

    public Integer getUsedCouponTotal() {
        return usedCouponTotal;
    }

    public void setUsedCouponTotal(Integer usedCouponTotal) {
        this.usedCouponTotal = usedCouponTotal;
    }

    public Boolean getRecommendStatus() {
        return recommendStatus;
    }

    public void setRecommendStatus(Boolean recommendStatus) {
        this.recommendStatus = recommendStatus;
    }

    public Long getRecommendUserId() {
        return recommendUserId;
    }

    public void setRecommendUserId(Long recommendUserId) {
        this.recommendUserId = recommendUserId;
    }

    public Long getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(Long totalCredit) {
        this.totalCredit = totalCredit;
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

    public Long getTotalCostAmount() {
        return totalCostAmount;
    }

    public void setTotalCostAmount(Long totalCostAmount) {
        this.totalCostAmount = totalCostAmount;
    }

    public Long getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public void setTotalDiscountAmount(Long totalDiscountAmount) {
        this.totalDiscountAmount = totalDiscountAmount;
    }

    public Date getPurchaseStartDate() {
        return purchaseStartDate;
    }

    public void setPurchaseStartDate(Date purchaseStartDate) {
        this.purchaseStartDate = purchaseStartDate;
    }

    public Date getPurchaseEndDate() {
        return purchaseEndDate;
    }

    public void setPurchaseEndDate(Date purchaseEndDate) {
        this.purchaseEndDate = purchaseEndDate;
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

    public UserSourceSceneTypeEnum getUserSourceSceneType() {
        return userSourceSceneType;
    }

    public void setUserSourceSceneType(UserSourceSceneTypeEnum userSourceSceneType) {
        this.userSourceSceneType = userSourceSceneType;
    }

    public Integer getAvailableRedPacketTotal() {
        return availableRedPacketTotal;
    }

    public void setAvailableRedPacketTotal(Integer availableRedPacketTotal) {
        this.availableRedPacketTotal = availableRedPacketTotal;
    }

    public Integer getUsedRedPacketTotal() {
        return usedRedPacketTotal;
    }

    public void setUsedRedPacketTotal(Integer usedRedPacketTotal) {
        this.usedRedPacketTotal = usedRedPacketTotal;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public RecommendSourceEnum getRecommendSource() {
        return recommendSource;
    }

    public void setRecommendSource(RecommendSourceEnum recommendSource) {
        this.recommendSource = recommendSource;
    }
}
