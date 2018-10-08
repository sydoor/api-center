package com.lizikj.api.controller.shop;

import java.util.List;

import com.lizikj.common.exception.BaseException;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.shop.api.enums.ShopErrorEnum;
import com.lizikj.shop.api.facade.IShopMerchandiseFlavorReadFacade;
import com.lizikj.shop.api.facade.IShopMerchandiseFlavorWriteFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.shop.ShopMerchandiseFlavorVO;
import com.lizikj.api.vo.shop.param.ShopMerchandiseFlavorParamVO;
import com.lizikj.common.exception.ArgumentCheckException;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.shop.api.dto.ShopMerchandiseFlavorDTO;
import com.lizikj.shop.api.exception.ShopException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * 店铺商品口味偏好接口
 * @author zhoufe
 * @date 2017/07/10
 */
@Controller
@RequestMapping("/shop/merchandise/flavor")
@Api(value = "shop-merchandise-flavor", tags = "店铺商品口味偏好API接口")
public class ShopMerchandiseFlavorController {

    private static final Logger logger = LoggerFactory.getLogger(ShopMerchandiseFlavorController.class);

    @Autowired
    private IShopMerchandiseFlavorReadFacade shopMerchandiseFlavorReadFacade;

    @Autowired
    private IShopMerchandiseFlavorWriteFacade shopMerchandiseFlavorWriteFacade;
    /**
     * 查询该店铺下的商品口味偏好
     * @return Result<List<ShopMerchandiseFlavorVO>>
     * @author zhoufe
     * @date 2017年7月18日 下午3:31:23
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/listShopMerchandiseFlavor")
    @ApiOperation(value = "查询该店铺下的商品口味偏好", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<ShopMerchandiseFlavorVO>> listShopMerchandiseFlavor() {
        Result<List<ShopMerchandiseFlavorVO>> result;
        try {
            Long shopId = ThreadLocalContext.getShopId();
            List<ShopMerchandiseFlavorDTO>  shopMerchandiseFlavorDTOS = shopMerchandiseFlavorReadFacade.listShopMerchandiseFlavor(shopId);
            List<ShopMerchandiseFlavorVO> shopMerchandiseFlavorVOS = ObjectConvertUtil.copyListProperties(shopMerchandiseFlavorDTOS, ShopMerchandiseFlavorVO.class);
        	result = Result.SUCESS(shopMerchandiseFlavorVOS);
        } catch (BaseException e) {
            logger.error("listShopMerchandiseFlavor Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        
        return result;
    }

    /**
     * 删除口味偏好
     * @param merchandiseFlavorId
     * @return Result<Boolean>
     * @author zhoufe
     * @date 2017年7月18日 下午3:38:16
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/deleteShopMerchandiseFlavor/{merchandiseFlavorId}")
    @ApiOperation(value = "删除口味偏好", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> deleteShopMerchandiseFlavor(
 	       @ApiParam(name = "merchandiseFlavorId", value = "口味偏好ID", required = true, type = "Long")
           @PathVariable(name = "merchandiseFlavorId") Long merchandiseFlavorId) {
        Result<Boolean> result;
        try {
            if (isLongNull(merchandiseFlavorId)){
                throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, String.format("传入要删除的口味ID(%s)为空或者为0", merchandiseFlavorId));
            }
        	Boolean isSuccess = shopMerchandiseFlavorWriteFacade.deleteShopMerchandiseFlavor(merchandiseFlavorId);
        	result = Result.SUCESS(isSuccess);
        } catch (BaseException e) {
            logger.error("deleteShopMerchandiseFlavor Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        } 
        return result;
    }

    private boolean isLongNull(Long merchandiseFlavorId) {
        return null == merchandiseFlavorId || 0 == merchandiseFlavorId;
    }

    /**
     * 新增口味偏好
     * @param shopMerchandiseFlavorParamVO
     * @return Result<Long>
     * @author zhoufe
     * @date 2017年7月18日 下午3:45:20
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/insertShopMerchandiseFlavor")
    @ApiOperation(value = "新增口味偏好", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Long> insertShopMerchandiseFlavor(
  	        @ApiParam(name = "addParamVO", value = "addParamVO", required = true)
    		@RequestBody ShopMerchandiseFlavorParamVO shopMerchandiseFlavorParamVO) {
         Result<Long> result ;
         try {
			ShopMerchandiseFlavorDTO shopMerchandiseFlavorDTO = ObjectConvertUtil.copyProperties(ShopMerchandiseFlavorDTO.class, shopMerchandiseFlavorParamVO);
			if (shopMerchandiseFlavorDTO != null){
			    shopMerchandiseFlavorDTO.setShopId(ThreadLocalContext.getShopId());
            }
			Long merchandiseUnitId = shopMerchandiseFlavorWriteFacade.insertShopMerchandiseFlavor(shopMerchandiseFlavorDTO);
         	result = Result.SUCESS(merchandiseUnitId);
         } catch (BaseException e) {
             logger.error("insertShopMerchandiseFlavor Exception: {}", e);
             result = Result.FAILURE(e.getCode(), e.getMessage());
         } 
         return result;
     }
    
    /**
     * 更新口味偏好
     * @param shopMerchandiseFlavorParamVO
     * @return Result<Boolean>
     * @author zhoufe
     * @date 2017年7月18日 下午3:47:32
     */
    @SuppressWarnings("unchecked")
   	@ResponseBody
    @RequestMapping("/updateShopMerchandiseFlavor")
    @ApiOperation(value = "更新口味偏好", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> updateShopMerchandiseFlavor(
  	        @ApiParam(name = "updateParamVO", value = "updateParamVO", required = true)
    		@RequestBody ShopMerchandiseFlavorParamVO shopMerchandiseFlavorParamVO) {
         Result<Boolean> result;
         try {
             if (null == shopMerchandiseFlavorParamVO){
                 throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, "传入的参数为空");
             }

			 ShopMerchandiseFlavorDTO shopMerchandiseFlavorDTO = ObjectConvertUtil.copyProperties(ShopMerchandiseFlavorDTO.class, shopMerchandiseFlavorParamVO);
             Long shopId = ThreadLocalContext.getShopId();
             shopMerchandiseFlavorDTO.setShopId(shopId);
             Boolean isSuccess = shopMerchandiseFlavorWriteFacade.updateShopMerchandiseFlavor(shopMerchandiseFlavorDTO);
         	result = Result.SUCESS(isSuccess);
         } catch (BaseException e) {
             logger.error("updateShopMerchandiseFlavor Exception: {}", e);
             result = Result.FAILURE(e.getCode(), e.getMessage());
         } 
         return result;
     }
    
   

}
