package com.lizikj.api.vo.user.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserQueryVO {

	@ApiModelProperty(value = "用户id")
	private Long userId;

	@ApiModelProperty(value = "手机号码")
	
	private String mobile;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
