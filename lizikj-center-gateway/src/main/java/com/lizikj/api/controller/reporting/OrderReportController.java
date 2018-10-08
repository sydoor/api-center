package com.lizikj.api.controller.reporting;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.lizikj.api.enums.ReportTargetEnum;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.lizikj.api.utils.ReportUtil;
import com.lizikj.api.vo.PageVO;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.member.MemberOrderFlowVO;
import com.lizikj.api.vo.member.param.DateParam;
import com.lizikj.api.vo.reporting.BusinessReportStatisticVO;
import com.lizikj.api.vo.reporting.OrderReportStatisticsVO;
import com.lizikj.api.vo.reporting.OrderReportVO;
import com.lizikj.api.vo.reporting.PaymentReportVO;
import com.lizikj.api.vo.reporting.PieChartVO;
import com.lizikj.api.vo.reporting.TrendsChartVO;
import com.lizikj.api.vo.reporting.TrendsMultiChartVO;
import com.lizikj.common.enums.ArgumentCheckErrorEnum;
import com.lizikj.common.enums.PaymentTypeEnum;
import com.lizikj.common.util.DateUtils;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.member.facade.IMerchantMemberAccountFacade;
import com.lizikj.merchant.dto.ShopDTO;
import com.lizikj.merchant.facade.IShopReadFacade;
import com.lizikj.order.dto.OrderInfoDTO;
import com.lizikj.order.dto.param.query.OrderInfoQueryParamDTO;
import com.lizikj.order.enums.OrderStatusEnum;
import com.lizikj.order.facade.IOrderReadFacade;
import com.lizikj.payment.facade.IPaymentReadFacade;
import com.lizikj.payment.pay.dto.PayFlowDTO;
import com.lizikj.reporting.dto.BusinessReportStatisticDTO;
import com.lizikj.reporting.dto.OrderAndPaymentReportDTO;
import com.lizikj.reporting.dto.OrderReportStatisticDTO;
import com.lizikj.reporting.dto.ShopOrderReportDTO;
import com.lizikj.reporting.enums.PaymentTypeReportingEnum;
import com.lizikj.reporting.enums.ReportingTypeEnum;
import com.lizikj.reporting.facade.IOrderReportFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Created by liangxiaolin on 2017/8/15.
 */
@RestController
@RequestMapping(value = "/reporting/order")
@Api(value = "report_order",tags = "订单统计接口")
public class OrderReportController {
    private final static Logger log = LoggerFactory.getLogger(OrderReportController.class);
    @Autowired
    IOrderReportFacade orderReportFacade;
    @Autowired
    IShopReadFacade shopReadFacade;
    @Autowired
    IOrderReadFacade orderReadFacade;
    @Autowired
    IPaymentReadFacade paymentReadFacade;
    @Autowired
    IMerchantMemberAccountFacade merchantMemberAccountFacade;

