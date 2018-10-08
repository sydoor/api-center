package com.lizikj.api.controller.marketing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lizikj.merchandise.enums.YesOrNoEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.marketing.ShopDiscountVO;
import com.lizikj.common.exception.BaseException;
import com.lizikj.common.util.BigDecimalUtil;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.marketing.api.dto.CutPriceActivityDetailQueryDTO;
import com.lizikj.marketing.api.dto.ShopActivityDTO;
import com.lizikj.marketing.api.enums.ActivityTemplateEnum;
import com.lizikj.marketing.api.enums.CutModeEnum;
import com.lizikj.marketing.api.enums.MarketingActivityStatusEnum;
import com.lizikj.marketing.api.enums.ShopDiscountActivityEnum;
import com.lizikj.marketing.api.facade.IActivityTemplateReadFacade;
import com.lizikj.marketing.api.utils.CutPriceUtils;
import com.lizikj.member.dto.MerchantHighestDiscountDTO;
import com.lizikj.member.enums.MemberDiscountTypeEnum;
import com.lizikj.member.facade.IMemberSettingFacade;
import com.lizikj.merchandise.dto.GoodsDTO;
import com.lizikj.merchandise.facade.IGoodsReadFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 优惠活动api
 * @author lijundong 
 * @date 2017年8月31日 下午3:33:11
 */
@Controller
@RequestMapping("marketing/discount")
@SuppressWarnings("unchecked")
@Api(value = "marketing", tags = "店铺优惠信息")
public class ShopDiscountController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IActivityTemplateReadFacade activityTemplateReadFacade;
	
	@Autowired
	private IGoodsReadFacade goodsReadFacade;
	
	@Autowired
	private IMemberSettingFacade memberSettingFacade;
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ApiOperation(value = "获取店铺的优惠信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<ShopDiscountVO>> list(){
		Long merchantId = ThreadLocalContext.getMerchantId();
		long shopId = ThreadLocalContext.getShopId();
		logger.info("获取店铺的优惠信息 | shopId={}, merchantId={}", shopId, merchantId);
		
		List<ShopDiscountVO> discountVOs = new ArrayList<ShopDiscountVO>();
		try {
			//封装会员优惠信息
			MerchantHighestDiscountDTO discountDTO = memberSettingFacade.findHighestDiscount(merchantId);
			if(null != discountDTO && discountDTO.getMemberDiscountTypeEnum() != MemberDiscountTypeEnum.NONE_DISCOUNT){
				ShopDiscountVO discountVO = new ShopDiscountVO();
				discountVO.setType(ShopDiscountActivityEnum.MEMBER.getType());
				switch (discountDTO.getMemberDiscountTypeEnum()) {
					case MEMBER_PRICE_BENEFIT:
						discountVO.setDescribe("会员享受会员价美食");
						break;
					case MEMBER_LEVEL_DISCOUNT:
						//8折=0.8,所以要乘以10
						double discount = BigDecimalUtil.mul(discountDTO.getDiscount(), 10);
						discountVO.setDescribe("会员最高可享"+discount+"折优惠");
						break;
					default:
						break;
				}
				discountVOs.add(discountVO);
			}
			
			//获取营销活动
			List<ActivityTemplateEnum> templateList = new ArrayList<ActivityTemplateEnum>();
			templateList.add(ActivityTemplateEnum.CUT_PRICE);
			
			List<ShopActivityDTO> list = activityTemplateReadFacade.listShopActivity(shopId, templateList, MarketingActivityStatusEnum.CONDUCTING);
			for(ShopActivityDTO shopActivityDTO: list){
				ActivityTemplateEnum templateEnum = ActivityTemplateEnum.getEnum(shopActivityDTO.getTemplateId());
				switch (templateEnum) {
					case CUT_PRICE:
						ShopDiscountVO discountVO = new ShopDiscountVO();
						discountVO.setType(ShopDiscountActivityEnum.CUT_PRICE.getType());
						
						//用来存放活动模版属性的map
						Map<String, Object> attrMap = CutPriceUtils.activityToMap(shopActivityDTO);
						//Map转object
						CutPriceActivityDetailQueryDTO cutPriceDTO = ObjectConvertUtil.mapToObject(attrMap, CutPriceActivityDetailQueryDTO.class);
						
						double discount = BigDecimalUtil.div((100 - cutPriceDTO.getDiscount()), 10);
						if(cutPriceDTO.getCutMode() == CutModeEnum.WHOLE_BILL_CUT.getCode()){
							double amount = BigDecimalUtil.div(cutPriceDTO.getStartCutAmounts(), 100);
							//订单满#，邀请好友砍价最低可享#折剁手价
							discountVO.setDescribe("订单满"+amount+"，邀请好友砍价最低可享"+discount+"折剁手价");
						}else if(cutPriceDTO.getCutMode() == CutModeEnum.SINGLE_GOODS_CUT.getCode()){
							GoodsDTO goods = goodsReadFacade.getGoods(cutPriceDTO.getGoodsId(), YesOrNoEnum.NO);
							if(null != goods){
								//****(砍价单品名称)，邀请好友砍价可享#折剁手价
								discountVO.setDescribe(goods.getGoodsName() + "，邀请好友砍价可享"+discount+"折剁手价");
							}
						}
						discountVOs.add(discountVO);
						break;
					default:
						break;
				}
			}
		} catch (BaseException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		}
		return Result.SUCESS(discountVOs);
	}
}
