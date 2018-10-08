package com.lizikj.api.vo.schedule;

import com.lizikj.schedule.api.enums.QueueStatusEnum;
import com.lizikj.schedule.api.enums.QueueTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhoufe
 * @date 2017/11/29 19:21
 */
@ApiModel
public class QueryScheduleQueueVO implements Serializable {

    private static final long serialVersionUID = -2296396259328232726L;

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "排号类型：SMALL_DESK.小桌，MIDDLE_DESK.中桌，BIG_DESK.大桌，HUGE_DESK.超大桌")
    private QueueTypeEnum queueType;

    @ApiModelProperty(value = "状态列表：见QueueStatusEnum：LINNIG.排队中，SEATED.已入座，MISSING.已过号，CANCEL.已取消。")
    private List<QueueStatusEnum> queueStatusList;

    @ApiModelProperty(value = "状态：见QueueStatusEnum：LINNIG.排队中，SEATED.已入座，MISSING.已过号，CANCEL.已取消。")
    private QueueStatusEnum queueStatus;

    @ApiModelProperty(value = "页码")
    private Integer pageNum;

    @ApiModelProperty(value = "页大小")
    private Integer pageSize;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public QueueTypeEnum getQueueType() {
        return queueType;
    }

    public void setQueueType(QueueTypeEnum queueType) {
        this.queueType = queueType;
    }

    public List<QueueStatusEnum> getQueueStatusList() {
        return queueStatusList;
    }

    public void setQueueStatusList(List<QueueStatusEnum> queueStatusList) {
        this.queueStatusList = queueStatusList;
    }

    public QueueStatusEnum getQueueStatus() {
        return queueStatus;
    }

    public void setQueueStatus(QueueStatusEnum queueStatus) {
        this.queueStatus = queueStatus;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
