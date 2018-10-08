package com.lizikj.api.vo.merchandise.param;

import java.util.List;

import com.lizikj.merchandise.enums.ShelveStateEnum;
import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author zhoufe 
 * @date 2017年7月12日 下午4:46:59
 */
@ApiModel
public class ChainGoodsParamVO extends BaseDTO {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 美食模板id
	 */
	@ApiModelProperty(value = "美食模板ID，新增美食模板时不用传", required = true)
    private String id;
	
	/**
     * 图片列表
     */
	@ApiModelProperty(value = "图片列表", required = true)
    private List<Long> mediaIds;
    
    /**
     * 物品名称
     */
	@ApiModelProperty(value = "物品名称", required = true)
    private String goodsName;
    
    /**
     * 分类ID
     */
	@ApiModelProperty(value = "分类ID", required = true)
    private List<String> categoryIds;
	
	/**
	 * 上下架状态：见ShelveStateEnum
	 */
	@ApiModelProperty(value = "上下架状态：见ShelveStateEnum", required = true)
    private ShelveStateEnum shelveState;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Long> getMediaIds() {
		return mediaIds;
	}

	public void setMediaIds(List<Long> mediaIds) {
		this.mediaIds = mediaIds;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public List<String> getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(List<String> categoryIds) {
		this.categoryIds = categoryIds;
	}

	public ShelveStateEnum getShelveState() {
		return shelveState;
	}

	public void setShelveState(ShelveStateEnum shelveState) {
		this.shelveState = shelveState;
	}
    
    

}
