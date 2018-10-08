package com.lizikj.api.vo.shop.param;

import com.lizikj.common.enums.RemoveStatusEnum;
import com.lizikj.shop.api.enums.*;
import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhoufe
 * @date 2017/11/27 10:50
 */
@ApiModel
public class QueryShopSettingVO implements Serializable {

    private static final long serialVersionUID = -9192814711828095034L;
    /**
     * 商户ID：一定有值
     */
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;

    /**
     * 店铺ID：不一定有值，连锁店的老板登录的时候就没有这个店铺ID
     */
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    /**
     * 配置名称
     */
    @ApiModelProperty(value = "配置名称")
    private String settingName;

    /**
     * 设置编码：编码规则生成编码的开始，店铺设置1000，美食2000，营销活动3000，会员4000
     */
    @ApiModelProperty(value = "设置编码")
    private Integer settingCode;

    /**
     * 是否启用：1.已启用，0，未启用，对指定店铺；
     */
    @ApiModelProperty(value = "是否启用")
    private OpenStatusEnum openStatus;

    /**
     * 模版类型：1.系统，0.自定义
     */
    @ApiModelProperty(value = "模版类型")
    private ShopSettingTypeEnum type;

    /**
     * 配置用途：0.其他，1.费用，2.常规收款方式
     */
    @ApiModelProperty(value = "配置用途")
    private UsedTypeEnum usedType;

    /**
     * 所属业务：1.店铺，2.美食，3.营销，4.会员，如果还有以后加
     */
    @ApiModelProperty(value = "所属业务")
    private BizTypeEnum bizType;

    /**
     * 是否连锁的配置：1.连锁，0.门店
     */
    @ApiModelProperty(value = "是否连锁的配置")
    private ChainStatusEnum chainStatus;

    /**
     * 是否删除:1.删除；0.未删除
     */
    @ApiModelProperty(value = "是否删除")
    private RemoveStatusEnum removeStatus;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建开始时间")
    private Date startCreateTime;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建结束时间")
    private Date endCreateTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新开始时间")
    private Date startUpdateTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新结束时间")
    private Date endUpdateTime;

    @ApiModelProperty(value = "页码")
    private int pageNum;

    @ApiModelProperty(value = "页大小")
    private int pageSize;


    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public Integer getSettingCode() {
        return settingCode;
    }

    public void setSettingCode(Integer settingCode) {
        this.settingCode = settingCode;
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

    public Date getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(Date startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public Date getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(Date endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

    public Date getStartUpdateTime() {
        return startUpdateTime;
    }

    public void setStartUpdateTime(Date startUpdateTime) {
        this.startUpdateTime = startUpdateTime;
    }

    public Date getEndUpdateTime() {
        return endUpdateTime;
    }

    public void setEndUpdateTime(Date endUpdateTime) {
        this.endUpdateTime = endUpdateTime;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
