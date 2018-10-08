package com.lizikj.api.controller.reporting;

import com.github.pagehelper.PageInfo;
import com.lizikj.api.converter.PaymentTypeCodeConverter;
import com.lizikj.api.utils.ReportExportUtil;
import com.lizikj.api.utils.ReportUtil;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.member.param.DateParam;
import com.lizikj.api.vo.pay.ExportDateVO;
import com.lizikj.api.vo.pay.PayExportVO;
import com.lizikj.api.vo.pay.RefundExportVO;
import com.lizikj.api.vo.reporting.*;
import com.lizikj.cache.Cache;
import com.lizikj.common.enums.PaymentTypeEnum;
import com.lizikj.common.util.DateUtils;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.SendMailUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.login.interceptor.LoginExclude;
import com.lizikj.merchant.dto.ShopDTO;
import com.lizikj.merchant.dto.ShopMerchantDTO;
import com.lizikj.merchant.facade.IShopMerchantReadFacade;
import com.lizikj.merchant.facade.IShopReadFacade;
import com.lizikj.order.enums.OrderBizTypeEnum;
import com.lizikj.order.enums.RefundAmountTypeEnum;
import com.lizikj.order.enums.RefundTypeEnum;
import com.lizikj.payment.facade.IPaymentReadFacade;
import com.lizikj.payment.pay.dto.PayFlowDTO;
import com.lizikj.payment.pay.dto.PayReportQueryDTO;
import com.lizikj.payment.refund.dto.RefundOrderDTO;
import com.lizikj.payment.refund.dto.RefundReportQueryDTO;
import com.lizikj.reporting.dto.*;
import com.lizikj.reporting.enums.CurrencyTypeReportEnum;
import com.lizikj.reporting.enums.ReportingTypeEnum;
import com.lizikj.reporting.facade.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by liangxiaolin on 2017/9/13.
 */
@Controller
@RequestMapping(value = "/reporting/platform")
@Api(value = "report_order",tags = "李子平台接口")
@SuppressWarnings(value = {"rawtypes", "unchecked"})
public class PlatformReportController {
    private final static Logger log = LoggerFactory.getLogger(PlatformReportController.class);
    @Autowired
    IPlatformReportFacade platformReportFacade;
    @Autowired
    IPaymentReadFacade paymentReadFacade;
    @Autowired
    IStatisticFacade statisticFacade;

    @Autowired
    IShopMerchantReadFacade shopMerchantReadFacade;
    @Autowired
    IShopReadFacade shopReadFacade;

    @Autowired
    IPaymentReportFacade paymentReportFacade;
    @Autowired
    IOrderReportFacade orderReportFacade;
    @Autowired
    ICaterOrderReportFacade caterOrderReportFacade;
    
	@Autowired
    private Cache cache;

