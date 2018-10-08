package com.lizikj.api.vo.order.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 支付信息内容
 *
 * @author zone
 * @date 2017/9/19
 */
@ApiModel
public class PayParamVO {
	@ApiModelProperty(value = "支付金额")
	private Long payAmount;

	@ApiModelProperty(value = "业务数据(会员支付表示会员ID,团购券表示账户信息,具体根据支付方式不同，自由转换)")
	private String bizData;

//	@ApiModelProperty(value = "支付渠道 1:钱宝  2:通联  3:兴业  4:银盛  5:群迈  6:李子")
//	private byte paymentChannel;

	@ApiModelProperty(value = "支付方式 1:支付宝  2:微信  3:银联钱包  4:刷卡  5:现金  6:云闪付  8:会员  9:优惠券  10:美团券  12:预付券 13:会员股东 14:外部收款")
	private byte paymentType;

	@ApiModelProperty(value = "支付场景 1:收银台付款码  2:店铺收款码  3:订单小票支付二维码  4:pos扫用户  5:h5点餐在线支付  7:刷卡  8:app支付  11:小程序 12:外部收款")
	private byte paymentScene;

	@ApiModelProperty(value = "股东会员支付方式:见AccountFlowSourceEnum：2300.股东会员门店消费,3001.抵扣支付。")
	private int accountFlowSource;
	
	@ApiModelProperty(value = "流水备注")
	private String flowMemo;

	public Long getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(Long payAmount) {
		this.payAmount = payAmount;
	}

	public String getBizData() {
		return bizData;
	}

	public void setBizData(String bizData) {
		this.bizData = bizData;
	}

//	public byte getPaymentChannel() {
//		return paymentChannel;
//	}
//
//	public void setPaymentChannel(byte paymentChannel) {
//		this.paymentChannel = paymentChannel;
//	}

	public String getFlowMemo() {
		return flowMemo;
	}

	public void setFlowMemo(String flowMemo) {
		this.flowMemo = flowMemo;
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

	public int getAccountFlowSource() {
		return accountFlowSource;
	}

	public void setAccountFlowSource(int accountFlowSource) {
		this.accountFlowSource = accountFlowSource;
	}

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PayParamDTO{");
        sb.append("payAmount=").append(payAmount);
        sb.append(", bizData='").append(bizData).append('\'');
        sb.append(", paymentType=").append(paymentType);
        sb.append(", paymentScene=").append(paymentScene);
        sb.append(", accountFlowSource=").append(accountFlowSource);
        sb.append(", flowMemo=").append(flowMemo);
        sb.append('}');
        return sb.toString();
    }
}
