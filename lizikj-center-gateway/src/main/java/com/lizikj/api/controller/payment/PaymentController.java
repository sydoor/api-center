package com.lizikj.api.controller.payment;

import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.lizikj.api.vo.PageVO;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.payment.*;
import com.lizikj.common.enums.PaymentChannelEnum;
import com.lizikj.common.enums.PaymentSceneEnum;
import com.lizikj.common.enums.PaymentTypeEnum;
import com.lizikj.common.util.*;
import com.lizikj.order.dto.OrderInfoDTO;
import com.lizikj.order.enums.OrderErrorEnum;
import com.lizikj.order.exception.OrderException;
import com.lizikj.order.facade.IOrderReadFacade;
import com.lizikj.order.facade.IOrderWriteFacade;
import com.lizikj.payment.dto.FixPayFlowDTO;
import com.lizikj.payment.facade.IPaymentReadFacade;
import com.lizikj.payment.facade.IPaymentWriteFacade;
import com.lizikj.payment.gateway.dto.PaymentGatewayFlowDTO;
import com.lizikj.payment.gateway.dto.PaymentGatewayFlowQueryDTO;
import com.lizikj.payment.gateway.enums.PayGatewayRequestTypeEnum;
import com.lizikj.payment.pay.dto.LmwPurchasePayFlowDTO;
import com.lizikj.payment.pay.dto.PayFlowAccountDetailDTO;
import com.lizikj.payment.pay.dto.PayFlowDTO;
import com.lizikj.payment.pay.dto.PayReportQueryDTO;
import com.lizikj.payment.pay.enums.PayStatusEnum;
import com.lizikj.payment.pay.enums.RefundEnableEnum;
import com.lizikj.payment.refund.dto.RefundOrderDTO;
import com.lizikj.payment.refund.dto.RefundReportQueryDTO;
import com.lizikj.payment.refund.enums.RefundStatusEnum;
import com.lizikj.user.dto.ThirdUserInfoDTO;
import com.lizikj.user.facade.IThirdUserInfoUserFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping(value = "/payment")
@Api(value = "payment", tags = "支付网关相关接口")
public class PaymentController {

	private final static Logger logger = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	private IPaymentReadFacade paymentReadFacade;
	
	@Autowired
	private IPaymentWriteFacade paymentWriteFacade;

	@Autowired
	private IThirdUserInfoUserFacade thirdUserInfoUserFacade;

	@Autowired
	private IOrderReadFacade orderReadFacade;

	@Autowired
	private IOrderWriteFacade orderWriteFacade;
	
