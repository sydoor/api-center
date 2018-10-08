package com.lizikj.api.vo.msg;

import com.lizikj.message.api.enums.TerminalTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author lxl
 * @Date 2017/11/29 10:18
 */
@ApiModel
public class DeviceAndUserRecordVO {
    /**
     * 记录ID
     */
    @ApiModelProperty(value = "记录ID")
    private Long recordId;
    @ApiModelProperty(value = "终端类型")
    private TerminalTypeEnum terminalTypeEnum;
    /**
     * 登录时间
     */
    @ApiModelProperty(value = "登录时间")
    private Date loginTime;
    /**
     * 消息系统用户ID
     */
    @ApiModelProperty(value = "消息系统用户ID")
    private Long msgUserId;
    /**
     * 别名
     */
    @ApiModelProperty(value = "别名")
    private String userAlias;
    /**
     * SN
     */
    @ApiModelProperty(value = "SN")
    private String sn;
    /**
     * 第三方注册ID
     */
    @ApiModelProperty(value = "第三方注册ID")
    private String registerId;
    /**
     * 第三方平台名称
     */
    @ApiModelProperty(value = "第三方注册ID")
    private String thirdPlatformName;
    /**
     * 店铺ID
     */
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    /**
     * 商户ID
     */
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    /**
     * 登录用户ID
     */
    @ApiModelProperty(value = "登录用户ID")
    private Long userId;

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public TerminalTypeEnum getTerminalTypeEnum() {
        return terminalTypeEnum;
    }

    public void setTerminalTypeEnum(TerminalTypeEnum terminalTypeEnum) {
        this.terminalTypeEnum = terminalTypeEnum;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Long getMsgUserId() {
        return msgUserId;
    }

    public void setMsgUserId(Long msgUserId) {
        this.msgUserId = msgUserId;
    }

    public String getUserAlias() {
        return userAlias;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public String getThirdPlatformName() {
        return thirdPlatformName;
    }

    public void setThirdPlatformName(String thirdPlatformName) {
        this.thirdPlatformName = thirdPlatformName;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
