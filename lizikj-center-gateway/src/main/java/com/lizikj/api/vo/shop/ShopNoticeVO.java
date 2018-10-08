package com.lizikj.api.vo.shop;

import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhoufe 
 * @date 2017年7月11日 下午3:16:30
 */
@ApiModel
public class ShopNoticeVO extends BaseDTO{

	private static final long serialVersionUID = 1L;

	/**
	 * 店铺公告ID
	 */
	@ApiModelProperty(value = "店铺公告ID", required = true)
	private Long shopNoticeId;
	
	/**
	 * 店铺ID
	 */
	@ApiModelProperty(value = "店铺ID", required = true)
	private Long shopId;
	
	/**
	 * 标题：可能为空
	 */
	@ApiModelProperty(value = "标题：可能为空", required = true)
	private String title;
	
	/**
	 * 公告内容
	 */
	@ApiModelProperty(value = "公告内容", required = true)
	private String content;

	public Long getShopNoticeId() {
		return shopNoticeId;
	}

	public void setShopNoticeId(Long shopNoticeId) {
		this.shopNoticeId = shopNoticeId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
