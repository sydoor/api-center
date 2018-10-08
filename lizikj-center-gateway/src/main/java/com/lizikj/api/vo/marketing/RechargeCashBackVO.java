package com.lizikj.api.vo.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lxl
 * @Date 2018/5/17 14:16
 */
@ApiModel
public class RechargeCashBackVO {
    /**
     * 金额
     */
    @ApiModelProperty(value = "充值金额")
    private Long recharge;
    /**
     * 返利金额
     */
    @ApiModelProperty(value = "返利金额")
    private Long rebate;
    /**
     * 店铺ID
     */
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "活动ID")
    private Long activityId;


    public Long getRecharge() {
        return recharge;
    }

    public void setRecharge(Long recharge) {
        this.recharge = recharge;
    }

    public Long getRebate() {
        return rebate;
    }

    public void setRebate(Long rebate) {
        this.rebate = rebate;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
