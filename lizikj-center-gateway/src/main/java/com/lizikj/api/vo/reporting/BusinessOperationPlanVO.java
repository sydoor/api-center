package com.lizikj.api.vo.reporting;

import com.lizikj.reporting.enums.BusinessPlanTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @author lxl
 * @Date 2018/1/5 15:52
 */
@ApiModel
public class BusinessOperationPlanVO {
    /**
     * 计划类型
     */
    @ApiModelProperty(value = "计划类型")
    private BusinessPlanTypeEnum planTypeEnum;
    /**
     * 计划时间
     */
    @ApiModelProperty(value = "计划时间")
    private Integer planTime;
    /**
     * 目标营业额
     */
    @ApiModelProperty(value = "目标营业额")
    private Long goalAmount;
    /**
     * 目标笔数
     */
    @ApiModelProperty(value = "目标笔数")
    private Long goalNum;

    public BusinessPlanTypeEnum getPlanTypeEnum() {
        return planTypeEnum;
    }

    public void setPlanTypeEnum(BusinessPlanTypeEnum planTypeEnum) {
        this.planTypeEnum = planTypeEnum;
    }

    public Integer getPlanTime() {
        return planTime;
    }

    public void setPlanTime(Integer planTime) {
        this.planTime = planTime;
    }

    public Long getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(Long goalAmount) {
        this.goalAmount = goalAmount;
    }

    public Long getGoalNum() {
        return goalNum;
    }

    public void setGoalNum(Long goalNum) {
        this.goalNum = goalNum;
    }
}
