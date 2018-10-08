package com.lizikj.api.vo.marketing.coupon;

import com.lizikj.marketing.api.enums.RedPacketStatusEnum;
import com.lizikj.marketing.api.enums.RedPacketTypeEnum;
import com.lizikj.marketing.api.enums.SourceEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author lxl
 * @Date 2018/7/31 16:22
 */
@ApiModel(value = "红包组合查询条件")
public class RedPacketQueryParamVO {
    @ApiModelProperty(value = "红包ID")
    private Long packetId;
    @ApiModelProperty(value = "红包编码")
    private String packetNo;
    @ApiModelProperty(value = "红包类型")
    private RedPacketTypeEnum packetTypeEnum;
    @ApiModelProperty(value = "红包来源")
    private SourceEnum packetSourceEnum;
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    @ApiModelProperty(value = "商圈ID")
    private Long tradeAreaId;
    @ApiModelProperty(value = "活动ID")
    private Long activityId;
    @ApiModelProperty(value = "红包状态")
    private RedPacketStatusEnum packetStatusEnum;

    public Long getPacketId() {
        return packetId;
    }

    public void setPacketId(Long packetId) {
        this.packetId = packetId;
    }

    public String getPacketNo() {
        return packetNo;
    }

    public void setPacketNo(String packetNo) {
        this.packetNo = packetNo;
    }

    public RedPacketTypeEnum getPacketTypeEnum() {
        return packetTypeEnum;
    }

    public void setPacketTypeEnum(RedPacketTypeEnum packetTypeEnum) {
        this.packetTypeEnum = packetTypeEnum;
    }

    public SourceEnum getPacketSourceEnum() {
        return packetSourceEnum;
    }

    public void setPacketSourceEnum(SourceEnum packetSourceEnum) {
        this.packetSourceEnum = packetSourceEnum;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getTradeAreaId() {
        return tradeAreaId;
    }

    public void setTradeAreaId(Long tradeAreaId) {
        this.tradeAreaId = tradeAreaId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public RedPacketStatusEnum getPacketStatusEnum() {
        return packetStatusEnum;
    }

    public void setPacketStatusEnum(RedPacketStatusEnum packetStatusEnum) {
        this.packetStatusEnum = packetStatusEnum;
    }
}
