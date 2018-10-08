package com.lizikj.api.vo.order;

import com.lizikj.api.vo.pay.PayFlowVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by liangxiaolin on 2017/9/22.
 */
@ApiModel
public class SyncPosPayFlowVO extends PayFlowVO {

    @ApiModelProperty(value = "版本号", required = true)
    private Long payVersion;

    @ApiModelProperty(value = "支付详情", required = true)
    private SyncPayFlowAccountDetailVO syncPayFlowAccountDetail;

    public SyncPayFlowAccountDetailVO getSyncPayFlowAccountDetail() {
        return syncPayFlowAccountDetail;
    }

    public void setSyncPayFlowAccountDetail(SyncPayFlowAccountDetailVO syncPayFlowAccountDetail) {
        this.syncPayFlowAccountDetail = syncPayFlowAccountDetail;
    }

    public Long getPayVersion() {
        return payVersion;
    }

    public void setPayVersion(Long payVersion) {
        this.payVersion = payVersion;
    }
}
