package com.lizikj.api.controller.marketing;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.lizikj.merchandise.enums.YesOrNoEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.lizikj.api.vo.PageVO;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.marketing.CutPageJoinVO;
import com.lizikj.api.vo.marketing.CutPageStartParamsVO;
import com.lizikj.api.vo.marketing.CutPageStartVO;
import com.lizikj.api.vo.marketing.CutPriceActivityDetailCreateVO;
import com.lizikj.api.vo.marketing.CutPriceActivityDetailQueryVO;
import com.lizikj.api.vo.marketing.CutPriceActivityDetailUpdateVO;
import com.lizikj.api.vo.marketing.CutPriceSinglePageVO;
import com.lizikj.api.vo.marketing.CutPriceWholePageVO;
import com.lizikj.api.vo.marketing.FirendUserVO;
import com.lizikj.api.vo.marketing.GoodsVO;
import com.lizikj.api.vo.marketing.HelpCutResultVO;
import com.lizikj.api.vo.marketing.coupon.MerchantCouponInfoVO;
import com.lizikj.common.exception.BaseException;
import com.lizikj.common.util.BigDecimalUtil;
import com.lizikj.common.util.DateUtils;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.marketing.api.dto.CutPageStartParamsDTO;
import com.lizikj.marketing.api.dto.CutPriceActivityDetailDTO;
import com.lizikj.marketing.api.dto.CutPriceActivityDetailQueryDTO;
import com.lizikj.marketing.api.dto.HelpCutResultDTO;
import com.lizikj.marketing.api.dto.OrderCutActivityDTO;
import com.lizikj.marketing.api.dto.OrderCutActivityDetailDTO;
import com.lizikj.marketing.api.dto.ShopActivityDTO;
import com.lizikj.marketing.api.dto.TakeCouponDTO;
import com.lizikj.marketing.api.dto.TakeMerchantCouponResultDTO;
import com.lizikj.marketing.api.enums.ActivityTemplateEnum;
import com.lizikj.marketing.api.enums.CutModeEnum;
import com.lizikj.marketing.api.enums.MarketingActivityStatusEnum;
import com.lizikj.marketing.api.enums.MarketingErrorEnum;
import com.lizikj.marketing.api.exception.MarketingException;
import com.lizikj.marketing.api.facade.IActivityTemplateReadFacade;
import com.lizikj.marketing.api.facade.IActivityTemplateWriteFacade;
import com.lizikj.marketing.api.facade.ICutPriceReadFacade;
import com.lizikj.marketing.api.facade.ICutPriceWriteFacade;
import com.lizikj.marketing.api.facade.IMerchantCouponWriteFacade;
import com.lizikj.marketing.api.utils.CutPriceUtils;
import com.lizikj.merchandise.dto.GoodsDTO;
import com.lizikj.merchandise.facade.IGoodsReadFacade;
import com.lizikj.merchant.dto.ShopDTO;
import com.lizikj.merchant.enums.MerchantErrorEnum;
import com.lizikj.merchant.exception.MerchantException;
import com.lizikj.merchant.facade.IShopReadFacade;
import com.lizikj.order.dto.OrderInfoDTO;
import com.lizikj.order.dto.OrderItemDTO;
import com.lizikj.order.dto.param.GetOrderInfoParamDTO;
import com.lizikj.order.dto.param.query.OrderInfoQueryParamDTO;
import com.lizikj.order.enums.ItemTypeEnum;
import com.lizikj.order.enums.OrderErrorEnum;
import com.lizikj.order.enums.OrderStatusEnum;
import com.lizikj.order.enums.OrderTypeEnum;
import com.lizikj.order.exception.OrderException;
import com.lizikj.order.facade.IOrderReadFacade;
import com.lizikj.shop.api.enums.ShopBizModeEnum;
import com.lizikj.shop.api.facade.IShopSettingReadFacade;
import com.lizikj.user.dto.ThirdUserInfoDTO;
import com.lizikj.user.enums.ThirdUserErrorEnum;
import com.lizikj.user.exception.ThirdUserException;
import com.lizikj.user.facade.IThirdUserInfoUserFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 砍价API接口
 * @author lijundong 
 * @date 2017年7月17日 下午5:00:01
 */
