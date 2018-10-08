package com.lizikj.api.controller.order;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.order.OrderRefundReasonInfoVO;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.order.dto.detail.OrderRefundReasonInfoDTO;
import com.lizikj.order.enums.OrderErrorEnum;
import com.lizikj.order.exception.OrderException;
import com.lizikj.order.facade.IOrderRefundWriteFacade;
import com.lizikj.shop.api.enums.AuthOperationEnum;
import com.lizikj.shop.api.facade.IShopAuthCodeReadFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author Michael.Huang
 * @date 2017/9/19 13:56
 */
@Controller
@RequestMapping("/order/refund")
@Api(value = "order/refund", tags = "订单API接口")
public class OrderRefundController extends OrderBaseController {
    /**
     * @private
     */
    private static final Logger logger = LoggerFactory.getLogger(OrderRefundController.class);

    @Resource
    private IOrderRefundWriteFacade orderRefundWriteFacade;
    
    @Resource
    private IShopAuthCodeReadFacade shopAuthCodeReadFacade;


    @ResponseBody
    @RequestMapping("/refundOrder")
    @ApiOperation(value = "订单退款", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Long> refundOrder(
            @ApiParam(value = "paramVO", name = "paramVO", required = true)
            @RequestBody OrderRefundReasonInfoVO orderRefundReasonInfoVO) {
        Result<Long> result;
        try {

            Long shopId = ThreadLocalContext.getShopId();
            Long staffId = ThreadLocalContext.getStaffId();
            Long merchantId = ThreadLocalContext.getMerchantId();

            checkReasonData(orderRefundReasonInfoVO, shopId, staffId);

            //不传就不检验
            String authorCode = orderRefundReasonInfoVO.getAuthorCode();
            if (StringUtils.isNotBlank(authorCode)) {
                if (!shopAuthCodeReadFacade.verifyAuthCodeTwice(merchantId, authorCode, staffId, AuthOperationEnum.REFUND_ORDER)) {
                    return Result.FAILURE("授权码错误或已失效!");
                }
            }

            OrderRefundReasonInfoDTO orderRefundReasonInfoDTO =
                    ObjectConvertUtil.getMapperFactory().getMapperFacade().map(orderRefundReasonInfoVO, OrderRefundReasonInfoDTO.class);

            orderRefundReasonInfoDTO.setShopId(shopId);
            orderRefundReasonInfoDTO.setOperatorStaffId(staffId);

            Long refundOrderId = orderRefundWriteFacade.refundOrder(orderRefundReasonInfoDTO);
            result = Result.SUCESS(refundOrderId);
        } catch (OrderException e) {
            logger.error("refundOrder Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    /**
     * 建议参数
     *
     * @return boolean
     * @params [orderRefundReasonInfoVO, shopId, staffId]
     * @author zhoufe
     * @date 2017/9/20 15:50
     */
    private boolean checkReasonData(OrderRefundReasonInfoVO orderRefundReasonInfoVO, Long shopId, Long staffId) {
        if (null == orderRefundReasonInfoVO) {
            throw new OrderException(OrderErrorEnum.ORDER_BUYER_INFO_ERROR, "输入参数为空");
        }

        if (isIdNull(shopId)) {
            throw new OrderException(OrderErrorEnum.ORDER_BUYER_INFO_ERROR, String.format("获取的店铺ID(%s)为空或者为0", shopId));
        }

        if (isIdNull(staffId)) {
            throw new OrderException(OrderErrorEnum.ORDER_BUYER_INFO_ERROR, String.format("获取的操作人员工ID(%s)为空或者为0", staffId));
        }
        return true;
    }

    private boolean isIdNull(Long id) {
        return null == id || 0 == id;
    }

}
