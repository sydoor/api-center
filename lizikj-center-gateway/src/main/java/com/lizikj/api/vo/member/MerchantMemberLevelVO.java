package com.lizikj.api.vo.member;

import java.util.List;

import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 会员等级
 * Created by liangjiankang on 2017-07-06 15:31:33
 */
@ApiModel(value="会员等级")
public class MerchantMemberLevelVO extends BaseDTO {
    private static final long serialVersionUID = 1988996056305348850L;
    /**
     * 等级id
     */
    @ApiModelProperty(value = "等级id")
    private Long levelId;
    /**
     * 等级名
     */
    @ApiModelProperty(value = "等级名")
    private String levelName;

    /**
     * 等级code 1,2,3,4,5最多5级
     */
    @ApiModelProperty(value = "等级code 1,2,3,4,5最多5级")
    private Integer levelCode;
    /**
     * 父类id
     */
    @ApiModelProperty(value = "父类id")
    private Long parentLevelId;

    /**
     * 商户id
     */
    @ApiModelProperty(value = "商户id")
    private Long merchantId;

    /**
     * 折扣
     */
    @ApiModelProperty(value = "折扣")
    private Double discount;

    /**
     * 是否开放积分功能 0关 1开
     */
    @ApiModelProperty(value = "是否开放积分功能 0关 1开")
    private Integer openCreditStatus;

    /**
     * 等级类型 5100 普通  5200 特种
     */
    @ApiModelProperty(value = "等级类型 5100 普通  5200 特种")
    private Integer levelType;

    /**
     * 升级条件列表
     */
    @ApiModelProperty(value = "升级条件列表")
	private List<MerchantMemberLevelConditionVO> conditionList;
    
    /**
     * 其它等级折扣信息列表
     */
    @ApiModelProperty(value = "其它等级折扣信息列表")
    private List<MerchantMemberLevelVO> otherLevelInfoList;

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(Integer levelCode) {
        this.levelCode = levelCode;
    }

    public Long getParentLevelId() {
        return parentLevelId;
    }

    public void setParentLevelId(Long parentLevelId) {
        this.parentLevelId = parentLevelId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getOpenCreditStatus() {
        return openCreditStatus;
    }

    public void setOpenCreditStatus(Integer openCreditStatus) {
        this.openCreditStatus = openCreditStatus;
    }

    public Integer getLevelType() {
        return levelType;
    }

    public void setLevelType(Integer levelType) {
        this.levelType = levelType;
    }

    public List<MerchantMemberLevelConditionVO> getConditionList() {
        return conditionList;
    }

    public void setConditionList(List<MerchantMemberLevelConditionVO> conditionList) {
        this.conditionList = conditionList;
    }

    public List<MerchantMemberLevelVO> getOtherLevelInfoList() {
        return otherLevelInfoList;
    }

    public void setOtherLevelInfoList(List<MerchantMemberLevelVO> otherLevelInfoList) {
        this.otherLevelInfoList = otherLevelInfoList;
    }
}