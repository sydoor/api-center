package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by liangxiaolin on 2017/9/28.
 */
@ApiModel
public class ValueAddServiceStatisticVO {
    @ApiModelProperty(value = "服务类型 （1年 2年）0为汇总数据")
    private BigDecimal agentChargeModeRuleType;
    /**
     * 当前开通服务店铺数
     */
    @ApiModelProperty(value = "当前开通服务店铺数")
    private Integer currentServiceShopNum;
    /**
     * 当前续费店铺数
     */
    @ApiModelProperty(value = "当前续费店铺数")
    private Integer currentServiceRenewShopNum;
    /**
     * 新增服务开通店铺数
     */
    @ApiModelProperty(value = "新增服务开通店铺数")
    private Integer serviceIncreaseShopNum;
    /**
     * 新增续费店铺数
     */
    @ApiModelProperty(value = "新增续费店铺数")
    private Integer serviceRenewIncreaseNum;
    /**
     * 停用店铺数
     */
    @ApiModelProperty(value = "停用店铺数")
    private Integer serviceBlockUpNum;
    /**
     * 当前停用店铺数
     */
    @ApiModelProperty(value = "当前停用店铺数")
    private Integer currentServiceBlockUpNum;
    /**
     * 当前服务总费用
     */
    @ApiModelProperty(value = "当前服务总费用")
    private Integer currentServiceTotalAmount;
    /**
     * 新增服务费
     */
    @ApiModelProperty(value = "新增服务费")
    private Integer serviceIncreaseAmount;


    public ValueAddServiceStatisticVO() {
        this.currentServiceShopNum = 0;
        this.currentServiceRenewShopNum = 0;
        this.serviceIncreaseShopNum = 0;
        this.serviceRenewIncreaseNum = 0;
        this.serviceBlockUpNum = 0;
        this.currentServiceBlockUpNum = 0;
        this.currentServiceTotalAmount = 0;
        this.serviceIncreaseAmount = 0;
    }

    public ValueAddServiceStatisticVO add(ValueAddServiceStatisticVO valueAddServiceStatisticVO){
        if(valueAddServiceStatisticVO == null)
            return this;

        this.currentServiceShopNum = this.currentServiceShopNum + valueAddServiceStatisticVO.getCurrentServiceShopNum();
        this.currentServiceRenewShopNum = this.currentServiceRenewShopNum + valueAddServiceStatisticVO.getCurrentServiceRenewShopNum();
        this.serviceIncreaseShopNum = this.serviceIncreaseShopNum + valueAddServiceStatisticVO.getServiceIncreaseShopNum();
        this.serviceRenewIncreaseNum = this.serviceRenewIncreaseNum + valueAddServiceStatisticVO.getServiceRenewIncreaseNum();
        this.serviceBlockUpNum = this.serviceBlockUpNum + valueAddServiceStatisticVO.getServiceBlockUpNum();
        this.currentServiceBlockUpNum = this.currentServiceBlockUpNum + valueAddServiceStatisticVO.getCurrentServiceBlockUpNum();
        this.currentServiceTotalAmount = this.currentServiceTotalAmount + valueAddServiceStatisticVO.getCurrentServiceTotalAmount();
        this.serviceIncreaseAmount = this.serviceIncreaseAmount + valueAddServiceStatisticVO.getServiceIncreaseAmount();

        return this;
    }


    public Integer getCurrentServiceShopNum() {
        return currentServiceShopNum;
    }

    public void setCurrentServiceShopNum(Integer currentServiceShopNum) {
        this.currentServiceShopNum = currentServiceShopNum;
    }

    public Integer getCurrentServiceRenewShopNum() {
        return currentServiceRenewShopNum;
    }

    public void setCurrentServiceRenewShopNum(Integer currentServiceRenewShopNum) {
        this.currentServiceRenewShopNum = currentServiceRenewShopNum;
    }

    public Integer getServiceIncreaseShopNum() {
        return serviceIncreaseShopNum;
    }

    public void setServiceIncreaseShopNum(Integer serviceIncreaseShopNum) {
        this.serviceIncreaseShopNum = serviceIncreaseShopNum;
    }

    public Integer getServiceRenewIncreaseNum() {
        return serviceRenewIncreaseNum;
    }

    public void setServiceRenewIncreaseNum(Integer serviceRenewIncreaseNum) {
        this.serviceRenewIncreaseNum = serviceRenewIncreaseNum;
    }

    public Integer getServiceBlockUpNum() {
        return serviceBlockUpNum;
    }

    public void setServiceBlockUpNum(Integer serviceBlockUpNum) {
        this.serviceBlockUpNum = serviceBlockUpNum;
    }

    public Integer getCurrentServiceBlockUpNum() {
        return currentServiceBlockUpNum;
    }

    public void setCurrentServiceBlockUpNum(Integer currentServiceBlockUpNum) {
        this.currentServiceBlockUpNum = currentServiceBlockUpNum;
    }

    public Integer getCurrentServiceTotalAmount() {
        return currentServiceTotalAmount;
    }

    public void setCurrentServiceTotalAmount(Integer currentServiceTotalAmount) {
        this.currentServiceTotalAmount = currentServiceTotalAmount;
    }

    public Integer getServiceIncreaseAmount() {
        return serviceIncreaseAmount;
    }

    public void setServiceIncreaseAmount(Integer serviceIncreaseAmount) {
        this.serviceIncreaseAmount = serviceIncreaseAmount;
    }

    public BigDecimal getAgentChargeModeRuleType() {
        return agentChargeModeRuleType;
    }

    public void setAgentChargeModeRuleType(BigDecimal agentChargeModeRuleType) {
        this.agentChargeModeRuleType = agentChargeModeRuleType;
    }
}
