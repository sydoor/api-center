package com.lizikj.api.controller.marketing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lizikj.merchandise.enums.YesOrNoEnum;
import org.apache.commons.lang.StringUtils;
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
import com.lizikj.api.vo.marketing.ShopHotSaleAdDetailCreateVO;
import com.lizikj.api.vo.marketing.ShopHotSaleAdDetailQueryVO;
import com.lizikj.api.vo.marketing.ShopHotSaleAdDetailSortVO;
import com.lizikj.api.vo.marketing.ShopHotSaleAdDetailUpdateVO;
import com.lizikj.common.enums.UserLoginSourceEnum;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.marketing.api.dto.ShopActivityDTO;
import com.lizikj.marketing.api.dto.ShopHotSaleAdDetailDTO;
import com.lizikj.marketing.api.enums.ActivityDataTypeEnum;
import com.lizikj.marketing.api.enums.ActivityTemplateEnum;
import com.lizikj.marketing.api.enums.MarketingActivityStatusEnum;
import com.lizikj.marketing.api.enums.MarketingErrorEnum;
import com.lizikj.marketing.api.exception.MarketingException;
import com.lizikj.marketing.api.facade.IActivityTemplateReadFacade;
import com.lizikj.marketing.api.facade.IActivityTemplateWriteFacade;
import com.lizikj.merchandise.dto.GoodsDTO;
import com.lizikj.merchandise.enums.RemoveStatusEnum;
import com.lizikj.merchandise.enums.ShelveStateEnum;
import com.lizikj.merchandise.facade.IGoodsReadFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 营销广告API
 * @author lijundong 
 * @date 2017年7月24日 下午5:31:03
 */
