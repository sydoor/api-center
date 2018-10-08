package com.lizikj.api.vo.commoninfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author dyh
 * @created at 2017 10 16 17:45
 */
@ApiModel(value = "服务端启动信息")
public class CommonInfoVO {
    @ApiModelProperty(value = "服务端当前时间")
    private long currentTime;

    @ApiModelProperty(value = "环境,dev,test,prod")
    private String environment;

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}
