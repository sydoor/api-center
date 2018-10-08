package com.lizikj.api.vo.shop.param;

import com.lizikj.shop.api.enums.OpenStatusEnum;
import com.lizikj.shop.api.enums.ShopSettingTypeEnum;
import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author zhoufe
 * @date 2017年7月19日 下午3:55:22
 */
@ApiModel
public class ShopSettingParamVO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 店铺ID
     */
    @ApiModelProperty(value = "店铺ID", required = true)
    private Long shopId;
    /**
     * 店铺配置ID
     */
    @ApiModelProperty(value = "店铺配置ID", required = true)
    private Long shopSettingId;

    /**
     * 配置类型
     */
    @ApiModelProperty(value = "配置类型：见：ShopSettingTypeEnum：SYSTEM.系统，CUSTOM.自定义", required = true)
    private ShopSettingTypeEnum type;

    /**
     * 配置名称
     */
    @ApiModelProperty(value = "配置名称", required = true)
    private String settingName;

    /**
     * 设置编码：编码规则自动生成编码的开始，店铺设置1000，美食2000，营销活动3000，会员4000
     */
    @ApiModelProperty(value = "设置编码", required = true)
    private Integer settingCode;

    /**
     * 是否启用：1.已启用，0，未启用，对指定店铺；后台设置不启用，前端显示灰色，不可编辑，店铺设置不启用，前端显 示可编辑，仍然有机会启用
     */
    @ApiModelProperty(value = "见OpenStatusEnum：OPEN.已启用，CLOSE，未启用", required = true)
    private OpenStatusEnum openStatus;

    /**
     * 设置的值
     */
    @ApiModelProperty(value = "设置的值", required = true)
    private List<ShopSettingValueParamVO> shopSettingValues;

    /**
     * @return the shopSettingId
     */
    public Long getShopSettingId() {
        return shopSettingId;
    }

    /**
     * @param shopSettingId the shopSettingId to set
     */
    public void setShopSettingId(Long shopSettingId) {
        this.shopSettingId = shopSettingId;
    }

    /**
     * @return the settingName
     */
    public String getSettingName() {
        return settingName;
    }

    /**
     * @param settingName the settingName to set
     */
    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public OpenStatusEnum getOpenStatus() {
        return openStatus;
    }

    public void setOpenStatus(OpenStatusEnum openStatus) {
        this.openStatus = openStatus;
    }

    public List<ShopSettingValueParamVO> getShopSettingValues() {
        return shopSettingValues;
    }

    public void setShopSettingValues(List<ShopSettingValueParamVO> shopSettingValues) {
        this.shopSettingValues = shopSettingValues;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Integer getSettingCode() {
        return settingCode;
    }

    public void setSettingCode(Integer settingCode) {
        this.settingCode = settingCode;
    }

    public ShopSettingTypeEnum getType() {
        return type;
    }

    public void setType(ShopSettingTypeEnum type) {
        this.type = type;
    }
}
