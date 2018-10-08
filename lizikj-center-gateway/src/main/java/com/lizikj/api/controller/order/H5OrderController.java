package com.lizikj.api.controller.order;

import com.github.pagehelper.PageInfo;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.order.OrderInfoVO;
import com.lizikj.api.vo.order.param.query.OrderInfoQueryParamVO;
import com.lizikj.common.enums.UserTypeEnum;
import com.lizikj.common.util.CollectionUtils;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.order.dto.OrderInfoDTO;
import com.lizikj.order.dto.param.GetOrderInfoParamDTO;
import com.lizikj.order.dto.param.query.OrderInfoQueryParamDTO;
import com.lizikj.order.enums.OrderErrorEnum;
import com.lizikj.order.enums.OrderInfoDetailEnum;
import com.lizikj.order.exception.OrderException;
import com.lizikj.order.facade.IOrderReadFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * H5独有接口
 *
 * @author Michael.Huang
 * @date 2017/9/26 10:11
 */
@Controller
@RequestMapping("/order/h5/")
@Api(value = "order/h5/", tags = "H5订单API接口")
public class H5OrderController {

    /**
     * @private
     */
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);


    /**
     * 订单Facade
     */
    @Autowired
    private IOrderReadFacade orderReadFacade;


    @ResponseBody
    @RequestMapping("/getOrderByDeskId/{deskId}")
    @ApiOperation(value = "根据桌台id获取订单编号", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> getOrderByDeskId(@PathVariable(value = "deskId", required = true) Long deskId) {

        if (null == deskId || deskId <= 0) {
            return Result.FAILURE("桌台ID不能为空！");
        }
        byte userType = ThreadLocalContext.getUserType();
        Long userId = ThreadLocalContext.getUserId();
        if (null == userId || userId <= 0) {
            return Result.FAILURE("用户未登录！");
        }
		if (UserTypeEnum.CLIENT_USER.getType() != userType
				&& UserTypeEnum.SMALL_CLIENT_USER.getType() != userType) {
			return Result.FAILURE("登录用户类型不正确！");
		}

        Result<String> result = null;
        try {
            String orderNo = orderReadFacade.getOrderByDeskIdAndUserId(deskId, userId);
            result = Result.SUCESS(orderNo);
        } catch (OrderException e) {
            if (logger.isErrorEnabled()) {
                logger.error("获取桌台订单编号异常 Exception: {}", e);
            }
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/findDeskIdByUUID/{uuid}")
    @ApiOperation(value = "根据UUID查询桌台ID", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Long> findDeskIdByUUID(@PathVariable(value = "uuid", required = true) String uuid) {

        if (StringUtils.isBlank(uuid)) {
            return Result.FAILURE("UUID不能为空！");
        }
        byte userType = ThreadLocalContext.getUserType();
        Long userId = ThreadLocalContext.getUserId();
        Long merchantId = ThreadLocalContext.getMerchantId();
        Long shopId = ThreadLocalContext.getShopId();
        if (null == userId || userId <= 0) {
            return Result.FAILURE("用户未登录！");
        }
        if (UserTypeEnum.CLIENT_USER.getType() != userType
                && UserTypeEnum.SMALL_CLIENT_USER.getType() != userType) {
            return Result.FAILURE("登录用户类型不正确！");
        }

        Result<Long> result;
        try {
            Long areaDeskId = orderReadFacade.findDeskIdByUUID(merchantId, shopId, uuid);
            result = Result.SUCESS(areaDeskId);
        } catch (OrderException e) {
            if (logger.isErrorEnabled()) {
                logger.error("获取桌台ID异常 Exception: {}", e);
            }
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }



    @ResponseBody
    @RequestMapping("/getOrderByUuid/{uuid}")
    @ApiOperation(value = "根据预二维码uuid获取订单编号", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> getOrderByDeskId(@PathVariable(value = "uuid", required = true) String uuid) {

        if (StringUtils.isBlank(uuid)) {
            return Result.FAILURE("uuid不能为空！");
        }
        byte userType = ThreadLocalContext.getUserType();
        Long userId = ThreadLocalContext.getUserId();
        Long merchantId = ThreadLocalContext.getMerchantId();
        Long shopId = ThreadLocalContext.getShopId();
        if (null == userId || userId <= 0) {
            return Result.FAILURE("用户未登录！");
        }
        if (UserTypeEnum.CLIENT_USER.getType() != userType
                && UserTypeEnum.SMALL_CLIENT_USER.getType() != userType) {
            return Result.FAILURE("登录用户类型不正确！");
        }

        Result<String> result = null;
        try {
            String orderNo = orderReadFacade.getOrderByUuidAndUserId(merchantId, shopId, uuid, userId);
            result = Result.SUCESS(orderNo);
        } catch (OrderException e) {
            if (logger.isErrorEnabled()) {
                logger.error("用uuid获台订单编号异常 Exception: {}", e);
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
    		Long userId = ThreadLocalContext.getUserId();
            OrderInfoQueryParamDTO queryParamDTO = ObjectConvertUtil.map(queryParam, OrderInfoQueryParamDTO.class);
			if (userId != null && userId > 0) {
				queryParamDTO.setUserId(userId);
			}

            Long shopIdParam = queryParamDTO.getShopId();
            if (null == shopIdParam || 0 == shopIdParam) {
                Long shopId = ThreadLocalContext.getShopId();
                queryParamDTO.setShopId(shopId);
            }

            PageInfo<OrderInfoDTO> orderInfoDTOPageInfo = orderReadFacade.query(queryParamDTO, queryParam.getCurrentPage(), queryParam.getPageSize());

            List<OrderInfoDTO> list = orderInfoDTOPageInfo.getList();

            if (CollectionUtils.isListBlank(list)){
                PageInfo<OrderInfoVO> orderInfoVoPageInfo = new PageInfo<>();
                if (orderInfoDTOPageInfo != null) {
                    BeanUtils.copyProperties(orderInfoVoPageInfo, orderInfoDTOPageInfo);
                }
                result = Result.SUCESS(orderInfoVoPageInfo);
                //返回
                return result;
            }

            Iterator<OrderInfoDTO> it = list.iterator();
            ArrayList<OrderInfoDTO> newList = new ArrayList<>(list.size());

            while (it.hasNext()) {
                OrderInfoDTO orderInfoDTO = it.next();
                GetOrderInfoParamDTO param = new GetOrderInfoParamDTO();
                param.setOrderId(orderInfoDTO.getOrderId());
                int flag = 1 | OrderInfoDetailEnum.BASE.getCode() | OrderInfoDetailEnum.CONTENT.getCode() | OrderInfoDetailEnum.DESK.getCode() | OrderInfoDetailEnum.SHOP.getCode() | OrderInfoDetailEnum.DISCOUNT.getCode();
                param.setDetailFlag(flag);
                OrderInfoDTO info = orderReadFacade.getOrderInfoByOrderId(param);
                newList.add(info);
            }
            orderInfoDTOPageInfo.setList(newList);

            PageInfo<OrderInfoVO> orderInfoVoPageInfo = new PageInfo<>();
            if (orderInfoDTOPageInfo != null) {
                BeanUtils.copyProperties(orderInfoVoPageInfo, orderInfoDTOPageInfo);
                List<OrderInfoVO> infoVOList = ObjectConvertUtil.mapList(list, OrderInfoDTO.class, OrderInfoVO.class);
                orderInfoVoPageInfo.setList(infoVOList);
            }
            result = Result.SUCESS(orderInfoVoPageInfo);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("h5 listOrderInfo Exception: ", e);
            }
            result = Result.FAILURE(e.getMessage());
        } finally {
            return result;
        }
    }
}
