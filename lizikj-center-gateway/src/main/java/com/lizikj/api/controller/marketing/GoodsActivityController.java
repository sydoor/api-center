package com.lizikj.api.controller.marketing;

import java.util.List;
import java.util.Map;

import com.lizikj.merchandise.enums.YesOrNoEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.marketing.CutPriceActivityDetailQueryVO;
import com.lizikj.api.vo.marketing.GoodsActivityVO;
import com.lizikj.api.vo.merchandise.GoodsVO;
import com.lizikj.common.exception.BaseException;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.marketing.api.dto.ShopActivityDTO;
import com.lizikj.marketing.api.enums.ActivityTemplateEnum;
import com.lizikj.marketing.api.enums.CutModeEnum;
import com.lizikj.marketing.api.facade.IActivityTemplateReadFacade;
import com.lizikj.marketing.api.utils.CutPriceUtils;
import com.lizikj.merchandise.dto.GoodsDTO;
import com.lizikj.merchandise.facade.IGoodsReadFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 查看美食参加过的活动
 * @author lijundong 
 * @date 2017年9月5日 下午5:34:14
 */
@Controller
@RequestMapping("marketing")
@SuppressWarnings("unchecked")
@Api(value = "marketing", tags = "美食参与的营销活动")
public class GoodsActivityController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IActivityTemplateReadFacade activityTemplateReadFacade;
	
	@Autowired
	private IGoodsReadFacade goodsReadFacade;
	
	@ResponseBody
	@RequestMapping(value = "/goods/activity", method = RequestMethod.GET)
	@ApiOperation(value = "获取美食参与的营销活动", httpMethod = "GET")
	public Result<GoodsActivityVO> addAdverting(@ApiParam(name = "goodsId", value = "美食Id", required = true) @RequestParam(name = "goodsId", required = true) String goodsId){
		long shopId = ThreadLocalContext.getShopId();
		logger.info("addAdverting | 新增营销广告 | shopId: {}, goodsId: {}", shopId, goodsId);
		final GoodsActivityVO goodsActivityVO = new GoodsActivityVO();
		try {
			//根据店铺id、美食id、活动模版id集合，获取美食参与过的活动列表
			List<ShopActivityDTO> activityList = activityTemplateReadFacade.listActivityByGoodsId(shopId, goodsId, ActivityTemplateEnum.getGoodsTemplateIds());
			activityList.forEach(activity ->{
				ActivityTemplateEnum templateEnum = ActivityTemplateEnum.getEnum(activity.getTemplateId());
				switch (templateEnum) {
					case CUT_PRICE:
						Map<String, Object> attrMap = CutPriceUtils.activityToMap(activity);
						CutPriceActivityDetailQueryVO cutPrice = ObjectConvertUtil.mapToObject(attrMap, CutPriceActivityDetailQueryVO.class);
						if(cutPrice.getCutMode() == CutModeEnum.SINGLE_GOODS_CUT.getCode()){
							//根据美食id获取美食
							GoodsDTO goods = goodsReadFacade.getGoods(cutPrice.getGoodsId(), YesOrNoEnum.YES);
							
							cutPrice.setGoods(ObjectConvertUtil.map(goods, GoodsVO.class));
						}
						goodsActivityVO.setCutPrice(cutPrice);
						break;
					default:
						break;
				}
			});
		} catch (BaseException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		} catch (Exception e) {
			return Result.FAILURE(e.getMessage());
		} 
		return Result.SUCESS(goodsActivityVO);
	}
}
