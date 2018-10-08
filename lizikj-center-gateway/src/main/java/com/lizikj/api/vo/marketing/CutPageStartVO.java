package com.lizikj.api.vo.marketing;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 砍价活动页面发起端
 * 
 * @author lijundong
 * @date 2017年9月8日 下午3:37:56
 */
@ApiModel(value = "发起砍价活动页面对象")
public class CutPageStartVO {

	/**
	 * 砍价活动Id
	 */
	@ApiModelProperty(required = true, name = "cutId", value = "砍价活动Id", dataType = "Integer")
	private Long cutId;

	/**
	 * 是否发起人
	 */
	@ApiModelProperty(required = true, name = "isSponsor", value = "是否发起人", dataType = "Boolean")
	private Boolean isSponsor = true;

	/**
	 * 砍价模式：1=整单砍价，2=单品砍价
	 */
	@ApiModelProperty(required = true, name = "cutMode", value = "砍价模式：1.整单砍价；2.单品砍价", dataType = "Integer")
	private Byte cutMode;

	/**
	 * 店铺名称
	 */
	@ApiModelProperty(required = true, name = "shopName", value = "店铺名称", dataType = "String")
	private String shopName;

	/**
	 * 店铺logo, 七牛Id
	 */
	@ApiModelProperty(required = true, name = "shopLogo", value = "店铺logo, 七牛获取", dataType = "Long")
	private Long shopLogo;

	/**
	 * 店铺地址
	 */
	@ApiModelProperty(required = true, name = "shopAddress", value = "店铺地址", dataType = "String")
	private String shopAddress;

	/**
	 * 原价
	 */
	@ApiModelProperty(required = true, name = "sellPrice", value = "原价", dataType = "Long")
	private long sellPrice;

	/**
	 * 当前剁手价
	 */
	@ApiModelProperty(required = true, name = "currentPrice", value = "当前剁手价", dataType = "Long")
	private long currentPrice;

	/**
	 * 美食名称
	 */
	@ApiModelProperty(required = true, name = "goodsName", value = "美食名称, 当cutMode=2时有值", dataType = "String")
	private String goodsName;

	/**
	 * 可砍价次数
	 */
	@ApiModelProperty(required = true, name = "allCutNum", value = "可砍价次数", dataType = "Integer")
	private int allCutNum;

	/**
	 * 当前已砍次数
	 */
	@ApiModelProperty(required = true, name = "currentCutNum", value = "当前已砍次数", dataType = "Integer")
	private int currentCutNum;

	/**
	 * 已砍金额
	 */
	@ApiModelProperty(required = true, name = "cutAmount", value = "已砍金额", dataType = "Integer")
	private long cutAmount;

	/**
	 * 已砍比例
	 */
	@ApiModelProperty(required = true, name = "cutPercent", value = "已砍比例", dataType = "String")
	private String cutPercent;

	@ApiModelProperty(required = true, name = "firendList", value = "真爱榜单", dataType = "Array")
	private List<FirendUserVO> firendList;

	public Long getCutId() {
		return cutId;
	}

	public void setCutId(Long cutId) {
		this.cutId = cutId;
	}

	public Boolean getIsSponsor() {
		return isSponsor;
	}

	public void setIsSponsor(Boolean isSponsor) {
		this.isSponsor = isSponsor;
	}

	public Byte getCutMode() {
		return cutMode;
	}

	public void setCutMode(Byte cutMode) {
		this.cutMode = cutMode;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Long getShopLogo() {
		return shopLogo;
	}

	public void setShopLogo(Long shopLogo) {
		this.shopLogo = shopLogo;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	public long getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(long sellPrice) {
		this.sellPrice = sellPrice;
	}

	public long getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(long currentPrice) {
		this.currentPrice = currentPrice;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public int getAllCutNum() {
		return allCutNum;
	}

	public void setAllCutNum(int allCutNum) {
		this.allCutNum = allCutNum;
	}

	public int getCurrentCutNum() {
		return currentCutNum;
	}

	public void setCurrentCutNum(int currentCutNum) {
		this.currentCutNum = currentCutNum;
	}

	public long getCutAmount() {
		return cutAmount;
	}

	public void setCutAmount(long cutAmount) {
		this.cutAmount = cutAmount;
	}

	public List<FirendUserVO> getFirendList() {
		return firendList;
	}

	public void setFirendList(List<FirendUserVO> firendList) {
		this.firendList = firendList;
	}

	public String getCutPercent() {
		return cutPercent;
	}

	public void setCutPercent(String cutPercent) {
		this.cutPercent = cutPercent;
	}
}
