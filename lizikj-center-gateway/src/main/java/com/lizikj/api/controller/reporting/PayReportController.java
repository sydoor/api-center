package com.lizikj.api.controller.reporting;


import com.github.pagehelper.PageInfo;
import com.lizikj.api.enums.ReportTargetEnum;
import com.lizikj.api.utils.ReportUtil;
import com.lizikj.api.vo.PageVO;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.member.param.DateParam;
import com.lizikj.api.vo.pay.*;
import com.lizikj.api.vo.reporting.*;
import com.lizikj.api.vo.reporting.param.ReportCommonParam;
import com.lizikj.common.enums.ArgumentCheckErrorEnum;
import com.lizikj.common.enums.PaymentTypeEnum;
import com.lizikj.common.util.DateUtils;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.PageParameter;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.member.facade.IMerchantMemberFacade;
import com.lizikj.merchant.dto.AgentDTO;
import com.lizikj.merchant.dto.ShopDTO;
import com.lizikj.merchant.dto.ShopMerchantDTO;
import com.lizikj.merchant.dto.ShopMerchantExpandDTO;
import com.lizikj.merchant.facade.IAgentReadFacade;
import com.lizikj.merchant.facade.IShopMerchantReadFacade;
import com.lizikj.merchant.facade.IShopReadFacade;
import com.lizikj.order.dto.OrderInfoDTO;
import com.lizikj.order.dto.param.query.OrderInfoQueryParamDTO;
import com.lizikj.order.enums.OrderBizTypeEnum;
import com.lizikj.order.facade.IOrderReadFacade;
import com.lizikj.payment.facade.IPaymentReadFacade;
import com.lizikj.payment.pay.dto.PayFlowDTO;
import com.lizikj.payment.pay.dto.PayReportQueryDTO;
import com.lizikj.payment.pay.enums.PayStatusEnum;
import com.lizikj.payment.refund.dto.RefundOrderDTO;
import com.lizikj.payment.refund.dto.RefundReportQueryDTO;
import com.lizikj.payment.refund.enums.RefundStatusEnum;
import com.lizikj.reporting.dto.*;
import com.lizikj.reporting.dto.param.ExternalPaymentQueryDTO;
import com.lizikj.reporting.enums.ReportingTypeEnum;
import com.lizikj.reporting.facade.IPaymentReportFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA. adept
 * User:  liuyuntao
 * Date: 2017/7/20
 * Time:  10:30
 */
@RestController
@RequestMapping("/reporting/pay")
@Api(value = "report_pay", tags = "支付统计API接口")
public class PayReportController {
    private static final Logger logger = LoggerFactory.getLogger(PayReportController.class);

    @Autowired
    private IPaymentReportFacade paymentReportFacade;
    @Autowired
    IPaymentReadFacade paymentReadFacade;
    @Autowired
    IOrderReadFacade orderReadFacade;
    @Autowired
    IMerchantMemberFacade merchantMemberFacade;
    @Autowired
    IAgentReadFacade agentReadFacade;
    @Autowired
    IShopReadFacade shopReadFacade;
    @Autowired
    IShopMerchantReadFacade shopMerchantReadFacade;

