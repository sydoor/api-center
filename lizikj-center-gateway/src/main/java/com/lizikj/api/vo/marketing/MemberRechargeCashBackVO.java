package com.lizikj.api.vo.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @author lxl
 * @Date 2018/5/2 12:01
 */
@ApiModel(value = "会员充值满送")
public class MemberRechargeCashBackVO {
    @ApiModelProperty(value = "活动ID")
    private Long activityId;

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    /**
     * 开始时间
     */
    @ApiModelProperty(required = true, name = "startTime", value = "开始时间", dataType = "Date")
    private Date startTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(required = true, name = "endTime", value = "结束时间", dataType = "Date")
    private Date endTime;

    @ApiModelProperty(value = "满送键值对")
    private List<RechargeBackKVVO> kvvos;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public List<RechargeBackKVVO> getKvvos() {
        return kvvos;
    }

    public void setKvvos(List<RechargeBackKVVO> kvvos) {
        this.kvvos = kvvos;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    @Override
    public String toString() {
        return "MemberRechargeCashBackVO{" +
                "activityId=" + activityId +
                ", shopId=" + shopId +
                ", merchantId=" + merchantId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", kvvos=" + kvvos +
                '}';
    }
}
