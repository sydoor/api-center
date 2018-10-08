package com.lizikj.api.vo.schedule;

import com.lizikj.schedule.api.enums.QueueSourceEnum;
import com.lizikj.schedule.api.enums.QueueStatusEnum;
import com.lizikj.schedule.api.enums.QueueTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Model for table -- schedule_queue
 * Created by zhoufe on 2017-24-29 10:24:57
 */
@ApiModel
public class ScheduleQueueVO implements Serializable {
    
    private static final long serialVersionUID = -3405367857582464050L;
    /**
     * 排号ID
     */
    @ApiModelProperty(value = "排号ID")
    private Long scheduleQueueId;

    /**
     * 商户ID
     */
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;

    /**
     * 店铺ID
     */
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    /**
     * 还需等待的序号：数据库不存，返回前端需要
     */
    @ApiModelProperty(value = "还需等待的序号")
    private Integer beforeIndex;

    /**
     * 排号
     */
    @ApiModelProperty(value = "排号")
    private String queueNo;

    /**
     * 排号来源
     */
    @ApiModelProperty(value = "排号来源：见QueueSourceEnum：H5.H5，POS.POS。")
    private QueueSourceEnum queueSource;

    /**
     * 排号状态：0.排队中，1.已入座，2.已过号，3.已取消
     */
    @ApiModelProperty(value = "排号状态：见QueueStatusEnum：LINNIG.排队中，SEATED.已入座，MISSING.已过号，CANCEL.已取消。")
    private QueueStatusEnum queueStatus;

    /**
     * 就餐人数
     */
    @ApiModelProperty(value = "就餐人数")
    private Integer peoples;

    /**
     * 排号类型：12.小桌，34.中桌，58.大桌，999.超大桌
     */
    @ApiModelProperty(value = "排号类型：SMALL_DESK.小桌，MIDDLE_DESK.中桌，BIG_DESK.大桌，HUGE_DESK.超大桌")
    private QueueTypeEnum queueType;

    /**
     * 取号人手机号
     */
    @ApiModelProperty(value = "取号人手机号")
    private String takeUserMobile;

    /**
     * 取号人：H5取号的user_id
     */
    @ApiModelProperty(value = "取号人：H5取号的user_id")
    private Long takeUserId;

    /**
     * 派号人：pos派号的staff_id
     */
    @ApiModelProperty(value = "派号人：pos派号的staff_id")
    private Long sendOperatorId;

    /**
     * 入座人：POS操作入座的操作人staff_id
     */
    @ApiModelProperty(value = "入座人：POS操作入座的操作人staff_id")
    private Long seatDownOperatorId;

    /**
     * 过号人：pos操作的staff_id
     */
    @ApiModelProperty(value = "过号人：pos操作的staff_id")
    private Long missingOperatorId;

    /**
     * 消号人：H5取消的user_id
     */
    @ApiModelProperty(value = "消号人：H5取消的user_id")
    private Long cancelOperatorId;

    /**
     * 取号时间
     */
    @ApiModelProperty(value = "取号时间")
    private Date ownTime;

    /**
     * 入座时间
     */
    @ApiModelProperty(value = "入座时间")
    private Date seatDownTime;

    /**
     * 过号时间
     */
    @ApiModelProperty(value = "过号时间")
    private Date missingTime;

    /**
     * 消号时间
     */
    @ApiModelProperty(value = "消号时间")
    private Date cancelTime;


    /**
     * 查看进度的url
     */
    @ApiModelProperty(value = "查看进度的url")
    private String checkScheduleUrl;


    /**
     * 发模板消息所需的formId
     */
    @ApiModelProperty(value = "发模板消息所需的formId")
    private String formId;
    
    /**
     * 小程序的appid
     */
    @ApiModelProperty(value = "小程序的appid")
    private String appid;

    /**
     * 排号ID
     */
    public Long getScheduleQueueId() {
        return scheduleQueueId;
    }

