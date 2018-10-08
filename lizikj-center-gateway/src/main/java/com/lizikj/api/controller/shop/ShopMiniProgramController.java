package com.lizikj.api.controller.shop;

import com.lizikj.api.vo.Result;
import com.lizikj.login.interceptor.LoginExclude;
import com.lizikj.merchant.dto.ShopExpandDTO;
import com.lizikj.merchant.facade.IShopExpandReadFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Michael.Huang
 * @date 2018/3/28 17:53
 */
@Controller
@RequestMapping("/shop/miniprogram")
@Api(value = "shop-mini-program", tags = "店铺小程序接口")
public class ShopMiniProgramController {

    @Autowired
    IShopExpandReadFacade shopExpandReadFacade;


    @LoginExclude
    @ResponseBody
    @RequestMapping("/getShopConfig/{shopId}")
    @ApiOperation(value = "获取延迟删除任务", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> getShopConfig(@ApiParam(name = "shopId", value = "shopId", required = true)
                                                         @PathVariable Long shopId) {
        try {
            ShopExpandDTO info = shopExpandReadFacade.findById(shopId);
            if (info != null) {
                String source = info.getMiniProgramConfig();
                if (source != null) {
//                    ShopMiniProgramConfigVO configVO = JSON.parseObject(source, ShopMiniProgramConfigVO.class);
                    return Result.SUCESS(source);
                } else {
                    return Result.SUCESS(null);
                }
            } else {
                return Result.SUCESS(null);
            }
        } catch (Exception e) {
            return Result.FAILURE();
        }
    }
}
