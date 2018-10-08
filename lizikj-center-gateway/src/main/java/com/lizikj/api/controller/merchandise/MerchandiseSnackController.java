package com.lizikj.api.controller.merchandise;

import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.merchandise.SnackExVO;
import com.lizikj.api.vo.merchandise.SnackVO;
import com.lizikj.api.vo.merchandise.param.SnackParamVO;
import com.lizikj.api.vo.merchandise.param.SnackQueryVO;
import com.lizikj.common.exception.ArgumentCheckException;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.merchandise.dto.SnackDTO;
import com.lizikj.merchandise.dto.SnackExDTO;
import com.lizikj.merchandise.dto.SnackQueryDTO;
import com.lizikj.merchandise.exception.MerchandiseException;
import com.lizikj.merchandise.facade.ISnackReadFacade;
import com.lizikj.merchandise.facade.ISnackWriteFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品加料服务
 * 
 * @author zhoufe
 * @date 2017/07/10
 */
@Controller
@RequestMapping("/merchandise/snack")
@Api(value = "merchandise-snack", tags = "商品-加料API接口")
public class MerchandiseSnackController {

	private static final Logger logger = LoggerFactory.getLogger(MerchandiseSnackController.class);

	@Autowired
	private ISnackReadFacade snackReadFacade;
	@Autowired
	private ISnackWriteFacade snackWriteFacade;

