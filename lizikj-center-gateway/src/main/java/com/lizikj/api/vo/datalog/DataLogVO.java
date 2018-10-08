package com.lizikj.api.vo.datalog;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @auth zone
 * @date 2017-11-06
 */
@ApiModel(value = "数据vo")
public class DataLogVO {

	@ApiModelProperty(required = true, value = "最新游标", name = "lastCursorValue", dataType = "Long")
	long lastCursorValue;

	@ApiModelProperty(required = true, value = "数据拉取vo", name = "dataLogPullVOList", dataType = "List")
	List<DataLogPullVO> dataLogPullVOList;
	
	@ApiModelProperty(required = true, value = "是否重新拉取所有数据", name = "refreshAll", dataType = "boolean")
	boolean refreshAll;
	
	public long getLastCursorValue() {
		return lastCursorValue;
	}
	public void setLastCursorValue(long lastCursorValue) {
		this.lastCursorValue = lastCursorValue;
	}
	public List<DataLogPullVO> getDataLogPullVOList() {
		return dataLogPullVOList;
	}
	public void setDataLogPullVOList(List<DataLogPullVO> dataLogPullVOList) {
		this.dataLogPullVOList = dataLogPullVOList;
	}
	public boolean isRefreshAll() {
		return refreshAll;
	}
	public void setRefreshAll(boolean refreshAll) {
		this.refreshAll = refreshAll;
	}
}
