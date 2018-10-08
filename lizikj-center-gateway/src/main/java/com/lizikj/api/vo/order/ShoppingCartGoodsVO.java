package com.lizikj.api.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author Michael.Huang
 * @date 2017/8/17 17:10
 */
@ApiModel
public class ShoppingCartGoodsVO {

    /**
     * 自增ID
     */
    @ApiModelProperty(name = "shoppingCartGoodsId", value = "购物车物品数据ID,从购物车中删除时传递此ID")
    private Long shoppingCartGoodsId;

    /**
     * 购物车ID
     */
    @ApiModelProperty(name = "shoppingCartId", value = "购物车ID")
    private Long shoppingCartId;

    /**
     * 物品id
     */
    @ApiModelProperty(name = "goodsId", value = "物品id")
    private String goodsId;

    /**
     * 物品名称，冗余
     */
    @ApiModelProperty(name = "goodsName", value = "物品名称，冗余")
    private String goodsName;

    /**
     * 商品类型：1000.增值服务商品，2000.普通商品，3000.套餐，4000.费用商品，5000.临时菜，6000.赠菜，7000.临时赠菜，8000.加料
     */
    @ApiModelProperty(name = "goodsType", value = "商品类型：2000.普通商品，3000.套餐，5000.临时菜，6000.赠菜，7000.临时赠菜")
    private Integer goodsType;

    /**
     * sku Id
     */
    @ApiModelProperty(name = "skuId", value = "skuId")
    private Integer skuId;

//    /**
//     * 属性信息：goods_attr和value的信息json格式的集中存放；如：[{"attr_id":"10001","attr_name":"组1（5选2）","attr_value":[{"attr_value_id":"20001","attr_value_name":"牛肉","nums":"1"},{"attr_value_id":"20002","attr_value_name":"鸡肉","nums":"2"}]},{"attr_id":"10002","attr_name":"口味","attr_value":[{"attr_value_id":"20003","attr_value_name":"不放辣","nums":"1"},{"attr_value_id":"20004","attr_value_name":"不放糖","nums":"1"}]},{"attr_id":"10003","attr_name":"做法","attr_value":[{"attr_value_id":"20005","attr_value_name":"凉拌","nums":"1"},{"attr_value_id":"20006","attr_value_name":"清蒸","nums":"1"}]},{"attr_id":"10004","attr_name":"自定义备注","attr_value":[{"attr_value_id":"20006","attr_value_name":"备注1","nums":"1"},{"attr_value_id":"20007","attr_value_name":"备注2","nums":"1"}]}]
//     */
//    @ApiModelProperty(name = "weightStatus",value = "购物车物品数据ID,从购物车中删除时传递此ID")
//    private String goodsAttr;

    @ApiModelProperty(value = "商品属性：套餐也有SKUID，每个组就是属性，组里面的商品就属性值")
    private List<ShoppingCartGoodsAttrVO> goodsAttrList;
    /**
     * 是否计重菜：1.是，0.否
     */
    @ApiModelProperty(name = "weightStatus", value = "是否计重菜：1.是，0.否")
    private Byte weightStatus;

    /**
     * 重量
     */
    @ApiModelProperty(name = "weight", value = "重量，记重菜时此值有效")
    private Long weight;

    /**
     * 数量
     */
    @ApiModelProperty(name = "nums", value = "数量，非记重菜时此值有效")
    private Long nums;

    /**
     * 成本价
     */
    @ApiModelProperty(name = "costAmount", value = "成本价")
    private Long costAmount;

    /**
     * 单价：1个商品的价格
     */
    @ApiModelProperty(name = "saleAmount", value = "单价：1个商品的价格")
    private Long saleAmount;

    /**
     * 总价
     */
    @ApiModelProperty(name = "totalAmount", value = "总价")
    private Long totalAmount;

    /**
     * 图片id 冗余
     */
    @ApiModelProperty(name = "mediaId", value = "图片id 冗余")
    private Long mediaId;


    public Long getShoppingCartGoodsId() {
        return shoppingCartGoodsId;
    }

    public void setShoppingCartGoodsId(Long shoppingCartGoodsId) {
        this.shoppingCartGoodsId = shoppingCartGoodsId;
    }

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public List<ShoppingCartGoodsAttrVO> getGoodsAttrList() {
        return goodsAttrList;
    }

    public void setGoodsAttrList(List<ShoppingCartGoodsAttrVO> goodsAttrList) {
        this.goodsAttrList = goodsAttrList;
    }

    public Byte getWeightStatus() {
        return weightStatus;
    }

    public void setWeightStatus(Byte weightStatus) {
        this.weightStatus = weightStatus;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Long getNums() {
        return nums;
    }

    public void setNums(Long nums) {
        this.nums = nums;
    }

    public Long getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(Long costAmount) {
        this.costAmount = costAmount;
    }

    public Long getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(Long saleAmount) {
        this.saleAmount = saleAmount;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }
}
