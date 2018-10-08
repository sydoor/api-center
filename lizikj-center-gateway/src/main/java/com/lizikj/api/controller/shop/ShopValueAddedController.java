package com.lizikj.api.controller.shop;

import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.shop.ShopServiceTimeLeftVO;
import com.lizikj.common.exception.BaseException;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.shop.api.dto.ShopServiceTimeLeftDTO;
import com.lizikj.shop.api.facade.IShopValueAddedReadFacade;
import com.lizikj.shop.api.facade.IShopValueAddedWriteFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ma.glasnost.orika.MapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 店铺增值服务接口
 * @author zhoufe
 * @date 2017/07/10
 */
@Controller
@RequestMapping("/shop/value/added")
@Api(value = "shop-value/added", tags = "店铺增值服务API接口")
public class ShopValueAddedController {

    private static final Logger logger = LoggerFactory.getLogger(ShopValueAddedController.class);

    @Autowired
    private IShopValueAddedReadFacade shopValueAddedReadFacade;

    @Autowired
    private IShopValueAddedWriteFacade shopValueAddedWriteFacade;
    /**
     * 查询该店铺的增值服务到期时间
     * @return Result<ShopServiceTimeLeftVO>
     * @author zhoufe
     * @date 2017年7月18日 下午3:31:23
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/getShopExpiredTime")
    @ApiOperation(value = "获取该店铺的增值服务到期时间", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<ShopServiceTimeLeftVO> getShopExpiredTime() {
        Result<ShopServiceTimeLeftVO> result;
        try {
            Long shopId = ThreadLocalContext.getShopId();
            ShopServiceTimeLeftDTO shopServiceTimeLeft = shopValueAddedReadFacade.getShopExpiredTime(shopId);

            MapperFactory mapperFactory = ObjectConvertUtil.getMapperFactory();
            ShopServiceTimeLeftVO shopServiceTimeLeftVO = mapperFactory.getMapperFacade().map(shopServiceTimeLeft, ShopServiceTimeLeftVO.class);
        	result = Result.SUCESS(shopServiceTimeLeftVO);
        } catch (BaseException e) {
            logger.error("getShopExpiredTime Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        
        return result;
    }


    /**
     * 激活增值服务
     * @return Result<ShopServiceTimeLeftVO>
     * @author zhoufe
     * @date 2017年7月18日 下午3:31:23
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/activatedValueAdded")
    @ApiOperation(value = "激活增值服务", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<ShopServiceTimeLeftVO> activatedValueAdded() {
        Result<ShopServiceTimeLeftVO> result;
        try {
            Long shopId = ThreadLocalContext.getShopId();
            ShopServiceTimeLeftDTO shopServiceTimeLeft = shopValueAddedWriteFacade.activatedValueAdded(shopId);

            MapperFactory mapperFactory = ObjectConvertUtil.getMapperFactory();
            ShopServiceTimeLeftVO shopServiceTimeLeftVO = mapperFactory.getMapperFacade().map(shopServiceTimeLeft, ShopServiceTimeLeftVO.class);
            result = Result.SUCESS(shopServiceTimeLeftVO);
        } catch (BaseException e) {
            logger.error("activatedValueAdded Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }



    /**
     * 续期
     * @return Result<ShopServiceTimeLeftVO>
     * @author zhoufe
     * @date 2017年7月18日 下午3:31:23
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/addServiceTimeLeft")
    @ApiOperation(value = "续期", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> addServiceTimeLeft(
            @ApiParam(name = "shopId", value = "店铺ID", required = true)
            @RequestParam(name = "shopId") Long shopId,
            @ApiParam(name = "addDays", value = "天数", required = true)
            @RequestParam(name = "addDays") Long addDays
    ) {
        Result<Boolean> result;
        try {
            boolean isSucc = shopValueAddedWriteFacade.addServiceTimeLeft(shopId, addDays);
            result = Result.SUCESS(isSucc);
        } catch (BaseException e) {
            logger.error("addServiceTimeLeft Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }





    
   

}
