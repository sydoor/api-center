package com.lizikj.api.vo.order.param;

import com.lizikj.order.enums.AppendDishStatusEnum;
import com.lizikj.order.enums.PackStatusEnum;
import com.lizikj.order.enums.PrintStatusEnum;
import com.lizikj.order.enums.RecStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author Michael.Huang
 * @date 2017/8/25 15:37
 */
@ApiModel
public class ItemParamVO<T extends ItemAttrParamVO> implements Serializable {

    private static final long serialVersionUID = 8588163988477169155L;

    @ApiModelProperty(value = "skuId：美食系统有的都是正整数，店铺收款码：-999901，增值服务：-999902，无规格商品（废弃，无规格也会生成一个SKU）：-999903，快捷收银：-999904，临时菜：-999905，整单打包：-999906，单个打包：-999907，加料：-999908，茶位费：-999909，自定义费用：-999910")
    private Long skuId;

    @ApiModelProperty(value = "商品ID", required = true)
    private String goodsId;

    @ApiModelProperty(value = "临时菜/商品名称")
    private String tempName;

    @ApiModelProperty(value = "临时菜/商品单价")
    private Long tempPrice;

    @ApiModelProperty(value = "商品的版本号（为商品的更新时间）")
    private Long merchandiseVersion;

    @ApiModelProperty(value = "商品数量")
    private Double nums;

    @ApiModelProperty(value = "商品行备注")
    private String remark;

    @ApiModelProperty(value = "是否赠品")
    private Boolean freeDishStatus;

    @ApiModelProperty(value = "是否打包该美食")
    private PackStatusEnum packStatus;

    @ApiModelProperty(value = "商品属性：套餐也有SKUID，每个组就是属性，组里面的商品就属性值")
    private List<T> itemAttrParamList;

    /**
     * 选中的skuValue的ID列表，对于组合型套餐，由于存在大量组合条件，而价格又是一致的，实际上存储的SKU只有一条记录
     * 这个时候传递skuValueId显得特别有意义，通过skuValueId能直接定位某个被选中的skuValue
     */
    @ApiModelProperty(value = "选中的skuValue的ID列表，对于组合型套餐，由于存在大量组合条件，而价格又是一致的，实际上存储的SKU只有一条记录<br/>这个时候传递skuValueId显得特别有意义，通过skuValueId能直接定位某个被选中的skuValue")
    private List<Long> skuValueIds;

    @ApiModelProperty(value = "是否加菜")
    private Boolean appendDishStatus;

    @ApiModelProperty(value = "是否接单")
    private Boolean recStatus;

    @ApiModelProperty(value = "是否已经打印")
    private Boolean printStatus;


    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getTempName() {
        return tempName;
    }

    public void setTempName(String tempName) {
        this.tempName = tempName;
    }

    public Long getTempPrice() {
        return tempPrice;
    }

    public void setTempPrice(Long tempPrice) {
        this.tempPrice = tempPrice;
    }

    public Long getMerchandiseVersion() {
        return merchandiseVersion;
    }

    public void setMerchandiseVersion(Long merchandiseVersion) {
        this.merchandiseVersion = merchandiseVersion;
    }

    public Double getNums() {
        return nums;
    }

    public void setNums(Double nums) {
        this.nums = nums;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getFreeDishStatus() {
        return freeDishStatus;
    }

    public void setFreeDishStatus(Boolean freeDishStatus) {
        this.freeDishStatus = freeDishStatus;
    }

    public PackStatusEnum getPackStatus() {
        return packStatus;
    }

    public void setPackStatus(PackStatusEnum packStatus) {
        this.packStatus = packStatus;
    }

    public List<T> getItemAttrParamList() {
        return itemAttrParamList;
    }

    public void setItemAttrParamList(List<T> itemAttrParamList) {
        this.itemAttrParamList = itemAttrParamList;
    }

    public List<Long> getSkuValueIds() {
        return skuValueIds;
    }

    public void setSkuValueIds(List<Long> skuValueIds) {
        this.skuValueIds = skuValueIds;
    }

    public Boolean getAppendDishStatus() {
        return appendDishStatus;
    }

    public void setAppendDishStatus(Boolean appendDishStatus) {
        this.appendDishStatus = appendDishStatus;
    }

    public Boolean getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(Boolean recStatus) {
        this.recStatus = recStatus;
    }

    public Boolean getPrintStatus() {
        return printStatus;
    }

    public void setPrintStatus(Boolean printStatus) {
        this.printStatus = printStatus;
    }
}
