package com.lizikj.api.vo.payment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class PayFlowDetaiResultVO{
	/**
	 * 支付流水账号详情ID
	 */
	@ApiModelProperty(value = "详情ID")
	private Long id;

	/**
	 * 支付流水ID
	 */
	@ApiModelProperty(value = "支付流水ID")
	private Long payFlowId;

	/**
	 * 商户ID
	 */
	@ApiModelProperty(value = "商户ID")
	private Long merchantId;

	/**
	 * 商户名称(冗余)
	 */
	@ApiModelProperty(value = "商户名称")
	private String merchantName;

	/**
	 * 店铺ID
	 */
	@ApiModelProperty(value = "店铺ID")
	private Long shopId;

	/**
	 * 店铺名称(冗余)
	 */
	@ApiModelProperty(value = "店铺名称")
	private String shopName;

	/**
	 * 用户ID
	 */
	@ApiModelProperty(value = "用户ID")
	private Long userId;

	/**
	 * 收银员Id
	 */
	@ApiModelProperty(value = "收银员Id")
	private Long cashierId;

	/**
	 * 收银员名称
	 */
	@ApiModelProperty(value = "收银员名称")
	private String cashierName;

	/**
	 * 收银员角色
	 */
	@ApiModelProperty(value = "收银员角色")
	private String cashierRoleName;

	/**
	 * 李子会员ID
	 */
	@ApiModelProperty(value = "李子会员ID")
	private Long memberId;

	/**
	 * 商户会员ID
	 */
	@ApiModelProperty(value = "商户会员ID")
	private Long merchantMemberId;

	/**
	 * 会员手机号(冗余)
	 */
	@ApiModelProperty(value = "会员手机号")
	private String memberMobile;
	
	/**
	 * Open ID
	 */
	@ApiModelProperty(value = "第三方openId")
	private String openId;

	/**
	 * 刷卡的银行帐号
	 */
	@ApiModelProperty(value = "刷卡的银行帐号")
	private String account;

	/**
	 * pos设备号
	 */
	@ApiModelProperty(value = "pos设备号")
	private String snNum;

	/**
	 * 支付信息
	 */
	@ApiModelProperty(value = "支付信息")
	private String payInfo;

	/**
	 * 支付流水账号详情ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 支付流水账号详情ID
	 * 
	 * @param id
	 *            the value for pay_flow_account_detail.id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 支付流水ID
	 */
	public Long getPayFlowId() {
		return payFlowId;
	}

	/**
	 * 支付流水ID
	 * 
	 * @param payFlowId
	 *            the value for pay_flow_account_detail.pay_flow_id
	 */
	public void setPayFlowId(Long payFlowId) {
		this.payFlowId = payFlowId;
	}

	/**
	 * 商户ID
	 */
	public Long getMerchantId() {
		return merchantId;
	}

	/**
	 * 商户ID
	 * 
	 * @param merchantId
	 *            the value for pay_flow_account_detail.merchant_id
	 */
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	/**
	 * 商户名称(冗余)
	 */
	public String getMerchantName() {
		return merchantName;
	}

	/**
	 * 商户名称(冗余)
	 * 
	 * @param merchantName
	 *            the value for pay_flow_account_detail.merchant_name
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	/**
	 * 店铺ID
	 */
	public Long getShopId() {
		return shopId;
	}

	/**
	 * 店铺ID
	 * 
	 * @param shopId
	 *            the value for pay_flow_account_detail.shop_id
	 */
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	/**
	 * 店铺名称(冗余)
	 */
	public String getShopName() {
		return shopName;
	}

	/**
	 * 店铺名称(冗余)
	 * 
	 * @param shopName
	 *            the value for pay_flow_account_detail.shop_name
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/**
	 * 用户ID
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 用户ID
	 * 
	 * @param userId
	 *            the value for pay_flow_account_detail.user_id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCashierId() {
		return cashierId;
	}

	public void setCashierId(Long cashierId) {
		this.cashierId = cashierId;
	}

	/**
	 * 李子会员ID
	 */
	public Long getMemberId() {
		return memberId;
	}

	/**
	 * 李子会员ID
	 * 
	 * @param memberId
	 *            the value for pay_flow_account_detail.member_id
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	/**
	 * 商户会员ID
	 */
	public Long getMerchantMemberId() {
		return merchantMemberId;
	}

	/**
	 * 商户会员ID
	 * 
	 * @param merchantMemberId
	 *            the value for pay_flow_account_detail.merchant_member_id
	 */
	public void setMerchantMemberId(Long merchantMemberId) {
		this.merchantMemberId = merchantMemberId;
	}

	public String getMemberMobile() {
		return memberMobile;
	}

	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}
	
	/**
	 * Open ID
	 */
	public String getOpenId() {
		return openId;
	}

	/**
	 * Open ID
	 * 
	 * @param openId
	 *            the value for pay_flow_account_detail.open_id
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}

	/**
	 * 刷卡的银行帐号
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * 刷卡的银行帐号
	 * 
	 * @param account
	 *            the value for pay_flow_account_detail.account
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * pos设备号
	 */
	public String getSnNum() {
		return snNum;
	}

	/**
	 * pos设备号
	 * 
	 * @param snNum
	 *            the value for pay_flow_account_detail.sn_num
	 */
	public void setSnNum(String snNum) {
		this.snNum = snNum;
	}

	public String getPayInfo() {
		return payInfo;
	}

	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}

	public String getCashierName() {
		return cashierName;
	}

	public void setCashierName(String cashierName) {
		this.cashierName = cashierName;
	}

	public String getCashierRoleName() {
		return cashierRoleName;
	}

	public void setCashierRoleName(String cashierRoleName) {
		this.cashierRoleName = cashierRoleName;
	}
}