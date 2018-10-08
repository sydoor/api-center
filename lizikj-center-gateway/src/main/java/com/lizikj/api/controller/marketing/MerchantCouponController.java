package com.lizikj.api.controller.marketing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.lizikj.api.controller.order.OrderBaseController;
import com.lizikj.api.vo.PageVO;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.marketing.coupon.MerchantCouponAddParamVO;
import com.lizikj.api.vo.marketing.coupon.MerchantCouponInfoVO;
import com.lizikj.api.vo.marketing.coupon.UserCouponInfoVO;
import com.lizikj.api.vo.order.param.OrderInfoParamVO;
import com.lizikj.common.util.CollectionUtils;
import com.lizikj.common.util.DateUtils;
import com.lizikj.common.util.JsonUtils;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.PageParameter;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.marketing.api.dto.MerchantCouponInfoDTO;
import com.lizikj.marketing.api.dto.UserCouponInfoDTO;
import com.lizikj.marketing.api.enums.MerchantCouponCancelStatusEnum;
import com.lizikj.marketing.api.enums.MerchantCouponEffectiveStatusEnum;
import com.lizikj.marketing.api.enums.MerchantCouponTypeEnum;
import com.lizikj.marketing.api.exception.MarketingException;
import com.lizikj.marketing.api.facade.IMerchantCouponReadFacade;
import com.lizikj.marketing.api.facade.IMerchantCouponWriteFacade;
import com.lizikj.merchant.dto.ShopDTO;
import com.lizikj.merchant.facade.IShopReadFacade;
import com.lizikj.order.dto.OrderInfoDTO;
import com.lizikj.order.dto.param.GetOrderInfoParamDTO;
import com.lizikj.order.dto.param.OrderInfoParamDTO;
import com.lizikj.order.enums.OrderInfoDetailEnum;
import com.lizikj.order.enums.OrderSourceEnum;
import com.lizikj.order.exception.OrderException;
import com.lizikj.order.facade.IOrderReadFacade;
import com.lizikj.payment.pay.enums.PayStatusEnum;
import com.lizikj.user.dto.MerchantUserInfoDTO;
import com.lizikj.user.facade.IMerchantUserInfoFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 商家优惠券API
 * @auth zone
 * @date 2017-12-12
 */
@Controller
@RequestMapping("marketing/coupon")
@Api(value = "coupon", tags = "商家优惠券")
public class MerchantCouponController extends OrderBaseController {
	
	private Logger logger = LoggerFactory.getLogger(MerchantCouponController.class);
	
	@Autowired
	IMerchantCouponReadFacade merchantCouponReadFacade;
	
	@Autowired
	IMerchantCouponWriteFacade merchantCouponWriteFacade;
	
	@Autowired
	IShopReadFacade shopReadFacade;
	
	@Autowired
    private IOrderReadFacade orderReadFacade;
	
	@Autowired
	private IMerchantUserInfoFacade merchantUserInfoFacade;
	
