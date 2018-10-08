package com.lizikj.api.controller.marketing;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.marketing.TimeDiscountActivityDetailCreateVO;
import com.lizikj.api.vo.marketing.TimeDiscountActivityDetailQueryVO;
import com.lizikj.api.vo.marketing.TimeDiscountActivityDetailUpdateVO;
import com.lizikj.api.vo.marketing.TimeDiscountSwitchAllVO;
import com.lizikj.common.exception.BaseException;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.marketing.api.dto.ShopActivityDTO;
import com.lizikj.marketing.api.dto.TimeDiscountActivityDetailDTO;
import com.lizikj.marketing.api.enums.ActivityTemplateEnum;
import com.lizikj.marketing.api.enums.MarketingActivityStatusEnum;
import com.lizikj.marketing.api.facade.IActivityTemplateReadFacade;
import com.lizikj.marketing.api.facade.IActivityTemplateWriteFacade;
import com.lizikj.marketing.api.utils.TimeDiscountUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 分时段优惠接口
 * @author lijundong 
 * @date 2017年12月27日 上午9:59:46
 */
@RestController
@RequestMapping("marketing/timeDiscount")
@SuppressWarnings({"unchecked", "rawtypes"})
@Api(value = "marketing", tags = "分时段优惠接口")
public class TimeDiscountController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IActivityTemplateReadFacade activityTemplateReadFacade;
	
	@Autowired
	private IActivityTemplateWriteFacade activityTemplateWriteFacade;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ApiOperation(value = "新增分时段优惠", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Long> add(@RequestBody @ApiParam(name = "新增分时段优惠对象", value = "JSON格式表单", required = true) TimeDiscountActivityDetailCreateVO timeDiscountActivityDetailCreateVO){
		long shopId = ThreadLocalContext.getShopId();
		if(logger.isInfoEnabled()){
			logger.info("新增分时段优惠 , shopId={}, timeDiscountActivityDetailCreateVO={}", shopId, timeDiscountActivityDetailCreateVO);
		}
		
		Long activityId = null;
		try {
			Result result = validateTimeDiscount(null, timeDiscountActivityDetailCreateVO.getStartTime(), timeDiscountActivityDetailCreateVO.getEndTime(), timeDiscountActivityDetailCreateVO.getWeeks());
			if(null != result){
				return result;
			}
			
			//封装活动属性对象
			TimeDiscountActivityDetailDTO timeDiscountActivityDetailDTO = ObjectConvertUtil.copyProperties(TimeDiscountActivityDetailDTO.class, timeDiscountActivityDetailCreateVO);
			timeDiscountActivityDetailDTO.setWeeks(timeDiscountActivityDetailCreateVO.getWeeks());
			
			//封装店铺活动对象
			ShopActivityDTO shopActivity = new ShopActivityDTO();
			shopActivity.setShopId(shopId);
			shopActivity.setMode(timeDiscountActivityDetailDTO.getMode());
			shopActivity.setTemplateId(ActivityTemplateEnum.TIME_DISCOUNT.getValue());
			shopActivity.setStatus(MarketingActivityStatusEnum.CONDUCTING.getStatus());
			
			shopActivity = activityTemplateWriteFacade.add(shopId, ActivityTemplateEnum.TIME_DISCOUNT.getValue(), timeDiscountActivityDetailDTO, shopActivity);
			activityId = shopActivity.getActivityId();
		} catch (BaseException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		}
		return Result.SUCESS(activityId);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ApiOperation(value = "编辑分时段优惠", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Object> edit(@RequestBody @ApiParam(name = "编辑分时段优惠对象", value = "JSON格式表单", required = true) TimeDiscountActivityDetailUpdateVO timeDiscountActivityDetailUpdateVO){
		long shopId = ThreadLocalContext.getShopId();
		if(logger.isInfoEnabled()){
			logger.info("编辑满减优惠 , shopId={}, timeDiscountActivityDetailUpdateVO={}", shopId, timeDiscountActivityDetailUpdateVO);
		}
		try {
			Result<Object> result = validateTimeDiscount(timeDiscountActivityDetailUpdateVO.getTimeDiscountId(), timeDiscountActivityDetailUpdateVO.getStartTime(), timeDiscountActivityDetailUpdateVO.getEndTime(), timeDiscountActivityDetailUpdateVO.getWeeks());
			if(null != result){
				return result;
			}
			
			//封装活动属性对象
			TimeDiscountActivityDetailDTO timeDiscountActivityDetailDTO = ObjectConvertUtil.copyProperties(TimeDiscountActivityDetailDTO.class, timeDiscountActivityDetailUpdateVO);
			timeDiscountActivityDetailDTO.setWeeks(timeDiscountActivityDetailUpdateVO.getWeeks());
			
			//封装店铺活动对象
			ShopActivityDTO shopActivity = new ShopActivityDTO();
			shopActivity.setActivityId(timeDiscountActivityDetailDTO.getTimeDiscountId());
			shopActivity.setShopId(shopId);
			shopActivity.setMode(timeDiscountActivityDetailDTO.getMode());
			shopActivity.setTemplateId(ActivityTemplateEnum.TIME_DISCOUNT.getValue());
			shopActivity.setStatus(timeDiscountActivityDetailUpdateVO.getStatus());
			
			activityTemplateWriteFacade.update(shopId, ActivityTemplateEnum.TIME_DISCOUNT.getValue(), timeDiscountActivityDetailDTO, shopActivity, false, true);
		} catch (BaseException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		}
		return Result.SUCESS();
	}
	
	@RequestMapping(value = "/delete/{timeDiscountId}", method = RequestMethod.POST)
	@ApiOperation(value = "删除分时段优惠", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Object> delete(@PathVariable(name = "timeDiscountId", required = true) Long timeDiscountId){
		long shopId = ThreadLocalContext.getShopId();
		if(logger.isInfoEnabled()){
			logger.info("删除分时段优惠 , shopId={}, timeDiscountId={}", shopId, timeDiscountId);
		}
		try {
			activityTemplateWriteFacade.endActivity(shopId, ActivityTemplateEnum.TIME_DISCOUNT.getValue(), timeDiscountId);
		} catch (BaseException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		}
		return Result.SUCESS();
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ApiOperation(value = "获取分时段优惠列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<TimeDiscountActivityDetailQueryVO>> list(){
		long shopId = ThreadLocalContext.getShopId();
		if(logger.isInfoEnabled()){
			logger.info("获取分时段优惠列表, shopId={}", shopId);
		}
		List<TimeDiscountActivityDetailQueryVO> list = null;
		try {
			List<Byte> statusList = new ArrayList<Byte>();
			statusList.add(MarketingActivityStatusEnum.NOT_BEGIN.getStatus());
			statusList.add(MarketingActivityStatusEnum.CONDUCTING.getStatus());
			
			PageInfo<ShopActivityDTO> pageInfo = activityTemplateReadFacade.list(0, 0, shopId, ActivityTemplateEnum.TIME_DISCOUNT.getValue(), statusList);
			
			List<TimeDiscountActivityDetailDTO> dtolist = TimeDiscountUtils.activityListConvertVOList(pageInfo.getList());
			
			list = ObjectConvertUtil.mapList(dtolist, TimeDiscountActivityDetailDTO.class, TimeDiscountActivityDetailQueryVO.class);
		} catch (BaseException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		}
		return Result.SUCESS(list);
	}
	
	@RequestMapping(value = "/switch/all", method = RequestMethod.POST)
	@ApiOperation(value = "批量开(关)分时段优惠", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<TimeDiscountActivityDetailQueryVO>> switchAll(@RequestBody @ApiParam(name = "批量开(关)分时段优惠对象", value = "JSON格式表单", required = true) TimeDiscountSwitchAllVO timeDiscountSwitchAllVO){
		long shopId = ThreadLocalContext.getShopId();
		if(logger.isInfoEnabled()){
			logger.info("批量开(关)分时段优惠, shopId={}, timeDiscountSwitchAllVO={}", shopId, timeDiscountSwitchAllVO);
		}
		try {
			activityTemplateWriteFacade.setActivityListStatus(timeDiscountSwitchAllVO.getTimeDiscountIds(), MarketingActivityStatusEnum.getEnum(timeDiscountSwitchAllVO.getStatus()));
		} catch (BaseException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		}
		return Result.SUCESS();
	}
	
	/**
	 * 校验分时段优惠
	 * @param startTime
	 * @param endTime
	 * @param weeks
	 * @return Result<Object>
	 * @author lijundong
	 * @date 2018年1月17日 下午3:17:22
	 */
	private Result validateTimeDiscount(Long timeDiscountId, long startTime, long endTime, String weeks){
		
		if(StringUtils.isBlank(weeks)){
			return Result.FAILURE("重复时间段不能为空!");
		}
		
		//获取已经存在的分时段优惠活动，校验时段是否重叠
		List<Byte> statusList = new ArrayList<Byte>();
		statusList.add(MarketingActivityStatusEnum.NOT_BEGIN.getStatus());
		statusList.add(MarketingActivityStatusEnum.CONDUCTING.getStatus());
		PageInfo<ShopActivityDTO> pageInfo = activityTemplateReadFacade.list(0, 0, ThreadLocalContext.getShopId(), ActivityTemplateEnum.TIME_DISCOUNT.getValue(), statusList);
		List<TimeDiscountActivityDetailDTO> dtolist = TimeDiscountUtils.activityListConvertVOList(pageInfo.getList());
		if(null != dtolist && !dtolist.isEmpty()){
			for(TimeDiscountActivityDetailDTO timeDiscount: dtolist){
				
				//新增时校验/编辑时过滤自身
				if(null == timeDiscountId || !timeDiscountId.equals(timeDiscount.getTimeDiscountId())){
					//判断时间段是否存在重叠，比如存在周一：8-14，新增周一:13-20，就发生重叠了
					if(null != timeDiscount.getWeeks()){
						String[] oldWeeks = timeDiscount.getWeeks().split(",");
						for(String week: oldWeeks){
							if(weeks.contains(week)){
								if((startTime >= timeDiscount.getStartTime() && startTime <= timeDiscount.getEndTime()) || (endTime >= timeDiscount.getStartTime() && endTime <= timeDiscount.getEndTime())){
									return Result.FAILURE("分时段优惠时间重叠，请重新设置!");
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
}
