package com.lizikj.api.controller.merchandise;

import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.merchandise.SkuGenerateResultVO;
import com.lizikj.api.vo.merchandise.param.SkuGenerateVO;
import com.lizikj.common.exception.ArgumentCheckException;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.merchandise.dto.SkuGenerateDTO;
import com.lizikj.merchandise.dto.SkuGenerateResultDTO;
import com.lizikj.merchandise.exception.MerchandiseException;
import com.lizikj.merchandise.facade.ISkuReadFacade;
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

/**
 * 商品SKU服务
 * @author zhoufe
 * @date 2017/07/10
 */
@Controller
@RequestMapping("/merchandise/sku")
@Api(value = "merchandise-sku", tags = "商品-SKUAPI接口")
public class MerchandiseSkuController {

	private static final Logger logger = LoggerFactory.getLogger(MerchandiseSkuController.class);

	@Autowired
	private ISkuReadFacade skuReadFacade;
	/**
	 * 根据sku组产生sku明细
	 * 没有为每个明细输入价格和库存等，保存商品的时候才保存这些
	 * @param skuGenerateVO
	 * @return Result<List<SkuAddParamVO>>
	 * @author zhoufe
	 * @date 2017年7月20日 上午10:40:40
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/generateSku")
	@ApiOperation(value = "根据sku组产生sku明细", notes = "根据sku组产生sku明细", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<SkuGenerateResultVO> generateSku(
	  	    @ApiParam(name = "skuGenerateVO", value = "skuGenerateVO", required = true)
			@RequestBody SkuGenerateVO skuGenerateVO) {
		Result<SkuGenerateResultVO> result;
		SkuGenerateResultVO skuGenerateResultVO;
		try {
			//取出各个sku组的值list产生sku的list返回
			SkuGenerateDTO skuGenerateDTO = ObjectConvertUtil.map(skuGenerateVO, SkuGenerateDTO.class);
			SkuGenerateResultDTO skuGenerateResultDTO = skuReadFacade.generateSku(skuGenerateDTO);
			skuGenerateResultVO = ObjectConvertUtil.map(skuGenerateResultDTO,SkuGenerateResultVO.class);
			result = Result.SUCESS(skuGenerateResultVO);
		} catch (MerchandiseException | ArgumentCheckException e) {
			logger.error("generateSku Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}
	
	
	

}
