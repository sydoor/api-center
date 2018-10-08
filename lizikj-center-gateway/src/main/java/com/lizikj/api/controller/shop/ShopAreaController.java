package com.lizikj.api.controller.shop;

import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.shop.ShopAreaDeskSummaryVO;
import com.lizikj.api.vo.shop.ShopAreaSummaryVO;
import com.lizikj.api.vo.shop.ShopAreaVO;
import com.lizikj.api.vo.shop.param.ShopAreaParamVO;
import com.lizikj.common.exception.ArgumentCheckException;
import com.lizikj.common.exception.BaseException;
import com.lizikj.common.util.CollectionUtils;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.shop.api.dto.ShopAreaDTO;
import com.lizikj.shop.api.dto.ShopAreaDeskSummaryDTO;
import com.lizikj.shop.api.dto.ShopAreaSummaryDTO;
import com.lizikj.shop.api.enums.DeskUsedStatusEnum;
import com.lizikj.shop.api.enums.ShopAreaDefaultStatusEnum;
import com.lizikj.shop.api.enums.ShopErrorEnum;
import com.lizikj.shop.api.exception.ShopException;
import com.lizikj.shop.api.facade.IShopAreaReadFacade;
import com.lizikj.shop.api.facade.IShopAreaWriteFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ma.glasnost.orika.MapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


/**
 * 店铺区域接口
 * @author zhoufe
 * @date 2017/07/10
 */
@Controller
@RequestMapping("/shop/area")
@Api(value = "shop-area", tags = "店铺区域API接口")
public class ShopAreaController {

    private static final Logger logger = LoggerFactory.getLogger(ShopAreaController.class);

    @Autowired
    private IShopAreaReadFacade shopAreaReadFacade;

    @Autowired
    private IShopAreaWriteFacade shopAreaWriteFacade;

