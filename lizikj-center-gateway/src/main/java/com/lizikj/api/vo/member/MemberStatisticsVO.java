package com.lizikj.api.vo.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liangxiaolin on 2017/9/11.
 */
@ApiModel
public class MemberStatisticsVO {
    /**
     * 会员总数
     */
    @ApiModelProperty(value = "会员总数")
    private Integer totalNum;
    /**
     * 新增会员数
     */
    @ApiModelProperty(value = "新增会员数")
    private Integer increaseNum;
    /**
     * 活跃会员数
     */
    @ApiModelProperty(value = "活跃会员数")
    private Integer activeNum;
    /**
     * 会员消费
     */
    @ApiModelProperty(value = "会员消费")
    private Long memberAmount;
    /**
     * 会员优惠
     */
    @ApiModelProperty(value = "会员优惠")
    private Long benefitAmount;
    /**
     * 会员消费占比（预留）
     */
    @ApiModelProperty(value = "会员消费占比")
    private Integer memberAmountRate;
    /**
     * 会员消费次数
     */
    @ApiModelProperty(value = "会员消费次数")
    private Integer consumeNum;

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getIncreaseNum() {
        return increaseNum;
    }

    public void setIncreaseNum(Integer increaseNum) {
        this.increaseNum = increaseNum;
    }

    public Integer getActiveNum() {
        return activeNum;
    }

    public void setActiveNum(Integer activeNum) {
        this.activeNum = activeNum;
    }

    public Long getMemberAmount() {
        return memberAmount;
    }

    public void setMemberAmount(Long memberAmount) {
        this.memberAmount = memberAmount;
    }

    public Long getBenefitAmount() {
        return benefitAmount;
    }

    public void setBenefitAmount(Long benefitAmount) {
        this.benefitAmount = benefitAmount;
    }

    public Integer getMemberAmountRate() {
        return memberAmountRate;
    }

    public void setMemberAmountRate(Integer memberAmountRate) {
        this.memberAmountRate = memberAmountRate;
    }

    public Integer getConsumeNum() {
        return consumeNum;
    }

    public void setConsumeNum(Integer consumeNum) {
        this.consumeNum = consumeNum;
    }

    @Override
    public String toString() {
        return "MemberStatisticsVO{" +
                "totalNum=" + totalNum +
                ", increaseNum=" + increaseNum +
                ", activeNum=" + activeNum +
                ", memberAmount=" + memberAmount +
                ", benefitAmount=" + benefitAmount +
                ", memberAmountRate=" + memberAmountRate +
                ", consumeNum=" + consumeNum +
                '}';
    }
}
