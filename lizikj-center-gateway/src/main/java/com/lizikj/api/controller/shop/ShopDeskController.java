package com.lizikj.api.controller.shop;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.shop.ShopCustomDeskNumberVO;
import com.lizikj.api.vo.shop.ShopDeskVO;
import com.lizikj.api.vo.shop.param.RemoveToAnotherAreaParamVO;
import com.lizikj.api.vo.shop.param.ShopDeskBatchParamVO;
import com.lizikj.api.vo.shop.param.ShopDeskCallServiceParamVO;
import com.lizikj.api.vo.shop.param.ShopDeskChangeUsedStatusParamVO;
import com.lizikj.api.vo.shop.param.ShopDeskParamVO;
import com.lizikj.common.exception.BaseException;
import com.lizikj.common.util.CollectionUtils;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.login.interceptor.LoginExclude;
import com.lizikj.order.dto.ShopCustomDeskNumberDTO;
import com.lizikj.order.facade.IOrderReadFacade;
import com.lizikj.shop.api.dto.ShopDeskDTO;
import com.lizikj.shop.api.dto.param.QueryDeskParamDTO;
import com.lizikj.shop.api.dto.param.RemoveToAnotherAreaParamDTO;
import com.lizikj.shop.api.dto.param.ShopDeskBacthParamDTO;
import com.lizikj.shop.api.dto.param.ShopDeskCallServiceParamDTO;
import com.lizikj.shop.api.dto.param.ShopDeskChangeUsedStatusParamDTO;
import com.lizikj.shop.api.enums.DeskUsedStatusEnum;
import com.lizikj.shop.api.enums.DeskUsedSubStatusEnum;
import com.lizikj.shop.api.enums.ShopErrorEnum;
import com.lizikj.shop.api.exception.ShopException;
import com.lizikj.shop.api.facade.IDeskPublicQrcodeReadFacade;
import com.lizikj.shop.api.facade.IShopDeskReadFacade;
import com.lizikj.shop.api.facade.IShopDeskWriteFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ma.glasnost.orika.MapperFactory;


/**
 * 店铺桌台接口
 * @author zhoufe
 * @date 2017/07/10
 */
@Controller
@RequestMapping("/shop/desk")
@Api(value = "shop-desk", tags = "店铺桌台API接口")
public class ShopDeskController {

    private static final Logger logger = LoggerFactory.getLogger(ShopDeskController.class);

    @Autowired
    private IShopDeskReadFacade shopDeskReadFacade;

    @Autowired
    private IShopDeskWriteFacade shopDeskWriteFacade;

    @Autowired
    private IOrderReadFacade orderReadFacade;
    
    @Autowired
    private IDeskPublicQrcodeReadFacade deskPublicQrcodeReadFacade;

