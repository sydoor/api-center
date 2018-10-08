package com.lizikj.api.vo.shop;

import java.util.Date;
import java.util.List;

import com.lizikj.common.enums.RemoveStatusEnum;
import com.lizikj.shop.api.enums.DefaultStatusEnum;
import com.lizikj.shop.api.enums.ShopPrinterConectStatusEnum;
import com.lizikj.shop.api.enums.ShopPrinterConectTypeEnum;
import com.lizikj.shop.api.enums.ShopPrinterTypeEnum;
import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Model for table -- shop_printer
 * Created by zhoufe on 2017-07-20 15:01:56
 */
@ApiModel
public class ShopPrinterVO extends BaseDTO {
   
	private static final long serialVersionUID = 1L;

	/**
     * 店铺打印机ID
     */
	@ApiModelProperty(value = "店铺打印机ID", required = true)
    private Long printerId;

    /**
     * 店铺ID
     */
	@ApiModelProperty(value = "店铺ID", required = true)
    private Long shopId;

    /**
     * 打印类型：1000.厨房打印；1001.标签打印；1002.配菜打印；打印小票的书写格式区别
     */
	@ApiModelProperty(value = "打印类型：见ShopPrinterTypeEnum：KITCHEN.厨房打印；TAG.标签打印；DISH.配菜打印；打印小票的书写格式区别。", required = true)
    @Deprecated
    private ShopPrinterTypeEnum type;

    /**
     * 打印机名称
     */
	@ApiModelProperty(value = "打印机名称", required = true)
    private String name;

    /**
     * 连接方式：2001.WIFI；2002.蓝牙
     */
	@ApiModelProperty(value = "连接方式：WIFI.WIFI；BLUETOOTH.蓝牙。", required = true)
    @Deprecated
    private ShopPrinterConectTypeEnum conectType;

    /**
     * 连接状态：1.连接中 ；2.已连接；3.已断开 ；4.NONE
     */
	@ApiModelProperty(value = "连接状态：见ShopPrinterConectStatusEnum：CONNECTING.连接中；CENNECTED.已连接；BROKEN.已断开；NONE.无设置。", required = true)
    @Deprecated
    private ShopPrinterConectStatusEnum conectStatus;

    /**
     * WIFI打印机IP地址
     */
	@ApiModelProperty(value = "WIFI打印机IP地址", required = true)
    @Deprecated
    private String wifiIpAddress;

    /**
     * WIFI打印机端口
     */
	@ApiModelProperty(value = "WIFI打印机端口", required = true)
    @Deprecated
    private Integer wifiPort;

    /**
     * 打印机品牌：  4001.佳博 ；4002 .芯烨
     */
	@ApiModelProperty(value = "打印机品牌：  4001.佳博 ；4002 .芯烨。", required = true)
    @Deprecated
    private String brand;

    /**
     * 小票尺寸：1.80mm，2.58mm
     */
	@ApiModelProperty(value = "小票尺寸：1.80mm，2.58mm；乘以100传入。", required = true)
    @Deprecated
    private Integer ticketSize;

    /**
     * 是否默认：1.是默认，2.非默认；如果订单的商品没找到配置的打印机就用默认的打印机
     */
	@ApiModelProperty(value = "否默认：见：DefaultStatusEnum：DEFAULT.是默认，UN_DEFAULT.非默认；如果订单的商品没找到配置的打印机就用默认的打印机。", required = true)
    @Deprecated
    private DefaultStatusEnum defaultStatus;
    
    /**
     * 打印机需要打印的商品
     */
	@ApiModelProperty(value = "打印机需要打印的商品", required = true)
    @Deprecated
    private List<String> productIds;

    /**
     * 李子平台打印的商品
     */
    @ApiModelProperty(value = "可以存json。李子平台打印的商品：新版本用此字段，老版本用productIds，前端兼容", required = true)
    @Deprecated
    private String liziProducts;

    /**
     * 美团平台打印机的商品
     */
    @ApiModelProperty(value = "可以存json。美团平台打印机的商品", required = true)
    @Deprecated
    private String meituanProducts;

    /**
     * 饿了么平台打印机的商品
     */
    @ApiModelProperty(value = "可以存json。饿了么平台打印机的商品", required = true)
    @Deprecated
    private String elemeProducts;

