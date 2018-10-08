package com.lizikj.api.vo.msg;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lxl
 * @Date 2018/2/2 11:37
 */
@ApiModel
public class MsgPullQueryVO {
    @ApiModelProperty(value = "当前消息ID游标")
    private Long cursorMsgId;

    public Long getCursorMsgId() {
        return cursorMsgId;
    }

    public void setCursorMsgId(Long cursorMsgId) {
        this.cursorMsgId = cursorMsgId;
    }
}
