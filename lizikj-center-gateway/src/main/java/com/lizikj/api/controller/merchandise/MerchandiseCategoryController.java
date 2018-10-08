package com.lizikj.api.controller.merchandise;

import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.merchandise.CategoryVO;
import com.lizikj.api.vo.merchandise.ChainGoodsVO;
import com.lizikj.api.vo.merchandise.GoodsVO;
import com.lizikj.api.vo.merchandise.PCGoodsVO;
import com.lizikj.api.vo.merchandise.param.CategoryCodeQueryParamVO;
import com.lizikj.api.vo.merchandise.param.CategoryIdQueryParamVO;
import com.lizikj.api.vo.merchandise.param.CategoryParamVO;
import com.lizikj.api.vo.merchandise.param.UpdateCategoryOrderParamVO;
import com.lizikj.common.exception.ArgumentCheckException;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.merchandise.dto.CategoryDTO;
import com.lizikj.merchandise.dto.ChainGoodsDTO;
import com.lizikj.merchandise.dto.GoodsDTO;
import com.lizikj.merchandise.dto.UpdateCategoryOrderDTO;
import com.lizikj.merchandise.enums.CategoryCodeEnum;
import com.lizikj.merchandise.enums.MerchandiseErrorEnum;
import com.lizikj.merchandise.enums.YesOrNoEnum;
import com.lizikj.merchandise.exception.MerchandiseException;
import com.lizikj.merchandise.facade.ICategoryReadFacade;
import com.lizikj.merchandise.facade.ICategoryWriteFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品分类服务
 * 
 * @author zhoufe
 * @date 2017/07/10
 */
@Controller
@RequestMapping("/merchandise/category")
@Api(value = "merchandise-category", tags = "商品-分类API接口")
public class MerchandiseCategoryController {

	/**
	 * @private
	 */
	private static final Logger logger = LoggerFactory.getLogger(MerchandiseCategoryController.class);

