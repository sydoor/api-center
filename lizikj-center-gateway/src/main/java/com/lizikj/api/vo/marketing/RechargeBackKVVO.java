package com.lizikj.api.vo.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lxl
 * @Date 2018/5/2 14:20
 */
@ApiModel(value = "充值满送")
public class RechargeBackKVVO {
    /**
     * 金额
     */
    @ApiModelProperty(value = "充值金额")
    private Long amount;
    /**
     * 返利金额
     */
    @ApiModelProperty(value = "返利金额")
    private Long rebate;

    @ApiModelProperty(value = "活动ID")
    private Long activityId;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getRebate() {
        return rebate;
    }

    public void setRebate(Long rebate) {
        this.rebate = rebate;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
