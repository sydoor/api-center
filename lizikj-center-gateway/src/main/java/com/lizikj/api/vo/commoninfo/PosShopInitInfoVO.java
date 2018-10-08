package com.lizikj.api.vo.commoninfo;

import java.util.List;

import com.lizikj.api.vo.merchandise.GoodsAndCategoryVO;
import com.lizikj.api.vo.merchant.MerchantPaymentConfigVO;
import com.lizikj.api.vo.shop.H5QrCodeVO;
import com.lizikj.api.vo.shop.ShopAreaVO;
import com.lizikj.api.vo.shop.ShopDeskVO;
import com.lizikj.api.vo.shop.ShopPrinterVO;
import com.lizikj.api.vo.shop.ShopSettingVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author dyh
 * @created at 2017 10 16 17:35
 */
@ApiModel(value = "pos 店铺初始化信息")
public class PosShopInitInfoVO {

	@ApiModelProperty(value = "店铺设置")
	private List<ShopSettingVO> shopSettings;

	@ApiModelProperty(value = "美食设置")
	private List<ShopSettingVO> merchandiseSettings;

	@ApiModelProperty(value = "会员设置")
	private List<ShopSettingVO> memberSettings;

	@ApiModelProperty(value = "营销配置")
	private List<ShopSettingVO> marketingSettings;

	@ApiModelProperty(value = "服务端启动信息")
	private CommonInfoVO commonInfo;

	@ApiModelProperty(value = "分类以及该分类下的商品")
	private GoodsAndCategoryVO goodsAndCategoryVO;

	@ApiModelProperty(value = "店铺打印机信息")
	private List<ShopPrinterVO> shopPrinterVOS;

	@ApiModelProperty(value = "店铺的支付通道信息")
	private List<MerchantPaymentConfigVO> merchantPaymentConfigs;

	@ApiModelProperty(value = "店铺的区域基础信息")
	private List<ShopAreaVO> shopAreas;

	@ApiModelProperty(value = "店铺的桌台基础信息")
	private List<ShopDeskVO> shopDesks;

	@ApiModelProperty(value = "h5二维码信息")
	private H5QrCodeVO h5QrCode;
	
	@ApiModelProperty(value = "菜单列表模板(JSON格式)")
	private String menuTemplate;

	public List<ShopPrinterVO> getShopPrinterVOS() {
		return shopPrinterVOS;
	}

	public void setShopPrinterVOS(List<ShopPrinterVO> shopPrinterVOS) {
		this.shopPrinterVOS = shopPrinterVOS;
	}

	public GoodsAndCategoryVO getGoodsAndCategoryVO() {
		return goodsAndCategoryVO;
	}

	public void setGoodsAndCategoryVO(GoodsAndCategoryVO goodsAndCategoryVO) {
		this.goodsAndCategoryVO = goodsAndCategoryVO;
	}

	public CommonInfoVO getCommonInfo() {
		return commonInfo;
	}

	public void setCommonInfo(CommonInfoVO commonInfo) {
		this.commonInfo = commonInfo;
	}

	public List<ShopSettingVO> getShopSettings() {
		return shopSettings;
	}

	public void setShopSettings(List<ShopSettingVO> shopSettings) {
		this.shopSettings = shopSettings;
	}

	public List<ShopSettingVO> getMerchandiseSettings() {
		return merchandiseSettings;
	}

	public void setMerchandiseSettings(List<ShopSettingVO> merchandiseSettings) {
		this.merchandiseSettings = merchandiseSettings;
	}

	public List<ShopSettingVO> getMemberSettings() {
		return memberSettings;
	}

	public void setMemberSettings(List<ShopSettingVO> memberSettings) {
		this.memberSettings = memberSettings;
	}

	public List<ShopSettingVO> getMarketingSettings() {
		return marketingSettings;
	}

	public void setMarketingSettings(List<ShopSettingVO> marketingSettings) {
		this.marketingSettings = marketingSettings;
	}

	public List<MerchantPaymentConfigVO> getMerchantPaymentConfigs() {
		return merchantPaymentConfigs;
	}

	public void setMerchantPaymentConfigs(List<MerchantPaymentConfigVO> merchantPaymentConfigs) {
		this.merchantPaymentConfigs = merchantPaymentConfigs;
	}

	public List<ShopAreaVO> getShopAreas() {
		return shopAreas;
	}

	public void setShopAreas(List<ShopAreaVO> shopAreas) {
		this.shopAreas = shopAreas;
	}

	public List<ShopDeskVO> getShopDesks() {
		return shopDesks;
	}

	public void setShopDesks(List<ShopDeskVO> shopDesks) {
		this.shopDesks = shopDesks;
	}

	public H5QrCodeVO getH5QrCode() {
		return h5QrCode;
	}

	public void setH5QrCode(H5QrCodeVO h5QrCode) {
		this.h5QrCode = h5QrCode;
	}

	public String getMenuTemplate() {
		return menuTemplate;
	}

	public void setMenuTemplate(String menuTemplate) {
		this.menuTemplate = menuTemplate;
	}

	
}
