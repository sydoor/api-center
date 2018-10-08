package com.lizikj.api.vo.shop;

import com.lizikj.common.enums.RemoveStatusEnum;
import com.lizikj.shop.api.enums.PreQrCodeUsedStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Model for table -- shop_pregen_desk_qrcode
 * Created by zhoufe on 2018-44-22 10:44:41
 */
@ApiModel
public class ShopPregenDeskQrcodeVO implements Serializable {
    private static final long serialVersionUID = -1301283083322274287L;
    /**
     * 预生成二维码ID
     */
    @ApiModelProperty(value = "预生成二维码ID", required = true)
    private Long pregenDeskQrcodeId;

    /**
     * 商户ID
     */
    @ApiModelProperty(value = "商户ID", required = true)
    private Long merchantId;

    /**
     * 店铺ID
     */
    @ApiModelProperty(value = "店铺ID", required = true)
    private Long shopId;

    /**
     * 二维码标识：QR_日期_商圈ID_商户ID_店铺ID
     */
    @ApiModelProperty(value = "二维码标识：QR_日期_随机数_商户ID_店铺ID", required = true)
    private String pregenQrcodeUuid;

    /**
     * 使用状态：0.未绑定，1.已经绑定
     */
    @ApiModelProperty(value = "使用状态：见PreQrCodeUsedStatusEnum：FREE.未绑定，BINDING.已经绑定。", required = true)
    private PreQrCodeUsedStatusEnum usedStatus;

    /**
     * 有效时间：不设置为无限
     */
    @ApiModelProperty(value = "有效时间：不设置为无限", required = true)
    private Date validDate;

    /**
     * 是否删除:1.删除；0.未删除
     */
    @ApiModelProperty(value = "是否删除。", required = true)
    private RemoveStatusEnum removeStatus;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", required = true)
    private Date updateTime;

    /**
     * 预生成二维码操作记录
     */
    @ApiModelProperty(value = "预生成二维码操作记录", required = true)
    private Long pregenDeskQrcodeOptHisId;
    /**
     * 区域名称
     */
    @ApiModelProperty(value = "区域名称", required = true)
    private String areaName;

    /**
     * 桌台名称
     */
    @ApiModelProperty(value = "桌台名称", required = true)
    private String deskName;

    /**
     * 座位数
     */
    @ApiModelProperty(value = "座位数", required = true)
    private Integer seatNum;

    /**
     * 绑定桌台的名称
     */
    @ApiModelProperty(value = "绑定桌台的名称", required = true)
    private String bindingDeskName;

    /**
     * 预生成二维码ID
     */
    public Long getPregenDeskQrcodeId() {
        return pregenDeskQrcodeId;
    }

    /**
     * 预生成二维码ID
     * @param pregenDeskQrcodeId the value for shop_pregen_desk_qrcode.pregen_desk_qrcode_id
     */
    public void setPregenDeskQrcodeId(Long pregenDeskQrcodeId) {
        this.pregenDeskQrcodeId = pregenDeskQrcodeId;
    }

    /**
     * 商户ID
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * 商户ID
     * @param merchantId the value for shop_pregen_desk_qrcode.merchant_id
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 店铺ID
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 店铺ID
     * @param shopId the value for shop_pregen_desk_qrcode.shop_id
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 二维码标识：QR_日期_商圈ID_商户ID_店铺ID
     */
    public String getPregenQrcodeUuid() {
        return pregenQrcodeUuid;
    }

    /**
     * 二维码标识：QR_日期_商圈ID_商户ID_店铺ID
     * @param pregenQrcodeUuid the value for shop_pregen_desk_qrcode.pregen_qrcode_uuid
     */
    public void setPregenQrcodeUuid(String pregenQrcodeUuid) {
        this.pregenQrcodeUuid = pregenQrcodeUuid == null ? null : pregenQrcodeUuid.trim();
    }

    public PreQrCodeUsedStatusEnum getUsedStatus() {
        return usedStatus;
    }

    public void setUsedStatus(PreQrCodeUsedStatusEnum usedStatus) {
        this.usedStatus = usedStatus;
    }

    public RemoveStatusEnum getRemoveStatus() {
        return removeStatus;
    }

    public void setRemoveStatus(RemoveStatusEnum removeStatus) {
        this.removeStatus = removeStatus;
    }

    /**
     * 有效时间：不设置为无限
     */
    public Date getValidDate() {
        return validDate;
    }

    /**
     * 有效时间：不设置为无限
     * @param validDate the value for shop_pregen_desk_qrcode.valid_date
     */
    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }


    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime the value for shop_pregen_desk_qrcode.create_time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime the value for shop_pregen_desk_qrcode.update_time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getPregenDeskQrcodeOptHisId() {
        return pregenDeskQrcodeOptHisId;
    }

    public void setPregenDeskQrcodeOptHisId(Long pregenDeskQrcodeOptHisId) {
        this.pregenDeskQrcodeOptHisId = pregenDeskQrcodeOptHisId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getDeskName() {
        return deskName;
    }

    public void setDeskName(String deskName) {
        this.deskName = deskName;
    }

    public Integer getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(Integer seatNum) {
        this.seatNum = seatNum;
    }

    public String getBindingDeskName() {
        return bindingDeskName;
    }

    public void setBindingDeskName(String bindingDeskName) {
        this.bindingDeskName = bindingDeskName;
    }
}