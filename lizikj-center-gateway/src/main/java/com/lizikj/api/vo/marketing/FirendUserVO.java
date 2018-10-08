package com.lizikj.api.vo.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 邀请好友砍价页面，真爱榜单
 * 
 * @author lijundong
 * @date 2017年9月9日 上午11:15:21
 */
@ApiModel(value = "邀请好友砍价页面真爱榜单")
public class FirendUserVO {

	/**
	 * 好友昵称
	 */
	@ApiModelProperty(required = true, name = "nickname", value = "好友昵称", dataType = "String")
	private String nickname;

	/**
	 * 好友头像
	 */
	@ApiModelProperty(required = true, name = "headimg", value = "好友头像", dataType = "String")
	private String headimg;

	/**
	 * 砍价百分比
	 */
	@ApiModelProperty(required = true, name = "cutPercent", value = "砍价百分比", dataType = "String")
	private String cutPercent;

	/**
	 * 砍价时间
	 */
	@ApiModelProperty(required = true, name = "cutTime", value = "砍价时间", dataType = "String")
	private String cutTime;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getCutPercent() {
		return cutPercent;
	}

	public void setCutPercent(String cutPercent) {
		this.cutPercent = cutPercent;
	}

	public String getCutTime() {
		return cutTime;
	}

	public void setCutTime(String cutTime) {
		this.cutTime = cutTime;
	}

}
