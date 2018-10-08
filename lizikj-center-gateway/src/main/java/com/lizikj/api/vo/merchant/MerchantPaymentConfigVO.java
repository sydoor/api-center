package com.lizikj.api.vo.merchant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 支付配置VO：copy自merchant-gateway
 * 
 * @author liaojw 
 * @date 2017年10月12日 上午10:12:31
 */
@ApiModel
public class MerchantPaymentConfigVO implements Serializable{
    /**
     * 配置ID
     */
	@ApiModelProperty(value = "配置ID")
    private Long configId;

    /**
     * 商户ID
     */
	@ApiModelProperty(value = "商户ID")
    private Long merchantId;

    /**
     * 收款账号ID
     */
	@ApiModelProperty(value = "收款账号ID")
    private Long accountId;

    /**
     * 通道商户号
     */
	@ApiModelProperty(value = "通道商户号")
    private String channelMerchantNo;

    /**
     * 通道ID
     */
	@ApiModelProperty(value = "通道ID")
    private Long channelId;

    /**
     * 通道code
     */
	@ApiModelProperty(value = "通道code 1、钱宝 2、通联 3、兴业 4、银盛 5、群迈 6、李子 7、汇宜")
    private Byte channelCode;
    
    /**
     * 通道名称
     */
	@ApiModelProperty(value = "通道名称")
    private String channelName;

    /**
     * 支付方式ID
     */
	@ApiModelProperty(value = "支付方式ID")
    private Long paymentTypeId;

    /**
     * 支付方式Code
     */
	@ApiModelProperty(value = "支付方式Code 1、支付宝 2、微信  3、银联钱包 4、银行卡支付  5、现金  8、会员 9、优惠券 10、美团券 12、预付券")
    private Byte paymentTypeCode;
	
	/**
	 * 支付场景 
	 */
	@ApiModelProperty(value = "支付场景Code 、收银台付款码 2、店铺收款码 3、订单小票支付二维码 4、pos扫用户 5、h5点餐在线支付 6、公众号点餐在线支付 7、刷卡 8、app支付 9、云闪付 10、现金")
	private List<PaymentTypeSceneVO> sceneList;

	public Long getConfigId() {
		return configId;
	}

	public void setConfigId(Long configId) {
		this.configId = configId;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getChannelMerchantNo() {
		return channelMerchantNo;
	}

	public void setChannelMerchantNo(String channelMerchantNo) {
		this.channelMerchantNo = channelMerchantNo;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Byte getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(Byte channelCode) {
		this.channelCode = channelCode;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Long getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(Long paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public Byte getPaymentTypeCode() {
		return paymentTypeCode;
	}

	public void setPaymentTypeCode(Byte paymentTypeCode) {
		this.paymentTypeCode = paymentTypeCode;
	}

	public List<PaymentTypeSceneVO> getSceneList() {
		return sceneList;
	}

	public void setSceneList(List<PaymentTypeSceneVO> sceneList) {
		this.sceneList = sceneList;
	}


}