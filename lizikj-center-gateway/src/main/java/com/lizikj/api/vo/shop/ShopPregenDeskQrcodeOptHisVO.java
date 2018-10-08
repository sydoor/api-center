package com.lizikj.api.vo.shop;

import com.lizikj.api.vo.shop.param.PregenQrcodeParamVO;
import com.lizikj.common.enums.RemoveStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Model for table -- shop_pregen_desk_qrcode_opt_his
 * Created by zhoufe on 2018-8-22 15:08:09
 */
@ApiModel
public class ShopPregenDeskQrcodeOptHisVO implements Serializable {

    private static final long serialVersionUID = 2365736021062486614L;
    /**
     * 预生成二维码操作记录
     */
    @ApiModelProperty(value = "预生成二维码操作记录", required = true)
    private Long pregenDeskQrcodeOptHisId;

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
     * 是否删除:1.删除；0.未删除
     */
    @ApiModelProperty(value = "是否删除：见RemoveStatusEnum。", required = true)
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
     * 配置内容
     */
    @ApiModelProperty(value = "配置内容：JSON", required = true)
    private String content;

    /**
     * 由content转成的对象
     */
    @ApiModelProperty(value = "配置内容", required = true)
    private PregenQrcodeParamVO pregenQrcodeParam;

    /**
     * 图片资源ID
     */
    @ApiModelProperty(value = "图片资源ID：选中模板用")
    private Long qrcodeTemplatePicId;


    /**
     * 预生成二维码操作记录
     */
    public Long getPregenDeskQrcodeOptHisId() {
        return pregenDeskQrcodeOptHisId;
    }

    /**
     * 预生成二维码操作记录
     * @param pregenDeskQrcodeOptHisId the value for shop_pregen_desk_qrcode_opt_his.pregen_desk_qrcode_opt_his_id
     */
    public void setPregenDeskQrcodeOptHisId(Long pregenDeskQrcodeOptHisId) {
        this.pregenDeskQrcodeOptHisId = pregenDeskQrcodeOptHisId;
    }

    /**
     * 商户ID
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * 商户ID
     * @param merchantId the value for shop_pregen_desk_qrcode_opt_his.merchant_id
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
     * @param shopId the value for shop_pregen_desk_qrcode_opt_his.shop_id
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public RemoveStatusEnum getRemoveStatus() {
        return removeStatus;
    }

    public void setRemoveStatus(RemoveStatusEnum removeStatus) {
        this.removeStatus = removeStatus;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime the value for shop_pregen_desk_qrcode_opt_his.create_time
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
     * @param updateTime the value for shop_pregen_desk_qrcode_opt_his.update_time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 配置内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 配置内容
     * @param content the value for shop_pregen_desk_qrcode_opt_his.content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public PregenQrcodeParamVO getPregenQrcodeParam() {
        return pregenQrcodeParam;
    }

    public void setPregenQrcodeParam(PregenQrcodeParamVO pregenQrcodeParam) {
        this.pregenQrcodeParam = pregenQrcodeParam;
    }

    public Long getQrcodeTemplatePicId() {
        return qrcodeTemplatePicId;
    }

    public void setQrcodeTemplatePicId(Long qrcodeTemplatePicId) {
        this.qrcodeTemplatePicId = qrcodeTemplatePicId;
    }
}