    @ResponseBody
    @RequestMapping(value = "rebuildStatisticsData",method = RequestMethod.GET)
    @LoginExclude
    @ApiOperation(value = "统计数据重跑",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<Object> rebuildStatisticsData(@RequestParam("startDate")@DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                @RequestParam(value = "endDate",required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
        endDate = endDate == null ? DateUtils.getStartOfDay(DateUtils.getCurrentDate()):endDate;
        for(Date reportDate = startDate;!reportDate.after(endDate);){
            this.statisticFacade.memberConsumeDailyStatistic(reportDate,true);
            log.info("会员消费统计 {} successfully",reportDate);
            this.statisticFacade.merchantMemberConsumeDailyStatistic(reportDate,true);
            log.info("商户会员消费统计 {} successfully",reportDate);
            this.statisticFacade.merchantMemberDailyStatistic(reportDate,true);
            log.info("商户会员统计 {} successfully",reportDate);
            //订单统计--营业统计
            this.statisticFacade.vasServiceDailyStatistic(reportDate,true);
            log.info("增值服务统计 {} successfully",reportDate);
            this.statisticFacade.platformDailyStatistic(reportDate,true);
            log.info("平台统计 {} successfully",reportDate);
            //订单统计--营业统计
            this.statisticFacade.shopOrderDailyStatistic(reportDate,true);
            log.info("订单统计--营业统计 {} successfully",reportDate);
            //订单优惠统计 -- 优惠让利
            this.statisticFacade.orderDiscountDailyStatistic(reportDate,true);
            log.info("订单优惠统计 -- 优惠让利 {} successfully",reportDate);
            //支付统计  -- 交易统计
            this.statisticFacade.payFlowDailyStatistic(reportDate,true);
            log.info("支付统计  -- 交易统计 {} successfully",reportDate);
            //支付退款统计 -- 交易退款
            this.statisticFacade.refundOrderDailyStatistic(reportDate,true);
            log.info("支付退款统计 -- 交易退款 {} successfully",reportDate);
            this.statisticFacade.merchandiseDailyStatistic(reportDate,true);
            log.info("美食日统计 {} successfully",reportDate);
            log.info("重跑数据成功 {}",reportDate);
            reportDate = DateUtils.addDays(reportDate,1);
        }

        return Result.SUCESS("重跑数据成功");
    }

    @ResponseBody
    @RequestMapping(value = "/currentStatistics",method = RequestMethod.GET)
    @ApiOperation(value = "李子平台运营概况",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<PlatformReportVO> getPlatformCurrentStatistics(){
        PlatformStatisticDTO platformReportDTO = this.platformReportFacade.getPlatformCurrentReport();
        if(platformReportDTO == null)
            platformReportDTO = new PlatformStatisticDTO();
        return Result.SUCESS(ObjectConvertUtil.map(platformReportDTO,PlatformReportVO.class));
    }

    @ResponseBody
    @RequestMapping(value = "/queryShopNumTrendsChart",method = RequestMethod.GET)
    @ApiOperation(value = "李子平台门店新增趋势图（不包括实时数据）",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<TrendsChartVO> queryShopNumTrendsChart(
            @ApiParam(name = "reportType",value = "统计类型  8100 日 8200 月 8300 年 8400 当天 8500 近七天（不包括当天） 8600 近30天（不包括当天）" )
            @RequestParam(value = "reportType",defaultValue = "8500")Integer reportType,
            @ApiParam(name = "reportDate",value = "统计日期 YYYY-MM-DD") @RequestParam(value = "reportDate",required = false)String reportDate){
        DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(reportType),reportDate);
        List<PlatformReportDTO> platformReportDTOS = this.platformReportFacade.queryPlatformReportByDate(dateParam.getStartDate(),dateParam.getEndDate());

        return Result.SUCESS(ReportUtil.buildTrendsChart(platformReportDTOS,"门店新增","getReportDate","getShopIncreaseNum",ReportUtil.ValueTypeEnum.NUMBER));
    }

    @ResponseBody
    @RequestMapping(value = "/queryMemberNumTrendsChart",method = RequestMethod.GET)
    @ApiOperation(value = "李子会员新增趋势图（不包括实时数据）",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<TrendsChartVO> queryMemberNumTrendsChart(
            @ApiParam(name = "reportType",value = "统计类型  8100 日 8200 月 8300 年 8400 当天 8500 近七天（不包括当天） 8600 近30天（不包括当天）" )
            @RequestParam(value = "reportType",defaultValue = "8500")Integer reportType,
            @ApiParam(name = "reportDate",value = "统计日期 YYYY-MM-DD") @RequestParam(value = "reportDate",required = false)String reportDate){
        DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(reportType),reportDate);
        List<PlatformReportDTO> platformReportDTOS = this.platformReportFacade.queryPlatformReportByDate(dateParam.getStartDate(),dateParam.getEndDate());

        return Result.SUCESS(ReportUtil.buildTrendsChart(platformReportDTOS,"新增会员","getReportDate","getMemberIncreaseNum",ReportUtil.ValueTypeEnum.NUMBER));
    }


	@RequestMapping(value = "/exportBusinessReportByDate",method = RequestMethod.GET)
    @ApiOperation(value = "导出经营看板",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Object> exportBusinessReportByDate(HttpServletRequest request, HttpServletResponse response,
                                               @ApiParam(name = "startDate",value = "开始时间")@RequestParam(value = "startDate") @DateTimeFormat(pattern="yyyy-MM-dd")Date startDate,
                                               @ApiParam(name = "endDate",value = "结束时间")@RequestParam(value = "endDate") @DateTimeFormat(pattern="yyyy-MM-dd")Date endDate){
        Long merchantId = ThreadLocalContext.getMerchantId();
        Long shopId = ThreadLocalContext.getShopId();
        String owner = "";
        if(shopId == null || shopId == 0){
            shopId = null;
            ShopMerchantDTO shopMerchantDTO = this.shopMerchantReadFacade.findById(merchantId);
            if(shopMerchantDTO != null){
                owner = String.valueOf(shopMerchantDTO.getMerchantName());
            }
        }
        if(merchantId == null || merchantId == 0){
            merchantId = null;
            ShopDTO shopDTO = this.shopReadFacade.findById(shopId);
            if(shopDTO != null){
                owner = String.valueOf(shopDTO.getShopName());
            }
        }
        List<BusinessReportExportDTO> businessReportExportDTOS = this.platformReportFacade.queryBusinessExportDataByDate(merchantId,shopId,startDate,endDate);
        InputStream is = getClass().getClassLoader().getResourceAsStream("excel/business_report_export_template.xlsx");
        if(is == null){
            return Result.FAILURE("模板不存在");
        }

        String writeFileName = owner + "_经营数据报表_" + DateUtils.format(startDate,DateUtils.DATE_PATTERN) + "_" + DateUtils.format(endDate,DateUtils.DATE_PATTERN) + ".xlsx";

        DateParam dateParam  = new DateParam();
        dateParam.setEndDate(endDate);
        dateParam.setStartDate(startDate);
        dateParam.setEndDateStr(DateUtils.format(endDate,DateUtils.DATE_PATTERN));
        dateParam.setStartDateStr(DateUtils.format(startDate,DateUtils.DATE_PATTERN));
        Context context = new Context();
        List<BusinessReportExportVO> exportVOS = this.buildBusinessReportExportVO(businessReportExportDTOS);
        context.putVar("businessReportExportVOs", exportVOS);
        context.putVar("dateParam", dateParam);
        context.putVar("totalReport",this.buildBusinessReportExportTotal(exportVOS));
        String token = null;
        try {
        	this.setDownloadResponse(response, writeFileName);
        	JxlsHelper.getInstance().processTemplate(is, response.getOutputStream(), context);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Result.SUCESS(token);
    }

    @RequestMapping(value = "/exportPaymentReport",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出交易记录",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<Object> exportPaymentReport(HttpServletRequest request, HttpServletResponse response,
                                                     @ApiParam(name = "reportType",value = "统计类型 8000 小时 8100 日 8200 月 8300 年 8400 当天 8500 近七天（不包括当天） 8600 近30天（不包括当天）9999 全部")
                                                     @RequestParam(value = "reportType") Integer reportType,
                                                     @ApiParam(name = "reportDate",value = "统计时间")@RequestParam(value = "reportDate",required = false) String reportDate){
        Long merchantId = ThreadLocalContext.getMerchantId();
        Long shopId = ThreadLocalContext.getShopId();
        DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(reportType),reportDate);

        String owner = "";
        if(shopId == null || shopId == 0){
            shopId = null;
            ShopMerchantDTO shopMerchantDTO = this.shopMerchantReadFacade.findById(merchantId);
            if(shopMerchantDTO != null){
                owner = String.valueOf(shopMerchantDTO.getMerchantName());
            }
        }
        if(merchantId == null || merchantId == 0){
            merchantId = null;
            ShopDTO shopDTO = this.shopReadFacade.findById(shopId);
            if(shopDTO != null){
                owner = String.valueOf(shopDTO.getShopName());
            }
        }

        PayReportQueryDTO payReportQueryDTO = new PayReportQueryDTO();
        payReportQueryDTO.setEndTime(dateParam.getEndDate());
        payReportQueryDTO.setStartTime(dateParam.getStartDate());
        payReportQueryDTO.setMerchantId(merchantId);
        payReportQueryDTO.setShopId(shopId);

        RefundReportQueryDTO refundReportQueryDTO = new RefundReportQueryDTO();
        refundReportQueryDTO.setEndTime(dateParam.getEndDate());
        refundReportQueryDTO.setStartTime(dateParam.getStartDate());
        refundReportQueryDTO.setMerchantId(merchantId);
        refundReportQueryDTO.setShopId(shopId);


        InputStream is = getClass().getClassLoader().getResourceAsStream("excel/payment_export_template.xlsx");
        if(is == null){
            return Result.FAILURE("模板不存在");
        }

        PageInfo<PayFlowDTO> payFlowDTOPageInfo = this.paymentReadFacade.listPayFlowByReport(1, 0, payReportQueryDTO);
        PageInfo<RefundOrderDTO> refundOrderDTOPageInfo = this.paymentReadFacade.listRefundFlowByReport(1, 0, refundReportQueryDTO);

        String writeFileName = owner + "_交易报表_" + DateUtils.format(dateParam.getStartDate(),DateUtils.DATE_PATTERN) + "_" + DateUtils.format(dateParam.getEndDate(),DateUtils.DATE_PATTERN) + ".xlsx";

        ExportDateVO exportDateVO = new ExportDateVO();
        exportDateVO.setPayDate(reportDate);
        exportDateVO.setRefundDate(reportDate);
        Context context = new Context();
        context.putVar("pays", this.buildPayExportModel(payFlowDTOPageInfo.getList()));
        context.putVar("refunds",this.buildRefundExportModel(refundOrderDTOPageInfo.getList()));
        context.putVar("reportDate", exportDateVO);

        this.setDownloadResponse(response,writeFileName);

        try {
            JxlsHelper.getInstance().processTemplate(is, response.getOutputStream(), context);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Result.SUCESS();
    }


    @RequestMapping(value = "/exportAllBusinessReportByDate",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "按日导出所有经营看板统计数据",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<Object> exportAllBusinessReportByDate(HttpServletRequest request, HttpServletResponse response,
                                                     @ApiParam(name = "startDate",value = "开始时间")@RequestParam(value = "startDate") @DateTimeFormat(pattern="yyyy-MM-dd")Date startDate,
                                                     @ApiParam(name = "endDate",value = "结束时间")@RequestParam(value = "endDate") @DateTimeFormat(pattern="yyyy-MM-dd")Date endDate){
        Long merchantId = ThreadLocalContext.getMerchantId();
        Long shopId = ThreadLocalContext.getShopId();

        String owner = "";
        if(shopId == null || shopId == 0){
            shopId = null;
            ShopMerchantDTO shopMerchantDTO = this.shopMerchantReadFacade.findById(merchantId);
            if(shopMerchantDTO != null){
                owner = String.valueOf(shopMerchantDTO.getMerchantName());
            }
        }
        if(merchantId == null || merchantId == 0){
            ShopDTO shopDTO = this.shopReadFacade.findById(shopId);
            if(shopDTO != null){
                owner = String.valueOf(shopDTO.getShopName());
            }
        }
        InputStream is = getClass().getClassLoader().getResourceAsStream("excel/all_business_report_export_template.xlsx");
        if(is == null ){
            return Result.FAILURE("导出模板不存在");
        }

        //营业统计
        List<BusinessReportStatisticDTO> businessReportStatisticDTOS = this.paymentReportFacade.queryBusinessReportGroupByDate(merchantId, shopId, startDate, endDate);
        //营业额
        List<OrderSourceReportDTO> orderSourceReportDTOS = this.caterOrderReportFacade.queryOrderSourceReportGroupByDate(merchantId, shopId, startDate, endDate, false);
        //实际收入
        List<PaymentReportDTO> realPays = this.paymentReportFacade.queryPaymentReportGroupByDateAndCode(merchantId, shopId, startDate, endDate, CurrencyTypeReportEnum.REAL_PAY);
        //其他收入
        List<PaymentReportDTO> couponPays = this.paymentReportFacade.queryPaymentReportGroupByDateAndCode(merchantId, shopId, startDate, endDate, CurrencyTypeReportEnum.COUPON_PAY);
        //优惠
        List<OrderBenefitReportDTO> benefitReportDTOS = this.orderReportFacade.queryBenefitReportGroupByDateAndSubType(merchantId, shopId, startDate, endDate, false);

        String writeFileName = owner + "_营业报表统计.xlsx";

        DateParam dateParam  = new DateParam();
        dateParam.setEndDate(endDate);
        dateParam.setStartDate(startDate);
        dateParam.setEndDateStr(DateUtils.format(endDate,DateUtils.DATE_PATTERN));
        dateParam.setStartDateStr(DateUtils.format(startDate,DateUtils.DATE_PATTERN));
        Context context = new Context();

        List<OrderSourceReportExportVO> orderSourceReportExportVOS = ReportExportUtil.buildOrderSourceExportVOs(startDate,endDate,orderSourceReportDTOS);
        List<PaymentStatisticExportVO> realPayExports = ReportExportUtil.buildPaymentExportVOs(startDate,endDate,realPays);
        List<PaymentStatisticExportVO> couponPayExports = ReportExportUtil.buildPaymentExportVOs(startDate,endDate,couponPays);
        List<OrderBenefitExportVO> benefitExportVOS = ReportExportUtil.buildOrderBenefitExportVOs(startDate,endDate,benefitReportDTOS);

        context.putVar("businessReportExportVOs", ReportExportUtil.buildBusinessExportVOs(startDate,endDate,businessReportStatisticDTOS));
        context.putVar("orderSourceReportExportVOs", orderSourceReportExportVOS);
        context.putVar("realPayExports", realPayExports);
        context.putVar("couponPayExports", couponPayExports);
        context.putVar("benefitExportVOs", benefitExportVOS);
        context.putVar("dateParam", dateParam);
        context.putVar("totalReport",ReportExportUtil.sumBusinessExportVOs(businessReportStatisticDTOS));
        context.putVar("totalOrderSourceReport",ReportExportUtil.sumOrderSourceReports(orderSourceReportExportVOS));
        context.putVar("totalRealPay",ReportExportUtil.sumPaymentExportVOs(realPayExports));
        context.putVar("totalCouponPay",ReportExportUtil.sumPaymentExportVOs(couponPayExports));
        context.putVar("totalBenefitReport",ReportExportUtil.sumOrderBenefits(benefitExportVOS));

        try {
            this.setDownloadResponse(response,writeFileName);
            JxlsHelper.getInstance().processTemplate(is, response.getOutputStream(), context);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Result.SUCESS();
    }


    @RequestMapping(value = "/emailBusinessReportByDate",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "发送经营经营看板数据",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<Object> emailBusinessReportByDate(HttpServletRequest request, HttpServletResponse response,
                                                    @ApiParam(name = "emails",value = "邮箱列表")@RequestBody List<String> emails,
                                                    @ApiParam(name = "startDate",value = "开始时间")@RequestParam(value = "startDate") @DateTimeFormat(pattern="yyyy-MM-dd")Date startDate,
                                                    @ApiParam(name = "endDate",value = "结束时间")@RequestParam(value = "endDate") @DateTimeFormat(pattern="yyyy-MM-dd")Date endDate){
        Long merchantId = ThreadLocalContext.getMerchantId();
        Long shopId = ThreadLocalContext.getShopId();
        if(emails == null || emails.isEmpty()){
            if(log.isInfoEnabled()){
                log.info("发送目标邮箱为空");
            }
            return Result.SUCESS();
        }

        if((shopId == null && merchantId == null) || (shopId == 0 && merchantId == 0)){
            if(log.isInfoEnabled()){
                log.info("请登录");
            }
            return Result.FAILURE();
        }
        String owner = "";
        if(shopId == null || shopId == 0){
            shopId = null;
            ShopMerchantDTO shopMerchantDTO = this.shopMerchantReadFacade.findById(merchantId);
            if(shopMerchantDTO != null){
                owner = String.valueOf(shopMerchantDTO.getMerchantName());
            }
        }
        if(merchantId == null || merchantId == 0){
            merchantId = null;
            ShopDTO shopDTO = this.shopReadFacade.findById(shopId);
            if(shopDTO != null){
                owner = String.valueOf(shopDTO.getShopName());
            }
        }
        List<BusinessReportExportDTO> businessReportExportDTOS = this.platformReportFacade.queryBusinessExportDataByDate(merchantId,shopId,startDate,endDate);
        InputStream is = getClass().getClassLoader().getResourceAsStream("excel/business_report_export_template.xlsx");

        if(is == null ){
            return Result.FAILURE("导出模板不存在");
        }

        String writeFileName = owner + request.getSession().getServletContext().getRealPath("") + "/经营数据报表_" + DateUtils.format(endDate,DateUtils.DATE_PATTERN) + ".xlsx";

        DateParam dateParam  = new DateParam();
        dateParam.setEndDate(endDate);
        dateParam.setStartDate(startDate);
        dateParam.setEndDateStr(DateUtils.format(endDate,DateUtils.DATE_PATTERN));
        dateParam.setStartDateStr(DateUtils.format(startDate,DateUtils.DATE_PATTERN));
        Context context = new Context();
        List<BusinessReportExportVO> exportVOS = this.buildBusinessReportExportVO(businessReportExportDTOS);
        context.putVar("businessReportExportVOs", exportVOS);
        context.putVar("dateParam", dateParam);
        context.putVar("totalReport",this.buildBusinessReportExportTotal(exportVOS));

        try {
            FileOutputStream os =  new FileOutputStream(writeFileName);
//            InputStream is = new FileInputStream(templateFilePath);
            JxlsHelper.getInstance().processTemplate(is, os, context);

        } catch (IOException e) {
            e.printStackTrace();
        }

        String content = "您好！"
                + "<br>"
                + "&nbsp;&nbsp;&nbsp;&nbsp;附件中是您在撩客·云餐厅中导出的"+ DateUtils.format(startDate, "yyyy.MM.dd")+"~"+DateUtils.format(endDate, "yyyy.MM.dd")+"经营数据报表，请您查收！"
                + "<br>"
                + "&nbsp;&nbsp;&nbsp;&nbsp;顺祝商祺！"
                + "<br><br>"
                + "&nbsp;&nbsp;&nbsp;&nbsp;广州李子网络科技有限公司"
                + "<br>"
                + "&nbsp;&nbsp;&nbsp;&nbsp;Tel：400-0098-008"
                + "<br>"
                ;

        for (String email:emails) {
            try {
                boolean succeed = SendMailUtil.sendMail(email,"经营看板",content,new String[]{writeFileName});
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return Result.SUCESS();
    }


    private void setDownloadResponse(HttpServletResponse response,String fileName){
        try {
            fileName = URLEncoder.encode(fileName, "utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(),"utf-8"));
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 汇总
     * @param businessReportExportVOS
     * @return
     */
    private BusinessReportExportVO buildBusinessReportExportTotal(List<BusinessReportExportVO> businessReportExportVOS){
        BusinessReportExportVO totalReport = new BusinessReportExportVO();
        if(businessReportExportVOS == null){
            return totalReport;
        }

        final DecimalFormat decimalFormat = new DecimalFormat("#.##");

        businessReportExportVOS.forEach(t -> {
            totalReport.setReceivedAmount(totalReport.getReceivedAmount() + t.getReceivedAmount());
            totalReport.setAliAmount(totalReport.getAliAmount() + t.getAliAmount());
            totalReport.setBenefitAmount(totalReport.getBenefitAmount() + t.getBenefitAmount());
            totalReport.setCardAmount(totalReport.getCardAmount() + t.getCardAmount());
            totalReport.setCashAmount(totalReport.getCashAmount() + t.getCashAmount());
            totalReport.setWebchatAmount(totalReport.getWebchatAmount() + t.getWebchatAmount());
            totalReport.setCostAmount(totalReport.getCostAmount() + t.getCostAmount());
            totalReport.setTotalAmount(totalReport.getTotalAmount() + t.getTotalAmount());
            totalReport.setProfitAmount(totalReport.getProfitAmount() + t.getProfitAmount());
            totalReport.setAliNums(totalReport.getAliNums() + t.getAliNums());
            totalReport.setCardNums(totalReport.getCardNums() + t.getCardNums());
            totalReport.setCashNums(totalReport.getCashNums() + t.getCashNums());
            totalReport.setWebchatNums(totalReport.getWebchatNums() + t.getWebchatNums());
            totalReport.setUnionPayAmount(totalReport.getUnionPayAmount() + t.getUnionPayAmount());
            totalReport.setUnionPayNums(totalReport.getUnionPayNums() + t.getUnionPayNums());
            totalReport.setMeituanPayNums(totalReport.getMeituanPayNums() + t.getMeituanPayNums());
            totalReport.setMeituanPayAmount(totalReport.getMeituanPayAmount() + t.getMeituanPayAmount());
            totalReport.setMemberPayNums(totalReport.getMemberPayNums() + t.getMemberPayNums());
            totalReport.setMemberPayAmount(totalReport.getMemberPayAmount() + t.getMemberPayAmount());
            totalReport.setMemberTotalNum(t.getMemberTotalNum());
        });

        //毛利率
        Double profitRate = 0D;
        Double received = totalReport.getReceivedAmount() - totalReport.getRefundAmount();
        if(received > 0){
            profitRate = Double.valueOf(decimalFormat.format(totalReport.getProfitAmount()/received * 100));
        }
        totalReport.setProfitRate(profitRate);

        return totalReport;
    }

    /**
     * 构建可导出对象
     * @param businessReportExportDTOS
     * @return
     */
    private List<BusinessReportExportVO> buildBusinessReportExportVO(List<BusinessReportExportDTO> businessReportExportDTOS){
        if(businessReportExportDTOS == null){
            return null;
        }

        List<BusinessReportExportVO> businessReportExportVOS = new ArrayList<>(businessReportExportDTOS.size());
        final DecimalFormat decimalFormat = new DecimalFormat("#.##");
        businessReportExportDTOS.forEach(b -> {
            BusinessReportExportVO businessReportExportVO = new BusinessReportExportVO();
            businessReportExportVO.setReportDate(b.getReportDate());
            businessReportExportVO.setReportDateStr("");
            if (b.getReportDate() != null) {
                businessReportExportVO.setReportDateStr(DateUtils.format(b.getReportDate(),DateUtils.FULL_SMALL_PATTERN));
            }
            businessReportExportVO.setBenefitAmount(Double.valueOf(decimalFormat.format((float)b.getBenefitAmount()/100)));
            businessReportExportVO.setCostAmount(Double.valueOf(decimalFormat.format((float)b.getCostAmount()/100)));
            businessReportExportVO.setProfitAmount(Double.valueOf(decimalFormat.format((float)b.getProfitAmount()/100)));
            //毛利率
            Double profitRate = 0D;
            Long received = b.getReceivedAmount() - b.getRefundAmount();
            if(received > 0){
                profitRate = Double.valueOf(decimalFormat.format(b.getProfitAmount()/(double)received * 100));
            }
            businessReportExportVO.setProfitRate(profitRate);
            businessReportExportVO.setMemberTotalNum(b.getMemberTotalNum());
            businessReportExportVO.setTotalAmount(Double.valueOf(decimalFormat.format((float)b.getTotalAmount()/100)));
            businessReportExportVO.setTotalNum(b.getTotalNum());
            businessReportExportVO.setReceivedAmount(Double.valueOf(decimalFormat.format((float)b.getReceivedAmount()/100)));
            businessReportExportVO.setMerchandiseTopSale("");
            if (b.getMerchandiseTopSale() != null) {
                businessReportExportVO.setMerchandiseTopSale(b.getMerchandiseTopSale());
            }
            businessReportExportVO.setValidNum(b.getValidNum());
            businessReportExportVO.setAliAmount(0.00);
            businessReportExportVO.setAliNums(0);
            businessReportExportVO.setWebchatAmount(0.00);
            businessReportExportVO.setWebchatNums(0);
            businessReportExportVO.setCardAmount(0.00);
            businessReportExportVO.setCardNums(0);
            businessReportExportVO.setUnionPayAmount(0.00);
            businessReportExportVO.setUnionPayNums(0);
            businessReportExportVO.setCashAmount(0.00);
            businessReportExportVO.setCashNums(0);
            if(b.getPaymentReportExportDTOS() != null){
                b.getPaymentReportExportDTOS().forEach(t -> {
                    Double payAmount = Double.valueOf(decimalFormat.format((float)t.getPayAmount()/100));
                    if(t.getPaymentTypeCode() == PaymentTypeEnum.PAYMENT_TYPE_CASH.getCode()){
                        businessReportExportVO.setCashAmount(payAmount);
                        businessReportExportVO.setCashNums(t.getPayNums());
                    }
                    if(t.getPaymentTypeCode() == PaymentTypeEnum.PAYMENT_TYPE_ALIPAY.getCode()){
                        businessReportExportVO.setAliAmount(payAmount);
                        businessReportExportVO.setAliNums(t.getPayNums());
                    }
                    if(t.getPaymentTypeCode() == PaymentTypeEnum.PAYMENT_TYPE_WECHAT.getCode()){
                        businessReportExportVO.setWebchatAmount(payAmount);
                        businessReportExportVO.setWebchatNums(t.getPayNums());
                    }
                    if(t.getPaymentTypeCode() == PaymentTypeEnum.PAYMENT_TYPE_YINLIANQIANBAO.getCode()){
                        businessReportExportVO.setUnionPayAmount(payAmount);
                        businessReportExportVO.setUnionPayNums(t.getPayNums());
                    }
                    if(t.getPaymentTypeCode() == PaymentTypeEnum.PAYMENT_TYPE_SWIPECARD.getCode()){
                        businessReportExportVO.setCardAmount(payAmount);
                        businessReportExportVO.setCardNums(t.getPayNums());
                    }
                    if(t.getPaymentTypeCode() == PaymentTypeEnum.PAYMENT_TYPE_MEMBER.getCode()){
                        businessReportExportVO.setMemberPayAmount(payAmount);
                        businessReportExportVO.setMemberPayNums(t.getPayNums());
                    }
                    if(t.getPaymentTypeCode() == PaymentTypeEnum.PAYMENT_TYPE_MEI_TUAN_COUPON.getCode()){
                        businessReportExportVO.setMeituanPayAmount(payAmount);
                        businessReportExportVO.setMeituanPayNums(t.getPayNums());
                    }
                });
            }

            businessReportExportVOS.add(businessReportExportVO);
        });

        return businessReportExportVOS;
    }


    private List<RefundExportVO> buildRefundExportModel(List<RefundOrderDTO> refundOrderDTOS){
        List<RefundExportVO> refundExportVOS = new ArrayList<>();
        final DecimalFormat decimalFormat = new DecimalFormat("0.00");
        if(refundOrderDTOS != null){
            refundOrderDTOS.forEach(t -> {
                RefundExportVO refundExportVO = new RefundExportVO();
                refundExportVO.setInnerRefundNo(t.getInnerRefundNo());
                refundExportVO.setInnerTradeNo(t.getInnerTradeNo());
                refundExportVO.setRefundAmount(Double.valueOf(decimalFormat.format((double)t.getRefundAmount()/100)));
                refundExportVO.setPaymentTypeCode("");
                if(t.getPaymentTypeCode() != null){
                    refundExportVO.setPaymentTypeCode(PaymentTypeEnum.get(t.getPaymentTypeCode()).getMessage());
                }
                refundExportVO.setRefundAmountType("");
                if(t.getRefundAmountType() != null){
                    refundExportVO.setRefundAmountType(RefundAmountTypeEnum.getEnum(t.getRefundAmountType()).getDescription());
                }
                refundExportVO.setRefundType("");
                if (t.getRefundType() != null) {
                    refundExportVO.setRefundType(RefundTypeEnum.getEnum(t.getRefundType()).getDescription());
                }

                refundExportVO.setOrderNo(t.getOrderNo());
                refundExportVO.setRefundTime("");
                if (t.getCreateTime() != null) {
                    refundExportVO.setRefundTime(DateUtils.format(t.getCreateTime(),DateUtils.FULL_BAR_PATTERN));
                }
                refundExportVOS.add(refundExportVO);
            });
        }

        return refundExportVOS;
    }

    private List<PayExportVO> buildPayExportModel(List<PayFlowDTO> payFlowDTOS){
        List<PayExportVO> payExportVOS = new ArrayList<>();
        DecimalFormat decimalFormat = new DecimalFormat();
        if(payFlowDTOS != null){
            payFlowDTOS.forEach(t ->{
                PayExportVO payExportVO = new PayExportVO();
                payExportVO.setTradeTime("");
                if (t.getTradeTime() != null) {
                    payExportVO.setTradeTime(DateUtils.format(t.getTradeTime(),DateUtils.FULL_BAR_PATTERN));
                }
                payExportVO.setPaymentTypeCode("");
                if (t.getPaymentTypeCode() != null) {
                    payExportVO.setPaymentTypeCode(PaymentTypeEnum.get(t.getPaymentTypeCode()).getMessage());
                }
                payExportVO.setBizType("");
                if(t.getBizType() != null){
                    payExportVO.setBizType(OrderBizTypeEnum.getEnum(t.getBizType()).getDescription());
                }
                payExportVO.setOrderNo(t.getOrderNo());
                payExportVO.setInnerTradeNo(t.getInnerTradeNo());
                payExportVO.setAmount(Double.valueOf(decimalFormat.format((double)t.getAmount()/100)));
                payExportVOS.add(payExportVO);
            });
        }

        return payExportVOS;
    }
}