@Controller
@RequestMapping("marketing/adverting")
@SuppressWarnings("unchecked")
@Api(value = "marketing", tags = "美食推荐API接口")
public class AdvertingController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IGoodsReadFacade goodsReadFacade;
	
	@Autowired
	private IActivityTemplateWriteFacade activityTemplateWriteFacade;
	
	@Autowired
	private IActivityTemplateReadFacade activityTemplateReadFacade;
	
	@ResponseBody
	@RequestMapping(value = "/addAdverting", method = RequestMethod.POST)
	@ApiOperation(value = "新增美食推荐", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Object> addAdverting(@RequestBody @ApiParam(name = "新增营销广告对象", value = "JSON格式表单", required = true) ShopHotSaleAdDetailCreateVO hotSaleAdDetailCreateVO){
		Long shopId = ThreadLocalContext.getShopId();
		logger.info("新增美食推荐shopId={}, ShopHotSaleAdDetailCreateVO={}", shopId, hotSaleAdDetailCreateVO);
		try {
			if(StringUtils.isBlank(hotSaleAdDetailCreateVO.getGoodsId())){
				return Result.FAILURE(MarketingErrorEnum.MARKETING_GOODSID_NOT_EXIST.getCode().toString(), MarketingErrorEnum.MARKETING_GOODSID_NOT_EXIST.getMessage());
			}
			
			List<Byte> templateIds = new ArrayList<Byte>();
			templateIds.add(ActivityTemplateEnum.GOODS_RECOMMEND.getValue());
			List<ShopActivityDTO> list = activityTemplateReadFacade.listActivityByGoodsId(shopId, hotSaleAdDetailCreateVO.getGoodsId(), templateIds);
			if(null != list && list.size() > 0){
				return Result.FAILURE(MarketingErrorEnum.ADVERTING_ACTIVITY_GOODSID_ALREADY_EXIST.getCode().toString(), MarketingErrorEnum.ADVERTING_ACTIVITY_GOODSID_ALREADY_EXIST.getMessage());
			}
			
			ShopHotSaleAdDetailDTO hotSaleAdDetailDTO = ObjectConvertUtil.copyProperties(ShopHotSaleAdDetailDTO.class, hotSaleAdDetailCreateVO);
			
			//封装店铺活动对象
			ShopActivityDTO shopActivity = new ShopActivityDTO();
			shopActivity.setShopId(shopId);
			shopActivity.setTemplateId(ActivityTemplateEnum.GOODS_RECOMMEND.getValue());
			shopActivity.setStatus(MarketingActivityStatusEnum.CONDUCTING.getStatus());
			
			activityTemplateWriteFacade.add(shopId, ActivityTemplateEnum.GOODS_RECOMMEND.getValue(), hotSaleAdDetailDTO, shopActivity);
		} catch (MarketingException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		}
		return Result.SUCESS();
	}
	
	@ResponseBody
	@RequestMapping(value = "/editAdverting", method = RequestMethod.POST)
	@ApiOperation(value = "编辑美食推荐", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Object> editAdverting(@RequestBody @ApiParam(name = "编辑营销广告对象", value = "JSON格式表单", required = true) ShopHotSaleAdDetailUpdateVO shopHotSaleAdDetailUpdateVO){
		Long shopId = ThreadLocalContext.getShopId();
		logger.info("编辑美食推荐shopId={}, shopHotSaleAdDetailUpdateVO={}", shopId, shopHotSaleAdDetailUpdateVO);
		try {
			ShopHotSaleAdDetailDTO hotSaleAdDetailDTO = ObjectConvertUtil.copyProperties(ShopHotSaleAdDetailDTO.class, shopHotSaleAdDetailUpdateVO);
			
			//封装店铺活动对象
			ShopActivityDTO shopActivity = new ShopActivityDTO();
			shopActivity.setActivityId(hotSaleAdDetailDTO.getHotSaleAdDetailId());
			shopActivity.setShopId(shopId);
			shopActivity.setTemplateId(ActivityTemplateEnum.GOODS_RECOMMEND.getValue());
			shopActivity.setStatus(MarketingActivityStatusEnum.CONDUCTING.getStatus());
			
			activityTemplateWriteFacade.update(shopId, ActivityTemplateEnum.GOODS_RECOMMEND.getValue(), hotSaleAdDetailDTO, shopActivity, false, false);
		} catch (MarketingException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		}
		return Result.SUCESS();
	}
	
	@ResponseBody
	@RequestMapping(value = "/sort", method = RequestMethod.POST)
	@ApiOperation(value = "美食推荐排序", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Object> sort(@RequestBody  @ApiParam(name = "美食推荐排序对象", value = "JSON格式表单", required = true) List<ShopHotSaleAdDetailSortVO> list){
		Long shopId = ThreadLocalContext.getShopId();
		logger.info("美食推荐排序| shopId={}, list={}", shopId, list);
		try {
			if(null == list || list.isEmpty()){
				return Result.FAILURE("美食推荐排序对象is null");
			}
			
			List<ShopHotSaleAdDetailDTO> dtoList = new ArrayList<ShopHotSaleAdDetailDTO>();
			List<ShopActivityDTO> activityList = new ArrayList<ShopActivityDTO>();
			for(ShopHotSaleAdDetailSortVO vo: list){
				ShopHotSaleAdDetailDTO hotSaleAdDetailDTO = ObjectConvertUtil.copyProperties(ShopHotSaleAdDetailDTO.class, vo);
				dtoList.add(hotSaleAdDetailDTO);
				
				//封装店铺活动对象
				ShopActivityDTO shopActivity = new ShopActivityDTO();
				shopActivity.setActivityId(hotSaleAdDetailDTO.getHotSaleAdDetailId());
				shopActivity.setShopId(shopId);
				shopActivity.setTemplateId(ActivityTemplateEnum.GOODS_RECOMMEND.getValue());
				shopActivity.setStatus(MarketingActivityStatusEnum.CONDUCTING.getStatus());
				activityList.add(shopActivity);
			}
			activityTemplateWriteFacade.updateBatch(shopId, ActivityTemplateEnum.GOODS_RECOMMEND.getValue(), dtoList, activityList, true);
		} catch (MarketingException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		}
		return Result.SUCESS();
	}
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ApiOperation(value = "获取营销广告列表", httpMethod = "GET", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public Result<List<ShopHotSaleAdDetailQueryVO>> list(){
		long shopId = ThreadLocalContext.getShopId();
		UserLoginSourceEnum userLoginSourceEnum = UserLoginSourceEnum.getEnum(ThreadLocalContext.getLoginSource());
		logger.info("sort | 获取营销广告列表 | shopId={}, userLoginSourceEnum={}", shopId, userLoginSourceEnum);
		List<ShopHotSaleAdDetailQueryVO> list = null;
		PageInfo<ShopActivityDTO> pageInfo = null;
		try {
			//获取有效活动的分页列表
			pageInfo = activityTemplateReadFacade.list(1, 10, shopId, ActivityTemplateEnum.GOODS_RECOMMEND.getValue(), MarketingActivityStatusEnum.CONDUCTING.getStatus());
			//dtoList转VOlist
			list = ObjectConvertUtil.mapList(dtoListConvertVOList(pageInfo.getList(), userLoginSourceEnum), ShopHotSaleAdDetailDTO.class, ShopHotSaleAdDetailQueryVO.class);
			Collections.sort(list,new Comparator<ShopHotSaleAdDetailQueryVO>() {
				@Override
				public int compare(ShopHotSaleAdDetailQueryVO o1, ShopHotSaleAdDetailQueryVO o2) {
					return o1.getSort().compareTo(o2.getSort());
				}
			});
		} catch (MarketingException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		}
		return Result.SUCESS(list);
	} 
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ApiOperation(value = "删除美食推荐", httpMethod = "POST", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public Result<Object> delete(@RequestBody @ApiParam(name = "营销广告id数组", value = "JSON格式表单", required = true) List<Long> ids){
		long shopId = ThreadLocalContext.getShopId();
		logger.info("删除美食推荐 | shopId: {}", shopId);
		try {
			for(Long id: ids){
				activityTemplateWriteFacade.endActivity(shopId, ActivityTemplateEnum.GOODS_RECOMMEND.getValue(), id);
			}
		} catch (MarketingException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		}
		return Result.SUCESS();
	} 
	
	/**
	 * 活动模版对象，转为业务dto对象, 根据登录来源做不同端口数据展示
	 * @param dtoList
	 * @param userLoginSourceEnum
	 * @return List<ShopHotSaleAdDetailDTO>
	 * @author lijundong
	 * @date 2017年11月1日 下午9:13:30
	 */
	public List<ShopHotSaleAdDetailDTO> dtoListConvertVOList(List<ShopActivityDTO> dtoList, UserLoginSourceEnum userLoginSourceEnum){
		List<ShopHotSaleAdDetailDTO> list = new ArrayList<ShopHotSaleAdDetailDTO>();
		
		//把对象转为map类型
		List<Map<String, Object>> listToMap = activityListToMap(dtoList);
		listToMap.forEach(map ->{
			//Map转object
			ShopHotSaleAdDetailDTO hotSaleAdDetailDTO = ObjectConvertUtil.mapToObject(map, ShopHotSaleAdDetailDTO.class);
			GoodsDTO goods = goodsReadFacade.getGoods(hotSaleAdDetailDTO.getGoodsId(), YesOrNoEnum.NO);
			if(userLoginSourceEnum == UserLoginSourceEnum.H5){
				if(null != goods){
					//如果美食是下架，删除，不可见，禁用，则不展示给h5
					if(goods.getShelveState() == ShelveStateEnum.OFF_LINE || (goods.getRemoveStatus() == RemoveStatusEnum.REMOVED || goods.getRemoveStatus() == RemoveStatusEnum.INVISIBLE)){
						return;
					}
				}
			}
			
			hotSaleAdDetailDTO.setGoods(goods);
			
			list.add(hotSaleAdDetailDTO);
		});
		return list;
	}
	
	/**
	 * 活动列表转为List<Map>
	 * @param list
	 * @return List<Map<String,Object>>
	 * @author lijundong
	 * @date 2017年9月4日 下午8:57:01
	 */
	public List<Map<String, Object>> activityListToMap(List<ShopActivityDTO> list){
		final List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		list.forEach(activity -> listMap.add(activityToMap(activity)));
		return listMap;
	}
	
	public Map<String, Object> activityToMap(ShopActivityDTO activityDTO){
		final Map<String, Object> attrMap = new HashMap<String, Object>();
		activityDTO.getAttrList().forEach(attr ->{
			ActivityDataTypeEnum dataTypeEnum = ActivityDataTypeEnum.getEnum(attr.getDataType());
			String attrValue = attr.getAttrValue();
			switch (dataTypeEnum) {
				case STRING:
					attrMap.put(attr.getAttrName(), attrValue);
					break;
				case INTEGER:
					attrMap.put(attr.getAttrName(), StringUtils.isBlank(attrValue) ? 0 : Integer.parseInt(attrValue));
					break;
				case LONG:
					attrMap.put(attr.getAttrName(), StringUtils.isBlank(attrValue) ? 0 : Long.parseLong(attrValue));
					break;
				case BYTE:
					attrMap.put(attr.getAttrName(), StringUtils.isBlank(attrValue) ? 0 : Byte.parseByte(attrValue));
					break;
				default:
					break;
			}
		});
		attrMap.put("hotSaleAdDetailId", activityDTO.getActivityId());
		return attrMap;
	}
}
