package com.lizikj.api.vo.marketing.coupon;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lxl
 * @Date 2018/7/30 15:08
 */
@ApiModel(value = "用户基础统计")
public class UserCommonStaticsVO {
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    @ApiModelProperty(value = "卡券数")
    private Integer couponNum;
    @ApiModelProperty(value = "红包数")
    private Integer packetNum;
    @ApiModelProperty(value = "订单数")
    private Integer orderNum;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getCouponNum() {
        return couponNum;
    }

    public void setCouponNum(Integer couponNum) {
        this.couponNum = couponNum;
    }

    public Integer getPacketNum() {
        return packetNum;
    }

    public void setPacketNum(Integer packetNum) {
        this.packetNum = packetNum;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}
