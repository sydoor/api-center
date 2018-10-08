package com.lizikj.api.vo.shop;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class H5QrCodeVO {

	/**
	 * 店铺付款码url
	 */
	@ApiModelProperty(value = "店铺付款码url")
	private String deskPayCodeUrl;

	/**
	 * 扫码取号url
	 */
	@ApiModelProperty(value = "扫码取号url")
	private String scavengingNumberUrl;

	/**
	 * 扫码点餐url
	 */
	@ApiModelProperty(value = "扫码点餐url：要自己拼接桌台id")
	private String scavengingOrderUrl;

	@ApiModelProperty(value = "订单小票二维码：自己拼接orderNo")
	private String orderTicketPayUrl;

	public H5QrCodeVO(String deskPayCodeUrl, String scavengingNumberUrl, String scavengingOrderUrl,
			String orderTicketPayUrl) {
		super();
		this.deskPayCodeUrl = deskPayCodeUrl;
		this.scavengingNumberUrl = scavengingNumberUrl;
		this.scavengingOrderUrl = scavengingOrderUrl;
		this.orderTicketPayUrl = orderTicketPayUrl;
	}

	public String getDeskPayCodeUrl() {
		return deskPayCodeUrl;
	}

	public void setDeskPayCodeUrl(String deskPayCodeUrl) {
		this.deskPayCodeUrl = deskPayCodeUrl;
	}

	public String getScavengingNumberUrl() {
		return scavengingNumberUrl;
	}

	public void setScavengingNumberUrl(String scavengingNumberUrl) {
		this.scavengingNumberUrl = scavengingNumberUrl;
	}

	public String getScavengingOrderUrl() {
		return scavengingOrderUrl;
	}

	public void setScavengingOrderUrl(String scavengingOrderUrl) {
		this.scavengingOrderUrl = scavengingOrderUrl;
	}

	public String getOrderTicketPayUrl() {
		return orderTicketPayUrl;
	}

	public void setOrderTicketPayUrl(String orderTicketPayUrl) {
		this.orderTicketPayUrl = orderTicketPayUrl;
	}

}
