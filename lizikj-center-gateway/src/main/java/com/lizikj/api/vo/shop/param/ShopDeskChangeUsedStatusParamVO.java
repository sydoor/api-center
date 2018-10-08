package com.lizikj.api.vo.shop.param;

import com.lizikj.shop.api.enums.DeskUsedStatusEnum;
import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhoufe
 * @date 2017/8/23 16:54
 */
@ApiModel
public class ShopDeskChangeUsedStatusParamVO extends BaseDTO {

    private static final long serialVersionUID = 1024945967926027067L;

    @ApiModelProperty(value = "桌台ID", required = true)
    private Long areaDeskId;

    @ApiModelProperty(value = "使用状态：见：DeskUsedStatusEnum：FREE. 空闲，WAIT_REC. 待接单，WAIT_ORDER. 待点单，" +
            "WAIT_PAYMENT. 待结账，WAIT_DESK_CLEAN. 待清台，LOCKED. 锁定。", required = true)
    private DeskUsedStatusEnum usedStatus;

    @ApiModelProperty(value = "锁定原因")
    private String lockReason;

    public Long getAreaDeskId() {
        return areaDeskId;
    }

    public void setAreaDeskId(Long areaDeskId) {
        this.areaDeskId = areaDeskId;
    }

    public DeskUsedStatusEnum getUsedStatus() {
        return usedStatus;
    }

    public void setUsedStatus(DeskUsedStatusEnum usedStatus) {
        this.usedStatus = usedStatus;
    }

    public String getLockReason() {
        return lockReason;
    }

    public void setLockReason(String lockReason) {
        this.lockReason = lockReason;
    }
}
