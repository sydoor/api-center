package com.lizikj.api.controller.payment;

import com.alibaba.fastjson.JSONObject;
import com.lizikj.api.vo.payment.ThirdTradeflowQueryVO;

/**
 * @author zhoufe
 * @date 2018/5/3 17:42
 */
public class Dddd {

    public static void main(String[] args) {
        String request = "{\"paymentChannel\":11,\"requestParam\":\"{\\\"tradeNo\\\":\\\"P201805031209342443343245\\\"}\"}";
        ThirdTradeflowQueryVO thirdTradeflowQueryVO = JSONObject.parseObject(request, ThirdTradeflowQueryVO.class);

    }

}
