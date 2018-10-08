package com.lizikj.api.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 客户端手机验证码VO
 * 
 * @author lijundong
 * @date 2018年1月19日 下午3:49:47
 */
@ApiModel
public class ClientMobileCodeVO {

	/**
	 * 手机号
	 */
	@ApiModelProperty(required = true, name = "mobile", value = "手机号", dataType = "String")
	private String mobile;

	/**
	 * 图片验证码
	 */
	@ApiModelProperty(required = true, name = "code", value = "图片验证码", dataType = "String")
	private String imgCode;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getImgCode() {
		return imgCode;
	}

	public void setImgCode(String imgCode) {
		this.imgCode = imgCode;
	}

	@Override
	public String toString() {
		return "ClientMobileCodeVO [mobile=" + mobile + ", imgCode=" + imgCode + "]";
	}

}
