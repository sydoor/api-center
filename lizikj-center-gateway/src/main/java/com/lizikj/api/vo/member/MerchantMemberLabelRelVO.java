package com.lizikj.api.vo.member;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 会员标签关系
 * @author zone
 * @date 2017-07-18
 */
@ApiModel(description = "会员与标签的关系")
public class MerchantMemberLabelRelVO implements Serializable {
	private static final long serialVersionUID = -2075788037544870855L;
	/**
	 * 标签id
	 */
    @ApiModelProperty(value = "标签id")
	private Long id;

    /**
     * 标签id
     */
    @ApiModelProperty(value = "标签id")
    private String labelId;

    /**
     * 标签名
     */
    @ApiModelProperty(value = "标签名",required = true)
    private String labelName;

    /**
     * 会员基础信息id
     */
    @ApiModelProperty(value = "会员基础信息id")
    private Long merchantMemberId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Long getMerchantMemberId() {
        return merchantMemberId;
    }

    public void setMerchantMemberId(Long merchantMemberId) {
        this.merchantMemberId = merchantMemberId;
    }
}