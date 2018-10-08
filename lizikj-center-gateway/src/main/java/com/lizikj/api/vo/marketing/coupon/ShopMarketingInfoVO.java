package com.lizikj.api.vo.marketing.coupon;

import java.util.List;

import com.lizikj.marketing.api.enums.DinerCountTypeEnum;
import com.lizikj.marketing.api.enums.ShopOpenTimeTypeEnum;
import com.lizikj.merchant.enums.ShopHotStatusEnum;
import com.lizikj.merchant.enums.ShopMarketingEnableStatusEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 门店营销信息详情
 * 
 * @author liaojw 
 * @date 2018年6月28日 下午2:27:33
 */
@ApiModel
public class ShopMarketingInfoVO {
	@ApiModelProperty(value = "门店ID")
	private Long shopId;
	
	@ApiModelProperty(value = "商户ID")
	private Long merchantId;
	
	/**
	 * 商户名称
	 */
	@ApiModelProperty(value = "商户名称")
	private String merchantName;
	
	/**
	 * logo
	 */
	@ApiModelProperty(value = "logo")
	private Long logoId;
	
	/**
	 * 门店图片
	 */
	@ApiModelProperty(value = "门店图片")
	private List<Long> shopImgs;
	
	/**
	 * 省
	 */
	@ApiModelProperty(value = "省")
	private String provinceName;
	
	/**
	 * 市
	 */
	@ApiModelProperty(value = "市")
	private String cityName;
	
	/**
	 * 区
	 */
	@ApiModelProperty(value = "区")
	private String regionName;
	
	/**
	 * 详情地址
	 */
	@ApiModelProperty(value = "详情地址")
	private String address;
	
	/**
	 * 营销开关
	 */
	@ApiModelProperty(value = "营销开关")
	private ShopMarketingEnableStatusEnum marketingEnableStatus;
	
	/**
	 * 门店名称
	 */
	@ApiModelProperty(value = "门店名称")
	private String shopName;
	
	/**
	 * 门店餐饮标签
	 */
	@ApiModelProperty(value = "门店餐饮标签")
	private List<ShopMarketingCateringTagVO> cateringTags;
	
	/**
	 * 人均最低价格
	 */
	@ApiModelProperty(value = "人均最低价格")
	private Long perCapitaMinPrice;
	
	/**
	 * 人均最高价格
	 */
	@ApiModelProperty(value = "人均最高价格")
	private Long perCapitaMaxPrice;
	
	/**
	 * 商圈区
	 */
	@ApiModelProperty(value = "商圈区")
	private Long tradeAreaId;
	
	/**
	 * 商圈名称
	 */
	@ApiModelProperty(value = "商圈名称")
	private String tradeAreaName;
	
	/**
	 * 经营时段类型
	 */
	@ApiModelProperty(value = "经营时段类型")
	private List<ShopOpenTimeTypeEnum> openTimeTypes;
	
	/**
	 * 用餐人数类型
	 */
	@ApiModelProperty(value = "用餐人数类型")
	private List<DinerCountTypeEnum> dinerCountTypes;
	
	/**
	 * 热门状态
	 */
	@ApiModelProperty(value = "热门状态")
	private ShopHotStatusEnum hotStatus;
	
	/**
	 * 是否自动通过评论
	 */
	@ApiModelProperty(value = "是否自动通过评论")
	private Boolean autoPassComment;
	
	
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private int sort;
	
	/**
	 * 人气数
	 */
	@ApiModelProperty(value = "人气数")
	private int hotValue;
	
	/**
	 * 综合评分
	 */
	@ApiModelProperty(value = "综合评分")
	private Double avgScore;
	
	/**
	 * 是否支持用券
	 */
	@ApiModelProperty(value = "是否支持用券")
	private Boolean supportCoupon;
	
	/**
	 * 用券时间段
	 */
	@ApiModelProperty(value = "用券时间段")
	private List<TimeSectionModelVO> couponTimes;
	
	/**
	 * 联系电话
	 */
	@ApiModelProperty(value = "联系电话")
	private List<String> tels;
	
	/**
	 * 营业时间段
	 */
	@ApiModelProperty(value = "营业时间段")
	private List<TimeSectionModelVO> openTimes;
	
