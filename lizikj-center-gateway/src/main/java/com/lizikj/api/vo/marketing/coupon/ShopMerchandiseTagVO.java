package com.lizikj.api.vo.marketing.coupon;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 门店特色美食标签
 * 
 * @author liaojw 
 * @date 2018年7月11日 下午2:15:52
 */
@ApiModel
public class ShopMerchandiseTagVO {
	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	private String tagId;
	
	/**
	 * 美食系统ID
	 */
	@ApiModelProperty(value = "美食系统ID")
	private Long merchandiseId;
	
	/**
	 * 图片ID
	 */
	@ApiModelProperty(value = "图片ID")
	private List<Long> imgIds;
	
	/**
	 * 标签名称
	 */
	@ApiModelProperty(value = "标签名称")
	private String merchandiseName;
	
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remark;
	
	
	public String getTagId() {
		return tagId;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getMerchandiseId() {
		return merchandiseId;
	}
	public void setMerchandiseId(Long merchandiseId) {
		this.merchandiseId = merchandiseId;
	}
	public String getMerchandiseName() {
		return merchandiseName;
	}
	public void setMerchandiseName(String merchandiseName) {
		this.merchandiseName = merchandiseName;
	}
	public List<Long> getImgIds() {
		return imgIds;
	}
	public void setImgIds(List<Long> imgIds) {
		this.imgIds = imgIds;
	}
	
	
}