@Controller
@RequestMapping("marketing/cutPrice")
@SuppressWarnings("unchecked")
@Api(value = "marketing", tags = "砍价API接口")
public class CutPriceController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IGoodsReadFacade goodsReadFacade;
	
	@Autowired
	private IActivityTemplateReadFacade activityTemplateReadFacade;
	
	@Autowired
	private IActivityTemplateWriteFacade activityTemplateWriteFacade;
	
	@Autowired
	private ICutPriceReadFacade cutPriceReadFacade;
	
	@Autowired
	private ICutPriceWriteFacade cutPriceWriteFacade;
	
	@Autowired
	private IShopReadFacade shopReadFacade;

	@Autowired
	private IOrderReadFacade orderReadFacade;
	
	@Autowired
	private IThirdUserInfoUserFacade thirdUserInfoUserFacade;
	
	@Autowired
	private IShopSettingReadFacade shopSettingReadFacade;
	
	@Autowired
	private IMerchantCouponWriteFacade merchantCouponWriteFacade;
	
	@ResponseBody
	@RequestMapping(value = "/addCutPrice", method = RequestMethod.POST)
	@ApiOperation(value = "新增砍价活动", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Object> addCutPrice(@RequestBody @ApiParam(name = "新增砍价活动对象", value = "JSON格式表单", required = true) CutPriceActivityDetailCreateVO cutPriceActivityDetailCreateVO){
		long shopId = ThreadLocalContext.getShopId();
		logger.info("addCutPrice | 新增砍价活动 | shopId:{}, cutPriceActivityDetailCreateVO: {}", shopId, cutPriceActivityDetailCreateVO);
		try {
			ShopBizModeEnum shopBizModeEnum = shopSettingReadFacade.findShopBusiMode(shopId);
			if(shopBizModeEnum == ShopBizModeEnum.PAY_FIRST){
				return Result.FAILURE("“先付后食”模式下不支持砍价活动");
			}
			
			//校验当前店铺是否有未完成的订单
			Result<Object> result = validateOrder(shopId);
			if(null != result){
				return result;
			}
			
			//封装活动属性对象
			CutPriceActivityDetailDTO cutPriceActivityDetailDTO = ObjectConvertUtil.copyProperties(CutPriceActivityDetailDTO.class, cutPriceActivityDetailCreateVO);
			
			//参数校验
			validate(cutPriceActivityDetailDTO);
			
			//封装店铺活动对象
			ShopActivityDTO shopActivity = new ShopActivityDTO();
			shopActivity.setShopId(shopId);
			shopActivity.setTemplateId(ActivityTemplateEnum.CUT_PRICE.getValue());
			shopActivity.setStatus(MarketingActivityStatusEnum.CONDUCTING.getStatus());
			shopActivity.setMode(cutPriceActivityDetailCreateVO.getCutMode());
			
			activityTemplateWriteFacade.add(shopId, ActivityTemplateEnum.CUT_PRICE.getValue(), cutPriceActivityDetailDTO, shopActivity);
		} catch (BaseException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		}
		return Result.SUCESS();
	}
	
	@ResponseBody
	@RequestMapping(value = "/editCutPrice", method = RequestMethod.POST)
	@ApiOperation(value = "编辑砍价活动", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Object> editCutPrice(@RequestBody @ApiParam(name = "更新砍价活动对象", value = "JSON格式表单", required = true) CutPriceActivityDetailUpdateVO cutPriceActivityDetailUpdateVO){
		long shopId = ThreadLocalContext.getShopId();
		logger.info("addCutPrice | 编辑砍价活动 | shopId:{}, cutPriceActivityDetailUpdateVO: {}", shopId, cutPriceActivityDetailUpdateVO);
		try {
			CutPriceActivityDetailDTO cutPriceActivityDetailDTO = ObjectConvertUtil.copyProperties(CutPriceActivityDetailDTO.class, cutPriceActivityDetailUpdateVO);
			
			//参数校验
			validate(cutPriceActivityDetailDTO);

			//校验当前店铺是否有未完成的订单
			Result<Object> result = validateOrder(shopId);
			if(null != result){
				return result;
			}
			
			//封装店铺活动对象
			ShopActivityDTO shopActivity = new ShopActivityDTO();
			shopActivity.setActivityId(cutPriceActivityDetailDTO.getCutPriceActivityId());
			shopActivity.setShopId(shopId);
			shopActivity.setTemplateId(ActivityTemplateEnum.CUT_PRICE.getValue());
			shopActivity.setStatus(MarketingActivityStatusEnum.CONDUCTING.getStatus());
			shopActivity.setMode(cutPriceActivityDetailUpdateVO.getCutMode());
			
			activityTemplateWriteFacade.update(shopId, ActivityTemplateEnum.CUT_PRICE.getValue(), cutPriceActivityDetailDTO, shopActivity, false, false);
		} catch (BaseException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.info("addCutPrice | 编辑砍价活动error | e:{}", e);
			return Result.FAILURE(e.getMessage());
		}
		return Result.SUCESS();
	}
	
	@ResponseBody
	@RequestMapping(value = "/getCutPrice", method = RequestMethod.GET)
	@ApiOperation(value = "获取单个砍价活动信息", httpMethod = "GET")
	public Result<CutPriceActivityDetailQueryVO> getCutPrice(@ApiParam(name = "id", value = "砍价活动id", required = true, type = "Long") @RequestParam(name = "id") long cutPriceActivityId){
		logger.info("getCutPrice | 获取单个砍价活动信息 | cutPriceActivityId: {}", cutPriceActivityId);
		
		CutPriceActivityDetailQueryVO queryVO = null;
		try {
			//根据活动id获取具体的活动属性
			ShopActivityDTO activityDTO = activityTemplateReadFacade.getActivityById(cutPriceActivityId);
			
			//用来存放活动模版属性的map
			final Map<String, Object> attrMap = CutPriceUtils.activityToMap(activityDTO);
			
			//Map转object
			queryVO = ObjectConvertUtil.mapToObject(attrMap, CutPriceActivityDetailQueryVO.class);
			
			//不是已结束，都显示为进行中
			if(MarketingActivityStatusEnum.getEnum(queryVO.getStatus()) != MarketingActivityStatusEnum.IS_OVER){
				queryVO.setStatus(MarketingActivityStatusEnum.CONDUCTING.getStatus());
			}
			if(CutModeEnum.SINGLE_GOODS_CUT.getCode().equals(queryVO.getCutMode())){
				//根据美食id获取美食
				GoodsDTO goods = goodsReadFacade.getGoods(queryVO.getGoodsId(), YesOrNoEnum.YES);
				
				com.lizikj.api.vo.merchandise.GoodsVO goodsVO = ObjectConvertUtil.map(goods, com.lizikj.api.vo.merchandise.GoodsVO.class);
				queryVO.setGoods(goodsVO);
			}
		} catch (BaseException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		} catch (Exception e) {
			return Result.FAILURE(e.getMessage());
		}
		return Result.SUCESS(queryVO);
	}
	
	@ResponseBody
	@RequestMapping(value = "/endCutPrice", method = RequestMethod.POST)
	@ApiOperation(value = "结束砍价活动", httpMethod = "POST")
	public Result<Object> endCutPrice(@ApiParam(name = "id", value = "砍价活动id", required = true, type = "Long") @RequestParam(name = "id") long cutPriceActivityId){
		long shopId = ThreadLocalContext.getShopId();
		logger.info("endCutPrice | 结束砍价活动 | shopId:{}, cutPriceActivityId: {}", shopId, cutPriceActivityId);
		try {
			activityTemplateWriteFacade.endActivity(shopId, ActivityTemplateEnum.CUT_PRICE.getValue(), cutPriceActivityId);
		} catch (BaseException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		} catch (Exception e) {
			return Result.FAILURE(e.getMessage());
		}
		return Result.SUCESS();
	}
	
	@ResponseBody
	@RequestMapping(value = "/listEffective", method = RequestMethod.GET)
	@ApiOperation(value = "获取有效的砍价活动列表", httpMethod = "GET")
	public Result<PageVO<CutPriceActivityDetailQueryVO>> listEffective(@ApiParam(name = "pageNo", value = "分页页码", required = true, type = "Integer") @RequestParam(name = "pageNo") int pageNo,
			@ApiParam(name = "pageSize", value = "每一页数量", required = true, type = "Integer") @RequestParam(name = "pageSize") int pageSize){
		long shopId = ThreadLocalContext.getShopId();
		logger.info("listEffective | 获取有效的砍价活动列表 | shopId:{}, pageNo: {}, pageSize: {}", shopId, pageNo, pageSize);
		
		List<CutPriceActivityDetailQueryVO> list = new ArrayList<CutPriceActivityDetailQueryVO>();
		PageInfo<ShopActivityDTO> pageInfo = null;
		try {
			//获取有效活动的分页列表
			pageInfo = activityTemplateReadFacade.list(pageNo, pageSize, shopId, ActivityTemplateEnum.CUT_PRICE.getValue(), MarketingActivityStatusEnum.CONDUCTING.getStatus());
			
			//dtoList转VOlist
			list = ObjectConvertUtil.mapList(CutPriceUtils.activityListConvertVOList(pageInfo.getList()), CutPriceActivityDetailQueryDTO.class, CutPriceActivityDetailQueryVO.class);
		} catch (BaseException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		} catch (Exception e) {
			return Result.FAILURE(e.getMessage());
		}
		return Result.SUCESS(new PageVO<CutPriceActivityDetailQueryVO>(pageNo, pageSize, pageInfo.getPages(), pageInfo.getTotal(), list));
	}
	
	@ResponseBody
	@RequestMapping(value = "/listEnd", method = RequestMethod.GET)
	@ApiOperation(value = "获取已结束的砍价活动列表", httpMethod = "GET")
	public Result<PageVO<CutPriceActivityDetailQueryVO>> listEnd(@ApiParam(name = "pageNo", value = "分页页码", required = true, type = "Integer") @RequestParam(name = "pageNo") int pageNo,
			@ApiParam(name = "pageSize", value = "每一页数量", required = true, type = "Integer") @RequestParam(name = "pageSize") int pageSize){
		long shopId = ThreadLocalContext.getShopId();
		logger.info("listEnd | 获取已结束的砍价活动列表 | shopId:{}, pageNo: {}, pageSize: {}", shopId, pageNo, pageSize);
		
		List<CutPriceActivityDetailQueryVO> list = new ArrayList<CutPriceActivityDetailQueryVO>();
		PageInfo<ShopActivityDTO> pageInfo = null;
		try {
			//获取已结束活动的分页列表
			pageInfo = activityTemplateReadFacade.list(pageNo, pageSize, shopId, ActivityTemplateEnum.CUT_PRICE.getValue(), MarketingActivityStatusEnum.IS_OVER.getStatus());
			
			//dtoList转VOlist
			list = ObjectConvertUtil.mapList(CutPriceUtils.activityListConvertVOList(pageInfo.getList()), CutPriceActivityDetailQueryDTO.class, CutPriceActivityDetailQueryVO.class);
		} catch (BaseException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		} catch (Exception e) {
			return Result.FAILURE(e.getMessage());
		}
		return Result.SUCESS(new PageVO<CutPriceActivityDetailQueryVO>(pageNo, pageSize, pageInfo.getPages(), pageInfo.getTotal(), list));
	}
	
	@ResponseBody
	@RequestMapping(value = "/startPage", method = RequestMethod.POST)
	@ApiOperation(value = "邀请好友砍价页面", httpMethod = "POST")
	public Result<CutPageStartVO> startPage(@ApiParam(name = "发起砍价活动页面参数对象", value = "JSON格式表单", required = true) @RequestBody CutPageStartParamsVO cutPageStartParamsVO){
		long userId = ThreadLocalContext.getUserId();
		long shopId = ThreadLocalContext.getShopId();
		logger.info("startPage | 邀请好友砍价页面 | userId:{}, cutPageStartParamsVO:{}", userId, cutPageStartParamsVO);
		CutPageStartVO cutPageStartVO = new CutPageStartVO();
		try {
			CutPageStartParamsDTO paramsDTO = ObjectConvertUtil.copyProperties(CutPageStartParamsDTO.class, cutPageStartParamsVO);
			paramsDTO.setUserId(userId);//设置活动发起人
			paramsDTO.setShopId(shopId);
			
			//获取砍价详情
			OrderCutActivityDTO orderCutActivityDTO = cutPriceWriteFacade.startCutPrice(paramsDTO);
			
			//如果是好友进入发起页面，返回帮忙砍价数据
			if(orderCutActivityDTO.getUserId().longValue() != userId){
				return Result.SUCESS(createCutPageJoinVO(orderCutActivityDTO));
			}
			
			//封装邀请好友砍价页面的数据
			cutPageStartVO = createCutPageStartVO(orderCutActivityDTO);
		} catch (BaseException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		} catch (Exception e) {
			return Result.FAILURE(e.getMessage());
		}
		return Result.SUCESS(cutPageStartVO);
	}
	
	@ResponseBody
	@RequestMapping(value = "/helpCutPage", method = RequestMethod.GET)
	@ApiOperation(value = "帮朋友砍价页面", httpMethod = "GET")
	public Result<CutPageJoinVO> helpCutPage(@ApiParam(name = "cutId", value = "砍价活动Id", required = true, type = "Long") @RequestParam(name = "cutId") long cutId){
		long userId = ThreadLocalContext.getUserId();
		logger.info("helpCutPage | 帮朋友砍价页面 | userId:{}, cutId:{}", userId, cutId);
		CutPageJoinVO cutPageJoinVO = new CutPageJoinVO();
		try {
			OrderCutActivityDTO orderCutActivityDTO = cutPriceReadFacade.getOrderCutActivityById(cutId);
			//校验订单状态
			OrderInfoDTO order = getOrder(orderCutActivityDTO.getOrderNo());
			if(OrderStatusEnum.isCancelEnum(order.getOrderStatus())){
				throw new MarketingException(MarketingErrorEnum.CUT_PRICE_INVALID);
			}
			
			//判断砍价模式
			CutModeEnum cutModeEnum = CutModeEnum.getEnum(orderCutActivityDTO.getCutMode());
			switch (cutModeEnum) {
				case SINGLE_GOODS_CUT:
					boolean isRemove = true;
					//单品砍价减菜
					for(OrderItemDTO orderItem: order.getOrderItems()){
						//如果订单砍价详情orderItemId在订单物品列表里面找得到，说明没有被减过菜(减菜的不会出现在order.getOrderItems里面)
						if(orderItem.getOrderItemId().longValue() == orderCutActivityDTO.getOrderItemId().longValue()){
							isRemove = false;
							break;
						}
					}
					if(isRemove){
						throw new MarketingException(MarketingErrorEnum.CUT_PRICE_INVALID);
					}
					break;
				case WHOLE_BILL_CUT:
					//整单砍价减过菜
					if(order.getTotalAmount() < orderCutActivityDTO.getStartCutAmounts()){
						throw new MarketingException(MarketingErrorEnum.CUT_PRICE_INVALID);
					}
					break;
				default:
					break;
			}
			
			//如果活动发起人，进入自己分享的页面，则返回自己的活动数据
			if(orderCutActivityDTO.getUserId().longValue() == userId){
				return Result.SUCESS(createCutPageStartVO(orderCutActivityDTO));
			}
			//封装参加砍价活动页面的数据
			cutPageJoinVO = createCutPageJoinVO(orderCutActivityDTO);
		} catch (BaseException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		} catch (Exception e) {
			return Result.FAILURE(e.getMessage());
		}
		return Result.SUCESS(cutPageJoinVO);
	}
	
	@ResponseBody
	@RequestMapping(value = "/helpCut", method = RequestMethod.POST)
	@ApiOperation(value = "帮忙砍一刀", httpMethod = "POST")
	public Result<HelpCutResultVO> helpCut(@ApiParam(name = "cutId", value = "砍价活动Id", required = true, type = "Long") @RequestParam(name = "cutId") long cutId){
		long userId = ThreadLocalContext.getUserId();
		logger.info("start | 帮忙砍一刀 | userId:{}, cutId:{}", userId, cutId);
		HelpCutResultVO helpCutResultVO = null;
		try {
			HelpCutResultDTO helpCutResultDTO = cutPriceWriteFacade.helpCut(userId, cutId);
			if(null != helpCutResultDTO){
				helpCutResultVO = ObjectConvertUtil.copyProperties(HelpCutResultVO.class, helpCutResultDTO);
				
				//砍完价，领取优惠券
				TakeCouponDTO takeCouponDTO = new TakeCouponDTO();
				takeCouponDTO.setUserId(userId);
				takeCouponDTO.setMerchantId(ThreadLocalContext.getMerchantId());
				takeCouponDTO.setCutId(cutId);
				TakeMerchantCouponResultDTO couponResultDTO = merchantCouponWriteFacade.takeCouponOnBargain(takeCouponDTO);
				if(couponResultDTO.getSuccess()){
					MerchantCouponInfoVO merchantCouponInfoVO = ObjectConvertUtil.map(couponResultDTO.getMerchantCouponInfoDTO(), MerchantCouponInfoVO.class);
					helpCutResultVO.setMerchantCouponInfoVO(merchantCouponInfoVO);
				}
			}
		} catch (BaseException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		} catch (Exception e) {
			return Result.FAILURE(e.getMessage());
		}
		return Result.SUCESS(helpCutResultVO);
	}
	/**
	 * 参数校验
	 * @param cutPriceActivityDetailDTO void
	 * @author lijundong
	 * @date 2017年7月19日 上午11:41:47
	 */
	private void validate(CutPriceActivityDetailDTO cutPriceActivityDetailDTO){
		if(null == cutPriceActivityDetailDTO)
			throw new MarketingException(MarketingErrorEnum.PARAMETERS_ERROR);
		CutModeEnum cutModeEnum = CutModeEnum.getEnum(cutPriceActivityDetailDTO.getCutMode());
		if(null == cutModeEnum)
			throw new MarketingException(MarketingErrorEnum.CUT_PRICE_CUT_MODE_NOT_EXIST);
		
		//整单砍价校验起砍金额
		if(cutModeEnum == CutModeEnum.WHOLE_BILL_CUT && (null == cutPriceActivityDetailDTO.getStartCutAmounts() || cutPriceActivityDetailDTO.getStartCutAmounts() == 0))
			throw new MarketingException(MarketingErrorEnum.CUT_PRICE_START_CUT_AMOUNTS_NOT_EXIST);
		
		if(null == cutPriceActivityDetailDTO.getDiscount() || cutPriceActivityDetailDTO.getDiscount() == 0)
			throw new MarketingException(MarketingErrorEnum.CUT_PRICE_DISCOUNT_NOT_EXIST);
		
		if(null == cutPriceActivityDetailDTO.getValidTimeLength() || cutPriceActivityDetailDTO.getValidTimeLength() <= 0)
			throw new MarketingException(MarketingErrorEnum.CUT_PRICE_VALID_TIME_EQUAL_ZERO);
		
		//整单砍价，goodsId，不能填
		if(cutModeEnum == CutModeEnum.WHOLE_BILL_CUT)
			cutPriceActivityDetailDTO.setGoodsId(null);
	}
	
	/**
	 * 根据订单编号获取订单信息
	 * @param orderNo
	 * @return OrderInfoDTO
	 * @author lijundong
	 * @date 2017年9月9日 下午3:01:37
	 */
	private OrderInfoDTO getOrder(String orderNo){
		//获取订单信息
		GetOrderInfoParamDTO getOrderInfoParam = new GetOrderInfoParamDTO();
		getOrderInfoParam.setOrderNo(orderNo);
		getOrderInfoParam.setDetailFlag(0);
		OrderInfoDTO orderInfoDTO = orderReadFacade.getOrderInfoByOrderNo(getOrderInfoParam);
		if(null == orderInfoDTO)
			throw new OrderException(OrderErrorEnum.DB_ORDER_INFO_NOT_EXIST);
		return orderInfoDTO;
	}
	
	/**
	 * 根据用户id获取第三方用户信息
	 * @param userId
	 * @return ThirdUserInfoDTO
	 * @author lijundong
	 * @date 2017年9月9日 下午3:38:49
	 */
	private ThirdUserInfoDTO getThirdUserInfo(long userId){
		ThirdUserInfoDTO thirdUserInfoDTO = new ThirdUserInfoDTO();
		thirdUserInfoDTO.setUserId(userId);
		thirdUserInfoDTO = thirdUserInfoUserFacade.selectUserByExample(thirdUserInfoDTO);
		if(null == thirdUserInfoDTO)
			throw new ThirdUserException(ThirdUserErrorEnum.THIRD_USER_NOT_EXIST);
		return thirdUserInfoDTO;
	}
	
	private String baiFenBi(double obj){
		obj = obj/100;
		NumberFormat percent = NumberFormat.getPercentInstance();
		percent.setMaximumFractionDigits(2);//设置数的小数部分所允许的最大位数(如果超过会四舍五入)
		return percent.format(obj);
	}
	
	/**
	 * 封装发起砍价页面的数据
	 * @param orderCutActivityDTO
	 * @return CutPageStartVO
	 * @author lijundong
	 * @date 2017年9月12日 上午11:35:37
	 */
	private CutPageStartVO createCutPageStartVO(OrderCutActivityDTO orderCutActivityDTO){
		CutPageStartVO cutPageStartVO = new CutPageStartVO();
		
		List<OrderCutActivityDetailDTO> detailList = orderCutActivityDTO.getDetailList();
		
		//判断砍价模式
		CutModeEnum cutModeEnum = CutModeEnum.getEnum(orderCutActivityDTO.getCutMode());
		switch (cutModeEnum) {
			case SINGLE_GOODS_CUT:
				GoodsDTO goods = goodsReadFacade.getGoods(orderCutActivityDTO.getGoodsId(),YesOrNoEnum.NO);
				cutPageStartVO.setGoodsName(goods.getGoodsName());//美食名称
				break;
			case WHOLE_BILL_CUT:
				break;
			default:
				break;
		}
		cutPageStartVO.setSellPrice(orderCutActivityDTO.getTotalFee());//原价
		
		//获取店铺信息
		ShopDTO shop = getShop(orderCutActivityDTO.getShopId());
		
		cutPageStartVO.setShopName(shop.getShopName());//店铺名称
		cutPageStartVO.setShopLogo(shop.getLogoPicId());//店铺logo
		cutPageStartVO.setShopAddress(shop.getProvinceName() + shop.getCityName() + shop.getRegionName() + shop.getAddress());//店铺地址，分享活动用到
		cutPageStartVO.setCutMode(orderCutActivityDTO.getCutMode());//砍价模式
		cutPageStartVO.setAllCutNum(orderCutActivityDTO.getCutNum());//总共可砍次数
		cutPageStartVO.setCurrentCutNum(orderCutActivityDTO.getDetailList().size());//当前已砍次数
		
		//累加总共砍了多少比例
		double cutPercent = 0;
		List<FirendUserVO> firendList = new ArrayList<FirendUserVO>(detailList.size());
		for(OrderCutActivityDetailDTO detail: detailList){
			cutPercent+= detail.getCutPercent();
			
			//设置真爱榜单用户信息
			FirendUserVO firendUserVO = new FirendUserVO();
			ThirdUserInfoDTO thirdUserInfoDTO = getThirdUserInfo(detail.getUserId());
			firendUserVO.setNickname(thirdUserInfoDTO.getNickname());//好友昵称
			firendUserVO.setHeadimg(thirdUserInfoDTO.getHeadimgurl());//好友头像
			firendUserVO.setCutPercent(baiFenBi(detail.getCutPercent()));//每个好友的砍价比例
			firendUserVO.setCutTime(DateUtils.format(detail.getCreateTime(), DateUtils.SEMI_FULL_BAR_PATTERN));//砍价时间
			firendList.add(firendUserVO);
		}
		cutPageStartVO.setCutPercent(baiFenBi(cutPercent));//总砍价比例
		cutPageStartVO.setFirendList(firendList);//真爱列表
		
		//已被砍金额
		double cutAmount = BigDecimalUtil.mul(cutPageStartVO.getSellPrice(), cutPercent) / 100;
		cutAmount = BigDecimalUtil.rounding(cutAmount, 0);//四舍五入
		cutPageStartVO.setCutAmount((long)cutAmount);//总砍价金额
		cutPageStartVO.setCurrentPrice(cutPageStartVO.getSellPrice() - cutPageStartVO.getCutAmount());//当前价=原价-累计被砍金额
		cutPageStartVO.setCutId(orderCutActivityDTO.getCutId());
		return cutPageStartVO;
	}
	
	/**
	 * 封装参加砍价活动页面的数据
	 * @param orderCutActivityDTO
	 * @return CutPageJoinVO
	 * @author lijundong
	 * @date 2017年9月12日 上午11:37:34
	 */
	private CutPageJoinVO createCutPageJoinVO(OrderCutActivityDTO orderCutActivityDTO){
		CutPageJoinVO cutPageJoinVO = new CutPageJoinVO();
		cutPageJoinVO.setCutId(orderCutActivityDTO.getCutId());
		
		List<OrderCutActivityDetailDTO> detailList = orderCutActivityDTO.getDetailList();
		//获取店铺信息
		ShopDTO shop = getShop(orderCutActivityDTO.getShopId());
		
		//判断砍价模式
		CutModeEnum cutModeEnum = CutModeEnum.getEnum(orderCutActivityDTO.getCutMode());
		switch (cutModeEnum) {
			case SINGLE_GOODS_CUT:
				//获取美食信息
				GoodsDTO goods = goodsReadFacade.getGoods(orderCutActivityDTO.getGoodsId(),YesOrNoEnum.YES);
				if(null == goods)
					throw new MarketingException(MarketingErrorEnum.MARKETING_GOODS_NOT_EXIST);
				
				//封装单品砍价信息
				CutPriceSinglePageVO single = new CutPriceSinglePageVO();
				
				GoodsVO goodsVO = new GoodsVO();
				goodsVO.setGoodsName(goods.getGoodsName());//美食名称
				List<Long> mediaIds = goods.getMediaIds();
				if(null != mediaIds && mediaIds.size() > 0){
					goodsVO.setGoodsImg(mediaIds.get(0));
				}
				goodsVO.setSellPrice(orderCutActivityDTO.getTotalFee());//多规则美食，拿砍价录入的价格吧
				
				single.setGoods(goodsVO);
				
				//最低金额=原价*(1-砍价比例)
				single.setMinPrice(goods.getSellPrice() * (100-orderCutActivityDTO.getDiscount()) / 100);

				cutPageJoinVO.setSingle(single);
				break;
			case WHOLE_BILL_CUT:
				//封装整单砍价
				CutPriceWholePageVO whole = new CutPriceWholePageVO();
				
				OrderInfoDTO orderInfoDTO = getOrder(orderCutActivityDTO.getOrderNo());
				
				//封装订单物品列表
				List<OrderItemDTO> itemList = orderInfoDTO.getOrderItems();
				List<GoodsVO> goodsList = new ArrayList<GoodsVO>();
				for(OrderItemDTO dItemDTO: itemList){
					//过滤出计价美食
					if(ItemTypeEnum.isCalcPriceFood(dItemDTO.getItemType().getCode())){
						GoodsVO vo = new GoodsVO();
						vo.setGoodsName(dItemDTO.getGoodsName());
						vo.setSellPrice(dItemDTO.getTotalAmount());
						if(null != dItemDTO.getMediaId()){
							vo.setGoodsImg(dItemDTO.getMediaId());
						}
						goodsList.add(vo);
					}
				}
				whole.setGoodsList(goodsList);
				whole.setDiscount(100 - orderCutActivityDTO.getDiscount());
				
				cutPageJoinVO.setWhole(whole);
				break;
			default:
				break;
		}
		cutPageJoinVO.setSellPrice(orderCutActivityDTO.getTotalFee());
		
		//累加总共砍了多少比例
		double cutPercent = 0;
		for(OrderCutActivityDetailDTO detail: detailList){
			cutPercent+= detail.getCutPercent();
		}
		cutPageJoinVO.setCutPercent(baiFenBi(cutPercent));//设置已砍比例
		
		//已被砍总金额
		double allCutAmount = BigDecimalUtil.mul(cutPageJoinVO.getSellPrice(), cutPercent) / 100;
		allCutAmount = BigDecimalUtil.rounding(allCutAmount, 0);//四舍五入
		cutPageJoinVO.setAllCutAmount((long)allCutAmount);//已砍金额
		
		cutPageJoinVO.setCurrentPrice(cutPageJoinVO.getSellPrice() - cutPageJoinVO.getAllCutAmount());//当前价=原价-已被砍总金额
		
		cutPageJoinVO.setShopName(shop.getShopName());
		cutPageJoinVO.setShopLogo(shop.getLogoPicId());
		cutPageJoinVO.setShopAddress(shop.getProvinceName() + shop.getCityName() + shop.getRegionName() + shop.getAddress());
		cutPageJoinVO.setAllCutNum(orderCutActivityDTO.getCutNum());//总共砍价次数
		cutPageJoinVO.setCurrentCutNum(detailList.size());//当前已砍次数
		
		//遍历砍价详情列表，找到当前用户是否已帮忙砍价, 如果是，标识已砍价状态，并且设置砍价金额
		detailList.forEach(detail ->{
			if(detail.getUserId().longValue() == ThreadLocalContext.getUserId()){
				cutPageJoinVO.setJoin(true);
				
				//已帮忙砍金额
				double currentCutAmount = BigDecimalUtil.mul(cutPageJoinVO.getSellPrice(), detail.getCutPercent()) / 100;
				currentCutAmount = BigDecimalUtil.rounding(currentCutAmount, 0);//四舍五入
				cutPageJoinVO.setCurrentCutAmount((long)currentCutAmount);
				
			}
		});
		
		//获取活动发起人的昵称和头像
		ThirdUserInfoDTO thirdUserInfoDTO = getThirdUserInfo(orderCutActivityDTO.getUserId());
		cutPageJoinVO.setNickname(thirdUserInfoDTO.getNickname());//设置好友昵称
		cutPageJoinVO.setHeadimg(thirdUserInfoDTO.getHeadimgurl());//设置好友头像
		cutPageJoinVO.setCutMode(cutModeEnum.getCode());
		return cutPageJoinVO;
	}
	
	/**
	 * 根据店铺Id获取店铺
	 * @param shopId
	 * @return ShopDTO
	 * @author lijundong
	 * @date 2017年9月12日 上午11:33:12
	 */
	private ShopDTO getShop(long shopId){
		//获取店铺信息
		ShopDTO shop = shopReadFacade.findById(shopId);
		if(null == shop){
			MerchantErrorEnum errorEnum = MerchantErrorEnum.MERCHANT_ERROR_SHOP_NOT_FOUND;
			throw new MerchantException(errorEnum.getCode(), errorEnum.getMessage());
		}
		return shop;
	}
	
	/**
	 * 校验订单
	 * @param shopId
	 * @return Result<Object>
	 * @author lijundong
	 * @date 2017年11月6日 下午3:13:41
	 */
	private Result<Object> validateOrder(long shopId){
		//获取店铺下所有的未完成订单
		OrderInfoQueryParamDTO queryParamDTO = new OrderInfoQueryParamDTO();
		queryParamDTO.setShopId(shopId);
		queryParamDTO.setOrderType(OrderTypeEnum.EAT_FIRST);
		List<OrderStatusEnum> orderStatusList = new ArrayList<OrderStatusEnum>();
		orderStatusList.add(OrderStatusEnum.WAIT_REC);
		orderStatusList.add(OrderStatusEnum.WAIT_PAY);
		queryParamDTO.setOrderStatusList(orderStatusList);//未完成交易的订单状态
		PageInfo<OrderInfoDTO> query = orderReadFacade.query(queryParamDTO, 1, 1000);
		
		if(null != query.getList()){
			//根据所有未完成的订单编号获取订单砍价活动列表
			List<String> orderNos = query.getList().stream().map(orderInfo -> orderInfo.getOrderNo()).collect(Collectors.toList());
			List<OrderCutActivityDTO> orderCutActivityDetailDTOs = cutPriceReadFacade.listByOrderNos(orderNos);
			
			//如果砍价活动还有未完成的订单，则不让修改砍价规则信息
			if(!orderCutActivityDetailDTOs.isEmpty()){
				return Result.FAILURE("当前有订单未完成，无法修改保存营销活动。");
			}
		}
		return null;
	}
}
