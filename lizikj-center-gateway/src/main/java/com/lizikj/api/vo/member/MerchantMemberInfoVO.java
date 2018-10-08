package com.lizikj.api.vo.member;

import java.util.Date;

import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 会员基础信息
 * Created by liangjiankang on 2017-07-06 15:31:33
 */
@ApiModel(value="会员详细资料")
public class MerchantMemberInfoVO extends BaseDTO {
    private static final long serialVersionUID = -6853588240200279089L;
    /**
     * 会员基础信息id
     */
    @ApiModelProperty(value = "会员基础信息id")
    private Long memberId;
    
    /**
     * 商户会员id
     */
    @ApiModelProperty(value = "商户会员id")
    private Long merchantMemberId;

    /**
     * 商户id
     */
    @ApiModelProperty(value = "商户id")
    private Long merchantId;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;

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
	 * 年龄
	 */
	@ApiModelProperty(value = "年龄")
	private int age;

    /**
     * 会员卡号
     */
    @ApiModelProperty(value = "会员卡号")
    private String  memberNum;
    /**
     * 等级，1,2,3,4,5最多5级
     */
    @ApiModelProperty(value = "等级，1,2,3,4,5最多5级")
    private Integer levelCode;

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
     * 注册店铺名称
     */
    @ApiModelProperty(value = "注册店铺名称")
    private String registerShopName;
    /**
     * 会员注册方式
     */
    @ApiModelProperty(value = "会员注册方式")
    private Integer sourceType;

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
	@ApiModelProperty(value = "首次推荐人id")
	private Long introducerUserId;
	/**
	 * 首次推荐人手机号
	 */
	@ApiModelProperty(value = "首次推荐人手机号")
	private String introducerMobile;


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

	public String getRegisterShopName() {
		return registerShopName;
	}

	public void setRegisterShopName(String registerShopName) {
		this.registerShopName = registerShopName;
	}

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
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

	public Long getIntroducerUserId() {
		return introducerUserId;
	}

	public void setIntroducerUserId(Long introducerUserId) {
		this.introducerUserId = introducerUserId;
	}

	public String getIntroducerMobile() {
		return introducerMobile;
	}

	public void setIntroducerMobile(String introducerMobile) {
		this.introducerMobile = introducerMobile;
	}

	public String getWaiterName() {
		return waiterName;
	}

	public void setWaiterName(String waiterName) {
		this.waiterName = waiterName;
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
}