package com.lizikj.api.vo.msg;

import com.lizikj.message.api.enums.TerminalTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lxl
 * @Date 2018/1/24 10:16
 */
@ApiModel
public class MsgDeviceReportVO {
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    @ApiModelProperty(value = "设备号")
    private String sn;
    @ApiModelProperty(value = "终端类型")
    private TerminalTypeEnum terminalTypeEnum;
    @ApiModelProperty(value = "连接打印成功率（统计一定时间内，百分比乘以100）")
    private Integer printSuccessRate;
    @ApiModelProperty(value = "推送消息成功")
    private Integer pushSuccessRate;
    @ApiModelProperty(value = "优良值")
    private Integer goodValue;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public TerminalTypeEnum getTerminalTypeEnum() {
        return terminalTypeEnum;
    }

    public void setTerminalTypeEnum(TerminalTypeEnum terminalTypeEnum) {
        this.terminalTypeEnum = terminalTypeEnum;
    }

    public Integer getPrintSuccessRate() {
        return printSuccessRate;
    }

    public void setPrintSuccessRate(Integer printSuccessRate) {
        this.printSuccessRate = printSuccessRate;
    }

    public Integer getPushSuccessRate() {
        return pushSuccessRate;
    }

    public void setPushSuccessRate(Integer pushSuccessRate) {
        this.pushSuccessRate = pushSuccessRate;
    }

    public Integer getGoodValue() {
        return goodValue;
    }

    public void setGoodValue(Integer goodValue) {
        this.goodValue = goodValue;
    }
}
