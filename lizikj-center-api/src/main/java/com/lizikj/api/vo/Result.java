package com.lizikj.api.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Michael.Huang
 * @date 2017/6/21
 */
@ApiModel(description = "返回结果内容详情")
public class Result<T> implements Serializable {

	/**
	 * 通用成功业务码
	 */
	public static final String SUCCESS_BIZ_CODE = "10000";
	
	/**
	 * 通用失败业务码
	 */
	public static final String FAIL_BIZ_CODE = "10001";
	
    /**
     * 状态码
     */
    @ApiModelProperty(value = "1-成功;0-失败")
    private int code = 1;
    
    /**
     * 业务码
     */
    @ApiModelProperty(value = "失败时返回的业务码，通过业务码可以明确各个系统错误内容，通用失败业务码为10001，登录token过期为9999")
    private String bizCode;
    /**
     * 提示消息
     */
    @ApiModelProperty(value = "提示消息")
    private String message;

    /**
     * 详细提示信息，比如错误堆栈内容等
     */
    @ApiModelProperty(value = "详细提示信息，比如错误堆栈内容等")
    private String detailMessage;
    /**
     * 返回数据对象
     */
    @ApiModelProperty(value = "返回数据")
    private T data;
    
    /**
     * 服务器返回结果响应时间
     */
    @ApiModelProperty(value = "响应时间")
    private Long responseTime = new Date().getTime();

    public Result(int code, String bizCode, String message, T data) {
        this.code = code;
        this.bizCode = bizCode;
        this.message = message;
        this.data = data;
    }


    public Result(int code, String bizCode, String message) {
        this.code = code;
        this.bizCode = bizCode;
        this.message = message;
    }

    /**
     * 成功返回
     *
     * @return
     */
    public static Result SUCESS() {
        Result result = new Result(1, SUCCESS_BIZ_CODE, "成功");
        return result;
    }

    /**
     * 成功返回
     *
     * @return
     */
    public static Result SUCESS(Object data) {
        return new Result(1, SUCCESS_BIZ_CODE, "成功", data);
    }


    /**
     * 失败返回
     *
     * @return
     */
    public static Result FAILURE() {
        Result result = new Result(0, FAIL_BIZ_CODE, "失败");
        return result;
    }

    /**
     * 成功返回
     *
     * @return
     */
    public static Result FAILURE(String bizCode, String message) {
        return new Result(0, bizCode, message);
    }

    /**
     * 失败返回
     * @return
     */
    public static Result FAILURE(String message) {
        return new Result(0, FAIL_BIZ_CODE, message);
    }
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }


	public Long getResponseTime() {
		return responseTime;
	}


	public void setResponseTime(Long responseTime) {
		this.responseTime = responseTime;
	}
    
    
}

