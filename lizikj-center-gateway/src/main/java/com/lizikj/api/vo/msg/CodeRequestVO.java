package com.lizikj.api.vo.msg;

import com.lizikj.message.api.enums.MsgBizCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lxl
 * @Date 2018/4/27 15:09
 */
@ApiModel
public class CodeRequestVO {
    @ApiModelProperty(value = "电话")
    private String mobile;
    @ApiModelProperty(value = "业务类型")
    private MsgBizCode msgBizCode;
    @ApiModelProperty(value = "发送内容模板 StringFormat格式")
    private String content;
    @ApiModelProperty(value = "是否发送成功")
    private Boolean success;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public MsgBizCode getMsgBizCode() {
        return msgBizCode;
    }

    public void setMsgBizCode(MsgBizCode msgBizCode) {
        this.msgBizCode = msgBizCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "CodeRequestVO{" +
                "mobile='" + mobile + '\'' +
                ", msgBizCode=" + msgBizCode +
                ", content='" + content + '\'' +
                ", success=" + success +
                '}';
    }
}