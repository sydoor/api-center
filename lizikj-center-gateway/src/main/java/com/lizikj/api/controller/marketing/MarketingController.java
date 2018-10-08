package com.lizikj.api.controller.marketing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.lizikj.api.vo.order.UsedStatisticsRedPacketVO;
import com.lizikj.common.enums.UserSourceSceneEnum;
import com.lizikj.common.util.DateUtils;
import com.lizikj.marketing.api.dto.*;
import com.lizikj.order.exception.OrderException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lizikj.api.controller.order.OrderBaseController;
import com.lizikj.api.utils.CenterUtil;
import com.lizikj.api.utils.MarketingUtil;
import com.lizikj.api.utils.ReportUtil;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.marketing.ShopHotSaleAdDetailQueryVO;
import com.lizikj.api.vo.marketing.coupon.ConditionAndUserVO;
import com.lizikj.api.vo.marketing.coupon.CouponItemQueryParamVO;
import com.lizikj.api.vo.marketing.coupon.CouponItemVO;
import com.lizikj.api.vo.marketing.coupon.CouponStaticVO;
import com.lizikj.api.vo.marketing.coupon.DrawParamVO;
import com.lizikj.api.vo.marketing.coupon.GoodsSimpleRecommendVO;
import com.lizikj.api.vo.marketing.coupon.PrizeListVO;
import com.lizikj.api.vo.marketing.coupon.PrizeUseCheckVO;
import com.lizikj.api.vo.marketing.coupon.RedPacketItemQueryParamVO;
import com.lizikj.api.vo.marketing.coupon.RedPacketItemVO;
import com.lizikj.api.vo.marketing.coupon.ShopMarketingInfoVO;
import com.lizikj.api.vo.marketing.coupon.UserCommonStaticsVO;
import com.lizikj.api.vo.marketing.coupon.UserCouponItemDetailVO;
import com.lizikj.api.vo.marketing.coupon.UserRedPacketRecordVO;
import com.lizikj.api.vo.marketing.coupon.UserShareOrderRedPacketVO;
import com.lizikj.api.vo.merchandise.GoodsVO;
import com.lizikj.api.vo.order.OrderInfoVO;
import com.lizikj.api.vo.order.param.OrderInfoParamVO;
import com.lizikj.cache.Cache;
import com.lizikj.common.util.CollectionUtils;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.marketing.api.enums.CardUseEnum;
import com.lizikj.marketing.api.enums.ConditionCheckStatusEnum;
import com.lizikj.marketing.api.enums.MarketingErrorEnum;
import com.lizikj.marketing.api.enums.PlayCodeEnum;
import com.lizikj.marketing.api.enums.RedPacketUseEnum;
import com.lizikj.marketing.api.exception.MarketingException;
import com.lizikj.marketing.api.facade.ICardReadFacade;
import com.lizikj.marketing.api.facade.ICardWriteFacade;
import com.lizikj.marketing.api.facade.IConditionFacade;
import com.lizikj.marketing.api.facade.IPrizeFacade;
import com.lizikj.marketing.api.facade.IPrizePoolItemReadFacade;
import com.lizikj.marketing.api.facade.IRedPacketReadFacade;
import com.lizikj.marketing.api.facade.IRedPacketWriteFacade;
import com.lizikj.marketing.api.facade.IShopMarketingReadFacade;
import com.lizikj.marketing.api.facade.IUserRedPacketRecordReadFacade;
import com.lizikj.merchant.dto.ShopMarketingInfoDTO;
import com.lizikj.order.dto.OrderInfoDTO;
import com.lizikj.order.dto.OrderInfoStatusCountDTO;
import com.lizikj.order.dto.param.GetOrderInfoParamDTO;
import com.lizikj.order.dto.param.OrderInfoParamDTO;
import com.lizikj.order.dto.param.query.OrderInfoQueryParamDTO;
import com.lizikj.order.enums.OrderInfoDetailEnum;
import com.lizikj.order.enums.OrderSourceEnum;
import com.lizikj.order.enums.OrderStatusEnum;
import com.lizikj.order.facade.IOrderReadFacade;
import com.lizikj.user.dto.ThirdUserInfoDTO;
import com.lizikj.user.facade.IThirdUserInfoUserFacade;
import com.lizikj.user.facade.IWechatPublicQrcodeWriteFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ma.glasnost.orika.MapperFacade;

/**
 * 用户优惠券controller
 * @auth zone
 * @date 2018-07-12
 */
@RestController
@RequestMapping("marketing/user")
@Api(value = "userCoupon", tags = "用户优惠券controller")
public class MarketingController extends OrderBaseController {
	private static final Logger logger = LoggerFactory.getLogger(MarketingController.class);

