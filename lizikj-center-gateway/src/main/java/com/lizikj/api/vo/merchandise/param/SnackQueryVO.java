package com.lizikj.api.vo.merchandise.param;

/**
 * @author dyh
 * @created at 2017 12 05 11:00
 */
public class SnackQueryVO {
    private Long shopId;

    private String snackName;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getSnackName() {
        return snackName;
    }

    public void setSnackName(String snackName) {
        this.snackName = snackName;
    }
}
