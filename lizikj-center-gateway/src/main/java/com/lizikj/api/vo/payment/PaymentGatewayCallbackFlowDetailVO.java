package com.lizikj.api.vo.payment;

import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhoufe
 * @date 2018/7/3 15:56
 */
@ApiModel
public class PaymentGatewayCallbackFlowDetailVO implements Serializable {
    @ApiModelProperty(value = "回调流水详情ID")
    private Long id;

    @ApiModelProperty(value = "回调流水ID")
    private Long callbackFlowId;

    @ApiModelProperty(value = "回调地址")
    private String callbackUrl;

    @ApiModelProperty(value = "回调的结果")
    private String responseData;

    @ApiModelProperty(value = "回调的内容")
    private String returnData;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    public PaymentGatewayCallbackFlowDetailVO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCallbackFlowId() {
        return callbackFlowId;
    }

    public void setCallbackFlowId(Long callbackFlowId) {
        this.callbackFlowId = callbackFlowId;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }

    public String getReturnData() {
        return returnData;
    }

    public void setReturnData(String returnData) {
        this.returnData = returnData;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}