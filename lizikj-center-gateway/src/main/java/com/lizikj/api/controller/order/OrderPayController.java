package com.lizikj.api.controller.order;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.order.OrderPayResultVO;
import com.lizikj.api.vo.order.param.OrderPayParamVO;
import com.lizikj.api.vo.order.param.PayParamVO;
import com.lizikj.api.vo.order.param.QuickBuyParamVO;
import com.lizikj.api.vo.order.param.QuickPayParamVO;
import com.lizikj.common.enums.PaymentSceneEnum;
import com.lizikj.common.enums.PaymentTypeEnum;
import com.lizikj.common.enums.UserLoginSourceEnum;
import com.lizikj.common.util.HttpProtocolUtils;
import com.lizikj.common.util.JsonUtils;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.order.dto.OrderPayResultDTO;
import com.lizikj.order.dto.param.OrderPayParamInfoDTO;
import com.lizikj.order.dto.param.PayParamDTO;
import com.lizikj.order.dto.param.QuickBuyParamDTO;
import com.lizikj.order.dto.param.QuickPayParamDTO;
import com.lizikj.order.enums.OrderSourceEnum;
import com.lizikj.order.exception.OrderException;
import com.lizikj.order.facade.IOrderPayWriteFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author Michael.Huang
 * @date 2017/9/19 13:56
 */
@Controller
@RequestMapping("/order/pay")
@Api(value = "order/pay", tags = "订单API接口")
public class OrderPayController extends OrderBaseController {
    /**
     * @private
     */
    private static final Logger logger = LoggerFactory.getLogger(OrderPayController.class);
    @Autowired
    HttpServletRequest request;
    @Resource
    private IOrderPayWriteFacade orderPayWriteFacade;

