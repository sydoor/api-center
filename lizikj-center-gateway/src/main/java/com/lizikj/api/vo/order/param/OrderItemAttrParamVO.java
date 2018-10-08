package com.lizikj.api.vo.order.param;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * 下单：商品属性
 * Created by adept on 2017/7/12.
 */
@ApiModel
public class OrderItemAttrParamVO extends ItemAttrParamVO<OrderItemAttrValueParamVO> implements Serializable {
    private static final long serialVersionUID = 1207374314345799901L;
}
