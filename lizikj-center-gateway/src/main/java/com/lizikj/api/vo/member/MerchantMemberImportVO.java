package com.lizikj.api.vo.member;

import com.lizikj.member.enums.GenderEnum;
import com.lizikj.member.enums.StatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author lxl
 * @Date 2018/5/3 17:58
 */
@ApiModel
public class MerchantMemberImportVO {
    /**
     * 商户ID
     */
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private GenderEnum genderEnum;
    /**
     * 注册日期
     */
    @ApiModelProperty(value = "注册日期")
    private Date registerDate;
    /**
     * 积分
     */
    @ApiModelProperty(value = "积分")
    private Long credit;
    /**
     * 充值金额
     */
    @ApiModelProperty(value = "充值金额")
    private Long amount;
    /**
     * 返利金额
     */
    @ApiModelProperty(value = "返利金额")
    private Long rebateAmount;
    /**
     * 会员姓名
     */
    @ApiModelProperty(value = "会员姓名")
    private String realName;
    /**
     * 会员卡
     */
    @ApiModelProperty(value = "会员卡")
    private String memberNum;

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    /**
     * 是否成功
     */
    @ApiModelProperty(value = "是否成功")
    private StatusEnum success;

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

    public GenderEnum getGenderEnum() {
        return genderEnum;
    }

    public void setGenderEnum(GenderEnum genderEnum) {
        this.genderEnum = genderEnum;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Long getCredit() {
        return credit;
    }

    public void setCredit(Long credit) {
        this.credit = credit;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getRebateAmount() {
        return rebateAmount;
    }

    public void setRebateAmount(Long rebateAmount) {
        this.rebateAmount = rebateAmount;
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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public StatusEnum getSuccess() {
        return success;
    }

    public void setSuccess(StatusEnum success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "MerchantMemberImportVO{" +
                "merchantId=" + merchantId +
                ", mobile='" + mobile + '\'' +
                ", genderEnum=" + genderEnum +
                ", registerDate=" + registerDate +
                ", credit=" + credit +
                ", amount=" + amount +
                ", rebateAmount=" + rebateAmount +
                ", realName='" + realName + '\'' +
                ", memberNum='" + memberNum + '\'' +
                ", shopId=" + shopId +
                ", success=" + success +
                '}';
    }
}
