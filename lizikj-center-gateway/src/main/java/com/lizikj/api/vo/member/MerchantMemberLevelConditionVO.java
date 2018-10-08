package com.lizikj.api.vo.member;

import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 会员等级条件
 * Created by liangjiankang on 2017-07-06 15:31:33
 */
@ApiModel(value="会员等级条件")
public class MerchantMemberLevelConditionVO {
    /**
     * 条件id
     */
    @ApiModelProperty(value = "条件id")
    private Long conditionId;

    /**
     * 等级id
     */
    @ApiModelProperty(value = "等级id")
    private Long levelId;

    /**
     * 升级条件类型 6100 无  6200 积分 6300消费
     */
    @ApiModelProperty(value = "升级条件类型 6100 无  6200 积分 6300消费")
    private Integer conditionType;

    /**
     * 升级需要值
     */
    @ApiModelProperty(value = "升级需要值")
    private Long needValue;

    /**
     * 商户id
     */
    @ApiModelProperty(value = "商户id")
    private Long merchantId;
    /**
     * 条件id
     */
    public Long getConditionId() {
        return conditionId;
    }

    /**
     * 条件id
     * @param conditionId the value for member_level_condition.condition_id
     */
    public void setConditionId(Long conditionId) {
        this.conditionId = conditionId;
    }

    /**
     * 等级id
     */
    public Long getLevelId() {
        return levelId;
    }

    /**
     * 等级id
     * @param levelId the value for member_level_condition.level_id
     */
    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    /**
     * 升级条件类型 6100 无  6200 积分 6300消费
     */
    public Integer getConditionType() {
        return conditionType;
    }

    /**
     * 升级条件类型 6100 无  6200 积分 6300消费
     * @param conditionType the value for member_level_condition.condition_type
     */
    public void setConditionType(Integer conditionType) {
        this.conditionType = conditionType;
    }

    /**
     * 升级需要值
     */
    public Long getNeedValue() {
        return needValue;
    }

    /**
     * 升级需要值
     * @param needValue the value for member_level_condition.need_value
     */
    public void setNeedValue(Long needValue) {
        this.needValue = needValue;
    }

    /**
     * 商户id
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * 商户id
     * @param merchantId the value for member_level_condition.merchant_id
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

}