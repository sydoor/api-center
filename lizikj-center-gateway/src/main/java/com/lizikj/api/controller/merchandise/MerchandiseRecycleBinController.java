package com.lizikj.api.controller.merchandise;

import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.merchandise.GoodsVO;
import com.lizikj.api.vo.merchandise.param.GoodsSimpleQueryVO;
import com.lizikj.api.vo.merchandise.param.RecycleGoodsUpdateParamVO;
import com.lizikj.common.exception.ArgumentCheckException;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.merchandise.dto.GoodsDTO;
import com.lizikj.merchandise.dto.GoodsQueryDTO;
import com.lizikj.merchandise.dto.PageGoodsQueryConditionDTO;
import com.lizikj.merchandise.dto.RecycleGoodsDTO;
import com.lizikj.merchandise.enums.RemoveStatusEnum;
import com.lizikj.merchandise.enums.YesOrNoEnum;
import com.lizikj.merchandise.exception.MerchandiseException;
import com.lizikj.merchandise.facade.IGoodsReadFacade;
import com.lizikj.merchandise.facade.IGoodsWriteFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 商品回收站服务
 * @author zhoufe
 * @date 2017/07/10
 */
@Controller
@RequestMapping("/merchandise/recycle/bin")
@Api(value = "merchandise-recycle-bin", tags = "商品-回收站API接口")
public class MerchandiseRecycleBinController {

	private static final Logger logger = LoggerFactory.getLogger(MerchandiseRecycleBinController.class);

	@Autowired
	private IGoodsWriteFacade goodsWriteFacade;
	@Autowired
	private IGoodsReadFacade goodsReadFacade;
	/**
	 * 获取在该店铺下回收站的美食
	 * @params [shopId]
	 * @return com.lizikj.api.vo.Result<java.util.List<com.lizikj.api.vo.merchandise.GoodsVO>>
	 * @author zhoufe
	 * @date 2017/7/24 16:58
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/listRecycleGoods_v2")
	@ApiOperation(value = "获取在该店铺下回收站的美食", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<GoodsVO>> listRecycleGoods_v2(
			@ApiParam(name = "needComputeSpecialDiscount", value = "needComputeSpecialDiscount", required = false)
			@RequestBody YesOrNoEnum needComputeSpecialDiscount) {
		Result<List<GoodsVO>> result = null;
		List<GoodsVO> goodsVOS;
		try {
			GoodsQueryDTO goodsQueryDTO = new GoodsQueryDTO();
			goodsQueryDTO.setShopId(ThreadLocalContext.getShopId());
			goodsQueryDTO.setRemoveStatus(RemoveStatusEnum.RECYCLE);
			goodsQueryDTO.setNeedComputeSpecialDiscount(needComputeSpecialDiscount);

			List<GoodsDTO> goodsDTOS = goodsReadFacade.listGoodsByCondition(goodsQueryDTO);
			goodsVOS = ObjectConvertUtil.mapList(goodsDTOS,GoodsDTO.class,GoodsVO.class);
			result = Result.SUCESS(goodsVOS);
		} catch (MerchandiseException | ArgumentCheckException e) {
			logger.error("listRecycleGoods Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}

		return result;
	}


	/**
	 * 获取在该店铺下回收站的美食
	 * @params [shopId]
	 * @return com.lizikj.api.vo.Result<java.util.List<com.lizikj.api.vo.merchandise.GoodsVO>>
	 * @author zhoufe
	 * @date 2017/7/24 16:58
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/listRecycleGoods")
	@ApiOperation(value = "获取在该店铺下回收站的美食", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<GoodsVO>> listRecycleGoods() {
		Result<List<GoodsVO>> result = null;
		List<GoodsVO> goodsVOS;
		try {
			GoodsQueryDTO goodsQueryDTO = new GoodsQueryDTO();
			goodsQueryDTO.setShopId(ThreadLocalContext.getShopId());
			goodsQueryDTO.setRemoveStatus(RemoveStatusEnum.RECYCLE);
			List<GoodsDTO> goodsDTOS = goodsReadFacade.listGoodsByCondition(goodsQueryDTO);
			goodsVOS = ObjectConvertUtil.mapList(goodsDTOS,GoodsDTO.class,GoodsVO.class);
			result = Result.SUCESS(goodsVOS);
		} catch (MerchandiseException | ArgumentCheckException e) {
			logger.error("listRecycleGoods Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}

		return result;
	}

	@ResponseBody
	@RequestMapping("/listRecycleGoodsByCondition")
	@ApiOperation(value = "PC端，根据条件获取在该店铺下回收站的美食", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<GoodsVO>> listRecycleGoodsByCondition(@ApiParam(name = "goodsSimpleQueryVO", value = "查询参数", required = true)
																 @RequestBody GoodsSimpleQueryVO goodsSimpleQueryVO) {
		Result<List<GoodsVO>> result = null;
		List<GoodsVO> goodsVOS;
		try {
			PageGoodsQueryConditionDTO pageGoodsQueryConditionDTO = ObjectConvertUtil.map(goodsSimpleQueryVO,PageGoodsQueryConditionDTO.class);
			pageGoodsQueryConditionDTO.setShopId(ThreadLocalContext.getShopId());
			pageGoodsQueryConditionDTO.setRemoveStatus(RemoveStatusEnum.RECYCLE);

			List<GoodsDTO> goodsDTOS = goodsReadFacade.listGoodsByCondition(pageGoodsQueryConditionDTO);
			goodsVOS = ObjectConvertUtil.mapList(goodsDTOS,GoodsDTO.class,GoodsVO.class);
			result = Result.SUCESS(goodsVOS);
		} catch (MerchandiseException | ArgumentCheckException e) {
			logger.error("listRecycleGoods Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}

		return result;
	}

	/**
	 * 回收美食
	 * @params [recycleGoodsUpdateParamVO]
	 * @return com.lizikj.api.vo.Result<java.lang.Boolean>
	 * @author zhoufe
	 * @date 2017/7/24 16:38
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/recycleGoods")
	@ApiOperation(value = "回收美食", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Boolean> recycleGoods(
	  	    @ApiParam(name = "updateParamVO", value = "updateParamVO", required = true)
			@RequestBody RecycleGoodsUpdateParamVO recycleGoodsUpdateParamVO) {
		Result<Boolean> result = null;
		try {
			Boolean isSuccess;
			RecycleGoodsDTO recycleGoodsDTO = ObjectConvertUtil.copyProperties(RecycleGoodsDTO.class,recycleGoodsUpdateParamVO);
			isSuccess = goodsWriteFacade.recycleGoods(recycleGoodsDTO);
			result = Result.SUCESS(isSuccess);
		} catch (MerchandiseException | ArgumentCheckException e) {
			logger.error("recycleGoods Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}

		return result;
	}
	
	/**
	 * 彻底删除美食
	 * @params [goodsIds]
	 * @return com.lizikj.api.vo.Result<java.lang.Boolean>
	 * @author zhoufe
	 * @date 2017/7/24 16:33
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/deleteGoods")
	@ApiOperation(value = "彻底删除美食", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Boolean> deleteGoods(
	  	    @ApiParam(name = "goodsIds", value = "goodsIds", required = true)
			@RequestBody List<String> goodsIds) {
		Result<Boolean> result = null;
		try {
			Boolean isSuccess;
			isSuccess = goodsWriteFacade.deleteGoods(goodsIds,ThreadLocalContext.getShopId());
			result = Result.SUCESS(isSuccess);
		} catch (MerchandiseException | ArgumentCheckException e) {
			logger.error("deleteGoods Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}

		return result;
	}

}
