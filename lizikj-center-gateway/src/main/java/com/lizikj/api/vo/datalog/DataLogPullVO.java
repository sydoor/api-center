package com.lizikj.api.vo.datalog;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @auth zone
 * @date 2017-09-21
 */
@ApiModel(value = "数据拉取vo")
public class DataLogPullVO {
	@ApiModelProperty(required = true, name = "数据类型", value = "数据类型 1：商品   2：会员  3：订单  4：卡券  5：员工  6：店铺管理  7：营销活动  8：基础数据管理", dataType = "Byte")
	byte dataType;
	
	@ApiModelProperty(required = true, name = "对应数据类型的影响数据", value = "operationLogVOs", dataType = "List")
	List<DataOperationLogVO> operationLogVOs;
	
	public byte getDataType() {
		return dataType;
	}
	public void setDataType(byte dataType) {
		this.dataType = dataType;
	}
	public List<DataOperationLogVO> getOperationLogVOs() {
		return operationLogVOs;
	}
	public void setOperationLogVOs(List<DataOperationLogVO> operationLogVOs) {
		this.operationLogVOs = operationLogVOs;
	}
}
