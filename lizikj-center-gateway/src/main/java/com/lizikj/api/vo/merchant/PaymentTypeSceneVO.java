package com.lizikj.api.vo.merchant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liangxiaolin on 2017/7/12.
 */
@ApiModel
public class PaymentTypeSceneVO {
    /**
     * 场景ID
     */
    @ApiModelProperty(value = "场景ID")
    private Long sceneId;

    /**
     * 支付方式ID
     */
    @ApiModelProperty(value = "支付方式ID")
    private Long typeId;

    /**
     * 场景名称
     */
    @ApiModelProperty(value = "场景名称")
    private String sceneName;

    /**
     * 场景代码
     */
    @ApiModelProperty(value = "场景代码")
    private String sceneCode;

    /**
     * 选中状态  0: 否 1：是
     */
    @ApiModelProperty(value = "选中状态",notes = "0: 否 1：是")
    private Byte isSelected;

    public Long getSceneId() {
        return sceneId;
    }

    public void setSceneId(Long sceneId) {
        this.sceneId = sceneId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getSceneCode() {
        return sceneCode;
    }

    public void setSceneCode(String sceneCode) {
        this.sceneCode = sceneCode;
    }

    public Byte getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Byte isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public String toString() {
        return "PaymentTypeSceneVO{" +
                "sceneId=" + sceneId +
                ", typeId=" + typeId +
                ", sceneName='" + sceneName + '\'' +
                ", sceneCode='" + sceneCode + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}
