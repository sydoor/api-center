package com.lizikj.api.vo.member;

import com.lizikj.member.enums.GenderEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by liangxiaolin on 2017/9/27.
 */
@ApiModel
public class MerchantMemberQueryVO {
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    @ApiModelProperty(value = "李子会员ID")
    private Long memberId;
    @ApiModelProperty(value = "会员手机号")
    private String mobile;
    @ApiModelProperty(value = "会员等级ID")
    private Long levelId;
    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endTime;
    @ApiModelProperty(value = "推荐人手机号")
    private String introducerMobile;
    @ApiModelProperty(value = "会员名称")
    private String realName;
    @ApiModelProperty(value = "性别")
    private GenderEnum genderEnum;
    @ApiModelProperty(value = "等级")
    private Integer levelCode;


    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
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

    public String getIntroducerMobile() {
        return introducerMobile;
    }

    public void setIntroducerMobile(String introducerMobile) {
        this.introducerMobile = introducerMobile;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public GenderEnum getGenderEnum() {
        return genderEnum;
    }

    public void setGenderEnum(GenderEnum genderEnum) {
        this.genderEnum = genderEnum;
    }

    public Integer getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(Integer levelCode) {
        this.levelCode = levelCode;
    }
}
