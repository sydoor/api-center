package com.lizikj.api.vo.payment;

import java.io.Serializable;

/**
 * @author zhoufe
 * @date 2018/5/3 15:19
 */
public class ThirdTQueryRequestParamVO  implements Serializable {
    private static final long serialVersionUID = 8845752274750704225L;

    private String tradeNo;

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }
}
