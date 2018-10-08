package com.lizikj.api.vo.merchandise;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author dyh
 * @created at 2017 12 11 15:58
 */
@ApiModel(value = "批量新增美食VO")
public class BatchResultVO {
    @ApiModelProperty(value = "是否成功")
    private Boolean result;

    @ApiModelProperty(value = "上传的记录数")
    private int total;

    @ApiModelProperty(value = "校验失败记录数")
    private int failCount;

    @ApiModelProperty(value = "失败详细信息")
    private List<BatchItemResultVO> items;

    @ApiModelProperty(value = "是否是正确的模板")
    private boolean isValid;

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFailCount() {
        return failCount;
    }

    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }

    public List<BatchItemResultVO> getItems() {
        return items;
    }

    public void setItems(List<BatchItemResultVO> items) {
        this.items = items;
    }
}
