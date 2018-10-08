package com.lizikj.api.vo.order;

import com.lizikj.marketing.api.enums.SupportCouponTimeTypeEnum;
import com.lizikj.order.enums.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 商品行
 * Created by adept on 2017/7/12.
 */
@ApiModel
public class ItemVO implements Serializable {

    private static final long serialVersionUID = 3507172781778253630L;

    @ApiModelProperty(value = "SKUID", required = true)
    private Long skuId;

    @ApiModelProperty(value = "商品ID", required = true)
    private String goodsId;


    @ApiModelProperty(value = "商品名称", required = true)
    private String goodsName;

    @ApiModelProperty(value = "单位名称：如果是茶位费，自定义费用等请见ShopSettingFeeModeEnum：DESK_MODE.桌，PEOPLE_MODE.人，ORDER_MODE.单。", required = true)
    private String unit;

    @ApiModelProperty(value = "实际出售总价", required = true)
    private Long saleAmount;

    @ApiModelProperty(value = "商品单价", required = true)
    private Long sellPrice;

    @ApiModelProperty(value = "个数", required = true)
    private Long sellVolume;

    @ApiModelProperty(value = "重量：是记重菜时有可能是小数，都乘以100保存", required = false)
    private Long weight;

    @ApiModelProperty(value = "总价", required = true)
    private Long totalAmount;

    @ApiModelProperty(value = "是否加菜")
    private Boolean appendDishStatus;

    @ApiModelProperty(value = "是否接单")
    private Boolean recStatus;

    @ApiModelProperty(value = "是否已经打印")
    private Boolean printStatus;

    @ApiModelProperty(value = "打印标识：-1.默认，0.打印失败，1+.打印次数")
    private Integer printFlag;

    @ApiModelProperty(value = "是否临时菜：skuId为FixedSKUID4NotSKUIDEnum.TEMP_DISH(-999905)临时菜")
    private Boolean tempDishStatus;

    @ApiModelProperty(value = "商品行备注")
    private String remark;

    @ApiModelProperty(value = "美食图片ID")
    private Long mediaId;

    @ApiModelProperty(value = "是否赠品")
    private Boolean freeDishStatus;

    @ApiModelProperty(value = "是否打包该美食")
    private PackStatusEnum packStatus;


    /**
     * 选中的skuValue的ID列表，对于组合型套餐，由于存在大量组合条件，而价格又是一致的，实际上存储的SKU只有一条记录
     * 这个时候传递skuValueId显得特别有意义，通过skuValueId能直接定位某个被选中的skuValue
     */
    @ApiModelProperty(value = "选中的skuValue的ID列表，对于组合型套餐，由于存在大量组合条件，而价格又是一致的，实际上存储的SKU只有一条记录<br/>这个时候传递skuValueId显得特别有意义，通过skuValueId能直接定位某个被选中的skuValue")
    private List<Long> skuValueIds;

    /**
     * 商品版本
     */
    @ApiModelProperty(value = "商品版本")
    private Long merchandiseVersion;

    /**
     * 是否计重菜
     */
    @ApiModelProperty(value = "是计记重菜：见IsWeightEnum：YES.是计重菜，NO.非计重菜。")
    private IsWeightEnum weightStatus;

    /**
     * 会员价
     */
    @ApiModelProperty(value = "会员价：有会员价就显示，没有就不显示")
    private Long memberPrice;


    /**
     * 商品类型：1000.增值服务商品，2000.普通商品，3000.套餐，4000.费用商品，5000.临时菜，6000.赠菜，7000.临时赠菜，8000.加料
     */
    @ApiModelProperty(value = "商品类型：见ItemTypeEnum。")
    private ItemTypeEnum itemType;

    /**
     * 商品子类型：1100.1年，1200.2年，1300.3年；2100.普通商品；3100.任意双拼套餐；3200.固定双拼套餐；3300.主次套餐；3400.组合多选套餐;4100.打包费;4200.茶位费，4300.自定义费用；5100.临时菜；6100.普通赠送；6200.营销活动赠送；7100.临时赠菜，8100.加料
     */
    @ApiModelProperty(value = "商品子类型：见ItemSubTypeEnum PACKAGE_ANY 任意双拼套餐， PACKAGE_FIXED 固定双拼套餐，PACKAGE_MASTER_SLAVE 主次套餐，PACKAGE_COMPOSE 组合多选套餐。")
    private ItemSubTypeEnum itemSubType;


    /**
     * 下单菜员工id
     */
    @ApiModelProperty(value = "下单菜员工ID")
    private Long addStaffId;

    /**
     * 下单菜员工名称：返回前端是用
     */
    @ApiModelProperty(value = "下单菜员工名称")
    private String addStaffName;

    /**
     * 加菜员工id
     */
    @ApiModelProperty(value = "加菜员工")
    private Long incrStaffId;

    /**
     * 加菜员工名称：返回前端是用
     */
    @ApiModelProperty(value = "加菜员工名称")
    private String incrStaffName;

    /**
     * 减菜员工id
     */
    @ApiModelProperty(value = "减菜员工ID")
    private Long decrStaffId;

    /**
     * 减菜员工名称：返回前端是用
     */
    @ApiModelProperty(value = "减菜员工名称")
    private String decrStaffName;

    /**
     * 退款菜员工id
     */
    @ApiModelProperty(value = "退款菜员工ID")
    private Long refundStaffId;

    /**
     * 退款菜员工名称：返回前端是用
     */
    @ApiModelProperty(value = "退款菜员工名称")
    private String refundStaffName;

