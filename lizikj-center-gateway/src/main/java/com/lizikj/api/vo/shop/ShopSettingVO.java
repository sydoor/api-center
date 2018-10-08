package com.lizikj.api.vo.shop;

import java.util.Date;
import java.util.List;

import com.lizikj.common.enums.RemoveStatusEnum;
import com.lizikj.shop.api.enums.*;
import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Model for table -- shop_setting
 * Created by zhoufe on 2017-07-13 18:03:50
 */
@ApiModel
public class ShopSettingVO extends BaseDTO {
	
    private static final long serialVersionUID = 1L;

	/**
     * 店铺使用的配置ID
     */
	@ApiModelProperty(value = "店铺使用的配置ID", required = true)
    private Long shopSettingId;

    /**
     * 商户ID：一定有值
     */
    @ApiModelProperty(value = "商户ID", required = true)
    private Long merchantId;

    /**
     * 店铺ID
     */
	@ApiModelProperty(value = "店铺ID", required = true)
    private Long shopId;

    /**
     * 店铺配置模版ID：自定义的配置模板为空
     */
	@ApiModelProperty(value = "店铺配置模版ID：自定义的配置模板为空", required = true)
    private Long settingTemplateId;

    /**
     * 配置名称
     */
	@ApiModelProperty(value = "配置名称", required = true)
    private String settingName;

    /**
     * 设置编码：编码规则自动生成编码的开始，店铺设置1000，美食2000，营销活动3000，会员4000
     */
	@ApiModelProperty(value = "设置编码：编码规则生成编码的开始，店铺设置1000，美食2000，营销活动3000，会员4000", required = true)
    private Integer settingCode;

    /**
     * 与谁互斥：同一业务下，如果和该值相同，就和该配置互斥
     */
	@ApiModelProperty(value = "与谁互斥：同一业务下，如果和该值相同，就和该配置互斥", required = true)
    private Integer mutexGroup;

    /**
     * 是否多选：该配置的值是多选还是单选。1.是多选，0.是单选
     */
	@ApiModelProperty(value = "是否多选：见MultiSelectStatusEnum：该配置的值是多选还是单选。MULTI_SELECT.是多选，ONE_SELECT.是单选", required = true)
    private MultiSelectStatusEnum multiSelectStatus;

    /**
     * 是否启用：1.已启用，0，未启用，对指定店铺；后台设置不启用，前端显示灰色，不可编辑，店铺设置不启用，前端显 示可编辑，仍然有机会启用
     */
	@ApiModelProperty(value = "是否启用：见OpenStatusEnum：OPEN：已开启，CLOSE：未开启", required = true)
    private OpenStatusEnum openStatus;

    /**
     * 模版类型：1.系统，0.自定义
     */
	@ApiModelProperty(value = "模版类型：1.系统，0.自定义", required = true)
    private ShopSettingTypeEnum type;

    /**
     * 配置用途：0.其他，1.费用，2.常规收款方式
     */
	@ApiModelProperty(value = "配置用途：见UsedTypeEnum：OTHER.其他，FEE.费用，NORMAL_RECEIPT.常规收款方式", required = true)
    private UsedTypeEnum usedType;

    /**
     * 所属业务：1.店铺，2.美食，3.营销，4.会员，如果还有以后加
     */
	@ApiModelProperty(value = "所属业务：1.店铺，2.美食，3.营销，4.会员，如果还有以后加", required = true)
    private BizTypeEnum bizType;

    /**
     * 是否连锁的配置：1.连锁，0.门店
     */
    @ApiModelProperty(value = "是否连锁的配置：见ChainStatusEnum：CHAIN.连锁，SHOP.门店", required = true)
    private ChainStatusEnum chainStatus;

    /**
     * 排序
     */
	@ApiModelProperty(value = "排序", required = true)
    private Integer orderNo;

    /**
     * 提示信息
     */
	@ApiModelProperty(value = "提示信息", required = true)
    private String tips;


    /**
     * 是否删除:1.删除；0.未删除
     */
    @ApiModelProperty(value = "是否删除：见RemoveStatusEnum。", required = true)
    private RemoveStatusEnum removeStatus;
    
    /**
     * 设置的值
     */
	@ApiModelProperty(value = "设置的值", required = true)
    private List<ShopSettingValueVO> shopSettingValues;

