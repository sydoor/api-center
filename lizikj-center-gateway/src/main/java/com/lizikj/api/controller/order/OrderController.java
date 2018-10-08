package com.lizikj.api.controller.order;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.lizikj.api.constant.CenterConstants;
import com.lizikj.api.utils.CenterUtil;
import com.lizikj.api.utils.FreeMarkerExportExcelUtil;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.order.*;
import com.lizikj.api.vo.order.param.ChangeTableParamVO;
import com.lizikj.api.vo.order.param.OrderInfoParamVO;
import com.lizikj.api.vo.order.param.QuerySyncOrderParamVO;
import com.lizikj.api.vo.order.param.query.OrderInfoQueryParamVO;
import com.lizikj.api.vo.reporting.LmwOrderStatisticsSummaryVO;
import com.lizikj.cache.Cache;
import com.lizikj.common.enums.PaymentTypeEnum;
import com.lizikj.common.enums.UserLoginSourceEnum;
import com.lizikj.common.enums.UserSourceSceneEnum;
import com.lizikj.common.util.*;
import com.lizikj.login.interceptor.LoginExclude;
import com.lizikj.marketing.api.enums.SupportCouponTimeTypeEnum;
import com.lizikj.merchant.dto.AuthShopStaffDTO;
import com.lizikj.merchant.facade.IAuthShopStaffReadFacade;
import com.lizikj.order.dto.*;
import com.lizikj.order.dto.detail.OrderCancelReasonInfoDTO;
import com.lizikj.order.dto.detail.OrderCancelReasonItemDTO;
import com.lizikj.order.dto.param.GetOrderInfoParamDTO;
import com.lizikj.order.dto.param.OrderInfoParamDTO;
import com.lizikj.order.dto.param.query.OrderInfoQueryParamDTO;
import com.lizikj.order.enums.*;
import com.lizikj.order.exception.OrderException;
import com.lizikj.order.facade.*;
import com.lizikj.payment.pay.dto.LmwOrderCirculationInfoDTO;
import com.lizikj.payment.pay.dto.PayFlowDTO;
import com.lizikj.payment.pay.dto.ShopCirculationSummaryDTO;
import com.lizikj.payment.pay.enums.CirculationTypeEnum;
import com.lizikj.payment.pay.enums.PayStatusEnum;
import com.lizikj.payment.pay.facade.ICirculationReadFacade;
import com.lizikj.shop.api.enums.AuthOperationEnum;
import com.lizikj.shop.api.facade.IShopAuthCodeReadFacade;
import io.jsonwebtoken.lang.Collections;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by adept on 2017/7/12.
 */
@Controller
@RequestMapping("/order")
@Api(value = "order", tags = "订单API接口")
@SuppressWarnings("unchecked")
public class OrderController extends OrderBaseController {
    /**
     * @private
     */
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Resource
    private IOrderReadFacade orderReadFacade;

    @Resource
    private IOrderWriteFacade orderWriteFacade;

    @Resource
    private ISyncPosOrderWriteFacade syncPosOrderWriteFacade;

    @Resource
    private ISyncPosOrderReadFacade syncPosOrderReadFacade;

    @Resource
    private IShopAuthCodeReadFacade shopAuthCodeReadFacade;

    @Resource
    private IAuthShopStaffReadFacade authShopStaffReadFacade;

    @Resource
    private ICirculationReadFacade circulationReadFacade;

    @Resource
    private IPendingOrderInfoWriteFacade pendingOrderInfoWriteFacade;

    @Resource
    private IPendingOrderInfoReadFacade pendingOrderInfoReadFacade;

    @Resource
    private Cache cache;


    @Value("${export.excel.template.path}")
    private String excelBuilPath;

