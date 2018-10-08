package com.lizikj.api.vo.order.param;

import com.lizikj.common.enums.PaymentTypeEnum;
import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 结账的参数
 * @author zhoufe
 * @date 2017/8/11 17:03
 */
@ApiModel
public class VerifyMemberParamVO extends BaseDTO {

    private static final long serialVersionUID = -6694005676576123303L;

    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    @ApiModelProperty(value = "验证的会员手机号")
    private String memberMobile;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getMemberMobile() {
        return memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }
}