    @RequestMapping(value = "/getOrderAndMemberStatistics",method = RequestMethod.POST)
    @ApiOperation(value = "获取订单和会员统计", notes = "获取订单和会员统计", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<OrderAndMemberStaticsVO> getOrderAndMemberStatistics(@RequestBody ReportCommonParam reportCommonParam){
        ReportCommonParam query = ReportUtil.routeReportTarget(ThreadLocalContext.getMerchantId(), ThreadLocalContext.getShopId(), reportCommonParam);
        if(logger.isInfoEnabled()){
            logger.info("getBusinessReport para {}",query);
        }
        OrderAndMemberStaticsDTO statistic = this.paymentReportFacade.getRealTimeOrderAndMemberStatics(ThreadLocalContext.getMerchantId(), query.getShopId(), query.getStartDate(), query.getEndDate());

        return Result.SUCESS(ObjectConvertUtil.map(statistic,OrderAndMemberStaticsVO.class));
    }

    @RequestMapping(value = "/getBusinessReport",method = RequestMethod.POST)
    @ApiOperation(value = "经营统计（包括外卖、实收、其他）", notes = "经营统计（包括外卖、实收、其他）", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<BusinessReportStatisticVO> getBusinessReport(@RequestBody ReportCommonParam reportCommonParam){
        ReportCommonParam query = ReportUtil.routeReportTarget(ThreadLocalContext.getMerchantId(), ThreadLocalContext.getShopId(), reportCommonParam);
        if(logger.isInfoEnabled()){
            logger.info("getBusinessReport para {}",query);
        }
        BusinessReportStatisticDTO statistic = this.paymentReportFacade.getBusinessReportStatistic(query.getMerchantId(), query.getShopId(), query.getStartDate(), query.getEndDate());

        return Result.SUCESS(ObjectConvertUtil.map(statistic,BusinessReportStatisticVO.class));
    }

    @RequestMapping(value = "/getMerchantPaymentReportStatistics",method = RequestMethod.GET)
    @ApiOperation(value = "获取商户交易汇总（包括实时数据）", notes = "获取商户交易汇总（包括实时数据）", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PaymentReportVO> getPaymentReportStatistics(@ModelAttribute ReportCommonParam reportCommonParam){
        Long shopId = ThreadLocalContext.getShopId();
        Long merchantId = ThreadLocalContext.getMerchantId();

        if(reportCommonParam.getStartDate() != null){
            reportCommonParam.setStartDate(DateUtils.getStartOfDay(reportCommonParam.getStartDate()));
        }

        if(reportCommonParam.getEndDate() != null){
            reportCommonParam.setEndDate(DateUtils.getEndOfDay(reportCommonParam.getEndDate()));
        }

        if(reportCommonParam.getReportType() != null){
            DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(reportCommonParam.getReportType()),reportCommonParam.getReportDate());
            reportCommonParam.setEndDate(dateParam.getEndDate());
            reportCommonParam.setStartDate(dateParam.getStartDate());
        }

        logger.info("getMerchantPaymentReportStatistics merchantId={} shopId={} startDate={} endDate={}",
                merchantId,shopId,reportCommonParam.getStartDate(),reportCommonParam.getEndDate());
        PaymentReportStatisticDTO paymentReportDTO = null;
        //查询商户交易
        if(reportCommonParam.getTarget() == ReportTargetEnum.MERCHANT.getCode()){
            paymentReportDTO = this.paymentReportFacade.getMerchantPaymentReportStatistic(merchantId,null,reportCommonParam.getStartDate(),reportCommonParam.getEndDate());
        }else if(reportCommonParam.getTarget() == ReportTargetEnum.SHOP.getCode()){
            paymentReportDTO = this.paymentReportFacade.getShopPaymentReportStatistic(shopId,null,reportCommonParam.getStartDate(),reportCommonParam.getEndDate());
        }else {
            paymentReportDTO = this.paymentReportFacade.getMerchantPaymentReportStatistic(null,null,reportCommonParam.getStartDate(),reportCommonParam.getEndDate());
        }

        return Result.SUCESS(ObjectConvertUtil.map(paymentReportDTO,PaymentReportVO.class));
    }

    @RequestMapping(value = "/getShopPaymentReportStatistics",method = RequestMethod.GET)
    @ApiOperation(value = "获取店铺交易汇总（包括实时数据）",notes = "获取店铺交易汇总（包括实时数据）", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PaymentReportVO> getShopPaymentReportStatistics(@ModelAttribute ReportCommonParam reportCommonParam){
        Long shopId = ThreadLocalContext.getShopId();
        Long merchantId = ThreadLocalContext.getMerchantId();

        if(reportCommonParam.getReportType() != null){
            DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(reportCommonParam.getReportType()),reportCommonParam.getReportDate());
            reportCommonParam.setEndDate(dateParam.getEndDate());
            reportCommonParam.setStartDate(dateParam.getStartDate());
        }

        logger.info("getShopPaymentReportStatistics merchantId={} shopId={} startDate={} endDate={}",
                merchantId,shopId,reportCommonParam.getStartDate(),reportCommonParam.getEndDate());
        PaymentReportStatisticDTO paymentReportDTO = this.paymentReportFacade.getShopPaymentReportStatistic(shopId,null,reportCommonParam.getStartDate(),reportCommonParam.getEndDate());

        return Result.SUCESS(ObjectConvertUtil.map(paymentReportDTO,PaymentReportVO.class));
    }

    @RequestMapping(value = "/date/queryPaymentReportStatisticsByDate",method = RequestMethod.GET)
    @ApiOperation(value = "交易统计列表（包括实时数据，维度：店铺+日期）", notes = "交易统计（包括实时数据，维度：日期）", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<PaymentReportVO>> queryPaymentReportStatisticsByDate(@ModelAttribute ReportCommonParam reportCommonParam){
        Long shopId = ThreadLocalContext.getShopId();
        Long merchantId = ThreadLocalContext.getMerchantId();

        if(reportCommonParam.getReportType() != null){
            DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(reportCommonParam.getReportType()),reportCommonParam.getReportDate());
            reportCommonParam.setEndDate(dateParam.getEndDate());
            reportCommonParam.setStartDate(dateParam.getStartDate());
        }
        //查询所有交易
        if(reportCommonParam.getTarget() == ReportTargetEnum.PLATFORM.getCode()){
            shopId = null;
            merchantId = null;
        }
        //查询商户交易
        if(reportCommonParam.getTarget() == ReportTargetEnum.MERCHANT.getCode()){
            shopId = null;
        }
        //查询店铺交易
        if(reportCommonParam.getTarget() == ReportTargetEnum.SHOP.getCode()){
            merchantId = null;
        }

        logger.info("queryPaymentReportStatistics merchantId={} shopId={} startDate={} endDate={}",
                merchantId,shopId,reportCommonParam.getStartDate(),reportCommonParam.getEndDate());
        List<PaymentReportDTO> paymentReportDTOS = this.paymentReportFacade.queryPaymentReportByDate(merchantId,shopId,reportCommonParam.getStartDate(),reportCommonParam.getEndDate());

        List<PaymentReportDTO> sorted = paymentReportDTOS.stream()
                .sorted(Comparator.comparing(PaymentReportDTO::getReportDate))
                .collect(Collectors.toList());
        return Result.SUCESS(ObjectConvertUtil.mapList(sorted,PaymentReportDTO.class,PaymentReportVO.class));
    }


    @RequestMapping(value = "/queryPaymentReportPageByDate",method = RequestMethod.GET)
    @ApiOperation(value = "交易统计列表分页（不包含实时数据，维度：店铺+日期）", notes = "交易统计（包括实时数据，维度：日期）", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PageVO<PaymentReportVO>> queryPaymentReportPageByDate(
            @ApiParam(name = "reportDate",value = "查询日期（YYYY-MM-DD）")@RequestParam(value = "reportDate",required = false)@DateTimeFormat(pattern="yyyy-MM-dd")Date reportDate,
            @ApiParam(name = "target",value = "查询目标对象（1 所有、2 商户、3 店铺）")@RequestParam(value = "target",required = true) Integer target,
            @ApiParam(name = "shopName",value = "店铺名称")@RequestParam(value = "shopName",required = false)String shopName,
            @ApiParam(name = "pageNum",value = "页码")@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
            @ApiParam(name = "pageSize",value = "每页条数")@RequestParam(value = "pageSize",defaultValue = "25")Integer pageSize){
        Long shopId = ThreadLocalContext.getShopId();
        Long merchantId = ThreadLocalContext.getMerchantId();

        //查询所有交易
        if(target == ReportTargetEnum.PLATFORM.getCode()){
            shopId = null;
            merchantId = null;
        }
        //查询商户交易
        if(target == ReportTargetEnum.MERCHANT.getCode()){
            shopId = null;
        }
        //查询店铺交易
        if(target == ReportTargetEnum.SHOP.getCode()){
            merchantId = null;
        }

        logger.info("queryPaymentReportPageByDate merchantId={} shopId={}", merchantId,shopId);
        PageParameter pageParameter = new PageParameter(pageNum,pageSize);
        PageInfo<PaymentReportDTO> paymentReportDTOS = this.paymentReportFacade.queryPaymentReportPageByDate(merchantId,shopId,shopName,reportDate,pageParameter);
        PageInfo pageInfo = paymentReportDTOS;
        List<PaymentReportVO> paymentReportVOS = new ArrayList<>();
        if(paymentReportDTOS.getList() != null){
            paymentReportVOS = ObjectConvertUtil.mapList(paymentReportDTOS.getList(),PaymentReportDTO.class,PaymentReportVO.class);
        }
        List<PaymentReportVO> collect = paymentReportVOS.stream().sorted(Comparator.comparing(PaymentReportVO::getReportDate).reversed()).collect(Collectors.toList());

        pageInfo.setList(collect);

        return Result.SUCESS(pageInfo);
    }

    @RequestMapping(value = "/queryPaymentReportPageByMonth",method = RequestMethod.GET)
    @ApiOperation(value = "交易月统计列表分页（不包含实时数据，维度：日期）", notes = "交易统计（包括实时数据，维度：日期）", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PageVO<PaymentReportVO>> queryPaymentReportPageByMonth(
            @ApiParam(name = "reportMonth",value = "查询月（YYYY-MM）")@RequestParam(value = "reportMonth",required = false)String reportMonth,
            @ApiParam(name = "target",value = "查询目标对象（1 所有、2 商户、3 店铺）")@RequestParam(value = "target",required = true) Integer target,
            @ApiParam(name = "shopName",value = "店铺名称")@RequestParam(value = "shopName",required = false)String shopName,
            @ApiParam(name = "pageNum",value = "页码")@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
            @ApiParam(name = "pageSize",value = "每页条数")@RequestParam(value = "pageSize",defaultValue = "25")Integer pageSize){
        Long shopId = ThreadLocalContext.getShopId();
        Long merchantId = ThreadLocalContext.getMerchantId();

        //查询所有交易
        if(target == ReportTargetEnum.PLATFORM.getCode()){
            shopId = null;
            merchantId = null;
        }
        //查询商户交易
        if(target == ReportTargetEnum.MERCHANT.getCode()){
            shopId = null;
        }
        //查询店铺交易
        if(target == ReportTargetEnum.SHOP.getCode()){
            merchantId = null;
        }

        logger.info("queryPaymentReportPageByMonth merchantId={} shopId={}", merchantId,shopId);
        PageParameter pageParameter = new PageParameter(pageNum,pageSize);
        PageInfo<PaymentReportDTO> paymentReportDTOS = this.paymentReportFacade.queryPaymentReportPageByMonth(merchantId,shopId,shopName,reportMonth,pageParameter);
        PageInfo pageInfo = paymentReportDTOS;

        List<PaymentReportVO> paymentReportVOS = new ArrayList<>();
        if(paymentReportDTOS.getList() != null){
            paymentReportVOS = ObjectConvertUtil.mapList(paymentReportDTOS.getList(),PaymentReportDTO.class,PaymentReportVO.class);
        }
        List<PaymentReportVO> collect = paymentReportVOS.stream().sorted(Comparator.comparing(PaymentReportVO::getReportTime).reversed()).collect(Collectors.toList());
        pageInfo.setList(collect);

        return Result.SUCESS(pageInfo);
    }

    @RequestMapping(value = "/date/queryBranchPaymentReportChartByCode",method = RequestMethod.GET)
    @ApiOperation(value = "交易统计汇总（包括实时数据，维度：支付方式）", notes = "店铺交易统计（包括实时数据，维度：支付方式）", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PieChartVO> queryBranchPaymentReportChartByCode(@ModelAttribute ReportCommonParam reportCommonParam,
                                                                     @ApiParam(name = "shopId",value = "店铺ID")@RequestParam(value = "shopId")Long shopId){
//        if(ThreadLocalContext.getShopId() != null){
//            return Result.FAILURE();
//        }

        if(reportCommonParam.getReportType() != null){
            DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(reportCommonParam.getReportType()),reportCommonParam.getReportDate());
            reportCommonParam.setEndDate(dateParam.getEndDate());
            reportCommonParam.setStartDate(dateParam.getStartDate());
        }

        List<PaymentReportDTO> paymentReportDTOS = this.paymentReportFacade.queryPaymentReportByCode(null,shopId,reportCommonParam.getStartDate(),reportCommonParam.getEndDate(),null,true);


        return Result.SUCESS(ReportUtil.buildPieChart(paymentReportDTOS,"支付方式统计","getPayName","getPayAmount",ReportUtil.ValueTypeEnum.AMOUNT));
    }

    @RequestMapping(value = "/date/queryPaymentReportChartByCode",method = RequestMethod.GET)
    @ApiOperation(value = "交易统计汇总（包括实时数据，维度：支付方式）", notes = "店铺交易统计（包括实时数据，维度：支付方式）", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PieChartVO> queryPaymentReportChartByCode(@ModelAttribute ReportCommonParam reportCommonParam){
        Long shopId = ThreadLocalContext.getShopId();
        Long merchantId = ThreadLocalContext.getMerchantId();

        if(reportCommonParam.getReportType() != null){
            DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(reportCommonParam.getReportType()),reportCommonParam.getReportDate());
            reportCommonParam.setEndDate(dateParam.getEndDate());
            reportCommonParam.setStartDate(dateParam.getStartDate());
        }
        //查询所有交易
        if(reportCommonParam.getTarget() == ReportTargetEnum.PLATFORM.getCode()){
            shopId = null;
            merchantId = null;
        }
        //查询商户交易
        if(reportCommonParam.getTarget() == ReportTargetEnum.MERCHANT.getCode()){
            shopId = null;
        }
        //查询店铺交易
        if(reportCommonParam.getTarget() == ReportTargetEnum.SHOP.getCode()){
            merchantId = null;
        }

        logger.info("queryPaymentReportChartByCode merchantId={} shopId={} startDate={} endDate={}",
                merchantId,shopId,reportCommonParam.getStartDate(),reportCommonParam.getEndDate());

        List<PaymentReportDTO> paymentReportDTOS = this.paymentReportFacade.queryPaymentReportByCode(merchantId,shopId,reportCommonParam.getStartDate(),reportCommonParam.getEndDate(),null,true);


        return Result.SUCESS(ReportUtil.buildPieChart(paymentReportDTOS,"支付方式统计","getPayName","getPayAmount",ReportUtil.ValueTypeEnum.AMOUNT));
    }

    @RequestMapping(value = "/date/queryPaymentReportStatisticsByCode",method = RequestMethod.GET)
    @ApiOperation(value = "交易统计汇总（包括实时数据，维度：支付方式）", notes = "店铺交易统计（包括实时数据，维度：支付方式）", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<PaymentReportVO>> queryPaymentReportStatisticsByCode(@ModelAttribute ReportCommonParam reportCommonParam){
        Long shopId = ThreadLocalContext.getShopId();
        Long merchantId = ThreadLocalContext.getMerchantId();

        if(reportCommonParam.getReportType() != null){
            DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(reportCommonParam.getReportType()),reportCommonParam.getReportDate());
            reportCommonParam.setEndDate(dateParam.getEndDate());
            reportCommonParam.setStartDate(dateParam.getStartDate());
        }else{
            if(reportCommonParam.getStartDate() != null){
                reportCommonParam.setStartDate(DateUtils.getStartOfDay(reportCommonParam.getStartDate()));
            }
            if(reportCommonParam.getEndDate() != null){
                reportCommonParam.setEndDate(DateUtils.getEndOfDay(reportCommonParam.getEndDate()));
            }
        }
        //查询所有交易
        if(reportCommonParam.getTarget() == ReportTargetEnum.PLATFORM.getCode()){
            shopId = null;
            merchantId = null;
        }
        //查询商户交易
        if(reportCommonParam.getTarget() == ReportTargetEnum.MERCHANT.getCode()){
            shopId = null;
        }
        //查询店铺交易
        if(reportCommonParam.getTarget() == ReportTargetEnum.SHOP.getCode()){
            merchantId = null;
        }

        if(reportCommonParam.getMerchantId() != null){
            merchantId = reportCommonParam.getMerchantId();
        }

        logger.info("queryPaymentReportStatisticsByCode merchantId={} shopId={} startDate={} endDate={}",
                merchantId,shopId,reportCommonParam.getStartDate(),reportCommonParam.getEndDate());

        List<PaymentReportDTO> paymentReportDTOS = this.paymentReportFacade.queryPaymentReportByCode(merchantId,shopId,reportCommonParam.getStartDate(),reportCommonParam.getEndDate(),null,true);
        ExternalPaymentQueryDTO queryDTO = new ExternalPaymentQueryDTO();
        queryDTO.setShopId(shopId);
        queryDTO.setMerchantId(merchantId);
        queryDTO.setPayStatus(PayStatusEnum.PAY_SUCCESS.getStatus());
        queryDTO.setRefundStatus(RefundStatusEnum.FAIL.getStatus());
        List<ExternalPaymentReportDTO> externalPaymentReportDTOS = paymentReportFacade.queryRealTimeExternalPaymentStatistic(queryDTO);
        return Result.SUCESS(gatherExternal(paymentReportDTOS,externalPaymentReportDTOS));
    }

    /**
     * 商户收款概况（不包含实时数据）
     * @param agentName
     * @param merchantName
     * @param startDate
     * @param endDate
     * @return
     */
    @RequestMapping(value = "/queryPaymentReportByMerchantAndCode",method = RequestMethod.GET)
    @ApiOperation(value = "商户收款概况（不包含实时数据）", notes = "商户收款概况（不包含实时数据）", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PageVO<MerchantPaymentReportVO>> queryPaymentReportByMerchantAndCode(
            @ApiParam(name = "agentName",value = "代理商名称")@RequestParam(value = "agentName",required = false)String agentName,
            @ApiParam(name = "merchantName",value = "商户名称")@RequestParam(value = "merchantName",required = false)String merchantName,
            @ApiParam(name = "startDate",value = "开始时间")@RequestParam(value = "startDate") @DateTimeFormat(pattern="yyyy-MM-dd")Date startDate,
            @ApiParam(name = "endDate",value = "结束时间")@RequestParam(value = "endDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date endDate,
            @ApiParam(name = "pageNum",value = "页码")@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
            @ApiParam(name = "pageSize",value = "每页条数")@RequestParam(value = "pageSize",defaultValue = "25")Integer pageSize){
        logger.info("queryPaymentReportByMerchantAndCode agentName={} merchantName={} startDate=% endDate={}",
                agentName,merchantName,startDate,endDate);
        if(startDate == null || endDate == null){
            return Result.FAILURE(ArgumentCheckErrorEnum.INVALIDE_ARGUMENT.getCode(),"时间参数不能为空");
        }
        startDate = DateUtils.getStartOfDay(startDate);
        endDate = DateUtils.getEndOfDay(endDate);
        PageParameter pageParameter = new PageParameter(pageNum,pageSize);
        PageInfo<MerchantPaymentReportDTO> paymentReportDTOS = this.paymentReportFacade.queryMerchantPaymentReport(merchantName,agentName,startDate,endDate,pageParameter);
        PageInfo pageInfo = paymentReportDTOS;
        List<MerchantPaymentReportVO> merchantPaymentReportVOS = new ArrayList<>();
        if(paymentReportDTOS.getList() != null){
            paymentReportDTOS.getList().forEach(t -> {
                MerchantPaymentReportVO paymentReportVO = ObjectConvertUtil.map(t,MerchantPaymentReportVO.class);
                AgentDTO agentDTO = this.agentReadFacade.findByMerchantId(t.getMerchantId());
                if(agentDTO != null){
                    paymentReportVO.setAgentName(agentDTO.getAgentName());
                }
                merchantPaymentReportVOS.add(paymentReportVO);
            });
        }

        pageInfo.setList(merchantPaymentReportVOS);
        //        List<PaymentReportDTO> paymentReportDTOS = this.paymentReportFacade.queryPaymentReportByMerchantAndCode(merchantName,agentName,startDate,endDate);

        return Result.SUCESS(pageInfo);
    }


    @RequestMapping(value = "/queryTopNPaymentReportByShop",method = RequestMethod.GET)
    @ApiOperation(value = "门店交易额排名TopN（不包含实时数据）", notes = "门店交易额排名TopN", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<TrendsChartVO> queryPlatformTopNReportByShop(
            @ApiParam(name = "topN",value = "排名前N位")@RequestParam(value = "topN")Integer topN,
            @ModelAttribute ReportCommonParam reportCommonParam){
       DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(reportCommonParam.getReportType()),reportCommonParam.getReportDate());
       List<PaymentReportDTO> paymentReportDTOS = this.paymentReportFacade.queryPlatformTopNReportByShop(topN,dateParam.getStartDate(),dateParam.getEndDate());

       return Result.SUCESS(ReportUtil.buildTrendsChart(paymentReportDTOS,"lizi","getShopName","getPayAmount",ReportUtil.ValueTypeEnum.AMOUNT));
    }

    @RequestMapping(value = "/queryPlatformPaymentReportByDate",method = RequestMethod.GET)
    @ApiOperation(value = "平台交易流水趋势", notes = "平台交易流水趋势", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<TrendsChartVO>> queryPlatformPaymentReportByDate(@ModelAttribute ReportCommonParam reportCommonParam){
        DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(reportCommonParam.getReportType()),reportCommonParam.getReportDate());
        List<TrendsChartVO> trendsChartVOS = new ArrayList<>();
        List<PaymentReportDTO> paymentReportDTOS = this.paymentReportFacade.queryPlatformPaymentReportByDate(dateParam.getStartDate(),dateParam.getEndDate());

        paymentReportDTOS = paymentReportDTOS.stream().sorted(Comparator.comparingLong(paymentReportDTO -> {
            if(paymentReportDTO.getReportDate() == null){
                return 0;
            }else{
                return paymentReportDTO.getReportDate().getTime();
            }
        })).collect(Collectors.toList());

        if(paymentReportDTOS != null){
            trendsChartVOS.add(ReportUtil.buildTrendsChart(paymentReportDTOS,"amount","getReportDate","getPayAmount",ReportUtil.ValueTypeEnum.AMOUNT));
            trendsChartVOS.add(ReportUtil.buildTrendsChart(paymentReportDTOS,"num","getReportDate","getPayNums",ReportUtil.ValueTypeEnum.NUMBER));
        }
        return Result.SUCESS(trendsChartVOS);
    }

    @RequestMapping(value = "/listPayment",method = RequestMethod.GET)
    @ApiOperation(value = "获取交易记录（财务管理）",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<PageVO<PayFlowVO>> listPayment(@RequestBody PayReportQueryVO payReportQueryVO,
                                                 @ApiParam(name = "pageNum",value = "页码")@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                 @ApiParam(name = "pageSize",value = "每页条数")@RequestParam(value = "pageSize",defaultValue = "25")Integer pageSize){
        if(payReportQueryVO != null && payReportQueryVO.getStartTime() != null){
            payReportQueryVO.setStartTime(DateUtils.getStartOfDay(payReportQueryVO.getStartTime()));
        }
        if(payReportQueryVO != null && payReportQueryVO.getEndTime() != null){
            payReportQueryVO.setEndTime(DateUtils.getEndOfDay(payReportQueryVO.getEndTime()));
        }
        //TODO 原型暂无按 会员查询
//        List<PayFlowDTO> this.paymentFacade.listByReport()
        return Result.SUCESS();
    }

    @RequestMapping(value = "/listOrderAndPay",method = RequestMethod.POST)
    @ApiOperation(value = "获取订单交易记录（交易）",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<PageVO<OrderAndPayVO>> listOrderAndPay(@RequestBody PayReportQueryVO payReportQueryVO,
                                                         @ApiParam(name = "pageNum",value = "页码")@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                         @ApiParam(name = "pageSize",value = "每页条数")@RequestParam(value = "pageSize",defaultValue = "25")Integer pageSize){
        PayReportQueryDTO payReportQueryDTO = new PayReportQueryDTO();

        if(payReportQueryVO != null){
            if(payReportQueryVO.getStartTime() != null){
                payReportQueryVO.setStartTime(DateUtils.getStartOfDay(payReportQueryVO.getStartTime()));
            }
            if(payReportQueryVO.getEndTime() != null){
                payReportQueryVO.setEndTime(DateUtils.getEndOfDay(payReportQueryVO.getEndTime()));
            }

            payReportQueryDTO = ObjectConvertUtil.map(payReportQueryVO,PayReportQueryDTO.class);
            if(payReportQueryVO.getPaymentTypeCode() != null){
                payReportQueryDTO.setPaymentTypeEnum(PaymentTypeEnum.get(payReportQueryVO.getPaymentTypeCode()));
            }

            //模糊查询
            if(!StringUtils.isBlank(payReportQueryVO.getShopName())){
                ShopDTO shopDTO = new ShopDTO();
                shopDTO.setShopName(payReportQueryVO.getShopName());
                List<ShopDTO> shopDTOS = this.shopReadFacade.findList(shopDTO);
                List<Long> shopIds = new ArrayList<>();
                if(shopDTOS != null){
                    shopDTOS.forEach(t -> {
                        shopIds.add(t.getShopId());
                    });
                }

                if (shopIds.size() <= 0){
                    PageVO pageVO = new PageVO(0,0,0,0);
                    pageVO.setList(new ArrayList());
                    return Result.SUCESS(pageVO);
                }

                payReportQueryDTO.setShopIds(shopIds);
            }
        }

        //查询本身
        if (payReportQueryVO == null ||payReportQueryVO.getIsOwner() == null || payReportQueryVO.getIsOwner() == 1) {
            payReportQueryDTO.setMerchantId(ThreadLocalContext.getMerchantId());
            payReportQueryDTO.setShopId(ThreadLocalContext.getShopId());
        }

        if(payReportQueryVO == null || payReportQueryVO.getBizType() == null){
            List<Byte> bizList = new ArrayList<>();
            //剔除会员充值
            bizList.add(OrderBizTypeEnum.MERCHANDISE.getBizType());
            bizList.add(OrderBizTypeEnum.MONEY.getBizType());
            payReportQueryDTO.setOrderBizTypeList(bizList);
        }

        //只查成功记录
        payReportQueryDTO.setPayStatusEnum(PayStatusEnum.PAY_SUCCESS);
        List<OrderAndPayVO> orderAndPayVOS = new ArrayList<>();
        PageInfo pageInfo = null;

        PageInfo<PayFlowDTO> flowDTOPageInfo = this.paymentReadFacade.listPayFlowByReport(pageNum,pageSize,payReportQueryDTO);

        pageInfo = flowDTOPageInfo;
        List<String> orderNos = new ArrayList<>();
        flowDTOPageInfo.getList().forEach(t -> {
            orderNos.add(t.getOrderNo());
        });

        Map<String,OrderInfoDTO> orderMap = this.findOrderMap(orderNos);

        flowDTOPageInfo.getList().forEach(t -> {
            OrderAndPayVO orderAndPayVO = ObjectConvertUtil.map(t,OrderAndPayVO.class);
            orderAndPayVO.setPayAmount(t.getAmount());
            if(t.getAccountDetail() != null){
                orderAndPayVO.setSnNum(t.getAccountDetail().getSnNum());
                orderAndPayVO.setMobile(t.getAccountDetail().getMemberMobile());
            }
            OrderInfoDTO orderInfoDTO = orderMap.get(t.getOrderNo());
            if (orderInfoDTO != null) {
                orderAndPayVO.setBenefitAmount(orderInfoDTO.getBenefitAmount());
                orderAndPayVO.setTotalAmount(orderInfoDTO.getTotalAmount());
                orderAndPayVO.setOrderId(orderInfoDTO.getOrderId());
            }
            if(t.getAccountDetail() != null){
                orderAndPayVO.setShopName(t.getAccountDetail().getShopName());
                if(StringUtils.isBlank(t.getAccountDetail().getShopName()) && t.getAccountDetail().getShopId()!= null){
                    ShopDTO shopDTO = this.shopReadFacade.findById(t.getAccountDetail().getShopId());
                    if(shopDTO != null){
                        orderAndPayVO.setShopName(shopDTO.getShopName());
                    }
                }
                orderAndPayVO.setSnNum(t.getAccountDetail().getSnNum());
            }
            orderAndPayVOS.add(orderAndPayVO);
        });



        pageInfo.setList(orderAndPayVOS);

        if(pageInfo != null){
            PageVO pageVO = new PageVO(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getPages(),pageInfo.getTotal());
            pageVO.setList(pageInfo.getList());
            return Result.SUCESS(pageVO);
        }


        return Result.SUCESS();
    }

    @RequestMapping(value = "/listOrderAndRefund",method = RequestMethod.POST)
    @ApiOperation(value = "获取订单退款记录（交易）",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<PageVO<OrderAndRefundVO>> listOrderAndRefund(@RequestBody RefundReportQueryVO refundReportQueryVO,
                                                               @ApiParam(name = "pageNum",value = "页码")@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                               @ApiParam(name = "pageSize",value = "每页条数")@RequestParam(value = "pageSize",defaultValue = "25")Integer pageSize){
        RefundReportQueryDTO refundReportQueryDTO = new RefundReportQueryDTO();
        if(refundReportQueryVO != null){
            refundReportQueryVO.setMerchantId(ThreadLocalContext.getMerchantId());
            if (ThreadLocalContext.getShopId() != null && ThreadLocalContext.getShopId() != 0) {
                refundReportQueryVO.setShopId(ThreadLocalContext.getShopId());
            }
            if(refundReportQueryVO.getStartTime() != null){
                refundReportQueryVO.setStartTime(DateUtils.getStartOfDay(refundReportQueryVO.getStartTime()));
            }
            if(refundReportQueryVO.getEndTime() != null){
                refundReportQueryVO.setEndTime(DateUtils.getEndOfDay(refundReportQueryVO.getEndTime()));
            }

            refundReportQueryDTO = ObjectConvertUtil.map(refundReportQueryVO,RefundReportQueryDTO.class);
            if(refundReportQueryVO.getPaymentTypeCode() != null){
                refundReportQueryDTO.setPaymentTypeEnum(PaymentTypeEnum.get(refundReportQueryVO.getPaymentTypeCode()));
            }

            //查询商户
            if(!StringUtils.isBlank(refundReportQueryVO.getMerchantName())){
                ShopMerchantExpandDTO shopMerchantExpandDTO = new ShopMerchantExpandDTO();
                shopMerchantExpandDTO.setMerchantName(refundReportQueryVO.getMerchantName());
                List<ShopMerchantExpandDTO> expandDTOS = this.shopMerchantReadFacade.findList(shopMerchantExpandDTO);
                List<Long> merchantIds = new ArrayList<>();
                if(expandDTOS != null && expandDTOS.size() > 0){
                    expandDTOS.forEach(t -> {
                        merchantIds.add(t.getMerchantId());
                    });
                }else{
                    PageVO pageVO = new PageVO<>(0,0,0,0);
                    pageVO.setList(new ArrayList());
                    return Result.SUCESS(pageVO);
                }

                refundReportQueryDTO.setMerchantIds(merchantIds);
            }
        }

        if (refundReportQueryVO == null ||refundReportQueryVO.getIsOwner() == null || refundReportQueryVO.getIsOwner() == 1){
            refundReportQueryDTO.setMerchantId(ThreadLocalContext.getMerchantId());
            refundReportQueryDTO.setShopId(ThreadLocalContext.getShopId());
        }

        refundReportQueryDTO.setRefundStatusEnum(RefundStatusEnum.SUCCESS);
        List<OrderAndRefundVO> orderAndRefundVOS = new ArrayList<>();
        PageInfo pageInfo = null;

        PageInfo<RefundOrderDTO> refundOrderDTOPageInfo = this.paymentReadFacade.listRefundFlowByReport(pageNum,pageSize,refundReportQueryDTO);
        pageInfo = refundOrderDTOPageInfo;

        List<String> orderNos = new ArrayList<>();
        refundOrderDTOPageInfo.getList().forEach(t -> {
            orderNos.add(t.getOrderNo());
        });

        Map<String,OrderInfoDTO> orderMap = this.findOrderMap(orderNos);

        refundOrderDTOPageInfo.getList().forEach(t -> {
            OrderAndRefundVO orderAndRefundVO = ObjectConvertUtil.map(t,OrderAndRefundVO.class);
            orderAndRefundVO.setRefundAmount(t.getRefundAmount());
            OrderInfoDTO orderInfoDTO = orderMap.get(t.getOrderNo());
            if (orderInfoDTO != null) {
                orderAndRefundVO.setBenefitAmount(orderInfoDTO.getBenefitAmount());
                orderAndRefundVO.setTotalAmount(orderInfoDTO.getTotalAmount());
                orderAndRefundVO.setOrderId(orderInfoDTO.getOrderId());
                orderAndRefundVO.setReceivedAmount(orderInfoDTO.getNeedAmount());
                orderAndRefundVO.setCostAmount(orderInfoDTO.getCostAmount());
            }
            ShopMerchantDTO shopMerchantDTO = this.shopMerchantReadFacade.findById(t.getMerchantId());
            if(shopMerchantDTO != null){
                orderAndRefundVO.setMerchantName(shopMerchantDTO.getMerchantName());
            }
            orderAndRefundVO.setShopName(t.getShopName());
            if(StringUtils.isBlank(t.getShopName()) && t.getShopId() != null){
                ShopDTO shopDTO = this.shopReadFacade.findById(t.getShopId());
                if(shopDTO != null){
                    orderAndRefundVO.setShopName(shopDTO.getShopName());
                }
            }
            orderAndRefundVO.setRefundTime(t.getCreateTime());
            orderAndRefundVOS.add(orderAndRefundVO);
        });
        pageInfo.setList(orderAndRefundVOS);

        if(pageInfo != null){
            PageVO pageVO = new PageVO(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getPages(),pageInfo.getTotal());
            pageVO.setList(pageInfo.getList());
            return Result.SUCESS(pageVO);
        }

        return Result.SUCESS();
    }

    /**
     * 转为外部收款
     * @param paymentReportDTOS
     * @param dtos
     * @return
     */
    private List<PaymentReportVO>  gatherExternal(List<PaymentReportDTO> paymentReportDTOS,List<ExternalPaymentReportDTO> dtos){
        List<PaymentReportVO> reportVOS = new ArrayList<>();
        paymentReportDTOS.forEach(dto ->{
            if(dto.getPaymentTypeCode() != PaymentTypeEnum.PAYMENT_TYPE_EXTERNAL_GATHERING.getCode()){
                reportVOS.add(ObjectConvertUtil.map(dto,PaymentReportVO.class));
            }
        });
        dtos.forEach(dto ->{
            PaymentReportVO vo = ObjectConvertUtil.map(dto, PaymentReportVO.class);
            vo.setExternalId(dto.getExternalId());
            vo.setExternalName(dto.getExternalPaymentName());
            reportVOS.add(vo);
        });

        return reportVOS;
    }

    private Map<String,OrderInfoDTO> findOrderMap(List<String> orderNos){
        Map<String,OrderInfoDTO> orderMap = new HashMap<>();
        OrderInfoQueryParamDTO orderInfoQueryParamDTO = new OrderInfoQueryParamDTO();
        orderInfoQueryParamDTO.setOrderNos(orderNos);
        if (orderNos.size() > 0) {
            PageInfo<OrderInfoDTO> orderInfoDTOPageInfo = this.orderReadFacade.query(orderInfoQueryParamDTO, 1, orderNos.size());
            if(orderInfoDTOPageInfo.getList() != null){
                orderInfoDTOPageInfo.getList().forEach(t -> {
                    orderMap.put(t.getOrderNo(),t);
                });
            }
        }

        return orderMap;
    }
}
