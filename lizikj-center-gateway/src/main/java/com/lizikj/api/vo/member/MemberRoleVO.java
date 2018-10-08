package com.lizikj.api.vo.member;

import com.lizikj.api.enums.MemberRoleEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lxl
 * @Date 2018/3/5 16:50
 */
@ApiModel
public class MemberRoleVO {
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "电话")
    private String mobile;
    @ApiModelProperty(value = "会员ID")
    private Long memberId;
    @ApiModelProperty(value = "会员角色（商户、股东）")
    private MemberRoleEnum memberRoleEnum;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public MemberRoleEnum getMemberRoleEnum() {
        return memberRoleEnum;
    }

    public void setMemberRoleEnum(MemberRoleEnum memberRoleEnum) {
        this.memberRoleEnum = memberRoleEnum;
    }
}