    /**
     * 查询该店铺下的桌台
     * @return Result<List<ShopDeskVO>>
     * @author zhoufe
     * @date 2017年7月18日 下午3:31:23
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/listShopDesk")
    @ApiOperation(value = "查询该店铺下的桌台", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<ShopDeskVO>> listShopDesk() {
        Result<List<ShopDeskVO>> result;
        try {
            Long shopId = ThreadLocalContext.getShopId();
            List<ShopDeskDTO> shopDeskDTOS = shopDeskReadFacade.listShopDesk(shopId);
            if (CollectionUtils.isListBlank(shopDeskDTOS)){
                return Result.SUCESS(new ArrayList<>());
            }
            MapperFactory mapperFactory = ObjectConvertUtil.getMapperFactory();
            List<ShopDeskVO> shopDeskVOS = mapperFactory.getMapperFacade().mapAsList(shopDeskDTOS, ShopDeskVO.class);
            result = Result.SUCESS(shopDeskVOS);
        } catch (BaseException e) {
            logger.error("listShopDesk Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    /**
     * 查询该店铺下的桌台
     * @return Result<List<ShopDeskVO>>
     * @author zhoufe
     * @date 2017年7月18日 下午3:31:23
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/listByIds")
    @ApiOperation(value = "根据IDs查询该的桌台", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<ShopDeskVO>> listByIds(
            @ApiParam(name = "areaDeskIds", value = "店铺桌台ID", required = true)
            @RequestBody List<Long> areaDeskIds) {
        Result<List<ShopDeskVO>> result;
        try {
            List<ShopDeskDTO> shopDeskDTOS = shopDeskReadFacade.listByIds(areaDeskIds);
            if (CollectionUtils.isListBlank(shopDeskDTOS)){
                return Result.SUCESS(new ArrayList<>());
            }
            MapperFactory mapperFactory = ObjectConvertUtil.getMapperFactory();
            List<ShopDeskVO> shopDeskVOS = mapperFactory.getMapperFacade().mapAsList(shopDeskDTOS, ShopDeskVO.class);
            result = Result.SUCESS(shopDeskVOS);
        } catch (BaseException e) {
            logger.error("listByIds Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }


    /**
     * 删除店铺桌台
     * @param areaDeskIds
     * @return Result<Boolean>
     * @author zhoufe
     * @date 2017年7月18日 下午3:38:16
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/deleteShopDesks")
    @ApiOperation(value = "删除店铺桌台", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> deleteShopDesks(
 	       @ApiParam(name = "areaDeskIds", value = "店铺桌台ID", required = true, type = "Long")
           @RequestBody List<Long> areaDeskIds) {
        Result<Boolean> result;
        try {
            Boolean isSuccess = shopDeskWriteFacade.deleteShopDesks(areaDeskIds);
        	result = Result.SUCESS(isSuccess);
        } catch (BaseException e) {
            logger.error("deleteShopDesks Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }

    /**
     * 新增店铺桌台
     * @param shopDeskParamVO
     * @return Result<Long>
     * @author zhoufe
     * @date 2017年7月18日 下午3:45:20
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/insertShopDesk")
    @ApiOperation(value = "新增店铺桌台", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Long> insertShopDesk(
  	        @ApiParam(name = "addParamVO", value = "addParamVO", required = true)
    		@RequestBody ShopDeskParamVO shopDeskParamVO) {
         Result<Long> result;
         try {

             if(shopDeskParamVO == null){
                 throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, "输入的参数为空");
             }
             MapperFactory mapperFactory = ObjectConvertUtil.getMapperFactory();
             ShopDeskDTO shopDeskDTO = mapperFactory.getMapperFacade().map(shopDeskParamVO, ShopDeskDTO.class);
             Long shopId = ThreadLocalContext.getShopId();
             shopDeskDTO.setShopId(shopId);
             Long shopDeskId = shopDeskWriteFacade.insertShopDesk(shopDeskDTO);
             result = Result.SUCESS(shopDeskId);
         } catch (BaseException e) {
             logger.error("insertShopDesk Exception: {}", e);
             result = Result.FAILURE(e.getCode(), e.getMessage());
         }
         return result;
     }


    /**
     * 批量新增店铺桌台
     * @param shopDeskBatchParamVO
     * @return Result<Long>
     * @author zhoufe
     * @date 2017年7月18日 下午3:45:20
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/batchInsertShopDesk")
    @ApiOperation(value = "批量新增店铺桌台", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> batchInsertShopDesk(
  	        @ApiParam(name = "addParamVO", value = "addParamVO", required = true)
    		@RequestBody ShopDeskBatchParamVO shopDeskBatchParamVO) {
         Result<Boolean> result;
         try {
             if (shopDeskBatchParamVO == null){
                 throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, "参数传入为空");
             }
             MapperFactory mapperFactory = ObjectConvertUtil.getMapperFactory();
             ShopDeskBacthParamDTO shopDeskBacthParamDTO = mapperFactory.getMapperFacade().map(shopDeskBatchParamVO, ShopDeskBacthParamDTO.class);


             Long shopId = ThreadLocalContext.getShopId();
             shopDeskBacthParamDTO.setShopId(shopId);
             shopDeskWriteFacade.batchInsertShopDesk(shopDeskBacthParamDTO);
         	 result = Result.SUCESS(true);
         } catch (BaseException e) {
             logger.error("batchInsertShopDesk Exception: {}", e);
             result = Result.FAILURE(e.getCode(), e.getMessage());
         }
         return result;
     }

    /**
     * 更新店铺桌台
     * @param shopDeskParamVOS
     * @return Result<Boolean>
     * @author zhoufe
     * @date 2017年7月18日 下午3:47:32
     */
    @SuppressWarnings("unchecked")
   	@ResponseBody
    @RequestMapping("/updateShopDesks")
    @ApiOperation(value = "更新店铺桌台", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> updateShopDesks(
  	        @ApiParam(name = "updateParamVO", value = "updateParamVO", required = true)
    		@RequestBody List<ShopDeskParamVO> shopDeskParamVOS) {
         Result<Boolean> result;
         try {
             if (CollectionUtils.isListBlank(shopDeskParamVOS)){
                 throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, "传入参数为空");
             }
             MapperFactory mapperFactory = ObjectConvertUtil.getMapperFactory();
             List<ShopDeskDTO> shopDeskDTOS = mapperFactory.getMapperFacade().mapAsList(shopDeskParamVOS, ShopDeskDTO.class);

             Long shopId = ThreadLocalContext.getShopId();

             for (ShopDeskDTO shopDeskDTO : shopDeskDTOS) {
                 shopDeskDTO.setShopId(shopId);
             }
             Boolean isSuccess = shopDeskWriteFacade.updateShopDesks(shopDeskDTOS);
             result = Result.SUCESS(isSuccess);
         } catch (BaseException e) {
             logger.error("updateShopDesks Exception: {}", e);
             result = Result.FAILURE(e.getCode(), e.getMessage());
         }
         return result;
     }


