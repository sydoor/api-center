package com.lizikj.api.vo.reporting;

import com.lizikj.marketing.api.enums.SupportCouponTimeTypeEnum;
import com.lizikj.reporting.dto.MerchandiseReportDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liangxiaolin on 2017/8/9.
 */
@ApiModel
public class MerchandiseReportVO {
    /**
     * 统计ID
     */
    @ApiModelProperty(value = "统计ID")
    private Long merchandiseReportId;
    /**
     * 统计日期
     */
    @ApiModelProperty(value = "统计日期")
    private Date reportDate;
    /**
     * 商户ID
     */
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    /**
     * 商户名称
     */
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    /**
     * 店铺ID
     */
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    /**
     * 店铺名称
     */
    @ApiModelProperty(value = "店铺名称")
    private String shopName;
    /**
     * 分类ID
     */
    @ApiModelProperty(value = "分类ID")
    private String categoryId;
    /**
     * 分类名称
     */
    @ApiModelProperty(value = "分类名称")
    private String categoryName;
    /**
     * 美食ID
     */
    @ApiModelProperty(value = "美食ID")
    private String goodsId;
    /**
     * 美食名称
     */
    @ApiModelProperty(value = "美食名称")
    private String goodsName;
    /**
     * 是否记重
     */
    @ApiModelProperty(value = "是否记重 1 是 0 否")
    private Boolean isWeight;
    /**
     * 记重菜单位
     */
    @ApiModelProperty(value = "记重菜单位")
    private String goodUnit;
    /**
     * 销量（记重 +斤）
     */
    @ApiModelProperty(value = "销量（记重 +斤）")
    private Integer saleNum;
    /**
     * 销量（数量，份）
     */
    @ApiModelProperty(value = "销量（数量，份）")
    private Integer saleQuantity;
    /**
     * 销售金额
     */
    @ApiModelProperty(value = "销售金额")
    private Integer saleAmount;
    /**
     * 销售成本
     */
    @ApiModelProperty(value = "销售成本")
    private Integer saleCost;
    /**
     * 毛利润
     */
    @ApiModelProperty(value = "毛利润")
    private Long saleProfit;
    /**
     * 统计状态 1成功 2失败 0进行中 默认0
     */
    @ApiModelProperty(value = "统计状态 1成功 2失败 0进行中 默认0")
    private Boolean reportStatus;
    @ApiModelProperty(value = "是否闲时")
    private SupportCouponTimeTypeEnum idleStatus;

    public static MerchandiseReportVO from(MerchandiseReportDTO dto){
        MerchandiseReportVO vo = new MerchandiseReportVO();
        vo.setGoodsName(dto.getGoodsName());
        vo.setGoodUnit(dto.getGoodsUnit());
        vo.setCategoryId(dto.getCategoryId());
        vo.setCategoryName(dto.getCategoryName());
        vo.setGoodsId(dto.getGoodsId());
        vo.setMerchandiseReportId(dto.getMerchandiseReportId());
        vo.setMerchantId(dto.getMerchantId());
        vo.setShopId(dto.getShopId());
        vo.setMerchantName(dto.getMerchantName());
        vo.setShopName(dto.getShopName());
        vo.setReportDate(dto.getReportDate());
        vo.setReportStatus(dto.getReportStatus());
        vo.setSaleAmount(dto.getSaleAmount());
        vo.setSaleCost(dto.getSaleCost());
        vo.setSaleNum(dto.getSaleNum());
        vo.setSaleProfit(dto.getSaleProfit());
        vo.setSaleQuantity(dto.getSaleQuantity());
        vo.setWeight(dto.getWeight());
        vo.setIdleStatus(SupportCouponTimeTypeEnum.getType(dto.getIdleStatus()));
        return vo;
    }

    public static List<MerchandiseReportVO> from(List<MerchandiseReportDTO> dtos){
        List<MerchandiseReportVO> vos = new ArrayList<>();
        dtos.forEach(dto -> vos.add(from(dto)));
        return vos;
    }

    public Long getMerchandiseReportId() {
        return merchandiseReportId;
    }

    public void setMerchandiseReportId(Long merchandiseReportId) {
        this.merchandiseReportId = merchandiseReportId;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Boolean getWeight() {
        return isWeight;
    }

    public void setWeight(Boolean weight) {
        isWeight = weight;
    }

    public String getGoodUnit() {
        return goodUnit;
    }

    public void setGoodUnit(String goodUnit) {
        this.goodUnit = goodUnit;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public Integer getSaleQuantity() {
        return saleQuantity;
    }

    public void setSaleQuantity(Integer saleQuantity) {
        this.saleQuantity = saleQuantity;
    }

    public Integer getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(Integer saleAmount) {
        this.saleAmount = saleAmount;
    }

    public Integer getSaleCost() {
        return saleCost;
    }

    public void setSaleCost(Integer saleCost) {
        this.saleCost = saleCost;
    }

    public Long getSaleProfit() {
        return saleProfit;
    }

    public void setSaleProfit(Long saleProfit) {
        this.saleProfit = saleProfit;
    }

    public Boolean getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(Boolean reportStatus) {
        this.reportStatus = reportStatus;
    }

    public SupportCouponTimeTypeEnum getIdleStatus() {
        return idleStatus;
    }

    public void setIdleStatus(SupportCouponTimeTypeEnum idleStatus) {
        this.idleStatus = idleStatus;
    }
}