    /**
     * 查询该店铺下的区域
     * 并带回该区域下的餐桌数
     * @return Result<List<ShopAreaVO>>
     * @author zhoufe
     * @date 2017年7月18日 下午3:31:23
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/listShopArea")
    @ApiOperation(value = "查询该店铺下的区域", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<ShopAreaVO>> listShopArea() {
        Result<List<ShopAreaVO>> result;
        List<ShopAreaVO> shopAreaVOS;
        try {
            Long shopId = ThreadLocalContext.getShopId();
            List<ShopAreaDTO> shopAreaDTOS;
            if (null == shopId || 0 == shopId){
                String sn = ThreadLocalContext.getDid();
                shopAreaDTOS = shopAreaReadFacade.listShopAreaByShopIdFirstBySn(sn);
            }else{
                shopAreaDTOS = shopAreaReadFacade.listShopArea(shopId);
            }
            if (CollectionUtils.isListBlank(shopAreaDTOS)){
                return Result.SUCESS(new ArrayList<>());
            }
            shopAreaVOS = ObjectConvertUtil.getMapperFactory().getMapperFacade().mapAsList(shopAreaDTOS, ShopAreaVO.class);
            for (ShopAreaVO shopAreaVO : shopAreaVOS) {
                shopAreaVO.setDeskTotal(shopAreaReadFacade.countDeskTotal(shopAreaVO.getShopAreaId()));
            }
            result = Result.SUCESS(shopAreaVOS);
        } catch (BaseException e) {
            logger.error("listShopArea Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        
        return result;
    }

    /**
     * 查询该店铺下的区域
     * 并带回该区域下的餐桌数
     * @return Result<List<ShopAreaVO>>
     * @author zhoufe
     * @date 2017年7月18日 下午3:31:23
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/listByIds")
    @ApiOperation(value = "根据IDS查询该的区域", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<ShopAreaVO>> listByIds(
            @ApiParam(name = "shopAreaIds", value = "店铺区域ID", required = true)
            @RequestBody List<Long> shopAreaIds) {
        Result<List<ShopAreaVO>> result;
        List<ShopAreaVO> shopAreaVOS;
        try {
            List<ShopAreaDTO> shopAreaDTOS = shopAreaReadFacade.listByIds(shopAreaIds);
            if (CollectionUtils.isListBlank(shopAreaDTOS)){
                return Result.SUCESS(new ArrayList<>());
            }
            shopAreaVOS = ObjectConvertUtil.getMapperFactory().getMapperFacade().mapAsList(shopAreaDTOS, ShopAreaVO.class);
            for (ShopAreaVO shopAreaVO : shopAreaVOS) {
                shopAreaVO.setDeskTotal(shopAreaReadFacade.countDeskTotal(shopAreaVO.getShopAreaId()));
            }
            result = Result.SUCESS(shopAreaVOS);
        } catch (BaseException e) {
            logger.error("listByIds Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    /**
     * 根据ID查询区域
     * 并带回该区域下的餐桌数
     * @return Result<List<ShopAreaVO>>
     * @author zhoufe
     * @date 2017年7月18日 下午3:31:23
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/findShopAreaById/{shopAreaId}")
    @ApiOperation(value = "根据ID查询区域", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<ShopAreaVO> findShopAreaById(
            @ApiParam(name = "shopAreaId", value = "店铺区域ID", required = true)
            @PathVariable Long shopAreaId) {
        Result<ShopAreaVO> result;
        ShopAreaVO shopAreaVO;
        try {
            ShopAreaDTO shopAreaDTO = shopAreaReadFacade.getShopArea(shopAreaId);
            if (null == shopAreaDTO){
                return Result.SUCESS(null);
            }
            shopAreaVO = ObjectConvertUtil.getMapperFactory().getMapperFacade().map(shopAreaDTO, ShopAreaVO.class);
            //shopAreaVO.setDeskTotal(shopAreaReadFacade.countDeskTotal(shopAreaId));
            result = Result.SUCESS(shopAreaVO);
        } catch (BaseException e) {
            logger.error("findShopAreaById Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    /**
     * 删除店铺区域
     * @param shopAreaIds
     * @return Result<Boolean>
     * @author zhoufe
     * @date 2017年7月18日 下午3:38:16
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/deleteShopArea")
    @ApiOperation(value = "删除店铺区域", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> deleteShopArea(
 	       @ApiParam(name = "shopAreaIds", value = "店铺区域ID", required = true)
           @RequestBody List<Long> shopAreaIds) {
        Result<Boolean> result;
        try {
        	Boolean isSuccess = shopAreaWriteFacade.deleteShopAreas(shopAreaIds);
        	result = Result.SUCESS(isSuccess);
        } catch (BaseException e) {
            logger.error("deleteShopArea Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        } 
        return result;
    }
    
    /**
     * 新增店铺区域
     * @param shopAreaParamVO
     * @return Result<Long>
     * @author zhoufe
     * @date 2017年7月18日 下午3:45:20
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/insertShopArea")
    @ApiOperation(value = "新增店铺区域：自定义区域，默认区域由系统创建", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Long> insertShopArea(
  	        @ApiParam(name = "addParamVO", value = "addParamVO", required = true)
    		@RequestBody ShopAreaParamVO shopAreaParamVO) {
         Result<Long> result;
         try {
             Long shopAreaId;
             Long shopId = ThreadLocalContext.getShopId();
             if (null == shopId || 0 == shopId){
                 throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, "获取店铺ID参数为空");
             }
             if (shopAreaParamVO == null){
                 throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, "传入的参数为空");
             }
             ShopAreaDTO shopAreaDTO = ObjectConvertUtil.getMapperFactory().getMapperFacade().map(shopAreaParamVO, ShopAreaDTO.class);
             shopAreaDTO.setShopId(shopId);
             shopAreaDTO.setDefaultStatus(ShopAreaDefaultStatusEnum.CUSTOM);
             shopAreaId = shopAreaWriteFacade.insertShopArea(shopAreaDTO);

         	result = Result.SUCESS(shopAreaId);
         } catch (BaseException e) {
             logger.error("insertShopArea Exception: {}", e);
             result = Result.FAILURE(e.getCode(), e.getMessage());
         } 
         return result;
     }
    
    /**
     * 更新店铺区域
     * @param shopAreaParamVOS
     * @return Result<Boolean>
     * @author zhoufe
     * @date 2017年7月18日 下午3:47:32
     */
    @SuppressWarnings("unchecked")
   	@ResponseBody
    @RequestMapping("/updateShopArea")
    @ApiOperation(value = "更新店铺区域", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> updateShopArea(
  	        @ApiParam(name = "updateParamVO", value = "updateParamVO", required = true)
    		@RequestBody List<ShopAreaParamVO> shopAreaParamVOS) {
         Result<Boolean> result;
         try {

             if (CollectionUtils.isListBlank(shopAreaParamVOS)){
                 throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, "传入的参数为空");
             }
             List<ShopAreaDTO> shopAreaDTOS = ObjectConvertUtil.getMapperFactory().getMapperFacade().mapAsList(shopAreaParamVOS, ShopAreaDTO.class);
             Long shopId = ThreadLocalContext.getShopId();
             for (ShopAreaDTO shopAreaDTO : shopAreaDTOS) {
                 shopAreaDTO.setShopId(shopId);
             }
			 Boolean isSuccess = shopAreaWriteFacade.updateShopArea(shopAreaDTOS);
         	 result = Result.SUCESS(isSuccess);
         } catch (BaseException e) {
             logger.error("updateShopArea Exception: {}", e);
             result = Result.FAILURE(e.getCode(), e.getMessage());
         } 
         return result;
     }


