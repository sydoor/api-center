package com.lizikj.api.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 手机验证码验证通过返回的授权码
 * 
 * @author lijundong
 * @date 2017年8月29日 下午12:30:53
 */
@ApiModel(value = "验证码验证通过返回的授权码")
public class MobileCodeTokenVO {

	@ApiModelProperty(required = true, name = "token", value = "d授权码", dataType = "String")
	private String token;

	public MobileCodeTokenVO() {
		super();
	}

	public MobileCodeTokenVO(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