    /**
     * 是否删除:1.删除；0.未删除
     */
    @ApiModelProperty(value = "是否删除：见RemoveStatusEnum。", required = true)
    @Deprecated
    private RemoveStatusEnum removeStatus;


    @ApiModelProperty(value = "打印方式：见：ShopPrintWayEnum： 0.默认，1.热敏打印, 2.针式打印。", required = true)
    @Deprecated
    private Integer printWay;

    /**
     * 打印机配置内容
     */
    @ApiModelProperty(value = "打印机配置内容--json内容，由客户端负责解析", required = true)
    private String content;

    @ApiModelProperty(value = "更新时间", required = true)
    private Date updateTime;

    /**
     * 店铺打印机ID
     */
    public Long getPrinterId() {
        return printerId;
    }

    /**
     * 店铺打印机ID
     * @param printerId the value for shop_printer.printer_id
     */
    public void setPrinterId(Long printerId) {
        this.printerId = printerId;
    }

    /**
     * 店铺ID
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 店铺ID
     * @param shopId the value for shop_printer.shop_id
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 打印机名称
     */
    public String getName() {
        return name;
    }

    /**
     * 打印机名称
     * @param name the value for shop_printer.name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * WIFI打印机IP地址
     */
    public String getWifiIpAddress() {
        return wifiIpAddress;
    }

    /**
     * WIFI打印机IP地址
     * @param wifiIpAddress the value for shop_printer.wifi_ip_address
     */
    public void setWifiIpAddress(String wifiIpAddress) {
        this.wifiIpAddress = wifiIpAddress == null ? null : wifiIpAddress.trim();
    }

    /**
     * WIFI打印机端口
     */
    public Integer getWifiPort() {
        return wifiPort;
    }

    /**
     * WIFI打印机端口
     * @param wifiPort the value for shop_printer.wifi_port
     */
    public void setWifiPort(Integer wifiPort) {
        this.wifiPort = wifiPort;
    }

    /**
     * 打印机品牌：  4001.佳博 ；4002 .芯烨
     */
    public String getBrand() {
        return brand;
    }

    /**
     * 打印机品牌：  4001.佳博 ；4002 .芯烨
     * @param brand the value for shop_printer.brand
     */
    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    /**
     * 小票尺寸：1.80mm，2.58mm
     */
    public Integer getTicketSize() {
        return ticketSize;
    }

    /**
     * 小票尺寸：1.80mm，2.58mm
     * @param ticketSize the value for shop_printer.ticket_size
     */
    public void setTicketSize(Integer ticketSize) {
        this.ticketSize = ticketSize;
    }

	/**
	 * @return the productIds
	 */
	public List<String> getProductIds() {
		return productIds;
	}

	/**
	 * @param productIds the productIds to set
	 */
	public void setProductIds(List<String> productIds) {
		this.productIds = productIds;
	}

    public ShopPrinterTypeEnum getType() {
        return type;
    }

    public void setType(ShopPrinterTypeEnum type) {
        this.type = type;
    }

    public ShopPrinterConectTypeEnum getConectType() {
        return conectType;
    }

    public void setConectType(ShopPrinterConectTypeEnum conectType) {
        this.conectType = conectType;
    }

    public ShopPrinterConectStatusEnum getConectStatus() {
        return conectStatus;
    }

    public void setConectStatus(ShopPrinterConectStatusEnum conectStatus) {
        this.conectStatus = conectStatus;
    }

    public DefaultStatusEnum getDefaultStatus() {
        return defaultStatus;
    }

    public void setDefaultStatus(DefaultStatusEnum defaultStatus) {
        this.defaultStatus = defaultStatus;
    }

    public String getLiziProducts() {
        return liziProducts;
    }

    public void setLiziProducts(String liziProducts) {
        this.liziProducts = liziProducts;
    }

    public String getMeituanProducts() {
        return meituanProducts;
    }

    public void setMeituanProducts(String meituanProducts) {
        this.meituanProducts = meituanProducts;
    }

    public String getElemeProducts() {
        return elemeProducts;
    }

    public void setElemeProducts(String elemeProducts) {
        this.elemeProducts = elemeProducts;
    }

    public RemoveStatusEnum getRemoveStatus() {
        return removeStatus;
    }

    public void setRemoveStatus(RemoveStatusEnum removeStatus) {
        this.removeStatus = removeStatus;
    }

    public Integer getPrintWay() {
        return printWay;
    }

    public void setPrintWay(Integer printWay) {
        this.printWay = printWay;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}