	/**
	 * 获取该店铺下的商品加料
	 * @param
	 * @return Result<List<ApiSnackVO>>
	 * @author zhoufe
	 * @date 2017年7月11日 下午4:49:48
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/listSnacks")
	@ApiOperation(value = "获取该店铺下的商品加料", notes = "获取该店铺下的商品加料", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<SnackVO>> listSnacks() {
		Result<List<SnackVO>> result = null;
		List<SnackVO> apiSnackVOs = null;
		List<SnackDTO> snackDTOs = null;
		try {
			snackDTOs = snackReadFacade.listSnacks(ThreadLocalContext.getShopId());
			apiSnackVOs = ObjectConvertUtil.copyListProperties(snackDTOs, SnackVO.class);
			result = Result.SUCESS(apiSnackVOs);
		} catch (MerchandiseException | ArgumentCheckException e) {
			logger.error("listSnacks Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/listSnacksByCondition")
	@ApiOperation(value = "根据条件获取该店铺下的商品加料", notes = "根据条件获取该店铺下的商品加料", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<SnackExVO>> listSnacksByCondition(@ApiParam(name = "snackQueryVO", value = "查询参数", required = true)
														   @RequestBody SnackQueryVO snackQueryVO) {
		Result<List<SnackExVO>> result = null;
		List<SnackExVO> apiSnackVOs = null;
		List<SnackExDTO> snackDTOs = null;
		try {
			snackQueryVO.setShopId(ThreadLocalContext.getShopId());
			snackDTOs = snackReadFacade.listSnackByCondition(ObjectConvertUtil.map(snackQueryVO, SnackQueryDTO.class));
			apiSnackVOs = ObjectConvertUtil.copyListProperties(snackDTOs, SnackExVO.class);
			result = Result.SUCESS(apiSnackVOs);
		} catch (MerchandiseException | ArgumentCheckException e) {
			logger.error("listSnacks Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}

	/**
	 * 获取该店铺下的商品加料：POS
	 * 根据snNum查询shopId
	 * @param snNum
	 * @return Result<List<SnackVO>>
	 * @author zhoufe
	 * @date 2017年7月11日 下午4:49:48
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/listSnacks4Pos")
	@ApiOperation(value = "获取该店铺下的商品加料：POS", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<SnackVO>> listSnacks4Pos(@RequestHeader("lz-sn") String snNum) {
		Result<List<SnackVO>> result;
		try {
			List<SnackDTO> snackDTOS = snackReadFacade.listSnacks4Pos(snNum);
			List<SnackVO> snackVOS = ObjectConvertUtil.copyListProperties(snackDTOS, SnackVO.class);
			result = Result.SUCESS(snackVOS);
		} catch (MerchandiseException | ArgumentCheckException e) {
			logger.error("listSnacks4Pos Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}



	/**
	 * 在该店铺下增加商品加料
	 * @param snackAddParamVO
	 * @return Result<String> 返回增加的加料ID
	 * @author zhoufe
	 * @date 2017年7月11日 下午8:22:28
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/saveSnack")
	@ApiOperation(value = "在该店铺下增加商品加料", notes = "在该店铺下增加商品加料", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<String> saveSnack(
	  	    @ApiParam(name = "addParamVO", value = "addParamVO", required = true)
			@RequestBody SnackParamVO snackAddParamVO) {
		Result<String> result;
		try {
			SnackDTO snackDTO = ObjectConvertUtil.copyProperties(SnackDTO.class, snackAddParamVO);
			snackDTO.setShopId(ThreadLocalContext.getShopId());
			String snackId = snackWriteFacade.saveSnack(snackDTO);
			result = Result.SUCESS(snackId);
		} catch (MerchandiseException | ArgumentCheckException e) {
			logger.error("saveSnack Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}

	/**
	 * 更新商品加料
	 * @param paramUpdateSnackVO
	 * @return Result<Boolean> 成功与否
	 * @author zhoufe 
	 * @date 2017年7月11日 下午8:44:54
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/updateSnacks")
	@ApiOperation(value = "更新商品加料", notes = "更新商品加料", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Boolean> updateSnacks(
	  	    @ApiParam(name = "updateSnackVO", value = "updateSnackVO", required = true)
			@RequestBody List<SnackParamVO> paramUpdateSnackVO) {
		Result<Boolean> result;
		try {
			List<SnackDTO> snackDTOs = ObjectConvertUtil.copyListProperties(paramUpdateSnackVO,
					SnackDTO.class);
			if (!org.springframework.util.CollectionUtils.isEmpty(snackDTOs)){
				snackDTOs.forEach(snackDTO -> snackDTO.setShopId(ThreadLocalContext.getShopId()));
			}
			Boolean isSuccess = snackWriteFacade.updateSnacks(snackDTOs);
			result = Result.SUCESS(isSuccess);
		} catch (MerchandiseException | ArgumentCheckException e) {
			logger.error("updateSnacks Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}
	
	/**
	 * 删除商品加料
	 * @param snackIds
	 * @return Result<Boolean>
	 * @author zhoufe 
	 * @date 2017年7月12日 上午9:24:20
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/deleteSnacks")
	@ApiOperation(value = "删除商品加料", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Boolean> deleteSnacks(
	  	    @ApiParam(name = "snackIds", value = "snackIds", required = true)
			@RequestBody List<String> snackIds) {
		Result<Boolean> result;
		try {
			Long shopId = ThreadLocalContext.getShopId();
			Boolean isSucce = snackWriteFacade.deleteSnacks(shopId, snackIds);
			result = Result.SUCESS(isSucce);
		} catch (MerchandiseException | ArgumentCheckException e) {
			logger.error("deleteSnacks Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}

	/**
	 * 获取商品加料
	 * @param snackIds
	 * @return Result<List<SnackVO>>
	 * @author zhoufe
	 * @date 2017年7月11日 下午4:49:48
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/listSnacksBySnackIds/{snackIds}")
	@ApiOperation(value = "获取该店铺下的商品加料：POS", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<SnackVO>> listSnacksBySnackIds(
			@ApiParam(name = "snackIds", value = "snackIds", required = true)
			@RequestParam(name = "snackIds") List<String> snackIds) {
		Result<List<SnackVO>> result;
		try {
			List<SnackDTO> snackDTOS = snackReadFacade.listSnacks(snackIds);
			List<SnackVO> snackVOS = ObjectConvertUtil.copyListProperties(snackDTOS, SnackVO.class);
			result = Result.SUCESS(snackVOS);
		} catch (MerchandiseException | ArgumentCheckException e) {
			logger.error("listSnacks4Pos Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}

}