	@Autowired
	private IPrizePoolItemReadFacade prizePoolItemReadFacade;
	@Autowired
    private IOrderReadFacade orderReadFacade;
	@Autowired
	private IShopMarketingReadFacade  shopMarketingReadFacade ;
	@Autowired
	private IRedPacketReadFacade redPacketReadFacade;
	@Autowired
	private IRedPacketWriteFacade redPacketWriteFacade;
	@Autowired
	private Cache cache;
    @Autowired
    private IUserRedPacketRecordReadFacade userRedPacketRecordReadFacade;
    @Autowired
    private IThirdUserInfoUserFacade thirdUserInfoUserFacade;
    @Autowired
    private AdvertingController advertingController;
    @Autowired
	private ICardWriteFacade cardWriteFacade;
    @Autowired
	private ICardReadFacade cardReadFacade;
    @Autowired
	private IConditionFacade conditionFacade;
    @Autowired
    private IWechatPublicQrcodeWriteFacade wechatPublicQrcodeWriteFacade;
    @Autowired
	private IPrizeFacade prizeFacade;


	@RequestMapping(value = "/statics/listCommon", method = RequestMethod.GET)
	@ApiOperation(value = "获取订单可用红包", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<UserCommonStaticsVO> listCommonStatics(){
		Long userId = ThreadLocalContext.getUserId();
		logger.info("/statics/listCommon userId={}",userId);
		CouponItemQueryParamDTO queryParamDTO = new CouponItemQueryParamDTO();
		queryParamDTO.setUserId(userId);
		queryParamDTO.setUseStatusEnum(CardUseEnum.TO_USE);
		int couponNum = cardReadFacade.countByParam(queryParamDTO);
		RedPacketItemQueryParamDTO redPacketItemQueryParamDTO = new RedPacketItemQueryParamDTO();
		redPacketItemQueryParamDTO.setUserId(userId);
		redPacketItemQueryParamDTO.setUseStatusEnum(RedPacketUseEnum.DRAW);
		int packetNum = redPacketReadFacade.countByParam(redPacketItemQueryParamDTO);
		UserCommonStaticsVO vo = new UserCommonStaticsVO();
		vo.setUserId(userId);
		vo.setCouponNum(couponNum);
		vo.setPacketNum(packetNum);
		OrderInfoQueryParamDTO orderInfoQueryParamDTO = new OrderInfoQueryParamDTO();
		orderInfoQueryParamDTO.setUserId(userId);
		orderInfoQueryParamDTO.setOrderSource(OrderSourceEnum.LMW);
		List<OrderInfoStatusCountDTO> orderInfoStatusCountDTOS = orderReadFacade.countOrderStatus(orderInfoQueryParamDTO);
		int orderNum = 0;
		if(orderInfoStatusCountDTOS != null){
			for(OrderInfoStatusCountDTO countDTO : orderInfoStatusCountDTOS){
				//取消的订单不算
//				if (OrderStatusEnum.isCancelEnum(countDTO.getOrderStatus())){
//					continue;
//				}
				orderNum = orderNum + countDTO.getTotal().intValue();
			}
		}
		vo.setOrderNum(orderNum);

		return Result.SUCESS(vo);
	}

	@RequestMapping(value = "/prize/checkUseLimit", method = RequestMethod.GET)
	@ApiOperation(value = "检查卡券和红包是否超出订单可用限制", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<PrizeUseCheckVO> listOrderUsablePrizes(@RequestBody PrizeUseCheckVO prizeUseCheckVO){
		PrizeUseCheckResultDTO prizeUseCheckResultDTO = prizeFacade.checkUseLimit(prizeUseCheckVO.getOrderNo(), prizeUseCheckVO.getPacketCodeList(), prizeUseCheckVO.getCouponCodeList());
		prizeUseCheckVO.setPass(prizeUseCheckResultDTO.getPass());
		prizeUseCheckVO.setReason(prizeUseCheckResultDTO.getFailReason());

		return Result.SUCESS(prizeUseCheckVO);
	}

	@RequestMapping(value = "/prize/listOrderUsablePrizes/{orderNo}", method = RequestMethod.GET)
	@ApiOperation(value = "获取订单可用卡券和红包", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<PrizeListVO> listOrderUsablePrizes(@PathVariable("orderNo")String orderNo){
		PrizeListDTO prizeListDTO = prizeFacade.listUsablePrizes(orderNo);
		PrizeListVO prizeListVO = new PrizeListVO();
		if (prizeListDTO.getCouponItemDTOList() != null) {
			prizeListVO.setCouponItemVOS(ObjectConvertUtil.mapList(prizeListDTO.getCouponItemDTOList(),CouponItemDTO.class,CouponItemVO.class));
		}
		if (prizeListDTO.getRedPacketItemDTOList() != null) {
			prizeListVO.setRedPacketItemVOS(ObjectConvertUtil.mapList(prizeListDTO.getRedPacketItemDTOList(),RedPacketItemDTO.class,RedPacketItemVO.class));
		}

		return Result.SUCESS(prizeListVO);
	}


	@RequestMapping(value = "/coupon/listOrderUsableCoupons/{orderNo}", method = RequestMethod.GET)
	@ApiOperation(value = "获取订单可用卡券", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<CouponItemVO>> listOrderUsableCoupons(@PathVariable("orderNo")String orderNo){
		List<CouponItemDTO> itemDTOS = cardReadFacade.listUsableCoupons(orderNo);

		return Result.SUCESS(ObjectConvertUtil.mapList(itemDTOS,CouponItemDTO.class,CouponItemVO.class));
	}

	@RequestMapping(value = "/packet/listOrderUsablePackets/{orderNo}", method = RequestMethod.GET)
	@ApiOperation(value = "获取订单可用红包", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<RedPacketItemVO>> listOrderUsablePackets(@PathVariable("orderNo")String orderNo){
		List<RedPacketItemDTO> itemDTOS = redPacketReadFacade.listUsablePackets(orderNo);

		return Result.SUCESS(ObjectConvertUtil.mapList(itemDTOS,RedPacketItemDTO.class,RedPacketItemVO.class));
	}

	@RequestMapping(value = "/coupon/donate/{shareCode}", method = RequestMethod.GET)
	@ApiOperation(value = "转赠卡券", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<CouponItemVO> shareOrder(@PathVariable("shareCode")String shareCode) {
		Object value = cache.get("share:" + shareCode);
		if(value == null){
			return Result.FAILURE("该卡券已被其他人领取");
		}
		DrawParamVO drawParamVO = JSONObject.parseObject(String.valueOf(value),DrawParamVO.class);
		if(drawParamVO == null || drawParamVO.getDonateCouponCode() == null){
			return Result.FAILURE("参数有误");
		}
		CouponItemDTO itemDTO = null;
		try {
			itemDTO = cardWriteFacade.donateCoupon(drawParamVO.getDonateCouponCode(), ThreadLocalContext.getUserId());
		} catch (Exception e) {
			logger.info("转赠失败 ",e);
		}
		if(itemDTO == null){
			return Result.FAILURE("转赠失败");
		}
		cache.delete("share:" + shareCode);
		return Result.SUCESS(ObjectConvertUtil.map(itemDTO,CouponItemVO.class));
	}


	@RequestMapping(value = "/packet/getShareKey", method = RequestMethod.POST)
	@ApiOperation(value = "获取分享码", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<String> getShareKey(@RequestBody DrawParamVO drawParamVO) {
		DrawParamDTO drawParamDTO = ObjectConvertUtil.map(drawParamVO,DrawParamDTO.class);
		drawParamDTO.setShareUserId(ThreadLocalContext.getUserId());
		String shareCode = cardWriteFacade.getShareCode(drawParamDTO);

		return Result.SUCESS(shareCode);
	}

	@RequestMapping(value = "/packet/draw/{packetNo}", method = RequestMethod.GET)
	@ApiOperation(value = "领取红包", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<RedPacketItemVO>> drawPackets(@PathVariable(value = "packetNo")String packetNo){
		if(StringUtils.isBlank(packetNo)){
			return Result.FAILURE("参数为空");
		}
		Long userId = ThreadLocalContext.getUserId();

		List<RedPacketItemDTO> dtos = redPacketWriteFacade.drawPackets(userId, packetNo);

		return Result.SUCESS(ObjectConvertUtil.mapList(dtos,RedPacketItemDTO.class,RedPacketItemVO.class));
	}

	@RequestMapping(value = "/packet/drawByPlayCode", method = RequestMethod.POST)
	@ApiOperation(value = "根据邀请码领取红包", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<RedPacketItemVO>> drawByPlayCode(@RequestBody DrawParamVO drawParamVO){
		if(drawParamVO == null || drawParamVO.getPlayCodeEnum() == null){
			return Result.FAILURE("参数为空");
		}
		Long userId = ThreadLocalContext.getUserId();
		DrawParamDTO drawParamDTO = ObjectConvertUtil.map(drawParamVO,DrawParamDTO.class);
		drawParamDTO.setDrawUserId(userId);
		ProcessResult<List<RedPacketItemDTO>> dtoResult = redPacketWriteFacade.drawPackets(drawParamDTO);
		if(dtoResult.getCode() == ProcessResult.Status.FAILURE){
			return Result.FAILURE(dtoResult.getDescription());
		}

		return Result.SUCESS(ObjectConvertUtil.mapList(dtoResult.getData(),RedPacketItemDTO.class,RedPacketItemVO.class));
	}

	@RequestMapping(value = "/packet/drawRemaining/{playCodeEnum}", method = RequestMethod.GET)
	@ApiOperation(value = "根据邀请码领取红包", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Integer> drawRemaining(@PathVariable(value = "playCodeEnum")PlayCodeEnum playCodeEnum){
		if(playCodeEnum == null){
			return Result.FAILURE("参数为空");
		}
		Long userId = ThreadLocalContext.getUserId();
		List<RedPacketDTO> dtos = redPacketReadFacade.listPacketsForDraw(userId, playCodeEnum.getCode());

//		List<RedPacketItemDTO> dtos = redPacketReadFacade.listPackets(userId, playCodeEnum.getCode());

		return Result.SUCESS(dtos == null ? 1:dtos.size());
	}


	@RequestMapping(value = "/packet/shareOrderDraw/{shareCode}", method = RequestMethod.GET)
	@ApiOperation(value = "分享订单领取红包", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<RedPacketItemVO>> shareOrderDraw(@PathVariable("shareCode")String shareCode) {
		String val = cache.getAsStr("share:" + shareCode);
		if(val == null){
			return Result.FAILURE("链接已失效或不存在");
		}
		DrawParamVO drawParamVO = JSONObject.parseObject(val,DrawParamVO.class);
		if(drawParamVO == null || drawParamVO.getOrderNo() == null || drawParamVO.getPlayCodeEnum() == null){
			return Result.FAILURE("参数有误");
		}

		DrawParamDTO drawParamDTO = ObjectConvertUtil.map(drawParamVO, DrawParamDTO.class);
		drawParamDTO.setDrawUserId(ThreadLocalContext.getUserId());
		List<RedPacketItemDTO> redPacketItemDTOS = null;
		try {
			redPacketItemDTOS = redPacketWriteFacade.drawPacketsByShareOrder(drawParamDTO);
		} catch (MarketingException e) {
			return Result.FAILURE("已领取过红包");
		}

		return Result.SUCESS(ObjectConvertUtil.mapList(redPacketItemDTOS,RedPacketItemDTO.class,RedPacketItemVO.class));
	}

	@RequestMapping(value = "/packet/listUserPackets", method = RequestMethod.POST)
	@ApiOperation(value = "查询用户红包", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<RedPacketItemVO>> listUserPackets(@RequestBody RedPacketItemQueryParamVO queryParamVO) {
		queryParamVO.setUserId(ThreadLocalContext.getUserId());
		List<RedPacketItemDTO> itemDTOList = redPacketReadFacade.listByParam(ObjectConvertUtil.map(queryParamVO, RedPacketItemQueryParamDTO.class));

		return Result.SUCESS(ObjectConvertUtil.mapList(itemDTOList,RedPacketItemDTO.class,RedPacketItemVO.class));
	}

	@RequestMapping(value = "/coupon/draw/{cardId}", method = RequestMethod.GET)
	@ApiOperation(value = "领卡券", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<CouponItemVO>> drawCoupons(@PathVariable(value = "cardId")String cardId){
		List<CouponItemDTO> couponItemDTOS = cardWriteFacade.drawCoupons(ThreadLocalContext.getUserId(), cardId);

		return Result.SUCESS(CouponItemVO.from(couponItemDTOS));
	}

	@RequestMapping(value = "/coupon/drawByPlayCode", method = RequestMethod.POST)
	@ApiOperation(value = "根据邀请码参与活动领卡券", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<CouponItemVO>> drawCoupons(@RequestBody DrawParamVO drawParamVO){
		if(drawParamVO == null && drawParamVO.getPlayCodeEnum() == null){
			return Result.FAILURE("参数为空");
		}

		DrawParamDTO drawParamDTO = ObjectConvertUtil.map(drawParamVO,DrawParamDTO.class);
		drawParamDTO.setDrawUserId(ThreadLocalContext.getUserId());

		List<CouponItemDTO> couponItemDTOS = cardWriteFacade.drawCoupons(drawParamDTO);

		return Result.SUCESS(CouponItemVO.from(couponItemDTOS));
	}

	@RequestMapping(value = "/coupon/listUserCoupons", method = RequestMethod.POST)
	@ApiOperation(value = "查询卡券", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<CouponItemVO>> listUserCoupons(@RequestBody CouponItemQueryParamVO queryParamVO) {
		queryParamVO.setUserId(ThreadLocalContext.getUserId());
		List<CouponItemDTO> itemDTOList = prizePoolItemReadFacade.listCouponsByParam(ObjectConvertUtil.map(queryParamVO,CouponItemQueryParamDTO.class));
		List<CouponItemVO> vos =CouponItemVO.from(itemDTOList);
		getAndSetUserName(vos);

		return Result.SUCESS(vos);
	}

	@RequestMapping(value = "/coupon/listUserCouponStatics", method = RequestMethod.POST)
	@ApiOperation(value = "查询卡券汇总", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<CouponStaticVO>> listUserCouponStatics(@RequestBody CouponItemQueryParamVO queryParamVO) {
		queryParamVO.setUserId(ThreadLocalContext.getUserId());
		List<CouponItemDTO> itemDTOList = prizePoolItemReadFacade.listCouponsByParam(ObjectConvertUtil.map(queryParamVO,CouponItemQueryParamDTO.class));

		return Result.SUCESS(getStatics(itemDTOList));
	}
	
	@RequestMapping(value = "/coupon/listOnPay", method = RequestMethod.GET)
	@ApiOperation(value = "支付前列出用户可用券", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Object> listOnPay(@ApiParam(name = "paramVO", value = "paramVO", required = true) @NotNull @RequestBody OrderInfoParamVO orderInfoParamVO) {
		Long userId = ThreadLocalContext.getUserId();
		Long shopId = ThreadLocalContext.getShopId();

		// 查看现在能否使用券
		boolean canUseCoupon = shopMarketingReadFacade.checkSupportCoupon(shopId, new Date());

		if (!canUseCoupon) {
			return Result.SUCESS();
		}
		long money = 0L;
		try {
			OrderInfoParamDTO orderInfoParamDTO = infoVO2DTO(orderInfoParamVO);
			// 设置登录相关参数
			orderInfoParamDTO.setShopId(ThreadLocalContext.getShopId());

			if (OrderSourceEnum.isH5ScEnum(orderInfoParamVO.getOrderSource()) || OrderSourceEnum.QR_CODE.equals(orderInfoParamVO.getOrderSource())) {
				orderInfoParamDTO.setMemberId(ThreadLocalContext.getMemberId());
				orderInfoParamDTO.setUserId(ThreadLocalContext.getUserId());
				if (orderInfoParamDTO.getMerchantMemberId() == null || orderInfoParamDTO.getMerchantMemberId().longValue() <= 0) {
					orderInfoParamDTO.setMerchantMemberId(ThreadLocalContext.getMerchantMemberId());
				}
			} else {
				orderInfoParamDTO.setOrderPersonId(ThreadLocalContext.getStaffId());
				orderInfoParamDTO.setSnNum(ThreadLocalContext.getDid());
				// orderInfoParamDTO.setMerchantMemberId();//infoVO2DTO(orderInfoParamVO)里面传
			}
			OrderInfoDTO orderInfoDTO = orderReadFacade.calculatePrice(orderInfoParamDTO);
			money = orderInfoDTO.getNeedAmount();
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("calculatePrice Exception: ", e);
			}
		}

		List<UserCouponItemDetailDTO> dtoList = null;
		try {
			dtoList = prizePoolItemReadFacade.listUserCouponOnPay(userId, money);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("listUserCouponOnPay Exception: ", e);
			}
			return Result.FAILURE();
		}
		List<UserCouponItemDetailVO> voList = ObjectConvertUtil.copyListProperties(dtoList, UserCouponItemDetailVO.class);
		return Result.SUCESS(voList);
	}

	private List<CouponStaticVO> getStatics(List<CouponItemDTO> dtos){
		Map<String, List<CouponItemDTO>> dtoMap = ReportUtil.groupByKey(dtos, t -> t.getCardId());
		List<CouponStaticVO> staticVOS = new ArrayList<>();
		for(Map.Entry<String,List<CouponItemDTO>> entry:dtoMap.entrySet()){
			if(entry.getValue() != null && !entry.getValue().isEmpty()){
				CouponStaticVO staticVO = ObjectConvertUtil.map(entry.getValue().get(0),CouponStaticVO.class);
				staticVO.setNum(entry.getValue().size());
				staticVO.setSoonExpireNum(getSoonExpireNum(entry.getValue()));
				staticVOS.add(staticVO);
			}
		}

		return staticVOS;
	}


	private int getSoonExpireNum(List<CouponItemDTO> dtos){
		long soonExpireTime = DateUtils.addDays(new Date(),3).getTime();
		int num = 0;
		for(CouponItemDTO itemDTO:dtos){
			if(itemDTO.getExpireTime() != null && itemDTO.getExpireTime().getTime() <= soonExpireTime){
				num++;
			}
		}

		return num;
	}

		/**
		 * 撩美味用户分享红包领取列表
		 * @params [orderNo]
		 * @return com.lizikj.api.vo.Result<com.lizikj.api.vo.marketing.coupon.UserShareOrderRedPacketVO>
		 * @author zhoufe
		 * @date 2018/7/25 17:18
		 */
	@RequestMapping(value = "/redPacket/listUserShareOrderRedPacket", method = RequestMethod.GET)
	@ApiOperation(value = "撩美味用户分享红包领取列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<UserShareOrderRedPacketVO> listUserShareOrderRedPacket(
			@ApiParam(name = "orderNo", value = "订单号")
			@RequestParam(name = "orderNo") String orderNo,
			@ApiParam(name = "userSourceScene", value = "分享场景3  结账前分享 ,     4  结账后分享")
			@RequestParam(name = "userSourceScene", defaultValue = "0") int userSourceScene) {
		UserShareOrderRedPacketVO userShareOrderRedPacketVO = new UserShareOrderRedPacketVO();
		Long invitedUserId = ThreadLocalContext.getUserId();
		try {
			//封装订单信息
			OrderInfoVO orderInfoVO = assemblyOrderInfo(userShareOrderRedPacketVO, null, orderNo, userSourceScene);
			Long shopId =  0L;
			Long orderId = 0L;
			Long userId = 0L;
			if (orderInfoVO != null){
				shopId = orderInfoVO.getShopId();
				orderId = orderInfoVO.getOrderId();
				userId = orderInfoVO.getUserId();
				assemblyShareUser(userShareOrderRedPacketVO, userId);
			}

			//广告推荐
			assemblyShopGoodsAdverting(userShareOrderRedPacketVO);


			//封装红包记录信息
			assemblyRedPacketRecord(userShareOrderRedPacketVO, orderId, orderNo);

			//封装店铺信息
			assemblyShopInfo(userShareOrderRedPacketVO, shopId);
			
			//设置分享的公众号二维码
			if (userId != null && userId > 0) {
				String qrcodeUrl = wechatPublicQrcodeWriteFacade.makeTempQrcodeUrlForInvite(userId, invitedUserId, userSourceScene, orderNo);
				userShareOrderRedPacketVO.setQrcodeUrl(qrcodeUrl);
			}

		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("listUserShareOrderRedPacket Exception: ", e);
			}
			return Result.FAILURE(e.getMessage());
		}

		return Result.SUCESS(userShareOrderRedPacketVO);
	}
	
	/**
	 * 被邀请人获取邀请二维码
	 * @param shareUserId
	 * @return
	 */
	@RequestMapping(value = "/invited/getQrcodeUrl", method = RequestMethod.GET)
	@ApiOperation(value = "被邀请人获取邀请二维码", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<String> getQrcodeUrlWhileInvite(
			@ApiParam(name = "shareUserId", value = "分享者userid")
			@RequestParam(name = "shareUserId") long shareUserId) {
		Long userId = ThreadLocalContext.getUserId();
		String qrcodeUrl = "";
		
		// 设置分享的公众号二维码
		if (userId != null && userId > 0) {
			qrcodeUrl = wechatPublicQrcodeWriteFacade.makeTempQrcodeUrlForInvite(shareUserId, userId, 5, null);
		}
		return Result.SUCESS(qrcodeUrl);
	}
	
	/**
	 * 获取赠送券二维码
	 * @param shareUserId
	 * @return
	 */
	@RequestMapping(value = "/give/getQrcodeUrl", method = RequestMethod.GET)
	@ApiOperation(value = "获取赠送券二维码", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<String> getQrcodeUrlWhileGive(
			@ApiParam(name = "shareCode", value = "分享码", type = "String") @RequestParam(name = "shareCode") String shareCode,
			@ApiParam(name = "userSourceScene", value = "来源场景 填6表示赠送券") @RequestParam(name = "userSourceScene") int userSourceScene,
			@ApiParam(name = "shareUserId", value = "分享者userId") @RequestParam(name = "shareUserId") long shareUserId) {
		Long invitedUserId = ThreadLocalContext.getUserId();
		String qrcodeUrl = wechatPublicQrcodeWriteFacade.makeTempQrcodeUrlForGive(userSourceScene, shareCode, invitedUserId, shareUserId);
		return Result.SUCESS(qrcodeUrl);
	}

	/**
	 * 补用户名
	 * @param vos
	 */
	private void getAndSetUserName(List<CouponItemVO> vos) {
		for(CouponItemVO vo:vos){
			List<ConditionAndUserDTO> conditions = conditionFacade.listConditionWithUserInfo(vo.getCode(), ConditionCheckStatusEnum.NOT_CHECK);
			List<ConditionAndUserVO> conditionVOs = new ArrayList<>();
			conditions.forEach(cond -> {
				ConditionAndUserVO condVo = ObjectConvertUtil.map(cond,ConditionAndUserVO.class);
				if (condVo.getParenUserId() != null) {
					ThirdUserInfoDTO infoDTO = new ThirdUserInfoDTO();
					infoDTO.setUserId(condVo.getParenUserId());
					ThirdUserInfoDTO userInfoDTO = thirdUserInfoUserFacade.selectUserByExample(infoDTO);
					if (userInfoDTO != null) {
						condVo.setParentUserName(userInfoDTO.getNickname());
					}
				}

				conditionVOs.add(condVo);
			});

			vo.setConditionAndUserVOS(conditionVOs);
		}
	}

	/**
	 * 封装分享用户的信息
	 * @params [userShareOrderRedPacketVO, userId]
	 * @return void
	 * @author zhoufe
	 * @date 2018/7/26 9:34
	 */
	private void assemblyShareUser(UserShareOrderRedPacketVO userShareOrderRedPacketVO, Long userId) {
		if (! isLongNuull(userId)){
            ThirdUserInfoDTO thirdUserInfoParamDTO = new ThirdUserInfoDTO();
            thirdUserInfoParamDTO.setUserId(userId);
            ThirdUserInfoDTO thirdUserInfoDTO = thirdUserInfoUserFacade.selectUserByExample(thirdUserInfoParamDTO);
            if (thirdUserInfoDTO != null){
                userShareOrderRedPacketVO.setShareUserName(thirdUserInfoDTO.getNickname());
                userShareOrderRedPacketVO.setShareUserPortraitUrl(thirdUserInfoDTO.getHeadimgurl());
            }
        }
	}

	private boolean isLongNuull(Long userId) {
		return null == userId || 0 == userId;
	}

	/**
	 * 封装店铺信息
	 * @params [userShareOrderRedPacketVO, shopId]
	 * @return void
	 * @author zhoufe
	 * @date 2018/7/25 16:37
	 */
	private void assemblyShopInfo(UserShareOrderRedPacketVO userShareOrderRedPacketVO, Long shopId) {
		ShopMarketingInfoDTO shopMarketingInfoDTO = shopMarketingReadFacade.findMarketingInfoByShopId(shopId);
		if (shopMarketingInfoDTO != null){
            ShopMarketingInfoVO shopMarketingInfoVO = ObjectConvertUtil.map(shopMarketingInfoDTO,ShopMarketingInfoVO.class);
            userShareOrderRedPacketVO.setShopMarketingInfo(shopMarketingInfoVO);
        }
	}

	/**
	 * 封装红包记录信息
	 * @params [orderId, orderNo]
	 * @return void
	 * @author zhoufe
	 * @date 2018/7/25 16:36
	 */
	private void assemblyRedPacketRecord(UserShareOrderRedPacketVO userShareOrderRedPacketVO, Long orderId, String orderNo) {
		UserRedPacketRecordDTO userRedPacketRecordDTO = new UserRedPacketRecordDTO();
		userRedPacketRecordDTO.setPlayCodeEnum(PlayCodeEnum.RED_PACKET_FROM_CODE);
		userRedPacketRecordDTO.setOrderNo(orderNo);
		List<UserRedPacketRecordDTO> userRedPacketRecordDTOS = userRedPacketRecordReadFacade.selectByParam(userRedPacketRecordDTO);
		List<UserRedPacketRecordVO> userRedPacketRecordVOS = null;
		if (CollectionUtils.isListNotBlank(userRedPacketRecordDTOS)){
             userRedPacketRecordVOS = ObjectConvertUtil.mapList(userRedPacketRecordDTOS, UserRedPacketRecordDTO.class, UserRedPacketRecordVO.class);
            userRedPacketRecordVOS.forEach(target -> {
                ThirdUserInfoDTO thirdUserInfoParamDTO = new ThirdUserInfoDTO();
                thirdUserInfoParamDTO.setUserId(target.getUserId());
                ThirdUserInfoDTO thirdUserInfoDTO = thirdUserInfoUserFacade.selectUserByExample(thirdUserInfoParamDTO);
                if (thirdUserInfoDTO != null){
					target.setUserName(thirdUserInfoDTO.getNickname());
                    target.setUserPortraitUrl(thirdUserInfoDTO.getHeadimgurl());
                }
            });
        }

		userShareOrderRedPacketVO.setUserRedPacketRecords(userRedPacketRecordVOS);
	}

	/**
	 * 封装订单信息
	 * @params [userShareOrderRedPacketVO, orderId, orderNo]
	 * @return void
	 * @author zhoufe
	 * @date 2018/7/25 16:35
	 */
	private OrderInfoVO assemblyOrderInfo(UserShareOrderRedPacketVO userShareOrderRedPacketVO, Long orderId, String orderNo, int userSourceScene) {
		GetOrderInfoParamDTO getOrderInfoParamDTO = new GetOrderInfoParamDTO();
		getOrderInfoParamDTO.setOrderNo(orderNo);
		getOrderInfoParamDTO.setOrderId(orderId);
		//OrderInfoDetailEnum.BASE.getCode() | OrderInfoDetailEnum.CONTENT.getCode() | OrderInfoDetailEnum.DISCOUNT.getCode()
		int detailFlag = OrderInfoDetailEnum.ALL.getCode();
		getOrderInfoParamDTO.setDetailFlag(detailFlag);

		OrderInfoDTO orderInfoDTO = orderReadFacade.getOrderInfoByOrderNo(getOrderInfoParamDTO);
		if (null == orderInfoDTO){
			throw new MarketingException(MarketingErrorEnum.PARAMETERS_ERROR, String.format("根据订单号(%s)已经查询不到订单信息了！", orderNo));
		}

		MapperFacade mapperFacade = CenterUtil.getMapperFactory().getMapperFacade();
		OrderInfoVO orderInfoVO = mapperFacade.map(orderInfoDTO, OrderInfoVO.class);


		//结账前
		if (UserSourceSceneEnum.BEFROE_PAY_QRCODE.getCode().equals(userSourceScene)) {
				if (StringUtils.isBlank(orderNo)) {
					throw new OrderException("传入订单号为空！");
				}
				String key = CenterUtil.getSaveTempOrderPriceKey(orderNo);
				Object o = cache.get(key);
				if (o != null) {
					String oStr = (String) o;
					UsedStatisticsRedPacketVO voFromRedis = JSONObject.parseObject(oStr, UsedStatisticsRedPacketVO.class);
					if (voFromRedis != null) {
						//修改
						Long benefitAmount = voFromRedis.getBenefitAmount();
						if (benefitAmount != null){
							orderInfoVO.setBenefitAmount(benefitAmount);
						}
						//修改
						Long totalAmount = voFromRedis.getTotalAmount();
						if (totalAmount != null){
							orderInfoVO.setTotalAmount(totalAmount);
						}
					}
				}

			}

		userShareOrderRedPacketVO.setOrderInfo(orderInfoVO);

		return orderInfoVO;
	}

	/**
	 * 封装店铺美食推荐信息
	 * @params [userShareOrderRedPacketVO]
	 * @return void
	 * @author zhoufe
	 * @date 2018/7/25 16:34
	 */
	private void assemblyShopGoodsAdverting(UserShareOrderRedPacketVO userShareOrderRedPacketVO) {
		Result<List<ShopHotSaleAdDetailQueryVO>> listResult = advertingController.list();
		if (listResult != null){
            List<ShopHotSaleAdDetailQueryVO> dataList = listResult.getData();
            if (CollectionUtils.isListNotBlank(dataList)){
                List<GoodsSimpleRecommendVO> shopGoodsRecommendsVOLitst = new ArrayList<>();
                dataList.forEach(target -> {
                    GoodsSimpleRecommendVO goodsSimpleRecommendVO = new GoodsSimpleRecommendVO();
                    GoodsVO goodsVO = target.getGoods();
                    String goodsName = null;
                    if (goodsVO != null){
                        goodsName = goodsVO.getGoodsName();
                    }
                    goodsSimpleRecommendVO.setGoodsName(goodsName);
                    goodsSimpleRecommendVO.setMediaId(target.getMediaId());

                    shopGoodsRecommendsVOLitst.add(goodsSimpleRecommendVO);
                });

                userShareOrderRedPacketVO.setShopGoodsRecommends(shopGoodsRecommendsVOLitst);
            }
        }
	}


	/**
	 * 订单分享 redis key
	 * @param drawParamVO
	 * @return
	 */
	private String getShareCode(DrawParamVO drawParamVO){
		String md5 = MarketingUtil.md5(JSONObject.toJSONString(drawParamVO));

		return md5;
	}

}
