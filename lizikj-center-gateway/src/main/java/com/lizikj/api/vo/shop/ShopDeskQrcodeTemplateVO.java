package com.lizikj.api.vo.shop;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Model for table -- shop_desk_qrcode_template
 * Created by zhoufe on 2018-51-10 15:51:59
 */
@ApiModel
public class ShopDeskQrcodeTemplateVO implements Serializable {

    private static final long serialVersionUID = -6766428352628255742L;
    /**
     * 模板ID
     */
    @ApiModelProperty(value = "模板ID")
    private Long deskQrcodeTemplateId;

    /**
     * 模板名称
     */
    @ApiModelProperty(value = "模板名称")
    private String templateName;

    /**
     * 图片资源ID
     */
    @ApiModelProperty(value = "图片资源ID")
    private Long qrcodeTemplatePicId;


    @ApiModelProperty(value = "模板图片配置信息")
    private QrcodeTemplateLocationVO qrcodeTemplateLocation;


    public Long getDeskQrcodeTemplateId() {
        return deskQrcodeTemplateId;
    }

    public void setDeskQrcodeTemplateId(Long deskQrcodeTemplateId) {
        this.deskQrcodeTemplateId = deskQrcodeTemplateId;
    }

    public Long getQrcodeTemplatePicId() {
        return qrcodeTemplatePicId;
    }

    public void setQrcodeTemplatePicId(Long qrcodeTemplatePicId) {
        this.qrcodeTemplatePicId = qrcodeTemplatePicId;
    }

    public QrcodeTemplateLocationVO getQrcodeTemplateLocation() {
        return qrcodeTemplateLocation;
    }

    public void setQrcodeTemplateLocation(QrcodeTemplateLocationVO qrcodeTemplateLocation) {
        this.qrcodeTemplateLocation = qrcodeTemplateLocation;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}