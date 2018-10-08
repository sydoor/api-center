package com.lizikj.api.vo.msg;

import com.lizikj.message.api.enums.MsgBizCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lxl
 * @Date 2018/4/27 11:35
 */
@ApiModel
public class CodeConfirmVO {
    @ApiModelProperty(value = "电话")
    private String mobile;
    @ApiModelProperty(value = "验证码")
    private String code;
    @ApiModelProperty(value = "业务码")
    private MsgBizCode msgBizCode;
    @ApiModelProperty(value = "是否验证通过")
    private Boolean validMode;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MsgBizCode getMsgBizCode() {
        return msgBizCode;
    }

    public void setMsgBizCode(MsgBizCode msgBizCode) {
        this.msgBizCode = msgBizCode;
    }

    public Boolean getValidMode() {
        return validMode;
    }

    public void setValidMode(Boolean validMode) {
        this.validMode = validMode;
    }

    @Override
    public String toString() {
        return "CodeConfirmVO{" +
                "mobile='" + mobile + '\'' +
                ", code=" + code +
                ", msgBizCode=" + msgBizCode +
                ", validMode=" + validMode +
                '}';
    }
}