    @RequestMapping(value = "/queryMemberOrderFlowRecords",method = RequestMethod.GET)
    @ApiOperation(value = "会员详情--会员消费记录",hidden = false,httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<PageVO<MemberOrderFlowVO>> queryMemberOrderFlowRecords(@ApiParam(name = "merchantMemberId",value = "会员ID",required = true)@RequestParam(value = "merchantMemberId",required = true) Long merchantMemberId,
                                                                 @ApiParam(name = "merchantId",value = "商户ID",required = true)@RequestParam(value = "merchantId",required = false) Long merchantId,
                                                                 @ApiParam(name = "pageNum",value = "页码",defaultValue = "1") @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
                                                                 @ApiParam(name = "pageSize",value = "每页数量",defaultValue = "25") @RequestParam(value = "pageSize",defaultValue = "25",required = false) int pageSize,
                                                                 @ApiParam(name = "orderBy",value = "排序字段") @RequestParam(value = "orderBy",required = false) String orderBy){
        if(merchantId == null){
            merchantId = ThreadLocalContext.getMerchantId();
        }
        Long shopId = ThreadLocalContext.getShopId();

        OrderInfoQueryParamDTO orderInfoQueryParamDTO = new OrderInfoQueryParamDTO();
        if(merchantId != null && merchantId != 0)
            orderInfoQueryParamDTO.setMerchantId(merchantId);
        if(shopId != null && shopId != 0)
            orderInfoQueryParamDTO.setShopId(shopId);

        orderInfoQueryParamDTO.setMerchantMemberId(merchantMemberId);
        //查询退款 交易完成记录
        orderInfoQueryParamDTO.setOrderStatusList(Arrays.asList(OrderStatusEnum.FINISHED,OrderStatusEnum.REFUND));
        PageInfo<OrderInfoDTO> orderInfoDTOPageInfo = this.orderReadFacade.query(orderInfoQueryParamDTO,pageNum,pageSize);
        PageVO pageInfo = new PageVO(orderInfoDTOPageInfo.getPageNum(),orderInfoDTOPageInfo.getPageSize(),
                orderInfoDTOPageInfo.getPages(),orderInfoDTOPageInfo.getTotal());
        if(orderInfoDTOPageInfo != null && orderInfoDTOPageInfo.getList() != null){
            List<MemberOrderFlowVO> memberOrderFlowVOS = new ArrayList<>(orderInfoDTOPageInfo.getList().size());
            orderInfoDTOPageInfo.getList().forEach(t -> {
                MemberOrderFlowVO memberOrderFlowVO = new MemberOrderFlowVO();
                memberOrderFlowVO.setBenefitAmount(t.getBenefitAmount());
                memberOrderFlowVO.setOrderNo(t.getOrderNo());
                memberOrderFlowVO.setMemberId(t.getMemberId());
                memberOrderFlowVO.setMerchantMemberId(t.getMerchantMemberId());
                memberOrderFlowVO.setPayAmount(t.getPayAmount());
                memberOrderFlowVO.setTotalAmount(t.getTotalAmount());
                memberOrderFlowVO.setTradeTime(t.getOrderTime());
                List<String> tradeNoList = new ArrayList<>();
                List<Byte> paymentTypeCodes = new ArrayList<>();
                List<PayFlowDTO> payFlowDTOS = this.paymentReadFacade.listPayFlowByOrderNo(t.getOrderNo(), false);
                if(payFlowDTOS != null){
                    StringBuilder payNameBuilder = new StringBuilder();
                    for(PayFlowDTO payFlowDTO:payFlowDTOS){
                        payNameBuilder.append(PaymentTypeEnum.get(payFlowDTO.getPaymentTypeCode()).getMessage()).append(",");
                        tradeNoList.add(payFlowDTO.getInnerTradeNo());
                        paymentTypeCodes.add(payFlowDTO.getPaymentTypeCode());
                    }
                    String paymentNames = payNameBuilder.toString();
                    if(!StringUtils.isBlank(paymentNames)){
                        memberOrderFlowVO.setPaymentTypeCodeNames(paymentNames.substring(0,paymentNames.length()-1));
                    }
                }

                memberOrderFlowVO.setCredit(this.merchantMemberAccountFacade.getMemberCreditByOrder(t.getOrderNo()));
                memberOrderFlowVOS.add(memberOrderFlowVO);
            });
            pageInfo.setList(memberOrderFlowVOS);
        }

        return Result.SUCESS(pageInfo);
    }

    /**
     * 经营看板 -- 营业统计
     * @param reportType
     * @return
     */
    @RequestMapping(value = "/shopOrderStatistics",method = RequestMethod.GET)
    @ApiOperation(value = "经营看板 -- 营业统计汇总",hidden = true,httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<BusinessReportStatisticVO> getShopOrderStatistics(
            @ApiParam(name = "reportType",value = "统计类型 8100 日 8200 月 8300 年 8400 当天 8500 近七天（不包括当天） 8600 近30天（不包括当天）" )
            @RequestParam(value = "reportType",defaultValue = "8500")Integer reportType,
            @ApiParam(name = "reportDate",value = "统计日期，8100 YYYY-MM-DD 8200 YYYY-MM 8300 YYYY" )
            @RequestParam(value = "reportDate",required = false)String reportDate){
        Long shopId = ThreadLocalContext.getShopId();
        if(shopId == null || shopId <= 0){
            log.info("/reporting/order/shopOrderStatistics 参数不合法");
            return Result.FAILURE(ArgumentCheckErrorEnum.INVALIDE_ARGUMENT.getCode(),ArgumentCheckErrorEnum.INVALIDE_ARGUMENT.getMessage());
        }
        DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(reportType),reportDate);
        OrderReportStatisticDTO orderReportStatisticDTO = this.orderReportFacade.getShopOrderReportStatistic(shopId,dateParam.getStartDate(),dateParam.getEndDate());
        long days = DateUtils.getDaySub(dateParam.getStartDate(),dateParam.getEndDate(),null) + 1;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        BusinessReportStatisticVO businessReportStatisticVO = ObjectConvertUtil.copyProperties(BusinessReportStatisticVO.class,orderReportStatisticDTO);
        if (businessReportStatisticVO != null) {
            businessReportStatisticVO.setDailyAmount(businessReportStatisticVO.getTotalAmount()/days);
            businessReportStatisticVO.setDailyNum(businessReportStatisticVO.getTotalNum()/(int)days);
            businessReportStatisticVO.setDailyProfit(businessReportStatisticVO.getProfitAmount()/days);
            double benefitRate = 0;
            double profitRate = 0;
            if(businessReportStatisticVO.getTotalAmount() > 0) {
                benefitRate = Double.valueOf(decimalFormat.format((double)businessReportStatisticVO.getBenefitAmount()/businessReportStatisticVO.getTotalAmount()));
                profitRate = Double.valueOf(decimalFormat.format((double)businessReportStatisticVO.getProfitAmount()/businessReportStatisticVO.getTotalAmount()));
            }
            businessReportStatisticVO.setBenefitRate(benefitRate);
            businessReportStatisticVO.setProfitRate(profitRate);
        }
        return Result.SUCESS(businessReportStatisticVO);
    }

    /**
     * 经营看板 -- 营业统计
     * @param reportType
     * @return
     */
    @RequestMapping(value = "/merchantOrderStatistics",method = RequestMethod.GET)
    @ApiOperation(value = "经营看板 -- 营业统计汇总",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<BusinessReportStatisticVO> getMerchantOrderStatistics(
            @ApiParam(name = "reportType",value = "统计类型 8100 日 8200 月 8300 年 8400 当天 8500 近七天（不包括当天） 8600 近30天（不包括当天）" )
            @RequestParam(value = "reportType",defaultValue = "8500")Integer reportType,
            @ApiParam(name = "reportDate",value = "统计日期，8100 YYYY-MM-DD 8200 YYYY-MM 8300 YYYY" )
            @RequestParam(value = "reportDate",required = false)String reportDate){
        Long merchantId = ThreadLocalContext.getMerchantId();

        if(merchantId == null || merchantId <= 0){
            log.info("/reporting/order/merchantOrderStatistics/ 参数不合法");
            return Result.FAILURE(ArgumentCheckErrorEnum.INVALIDE_ARGUMENT.getCode(),ArgumentCheckErrorEnum.INVALIDE_ARGUMENT.getMessage());
        }
        Long shopId = ThreadLocalContext.getShopId();
        if(shopId == null || shopId <= 0)
            shopId = null;

        DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(reportType),reportDate);
        BusinessReportStatisticDTO orderReportStatisticDTO = this.orderReportFacade.getBusinessReportStatistic(merchantId,shopId,dateParam.getStartDate(),dateParam.getEndDate());

        long days = DateUtils.getDaySub(dateParam.getStartDate(),dateParam.getEndDate(),null) + 1;
        BusinessReportStatisticVO businessReportStatisticVO = ObjectConvertUtil.copyProperties(BusinessReportStatisticVO.class,orderReportStatisticDTO);
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        if (businessReportStatisticVO != null) {
            businessReportStatisticVO.setDailyAmount(businessReportStatisticVO.getReceivedAmount()/days);
            businessReportStatisticVO.setDailyNum(businessReportStatisticVO.getTotalNum()/(int)days);
            businessReportStatisticVO.setDailyProfit(businessReportStatisticVO.getProfitAmount()/days);
            double benefitRate = 0;
            double profitRate = 0;
            if(businessReportStatisticVO.getTotalAmount() > 0) {
                benefitRate = Double.valueOf(decimalFormat.format((double)businessReportStatisticVO.getBenefitAmount()/businessReportStatisticVO.getTotalAmount()));
                profitRate = Double.valueOf(decimalFormat.format((double)businessReportStatisticVO.getProfitAmount()/businessReportStatisticVO.getTotalAmount()));
            }
            businessReportStatisticVO.setBenefitRate(benefitRate);
            businessReportStatisticVO.setProfitRate(profitRate);
        }
        return Result.SUCESS(businessReportStatisticVO);
    }


    /**
     * 经营看板 -- 营业额趋势
     * @param reportType
     * @return
     */
    @RequestMapping(value = "/queryOrderHourReport",method = RequestMethod.GET)
    @ApiOperation(value = "订单小时统计",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<List<OrderReportVO>> queryOrderReport(
            @ApiParam(name = "reportType",value = "统计类型  8100 日 8200 月 8300 年 8400 当天 8500 近七天（不包括当天） 8600 近30天（不包括当天）" )
            @RequestParam(value = "reportType",defaultValue = "8500")Integer reportType,
            @ApiParam(name = "reportDate",value = "统计日期 YYYY-MM-DD") @RequestParam(value = "reportDate",required = false)String reportDate,
            @ApiParam(name = "hourStep",value = "小时间隔") @RequestParam(value = "hourStep",defaultValue = "1")Integer hourStep){
        Long shopId = ThreadLocalContext.getShopId();
        Long merchantId = ThreadLocalContext.getMerchantId();
        DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(reportType),reportDate);

        List<ShopOrderReportDTO> shopOrderReportDTOS = this.orderReportFacade.queryRealHourOrderReport(merchantId,shopId,dateParam.getStartDate(),dateParam.getEndDate());

        return Result.SUCESS(getOrderReportByStep(shopOrderReportDTOS,hourStep));
    }

    /**
     * 经营看板 -- 营业额趋势
     * @param reportType
     * @return
     */
    @RequestMapping(value = "/shopSalesTrendsChart",method = RequestMethod.GET)
    @ApiOperation(value = "经营看板 -- 营业额趋势",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<TrendsChartVO> getShopOrderTrendsChart(
            @ApiParam(name = "reportType",value = "统计类型  8100 日 8200 月 8300 年 8400 当天 8500 近七天（不包括当天） 8600 近30天（不包括当天）" )
            @RequestParam(value = "reportType",defaultValue = "8500")Integer reportType,
            @ApiParam(name = "valueType",value = "纵坐标值类型 1 营业额 2 毛利润",defaultValue = "1") @RequestParam(value = "valueType") Integer valueType,
            @ApiParam(name = "reportDate",value = "统计日期 YYYY-MM-DD") @RequestParam(value = "reportDate",required = false)String reportDate){
        Long shopId = ThreadLocalContext.getShopId();
        Long merchantId = ThreadLocalContext.getMerchantId();
        DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(reportType),reportDate);

        List<ShopOrderReportDTO> shopOrderReportDTOS = new ArrayList<>();
        String xAxisMethod = null;
        if(reportType == ReportingTypeEnum.CURRENT_DAY.getCode()){
            shopOrderReportDTOS = this.orderReportFacade.queryRealHourOrderReport(merchantId,shopId,dateParam.getStartDate(),dateParam.getEndDate());
            xAxisMethod = "getReportDateStr";
        }else{
            shopOrderReportDTOS = this.orderReportFacade.queryOrderReportByDate(merchantId,shopId,dateParam.getStartDate(),dateParam.getEndDate());
            xAxisMethod = "getReportDate";
        }


        if(valueType == 1){
            return Result.SUCESS(ReportUtil.buildTrendsChart(shopOrderReportDTOS,"店铺营业额",xAxisMethod,"getReceivedAmount",ReportUtil.ValueTypeEnum.AMOUNT));
        }else{
            return Result.SUCESS(ReportUtil.buildTrendsChart(shopOrderReportDTOS,"店铺营业毛利润",xAxisMethod,"getProfitAmount",ReportUtil.ValueTypeEnum.AMOUNT));
        }
    }


    /**
     * 经营看板 -- 营业额趋势
     * @param reportType
     * @return
     */
    @RequestMapping(value = "/merchantSalesTrendsChart",method = RequestMethod.GET)
    @ApiOperation(value = "经营看板 -- 营业额趋势",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<TrendsMultiChartVO> getMerchantOrderTrendsChart(
            @ApiParam(name = "reportType",value = "统计类型  8100 日 8200 月 8300 年 8400 当天 8500 近七天（不包括当天） 8600 近30天（不包括当天）" )
            @RequestParam(value = "reportType",defaultValue = "8500")Integer reportType,
            @ApiParam(name = "valueType",value = "纵坐标值类型 1 营业额 2 毛利润",defaultValue = "1") @RequestParam(value = "valueType") Integer valueType,
            @ApiParam(name = "reportDate",value = "统计日期 YYYY-MM-DD") @RequestParam(value = "reportDate",required = false)String reportDate){
        Long merchantId = ThreadLocalContext.getMerchantId();
        if(merchantId == null || merchantId <= 0){
            log.info("/reporting/order/merchantOrderTrendsChart/ 参数不合法");
            return Result.FAILURE(ArgumentCheckErrorEnum.INVALIDE_ARGUMENT.getCode(),ArgumentCheckErrorEnum.INVALIDE_ARGUMENT.getMessage());
        }
        DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(reportType),reportDate);
        List<ShopOrderReportDTO> shopOrderReportDTOS = new ArrayList<>();
        String xAxisMethod = null;
        if(reportType == ReportingTypeEnum.CURRENT_DAY.getCode()){
            shopOrderReportDTOS = this.orderReportFacade.queryRealHourOrderReport(merchantId,null,dateParam.getStartDate(),dateParam.getEndDate());
            shopOrderReportDTOS = this.fillShopAllHourReport(merchantId,shopOrderReportDTOS);
            xAxisMethod = "getReportDateStr";
        }else{
            shopOrderReportDTOS = this.orderReportFacade.queryMerchantShopOrderReport(merchantId,dateParam.getStartDate(),dateParam.getEndDate());
            shopOrderReportDTOS = this.fillShopDateReport(merchantId,shopOrderReportDTOS,dateParam.getStartDate(),dateParam.getEndDate());
            xAxisMethod = "getReportDate";
        }


        TrendsMultiChartVO trendsMultiChartVO = null;
        if (valueType == 2) {
            trendsMultiChartVO = ReportUtil.buildMultiChart(shopOrderReportDTOS, "getShopName", xAxisMethod, "getProfitAmount",ReportUtil.ValueTypeEnum.AMOUNT);
        } else {
            trendsMultiChartVO = ReportUtil.buildMultiChart(shopOrderReportDTOS, "getShopName", xAxisMethod, "getReceivedAmount",ReportUtil.ValueTypeEnum.AMOUNT);
        }
        return Result.SUCESS(trendsMultiChartVO);
    }


    /**
     * 经营看板 -- 商户店铺营业额分布
     * @param reportType
     * @return
     */
    @RequestMapping(value = "/merchantSalesPieChart",method = RequestMethod.GET)
    @ApiOperation(value = "经营看板 -- 商户店铺营业额分布",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<PieChartVO> getMerchantOrderPieChart(
            @ApiParam(name = "reportType",value = "统计类型  8100 日 8200 月 8300 年 8400 当天 8500 近七天（不包括当天） 8600 近30天（不包括当天）" )
            @RequestParam(value = "reportType",defaultValue = "8500")Integer reportType,
            @ApiParam(name = "valueType",value = "纵坐标值类型 1 营业额 2 毛利润 3 营业笔数",defaultValue = "1") @RequestParam(value = "valueType") Integer valueType,
            @ApiParam(name = "reportDate",value = "统计日期 YYYY-MM-DD") @RequestParam(value = "reportDate",required = false)String reportDate){
        Long merchantId = ThreadLocalContext.getMerchantId();
        if(merchantId == null || merchantId <= 0){
            log.info("/reporting/order/merchantSalesPieChart/ 参数不合法");
            return Result.FAILURE(ArgumentCheckErrorEnum.INVALIDE_ARGUMENT.getCode(),ArgumentCheckErrorEnum.INVALIDE_ARGUMENT.getMessage());
        }
        DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(reportType),reportDate);
        List<ShopOrderReportDTO> shopOrderReportDTOS = this.orderReportFacade.queryMerchantShopOrderStatistic(merchantId,dateParam.getStartDate(),dateParam.getEndDate());


        if (valueType == 1) {
            return Result.SUCESS(ReportUtil.buildPieChart(shopOrderReportDTOS,String.valueOf(merchantId),"getShopName","getReceivedAmount",ReportUtil.ValueTypeEnum.AMOUNT));
        }else {
            return Result.SUCESS(ReportUtil.buildPieChart(shopOrderReportDTOS,String.valueOf(merchantId),"getShopName","getTotalNum",ReportUtil.ValueTypeEnum.NUMBER));
        }
    }

    @RequestMapping(value = "/shopOrderAndPaymentStatistic",method = RequestMethod.GET)
    @ApiOperation(value = "营业统计（+支付方式统计） -- POS端",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<OrderReportStatisticsVO> getShopOrderAndPaymentStatistic(
            @ApiParam(name = "reportType",value = "统计类型  8100 日 8200 月 8300 年 8400 当天 8500 近七天（不包括当天） 8600 近30天（不包括当天）" )
            @RequestParam(value = "reportType",defaultValue = "8500")Integer reportType,
            @ApiParam(name = "reportDate",value = "统计日期 YYYY-MM-DD") @RequestParam(value = "reportDate",required = false)String reportDate){
        Long shopId = ThreadLocalContext.getShopId();
        if(shopId == null || shopId <= 0){
            log.info("/reporting/order/shopOrderAndPaymentStatistic/ 参数不合法");
            return Result.FAILURE(ArgumentCheckErrorEnum.INVALIDE_ARGUMENT.getCode(),ArgumentCheckErrorEnum.INVALIDE_ARGUMENT.getMessage());
        }
        DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(reportType),reportDate);
        OrderAndPaymentReportDTO orderAndPaymentReportDTO = this.orderReportFacade.getOrderAndPaymentReportStatistic(null,shopId,dateParam.getStartDate(),dateParam.getEndDate());
        OrderReportStatisticsVO orderReportStatisticsVO = ObjectConvertUtil.copyProperties(OrderReportStatisticsVO.class,orderAndPaymentReportDTO);
        if(orderReportStatisticsVO != null && orderAndPaymentReportDTO.getPaymentReportDTOS() != null){
            List<PaymentReportVO> paymentReportVOS = new ArrayList<>();
            orderAndPaymentReportDTO.getPaymentReportDTOS().forEach(t->{
                PaymentReportVO paymentReportVO = ObjectConvertUtil.map(t,PaymentReportVO.class);
                paymentReportVOS.add(paymentReportVO);
            });

            List<PaymentReportVO> sorted = this.sortPaymentReports(paymentReportVOS);
            orderReportStatisticsVO.setPaymentReportVOS(sorted);
        }

        return Result.SUCESS(orderReportStatisticsVO);
    }

    @RequestMapping(value = "/queryDateBusinessReport",method = RequestMethod.GET)
    @ApiOperation(value = "营业报表日统计 -- （包含实时）",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<List<OrderReportVO>> queryDateBusinessReport(
            @ApiParam(name = "reportType",value = "统计类型  8100 日 8200 月 8300 年 8400 当天 8500 近七天（不包括当天） 8600 近30天（不包括当天）" )
            @RequestParam(value = "reportType",defaultValue = "8500")Integer reportType,
            @ApiParam(name = "target",value = "统计对象（1 所有、2 商户、3 店铺）")@RequestParam(value = "target",defaultValue = "3")Integer target,
            @ApiParam(name = "reportDate",value = "统计日期 YYYY-MM-DD") @RequestParam(value = "reportDate",required = false)String reportDate){
        Long shopId = ThreadLocalContext.getShopId();
        Long merchantId = ThreadLocalContext.getMerchantId();

        DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(reportType),reportDate);
        List<ShopOrderReportDTO> shopOrderReportDTOS = null;
        if (target == 3) {
            shopOrderReportDTOS = this.orderReportFacade.queryShopOrderReport(shopId,dateParam.getStartDate(),dateParam.getEndDate());
        }else{
            shopOrderReportDTOS = this.orderReportFacade.queryMerchantOrderReport(merchantId,dateParam.getStartDate(),dateParam.getEndDate());
        }

        if(shopOrderReportDTOS == null){
            return Result.SUCESS();
        }

        //应前端要求，返回统计日期时间戳
        List<OrderReportVO> orderReportVOS = new ArrayList<>();
        shopOrderReportDTOS.forEach(t -> {
            OrderReportVO orderReportVO = ObjectConvertUtil.map(t,OrderReportVO.class);
            if (orderReportVO.getReportDate() != null) {
                orderReportVO.setReportTime(orderReportVO.getReportDate().getTime());
            }
            orderReportVOS.add(orderReportVO);
        });
        return Result.SUCESS(orderReportVOS);
    }

    @RequestMapping(value = "/queryMonthBusinessReport",method = RequestMethod.GET)
    @ApiOperation(value = "营业报表月统计 -- 包含实时",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<List<OrderReportVO>> queryMonthBusinessReport(
            @ApiParam(name = "reportType",value = "统计类型  8100 日 8200 月 8300 年 8400 当天 8500 近七天（不包括当天） 8600 近30天（不包括当天）" )
            @RequestParam(value = "reportType",defaultValue = "8500")Integer reportType,
            @ApiParam(name = "target",value = "统计对象（1 所有、2 商户、3 店铺）")@RequestParam(value = "target",defaultValue = "3")Integer target,
            @ApiParam(name = "reportDate",value = "统计日期 YYYY-MM-DD") @RequestParam(value = "reportDate",required = false)String reportDate){
        Long shopId = ThreadLocalContext.getShopId();
        Long merchantId = ThreadLocalContext.getMerchantId();

        if(reportType < 8400 && StringUtils.isBlank(reportDate)){
            log.info("/reporting/order/queryMonthBusinessReport/ 参数不合法");
            return Result.FAILURE(ArgumentCheckErrorEnum.INVALIDE_ARGUMENT.getCode(),ArgumentCheckErrorEnum.INVALIDE_ARGUMENT.getMessage());
        }

        DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(reportType),reportDate);
        List<ShopOrderReportDTO> shopOrderReportDTOS = null;
        if (target == ReportTargetEnum.SHOP.getCode()) {
            shopOrderReportDTOS = this.orderReportFacade.queryShopOrderReportByMonth(shopId,dateParam.getStartDate(),dateParam.getEndDate());
        }else{
            shopOrderReportDTOS = this.orderReportFacade.queryMerchantOrderReportByMonth(merchantId,dateParam.getStartDate(),dateParam.getEndDate());
        }

        if(shopOrderReportDTOS == null){
            return Result.SUCESS();
        }

        //应前端要求，返回统计日期时间戳
        List<OrderReportVO> orderReportVOS = new ArrayList<>();
        shopOrderReportDTOS.forEach(t -> {
            OrderReportVO orderReportVO = ObjectConvertUtil.map(t,OrderReportVO.class);
            if (orderReportVO.getReportDate() != null) {
                orderReportVO.setReportTime(orderReportVO.getReportDate().getTime());
            }
            orderReportVOS.add(orderReportVO);
        });

        return Result.SUCESS(orderReportVOS);
    }


    @RequestMapping(value = "/queryPlatformMemberConsumeRateByMonth",method = RequestMethod.GET)
    @ApiOperation(value = "会员消费占比趋势 -- 不实时",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<List<TrendsChartVO>> queryPlatformMemberConsumeRateByMonth(
            @ApiParam(name = "reportType",value = "统计类型  8100 日 8200 月 8300 年 8400 当天 8500 近七天（不包括当天） 8600 近30天（不包括当天）" )
            @RequestParam(value = "reportType",defaultValue = "8500")Integer reportType,
            @ApiParam(name = "target",value = "统计对象（1 所有、2 商户、3 店铺）")@RequestParam(value = "target",defaultValue = "3")Integer target,
            @ApiParam(name = "reportDate",value = "统计日期 YYYY-MM-DD") @RequestParam(value = "reportDate",required = false)String reportDate){
        DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(reportType),reportDate);

        List<OrderReportStatisticDTO> orderReportStatisticDTOS = this.orderReportFacade.queryMemberOrderStatisticByMonth(null,null,dateParam.getStartDate(),dateParam.getEndDate());
        List<TrendsChartVO> trendsChartVOS = new ArrayList<>();
        if(orderReportStatisticDTOS != null){
            trendsChartVOS.add(ReportUtil.buildTrendsChart(orderReportStatisticDTOS,"totalAmount","getReportDate","getReceivedAmount",ReportUtil.ValueTypeEnum.AMOUNT));
            trendsChartVOS.add(ReportUtil.buildTrendsChart(orderReportStatisticDTOS,"memberAmount","getReportDate","getMerchantMemberAmount",ReportUtil.ValueTypeEnum.AMOUNT));
        }

        return Result.SUCESS(trendsChartVOS);
    }


    private List<OrderReportVO> getOrderReportByStep(List<ShopOrderReportDTO> dtos,int hourStep){
        List<OrderReportVO> orderReportVOS = new ArrayList<>();
        Map<Integer,OrderReportVO> reportVOMap = new HashMap<>();
        for(int key = 0;(key+1)*hourStep-1 < 24;key++){
            OrderReportVO vo = new OrderReportVO();
            vo.setReportDateStr(String.valueOf(key*hourStep) + "-" + String.valueOf((key+1)*hourStep-1));
            reportVOMap.put(key,vo);
            orderReportVOS.add(vo);
        }

        for(ShopOrderReportDTO dto : dtos){
            int key = 0;
            try {
                key = Integer.valueOf(dto.getReportDateStr())/hourStep;
            } catch (NumberFormatException e) {
                continue;
            }
            OrderReportVO vo = reportVOMap.get(key);
            if(vo == null){
                continue;
            }
            vo.add(dto);
        }

        return orderReportVOS;
    }

    private List<ShopOrderReportDTO> fillShopDateReport(final Long merchantId,final List<ShopOrderReportDTO> orderReportDTOS,Date startDate,Date endDate){
        if(startDate == null || endDate == null){
            return  orderReportDTOS;
        }

        Map<String,ShopOrderReportDTO> orderReportMap = new HashMap<>();
        if(orderReportDTOS != null){
            orderReportDTOS.forEach(t -> {
                ShopOrderReportDTO orderReport = ObjectConvertUtil.map(t,ShopOrderReportDTO.class);
                orderReportMap.put(DateUtils.format(orderReport.getReportDate(),DateUtils.FULL_SMALL_PATTERN) + String.valueOf(orderReport.getShopId()),orderReport);
            });
        }

        List<ShopDTO> shopDTOS = this.shopReadFacade.findByMerchantId(merchantId);
        Map<Long,String> shopNameMap = new HashMap<>();
        if(shopDTOS == null){
            return orderReportDTOS;
        }

        shopDTOS.forEach(shopDTO -> {
            shopNameMap.put(shopDTO.getShopId(),shopDTO.getShopName());
        });
        List<ShopOrderReportDTO> reports = new ArrayList<>();

        for(Date current = startDate;!current.after(endDate);){
            for (Map.Entry<Long,String> entry:shopNameMap.entrySet()) {
                ShopOrderReportDTO orderReport = orderReportMap.get(DateUtils.format(current,DateUtils.FULL_SMALL_PATTERN)+String.valueOf(entry.getKey()));
                if(orderReport == null){
                    orderReport = new ShopOrderReportDTO();
                    orderReport.setReportDate(current);
                    orderReport.setReportDateStr(String.valueOf(DateUtils.format(current,DateUtils.FULL_SMALL_PATTERN)));
                }
                orderReport.setShopName(entry.getValue());
                orderReport.setShopId(entry.getKey());
                reports.add(orderReport);
            }

            current = DateUtils.addDays(current,1);
        }

        return reports;
    }

    /**
     * 返回商户所有门店数据
     * @param merchantId
     * @param orderReportDTOS
     * @return
     */
    private List<ShopOrderReportDTO> fillShopAllHourReport(final Long merchantId,final List<ShopOrderReportDTO> orderReportDTOS){
        Map<String,ShopOrderReportDTO> orderReportMap = new HashMap<>();
        if(orderReportDTOS != null){
            orderReportDTOS.forEach(t -> {
                ShopOrderReportDTO orderReport = ObjectConvertUtil.map(t,ShopOrderReportDTO.class);
                orderReportMap.put(orderReport.getReportDateStr() + "_" + String.valueOf(orderReport.getShopId()),orderReport);
            });
        }

        List<ShopDTO> shopDTOS = this.shopReadFacade.findByMerchantId(merchantId);
        Map<Long,String> shopNameMap = new HashMap<>();
        if(shopDTOS == null){
            return orderReportDTOS;
        }

        shopDTOS.forEach(shopDTO -> {
            shopNameMap.put(shopDTO.getShopId(),shopDTO.getShopName());
        });
        List<ShopOrderReportDTO> reports = new ArrayList<>();

        int currentHour = DateUtils.getHourOfDate(new Date());
        for(int i=0;i <= currentHour;i++){
            for (Map.Entry<Long,String> entry:shopNameMap.entrySet()) {
                String hour = String.format("%02d:00",i);
                ShopOrderReportDTO orderReport = orderReportMap.get(String.format("%02d",i) + "_" + String.valueOf(entry.getKey()));
                if(orderReport == null){
                    orderReport = new ShopOrderReportDTO();
                }
                orderReport.setReportDateStr(hour);
                orderReport.setShopName(entry.getValue());
                orderReport.setShopId(entry.getKey());
                reports.add(orderReport);
            }
        }

        return reports;
    }

    /**
     * 填充订单统计店铺名称
     * @param orderReportDTOS
     * @return
     */
    private void fillReportWithShopName( final List<ShopOrderReportDTO> orderReportDTOS){
        if(orderReportDTOS == null){
            return;
        }
        final Map<Long,String> shopNameMap = new HashMap<>();
        orderReportDTOS.forEach(t -> {
            String shopName = shopNameMap.get(t.getShopId());
            if (shopName == null){
                ShopDTO shopDTO = this.shopReadFacade.findById(t.getShopId());
                if(shopDTO != null){
                    shopNameMap.put(t.getShopId(),shopDTO.getShopName());
                }
            }
        });

        orderReportDTOS.forEach(t -> {
            String shopName = shopNameMap.get(t.getShopId());
            if(shopName != null){
                t.setShopName(shopName);
            }
        });

        return;
    }

    /**
     * 排序
     * @param paymentReportVOS
     * @return
     */
    private List<PaymentReportVO> sortPaymentReports(List<PaymentReportVO> paymentReportVOS){
        List<PaymentReportVO> reportVOS = new ArrayList<>(paymentReportVOS.size());
        Map<Byte,PaymentReportVO> reportVOMap = new HashMap<>();

        paymentReportVOS.forEach(t -> {
            reportVOMap.put(t.getPaymentTypeCode(),t);
        });

        for(PaymentTypeReportingEnum paymentTypeReportingEnum:PaymentTypeReportingEnum.values()){
            PaymentReportVO reportVO = reportVOMap.get(paymentTypeReportingEnum.getCode());
            if(reportVO != null){
                reportVOS.add(reportVO);
            }
        }

        PaymentReportVO totalReport = reportVOMap.get((byte)0);
        if(totalReport != null){
            reportVOS.add(totalReport);
        }

        return reportVOS;
    }
}
