package com.lizikj.api.vo.merchandise;

import com.lizikj.merchandise.enums.SellStateEnum;
import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author Michael.Huang
 * @date 2017/6/22
 */
@ApiModel
public class SkuVO extends BaseDTO {

    private static final long serialVersionUID = 1L;
    /**
     * skuId
     */
    @ApiModelProperty(value = "skuId", required = true)
    private Long skuId;
    /**
     * 库存
     */
    @ApiModelProperty(value = "库存", required = true)
    private Integer stock;

    /**
     * 是否选中激活
     */
    @ApiModelProperty(value = "是否选中激活,此值指针对购物车选中的内容有效", required = false)
    private Boolean isActive;


    /**
     * 销量
     */
    @ApiModelProperty(value = "销量")
    private Integer sellVolume;

    /**
     * 重量
     */
    @ApiModelProperty(value = "重量")
    private Integer weight;

    /**
     * 销售状态
     */
    @ApiModelProperty(value = "销售状态：见SellStateEnum：AVAILABLE.可售，SELL_OUT.售罄", required = true)
    private SellStateEnum sellState;
    /**
     * 出售价格(单位分)
     */
    @ApiModelProperty(value = "出售价格(单位分)", required = true)
    private Long sellPrice;
    /**
     * 会员出售价格(单位分)
     */
    @ApiModelProperty(value = "会员出售价格(单位分)", required = true)
    private Long memberPrice;
    /**
     * 成本价(单位分)
     */
    @ApiModelProperty(value = "成本价(单位分)", required = true)
    private Long costPrice;
    /**
     * skuValues：sku的明细
     */
    @ApiModelProperty(value = "sku的明细", required = true)
    private List<SkuValueVO> skuValues;

    /**
     * 数量或者重量(OrderGodos|CartGoods中有效)
     */
    @ApiModelProperty(value = "数量或者重量(OrderGodos|CartGoods中有效),如果是重量，放大了100倍")
    private Integer nums;

    /**
     * 销售提成金额*100
     */
    @ApiModelProperty(value = "销售提成金额*100", required = true)
    private Long salesExtractAmount;


    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Integer getSellVolume() {
        return sellVolume;
    }

    public void setSellVolume(Integer sellVolume) {
        this.sellVolume = sellVolume;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public SellStateEnum getSellState() {
        return sellState;
    }

    public void setSellState(SellStateEnum sellState) {
        this.sellState = sellState;
    }

    public Long getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Long sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Long getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(Long memberPrice) {
        this.memberPrice = memberPrice;
    }

    public Long getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Long costPrice) {
        this.costPrice = costPrice;
    }

    public List<SkuValueVO> getSkuValues() {
        return skuValues;
    }

    public void setSkuValues(List<SkuValueVO> skuValues) {
        this.skuValues = skuValues;
    }

    public Long getSalesExtractAmount() {
        return salesExtractAmount;
    }

    public void setSalesExtractAmount(Long salesExtractAmount) {
        this.salesExtractAmount = salesExtractAmount;
    }
}