    @ApiModelProperty(value = "更新时间", required = true)
    private Date updateTime;

    /**
     * 店铺使用的配置ID
     */
    public Long getShopSettingId() {
        return shopSettingId;
    }

    /**
     * 店铺使用的配置ID
     * @param shopSettingId the value for shop_setting.shop_setting_id
     */
    public void setShopSettingId(Long shopSettingId) {
        this.shopSettingId = shopSettingId;
    }

    /**
     * 店铺ID
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 店铺ID
     * @param shopId the value for shop_setting.shop_id
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 店铺配置模版ID：自定义的配置模板为空
     */
    public Long getSettingTemplateId() {
        return settingTemplateId;
    }

    /**
     * 店铺配置模版ID：自定义的配置模板为空
     * @param settingTemplateId the value for shop_setting.setting_template_id
     */
    public void setSettingTemplateId(Long settingTemplateId) {
        this.settingTemplateId = settingTemplateId;
    }

    /**
     * 配置名称
     */
    public String getSettingName() {
        return settingName;
    }

    /**
     * 配置名称
     * @param settingName the value for shop_setting.setting_name
     */
    public void setSettingName(String settingName) {
        this.settingName = settingName == null ? null : settingName.trim();
    }

    /**
     * 设置编码：编码规则自动生成编码的开始，店铺设置1000，美食2000，营销活动3000，会员4000
     */
    public Integer getSettingCode() {
        return settingCode;
    }

    /**
     * 设置编码：编码规则自动生成编码的开始，店铺设置1000，美食2000，营销活动3000，会员4000
     * @param settingCode the value for shop_setting.setting_code
     */
    public void setSettingCode(Integer settingCode) {
        this.settingCode = settingCode;
    }

    /**
     * 与谁互斥：同一业务下，如果和该值相同，就和该配置互斥
     */
    public Integer getMutexGroup() {
        return mutexGroup;
    }

    /**
     * 与谁互斥：同一业务下，如果和该值相同，就和该配置互斥
     * @param mutexGroup the value for shop_setting.mutex_group
     */
    public void setMutexGroup(Integer mutexGroup) {
        this.mutexGroup = mutexGroup;
    }

    public MultiSelectStatusEnum getMultiSelectStatus() {
        return multiSelectStatus;
    }

    public void setMultiSelectStatus(MultiSelectStatusEnum multiSelectStatus) {
        this.multiSelectStatus = multiSelectStatus;
    }

    public ShopSettingTypeEnum getType() {
        return type;
    }

    public void setType(ShopSettingTypeEnum type) {
        this.type = type;
    }

    /**
     * 排序
     */
    public Integer getOrderNo() {
        return orderNo;
    }

    /**
     * 排序
     * @param orderNo the value for shop_setting.order_no
     */
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 提示信息
     */
    public String getTips() {
        return tips;
    }

    /**
     * 提示信息
     * @param tips the value for shop_setting.tips
     */
    public void setTips(String tips) {
        this.tips = tips == null ? null : tips.trim();
    }

    public List<ShopSettingValueVO> getShopSettingValues() {
        return shopSettingValues;
    }

    public void setShopSettingValues(List<ShopSettingValueVO> shopSettingValues) {
        this.shopSettingValues = shopSettingValues;
    }

    public OpenStatusEnum getOpenStatus() {
        return openStatus;
    }

    public void setOpenStatus(OpenStatusEnum openStatus) {
        this.openStatus = openStatus;
    }

    public UsedTypeEnum getUsedType() {
        return usedType;
    }

    public void setUsedType(UsedTypeEnum usedType) {
        this.usedType = usedType;
    }

    public BizTypeEnum getBizType() {
        return bizType;
    }

    public void setBizType(BizTypeEnum bizType) {
        this.bizType = bizType;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public ChainStatusEnum getChainStatus() {
        return chainStatus;
    }

    public void setChainStatus(ChainStatusEnum chainStatus) {
        this.chainStatus = chainStatus;
    }

    public RemoveStatusEnum getRemoveStatus() {
        return removeStatus;
    }

    public void setRemoveStatus(RemoveStatusEnum removeStatus) {
        this.removeStatus = removeStatus;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}