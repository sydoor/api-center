package com.lizikj.api.controller.merchandise;


import com.lizikj.api.vo.PageVO;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.merchandise.*;
import com.lizikj.api.vo.merchandise.param.*;
import com.lizikj.common.exception.ArgumentCheckException;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.merchandise.dto.*;
import com.lizikj.merchandise.enums.*;
import com.lizikj.merchandise.exception.MerchandiseException;
import com.lizikj.merchandise.facade.ICategoryReadFacade;
import com.lizikj.merchandise.facade.IGoodsReadFacade;
import com.lizikj.merchandise.facade.IGoodsWriteFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.ReaderConfig;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * 门店商品美食服务
 *
 * @author zhoufe
 * @date 2017/07/10
 */
@Controller
@RequestMapping("/merchandise/goods")
@Api(value = "merchandise-goods", tags = "门店商品-美食API接口")
public class MerchandiseGoodsController {

    private static final Logger logger = LoggerFactory.getLogger(MerchandiseGoodsController.class);

    @Autowired
    private IGoodsReadFacade goodsReadFacade;
    @Autowired
    private IGoodsWriteFacade goodsWriteFacade;
    @Autowired
    private ICategoryReadFacade categoryReadFacade;

    @Autowired
    private Environment env;

    /**
     * 获取该店铺下商品的数量总览
     *
     * @param
     * @return Result<ApiGoodsSummaryVO>
     * @author zhoufe
     * @date 2017年7月11日 下午5:07:39
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/countGoodsSummary")
    @ApiOperation(value = "获取该店铺下商品的数量总览", notes = "获取该店铺下商品的数量总览", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<GoodsSummaryVO> countGoodsSummary() {
        Result<GoodsSummaryVO> result = null;

        GoodsSummaryDTO goodsSummaryDTO = null;
        try {
            goodsSummaryDTO = goodsReadFacade.countGoodsSummary(ThreadLocalContext.getShopId());
            GoodsSummaryVO apiGoodsSummaryVO = ObjectConvertUtil.map(goodsSummaryDTO, GoodsSummaryVO.class);
            result = Result.SUCESS(apiGoodsSummaryVO);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("countGoodsSummary Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }


    /**
     * 新增美食
     *
     * @param goodsParamVO
     * @return Result<Long> 新增美食的ID
     * @author zhoufe
     * @date 2017年7月12日 上午10:39:11
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/saveGoods")
    @ApiOperation(value = "新增美食", notes = "新增美食", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<GoodsVO> saveGoods(
            @ApiParam(name = "addParamVO", value = "addParamVO", required = true)
            @RequestBody GoodsParamVO goodsParamVO) {
        Result<GoodsVO> result = null;
        try {
            if (goodsParamVO != null){
                goodsParamVO.setShopId(ThreadLocalContext.getShopId());
            }
			GoodsParamDTO goodsParamDTO = ObjectConvertUtil.map(goodsParamVO,GoodsParamDTO.class);
            goodsParamDTO.setShopId(ThreadLocalContext.getShopId());
			GoodsDTO goodsDTO = goodsWriteFacade.saveGoods(goodsParamDTO);
			result = Result.SUCESS(goodsDTO);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("saveGoods Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    /**
     * 新增美食,不用事先生成Sku,由服务端生成，所以这个方法适合于套餐的情况
     *
     * @param goodsParamVO
     * @return Result<Long> 新增美食的ID
     * @author zhoufe
     * @date 2017年7月12日 上午10:39:11
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/saveGoodsWithoutSku")
    @ApiOperation(value = "新增美食,不用事先生成Sku,由服务端生成", notes = "新增美食,不用事先生成Sku,由服务端生成", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<GoodsVO> saveGoodsWithoutSku(
            @ApiParam(name = "addParamVO", value = "addParamVO", required = true)
            @RequestBody GoodsParamVO goodsParamVO) {
        Result<GoodsVO> result = null;
        try {
            if (goodsParamVO != null){
                goodsParamVO.setShopId(ThreadLocalContext.getShopId());
            }
            GoodsParamDTO goodsParamDTO = ObjectConvertUtil.map(goodsParamVO,GoodsParamDTO.class);
            goodsParamDTO.setShopId(ThreadLocalContext.getShopId());
            GoodsDTO goodsDTO = goodsWriteFacade.saveGoodsWithoutSku(goodsParamDTO);
            result = Result.SUCESS(goodsDTO);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("saveGoods Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    /**
     * 修改美食
     *
     * @param goodsParamVO
     * @return Result<Boolean>
     * @author zhoufe
     * @date 2017年7月14日 上午10:00:20
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/updateGoods")
    @ApiOperation(value = "修改美食", notes = "修改美食", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<GoodsVO> updateGoods(
            @ApiParam(name = "updateParamVO", value = "updateParamVO", required = true)
            @RequestBody GoodsParamVO goodsParamVO) {
        Result<GoodsVO> result = null;
        try {
            if (goodsParamVO != null){
                goodsParamVO.setShopId(ThreadLocalContext.getShopId());
            }
            GoodsParamDTO goodsParamDTO = ObjectConvertUtil.map(goodsParamVO,GoodsParamDTO.class);
            goodsParamDTO.setShopId(ThreadLocalContext.getShopId());
			GoodsDTO goodsDTO = goodsWriteFacade.updateGoods(goodsParamDTO);
			result = Result.SUCESS(goodsDTO);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("updateGoods Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }


    /**
     * 批量修改美食
     *
     * @param goodsParamVOList
     * @return Result<Boolean>
     * @author zhoufe
     * @date 2017年7月14日 上午10:00:20
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/updateGoodsList")
    @ApiOperation(value = "批量修改美食", notes = "批量修改美食", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> updateGoodsList(
            @ApiParam(name = "updateParamVOList", value = "updateParamVOList", required = true)
            @RequestBody List<GoodsParamVO> goodsParamVOList) {
        Result<Boolean> result = null;
        try {

            if (CollectionUtils.isEmpty(goodsParamVOList)){
                throw new MerchandiseException(MerchandiseErrorEnum.PARAMETERS_ERROR, "传入美食list参数为空");
            }

            for (GoodsParamVO goodsParamVO : goodsParamVOList) {
                goodsParamVO.setShopId(ThreadLocalContext.getShopId());
            }
            List<GoodsParamDTO> goodsParamDTOS = ObjectConvertUtil.mapList(goodsParamVOList, GoodsParamVO.class, GoodsParamDTO.class);
            Boolean isSucc = goodsWriteFacade.updateGoodsList(goodsParamDTOS);
            result = Result.SUCESS(isSucc);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("updateGoodsList Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    /**
     * 批量修改美食
     *
     * @param goodsParamVOList
     * @return Result<Boolean>
     * @author zhoufe
     * @date 2017年7月14日 上午10:00:20
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/updateGoodsListOnlyField")
    @ApiOperation(value = "批量修改美食：只修改指定字段", notes = "批量修改美食：只修改指定字段", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> updateGoodsListOnlyField(
            @ApiParam(name = "updateParamVOList", value = "updateParamVOList", required = true)
            @RequestBody List<GoodsParamVO> goodsParamVOList) {
        Result<Boolean> result = null;
        try {

            if (CollectionUtils.isEmpty(goodsParamVOList)){
                throw new MerchandiseException(MerchandiseErrorEnum.PARAMETERS_ERROR, "传入美食list参数为空");
            }

            for (GoodsParamVO goodsParamVO : goodsParamVOList) {
                goodsParamVO.setShopId(ThreadLocalContext.getShopId());
            }
            List<GoodsParamDTO> goodsParamDTOS = ObjectConvertUtil.mapList(goodsParamVOList, GoodsParamVO.class, GoodsParamDTO.class);
            Boolean isSucc = goodsWriteFacade.updateGoodsListOnlyField(goodsParamDTOS);
            result = Result.SUCESS(isSucc);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("updateGoodsListOnlyField Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    /**
     * 删除美食：放入回收站
     *
     * @param goodsIds
     * @return Result<Boolean>
     * @author zhoufe
     * @date 2017年7月14日 上午10:04:05
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/putIntoRecycleBin")
    @ApiOperation(value = "删除美食：放入回收站", notes = "删除美食：放入回收站", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> deleteGoods(
            @ApiParam(name = "goodsIds", value = "goodsIds", required = true)
            @RequestBody List<String> goodsIds) {
        Result<Boolean> result = null;
        try {
            Boolean isSuccess = goodsWriteFacade.putIntoRecycleBin(goodsIds);
            result = Result.SUCESS(isSuccess);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("putIntoRecycleBin Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    /**
     * 上下架美食
     *
     * @param onOffShelveParam
     * @return Result<Boolean>
     * @author zhoufe
     * @date 2017年7月14日 上午10:04:05
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/onOffShelve")
    @ApiOperation(value = "上下架美食", notes = "上下架美食", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> onOffShelve(
            @ApiParam(name = "onOffShelveParam", value = "onOffShelveParam", required = true)
            @RequestBody OnOffShelveParam onOffShelveParam) {
        Result<Boolean> result = null;
        try {
            Boolean isSuccess = false;
            if (onOffShelveParam.getShelveState() == ShelveStateEnum.ON_LINE) {
                isSuccess = goodsWriteFacade.onShelveGoods(onOffShelveParam.getGoodsIds());
            } else if (onOffShelveParam.getShelveState() == ShelveStateEnum.OFF_LINE) {
                isSuccess = goodsWriteFacade.offShelveGoods(onOffShelveParam.getGoodsIds());
            }

            result = Result.SUCESS(isSuccess);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("onOffShelve Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    /**
     * 售罄/沽清与否美食
     *
     * 沽清，把美食变为售罄状态，不改变原库存数量.
     *
     * 沽清：就是把商品的销售状态标记为售罄状态，前端可见但无法再下单。
     *
     * @param goodsStateParamVOS
     * @return Result<Boolean>
     * @author zhoufe
     * @date 2017年7月14日 上午10:04:05
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/onOutSell")
    @ApiOperation(value = "沽清美食", notes = "沽清美食", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> onOutSell(
            @ApiParam(name = "goodsStateParamVOS", value = "goodsStateParamVOS", required = true)
            @RequestBody List<GoodsStateParamVO> goodsStateParamVOS) {
        Result<Boolean> result = null;
        try {
            Boolean isSuccess = false;
            isSuccess = goodsWriteFacade.onOutSell(ObjectConvertUtil.mapList(goodsStateParamVOS,GoodsStateParamVO.class,GoodsStateDTO.class));
            result = Result.SUCESS(isSuccess);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("onOutSell Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    /**
     * 根据ID查询美食
     *
     * @param goodsIdsQueryParamVO
     * @return Result<List<GoodsVO>>
     * @author zhoufe
     * @date 2017年7月14日 上午10:08:42
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/listGoodsByIds_v2")
    @ApiOperation(value = "根据ID查询美食", notes = "根据ID查询美食", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<GoodsVO>> listGoodsByIds_v2(
            @ApiParam(name = "goodsIds", value = "goodsIds", required = true)
            @RequestBody GoodsIdsQueryParamVO goodsIdsQueryParamVO) {
        Result<List<GoodsVO>> result = null;
        try {
            List<GoodsDTO> goodsDTOs = goodsReadFacade.listGoodsByIds(goodsIdsQueryParamVO.getGoodsIds(),goodsIdsQueryParamVO.getNeedComputeSpecialDiscount());
			List<GoodsVO> goodsVOs = ObjectConvertUtil.mapList(goodsDTOs,GoodsDTO.class,GoodsVO.class);
			result = Result.SUCESS(goodsVOs);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("listGoodsByIds Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }


    /**
     * 根据ID查询美食
     *
     * @param goodsIds
     * @return Result<List<GoodsVO>>
     * @author zhoufe
     * @date 2017年7月14日 上午10:08:42
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/listGoodsByIds")
    @ApiOperation(value = "根据ID查询美食", notes = "根据ID查询美食", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<GoodsVO>> listGoodsByIds(
            @ApiParam(name = "goodsIds", value = "goodsIds", required = true)
            @RequestBody List<String> goodsIds) {
        Result<List<GoodsVO>> result = null;
        try {
            List<GoodsDTO> goodsDTOs = goodsReadFacade.listGoodsByIds(goodsIds,YesOrNoEnum.NO);
            List<GoodsVO> goodsVOs = ObjectConvertUtil.mapList(goodsDTOs,GoodsDTO.class,GoodsVO.class);
            result = Result.SUCESS(goodsVOs);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("listGoodsByIds Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    /**
     * 把美食加入分类/快捷菜单/会员价美食
     *
     * @return com.lizikj.api.vo.Result<java.lang.Boolean>
     * @params [goods2CategoryParamVO]
     * @author zhoufe
     * @date 2017/7/24 17:29
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/saveGoods2Category")
    @ApiOperation(value = "把美食加入分类/快捷菜单/会员价美食", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> saveGoods2Category(
            @ApiParam(name = "goods2CategoryParamVO", value = "goods2CategoryParamVO", required = true)
            @RequestBody Goods2CategoryParamVO goods2CategoryParamVO) {
        Result<Boolean> result = null;
        Boolean isSuccess;
        try {
            isSuccess = goodsWriteFacade.saveGoods2Category(goods2CategoryParamVO.getGoodsIds(), goods2CategoryParamVO.getCategoryIds());
            result = Result.SUCESS(isSuccess);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("saveGoods2Category Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    /**
     * 给美食加标签
     * @param goods2TagParamVO
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveGoods2Tag")
    @ApiOperation(value = "给美食加标签", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> saveGoods2Tag(
            @ApiParam(name = "goods2TagParamVO", value = "goods2TagParamVO", required = true)
            @RequestBody Goods2TagParamVO goods2TagParamVO) {
        Result<Boolean> result = null;
        Boolean isSuccess;
        try {
            if (goods2TagParamVO != null){
                isSuccess = goodsWriteFacade.addGoods2Tag(goods2TagParamVO.getGoodsIds(),goods2TagParamVO.getTagIds());
                result = Result.SUCESS(isSuccess);
            }
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("saveGoods2Category Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    /**
     * 把美食从分类/快捷菜单/会员价美食删除
     *
     * @return com.lizikj.api.vo.Result<java.lang.Boolean>
     * @params [goods4CategoryParamVO]
     * @author zhoufe
     * @date 2017/7/24 17:33
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/deleteGoods4Category")
    @ApiOperation(value = "把美食从分类/快捷菜单/会员价美食删除", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> deleteGoods4Category(
            @ApiParam(name = "goods4CategoryParamVO", value = "goods4CategoryParamVO", required = true)
            @RequestBody Goods4CategoryParamVO goods4CategoryParamVO) {
        Result<Boolean> result = null;
        Boolean isSuccess = false;
        try {
            if (goods4CategoryParamVO == null){
                throw new MerchandiseException(MerchandiseErrorEnum.PARAMETERS_ERROR,"参数不能为空");
            }
            isSuccess = goodsWriteFacade.removeGoods2Category(goods4CategoryParamVO.getGoodsIds(), goods4CategoryParamVO.getCategoryId());
            result = Result.SUCESS(isSuccess);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("deleteGoods4Category Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }


    /**
     * 删除美食的标签
     * @param goods4TagParamVO
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteGoods4Tag")
    @ApiOperation(value = "删除美食的标签", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> deleteGoods4Tag(
            @ApiParam(name = "goods4TagParamVO", value = "goods4TagParamVO", required = true)
            @RequestBody Goods4TagParamVO goods4TagParamVO) {
        Result<Boolean> result = null;
        Boolean isSuccess = false;
        try {
            isSuccess = goodsWriteFacade.removeGoodsFromTag(goods4TagParamVO.getGoodsIds(),goods4TagParamVO.getTagId());
            result = Result.SUCESS(isSuccess);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("deleteGoods4Category Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    /**
     * 根据加料ID查询该加料下有的商品
     *
     * @param snackId
     * @return Result<List<GoodsVO>>
     * @author zhoufe
     * @date 2017年7月12日 上午11:34:29
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/listGoodsBySnackId/{snackId}")
    @ApiOperation(value = "根据加料ID查询该加料下有的商品", notes = "根据加料ID查询该加料下有的商品", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<GoodsVO>> listGoodsBySnackId(
            @ApiParam(name = "snackId", value = "加料ID", required = true, type = "String")
            @PathVariable(name = "snackId") String snackId) {
        Result<List<GoodsVO>> result = null;
        try {
            List<GoodsDTO> goodsDTOs = goodsReadFacade.listGoodsByShopIdAndSnackId(ThreadLocalContext.getShopId(), snackId);
			List<GoodsVO> goodsVOs = ObjectConvertUtil.mapList(goodsDTOs,GoodsDTO.class,GoodsVO.class);
			result = Result.SUCESS(goodsVOs);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("listGoodsBySnackId Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    /**
     * 根据加料ID查询该加料下有的商品：POS
     * 根据snNum查询到shopId
     *
     * @param snackId
     * @return Result<List<GoodsVO>>
     * @author zhoufe
     * @date 2017年7月12日 上午11:34:29
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/listGoodsBySnackId4Pos/{snackId}")
    @ApiOperation(value = "根据加料ID查询该加料下有的商品：POS", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<GoodsVO>> listGoodsBySnackId4Pos(
            @RequestHeader("lz-sn") String snNum,
            @ApiParam(name = "snackId", value = "加料ID", required = true)
            @PathVariable(name = "snackId") String snackId) {
        Result<List<GoodsVO>> result = null;
        try {
            List<GoodsDTO> goodsDTOs = goodsReadFacade.listGoodsBySnNumAndSnackId4Pos(snNum, snackId);
			List<GoodsVO> goodsVOs = ObjectConvertUtil.mapList(goodsDTOs,GoodsDTO.class,GoodsVO.class);
			result = Result.SUCESS(goodsVOs);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("listGoodsBySnackId4Pos Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }


    /**
	 * 获取该店铺下的全部的商品和分类
	 *
	 * @return com.lizikj.api.vo.Result<com.lizikj.api.vo.merchandise.GoodsAndCategoryVO>
	 * @params [shopId]
	 * @author zhoufe
	 * @date 2017/7/25 20:59
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/listAllGoodsAndCategories_v2", method = RequestMethod.GET)
	@ApiOperation(value = "获取该店铺下的全部的商品和分类", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<GoodsAndCategoryVO> listAllGoodsAndCategories_v2(
			@RequestParam(required = false, defaultValue = "NO")
			@ApiParam(name = "needComputeSpecialDiscount,是否需要计算分段价格", value = "needComputeSpecialDiscount") YesOrNoEnum needComputeSpecialDiscount) {
		Result<GoodsAndCategoryVO> result = null;
		GoodsAndCategoryVO goodsAndCategoryVO = new GoodsAndCategoryVO();
		try {
			List<GoodsDTO> goodsDTOS = goodsReadFacade.findByShopId(ThreadLocalContext.getShopId(), needComputeSpecialDiscount);
			List<CategoryDTO> categoryDTOS = categoryReadFacade.listCategoriesByCategoryCode(ThreadLocalContext.getShopId(), CategoryCodeEnum.QUICK_MENU);

			if (!CollectionUtils.isEmpty(goodsDTOS)) {
				convertResult(goodsAndCategoryVO, goodsDTOS);
			}

            setQuickMenu(goodsAndCategoryVO, categoryDTOS);

            result = Result.SUCESS(goodsAndCategoryVO);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("listAllGoodsAndCategories Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }


    /**
     * 获取该店铺下的全部的商品和分类
     *
     * @return com.lizikj.api.vo.Result<com.lizikj.api.vo.merchandise.GoodsAndCategoryVO>
     * @params [shopId]
     * @author zhoufe
     * @date 2017/7/25 20:59
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/listAllGoodsAndCategories")
    @ApiOperation(value = "获取该店铺下的全部的商品和分类", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<GoodsAndCategoryVO> listAllGoodsAndCategories() {

        Result<GoodsAndCategoryVO> result = null;
        GoodsAndCategoryVO goodsAndCategoryVO = new GoodsAndCategoryVO();
        try {
            List<GoodsDTO> goodsDTOS = goodsReadFacade.findByShopId(ThreadLocalContext.getShopId(),YesOrNoEnum.NO);
            List<CategoryDTO> categoryDTOS = categoryReadFacade.listCategoriesByCategoryCode(ThreadLocalContext.getShopId(),CategoryCodeEnum.QUICK_MENU);

            if (!CollectionUtils.isEmpty(goodsDTOS)) {
                convertResult(goodsAndCategoryVO, goodsDTOS);
            }

            setQuickMenu(goodsAndCategoryVO, categoryDTOS);

            result = Result.SUCESS(goodsAndCategoryVO);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("listAllGoodsAndCategories Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    private void convertResult(GoodsAndCategoryVO goodsAndCategoryVO, List<GoodsDTO> goodsDTOS) {
        if (CollectionUtils.isEmpty(goodsDTOS)){
            return;
        }
        List<GoodsDTO> goodsDTOS1 = new ArrayList<>();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        goodsDTOS.forEach(goodsDTO -> {
            goodsDTOS1.add(goodsDTO);
            if (!CollectionUtils.isEmpty(goodsDTO.getCategories())) {
                categoryDTOS.addAll(goodsDTO.getCategories());
            }
        });

        List<CategoryDTO> distinct = categoryDTOS.stream().distinct().collect(toList());

		List<GoodsVO>  goodsVOS= ObjectConvertUtil.mapList(goodsDTOS1,GoodsDTO.class,GoodsVO.class);
		List<CategoryVO> categoryVOS = ObjectConvertUtil.mapList(distinct, CategoryDTO.class,CategoryVO.class);

		goodsAndCategoryVO.setCategoryVOs(categoryVOS);
		goodsAndCategoryVO.setGoodsVOs(goodsVOS);
    }

    /**
     * 已经废弃了
     *
     * 获取该店铺下的全部的商品和分类：pos
     * 根据POS的snNum找到shopId
     *
     * @return com.lizikj.api.vo.Result<com.lizikj.api.vo.merchandise.GoodsAndCategoryVO>
     * @params [snNum]
     * @author zhoufe
     * @date 2017/7/25 20:59
     */
    @SuppressWarnings("unchecked")
    @Deprecated
    @ResponseBody
    @RequestMapping("/listAllGoodsAndCategories4Pos")
    @ApiOperation(value = "获取该店铺下的全部的商品和分类：POS", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<GoodsAndCategoryVO> listAllGoodsAndCategories4Pos(@RequestHeader("lz-sn") String snNum) {
        Result<GoodsAndCategoryVO> result = null;
        GoodsAndCategoryVO goodsAndCategoryVO = new GoodsAndCategoryVO();
        try {
            List<GoodsDTO> goodsDTOS = goodsReadFacade.findBySnNum(snNum);
            List<CategoryDTO> categoryDTOS = categoryReadFacade.listCategoriesByCategoryCode(ThreadLocalContext.getShopId(),CategoryCodeEnum.QUICK_MENU);

            convertResult(goodsAndCategoryVO, goodsDTOS);

            setQuickMenu(goodsAndCategoryVO, categoryDTOS);

            result = Result.SUCESS(goodsAndCategoryVO);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("listAllGoodsAndCategories4Pos Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    private void setQuickMenu(GoodsAndCategoryVO goodsAndCategoryVO, List<CategoryDTO> categoryDTOS) {
        if (!CollectionUtils.isEmpty(goodsAndCategoryVO.getCategoryVOs())){
            boolean containQuickMenu = false;
            for (CategoryVO categoryVO : goodsAndCategoryVO.getCategoryVOs()) {
                if (categoryVO.getCode() == CategoryCodeEnum.QUICK_MENU){
                    containQuickMenu = true;
                }
            }

            if (!containQuickMenu && !CollectionUtils.isEmpty(categoryDTOS)){
                List<CategoryVO> categoryVOS = goodsAndCategoryVO.getCategoryVOs();
                categoryVOS.add(ObjectConvertUtil.map(categoryDTOS.get(0),CategoryVO.class));
                goodsAndCategoryVO.setCategoryVOs(categoryVOS);
            }
        }
    }


    /**
     * 根据标签ID和类型查询商品
     * 可以根据类型过滤显示已添加至此标签下的美食
     *
     * @param tagIdTypeQueryParamVO
     * @return Result<List<GoodsVO>>
     * @author zhoufe
     * @date 2017年7月12日 上午11:34:29
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/listGoodsByTagIdAndType")
    @ApiOperation(value = "根据标签ID和类型查询商品", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<GoodsVO>> listGoodsByTagIdAndType(
            @ApiParam(name = "queryParamVO", value = "queryParamVO", required = true)
            @RequestBody TagIdTypeQueryParamVO tagIdTypeQueryParamVO) {
        Result<List<GoodsVO>> result = null;
        try {
            TagIdTypeQueryParamDTO tagIdTypeQueryParamDTO = ObjectConvertUtil.copyProperties(TagIdTypeQueryParamDTO.class, tagIdTypeQueryParamVO);
            List<GoodsDTO> goodsDTOs = goodsReadFacade.listGoodsByTagIdAndType(tagIdTypeQueryParamDTO);
			List<GoodsVO> goodsVOs = ObjectConvertUtil.mapList(goodsDTOs,GoodsDTO.class,GoodsVO.class);
			result = Result.SUCESS(goodsVOs);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("listGoodsByTagIdAndType Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    /**
     * 根据规格类型查询商品：可查询无规格商品（无规格也会生成一个SKU）
     *
     * @param skuTypeQueryParamVO
     * @return Result<List<GoodsVO>>
     * @author zhoufe
     * @date 2017年7月21日 下午4:31:35
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/listGoodsBySkuType_v2")
    @ApiOperation(value = "根据规格类型查询商品", notes = "根据规格类型查询商品", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<GoodsVO>> listGoodsBySkuType_v2(
            @ApiParam(name = "Sku类型", value = "skuType", required = true)
            @RequestBody SkuTypeQueryParamVO skuTypeQueryParamVO) {
        Result<List<GoodsVO>> result = null;
        try {
            List<GoodsVO> goodsVOs = null;
            GoodsQueryDTO goodsQueryDTO = new GoodsQueryDTO();
            goodsQueryDTO.setShopId(ThreadLocalContext.getShopId());
            goodsQueryDTO.setSkuType(skuTypeQueryParamVO.getSkuType());
            goodsQueryDTO.setNeedComputeSpecialDiscount(skuTypeQueryParamVO.getNeedComputeSpecialDiscount());
            goodsQueryDTO.setRemoveStatus(RemoveStatusEnum.ACTIVE);

            List<GoodsDTO> goodsDTOS = goodsReadFacade.listGoodsByCondition(goodsQueryDTO);
			goodsVOs = ObjectConvertUtil.mapList(goodsDTOS,GoodsDTO.class,GoodsVO.class);
			result = Result.SUCESS(goodsVOs);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("listGoodsBySkuType Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    /**
     * 根据规格类型查询商品：可查询无规格商品（无规格也会生成一个SKU）
     *
     * @param skuType
     * @return Result<List<GoodsVO>>
     * @author zhoufe
     * @date 2017年7月21日 下午4:31:35
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/listGoodsBySkuType")
    @ApiOperation(value = "根据规格类型查询商品", notes = "根据规格类型查询商品", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<GoodsVO>> listGoodsBySkuType(
            @ApiParam(name = "skuType", value = "skuType", required = true)
            @RequestBody SkuTypeEnum skuType) {
        Result<List<GoodsVO>> result = null;
        try {
            List<GoodsVO> goodsVOs = null;
            GoodsQueryDTO goodsQueryDTO = new GoodsQueryDTO();
            goodsQueryDTO.setShopId(ThreadLocalContext.getShopId());
            goodsQueryDTO.setSkuType(skuType);
            goodsQueryDTO.setNeedComputeSpecialDiscount(YesOrNoEnum.NO);
            goodsQueryDTO.setRemoveStatus(RemoveStatusEnum.ACTIVE);

            List<GoodsDTO> goodsDTOS = goodsReadFacade.listGoodsByCondition(goodsQueryDTO);
            goodsVOs = ObjectConvertUtil.mapList(goodsDTOS,GoodsDTO.class,GoodsVO.class);
            result = Result.SUCESS(goodsVOs);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("listGoodsBySkuType Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    /**
     * 根据套餐类型查询商品
     *
     * 如果order不为空则按照order排序，否则按照createTime排序
     *
     * @param packageTypeQueryParamVO
     * @return Result<List<GoodsVO>>
     * @author zhoufe
     * @date 2017年7月21日 下午4:31:35
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/listGoodsByPackageType_v2")
    @ApiOperation(value = "根据套餐类型查询商品", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<GoodsVO>> listGoodsByPackageType_v2(
            @ApiParam(name = "packageTypeQueryParamVO", value = "packageTypeQueryParamVO：见PackageTypeEnum：NONE.普通美食，MASTER_SLAVE.主次搭配型套餐，COMPOSE_MORE_SELECT.组合多选型套餐，DOUBLE_FIXED.固定双拼方案，DOUBLE_ANY.任意双拼方案", required = true)
            @RequestBody PackageTypeQueryParamVO packageTypeQueryParamVO) {
        Result<List<GoodsVO>> result = null;
        try {
            List<GoodsVO> goodsVOs = null;
            GoodsQueryDTO goodsQueryDTO = new GoodsQueryDTO();
            goodsQueryDTO.setPackageType(packageTypeQueryParamVO.getPackageType());
            goodsQueryDTO.setNeedComputeSpecialDiscount(packageTypeQueryParamVO.getNeedComputeSpecialDiscount());
            goodsQueryDTO.setShopId(ThreadLocalContext.getShopId());
            goodsQueryDTO.setRemoveStatus(RemoveStatusEnum.ACTIVE);

            List<GoodsDTO> goodsDTOS = goodsReadFacade.listGoodsByCondition(goodsQueryDTO);
			goodsVOs = ObjectConvertUtil.mapList(goodsDTOS,GoodsDTO.class,GoodsVO.class);
			result = Result.SUCESS(goodsVOs);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("listGoodsByPackageType Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }


    /**
     * 根据套餐类型查询商品
     *
     * 如果order不为空则按照order排序，否则按照createTime排序
     *
     * @param packageType
     * @return Result<List<GoodsVO>>
     * @author zhoufe
     * @date 2017年7月21日 下午4:31:35
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/listGoodsByPackageType/{packageType}")
    @ApiOperation(value = "根据套餐类型查询商品", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<GoodsVO>> listGoodsByPackageType(
            @ApiParam(name = "packageType", value = "packageType：见PackageTypeEnum：NONE.普通美食，MASTER_SLAVE.主次搭配型套餐，COMPOSE_MORE_SELECT.组合多选型套餐，DOUBLE_FIXED.固定双拼方案，DOUBLE_ANY.任意双拼方案", required = true)
            @PathVariable  PackageTypeEnum packageType) {
        Result<List<GoodsVO>> result = null;
        try {
            List<GoodsVO> goodsVOs = null;
            GoodsQueryDTO goodsQueryDTO = new GoodsQueryDTO();
            goodsQueryDTO.setPackageType(packageType);
            goodsQueryDTO.setShopId(ThreadLocalContext.getShopId());
            goodsQueryDTO.setRemoveStatus(RemoveStatusEnum.ACTIVE);
            goodsQueryDTO.setNeedComputeSpecialDiscount(YesOrNoEnum.NO);

            List<GoodsDTO> goodsDTOS = goodsReadFacade.listGoodsByCondition(goodsQueryDTO);
            goodsVOs = ObjectConvertUtil.mapList(goodsDTOS,GoodsDTO.class,GoodsVO.class);
            result = Result.SUCESS(goodsVOs);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("listGoodsByPackageType Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    /**
     * 根据下架/售罄类型查询该店铺下的商品
     *
     * @return com.lizikj.api.vo.Result<java.util.List<com.lizikj.api.vo.merchandise.GoodsVO>>
     * @params [stateTypeQueryParamVO]
     * @author zhoufe
     * @date 2017/7/24 17:44
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/listGoodsByStateType_v2")
    @ApiOperation(value = "根据下架/售罄类型查询该店铺下的商品", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<GoodsVO>> listGoodsByStateType_v2(
            @ApiParam(name = "stateTypeQueryParamVO", value = "stateTypeQueryParamVO", required = true)
            @RequestBody StateTypeQueryParamVO stateTypeQueryParamVO) {
        Result<List<GoodsVO>> result = null;
        try {
            List<GoodsVO> goodsVOs = null;

            GoodsQueryDTO goodsQueryDTO = new GoodsQueryDTO();
            goodsQueryDTO.setShopId(ThreadLocalContext.getShopId());
            if (stateTypeQueryParamVO.getStateType() == StateTypeEnum.OFF_LINE) {
                goodsQueryDTO.setShelveState(ShelveStateEnum.OFF_LINE);
                goodsQueryDTO.setPackageType(PackageTypeEnum.NONE);
            }
            if (stateTypeQueryParamVO.getStateType() == StateTypeEnum.SELL_OUT) {
                goodsQueryDTO.setSellState(SellStateEnum.SELL_OUT);
            }
            if (stateTypeQueryParamVO.getStateType() == StateTypeEnum.AVAILABLE) {
                goodsQueryDTO.setSellState(SellStateEnum.AVAILABLE);
            }
            goodsQueryDTO.setRemoveStatus(RemoveStatusEnum.ACTIVE);

            List<GoodsDTO> goodsDTOS = goodsReadFacade.listGoodsByCondition(goodsQueryDTO);

			goodsVOs = ObjectConvertUtil.mapList(goodsDTOS,GoodsDTO.class,GoodsVO.class);
			result = Result.SUCESS(goodsVOs);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("listGoodsByStateType Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    /**
     * 根据下架/售罄类型查询该店铺下的商品
     *
     * @return com.lizikj.api.vo.Result<java.util.List<com.lizikj.api.vo.merchandise.GoodsVO>>
     * @params [stateTypeQueryParamVO]
     * @author zhoufe
     * @date 2017/7/24 17:44
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/listGoodsByStateType")
    @ApiOperation(value = "根据下架/售罄类型查询该店铺下的商品", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<GoodsVO>> listGoodsByStateType(
            @ApiParam(name = "stateType", value = "stateType", required = true)
            @RequestBody StateTypeEnum stateType) {
        Result<List<GoodsVO>> result = null;
        try {
            List<GoodsVO> goodsVOs = null;

            GoodsQueryDTO goodsQueryDTO = new GoodsQueryDTO();
            goodsQueryDTO.setShopId(ThreadLocalContext.getShopId());
            if (stateType == StateTypeEnum.OFF_LINE) {
                goodsQueryDTO.setShelveState(ShelveStateEnum.OFF_LINE);
                goodsQueryDTO.setPackageType(PackageTypeEnum.NONE);
            }
            if (stateType == StateTypeEnum.SELL_OUT) {
                goodsQueryDTO.setSellState(SellStateEnum.SELL_OUT);
            }
            if (stateType == StateTypeEnum.AVAILABLE) {
                goodsQueryDTO.setSellState(SellStateEnum.AVAILABLE);
            }
            goodsQueryDTO.setRemoveStatus(RemoveStatusEnum.ACTIVE);
            goodsQueryDTO.setNeedComputeSpecialDiscount(YesOrNoEnum.NO);

            List<GoodsDTO> goodsDTOS = goodsReadFacade.listGoodsByCondition(goodsQueryDTO);

            goodsVOs = ObjectConvertUtil.mapList(goodsDTOS,GoodsDTO.class,GoodsVO.class);
            result = Result.SUCESS(goodsVOs);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("listGoodsByStateType Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }


    /**
     * 直接售卖商品：
     * 直接售卖，把所有规格美食变为可售状态，不改变原库存数量（若有设置库存）。
     *
     * @return com.lizikj.api.vo.Result<java.lang.Boolean>
     * @params [goodsId]
     * @author zhoufe
     * @date 2017/7/24 17:51
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/directSellGoods")
    @ApiOperation(value = "直接售卖: 把所有规格美食变为可售状态，不改变原库存数量（若有设置库存）", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> directSellGoods(
            @ApiParam(name = "directSellGoodsParamVOS", value = "商品ID和SkuId", required = true)
            @RequestBody List<DirectSellGoodsParamVO> directSellGoodsParamVOS) {
        Result<Boolean> result = null;

        try {
            Boolean isSuccess;
            isSuccess = goodsWriteFacade.directSellGoods(ObjectConvertUtil.mapList(directSellGoodsParamVOS,DirectSellGoodsParamVO.class,DirectSellGoodsDTO.class));
            result = Result.SUCESS(isSuccess);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("directSellGoods Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    /**
     * 录入库存数量
     *
     * 录入库存：录入增加的库存数量，在原来的基本上直接加数。如果未开启【库存】开关，则没有此项。
     *
     * @return com.lizikj.api.vo.Result<java.lang.Boolean>
     * @params [goodsId, stock]
     * @author zhoufe
     * @date 2017/7/24 17:56
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/increaseGoodsStock")
    @ApiOperation(value = "录入库存数量", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> increaseGoodsStock(
            @ApiParam(name = "increaseGoodsStockParamVO", value = "录入库存vo", required = true)
            @RequestBody IncreaseGoodsStockParamVO increaseGoodsStockParamVO) {
        Result<Boolean> result = null;

        try {
            Boolean isSuccess;
            IncreaseGoodsStockDTO increaseGoodsStockDTO = ObjectConvertUtil.map(increaseGoodsStockParamVO,IncreaseGoodsStockDTO.class);

            isSuccess = goodsWriteFacade.increaseStock(Arrays.asList(increaseGoodsStockDTO),ThreadLocalContext.getShopId());
            result = Result.SUCESS(isSuccess);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("increaseGoodsStock Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    /**
     * 批量录入库存数量
     *
     * 录入库存：录入增加的库存数量，在原来的基本上直接加数。如果未开启【库存】开关，则没有此项。
     *
     * @return com.lizikj.api.vo.Result<java.lang.Boolean>
     * @params [goodsId, stock]
     * @author zhoufe
     * @date 2017/7/24 17:56
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/batchIncreaseGoodsStock")
    @ApiOperation(value = "录入库存数量", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> batchIncreaseGoodsStock(
            @ApiParam(name = "increaseGoodsStockParamVOS", value = "录入库存vo", required = true)
            @RequestBody List<IncreaseGoodsStockParamVO> increaseGoodsStockParamVOS) {
        Result<Boolean> result = null;

        try {
            Boolean isSuccess;
            isSuccess = goodsWriteFacade.increaseStock(ObjectConvertUtil.mapList(increaseGoodsStockParamVOS,IncreaseGoodsStockParamVO.class,IncreaseGoodsStockDTO.class),ThreadLocalContext.getShopId());
            result = Result.SUCESS(isSuccess);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("increaseGoodsStock Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    @ResponseBody
    @RequestMapping("/getPackageGoodsByGoodsId_v2")
    @ApiOperation(value = "查询一个美食关联的套餐", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<GoodsVO>> getPackageGoodsByGoodsId_v2(
            @ApiParam(name = "goodsIdQueryParamVO", value = "商品ID", required = true)
            @RequestBody GoodsIdQueryParamVO goodsIdQueryParamVO){
        Result<List<GoodsVO>> result;

        try {
            List<GoodsDTO> goodsDTOS = goodsReadFacade.findPackageGoodsByGoodsId(ThreadLocalContext.getShopId(),goodsIdQueryParamVO.getGoodsId(),goodsIdQueryParamVO.getNeedComputeSpecialDiscount());
            List<GoodsVO> goodsVOS = ObjectConvertUtil.mapList(goodsDTOS,GoodsDTO.class,GoodsVO.class);
            result = Result.SUCESS(goodsVOS);
        }catch (MerchandiseException e){
            logger.error("getPackageGoodsByGoodsId Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }


    @ResponseBody
    @RequestMapping("/getPackageGoodsByGoodsId/{goodsId}")
    @ApiOperation(value = "查询一个美食关联的套餐", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<GoodsVO>> getPackageGoodsByGoodsId(
            @ApiParam(name = "goodsId", value = "商品ID", required = true)
            @PathVariable String goodsId){
        Result<List<GoodsVO>> result;

        try {
            List<GoodsDTO> goodsDTOS = goodsReadFacade.findPackageGoodsByGoodsId(ThreadLocalContext.getShopId(),goodsId,YesOrNoEnum.NO);
            List<GoodsVO> goodsVOS = ObjectConvertUtil.mapList(goodsDTOS,GoodsDTO.class,GoodsVO.class);
            result = Result.SUCESS(goodsVOS);
        }catch (MerchandiseException e){
            logger.error("getPackageGoodsByGoodsId Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/updateGoodsOrder")
    @ApiOperation(value = "更新美食的排序", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> updateGoodsOrder(
            @ApiParam(name = "updateGoodsOrdersParam", value = "商品ID和顺序", required = true)
            @RequestBody UpdateGoodsOrdersParam updateGoodsOrdersParam){
        Result<Boolean> result;
        try {
            UpdateGoodsOrdersDTO updateGoodsOrdersDTO = ObjectConvertUtil.map(updateGoodsOrdersParam,UpdateGoodsOrdersDTO.class);
            updateGoodsOrdersDTO.setShopId(ThreadLocalContext.getShopId());
            boolean rst = goodsWriteFacade.updateGoodsOrder(updateGoodsOrdersDTO);
            result = Result.SUCESS(rst);
        }catch (MerchandiseException e){
            logger.error("updateGoodsOrder Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }


    /**
     * 根据销售状态 返回商品的简要的信息
     * @param sellState
     * @return
     */
    @ResponseBody
    @RequestMapping("/findSimpleGoodsBySellState")
    @ApiOperation(value = "根据销售状态 返回商品的简要的信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<SimpleGoodsVO>> findSimpleGoodsBySellState(
            @ApiParam(name = "sellState", value = "sellState：AVAILABLE, 可售, SELL_OUT, 售罄", required = true)
            @RequestBody SellStateEnum sellState){
        Result<List<SimpleGoodsVO>> result;
        try {

            List<SimpleGoodsDTO> simpleGoodsDTOS = goodsReadFacade.findSimpleGoodsBySellState(ThreadLocalContext.getShopId(),sellState);
            List<SimpleGoodsVO> simpleGoodsVOS = ObjectConvertUtil.mapList(simpleGoodsDTOS,SimpleGoodsDTO.class,SimpleGoodsVO.class);
            result = Result.SUCESS(simpleGoodsVOS);
        }catch (MerchandiseException e){
            logger.error("findSimpleGoodsBySellState Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }


    /**
     * 找到店铺中，美食回收站中的美食
     * @return
     */
    @ResponseBody
    @RequestMapping("/findRecycleBinGoods_v2")
    @ApiOperation(value = "找到店铺中，美食回收站中的美食", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<GoodsVO>> findRecycleBinGoods_v2(
            @ApiParam(name = "needComputeSpecialDiscount", value = "needComputeSpecialDiscount", required = false)
            @RequestBody YesOrNoEnum needComputeSpecialDiscount){
        Result<List<GoodsVO>> result;

        try {
            List<GoodsDTO> goodsDTOS = goodsReadFacade.findGoodsInRecycleBin(ThreadLocalContext.getShopId(),needComputeSpecialDiscount);
            result = Result.SUCESS(ObjectConvertUtil.mapList(goodsDTOS,GoodsDTO.class,GoodsVO.class));
        }catch (MerchandiseException e){
            logger.error("findRecycleBinGoods Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }


    /**
     * 找到店铺中，美食回收站中的美食
     * @return
     */
    @ResponseBody
    @RequestMapping("/findRecycleBinGoods")
    @ApiOperation(value = "找到店铺中，美食回收站中的美食", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<GoodsVO>> findRecycleBinGoods(){
        Result<List<GoodsVO>> result;

        try {
            List<GoodsDTO> goodsDTOS = goodsReadFacade.findGoodsInRecycleBin(ThreadLocalContext.getShopId(),YesOrNoEnum.NO);
            result = Result.SUCESS(ObjectConvertUtil.mapList(goodsDTOS,GoodsDTO.class,GoodsVO.class));
        }catch (MerchandiseException e){
            logger.error("findRecycleBinGoods Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }


    @ResponseBody
    @RequestMapping("/findGoodsListByCondition")
    @ApiOperation(value = "根据各种条件查询美食", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PageVO<GoodsVO>> findGoodsListByCondition(
            @ApiParam(name = "goodsQueryAllConditionVO", value = "查询参数", required = true)
            @RequestBody GoodsQueryAllConditionVO goodsQueryAllConditionVO,
            @ApiParam(name = "pageNum", value = "页码", defaultValue = "1") @RequestParam(value = "pageNum", defaultValue = "1", required = true) int pageNum,
            @ApiParam(name = "pageSize", value = "每页数量", defaultValue = "10") @RequestParam(value = "pageSize", defaultValue = "10", required = true) int pageSize){
        Result<PageVO<GoodsVO>> result;

        try {
            if (goodsQueryAllConditionVO.getShopId() == null){
                goodsQueryAllConditionVO.setShopId(ThreadLocalContext.getShopId());
            }

            PageParameter pageParameter = new PageParameter();
            pageParameter.setPageNum(pageNum);
            pageParameter.setPageSize(pageSize);
            PageDTO<GoodsDTO> pageDTO = goodsReadFacade.listGoodsByAllConditionPage(ObjectConvertUtil.map(goodsQueryAllConditionVO,GoodsQueryConditionDTO.class),pageParameter);

            PageVO<GoodsVO> pageVO;
            if (pageDTO != null){
                pageVO = new PageVO(pageDTO.getPageNum(),pageDTO.getPageSize(),pageDTO.getPages(),pageDTO.getTotal(),pageDTO.getData());
            }else {
                pageVO = null;
            }
            result = Result.SUCESS(pageVO);
        }catch (MerchandiseException e){
            logger.error("findGoodsListByCondition Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }


    @ResponseBody
    @RequestMapping("/listGoodsByCondition")
    @ApiOperation(value = "PC端，根据条件查询美食", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<PCGoodsVO>> listGoodsByCondition(
            @ApiParam(name = "goodsSimpleQueryVO", value = "查询参数", required = true)
            @RequestBody GoodsSimpleQueryVO goodsSimpleQueryVO){
        Result<List<PCGoodsVO>> result;

        try {
            if (goodsSimpleQueryVO.getShopId() == null){
                goodsSimpleQueryVO.setShopId(ThreadLocalContext.getShopId());
            }

            List<GoodsDTO> pageDTO = goodsReadFacade.listGoodsByCondition(ObjectConvertUtil.map(goodsSimpleQueryVO,PageGoodsQueryConditionDTO.class));
            List<PCGoodsVO> goodsVOS = ObjectConvertUtil.mapList(pageDTO,GoodsDTO.class,PCGoodsVO.class);

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
            logger.error("listGoodsByCondition Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/updateGoodsImages")
    @ApiOperation(value = "PC端，更新美食图片", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> updateGoodsImages(
            @ApiParam(name = "updateGoodsImagesVO", value = "参数", required = true)
            @RequestBody UpdateGoodsImagesVO updateGoodsImagesVO){
        Result<Boolean> result;

        try {
            Boolean aBoolean = goodsWriteFacade.updateGoodsImages(updateGoodsImagesVO.getGoodsId(),updateGoodsImagesVO.getMediaIds());
            result = Result.SUCESS(aBoolean);
        }catch (MerchandiseException e){
            logger.error("updateGoodsImages Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/addOrRemoveGoods2QuickMenu")
    @ApiOperation(value = "PC端，把美食加入/移出快捷菜单", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> addOrRemoveGoods2QuickMenu(
            @ApiParam(name = "goodsId", required = true)
            @RequestParam String goodsId,
            @ApiParam(name = "add", required = true)
            @RequestParam Boolean add){
        Result<Boolean> result;

        try {
            Boolean aBoolean = goodsWriteFacade.addOrRemoveGoods2QuickMenu(goodsId,add);
            result = Result.SUCESS(aBoolean);
        }catch (MerchandiseException e){
            logger.error("addGoods2QuickMenu Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }



    /**
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/batchAdd", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "PC端，批量新增美食", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<BatchResultVO> batchAdd(@RequestParam("file")MultipartFile file){
        Result<BatchResultVO> result = null;
        BatchResultVO batchResultVO = null;

        if (!file.isEmpty()) {
            String filename = file.getOriginalFilename();
            String directory = env.getProperty("merchandise.batch.path");
            String directoryPath = Paths.get(directory).toString();
            File file1 = new File(directoryPath);

            if(!file1.exists()){
                file1.mkdir();
                logger.info("batchAdd：" + file1.getAbsolutePath());
            }

            String filepath = Paths.get(directoryPath, filename).toString();

            try(BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(new File(filepath)))){
                out.write(file.getBytes());
                out.flush();
            } catch (IOException e) {
                logger.error("上传文件发生异常，异常信息为:\n{}",e);
                result = Result.FAILURE("上传文件失败");
            }

            if (result != null){
                new File(filepath).delete();
                return result;
            }

            InputStream inputXML = null;
            InputStream inputXLS = null;
            try {

                inputXML = new BufferedInputStream(getClass().getClassLoader().getResourceAsStream("xml/template/batchAddGoods.xml"));
                inputXLS = new BufferedInputStream(new FileInputStream(new File(filepath)));
                ReaderConfig.getInstance().setSkipErrors(true);
                XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);

                List<BatchAddGoodsDTO> batchAddGoodsList = new ArrayList<>();
                BatchAddGoodsHeaderDTO batchAddGoodsHeaderDTO = new BatchAddGoodsHeaderDTO();
                Map beans = new HashMap();
                beans.put("header",batchAddGoodsHeaderDTO);
                beans.put("batchAddGoodsList", batchAddGoodsList);

                long startTime = System.currentTimeMillis();
                XLSReadStatus readStatus = mainReader.read(inputXLS,beans);

                logger.warn("解析文件耗时：{}",System.currentTimeMillis() - startTime);

                if (validateHeader(batchResultVO, batchAddGoodsHeaderDTO)) return Result.SUCESS(batchResultVO);

                BatchResultDTO batchResultDTO = goodsWriteFacade.batchAddGoods(batchAddGoodsList,ThreadLocalContext.getShopId());
                batchResultVO = ObjectConvertUtil.map(batchResultDTO,BatchResultVO.class);
                result = Result.SUCESS(batchResultVO);
            }catch (SAXException e) {
                logger.error("解析文件发生异常，异常信息为:\n{}",e);
                result = Result.FAILURE("解析文件失败");
            } catch (InvalidFormatException e) {
                logger.error("解析文件发生异常，异常信息为:\n{}",e);
                result = Result.FAILURE("解析文件失败");
            }catch (Exception e){
                logger.error("处理文件发生异常，异常信息为:\n{}",e);
                result = Result.FAILURE("处理文件失败");
            }finally {
                new File(filepath).delete();

                if(inputXLS != null){
                    try {
                        inputXLS.close();
                    } catch (IOException e) {
                        logger.error("关闭文件流发生异常，异常信息为:\n{}",e);
                    }
                }

                if(inputXML != null){
                    try {
                        inputXML.close();
                    } catch (IOException e) {
                        logger.error("关闭文件流发生异常，异常信息为:\n{}",e);
                    }
                }
            }
        } else {
            result = Result.FAILURE("上传的文件为空");
        }

        return result;
    }

    private boolean validateHeader(BatchResultVO batchResultVO, BatchAddGoodsHeaderDTO batchAddGoodsHeaderDTO) {
        if (batchAddGoodsHeaderDTO == null){
            batchResultVO.setValid(false);
            return true;
        }

        String aliasHeader = batchAddGoodsHeaderDTO.getAlias();
        String calMethodHeader = batchAddGoodsHeaderDTO.getCalMethod();
        String categoryNameHeader = batchAddGoodsHeaderDTO.getCategoryName();
        String goodsNameHeader = batchAddGoodsHeaderDTO.getGoodsName();
        String goodsNumberHeader = batchAddGoodsHeaderDTO.getGoodsNumber();
        String onOffShelveHeader = batchAddGoodsHeaderDTO.getOnOffShelve();
        String sellPriceHeader = batchAddGoodsHeaderDTO.getSellPrice();
        String unitHeader = batchAddGoodsHeaderDTO.getUnit();

        boolean isValid = false;

        if (StringUtils.isNotBlank(aliasHeader)
                && StringUtils.isNotBlank(calMethodHeader)
                && StringUtils.isNotBlank(categoryNameHeader)
                && StringUtils.isNotBlank(goodsNameHeader)
                && StringUtils.isNotBlank(goodsNumberHeader)
                && StringUtils.isNotBlank(onOffShelveHeader)
                && StringUtils.isNotBlank(sellPriceHeader)
                && StringUtils.isNotBlank(unitHeader)){

            if (categoryNameHeader.contains("美食分类")
                    && goodsNameHeader.contains("美食名称")
                    && goodsNumberHeader.contains("美食编号")
                    && aliasHeader.contains("美食别名")
                    && calMethodHeader.contains("计价方式")
                    && unitHeader.contains("单位")
                    && sellPriceHeader.contains("售价")
                    && onOffShelveHeader.contains("上架/下架")){
                isValid = true;
            }
        }

        if (!isValid){
            batchResultVO.setValid(false);
            return true;
        }
        return false;
    }

    /**
     * 把美食从原有分类中删除，移入新的分类
     *
     * @return com.lizikj.api.vo.Result<java.lang.Boolean>
     * @params [goods2CategoryParamVO]
     * @author zhoufe
     * @date 2017/7/24 17:29
     */
    @ResponseBody
    @RequestMapping("/moveGoods2Category")
    @ApiOperation(value = "把美食从原有分类中删除，移入新的分类", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> moveGoods2Category(
            @ApiParam(name = "goods2CategoryParamVO", value = "goods2CategoryParamVO", required = true)
            @RequestBody Goods2CategoryParamVO goods2CategoryParamVO) {
        Result<Boolean> result = null;
        Boolean isSuccess;
        try {
            isSuccess = goodsWriteFacade.moveGoods2Category(goods2CategoryParamVO.getGoodsIds(), goods2CategoryParamVO.getCategoryIds());
            result = Result.SUCESS(isSuccess);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("moveGoods2Category Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }


    @ResponseBody
    @RequestMapping("/findCategoryAndSimpleGoods4PC")
    @ApiOperation(value = "PC端，找到店铺下所有的分类和对应的美食", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<CategoryAndGoods4PCVO>> findCategoryAndSimpleGoods4PC(
            @ApiParam(name = "needComputeSpecialDiscount", value = "needComputeSpecialDiscount", required = false)
            @RequestBody YesOrNoEnum needComputeSpecialDiscount) {
        Result<List<CategoryAndGoods4PCVO>> result;
        try {
            List<CategoryAndGoodsDTO> categoryAndGoodsDTOS = goodsReadFacade.findCategoryAndGoodsByShopId(ThreadLocalContext.getShopId(),needComputeSpecialDiscount);

            List<CategoryAndGoods4PCVO> categoryAndGoods4PCVOS = new ArrayList<>();

            if (!CollectionUtils.isEmpty(categoryAndGoodsDTOS)) {
                categoryAndGoods4PCVOS = ObjectConvertUtil.mapList(categoryAndGoodsDTOS,CategoryAndGoodsDTO.class,CategoryAndGoods4PCVO.class);
            }

            result = Result.SUCESS(categoryAndGoods4PCVOS);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("moveGoods2Category Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }


    @ResponseBody
    @RequestMapping("/addAdjustPricePlan")
    @ApiOperation(value = "新增美食调价", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Long> addAdjustPricePlan(
            @ApiParam(name = "adjustPriceVO", value = "adjustPriceVO", required = true)
            @RequestBody AdjustPriceVO adjustPriceVO) {
        Result<Long> result;
        try {
            Long rst = goodsWriteFacade.addAdjustPrice(ObjectConvertUtil.map(adjustPriceVO,AdjustPriceDTO.class));
            result = Result.SUCESS(rst);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("addAdjustPricePlan Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    @ResponseBody
    @RequestMapping("/updateAdjustPricePlan")
    @ApiOperation(value = "编辑美食调价", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> updateAdjustPricePlan(
            @ApiParam(name = "adjustPriceVO", value = "adjustPriceVO", required = true)
            @RequestBody AdjustPriceVO adjustPriceVO) {
        Result<Boolean> result;
        try {
            Boolean rst = goodsWriteFacade.updateAdjustPricePlan(ObjectConvertUtil.map(adjustPriceVO,AdjustPriceDTO.class),ThreadLocalContext.getShopId());
            result = Result.SUCESS(rst);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("updateAdjustPricePlan Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }


    @ResponseBody
    @RequestMapping("/deleteAdjustPricePlan/{planId}")
    @ApiOperation(value = "删除美食调价", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> deleteAdjustPricePlan(
            @ApiParam(name = "planId", value = "planId", required = true)
            @PathVariable Long planId) {
        Result<Boolean> result;
        try {
            Boolean rst = goodsWriteFacade.deleteAdjustPricePlan(ThreadLocalContext.getShopId(),planId);
            result = Result.SUCESS(rst);
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("deleteAdjustPricePlan Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }


    @ResponseBody
    @RequestMapping("/listAdjustPricePlan")
    @ApiOperation(value = "查找店铺下的 美食调价方案", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<AdjustPriceVO>> listAdjustPricePlan() {
        Result<List<AdjustPriceVO>> result;
        try {
            List<AdjustPriceDTO> rst = goodsReadFacade.listAdjustPricePlan(ThreadLocalContext.getShopId());
            result = Result.SUCESS(ObjectConvertUtil.mapList(rst,AdjustPriceDTO.class,AdjustPriceVO.class));
        } catch (MerchandiseException | ArgumentCheckException e) {
            logger.error("listAdjustPricePlan Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

}
