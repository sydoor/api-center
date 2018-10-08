package com.lizikj.api.vo.merchandise.param;

import com.lizikj.common.enums.VirtualEnum;
import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 标签
 * @author zhoufe
 * @date 2017/6/22
 */
@ApiModel
public class TagParamVO extends BaseDTO{
	
	private static final long serialVersionUID = 1L;

	/**
     * id
     */
	@ApiModelProperty(value = "标签id", required = true)
    private String id;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    private Long shopId;
    /**
     * 名称
     */
	@ApiModelProperty(value = "名称", required = true)
    private String name;


    /**
     * 排序
     */
	@ApiModelProperty(value = "排序", required = true)
    private Integer order;


    /**
     * 该标签下有的商品ID
     */
	@ApiModelProperty(value = "该标签下有的商品ID", required = true)
    List<String> goodsIds;

    private VirtualEnum virtual;

    public VirtualEnum getVirtual() {
        return virtual;
    }

    public void setVirtual(VirtualEnum virtual) {
        this.virtual = virtual;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public List<String> getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(List<String> goodsIds) {
        this.goodsIds = goodsIds;
    }
}
