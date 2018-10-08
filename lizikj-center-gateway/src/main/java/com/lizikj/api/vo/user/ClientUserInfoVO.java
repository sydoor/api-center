package com.lizikj.api.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ClientUserInfoVO {

	@ApiModelProperty(name = "nickName", value = "微信昵称")
	private String nickName;

	@ApiModelProperty(name = "headimgurl", value = "头像")
	private String headimgurl;

	@ApiModelProperty(name = "mobile", value = "手机号码")
	private String mobile;

	@ApiModelProperty(name = "sex", value = "性别")
	private Byte sex;

	@ApiModelProperty(name = "birthday", value = "生日")
	private String birthday;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Byte getSex() {
		return sex;
	}

	public void setSex(Byte sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

}