    /**
     * 排号ID
     * @param scheduleQueueId the value for schedule_queue.schedule_queue_id
     */
    public void setScheduleQueueId(Long scheduleQueueId) {
        this.scheduleQueueId = scheduleQueueId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 店铺ID
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 店铺ID
     * @param shopId the value for schedule_queue.shop_id
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Integer getBeforeIndex() {
        return beforeIndex;
    }

    public void setBeforeIndex(Integer beforeIndex) {
        this.beforeIndex = beforeIndex;
    }

    /**
     * 排号
     */
    public String getQueueNo() {
        return queueNo;
    }

    /**
     * 排号
     * @param queueNo the value for schedule_queue.queue_no
     */
    public void setQueueNo(String queueNo) {
        this.queueNo = queueNo == null ? null : queueNo.trim();
    }

    public QueueSourceEnum getQueueSource() {
        return queueSource;
    }

    public void setQueueSource(QueueSourceEnum queueSource) {
        this.queueSource = queueSource;
    }

    /**
     * 排号状态：0.排队中，1.已入座，2.已过号，3.已取消
     */
    public QueueStatusEnum getQueueStatus() {
        return queueStatus;
    }

    /**
     * 排号状态：0.排队中，1.已入座，2.已过号，3.已取消
     * @param queueStatus the value for schedule_queue.queue_status
     */

    public void setQueueStatus(QueueStatusEnum queueStatus) {
        this.queueStatus = queueStatus;
    }

    /**
     * 就餐人数
     */
    public Integer getPeoples() {
        return peoples;
    }

    /**
     * 就餐人数
     * @param peoples the value for schedule_queue.peoples
     */
    public void setPeoples(Integer peoples) {
        this.peoples = peoples;
    }

    /**
     * 排号类型：12.小桌，34.中桌，58.大桌，999.超大桌
     */
    public QueueTypeEnum getQueueType() {
        return queueType;
    }

    /**
     * 排号类型：12.小桌，34.中桌，58.大桌，999.超大桌
     * @param queueType the value for schedule_queue.queue_type
     */

    public void setQueueType(QueueTypeEnum queueType) {
        this.queueType = queueType;
    }

    /**
     * 取号人手机号
     */
    public String getTakeUserMobile() {
        return takeUserMobile;
    }

    /**
     * 取号人手机号
     * @param takeUserMobile the value for schedule_queue.take_user_mobile
     */
    public void setTakeUserMobile(String takeUserMobile) {
        this.takeUserMobile = takeUserMobile == null ? null : takeUserMobile.trim();
    }

    /**
     * 取号人：H5取号的user_id
     */
    public Long getTakeUserId() {
        return takeUserId;
    }

    /**
     * 取号人：H5取号的user_id
     * @param takeUserId the value for schedule_queue.take_user_id
     */
    public void setTakeUserId(Long takeUserId) {
        this.takeUserId = takeUserId;
    }

    /**
     * 派号人：pos派号的staff_id
     */
    public Long getSendOperatorId() {
        return sendOperatorId;
    }

    /**
     * 派号人：pos派号的staff_id
     * @param sendOperatorId the value for schedule_queue.send_operator_id
     */
    public void setSendOperatorId(Long sendOperatorId) {
        this.sendOperatorId = sendOperatorId;
    }

    public Long getSeatDownOperatorId() {
        return seatDownOperatorId;
    }

    public void setSeatDownOperatorId(Long seatDownOperatorId) {
        this.seatDownOperatorId = seatDownOperatorId;
    }

    /**
     * 过号人：pos操作的staff_id
     */
    public Long getMissingOperatorId() {
        return missingOperatorId;
    }

    /**
     * 过号人：pos操作的staff_id
     * @param missingOperatorId the value for schedule_queue.missing_operator_id
     */
    public void setMissingOperatorId(Long missingOperatorId) {
        this.missingOperatorId = missingOperatorId;
    }

    /**
     * 消号人：H5取消的user_id
     */
    public Long getCancelOperatorId() {
        return cancelOperatorId;
    }

    /**
     * 消号人：H5取消的user_id
     * @param cancelOperatorId the value for schedule_queue.cancel_operator_id
     */
    public void setCancelOperatorId(Long cancelOperatorId) {
        this.cancelOperatorId = cancelOperatorId;
    }

    /**
     * 取号时间
     */
    public Date getOwnTime() {
        return ownTime;
    }

    /**
     * 取号时间
     * @param ownTime the value for schedule_queue.own_time
     */
    public void setOwnTime(Date ownTime) {
        this.ownTime = ownTime;
    }

    /**
     * 入座时间
     */
    public Date getSeatDownTime() {
        return seatDownTime;
    }

    /**
     * 入座时间
     * @param seatDownTime the value for schedule_queue.seat_down_time
     */
    public void setSeatDownTime(Date seatDownTime) {
        this.seatDownTime = seatDownTime;
    }

    /**
     * 过号时间
     */
    public Date getMissingTime() {
        return missingTime;
    }

    /**
     * 过号时间
     * @param missingTime the value for schedule_queue.missing_time
     */
    public void setMissingTime(Date missingTime) {
        this.missingTime = missingTime;
    }

    /**
     * 消号时间
     */
    public Date getCancelTime() {
        return cancelTime;
    }

    /**
     * 消号时间
     * @param cancelTime the value for schedule_queue.cancel_time
     */
    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getCheckScheduleUrl() {
        return checkScheduleUrl;
    }

    public void setCheckScheduleUrl(String checkScheduleUrl) {
        this.checkScheduleUrl = checkScheduleUrl;
    }

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}
}