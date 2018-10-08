package com.lizikj.api.vo.order.param;

import com.lizikj.common.enums.PaymentChannelEnum;
import com.lizikj.common.enums.PaymentSceneEnum;
import com.lizikj.common.enums.PaymentTypeEnum;
import com.lizikj.order.enums.QuickBuyBizTypeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 快捷购买入参
 *
 * @author Michael.Huang
 * @date 2017/9/11 16:29
 */
@ApiModel
public class QuickBuyParamVO {
	@ApiModelProperty(value = "店铺ID")
    private Long shopId;

	@ApiModelProperty(value = "商户ID")
    private Long merchantId;

	@ApiModelProperty(value = "金额")
    private Long amount;

	@ApiModelProperty(value = "1.增值服务 2.商户会员充值 3.李子会员购买")
    private byte bizType;

	@ApiModelProperty(value = "业务数据，根据业务类型不同传递的数据也不一样 1.增值服务--增值服务类型id  2.商户会员充值--商户会员id")
    private String data;

	@ApiModelProperty(value = "支付渠道 1:钱宝  2:通联 3:兴业  4:银盛  5:群迈  6:李子")
    private byte paymentChannel;

	@ApiModelProperty(value = "支付方式  1:支付宝   2:微信  3:银联钱包  4:刷卡  5:现金  6:云闪付  8:会员  9:优惠券  10:美团券  12：预付券 13:会员股东")
    private byte paymentType;

	@ApiModelProperty(value = "支付场景 0:默认的 1:收银台付款码   2:店铺收款码  3:订单小票支付二维码  4:pos扫用户   5:h5点餐在线支付   6:公众号点餐在线支付  7:刷卡  8:app支付 9:云闪付 10:现金)")
    private byte paymentScene;

	@ApiModelProperty(value = "付款码支付的条形码的上方的码")
	private String authCode;

	@ApiModelProperty(value = "现金支付是接收到的金额")
	private Long receiveCashAmount;

	@ApiModelProperty(value = "现金支付是接收到的金额的找零")
	private Long cashChangeAmount;

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

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public byte getBizType() {
		return bizType;
	}

	public void setBizType(byte bizType) {
		this.bizType = bizType;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public byte getPaymentChannel() {
		return paymentChannel;
	}

	public void setPaymentChannel(byte paymentChannel) {
		this.paymentChannel = paymentChannel;
	}

	public byte getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(byte paymentType) {
		this.paymentType = paymentType;
	}

	public byte getPaymentScene() {
		return paymentScene;
	}

	public void setPaymentScene(byte paymentScene) {
		this.paymentScene = paymentScene;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public Long getReceiveCashAmount() {
		return receiveCashAmount;
	}

	public void setReceiveCashAmount(Long receiveCashAmount) {
		this.receiveCashAmount = receiveCashAmount;
	}

	public Long getCashChangeAmount() {
		return cashChangeAmount;
	}

	public void setCashChangeAmount(Long cashChangeAmount) {
		this.cashChangeAmount = cashChangeAmount;
	}
}
