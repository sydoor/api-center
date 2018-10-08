package com.lizikj.api.controller.payment;

import com.lizikj.api.utils.CenterUtil;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.payment.PaymentGatewayCallbackFlowDetailVO;
import com.lizikj.api.vo.payment.ThirdQueryCommonVO;
import com.lizikj.common.enums.PaymentChannelEnum;
import com.lizikj.common.enums.PaymentTypeEnum;
import com.lizikj.payment.enums.PaymentErrorEnum;
import com.lizikj.payment.exception.PaymentException;
import com.lizikj.payment.facade.IPaymentReadFacade;
import com.lizikj.payment.facade.IPaymentWriteFacade;
import com.lizikj.payment.facade.params.PaymentQueryCardOrderParams;
import com.lizikj.payment.facade.params.PaymentQueryOrderParams;
import com.lizikj.payment.facade.params.PaymentQueryRefundParams;
import com.lizikj.payment.gateway.dto.PaymentGatewayCallbackFlowDetailDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping(value = "/payment")
@Api(value = "payment-third-trade-query", tags = "第三方交易流水查询相关接口")
public class ThirdTradeQueryController {

	private final static Logger logger = LoggerFactory.getLogger(ThirdTradeQueryController.class);

	@Autowired
	private IPaymentWriteFacade paymentWriteFacade;

	@Autowired
	private IPaymentReadFacade paymentReadFacade;


