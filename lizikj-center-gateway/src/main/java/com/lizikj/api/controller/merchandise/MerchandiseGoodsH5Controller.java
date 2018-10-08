package com.lizikj.api.controller.merchandise;

import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.merchandise.CategoryAndGoodsVO;
import com.lizikj.api.vo.merchandise.CategoryVO;
import com.lizikj.api.vo.merchandise.GoodsVO;
import com.lizikj.api.vo.merchandise.H5GoodsVO;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.marketing.api.enums.ActivityTemplateEnum;
import com.lizikj.member.enums.MemberDiscountTypeEnum;
import com.lizikj.member.facade.IMemberSettingFacade;
import com.lizikj.merchandise.dto.CategoryAndGoodsDTO;
import com.lizikj.merchandise.dto.CategoryDTO;
import com.lizikj.merchandise.dto.GoodsDTO;
import com.lizikj.merchandise.dto.GoodsQueryDTO;
import com.lizikj.merchandise.enums.*;
import com.lizikj.merchandise.exception.MerchandiseException;
import com.lizikj.merchandise.facade.ICategoryReadFacade;
import com.lizikj.merchandise.facade.IGoodsReadFacade;
import com.lizikj.reporting.dto.MerchandiseReportDTO;
import com.lizikj.reporting.facade.IMerchandiseReportFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dyh
 * @created at 2017 09 01 9:55
 */
@RestController
@RequestMapping("/merchandise/h5goods")
@Api(value = "merchandise-goods-h5", tags = "门店商品-美食API-HTML5接口")
public class MerchandiseGoodsH5Controller {
    private static final Logger logger = LoggerFactory.getLogger(MerchandiseGoodsController.class);

    @Autowired
    private IGoodsReadFacade goodsReadFacade;

    @Autowired
    private ICategoryReadFacade categoryReadFacade;

    @Autowired
    private IMerchandiseReportFacade merchandiseReportFacade;

    @Autowired
    private IMemberSettingFacade memberSettingFacade;

