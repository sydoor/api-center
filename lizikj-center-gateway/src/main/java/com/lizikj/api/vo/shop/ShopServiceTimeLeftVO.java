package com.lizikj.api.vo.shop;

import com.lizikj.shop.api.enums.ServiceTimeLeftActivatedStatusEnum;
import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author zhoufe
 * @date 2017/7/25 15:36
 */
@ApiModel
public class ShopServiceTimeLeftVO extends BaseDTO {

    private static final long serialVersionUID = 1L;


    /**
     * 服务续费ID
     */
    @ApiModelProperty(value = "服务续费ID", required = true)
    private Long shopServiceTimeLeftId;

    /**
     * 店铺ID
     */
    @ApiModelProperty(value = "店铺ID", required = true)
    private Long shopId;
    @ApiModelProperty(value = "店铺名称")
    private String shopName;
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    /**
     * 代理商ID
     */
    @ApiModelProperty(value = "代理商ID", required = true)
    private Long agentId;

    /**
     * 激活状态
     */
    @ApiModelProperty(value = "激活状态：见ServiceTimeLeftActivatedStatusEnum：TRAIL.配置的天数，ACTIVE.已激活，TIME_OUT.过期", required = true)
    private ServiceTimeLeftActivatedStatusEnum activatedStatus;

    /**
     * 激活时间：激活的时候才能有时间
     */
    @ApiModelProperty(value = "激活时间", required = true)
    private Date activatedDate;

    /**
     * 到期时间
     */
    @ApiModelProperty(value = "过期日期", required = true)
    private Date expiredTime;

    @ApiModelProperty(value = "剩余天数：根据expiredTime后端计算", required = true)
    private Integer daysRemainning;

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }

    public Integer getDaysRemainning() {
        return daysRemainning;
    }

    public void setDaysRemainning(Integer daysRemainning) {
        this.daysRemainning = daysRemainning;
    }

    public Long getShopServiceTimeLeftId() {
        return shopServiceTimeLeftId;
    }

    public void setShopServiceTimeLeftId(Long shopServiceTimeLeftId) {
        this.shopServiceTimeLeftId = shopServiceTimeLeftId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public ServiceTimeLeftActivatedStatusEnum getActivatedStatus() {
        return activatedStatus;
    }

    public void setActivatedStatus(ServiceTimeLeftActivatedStatusEnum activatedStatus) {
        this.activatedStatus = activatedStatus;
    }

    public Date getActivatedDate() {
        return activatedDate;
    }

    public void setActivatedDate(Date activatedDate) {
        this.activatedDate = activatedDate;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

}
