package com.lizikj.api.controller.shop;

import java.util.List;

import com.lizikj.common.exception.BaseException;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.shop.api.enums.ShopErrorEnum;
import com.lizikj.shop.api.facade.IShopMerchandiseUnitReadFacade;
import com.lizikj.shop.api.facade.IShopMerchandiseUnitWriteFacade;
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
import com.lizikj.api.vo.shop.ShopMerchandiseUnitVO;
import com.lizikj.api.vo.shop.param.ShopMerchandiseUnitParamVO;
import com.lizikj.common.exception.ArgumentCheckException;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.shop.api.dto.ShopMerchandiseUnitDTO;
import com.lizikj.shop.api.exception.ShopException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * 店铺商品单位接口
 * @author zhoufe
 * @date 2017/07/10
 */
@Controller
@RequestMapping("/shop/merchandise/unit")
@Api(value = "shop-merchandise-unit", tags = "店铺商品单位API接口")
public class ShopMerchandiseUnitController {

    private static final Logger logger = LoggerFactory.getLogger(ShopMerchandiseUnitController.class);

    @Autowired
    private IShopMerchandiseUnitReadFacade shopMerchandiseUnitReadFacade;

    @Autowired
    private IShopMerchandiseUnitWriteFacade shopMerchandiseUnitWriteFacade;

    /**
     * 查询该店铺下的商品单位
     * @return Result<List<ShopMerchandiseUnitVO>>
     * @author zhoufe
     * @date 2017年7月18日 下午3:31:23
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/listShopMerchandiseUnits")
    @ApiOperation(value = "查询该店铺下的商品单位", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<ShopMerchandiseUnitVO>> listShopMerchandiseUnits() {
        Result<List<ShopMerchandiseUnitVO>> result;
        try {
            Long shopId = ThreadLocalContext.getShopId();
            List<ShopMerchandiseUnitDTO> shopMerchandiseUnitDTOS = shopMerchandiseUnitReadFacade.listShopMerchandiseUnits(shopId);
            List<ShopMerchandiseUnitVO> shopMerchandiseUnitVOS = ObjectConvertUtil.copyListProperties(shopMerchandiseUnitDTOS, ShopMerchandiseUnitVO.class);
        	result = Result.SUCESS(shopMerchandiseUnitVOS);
        } catch (BaseException e) {
            logger.error("listShopMerchandiseUnits Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        
        return result;
    }

    /**
     * 删除商品单位
     * @param merchandiseUnitId
     * @return Result<Boolean>
     * @author zhoufe
     * @date 2017年7月18日 下午3:38:16
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/deleteShopMerchandiseUnit/{merchandiseUnitId}")
    @ApiOperation(value = "删除商品单位", notes = "删除商品单位", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> deleteShopMerchandiseUnit(
 	       @ApiParam(name = "merchandiseUnitId", value = "商品单位ID", required = true, type = "Long")
           @PathVariable(name = "merchandiseUnitId") Long merchandiseUnitId) {
        Result<Boolean> result;
        try {
        	Boolean isSuccess = shopMerchandiseUnitWriteFacade.deleteShopMerchandiseUnit(merchandiseUnitId);
        	result = Result.SUCESS(isSuccess);
        } catch (BaseException e) {
            logger.error("deleteShopMerchandiseUnit Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        } 
        return result;
    }
    
    /**
     * 新增商品单位
     * @param shopMerchandiseUnitParamVO
     * @return Result<Long>
     * @author zhoufe
     * @date 2017年7月18日 下午3:45:20
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/insertShopMerchandiseUnit")
    @ApiOperation(value = "新增商品单位", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Long> insertShopMerchandiseUnit(
  	        @ApiParam(name = "addParamVO", value = "addParamVO", required = true)
    		@RequestBody ShopMerchandiseUnitParamVO shopMerchandiseUnitParamVO) {
         Result<Long> result;
         try {
			ShopMerchandiseUnitDTO shopMerchandiseUnitDTO = ObjectConvertUtil.copyProperties(ShopMerchandiseUnitDTO.class, shopMerchandiseUnitParamVO);
			if (shopMerchandiseUnitDTO != null){
                shopMerchandiseUnitDTO.setShopId(ThreadLocalContext.getShopId());
            }
			Long merchandiseUnitId = shopMerchandiseUnitWriteFacade.insertShopMerchandiseUnit(shopMerchandiseUnitDTO);
         	result = Result.SUCESS(merchandiseUnitId);
         } catch (BaseException e) {
             logger.error("insertShopMerchandiseUnit Exception: {}", e);
             result = Result.FAILURE(e.getCode(), e.getMessage());
         } 
         return result;
     }
    
    /**
     * 更新商品单位
     * @param shopMerchandiseUnitParamVO
     * @return Result<Boolean>
     * @author zhoufe
     * @date 2017年7月18日 下午3:47:32
     */
    @SuppressWarnings("unchecked")
   	@ResponseBody
    @RequestMapping("/updateShopMerchandiseUnit")
    @ApiOperation(value = "更新商品单位", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> updateShopMerchandiseUnit(
  	        @ApiParam(name = "updateParamVO", value = "updateParamVO", required = true)
    		@RequestBody ShopMerchandiseUnitParamVO shopMerchandiseUnitParamVO) {
         Result<Boolean> result;
         try {
			if (null == shopMerchandiseUnitParamVO){
                throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, "传入参数为空");
            }
         	ShopMerchandiseUnitDTO shopMerchandiseUnitDTO = ObjectConvertUtil.copyProperties(ShopMerchandiseUnitDTO.class, shopMerchandiseUnitParamVO);
             shopMerchandiseUnitDTO.setShopId(ThreadLocalContext.getShopId());
			Boolean isSuccess = shopMerchandiseUnitWriteFacade.updateShopMerchandiseUnit(shopMerchandiseUnitDTO);
         	result = Result.SUCESS(isSuccess);
         } catch (BaseException e) {
             logger.error("updateShopMerchandiseUnit Exception: {}", e);
             result = Result.FAILURE(e.getCode(), e.getMessage());
         } 
         return result;
     }
    
   

}
