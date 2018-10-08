package com.lizikj.api.vo.member;

import java.util.Date;

import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.member.dto.MerchantMemberStatisticsDTO;
import com.lizikj.member.enums.MemberDiscountTypeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liangxiaolin on 2017/8/5.
 */
@ApiModel
public class MerchantMemberStatisticsVO {
    /**
     * 商户会员ID
     */
    @ApiModelProperty(value = "商户会员ID")
    private Long merchantMemberId;

    /**
     * 会员基础信息id
     */
    @ApiModelProperty(value = "会员基础信息id")
    private Long memberId;

    /**
     * 商户id
     */
    @ApiModelProperty(value = "商户id")
    private Long merchantId;

    @ApiModelProperty(value = "首次推荐所属店铺id")
    private Long sourceShopId;
    /**
     * 首次推荐所属店铺Name
     */
    @ApiModelProperty(value = "首次推荐所属店铺Name")
    private String sourceShopName;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 用户真实姓名
     */
    @ApiModelProperty(value = "用户真实姓名")
    private String realName;
    /**
     * 性别 0女 1男
     */
    @ApiModelProperty(value = "性别 0女 1男")
    private Integer sex;
    /**
     * 出生年份
     */
    @ApiModelProperty(value = "出生年份 YYYY")
    private String birthYear;
    /**
     * 出生月日
     */
    @ApiModelProperty(value = "出生月日 MM-DD")
    private String birthDate;
    /**
     * 会员卡号
     */
    @ApiModelProperty(value = "会员卡号")
    private String  memberNum;

    /**
     * 会员等级
     */
    @ApiModelProperty(value =  "会员等级")
    private Integer levelCode;
    /**
     * 会员等级名称
     */
    @ApiModelProperty(value =  "会员等级名称")
    private String levelName;
    /**
     * 折扣
     */
    @ApiModelProperty(value = "折扣")
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
     * 冻结积分
     */
    @ApiModelProperty(value = "冻结积分")
    private Long frozenCredit;
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
     * 冻结金额
     */
    @ApiModelProperty(value = "冻结金额（分）")
    private Long frozenAmount;
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
    @ApiModelProperty(value = "会员优惠方式设置")
    private MemberDiscountTypeEnum discountTypeEnum;
    @ApiModelProperty(value = "优惠券数")
    private Integer couponNum;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
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

    public Long getFrozenCredit() {
        return frozenCredit;
    }

    public void setFrozenCredit(Long frozenCredit) {
        this.frozenCredit = frozenCredit;
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

    public Long getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(Long frozenAmount) {
        this.frozenAmount = frozenAmount;
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

    public Long getSourceShopId() {
        return sourceShopId;
    }

    public void setSourceShopId(Long sourceShopId) {
        this.sourceShopId = sourceShopId;
    }

    public String getSourceShopName() {
        return sourceShopName;
    }

    public void setSourceShopName(String sourceShopName) {
        this.sourceShopName = sourceShopName;
    }

    public MemberDiscountTypeEnum getDiscountTypeEnum() {
        return discountTypeEnum;
    }

    public void setDiscountTypeEnum(MemberDiscountTypeEnum discountTypeEnum) {
        this.discountTypeEnum = discountTypeEnum;
    }

    public Integer getCouponNum() {
        return couponNum;
    }

    public void setCouponNum(Integer couponNum) {
        this.couponNum = couponNum;
    }

    public static MerchantMemberStatisticsVO from(MerchantMemberStatisticsDTO dto){
        MerchantMemberStatisticsVO merchantMemberStatisticsVO = ObjectConvertUtil.copyProperties(MerchantMemberStatisticsVO.class,dto);
        merchantMemberStatisticsVO.setCurrentCredit(
                Math.max(0,merchantMemberStatisticsVO.getTotalCredit() - merchantMemberStatisticsVO.getFrozenCredit() - merchantMemberStatisticsVO.getTotalUseCredit()));
        merchantMemberStatisticsVO.setCurrentAmount(
                Math.max(0,merchantMemberStatisticsVO.getTotalRechargeAmount() - merchantMemberStatisticsVO.getFrozenAmount() - merchantMemberStatisticsVO.getTotalCostRechargeAmount()));

        merchantMemberStatisticsVO.setCouponNum(0);
        return merchantMemberStatisticsVO;
    }
}
