package com.lizikj.api.vo.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * Created by liangxiaolin on 2017/9/13.
 */
@ApiModel
public class MemberInfoVO {
    /**
     * 会员id
     */
    @ApiModelProperty(value = "会员id")
    private Long memberId;
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
    @ApiModelProperty(value = "出生年份")
    private String birthYear;
    /**
     * 出生月日
     */
    @ApiModelProperty(value = "出生月日")
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
     * 注册商户数
     */
    @ApiModelProperty(value = "注册商户数")
    private Integer registerMerchantNum;
    /**
     * 李子会员在商户的等级
     */
    @ApiModelProperty(value = "李子会员在商户的等级")
    List<MemberLevelInfoVO> memberLevelInfoVOS;

    /**
     * 会员资格到期时间
     */
    @ApiModelProperty(value = "会员资格到期时间")
    private Date membershipExpiredTime;

    /**
     * 会员资格是否到期
     */
    @ApiModelProperty(value = " 会员资格是否到期")
    private Boolean membershipValidStatus;

    /**
     * 会员资格剩余天数
     */
    @ApiModelProperty(value = "会员资格剩余天数")
    private Integer membershipDaysRemaining;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getRegisterMerchantNum() {
        return registerMerchantNum;
    }

    public void setRegisterMerchantNum(Integer registerMerchantNum) {
        this.registerMerchantNum = registerMerchantNum;
    }

    public List<MemberLevelInfoVO> getMemberLevelInfoVOS() {
        return memberLevelInfoVOS;
    }

    public void setMemberLevelInfoVOS(List<MemberLevelInfoVO> memberLevelInfoVOS) {
        this.memberLevelInfoVOS = memberLevelInfoVOS;
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

    @Override
    public String toString() {
        return "MemberInfoVO{" +
                "memberId=" + memberId +
                ", realName='" + realName + '\'' +
                ", emailAdd='" + emailAdd + '\'' +
                ", nickName='" + nickName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", sex=" + sex +
                ", birthYear='" + birthYear + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", age=" + age +
                ", idCard='" + idCard + '\'' +
                ", registerMerchantNum=" + registerMerchantNum +
                ", memberLevelInfoVOS=" + memberLevelInfoVOS +
                '}';
    }
}
