package com.lizikj.api.vo.order.param;

import com.lizikj.api.vo.order.OrderDiscountVO;
import com.lizikj.order.dto.OrderDiscountDTO;
import com.lizikj.order.dto.param.OrderInfoParamDTO;
import com.lizikj.order.dto.param.OrderItemParamDTO;
import com.lizikj.order.enums.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 下单结束页面的参数
 * Created by adept on 2017/7/13.
 */
@ApiModel
public class OrderInfoParamVO implements Serializable {

    private static final long serialVersionUID = -7019435789517152545L;


    //    @ApiModelProperty(value="店铺ID（可以从头部取）")
//    private Long shopId;
//    @ApiModelProperty(value="会员ID")
//    private Long memberId;
//    @ApiModelProperty(value="H5用户ID：可以根据这个查询是否会员")
//    private Long userId;
//    @ApiModelProperty(value="POS的sn号（可以从头部取）")
//    private String snNum;
    @ApiModelProperty(value = "商户会员ID", required = true)
    private Long merchantMemberId;

    @ApiModelProperty(value = "撩美味的股东会员ID")
    private Long partnerMemberId;

    @ApiModelProperty(value = "订单ID，修改订单时传递", required = true)
    private Long orderId;


    @ApiModelProperty(value = "减菜：订单物品的ID，修改订单时传递", required = true)
    private List<Long> removeOrderItemIds;


    @ApiModelProperty(value = "来源：见OrderSourceEnum：H5.H5下单，POS.pos主动下单，QUICK.快捷收银台下单，QR_CODE.店铺收款码下单，VALUE_ADD.增值服务订单，LZ_PURCHASE.李子会员购买，SC.小程序，LMW.撩美味，OTHER.其他。")
    private OrderSourceEnum orderSource;

    @ApiModelProperty(value = "餐桌ID")
    private Long areaDeskId;

    @ApiModelProperty(value = "自定义桌牌号")
    private String customDeskNumber;

    @ApiModelProperty(value = "就餐人数")
    private Long peoples;

    @ApiModelProperty(value = "整个单的备注")
    private String remark;

    @ApiModelProperty(value = "是否叫起")
    private Boolean wait4Serving;

    @ApiModelProperty(value = "是否打包整个单：见：PackStatusEnum：PACK.是打包 UN_PACK.不打包。")
    private PackStatusEnum packStatus;

    //包含订单物品信息，优惠信息，支付信息，退款信息等
    @ApiModelProperty(value = "订单商品明细", required = true)
    private List<OrderItemParamVO> itemParamList;

    @ApiModelProperty(value = "是否拼桌:下单时用，是时areaDeskId后台就会认为创建桌台并做父ID", required = true)
    private Boolean mergeFlag;

    @ApiModelProperty(value = "层级", required = true)
    private Integer level;

    /**
     * 前端选中的优惠方式
     */
    @ApiModelProperty(value = "前端选中的优惠方式:见DiscountTypeNodeEnum。")
    private DiscountTypeNodeEnum discountSelected;

    @ApiModelProperty(value = "所选的用户优惠券Id")
    private Long userCouponId;

    @ApiModelProperty(value = "授权码", required = true)
    private String authorCode;

	@ApiModelProperty(value = "自定义的折扣")
    private String customDis;

    @ApiModelProperty(value = "优惠券和红包的优惠")
    private List<OrderDiscountVO> orderDiscountList;

    @ApiModelProperty(value = "待下单的订单的类型 PendingOrderTypeEnum：NONE 无，DELIVERY_BY_CLIENT 到店自取，DELIVERY_BY_MERCHANT 商户配送，FOR_HERE 到店堂食。")
    private PendingOrderTypeEnum pendingOrderType;

    //待下单，返回详情的时候直接用保存好的参数下单，为了和DTO字段一致
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    @ApiModelProperty(value = "订单业务类型")
    private OrderBizTypeEnum orderBizType;
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    @ApiModelProperty(value = "平台会员ID")
    private Long memberId;
    @ApiModelProperty(value = "下单人ID")
    private Long orderPersonId;
    @ApiModelProperty(value = "SN号")
    private String snNum;
    @ApiModelProperty(value = "员工ID")
    private Long staffId;
    @ApiModelProperty(value = "员工名称")
    private String staffName;
    @ApiModelProperty(value = "员工角色名称")
    private String staffRoleName;
    @ApiModelProperty(value = "待下单ID")
    private Long pendingOrderInfoId;


