package com.lizikj.api.vo.marketing;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.lizikj.api.vo.merchandise.GoodsVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 砍价活动API结果对象
 * 
 * @author lijundong
 * @date 2017年7月18日 上午10:49:21
 */
@ApiModel(value = "查询砍价活动对象")
public class CutPriceActivityDetailQueryVO {

	/**
	 * 砍价活动ID
	 */
	@ApiModelProperty(required = true, name = "cutPriceActivityId", value = "砍价活动的id", dataType = "Long")
	private Long cutPriceActivityId;

	/**
	 * 活动状态
	 */
	@ApiModelProperty(name = "status", value = "活动状态: 0未结束，1=进行中，2=已结束", dataType = "Integer")
	private Byte status;

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
	private String goodsId;

	/**
	 * 单品砍价的美食对象
	 */
	@ApiModelProperty(required = true, name = "goods", value = "单品砍价的美食对象", dataType = "Object")
	private GoodsVO goods;

	/**
	 * 砍价模式：1.整单砍价；2.单品砍价
	 */
	public Byte getCutMode() {
		return cutMode;
	}

	/**
	 * 砍价模式：1.整单砍价；2.单品砍价
	 * 
	 * @param cutMode
	 *            the value for cut_price_activity_detail.cut_mode
	 */
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

	@ApiIgnore
	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Long getCutPriceActivityId() {
		return cutPriceActivityId;
	}

	public void setCutPriceActivityId(Long cutPriceActivityId) {
		this.cutPriceActivityId = cutPriceActivityId;
	}

	public GoodsVO getGoods() {
		return goods;
	}

	public void setGoods(GoodsVO goods) {
		this.goods = goods;
	}

	
}