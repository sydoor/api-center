package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by liangxiaolin on 2017/9/13.
 */
@ApiModel
public class MemberCurrentStatisticVO {
    /**
     * 商户ID
     */
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    /**
     * 商户名称
     */
    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    /**
     * 当前会员总数
     */
    @ApiModelProperty(value = "当前会员总数")
    private Integer totalNum;
    /**
     * 当天新增会员
     */
    @ApiModelProperty(value = "当天新增会员")
    private Integer dateIncreaseNum;
    /**
     * 本周新增会员
     */
    @ApiModelProperty(value = "本周新增会员")
    private Integer weekIncreaseNum;
    /**
     * 当月新增会员
     */
    @ApiModelProperty(value = "当月新增会员")
    private Integer monthIncreaseNum;

    List<MemberCountRateVO> memberCountRateVOS;


    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getDateIncreaseNum() {
        return dateIncreaseNum;
    }

    public void setDateIncreaseNum(Integer dateIncreaseNum) {
        this.dateIncreaseNum = dateIncreaseNum;
    }

    public Integer getWeekIncreaseNum() {
        return weekIncreaseNum;
    }

    public void setWeekIncreaseNum(Integer weekIncreaseNum) {
        this.weekIncreaseNum = weekIncreaseNum;
    }

    public Integer getMonthIncreaseNum() {
        return monthIncreaseNum;
    }

    public void setMonthIncreaseNum(Integer monthIncreaseNum) {
        this.monthIncreaseNum = monthIncreaseNum;
    }

    public List<MemberCountRateVO> getMemberCountRateVOS() {
        return memberCountRateVOS;
    }

    public void setMemberCountRateVOS(List<MemberCountRateVO> memberCountRateVOS) {
        this.memberCountRateVOS = memberCountRateVOS;
    }
}