	/**
	 * 协议ID
	 */
	@ApiModelProperty(value = "协议ID")
	private Long fileId;
	
	@ApiModelProperty(value = "特色美食列表")
	private List<ShopMerchandiseTagVO> merchandiseTags;

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public ShopMarketingEnableStatusEnum getMarketingEnableStatus() {
		return marketingEnableStatus;
	}

	public void setMarketingEnableStatus(ShopMarketingEnableStatusEnum marketingEnableStatus) {
		this.marketingEnableStatus = marketingEnableStatus;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public List<ShopMarketingCateringTagVO> getCateringTags() {
		return cateringTags;
	}

	public void setCateringTags(List<ShopMarketingCateringTagVO> cateringTags) {
		this.cateringTags = cateringTags;
	}

	public Long getPerCapitaMinPrice() {
		return perCapitaMinPrice;
	}

	public void setPerCapitaMinPrice(Long perCapitaMinPrice) {
		this.perCapitaMinPrice = perCapitaMinPrice;
	}

	public Long getPerCapitaMaxPrice() {
		return perCapitaMaxPrice;
	}

	public void setPerCapitaMaxPrice(Long perCapitaMaxPrice) {
		this.perCapitaMaxPrice = perCapitaMaxPrice;
	}

	public Long getTradeAreaId() {
		return tradeAreaId;
	}

	public void setTradeAreaId(Long tradeAreaId) {
		this.tradeAreaId = tradeAreaId;
	}

	public String getTradeAreaName() {
		return tradeAreaName;
	}

	public void setTradeAreaName(String tradeAreaName) {
		this.tradeAreaName = tradeAreaName;
	}

	public List<ShopOpenTimeTypeEnum> getOpenTimeTypes() {
		return openTimeTypes;
	}

	public void setOpenTimeTypes(List<ShopOpenTimeTypeEnum> openTimeTypes) {
		this.openTimeTypes = openTimeTypes;
	}

	public List<DinerCountTypeEnum> getDinerCountTypes() {
		return dinerCountTypes;
	}

	public void setDinerCountTypes(List<DinerCountTypeEnum> dinerCountTypes) {
		this.dinerCountTypes = dinerCountTypes;
	}

	public ShopHotStatusEnum getHotStatus() {
		return hotStatus;
	}

	public void setHotStatus(ShopHotStatusEnum hotStatus) {
		this.hotStatus = hotStatus;
	}

	public Boolean getAutoPassComment() {
		return autoPassComment;
	}

	public void setAutoPassComment(Boolean autoPassComment) {
		this.autoPassComment = autoPassComment;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getHotValue() {
		return hotValue;
	}

	public void setHotValue(int hotValue) {
		this.hotValue = hotValue;
	}

	public Double getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(Double avgScore) {
		this.avgScore = avgScore;
	}

	public Boolean getSupportCoupon() {
		return supportCoupon;
	}

	public void setSupportCoupon(Boolean supportCoupon) {
		this.supportCoupon = supportCoupon;
	}

	public List<TimeSectionModelVO> getCouponTimes() {
		return couponTimes;
	}

	public void setCouponTimes(List<TimeSectionModelVO> couponTimes) {
		this.couponTimes = couponTimes;
	}

	public List<String> getTels() {
		return tels;
	}

	public void setTels(List<String> tels) {
		this.tels = tels;
	}

	public List<TimeSectionModelVO> getOpenTimes() {
		return openTimes;
	}

	public void setOpenTimes(List<TimeSectionModelVO> openTimes) {
		this.openTimes = openTimes;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public List<ShopMerchandiseTagVO> getMerchandiseTags() {
		return merchandiseTags;
	}

	public void setMerchandiseTags(List<ShopMerchandiseTagVO> merchandiseTags) {
		this.merchandiseTags = merchandiseTags;
	}

	public Long getLogoId() {
		return logoId;
	}

	public void setLogoId(Long logoId) {
		this.logoId = logoId;
	}

	public List<Long> getShopImgs() {
		return shopImgs;
	}

	public void setShopImgs(List<Long> shopImgs) {
		this.shopImgs = shopImgs;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
