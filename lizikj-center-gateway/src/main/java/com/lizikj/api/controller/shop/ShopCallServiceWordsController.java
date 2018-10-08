package com.lizikj.api.controller.shop;

import java.util.List;

import com.lizikj.common.exception.BaseException;
import com.lizikj.common.util.CollectionUtils;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.shop.api.enums.ShopErrorEnum;
import com.lizikj.shop.api.facade.IShopCallServiceWordsReadFacade;
import com.lizikj.shop.api.facade.IShopCallServiceWordsWriteFacade;
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
import com.lizikj.api.vo.shop.ShopCallServiceWordsVO;
import com.lizikj.api.vo.shop.param.ShopCallServiceWordsParamVO;
import com.lizikj.common.exception.ArgumentCheckException;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.shop.api.dto.ShopCallServiceWordsDTO;
import com.lizikj.shop.api.exception.ShopException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * 店铺呼叫服务接口
 * @author zhoufe
 * @date 2017/07/10
 */
@Controller
@RequestMapping("/shop/call/service/words")
@Api(value = "shop-call-service-words", tags = "店铺商品呼叫服务API接口")
public class ShopCallServiceWordsController {

    private static final Logger logger = LoggerFactory.getLogger(ShopCallServiceWordsController.class);

    @Autowired
    private IShopCallServiceWordsReadFacade shopCallServiceWordsReadFacade;

    @Autowired
    private IShopCallServiceWordsWriteFacade shopCallServiceWordsWriteFacade;

    /**
     * 查询该店铺下的呼叫服务
     * 包含系统配置
     * @return Result<List<ShopCallServiceWordsVO>>
     * @author zhoufe
     * @date 2017年7月18日 下午3:31:23
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/listShopCallServiceWords")
    @ApiOperation(value = "查询该店铺下的呼叫服务：包含系统配置", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<ShopCallServiceWordsVO>> listShopCallServiceWords() {
        Result<List<ShopCallServiceWordsVO>> result;
        try {
            Long shopId = ThreadLocalContext.getShopId();
            List<ShopCallServiceWordsDTO> shopCallServiceWordsDTOS = shopCallServiceWordsReadFacade.listShopCallServiceWords(shopId);
            if (CollectionUtils.isListBlank(shopCallServiceWordsDTOS)){
                result = Result.SUCESS(null);
                //【返回】
                return result;
            }
            List<ShopCallServiceWordsVO> shopCallServiceWordsVOS = ObjectConvertUtil.getMapperFactory().getMapperFacade().mapAsList(shopCallServiceWordsDTOS, ShopCallServiceWordsVO.class);
        	result = Result.SUCESS(shopCallServiceWordsVOS);
        } catch (BaseException e) {
            logger.error("listShopCallServiceWords Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    /**
     * 删除呼叫服务话术
     * @param shopCallServiceWordsId
     * @return Result<Boolean>
     * @author zhoufe
     * @date 2017年7月18日 下午3:38:16
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/deleteShopCallServiceWords/{shopCallServiceWordsId}")
    @ApiOperation(value = "删除呼叫服务话术", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> deleteShopCallServiceWords(
 	       @ApiParam(name = "shopCallServiceWordsId", value = "呼叫服务话术ID", required = true, type = "Long")
           @PathVariable(name = "shopCallServiceWordsId") Long shopCallServiceWordsId) {
        Result<Boolean> result;
        try {
        	Boolean isSuccess = shopCallServiceWordsWriteFacade.deleteShopCallServiceWords(shopCallServiceWordsId);
        	result = Result.SUCESS(isSuccess);
        } catch (BaseException e) {
            logger.error("deleteShopCallServiceWords Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }

    /**
     * 新增呼叫服务话术
     * @param shopCallServiceWordsParamVO
     * @return Result<Long>
     * @author zhoufe
     * @date 2017年7月18日 下午3:45:20
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/insertShopCallServiceWords")
    @ApiOperation(value = "新增呼叫服务话术", notes = "新增呼叫服务话术", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Long> insertShopCallServiceWords(
  	        @ApiParam(name = "addParamVO", value = "addParamVO", required = true)
    		@RequestBody ShopCallServiceWordsParamVO shopCallServiceWordsParamVO) {
         Result<Long> result;
         try {
             if (null == shopCallServiceWordsParamVO){
                 throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, "传入参数为空");
             }
         	ShopCallServiceWordsDTO shopCallServiceWordsDTO = ObjectConvertUtil.getMapperFactory().getMapperFacade().map(shopCallServiceWordsParamVO, ShopCallServiceWordsDTO.class);
            Long shopId = ThreadLocalContext.getShopId();
            shopCallServiceWordsDTO.setShopId(shopId);
			Long shopCallServiceWordsId = shopCallServiceWordsWriteFacade.insertShopCallServiceWords(shopCallServiceWordsDTO);
         	result = Result.SUCESS(shopCallServiceWordsId);
         } catch (BaseException e) {
             logger.error("insertShopCallServiceWords Exception: {}", e);
             result = Result.FAILURE(e.getCode(), e.getMessage());
         }
         return result;
     }

    /**
     * 更新呼叫服务话术
     * @param shopCallServiceWordsParamVO
     * @return Result<Boolean>
     * @author zhoufe
     * @date 2017年7月18日 下午3:47:32
     */
    @SuppressWarnings("unchecked")
   	@ResponseBody
    @RequestMapping("/updateShopCallServiceWords")
    @ApiOperation(value = "更新呼叫服务话术", notes = "更新呼叫服务话术", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> updateShopCallServiceWords(
  	        @ApiParam(name = "updateParamVO", value = "updateParamVO", required = true)
    		@RequestBody ShopCallServiceWordsParamVO shopCallServiceWordsParamVO) {
         Result<Boolean> result;
         try {

             if (null == shopCallServiceWordsParamVO){
                 throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, "传入参数为空");
             }

          	 ShopCallServiceWordsDTO shopCallServiceWordsDTO = ObjectConvertUtil.getMapperFactory().getMapperFacade().map(shopCallServiceWordsParamVO, ShopCallServiceWordsDTO.class);
             Long shopId = ThreadLocalContext.getShopId();
             shopCallServiceWordsDTO.setShopId(shopId);
             Boolean isSuccess = shopCallServiceWordsWriteFacade.updateShopCallServiceWords(shopCallServiceWordsDTO);
         	result = Result.SUCESS(isSuccess);
         } catch (BaseException e) {
             logger.error("updateShopCallServiceWords Exception: {}", e);
             result = Result.FAILURE(e.getCode(), e.getMessage());
         } 
         return result;
     }
    
   

}
