package com.lizikj.api.vo.member;

import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;

/**
 * @author Healthy
 * @description
 * @date 2017/7/10
 */
@ApiModel(description = "商户会员实时信息详情")
public class CurrentMemberStatisticsVO extends BaseDTO {

    /**
     * 会员总数
     */
    @ApiModelProperty(value = "会员总数")
    private Integer memberCount;

    /**
     * 今日新增会员
     */
    @ApiModelProperty(value = "今日新增会员")
    private Integer todayIncrease;


    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public Integer getTodayIncrease() {
        return todayIncrease;
    }

    public void setTodayIncrease(Integer todayIncrease) {
        this.todayIncrease = todayIncrease;
    }

}
