package com.lizikj.api.vo.order;

import java.io.Serializable;

import com.lizikj.marketing.api.enums.SupportCouponTimeTypeEnum;
import com.lizikj.order.enums.DiscountStatusEnum;
import com.lizikj.order.enums.DiscountTypeEnum;
import com.lizikj.order.enums.DiscountTypeNodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 优惠信息
 *
 * @author Michael.Huang
 * @date 2017/8/24 10:07
 */
@ApiModel
public class OrderDiscountVO implements Serializable {
    private static final long serialVersionUID = 2616919406891699982L;

	@ApiModelProperty(value = "订单id")
    private Long orderId;

	@ApiModelProperty(value = "订单号")
    private String orderNo;

	@ApiModelProperty(value = "店铺id")
    private Long shopId;



	@ApiModelProperty(value = "活动类型  FULL_ORDER 20:整单优惠  \n "
			+ "LIMIT_TIME 30:限时优惠  \n "
			+ "BARGAIN 40:砍价优惠\n "
			+ "MEMBER_DIS 50:会员折扣\n  "
			+ "ERASE_ODD 60:抹零\n "
			+ "COUPON 70:优惠券\n "
			+ "CUSTOM_ERASE_ODD 90:自定义减免\n"
			+ "CUSTOM_DIS 110:自定义折扣")
    private DiscountTypeEnum type;

	@ApiModelProperty(value = "活动类型节点 FULL_DIS 21:整单折扣\n "
			+ "FULL_CUT 22:整单满减\n "
			+ "SINGLE_DIS 31:单品折扣\n "
			+ "CATEGORY_DIS 32:分类折扣\n "
			+ "TIME_DISCOUNT 33:分时优惠\n"
			+ "SINGLE_BARGAIN 42:单品砍价\n "
			+ "FULL_BARGAIN 43:整单砍价\n"
			+ "MEMBER_PRICE 51:会员价\n "
			+ "MEMBER_LEVEL 52:会员等级折扣\n "
			+ "ZERO 61:抹零\n"
			+ "CASH_COUPON 71:代金券\n"
            + "LIZI_CASH_COUPON 72:李子代金券\n"
            + "LIZI_NOOB_CASH_COUPON 76:李子新手代金券\n"
            + "LIZI_REWARD_CASH_COUPON 77:李子奖励代金券\n"
            + "LIZI_SCAN_CASH_COUPON 73:扫码红包\n"
            + "LIZI_SHARE_COUPON 74:分享红包\n"
			+ "CUSTOM_ERASE_ODD 91:自定义减免优惠\n"
			+ "CUSTOM_DIS 111:自定义折扣")
    private DiscountTypeNodeEnum typeNode;

	@ApiModelProperty(value = "活动名称")
    private String name;

	@ApiModelProperty(value = "优惠金额")
	private Long benefitAmount;

	@ApiModelProperty(value = "有效状态 DOING 0:进行中， INVALID -1:失效， VALID 1:有效的")
    private DiscountStatusEnum status;

    @ApiModelProperty(value = "支付版本")
    private Long payVersion;
    
    @ApiModelProperty(value = "业务参数")
    private String bizData;

    @ApiModelProperty(value = "商圈ID")
    private Long tradeAreaId;

    @ApiModelProperty(value = "优惠券的编码")
    private String couponCode;

    @ApiModelProperty(value = "优惠券的名称")
    private String couponName;

    @ApiModelProperty(value = "忙闲状态：见SupportCouponTimeTypeEnum BUSY_TIME.忙时，IDLE_TIME.闲时")
    private SupportCouponTimeTypeEnum idleStatus;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public Long getBenefitAmount() {
        return benefitAmount;
    }

    public void setBenefitAmount(Long benefitAmount) {
        this.benefitAmount = benefitAmount;
    }

    public Long getPayVersion() {
        return payVersion;
    }

    public void setPayVersion(Long payVersion) {
        this.payVersion = payVersion;
    }

    public DiscountTypeEnum getType() {
        return type;
    }

    public void setType(DiscountTypeEnum type) {
        this.type = type;
    }

    public DiscountTypeNodeEnum getTypeNode() {
        return typeNode;
    }

    public void setTypeNode(DiscountTypeNodeEnum typeNode) {
        this.typeNode = typeNode;
    }

    public DiscountStatusEnum getStatus() {
        return status;
    }

    public void setStatus(DiscountStatusEnum status) {
        this.status = status;
    }

	public String getBizData() {
		return bizData;
	}

	public void setBizData(String bizData) {
		this.bizData = bizData;
	}

    public Long getTradeAreaId() {
        return tradeAreaId;
    }

    public void setTradeAreaId(Long tradeAreaId) {
        this.tradeAreaId = tradeAreaId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public SupportCouponTimeTypeEnum getIdleStatus() {
        return idleStatus;
    }

    public void setIdleStatus(SupportCouponTimeTypeEnum idleStatus) {
        this.idleStatus = idleStatus;
    }
}
