package com.lizikj.api.vo.shop;

import com.lizikj.shop.api.enums.MemberRoleEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 在该区域下就餐的会员
 * @author zhoufe 
 * @date 2017年7月19日 上午9:53:07 
 */
@ApiModel
public class ShopAreaMemberVO {
    /**
     * 会员身份
     */
	@ApiModelProperty(value = "会员类型：见：MemberRoleEnum：NONE.非会员，MEMBER.会员，SUPER_MEMBER.特殊会员。", required = true)
    private MemberRoleEnum memberRole;

    /**
     * 会员等级
     */
	@ApiModelProperty(value = "会员等级", required = true)
    private String memberLevel;

    /**
     * 会员等级名称
     */
	@ApiModelProperty(value = "会员等级名称", required = true)
    private Integer memberLavelName;

	/**
	 * 该身份的会员总数
	 */
	@ApiModelProperty(value = "该身份的会员总数", required = true)
	private Integer memberTotal;

	public MemberRoleEnum getMemberRole() {
		return memberRole;
	}

	public void setMemberRole(MemberRoleEnum memberRole) {
		this.memberRole = memberRole;
	}

	public String getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}

	public Integer getMemberLavelName() {
		return memberLavelName;
	}

	public void setMemberLavelName(Integer memberLavelName) {
		this.memberLavelName = memberLavelName;
	}

	public Integer getMemberTotal() {
		return memberTotal;
	}

	public void setMemberTotal(Integer memberTotal) {
		this.memberTotal = memberTotal;
	}
}