	/**
	 * 新增商家优惠券
	 * @param merchantCouponVO
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ApiOperation(value = "新增商家优惠券", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Object> add(
			@RequestBody @ApiParam(name = "优惠券数据", value = "JSON格式表单", required = true) MerchantCouponAddParamVO merchantCouponVO
			) {
		if (merchantCouponVO == null) {
			if (logger.isErrorEnabled()) {
				logger.error("新增商家优惠券，入参为空");
			}
			return Result.FAILURE("传入对象为空");
		}
		if (logger.isInfoEnabled()) {
			logger.info("新增商家优惠券， 数据 {}", JsonUtils.toJSONString(merchantCouponVO));
		}
		
		if (merchantCouponVO.getCouponType() != MerchantCouponTypeEnum.CASH_COUPON.getType()) {
			if (logger.isWarnEnabled()) {
				logger.warn("入参的券类型不正确 couponType is {}", merchantCouponVO.getCouponType());
			}
			merchantCouponVO.setCouponType(MerchantCouponTypeEnum.CASH_COUPON.getType());
		}
		if (StringUtils.isBlank(merchantCouponVO.getCouponName())) {
			return Result.FAILURE("请输入卡券名称");
		}
		if (merchantCouponVO.getFaceValue() == null) {
			return Result.FAILURE("请输入面值");
		}
		if (merchantCouponVO.getFaceValue().longValue() <= 0) {
			return Result.FAILURE("面值不能为0元");
		}
		if (merchantCouponVO.getFaceValue() % 100 != 0) {
			return Result.FAILURE("面值只能输入正整数数字");
		}
		if (merchantCouponVO.getFaceValue() > 99900L) {
			return Result.FAILURE("面值最多999元");
		}
		if (merchantCouponVO.getTotalNum() == null) {
			return Result.FAILURE("请输入发券张数");
		}
		if (merchantCouponVO.getTotalNum().longValue() <= 0) {
			return Result.FAILURE("发券张数不能为0");
		}
		if (merchantCouponVO.getTotalNum() > 99999999) {
			return Result.FAILURE("发券张数最多99999999");
		}
		if (merchantCouponVO.getBeginTime() == null || merchantCouponVO.getEndTime() == null) {
			return Result.FAILURE("请输入有效期");
		}

		merchantCouponVO.setBeginTime(DateUtils.getStartOfDay(merchantCouponVO.getBeginTime()));
		Date endDate = DateUtils.getEndOfDay(merchantCouponVO.getEndTime());
		endDate = DateUtils.formatToDate(endDate, DateUtils.FULL_BAR_PATTERN);
		merchantCouponVO.setEndTime(endDate);
		
		if (!DateUtils.format(merchantCouponVO.getBeginTime(), DateUtils.FULL_SMALL_PATTERN).equals(DateUtils.getCurrent(DateUtils.FULL_SMALL_PATTERN))) {
			return Result.FAILURE("开始时间必须为今天");
		}
		if (merchantCouponVO.getBeginTime().compareTo(merchantCouponVO.getEndTime()) >= 0) {
			return Result.FAILURE("有效期错误");
		}
		long shopId = ThreadLocalContext.getShopId();
		long merchantId = ThreadLocalContext.getMerchantId();
//		long shopId = 25L;
//		long merchantId = 147L;
		MerchantCouponInfoDTO dto = trans2DTO(merchantCouponVO);
		dto.setShopId(shopId);
		dto.setMerchantId(merchantId);
		dto.setCancelStatus(MerchantCouponCancelStatusEnum.UNCANCEL.getStatus());
		dto.setEffectiveStatus(MerchantCouponEffectiveStatusEnum.EFFECTIVE.getStatus());
		dto.setTookNum(0);
		dto.setUsedNum(0);
		try {
			if (merchantCouponWriteFacade.addMerchantCoupon(dto) > 0) {
				return Result.SUCESS();
			}
		} catch (MarketingException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		}
		return Result.FAILURE("保存失败，请重试");
	}

	/**	
	 * 商家券列表
	 * @param status
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/listMerchantCoupon", method = RequestMethod.GET)
	@ApiOperation(value = "商家券列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<PageInfo<MerchantCouponInfoVO>> listMerchantCoupon(
			@RequestParam @ApiParam(name = "status", value = "卡券状态 \n 1:可用\n 2:已失效", required = true, type = "Byte") byte status,
			@RequestParam @ApiParam(name = "pageNo", value = "页码", required = true, type = "Integer") int pageNo,
			@RequestParam @ApiParam(name = "pageSize", value = "页大小", required = true, type = "Integer") int pageSize
			) {
		if (status != 1 && status != 2) {
			if (logger.isWarnEnabled()) {
				logger.warn("卡券状态不对，默认设为1");
			}
		}
		long merchantId = ThreadLocalContext.getMerchantId();

		try {
			PageParameter page = new PageParameter(pageNo, pageSize);
			MerchantCouponEffectiveStatusEnum effectStatus = MerchantCouponEffectiveStatusEnum.getEnum(status);
			PageInfo<MerchantCouponInfoDTO> pageInfo = merchantCouponReadFacade.queryMerchantCouponList(merchantId, effectStatus, page);
			List<MerchantCouponInfoVO> voList = transToVOList(pageInfo.getList());
			return Result.SUCESS(new PageVO<MerchantCouponInfoVO>(pageNo, pageSize, pageInfo.getPages(), pageInfo.getTotal(), voList));
		} catch (MarketingException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		}
	}

	/**
	 * 付款时展示用户券列表
	 * @param userId
	 * @param openid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/listUserCouponOnPay", method = RequestMethod.POST)
	@ApiOperation(value = "付款时展示用户券列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<UserCouponInfoVO>> listUserCouponOnPay(
			@ApiParam(name = "paramVO", value = "paramVO", required = true) @NotNull @RequestBody OrderInfoParamVO orderInfoParamVO) {
		long userId = ThreadLocalContext.getUserId();
		long merchantId = ThreadLocalContext.getMerchantId();
		List<UserCouponInfoDTO> couponList = null;

		OrderInfoParamDTO orderInfoParamDTO = infoVO2DTO(orderInfoParamVO);
		if (logger.isInfoEnabled()) {
			logger.info("付款时展示用户券列表 param is {}", JsonUtils.toJSONString(orderInfoParamDTO));
		}
		
		// 有传orderId则根据此订单获取可用的券
		if (orderInfoParamDTO.getOrderId() != null && orderInfoParamDTO.getOrderId() > 0) {
			Long needAmount = 0L;
			try {
				GetOrderInfoParamDTO getOrderInfoParam = new GetOrderInfoParamDTO();
				getOrderInfoParam.setOrderId(orderInfoParamDTO.getOrderId());
				int flag = OrderInfoDetailEnum.BASE.getCode();
				getOrderInfoParam.setDetailFlag(flag);
				OrderInfoDTO orderInfoDTO = orderReadFacade.getOrderInfoByOrderId(getOrderInfoParam);

				// 不是未支付的单,也不是支付中
				if (orderInfoDTO.getPayStatus().getStatus() != PayStatusEnum.UN_PAY.getStatus() && orderInfoDTO.getPayStatus().getStatus() != PayStatusEnum.PAYING.getStatus()) {
					return Result.SUCESS(new ArrayList<UserCouponInfoVO>());
				}

				// 设置登录相关参数
				orderInfoParamDTO.setShopId(ThreadLocalContext.getShopId());
				if (OrderSourceEnum.isH5ScEnum(orderInfoParamVO.getOrderSource()) || OrderSourceEnum.QR_CODE.equals(orderInfoParamVO.getOrderSource())) {
					orderInfoParamDTO.setMemberId(ThreadLocalContext.getMemberId());
					orderInfoParamDTO.setUserId(ThreadLocalContext.getUserId());
					orderInfoParamDTO.setMerchantMemberId(orderInfoParamVO.getMerchantMemberId());
				} else {
					orderInfoParamDTO.setOrderPersonId(ThreadLocalContext.getStaffId());
					orderInfoParamDTO.setSnNum(ThreadLocalContext.getDid());
				}
				orderInfoDTO = orderReadFacade.calculatePrice(orderInfoParamDTO);
				needAmount = orderInfoDTO.getNeedAmount();
			} catch (OrderException e) {
				if (logger.isWarnEnabled()) {
					logger.warn("订单计价异常，不能查询优惠券信息", e);
				}
				// 订单计价异常就不能选择优惠券了，所以返回空列表
				return Result.SUCESS(new ArrayList<UserCouponInfoVO>());
			}

			try {
				couponList = merchantCouponReadFacade.queryUserCouponListOnPay(merchantId, userId, needAmount, orderInfoParamDTO.getOrderId());
				if (CollectionUtils.isListNotBlank(couponList)) {
					List<UserCouponInfoVO> voList = ObjectConvertUtil.copyListProperties(couponList, UserCouponInfoVO.class);
					for (UserCouponInfoVO one : voList) {
						if (orderInfoParamVO.getUserCouponId() != null && one.getUserCouponId().longValue() == orderInfoParamVO.getUserCouponId().longValue()
								&& one.getLimitMoney().longValue() <= needAmount.longValue()) {
							one.setChosen(true);
							break;
						}
					}
					return Result.SUCESS(voList);
				}
				return Result.SUCESS(new ArrayList<UserCouponInfoVO>());
			} catch (MarketingException e) {
				return Result.FAILURE(e.getCode(), e.getMessage());
			}
		} else {
			// 没有orderId则获取该用户在该商家的所有有效券
			couponList = merchantCouponReadFacade.queryUserCouponList(merchantId, userId);
			if (CollectionUtils.isListNotBlank(couponList)) {
				List<UserCouponInfoVO> voList = ObjectConvertUtil.copyListProperties(couponList, UserCouponInfoVO.class);
				return Result.SUCESS(voList);
			}
			return Result.SUCESS(new ArrayList<UserCouponInfoVO>());
		}
	
}
	
	/**
	 * 更新券数量
	 * @param couponId
	 * @param totalNum
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateNum", method = RequestMethod.POST)
	@ApiOperation(value = "更新券数量", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Object> updateNum(
			@RequestParam @ApiParam(name = "couponId", value = "券ID", required = true, type = "Long") Long couponId,
			@RequestParam @ApiParam(name = "totalNum", value = "总数", required = true, type = "Integer") Integer totalNum
			){
		MerchantCouponInfoDTO merchantCouponInfoDTO = merchantCouponReadFacade.queryMerchantCouponInfo(couponId);

		if (merchantCouponInfoDTO == null) {
			return Result.FAILURE("找不到优惠券信息！");
		}

		if (merchantCouponInfoDTO.getCancelStatus().byteValue() == MerchantCouponCancelStatusEnum.CANCELED.getStatus()) {
			return Result.FAILURE("券已撤下，无法修改");
		}

		if (merchantCouponInfoDTO.getEffectiveStatus().byteValue() == MerchantCouponEffectiveStatusEnum.INVALID.getStatus()) {
			return Result.FAILURE("券已失效，无法修改");
		}
		
		if (merchantCouponInfoDTO.getTookNum().intValue() > totalNum) {
			return Result.FAILURE("发券张数不能小于已领取的数量");
		}
		try {
			if (merchantCouponWriteFacade.updateMerchantCouponNum(couponId, totalNum) > 0) {
				return Result.SUCESS();
			} else {
				return Result.FAILURE("编辑卡券失败");
			}
		} catch (MarketingException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		}
	}
	
	/**
	 * 券详情
	 * @param couponId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	@ApiOperation(value = "券详情", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<MerchantCouponInfoVO> detail(
			@RequestParam @ApiParam(name = "couponId", value = "券ID", required = false, type = "Long") Long couponId){
		MerchantCouponInfoDTO merchantCouponInfoDTO = null;
		try {
			merchantCouponInfoDTO = merchantCouponReadFacade.queryMerchantCouponInfo(couponId);
		} catch (MarketingException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		}
		if (merchantCouponInfoDTO != null) {
			MerchantCouponInfoVO vo = trans2VO(merchantCouponInfoDTO);
			return Result.SUCESS(vo);
		} else {
			return Result.FAILURE("查看卡券详情失败");
		}
	}
	
	/**
	 * 检测卡券名，有重复则返回true，没重复则返回false
	 * @param couponName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkCouponName/{couponName}", method = RequestMethod.GET)
	@ApiOperation(value = "检测卡券名，有重复则返回true，没重复则返回false", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Boolean> checkCouponName(
			@ApiParam(name = "couponName", value = "卡券名", required = true, type = "String") @PathVariable(name = "couponName") String couponName
			){
		long merchantId = ThreadLocalContext.getMerchantId();
		try {
			if (merchantCouponReadFacade.queryMerchantCouponCountByName(merchantId, couponName) > 0) {
				return Result.SUCESS(true);
			} else {
				return Result.SUCESS(false);
			}
		} catch (MarketingException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		}
	}

	/**
	 * 撤下
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cancel/{couponId}", method = RequestMethod.POST)
	@ApiOperation(value = "撤下", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Object> cancel(
			@ApiParam(name = "couponId", value = "卡券ID", required = true, type = "Long") @PathVariable(name = "couponId") Long couponId
			){
		try {
			if (merchantCouponWriteFacade.cancel(couponId) > 0) {
				return Result.SUCESS();
			}
		} catch (MarketingException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		}
		return Result.FAILURE("撤下卡券失败");
	}
	
	/**
	 * 记录手机号
	 * @param mobile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/recordMobile", method = RequestMethod.POST)
	@ApiOperation(value = "记录手机号", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Boolean> recordMobile(
			@RequestParam @ApiParam(name = "mobile", value = "手机号", required = true, type = "String") String mobile,
			@RequestParam @ApiParam(name = "cutId", value = "砍价ID", required = true, type = "Long") Long cutId
		) {
		String pattern = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

		if (!mobile.matches(pattern)) {
			return Result.FAILURE("手机格式错误!");
		}

//		long merchantId = 147L;
//		long shopId = 125L;
//		long userId = 15L;
		long userId = ThreadLocalContext.getUserId();
		long merchantId = ThreadLocalContext.getMerchantId();
		long shopId = ThreadLocalContext.getShopId();
		if (merchantCouponWriteFacade.addMobile(mobile, merchantId, shopId, userId, cutId) > 0) {
			return Result.SUCESS();
		}
		return Result.FAILURE();
	}
	
	/**
	 * 预览商家优惠券，返回预览文本
	 * @param merchantCouponVO
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/previewCoupon", method = RequestMethod.POST)
	@ApiOperation(value = "预览商家优惠券，返回预览文本", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<String> previewCoupon(
			@RequestBody @ApiParam(name = "优惠券数据", value = "JSON格式表单", required = true) MerchantCouponAddParamVO merchantCouponVO
			){
		if (merchantCouponVO == null) {
			if (logger.isErrorEnabled()) {
				logger.error("新增商家优惠券，入参为空");
			}
			return Result.FAILURE("传入对象为空");
		}

		if (logger.isInfoEnabled()) {
			logger.info("预览商家优惠券， 数据 {}", JsonUtils.toJSONString(merchantCouponVO));
		}
		
		if (merchantCouponVO.getCouponType() != MerchantCouponTypeEnum.CASH_COUPON.getType()) {
			if (logger.isWarnEnabled()) {
				logger.warn("入参的券类型不正确 couponType is {}", merchantCouponVO.getCouponType());
			}
			merchantCouponVO.setCouponType(MerchantCouponTypeEnum.CASH_COUPON.getType());
		}
		if (StringUtils.isBlank(merchantCouponVO.getCouponName())) {
			return Result.FAILURE("请输入卡券名称");
		}
		if (merchantCouponVO.getFaceValue() == null) {
			return Result.FAILURE("请输入面值");
		}
		if (merchantCouponVO.getFaceValue().longValue() <= 0) {
			return Result.FAILURE("面值不能为0元");
		}
		if (merchantCouponVO.getFaceValue() % 100 != 0) {
			return Result.FAILURE("面值只能输入正整数数字");
		}
		if (merchantCouponVO.getFaceValue() > 99900L) {
			return Result.FAILURE("面值最多999元");
		}
		if (merchantCouponVO.getTotalNum() == null) {
			return Result.FAILURE("请输入发券张数");
		}
		if (merchantCouponVO.getTotalNum().longValue() <= 0) {
			return Result.FAILURE("发券张数不能为0");
		}
		if (merchantCouponVO.getTotalNum() > 99999999) {
			return Result.FAILURE("发券张数最多99999999");
		}
		if (merchantCouponVO.getBeginTime() == null || merchantCouponVO.getEndTime() == null) {
			return Result.FAILURE("请输入有效期");
		}

		ShopDTO shopDTO=  shopReadFacade.findById(ThreadLocalContext.getShopId());
		
		String data = merchantCouponReadFacade.getCouponReviewText(shopDTO.getShopName(), merchantCouponVO.getFaceValue().longValue(), merchantCouponVO.getEndTime());
		return Result.SUCESS(data);
	}
	
	/**
	 * 用户券的详情
	 * @param userCouponId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/userCouponDetail", method = RequestMethod.GET)
	@ApiOperation(value = "用户券的详情", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<UserCouponInfoVO> userCouponDetail(
			@RequestParam(required = true) @ApiParam(name = "userCouponId", value = "用户券ID", type = "Long") Long userCouponId) {
		UserCouponInfoDTO userCouponInfoDTO = null;
		try {
			userCouponInfoDTO = merchantCouponReadFacade.queryUserCouponById(userCouponId);
		} catch (MarketingException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		}
		if (userCouponInfoDTO != null) {
			UserCouponInfoVO vo = ObjectConvertUtil.map(userCouponInfoDTO, UserCouponInfoVO.class);
			return Result.SUCESS(vo);
		} else {
			return Result.FAILURE("查看用户券详情失败");
		}
	}

	/**
	 * 展示用户卡券列表
	 * @param merchantMemberId
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/listMerchantMemberCoupon/{merchantMemberId}", method = RequestMethod.GET)
	@ApiOperation(value = "展示用户卡券列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<PageVO<UserCouponInfoVO>> listMerchantMemberCoupon(
			@ApiParam(name = "pageNo", value = "分页页码", required = true, type = "Integer") @RequestParam(name = "pageNo") int pageNo,
			@ApiParam(name = "pageSize", value = "每一页数量", required = true, type = "Integer") @RequestParam(name = "pageSize") int pageSize,
			@ApiParam(name = "effectiveStatus", value = "生效状态 INEFFECTIVE:未生效  EFFECTIVE:生效  INVALID:已失效", required = false) @RequestParam(name = "effectiveStatus") MerchantCouponEffectiveStatusEnum effectiveStatus,
			@PathVariable(name = "merchantMemberId", required = true) Long merchantMemberId) {
		long merchantId = ThreadLocalContext.getMerchantId();

		PageParameter page = new PageParameter(pageNo, pageSize);
		PageInfo<UserCouponInfoDTO> pageInfo;
		List<UserCouponInfoVO> voList;
		MerchantUserInfoDTO userInfoDTO = merchantUserInfoFacade.getByMerchantMember(merchantMemberId);
		if (userInfoDTO == null) {
			if (logger.isWarnEnabled()) {
				logger.warn("{} 没找到对应的用户", merchantMemberId);
			}
			return Result.SUCESS();
		}

		try {
			long userId = userInfoDTO.getUserId();
			pageInfo = merchantCouponReadFacade.queryUserCouponList(merchantId, userId, effectiveStatus, page);
			voList = trans2VOList(pageInfo.getList());
		} catch (MarketingException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		} catch (Exception e) {
			return Result.FAILURE("获取用户卡券列表失败");
		}
		return Result.SUCESS(new PageVO<UserCouponInfoVO>(pageNo, pageSize, pageInfo.getPages(), pageInfo.getTotal(), voList));
	}
	
	private MerchantCouponInfoDTO trans2DTO(MerchantCouponAddParamVO merchantCouponVO) {
		MerchantCouponInfoDTO dto = new MerchantCouponInfoDTO();
		dto.setCouponType(merchantCouponVO.getCouponType());
		dto.setCouponName(merchantCouponVO.getCouponName());
		dto.setFaceValue(merchantCouponVO.getFaceValue());
		dto.setTotalNum(merchantCouponVO.getTotalNum());
		dto.setBeginTime(merchantCouponVO.getBeginTime());
		dto.setEndTime(merchantCouponVO.getEndTime());
		dto.setLimitMoney(merchantCouponVO.getLimitMoney());
		return dto;
	}

	private MerchantCouponInfoVO trans2VO(MerchantCouponInfoDTO dto) {
		MerchantCouponInfoVO vo = ObjectConvertUtil.copyProperties(MerchantCouponInfoVO.class, dto);
		vo.setBeginTime(dto.getBeginTime().getTime());
		vo.setEndTime(dto.getEndTime().getTime());
		return vo;
	}
	
	private List<MerchantCouponInfoVO> transToVOList(List<MerchantCouponInfoDTO> list) {
		List<MerchantCouponInfoVO> voList = new ArrayList<MerchantCouponInfoVO>();
		list.forEach(dto -> {
			MerchantCouponInfoVO vo = new MerchantCouponInfoVO();
			vo.setBeginTime(dto.getBeginTime().getTime());
			vo.setCancelStatus(dto.getCancelStatus());
			vo.setCouponId(dto.getCouponId());
			vo.setCouponName(dto.getCouponName());
			vo.setCouponType(dto.getCouponType());
			vo.setEffectiveStatus(dto.getEffectiveStatus());
			vo.setEndTime(dto.getEndTime().getTime());
			vo.setFaceValue(dto.getFaceValue());
			vo.setLimitMoney(dto.getLimitMoney());
			vo.setMerchantId(dto.getMerchantId());
			vo.setShopId(dto.getShopId());
			vo.setTookNum(dto.getTookNum());
			vo.setTotalNum(dto.getTotalNum());
			vo.setUsedNum(dto.getUsedNum());
			voList.add(vo);
		});
		return voList;
	}
	
	private List<UserCouponInfoVO> trans2VOList(List<UserCouponInfoDTO> list) {
		List<UserCouponInfoVO> voList = new ArrayList<UserCouponInfoVO>();
		if (CollectionUtils.isListNotBlank(list)) {
			list.forEach(dto -> {
				UserCouponInfoVO vo = new UserCouponInfoVO();
				vo.setBeginTime(dto.getBeginTime());
				vo.setBeginTimeStamp(dto.getBeginTimeStamp());
				vo.setCanUse(dto.getCanUse());
				vo.setCouponCode(dto.getCouponCode());
				vo.setCouponId(dto.getCouponId());
				vo.setCouponName(dto.getCouponName());
				vo.setCouponType(dto.getCouponType());
				vo.setEffectiveStatus(dto.getEffectiveStatus());
				vo.setEndTime(dto.getEndTime());
				vo.setEndTimeStamp(dto.getEndTimeStamp());
				vo.setFaceValue(dto.getFaceValue());
				vo.setIsOverTime(dto.getIsOverTime());
				vo.setIsUsed(dto.getIsUsed());
				vo.setLimitMoney(dto.getLimitMoney());
				vo.setMerchantId(dto.getMerchantId());
				vo.setUserCouponId(dto.getUserCouponId());
				voList.add(vo);
			});
		}
		return voList;
	}
}
