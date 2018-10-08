package com.lizikj.api.vo.msg;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author lxl
 * @Date 2017/11/28 10:26
 */
@ApiModel
public class MsgRePushParamVO {
    @ApiModelProperty(value = "消息推送记录ID列表")
    private List<Long> recordIds;
    @ApiModelProperty(value = "消息ID列表")
    private List<Long> msgIds;

    public List<Long> getRecordIds() {
        return recordIds;
    }

    public void setRecordIds(List<Long> recordIds) {
        this.recordIds = recordIds;
    }

    public List<Long> getMsgIds() {
        return msgIds;
    }

    public void setMsgIds(List<Long> msgIds) {
        this.msgIds = msgIds;
    }
}
