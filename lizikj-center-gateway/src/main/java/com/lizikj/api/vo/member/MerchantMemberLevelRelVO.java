package com.lizikj.api.vo.member;

import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 会员等级关系
 * Created by liangjiankang on 2017-07-06 15:31:33
 */
@ApiModel(value="会员等级关系")
public class MerchantMemberLevelRelVO extends BaseDTO {
    private static final long serialVersionUID = -2145620210698986637L;
    /**
     * 商户id
     */
    @ApiModelProperty(value = "商户id")
    private Long merchantId;

    /**
     * 商户会员ID
     */
    @ApiModelProperty(value = "会员id")
    private Long merchantMemberId;

    /**
     * 等级id
     */
    @ApiModelProperty(value = "等级id")
    private Long levelId;

    /**
     * 店铺id
     */
    @ApiModelProperty(value = "店铺id")
    private Long shopId;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 商户id
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * 商户id
     * @param merchantId the value for memeber_level_rel.merchant_id
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getMerchantMemberId() {
        return merchantMemberId;
    }

    public void setMerchantMemberId(Long merchantMemberId) {
        this.merchantMemberId = merchantMemberId;
    }

    /**
     * 等级id
     */
    public Long getLevelId() {
        return levelId;
    }

    /**
     * 等级id
     * @param levelId the value for memeber_level_rel.level_id
     */
    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    /**
     * 店铺id
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 店铺id
     * @param shopId the value for memeber_level_rel.shop_id
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark the value for memeber_level_rel.remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}