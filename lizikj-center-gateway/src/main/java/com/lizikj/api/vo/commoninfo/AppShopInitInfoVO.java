package com.lizikj.api.vo.commoninfo;

import java.util.List;

import com.lizikj.api.vo.shop.H5QrCodeVO;
import com.lizikj.api.vo.shop.ShopSettingVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author dyh
 * @created at 2017 10 16 17:35
 */
@ApiModel(value = "app 店铺初始化信息")
public class AppShopInitInfoVO {

	@ApiModelProperty(value = "店铺设置")
	private List<ShopSettingVO> shopSettings;

	@ApiModelProperty(value = "服务端启动信息")
	private CommonInfoVO commonInfo;

	@ApiModelProperty(value = "h5二维码信息")
	private H5QrCodeVO h5QrCode;
	
	@ApiModelProperty(value = "菜单列表模板(JSON格式)")
	private String menuTemplate;

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