	/**
	 * 已废弃（国通查询不准确见queryCallBack方法）
	 * 交易查询
	 * @params []
	 * @return com.lizikj.api.vo.Result<java.lang.String>
	 * @author zhoufe
	 * @date 2018/5/8 14:49
	 */
	@Deprecated
	@RequestMapping(value = "/third/trade/queryOrder", method = RequestMethod.POST)
	@ApiOperation(value = "交易查询：返回的是包装过的各个通道的原文", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<String> queryOrder(
			@ApiParam(name = "thirdQueryCommonVO", value = "请求参数")
			@RequestBody ThirdQueryCommonVO thirdQueryCommonVO){
		try {

			checkData(thirdQueryCommonVO, false);

			MapperFacade mapperFacade = CenterUtil.getMapperFactory().getMapperFacade();
			PaymentQueryOrderParams paymentQueryOrderParams = mapperFacade.map(thirdQueryCommonVO, PaymentQueryOrderParams.class);

			//整合接口，国通刷卡的用专用的接口查询
			if (isGoutongSwipeCard(thirdQueryCommonVO)) {
				PaymentQueryCardOrderParams paymentQueryCardOrderParams = mapperFacade.map(thirdQueryCommonVO, PaymentQueryCardOrderParams.class);
				String responseResult = paymentWriteFacade.queryCardOrder(paymentQueryCardOrderParams);
                //【返回】
				return Result.SUCESS(responseResult);
			}

			String responseResult = paymentWriteFacade.queryOrder(paymentQueryOrderParams);
			return Result.SUCESS(responseResult);
		} catch (Exception e) {
			if(logger.isErrorEnabled()){
				logger.error("第三方交易流水查询：", e);
			}
			return Result.FAILURE(null);
		}
	}


	/**
	 * 交易查询
	 * 根据我们内部的流水号查询流水回调的详情
	 * @params []
	 * @return com.lizikj.api.vo.Result<java.lang.String>
	 * @author zhoufe
	 * @date 2018/5/8 14:49
	 */
	@RequestMapping(value = "/third/trade/queryCallBack", method = RequestMethod.POST)
	@ApiOperation(value = "根据我们内部的流水号查询", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<PaymentGatewayCallbackFlowDetailVO> queryCallBack(
			@ApiParam(name = "thirdQueryCommonVO", value = "请求参数")
			@RequestBody ThirdQueryCommonVO thirdQueryCommonVO){
		try {

            if (null == thirdQueryCommonVO){
				PaymentException paymentException = new PaymentException(PaymentErrorEnum.PARAMETERS_ERROR, "传入参数为空");
				throw paymentException;
			}

			String innerTradeNo = thirdQueryCommonVO.getInnerTradeNo();
			if (StringUtils.isBlank(innerTradeNo)){
				PaymentException paymentException = new PaymentException(PaymentErrorEnum.PARAMETERS_ERROR, "传入参数内部流水号为空");
				throw paymentException;
			}

			List<PaymentGatewayCallbackFlowDetailDTO> detailDTOS = paymentReadFacade.listCallBackFlowDetailByInnerTradeNo(innerTradeNo);
			if (CollectionUtils.isEmpty(detailDTOS)){
				return Result.SUCESS(null);
			}

			MapperFacade mapperFacade = CenterUtil.getMapperFactory().getMapperFacade();
			List<PaymentGatewayCallbackFlowDetailVO> detailVOS = mapperFacade.mapAsList(detailDTOS, PaymentGatewayCallbackFlowDetailVO.class);

			return Result.SUCESS(detailVOS);
		} catch (Exception e) {
			if(logger.isErrorEnabled()){
				logger.error("第三方交易流水查询queryCallBack：", e);
			}
			return Result.FAILURE(null);
		}
	}

	/**
	 * 是国通刷卡
	 * @params [thirdQueryCommonVO]
	 * @return boolean
	 * @author zhoufe
	 * @date 2018/5/21 16:59
	 */
	private boolean isGoutongSwipeCard(ThirdQueryCommonVO thirdQueryCommonVO) {
		Byte channelCode = thirdQueryCommonVO.getChannelCode();
		Byte paymentTypeCode = thirdQueryCommonVO.getPaymentTypeCode();

		if(null == channelCode || null == paymentTypeCode){
			return false;
		}

		if(PaymentChannelEnum.PAY_CHANNEL_GUOTONG.getCode() == channelCode
		   && PaymentTypeEnum.PAYMENT_TYPE_SWIPECARD.getCode() == paymentTypeCode){
			return true;
		}

		return false;
	}


	/**
	 * 银行卡交易结果查询
	 * @params []
	 * @return com.lizikj.api.vo.Result<java.lang.String>
	 * @author zhoufe
	 * @date 2018/5/8 14:49
	 */
	@RequestMapping(value = "/third/trade/queryCardOrder", method = RequestMethod.POST)
	@ApiOperation(value = "银行卡交易结果查询：有些通道可能没有，返回的是包装过的各个通道的原文", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<String> queryCardOrder(
			@ApiParam(name = "thirdQueryCommonVO", value = "请求参数")
			@RequestBody ThirdQueryCommonVO thirdQueryCommonVO){
		try {

			checkData(thirdQueryCommonVO, false);

			MapperFacade mapperFacade = CenterUtil.getMapperFactory().getMapperFacade();
			PaymentQueryCardOrderParams paymentQueryCardOrderParams = mapperFacade.map(thirdQueryCommonVO, PaymentQueryCardOrderParams.class);

			String responseResult = paymentWriteFacade.queryCardOrder(paymentQueryCardOrderParams);
			return Result.SUCESS(responseResult);
		} catch (Exception e) {
			if(logger.isErrorEnabled()){
				logger.error("第三方银行卡交易结果查询：", e);
			}
			return Result.FAILURE(null);
		}
	}

	/**
	 * 退款查询
	 * @params []
	 * @return com.lizikj.api.vo.Result<java.lang.String>
	 * @author zhoufe
	 * @date 2018/5/8 14:49
	 */
	@RequestMapping(value = "/third/trade/queryRefund", method = RequestMethod.POST)
    @ApiOperation(value = "退款查询，返回的是包装过的各个通道的原文", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<String> queryRefund(
			@ApiParam(name = "thirdQueryCommonVO", value = "请求参数")
			@RequestBody ThirdQueryCommonVO thirdQueryCommonVO){
		try {

			checkData(thirdQueryCommonVO, true);

			MapperFacade mapperFacade = CenterUtil.getMapperFactory().getMapperFacade();
			PaymentQueryRefundParams paymentQueryRefundParams = mapperFacade.map(thirdQueryCommonVO, PaymentQueryRefundParams.class);
			String responseResult = paymentWriteFacade.queryRefund(paymentQueryRefundParams);
			return Result.SUCESS(responseResult);
		} catch (Exception e) {
			if(logger.isErrorEnabled()){
				logger.error("第三方退款查询：", e);
			}
			return Result.FAILURE(null);
		}
	}


	/**
	 * 检查数据
	 * @params [thirdQueryCommonVO]
	 * @return void
	 * @author zhoufe
	 * @date 2018/5/8 15:48
	 */
	private void checkData(ThirdQueryCommonVO thirdQueryCommonVO, boolean isRefund) {

		if(null == thirdQueryCommonVO.getChannelCode()){
			PaymentException paymentException = new PaymentException(PaymentErrorEnum.PARAMETERS_ERROR, "传入的通道号为空");
			if (logger.isErrorEnabled()) {
				logger.error(paymentException.getExtMsg(), paymentException);
			}
			throw paymentException;
		}

		if(null == thirdQueryCommonVO.getPaymentTypeCode()){
			PaymentException paymentException = new PaymentException(PaymentErrorEnum.PARAMETERS_ERROR, "传入的支付方式为空");
			if (logger.isErrorEnabled()) {
				logger.error(paymentException.getExtMsg(), paymentException);
			}
			throw paymentException;
		}

		if(null == thirdQueryCommonVO.getSceneCode()){
			PaymentException paymentException = new PaymentException(PaymentErrorEnum.PARAMETERS_ERROR, "传入的支付场景为空");
			if (logger.isErrorEnabled()) {
				logger.error(paymentException.getExtMsg(), paymentException);
			}
			throw paymentException;
		}

		if(isLongNull(thirdQueryCommonVO.getMerchantId())){
			PaymentException paymentException = new PaymentException(PaymentErrorEnum.PARAMETERS_ERROR, "商户ID为空");
			if (logger.isErrorEnabled()) {
				logger.error(paymentException.getExtMsg(), paymentException);
			}
			throw paymentException;
	    }

		if(isLongNull(thirdQueryCommonVO.getShopId())){
			PaymentException paymentException = new PaymentException(PaymentErrorEnum.PARAMETERS_ERROR, "店铺ID为空");
			if (logger.isErrorEnabled()) {
				logger.error(paymentException.getExtMsg(), paymentException);
			}
			throw paymentException;
		}


		//都是空
		if((isLongNull(thirdQueryCommonVO.getMerchantId())
				&& isLongNull(thirdQueryCommonVO.getShopId())
				&& isLongNull(thirdQueryCommonVO.getUserId())
				&& isLongNull(thirdQueryCommonVO.getCashierId()))){

			PaymentException paymentException = new PaymentException(PaymentErrorEnum.PARAMETERS_ERROR, "商户ID，店铺ID，H5的用户ID，收银员ID为空");
			if (logger.isErrorEnabled()) {
				logger.error(paymentException.getExtMsg(), paymentException);
			}
			throw paymentException;
		}

		if(!isRefund){
			if(StringUtils.isBlank(thirdQueryCommonVO.getInnerTradeNo())){
				PaymentException paymentException = new PaymentException(PaymentErrorEnum.PARAMETERS_ERROR, "传入的内部交易号为空");
				if (logger.isErrorEnabled()) {
					logger.error(paymentException.getExtMsg(), paymentException);
				}
				throw paymentException;
			}
		}

		//如果是退款
		if (!isRefund){
			return;
		}

		if(StringUtils.isBlank(thirdQueryCommonVO.getInnerRefundNo())){
			PaymentException paymentException = new PaymentException(PaymentErrorEnum.PARAMETERS_ERROR, "传入的退款号为空");
			if (logger.isErrorEnabled()) {
				logger.error(paymentException.getExtMsg(), paymentException);
			}
			throw paymentException;
		}
	}

	private boolean isLongNull(Long id) {
		return null == id || 0 == id;
	}

}