    @ResponseBody
    @RequestMapping("/payOrder")
    @ApiOperation(value = "结账", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<OrderPayResultVO> payOrder(
            @ApiParam(name = "payParamVO", value = "payParamVO", required = true)
            @RequestBody OrderPayParamVO orderPayParamVO) {
        Result<OrderPayResultVO> result = null;
        try {
            logger.info("orderPayParamVO:" + JsonUtils.toJSONString(orderPayParamVO));

            OrderPayParamInfoDTO payParamInfo = getOrderPayParamInfoDTO(orderPayParamVO);
            logger.info("OrderPayParamInfoDTO:" + JsonUtils.toJSONString(payParamInfo));

            Long cashierId = 0L;

            //H5下单 POS支付，不更新原先的userId字段
			logger.info("login source is {}", ThreadLocalContext.getLoginSource().byteValue());
            
            Long userId = null;
			if (UserLoginSourceEnum.POS.getValue().byteValue() == ThreadLocalContext.getLoginSource().byteValue()) {
				cashierId = ThreadLocalContext.getStaffId();
			} else if (UserLoginSourceEnum.H5.getValue().byteValue() == ThreadLocalContext.getLoginSource().byteValue()
					|| UserLoginSourceEnum.SC.getValue().byteValue() == ThreadLocalContext.getLoginSource().byteValue()) {
				userId = ThreadLocalContext.getUserId();
			}
            
            if(orderPayParamVO.getMerchantMemberId() != null && orderPayParamVO.getMerchantMemberId()>0){
                payParamInfo.setMerchantMemberId(orderPayParamVO.getMerchantMemberId());
            }

            Long partnerMemberId = orderPayParamVO.getPartnerMemberId();
            if(partnerMemberId != null && partnerMemberId > 0){
                payParamInfo.setPartnerMemberId(partnerMemberId);
            }

            payParamInfo.setClientIp(HttpProtocolUtils.getRealIpAddr(request));
            payParamInfo.setCashierId(cashierId);
            payParamInfo.setUserId(userId);
            OrderPayResultDTO resultDTO = orderPayWriteFacade.payOrder(payParamInfo);

            OrderPayResultVO orderPayResultVO = ObjectConvertUtil.map(resultDTO, OrderPayResultVO.class);
            result = Result.SUCESS(orderPayResultVO);
        } catch (OrderException e) {
            logger.error("payOrder Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        } finally {
            return result;
        }
    }

    @ResponseBody
    @RequestMapping("/quickBuy")
    @ApiOperation(value = "快捷购买，增值服务以及会员充值通过此接口下单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<OrderPayResultVO> quickBuy(
            @ApiParam(name = "payParamVO", value = "payParamVO", required = true)
			@RequestBody QuickBuyParamVO quickBuyParamVO) {

		Result<OrderPayResultVO> result = null;
		try {
			logger.info("quickBuyParamVO:" + JsonUtils.toJSONString(quickBuyParamVO));

			Long userId = 0L;
			if (UserLoginSourceEnum.SC.getValue().byteValue() == ThreadLocalContext.getLoginSource().byteValue() || UserLoginSourceEnum.H5.getValue().byteValue() == ThreadLocalContext.getLoginSource().byteValue()) {
				userId = ThreadLocalContext.getUserId();
			}

			Long shopId = ThreadLocalContext.getShopId();
			quickBuyParamVO.setShopId(shopId);
			QuickBuyParamDTO buyParam = getQuickBuyParamDTO(quickBuyParamVO);
			buyParam.setUserId(userId);
			buyParam.setClientIp(HttpProtocolUtils.getRealIpAddr(request));
			OrderPayResultDTO orderPayResultDTO = orderPayWriteFacade.quickBuy(buyParam);
			OrderPayResultVO orderPayResultVO = ObjectConvertUtil.map(orderPayResultDTO, OrderPayResultVO.class);
			result = Result.SUCESS(orderPayResultVO);
		} catch (OrderException e) {
			logger.error("quickBuy Exception:  ", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error("quickBuy Exception: ", e);
			result = Result.FAILURE();
		} finally {
			return result;
		}
	}


    @ResponseBody
    @RequestMapping("/quickPay")
    @ApiOperation(value = "快捷支付，店铺收款码以及POS快捷收银通过此接口下单收银", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<OrderPayResultVO> quickPay(
            @ApiParam(name = "payParamVO", value = "payParamVO", required = true)
            @RequestBody QuickPayParamVO payParamVO) {

        Result<OrderPayResultVO> result = null;
        try {
            logger.info("quickPayParamVO:" + JsonUtils.toJSONString(payParamVO));


            QuickPayParamDTO quickPayParamDTO = new QuickPayParamDTO();
            quickPayParamDTO.setTotalAmount(payParamVO.getTotalAmount());
            quickPayParamDTO.setReceiveCashAmount(payParamVO.getReceiveCashAmount());
            quickPayParamDTO.setCashChangeAmount(payParamVO.getCashChangeAmount());

            quickPayParamDTO.setShopId(ThreadLocalContext.getShopId());
            quickPayParamDTO.setMerchantId(ThreadLocalContext.getMerchantId());

            quickPayParamDTO.setOrderSource(payParamVO.getOrderSource());
            if (OrderSourceEnum.isH5ScEnum(payParamVO.getOrderSource()) ||
                    OrderSourceEnum.QR_CODE.equals(payParamVO.getOrderSource())) {
                quickPayParamDTO.setMemberId(ThreadLocalContext.getMemberId());
                quickPayParamDTO.setMerchantMemberId(ThreadLocalContext.getMerchantMemberId());
                quickPayParamDTO.setUserId(ThreadLocalContext.getUserId());

            } else {
                quickPayParamDTO.setSnNum(ThreadLocalContext.getDid());
                quickPayParamDTO.setCashierId(ThreadLocalContext.getStaffId());
            }
            quickPayParamDTO.setClientIp(HttpProtocolUtils.getRealIpAddr(request));

            if (null == payParamVO.getPayParamList() || payParamVO.getPayParamList().size() <= 0) {
                return Result.FAILURE("支付方式不能为空！");
            }

            List<PayParamDTO> payParamDTOList= new ArrayList<>();
            for (int i = 0; i < payParamVO.getPayParamList().size(); i++) {

                PayParamVO pvo = payParamVO.getPayParamList().get(i);

                PayParamDTO pdto = new PayParamDTO();
                pdto.setBizData(pvo.getBizData());
                pdto.setPayAmount(pvo.getPayAmount());
                pdto.setPaymentScene(PaymentSceneEnum.get(pvo.getPaymentScene()));
                pdto.setPaymentType(PaymentTypeEnum.get(pvo.getPaymentType()));
                payParamDTOList.add(pdto);
            }

            quickPayParamDTO.setPayParamList(payParamDTOList);

            quickPayParamDTO.setOrderNo(payParamVO.getOrderNo());
            OrderPayResultDTO orderPayResultDTO = orderPayWriteFacade.quickPay(quickPayParamDTO);
            OrderPayResultVO orderPayResultVO = ObjectConvertUtil.map(orderPayResultDTO, OrderPayResultVO.class);
            result = Result.SUCESS(orderPayResultVO);
        } catch (OrderException e) {
            logger.error("quickPay Exception: ", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        } finally {
            return result;
        }
    }

}
