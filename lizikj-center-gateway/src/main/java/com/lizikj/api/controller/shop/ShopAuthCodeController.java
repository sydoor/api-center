package com.lizikj.api.controller.shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lizikj.api.vo.Result;
import com.lizikj.common.exception.BaseException;
import com.lizikj.common.util.JsonUtils;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.login.interceptor.LoginExclude;
import com.lizikj.shop.api.enums.AuthOperationEnum;
import com.lizikj.shop.api.facade.IShopAuthCodeReadFacade;
import com.lizikj.shop.api.facade.IShopAuthCodeWriteFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * 店铺授权码服务
 * @author zhoufe
 * @date 2017/07/10
 */
@Controller
@RequestMapping("/shop/auth/code")
@Api(value = "shop-auth-code", tags = "店铺授权码API接口")
public class ShopAuthCodeController {

    private static final Logger logger = LoggerFactory.getLogger(ShopAuthCodeController.class);

    @Autowired
    private IShopAuthCodeReadFacade shopAuthCodeReadFacade;

    @Autowired
    private IShopAuthCodeWriteFacade shopAuthCodeWriteFacade;

    /**
     * 获取店铺授权码
     * @return Result<ApiShopNoticeVO>
     * @author zhoufe 
     * @date 2017年7月11日 下午3:16:06
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/getAndCreateAuthCode")
    @ApiOperation(value = "获取店铺授权码", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> getAndCreateAuthCode() {
        Result<String> result;
        try {
            Long shopId = ThreadLocalContext.getShopId();
            Long merchantId = ThreadLocalContext.getMerchantId();
            Long staffId = ThreadLocalContext.getStaffId();

            String authCode = shopAuthCodeWriteFacade.getAndCreateAuthCode(merchantId, shopId, staffId);
            if (null == authCode){
                result = Result.SUCESS(null);
                return result;
            }
        	result = Result.SUCESS(authCode);
        } catch (BaseException e) {
            logger.error("getAndCreateAuthCode Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }

    /**
     * 验证授权码是否正确
     * @return Result<ApiShopNoticeVO>
     * @author zhoufe
     * @date 2017年7月11日 下午3:16:06
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/verifyAuthCode")
    @ApiOperation(value = "验证授权码是否正确", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> verifyAuthCode(
            @ApiParam(name = "authCode", value = "authCode", required = true)
            @RequestBody String authCode,
			@ApiParam(name = "operation", value = "operation，需要授权码的操作类型,APPEND_DISH:加菜\n"
					+ "REMOVE_DISH:减菜\n"
					+ "FREE_DISH:赠菜\n"
					+ "CANCEL_ORDER:取消订单\n"
					+ "REFUND_ORDER:退款\n"
					+ "SUM_DAY_REPORT:日结报表\n"
					+ "EXCHANGE_STAFF:交接班\n"
					+ "CUSTOM_ERASE_ODD:自定义减免\n"
					+ "ERASE_ODD:抹零\n"
					+ "CASHIE:收银\n"
            		+ "CUSTOM_ERASE_DIS:自定义折扣")
            @RequestParam(required = false) AuthOperationEnum operation
	) {
		Result<Boolean> result;
		try {
			Long merchantId = ThreadLocalContext.getMerchantId();
			Long staffId = ThreadLocalContext.getStaffId();
			if (authCode.contains("\"authCode\"")) {
				authCode = JsonUtils.getValue(authCode, "authCode");
			}
			Boolean isHas = shopAuthCodeReadFacade.verifyAuthCode(merchantId, authCode, staffId, operation);
			result = Result.SUCESS(isHas);
		} catch (BaseException e) {
			logger.error("verifyAuthCode Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}
    
}
