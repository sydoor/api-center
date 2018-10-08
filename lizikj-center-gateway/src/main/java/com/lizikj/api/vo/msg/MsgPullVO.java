package com.lizikj.api.vo.msg;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author lxl
 * @Date 2018/2/2 11:54
 */
@ApiModel
public class MsgPullVO {
    @ApiModelProperty(value = "消息已拉取消息ID游标")
    private Long cursorMsgId;
    @ApiModelProperty(value = "未回调消息列表")
    List<MsgPushDetailVO> detailVOList;

    public Long getCursorMsgId() {
        return cursorMsgId;
    }

    public void setCursorMsgId(Long cursorMsgId) {
        this.cursorMsgId = cursorMsgId;
    }

    public List<MsgPushDetailVO> getDetailVOList() {
        return detailVOList;
    }

    public void setDetailVOList(List<MsgPushDetailVO> detailVOList) {
        this.detailVOList = detailVOList;
    }
}