    /**
     * 更新店铺桌台状态
     * @param shopDeskChangeUsedStatusParamVO
     * @return Result<Boolean>
     * @author zhoufe
     * @date 2017年7月18日 下午3:47:32
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/changeUsedStatus")
    @ApiOperation(value = "更新店铺桌台状态", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> changeUsedStatus(
            @ApiParam(name = "changeParamVO", value = "changeParamVO", required = true)
            @RequestBody ShopDeskChangeUsedStatusParamVO shopDeskChangeUsedStatusParamVO) {
        Result<Boolean> result;
        try {

            if (shopDeskChangeUsedStatusParamVO == null){
                throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, "传入参数为空");
            }
            MapperFactory mapperFactory = ObjectConvertUtil.getMapperFactory();
            ShopDeskChangeUsedStatusParamDTO shopDeskChangeUsedStatusParamDTO =
                    mapperFactory.getMapperFacade().map(shopDeskChangeUsedStatusParamVO, ShopDeskChangeUsedStatusParamDTO.class);

            Long shopId = ThreadLocalContext.getShopId();
            shopDeskChangeUsedStatusParamDTO.setShopId(shopId);
            Boolean isSuccess = shopDeskWriteFacade.changeUsedStatus(shopDeskChangeUsedStatusParamDTO);
            result = Result.SUCESS(isSuccess);
        } catch (BaseException e) {
            logger.error("changeUsedStatus Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }

    /**
     * 更新店铺桌台叫起状态
     * @param areaDeskId
     * @return Result<Boolean>
     * @author zhoufe
     * @date 2017年7月18日 下午3:47:32
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/changeUsedSubStatus")
    @ApiOperation(value = "更新店铺桌台叫起状态", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> changeUsedSubStatus(
            @ApiParam(value = "桌台ID", required = true)
            @RequestParam Long areaDeskId,
            @ApiParam(value = "叫起与否：见DeskUsedSubStatusEnum： WAIT.待叫起，NONE.无。", required = true)
            @RequestParam DeskUsedSubStatusEnum deskUsedSubStatus) {
        Result<Boolean> result;
        try {

            Boolean isSuccess = shopDeskWriteFacade.changeUsedSubStatus(areaDeskId, deskUsedSubStatus);
            result = Result.SUCESS(isSuccess);
        } catch (BaseException e) {
            logger.error("changeUsedSubStatus Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }


    /**
     * 根据桌台ID查询桌台信息
     * @param areaDeskId
     * @return Result<Boolean>
     * @author zhoufe
     * @date 2017年7月18日 下午3:47:32
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/findAreaDeskById")
    @ApiOperation(value = "根据桌台ID查询桌台信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<ShopDeskVO> findAreaDeskById(
            @ApiParam(value = "桌台ID", required = true)
            @RequestParam Long areaDeskId) {
        Result<ShopDeskVO> result;
        try {

            ShopDeskDTO shopDeskDTO = shopDeskReadFacade.findByDeskId(areaDeskId);
            ShopDeskVO shopDeskVO = ObjectConvertUtil.getMapperFactory().getMapperFacade().map(shopDeskDTO, ShopDeskVO.class);
            result = Result.SUCESS(shopDeskVO);
        } catch (BaseException e) {
            logger.error("findAreaDeskById Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }

    /**
     * 呼叫服务
     * @param shopDeskCallServiceParamVO
     * @return Result<Boolean>
     * @author zhoufe
     * @date 2017年7月18日 下午3:47:32
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/changeCallService")
    @ApiOperation(value = "呼叫服务", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> changeCallService(
            @ApiParam(name = "paramVO", value = "paramVO", required = true)
            @RequestBody ShopDeskCallServiceParamVO shopDeskCallServiceParamVO) {
        Result<Boolean> result;
        try {
            //如果是不呼叫CallStatusEnum.NOT_CALL就改变桌台的呼叫状态
            MapperFactory mapperFactory = ObjectConvertUtil.getMapperFactory();
            ShopDeskCallServiceParamDTO shopDeskCallServiceParamDTO =
                    mapperFactory.getMapperFacade().map(shopDeskCallServiceParamVO, ShopDeskCallServiceParamDTO.class);

            if (shopDeskCallServiceParamDTO == null){
                throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, "传入参数为空");
            }
            Boolean isSuccess = shopDeskWriteFacade.changeCallService(shopDeskCallServiceParamDTO);
            result = Result.SUCESS(isSuccess);
        } catch (BaseException e) {
            logger.error("changeCallService Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }

    /**
     * 获取桌台统计APP
     * @param groupByColum 分组的字段名称：0.区域 shopAreaId， 1.状态 usedStatus，2.人数 seatNum
     * @return Result<ShopAreaDeskSummaryVO>
     * @author zhoufe
     * @date 2017年7月19日 上午11:32:47
     */
//    @SuppressWarnings("unchecked")
//   	@ResponseBody
//    @RequestMapping("/listShopAreaDeskSummary/{groupByColum}")
//    @ApiOperation(value = "获取桌台统计", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Result<ShopAreaDeskSummaryVO> listShopAreaDeskSummary(
//            @ApiParam(name = "groupByColum", value = "分组的字段名称：AREA.区域，STATUS.状态，SEATNUM.座位数。", required = true, type = "Integer")
//            @PathVariable(name = "groupByColum") GroupByColumEnum groupByColum
//    		) {
//         Result<ShopAreaDeskSummaryVO> result;
//         try {
//             Long shopId = ThreadLocalContext.getShopId();
//        	 ShopAreaDeskSummaryVO shopAreaDeskSummaryVO = null;
//        	 result = Result.SUCESS(shopAreaDeskSummaryVO);
//         } catch (BaseException e) {
//             logger.error("listShopAreaDeskSummary Exception: {}", e);
//             result = Result.FAILURE(e.getCode(), e.getMessage());
//         }
//         return result;
//     }


