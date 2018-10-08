package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author lxl
 * @Date 2018/1/19 9:48
 */
@ApiModel
public class ShopShiftVO {
    @ApiModelProperty(value = "交接班ID")
    private Long shopShiftId;
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    @ApiModelProperty(value = "上班人员")
    private Long staffId;
    @ApiModelProperty(value = "上班时间")
    private Date shiftStartTime;
    @ApiModelProperty(value = "交接班结束时间")
    private Date shiftEndTime;
    @ApiModelProperty(value = "下次接班人员ID")
    private Long shiftStaffId;

    public Long getShopShiftId() {
        return shopShiftId;
    }

    public void setShopShiftId(Long shopShiftId) {
        this.shopShiftId = shopShiftId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Date getShiftStartTime() {
        return shiftStartTime;
    }

    public void setShiftStartTime(Date shiftStartTime) {
        this.shiftStartTime = shiftStartTime;
    }

    public Date getShiftEndTime() {
        return shiftEndTime;
    }

    public void setShiftEndTime(Date shiftEndTime) {
        this.shiftEndTime = shiftEndTime;
    }

    public Long getShiftStaffId() {
        return shiftStaffId;
    }

    public void setShiftStaffId(Long shiftStaffId) {
        this.shiftStaffId = shiftStaffId;
    }
}
