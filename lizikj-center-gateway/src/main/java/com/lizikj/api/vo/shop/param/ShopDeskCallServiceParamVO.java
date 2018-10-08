package com.lizikj.api.vo.shop.param;

import com.lizikj.shop.api.enums.CallStatusEnum;
import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 呼叫服务的参数
 * @author zhoufe 
 * @date 2017年7月19日 上午10:09:37 
 */
@ApiModel
public class ShopDeskCallServiceParamVO extends BaseDTO {
	
	private static final long serialVersionUID = 1L;

	/**
     * 桌台ID
     */
	@ApiModelProperty(value = "桌台ID", required = true)
    private Long areaDeskId;

    /**
     * 呼叫状态
     */
	@ApiModelProperty(value = "呼叫状态：见：CallStatusEnum：HAD_CALL.有呼叫，NOT_CALL.不呼叫。", required = true)
    private CallStatusEnum callStatus;

	/**
	 * 呼叫服务话术ID
	 */
	@ApiModelProperty(value = "呼叫服务话术ID")
    private Long callServiceWordsId;

	public Long getAreaDeskId() {
		return areaDeskId;
	}

	public void setAreaDeskId(Long areaDeskId) {
		this.areaDeskId = areaDeskId;
	}

	public CallStatusEnum getCallStatus() {
		return callStatus;
	}

	public void setCallStatus(CallStatusEnum callStatus) {
		this.callStatus = callStatus;
	}

	public Long getCallServiceWordsId() {
		return callServiceWordsId;
	}

	public void setCallServiceWordsId(Long callServiceWordsId) {
		this.callServiceWordsId = callServiceWordsId;
	}
}