    public Long getAreaDeskId() {
        return areaDeskId;
    }

    public void setAreaDeskId(Long areaDeskId) {
        this.areaDeskId = areaDeskId;
    }

    public Long getPeoples() {
        return peoples;
    }

    public void setPeoples(Long peoples) {
        this.peoples = peoples;
    }

    public List<OrderItemParamVO> getItemParamList() {
        return itemParamList;
    }

    public void setItemParamList(List<OrderItemParamVO> itemParamList) {
        this.itemParamList = itemParamList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public OrderSourceEnum getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(OrderSourceEnum orderSource) {
        this.orderSource = orderSource;
    }

    public PackStatusEnum getPackStatus() {
        return packStatus;
    }

    public void setPackStatus(PackStatusEnum packStatus) {
        this.packStatus = packStatus;
    }

    public Boolean getWait4Serving() {
        return wait4Serving;
    }

    public void setWait4Serving(Boolean wait4Serving) {
        this.wait4Serving = wait4Serving;
    }

    public String getCustomDeskNumber() {
        return customDeskNumber;
    }

    public void setCustomDeskNumber(String customDeskNumber) {
        this.customDeskNumber = customDeskNumber;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<Long> getRemoveOrderItemIds() {
        return removeOrderItemIds;
    }

    public void setRemoveOrderItemIds(List<Long> removeOrderItemIds) {
        this.removeOrderItemIds = removeOrderItemIds;
    }

    public Long getMerchantMemberId() {
        return merchantMemberId;
    }

    public void setMerchantMemberId(Long merchantMemberId) {
        this.merchantMemberId = merchantMemberId;
    }

    public Long getPartnerMemberId() {
        return partnerMemberId;
    }

    public void setPartnerMemberId(Long partnerMemberId) {
        this.partnerMemberId = partnerMemberId;
    }

    public DiscountTypeNodeEnum getDiscountSelected() {
        return discountSelected;
    }

    public void setDiscountSelected(DiscountTypeNodeEnum discountSelected) {
        this.discountSelected = discountSelected;
    }

    public Long getUserCouponId() {
        return userCouponId;
    }

    public void setUserCouponId(Long userCouponId) {
        this.userCouponId = userCouponId;
    }

    public String getAuthorCode() {
        return authorCode;
    }

    public void setAuthorCode(String authorCode) {
        this.authorCode = authorCode;
    }

    public Boolean getMergeFlag() {
        return mergeFlag;
    }

    public void setMergeFlag(Boolean mergeFlag) {
        this.mergeFlag = mergeFlag;
    }


    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

	public String getCustomDis() {
		return customDis;
	}

	public void setCustomDis(String customDis) {
		this.customDis = customDis;
	}

    public List<OrderDiscountVO> getOrderDiscountList() {
        return orderDiscountList;
    }

    public void setOrderDiscountList(List<OrderDiscountVO> orderDiscountList) {
        this.orderDiscountList = orderDiscountList;
    }

    public PendingOrderTypeEnum getPendingOrderType() {
        return pendingOrderType;
    }

    public void setPendingOrderType(PendingOrderTypeEnum pendingOrderType) {
        this.pendingOrderType = pendingOrderType;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public OrderBizTypeEnum getOrderBizType() {
        return orderBizType;
    }

    public void setOrderBizType(OrderBizTypeEnum orderBizType) {
        this.orderBizType = orderBizType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getOrderPersonId() {
        return orderPersonId;
    }

    public void setOrderPersonId(Long orderPersonId) {
        this.orderPersonId = orderPersonId;
    }

    public String getSnNum() {
        return snNum;
    }

    public void setSnNum(String snNum) {
        this.snNum = snNum;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffRoleName() {
        return staffRoleName;
    }

    public void setStaffRoleName(String staffRoleName) {
        this.staffRoleName = staffRoleName;
    }

    public Long getPendingOrderInfoId() {
        return pendingOrderInfoId;
    }

    public void setPendingOrderInfoId(Long pendingOrderInfoId) {
        this.pendingOrderInfoId = pendingOrderInfoId;
    }
}

