package com.lizikj.api.controller.shop;

import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.shop.ShopNoticeVO;
import com.lizikj.common.exception.ArgumentCheckException;
import com.lizikj.common.exception.BaseException;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.shop.api.dto.ShopNoticeDTO;
import com.lizikj.shop.api.enums.ShopErrorEnum;
import com.lizikj.shop.api.exception.ShopException;
import com.lizikj.shop.api.facade.IShopNoticeReadFacade;
import com.lizikj.shop.api.facade.IShopNoticeWriteFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 店铺服务
 * @author zhoufe
 * @date 2017/07/10
 */
@Controller
@RequestMapping("/shop/notice")
@Api(value = "shop-notice", tags = "店铺API接口")
public class ShopNoticeController {

    private static final Logger logger = LoggerFactory.getLogger(ShopNoticeController.class);

    @Autowired
    private IShopNoticeReadFacade shopNoticeReadFacade;

    @Autowired
    private IShopNoticeWriteFacade shopNoticeWriteFacade;

    /**
     * 获取店铺公告
     * @return Result<ApiShopNoticeVO>
     * @author zhoufe 
     * @date 2017年7月11日 下午3:16:06
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/getShopNotice")
    @ApiOperation(value = "获取店铺公告", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<ShopNoticeVO> getShopNotice() {
        Result<ShopNoticeVO> result;
        try {
            Long shopId = ThreadLocalContext.getShopId();

            ShopNoticeDTO shopNoticeDTO = shopNoticeReadFacade.getShopNotice(shopId);
            if (null == shopNoticeDTO){
                result = Result.SUCESS(null);
                return result;
            }
            ShopNoticeVO  shopNoticeVO = ObjectConvertUtil.getMapperFactory().getMapperFacade().map(shopNoticeDTO, ShopNoticeVO.class);
        	result = Result.SUCESS(shopNoticeVO);
        } catch (BaseException e) {
            logger.error("getShopNotice Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }

    /**
     * 更新店铺公告
     * @return Result<ApiShopNoticeVO>
     * @author zhoufe
     * @date 2017年7月11日 下午3:16:06
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/updateShopNotice")
    @ApiOperation(value = "更新店铺公告", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<ShopNoticeVO> updateShopNotice(
            @ApiParam(name = "shopNoticeVO", value = "shopNoticeVO", required = true)
            @RequestBody ShopNoticeVO shopNoticeVO
    ) {
        Result<ShopNoticeVO> result;
        try {
            Long shopId = ThreadLocalContext.getShopId();
            if (null == shopNoticeVO){
                throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, "传入参数为空");
            }

            if (null == shopId || 0 == shopId){
                throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, String.format("传入店铺ID(%s)参数为空或者为0", shopId));
            }

            ShopNoticeDTO  shopNoticeDTO = ObjectConvertUtil.getMapperFactory().getMapperFacade().map(shopNoticeVO,ShopNoticeDTO.class);
            shopNoticeDTO.setShopId(shopId);
            ShopNoticeDTO  returnShopNoticeDTO = shopNoticeWriteFacade.updateShopNotice(shopNoticeDTO);
            ShopNoticeVO  returnShopNoticeVO = ObjectConvertUtil.getMapperFactory().getMapperFacade().map(returnShopNoticeDTO,ShopNoticeVO.class);
            result = Result.SUCESS(returnShopNoticeVO);
        } catch (BaseException e) {
            logger.error("updateShopNotice Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }
    
}
