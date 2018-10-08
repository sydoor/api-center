package com.lizikj.api.vo.marketing.coupon;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lxl
 * @Date 2018/8/16 18:07
 */
@ApiModel
public class RedPacketTotalStatisticsVO {
    @ApiModelProperty(value = "发放数量")
    private Integer grantNum = Integer.valueOf(0);
    @ApiModelProperty(value = "发放金额")
    private Long grantAmount = Long.valueOf(0);
    @ApiModelProperty(value = "使用数量")
    private Integer useNum = Integer.valueOf(0);
    @ApiModelProperty(value = "使用总金额")
    private Long useAmount = 0L;
    @ApiModelProperty(value = "未使用数量")
    private Integer notUseNum = Integer.valueOf(0);
    @ApiModelProperty(value = "订单总额")
    private Long orderTotalAmount = 0L;

    public Integer getGrantNum() {
        return grantNum;
    }

    public void setGrantNum(Integer grantNum) {
        this.grantNum = grantNum;
    }

    public Integer getUseNum() {
        return useNum;
    }

    public void setUseNum(Integer useNum) {
        this.useNum = useNum;
    }

    public Long getUseAmount() {
        return useAmount;
    }

    public void setUseAmount(Long useAmount) {
        this.useAmount = useAmount;
    }

    public Integer getNotUseNum() {
        return notUseNum;
    }

    public void setNotUseNum(Integer notUseNum) {
        this.notUseNum = notUseNum;
    }

    public Long getGrantAmount() {
        return grantAmount;
    }

    public void setGrantAmount(Long grantAmount) {
        this.grantAmount = grantAmount;
    }

    public Long getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(Long orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }
}
