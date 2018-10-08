package com.lizikj.api.vo.merchandise.param;

import com.lizikj.merchandise.dto.SkuPropertyDTO;
import com.lizikj.merchandise.enums.PackageTypeEnum;
import com.lizikj.merchandise.enums.SkuTypeEnum;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author dyh
 * @created at 2017 09 05 18:19
 */
public class SkuGenerateVO {
    private PackageTypeEnum packageType;
    private SkuTypeEnum skuType;
    private List<SkuPropertyDTO> skuProperties;
    private Long sellPrice;
    private Long memberPrice;
    private Long costPrice;
    private Long salesExtractAmount;

    public PackageTypeEnum getPackageType() {
        return packageType;
    }

    public void setPackageType(PackageTypeEnum packageType) {
        this.packageType = packageType;
    }

    public SkuTypeEnum getSkuType() {
        return skuType;
    }

    public void setSkuType(SkuTypeEnum skuType) {
        this.skuType = skuType;
    }

    public List<SkuPropertyDTO> getSkuProperties() {
        return skuProperties;
    }

    public void setSkuProperties(List<SkuPropertyDTO> skuProperties) {
        this.skuProperties = skuProperties;
    }

    public Long getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Long sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Long getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(Long memberPrice) {
        this.memberPrice = memberPrice;
    }

    public Long getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Long costPrice) {
        this.costPrice = costPrice;
    }

    public Long getSalesExtractAmount() {
        return salesExtractAmount;
    }

    public void setSalesExtractAmount(Long salesExtractAmount) {
        this.salesExtractAmount = salesExtractAmount;
    }
}
