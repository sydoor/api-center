package com.lizikj.api.vo.merchandise.param;

/**
 * @author dyh
 * @created at 2017 12 05 10:59
 */
public class TagQueryVO {
    private Long shopId;

    private String tagName;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
