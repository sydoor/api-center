package com.lizikj.api.vo.merchandise;

import com.lizikj.merchandise.enums.RemoveStatusEnum;
import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhoufe
 * @date 2017年7月12日 上午11:43:43
 */
@ApiModel
public class SnackVO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "加料ID", required = true)
    private String id;

    /**
     * 店铺id
     */
    @ApiModelProperty(value = "店铺ID", required = true)
    private Long shopId;

    /**
     * 名字
     */
    @ApiModelProperty(value = "名字", required = true)
    private String name;

    /**
     * 价格(单位分)
     */
    @ApiModelProperty(value = "价格(单位分)", required = true)
    private Long price;

    /**
     * 删除状态
     */
    @ApiModelProperty(value = "删除状态", required = true)
    private RemoveStatusEnum removeStatus;

    /**
     * 是否选中激活
     */
    @ApiModelProperty(value = "是否选中激活,此值指针对购物车选中的内容有效", required = false)
    private Boolean isActive;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public RemoveStatusEnum getRemoveStatus() {
        return removeStatus;
    }

    public void setRemoveStatus(RemoveStatusEnum removeStatus) {
        this.removeStatus = removeStatus;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
