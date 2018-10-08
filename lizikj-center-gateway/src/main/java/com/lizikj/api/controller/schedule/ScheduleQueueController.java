package com.lizikj.api.controller.schedule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.schedule.QueryScheduleQueueVO;
import com.lizikj.api.vo.schedule.ScheduleQueueVO;
import com.lizikj.common.enums.UserLoginSourceEnum;
import com.lizikj.common.exception.BaseException;
import com.lizikj.common.util.CollectionUtils;
import com.lizikj.common.util.HttpClientUtils;
import com.lizikj.common.util.JsonUtils;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.login.interceptor.LoginExclude;
import com.lizikj.merchant.dto.ShopDTO;
import com.lizikj.merchant.dto.ShopMerchantDTO;
import com.lizikj.merchant.facade.IShopMerchantReadFacade;
import com.lizikj.merchant.facade.IShopReadFacade;
import com.lizikj.schedule.api.dto.QueryScheduleQueueDTO;
import com.lizikj.schedule.api.dto.ScheduleQueueDTO;
import com.lizikj.schedule.api.enums.QueueSourceEnum;
import com.lizikj.schedule.api.enums.QueueStatusEnum;
import com.lizikj.schedule.api.enums.ScheduleErrorEnum;
import com.lizikj.schedule.api.exception.ScheduleException;
import com.lizikj.schedule.api.facade.IScheduleQueueReadFacade;
import com.lizikj.schedule.api.facade.IScheduleQueueWriteFacade;
import com.lizikj.user.constants.WechatQrCodeConstants;
import com.lizikj.user.facade.IWechatPublicAuthorizationFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ma.glasnost.orika.MapperFacade;

/**
 * 店铺区域接口
 * 
 * @author zhoufe
 * @date 2017/07/10
 */
@Controller
@RequestMapping("/schedule/queue")
@Api(value = "schedule-queue", tags = "排队取号API接口")
public class ScheduleQueueController {

	private static final Logger logger = LoggerFactory.getLogger(ScheduleQueueController.class);

	@Autowired
	private IScheduleQueueReadFacade scheduleQueueReadFacade;

	@Autowired
	private IScheduleQueueWriteFacade scheduleQueueWriteFacade;

	@Autowired
	private IShopReadFacade shopReadFacade;

	@Autowired
	private IWechatPublicAuthorizationFacade wechatPublicAuthorizationFacade;

