package com.lizikj.api.vo.user.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 登陆参数
 * 
 * @author liaojw 
 * @date 2017年9月29日 上午11:49:55
 */
@ApiModel(value = "登陆参数")
public class LoginParam implements Serializable{
	/**
	 * 登录名
	 */
	@ApiModelProperty(value = "登录名")
	private String loginName;
	
	/**
	 * 登录密码
	 */
	@ApiModelProperty(value = "登录密码")
	private String password;
	
	/**
	 * 登录来源,可选项有0=PC,1=APP,2=POS
	 */
	@ApiModelProperty(value = "登录来源,可选项有0=PC,1=APP,2=POS")
	private Byte loginSource;
	
	/**
	 * 角色代码,APP端登录必填!老板=admin;店长=shopManager
	 */
	@ApiModelProperty(value = "角色代码,APP端登录必填!老板=admin;店长=shopManager")
	private String roleCode;
	
	/**
	 * 登录验证码, loginSource=0, 必填
	 */
	@ApiModelProperty(value = "登录验证码, loginSource=0, 必填")
	private  String code;

	@ApiModelProperty(value = "商户ID")
	private Long merchantId;
	
	@ApiModelProperty(value = "用户ID")
	private Long userId;
	
	@ApiModelProperty(value = "员工ID")
	private Long staffId;
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Byte getLoginSource() {
		return loginSource;
	}

	public void setLoginSource(Byte loginSource) {
		this.loginSource = loginSource;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}
	
	
}
