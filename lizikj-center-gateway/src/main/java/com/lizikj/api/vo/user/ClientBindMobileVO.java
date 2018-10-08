package com.lizikj.api.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 客户端绑定手机VO
 * 
 * @author lijundong
 * @date 2018年1月19日 下午3:49:47
 */
@ApiModel
public class ClientBindMobileVO {

	/**
	 * 手机号
	 */
	@ApiModelProperty(required = true, name = "mobile", value = "手机号", dataType = "String")
	private String mobile;

	/**
	 * 手机验证码
	 */
	@ApiModelProperty(required = true, name = "code", value = "图片验证码", dataType = "String")
	private String code;

	public String getMobile() {
		return mobile;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "ClientBindMobileVO [mobile=" + mobile + ", code=" + code + "]";
	}

}
