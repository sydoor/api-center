package com.lizikj.api.vo.marketing.coupon;

import com.lizikj.marketing.api.enums.ConditionCheckStatusEnum;
import com.lizikj.marketing.api.enums.ConditionTypeEnum;
import com.lizikj.marketing.api.enums.PrizeTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lxl
 * @Date 2018/7/31 11:02
 */
@ApiModel(value = "激活条件和用户信息")
public class ConditionAndUserVO {
    @ApiModelProperty(value = "激活条件ID")
    private Long condId;
    @ApiModelProperty(value = "条件目标类型")
    private PrizeTypeEnum prizeTypeEnum;
    @ApiModelProperty(value = "条件类型")
    private ConditionTypeEnum condTypeEnum;
    @ApiModelProperty(value = "当前卡券领取码")
    private String targetCode;
    @ApiModelProperty(value = "当前卡券来源值")
    private String condValue;
    @ApiModelProperty(value = "当前卡券归属用户ID")
    private Long targetUserId;
    @ApiModelProperty(value = "当前卡券归属用户名称")
    private String targetUserName;
    @ApiModelProperty(value = "当前卡券来源用户ID")
    private Long parenUserId;
    @ApiModelProperty(value = "当前卡券来源用户名称")
    private String parentUserName;
    @ApiModelProperty(value = "是否检验通过")
    private ConditionCheckStatusEnum checkStatusEnum;

    public Long getCondId() {
        return condId;
    }

    public void setCondId(Long condId) {
        this.condId = condId;
    }

    public PrizeTypeEnum getPrizeTypeEnum() {
        return prizeTypeEnum;
    }

    public void setPrizeTypeEnum(PrizeTypeEnum prizeTypeEnum) {
        this.prizeTypeEnum = prizeTypeEnum;
    }

    public ConditionTypeEnum getCondTypeEnum() {
        return condTypeEnum;
    }

    public void setCondTypeEnum(ConditionTypeEnum condTypeEnum) {
        this.condTypeEnum = condTypeEnum;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode;
    }

    public String getCondValue() {
        return condValue;
    }

    public void setCondValue(String condValue) {
        this.condValue = condValue;
    }

    public Long getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(Long targetUserId) {
        this.targetUserId = targetUserId;
    }

    public String getTargetUserName() {
        return targetUserName;
    }

    public void setTargetUserName(String targetUserName) {
        this.targetUserName = targetUserName;
    }

    public Long getParenUserId() {
        return parenUserId;
    }

    public void setParenUserId(Long parenUserId) {
        this.parenUserId = parenUserId;
    }

    public String getParentUserName() {
        return parentUserName;
    }

    public void setParentUserName(String parentUserName) {
        this.parentUserName = parentUserName;
    }

    public ConditionCheckStatusEnum getCheckStatusEnum() {
        return checkStatusEnum;
    }

    public void setCheckStatusEnum(ConditionCheckStatusEnum checkStatusEnum) {
        this.checkStatusEnum = checkStatusEnum;
    }
}
