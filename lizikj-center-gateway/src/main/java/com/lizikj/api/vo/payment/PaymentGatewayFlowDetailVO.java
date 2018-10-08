package com.lizikj.api.vo.payment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class PaymentGatewayFlowDetailVO{
    /**
     * 请求流水详情ID
     */
	@ApiModelProperty(value = "流水详情ID")
    private Long id;

    /**
     * 请求流水ID
     */
	@ApiModelProperty(value = "流水ID")
    private Long reqFlowId;

    /**
     * 请求URL
     */
	@ApiModelProperty(value = "请求URL")
    private String requestUrl;

    /**
     * 请求数据
     */
	@ApiModelProperty(value = "请求数据")
    private String requestData;

    /**
     * 响应数据
     */
	@ApiModelProperty(value = "响应数据")
    private String responseData;

    /**
     * 请求流水详情ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 请求流水详情ID
     * @param id the value for payment_gateway_flow_detail.id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 请求流水ID
     */
    public Long getReqFlowId() {
        return reqFlowId;
    }

    /**
     * 请求流水ID
     * @param reqFlowId the value for payment_gateway_flow_detail.req_flow_id
     */
    public void setReqFlowId(Long reqFlowId) {
        this.reqFlowId = reqFlowId;
    }

    /**
     * 请求URL
     */
    public String getRequestUrl() {
        return requestUrl;
    }

    /**
     * 请求URL
     * @param requestUrl the value for payment_gateway_flow_detail.request_url
     */
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    /**
     * 请求数据
     */
    public String getRequestData() {
        return requestData;
    }

    /**
     * 请求数据
     * @param requestData the value for payment_gateway_flow_detail.request_data
     */
    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    /**
     * 响应数据
     */
    public String getResponseData() {
        return responseData;
    }

    /**
     * 响应数据
     * @param responseData the value for payment_gateway_flow_detail.response_data
     */
    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }
}