    @RequestMapping("/listAllCategories")
    @ApiOperation(value = "根据店铺ID查询该店铺下所有的分类", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<CategoryVO>> listAllCategories() {
        Result<List<CategoryVO>> result;

        try {
            List<CategoryDTO> categoryDTOS = categoryReadFacade.listCategories(ThreadLocalContext.getShopId());

            if (!CollectionUtils.isEmpty(categoryDTOS)) {
                Iterator<CategoryDTO> it = categoryDTOS.iterator();
                while (it.hasNext()) {
                    CategoryDTO categoryDTO = it.next();
                    if (CollectionUtils.isEmpty(categoryDTO.getGoodsIds()) || categoryDTO.getCode() == CategoryCodeEnum.QUICK_MENU) {
                        it.remove();
                    }
                }
            }

            if (!CollectionUtils.isEmpty(categoryDTOS)) {
                Iterator<CategoryDTO> it = categoryDTOS.iterator();
                while (it.hasNext()) {
                    CategoryDTO categoryDTO = it.next();
                    List<String> goodsIds = categoryDTO.getGoodsIds();

                    List<GoodsDTO> goodsDTOS = goodsReadFacade.listGoodsByIds(goodsIds, YesOrNoEnum.YES);


                    if (!CollectionUtils.isEmpty(goodsDTOS)) {
                        boolean allOffLine = true;
                        for (GoodsDTO goodsDTO : goodsDTOS) {
                            allOffLine = allOffLine && (goodsDTO.getShelveState() == ShelveStateEnum.OFF_LINE);
                        }
                        if (allOffLine) {
                            it.remove();
                        }
                    }
                }
            }

            List<CategoryVO> categoryVOS = ObjectConvertUtil.mapList(categoryDTOS, CategoryDTO.class, CategoryVO.class);

            result = Result.SUCESS(categoryVOS);
        } catch (MerchandiseException e) {
            logger.error("listAllCategories Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    @RequestMapping("/listGoodsByCategory/{categroyId}")
    @ApiOperation(value = "根据分类ID查询该分类下所有的商品", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<H5GoodsVO>> listGoodsByCategory(
            @ApiParam(name = "categroyId", value = "分类ID", required = true)
            @PathVariable String categroyId) {
        Result<List<H5GoodsVO>> result;

        try {
            List<GoodsDTO> goodsDTOS = goodsReadFacade.findByShopIdAndCategoryId(ThreadLocalContext.getShopId(), categroyId, YesOrNoEnum.YES);
            List<H5GoodsVO> h5GoodsVOS = ObjectConvertUtil.mapList(goodsDTOS, GoodsDTO.class, H5GoodsVO.class);

            setLast30DaysSellVolume(h5GoodsVOS);

            boolean memberFavorable = getMemberFavorable();

            if (!CollectionUtils.isEmpty(h5GoodsVOS)) {
                h5GoodsVOS = h5GoodsVOS.stream().filter(h5GoodsVO -> h5GoodsVO.getShelveState() == ShelveStateEnum.ON_LINE).collect(Collectors.toList());

                h5GoodsVOS.forEach(h5GoodsVO -> h5GoodsVO.setMemberFavorable(memberFavorable));
            }

            result = Result.SUCESS(h5GoodsVOS);
        } catch (MerchandiseException e) {
            logger.error("listGoodsByCategory Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    private boolean getMemberFavorable() {
        boolean memberFavorable = false;
        Long merchantId = ThreadLocalContext.getMerchantId();
        if (merchantId != null && merchantId != 0) {
            MemberDiscountTypeEnum memberDiscountTypeEnum = memberSettingFacade.findDiscountTypeByMerchant(merchantId);

            if (memberDiscountTypeEnum != null) {
                memberFavorable = (memberDiscountTypeEnum == MemberDiscountTypeEnum.MEMBER_PRICE_BENEFIT);
            }
        }
        return memberFavorable;
    }

    private void setLast30DaysSellVolume(List<H5GoodsVO> h5GoodsVOS) {
        if (CollectionUtils.isEmpty(h5GoodsVOS)) {
            return;
        }

        Date now = new Date();
        Date lastMonth = DateUtils.addDays(now, -30);
        for (H5GoodsVO h5GoodsVO : h5GoodsVOS) {
            MerchandiseReportDTO merchandiseReportDTO = merchandiseReportFacade.getMerchandiseStatistic(h5GoodsVO.getId(), now, lastMonth);
            if (merchandiseReportDTO != null) {
                if (h5GoodsVO.getCalcPriceMethod() != null && h5GoodsVO.getCalcPriceMethod().getCalcMethod() == CalcMethodEnum.NUMBER) {
                    h5GoodsVO.setLast30DaysSellVolume(merchandiseReportDTO.getSaleQuantity());
                } else if (h5GoodsVO.getCalcPriceMethod() != null && h5GoodsVO.getCalcPriceMethod().getCalcMethod() == CalcMethodEnum.WEIGHT) {
                    h5GoodsVO.setLast30DaysSellVolume(merchandiseReportDTO.getSaleNum());
                }
            }
        }
    }

    @RequestMapping("/getGoodsById/{goodsId}")
    @ApiOperation(value = "根据美食id查询美食详情", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<GoodsVO> getGoodsById(
            @ApiParam(name = "goodsId", value = "美食ID", required = true)
            @PathVariable String goodsId) {
        Result<GoodsVO> result;
        try {
            GoodsDTO goodsDTO = goodsReadFacade.getGoods(goodsId, YesOrNoEnum.YES);
            GoodsVO goodsVO = ObjectConvertUtil.map(goodsDTO, GoodsVO.class);
            result = Result.SUCESS(goodsVO);
        } catch (MerchandiseException e) {
            logger.error("getGoodsById Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    @RequestMapping("/listAllCategoriesAndGoods")
    @ApiOperation(value = "所有的分类的美食", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<CategoryAndGoodsVO>> listAllCategoriesAndGoods() {
        Result<List<CategoryAndGoodsVO>> result;
        try {
            List<CategoryAndGoodsDTO> categoryAndGoodsDTOS = goodsReadFacade.findCategoryAndGoodsByShopId(ThreadLocalContext.getShopId(), YesOrNoEnum.YES);
            if (!CollectionUtils.isEmpty(categoryAndGoodsDTOS)) {
                Iterator<CategoryAndGoodsDTO> it = categoryAndGoodsDTOS.iterator();
                while (it.hasNext()) {
                    CategoryAndGoodsDTO categoryAndGoodsDTO = it.next();

                    if (!CollectionUtils.isEmpty(categoryAndGoodsDTO.getGoodsList())) {
                        List<GoodsDTO> goodsDTOS = categoryAndGoodsDTO.getGoodsList();
                        goodsDTOS = goodsDTOS.stream().filter(goodsDTO -> goodsDTO.getShelveState() == ShelveStateEnum.ON_LINE).collect(Collectors.toList());
                        categoryAndGoodsDTO.setGoodsList(goodsDTOS);
                    }

                    if (CollectionUtils.isEmpty(categoryAndGoodsDTO.getGoodsList()) || categoryAndGoodsDTO.getCode() == CategoryCodeEnum.QUICK_MENU) {
                        it.remove();
                    }
                }
            }
            List<CategoryAndGoodsVO> categoryAndGoodsVOS = ObjectConvertUtil.mapList(categoryAndGoodsDTOS, CategoryAndGoodsDTO.class, CategoryAndGoodsVO.class);

            if (!CollectionUtils.isEmpty(categoryAndGoodsVOS)) {
                boolean memberFavorable = getMemberFavorable();

                for (CategoryAndGoodsVO categoryAndGoodsVO : categoryAndGoodsVOS) {
                    List<H5GoodsVO> h5GoodsVOS = categoryAndGoodsVO.getGoodsList();
                    if (!CollectionUtils.isEmpty(h5GoodsVOS)) {

                        setLast30DaysSellVolume(h5GoodsVOS);
                        h5GoodsVOS.forEach(h5GoodsVO -> h5GoodsVO.setMemberFavorable(memberFavorable));
                        categoryAndGoodsVO.setGoodsList(h5GoodsVOS);
                    }
                }
            }
            result = Result.SUCESS(categoryAndGoodsVOS);
        } catch (MerchandiseException e) {
            logger.error("listAllCategoriesAndGoods Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }


    /**
     * @param packageType
     * @return
     */
    @RequestMapping("/listGoodsByPackageType/{packageType}")
    @ApiOperation(value = "套餐类型查询套餐美食", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<H5GoodsVO>> listGoodsByPackageType(
            @ApiParam(name = "packageType", value = "套餐类型：-1, 所有套餐，不包括普通美食," +
                    "套餐类型：0, 普通美食," +
                    "1,主次搭配型套餐" +
                    "2,组合多选型套餐" +
                    "3,固定双拼方案" +
                    "4,任意双拼方案", required = true)
            @PathVariable Byte packageType) {
        Result<List<H5GoodsVO>> result;

        try {

            PackageTypeEnum packageTypeEnum = PackageTypeEnum.getEnum(packageType);
            GoodsQueryDTO goodsQueryDTO = new GoodsQueryDTO();
            goodsQueryDTO.setShopId(ThreadLocalContext.getShopId());
            goodsQueryDTO.setPackageType(packageTypeEnum);
            goodsQueryDTO.setRemoveStatus(RemoveStatusEnum.ACTIVE);
            goodsQueryDTO.setNeedComputeSpecialDiscount(YesOrNoEnum.YES);
            List<GoodsDTO> goodsDTOS = goodsReadFacade.listGoodsByCondition(goodsQueryDTO);
            List<H5GoodsVO> h5GoodsVOS = ObjectConvertUtil.mapList(goodsDTOS, GoodsDTO.class, H5GoodsVO.class);

            if (!CollectionUtils.isEmpty(h5GoodsVOS)) {
                h5GoodsVOS = h5GoodsVOS.stream().filter(h5GoodsVO -> h5GoodsVO.getShelveState() == ShelveStateEnum.ON_LINE).collect(Collectors.toList());
            }

            setLast30DaysSellVolume(h5GoodsVOS);

            result = Result.SUCESS(h5GoodsVOS);
        } catch (MerchandiseException e) {
            logger.error("listGoodsByPackageType Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    @RequestMapping("/listBargainGoods")
    @ApiOperation(value = "查询店铺下的砍价商品", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<H5GoodsVO>> listBargainGoods() {
        Result<List<H5GoodsVO>> result;

        try {
            List<GoodsDTO> goodsDTOS = goodsReadFacade.findByShopIdAndTemplateId(ThreadLocalContext.getShopId(), ActivityTemplateEnum.CUT_PRICE.getValue(), YesOrNoEnum.YES);
            List<H5GoodsVO> h5GoodsVOS = ObjectConvertUtil.mapList(goodsDTOS, GoodsDTO.class, H5GoodsVO.class);

            setLast30DaysSellVolume(h5GoodsVOS);

            boolean memberFavorable = getMemberFavorable();
            if (!CollectionUtils.isEmpty(h5GoodsVOS)) {
                h5GoodsVOS = h5GoodsVOS.stream().filter(h5GoodsVO -> h5GoodsVO.getShelveState() == ShelveStateEnum.ON_LINE).collect(Collectors.toList());
                h5GoodsVOS.forEach(h5GoodsVO -> h5GoodsVO.setMemberFavorable(memberFavorable));
            }

            result = Result.SUCESS(h5GoodsVOS);
        } catch (MerchandiseException e) {
            logger.error("listBargainGoods Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    @RequestMapping("/listAll")
    @ApiOperation(value = "查询店铺下所有美食，包括：砍价，套餐，平台美食等，不包括热销", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<CategoryAndGoodsVO>> listAll() {

        Result<List<CategoryAndGoodsVO>> result;
        try {
            //普通美食处理
            List<CategoryAndGoodsDTO> categoryAndGoodsDTOS = goodsReadFacade.findCategoryAndGoodsByShopId(ThreadLocalContext.getShopId(), YesOrNoEnum.YES);
            if (!CollectionUtils.isEmpty(categoryAndGoodsDTOS)) {
                Iterator<CategoryAndGoodsDTO> it = categoryAndGoodsDTOS.iterator();
                while (it.hasNext()) {
                    CategoryAndGoodsDTO categoryAndGoodsDTO = it.next();

                    if (!CollectionUtils.isEmpty(categoryAndGoodsDTO.getGoodsList())) {
                        List<GoodsDTO> goodsDTOS = categoryAndGoodsDTO.getGoodsList();
                        goodsDTOS = goodsDTOS.stream().filter(goodsDTO -> goodsDTO.getShelveState() == ShelveStateEnum.ON_LINE).collect(Collectors.toList());
                        categoryAndGoodsDTO.setGoodsList(goodsDTOS);
                    }

                    if (CollectionUtils.isEmpty(categoryAndGoodsDTO.getGoodsList()) || categoryAndGoodsDTO.getCode() == CategoryCodeEnum.QUICK_MENU) {
                        it.remove();
                    }
                }
            }
            List<CategoryAndGoodsVO> categoryAndGoodsVOS = ObjectConvertUtil.mapList(categoryAndGoodsDTOS, CategoryAndGoodsDTO.class, CategoryAndGoodsVO.class);

            if (!CollectionUtils.isEmpty(categoryAndGoodsVOS)) {
                boolean memberFavorable = getMemberFavorable();

                for (CategoryAndGoodsVO categoryAndGoodsVO : categoryAndGoodsVOS) {
                    List<H5GoodsVO> h5GoodsVOS = categoryAndGoodsVO.getGoodsList();
                    if (!CollectionUtils.isEmpty(h5GoodsVOS)) {

                        setLast30DaysSellVolume(h5GoodsVOS);
                        h5GoodsVOS.forEach(h5GoodsVO -> h5GoodsVO.setMemberFavorable(memberFavorable));
                        categoryAndGoodsVO.setGoodsList(h5GoodsVOS);
                    }
                }
            }

            Long shopId = ThreadLocalContext.getShopId();


            //套餐处理
            for (byte i = 1; i < 4; i++) {
                CategoryAndGoodsVO cagVo = new CategoryAndGoodsVO();
                PackageTypeEnum packageTypeEnum = PackageTypeEnum.getEnum(i);
                cagVo.setName(packageTypeEnum.getDescription());
                cagVo.setShopId(shopId);
                cagVo.setOrder((int) i);
                cagVo.setId(String.valueOf(i));
                GoodsQueryDTO goodsQueryDTO = new GoodsQueryDTO();
                goodsQueryDTO.setShopId(shopId);
                goodsQueryDTO.setPackageType(packageTypeEnum);
                goodsQueryDTO.setRemoveStatus(RemoveStatusEnum.ACTIVE);
                goodsQueryDTO.setNeedComputeSpecialDiscount(YesOrNoEnum.YES);
                List<GoodsDTO> goodsDTOS = goodsReadFacade.listGoodsByCondition(goodsQueryDTO);
                List<H5GoodsVO> h5GoodsVOS = ObjectConvertUtil.mapList(goodsDTOS, GoodsDTO.class, H5GoodsVO.class);
                if (!CollectionUtils.isEmpty(h5GoodsVOS)) {
                    h5GoodsVOS = h5GoodsVOS.stream().filter(h5GoodsVO -> h5GoodsVO.getShelveState() == ShelveStateEnum.ON_LINE).collect(Collectors.toList());
                }
                setLast30DaysSellVolume(h5GoodsVOS);
                cagVo.setGoodsList(h5GoodsVOS);
                //添加到数组中
                categoryAndGoodsVOS.add(cagVo);
            }

            CategoryAndGoodsVO cagVo = new CategoryAndGoodsVO();
            cagVo.setName("砍价美食");
            cagVo.setOrder(5);
            cagVo.setId("5");
            cagVo.setShopId(shopId);

            //砍价美食
            List<GoodsDTO> goodsDTOS = goodsReadFacade.findByShopIdAndTemplateId(ThreadLocalContext.getShopId(), ActivityTemplateEnum.CUT_PRICE.getValue(), YesOrNoEnum.YES);
            List<H5GoodsVO> h5GoodsVOS = ObjectConvertUtil.mapList(goodsDTOS, GoodsDTO.class, H5GoodsVO.class);
            setLast30DaysSellVolume(h5GoodsVOS);
            boolean memberFavorable = getMemberFavorable();
            if (!CollectionUtils.isEmpty(h5GoodsVOS)) {
                h5GoodsVOS = h5GoodsVOS.stream().filter(h5GoodsVO -> h5GoodsVO.getShelveState() == ShelveStateEnum.ON_LINE).collect(Collectors.toList());
                h5GoodsVOS.forEach(h5GoodsVO -> h5GoodsVO.setMemberFavorable(memberFavorable));
            }
            cagVo.setGoodsList(h5GoodsVOS);


            categoryAndGoodsVOS.add(cagVo);

            result = Result.SUCESS(categoryAndGoodsVOS);
            logger.info("获取所有店铺美食结果:{}", result);
            return result;
        } catch (MerchandiseException e) {
            logger.error("listAllCategoriesAndGoods Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
            return result;
        }
    }
}