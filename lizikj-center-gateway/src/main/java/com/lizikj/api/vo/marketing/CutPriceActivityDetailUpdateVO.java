package com.lizikj.api.vo.marketing;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 更新创建活动表单类
 * 
 * @author lijundong
 * @date 2017年7月18日 上午10:49:21
 */
@ApiModel(value = "更新砍价活动对象")
public class CutPriceActivityDetailUpdateVO {

	/**
	 * 砍价活动ID
	 */
	@ApiModelProperty(required = true, name = "cutPriceActivityId", value = "砍价活动的id", dataType = "Long")
	private Long cutPriceActivityId;

	/**
	 * 砍价模式：1.整单砍价；2.单品砍价
	 */
	@ApiModelProperty(required = true, name = "cutMode", value = "砍价模式：1.整单砍价；2.单品砍价", dataType = "Integer")
	private Byte cutMode;

	/**
	 * 起砍金额
	 */
	@ApiModelProperty(required = true, name = "startCutAmounts", value = "起砍金额", dataType = "Long")
	private Long startCutAmounts;

	/**
	 * 砍价比例
	 */
	@ApiModelProperty(required = true, name = "discount", value = "砍价比例", dataType = "Integer", example = "50")
	private Integer discount;

	/**
	 * 砍价次数
	 */
	@ApiModelProperty(required = true, name = "cutNum", value = "砍价次数", dataType = "Integer")
	private Integer cutNum;

	/**
	 * 有效时长(分钟)
	 */
	@ApiModelProperty(required = true, name = "validTimeLength", value = "有效时长(分钟)", dataType = "Integer", example = "60")
	private Integer validTimeLength;

	/**
	 * 使用说明
	 */
	@ApiModelProperty(required = true, name = "instructions", value = "使用说明", dataType = "String")
	private String instructions;

	/**
	 * 单品砍价的美食id
	 */
	@ApiModelProperty(name = "goodsId", value = "单品砍价的美食id", dataType = "String")
	private String goodsId;

	public Long getCutPriceActivityId() {
		return cutPriceActivityId;
	}

	public void setCutPriceActivityId(Long cutPriceActivityId) {
		this.cutPriceActivityId = cutPriceActivityId;
	}

	public Byte getCutMode() {
		return cutMode;
	}

	public void setCutMode(Byte cutMode) {
		this.cutMode = cutMode;
	}

	/**
	 * 起砍金额
	 */
	public Long getStartCutAmounts() {
		return startCutAmounts;
	}

	/**
	 * 起砍金额
	 * 
	 * @param startCutAmounts
	 *            the value for cut_price_activity_detail.start_cut_amounts
	 */
	public void setStartCutAmounts(Long startCutAmounts) {
		this.startCutAmounts = startCutAmounts;
	}

	/**
	 * 砍价比例
	 */
	public Integer getDiscount() {
		return discount;
	}

	/**
	 * 砍价比例
	 * 
	 * @param discount
	 *            the value for cut_price_activity_detail.discount
	 */
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	/**
	 * 砍价次数
	 */
	public Integer getCutNum() {
		return cutNum;
	}

	/**
	 * 砍价次数
	 * 
	 * @param cutNum
	 *            the value for cut_price_activity_detail.cut_num
	 */
	public void setCutNum(Integer cutNum) {
		this.cutNum = cutNum;
	}

	/**
	 * 有效时长(分钟)
	 */
	public Integer getValidTimeLength() {
		return validTimeLength;
	}

	/**
	 * 有效时长(分钟)
	 * 
	 * @param validTimeLength
	 *            the value for cut_price_activity_detail.valid_time_length
	 */
	public void setValidTimeLength(Integer validTimeLength) {
		this.validTimeLength = validTimeLength;
	}

	/**
	 * 使用说明
	 */
	public String getInstructions() {
		return instructions;
	}

	/**
	 * 使用说明
	 * 
	 * @param instructions
	 *            the value for cut_price_activity_detail.instructions
	 */
	public void setInstructions(String instructions) {
		this.instructions = instructions == null ? null : instructions.trim();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

}