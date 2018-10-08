package com.lizikj.api.vo.shop;

import com.lizikj.shop.api.enums.ShopBaseDataCustomStatusEnum;
import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Model for table -- shop_call_service_words
 * Created by zhoufe on 2017-07-18 14:50:50
 */
@ApiModel
public class ShopCallServiceWordsVO extends BaseDTO {
   
	private static final long serialVersionUID = 1L;

	/**
     * 呼叫服务话术ID
     */
	@ApiModelProperty(value = "呼叫服务话术ID", required = true)
    private Long callServiceWordsId;

    /**
     * 呼叫服务话术编码ID：自定义的没有
     */
    @ApiModelProperty(value = "呼叫服务话术模板ID", required = true)
    private Long callServiceWordsTemplateId;
    /**
     * 店铺ID
     */
	@ApiModelProperty(value = "店铺ID", required = true)
    private Long shopId;

    /**
     * 话术
     */
	@ApiModelProperty(value = "话术", required = true)
    private String theWords;


    /**
     * 服务编码：1001.加茶水，1002.加菜，1003.结账，1004.加茶位；自定义的是没有的
     */
    @ApiModelProperty(value = "服务编码：1001.加茶水，1002.加菜，1003.结账，1004.加茶位", required = true)
    private Integer theWordsCode;

    /**
     * 是否自定义：1.系统，0.自定义
     */
    @ApiModelProperty(value = "是否自定义：SYSTEM.系统， CUSTOM.自定义", required = true)
    private ShopBaseDataCustomStatusEnum customStatus;

    /**
     * 呼叫服务话术
     */
    public Long getCallServiceWordsId() {
        return callServiceWordsId;
    }

    /**
     * 呼叫服务话术
     * @param callServiceWordsId the value for shop_call_service_words.call_service_words_id
     */
    public void setCallServiceWordsId(Long callServiceWordsId) {
        this.callServiceWordsId = callServiceWordsId;
    }

    /**
     * 店铺ID
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 店铺ID
     * @param shopId the value for shop_call_service_words.shop_id
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 话术
     */
    public String getTheWords() {
        return theWords;
    }

    /**
     * 话术
     * @param theWords the value for shop_call_service_words.the_words
     */
    public void setTheWords(String theWords) {
        this.theWords = theWords == null ? null : theWords.trim();
    }

    public Long getCallServiceWordsTemplateId() {
        return callServiceWordsTemplateId;
    }

    public void setCallServiceWordsTemplateId(Long callServiceWordsTemplateId) {
        this.callServiceWordsTemplateId = callServiceWordsTemplateId;
    }

    public Integer getTheWordsCode() {
        return theWordsCode;
    }

    public void setTheWordsCode(Integer theWordsCode) {
        this.theWordsCode = theWordsCode;
    }

    public ShopBaseDataCustomStatusEnum getCustomStatus() {
        return customStatus;
    }

    public void setCustomStatus(ShopBaseDataCustomStatusEnum customStatus) {
        this.customStatus = customStatus;
    }
}