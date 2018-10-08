package com.lizikj.api.controller.datalog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.datalog.DataLogPullVO;
import com.lizikj.api.vo.datalog.DataLogVO;
import com.lizikj.api.vo.datalog.DataOperationLogVO;
import com.lizikj.common.util.CollectionUtils;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.datalog.dto.DataLogPullDTO;
import com.lizikj.datalog.dto.DataOperationLogDTO;
import com.lizikj.datalog.enums.HighFreqDataTypeEnum;
import com.lizikj.datalog.facade.IDataOperationLogFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 数据操作日志controller
 *
 * @auth zone
 * @date 2017-09-21
 */
@Controller
@RequestMapping("datalog/")
@SuppressWarnings("unchecked")
@Api(value = "datalog", tags = "数据同步API接口")
public class DataLogController {
    private static final Logger logger = LoggerFactory.getLogger(DataLogController.class);
    
    // 超过此长度的下标范围就要返回的重新拉取信息的数据
    private long size = 10;
    
    @Autowired
    IDataOperationLogFacade dataOperationLogFacade;

    @ResponseBody
    @RequestMapping("pullSynchroData")
    @ApiOperation(value = "拉取同步数据", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<DataLogVO> pullSynchroData(
			@ApiParam(value = "接收到同步消息的最新下标", example = "5", name = "cursorValue", required = true) 
			@RequestParam("cursorValue") Long cursorValue ,
			@ApiParam(value = "是否获取该游标的数据", example = "false", name = "getValue", required = false, defaultValue = "false") 
			@RequestParam("getValue") boolean getValue) {
		if (cursorValue == null || cursorValue < 0) {
			logger.debug("下标值异常");
			cursorValue = 0L;
		}
		
		Long shopId = ThreadLocalContext.getShopId();
//		shopId=125L;
		long lastCursorValue = dataOperationLogFacade.getLastcursorValue(shopId);

		long sizeOfUnSync = dataOperationLogFacade.getSizeOfUnSync(shopId, cursorValue);
		if(logger.isInfoEnabled()) {
			logger.info("pullSynchroData shopId is " + shopId + "  lastCursorValue is " + lastCursorValue);
		}
		if (size < sizeOfUnSync) {
			DataLogVO vo = new DataLogVO();
			vo.setDataLogPullVOList(new ArrayList<>());
			vo.setLastCursorValue(lastCursorValue);
			vo.setRefreshAll(true);
			if (cursorValue == 0) {
				vo.setRefreshAll(false);
			}
			return Result.SUCESS(vo);
		}
		
//		List<DataLogPullDTO> dataLogPullDTOs = dataOperationLogFacade.pullDataLog(shopId, cursorValue, getValue);
		List<DataLogPullDTO> dataLogPullDTOs = dataOperationLogFacade.pullUnSyncDataLog(shopId, cursorValue, getValue);

		List<DataLogPullVO> dataLogPullVOs = getDataLogPullVOs(dataLogPullDTOs);

		// 最新的游标
		DataLogVO vo = new DataLogVO();
		vo.setDataLogPullVOList(dataLogPullVOs);
		vo.setLastCursorValue(lastCursorValue);
		vo.setRefreshAll(false);
		return Result.SUCESS(vo);
	}
    
    @ResponseBody
    @RequestMapping("pullSubTypeData")
    @ApiOperation(value = "根据类型拉取同步数据", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<DataLogVO>  pullSubTypeData(
			@ApiParam(value = "接收到同步消息的最新下标", example = "5", name = "cursorValue", required = true) 
			@RequestParam("cursorValue") Long cursorValue ,
			@ApiParam(value = "是否获取该游标的数据", example = "false", name = "getValue", required = false, defaultValue = "false") 
			@RequestParam("getValue") boolean getValue,
			@ApiParam(value = "变更高频的数据类型", required = true) 
			@RequestParam("dataType") HighFreqDataTypeEnum dataType){
		Long shopId = ThreadLocalContext.getShopId();
		long lastCursorValue = dataOperationLogFacade.getLastcursorValue(shopId, dataType);
		List<DataLogPullDTO> dataLogPullDTOs = dataOperationLogFacade.pullUnSyncDataLog(shopId, cursorValue, getValue, dataType);

		List<DataLogPullVO> dataLogPullVOs = getDataLogPullVOs(dataLogPullDTOs);

		// 最新的游标
		DataLogVO vo = new DataLogVO();
		vo.setDataLogPullVOList(dataLogPullVOs);
		vo.setLastCursorValue(lastCursorValue);
		vo.setRefreshAll(false);
		return Result.SUCESS(vo);
    }
    

    /**
	 * 获取拉取日志的vo
	 * @param dataLogPullDTOs
	 * @return
	 */
	private List<DataLogPullVO> getDataLogPullVOs(List<DataLogPullDTO> dataLogPullDTOs) {
		List<DataLogPullVO> voList = new ArrayList<DataLogPullVO>();
		if (CollectionUtils.isListNotBlank(dataLogPullDTOs)) {
			for (DataLogPullDTO dto : dataLogPullDTOs) {
				DataLogPullVO vo = new DataLogPullVO();
				vo.setDataType(dto.getDataType().getDataType());

				List<DataOperationLogVO> operationLogVOsList = new ArrayList<DataOperationLogVO>();
				if (CollectionUtils.isListNotBlank(dto.getOperationLogDTOs())) {
					for (DataOperationLogDTO operationLogDTO : dto.getOperationLogDTOs()) {
						for (int i = 0; i < operationLogDTO.getDataTimeDTOs().size(); i++) {
							String dataId = operationLogDTO.getDataTimeDTOs().get(i).getDataId();
							Date dataUpdateTime = operationLogDTO.getDataTimeDTOs().get(i).getUpdateTime();
							DataOperationLogVO dataOperationLogVO = new DataOperationLogVO();
							dataOperationLogVO.setDataId(dataId);
							dataOperationLogVO.setDataUpdateTime(dataUpdateTime);
							dataOperationLogVO.setDataType(operationLogDTO.getDataType().getDataType());
							dataOperationLogVO.setId(operationLogDTO.getId());
							dataOperationLogVO.setMerchantId(operationLogDTO.getMerchantId());
							dataOperationLogVO.setOperationType(operationLogDTO.getOperationType().getType());
							dataOperationLogVO.setShopId(operationLogDTO.getShopId());
							dataOperationLogVO.setSubDataType(operationLogDTO.getSubDataType().getSubDataType());
							operationLogVOsList.add(dataOperationLogVO);
						}
					}
				}
				vo.setOperationLogVOs(operationLogVOsList);
				voList.add(vo);
			}
		}
		return voList;
	}
}
