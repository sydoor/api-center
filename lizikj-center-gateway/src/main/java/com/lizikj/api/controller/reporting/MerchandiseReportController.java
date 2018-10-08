package com.lizikj.api.controller.reporting;

import static java.util.stream.Collectors.toList;

import java.util.*;

import com.github.pagehelper.PageInfo;
import com.lizikj.api.vo.PageVO;
import com.lizikj.api.vo.reporting.param.OrderItemQueryVO;
import com.lizikj.common.util.PageParameter;
import com.lizikj.marketing.api.enums.SupportCouponTimeTypeEnum;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lizikj.api.utils.ReportUtil;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.member.param.DateParam;
import com.lizikj.api.vo.reporting.MerchandiseCategoryReportVO;
import com.lizikj.api.vo.reporting.MerchandiseReportVO;
import com.lizikj.api.vo.reporting.TrendsChartVO;
import com.lizikj.api.vo.reporting.param.MerchandiseReportQueryParam;
import com.lizikj.api.vo.reporting.param.ReportCommonParam;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.merchandise.dto.GoodsDTO;
import com.lizikj.merchandise.dto.GoodsQueryDTO;
import com.lizikj.merchandise.enums.PackageTypeEnum;
import com.lizikj.merchandise.enums.RemoveStatusEnum;
import com.lizikj.merchandise.enums.YesOrNoEnum;
import com.lizikj.merchandise.facade.IGoodsReadFacade;
import com.lizikj.merchant.dto.ShopDTO;
import com.lizikj.merchant.facade.IShopReadFacade;
import com.lizikj.reporting.dto.MerchandiseCategoryReportDTO;
import com.lizikj.reporting.dto.MerchandiseReportDTO;
import com.lizikj.reporting.enums.ReportingErrorEnum;
import com.lizikj.reporting.enums.ReportingTypeEnum;
import com.lizikj.reporting.facade.IMerchandiseReportFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * Created by liangxiaolin on 2017/8/9.
 */
@RestController
@RequestMapping(value = "/reporting/merchandise")
@Api(value = "report_merchandise",tags = "美食统计接口")
public class MerchandiseReportController {
    private final static Logger log = LoggerFactory.getLogger(MerchandiseReportController.class);
    @Autowired
    IMerchandiseReportFacade merchandiseReportFacade;
    @Autowired
    IGoodsReadFacade goodsFacade;
    @Autowired
    IShopReadFacade shopReadFacade;

