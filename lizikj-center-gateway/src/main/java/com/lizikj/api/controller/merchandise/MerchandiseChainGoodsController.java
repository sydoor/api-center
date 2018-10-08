package com.lizikj.api.controller.merchandise;

import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.merchandise.ChainGoodsVO;
import com.lizikj.api.vo.merchandise.SimpleChainGoodsVO;
import com.lizikj.api.vo.merchandise.param.ChainGoodsDeleteParamVO;
import com.lizikj.api.vo.merchandise.param.ChainGoodsParamVO;
import com.lizikj.api.vo.merchandise.param.ChainGoodsSummaryVO;
import com.lizikj.common.exception.ArgumentCheckException;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.merchandise.dto.ChainGoodsDTO;
import com.lizikj.merchandise.dto.ChainGoodsSummaryDTO;
import com.lizikj.merchandise.dto.DeleteChainGoodsDTO;
import com.lizikj.merchandise.dto.SimpleChainGoodsDTO;
import com.lizikj.merchandise.exception.MerchandiseException;
import com.lizikj.merchandise.facade.IChainGoodsReadFacade;
import com.lizikj.merchandise.facade.IChainGoodsWriteFacade;
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
 * 商品美食模板服务
 * 
 * @author zhoufe
 * @date 2017/07/10
 */
@Controller
@RequestMapping("/merchandise/chaingoods")
@Api(value = "merchandise-chaingoods", tags = "连锁商品-美食模板API接口")
public class MerchandiseChainGoodsController {

	private static final Logger logger = LoggerFactory.getLogger(MerchandiseChainGoodsController.class);

	@Autowired
	private IChainGoodsReadFacade chainGoodsReadFacade;
	@Autowired
	private IChainGoodsWriteFacade chainGoodsWriteFacade;

