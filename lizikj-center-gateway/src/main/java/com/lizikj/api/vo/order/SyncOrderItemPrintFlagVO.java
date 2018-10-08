package com.lizikj.api.vo.order;

import java.io.Serializable;

/**
 * @author zhoufe
 * @date 2018/1/15 11:41
 */
public class SyncOrderItemPrintFlagVO implements Serializable {

    private static final long serialVersionUID = 5363286495866350094L;

    private Long orderItemId;

    private Integer printFlag;

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Integer getPrintFlag() {
        return printFlag;
    }

    public void setPrintFlag(Integer printFlag) {
        this.printFlag = printFlag;
    }
}
