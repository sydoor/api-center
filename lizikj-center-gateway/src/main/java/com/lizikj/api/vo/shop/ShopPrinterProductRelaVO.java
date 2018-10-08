package com.lizikj.api.vo.shop;

import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Model for table -- shop_printer_product_rela
 * Created by zhoufe on 2017-07-20 15:01:56
 */
@ApiModel
public class ShopPrinterProductRelaVO extends BaseDTO {
    
	private static final long serialVersionUID = 1L;

	/**
     * 打印机与商品关系ID
     */
	@ApiModelProperty(value = "打印机与商品关系ID", required = true)
    private Long shopPrinterProductRelaId;

    /**
     * 店铺打印机ID
     */
	@ApiModelProperty(value = "店铺打印机ID", required = true)
    private Long printerId;

    /**
     * 商品ID
     */
	@ApiModelProperty(value = "商品ID", required = true)
    private String productId;

    /**
     * 打印机与商品关系ID
     */
    public Long getShopPrinterProductRelaId() {
        return shopPrinterProductRelaId;
    }

    /**
     * 打印机与商品关系ID
     * @param shopPrinterProductRelaId the value for shop_printer_product_rela.shop_printer_product_rela_id
     */
    public void setShopPrinterProductRelaId(Long shopPrinterProductRelaId) {
        this.shopPrinterProductRelaId = shopPrinterProductRelaId;
    }

    /**
     * 店铺打印机ID
     */
    public Long getPrinterId() {
        return printerId;
    }

    /**
     * 店铺打印机ID
     * @param printerId the value for shop_printer_product_rela.printer_id
     */
    public void setPrinterId(Long printerId) {
        this.printerId = printerId;
    }

    /**
     * 商品ID
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 商品ID
     * @param productId the value for shop_printer_product_rela.product_id
     */
    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

}