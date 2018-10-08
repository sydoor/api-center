package com.lizikj.api.controller.marketing;

import java.util.List;

import com.lizikj.merchandise.enums.YesOrNoEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.merchandise.GoodsVO;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.marketing.api.dto.ActiviryNoDiscountUpdateDTO;
import com.lizikj.marketing.api.dto.ShopActivityDTO;
import com.lizikj.marketing.api.enums.ActivityTemplateEnum;
import com.lizikj.marketing.api.enums.MarketingActivityStatusEnum;
import com.lizikj.marketing.api.exception.MarketingException;
import com.lizikj.marketing.api.facade.IActivityTemplateReadFacade;
import com.lizikj.marketing.api.facade.IActivityTemplateWriteFacade;
import com.lizikj.marketing.api.utils.MemberNoDiscountUtils;
import com.lizikj.merchandise.dto.GoodsDTO;
import com.lizikj.merchandise.facade.IGoodsReadFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 会员不享受折扣API
 * @author lijundong 
 * @date 2017年8月31日 下午3:33:11
 */
@Controller
@RequestMapping("marketing/noDiscount")
@SuppressWarnings("unchecked")
@Api(value = "marketing", tags = "会员不享受折扣API接口")
public class MemberNoDiscountController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IActivityTemplateReadFacade activityTemplateReadFacade;
	
	@Autowired
	private IActivityTemplateWriteFacade activityTemplateWriteFacade;
	
	@Autowired
	private IGoodsReadFacade goodsReadFacade;
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ApiOperation(value = "新增没有折扣的美食", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Object> add(@RequestBody @ApiParam(name = "美食id集合", value = "JSON格式表单", required = true) List<String> list){
		long shopId = ThreadLocalContext.getShopId();
		logger.info("add | 新增没有折扣的美食 | shopId: {}, list: {}", shopId, list);
		try {
			//封装活动属性对象
			ActiviryNoDiscountUpdateDTO activiryNoDiscountUpdateDTO = new ActiviryNoDiscountUpdateDTO();
			activiryNoDiscountUpdateDTO.setGoodsIds(list);
			
			//封装店铺活动对象
			ShopActivityDTO shopActivity = new ShopActivityDTO();
			shopActivity.setShopId(shopId);
			shopActivity.setTemplateId(ActivityTemplateEnum.MEMBER_NO_DISCOUNT_GOODS.getValue());
			shopActivity.setStatus(MarketingActivityStatusEnum.CONDUCTING.getStatus());
			
			activityTemplateWriteFacade.add(shopId, ActivityTemplateEnum.MEMBER_NO_DISCOUNT_GOODS.getValue(), activiryNoDiscountUpdateDTO, shopActivity);
		} catch (MarketingException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		}
		return Result.SUCESS();
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ApiOperation(value = "更新不享受折扣的美食", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Object> update(@RequestBody @ApiParam(name = "美食id集合", value = "JSON格式表单", required = true) List<String> list){
		long shopId = ThreadLocalContext.getShopId();
		logger.info("update | 更新不享受折扣的美食 | shopId: {}, list: {}", shopId, list);
		try {
			//封装活动属性对象
			ActiviryNoDiscountUpdateDTO activiryNoDiscountUpdateDTO = new ActiviryNoDiscountUpdateDTO();
			activiryNoDiscountUpdateDTO.setGoodsIds(list);
			
			//封装店铺活动对象
			ShopActivityDTO shopActivity = new ShopActivityDTO();
			shopActivity.setShopId(shopId);
			shopActivity.setTemplateId(ActivityTemplateEnum.MEMBER_NO_DISCOUNT_GOODS.getValue());
			
			activityTemplateWriteFacade.update(shopId, ActivityTemplateEnum.MEMBER_NO_DISCOUNT_GOODS.getValue(), activiryNoDiscountUpdateDTO, shopActivity, false, false);
		} catch (MarketingException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		}
		return Result.SUCESS();
	}
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ApiOperation(value = "获取不享受折扣的美食列表", httpMethod = "GET")
	public Result<List<GoodsVO>> list(){
		long shopId = ThreadLocalContext.getShopId();
		logger.info("list | 获取不享受折扣的美食列表 | shopId: {}, pageNo: {}, pageSize:{}", shopId);
		
		List<GoodsVO> result = null;
		List<String> goodsIds = null;
		try {
			//封装店铺活动对象
			ShopActivityDTO shopActivity = null;
			
			PageInfo<ShopActivityDTO> pageInfo = activityTemplateReadFacade.list(1, 10, shopId, ActivityTemplateEnum.MEMBER_NO_DISCOUNT_GOODS.getValue(), MarketingActivityStatusEnum.CONDUCTING.getStatus());
			
			//从营销模版中，遍历出所有不参与会员折扣的美食id列表
			goodsIds = MemberNoDiscountUtils.getNoDiscountGoodIds(pageInfo);
			
			if(null != goodsIds && !goodsIds.isEmpty()){
				//获取美食列表
				List<GoodsDTO> goodsList = goodsReadFacade.listGoodsByIds(goodsIds, YesOrNoEnum.YES);
				result = ObjectConvertUtil.mapList(goodsList, GoodsDTO.class, GoodsVO.class);
			}
		} catch (MarketingException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		}
		return Result.SUCESS(result);
	}
}
