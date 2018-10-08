package com.lizikj.api.vo.merchandise;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author dyh
 * @created at 2017 09 19 20:40
 */
@ApiModel(value = "sku生成结果")
public class SkuGenerateResultVO {

    @ApiModelProperty(value = "生成的Sku")
    private List<SkuVO> skus;

    @ApiModelProperty(value = "SKuProperty，id已经由服务端生成")
    private List<SkuPropertyVO> skuProperties;


    public List<SkuVO> getSkus() {
        return skus;
    }

    public void setSkus(List<SkuVO> skus) {
        this.skus = skus;
    }

    public List<SkuPropertyVO> getSkuProperties() {
        return skuProperties;
    }

    public void setSkuProperties(List<SkuPropertyVO> skuProperties) {
        this.skuProperties = skuProperties;
    }
}
