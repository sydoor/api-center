package com.lizikj.api.vo.member;

import com.lizikj.api.enums.MemberRoleEnum;
import com.lizikj.member.enums.MemberDiscountTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 商户会员
 * Created by liangxiaolin on 2017/8/5.
 */
@ApiModel
public class MerchantMemberVO {
    /**
     * 会员id
     */
    @ApiModelProperty(value = "会员id")
    private Long memberId;

    /**
     * 商户id
     */
    @ApiModelProperty(value = "商户id")
    private Long merchantId;

    /**
     * 用户真实姓名
     */
    @ApiModelProperty(value = "用户真实姓名")
    private String realName;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String emailAdd;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;

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
     * 年龄
     */
    @ApiModelProperty(value = "年龄")
    private int age;
    /**
     * 身份证
     */
    @ApiModelProperty(value = "身份证")
    private String idCard;
    /**
     * 会员id
     */
    @ApiModelProperty(value = "会员id")
    private Long merchantMemberId;
    /**
     * 股东会员ID
     */
    @ApiModelProperty(value = "股东会员ID")
    private Long tenderMemberId;
    /**
     * 登录账号
     */
    @ApiModelProperty(value = "登录账号")
    private String loginAccount;
    /**
     * 首次推荐所属店铺id
     */
    @ApiModelProperty(value = "首次推荐所属店铺id")
    private Long sourceShopId;
    /**
     * 首次推荐所属店铺Name
     */
    @ApiModelProperty(value = "首次推荐所属店铺Name")
    private String sourceShopName;
    /**
     * 推荐服务员姓名
     */
    @ApiModelProperty(value = "推荐服务员姓名")
    private String waiterName;

    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 推荐服务员用户ID
     */
    @ApiModelProperty(value = "推荐服务员姓名")
    private Long waiterUserId;
    /**
     * 首次推荐人id
     */
    @ApiModelProperty(value = "首次会员id")
    private Long introducerMemberId;
    /**
     * 首次推荐人手机号
     */
    @ApiModelProperty(value = "首次推荐人手机号")
    private String introducerMobile;
    /**
     * 会员来源类型 21100服务员登记 21200用户注册
     */
    @ApiModelProperty(value = "会员来源类型 21100服务员登记 21200用户注册")
    private Integer sourceType;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;
    /**
     * 会员卡号
     */
    @ApiModelProperty(value = "会员卡号")
    private String memberNum;

    /**
     * 等级名
     */
    @ApiModelProperty(value = "等级名")
    private String levelName;
    @ApiModelProperty(value = "等级")
    private Integer levelCode;

    /**
     * 注册时间createDate
     */
    @ApiModelProperty(value = "注册时间registerDate")
    private Date registerDate;

    /**
     * 会员折扣
     */
    @ApiModelProperty(value = "会员折扣")
    private Double discount;

    @ApiModelProperty(value = "会员余额")
    private Long currentAmount;

    private MemberDiscountTypeEnum memberDiscountTypeEnum;

    @ApiModelProperty(value = "会员角色（商户、股东）")
    private MemberRoleEnum memberRoleEnum;
    
    @ApiModelProperty(value = "优惠券数量")
    private Integer couponNum;

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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmailAdd() {
        return emailAdd;
    }

    public void setEmailAdd(String emailAdd) {
        this.emailAdd = emailAdd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Long getMerchantMemberId() {
        return merchantMemberId;
    }

    public void setMerchantMemberId(Long merchantMemberId) {
        this.merchantMemberId = merchantMemberId;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public Long getSourceShopId() {
        return sourceShopId;
    }

    public void setSourceShopId(Long sourceShopId) {
        this.sourceShopId = sourceShopId;
    }

    public String getWaiterName() {
        return waiterName;
    }

    public void setWaiterName(String waiterName) {
        this.waiterName = waiterName;
    }


    public String getIntroducerMobile() {
        return introducerMobile;
    }

    public void setIntroducerMobile(String introducerMobile) {
        this.introducerMobile = introducerMobile;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getWaiterUserId() {
        return waiterUserId;
    }

    public void setWaiterUserId(Long waiterUserId) {
        this.waiterUserId = waiterUserId;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSourceShopName() {
        return sourceShopName;
    }

    public void setSourceShopName(String sourceShopName) {
        this.sourceShopName = sourceShopName;
    }

    public Integer getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(Integer levelCode) {
        this.levelCode = levelCode;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public MemberDiscountTypeEnum getMemberDiscountTypeEnum() {
        return memberDiscountTypeEnum;
    }

    public void setMemberDiscountTypeEnum(MemberDiscountTypeEnum memberDiscountTypeEnum) {
        this.memberDiscountTypeEnum = memberDiscountTypeEnum;
    }

    public Long getIntroducerMemberId() {
        return introducerMemberId;
    }

    public void setIntroducerMemberId(Long introducerMemberId) {
        this.introducerMemberId = introducerMemberId;
    }

    public Long getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Long currentAmount) {
        this.currentAmount = currentAmount;
    }

    public MemberRoleEnum getMemberRoleEnum() {
        return memberRoleEnum;
    }

    public void setMemberRoleEnum(MemberRoleEnum memberRoleEnum) {
        this.memberRoleEnum = memberRoleEnum;
    }

    public Long getTenderMemberId() {
        return tenderMemberId;
    }

    public void setTenderMemberId(Long tenderMemberId) {
        this.tenderMemberId = tenderMemberId;
    }

	public Integer getCouponNum() {
		return couponNum;
	}

	public void setCouponNum(Integer couponNum) {
		this.couponNum = couponNum;
	}
}
