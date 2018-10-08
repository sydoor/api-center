package com.lizikj.api.vo.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 参与砍价活动页面对象
 * 
 * @author lijundong
 * @date 2017年9月8日 下午3:37:56
 */
@ApiModel(value = "参与砍价活动页面对象")
public class CutPageJoinVO {

	/**
	 * 砍价活动Id
	 */
	@ApiModelProperty(required = true, name = "cutId", value = "砍价活动Id", dataType = "Long")
	private Long cutId;

	/**
	 * 是否发起人
	 */
	@ApiModelProperty(required = true, name = "isSponsor", value = "是否发起人", dataType = "Boolean")
	private Boolean isSponsor = false;

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
	 * 好友昵称
	 */
	@ApiModelProperty(required = true, name = "nickname", value = "好友昵称", dataType = "String")
	private String nickname;

	/**
	 * 好友头像
	 */
	@ApiModelProperty(required = true, name = "headimg", value = "好友头像", dataType = "String")
	private String headimg;

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
	 * 总已砍金额
	 */
	@ApiModelProperty(required = true, name = "allCutAmount", value = "总已砍金额", dataType = "Integer")
	private long allCutAmount;

	/**
	 * 已砍比例
	 */
	@ApiModelProperty(required = true, name = "cutPercent", value = "已砍比例", dataType = "String")
	private String cutPercent;

	/**
	 * 是否已帮忙砍价
	 */
	@ApiModelProperty(required = true, name = "isJoin", value = "是否已帮忙砍价", dataType = "Boolean")
	private boolean isJoin;

	/**
	 * 当前已帮忙砍金额
	 */
	@ApiModelProperty(required = true, name = "currentCutAmount", value = "当前已帮忙砍金额", dataType = "Integer")
	private long currentCutAmount;

	/**
	 * 整单砍价信息，当cutMode=1时有值
	 */
	@ApiModelProperty(required = true, name = "whole", value = "整单砍价信息，当cutMode=1时有值", dataType = "Object")
	private CutPriceWholePageVO whole;

	/**
	 * 单品砍价信息，当cutMode=2时有值
	 */
	@ApiModelProperty(required = true, name = "single", value = "单品砍价信息，当cutMode=2时有值", dataType = "Object")
	private CutPriceSinglePageVO single;

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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
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

	public long getAllCutAmount() {
		return allCutAmount;
	}

	public void setAllCutAmount(long allCutAmount) {
		this.allCutAmount = allCutAmount;
	}

	public String getCutPercent() {
		return cutPercent;
	}

	public void setCutPercent(String cutPercent) {
		this.cutPercent = cutPercent;
	}

	public boolean isJoin() {
		return isJoin;
	}

	public void setJoin(boolean isJoin) {
		this.isJoin = isJoin;
	}

	public long getCurrentCutAmount() {
		return currentCutAmount;
	}

	public void setCurrentCutAmount(long currentCutAmount) {
		this.currentCutAmount = currentCutAmount;
	}

	public CutPriceWholePageVO getWhole() {
		return whole;
	}

	public void setWhole(CutPriceWholePageVO whole) {
		this.whole = whole;
	}

	public CutPriceSinglePageVO getSingle() {
		return single;
	}

	public void setSingle(CutPriceSinglePageVO single) {
		this.single = single;
	}

}
