package com.lizikj.api.vo.merchandise;

import com.lizikj.merchandise.enums.DeleteSnacksFailReasonEnum;
import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhoufe
 * @date 2017/8/22 15:19
 */
@ApiModel
public class DeleteSnacksVO extends BaseDTO {

    @ApiModelProperty(value = "失败原因：DeleteSnacksFailReasonEnum：SUCCESS.成功删除，SNACK_USED.该加料已被商品使用，OTHER.其他", required = true)
    private DeleteSnacksFailReasonEnum deleteSnacksFailReason;

    @ApiModelProperty(value = "失败的加料ID", required = true)
    private String failSnackId;

    @ApiModelProperty(value = "失败的加料名称", required = true)
    private String failSnackName;

    public DeleteSnacksFailReasonEnum getDeleteSnacksFailReason() {
        return deleteSnacksFailReason;
    }

    public void setDeleteSnacksFailReason(DeleteSnacksFailReasonEnum deleteSnacksFailReason) {
        this.deleteSnacksFailReason = deleteSnacksFailReason;
    }

    public String getFailSnackId() {
        return failSnackId;
    }

    public void setFailSnackId(String failSnackId) {
        this.failSnackId = failSnackId;
    }

    public String getFailSnackName() {
        return failSnackName;
    }

    public void setFailSnackName(String failSnackName) {
        this.failSnackName = failSnackName;
    }
}
