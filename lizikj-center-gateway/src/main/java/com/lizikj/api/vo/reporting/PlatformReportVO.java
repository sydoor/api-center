package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liangxiaolin on 2017/9/18.
 */
@ApiModel
public class PlatformReportVO {
    /**
     * 会员增量
     */
    @ApiModelProperty(value = "会员增量")
    private Integer memberIncreaseNum;
    /**
     * 活跃会员数
     */
    @ApiModelProperty(value = "活跃会员数")
    private Integer memberActiveNum;
    /**
     * 会员总数
     */
    @ApiModelProperty(value = "会员总数")
    private Integer memberTotalNum;
    /**
     * 门店增量
     */
    @ApiModelProperty(value = "门店增量")
    private Integer shopIncreaseNum;
    /**
     * 门店总数
     */
    @ApiModelProperty(value = "门店总数")
    private Integer shopTotalNum;
    /**
     * 服务开通店铺总数
     */
    @ApiModelProperty(value = "服务开通店铺总数")
    private Integer serviceTotalShopNum;
    /**
     * 服务续费店铺总数
     */
    @ApiModelProperty(value = "服务续费店铺总数")
    private Integer serviceRenewShopNum;
    /**
     * 服务开通店铺日增量
     */
    @ApiModelProperty(value = "服务开通店铺日增量")
    private Integer serviceIncreaseShopNum;
    /**
     * 服务续费店铺日增量
     */
    @ApiModelProperty(value = "服务续费店铺日增量")
    private Integer serviceRenewIncreaseNum;
    /**
     * 服务停用店铺数
     */
    @ApiModelProperty(value = "服务停用店铺数")
    private Integer serviceBlockUpNum;
    /**
     * 服务开通总金额
     */
    @ApiModelProperty(value = "服务开通总金额")
    private Integer serviceTotalAmount;
    /**
     * 服务日开通总金额
     */
    @ApiModelProperty(value = "服务日开通总金额")
    private Integer serviceDateAmount;
    /**
     * 服务未开通店铺总数
     */
    @ApiModelProperty(value = "服务未开通店铺总数")
    private Integer notOpenServiceShopNum;

    /**
     * 本月新增门店数
     */
    @ApiModelProperty(value = "本月新增门店数")
    private Integer shopMonthIncreaseNum;
    /**
     * 流水总额
     */
    @ApiModelProperty(value = "流水总额")
    private Long payAmount;
    /**
     * 流水总笔数
     */
    @ApiModelProperty(value = "流水总笔数")
    private Integer payNum;

    public Integer getMemberIncreaseNum() {
        return memberIncreaseNum;
    }

    public void setMemberIncreaseNum(Integer memberIncreaseNum) {
        this.memberIncreaseNum = memberIncreaseNum;
    }

    public Integer getMemberActiveNum() {
        return memberActiveNum;
    }

    public void setMemberActiveNum(Integer memberActiveNum) {
        this.memberActiveNum = memberActiveNum;
    }

    public Integer getMemberTotalNum() {
        return memberTotalNum;
    }

    public void setMemberTotalNum(Integer memberTotalNum) {
        this.memberTotalNum = memberTotalNum;
    }

    public Integer getShopIncreaseNum() {
        return shopIncreaseNum;
    }

    public void setShopIncreaseNum(Integer shopIncreaseNum) {
        this.shopIncreaseNum = shopIncreaseNum;
    }

    public Integer getShopTotalNum() {
        return shopTotalNum;
    }

    public void setShopTotalNum(Integer shopTotalNum) {
        this.shopTotalNum = shopTotalNum;
    }

    public Integer getServiceTotalShopNum() {
        return serviceTotalShopNum;
    }

    public void setServiceTotalShopNum(Integer serviceTotalShopNum) {
        this.serviceTotalShopNum = serviceTotalShopNum;
    }

    public Integer getServiceRenewShopNum() {
        return serviceRenewShopNum;
    }

    public void setServiceRenewShopNum(Integer serviceRenewShopNum) {
        this.serviceRenewShopNum = serviceRenewShopNum;
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

    public Integer getServiceTotalAmount() {
        return serviceTotalAmount;
    }

    public void setServiceTotalAmount(Integer serviceTotalAmount) {
        this.serviceTotalAmount = serviceTotalAmount;
    }

    public Integer getServiceDateAmount() {
        return serviceDateAmount;
    }

    public void setServiceDateAmount(Integer serviceDateAmount) {
        this.serviceDateAmount = serviceDateAmount;
    }

    public Integer getNotOpenServiceShopNum() {
        return notOpenServiceShopNum;
    }

    public void setNotOpenServiceShopNum(Integer notOpenServiceShopNum) {
        this.notOpenServiceShopNum = notOpenServiceShopNum;
    }

    public Integer getShopMonthIncreaseNum() {
        return shopMonthIncreaseNum;
    }

    public void setShopMonthIncreaseNum(Integer shopMonthIncreaseNum) {
        this.shopMonthIncreaseNum = shopMonthIncreaseNum;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getPayNum() {
        return payNum;
    }

    public void setPayNum(Integer payNum) {
        this.payNum = payNum;
    }
}