	@RequestMapping(value = "queryPayFlow", method = RequestMethod.POST)
	@ApiOperation(value = "查询支付流水", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<PageVO<PayFlowVO>> queryPayFlow(@RequestParam(value = "pageNo", required = true) int pageNo, @RequestParam(value = "pageSize", required = true) int pageSize, 
			@RequestBody PayFlowQueryVO payFlowQueryVO){
		if(logger.isInfoEnabled()){
			logger.info("根据条件查询支付流水, userId={}, pageNo={}, pageSize={}, payFlowQueryVO={}", ThreadLocalContext.getUserId(), pageNo, pageSize, payFlowQueryVO);
		}
		PageInfo<PayFlowDTO> pageInfo = null;
		List<PayFlowVO> list = null;
		try {
			PayReportQueryDTO payReportQueryDTO = ObjectConvertUtil.copyProperties(PayReportQueryDTO.class, payFlowQueryVO);
			//封装参数
			if(null != payFlowQueryVO.getChannelCode()){
				payReportQueryDTO.setChannelEnum(PaymentChannelEnum.get(payFlowQueryVO.getChannelCode()));
			}
			if(null != payFlowQueryVO.getPaymentTypeCode()){
				payReportQueryDTO.setPaymentTypeEnum(PaymentTypeEnum.get(payFlowQueryVO.getPaymentTypeCode()));
			}
			if(null != payFlowQueryVO.getSceneCode()){
				payReportQueryDTO.setSceneEnum(PaymentSceneEnum.get(payFlowQueryVO.getSceneCode()));
			}
			if(null != payFlowQueryVO.getPayStatus()){
				payReportQueryDTO.setPayStatusEnum(PayStatusEnum.getEnum(payFlowQueryVO.getPayStatus()));
			}
			
			pageInfo = paymentReadFacade.listPayFlowByReport(pageNo, pageSize, payReportQueryDTO);
			
			list = ObjectConvertUtil.mapList(pageInfo.getList(), PayFlowDTO.class, PayFlowVO.class);
		} catch (Exception e) {
			if(logger.isErrorEnabled()){
				logger.error("根据条件查询支付流水error, message={}", e.getMessage());
			}
			return Result.FAILURE(e.getMessage());
		}
		return Result.SUCESS(new PageVO<PayFlowVO>(pageNo, pageSize, pageInfo.getPages(), pageInfo.getTotal(), list));
	}
	
	@RequestMapping(value = "queryRefundFlow", method = RequestMethod.POST)
	@ApiOperation(value = "查询退款流水", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<PageVO<RefundFlowVO>> queryRefundFlow(@RequestParam(value = "pageNo", required = true) int pageNo, @RequestParam(value = "pageSize", required = true) int pageSize, 
			@RequestBody RefundFlowQueryVO refundFlowQueryVO){
		if(logger.isInfoEnabled()){
			logger.info("根据条件查询退款流水, userId={}, pageNo={}, pageSize={}, refundFlowQueryVO={}", ThreadLocalContext.getUserId(), pageNo, pageSize, refundFlowQueryVO);
		}
		PageInfo<RefundOrderDTO> pageInfo = null;
		List<RefundFlowVO> list = null;
		try {
			RefundReportQueryDTO refundReportQueryDTO = ObjectConvertUtil.map(refundFlowQueryVO, RefundReportQueryDTO.class);
			if(null != refundFlowQueryVO.getChannelCode()){
				refundReportQueryDTO.setPaymentChannelEnum(PaymentChannelEnum.get(refundFlowQueryVO.getChannelCode()));
			}
			if(null != refundFlowQueryVO.getPaymentTypeCode()){
				refundReportQueryDTO.setPaymentTypeEnum(PaymentTypeEnum.get(refundFlowQueryVO.getPaymentTypeCode()));
			}
			if(null != refundFlowQueryVO.getSceneCode()){
				refundReportQueryDTO.setPaymentSceneEnum(PaymentSceneEnum.get(refundFlowQueryVO.getSceneCode()));
			}
			if(null != refundFlowQueryVO.getRefundStatus()){
				refundReportQueryDTO.setRefundStatusEnum(RefundStatusEnum.getEnum(refundFlowQueryVO.getRefundStatus()));
			}
			
			pageInfo = paymentReadFacade.listRefundFlowByReport(pageNo, pageSize, refundReportQueryDTO);
			
			list = ObjectConvertUtil.mapList(pageInfo.getList(), RefundOrderDTO.class, RefundFlowVO.class);
		} catch (Exception e) {
			if(logger.isErrorEnabled()){
				logger.error("根据条件查询退款流水error, message={}", e.getMessage());
			}
			return Result.FAILURE(e.getMessage());
		}
		return Result.SUCESS(new PageVO<RefundFlowVO>(pageNo, pageSize, pageInfo.getPages(), pageInfo.getTotal(), list));
	}


	@RequestMapping(value = "cancelPayFlow", method = RequestMethod.GET)
	@ApiOperation(value = "取消支付流水：唤起密码但不支付等情况，等于取消本次支付，需要告诉后台，取消券和红包的冻结状态", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Boolean> cancelPayFlow(
			@ApiParam(name = "orderNo", value = "orderNo", required = true)
			@RequestParam String orderNo,
			@ApiParam(name = "innerTradeNo", value = "innerTradeNo", required = true)
			@RequestParam String innerTradeNo){
		if(logger.isInfoEnabled()){
			logger.info("取消支付流水：, orderNo={}, innerTradeNo={},", orderNo, innerTradeNo);
		}

		try {

			Boolean b = orderWriteFacade.cancelPayFlow(orderNo, innerTradeNo);

		} catch (Exception e) {
			if(logger.isErrorEnabled()){
				logger.error("取消支付流水:cancelPayFlow error, message={}", e.getMessage(), e);
			}
			return Result.FAILURE(e.getMessage());
		}
		return Result.SUCESS(true);
	}
	
	@RequestMapping(value = "queryGatewayFlow", method = RequestMethod.POST)
	@ApiOperation(value = "查询网关流水", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<PageVO<PaymentGatewayFlowVO>> queryGateway(@RequestParam(value = "pageNo", required = true) int pageNo, @RequestParam(value = "pageSize", required = true) int pageSize,
			@RequestBody PaymentGatewayFlowQueryVO gatewayFlowQueryVO){
		
		if(logger.isInfoEnabled()){
			logger.info("根据条件查询网关流水, userId={}, pageNo={}, pageSize={}, gatewayFlowQueryVO={}", ThreadLocalContext.getUserId(), pageNo, pageSize, gatewayFlowQueryVO);
		}
		PageInfo<PaymentGatewayFlowDTO> pageInfo = null;
		List<PaymentGatewayFlowVO> list = null;
		try {
			PaymentGatewayFlowQueryDTO paymentGatewayFlowQueryDTO = ObjectConvertUtil.map(gatewayFlowQueryVO, PaymentGatewayFlowQueryDTO.class);
			if(null != gatewayFlowQueryVO.getChannelCode()){
				paymentGatewayFlowQueryDTO.setPaymentChannelEnum(PaymentChannelEnum.get(gatewayFlowQueryVO.getChannelCode()));
			}
			if(null != gatewayFlowQueryVO.getPaymentTypeCode()){
				paymentGatewayFlowQueryDTO.setPaymentTypeEnum(PaymentTypeEnum.get(gatewayFlowQueryVO.getPaymentTypeCode()));
			}
			if(null != gatewayFlowQueryVO.getSceneCode()){
				paymentGatewayFlowQueryDTO.setPaymentSceneEnum(PaymentSceneEnum.get(gatewayFlowQueryVO.getSceneCode()));
			}
			if(null != gatewayFlowQueryVO.getRequestType()){
				paymentGatewayFlowQueryDTO.setRequestTypeEnum(PayGatewayRequestTypeEnum.getEnum(gatewayFlowQueryVO.getRequestType()));
			}
			pageInfo = paymentReadFacade.listGatewayFlowByReport(pageNo, pageSize, paymentGatewayFlowQueryDTO);
			
			list = ObjectConvertUtil.mapList(pageInfo.getList(), PaymentGatewayFlowDTO.class, PaymentGatewayFlowVO.class);
		} catch (Exception e) {
			if(logger.isErrorEnabled()){
				logger.error("根据条件查询网关流水error, message={}", e.getMessage());
			}
			return Result.FAILURE(e.getMessage());
		}
		return Result.SUCESS(new PageVO<PaymentGatewayFlowVO>(pageNo, pageSize, pageInfo.getPages(), pageInfo.getTotal(), list));
	}
	
	@RequestMapping(value = "fixPayFlow", method = RequestMethod.POST)
	@ApiOperation(value = "修复支付流水", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Object> fixPayFlow(@RequestBody FixPayFlowVO fixPayFlowVO){
		if(logger.isInfoEnabled()){
			logger.info("修复支付流水, userId={}, fixPayFlowVO={}", ThreadLocalContext.getUserId(), fixPayFlowVO);
		}
		
		try {
			FixPayFlowDTO fixPayFlowDTO = ObjectConvertUtil.map(fixPayFlowVO, FixPayFlowDTO.class);
			paymentWriteFacade.fixOrder(fixPayFlowDTO);
		} catch (Exception e) {
			if(logger.isErrorEnabled()){
				logger.error("修复支付流水error", e);
			}
			return Result.FAILURE(e.getMessage());
		}
		return Result.SUCESS();
	}
	
	@RequestMapping(value = "addPayFlow", method = RequestMethod.POST)
	@ApiOperation(value = "人工补单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Object> addPayFlow(@RequestBody String payFlowStr){
		if(logger.isInfoEnabled()){
			logger.info("人工补单, userId={}, payFlowStr={}", ThreadLocalContext.getUserId(), payFlowStr);
		}
		
		try {
			PayFlowDTO payFlowDTO = JsonUtils.parseObject(payFlowStr, new TypeReference<PayFlowDTO>(){});
			if(StringUtils.isBlank(payFlowDTO.getOrderNo())){
				return Result.FAILURE("orderNo can't be empty");
			}
			
			PayFlowAccountDetailDTO accountDetail = payFlowDTO.getAccountDetail();
			if(accountDetail == null){
				return Result.FAILURE("accountDetail can't be null");
			}
			
			if(accountDetail.getMerchantId() == null){
				return Result.FAILURE("merchantId can't be null");
			}
			
			if(accountDetail.getMerchantId() == 0){
				return Result.FAILURE("merchantId can't be zero");
			}
			
			if(accountDetail.getShopId() == null){
				return Result.FAILURE("shopId can't be null");
			}
			
			if(accountDetail.getShopId() == 0){
				return Result.FAILURE("shopId can't be zero");
			}
			
			payFlowDTO.setInnerTradeNo(TradeNoUtil.genPayTradeNo(payFlowDTO.getOrderNo()));
			payFlowDTO.setPayStatus(PayStatusEnum.PAY_SUCCESS.getStatus());
			payFlowDTO.setTradeTime(new Date());
			payFlowDTO.setPayVersion(System.currentTimeMillis());
			payFlowDTO.setRefundEnable(RefundEnableEnum.CAN_REFUND.getValue());
			payFlowDTO.setRefundEndTime(DateUtils.addDays(new Date(), 1));
			
			List<PayFlowDTO> payFlowList = new ArrayList<PayFlowDTO>(2);
			payFlowList.add(payFlowDTO);
			
			paymentWriteFacade.posPaySync(payFlowList);
		} catch (Exception e) {
			if(logger.isErrorEnabled()){
				logger.error("人工补单error", e);
			}
			return Result.FAILURE(e.getMessage());
		}
		return Result.SUCESS();
	}

	@ResponseBody
	@RequestMapping("/lisLiziPurchanse")
	@ApiOperation(value = "获取流水列表信息：李子会员资格购买流水", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<PageInfo<LmwPurchasePayFlowVO>> lisLiziPurchanse(
			@ApiParam(name = "queryParam", value = "queryParam", required = true)
			@NotNull
			@RequestBody LmwPurchasePayFlowVO queryParam
	) {
		Result<PageInfo<LmwPurchasePayFlowVO>> result = null;
		try {

			if (null == queryParam){
				throw new OrderException(OrderErrorEnum.UN_PASSED_ARGUMENT, "传入参数为空");
			}
			LmwPurchasePayFlowDTO queryParamDTO = ObjectConvertUtil.map(queryParam, LmwPurchasePayFlowDTO.class);


			PageInfo<LmwPurchasePayFlowDTO> payFlowDTOPageInfo = paymentReadFacade.lisLiziPurchanse(queryParamDTO);
			PageInfo lmwVoPageInfo = new PageInfo<>();
			if (payFlowDTOPageInfo != null) {
				lmwVoPageInfo = payFlowDTOPageInfo;
				List<LmwPurchasePayFlowVO> infoVOList = ObjectConvertUtil.mapList(payFlowDTOPageInfo.getList(), LmwPurchasePayFlowDTO.class, LmwPurchasePayFlowVO.class);
				toGiveOpenIdAndOrderInfo(infoVOList);
				lmwVoPageInfo.setList(infoVOList);
			}
			result = Result.SUCESS(lmwVoPageInfo);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("lisLiziPurchanse Exception: ", e);
			}
			result = Result.FAILURE(e.getMessage());
		} finally {
			return result;
		}
	}

	private void toGiveOpenIdAndOrderInfo(List<LmwPurchasePayFlowVO> infoVOList) {
		if (CollectionUtils.isListBlank(infoVOList)){
			return;
		}

		infoVOList.forEach(vo ->{
			Long memberId = vo.getMemberId();
			if (! isLongNull(memberId)){
				ThirdUserInfoDTO thirdUserInfoDTO = thirdUserInfoUserFacade.selectByMemberId(memberId);
				if (thirdUserInfoDTO != null){
					vo.setOpenId(thirdUserInfoDTO.getOpenid());
				}
			}

			String orderNo = vo.getOrderNo();
			if (StringUtils.isNotBlank(orderNo)){
				OrderInfoDTO orderInfoDTO = orderReadFacade.getOrderInfoByOrderNoOnly(orderNo);
				if (orderInfoDTO != null){
					vo.setNeedAmount(orderInfoDTO.getNeedAmount());
					vo.setTotalAmount(orderInfoDTO.getTotalAmount());
					vo.setOrderCreateTime(orderInfoDTO.getCreateTime());
					vo.setOrderFinishTime(orderInfoDTO.getPaySuccessTime());
				}
			}
		});
	}




	private boolean isLongNull(Long id) {
		return null == id || 0 == id;
	}
}
