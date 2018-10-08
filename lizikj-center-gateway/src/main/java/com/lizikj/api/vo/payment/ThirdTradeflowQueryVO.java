package com.lizikj.api.vo.payment;

import java.io.Serializable;

/**
 * @author zhoufe
 * @date 2018/5/3 15:17
 */
public class ThirdTradeflowQueryVO implements Serializable {
    private static final long serialVersionUID = 7445507742620600234L;

    private Byte paymentChannel;

    private String requestParam;

    public Byte getPaymentChannel() {
        return paymentChannel;
    }

    public void setPaymentChannel(Byte paymentChannel) {
        this.paymentChannel = paymentChannel;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }
}