    /**
     * 获取店铺下的区域并区域下的桌台
     * @return Result<List<ShopAreaVO>>
     * @author zhoufe
     * @date 2017年7月19日 上午11:44:39
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/listAreaAndDesk")
    @ApiOperation(value = "获取店铺下的区域并区域下的桌台", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<ShopAreaVO>> listAreaAndDesk() {
        Result<List<ShopAreaVO>> result;
        try {

            List<ShopAreaDTO> shopAreaDTOS;
            Long shopId = ThreadLocalContext.getShopId();
            if (null == shopId || 0 == shopId){
                shopAreaDTOS = shopAreaReadFacade.listAreaAndDeskByShopIdFirstBySn(ThreadLocalContext.getDid());
            }else{
                shopAreaDTOS = shopAreaReadFacade.listAreaAndDeskByShopId(shopId);
            }
            if (CollectionUtils.isListBlank(shopAreaDTOS)){
                return Result.SUCESS(new ArrayList<>());
            }
            MapperFactory mapperFactory = ObjectConvertUtil.getMapperFactory();
            List<ShopAreaVO> shopDeskVOS = mapperFactory.getMapperFacade().mapAsList(shopAreaDTOS, ShopAreaVO.class);
            result = Result.SUCESS(shopDeskVOS);
        } catch (BaseException e) {
            logger.error("listAreaAndDesk Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }


    /**
     * 获取店铺下的区域并区域下的桌台：根据桌台状态
     * @return Result<List<ShopAreaVO>>
     * @author zhoufe
     * @date 2017年7月19日 上午11:44:39
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/listAreaAndDeskByUsedStatuses")
    @ApiOperation(value = "获取店铺下的区域并区域下的桌台：根据桌台状态", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<ShopAreaVO>> listAreaAndDeskByUsedStatuses(
            @ApiParam(name = "usedStatus", value = "usedStatus", required = true)
            @RequestBody  List<DeskUsedStatusEnum> usedStatuses) {
        Result<List<ShopAreaVO>> result;
        try {

            List<ShopAreaDTO> shopAreaDTOS;
            Long shopId = ThreadLocalContext.getShopId();
            if (null == shopId || 0 == shopId){
                shopAreaDTOS = shopAreaReadFacade.listAreaAndDeskByShopIdFirstBySn(ThreadLocalContext.getDid(), usedStatuses);
            }else{
                shopAreaDTOS =  shopAreaReadFacade.listAreaAndDeskByShopId(shopId, usedStatuses);
            }
            if (CollectionUtils.isListBlank(shopAreaDTOS)){
                return Result.SUCESS(new ArrayList<>());
            }
            MapperFactory mapperFactory = ObjectConvertUtil.getMapperFactory();
            List<ShopAreaVO> shopDeskVOS = mapperFactory.getMapperFacade().mapAsList(shopAreaDTOS, ShopAreaVO.class);
            result = Result.SUCESS(shopDeskVOS);
        } catch (BaseException e) {
            logger.error("listAreaAndDeskByUsedStatuses Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }

    /**
     * 获取桌台统计APP
     * @return Result<ShopAreaDeskSummaryVO>
     * @author zhoufe
     * @date 2017年7月19日 上午11:32:47
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/listShopAreaDeskSummaryAll")
    @ApiOperation(value = "获取桌台统计：一次性查出区域，桌台，人数的统计", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<ShopAreaDeskSummaryVO> listShopAreaDeskSummaryAll() {
        Result<ShopAreaDeskSummaryVO> result;
        try {
            Long shopId = ThreadLocalContext.getShopId();
            ShopAreaDeskSummaryDTO shopAreaDeskSummaryDTO = shopAreaReadFacade.listShopAreaDeskSummaryAll(shopId);
            if (null == shopAreaDeskSummaryDTO){
                return Result.SUCESS(null);
            }
            ShopAreaDeskSummaryVO shopAreaDeskSummaryVO = ObjectConvertUtil.getMapperFactory().getMapperFacade().map(shopAreaDeskSummaryDTO, ShopAreaDeskSummaryVO.class);
            result = Result.SUCESS(shopAreaDeskSummaryVO);
        } catch (BaseException e) {
            logger.error("listShopAreaDeskSummaryAll Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }

    /**
     * 获取区域桌台总览
     * 获取该店铺下的区域，并获取该区域下的各个桌台状态的统计信息
     * 根据开关信息返回数据
     * 如果是开启桌台状态，查询统计：空闲，待接单，待点单，待结账，待清台，锁定，呼叫
     * 如果是开启桌台状态，查询统计：空闲，待接单，待结账，锁定，呼叫
     * @params []
     * @return com.lizikj.api.vo.Result<java.util.List<com.lizikj.api.vo.shop.ShopAreaSummaryVO>>
     * @author zhoufe
     * @date 2017/8/28 21:54
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/listShopAreaSummary")
    @ApiOperation(value = "获取区域桌台总览", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<ShopAreaSummaryVO>> listShopAreaSummary() {
        Result<List<ShopAreaSummaryVO>> result;
        try {
            Long shopId = ThreadLocalContext.getShopId();
            List<ShopAreaSummaryDTO> shopAreaSummaryDTOS;
            if (null == shopId || 0 == shopId){
                String snNum = ThreadLocalContext.getDid();
                shopAreaSummaryDTOS = shopAreaReadFacade.listShopAreaSummaryByShopIdFirstBySn(snNum);
            }else{
                shopAreaSummaryDTOS = shopAreaReadFacade.listShopAreaSummaryByShopId(shopId);
            }
            if (CollectionUtils.isListBlank(shopAreaSummaryDTOS)){
                return Result.SUCESS(new ArrayList<>());
            }
            List<ShopAreaSummaryVO> shopAreaSummaryVOS = ObjectConvertUtil.getMapperFactory().getMapperFacade().mapAsList(shopAreaSummaryDTOS, ShopAreaSummaryVO.class);
            result = Result.SUCESS(shopAreaSummaryVOS);
        } catch (BaseException e) {
            logger.error("listShopAreaSummary Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }


}
