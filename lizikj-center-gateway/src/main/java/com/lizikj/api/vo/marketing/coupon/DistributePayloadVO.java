package com.lizikj.api.vo.marketing.coupon;

import com.lizikj.marketing.api.enums.DistributeModeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lxl
 * @Date 2018/8/3 14:31
 */
@ApiModel(value = "发放规则")
public class DistributePayloadVO {
    @ApiModelProperty(value = "发放规则模式")
    private DistributeModeEnum modeEnum;
    @ApiModelProperty(value = "定额发放数量")
    private Integer fixNum;
    @ApiModelProperty(value = "随机发放概率 1 到 100")
    private Integer randRate;
    @ApiModelProperty(value = "固定发送，新用户额外加赠送,格式：固定数量:新用户赠送数量")
    private String fixAndNewPlus;

    public DistributeModeEnum getModeEnum() {
        return modeEnum;
    }

    public void setModeEnum(DistributeModeEnum modeEnum) {
        this.modeEnum = modeEnum;
    }

    public Integer getFixNum() {
        return fixNum;
    }

    public void setFixNum(Integer fixNum) {
        this.fixNum = fixNum;
    }

    public Integer getRandRate() {
        return randRate;
    }

    public void setRandRate(Integer randRate) {
        this.randRate = randRate;
    }

    public String getFixAndNewPlus() {
        return fixAndNewPlus;
    }

    public void setFixAndNewPlus(String fixAndNewPlus) {
        this.fixAndNewPlus = fixAndNewPlus;
    }
}
