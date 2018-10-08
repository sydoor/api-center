package com.lizikj.api.controller.marketing;

import java.util.ArrayList;
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

import com.github.pagehelper.PageInfo;
import com.lizikj.api.vo.PageVO;
import com.lizikj.api.vo.Result;
import com.lizikj.common.exception.BaseException;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.marketing.api.dto.CutPriceActivityDetailQueryDTO;
import com.lizikj.marketing.api.dto.FullDiscountActivityDetailDTO;
import com.lizikj.marketing.api.dto.ShopActivityDTO;
import com.lizikj.marketing.api.enums.ActivityTemplateEnum;
import com.lizikj.marketing.api.enums.CutModeEnum;
import com.lizikj.marketing.api.enums.MarketingActivityStatusEnum;
import com.lizikj.marketing.api.facade.IActivityTemplateReadFacade;
import com.lizikj.marketing.api.facade.IActivityTemplateWriteFacade;
import com.lizikj.marketing.api.utils.CutPriceUtils;
import com.lizikj.marketing.api.utils.FullDiscountUtils;
import com.lizikj.merchandise.dto.GoodsDTO;
import com.lizikj.merchandise.facade.IGoodsReadFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 活动API接口
 * @author lijundong 
 * @date 2017年12月11日 下午5:06:55
 */
