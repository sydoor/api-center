package com.lizikj.api.vo.merchandise.param;

import com.lizikj.merchandise.enums.ShelveStateEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author dyh
 * @created at 2017 10 11 10:56
 */
@ApiModel
public class OnOffShelveParam {
    /**
     * 上下架状态
     */
    @ApiModelProperty(value = "上下架状态", required = true)
    private ShelveStateEnum shelveState;

    /**
     * 美食ID
     */
    @ApiModelProperty(value = "美食ID", required = true)
    private List<String> goodsIds;

    public ShelveStateEnum getShelveState() {
        return shelveState;
    }

    public void setShelveState(ShelveStateEnum shelveState) {
        this.shelveState = shelveState;
    }

    public List<String> getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(List<String> goodsIds) {
        this.goodsIds = goodsIds;
    }
}
