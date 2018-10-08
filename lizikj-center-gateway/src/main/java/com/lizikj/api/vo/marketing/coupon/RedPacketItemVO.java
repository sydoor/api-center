package com.lizikj.api.vo.marketing.coupon;

import com.lizikj.marketing.api.enums.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author lxl
 * @Date 2018/7/25 15:06
 */
@ApiModel(value = "红包实体")
public class RedPacketItemVO {
    @ApiModelProperty(value = "实体ID")
    private Long itemId;
    @ApiModelProperty(value = "红包编码")
    private String packetNo;
    @ApiModelProperty(value = "红包ID")
    private Long packetId;
    @ApiModelProperty(value = "红包码")
    private String packetCode;
    /**
     * 红包类型
     */
    @ApiModelProperty(value = "红包类型")
    private RedPacketTypeEnum packetTypeEnum;
    /**
     * 红包来源
     */
    @ApiModelProperty(value = "来源")
    private SourceEnum packetSourceEnum;
    @ApiModelProperty(value = "红包名称")
    private String packetName;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "金额")
    private Long amount;
    @ApiModelProperty(value = "红包使用状态")
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
    @ApiModelProperty(value = "领取时间")
    private Date getTime;
    @ApiModelProperty(value = "使用场景")
    private GetSceneEnum getSceneEnum;
    @ApiModelProperty(value = "使用时间")
    private Date useTime;
    @ApiModelProperty(value = "有效小时数")
    private Integer validHour;
    @ApiModelProperty(value = "到期时间")
    private Date expireTime;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private Date startTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "邀请码")
    private PlayCodeEnum playCodeEnum;
    @ApiModelProperty(value = "是否推荐使用")
    private Boolean promoteUse;

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

    public String getPacketName() {
        return packetName;
    }

    public void setPacketName(String packetName) {
        this.packetName = packetName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
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

    public Date getGetTime() {
        return getTime;
    }

    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }

    public GetSceneEnum getGetSceneEnum() {
        return getSceneEnum;
    }

    public void setGetSceneEnum(GetSceneEnum getSceneEnum) {
        this.getSceneEnum = getSceneEnum;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
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

    public Integer getValidHour() {
        return validHour;
    }

    public void setValidHour(Integer validHour) {
        this.validHour = validHour;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public PlayCodeEnum getPlayCodeEnum() {
        return playCodeEnum;
    }

    public void setPlayCodeEnum(PlayCodeEnum playCodeEnum) {
        this.playCodeEnum = playCodeEnum;
    }

    public Boolean getPromoteUse() {
        return promoteUse;
    }

    public void setPromoteUse(Boolean promoteUse) {
        this.promoteUse = promoteUse;
    }
}