    @ResponseBody
    @RequestMapping("/getOrderInfoByOrderId/{orderId}/{detailFlag}")
    @ApiOperation(value = "订单ID获取订单详情信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<OrderInfoVO> getOrderInfoByOrderId(
            @ApiParam(name = "orderId", value = "订单ID", required = true, type = "Long")
            @PathVariable(name = "orderId") Long orderId,
            @ApiParam(name = "detailFlag", value = "订单详情拆分成(\n" +
                    "ALL-Integer.MaxValue=2147483647,\n" +
                    "基础信息-Base 1 << 0 ,\n" +
                    "订单物品-orderItems 1 << 1,\n" +
                    "优惠详情-discounts 1 << 2,\n" +
                    "餐桌信息-deskInfo 1 << 3 ,\n" +
                    "POS下单员详情-personInfo 1 << 4 ,\n" +
                    "买家详情-buyerInfo 1 << 5 ,\n" +
                    "店铺商户详情-shopInfo 1 << 6 ,\n" +
                    "POS机详情-posInfo 1 << 7 ,\n" +
                    "支付详情-payInfo 1 << 8 ,\n" +
                    "支付流水-payInfo#payFlowList 1 << 9 ,\n" +
                    "退单详情-refundReasonInfo 1 << 10 ,\n" +
                    "退单流水-refundFlowInfo 1 << 11 ,\n" +
                    "取消详情-cancelReasonInfo 1 << 12) \n获取组合详情情况使用二进制 | 链接，服务端通过二进制 & 运算比较", required = true, type = "Integer")
            @PathVariable(name = "detailFlag") int detailFlag
    ) {
        Result<OrderInfoVO> result;
        try {
            GetOrderInfoParamDTO getOrderInfoParam = new GetOrderInfoParamDTO();
            getOrderInfoParam.setOrderId(orderId);
            getOrderInfoParam.setDetailFlag(detailFlag);
            OrderInfoDTO orderInfoDTO = orderReadFacade.getOrderInfoByOrderId(getOrderInfoParam);
            if (null == orderInfoDTO) {
                result = Result.SUCESS(null);
                return result;
            }

            OrderInfoVO orderInfoVO = CenterUtil.getMapperFactory().getMapperFacade().map(orderInfoDTO, OrderInfoVO.class);
            List<OrderItemVO> orderItems = itemDTOList2VOList(orderInfoDTO.getOrderItems());
            orderInfoVO.setOrderItems(orderItems);
            result = Result.SUCESS(orderInfoVO);
        } catch (OrderException e) {
            if (logger.isErrorEnabled()) {
                logger.error("getOrderInfoByOrderId Exception: ", e);
            }
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }


    @ResponseBody
    @RequestMapping("/getOrderInfoByOrderId4Lmw/{orderId}/{detailFlag}")
    @ApiOperation(value = "订单ID获取订单详情信息：带撩美味的数据：平台补贴，实际进账，闲时描述，平台退款", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<OrderInfoVO> getOrderInfoByOrderId4Lmw(
            @ApiParam(name = "orderId", value = "订单ID", required = true, type = "Long")
            @PathVariable(name = "orderId") Long orderId,
            @ApiParam(name = "detailFlag", value = "参考接口getOrderInfoByOrderId", required = true, type = "Integer")
            @PathVariable(name = "detailFlag") int detailFlag
    ) {
        Result<OrderInfoVO> result = this.getOrderInfoByOrderId(orderId, detailFlag);

        if (null == result) {
            return result;
        }

        OrderInfoVO data = result.getData();
        if (null == data) {
            return result;
        }

        Long circulationAmount = null;
        CirculationTypeEnum circulationType = null;
        String idleStatusDescr = null;
        Long subsidyOrIncomeAmount = 0L;
        Long lmwRestoreAmount = 0L;
        //组装撩美味平台数据
        SupportCouponTimeTypeEnum idleStatus = data.getIdleStatus();
        if (SupportCouponTimeTypeEnum.BUSY_TIME.equals(idleStatus)
                || SupportCouponTimeTypeEnum.IDLE_TIME.equals(idleStatus)) {

            Long id = data.getOrderId();
            List<Long> orderIds = new ArrayList<>();
            orderIds.add(id);
            List<LmwOrderCirculationInfoDTO> dtoList = circulationReadFacade.getCirculationInfoByOrderIds(orderIds);
            if (CollectionUtils.isListNotBlank(dtoList)){
                LmwOrderCirculationInfoDTO dto = dtoList.get(0);

                circulationAmount = dto.getAmount();
                circulationType = dto.getCirculationType();

                if (SupportCouponTimeTypeEnum.BUSY_TIME.equals(idleStatus)) {
                    idleStatusDescr = "";
                    subsidyOrIncomeAmount = data.getCirculationAmount() + data.getPayAmount();

                }

                if (SupportCouponTimeTypeEnum.IDLE_TIME.equals(idleStatus)) {
                    Double platformDiscount = dto.getPlatformDiscount();
                    idleStatusDescr = "（美食闲时时段撩美味平台合约协议拿货折扣 " + platformDiscount + " 折）";
                    subsidyOrIncomeAmount = data.getCirculationAmount();

                }

                lmwRestoreAmount = dto.getRefundAmount();

            }else{

                circulationAmount = 0L;
                circulationType = null;

                if (SupportCouponTimeTypeEnum.BUSY_TIME.equals(idleStatus)) {
                    idleStatusDescr = "";
                    subsidyOrIncomeAmount = circulationAmount + data.getPayAmount();

                }

                if (SupportCouponTimeTypeEnum.IDLE_TIME.equals(idleStatus)) {
                    idleStatusDescr = "（美食闲时时段撩美味平台合约协议拿货折扣 10 折）";
                    subsidyOrIncomeAmount = circulationAmount;

                }

                lmwRestoreAmount = 0L;

                if (logger.isWarnEnabled()) {
                    OrderException exception = new OrderException("订单ID("+ id +")是撩美味订单但是用的是现金或者其他方式结账，所以没有分账信息！");
                    logger.warn(exception.getMessage(), exception);
                }
            }

            data.setCirculationAmount(circulationAmount);
            data.setCirculationType(circulationType);
            data.setIdleStatusDescr(idleStatusDescr);
            data.setSubsidyOrIncomeAmount(subsidyOrIncomeAmount);
            data.setLmwRestoreAmount(lmwRestoreAmount);
        }

        return result;
    }

    @ResponseBody
    @RequestMapping("/getOrderInfoByOrderIds")
    @ApiOperation(value = "订单ID获取订单详情信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<OrderInfoVO>> getOrderInfoByOrderIds(
            @ApiParam(name = "orderIds", value = "订单ID", required = true)
            @RequestParam(name = "orderIds", required = true) Long[] orderIds,
            @ApiParam(name = "detailFlag", value = "订单详情拆分成(\n" +
                    "ALL-Integer.MaxValue=2147483647,\n" +
                    "基础信息-Base 1 << 0 ,\n" +
                    "订单物品-orderItems 1 << 1,\n" +
                    "优惠详情-discounts 1 << 2,\n" +
                    "餐桌信息-deskInfo 1 << 3 ,\n" +
                    "POS下单员详情-personInfo 1 << 4 ,\n" +
                    "买家详情-buyerInfo 1 << 5 ,\n" +
                    "店铺商户详情-shopInfo 1 << 6 ,\n" +
                    "POS机详情-posInfo 1 << 7 ,\n" +
                    "支付详情-payInfo 1 << 8 ,\n" +
                    "支付流水-payInfo#payFlowList 1 << 9 ,\n" +
                    "退单详情-refundReasonInfo 1 << 10 ,\n" +
                    "退单流水-refundFlowInfo 1 << 11 ,\n" +
                    "取消详情-cancelReasonInfo 1 << 12) \n获取组合详情情况使用二进制 | 链接，服务端通过二进制 & 运算比较", required = true, type = "Integer")
            @RequestParam(name = "detailFlag", required = true, defaultValue = "2147483647") int detailFlag
    ) {
        Result<List<OrderInfoVO>> result;
        try {
            if (orderIds == null || orderIds.length <= 0) {
                if (logger.isErrorEnabled()) {
                    logger.error("订单id数组为空");
                }
            }

            List<OrderInfoDTO> dtoList = orderReadFacade.getOrderInfoByOrderIds(orderIds, detailFlag);
            if (CollectionUtils.isListNotBlank(dtoList)) {
                List<OrderInfoVO> voList = new ArrayList<OrderInfoVO>();
                for (OrderInfoDTO dto : dtoList) {
                    OrderInfoVO orderInfoVO = CenterUtil.getMapperFactory().getMapperFacade().map(dto, OrderInfoVO.class);
                    List<OrderItemVO> orderItems = itemDTOList2VOList(dto.getOrderItems());
                    orderInfoVO.setOrderItems(orderItems);
                    voList.add(orderInfoVO);
                }
                result = Result.SUCESS(voList);
            } else {
                result = Result.SUCESS(null);
                return result;
            }
        } catch (OrderException e) {
            if (logger.isErrorEnabled()) {
                logger.error("getOrderInfoByOrderIds Exception: ", e);
            }
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/getOrderInfoByOrderId4Scan/{orderId}/{detailFlag}")
    @ApiOperation(value = "订单ID获取订单详情信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<OrderInfoVO> getOrderInfoByOrderId4Scan(
            @ApiParam(name = "orderId", value = "订单ID", required = true, type = "Long")
            @PathVariable(name = "orderId") Long orderId,
            @ApiParam(name = "detailFlag", value = "订单详情拆分成(" +
                    "ALL-Integer.MaxValue=2147483647," +
                    "基础信息-Base 1 << 0 ," +
                    "订单物品-orderItems 1 << 1," +
                    "优惠详情-discounts 1 << 2," +
                    "餐桌信息-deskInfo 1 << 3 ," +
                    "POS下单员详情-personInfo 1 << 4 ," +
                    "买家详情-buyerInfo 1 << 5 ," +
                    "店铺商户详情-shopInfo 1 << 6 ," +
                    "POS机详情-posInfo 1 << 7 ," +
                    "支付详情-payInfo 1 << 8 ," +
                    "支付流水-payInfo#payFlowList 1 << 9 ," +
                    "退单详情-refundReasonInfo 1 << 10) 获取组合详情情况使用二进制 | 链接，服务端通过二进制 & 运算比较", required = true, type = "Integer")
            @PathVariable(name = "detailFlag") int detailFlag
    ) {
        Result<OrderInfoVO> result;
        try {
            GetOrderInfoParamDTO getOrderInfoParam = new GetOrderInfoParamDTO();
            getOrderInfoParam.setOrderId(orderId);
            getOrderInfoParam.setDetailFlag(detailFlag);
            OrderInfoDTO orderInfoDTO = orderReadFacade.getOrderInfoByOrderId(getOrderInfoParam);
            if (null == orderInfoDTO) {
                result = Result.SUCESS(null);
                return result;
            }

            //【返回】
            Long orderShopId = orderInfoDTO.getShopId();
            Long loginShopId = ThreadLocalContext.getShopId();
            Result<OrderInfoVO> result1 = checkSameShop(orderShopId, loginShopId);
            if (result1 != null) {
                return result1;
            }

            OrderInfoVO orderInfoVO = CenterUtil.getMapperFactory().getMapperFacade().map(orderInfoDTO, OrderInfoVO.class);
            List<OrderItemVO> orderItems = itemDTOList2VOList(orderInfoDTO.getOrderItems());
            orderInfoVO.setOrderItems(orderItems);
            result = Result.SUCESS(orderInfoVO);
        } catch (OrderException e) {
            if (logger.isErrorEnabled()) {
                logger.error("getOrderInfoByOrderId4Scan Exception: ", e);
            }
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }

    /**
     * 检查是否相同店铺
     *
     * @return com.lizikj.api.vo.Result<com.lizikj.api.vo.order.OrderInfoVO>
     * @params [orderShopId, loginShopId]
     * @author zhoufe
     * @date 2017/11/11 21:53
     */
    private Result<OrderInfoVO> checkSameShop(Long orderShopId, Long loginShopId) {
        Result<OrderInfoVO> result;
        if (!loginShopId.equals(orderShopId)) {
            if (logger.isWarnEnabled()) {
                OrderException orderException = new OrderException(OrderErrorEnum.UN_PASSED_ARGUMENT, String.format("登录的店铺ID(%s)与订单店铺ID(%s)不同", orderShopId, loginShopId));
                logger.warn("不是该店铺的订单", orderException);
            }

            result = Result.SUCESS(null);
            return result;
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/getOrderInfoByOrderNo/{orderNo}/{detailFlag}")
    @ApiOperation(value = "订单号获取订单详情信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<OrderInfoVO> getOrderInfoByOrderNo(
            @ApiParam(name = "orderNo", value = "订单号", required = true, type = "String")
            @PathVariable(name = "orderNo") String orderNo,
            @ApiParam(name = "detailFlag", value = "订单详情拆分成(" +
                    "ALL-Integer.MaxValue=2147483647," +
                    "基础信息-Base 1 << 0 ," +
                    "订单物品-orderItems 1 << 1," +
                    "优惠详情-discounts 1 << 2," +
                    "餐桌信息-deskInfo 1 << 3 ," +
                    "POS下单员详情-personInfo 1 << 4 ," +
                    "买家详情-buyerInfo 1 << 5 ," +
                    "店铺商户详情-shopInfo 1 << 6 ," +
                    "POS机详情-posInfo 1 << 7 ," +
                    "支付详情-payInfo 1 << 8 ," +
                    "支付流水-payInfo#payFlowList 1 << 9 ," +
                    "退单详情-refundReasonInfo 1 << 10) 获取组合详情情况使用二进制 | 链接，服务端通过二进制 & 运算比较", required = true, type = "Integer")
            @PathVariable(name = "detailFlag") int detailFlag
    ) {

        Result<OrderInfoVO> result;
        try {
            GetOrderInfoParamDTO getOrderInfoParam = new GetOrderInfoParamDTO();
            getOrderInfoParam.setOrderNo(orderNo);
            getOrderInfoParam.setDetailFlag(detailFlag);
            OrderInfoDTO orderInfoDTO = orderReadFacade.getOrderInfoByOrderNo(getOrderInfoParam);
            if (null == orderInfoDTO) {
                return Result.SUCESS(null);
            }

            OrderInfoVO orderInfoVO = CenterUtil.getMapperFactory().getMapperFacade().map(orderInfoDTO, OrderInfoVO.class);
            List<OrderItemVO> orderItems = itemDTOList2VOList(orderInfoDTO.getOrderItems());
            orderInfoVO.setOrderItems(orderItems);
            result = Result.SUCESS(orderInfoVO);
        } catch (OrderException e) {
            if (logger.isErrorEnabled()) {
                logger.error("getOrderInfoByOrderNo Exception: ", e);
            }
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    @ResponseBody
    @RequestMapping("/getStatusByOrderNo/{orderNo}")
    @ApiOperation(value = "订单号获取订单的各个状态", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<OrderInfoStatusVO> getStatusByOrderNo(
            @ApiParam(name = "orderNo", value = "订单号", required = true, type = "String")
            @PathVariable(name = "orderNo") String orderNo
    ) {

        Result<OrderInfoStatusVO> result;
        try {
            GetOrderInfoParamDTO getOrderInfoParam = new GetOrderInfoParamDTO();
            getOrderInfoParam.setOrderNo(orderNo);
            getOrderInfoParam.setDetailFlag(OrderInfoDetailEnum.BASE.getCode());
            OrderInfoDTO orderInfoDTO = orderReadFacade.getOrderInfoByOrderNo(getOrderInfoParam);
            if (null == orderInfoDTO) {
                return Result.SUCESS(null);
            }

            OrderInfoStatusVO orderInfoStatusVO = new OrderInfoStatusVO();
            orderInfoStatusVO.setOrderStatus(orderInfoDTO.getOrderStatus());
            orderInfoStatusVO.setPayStatus(orderInfoDTO.getPayStatus());
            orderInfoStatusVO.setRefundStatus(orderInfoDTO.getRefundStatus());


            result = Result.SUCESS(orderInfoStatusVO);
        } catch (OrderException e) {
            if (logger.isErrorEnabled()) {
                logger.error("getStatusByOrderNo Exception: ", e);
            }
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }


    @ResponseBody
    @RequestMapping("/listOrderInfo")
    @ApiOperation(value = "获取订单列表信息", notes = "获取订单列表信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PageInfo<OrderInfoVO>> listOrderInfo(
            @ApiParam(name = "queryParam", value = "queryParam", required = true)
            @NotNull
            @RequestBody OrderInfoQueryParamVO queryParam
    ) {
        Result<PageInfo<OrderInfoVO>> result = null;
        try {

            if (null == queryParam) {
                throw new OrderException(OrderErrorEnum.UN_PASSED_ARGUMENT, "传入参数为空");
            }
            OrderInfoQueryParamDTO queryParamDTO = ObjectConvertUtil.map(queryParam, OrderInfoQueryParamDTO.class);

            Long shopIdParam = queryParamDTO.getShopId();
            if (isLongNull(shopIdParam)) {
                Long shopId = ThreadLocalContext.getShopId();
                queryParamDTO.setShopId(shopId);
            }

            PageInfo<OrderInfoDTO> orderInfoDTOPageInfo = orderReadFacade.query(queryParamDTO, queryParam.getCurrentPage(), queryParam.getPageSize());
            PageInfo<OrderInfoVO> orderInfoVoPageInfo = new PageInfo<>();
            if (orderInfoDTOPageInfo != null) {
                BeanUtils.copyProperties(orderInfoVoPageInfo, orderInfoDTOPageInfo);
                List<OrderInfoVO> infoVOList = ObjectConvertUtil.mapList(orderInfoDTOPageInfo.getList(), OrderInfoDTO.class, OrderInfoVO.class);
                orderInfoVoPageInfo.setList(infoVOList);
            }
            result = Result.SUCESS(orderInfoVoPageInfo);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("listOrderInfo Exception: ", e);
            }
            result = Result.FAILURE(e.getMessage());
        } finally {
            return result;
        }
    }


    @ResponseBody
    @RequestMapping("/listOrderInfo4Detail")
    @ApiOperation(value = "获取订单列表信息：带订单详情", notes = "获取订单列表信息：带订单详情", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PageInfo<OrderInfoVO>> listOrderInfo4Detail(
            @ApiParam(name = "queryParam", value = "queryParam", required = true)
            @NotNull
            @RequestBody OrderInfoQueryParamVO queryParam
    ) {
        Result<PageInfo<OrderInfoVO>> result = null;
        try {

            if (null == queryParam) {
                throw new OrderException(OrderErrorEnum.UN_PASSED_ARGUMENT, "传入参数为空");
            }
            OrderInfoQueryParamDTO queryParamDTO = ObjectConvertUtil.map(queryParam, OrderInfoQueryParamDTO.class);

            Long shopIdParam = queryParamDTO.getShopId();
            if (isLongNull(shopIdParam)) {
                Long shopId = ThreadLocalContext.getShopId();
                queryParamDTO.setShopId(shopId);
            }

            PageInfo<OrderInfoDTO> orderInfoDTOPageInfo = orderReadFacade.query4Detail(queryParamDTO, queryParam.getCurrentPage(), queryParam.getPageSize());
            PageInfo<OrderInfoVO> orderInfoVoPageInfo = new PageInfo<>();
            if (orderInfoDTOPageInfo != null) {
                BeanUtils.copyProperties(orderInfoVoPageInfo, orderInfoDTOPageInfo);
                List<OrderInfoVO> infoVOList = infoDTOList2VOList(orderInfoDTOPageInfo.getList());
                orderInfoVoPageInfo.setList(infoVOList);
            }
            result = Result.SUCESS(orderInfoVoPageInfo);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("listOrderInfo4Detail Exception: ", e);
            }
            result = Result.FAILURE(e.getMessage());
        } finally {
            return result;
        }
    }


    @ResponseBody
    @RequestMapping("/listOrderInfo4Lmw")
    @ApiOperation(value = "获取订单列表信息：带平台补贴金额。用里面的订单号/订单ID请求详情接口", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PageInfo<OrderInfoVO>> listOrderInfo4Lmw(
            @ApiParam(name = "queryParam", value = "queryParam", required = true)
            @NotNull
            @RequestBody OrderInfoQueryParamVO queryParam
    ) {

        if (null == queryParam){
            throw new OrderException("传入参数为空");
        }

        queryParam.setOrderSource(OrderSourceEnum.LMW);

        Result<PageInfo<OrderInfoVO>> result = this.listOrderInfo(queryParam);

        if (null == result) {
            return result;
        }

        PageInfo<OrderInfoVO> data = result.getData();
        if (null == data) {
            return result;
        }

        List<OrderInfoVO> voList = data.getList();
        if (CollectionUtils.isListNotBlank(voList)) {
            List<Long> orderIds = new ArrayList<>();
            voList.forEach(vo -> {
                Long orderId = vo.getOrderId();
                orderIds.add(orderId);
            });


            if (CollectionUtils.isListNotBlank(orderIds)) {
                List<LmwOrderCirculationInfoDTO> circulationList = circulationReadFacade.getCirculationInfoByOrderIds(orderIds);
                Map<Long, LmwOrderCirculationInfoDTO> circulationMap = new HashMap<>();
                if (CollectionUtils.isListNotBlank(circulationList)) {
                    circulationList.forEach(dto -> {
                        circulationMap.put(dto.getOrderId(), dto);
                    });
                }

                if (circulationMap != null && !circulationMap.isEmpty()) {
                    voList.forEach(vo -> {
                        Long orderId = vo.getOrderId();
                        LmwOrderCirculationInfoDTO dto = circulationMap.get(orderId);
                        if (dto != null) {
                            vo.setCirculationAmount(dto.getAmount());
                            vo.setCirculationType(dto.getCirculationType());
                            vo.setLmwRestoreAmount(dto.getRefundAmount());
                        }
                    });
                }
            }
        }

        return result;
    }


    @ResponseBody
    @RequestMapping("/getCirculationSummary")
    @ApiOperation(value = "获取撩美味平台汇总数据", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<LmwOrderStatisticsSummaryVO> getCirculationSummary(
            @ApiParam(name = "summaryParamVO", value = "summaryParamVO", required = true)
            @NotNull
            @RequestBody LmwOrderStatisticsSummaryVO summaryParamVO
    ) {
        Result<LmwOrderStatisticsSummaryVO> result;

        try {
            Date startCreateTime = summaryParamVO.getStartCreateTime();
            Date endCreateTime = summaryParamVO.getEndCreateTime();

            if (null == startCreateTime) {
                throw new OrderException("获取撩美味平台汇总数据的开始时间不能为空！");
            }

            if (null == endCreateTime) {
                throw new OrderException("获取撩美味平台汇总数据的结束时间不能为空！");
            }

            Long shopId = summaryParamVO.getShopId();
            if (isLongNull(shopId)) {
                shopId = ThreadLocalContext.getShopId();
            }

            if (isLongNull(shopId)) {
                throw new OrderException("获取撩美味平台汇总数据的店铺ID不能为空！");
            }

            LmwOrderStatisticsSummaryVO resultSummaryVO = new LmwOrderStatisticsSummaryVO();

            resultSummaryVO.setShopId(shopId);
            resultSummaryVO.setStartCreateTime(startCreateTime);
            resultSummaryVO.setEndCreateTime(endCreateTime);
            OrderSourceEnum orderSource = OrderSourceEnum.LMW;
            LmwOrderStatisticsSummaryDTO summaryDTO = orderReadFacade.getCirculationSummary(shopId, orderSource, startCreateTime, endCreateTime);
            if (summaryDTO != null) {
                resultSummaryVO.setTotalBusyTotalAmount(summaryDTO.getTotalBusyTotalAmount());
                resultSummaryVO.setTotalBusyOrderCount(summaryDTO.getTotalBusyOrderCount());
                resultSummaryVO.setTotalIdleTotalAmount(summaryDTO.getTotalIdleTotalAmount());
                resultSummaryVO.setTotalIdleOrderCount(summaryDTO.getTotalIdleOrderCount());
                resultSummaryVO.setTotalTotalAmount(summaryDTO.getTotalTotalAmount());
                resultSummaryVO.setTotalOrderCount(summaryDTO.getTotalOrderCount());
            }

            ShopCirculationSummaryDTO circulationSummary = circulationReadFacade.getCirculationSummary(shopId, startCreateTime, endCreateTime);
            if (circulationSummary != null) {
                resultSummaryVO.setTotalIdleIncomeAmount(circulationSummary.getIdleIncomeAmount());
                resultSummaryVO.setTotalSubsidyAmount(circulationSummary.getPlatformSubsidyAmount());
                resultSummaryVO.setTotalBusyIncomeAmount(circulationSummary.getBusyIncomeAmount());
            }
            result = Result.SUCESS(resultSummaryVO);
            return result;
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("getCirculationSummary 获取撩美味平台汇总数据错误：", e);
            }
            result = Result.FAILURE(e.getMessage());
            return result;



        }
    }


    @ResponseBody
    @RequestMapping("/listUsedStatisticsRedPacket")
    @ApiOperation(value = "历史红包使用统计", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PageInfo<UsedStatisticsRedPacketVO>> listUsedStatisticsRedPacket(
            @ApiParam(name = "paramVO", value = "paramVO", required = true)
            @NotNull
            @RequestBody UsedStatisticsRedPacketVO paramVO
    ) {
        Result<PageInfo<UsedStatisticsRedPacketVO>> result;

        try {
            Date startCreateTime = paramVO.getStartCreateTime();
            Date endCreateTime = paramVO.getEndCreateTime();

            if (null == startCreateTime) {
                throw new OrderException("历史红包使用统计的开始时间不能为空！");
            }

            if (null == endCreateTime) {
                throw new OrderException("历史红包使用统计的结束时间不能为空！");
            }

            //merchantName shopName mobile 模糊匹配
            MapperFacade mapperFacade = CenterUtil.getMapperFactory().getMapperFacade();
            UsedStatisticsRedPacketDTO paramDTO = mapperFacade.map(paramVO, UsedStatisticsRedPacketDTO.class);
            PageInfo<UsedStatisticsRedPacketDTO> pageDTO = orderReadFacade.listUsedStatisticsRedPacket(paramDTO);
            PageInfo pageVO = pageDTO;
            List<UsedStatisticsRedPacketDTO> dtoList = pageDTO.getList();
            if (CollectionUtils.isListNotBlank(dtoList)){
                List<UsedStatisticsRedPacketVO> voList = mapperFacade.mapAsList(dtoList, UsedStatisticsRedPacketVO.class);
                pageVO.setList(voList);
            }
            result = Result.SUCESS(pageVO);
            return result;
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("listUsedStatisticsRedPacket 历史红包使用统计：", e);
            }
            result = Result.FAILURE(e.getMessage());
            return result;
        }
    }


    @ResponseBody
    @RequestMapping(value = "/saveTempOrderPrice", method = RequestMethod.POST)
    @ApiOperation(value = "保存结账前的计价结果", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> saveTempOrderPrice(
            @ApiParam(name = "paramVO", value = "paramVO", required = true)
            @NotNull
            @RequestBody UsedStatisticsRedPacketVO paramVO
    ) {
        Result<Boolean> result = null;
        String paramVOStr = JsonUtils.toJSONString(paramVO);
        logger.info("UsedStatisticsRedPacketVO:" + paramVOStr);
        try {

            if (null == paramVO){
                throw new OrderException("保存结账前的计价结果参数为空！");
            }
            String orderNo = paramVO.getOrderNo();
            if (StringUtils.isBlank(orderNo)){
                throw new OrderException("保存结账前的计价结果订单号为空！");
            }
            String key = CenterUtil.getSaveTempOrderPriceKey(orderNo);
            cache.set(key, paramVOStr, 7, TimeUnit.DAYS);
            result = Result.SUCESS(true);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("saveTempOrderPrice Exception: ", e);
            }
            if (e instanceof OrderException) {
                result = Result.FAILURE(((OrderException) e).getCode(), e.getMessage());
            } else {
                result = Result.FAILURE(e.getMessage());
            }
        }
        return result;
    }


    @ResponseBody
    @RequestMapping("/listUsedStatisticsCoupon")
    @ApiOperation(value = "历史优惠券使用统计", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PageInfo<UsedStatisticsCouponVO>> listUsedStatisticsCoupon(
            @ApiParam(name = "paramVO", value = "paramVO", required = true)
            @NotNull
            @RequestBody UsedStatisticsRedPacketVO paramVO
    ) {
        Result<PageInfo<UsedStatisticsCouponVO>> result;

        try {
            Date startCreateTime = paramVO.getStartCreateTime();
            Date endCreateTime = paramVO.getEndCreateTime();

            if (null == startCreateTime) {
                throw new OrderException("历史优惠券使用统计的开始时间不能为空！");
            }

            if (null == endCreateTime) {
                throw new OrderException("历史优惠券使用统计的结束时间不能为空！");
            }

            //merchantName shopName mobile 模糊匹配
            MapperFacade mapperFacade = CenterUtil.getMapperFactory().getMapperFacade();
            UsedStatisticsCouponDTO paramDTO = mapperFacade.map(paramVO, UsedStatisticsCouponDTO.class);
            PageInfo<UsedStatisticsCouponDTO> pageDTO = orderReadFacade.listUsedStatisticsCoupon(paramDTO);
            PageInfo pageVO = pageDTO;
            List<UsedStatisticsCouponDTO> dtoList = pageDTO.getList();
            if (CollectionUtils.isListNotBlank(dtoList)){
                List<UsedStatisticsCouponVO> voList = mapperFacade.mapAsList(dtoList, UsedStatisticsCouponVO.class);
                pageVO.setList(voList);
            }
            result = Result.SUCESS(pageVO);
            return result;
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("listUsedStatisticsCoupon 历史优惠券使用统计错误：", e);
            }
            result = Result.FAILURE(e.getMessage());
            return result;
        }
    }






    @ResponseBody
    @RequestMapping("/countOrderStatus")
    @ApiOperation(value = "获取订单状态统计信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<OrderInfoStatusCountVO>> countOrderStatus() {
        Result<List<OrderInfoStatusCountVO>> result = null;
        try {

            OrderInfoQueryParamDTO queryParamDTO = new OrderInfoQueryParamDTO();
            //只能查询自己店铺的或者查询pos自己的
            //String did = ThreadLocalContext.getDid();
            //if (StringUtils.isNotBlank(did)){
            //先屏蔽
            //queryParamDTO.setSnNum(did);
            //}else{
            Long shopId = ThreadLocalContext.getShopId();
            queryParamDTO.setShopId(shopId);
            //}

            List<OrderInfoStatusCountDTO> orderInfoStatusCountDTOS = orderReadFacade.countOrderStatus(queryParamDTO);
            if (CollectionUtils.isListBlank(orderInfoStatusCountDTOS)) {
                result = Result.SUCESS(null);
            }
            List<OrderInfoStatusCountVO> orderInfoStatusCountVOS = ObjectConvertUtil.mapList(orderInfoStatusCountDTOS, OrderInfoStatusCountDTO.class, OrderInfoStatusCountVO.class);
            result = Result.SUCESS(orderInfoStatusCountVOS);
        } catch (OrderException e) {
            if (logger.isErrorEnabled()) {
                logger.error("countOrderStatus Exception: ", e);
            }
            result = Result.FAILURE(e.getCode(), e.getMessage());
        } finally {
            return result;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/calculatePrice", method = RequestMethod.POST)
    @ApiOperation(value = "计算订单价格", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<OrderInfoVO> calculatePrice(
            @ApiParam(name = "paramVO", value = "paramVO", required = true)
            @NotNull
            @RequestBody OrderInfoParamVO orderInfoParamVO
    ) {
        Result<OrderInfoVO> result = null;
        logger.info("orderInfoParamVO:" + JsonUtils.toJSONString(orderInfoParamVO));
        try {
            OrderInfoParamDTO orderInfoParamDTO = infoVO2DTO(orderInfoParamVO);
            //设置登录相关参数
            orderInfoParamDTO.setShopId(ThreadLocalContext.getShopId());

            if (OrderSourceEnum.isH5ScEnum(orderInfoParamVO.getOrderSource()) ||
                    OrderSourceEnum.QR_CODE.equals(orderInfoParamVO.getOrderSource())) {
                orderInfoParamDTO.setMemberId(ThreadLocalContext.getMemberId());
                orderInfoParamDTO.setUserId(ThreadLocalContext.getUserId());
                if (orderInfoParamDTO.getMerchantMemberId() == null || orderInfoParamDTO.getMerchantMemberId().longValue() <= 0) {
                    orderInfoParamDTO.setMerchantMemberId(ThreadLocalContext.getMerchantMemberId());
                }
            } else {
                orderInfoParamDTO.setOrderPersonId(ThreadLocalContext.getStaffId());
                orderInfoParamDTO.setSnNum(ThreadLocalContext.getDid());
                //orderInfoParamDTO.setMerchantMemberId();//infoVO2DTO(orderInfoParamVO)里面传
            }
            OrderInfoDTO orderInfoDTO = orderReadFacade.calculatePrice(orderInfoParamDTO);
            OrderInfoVO orderInfoVO = ObjectConvertUtil.map(orderInfoDTO, OrderInfoVO.class);
            List<OrderItemVO> orderItems = itemDTOList2VOList(orderInfoDTO.getOrderItems());
            orderInfoVO.setOrderItems(orderItems);
            result = Result.SUCESS(orderInfoVO);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("calculatePrice Exception: ", e);
            }
            if (e instanceof OrderException) {
                result = Result.FAILURE(((OrderException) e).getCode(), e.getMessage());
            } else {
                result = Result.FAILURE(e.getMessage());
            }
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    @ApiOperation(value = "新增订单信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<OrderInfoVO> addOrder(
            @ApiParam(name = "addParamVO", value = "addParamVO", required = true)
            @RequestBody OrderInfoParamVO orderInfoParamVO) {
        logger.info("orderInfoParamVO:" + JsonUtils.toJSONString(orderInfoParamVO));
        Result<OrderInfoVO> result = null;
        try {
            OrderInfoParamDTO orderInfoParamDTO = getAddOrderOrderInfoParamDTO(orderInfoParamVO);
            OrderInfoDTO orderInfoDTO = orderWriteFacade.addOrder(orderInfoParamDTO);
            OrderInfoVO orderInfoVO = ObjectConvertUtil.map(orderInfoDTO, OrderInfoVO.class);

            List<OrderItemVO> orderItems = itemDTOList2VOList(orderInfoDTO.getOrderItems());
            orderInfoVO.setOrderItems(orderItems);

            result = Result.SUCESS(orderInfoVO);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("addOrder Exception: ", e);
            }
            if (e instanceof OrderException) {
                result = Result.FAILURE(((OrderException) e).getCode(), e.getMessage());
            } else {
                result = Result.FAILURE(e.getMessage());
            }
        }

        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/savePendingOrder", method = RequestMethod.POST)
    @ApiOperation(value = "新增或者更新待下订单信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PendingOrderInfoVO> savePendingOrder(
            @ApiParam(name = "pendingParamVO", value = "pendingParamVO", required = true)
            @RequestBody PendingOrderInfoVO pendingOrderInfoVO) {
        logger.info("pending pendingOrderInfoVO:" + JsonUtils.toJSONString(pendingOrderInfoVO));
        Result<PendingOrderInfoVO> result = null;
        try {

            if(null == pendingOrderInfoVO){
                throw new OrderException("传入参数为空");
            }

            OrderInfoParamVO orderInfoParamVO = pendingOrderInfoVO.getPendingAddOrderParam();
            if (null == orderInfoParamVO){
                throw new OrderException("下单参数为空");
            }

            OrderInfoParamDTO orderInfoParamDTO = getAddOrderOrderInfoParamDTO(orderInfoParamVO);

            MapperFacade mapperFacade = CenterUtil.getMapperFactory().getMapperFacade();
            PendingOrderInfoDTO paramDTO = mapperFacade.map(pendingOrderInfoVO, PendingOrderInfoDTO.class);
            paramDTO.setPendingAddOrderParam(orderInfoParamDTO);
            paramDTO.setShopId(orderInfoParamDTO.getShopId());
            paramDTO.setMemberId(orderInfoParamDTO.getMemberId());
            paramDTO.setUserId(orderInfoParamDTO.getUserId());
            PendingOrderInfoDTO pendingOrderInfoDTO = pendingOrderInfoWriteFacade.savePendingOrder(paramDTO);

            PendingOrderInfoVO vo = pendingOrderDtoToVo(pendingOrderInfoDTO);

            result = Result.SUCESS(vo);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("savePendingOrder Exception: ", e);
            }
            if (e instanceof OrderException) {
                result = Result.FAILURE(((OrderException) e).getCode(), e.getMessage());
            } else {
                result = Result.FAILURE(e.getMessage());
            }
        }

        return result;
    }

    /**
     * 待下单 dto 转 vo
     *
     * @return com.lizikj.api.vo.order.PendingOrderInfoVO
     * @params [pendingOrderInfoDTO]
     * @author zhoufe
     * @date 2018/8/22 15:24
     */
    private PendingOrderInfoVO pendingOrderDtoToVo(PendingOrderInfoDTO pendingOrderInfoDTO) {
        if (null == pendingOrderInfoDTO) {
            return null;
        }

        MapperFacade mapperFacade = CenterUtil.getMapperFactory().getMapperFacade();
        PendingOrderInfoVO pendingOrderInfoVO = mapperFacade.map(pendingOrderInfoDTO, PendingOrderInfoVO.class);
        OrderInfoDTO orderInfoDTO = pendingOrderInfoDTO.getOrderInfo();
        if (orderInfoDTO != null) {
            List<OrderItemVO> orderItemVOS = itemDTOList2VOList(orderInfoDTO.getOrderItems());
            OrderInfoVO orderInfoVO = mapperFacade.map(orderInfoDTO, OrderInfoVO.class);
            if (orderInfoVO != null) {
                orderInfoVO.setOrderItems(orderItemVOS);
                pendingOrderInfoVO.setOrderInfo(orderInfoVO);
            }
        }

        OrderInfoParamDTO pendingAddOrderParamDTO = pendingOrderInfoDTO.getPendingAddOrderParam();
        if(pendingAddOrderParamDTO != null){
            OrderInfoParamVO orderInfoParamVO = mapperFacade.map(pendingAddOrderParamDTO, OrderInfoParamVO.class);
            pendingOrderInfoVO.setPendingAddOrderParam(orderInfoParamVO);
        }

        return pendingOrderInfoVO;
    }

    @ResponseBody
    @RequestMapping(value = "/listPendingOrder", method = RequestMethod.POST)
    @ApiOperation(value = "查询待下订单信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<PendingOrderInfoVO>> listPendingOrder(
            @ApiParam(name = "pendingParamVO", value = "pendingParamVO", required = true)
            @RequestBody PendingOrderInfoVO pendingOrderInfoVO) {
        logger.info("pending pendingOrderInfoVO:" + JsonUtils.toJSONString(pendingOrderInfoVO));
        Result<List<PendingOrderInfoVO>> result = null;
        try {
            MapperFacade mapperFacade = CenterUtil.getMapperFactory().getMapperFacade();
            PendingOrderInfoDTO pendingOrderInfoParamDTO = mapperFacade.map(pendingOrderInfoVO, PendingOrderInfoDTO.class);
            List<PendingOrderInfoDTO> pendingOrderInfoDTOS = pendingOrderInfoReadFacade.listPendingOrder(pendingOrderInfoParamDTO);
            if (CollectionUtils.isListBlank(pendingOrderInfoDTOS)) {
                result = Result.SUCESS(null);
                return result;
            }

            List<PendingOrderInfoVO> pendingOrderInfoVOS = new ArrayList<>();
            for (PendingOrderInfoDTO dto : pendingOrderInfoDTOS) {
                PendingOrderInfoVO vo = pendingOrderDtoToVo(dto);
                if (null == vo) {
                    continue;
                }
                pendingOrderInfoVOS.add(vo);
            }

            result = Result.SUCESS(pendingOrderInfoVOS);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("listPendingOrder Exception: ", e);
            }
            if (e instanceof OrderException) {
                result = Result.FAILURE(((OrderException) e).getCode(), e.getMessage());
            } else {
                result = Result.FAILURE(e.getMessage());
            }
        }

        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/listPendingOrderSimple", method = RequestMethod.POST)
    @ApiOperation(value = "查询待下订单的简易信息：至少需要传入待下单状态", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<PendingOrderInfoVO>> listPendingOrderSimple(
            @ApiParam(name = "pendingParamVO", value = "pendingParamVO", required = true)
            @RequestBody PendingOrderInfoVO pendingOrderInfoVO) {
        logger.info("pending pendingOrderInfoVO:" + JsonUtils.toJSONString(pendingOrderInfoVO));
        Result<List<PendingOrderInfoVO>> result = null;
        try {
            MapperFacade mapperFacade = CenterUtil.getMapperFactory().getMapperFacade();
            PendingOrderInfoDTO pendingOrderInfoParamDTO = mapperFacade.map(pendingOrderInfoVO, PendingOrderInfoDTO.class);

            if(null == pendingOrderInfoParamDTO){
                throw new OrderException("传入的参数为空");
            }

            Long userId = pendingOrderInfoParamDTO.getUserId();
            if (isLongNull(userId)){
                userId = ThreadLocalContext.getUserId();
                pendingOrderInfoParamDTO.setUserId(userId);
            }

            List<PendingOrderInfoDTO> pendingOrderInfoDTOS = pendingOrderInfoReadFacade.listPendingOrderSimple(pendingOrderInfoParamDTO);
            if (CollectionUtils.isListBlank(pendingOrderInfoDTOS)) {
                result = Result.SUCESS(null);
                return result;
            }

            List<PendingOrderInfoVO> pendingOrderInfoVOS = mapperFacade.mapAsList(pendingOrderInfoDTOS, PendingOrderInfoVO.class);
            result = Result.SUCESS(pendingOrderInfoVOS);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("listPendingOrderSimple Exception: ", e);
            }
            if (e instanceof OrderException) {
                result = Result.FAILURE(((OrderException) e).getCode(), e.getMessage());
            } else {
                result = Result.FAILURE(e.getMessage());
            }
        }

        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/findPendingOrderById", method = RequestMethod.GET)
    @ApiOperation(value = "根据ID查询待下订单信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PendingOrderInfoVO> findPendingOrderById(
            @ApiParam(name = "pendingOrderInfoId", value = "pendingOrderInfoId", required = true)
            @RequestParam Long pendingOrderInfoId) {
        Result<PendingOrderInfoVO> result = null;
        try {

            if (isLongNull(pendingOrderInfoId)) {
                throw new OrderException("传入的待下单的ID为空");
            }

            PendingOrderInfoDTO pendingOrderInfoDTO = pendingOrderInfoReadFacade.findById(pendingOrderInfoId);

            PendingOrderInfoVO pendingOrderInfoVO = pendingOrderDtoToVo(pendingOrderInfoDTO);

            result = Result.SUCESS(pendingOrderInfoVO);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("findPendingOrderById Exception: ", e);
            }
            if (e instanceof OrderException) {
                result = Result.FAILURE(((OrderException) e).getCode(), e.getMessage());
            } else {
                result = Result.FAILURE(e.getMessage());
            }
        }

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/cancelPendingOrder", method = RequestMethod.GET)
    @ApiOperation(value = "根据ID取消待下订单信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> cancelPendingOrder(
            @ApiParam(name = "pendingOrderInfoId", value = "pendingOrderInfoId", required = true)
            @RequestParam Long pendingOrderInfoId) {
        Result<Boolean> result = null;
        try {

            if (isLongNull(pendingOrderInfoId)) {
                throw new OrderException("取消时，传入的待下单的ID为空");
            }

            Boolean b = pendingOrderInfoWriteFacade.cancelPendingOrder(pendingOrderInfoId);

            result = Result.SUCESS(b);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("cancelPendingOrder Exception: ", e);
            }
            if (e instanceof OrderException) {
                result = Result.FAILURE(((OrderException) e).getCode(), e.getMessage());
            } else {
                result = Result.FAILURE(e.getMessage());
            }
        }

        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/pendingOrderToAddOrder", method = RequestMethod.POST)
    @ApiOperation(value = "扫码桌台二维码确定桌台ID，确认下单，把待下单变成真正的下单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PendingOrderInfoVO> pendingOrderToAddOrder(
            @ApiParam(name = "pendingParamVO", value = "pendingParamVO", required = true)
            @RequestBody PendingOrderInfoVO pendingOrderInfoVO) {
        Result<PendingOrderInfoVO> result = null;
        try {

            if (null == pendingOrderInfoVO) {
                throw new OrderException("传入参数为空");
            }

            Long pendingOrderInfoId = pendingOrderInfoVO.getPendingOrderInfoId();
            if (isLongNull(pendingOrderInfoId)) {
                throw new OrderException("传入待下单ID参数为空");

            }

            MapperFacade mapperFacade = CenterUtil.getMapperFactory().getMapperFacade();
            PendingOrderInfoDTO pendingOrderInfoDTO = mapperFacade.map(pendingOrderInfoVO, PendingOrderInfoDTO.class);
            //统一从头部取
            pendingOrderInfoDTO.setShopId(ThreadLocalContext.getShopId());

            //模拟下单addOrder下单参数
            OrderInfoParamVO orderInfoParamVO = pendingOrderInfoVO.getPendingAddOrderParam();
            OrderInfoParamDTO orderInfoParamDTO = getAddOrderOrderInfoParamDTO(orderInfoParamVO);
            pendingOrderInfoDTO.setPendingAddOrderParam(orderInfoParamDTO);

            PendingOrderInfoDTO dto = pendingOrderInfoWriteFacade.pendingOrderToAddOrder(pendingOrderInfoDTO);

            if (null == dto) {
                result = Result.SUCESS(null);
                return result;
            }

            PendingOrderInfoVO vo = mapperFacade.map(dto, PendingOrderInfoVO.class);

            OrderInfoDTO orderInfoDTO = dto.getOrderInfo();
            if (orderInfoDTO != null) {
                OrderInfoVO orderInfoVO = mapperFacade.map(orderInfoDTO, OrderInfoVO.class);
                List<OrderItemVO> orderItems = itemDTOList2VOList(orderInfoDTO.getOrderItems());
                orderInfoVO.setOrderItems(orderItems);
                vo.setOrderInfo(orderInfoVO);
            }

            result = Result.SUCESS(vo);

        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("pendingOrderToAddOrder Exception: ", e);
            }
            if (e instanceof OrderException) {
                result = Result.FAILURE(((OrderException) e).getCode(), e.getMessage());
            } else {
                result = Result.FAILURE(e.getMessage());
            }
        }

        return result;
    }

    /**
     * 下单的请求参数
     *
     * @return com.lizikj.order.dto.param.OrderInfoParamDTO
     * @params [orderInfoParamVO]
     * @author zhoufe
     * @date 2018/8/22 15:13
     */
    private OrderInfoParamDTO getAddOrderOrderInfoParamDTO(
            @ApiParam(name = "pendingParamVO", value = "pendingParamVO", required = true)
            @RequestBody OrderInfoParamVO orderInfoParamVO) {
        if (orderInfoParamVO == null) {
            throw new OrderException(OrderErrorEnum.MISSING_REQUIRED_ARGUMENT, "传入参数为空");
        }

        OrderInfoParamDTO orderInfoParamDTO = infoVO2DTO(orderInfoParamVO);
        Long shopId = ThreadLocalContext.getShopId();
        orderInfoParamDTO.setShopId(shopId);

        if (OrderSourceEnum.isH5ScEnum(orderInfoParamVO.getOrderSource()) ||
                OrderSourceEnum.QR_CODE.equals(orderInfoParamVO.getOrderSource())) {
            orderInfoParamDTO.setMemberId(ThreadLocalContext.getMemberId());
            orderInfoParamDTO.setUserId(ThreadLocalContext.getUserId());
            orderInfoParamDTO.setMerchantMemberId(ThreadLocalContext.getMerchantMemberId());
            orderInfoParamDTO.setOrderUpdateSource(OrderUpdateSourceEnum.H5);

        } else {
            orderInfoParamDTO.setOrderPersonId(ThreadLocalContext.getStaffId());
            orderInfoParamDTO.setSnNum(ThreadLocalContext.getDid());
            orderInfoParamDTO.setOrderUpdateSource(OrderUpdateSourceEnum.POS);

        }


        orderInfoParamDTO.setOrderBizType(OrderBizTypeEnum.MERCHANDISE);
        return orderInfoParamDTO;
    }


    @ResponseBody
    @RequestMapping(value = "/appendOrder", method = RequestMethod.POST)
    @ApiOperation(value = "追加订单：加减菜，赠菜", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<OrderInfoVO> appendOrder(
            @ApiParam(name = "appendParamVO", value = "appendParamVO", required = true)
            @NotNull
            @RequestBody OrderInfoParamVO orderInfoParamVO) {
        Result<OrderInfoVO> result = null;
        try {
            logger.info("appendParamVO:" + JsonUtils.toJSONString(orderInfoParamVO));
            OrderInfoParamDTO orderInfoParamDTO = infoVO2DTO(orderInfoParamVO);

            long merchantId = ThreadLocalContext.getMerchantId();
            Byte loginSource = ThreadLocalContext.getLoginSource();

            if (UserLoginSourceEnum.POS.getValue().equals(loginSource)) {
                orderInfoParamDTO.setOrderUpdateSource(OrderUpdateSourceEnum.POS);
                long staffId = ThreadLocalContext.getStaffId();

                if (!StringUtils.isBlank(orderInfoParamVO.getAuthorCode())) {
                    if (CollectionUtils.isListNotBlank(orderInfoParamDTO.getRemoveOrderItemIds())) {
                        if (StringUtils.isNotEmpty(orderInfoParamVO.getAuthorCode())) {
                            // 有要删除的菜品
                            if (!shopAuthCodeReadFacade.verifyAuthCodeTwice(merchantId, orderInfoParamVO.getAuthorCode(), staffId, AuthOperationEnum.REMOVE_DISH)) {
                                return Result.FAILURE("授权码错误或已失效!");
                            }
                        }

                    } else if (CollectionUtils.isListNotBlank(orderInfoParamDTO.getItemParamList())) {
                        if (StringUtils.isNotEmpty(orderInfoParamVO.getAuthorCode())) {
                            // 有要修改或增加的菜
                            boolean appendResult = shopAuthCodeReadFacade.verifyAuthCodeTwice(merchantId, orderInfoParamVO.getAuthorCode(), staffId, AuthOperationEnum.APPEND_DISH);
                            boolean freeResult = shopAuthCodeReadFacade.verifyAuthCodeTwice(merchantId, orderInfoParamVO.getAuthorCode(), staffId, AuthOperationEnum.FREE_DISH);
                            if (!appendResult && !freeResult) {
                                return Result.FAILURE("授权码错误或已失效!");
                            }
                        }
                    }
                }

                AuthShopStaffDTO authShopStaffDTO = authShopStaffReadFacade.findById(staffId, UserLoginSourceEnum.POS);
                if (authShopStaffDTO != null) {
                    orderInfoParamDTO.setStaffId(staffId);
                    orderInfoParamDTO.setStaffName(authShopStaffDTO.getFullName());
                    orderInfoParamDTO.setStaffRoleName(authShopStaffDTO.getRoleName());
                }
            } else if (UserLoginSourceEnum.H5.getValue().equals(loginSource)) {
                orderInfoParamDTO.setOrderUpdateSource(OrderUpdateSourceEnum.H5);
            }

            // 追加订单信息
            OrderInfoDTO orderInfoDTO = orderWriteFacade.appendOrder(orderInfoParamDTO);
            OrderInfoVO orderInfoVO = ObjectConvertUtil.map(orderInfoDTO, OrderInfoVO.class);

            List<OrderItemVO> orderItems = itemDTOList2VOList(orderInfoDTO.getOrderItems());
            orderInfoVO.setOrderItems(orderItems);

            result = Result.SUCESS(orderInfoVO);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("appendOrder Exception:", e);
            }
            if (e instanceof OrderException) {
                result = Result.FAILURE(((OrderException) e).getCode(), e.getMessage());
            } else {
                result = Result.FAILURE(e.getMessage());
            }
        }
        return result;

    }

    @ResponseBody
    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
    @ApiOperation(value = "取消订单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> cancelOrder(
            @ApiParam(name = "orderId", value = "订单ID", type = "Long") @RequestParam(required = true) Long orderId,
            @ApiParam(name = "orderCancelReasonInfoVO", value = "取消原因VO", type = "orderCancelReasonInfoVO") @RequestBody(required = false) OrderCancelReasonInfoVO orderCancelReasonInfoVO
    ) {
        Result<Boolean> result = null;
        try {
            // TODO 这里是否需要权限校验？
            OrderCancelReasonInfoDTO orderCancelReasonInfoDTO = null;

            Long merchantId = ThreadLocalContext.getMerchantId();
            Long staffId = ThreadLocalContext.getStaffId();

            Long shopId = ThreadLocalContext.getShopId();

            if (isLongNull(merchantId)){
                throw new OrderException("商户ID为空！");
            }

            if (isLongNull(shopId)){
                throw new OrderException("店铺ID为空！");
            }

            boolean toVerifyTwice = false;

            //授权码不为空才考虑验证
            if (orderCancelReasonInfoVO != null && StringUtils.isNotEmpty(orderCancelReasonInfoVO.getAuthorCode())) {
                toVerifyTwice = true;
            }

            GetOrderInfoParamDTO getOrderInfoParam = new GetOrderInfoParamDTO();
            getOrderInfoParam.setOrderId(orderId);
            getOrderInfoParam.setDetailFlag(OrderInfoDetailEnum.BASE.getCode());
            OrderInfoDTO orderInfoDTO = orderReadFacade.getOrderInfoByOrderId(getOrderInfoParam);

            if (orderInfoDTO != null && orderInfoDTO.getOrderType() == OrderTypeEnum.PAY_FIRST) {
                // 先付后食不需要校验授权码
                toVerifyTwice = false;
            }

            if (toVerifyTwice && orderCancelReasonInfoVO != null) {
                if (!shopAuthCodeReadFacade.verifyAuthCodeTwice(merchantId, orderCancelReasonInfoVO.getAuthorCode(), staffId, AuthOperationEnum.CANCEL_ORDER)) {
                    return Result.FAILURE("授权码错误或已失效!");
                }
            }

            orderCancelReasonInfoDTO = trans2DTO(orderCancelReasonInfoVO, orderId);

            Boolean flag = orderWriteFacade.cancelOrder(orderId, orderCancelReasonInfoDTO);
            result = Result.SUCESS(flag);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("cancelOrder Exception: ", e);
            }
            if (e instanceof OrderException) {
                result = Result.FAILURE(((OrderException) e).getCode(), e.getMessage());
            } else {
                result = Result.FAILURE(e.getMessage());
            }
        }
        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/makeOrderPayStatus", method = RequestMethod.POST)
    @ApiOperation(value = "设置订单的支付中状态为未支付：弹出密码框未输入密码或者密码输入错误，返回的时候就调用这个接口", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> makePayStatusPaying2UnPay(
            @ApiParam(name = "orderNo", value = "订单号", type = "String")
            @RequestParam String orderNo
    ) {
        Result<Boolean> result = null;
        try {

            Boolean flag = orderWriteFacade.makePayStatusPaying2UnPay(orderNo, PayStatusEnum.UN_PAY);
            result = Result.SUCESS(flag);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("makePayStatusPaying2UnPay Exception: ", e);
            }
            if (e instanceof OrderException) {
                result = Result.FAILURE(((OrderException) e).getCode(), e.getMessage());
            } else {
                result = Result.FAILURE(e.getMessage());
            }
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/recOrder/{orderId}", method = RequestMethod.POST)
    @ApiOperation(value = "接单", notes = "接单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> recOrder(
            @ApiParam(name = "orderId", value = "订单ID", required = true, type = "Long")
            @PathVariable(name = "orderId") Long orderId) {

        logger.info("orderId:" + orderId);
        Result<Boolean> result = null;
        try {
            String snNum = ThreadLocalContext.getDid();
            Long updateUserId = ThreadLocalContext.getStaffId();
            Boolean flag = orderWriteFacade.recOrder(orderId, updateUserId, snNum);
            result = Result.SUCESS(flag);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("recOrder Exception: ", e);
            }

            if (e instanceof OrderException) {
                result = Result.FAILURE(((OrderException) e).getCode(), e.getMessage());
            } else {
                result = Result.FAILURE(e.getMessage());
            }
        }

        return result;

    }

    @ResponseBody
    @RequestMapping(value = "/recOrder/v2/{orderId}", method = RequestMethod.POST)
    @ApiOperation(value = "接单", notes = "接单：返回接单的延迟状态:100改成110把100返回", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<OrderInfoVO> recOrderV2(
            @ApiParam(name = "orderId", value = "订单ID", required = true, type = "Long")
            @PathVariable(name = "orderId") Long orderId) {

        logger.info("orderId:" + orderId);
        Result<OrderInfoVO> result = null;
        try {
            String snNum = ThreadLocalContext.getDid();
            Long updateUserId = ThreadLocalContext.getStaffId();
            OrderInfoDTO orderInfoDTO = orderWriteFacade.recOrderV2(orderId, updateUserId, snNum);

            if (null == orderInfoDTO) {
                result = Result.SUCESS(null);
                return result;
            }

            OrderInfoVO orderInfoVO = CenterUtil.getMapperFactory().getMapperFacade().map(orderInfoDTO, OrderInfoVO.class);
            List<OrderItemVO> orderItems = itemDTOList2VOList(orderInfoDTO.getOrderItems());
            orderInfoVO.setOrderItems(orderItems);
            result = Result.SUCESS(orderInfoVO);

            result = Result.SUCESS(orderInfoVO);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("recOrderV2 Exception: ", e);
            }

            if (e instanceof OrderException) {
                result = Result.FAILURE(((OrderException) e).getCode(), e.getMessage());
            } else {
                result = Result.FAILURE(e.getMessage());
            }
        }

        return result;

    }

    @ResponseBody
    @RequestMapping("/changeTable")
    @ApiOperation(value = "换桌", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> changeTable(
            @ApiParam(value = "paramVO", name = "paramVO", required = true)
            @RequestBody ChangeTableParamVO changeTableParamVO) {

        Result<Boolean> result = null;
        try {
            if (null == changeTableParamVO){
                throw new OrderException("换桌时传入参数不能为空！");
            }

            Long orderId = changeTableParamVO.getOrderId();
            if (isLongNull(orderId)){
                throw new OrderException("换桌时订单ID不能为空！");
            }
            Integer toTableId = changeTableParamVO.getToTableId();
            if (null == toTableId || 0 == toTableId){
                throw new OrderException("换桌时目标桌台ID不能为空！");
            }
            String remark = changeTableParamVO.getRemark();
            logger.info("orderId:" + orderId + "   toTableId:" + toTableId + "  remark:" + remark);
            Boolean flag = orderWriteFacade.changeDesk(orderId, toTableId, remark);
            result = Result.SUCESS(flag);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("changeTable Exception: ", e);
            }

            if (e instanceof OrderException) {
                result = Result.FAILURE(((OrderException) e).getCode(), e.getMessage());
            } else {
                result = Result.FAILURE(e.getMessage());
            }
        }
        return result;

    }


    @ResponseBody
    @RequestMapping("/syncOrderItemPrintStatus")
    @ApiOperation(value = "同步订单商品是否已经打印", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> syncOrderItemPrintStatus(
            @ApiParam(value = "list", name = "list", required = true)
            @RequestBody List<Long> orderItemIds) {
        Result<Boolean> result = null;
        try {
            Boolean flag = orderWriteFacade.syncOrderItemPrintStatus(orderItemIds);
            result = Result.SUCESS(flag);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("syncOrderItemPrintStatus Exception: ", e);
            }

            if (e instanceof OrderException) {
                result = Result.FAILURE(((OrderException) e).getCode(), e.getMessage());
            } else {
                result = Result.FAILURE(e.getMessage());
            }
        }
        return result;

    }

    @ResponseBody
    @RequestMapping("/syncOrderItemPrintFlag")
    @ApiOperation(value = "同步订单商品是否已经打印（标识）", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> syncOrderItemPrintFlag(
            @ApiParam(value = "list", name = "list", required = true)
            @RequestBody List<SyncOrderItemPrintFlagVO> syncOrderItemPrintFlagVOS) {
        Result<Boolean> result;
        try {


            if (CollectionUtils.isListBlank(syncOrderItemPrintFlagVOS)) {
                throw new OrderException(OrderErrorEnum.UN_PASSED_ARGUMENT, "传入参数为空");
            }

            MapperFacade mapperFacade = ObjectConvertUtil.getMapperFactory().getMapperFacade();
            List<SyncOrderItemPrintFlagDTO> syncOrderItemPrintFlagDTOS = mapperFacade.mapAsList(syncOrderItemPrintFlagVOS, SyncOrderItemPrintFlagDTO.class);
            Boolean flag = orderWriteFacade.syncOrderItemPrintFlag(syncOrderItemPrintFlagDTOS);
            result = Result.SUCESS(flag);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("syncOrderItemPrintFlag Exception: ", e);
            }

            if (e instanceof OrderException) {
                result = Result.FAILURE(((OrderException) e).getCode(), e.getMessage());
            } else {
                result = Result.FAILURE(e.getMessage());
            }
        }
        return result;

    }


    @ResponseBody
    @RequestMapping("/syncOrderItemPrintFlag4incr")
    @ApiOperation(value = "同步订单商品已经打印的次数", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> syncOrderItemPrintFlag4incr(
            @ApiParam(value = "list", name = "list", required = true)
            @RequestBody List<Long> orderItemIds) {
        Result<Boolean> result;
        try {


            if (CollectionUtils.isListBlank(orderItemIds)) {
                throw new OrderException(OrderErrorEnum.UN_PASSED_ARGUMENT, "传入参数为空");
            }

            Boolean flag = orderWriteFacade.syncOrderItemPrintFlag4incr(orderItemIds);
            result = Result.SUCESS(flag);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("syncOrderItemPrintFlag4incr Exception: ", e);
            }

            if (e instanceof OrderException) {
                result = Result.FAILURE(((OrderException) e).getCode(), e.getMessage());
            } else {
                result = Result.FAILURE(e.getMessage());
            }
        }
        return result;

    }


    @ResponseBody
    @RequestMapping("/syncOrderItemPrintFlag4decr")
    @ApiOperation(value = "同步订单商品已经打印的失败的次数", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> syncOrderItemPrintFlag4decr(
            @ApiParam(value = "list", name = "list", required = true)
            @RequestBody List<Long> orderItemIds) {
        Result<Boolean> result;
        try {


            if (CollectionUtils.isListBlank(orderItemIds)) {
                throw new OrderException(OrderErrorEnum.UN_PASSED_ARGUMENT, "传入参数为空");
            }

            Boolean flag = orderWriteFacade.syncOrderItemPrintFlag4decr(orderItemIds);
            result = Result.SUCESS(flag);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("syncOrderItemPrintFlag4decr Exception: ", e);
            }

            if (e instanceof OrderException) {
                result = Result.FAILURE(((OrderException) e).getCode(), e.getMessage());
            } else {
                result = Result.FAILURE(e.getMessage());
            }
        }
        return result;

    }

    @ResponseBody
    @RequestMapping("/syncOrderPrinterTicketFlag/{orderNo}/{printerTicketFlag}")
    @ApiOperation(value = "同步订单已经打印小票的情况", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> syncOrderPrinterTicketFlag(
            @ApiParam(name = "orderNo", value = "订单号", required = true, type = "String")
            @PathVariable(name = "orderNo") String orderNo,
            @ApiParam(name = "printerTicketFlag", value = "是否已经打印小票标志位：见PrintTicketFlagEnum，2.点单完成小票，4.会员充值支付完成小票，" +
                    "8.结账支付完成小票，16.退款完成小票，32.快键收银支付完成小票，64.饿了么外卖接单完成小票，128.美团外卖接单完成小票。" +
                    "如果一次性传多个完成小票：2|4=6", required = true, type = "Integer")
            @PathVariable Integer printerTicketFlag
    ) {
        Result<Boolean> result = null;
        try {
            boolean b = orderWriteFacade.syncOrderPrinterTicketFlag(orderNo, printerTicketFlag);
            result = Result.SUCESS(b);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("syncOrderPrinterTicketFlag Exception: ", e);
            }

            if (e instanceof OrderException) {
                result = Result.FAILURE(((OrderException) e).getCode(), e.getMessage());
            } else {
                result = Result.FAILURE(e.getMessage());
            }
        }
        return result;

    }


    /**
     * POS同步订单
     * POS自己支付成功的订单，保存在POS，需要同步到订单系统
     * 订单系统检查，该订单是否已经同步，产生订单信息，流水信息，减库存，加会员积分等逻辑操作
     *
     * @return com.lizikj.api.vo.Result<java.lang.Boolean>
     * @params [syncOrderParamVO]
     * @author zhoufe
     * @date 2017/8/12 17:35
     */
    @LoginExclude
    @ResponseBody
    @RequestMapping("/syncOrder")
    @ApiOperation(value = "POS同步订单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> syncOrder(
            @ApiParam(value = "paramVO", name = "paramVO", required = true)
            @RequestBody List<OrderInfoVO> orderInfoVOS) {
        Result<Boolean> result = null;
        try {

            //Long shopId = ThreadLocalContext.getShopId();
            String snNum = ThreadLocalContext.getDid();
            //Long merchantId = ThreadLocalContext.getMerchantId();

//            if (null == shopId || 0 == shopId) {
//                throw new OrderException(OrderErrorEnum.UN_PASSED_ARGUMENT, String.format("获取的店铺ID(%s)为空或者为0", shopId));
//            }

//            if (null == merchantId || 0 == merchantId) {
//                throw new OrderException(OrderErrorEnum.UN_PASSED_ARGUMENT, String.format("获取的商户ID(%s)为空或者为0", merchantId));
//            }

//            if (StringUtils.isBlank(snNum)) {
//                throw new OrderException(OrderErrorEnum.UN_PASSED_ARGUMENT, String.format("获取的SN号(%s)为空", snNum));
//            }

            if (CollectionUtils.isListBlank(orderInfoVOS)) {
                throw new OrderException(OrderErrorEnum.UN_PASSED_ARGUMENT, "传入参数为空");
            }

            List<OrderInfoDTO> orderInfoDTOS = CenterUtil.getMapperFactory().getMapperFacade().mapAsList(orderInfoVOS, OrderInfoDTO.class);
            //放入店铺ID和SN
            putSomeHeatherParam(null, snNum, null, orderInfoDTOS);

            boolean isSucc = syncPosOrderWriteFacade.syncOrder(orderInfoDTOS);

            result = Result.SUCESS(isSucc);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("syncOrder Exception: ", e);
            }
            if (e instanceof OrderException) {
                result = Result.FAILURE(((OrderException) e).getCode(), e.getMessage());
            } else {
                result = Result.FAILURE(e.getMessage());
            }
        }
        return result;
    }

    /**
     * 存放从头部获取到的信息
     *
     * @return void
     * @params [shopId, snNum, merchantId, syncOrderParamDTOS]
     * @author zhoufe
     * @date 2017/9/29 18:56
     */
    private void putSomeHeatherParam(Long shopId, String snNum, Long merchantId, List<OrderInfoDTO> orderInfoDTOS) {
        for (OrderInfoDTO orderInfoDTO : orderInfoDTOS) {
            if (!isLongNull(shopId)) {
                orderInfoDTO.setShopId(shopId);
            }
            orderInfoDTO.setSnNum(snNum);
            if (!isLongNull(merchantId)) {
                orderInfoDTO.setMerchantId(merchantId);
            }

//            SyncOrderContentParamDTO syncOrderContent = syncOrderParamDTO.getSyncOrderContent();
//            if (null != syncOrderContent){
//                continue;
//            }
//            List<OrderItemParamDTO> itemParamList = syncOrderContent.getItemParamList();
//            if (CollectionUtils.isListNotBlank(itemParamList)){
//            }
//
//            List<SyncPosRefundOrderDTO> syncPosRefundOrders = syncOrderContent.getSyncPosRefundOrders();
//            if (CollectionUtils.isListNotBlank(syncPosRefundOrders)){
//
//            }
//
//            List<OrderDiscountDTO> orderDiscounts = syncOrderContent.getOrderDiscounts();
//            if (CollectionUtils.isListNotBlank(orderDiscounts)){
//
//            }
//
//            List<PayFlowDTO> payFlows = syncOrderContent.getPayFlows();
//            if (CollectionUtils.isListNotBlank(payFlows)){
//
//            }

        }
    }

    private boolean isLongNull(Long id) {
        return null == id || 0 == id;
    }


    /**
     * 标记是否开过发票
     *
     * @return com.lizikj.api.vo.Result<java.lang.Boolean>
     * @params [orderId, invoiceStatus]
     * @author zhoufe
     * @date 2017/9/14 15:35
     */
    @ResponseBody
    @RequestMapping("/invoiceOrder")
    @ApiOperation(value = "标记是否开过发票", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> invoiceOrder(
            @ApiParam(name = "orderId", value = "订单ID", required = true)
            @RequestParam long orderId,
            @ApiParam(name = "invoiceStatus", value = "是否开过发票：见InvoiceStatusEnum：YES.是，NO.否。", required = true)
            @RequestParam InvoiceStatusEnum invoiceStatus) {

        Result<Boolean> result;
        try {

            boolean isSucc = orderWriteFacade.invoiceOrder(orderId, invoiceStatus);
            result = Result.SUCESS(isSucc);
        } catch (OrderException e) {
            if (logger.isErrorEnabled()) {
                logger.error("invoiceOrder Exception: ", e);
            }
            if (e instanceof OrderException) {
                result = Result.FAILURE(((OrderException) e).getCode(), e.getMessage());
            } else {
                result = Result.FAILURE(e.getMessage());
            }
        }

        return result;
    }

    @ResponseBody
    @RequestMapping("/payStatus/{orderNo}")
    @ApiOperation(value = "订单号获取订单详情信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<OrderPayStatusVO> getOrderPayStatusByOrderNo(@PathVariable(name = "orderNo") String orderNo) {
        Long merchantId = ThreadLocalContext.getMerchantId();
        Long shopId = ThreadLocalContext.getShopId();
        if (logger.isInfoEnabled()) {
            logger.info("根据订单编号查询支付状态, merchantId={}, shopId={}, orderNo={}", merchantId, shopId, orderNo);
        }
        OrderPayStatusVO orderPayStatusVO = null;
        try {
            GetOrderInfoParamDTO getOrderInfoParam = new GetOrderInfoParamDTO();
            getOrderInfoParam.setOrderNo(orderNo);
            OrderInfoDTO orderInfoDTO = orderReadFacade.getOrderInfoByOrderNo(getOrderInfoParam);
            if (null == orderInfoDTO) {
                return Result.SUCESS();
            }

            if (null != merchantId && orderInfoDTO.getMerchantId().longValue() != merchantId.longValue()) {
                return Result.FAILURE("当前登录人的店铺和订单所属店铺不匹配");
            }

            orderPayStatusVO = new OrderPayStatusVO();
            orderPayStatusVO.setOrderNo(orderNo);
            orderPayStatusVO.setPayStatus(orderInfoDTO.getPayStatus().getStatus());
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("根据订单编号查询支付状态error, message={}", e.getMessage());
            }
        }
        return Result.SUCESS(orderPayStatusVO);
    }


    @ResponseBody
    @RequestMapping("/querySyncOrder")
    @ApiOperation(value = "查询POS同步订单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PageInfo<SyncPosOrderInfoVO>> querySyncOrder(
            @ApiParam(value = "paramVO", name = "paramVO", required = true)
            @RequestBody QuerySyncOrderParamVO querySyncOrderParamVO
    ) {
        Result<PageInfo<SyncPosOrderInfoVO>> result;
        try {

            if (null == querySyncOrderParamVO) {
                throw new OrderException(OrderErrorEnum.UN_PASSED_ARGUMENT, "传入参数为空");
            }
            QuerySyncOrderParamDTO querySyncOrderParamDTO = CenterUtil.getMapperFactory().getMapperFacade()
                    .map(querySyncOrderParamVO, QuerySyncOrderParamDTO.class);


            PageParameter pageParameter = new PageParameter();
            pageParameter.setPageNum(querySyncOrderParamVO.getPageNum());
            pageParameter.setPageSize(querySyncOrderParamVO.getPageSize());


            PageInfo<SyncPosOrderInfoDTO> page = syncPosOrderReadFacade.querySyncOrder(querySyncOrderParamDTO, pageParameter);

            if (null == page) {
                return Result.SUCESS(null);
            }

            List<SyncPosOrderInfoDTO> list = page.getList();
            if (CollectionUtils.isListBlank(list)) {
                return Result.SUCESS(page);
            }

            List<SyncPosOrderInfoVO> syncPosOrderInfoVOS = CenterUtil.getMapperFactory().getMapperFacade()
                    .mapAsList(list, SyncPosOrderInfoVO.class);

            PageInfo pageInfo = page;
            pageInfo.setList(syncPosOrderInfoVOS);
            result = Result.SUCESS(pageInfo);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("querySyncOrder Exception: ", e);
            }
            if (e instanceof OrderException) {
                result = Result.FAILURE(((OrderException) e).getCode(), e.getMessage());
            } else {
                result = Result.FAILURE(e.getMessage());
            }
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/bindingShareUserId")
    @ApiOperation(value = "设置第一个分享人的userId", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> bindingShareUserId(
            @ApiParam(name = "orderId", value = "订单ID", required = true)
            @RequestParam long orderId) {
        Result<Boolean> result = null;
        try {
            boolean isSucc = orderWriteFacade.bindingShareUserId(orderId, ThreadLocalContext.getUserId());
            result = Result.SUCESS(isSucc);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("invoiceOrder Exception: ", e);
            }
            if (e instanceof OrderException) {
                result = Result.FAILURE(((OrderException) e).getCode(), e.getMessage());
            } else {
                result = Result.FAILURE(e.getMessage());
            }
        }

        return result;
    }


    private OrderCancelReasonInfoDTO trans2DTO(OrderCancelReasonInfoVO vo, long orderId) {
        long merchantId = ThreadLocalContext.getMerchantId();
        long shopId = ThreadLocalContext.getShopId();

        //兼容不传参数的情况 zfe
        if (null == vo) {
            return null;
        }

        OrderCancelReasonInfoDTO dto = new OrderCancelReasonInfoDTO();
        dto.setAuthorCode(vo.getAuthorCode());
        dto.setCancelReason(vo.getCancelReason());
        dto.setCancelReasonType(vo.getCancelReasonType());
        dto.setCancelTime(new Date());
        dto.setMerchantId(merchantId);
        dto.setOperatorStaffId(vo.getOperatorStaffId());
        dto.setOrderId(orderId);
        dto.setOrderNo(vo.getOrderNo());
        dto.setShopId(shopId);
        List<OrderCancelReasonItemDTO> dtoList = null;
        if (CollectionUtils.isListNotBlank(vo.getOrderCancelReasonItems())) {
            dtoList = new ArrayList<>();
            for (OrderCancelReasonItemVO itemVO : vo.getOrderCancelReasonItems()) {
                OrderCancelReasonItemDTO itemDTO = new OrderCancelReasonItemDTO();
                itemDTO.setGoodsId(itemVO.getGoodsId());
                itemDTO.setGoodsName(itemVO.getGoodsName());
                itemDTO.setOrderGoodsProblem(itemVO.getOrderGoodsProblem());
                itemDTO.setOrderId(orderId);
                itemDTO.setOrderItemId(itemVO.getOrderItemId());
                itemDTO.setOrderNo(itemVO.getOrderNo());
                itemDTO.setShopId(shopId);
                dtoList.add(itemDTO);
            }
        }
        dto.setOrderCancelReasonItems(dtoList);
        return dto;
    }

    @ApiOperation(value = "导出订单明细报表excel", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/export/orderInfo4Detail", method = RequestMethod.GET)
    public void exportShopGoodsSaleRankExcel(HttpServletRequest request, HttpServletResponse response,
                                             @ApiParam(value = "门店ID", name = "shopId", required = true) @RequestParam(name = "shopId", required = true) Long shopId,
                                             @ApiParam(value = "订单号", name = "orderNo", required = false) @RequestParam(name = "orderNo", required = false) String orderNo,
                                             @ApiParam(value = "商户ID", name = "merchantId", required = false) @RequestParam(name = "merchantId", required = false) Long merchantId,
                                             @ApiParam(value = "李子会员ID", name = "memberId", required = false) @RequestParam(name = "memberId", required = false) Long memberId,
                                             @ApiParam(value = "商户会员IDeas", name = "merchantMemberId", required = false) @RequestParam(name = "merchantMemberId", required = false) Long merchantMemberId,
                                             @ApiParam(value = "手机号", name = "mobile", required = false) @RequestParam(name = "mobile", required = false) String mobile,
                                             @ApiParam(value = "用户ID", name = "userId", required = false) @RequestParam(name = "userId", required = false) Long userId,
                                             @ApiParam(value = "sn号", name = "snNum", required = false) @RequestParam(name = "snNum", required = false) String snNum,
                                             @ApiParam(value = "餐桌ID", name = "areaDeskId", required = false) @RequestParam(name = "areaDeskId", required = false) Long areaDeskId,
                                             @ApiParam(value = "餐桌名称", name = "areaDeskName", required = false) @RequestParam(name = "areaDeskName", required = false) String areaDeskName,
                                             @ApiParam(value = "订单来源", name = "orderSource", required = false) @RequestParam(name = "orderSource", required = false) OrderSourceEnum orderSource,
                                             @ApiParam(value = "订单状态", name = "orderStatus", required = false) @RequestParam(name = "orderStatus", required = false) OrderStatusEnum orderStatus,
                                             @ApiParam(value = "订单类型", name = "orderType", required = false) @RequestParam(name = "orderType", required = false) OrderTypeEnum orderType,
                                             @ApiParam(value = "订单业务类型：见OrderBizTypeEnum", name = "orderBizTypes", required = false) @RequestParam(name = "orderBizTypes", required = false) List<OrderBizTypeEnum> orderBizTypes,
                                             @ApiParam(value = "下单开始时间", name = "startOrderTime", required = false) @RequestParam(name = "startOrderTime", required = false) Date startOrderTime,
                                             @ApiParam(value = "下单结束时间", name = "endOrderTime", required = false) @RequestParam(name = "endOrderTime", required = false) Date endOrderTime,
                                             @ApiParam(value = "订单创建开始时间", name = "startCreateTime", required = false) @RequestParam(name = "startCreateTime", required = false) Date startCreateTime,
                                             @ApiParam(value = "订单创建结束时间", name = "endCreateTime", required = false) @RequestParam(name = "endCreateTime", required = false) Date endCreateTime,
                                             @ApiParam(value = "订单更新开始时间", name = "startUpdateTime", required = false) @RequestParam(name = "startUpdateTime", required = false) Date startUpdateTime,
                                             @ApiParam(value = "订单更新结束时间", name = "endUpdateTime", required = false) @RequestParam(name = "endUpdateTime", required = false) Date endUpdateTime,
                                             @ApiParam(value = "排序字段：排序的字段属性如：orderStatus，排序方式：OrderByEnum：DESC，ASC", name = "sortMaps", required = false) @RequestParam(name = "sortMaps", required = false) Map<String, OrderByEnum> sortMaps,
                                             @ApiParam(value = "下单人ID", name = "orderPersonId", required = true) @RequestParam(name = "orderPersonId", required = false) Long orderPersonId,
                                             @ApiParam(value = "收银员ID：当传入这个参数的时候：查询时间段开始，查询时间段结束时间要求传入", name = "cashierId", required = true) @RequestParam(name = "cashierId", required = false) Long cashierId,
                                             @ApiParam(value = "美食ID：当传入这个参数的时候：查询时间段开始时间，查询时间段结束时间要求传入", name = "goodsId", required = true) @RequestParam(name = "goodsId", required = false) String goodsId,
                                             @ApiParam(value = "页码", name = "pageNum", defaultValue = "1") @RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum,
                                             @ApiParam(value = "每页数量", name = "pageSize", defaultValue = "25") @RequestParam(value = "pageSize", defaultValue = "25", required = false) int pageSize
    ) {
        FreeMarkerExportExcelUtil util = new FreeMarkerExportExcelUtil(request, response, excelBuilPath);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        try {
            shopId = ThreadLocalContext.getShopId();
            merchantId = ThreadLocalContext.getMerchantId();
            OrderInfoQueryParamDTO queryParamDTO = new OrderInfoQueryParamDTO();
            queryParamDTO.setShopId(shopId);
            queryParamDTO.setOrderNo(orderNo);
            queryParamDTO.setMerchantId(merchantId);
            queryParamDTO.setMemberId(merchantMemberId);
            queryParamDTO.setMerchantMemberId(merchantMemberId);
            queryParamDTO.setMobile(mobile);
            queryParamDTO.setUserId(userId);
            queryParamDTO.setSnNum(snNum);
            queryParamDTO.setAreaDeskId(areaDeskId);
            queryParamDTO.setAreaDeskName(areaDeskName);
            queryParamDTO.setOrderSource(orderSource);
            queryParamDTO.setOrderStatus(orderStatus);
            queryParamDTO.setOrderType(orderType);
            queryParamDTO.setOrderBizTypes(orderBizTypes);
            queryParamDTO.setStartOrderTime(startOrderTime);
            queryParamDTO.setEndOrderTime(endOrderTime);
            queryParamDTO.setStartCreateTime(startCreateTime);
            queryParamDTO.setEndCreateTime(endCreateTime);
            queryParamDTO.setStartUpdateTime(startUpdateTime);
            queryParamDTO.setEndUpdateTime(endUpdateTime);
            queryParamDTO.setSortMaps(sortMaps);
            queryParamDTO.setOrderPersonId(orderPersonId);
            queryParamDTO.setCashierId(cashierId);
            queryParamDTO.setGoodsId(goodsId);
            PageInfo<OrderInfoDTO> orderInfoDTOPageInfo = orderReadFacade.query4Detail(queryParamDTO, pageNum, pageSize);
            if (Collections.isEmpty(orderInfoDTOPageInfo.getList())) {
                dataMap.put("list", null);
            } else {
                JSONArray list = new JSONArray();
                int maxRow = 0;
                for (OrderInfoDTO item : orderInfoDTOPageInfo.getList()) {
                    maxRow += (null == item.getOrderItems()) ? 0 : item.getOrderItems().size() + 1;
                    JSONObject o = this.setOrderDetailToMap(item);//封装订单结果
                    list.add(o);
                }
                dataMap.put("list", list);
                dataMap.put("maxRow", maxRow);
            }
            util.export("orderDetailReportTemplate.ftl", dataMap, "订单明细报表.xls");
        } catch (IOException e) {
            logger.error("导出订单明细报表excel出错,shopId:{},message={}", shopId, e);
        }
    }

    //封装订单详情结果toMap
    private JSONObject setOrderDetailToMap(OrderInfoDTO item) {
        JSONObject o = (JSONObject) JSONObject.toJSON(item);
        //计算最大行数 默认为1行
        int itemmaxRow = 1;
        int payRowCount = (item.getPayInfo() == null || item.getPayInfo().getPayFlowList() == null) ? 1 : item.getPayInfo().getPayFlowList().size();
        int discountRowCount = item.getOrderDiscounts() == null ? 1 : item.getOrderDiscounts().size();
        if (payRowCount > discountRowCount) {
            itemmaxRow = payRowCount;
        } else {
            itemmaxRow = discountRowCount;
        }
        o.put("maxRow", itemmaxRow);
        //转换枚举
        o.put("orderType", null != item.getOrderType() ? item.getOrderType().getDescription() : "");
        o.put("payStatus", null != item.getPayStatus() ? item.getPayStatus().getDescription() : "");
        o.put("orderStatus", null != item.getOrderStatus() ? item.getOrderStatus().getDescription() : "");
        o.put("orderSource", null != item.getOrderSource() ? item.getOrderSource().getDescription() : "");
        o.put("invoiceStatus", null != item.getInvoiceStatus() ? item.getInvoiceStatus().getDescription() : "");
        List<String> goodNames = new ArrayList<String>();
        Long feeTea = 0L;//茶位费
        Long customTotalAmount = 0L;//自定义费用
        if (!Collections.isEmpty(item.getOrderItems())) {
            for (OrderItemDTO orderItem : item.getOrderItems()) {
                if (orderItem.getItemSubType() == ItemSubTypeEnum.FEE_TEA) {
                    feeTea = orderItem.getSellPrice();
                } else {
                    goodNames.add(orderItem.getGoodsName());
                }
                if (orderItem.getSkuId() != null && FixedSKUID4NotSKUIDEnum.getEnum(orderItem.getSkuId()) == FixedSKUID4NotSKUIDEnum.CUSTOM_FEE) {
                    customTotalAmount += orderItem.getTotalAmount();
                }
            }
        }
        o.put("customTotalAmount", customTotalAmount);
        List<String> cashierNames = new ArrayList<String>();
        if (item.getPayInfo() != null && !Collections.isEmpty(item.getPayInfo().getPayFlowList())) {
            for (PayFlowDTO flow : item.getPayInfo().getPayFlowList()) {
                if (flow.getAccountDetail() != null) {
                    cashierNames.add(flow.getAccountDetail().getCashierName());
                }
            }
        }
        o.put("goodNames", String.join("/", goodNames));
        o.put("cashierNames", String.join(" ", cashierNames));
        o.put("feeTea", feeTea * ((item.getPeoples() == null) ? 0 : item.getPeoples()));
        o.put("invoiceStatus", item.getInvoiceStatus() == InvoiceStatusEnum.YES ? "已开票" : "未开票");

        //优惠信息
        if (!Collections.isEmpty(item.getOrderDiscounts())) {
            for (OrderDiscountDTO discountDTO : item.getOrderDiscounts()) {
                if (discountDTO.getTypeNode() != null && discountDTO.getBenefitAmount() != null && discountDTO.getStatus() == DiscountStatusEnum.VALID) {
                    String discountTypeKey = "discount_" + discountDTO.getTypeNode() + "_amount";
                    if (o.containsKey(discountTypeKey)) {
                        Long discountAmountRecord = o.getLong(discountTypeKey);
                        o.put(discountTypeKey, discountDTO.getBenefitAmount() + discountAmountRecord);
                    } else {
                        o.put(discountTypeKey, discountDTO.getBenefitAmount());
                    }
                }
            }
        }
        //支付详情
        if (item.getPayInfo() != null && !Collections.isEmpty(item.getPayInfo().getPayFlowList())) {
            for (PayFlowDTO payFlow : item.getPayInfo().getPayFlowList()) {
                if (payFlow.getPaymentTypeCode() != null && PaymentTypeEnum.get(payFlow.getPaymentTypeCode()) != null) {
                    String paymentTypeKey = "paymentType_" + PaymentTypeEnum.get(payFlow.getPaymentTypeCode()) + "_amount";
                    if (o.containsKey(paymentTypeKey)) {
                        Long payFlowAmountRecord = o.getLong(paymentTypeKey);
                        o.put(paymentTypeKey, payFlow.getAmount() + payFlowAmountRecord);
                    } else {
                        o.put(paymentTypeKey, payFlow.getAmount());
                    }
                }
            }
        }
        return o;
    }
}