@Controller
@RequestMapping("marketing/activity")
@SuppressWarnings("unchecked")
@Api(value = "marketing", tags = "活动API接口")
public class ActivityController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IActivityTemplateReadFacade activityTemplateReadFacade;
	
	@Autowired
	private IActivityTemplateWriteFacade activityTemplateWriteFacade;
	
	@Autowired
	private IGoodsReadFacade goodsReadFacade;
	
	@ResponseBody
	@RequestMapping(value = "/listEffective", method = RequestMethod.GET)
	@ApiOperation(value = "获取有效的活动列表", httpMethod = "GET")
	public Result<PageVO<Object>> listEffective(@ApiParam(name = "pageNo", value = "分页页码", required = true, type = "Integer") @RequestParam(name = "pageNo") int pageNo,
			@ApiParam(name = "pageSize", value = "每一页数量", required = true, type = "Integer") @RequestParam(name = "pageSize") int pageSize){
		long shopId = ThreadLocalContext.getShopId();
		logger.info("获取有效的砍价活动列表, shopId={}, pageNo={}, pageSize={}", shopId, pageNo, pageSize);
		
		List<Object> list = new ArrayList<Object>();
		PageInfo<ShopActivityDTO> pageInfo = null;
		try {
			//获取营销活动
			List<ActivityTemplateEnum> templateList = new ArrayList<ActivityTemplateEnum>();
			templateList.add(ActivityTemplateEnum.CUT_PRICE);
			templateList.add(ActivityTemplateEnum.FULL_DISCOUNT);
			
			pageInfo = activityTemplateReadFacade.listShopActivity(pageNo, pageSize, shopId, templateList, MarketingActivityStatusEnum.CONDUCTING);
			for(ShopActivityDTO shopActivityDTO: pageInfo.getList()){
				ActivityTemplateEnum templateEnum = ActivityTemplateEnum.getEnum(shopActivityDTO.getTemplateId());
				Byte templateId = templateEnum.getValue();
				switch (templateEnum) {
					case CUT_PRICE:
						//用来存放活动模版属性的map
						Map<String, Object> attrMap = CutPriceUtils.activityToMap(shopActivityDTO);
						//Map转object
						CutPriceActivityDetailQueryDTO cutPriceDTO = ObjectConvertUtil.mapToObject(attrMap, CutPriceActivityDetailQueryDTO.class);
						cutPriceDTO.setTemplateId(templateId);
						if(cutPriceDTO.getCutMode() == CutModeEnum.SINGLE_GOODS_CUT.getCode()){
							GoodsDTO goods = goodsReadFacade.getGoods(cutPriceDTO.getGoodsId(), YesOrNoEnum.YES);
							cutPriceDTO.setGoods(goods);
						}
						list.add(cutPriceDTO);
						break;
					case FULL_DISCOUNT:
						//Map转object
						FullDiscountActivityDetailDTO fullDiscountDTO = FullDiscountUtils.activityConvert2dto(shopActivityDTO);
						
						long nowTime = System.currentTimeMillis();
						//当前时间<开始时间，活动未开始
						if(nowTime < fullDiscountDTO.getStartTime().getTime()){
							fullDiscountDTO.setStatus(MarketingActivityStatusEnum.NOT_BEGIN.getStatus());
						}
						//当前时间>最后结束时间，活动已结束
						else if(nowTime > fullDiscountDTO.getEndTime().getTime()){
							//设置为已结束
							activityTemplateWriteFacade.endActivity(shopActivityDTO.getShopId(), templateId, shopActivityDTO.getActivityId());
							continue;
						}
						fullDiscountDTO.setTemplateId(templateId);
						list.add(fullDiscountDTO);
						break;
					default:
						break;
				}
			}
		} catch (BaseException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		} catch (Exception e) {
			return Result.FAILURE(e.getMessage());
		}
		return Result.SUCESS(new PageVO<>(pageNo, pageSize, pageInfo.getPages(), pageInfo.getTotal(), list));
	}
	
	@ResponseBody
	@RequestMapping(value = "/listEnd", method = RequestMethod.GET)
	@ApiOperation(value = "获取已结束的活动列表", httpMethod = "GET")
	public Result<PageVO<Object>> listEnd(@ApiParam(name = "pageNo", value = "分页页码", required = true, type = "Integer") @RequestParam(name = "pageNo") int pageNo,
			@ApiParam(name = "pageSize", value = "每一页数量", required = true, type = "Integer") @RequestParam(name = "pageSize") int pageSize){
		long shopId = ThreadLocalContext.getShopId();
		logger.info("获取已结束的砍价活动列表, shopId={}, pageNo={}, pageSize={}", shopId, pageNo, pageSize);
		
		List<Object> list = new ArrayList<Object>();
		PageInfo<ShopActivityDTO> pageInfo = null;
		try {
			//获取营销活动
			List<ActivityTemplateEnum> templateList = new ArrayList<ActivityTemplateEnum>();
			templateList.add(ActivityTemplateEnum.CUT_PRICE);
			templateList.add(ActivityTemplateEnum.FULL_DISCOUNT);
			
			pageInfo = activityTemplateReadFacade.listShopActivity(pageNo, pageSize, shopId, templateList, MarketingActivityStatusEnum.IS_OVER);
			for(ShopActivityDTO shopActivityDTO: pageInfo.getList()){
				ActivityTemplateEnum templateEnum = ActivityTemplateEnum.getEnum(shopActivityDTO.getTemplateId());
				Byte templateId = templateEnum.getValue();
				switch (templateEnum) {
					case CUT_PRICE:
						//用来存放活动模版属性的map
						Map<String, Object> attrMap = CutPriceUtils.activityToMap(shopActivityDTO);
						//Map转object
						CutPriceActivityDetailQueryDTO cutPriceDTO = ObjectConvertUtil.mapToObject(attrMap, CutPriceActivityDetailQueryDTO.class);
						cutPriceDTO.setTemplateId(templateId);
						if(cutPriceDTO.getCutMode() == CutModeEnum.SINGLE_GOODS_CUT.getCode()){
							GoodsDTO goods = goodsReadFacade.getGoods(cutPriceDTO.getGoodsId(),YesOrNoEnum.YES);
							cutPriceDTO.setGoods(goods);
						}
						list.add(cutPriceDTO);
						break;
					case FULL_DISCOUNT:
						//用来存放活动模版属性的map
						attrMap = FullDiscountUtils.activityToMap(shopActivityDTO);
						//Map转object
						FullDiscountActivityDetailDTO fullDiscountDTO = ObjectConvertUtil.mapToObject(attrMap, FullDiscountActivityDetailDTO.class);
						fullDiscountDTO.setTemplateId(templateId);
						list.add(fullDiscountDTO);
						break;
					default:
						break;
				}
			}
		} catch (BaseException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		} catch (Exception e) {
			return Result.FAILURE(e.getMessage());
		}
		return Result.SUCESS(new PageVO<>(pageNo, pageSize, pageInfo.getPages(), pageInfo.getTotal(), list));
	}
}
