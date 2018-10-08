package com.lizikj.api.vo.shop.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
 * @author zhoufe 
 * @date 2017年7月18日 下午3:46:20 
 */
@ApiModel
public class ShopCallServiceWordsParamVO {
	/**
     * 呼叫服务话术ID
     */
	@ApiModelProperty(value = "呼叫服务话术ID", required = true)
    private Long callServiceWordsId;

    /**
     * 话术
     */
	@ApiModelProperty(value = "话术", required = true)
    private String theWords;

    public Long getCallServiceWordsId() {
        return callServiceWordsId;
    }

    public void setCallServiceWordsId(Long callServiceWordsId) {
        this.callServiceWordsId = callServiceWordsId;
    }

    public String getTheWords() {
        return theWords;
    }

    public void setTheWords(String theWords) {
        this.theWords = theWords;
    }
}
