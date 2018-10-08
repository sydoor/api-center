package com.lizikj.api.vo.member;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 会员标签
 * @author zone
 * @date 2017-07-18
 */
@ApiModel(description = "会员标签")
public class MerchantMemberLabelVO implements Serializable {
	private static final long serialVersionUID = 5810571123915788019L;

	/**
	 * 标签id
	 */
    @ApiModelProperty(value = "标签id")
	private Long labelId;

	/**
	 * 创建时间
	 */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 删除状态
     */
    @ApiModelProperty(value = "删除状态")
    private Integer removeStatus;

    /**
     * 标签名
     */
    @ApiModelProperty(value = "标签名")
    private String labelName;

    /**
     * 商户id(merchant_id)
     */
    @ApiModelProperty(value = "商户id(merchant_id)")
    private Long merchantId;

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getRemoveStatus() {
        return removeStatus;
    }

    public void setRemoveStatus(Integer removeStatus) {
        this.removeStatus = removeStatus;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }
}