	/**
	 * 获取该商户下商品的模板数量总览
	 * @param
	 * @return Result<ChainGoodsSummaryVO>
	 * @author zhoufe
	 * @date 2017年7月11日 下午5:07:39
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/countChainGoodsSummaryByMerchant")
	@ApiOperation(value = "获取该商户下商品的模板数量总览", notes = "获取该商户下商品的模板数量总览", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<ChainGoodsSummaryVO> countChainGoodsSummaryByMerchant() {
		Result<ChainGoodsSummaryVO> result = null;

		try {
			ChainGoodsSummaryDTO chainGoodsSummaryDTO = chainGoodsReadFacade.countChainGoodsSummary(ThreadLocalContext.getMerchantId());
			ChainGoodsSummaryVO chainGoodsSummaryVO = ObjectConvertUtil.map(chainGoodsSummaryDTO,ChainGoodsSummaryVO.class);
			result = Result.SUCESS(chainGoodsSummaryVO);
		} catch (MerchandiseException | ArgumentCheckException e) {
			logger.error("countGoodsSummary Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}

		return result;
	}

	/**
	 * 在该商户下增加美食模板
	 * @param chainGoodsAddParamVO
	 * @return Result<Long> 新增美食模板的ID
	 * @author zhoufe
	 * @date 2017年7月12日 上午10:39:11
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/saveChainGoods")
	@ApiOperation(value = "在该商户下增加美食模板", notes = "在该商户下增加美食模板", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<ChainGoodsVO> saveChainGoods(
	  	    @ApiParam(name = "addParamVO", value = "addParamVO", required = true)
			@RequestBody ChainGoodsParamVO chainGoodsAddParamVO) {
		Result<ChainGoodsVO> result = null;
		try {
			ChainGoodsDTO chainGoodsDTO = ObjectConvertUtil.map(chainGoodsAddParamVO,ChainGoodsDTO.class);
			chainGoodsDTO.setMerchantId(ThreadLocalContext.getMerchantId());
			//因为是模板，所以会下发到该商户的各个门店创建Goods，之后让各个门店去编辑该商品怎么卖（sku）
			ChainGoodsDTO chainGoods = chainGoodsWriteFacade.saveChainGoods(chainGoodsDTO);

			result = Result.SUCESS(ObjectConvertUtil.map(chainGoods,ChainGoodsDTO.class));
		} catch (MerchandiseException | ArgumentCheckException e) {
			logger.error("saveChainGoods Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}
	
	/**
	 * 更新美食模板
	 * @param chainGoodsUpdateParamVO
	 * @return Result<Boolean>
	 * @author zhoufe 
	 * @date 2017年7月12日 下午5:20:01
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/updateChainGoods")
	@ApiOperation(value = "更新美食模板", notes = "更新美食模板", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<ChainGoodsVO> updateChainGoods(
	  	    @ApiParam(name = "updateParamVO", value = "updateParamVO", required = true)
			@RequestBody ChainGoodsParamVO chainGoodsUpdateParamVO) {
		Result<ChainGoodsVO> result = null;
		try {
			ChainGoodsDTO chainGoodsDTO = ObjectConvertUtil.map(chainGoodsUpdateParamVO,ChainGoodsDTO.class);
			ChainGoodsDTO chainGoods = chainGoodsWriteFacade.updateChainGoods(chainGoodsDTO);
			result = Result.SUCESS(ObjectConvertUtil.map(chainGoods,ChainGoodsDTO.class));
		} catch (MerchandiseException | ArgumentCheckException e) {
			logger.error("updateChainGoods Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}
	
	/**
	 * 根据选择的删除类型删除美食模板
	 * 立即删除美食模板就直接放入回收站，门店使用该模板的美食不显示
	 * 不是立即删除的插入店铺任务，到时间放入回收站
	 * @param chainGoodsDeleteParamVO
	 * @return Result<Boolean>
	 * @author zhoufe 
	 * @date 2017年7月12日 下午5:24:03
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/deleteChainGoods")
	@ApiOperation(value = "根据选择的删除类型删除美食模板：定时或者立即放入回收站", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Boolean> deleteChainGoods(
	  	    @ApiParam(name = "chainGoodsDeleteParamVO", value = "chainGoodsDeleteParamVO", required = true)
			@RequestBody ChainGoodsDeleteParamVO chainGoodsDeleteParamVO) {
		Result<Boolean> result = null;
		try {
			DeleteChainGoodsDTO deleteChainGoodsDTO = ObjectConvertUtil.map(chainGoodsDeleteParamVO,DeleteChainGoodsDTO.class);
			deleteChainGoodsDTO.setShopId(ThreadLocalContext.getShopId());
			Boolean suc = chainGoodsWriteFacade.deleteChainGoods(deleteChainGoodsDTO);
			result = Result.SUCESS(suc);
		} catch (MerchandiseException | ArgumentCheckException e) {
			logger.error("deleteChainGoods Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}

	/**
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findToBeDeleteChainGoods")
	@ApiOperation(value = "获取商户下即将删除的美食模板列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<SimpleChainGoodsVO>> findToBeDeleteChainGoods(){
		Result<List<SimpleChainGoodsVO>> result = null;
		try {

			List<SimpleChainGoodsDTO> chainGoodsDTOS = chainGoodsReadFacade.findToBeDeleteChainGoods(ThreadLocalContext.getMerchantId());
			result = Result.SUCESS(ObjectConvertUtil.mapList(chainGoodsDTOS,SimpleChainGoodsDTO.class,SimpleChainGoodsVO.class));
		}catch (MerchandiseException e){
			logger.error("findToBeDeleteChainGoods Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		//
		return result;
	}


	/**
	 * 取消美食模板删除任务
	 * @param chainGoodsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/cancellDeleteChainGoods/{chainGoodsId}")
	@ApiOperation(value = "获取商户下即将删除的美食模板列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Boolean> cancellDeleteChainGoods(
			@ApiParam(name = "chainGoodsId", value = "chainGoodsId", required = true)
			@PathVariable String chainGoodsId){
		Result<Boolean> result = null;
		try {

			boolean rst = chainGoodsWriteFacade.cancellDeleteChainGoods(ThreadLocalContext.getMerchantId(),chainGoodsId);
			result = Result.SUCESS(rst);
		}catch (MerchandiseException e){
			logger.error("cancellDeleteChainGoods Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}

	/**
	 * 找到店铺中，美食回收站中的美食模板
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findRecycleBinChainGoods")
	@ApiOperation(value = "找到店铺中，美食回收站中的美食模板", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<ChainGoodsVO>> findRecycleBinChainGoods(){
		Result<List<ChainGoodsVO>> result;

		try {
			List<ChainGoodsDTO> chainGoodsDTOS = chainGoodsReadFacade.findGoodsInRecycleBin(ThreadLocalContext.getShopId());
			result = Result.SUCESS(ObjectConvertUtil.mapList(chainGoodsDTOS,ChainGoodsDTO.class,ChainGoodsVO.class));
		}catch (MerchandiseException e){
			logger.error("findRecycleBinChainGoods Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}

		return result;
	}

	@ResponseBody
	@RequestMapping("/listByIds")
	@ApiOperation(value = "根据id查询美食模板",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<ChainGoodsVO>> listByIds(
			@ApiParam(name = "ids", value = "美食模板id", required = true, type = "List")
			@RequestParam(value = "ids") List<String> ids){
		Result<List<ChainGoodsVO>> result;
		try {
			List<ChainGoodsDTO> chainGoodsDTOS = chainGoodsReadFacade.listByIds(ids);
			result = Result.SUCESS(ObjectConvertUtil.mapList(chainGoodsDTOS,ChainGoodsDTO.class,ChainGoodsVO.class));
		}catch (MerchandiseException e){
			logger.error("listByIds Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}

		return result;
	}

}
