package com.lizikj.api.vo.marketing.coupon;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author lxl
 * @Date 2018/8/20 16:36
 */
@ApiModel
public class PrizeListVO {
    @ApiModelProperty(value = "卡券列表")
    private List<CouponItemVO> couponItemVOS;
    @ApiModelProperty(value = "红包列表")
    private List<RedPacketItemVO> redPacketItemVOS;

    public List<CouponItemVO> getCouponItemVOS() {
        return couponItemVOS;
    }

    public void setCouponItemVOS(List<CouponItemVO> couponItemVOS) {
        this.couponItemVOS = couponItemVOS;
    }

    public List<RedPacketItemVO> getRedPacketItemVOS() {
        return redPacketItemVOS;
    }

    public void setRedPacketItemVOS(List<RedPacketItemVO> redPacketItemVOS) {
        this.redPacketItemVOS = redPacketItemVOS;
    }
}
