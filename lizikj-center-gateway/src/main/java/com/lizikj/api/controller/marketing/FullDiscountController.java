package com.lizikj.api.controller.marketing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.marketing.FullDiscountActivityDetailCreateVO;
import com.lizikj.api.vo.marketing.FullDiscountActivityDetailUpdateVO;
import com.lizikj.common.exception.BaseException;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.marketing.api.dto.FullDiscountActivityDetailDTO;
import com.lizikj.marketing.api.dto.ShopActivityDTO;
import com.lizikj.marketing.api.enums.ActivityTemplateEnum;
import com.lizikj.marketing.api.enums.MarketingActivityStatusEnum;
import com.lizikj.marketing.api.facade.IActivityTemplateWriteFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 满减优惠API接口
 * @author lijundong 
 * @date 2017年12月11日 下午3:32:09
 */
@RestController
@RequestMapping("marketing/fullDiscount")
@SuppressWarnings("unchecked")
@Api(value = "fullDiscount", tags = "满减优惠API接口")
public class FullDiscountController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IActivityTemplateWriteFacade activityTemplateWriteFacade;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ApiOperation(value = "新增满减优惠", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Object> add(@RequestBody @ApiParam(name = "新增满减优惠对象", value = "JSON格式表单", required = true) FullDiscountActivityDetailCreateVO fullDiscountActivityDetailCreateVO){
		long shopId = ThreadLocalContext.getShopId();
		if(logger.isInfoEnabled()){
			logger.info("新增满减优惠 , shopId={}, fullDiscountActivityDetailCreateVO={}", shopId, fullDiscountActivityDetailCreateVO);
		}
		try {
			
			//封装活动属性对象
			FullDiscountActivityDetailDTO activityDetailDTO = ObjectConvertUtil.copyProperties(FullDiscountActivityDetailDTO.class, fullDiscountActivityDetailCreateVO);
			
			//封装店铺活动对象
			ShopActivityDTO shopActivity = new ShopActivityDTO();
			shopActivity.setShopId(shopId);
			shopActivity.setTemplateId(ActivityTemplateEnum.FULL_DISCOUNT.getValue());
			shopActivity.setStatus(MarketingActivityStatusEnum.CONDUCTING.getStatus());
			shopActivity.setStartTime(activityDetailDTO.getStartTime());
			shopActivity.setEndTime(activityDetailDTO.getEndTime());
			
			activityTemplateWriteFacade.add(shopId, ActivityTemplateEnum.FULL_DISCOUNT.getValue(), activityDetailDTO, shopActivity);
		} catch (BaseException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		}
		return Result.SUCESS();
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ApiOperation(value = "编辑满减优惠", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Object> edit(@RequestBody @ApiParam(name = "编辑满减优惠对象", value = "JSON格式表单", required = true) FullDiscountActivityDetailUpdateVO fullDiscountActivityDetailUpdateVO){
		long shopId = ThreadLocalContext.getShopId();
		if(logger.isInfoEnabled()){
			logger.info("编辑满减优惠 , shopId={}, fullDiscountActivityDetailUpdateVO={}", shopId, fullDiscountActivityDetailUpdateVO);
		}
		try {
			
			//封装活动属性对象
			FullDiscountActivityDetailDTO activityDetailDTO = ObjectConvertUtil.copyProperties(FullDiscountActivityDetailDTO.class, fullDiscountActivityDetailUpdateVO);
			
			//封装店铺活动对象
			ShopActivityDTO shopActivity = new ShopActivityDTO();
			shopActivity.setActivityId(activityDetailDTO.getFullDiscountId());
			shopActivity.setShopId(shopId);
			shopActivity.setTemplateId(ActivityTemplateEnum.FULL_DISCOUNT.getValue());
			shopActivity.setStatus(MarketingActivityStatusEnum.CONDUCTING.getStatus());
			shopActivity.setStartTime(activityDetailDTO.getStartTime());
			shopActivity.setEndTime(activityDetailDTO.getEndTime());
			
			activityTemplateWriteFacade.update(shopId, ActivityTemplateEnum.FULL_DISCOUNT.getValue(), activityDetailDTO, shopActivity, false, true);
		} catch (BaseException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		}
		return Result.SUCESS();
	}
	
	@RequestMapping(value = "/end/{fullDiscountId}", method = RequestMethod.POST)
	@ApiOperation(value = "结束满减优惠", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Object> end(@PathVariable(name = "fullDiscountId", required = true) Long fullDiscountId){
		long shopId = ThreadLocalContext.getShopId();
		if(logger.isInfoEnabled()){
			logger.info("结束满减优惠 , shopId={}, fullDiscountId={}", shopId, fullDiscountId);
		}
		try {
			activityTemplateWriteFacade.endActivity(shopId, ActivityTemplateEnum.FULL_DISCOUNT.getValue(), fullDiscountId);
		} catch (BaseException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		}
		return Result.SUCESS();
	}
}
