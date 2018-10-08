package com.lizikj.api.vo.shop;

import com.lizikj.merchandise.enums.CalcMethodEnum;
import com.lizikj.shop.api.enums.ShopBaseDataCustomStatusEnum;
import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
 * @author zhoufe 
 * @date 2017年7月18日 下午4:27:49 
 */
@ApiModel
public class ShopMerchandiseUnitVO extends BaseDTO {
	   
	private static final long serialVersionUID = 1L;

	/**
     * 商品单位ID
     */
    @ApiModelProperty(value = "商品单位ID", required = true)
    private Long merchandiseUnitId;

    /**
     * 商品单位模板ID
     */
    @ApiModelProperty(value = "商品单位模板ID", required = true)
    private Long merchandiseUnitTemplateId;

    /**
     * 单位模板编码：自定义的没有模板编码：1001.份，1002.个，1003.例，1004.杯，1005.只，1006.半只，1007.斤
     */
    @ApiModelProperty(value = "单位模板编码：自定义的没有模板编码：1001.份，1002.个，1003.例，1004.杯，1005.只，1006.半只，1007.斤", required = true)
    private Integer merchandiseUnitCode;

    /**
     * 店铺ID
     */
    @ApiModelProperty(value = "店铺ID", required = true)
    private Long shopId;

    /**
     * 计价方式：1.数量，2.重量
     */
    @ApiModelProperty(value = "计价方式：见CalcMethodEnum：NUMBER.数量，WEIGHT.重量。", required = true)
    private CalcMethodEnum priceMothed;

    /**
     * 计价单位
     */
    @ApiModelProperty(value = "计价单位", required = true)
    private String priceUnit;

    /**
     * 是否自定义：1.系统，0.自定义
     */
    @ApiModelProperty(value = "是否自定义：见：ShopBaseDataCustomStatusEnum：SYSTEM.系统，CUSTOM.自定义。", required = true)
    private ShopBaseDataCustomStatusEnum customStatus;

    /**
     * 商品单位ID
     */
    public Long getMerchandiseUnitId() {
        return merchandiseUnitId;
    }

    /**
     * 商品单位ID
     * @param merchandiseUnitId the value for shop_merchandise_unit.merchandise_unit_id
     */
    public void setMerchandiseUnitId(Long merchandiseUnitId) {
        this.merchandiseUnitId = merchandiseUnitId;
    }

    /**
     * 店铺ID
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 店铺ID
     * @param shopId the value for shop_merchandise_unit.shop_id
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public CalcMethodEnum getPriceMothed() {
        return priceMothed;
    }

    public void setPriceMothed(CalcMethodEnum priceMothed) {
        this.priceMothed = priceMothed;
    }

    /**
     * 计价单位
     */
    public String getPriceUnit() {
        return priceUnit;
    }

    /**
     * 计价单位
     * @param priceUnit the value for shop_merchandise_unit.price_unit
     */
    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit == null ? null : priceUnit.trim();
    }

    public Long getMerchandiseUnitTemplateId() {
        return merchandiseUnitTemplateId;
    }

    public void setMerchandiseUnitTemplateId(Long merchandiseUnitTemplateId) {
        this.merchandiseUnitTemplateId = merchandiseUnitTemplateId;
    }

    public Integer getMerchandiseUnitCode() {
        return merchandiseUnitCode;
    }

    public void setMerchandiseUnitCode(Integer merchandiseUnitCode) {
        this.merchandiseUnitCode = merchandiseUnitCode;
    }

    public ShopBaseDataCustomStatusEnum getCustomStatus() {
        return customStatus;
    }

    public void setCustomStatus(ShopBaseDataCustomStatusEnum customStatus) {
        this.customStatus = customStatus;
    }
}