    /**
     * 根据座位数获取桌台
     * 当9-999
     * @param seatNumStart seatNumEnd
     * @return Result<List<ShopDeskVO>>
     * @author zhoufe
     * @date 2017年7月19日 上午11:41:57
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/listShopDeskBySeatNum/{seatNumStart}/{seatNumEnd}")
    @ApiOperation(value = "根据座位数获取桌台：点击全部时调listShopDesk", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<ShopDeskVO>> listShopDeskBySeatNum(
            @ApiParam(name = "seatNumStart", value = "座位数开始", required = true, type = "Integer")
            @PathVariable(name = "seatNumStart") Integer seatNumStart,
            @ApiParam(name = "seatNumEnd", value = "座位数结束", required = true, type = "Integer")
            @PathVariable(name = "seatNumEnd") Integer seatNumEnd
    ) {
        Result<List<ShopDeskVO>> result;
        try {
            Long shopId = ThreadLocalContext.getShopId();
            QueryDeskParamDTO queryDeskParamDTO = new QueryDeskParamDTO();
            queryDeskParamDTO.setSeatNumStart(seatNumStart);
            queryDeskParamDTO.setSeatNumEnd(seatNumEnd);
            queryDeskParamDTO.setShopId(shopId);
            List<ShopDeskDTO> shopDeskDTOS = shopDeskReadFacade.findByCondition(queryDeskParamDTO);
            List<ShopDeskVO> shopDeskVOS = new ArrayList<>();
            if (CollectionUtils.isListNotBlank(shopDeskDTOS)){
                shopDeskVOS = ObjectConvertUtil.getMapperFactory().getMapperFacade().mapAsList(shopDeskDTOS, ShopDeskVO.class);
            }
            result = Result.SUCESS(shopDeskVOS);
        } catch (BaseException e) {
            logger.error("listShopDeskBySeatNum Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }

    /**
     * 根据区域获取桌台
     * 当shopAreaId为空取全部的区域的桌台listShopDesk接口
     * @param shopAreaId
     * @return Result<List<ShopDeskVO>>
     * @author zhoufe
     * @date 2017年7月19日 上午11:41:29
     */
    @SuppressWarnings("unchecked")
   	@ResponseBody
    @RequestMapping("/listShopDeskByArea/{shopAreaId}")
    @ApiOperation(value = "根据区域获取桌台：当shopAreaId为空取全部的区域的桌台listShopDesk接口", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<ShopDeskVO>> listShopDeskByArea(
            @ApiParam(name = "shopAreaId", value = "店铺区域ID", required = true)
            @PathVariable(name = "shopAreaId") Long shopAreaId
    		) {
         Result<List<ShopDeskVO>> result;
         try {

             if(shopAreaId == null ||shopAreaId == 0){
                 throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, "shopAreaId不能为空或者为0！如果需要请调listShopDesk接口");
             }

             QueryDeskParamDTO queryDeskParamDTO = new QueryDeskParamDTO();
             queryDeskParamDTO.setShopAreaId(shopAreaId);
             List<ShopDeskDTO> shopDeskDTOS = shopDeskReadFacade.findByCondition(queryDeskParamDTO);
             List<ShopDeskVO> shopDeskVOS = new ArrayList<>();
             if (CollectionUtils.isListNotBlank(shopDeskDTOS)){
                 shopDeskVOS = ObjectConvertUtil.getMapperFactory().getMapperFacade().mapAsList(shopDeskDTOS, ShopDeskVO.class);
             }
        	 result = Result.SUCESS(shopDeskVOS);
         } catch (BaseException e) {
             logger.error("listShopDeskByArea Exception: {}", e);
             result = Result.FAILURE(e.getCode(), e.getMessage());
         }
         return result;
     }

