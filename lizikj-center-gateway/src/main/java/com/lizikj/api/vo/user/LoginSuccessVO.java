package com.lizikj.api.vo.user;

import java.util.Date;

import com.lizikj.message.api.enums.ThirdPlatformTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 登录成功返回的VO对象
 * 
 * @author lijundong
 * @date 2017年8月14日 下午7:51:42
 */
@ApiModel(value = "登录成功返回信息")
public class LoginSuccessVO {

	/**
	 * 商户Id
	 */
	@ApiModelProperty(required = true, name = "merchantId", value = "商户Id", dataType = "Integer")
	private long merchantId;

	/**
	 * 店铺id
	 */
	@ApiModelProperty(required = true, name = "shopId", value = "店铺ID", dataType = "Integer")
	private long shopId;

	/**
	 * 店铺名称
	 */
	@ApiModelProperty(required = true, name = "shopName", value = "店铺名称", dataType = "String")
	private String shopName;

	/**
	 * 店铺logo
	 */
	@ApiModelProperty(required = true, name = "shopLogo", value = "店铺logo，七牛资源id", dataType = "String")
	private Long shopLogo;

	/**
	 * 手机号码
	 */
	@ApiModelProperty(required = true, name = "mobile", value = "手机号码", dataType = "String")
	private String mobile;

	/**
	 * 商户类型
	 */
	@ApiModelProperty(required = false, name = "merchantType", value = "商户类型   0:单店商家 1:多店商家", dataType = "Integer")
	private Byte merchantType;

	/**
	 * 员工角色代码
	 */
	@ApiModelProperty(required = false, name = "staffRoleCode", value = "员工角色代码 -老板：admin  店长：shop_manager 收银员：cashier ", dataType = "String")
	private String staffRoleCode;

	/**
	 * 商户用户id
	 */
	@ApiModelProperty(required = false, name = "userId", value = "商户用户id", dataType = "Long")
	private Long userId;

	/**
	 * 员工名称
	 */
	@ApiModelProperty(required = false, name = "userName", value = "员工名称", dataType = "String")
	private String userName;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(required = false, name = "createTime", value = "用户创建时间", dataType = "Date")
	private Date createTime;

	/**
	 * 客服电话
	 */
	@ApiModelProperty(required = false, name = "servicePhone", value = "客服电话", dataType = "String")
	private String servicePhone;

	/**
	 * 代理商logo
	 */
	@ApiModelProperty(required = false, name = "agentLogoPicId", value = "代理商logo", dataType = "Integer")
	private Long agentLogoPicId;

	/**
	 * 商户logo
	 */
	@ApiModelProperty(required = false, name = "merchantLogoPicId", value = "商户logo", dataType = "Integer")
	private Long merchantLogoPicId;

	/**
	 * 员工Id
	 */
	@ApiModelProperty(required = false, name = "staffId", value = "员工Id", dataType = "Integer")
	private Long staffId;

	/**
	 * 代理商名称
	 */
	@ApiModelProperty(required = false, name = "agentName", value = "代理商名称", dataType = "String")
	private String agentName;

	/**
	 * 商户名称
	 */
	@ApiModelProperty(required = false, name = "merchantName", value = "商户名称", dataType = "String")
	private String merchantName;
	
	/**
	 * 收银开启状态
	 */
	@ApiModelProperty(required = false, name = "cashierStatus", value = "收银开启状态", dataType = "Integer")	
	private Byte cashierStatus;

	@ApiModelProperty(required = false, name = "thirdPlatformTypeEnum", value = "移动推送平台类型", dataType = "Integer")
	private ThirdPlatformTypeEnum thirdPlatformTypeEnum;
	
	/**
	 * 交易功能状态
	 */
	@ApiModelProperty(name = "paymentConfigStatus", value = "交易功能状态 0：未配置 1：已配置 2：未开通", dataType = "Integer")
	private Byte paymentConfigStatus;

	/**
	 * 桌台二维码模板ID
	 */
	@ApiModelProperty(value = "桌台二维码模板ID")
	private Long qrcodeTemplateId;
	
	/**
	 * 是否需要注册门店
	 */
	@ApiModelProperty(value = "是否需要注册门店")
	private boolean needRegisterShop;
	
	/**
	 * mqttMsg配置信息，json字符串
	 */
	private String mqttMsgConfig;

	public LoginSuccessVO() {
		super();
	}

	public long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Long getShopLogo() {
		return shopLogo;
	}

	public void setShopLogo(Long shopLogo) {
		this.shopLogo = shopLogo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Byte getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(Byte merchantType) {
		this.merchantType = merchantType;
	}

	public String getStaffRoleCode() {
		return staffRoleCode;
	}

	public void setStaffRoleCode(String staffRoleCode) {
		this.staffRoleCode = staffRoleCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getServicePhone() {
		return servicePhone;
	}

	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}

	public Long getAgentLogoPicId() {
		return agentLogoPicId;
	}

	public void setAgentLogoPicId(Long agentLogoPicId) {
		this.agentLogoPicId = agentLogoPicId;
	}

	public Long getMerchantLogoPicId() {
		return merchantLogoPicId;
	}

	public void setMerchantLogoPicId(Long merchantLogoPicId) {
		this.merchantLogoPicId = merchantLogoPicId;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public Byte getCashierStatus() {
		return cashierStatus;
	}

	public void setCashierStatus(Byte cashierStatus) {
		this.cashierStatus = cashierStatus;
	}

	public ThirdPlatformTypeEnum getThirdPlatformTypeEnum() {
		return thirdPlatformTypeEnum;
	}

	public void setThirdPlatformTypeEnum(ThirdPlatformTypeEnum thirdPlatformTypeEnum) {
		this.thirdPlatformTypeEnum = thirdPlatformTypeEnum;
	}

	public Byte getPaymentConfigStatus() {
		return paymentConfigStatus;
	}

	public void setPaymentConfigStatus(Byte paymentConfigStatus) {
		this.paymentConfigStatus = paymentConfigStatus;
	}

	public Long getQrcodeTemplateId() {
		return qrcodeTemplateId;
	}

	public void setQrcodeTemplateId(Long qrcodeTemplateId) {
		this.qrcodeTemplateId = qrcodeTemplateId;
	}

	public boolean getNeedRegisterShop() {
		return needRegisterShop;
	}

	public void setNeedRegisterShop(boolean needRegisterShop) {
		this.needRegisterShop = needRegisterShop;
	}

	public String getMqttMsgConfig() {
		return mqttMsgConfig;
	}

	public void setMqttMsgConfig(String mqttMsgConfig) {
		this.mqttMsgConfig = mqttMsgConfig;
	}
	
	
}