	@Autowired
	private IShopMerchantReadFacade shopMerchantReadFacade;

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/listByCondition")
	@ApiOperation(value = "根据不同条件查询排队列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<PageInfo<ScheduleQueueVO>> listByCondition(@ApiParam(name = "paramVO", value = "paramVO", required = true) @RequestBody QueryScheduleQueueVO queryScheduleQueueVO) {
		Result<PageInfo<ScheduleQueueVO>> result;
		try {

			if (null == queryScheduleQueueVO) {
				throw new ScheduleException(ScheduleErrorEnum.PARAMETERS_ERROR, "传入参数为空");
			}

			Integer pageNum = queryScheduleQueueVO.getPageNum();
			Integer pageSize = queryScheduleQueueVO.getPageSize();
			if (null == pageNum) {
				throw new ScheduleException(ScheduleErrorEnum.PARAMETERS_ERROR, "传入页码参数为空");
			}

			if (null == pageSize) {
				throw new ScheduleException(ScheduleErrorEnum.PARAMETERS_ERROR, "传入页大小参数为空");
			}

			Byte loginSource = ThreadLocalContext.getLoginSource();
			if (UserLoginSourceEnum.POS.getValue().equals(loginSource)) {
				Long shopId = ThreadLocalContext.getShopId();
				queryScheduleQueueVO.setShopId(shopId);
			}

			MapperFacade mapperFacade = ObjectConvertUtil.getMapperFactory().getMapperFacade();
			QueryScheduleQueueDTO queryScheduleQueueDTO = mapperFacade.map(queryScheduleQueueVO, QueryScheduleQueueDTO.class);
			PageInfo<ScheduleQueueDTO> scheduleQueueDTOPageInfo = scheduleQueueReadFacade.listByCondition(queryScheduleQueueDTO, pageNum, pageSize);

			PageInfo scheduleQueueVOPageInfo = scheduleQueueDTOPageInfo;
			if (null == scheduleQueueDTOPageInfo) {
				scheduleQueueVOPageInfo = getScheduleQueueVOPageInfo(pageNum, pageSize);
				return Result.SUCESS(scheduleQueueVOPageInfo);
			}

			List<ScheduleQueueDTO> list = scheduleQueueDTOPageInfo.getList();
			if (CollectionUtils.isListBlank(list)) {
				scheduleQueueVOPageInfo = getScheduleQueueVOPageInfo(pageNum, pageSize);
				return Result.SUCESS(scheduleQueueVOPageInfo);
			}

			List<ScheduleQueueVO> scheduleQueueVOS = mapperFacade.mapAsList(list, ScheduleQueueVO.class);
			scheduleQueueVOPageInfo.setList(scheduleQueueVOS);
			result = Result.SUCESS(scheduleQueueVOPageInfo);
		} catch (BaseException e) {
			if (logger.isErrorEnabled()) {
				logger.error("listByCondition Exception: {}", e.getMessage(), e);
			}
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}

	private PageInfo<ScheduleQueueVO> getScheduleQueueVOPageInfo(Integer pageNum, Integer pageSize) {
		PageInfo<ScheduleQueueVO> scheduleQueueVOPageInfo;
		scheduleQueueVOPageInfo = new PageInfo<>();
		scheduleQueueVOPageInfo.setTotal(0);
		scheduleQueueVOPageInfo.setPageNum(pageNum);
		scheduleQueueVOPageInfo.setPageSize(pageSize);
		return scheduleQueueVOPageInfo;
	}

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findByScheduleQueueId/{scheduleQueueId}")
	@ApiOperation(value = "根据ID获取排队详情", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<ScheduleQueueVO> findByScheduleQueueId(@ApiParam(name = "scheduleQueueId", value = "排队ID", required = true) @PathVariable(name = "scheduleQueueId") Long scheduleQueueId) {
		Result<ScheduleQueueVO> result;
		try {
			ScheduleQueueDTO scheduleQueueDTO = scheduleQueueReadFacade.findByScheduleQueueId(QueueSourceEnum.POS, scheduleQueueId);
			MapperFacade mapperFacade = ObjectConvertUtil.getMapperFactory().getMapperFacade();
			ScheduleQueueVO scheduleQueueVO = mapperFacade.map(scheduleQueueDTO, ScheduleQueueVO.class);
			result = Result.SUCESS(scheduleQueueVO);
		} catch (BaseException e) {
			if (logger.isErrorEnabled()) {
				logger.error("findByScheduleQueueId Exception: {}", e.getMessage(), e);
			}
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}

	@LoginExclude
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findByScheduleQueueId4H5/{scheduleQueueId}")
	@ApiOperation(value = "根据ID获取排队详情：H5", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<ScheduleQueueVO> findByScheduleQueueId4H5(@ApiParam(name = "scheduleQueueId", value = "排队ID", required = true) @PathVariable(name = "scheduleQueueId") Long scheduleQueueId) {

		Result<ScheduleQueueVO> result;
		try {
			ScheduleQueueDTO scheduleQueueDTO = scheduleQueueReadFacade.findByScheduleQueueId(QueueSourceEnum.H5, scheduleQueueId);
			MapperFacade mapperFacade = ObjectConvertUtil.getMapperFactory().getMapperFacade();
			ScheduleQueueVO scheduleQueueVO = mapperFacade.map(scheduleQueueDTO, ScheduleQueueVO.class);
			result = Result.SUCESS(scheduleQueueVO);
		} catch (BaseException e) {
			if (logger.isErrorEnabled()) {
				logger.error("findByScheduleQueueId4H5 Exception: {}", e.getMessage(), e);
			}
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}

	@LoginExclude
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/insertScheduleQueue4H5")
	@ApiOperation(value = "取号：H5取号要传取号人和店铺ID和排号来源", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<ScheduleQueueVO> insertScheduleQueue4H5(@ApiParam(name = "paramVO", value = "paramVO", required = true) @RequestBody ScheduleQueueVO scheduleQueueParamVO) {

		return insertScheduleQueue(scheduleQueueParamVO);
	}

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/insertScheduleQueue")
	@ApiOperation(value = "取号", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<ScheduleQueueVO> insertScheduleQueue(@ApiParam(name = "paramVO", value = "paramVO", required = true) @RequestBody ScheduleQueueVO scheduleQueueParamVO) {

		Result<ScheduleQueueVO> result;
		try {

			if (null == scheduleQueueParamVO) {
				throw new ScheduleException(ScheduleErrorEnum.PARAMETERS_ERROR, "传入参数为空");
			}
			Long merchantId = null;
			Byte loginSource = ThreadLocalContext.getLoginSource();
			if (UserLoginSourceEnum.POS.getValue().equals(loginSource)) {
				Long shopId = ThreadLocalContext.getShopId();
				merchantId = ThreadLocalContext.getMerchantId();
				scheduleQueueParamVO.setShopId(shopId);
				scheduleQueueParamVO.setMerchantId(merchantId);
				Long staffId = ThreadLocalContext.getStaffId();
				scheduleQueueParamVO.setSendOperatorId(staffId);
				scheduleQueueParamVO.setQueueSource(QueueSourceEnum.POS);
			} else if (UserLoginSourceEnum.SC.getValue().equals(loginSource)) {
				Long shopId = ThreadLocalContext.getShopId();
				merchantId = ThreadLocalContext.getMerchantId();
				Long userId = ThreadLocalContext.getUserId();
				scheduleQueueParamVO.setShopId(shopId);
				scheduleQueueParamVO.setMerchantId(merchantId);
				Long staffId = ThreadLocalContext.getStaffId();
				scheduleQueueParamVO.setSendOperatorId(staffId);
				scheduleQueueParamVO.setQueueSource(QueueSourceEnum.SC);
				String scAppid = ThreadLocalContext.getSmallClientAppid();
				scheduleQueueParamVO.setAppid(scAppid);
				scheduleQueueParamVO.setTakeUserId(userId);
			}

			if (isLongNull(merchantId)) {
				Long shopId = scheduleQueueParamVO.getShopId();
				ShopDTO shopDTO = shopReadFacade.findById(shopId);
				if (null == shopDTO) {
					throw new ScheduleException(ScheduleErrorEnum.PARAMETERS_ERROR, String.format("根据店铺ID(%s)查询不到店铺信息", shopId));
				}
				merchantId = shopDTO.getMerchantId();

			}

			scheduleQueueParamVO.setMerchantId(merchantId);

			MapperFacade mapperFacade = ObjectConvertUtil.getMapperFactory().getMapperFacade();
			ScheduleQueueDTO scheduleQueueParamDTO = mapperFacade.map(scheduleQueueParamVO, ScheduleQueueDTO.class);
			ScheduleQueueDTO scheduleQueueDTO = scheduleQueueWriteFacade.insertScheduleQueue(scheduleQueueParamDTO);
			ScheduleQueueVO scheduleQueueVO = mapperFacade.map(scheduleQueueDTO, ScheduleQueueVO.class);
			result = Result.SUCESS(scheduleQueueVO);
		} catch (BaseException e) {
			if (logger.isErrorEnabled()) {
				logger.error("insertScheduleQueue Exception: {}", e.getMessage(), e);
			}
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}

	private boolean isLongNull(Long id) {
		return null == id || 0 == id;
	}

	@LoginExclude
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/updateScheduleQueue4H5")
	@ApiOperation(value = "入座，过号，消号等操作：H5消号要传消号人", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Boolean> updateScheduleQueue4H5(@ApiParam(name = "paramVO", value = "paramVO", required = true) @RequestBody ScheduleQueueVO scheduleQueueParamVO) {
		Result<Boolean> result;
		try {

			if (null == scheduleQueueParamVO) {
				throw new ScheduleException(ScheduleErrorEnum.PARAMETERS_ERROR, "传入参数为空");
			}
			// 判断操作源
			scheduleQueueParamVO.setQueueSource(QueueSourceEnum.H5);

			MapperFacade mapperFacade = ObjectConvertUtil.getMapperFactory().getMapperFacade();
			ScheduleQueueDTO scheduleQueueParamDTO = mapperFacade.map(scheduleQueueParamVO, ScheduleQueueDTO.class);
			Boolean succ = scheduleQueueWriteFacade.updateScheduleQueue(scheduleQueueParamDTO);
			result = Result.SUCESS(succ);
		} catch (BaseException e) {
			if (logger.isErrorEnabled()) {
				logger.error("updateScheduleQueue Exception: {}", e.getMessage(), e);
			}
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/updateScheduleQueue")
	@ApiOperation(value = "入座，过号，消号等操作", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Boolean> updateScheduleQueue(@ApiParam(name = "paramVO", value = "paramVO", required = true) @RequestBody ScheduleQueueVO scheduleQueueParamVO) {

		Result<Boolean> result;
		try {

			if (null == scheduleQueueParamVO) {
				throw new ScheduleException(ScheduleErrorEnum.PARAMETERS_ERROR, "传入参数为空");
			}

			Byte loginSource = ThreadLocalContext.getLoginSource();
			if (UserLoginSourceEnum.POS.getValue().equals(loginSource)) {
				Long shopId = ThreadLocalContext.getShopId();
				scheduleQueueParamVO.setShopId(shopId);
				// 判断操作源
				scheduleQueueParamVO.setQueueSource(QueueSourceEnum.POS);

				Long staffId = ThreadLocalContext.getStaffId();

				QueueStatusEnum queueStatus = scheduleQueueParamVO.getQueueStatus();
				if (QueueStatusEnum.MISSING.equals(queueStatus)) {
					scheduleQueueParamVO.setMissingOperatorId(staffId);
				} else if (QueueStatusEnum.SEATED.equals(queueStatus)) {
					scheduleQueueParamVO.setSeatDownOperatorId(staffId);
				}
			} else if (UserLoginSourceEnum.H5.getValue().equals(loginSource)) {
				scheduleQueueParamVO.setQueueSource(QueueSourceEnum.H5);
			}

			MapperFacade mapperFacade = ObjectConvertUtil.getMapperFactory().getMapperFacade();
			ScheduleQueueDTO scheduleQueueParamDTO = mapperFacade.map(scheduleQueueParamVO, ScheduleQueueDTO.class);
			Boolean succ = scheduleQueueWriteFacade.updateScheduleQueue(scheduleQueueParamDTO);
			result = Result.SUCESS(succ);
		} catch (BaseException e) {
			if (logger.isErrorEnabled()) {
				logger.error("updateScheduleQueue Exception: {}", e.getMessage(), e);
			}
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/resetScheduleQueue")
	@ApiOperation(value = "重置排队中的排队", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Boolean> resetScheduleQueue() {

		Result<Boolean> result;
		try {
			Long shopId = ThreadLocalContext.getShopId();
			Boolean succ = scheduleQueueWriteFacade.resetScheduleQueue(shopId);
			result = Result.SUCESS(succ);
		} catch (BaseException e) {
			if (logger.isErrorEnabled()) {
				logger.error("resetScheduleQueue Exception: {}", e.getMessage(), e);
			}
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/cleanScheduleQueue")
	@ApiOperation(value = "清空历史的排队", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Boolean> cleanScheduleQueue() {

		Result<Boolean> result;
		try {
			Long shopId = ThreadLocalContext.getShopId();
			Boolean succ = scheduleQueueWriteFacade.cleanScheduleQueue(shopId);
			result = Result.SUCESS(succ);
		} catch (BaseException e) {
			if (logger.isErrorEnabled()) {
				logger.error("cleanScheduleQueue Exception: {}", e.getMessage(), e);
			}
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/scavengingNumber")
	@ApiOperation(value = "获取店铺扫码取号二维码", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Object> scavengingNumber() {
		String qrCode = null;
		try {
			Long shopId = ThreadLocalContext.getShopId();
			Long merchantId = ThreadLocalContext.getMerchantId();
			ShopMerchantDTO merchantDTO = shopMerchantReadFacade.findById(merchantId);
			if (null != merchantDTO) {
				if (StringUtils.isBlank(merchantDTO.getWechantPublicKey())) {
					return Result.FAILURE("商户公众号未授权, 不能使用二维码自助取号！");
				}
				qrCode = wechatPublicAuthorizationFacade.createLimitQrCode(merchantDTO.getWechantPublicKey(), WechatQrCodeConstants.getShopScavengingNumber(merchantId, shopId));
			}
		} catch (BaseException e) {
			if (logger.isErrorEnabled()) {
				logger.error("获取店铺扫码取号二维码 Exception: {}", e.getMessage(), e);
			}
			return Result.FAILURE(e.getCode(), e.getMessage());
		}
		return Result.SUCESS(qrCode);
	}

	public static void main(String[] args) {
		ScheduleQueueVO scheduleQueueParamVO = new ScheduleQueueVO();
		Map<String, String> header = new HashMap<String, String>();
		Map<String, Object> map = new HashMap<String, Object>();
		scheduleQueueParamVO.setShopId(111L);
		scheduleQueueParamVO.setPeoples(1);
		scheduleQueueParamVO.setQueueSource(QueueSourceEnum.SC);
		System.out.println(JsonUtils.toJSONString(scheduleQueueParamVO));
		map.put("paramVO", scheduleQueueParamVO);

		System.out.println(JsonUtils.toJSONString(map));
		//
		// header.put("lz-token",
		// "eyJhbGciOiJIUzI1NiJ9.eyJ1aSI6MzksImxzIjo0LCJtaSI6MCwiZW52IjoiZGV2IiwiZXhwIjoxNTI0MDQ2ODM2LCJzcGkiOjEsImlhdCI6MTUyMzQ0MjAzNiwidXMiOjEsInV0Ijo2fQ.ewOT4FV1xt7X8jldZ5yA87Su5dlS-AkAAfzrJyD2lXM");
		// header.put("Content-Type", "application/json; charset=utf-8");
		// String data =
		// HttpClientUtils.doPost("https://api.lizikj.com/schedule/queue/insertScheduleQueue",
		// JsonUtils.toJSONString(map), header);
		// System.out.println(data);
	}
}
