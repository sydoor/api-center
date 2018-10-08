package com.lizikj.api.vo.member;

import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Healthy
 * @description 新增会员信息
 * @date 2017/7/10
 */
@ApiModel(value="新增会员信息")
public class NewMemberVO extends BaseDTO {
    private static final long serialVersionUID = -6853588240200279089L;
    /**
     * 会员基础信息id
     */
    @ApiModelProperty(value = "会员基础信息id")
    private Long memberId;
    /**
     *  商户会员ID
     */
    @ApiModelProperty(value = "商户会员ID")
    private Long merchantMemberId;

    /**
     * 商户id
     */
    @ApiModelProperty(value = "商户id")
    private Long merchantId;

    /**
     * 用户真实姓名
     */
    @ApiModelProperty(value = "用户真实姓名")
    private String realName;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;

    /**
     * 消费金额
     */
    @ApiModelProperty(value = "消费金额")
    private String amount;

    /**
     * 加入时间
     */
    @ApiModelProperty(value = "加入时间")
    private String time;

    /**
     * 会员等级
     */
    @ApiModelProperty(value = "会员等级")
    private String levelName;
    
    
    /**
     * 会员基础信息id
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * 会员基础信息id
     * @param memberId the value for member_base.member_id
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * 商户id
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * 商户id
     * @param merchantId the value for member_base.merchant_id
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 用户真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 用户真实姓名
     * @param realName the value for member_base.real_name
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 手机号
     * @param mobile the value for member_base.mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 消费金额
     */
	public String getAmount() {
		return amount;
	}

    /**
     * 消费金额
     */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * 加入时间 
	 */
	public String getTime() {
		return time;
	}

	/**
	 * 加入时间 
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * 等级名称
	 */
	public String getLevelName() {
		return levelName;
	}

	/**
	 * 等级名称
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

    public Long getMerchantMemberId() {
        return merchantMemberId;
    }

    public void setMerchantMemberId(Long merchantMemberId) {
        this.merchantMemberId = merchantMemberId;
    }
}