    /**
     * 根据桌台状态获取桌台
     * 如果有区域的话就传区域ID
     * 当shopAreaId为空取全部的区域
     * @param shopAreaId
     * @param usedStatus
     * @return Result<List<ShopDeskVO>>
     * @author zhoufe
     * @date 2017年7月19日 上午11:44:39
     */
    @SuppressWarnings("unchecked")
   	@ResponseBody
    @RequestMapping("/listShopDeskByStatus/{shopAreaId}/{usedStatus}")
    @ApiOperation(value = "根据桌台状态获取桌台：当shopAreaId为空取该店铺的全部的区域", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<ShopDeskVO>> listShopDeskByStatus(
            @ApiParam(name = "shopAreaId", value = "区域ID")
            @PathVariable(name = "shopAreaId") Long shopAreaId,

            @ApiParam(name = "usedStatus", value = "使用状态：见：DeskUsedStatusEnum：FREE. 空闲，WAIT_REC. 待接单，WAIT_ORDER. 待点单，" +
                    "WAIT_PAYMENT. 待结账，WAIT_CALL_SERV. 待清台，LOCKED. 锁定。", required = true)
            @PathVariable(name = "usedStatus") DeskUsedStatusEnum usedStatus
    		) {
         Result<List<ShopDeskVO>> result;
         try {
             Long shopId = ThreadLocalContext.getShopId();
             QueryDeskParamDTO queryDeskParamDTO = new QueryDeskParamDTO();
             queryDeskParamDTO.setUsedStatus(usedStatus);
             queryDeskParamDTO.setShopAreaId(shopAreaId);
             queryDeskParamDTO.setShopId(shopId);
             List<ShopDeskDTO> shopDeskDTOS = shopDeskReadFacade.findByCondition(queryDeskParamDTO);
             List<ShopDeskVO> shopDeskVOS = new ArrayList<>();
             if (CollectionUtils.isListNotBlank(shopDeskDTOS)){
                 MapperFactory mapperFactory = ObjectConvertUtil.getMapperFactory();
                 shopDeskVOS = mapperFactory.getMapperFacade().mapAsList(shopDeskDTOS, ShopDeskVO.class);
             }
             result = Result.SUCESS(shopDeskVOS);
         } catch (BaseException e) {
             logger.error("listShopDeskByStatus Exception: {}", e);
             result = Result.FAILURE(e.getCode(), e.getMessage());
         }
         return result;
     }

    /**
     * 获取该店铺下的自定义桌台
     * 需要的 distinct customDeskNumber 查询订单
     * @return Result<List<ShopDeskVO>>
     * @author zhoufe
     * @date 2017年7月19日 上午11:44:39
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/listShopCustomDeskNumber")
    @ApiOperation(value = "获取该店铺下的自定义桌台", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<ShopCustomDeskNumberVO>> listShopCustomDeskNumber() {
        Result<List<ShopCustomDeskNumberVO>> result;
        try {

            Long shopId = ThreadLocalContext.getShopId();
            List<ShopCustomDeskNumberDTO> shopCustomDeskNumberDTOS = orderReadFacade.listCustomDeskNumber(shopId);

            List<ShopCustomDeskNumberVO> shopCustomDeskNumberVOS = new ArrayList<>();
            if (CollectionUtils.isListNotBlank(shopCustomDeskNumberDTOS)){
                shopCustomDeskNumberVOS = ObjectConvertUtil.getMapperFactory().getMapperFacade().mapAsList(shopCustomDeskNumberDTOS, ShopCustomDeskNumberVO.class);
            }
            result = Result.SUCESS(shopCustomDeskNumberVOS);
        } catch (BaseException e) {
            logger.error("listShopCustomDeskNumber Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }


    /**
     * 根据区域ID，桌台名称模糊查询桌台
     * 当shopAreaId == null时查询全部区域符合的桌台
     * @return Result<List<ShopDeskVO>>
     * @author zhoufe
     * @date 2017年7月19日 上午11:44:39
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/listByAreaIdAndDeskName/{shopAreaId}/{deskName}")
    @ApiOperation(value = "根据区域ID，桌台名称模糊查询桌台：当shopAreaId == 0时查询全部区域符合的桌台", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<ShopDeskVO>> listByAreaIdAndDeskName(
            @ApiParam(name = "shopAreaId", value = "区域ID")
            @PathVariable Long shopAreaId,
            @ApiParam(name = "deskName", value = "桌台名称")
            @PathVariable String deskName

    ) {
        Result<List<ShopDeskVO>> result;
        try {

            List<ShopDeskDTO> shopDeskDTOS;
            Long shopId = ThreadLocalContext.getShopId();
            if (null == shopId || 0 == shopId){
                shopDeskDTOS = shopDeskReadFacade.listByAreaIdAndDeskNameByShopIdFirstBySn(ThreadLocalContext.getDid(), shopAreaId, deskName);
            }else{
                shopDeskDTOS = shopDeskReadFacade.listByAreaIdAndDeskName(shopId, shopAreaId, deskName);
            }
            List<ShopDeskVO> shopDeskVOS = new ArrayList<>();
            if (CollectionUtils.isListNotBlank(shopDeskDTOS)){
                MapperFactory mapperFactory = ObjectConvertUtil.getMapperFactory();
                shopDeskVOS = mapperFactory.getMapperFacade().mapAsList(shopDeskDTOS, ShopDeskVO.class);
            }
            result = Result.SUCESS(shopDeskVOS);
        } catch (BaseException e) {
            logger.error("listByAreaIdAndDeskName Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }


    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/removeToAnotherArea")
    @ApiOperation(value = "将这些桌台移入另一个区域", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> removeToAnotherArea(
            @ApiParam(name = "paramVO", value = "paramVO", required = true)
            @RequestBody RemoveToAnotherAreaParamVO removeToAnotherAreaParamVO) {
        Result<Boolean> result;
        try {
            if (removeToAnotherAreaParamVO == null){
                throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, "传入参数为空");
            }

            RemoveToAnotherAreaParamDTO removeToAnotherAreaParamDTO = ObjectConvertUtil.getMapperFactory().getMapperFacade().map(removeToAnotherAreaParamVO, RemoveToAnotherAreaParamDTO.class);
            boolean isSuccess = shopDeskWriteFacade.removeToAnotherArea(removeToAnotherAreaParamDTO);
            result = Result.SUCESS(isSuccess);
        } catch (BaseException e) {
            logger.error("removeToAnotherArea Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }

    /**
     * 通url获取桌号id
     * @param encodedUrl
     * @return
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/getDeskIdByPublicUrl")
    @ApiOperation(value = "通url获取桌号id", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Long> getDeskIdByPublicUrl(
			@ApiParam(name = "encodedUrl", value = "经过 URLEncode 的桌台url 编码utf-8") @RequestParam(name = "encodedUrl", required = true) String encodedUrl) {
		if (StringUtils.isBlank(encodedUrl)) {
			return Result.FAILURE("参数不允许为空");
		}
		Long deskId = deskPublicQrcodeReadFacade.getDeskIdByPublicUrl(encodedUrl);
		if (deskId != null && deskId > 0) {
			return Result.SUCESS(deskId);
		}
		return Result.FAILURE("此桌号没绑定二维码");
	}


}
