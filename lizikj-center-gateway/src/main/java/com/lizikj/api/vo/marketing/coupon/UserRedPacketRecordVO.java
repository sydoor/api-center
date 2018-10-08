package com.lizikj.api.vo.marketing.coupon;

import com.lizikj.marketing.api.enums.GetSceneEnum;
import com.lizikj.marketing.api.enums.SourceSceneEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Model for table -- user_red_packet_record
 * Created by ASUS on 2018-48-25 11:48:36
 */
@ApiModel
public class UserRedPacketRecordVO implements Serializable {
    /**
     * 记录ID
     */
    @ApiModelProperty(name = "recordId", value = "记录ID")
    private Long recordId;

    /**
     * 红包编号
     */
    @ApiModelProperty(name = "packetNo", value = "红包编号")
    private String packetNo;

    /**
     * 红包ID
     */
    @ApiModelProperty(name = "packetId", value = "红包ID")
    private Long packetId;

    /**
     * 红包码
     */
    @ApiModelProperty(name = "packetCode", value = "红包码")
    private String packetCode;

    /**
     * 订单ID
     */
    @ApiModelProperty(name = "orderId", value = "订单ID")
    private Long orderId;

    /**
     * 订单号
     */
    @ApiModelProperty(name = "orderNo", value = "订单号")
    private String orderNo;

    /**
     * 金额
     */
    @ApiModelProperty(name = "amount", value = "金额")
    private Long amount;

    /**
     * 用户ID
     */
    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long userId;

    /**
     * 父用户ID
     */
    @ApiModelProperty(name = "parentUserId", value = "父用户ID")
    private Long parentUserId;

    /**
     * 红包来源场景：1.结账前的分享，2.结账后分享
     */
    @ApiModelProperty(name = "sourceSceneEnum", value = "红包来源场景")
    private SourceSceneEnum sourceSceneEnum;

    /**
     * 领取场景：200.分享，300.扫码 400.卡券
     */
    @ApiModelProperty(name = "getSceneEnum", value = "领取场景")
    private GetSceneEnum getSceneEnum;

    /**
     * 活动ID
     */
    @ApiModelProperty(name = "activityId", value = "活动ID")
    private Long activityId;

    /**
     * 领取时间
     */
    @ApiModelProperty(name = "getTime", value = "领取时间")
    private Date getTime;

    /**
     * 创建时间
     */
    @ApiModelProperty(name = "createTime", value = "时间时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private Date updateTime;

    /**
     * 是否删除 1 删除 0 不删除
     */
    @ApiModelProperty(name = "removeStatus", value = "是否删除")
    private Byte removeStatus;

    @ApiModelProperty(name = "userName", value = "用户名称")
    private String userName;

    @ApiModelProperty(name = "userPortraitUrl", value = "用户头像链接")
    private String userPortraitUrl;

    /**
     * 记录ID
     */
    public Long getRecordId() {
        return recordId;
    }

    /**
     * 记录ID
     * @param recordId the value for user_red_packet_record.record_id
     */
    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    /**
     * 红包编号
     */
    public String getPacketNo() {
        return packetNo;
    }

    /**
     * 红包编号
     * @param packetNo the value for user_red_packet_record.packet_no
     */
    public void setPacketNo(String packetNo) {
        this.packetNo = packetNo == null ? null : packetNo.trim();
    }

    /**
     * 红包ID
     */
    public Long getPacketId() {
        return packetId;
    }

    /**
     * 红包ID
     * @param packetId the value for user_red_packet_record.packet_id
     */
    public void setPacketId(Long packetId) {
        this.packetId = packetId;
    }

    /**
     * 红包码
     */
    public String getPacketCode() {
        return packetCode;
    }

    /**
     * 红包码
     * @param packetCode the value for user_red_packet_record.packet_code
     */
    public void setPacketCode(String packetCode) {
        this.packetCode = packetCode == null ? null : packetCode.trim();
    }

    /**
     * 订单ID
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 订单ID
     * @param orderId the value for user_red_packet_record.order_id
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 订单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 订单号
     * @param orderNo the value for user_red_packet_record.order_no
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * 金额
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * 金额
     * @param amount the value for user_red_packet_record.amount
     */
    public void setAmount(Long amount) {
        this.amount = amount;
    }

    /**
     * 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 用户ID
     * @param userId the value for user_red_packet_record.user_id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 父用户ID
     */
    public Long getParentUserId() {
        return parentUserId;
    }

    /**
     * 父用户ID
     * @param parentUserId the value for user_red_packet_record.parent_user_id
     */
    public void setParentUserId(Long parentUserId) {
        this.parentUserId = parentUserId;
    }

    public SourceSceneEnum getSourceSceneEnum() {
        return sourceSceneEnum;
    }

    public void setSourceSceneEnum(SourceSceneEnum sourceSceneEnum) {
        this.sourceSceneEnum = sourceSceneEnum;
    }

    public GetSceneEnum getGetSceneEnum() {
        return getSceneEnum;
    }

    public void setGetSceneEnum(GetSceneEnum getSceneEnum) {
        this.getSceneEnum = getSceneEnum;
    }

    /**
     * 活动ID
     */
    public Long getActivityId() {
        return activityId;
    }

    /**
     * 活动ID
     * @param activityId the value for user_red_packet_record.activity_id
     */
    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    /**
     * 领取时间
     */
    public Date getGetTime() {
        return getTime;
    }

    /**
     * 领取时间
     * @param getTime the value for user_red_packet_record.get_time
     */
    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime the value for user_red_packet_record.create_time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime the value for user_red_packet_record.update_time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 是否删除 1 删除 0 不删除
     */
    public Byte getRemoveStatus() {
        return removeStatus;
    }

    /**
     * 是否删除 1 删除 0 不删除
     * @param removeStatus the value for user_red_packet_record.remove_status
     */
    public void setRemoveStatus(Byte removeStatus) {
        this.removeStatus = removeStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPortraitUrl() {
        return userPortraitUrl;
    }

    public void setUserPortraitUrl(String userPortraitUrl) {
        this.userPortraitUrl = userPortraitUrl;
    }
}