    @RequestMapping(value = "/queryRealTimeMerchandiseSale",method = RequestMethod.GET)
    @ApiOperation(value = "获取美食分类统计",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<PageVO<MerchandiseReportVO>> queryRealTimeMerchandiseSale(@ApiParam(name = "startTime",value = "开始时间毫秒")@RequestParam(value = "startTime")Date startTime,
                                                                      @ApiParam(name = "endTime",value = "结束时间毫秒")@RequestParam(value = "endTime")Date endTime,
                                                                      @ApiParam(name = "idleTimeStatus",value = "是否闲时")@RequestParam(value = "idleTimeStatus" ,required = false)SupportCouponTimeTypeEnum idleTimeStatus,
                                                                      @ApiParam(value = "页码", name = "pageNum", defaultValue = "1") @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
                                                                      @ApiParam(value = "每页数量", name = "pageSize", defaultValue = "25") @RequestParam(value = "pageSize",defaultValue = "25",required = false) int pageSize){
        Long merchantId = ThreadLocalContext.getMerchantId();
        Long shopId = ThreadLocalContext.getShopId();
        PageParameter pageParameter = new PageParameter(pageNum,pageSize);
        PageInfo<MerchandiseReportDTO> pageInfo = merchandiseReportFacade.queryMerchandiseGroupByGoodsAndIdle(merchantId, shopId, startTime, endTime, idleTimeStatus, pageParameter);
        PageVO pageVO = new PageVO(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getPages(),pageInfo.getTotal());
        pageVO.setList(filterAndGetMerchandiseReport(pageInfo.getList().size(),null,pageInfo.getList()));

        return Result.SUCESS(pageVO);
    }

    @RequestMapping(value = "/v2/queryRealTimeMerchandiseSale",method = RequestMethod.POST)
    @ApiOperation(value = "获取美食分类统计",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<PageVO<MerchandiseReportVO>> queryRealTimeMerchandiseSale_V2(@ApiParam(value = "页码", name = "pageNum", defaultValue = "1") @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
                                                                            @ApiParam(value = "每页数量", name = "pageSize", defaultValue = "25") @RequestParam(value = "pageSize",defaultValue = "25",required = false) int pageSize,
                                                                               @RequestBody OrderItemQueryVO queryVO){
        if(queryVO == null){
            queryVO = new OrderItemQueryVO();
        }
        queryVO.setMerchantId(ThreadLocalContext.getMerchantId());
        queryVO.setShopId(ThreadLocalContext.getShopId());

        PageParameter pageParameter = new PageParameter(pageNum,pageSize);
        PageInfo<MerchandiseReportDTO> pageInfo = merchandiseReportFacade.queryMerchandiseGroupByGoodsAndIdle(queryVO.convertToDTO(), pageParameter);
        PageVO pageVO = new PageVO(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getPages(),pageInfo.getTotal());
        pageVO.setList(filterAndGetMerchandiseReport(pageInfo.getList().size(),null,pageInfo.getList()));

        return Result.SUCESS(pageVO);
    }

    @RequestMapping(value = "/queryRealTimeMerchandiseTopNSale",method = RequestMethod.GET)
    @ApiOperation(value = "获取美食统计TopN",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<List<MerchandiseReportVO>> queryMerchandiseTopNSale(@ApiParam(name = "topN",value = "topN")@RequestParam(value = "topN",defaultValue = "10")Integer topN,
                                                                      @ApiParam(name = "startTime",value = "开始时间毫秒")@RequestParam(value = "startTime")Date startTime,
                                                                      @ApiParam(name = "endTime",value = "结束时间毫秒")@RequestParam(value = "endTime")Date endTime,
                                                                      @ApiParam(name = "idleTimeStatus",value = "是否闲时")@RequestParam(value = "idleTimeStatus" ,required = false)SupportCouponTimeTypeEnum idleTimeStatus){
        Long merchantId = ThreadLocalContext.getMerchantId();
        Long shopId = ThreadLocalContext.getShopId();

        List<MerchandiseReportDTO> dtos = this.merchandiseReportFacade.queryRealTimeMerchandiseSaleTopN(topN,merchantId,shopId,startTime,endTime,idleTimeStatus);

        return Result.SUCESS(this.filterAndGetMerchandiseReport(topN,null,dtos));
    }

    @RequestMapping(value = "/v2/queryRealTimeMerchandiseTopNSale",method = RequestMethod.POST)
    @ApiOperation(value = "获取美食统计TopN",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<List<MerchandiseReportVO>> queryMerchandiseTopNSale_V2(@RequestBody OrderItemQueryVO queryVO){
        if(queryVO == null){
            queryVO = new OrderItemQueryVO();
        }
        queryVO.setMerchantId(ThreadLocalContext.getMerchantId());
        queryVO.setShopId(ThreadLocalContext.getShopId());

        List<MerchandiseReportDTO> dtos = this.merchandiseReportFacade.queryMerchandiseSaleTopN(queryVO.convertToDTO());

        return Result.SUCESS(this.filterAndGetMerchandiseReport(queryVO.getTopN() == null ? Integer.MAX_VALUE:queryVO.getTopN(),null,dtos));
    }

    @RequestMapping(value = "/queryMerchandiseTopNSale",method = RequestMethod.POST)
    @ApiOperation(value = "获取美食分类统计",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<List<MerchandiseReportVO>> queryMerchandiseTopNSale(@RequestBody MerchandiseReportQueryParam queryParam){
        DateParam dateParam = new DateParam();
        Long merchantId = ThreadLocalContext.getMerchantId();
        Long shopId = ThreadLocalContext.getShopId();
        List<String> goodIds = null;
        int topN = Integer.MAX_VALUE;
        if (queryParam != null) {
            if (queryParam.getReportType() != null) {
                dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(queryParam.getReportType()),queryParam.getReportDate());
            }
            if(queryParam.getStartTime() != null){
                dateParam.setStartDate(queryParam.getStartTime());
            }
            if(queryParam.getEndTime() != null){
                dateParam.setEndDate(queryParam.getEndTime());
            }
            if(queryParam.getCategoryIds() != null){
                goodIds = new ArrayList<>(this.findGoodIdsByCategories(shopId,queryParam.getCategoryIds()).keySet());
            }
            if(queryParam.getGoodIds() != null){
                goodIds = queryParam.getGoodIds();
            }
            if(queryParam.getTopN() != null){
                topN = queryParam.getTopN();
            }

        }
        List<MerchandiseReportDTO> dtos = this.merchandiseReportFacade.queryMerchandiseStatisticGroupById(merchantId, shopId, dateParam.getStartDate(), dateParam.getEndDate());

        return Result.SUCESS(this.filterAndGetMerchandiseReport(topN,goodIds,dtos));
    }


    /**
     * 获取美食分类统计
     * @param reportCommonParam
     * @return
     */
    @RequestMapping(value = "/queryMerchandiseCategoryReport",method = RequestMethod.POST)
    @ApiOperation(value = "获取美食分类统计",httpMethod = "POST",hidden = true,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<List<MerchandiseCategoryReportVO>> queryMerchandiseCategoryReport(@ModelAttribute ReportCommonParam reportCommonParam){
        if(reportCommonParam == null || reportCommonParam.getTarget() == null){
            log.info("/report/merchandise/queryMerchandiseCategoryReport 参数为NUL.");
            return Result.FAILURE(String.valueOf(ReportingErrorEnum.NULL_PARAMETER_ERROR.getCode()),ReportingErrorEnum.NULL_PARAMETER_ERROR.getMessage());
        }
        if(reportCommonParam.getReportType() != null){
            DateParam dateParam = ReportUtil.getDateParam(reportCommonParam.getReportType());
            reportCommonParam.setEndDate(dateParam.getEndDate());
            reportCommonParam.setStartDate(dateParam.getStartDate());
        }
        List<MerchandiseCategoryReportDTO> merchandiseReportDTOS = new ArrayList<>();
        //统计店铺
        if(reportCommonParam.getTarget() == 3){
            merchandiseReportDTOS = this.merchandiseReportFacade.queryShopCategoryReportByPeriods(ThreadLocalContext.getShopId(), reportCommonParam.getStartDate(), reportCommonParam.getEndDate());
        }else{
            //统计商户
            if(reportCommonParam.getTarget() == 2){
                merchandiseReportDTOS = this.merchandiseReportFacade.queryMerchantCategoryReportByPeriods(ThreadLocalContext.getMerchantId(), reportCommonParam.getStartDate(), reportCommonParam.getEndDate());
            }
        }

        return Result.SUCESS(ObjectConvertUtil.copyListProperties(merchandiseReportDTOS,MerchandiseCategoryReportVO.class));
    }

    /**
     * 获取美食分类占比
     * @param reportCommonParam
     * @return
     */
    @RequestMapping(value = "/queryMerchandiseCategoryRatioReport",method = RequestMethod.POST)
    @ApiOperation(value = "获取美食分类占比",httpMethod = "POST",hidden = true,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<List<MerchandiseCategoryReportVO>> queryMerchandiseCategoryRatioReport(@ModelAttribute ReportCommonParam reportCommonParam){

        //TODO 占比是否需要另外开发接口，交由前端控件处理？
        return Result.SUCESS();
    }

    /**
     * 获取美食统计
     * @param reportCommonParam
     * @return
     */
    @RequestMapping(value = "/queryMerchandiseReport",method = RequestMethod.GET)
    @ApiOperation(value = "获取美食统计",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<List<MerchandiseReportVO>> queryMerchandiseReport(@ModelAttribute ReportCommonParam reportCommonParam){
        if(reportCommonParam.getReportType() != null){
            DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(reportCommonParam.getReportType()),reportCommonParam.getReportDate());
            reportCommonParam.setEndDate(dateParam.getEndDate());
            reportCommonParam.setStartDate(dateParam.getStartDate());
        }
        List<MerchandiseReportDTO> merchandiseReportDTOS = new ArrayList<>();
        //统计店铺
        if(reportCommonParam.getTarget() == 3 && ThreadLocalContext.getShopId() != null){
            merchandiseReportDTOS = this.merchandiseReportFacade.queryShopMerchandiseStatistic(ThreadLocalContext.getShopId(), reportCommonParam.getStartDate(), reportCommonParam.getEndDate());
            merchandiseReportDTOS = this.makeUpShopAllGoods(ThreadLocalContext.getShopId(),merchandiseReportDTOS);
        }else{
            //统计商户
            if(reportCommonParam.getTarget() == 2 && ThreadLocalContext.getMerchantId() != null){
                merchandiseReportDTOS = this.merchandiseReportFacade.queryMerchantMerchandiseStatistic(ThreadLocalContext.getMerchantId(), reportCommonParam.getStartDate(), reportCommonParam.getEndDate());
                merchandiseReportDTOS = this.makeUpMerchantAllGoods(ThreadLocalContext.getMerchantId(),merchandiseReportDTOS);
            }
        }

        //排序
        List<MerchandiseReportDTO> afterSorted = merchandiseReportDTOS.stream().sorted(Comparator.comparingInt(MerchandiseReportDTO::getSaleQuantity).reversed()).collect(toList());

        return Result.SUCESS(MerchandiseReportVO.from(afterSorted));

    }

    /**
     * 获取美食统计饼图
     * @param reportCommonParam
     * @return
     */
    @RequestMapping(value = "/queryMerchandiseReportChart",method = RequestMethod.GET)
    @ApiOperation(value = "获取美食统计饼图",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<TrendsChartVO> queryMerchandiseReportChart(@ModelAttribute ReportCommonParam reportCommonParam){
        if(reportCommonParam.getReportType() != null){
            DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(reportCommonParam.getReportType()),reportCommonParam.getReportDate());
            reportCommonParam.setEndDate(dateParam.getEndDate());
            reportCommonParam.setStartDate(dateParam.getStartDate());
        }
        List<MerchandiseReportDTO> merchandiseReportDTOS = new ArrayList<>();
        //统计店铺
        if(reportCommonParam.getTarget() == 3){
            merchandiseReportDTOS = this.merchandiseReportFacade.queryShopMerchandiseStatistic(ThreadLocalContext.getShopId(), reportCommonParam.getStartDate(), reportCommonParam.getEndDate());
            merchandiseReportDTOS = this.makeUpShopAllGoods(ThreadLocalContext.getShopId(),merchandiseReportDTOS);
        }else{
            //统计商户
            if(reportCommonParam.getTarget() == 2){
                merchandiseReportDTOS = this.merchandiseReportFacade.queryMerchantMerchandiseStatistic(ThreadLocalContext.getMerchantId(), reportCommonParam.getStartDate(), reportCommonParam.getEndDate());
                merchandiseReportDTOS = this.makeUpMerchantAllGoods(ThreadLocalContext.getMerchantId(),merchandiseReportDTOS);
            }
        }

        //排序
        List<MerchandiseReportDTO> afterSorted = merchandiseReportDTOS.stream().sorted(Comparator.comparingInt(MerchandiseReportDTO::getSaleQuantity).reversed()).collect(toList());

        return Result.SUCESS(ReportUtil.buildTrendsChart(afterSorted,"merchandise","getGoodsName","getSaleQuantity",ReportUtil.ValueTypeEnum.NUMBER));
    }

    /**
     * 获取美食统计
     * @param reportCommonParam
     * @return
     */
    @RequestMapping(value = "/queryBranchShopMerchandiseReport",method = RequestMethod.GET)
    @ApiOperation(value = "获取商户分店美食统计",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<TrendsChartVO> queryBranchShopMerchandiseReport(@ModelAttribute ReportCommonParam reportCommonParam,
                                                                  @ApiParam(name = "shopId",value = "店铺ID")@RequestParam(value = "shopId")Long shopId){
        if(reportCommonParam.getReportType() != null){
            DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(reportCommonParam.getReportType()),reportCommonParam.getReportDate());
            reportCommonParam.setEndDate(dateParam.getEndDate());
            reportCommonParam.setStartDate(dateParam.getStartDate());
        }
        List<MerchandiseReportDTO> merchandiseReportDTOS =this.merchandiseReportFacade.queryShopMerchandiseStatistic(shopId, reportCommonParam.getStartDate(), reportCommonParam.getEndDate());
        merchandiseReportDTOS = this.makeUpShopAllGoods(shopId,merchandiseReportDTOS);

        //排序
        List<MerchandiseReportDTO> afterSorted = merchandiseReportDTOS.stream().sorted(Comparator.comparingInt(MerchandiseReportDTO::getSaleQuantity).reversed()).collect(toList());

        return Result.SUCESS(ReportUtil.buildTrendsChart(afterSorted,"merchandise","getGoodsName","getSaleQuantity",ReportUtil.ValueTypeEnum.NUMBER));

    }

    /**
     * 过滤并获取TopN
     * @param topN
     * @param goodIds
     * @param dtos
     * @return
     */
    private List<MerchandiseReportVO> filterAndGetMerchandiseReport(int topN,List<String> goodIds,List<MerchandiseReportDTO> dtos){
        boolean filterGood = false;
        Map<String,Integer> goodsMap = new HashMap<>();
        if(goodIds != null){
            filterGood = true;
            goodIds.forEach(goodsId ->{
                goodsMap.put(goodsId,1);
            });
        }
        int i = 1;
        List<MerchandiseReportVO> reportVOS = new ArrayList<>();
        List<MerchandiseReportDTO> reportDTOS = dtos.stream().sorted(Comparator.comparing(MerchandiseReportDTO::getSaleQuantity).reversed()).collect(toList());
        for(MerchandiseReportDTO reportDTO:reportDTOS){
            MerchandiseReportVO reportVO = MerchandiseReportVO.from(reportDTO);
            reportVO.setGoodUnit(reportDTO.getGoodsUnit());
            boolean addFlag = !filterGood || (filterGood && goodsMap.containsKey(reportDTO.getGoodsId()));
            if(addFlag){
                GoodsDTO goodsDTO = this.goodsFacade.getGoods(reportVO.getGoodsId(), YesOrNoEnum.NO);
                if(goodsDTO != null){
                    reportVO.setGoodsName(goodsDTO.getGoodsName());
                }
                reportVOS.add(reportVO);

                i++;
            }
            if(i > topN ){
                break;
            }
        }

        return reportVOS;
    }

    private Map<String,String> findGoodIdsByCategories(Long shopId,List<String> categoryIds){
        Map<String,String> goodIdMap = new HashMap<>();

        if(categoryIds == null){
            return goodIdMap;
        }

        for(String categoryId:categoryIds){
            List<GoodsDTO> goodsDTOS = this.goodsFacade.findByShopIdAndCategoryId(shopId, categoryId, YesOrNoEnum.NO);
            if(goodsDTOS != null){
                goodsDTOS.forEach(goodsDTO -> {
                    goodIdMap.put(goodsDTO.getId(),goodsDTO.getGoodsName());
                });
            }
        }

        return goodIdMap;
    }

    /**
     * 商户美食
     * @param merchantId
     * @param merchandiseReportDTOS
     * @return
     */
    private List<MerchandiseReportDTO> makeUpMerchantAllGoods(Long merchantId,final List<MerchandiseReportDTO> merchandiseReportDTOS){
        List<ShopDTO> shopDTOS = this.shopReadFacade.findByMerchantId(merchantId);
        if(shopDTOS == null || shopDTOS.size() <= 0 ){
            return merchandiseReportDTOS;
        }

        Map<String,GoodsDTO> goodsMap = new HashMap<>();
        for (ShopDTO shopDTO : shopDTOS) {
            GoodsQueryDTO goodsQueryDTO = new GoodsQueryDTO();
            goodsQueryDTO.setShopId(shopDTO.getShopId());
            goodsQueryDTO.setPackageType(PackageTypeEnum.NONE);
            List<GoodsDTO> goodsDTOS = this.goodsFacade.listGoodsByCondition(goodsQueryDTO);
            if(goodsDTOS != null){
                goodsDTOS.forEach(goodsDTO -> {
                    goodsMap.put(goodsDTO.getId(),goodsDTO);
                });
            }
        }

        if(goodsMap.size() <= 0){
            return merchandiseReportDTOS;
        }

        Map<String,MerchandiseReportDTO> merchandiseMap = new HashMap<>();
        if(merchandiseReportDTOS != null){
            merchandiseReportDTOS.forEach(t -> {
                merchandiseMap.put(t.getGoodsId(),t);
            });
        }

        List<MerchandiseReportDTO> result = new ArrayList<>();
        for (Map.Entry<String,GoodsDTO> entry : goodsMap.entrySet()) {
            MerchandiseReportDTO reportDTO = merchandiseMap.get(entry.getKey());
            if(reportDTO == null){
                //如果没有销量 且 已删除 不返回
                if(entry.getValue().getRemoveStatus() == RemoveStatusEnum.REMOVED){
                    continue;
                }
                reportDTO = new MerchandiseReportDTO();
            }
            reportDTO.setGoodsName(entry.getValue().getGoodsName());
            reportDTO.setGoodsId(entry.getValue().getId());
            result.add(reportDTO);
        }

        return result;
    }

    /**
     * 补全店铺美食
     * @param shopId
     * @param merchandiseReportDTOS
     * @return
     */
    private List<MerchandiseReportDTO> makeUpShopAllGoods(Long shopId,final List<MerchandiseReportDTO> merchandiseReportDTOS){
        GoodsQueryDTO goodsQueryDTO = new GoodsQueryDTO();
        goodsQueryDTO.setShopId(shopId);
        goodsQueryDTO.setPackageType(PackageTypeEnum.NONE);
        List<GoodsDTO> goodsDTOS = this.goodsFacade.listGoodsByCondition(goodsQueryDTO);
        Map<String,MerchandiseReportDTO> merchandiseMap = new HashMap<>();
        if(merchandiseReportDTOS != null){
            merchandiseReportDTOS.forEach(t -> {
                merchandiseMap.put(t.getGoodsId(),t);
            });
        }

        if(goodsDTOS == null){
            return merchandiseReportDTOS;
        }

        List<MerchandiseReportDTO> result = new ArrayList<>();
        for (GoodsDTO t : goodsDTOS) {
            MerchandiseReportDTO reportDTO = merchandiseMap.get(t.getId());
            if(reportDTO == null){
                //如果没有销量 且 已删除 不返回
                if(t.getRemoveStatus() == RemoveStatusEnum.REMOVED){
                    continue;
                }
                reportDTO = new MerchandiseReportDTO();
            }
            reportDTO.setGoodsName(t.getGoodsName());
            reportDTO.setGoodsId(t.getId());
            result.add(reportDTO);
        }

        return result;
    }

    /**
     * set 商品名
     * @param merchandiseReportDTOS
     */
    private void makeUpNames(List<MerchandiseReportDTO> merchandiseReportDTOS){
        if(merchandiseReportDTOS == null)
            return;
        merchandiseReportDTOS.forEach(t ->{
            GoodsDTO goodsDTO = this.goodsFacade.getGoods(t.getGoodsId(), YesOrNoEnum.NO);
            if(goodsDTO != null && StringUtils.isBlank(t.getGoodsName())){
                t.setGoodsName(goodsDTO.getGoodsName());
            }
        });
    }
}
