package com.lizikj.api.vo.merchandise;

import com.lizikj.merchandise.enums.OperateTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author dyh
 * @created at 2018 01 19 12:35
 */
@ApiModel(value = "美食调价表达式 VO")
public class ScheduleDiscountVO {

    @ApiModelProperty(value = "美食id")
    private String goodsId;
    @ApiModelProperty(value = "美食调价方案id")
    private Long planId;
    /**
     * 调价方案名称
     */
    @ApiModelProperty(value = "调价方案名称")
    private String adjustPricePlanName;

    /**
     * 触发的日期
     */
    @ApiModelProperty(value = "触发的日期")
    private String cronExpression;

    /**
     * 操作类型
     */
    @ApiModelProperty(value = "操作类型")
    private OperateTypeEnum operateType;

    /**
     * 数值 单位为分
     */
    @ApiModelProperty(value = "数值 单位为分")
    private long amount;

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getAdjustPricePlanName() {
        return adjustPricePlanName;
    }

    public void setAdjustPricePlanName(String adjustPricePlanName) {
        this.adjustPricePlanName = adjustPricePlanName;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public OperateTypeEnum getOperateType() {
        return operateType;
    }

    public void setOperateType(OperateTypeEnum operateType) {
        this.operateType = operateType;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
