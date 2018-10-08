package com.lizikj.api.controller.merchandise;

import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.merchandise.TagVO;
import com.lizikj.api.vo.merchandise.param.TagParamVO;
import com.lizikj.api.vo.merchandise.param.TagQueryVO;
import com.lizikj.common.exception.ArgumentCheckException;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.merchandise.dto.TagDTO;
import com.lizikj.merchandise.dto.TagQueryDTO;
import com.lizikj.merchandise.exception.MerchandiseException;
import com.lizikj.merchandise.facade.ITagReadFacade;
import com.lizikj.merchandise.facade.ITagWriteFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 商品标签服务
 * 
 * @author zhoufe
 * @date 2017/07/10
 */
@Controller
@RequestMapping("/merchandise/tag")
@Api(value = "merchandise-tag", tags = "商品-标签API接口")
public class MerchandiseTagController {

	private static final Logger logger = LoggerFactory.getLogger(MerchandiseTagController.class);

	@Autowired
	private ITagReadFacade tagReadFacade;

	@Autowired
	private ITagWriteFacade tagWriteFacade;

	/**
	 * 获取该店铺下的商品标签
	 * @return Result<List<ApiSnackVO>>
	 * @author zhoufe
	 * @date 2017年7月11日 下午4:49:48
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/listTags")
	@ApiOperation(value = "获取该店铺下的商品标签", notes = "获取该店铺下的商品标签", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<TagVO>> listTags() {
		Result<List<TagVO>> result;

		try {
			Long shopId = ThreadLocalContext.getShopId();
			List<TagDTO> tagDTOS = tagReadFacade.listTags(shopId);
			List<TagVO> tagVOS = ObjectConvertUtil.copyListProperties(tagDTOS, TagVO.class);
			result = Result.SUCESS(tagVOS);
		} catch (MerchandiseException | ArgumentCheckException e) {
			logger.error("listTags Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}


	@ResponseBody
	@RequestMapping("/listTagsByCondition")
	@ApiOperation(value = "根据条件获取该店铺下的商品标签", notes = "根据条件获取该店铺下的商品标签", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<TagVO>> listTagsByCondition(@ApiParam(name = "tagQueryVO", value = "查询参数", required = true)
													   @RequestBody TagQueryVO tagQueryVO) {
		Result<List<TagVO>> result;

		try {
			Long shopId = ThreadLocalContext.getShopId();
			tagQueryVO.setShopId(shopId);
			List<TagDTO> tagDTOS = tagReadFacade.listTagsByCondition(ObjectConvertUtil.map(tagQueryVO, TagQueryDTO.class));
			List<TagVO> tagVOS = ObjectConvertUtil.copyListProperties(tagDTOS, TagVO.class);
			result = Result.SUCESS(tagVOS);
		} catch (MerchandiseException | ArgumentCheckException e) {
			logger.error("listTags Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}

	/**
	 * 获取该店铺下的商品标签：pos
	 * 根据snNum查询shopId
	 * @param snNum
	 * @return Result<List<TagVO>>
	 * @author zhoufe
	 * @date 2017年7月11日 下午4:49:48
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/listTags4Pos")
	@ApiOperation(value = "获取该店铺下的商品标签：POS", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<TagVO>> listTags4Pos(@RequestHeader("lz-sn") String snNum) {
		Result<List<TagVO>> result;
		try {
			List<TagDTO> tagDTOS = tagReadFacade.listTags4Pos(snNum);
			List<TagVO> tagVOS = ObjectConvertUtil.copyListProperties(tagDTOS, TagVO.class);
			result = Result.SUCESS(tagVOS);
		} catch (MerchandiseException | ArgumentCheckException e) {
			logger.error("listTags4Pos Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}


	/**
	 * 在该店铺下增加商品标签
	 * @param tagAddParamVO
	 * @return Result<String> 返回增加的标签ID
	 * @author zhoufe
	 * @date 2017年7月11日 下午8:22:28
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/saveTag")
	@ApiOperation(value = "在该店铺下增加商品标签", notes = "在该店铺下增加商品标签", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<String> saveTag(
	  	    @ApiParam(name = "addParamVO", value = "addParamVO", required = true)
			@RequestBody TagParamVO tagAddParamVO) {
		Result<String> result;
		try {
			Long shopId = ThreadLocalContext.getShopId();
			TagDTO tagDTO = ObjectConvertUtil.copyProperties(TagDTO.class, tagAddParamVO);
			if (tagDTO != null){
				tagDTO.setShopId(shopId);
			}
			String tagId = tagWriteFacade.saveTag(tagDTO);
			result = Result.SUCESS(tagId);
		} catch (MerchandiseException | ArgumentCheckException e) {
			logger.error("saveTag Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}

	/**
	 * 在该店铺下更新商品标签
	 * @param tagParamVO
	 * @return Result<Boolean> 返回更新的标签成功与否
	 * @author zhoufe
	 * @date 2017年7月11日 下午8:22:28
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/updateTag")
	@ApiOperation(value = "在该店铺下更新商品标签", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Boolean> updateTag(
			@ApiParam(name = "tagParamVO", value = "tagParamVO", required = true)
			@RequestBody TagParamVO tagParamVO) {
		Result<Boolean> result;
		try {
			TagDTO tagDTO = ObjectConvertUtil.map(tagParamVO,TagDTO.class);
			Boolean isSucc = tagWriteFacade.updateTag(tagDTO);
			result = Result.SUCESS(isSucc);
		} catch (MerchandiseException | ArgumentCheckException e) {
			logger.error("updateTag Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}

	
	/**
	 * 删除商品标签
	 * @param tagIds
	 * @return Result<Boolean>
	 * @author zhoufe 
	 * @date 2017年7月12日 上午9:24:20
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/deleteTags")
	@ApiOperation(value = "删除商品标签", notes = "删除商品标签", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Boolean> deleteTags(
	  	    @ApiParam(name = "tagIds", value = "tagIds", required = true)
			@RequestBody List<String> tagIds) {
		Result<Boolean> result;
		try {
			Boolean isSuccess = tagWriteFacade.deleteTags(tagIds);
			result = Result.SUCESS(isSuccess);
		} catch (MerchandiseException | ArgumentCheckException e) {
			logger.error("deleteTags Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}
	
	

}
