package com.lizikj.api.vo.member;

import com.lizikj.member.enums.MemberDiscountTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

/**
 * Created by liangxiaolin on 2017/10/18.
 */
@ApiModel
public class MerchantMemberDiscountSettingVO {
    @ApiModelProperty(value = "优惠方式设置ID")
    private Long settingId;
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    /**
     * 2000 无会员优惠 2100 按会员等级折扣优惠 2200 按会员等级折扣优惠
     */
    @ApiModelProperty(value = "2000 无会员优惠 2100 按会员等级折扣优惠 2200 按会员等级折扣优惠")
    private MemberDiscountTypeEnum memberDiscountTypeEnum;


    public Long getSettingId() {
        return settingId;
    }

    public void setSettingId(Long settingId) {
        this.settingId = settingId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public MemberDiscountTypeEnum getMemberDiscountTypeEnum() {
        return memberDiscountTypeEnum;
    }

    public void setMemberDiscountTypeEnum(MemberDiscountTypeEnum memberDiscountTypeEnum) {
        this.memberDiscountTypeEnum = memberDiscountTypeEnum;
    }
}
