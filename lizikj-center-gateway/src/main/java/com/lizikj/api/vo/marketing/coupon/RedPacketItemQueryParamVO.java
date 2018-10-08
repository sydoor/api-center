package com.lizikj.api.vo.marketing.coupon;

import com.lizikj.marketing.api.enums.GetSceneEnum;
import com.lizikj.marketing.api.enums.RedPacketTypeEnum;
import com.lizikj.marketing.api.enums.RedPacketUseEnum;
import com.lizikj.marketing.api.enums.SourceEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @author lxl
 * @Date 2018/7/25 15:01
 */
@ApiModel(value = "红包实体查询条件")
public class RedPacketItemQueryParamVO {
    @ApiModelProperty(value = "实体ID")
    private Long itemId;
    @ApiModelProperty(value = "红包编码")
    private String packetNo;
    @ApiModelProperty(value = "红包ID")
    private Long packetId;
    @ApiModelProperty(value = "红包码")
    private String packetCode;
    @ApiModelProperty(value = "红包类型")
    private RedPacketTypeEnum packetTypeEnum;
    @ApiModelProperty(value = "红包来源")
    private SourceEnum packetSourceEnum;
    @ApiModelProperty(value = "使用状态")
    private RedPacketUseEnum useStatusEnum;
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    @ApiModelProperty(value = "商圈ID")
    private Long tradeAreaId;
    @ApiModelProperty(value = "活动ID")
    private Long activityId;
    @ApiModelProperty(value = "活动ID列表")
    private List<String> activityIds;
    @ApiModelProperty(value = "领取场景")
    private GetSceneEnum getSceneEnum;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getPacketNo() {
        return packetNo;
    }

    public void setPacketNo(String packetNo) {
        this.packetNo = packetNo;
    }

    public Long getPacketId() {
        return packetId;
    }

    public void setPacketId(Long packetId) {
        this.packetId = packetId;
    }

    public String getPacketCode() {
        return packetCode;
    }

    public void setPacketCode(String packetCode) {
        this.packetCode = packetCode;
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

    public RedPacketUseEnum getUseStatusEnum() {
        return useStatusEnum;
    }

    public void setUseStatusEnum(RedPacketUseEnum useStatusEnum) {
        this.useStatusEnum = useStatusEnum;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public List<String> getActivityIds() {
        return activityIds;
    }

    public void setActivityIds(List<String> activityIds) {
        this.activityIds = activityIds;
    }

    public GetSceneEnum getGetSceneEnum() {
        return getSceneEnum;
    }

    public void setGetSceneEnum(GetSceneEnum getSceneEnum) {
        this.getSceneEnum = getSceneEnum;
    }
}