    /**
     * 取消菜员工id
     */
    @ApiModelProperty(value = "取消菜员工ID")
    private Long cancelStaffId;

    /**
     * 取消菜员工名称：返回前端是用
     */
    @ApiModelProperty(value = "取消菜员工名称")
    private String cancelStaffName;

    /**
     * 销售提成金额
     */
    @ApiModelProperty(value = "销售提成金额")
    private Long salesExtractAmount;

    /**
     * 忙闲状态：0.无，1.闲时，2.忙时
     */
    @ApiModelProperty(value = "忙闲状态SupportCouponTimeTypeEnum：NONE.无，IDLE_TIME.闲时，BUSY_TIME.忙时")
    private SupportCouponTimeTypeEnum idleStatus;


    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Long getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(Long saleAmount) {
        this.saleAmount = saleAmount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getSellVolume() {
        return sellVolume;
    }

    public void setSellVolume(Long sellVolume) {
        this.sellVolume = sellVolume;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getFreeDishStatus() {
        return freeDishStatus;
    }

    public void setFreeDishStatus(Boolean freeDishStatus) {
        this.freeDishStatus = freeDishStatus;
    }

    public PackStatusEnum getPackStatus() {
        return packStatus;
    }

    public void setPackStatus(PackStatusEnum packStatus) {
        this.packStatus = packStatus;
    }

    public Boolean getTempDishStatus() {
        return tempDishStatus;
    }

    public void setTempDishStatus(Boolean tempDishStatus) {
        this.tempDishStatus = tempDishStatus;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public List<Long> getSkuValueIds() {
        return skuValueIds;
    }

    public void setSkuValueIds(List<Long> skuValueIds) {
        this.skuValueIds = skuValueIds;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Boolean getAppendDishStatus() {
        return appendDishStatus;
    }

    public void setAppendDishStatus(Boolean appendDishStatus) {
        this.appendDishStatus = appendDishStatus;
    }

    public Long getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Long sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Long getMerchandiseVersion() {
        return merchandiseVersion;
    }

    public void setMerchandiseVersion(Long merchandiseVersion) {
        this.merchandiseVersion = merchandiseVersion;
    }

    public IsWeightEnum getWeightStatus() {
        return weightStatus;
    }

    public void setWeightStatus(IsWeightEnum weightStatus) {
        this.weightStatus = weightStatus;
    }

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public Boolean getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(Boolean recStatus) {
        this.recStatus = recStatus;
    }

    public Boolean getPrintStatus() {
        return printStatus;
    }

    public void setPrintStatus(Boolean printStatus) {
        this.printStatus = printStatus;
    }

    public Integer getPrintFlag() {
        return printFlag;
    }

    public void setPrintFlag(Integer printFlag) {
        this.printFlag = printFlag;
    }

    public Long getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(Long memberPrice) {
        this.memberPrice = memberPrice;
    }

    public ItemTypeEnum getItemType() {
        return itemType;
    }

    public void setItemType(ItemTypeEnum itemType) {
        this.itemType = itemType;
    }

    public ItemSubTypeEnum getItemSubType() {
        return itemSubType;
    }

    public void setItemSubType(ItemSubTypeEnum itemSubType) {
        this.itemSubType = itemSubType;
    }

    public Long getAddStaffId() {
        return addStaffId;
    }

    public void setAddStaffId(Long addStaffId) {
        this.addStaffId = addStaffId;
    }

    public String getAddStaffName() {
        return addStaffName;
    }

    public void setAddStaffName(String addStaffName) {
        this.addStaffName = addStaffName;
    }

    public Long getIncrStaffId() {
        return incrStaffId;
    }

    public void setIncrStaffId(Long incrStaffId) {
        this.incrStaffId = incrStaffId;
    }

    public String getIncrStaffName() {
        return incrStaffName;
    }

    public void setIncrStaffName(String incrStaffName) {
        this.incrStaffName = incrStaffName;
    }

    public Long getDecrStaffId() {
        return decrStaffId;
    }

    public void setDecrStaffId(Long decrStaffId) {
        this.decrStaffId = decrStaffId;
    }

    public String getDecrStaffName() {
        return decrStaffName;
    }

    public void setDecrStaffName(String decrStaffName) {
        this.decrStaffName = decrStaffName;
    }

    public Long getRefundStaffId() {
        return refundStaffId;
    }

    public void setRefundStaffId(Long refundStaffId) {
        this.refundStaffId = refundStaffId;
    }

    public String getRefundStaffName() {
        return refundStaffName;
    }

    public void setRefundStaffName(String refundStaffName) {
        this.refundStaffName = refundStaffName;
    }

    public Long getCancelStaffId() {
        return cancelStaffId;
    }

    public void setCancelStaffId(Long cancelStaffId) {
        this.cancelStaffId = cancelStaffId;
    }

    public String getCancelStaffName() {
        return cancelStaffName;
    }

    public void setCancelStaffName(String cancelStaffName) {
        this.cancelStaffName = cancelStaffName;
    }

    public Long getSalesExtractAmount() {
        return salesExtractAmount;
    }

    public void setSalesExtractAmount(Long salesExtractAmount) {
        this.salesExtractAmount = salesExtractAmount;
    }

    public SupportCouponTimeTypeEnum getIdleStatus() {
        return idleStatus;
    }

    public void setIdleStatus(SupportCouponTimeTypeEnum idleStatus) {
        this.idleStatus = idleStatus;
    }
}
