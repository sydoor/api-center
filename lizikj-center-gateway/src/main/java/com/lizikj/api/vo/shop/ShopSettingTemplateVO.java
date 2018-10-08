package com.lizikj.api.vo.shop;

import java.util.List;

import com.lizikj.shop.api.enums.*;
import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Model for table -- shop_setting_template
 * Created by zhoufe on 2017-07-13 18:03:50
 */
@ApiModel
public class ShopSettingTemplateVO extends BaseDTO {
    
	private static final long serialVersionUID = 1L;

	/**
     * 店铺配置模版ID
     */
	@ApiModelProperty(value = "店铺配置模版ID", required = true)
    private Long settingTemplateId;

    /**
     * 模板名称：如有，营业模式，启用餐桌号，开启自助点餐接单，自助点餐接单接单方式。
     */
	@ApiModelProperty(value = "模板名称：如有，营业模式，启用餐桌号，开启自助点餐接单，自助点餐接单接单方式", required = true)
    private String settingName;

    /**
     * 模板编码：编码规则自动生成编码的开始，店铺1000，美食2000，营销活动3000，会员4000
     */
	@ApiModelProperty(value = "模板编码：编码规则自动生成编码的开始，店铺1000，美食2000，营销活动3000，会员4000", required = true)
    private Integer settingCode;

    /**
     * 与谁互斥：同一业务下，如果和该值相同，就和该配置互斥
     */
	@ApiModelProperty(value = "与谁互斥：同一业务下，如果和该值相同，就和该配置互斥", required = true)
    private Integer mutexGroup;

    /**
     * 是否多选：该配置的值是多选还是单选。1.是多选，0.是单选，如果是多选就可以多个值，如果改成单选就随机保留一个设置值表中的记录
     */
	@ApiModelProperty(value = "是否多选：见MultiSelectStatusEnum：该配置的值是多选还是单选。MULTI_SELECT.是多选，ONE_SELECT.是单选，如果是多选就可以多个值，如果改成单选就随机保留一个设置值表中的记录", required = true)
    private MultiSelectStatusEnum multiSelectStatus;

    /**
     * 是否启用：1.已启用，0，未启用；这里未启用就区别都要更新店铺的配置，相当于总开关；后台设置不启用，前端显示灰色，不可编辑，店铺设置不启用，前端显 示可编辑，仍然有机会启用
     */
	@ApiModelProperty(value = "是否启用：见OpenStatusEnum：OPEN.已启用，CLOSE，未启用", required = true)
    private OpenStatusEnum openStatus;

    /**
     * 模版类型：1.系统，0.自定义
     */
	@ApiModelProperty(value = "模版类型：见：ShopSettingTypeEnum，SYSTEM.系统，CUSTOM.自定义", required = true)
    private ShopSettingTypeEnum type;

    /**
     * 配置用途：0.其他，1.费用，2.常规收款方式
     */
	@ApiModelProperty(value = "配置用途：见UsedTypeEnum：OTHER.其他，FEE.费用，NORMAL_RECEIPT.常规收款方式", required = true)
    private UsedTypeEnum usedType;

    /**
     * 所属业务：1.店铺，2.美食，3.营销，4.会员，如果还有以后加；也可以按业务系统来分
     */
	@ApiModelProperty(value = "所属业务：见BizTypeEnum：SHOP.店铺，MERCHANDISE.美食，MARKETING.营销，MEMBER.会员，如果还有以后加；也可以按业务系统来分", required = true)
    private BizTypeEnum bizType;


//    /**
//     * 是否连锁的配置：1.连锁，0.门店
//     */
//    @ApiModelProperty(value = "是否连锁的配置：见ChainStatusEnum：CHAIN.连锁，SHOP.门店", required = true)
//    private ChainStatusEnum chainStatus;

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
     * 模板设置的值
     */
	@ApiModelProperty(value = "模板设置的值", required = true)
    private List<ShopSettingTemplateValueVO> shopSettingTemplateValues;

    /**
     * 店铺配置模版ID
     */
    public Long getSettingTemplateId() {
        return settingTemplateId;
    }

    /**
     * 店铺配置模版ID
     * @param settingTemplateId the value for shop_setting_template.setting_template_id
     */
    public void setSettingTemplateId(Long settingTemplateId) {
        this.settingTemplateId = settingTemplateId;
    }

    /**
     * 模板名称：有，营业模式，茶位费，启用餐桌号，开启自助点餐接单，自助点餐接单接单方式。
     */
    public String getSettingName() {
        return settingName;
    }

    /**
     * 模板名称：有，营业模式，茶位费，启用餐桌号，开启自助点餐接单，自助点餐接单接单方式。
     * @param settingName the value for shop_setting_template.setting_name
     */
    public void setSettingName(String settingName) {
        this.settingName = settingName == null ? null : settingName.trim();
    }

    /**
     * 模板编码：编码规则自动生成编码的开始，美食2000，营销活动3000，会员4000
     */
    public Integer getSettingCode() {
        return settingCode;
    }

    /**
     * 模板编码：编码规则自动生成编码的开始，美食2000，营销活动3000，会员4000
     * @param settingCode the value for shop_setting_template.setting_code
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
     * @param mutexGroup the value for shop_setting_template.mutex_group
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

    public OpenStatusEnum getOpenStatus() {
        return openStatus;
    }

    public void setOpenStatus(OpenStatusEnum openStatus) {
        this.openStatus = openStatus;
    }

    public ShopSettingTypeEnum getType() {
        return type;
    }

    public void setType(ShopSettingTypeEnum type) {
        this.type = type;
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

    /**
     * 排序
     */
    public Integer getOrderNo() {
        return orderNo;
    }

    /**
     * 排序
     * @param orderNo the value for shop_setting_template.order_no
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
     * @param tips the value for shop_setting_template.tips
     */
    public void setTips(String tips) {
        this.tips = tips == null ? null : tips.trim();
    }

    public List<ShopSettingTemplateValueVO> getShopSettingTemplateValues() {
        return shopSettingTemplateValues;
    }

    public void setShopSettingTemplateValues(List<ShopSettingTemplateValueVO> shopSettingTemplateValues) {
        this.shopSettingTemplateValues = shopSettingTemplateValues;
    }
}