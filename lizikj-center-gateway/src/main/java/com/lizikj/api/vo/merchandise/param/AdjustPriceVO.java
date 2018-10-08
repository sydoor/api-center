package com.lizikj.api.vo.merchandise.param;

import com.lizikj.merchandise.enums.OperateTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author dyh
 * @created at 2018 01 19 10:09
 */
@ApiModel(value = "美食调价VO")
public class AdjustPriceVO {

    @ApiModelProperty(value = "美食调价方案id")
    private Long planId;

    @ApiModelProperty(value = "调价方案名称")
    private String adjustPricePlanName;

    /**
     * 操作类型
     */
    @ApiModelProperty(value = "操作类型")
    private OperateTypeEnum operateType;

    /**
     * 单位为分
     */
    @ApiModelProperty(value = "调价价格，单位为分")
    private Long amount;

    /**
     * 美食Id列表
     */
    @ApiModelProperty(value = "美食Id列表")
    private List<String> goodsIds;

    /**
     * 重复时间
     */
    @ApiModelProperty(value = "重复时间,周一=1，周二=2,周三=3,周四=4,周五=5,周六=6，周日=7")
    private List<String> dayOfWeeks;

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getAdjustPricePlanName() {
        return adjustPricePlanName;
    }

    public void setAdjustPricePlanName(String adjustPricePlanName) {
        this.adjustPricePlanName = adjustPricePlanName;
    }

    public OperateTypeEnum getOperateType() {
        return operateType;
    }

    public void setOperateType(OperateTypeEnum operateType) {
        this.operateType = operateType;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public List<String> getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(List<String> goodsIds) {
        this.goodsIds = goodsIds;
    }

    public List<String> getDayOfWeeks() {
        return dayOfWeeks;
    }

    public void setDayOfWeeks(List<String> dayOfWeeks) {
        this.dayOfWeeks = dayOfWeeks;
    }
}
