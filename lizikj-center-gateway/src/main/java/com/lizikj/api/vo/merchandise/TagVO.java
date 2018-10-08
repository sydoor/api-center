package com.lizikj.api.vo.merchandise;

import java.util.List;

import com.lizikj.common.enums.VirtualEnum;
import com.lizikj.merchandise.enums.TagCodeEnum;
import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 标签
 * @author zhoufe
 * @date 2017/6/22
 */
@ApiModel
public class TagVO extends BaseDTO{
	
	private static final long serialVersionUID = 1L;

	/**
     * id
     */
	@ApiModelProperty(value = "标签id", required = true)
    private String id;

    /**
     * 名称
     */
	@ApiModelProperty(value = "名称", required = true)
    private String name;

	@ApiModelProperty(value = "code",required = true)
    private TagCodeEnum code;
    /**
     * 店铺id
     */
	@ApiModelProperty(value = "店铺id", required = true)
    private Long shopId;

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

    public TagCodeEnum getCode() {
        return code;
    }

    public void setCode(TagCodeEnum code) {
        this.code = code;
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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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