	@Autowired
	private ICategoryWriteFacade categoryWriteFacade;
	@Autowired
	private ICategoryReadFacade categoryReadFacade;
	/**
	 * 获取该店铺下的商品分类
	 * @return Result<List<CategoryVO>>
	 * @author zhoufe
	 * @date 2017年7月11日 下午4:49:48
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/listCategories")
	@ApiOperation(value = "获取该店铺下的商品分类", notes = "获取该店铺下的商品分类", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<CategoryVO>> listCategories() {
		Result<List<CategoryVO>> result;
		try {
			List<CategoryDTO> categoryDTOS = categoryReadFacade.listCategories(ThreadLocalContext.getShopId());
			List<CategoryVO> categoryVOS = ObjectConvertUtil.mapList(categoryDTOS,CategoryDTO.class, CategoryVO.class);
			result = Result.SUCESS(categoryVOS);
		} catch (MerchandiseException | ArgumentCheckException e) {
			logger.error("listCategories Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}

	/**
	 * 获取该店铺下的商品分类：POS
	 * @param snNum
	 * @return Result<List<CategoryVO>>
	 * @author zhoufe
	 * @date 2017年7月11日 下午4:49:48
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/listCategories4Pos")
	@ApiOperation(value = "获取该店铺下的商品分类：POS", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<CategoryVO>> listCategories4Pos(@RequestHeader("lz-sn") String snNum) {
		Result<List<CategoryVO>> result = null;
		List<CategoryVO> apiCategoryVOs = null;
		List<CategoryDTO> categoryDTOs = null;
		try {
			categoryDTOs = categoryReadFacade.listCategories4Pos(snNum);
			apiCategoryVOs = ObjectConvertUtil.mapList(categoryDTOs, CategoryDTO.class,CategoryVO.class);
			result = Result.SUCESS(apiCategoryVOs);
		} catch (MerchandiseException | ArgumentCheckException e) {
			logger.error("listCategories4Pos Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}


	/**
	 * 1. 根据商户id查询该商户下的所有分类
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listCategoriesByMerchantId")
	@ApiOperation(value = "获取该商户下所有的商品分类", notes = "获取该商户下所有的商品分类", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<CategoryVO>> listCategoriesByMerchantId(){
		Result<List<CategoryVO>> result = null;
		List<CategoryVO> apiCategoryVOs = null;
		List<CategoryDTO> categoryDTOS = null;
		try {
			categoryDTOS = categoryReadFacade.listCategoriesByMerchantId(ThreadLocalContext.getMerchantId());
			apiCategoryVOs = ObjectConvertUtil.mapList(categoryDTOS,CategoryDTO.class,CategoryVO.class);
			result = Result.SUCESS(apiCategoryVOs);
		}catch (MerchandiseException e){
			logger.error("listCategoriesByMerchantId Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}

		return result;
	}


	/**
	 * 根据分类id查询一个分类下的所有商品,分类可能为连锁的分类和门店自有分类两种类型，连锁分类在ChainGoods里查询，门店自有分类在Goods里查询
	 * @param categoryIdQueryParamVO
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listGoodsByCategoryId_v2")
	@ApiOperation(value = "获取该分类下所有的商品，门店自有的美食", notes = "获取该分类下所有的商品,门店自有的美食", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<PCGoodsVO>> listGoodsByCategoryId_v2(
			@ApiParam(name = "categoryIdQueryParamVO", value = "categoryIdQueryParamVO", required = true)
			@RequestBody CategoryIdQueryParamVO categoryIdQueryParamVO){
		Result<List<PCGoodsVO>> result = null;
		List<GoodsDTO> goodsDTOS= null;
		List<PCGoodsVO> goodsVOS = null;
		try {
			goodsDTOS = categoryReadFacade.listGoodsByCategoryId(categoryIdQueryParamVO.getCategoryId(),categoryIdQueryParamVO.getNeedComputeSpecialDiscount());
			goodsVOS = ObjectConvertUtil.mapList(goodsDTOS,GoodsDTO.class,PCGoodsVO.class);

			if (!CollectionUtils.isEmpty(goodsVOS)){
				goodsVOS.forEach(pcGoodsVO -> {
					boolean containsQuickMenu = false;
					List<CategoryVO> categoryVOS = pcGoodsVO.getCategories();
					if (!CollectionUtils.isEmpty(categoryVOS)){
						for (CategoryVO categoryVO : categoryVOS) {
							if (categoryVO.getCode() == CategoryCodeEnum.QUICK_MENU){
								containsQuickMenu = true;
								break;
							}
						}
					}

					pcGoodsVO.setContainsQuickMenu(containsQuickMenu);
				});
			}

			result = Result.SUCESS(goodsVOS);
		}catch (MerchandiseException e){
			logger.error("listGoodsByCategoryId Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}

		return result;
	}

	/**
	 * 根据分类id查询一个分类下的所有商品,分类可能为连锁的分类和门店自有分类两种类型，连锁分类在ChainGoods里查询，门店自有分类在Goods里查询
	 * @param categoryId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listGoodsByCategoryId/{categoryId}")
	@ApiOperation(value = "获取该分类下所有的商品，门店自有的美食", notes = "获取该分类下所有的商品,门店自有的美食", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<PCGoodsVO>> listGoodsByCategoryId(
			@ApiParam(name = "categoryId", value = "categoryId", required = true)
			@PathVariable String categoryId){
		Result<List<PCGoodsVO>> result = null;
		List<GoodsDTO> goodsDTOS= null;
		List<PCGoodsVO> goodsVOS = null;
		try {
			goodsDTOS = categoryReadFacade.listGoodsByCategoryId(categoryId, YesOrNoEnum.NO);
			goodsVOS = ObjectConvertUtil.mapList(goodsDTOS,GoodsDTO.class,PCGoodsVO.class);

			if (!CollectionUtils.isEmpty(goodsVOS)){
				goodsVOS.forEach(pcGoodsVO -> {
					boolean containsQuickMenu = false;
					List<CategoryVO> categoryVOS = pcGoodsVO.getCategories();
					if (!CollectionUtils.isEmpty(categoryVOS)){
						for (CategoryVO categoryVO : categoryVOS) {
							if (categoryVO.getCode() == CategoryCodeEnum.QUICK_MENU){
								containsQuickMenu = true;
								break;
							}
						}
					}

					pcGoodsVO.setContainsQuickMenu(containsQuickMenu);
				});
			}

			result = Result.SUCESS(goodsVOS);
		}catch (MerchandiseException e){
			logger.error("listGoodsByCategoryId Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}

		return result;
	}


	/**
	 * 根据分类id查询分类
	 * @param categoryIds
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listCategoriesByIds")
	@ApiOperation(value = "根据分类id查询分类", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<CategoryVO>> listCategoriesByIds(
			@ApiParam(name = "categoryIds", value = "分类ID", required = true, type = "List")
			@RequestParam(value = "categoryIds") List<String> categoryIds){
		Result<List<CategoryVO>> result = null;
		List<CategoryVO> apiCategoryVOs = null;
		List<CategoryDTO> categoryDTOS = null;
		try {
			categoryDTOS = categoryReadFacade.listCategoriesByIds(categoryIds);
			apiCategoryVOs = ObjectConvertUtil.mapList(categoryDTOS,CategoryDTO.class,CategoryVO.class);
			result = Result.SUCESS(apiCategoryVOs);
		}catch (MerchandiseException e){
			logger.error("listCategoriesByIds Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}

		return result;
	}



	/**
	 * 获取该编码分类下所有的商品
	 * @params [code]
	 * @return com.lizikj.api.vo.Result<java.util.List<com.lizikj.api.vo.merchandise.GoodsVO>>
	 * @author zhoufe
	 * @date 2017/8/23 14:59
	 */
	@ResponseBody
	@RequestMapping("/listGoodsByCode_v2")
	@ApiOperation(value = "获取该编码分类下所有的商品", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<GoodsVO>> listGoodsByCode_v2(
			@ApiParam(name = "categoryCodeQueryParamVO", value = "分类编码：见CategoryCodeEnum：QUICK_MENU:快捷菜单，MEMBER_PRICE:会员价美食，CUSTOM_CATEGORY:其他的编码为自定义的标签", required = true, type = "String")
				@RequestBody CategoryCodeQueryParamVO categoryCodeQueryParamVO){
		Result<List<GoodsVO>> result = null;
		List<GoodsDTO> goodsDTOS= null;
		List<GoodsVO> goodsVOS = null;
		try {
			Long shopId = ThreadLocalContext.getShopId();
			goodsDTOS = categoryReadFacade.listGoodsByCode(shopId, categoryCodeQueryParamVO.getCode(),categoryCodeQueryParamVO.getNeedComputeSpecialDiscount());
			goodsVOS = ObjectConvertUtil.mapList(goodsDTOS,GoodsDTO.class,GoodsVO.class);
			result = Result.SUCESS(goodsVOS);
		}catch (MerchandiseException e){
			logger.error("listGoodsByCode Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}

		return result;
	}

	/**
	 * 获取该编码分类下所有的商品
	 * @params [code]
	 * @return com.lizikj.api.vo.Result<java.util.List<com.lizikj.api.vo.merchandise.GoodsVO>>
	 * @author zhoufe
	 * @date 2017/8/23 14:59
	 */
	@ResponseBody
	@RequestMapping("/listGoodsByCode")
	@ApiOperation(value = "获取该编码分类下所有的商品", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<GoodsVO>> listGoodsByCode(
			@ApiParam(name = "code", value = "分类编码：见CategoryCodeEnum：QUICK_MENU:快捷菜单，MEMBER_PRICE:会员价美食，CUSTOM_CATEGORY:其他的编码为自定义的标签", required = true, type = "String")
			@RequestBody CategoryCodeEnum code){
		Result<List<GoodsVO>> result = null;
		List<GoodsDTO> goodsDTOS= null;
		List<GoodsVO> goodsVOS = null;
		try {
			Long shopId = ThreadLocalContext.getShopId();
			goodsDTOS = categoryReadFacade.listGoodsByCode(shopId, code,YesOrNoEnum.NO);
			goodsVOS = ObjectConvertUtil.mapList(goodsDTOS,GoodsDTO.class,GoodsVO.class);
			result = Result.SUCESS(goodsVOS);
		}catch (MerchandiseException e){
			logger.error("listGoodsByCode Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}

		return result;
	}

	/**
	 * 根据分类id查询一个分类下的所有商品,分类可能为连锁的分类和门店自有分类两种类型，连锁分类在ChainGoods里查询，门店自有分类在Goods里查询
	 * @param categoryId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listChainGoodsByCategoryId/{categoryId}")
	@ApiOperation(value = "获取该分类下所有的商品,特指连锁美食", notes = "获取该分类下所有的商品,特指连锁美食", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<ChainGoodsVO>> listChainGoodsByCategoryId(
			@ApiParam(name = "categoryId", value = "分类ID", required = true, type = "String")
			@PathVariable(name = "categoryId")String categoryId){
		Result<List<ChainGoodsVO>> result = null;
		List<ChainGoodsVO> goodsVOS = null;
		List<ChainGoodsDTO> goodsDTOS = null;
		try {
			goodsDTOS = categoryReadFacade.listChainGoodsByCategoryId(categoryId);
			goodsVOS = ObjectConvertUtil.mapList(goodsDTOS,ChainGoodsDTO.class,ChainGoodsVO.class);
			result = Result.SUCESS(goodsVOS);
		}catch (MerchandiseException e){
			logger.error("listChainGoodsByCategoryId Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}

		return result;
	}


	/**
	 * 在该店铺下增加商品分类
	 * 
	 * @param categoryAddParamVO
	 * @return Result<String> 返回增加的分类ID
	 * @author zhoufe
	 * @date 2017年7月11日 下午8:22:28
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/saveCategory")
	@ApiOperation(value = "在该店铺下增加商品分类", notes = "在该店铺下增加商品分类", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<String> saveCategory(
	  	    @ApiParam(name = "addParamVO", value = "addParamVO", required = true)
	  	    @RequestBody CategoryParamVO categoryAddParamVO) {
		Result<String> result = null;
		try {
			CategoryDTO categoryDTO = ObjectConvertUtil.map(categoryAddParamVO,CategoryDTO.class);
			categoryDTO.setShopId(ThreadLocalContext.getShopId());
			categoryDTO.setMerchantId(ThreadLocalContext.getMerchantId());
			String categoryId = categoryWriteFacade.saveCategory(categoryDTO);
			result = Result.SUCESS(categoryId);
		} catch (MerchandiseException | ArgumentCheckException e) {
			logger.error("saveCategory Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}

	/**
	 * 更新商品分类
	 * @param categoryUpdateParamVOs
	 * @return Result<Boolean> 成功与否
	 * @author zhoufe 
	 * @date 2017年7月11日 下午8:44:54
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/updateCategories")
	@ApiOperation(value = "更新商品分类", notes = "更新商品分类", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Boolean> updateCategories(
	  	    @ApiParam(name = "updateParamVOs", value = "updateParamVOs", required = true)
			@RequestBody List<CategoryParamVO> categoryUpdateParamVOs) {
		Result<Boolean> result = null;
		try {
			List<CategoryDTO> categoryDTOs = ObjectConvertUtil.mapList(categoryUpdateParamVOs,CategoryParamVO.class, CategoryDTO.class);
			if (!CollectionUtils.isEmpty(categoryDTOs)){
				categoryDTOs.forEach(categoryDTO -> {
					categoryDTO.setShopId(ThreadLocalContext.getShopId());
					categoryDTO.setMerchantId(ThreadLocalContext.getMerchantId());
				});
				Boolean isSuccess = categoryWriteFacade.updateCategories(categoryDTOs);
				result = Result.SUCESS(isSuccess);
			}
		} catch (MerchandiseException | ArgumentCheckException e) {
			logger.error("updateCategories Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}
	
	/**
	 * 删除商品分类
	 * @param categoryIds
	 * @return Result<Boolean>
	 * @author zhoufe 
	 * @date 2017年7月12日 上午9:24:20
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/deleteCategories")
	@ApiOperation(value = "删除商品分类", notes = "删除商品分类", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Boolean> deleteCategories(
	  	    @ApiParam(name = "categoryIds", value = "categoryIds", required = true)
			@RequestBody List<String> categoryIds) {
		Result<Boolean> result = null;
		try {
			Boolean isSuccess = categoryWriteFacade.deleteCategories(categoryIds);
			result = Result.SUCESS(isSuccess);
		} catch (MerchandiseException | ArgumentCheckException e) {
			logger.error("deleteCategories Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}

	/**
	 * 获取店铺下该编码的所有分类
	 * @params [code]
	 * @return
	 * @author zhoufe
	 * @date 2017/8/23 14:59
	 */
	@ResponseBody
	@RequestMapping("/listCategoriesByCode")
	@ApiOperation(value = "获取店铺下该编码的所有分类", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<CategoryVO>> listCategoriesByCode(
			@ApiParam(name = "code", value = "分类编码：见CategoryCodeEnum：QUICK_MENU:快捷菜单，MEMBER_PRICE:会员价美食，CUSTOM_CATEGORY:其他的编码为自定义的标签", required = true)
			@RequestBody CategoryCodeEnum code){
		Result<List<CategoryVO>> result = null;
		List<CategoryDTO> categoryDTOS= null;
		List<CategoryVO> categoryVOS = null;
		try {
			categoryDTOS = categoryReadFacade.listCategoriesByCategoryCode(ThreadLocalContext.getShopId(), code);
			categoryVOS = ObjectConvertUtil.mapList(categoryDTOS,CategoryDTO.class,CategoryVO.class);
			result = Result.SUCESS(categoryVOS);
		}catch (MerchandiseException e){
			logger.error("listCategoriesByCode Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}

		return result;
	}


	@ResponseBody
	@RequestMapping("/updateCategoryOrder")
	@ApiOperation(value = "更新分类顺序", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Boolean> updateCategoryOrder(
			@ApiParam(name = "updateCategoryOrderParamVOS", value = "updateCategoryOrderParamVOS", required = true)
			@RequestBody List<UpdateCategoryOrderParamVO> updateCategoryOrderParamVOS){
			Result<Boolean> result;

			try {
				Boolean b = categoryWriteFacade.updateCategoryOrder(
						ObjectConvertUtil.mapList(updateCategoryOrderParamVOS,UpdateCategoryOrderParamVO.class, UpdateCategoryOrderDTO.class));

				result = Result.SUCESS(b);
			}catch(MerchandiseException e){
				logger.error("updateCategoryOrder Exception: {}", e);
				result = Result.FAILURE(e.getCode(), e.getMessage());
			}catch (Exception e){
				logger.error("updateCategoryOrder Exception: {}", e);
				result = Result.FAILURE(MerchandiseErrorEnum.SYSTEM_ERROR.getMessage());
			}

		return result;
	}

}
