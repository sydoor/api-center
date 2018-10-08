package com.lizikj.api.vo.shop;

import com.lizikj.shop.api.enums.OpenStatusEnum;
import com.lizikj.shop.api.enums.PosActiveStatusEnum;
import com.lizikj.shop.api.enums.PosDeviceRoleEnum;
import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 和ShopEquipmentVO都出值一张数据库表
 * Model for table -- shop_pos_device
 * Created by zhoufe on 2017-07-13 20:28:43
 */
@ApiModel
public class ShopPosDeviceVO extends BaseDTO{
    /**
     * pos设备ID
     */
    @ApiModelProperty(value = "pos设备ID", required = true)
    private Long equipmentId;
    
    /**
     * 商户ID
     */
    @ApiModelProperty(value = "商户ID", required = true)
    private Long merchantId;

    /**
     * 店铺ID：解绑时此字段为0
     */
    @ApiModelProperty(value = "店铺ID", required = true)
    private Long shopId;

    /**
     * SN号：申请时填入
     */
    @ApiModelProperty(value = "SN号", required = true)
    private String snNum;

    /**
     * 小票打印联数
     */
    @ApiModelProperty(value = "小票打印联数", required = true)
    private Integer ticketPrintNum;

    /**
     * 开启语音提醒：1.已开启；0.未开启
     */
    @ApiModelProperty(value = "小票打印联数：见OpenStatusEnum：OPEN.已开启，CLOSE.未开启", required = true)
    private OpenStatusEnum voicedStatus;

    /**
     * 开启打印机：1.已开启；0.未开启；针对每个pos机，不开启，那么本pos下的单就不会发送到厨房；开启之后，只有主设备可以新增打印机，填写的打印机要选择商品，这样订单有其中的商品就会按不同的打印机打印
     */
    @ApiModelProperty(value = "开启打印机", required = true)
    private OpenStatusEnum openPrintedStatus;

    /**
     * 设备身份：1.主设备；0.从设备；在app门店管理中，只有主设备可以设置自助点餐接单，第一台激活的设备为主设备
     */
    @ApiModelProperty(value = "设备身份：见PosDeviceRoleEnum： MASTER.主Pos，NOT_MASTER.非主Pos", required = true)
    private PosDeviceRoleEnum deviceRole;

    /**
     * POS版本号
     */
    @ApiModelProperty(value = "POS版本号", required = true)
    private String posVersion;

    /**
     * 激活状态：0.未激活，1.激活，2.停用
     */
    @ApiModelProperty(value = "激活状态", required = true)
    private PosActiveStatusEnum activeStatus;

    /**
     * 备注
     */
    private String remark;

    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    /**
     * 商户ID
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * 商户ID
     * @param merchantId the value for shop_pos_device.merchant_id
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 店铺ID：解绑时此字段为0
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 店铺ID：解绑时此字段为0
     * @param shopId the value for shop_pos_device.shop_id
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * SN号：申请时填入
     */
    public String getSnNum() {
        return snNum;
    }

    /**
     * SN号：申请时填入
     * @param snNum the value for shop_pos_device.sn_num
     */
    public void setSnNum(String snNum) {
        this.snNum = snNum == null ? null : snNum.trim();
    }

    /**
     * 小票打印联数
     */
    public Integer getTicketPrintNum() {
        return ticketPrintNum;
    }

    /**
     * 小票打印联数
     * @param ticketPrintNum the value for shop_pos_device.ticket_print_num
     */
    public void setTicketPrintNum(Integer ticketPrintNum) {
        this.ticketPrintNum = ticketPrintNum;
    }

    public PosDeviceRoleEnum getDeviceRole() {
        return deviceRole;
    }

    public void setDeviceRole(PosDeviceRoleEnum deviceRole) {
        this.deviceRole = deviceRole;
    }

    /**
     * POS版本号
     */
    public String getPosVersion() {
        return posVersion;
    }

    /**
     * POS版本号
     * @param posVersion the value for shop_pos_device.pos_version
     */
    public void setPosVersion(String posVersion) {
        this.posVersion = posVersion == null ? null : posVersion.trim();
    }

    /**
     * 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark the value for shop_pos_device.remark
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public OpenStatusEnum getVoicedStatus() {
        return voicedStatus;
    }

    public void setVoicedStatus(OpenStatusEnum voicedStatus) {
        this.voicedStatus = voicedStatus;
    }

    public OpenStatusEnum getOpenPrintedStatus() {
        return openPrintedStatus;
    }

    public void setOpenPrintedStatus(OpenStatusEnum openPrintedStatus) {
        this.openPrintedStatus = openPrintedStatus;
    }

    public PosActiveStatusEnum getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(PosActiveStatusEnum activeStatus) {
        this.activeStatus = activeStatus;
    }
}