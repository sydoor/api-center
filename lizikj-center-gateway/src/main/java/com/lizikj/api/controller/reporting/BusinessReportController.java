package com.lizikj.api.controller.reporting;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.lizikj.api.utils.FreeMarkerExportExcelUtil;
import com.lizikj.api.utils.ReportUtil;
import com.lizikj.api.vo.PageVO;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.reporting.BusinessFullStatisticsVO;
import com.lizikj.api.vo.reporting.BusinessOperationPlanVO;
import com.lizikj.api.vo.reporting.BusinessSettlePlanVO;
import com.lizikj.api.vo.reporting.CashierDetailVO;
import com.lizikj.api.vo.reporting.CashierPaymentReportVO;
import com.lizikj.api.vo.reporting.CashierReportVO;
import com.lizikj.api.vo.reporting.CashierVO;
import com.lizikj.api.vo.reporting.CategoryProduceReportVO;
import com.lizikj.api.vo.reporting.MemberAndFlowReportVO;
import com.lizikj.api.vo.reporting.OrderBenefitReportVO;
import com.lizikj.api.vo.reporting.OrderCheckBillVO;
import com.lizikj.api.vo.reporting.OrderSourceReportVO;
import com.lizikj.api.vo.reporting.OrderSumReportVO;
import com.lizikj.api.vo.reporting.PaymentReportVO;
import com.lizikj.api.vo.reporting.param.ReportCommonParam;
import com.lizikj.common.enums.PaymentTypeEnum;
import com.lizikj.common.util.DateUtils;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.PageParameter;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.login.interceptor.LoginExclude;
import com.lizikj.merchant.dto.AuthShopStaffDTO;
import com.lizikj.merchant.dto.CashierHandoverRecordDTO;
import com.lizikj.merchant.enums.CashierStatusEnum;
import com.lizikj.merchant.facade.IAuthShopStaffReadFacade;
import com.lizikj.merchant.facade.ICashierHandoverRecordReadFacade;
import com.lizikj.order.enums.DiscountTypeNodeEnum;
import com.lizikj.order.enums.OrderStatusEnum;
import com.lizikj.reporting.dto.BusinessFullStatisticsDTO;
import com.lizikj.reporting.dto.BusinessOperationPlanDTO;
import com.lizikj.reporting.dto.BusinessSettlePlanDTO;
import com.lizikj.reporting.dto.CashierReportDTO;
import com.lizikj.reporting.dto.OrderBenefitReportDTO;
import com.lizikj.reporting.dto.OrderCheckBillDTO;
import com.lizikj.reporting.dto.OrderSourceReportDTO;
import com.lizikj.reporting.dto.PaymentReportDTO;
import com.lizikj.reporting.dto.PaymentReportStatisticDTO;
import com.lizikj.reporting.dto.ShopCategoryProduceReportDTO;
import com.lizikj.reporting.dto.param.PaymentReportQueryDTO;
import com.lizikj.reporting.enums.CurrencyTypeReportEnum;
import com.lizikj.reporting.facade.IBusinessOperationPlanFacade;
import com.lizikj.reporting.facade.ICashierReportReadFacade;
import com.lizikj.reporting.facade.ICaterOrderReportFacade;
import com.lizikj.reporting.facade.IOrderReportFacade;
import com.lizikj.reporting.facade.IPaymentReportFacade;
import com.lizikj.reporting.facade.IShopGoodsReportFacade;
import com.lizikj.shop.api.enums.AuthOperationEnum;
import com.lizikj.shop.api.facade.IShopAuthCodeReadFacade;

import io.jsonwebtoken.lang.Collections;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * @author lxl
 * @Date 2018/1/5 14:35
 */
@RestController
@RequestMapping(value = "/reporting/business")
@Api(value = "report_business",tags = "商户运营统计相关接口")
public class BusinessReportController {
    private final static Logger log = LoggerFactory.getLogger(BusinessReportController.class);

    @Autowired
    IOrderReportFacade orderReportFacade;
    @Autowired
    IBusinessOperationPlanFacade businessOperationPlanFacade;
    @Autowired
    ICaterOrderReportFacade caterOrderReportFacade;
    @Autowired
    IPaymentReportFacade paymentReportFacade;
    @Autowired
    private IShopAuthCodeReadFacade shopAuthCodeReadFacade;
    @Autowired
    ICashierReportReadFacade cashierReportReadFacade;
    @Autowired
    ICashierHandoverRecordReadFacade cashierHandoverRecordReadFacade;
    @Autowired
    IAuthShopStaffReadFacade authShopStaffReadFacade;
    @Autowired
    IShopGoodsReportFacade shopGoodsReportFacade;
    
    @Value("${export.excel.template.path}")
	private String excelBuilPath;

    @RequestMapping(value = "/getFullBusinessStatistics",method = RequestMethod.GET)
    @ApiOperation(value = "获取经营数据全领域统计",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<BusinessFullStatisticsVO> getFullBusinessStatistics(@ApiParam(name = "startTime",value = "开始时间")@RequestParam(value = "startTime")Date startTime,
                                                                      @ApiParam(name = "endTime",value = "结束时间")@RequestParam(value = "endTime")Date endTime){
        Long merchantId = ThreadLocalContext.getMerchantId();
        Long shopId = ThreadLocalContext.getShopId();
        BusinessFullStatisticsDTO fullBusinessStatistics = businessOperationPlanFacade.getFullBusinessStatistics(merchantId, shopId, startTime, endTime);

        return Result.SUCESS(convertToVO(fullBusinessStatistics));
    }

    @RequestMapping(value = "/queryEarningByPayType",method = RequestMethod.POST)
    @ApiOperation(value = "获取收入",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<List<PaymentReportVO>> queryEarningByPayType(@RequestBody ReportCommonParam param){
        ReportCommonParam query = ReportUtil.routeReportTarget(ThreadLocalContext.getMerchantId(),ThreadLocalContext.getShopId(),param);
        if(log.isInfoEnabled()){
            log.info("queryEarningByPayType para {}",query);
        }
        final CurrencyTypeReportEnum currencyTypeReportEnum = query.getCurrencyTypeReportEnum();
        List<PaymentReportDTO> paymentReportDTOS = this.paymentReportFacade.queryPaymentReportByCode(query.getMerchantId(), query.getShopId(),
                query.getStartDate(), query.getEndDate(), currencyTypeReportEnum, false);
        List<PaymentReportVO> paymentReportVOS = new ArrayList<>();
        PaymentReportStatisticDTO inShopStatistic = this.paymentReportFacade.getPaymentReportStatistic(query.getMerchantId(), query.getShopId(),
                CurrencyTypeReportEnum.REAL_PAY, query.getStartDate(), query.getEndDate());
        //减退款
       final Long inShopAmount = inShopStatistic.getPayAmount() - inShopStatistic.getRefundAmount();
       final DecimalFormat decimalFormat = new DecimalFormat("#.##");
       paymentReportDTOS.forEach(t -> {
           PaymentReportVO paymentReportVO = ObjectConvertUtil.map(t,PaymentReportVO.class);
           //减退款
           paymentReportVO.setPayAmount(paymentReportVO.getPayAmount() - paymentReportVO.getRefundAmount());
           //其他收入占堂食比例
           if(currencyTypeReportEnum == CurrencyTypeReportEnum.COUPON_PAY){
               if(inShopAmount <= 0){
                   paymentReportVO.setPayRateInShop(0.00D);
               }else{
                   paymentReportVO.setPayRateInShop(Double.valueOf(decimalFormat.format(paymentReportVO.getPayAmount()/inShopAmount*100)));
               }
           }

           paymentReportVOS.add(paymentReportVO);
        });

       return Result.SUCESS(paymentReportVOS);
    }

    @RequestMapping(value = "/queryOrderSourceReport",method = RequestMethod.POST)
    @ApiOperation(value = "获取订单来源统计",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<List<OrderSourceReportVO>> queryOrderReportGroupByPlatform(@RequestBody ReportCommonParam param){
        ReportCommonParam query = ReportUtil.routeReportTarget(ThreadLocalContext.getMerchantId(),ThreadLocalContext.getShopId(),param);
        if(log.isInfoEnabled()){
            log.info("queryOrderReportGroupByPlatform para {}",query);
        }

        List<OrderSourceReportDTO> reportDTOS = this.caterOrderReportFacade.queryOrderSourceReport(query.getMerchantId(), query.getShopId(),
                query.getStartDate(), query.getEndDate(), true);

        return Result.SUCESS(ObjectConvertUtil.mapList(reportDTOS,OrderSourceReportDTO.class,OrderSourceReportVO.class));
    }

    @RequestMapping(value = "/benefit/list",method = RequestMethod.POST)
    @ApiOperation(value = "获取优惠统计",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<List<OrderBenefitReportVO>> queryOrderBenefitGroupBySubType(@RequestBody ReportCommonParam param){
        ReportCommonParam query = ReportUtil.routeReportTarget(ThreadLocalContext.getMerchantId(),ThreadLocalContext.getShopId(),param);
        if(log.isInfoEnabled()){
            log.info("queryOrderBenefitGroupBySubType para {}",query);
        }

        List<OrderBenefitReportDTO> benefitReportDTOS = this.orderReportFacade.queryRealTimeBenefitReportGroupBySubType(query.getMerchantId(), query.getShopId(),
                query.getStartDate(), query.getEndDate(), true);

        return Result.SUCESS(ObjectConvertUtil.mapList(benefitReportDTOS,OrderBenefitReportDTO.class,OrderBenefitReportVO.class));
    }

    @RequestMapping(value = "/operation/goal/set",method = RequestMethod.POST)
    @ApiOperation(value = "更新经营计划",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<Object> addOrUpdateOperationPlan(@RequestBody BusinessOperationPlanVO planVO){
        if(planVO == null || planVO.getGoalAmount() == null || planVO.getPlanTime() == null){
            log.info("参数有误");
            return Result.FAILURE("参数有误");
        }
        BusinessOperationPlanDTO planDTO = new BusinessOperationPlanDTO();
        planDTO.setShopId(ThreadLocalContext.getShopId());
        planDTO.setMerchantId(ThreadLocalContext.getMerchantId());
        planDTO.setGoalAmount(planVO.getGoalAmount());
        planDTO.setPlanTypeEnum(planVO.getPlanTypeEnum());
        planDTO.setPlanUserId(ThreadLocalContext.getUserId());
        planDTO.setPlanTime(planVO.getPlanTime());

        int i = this.businessOperationPlanFacade.addOrUpdatePlan(planDTO);

        return Result.SUCESS(i);
    }

    @RequestMapping(value = "/operation/finishBill",method = RequestMethod.GET)
    @ApiOperation(value = "日结",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Result<Object> finishBill(@ApiParam(name = "lastSucceedPrintSeq", value = "上次打印成功序列") @RequestParam(value = "lastSucceedPrintSeq") Long lastSucceedPrintSeq,
			@ApiParam(name = "authCode", value = "授权码") @RequestParam(value = "authCode", required = false) String authCode) {
		Long merchantId = ThreadLocalContext.getMerchantId();
		Long shopId = ThreadLocalContext.getShopId();
		Long staffId = ThreadLocalContext.getStaffId();

		if (lastSucceedPrintSeq == null) {
			lastSucceedPrintSeq = 0L;
		}

		if (StringUtils.isNotBlank(authCode)) {
			if (!shopAuthCodeReadFacade.verifyAuthCodeTwice(merchantId, authCode, staffId, AuthOperationEnum.SUM_DAY_REPORT)) {
				return Result.FAILURE("授权码错误或已失效!");
			}
		}
		//TODO:查询是否有营业员没有交接班
		
//		int i = this.businessOperationPlanFacade.updateSettlePrint(merchantId, shopId, lastSucceedPrintSeq + 1, false);

		return Result.SUCESS("成功");
	}


    @RequestMapping(value = "/operation/billPrintSucceed",method = RequestMethod.POST)
    @ApiOperation(value = "清机报表打印成功回调",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<Object> billPrintSucceed(@ApiParam(name = "lastSucceedPrintSeq",value = "上次打印成功序列")@RequestParam(value = "lastSucceedPrintSeq")Long lastSucceedPrintSeq){
        Long merchantId = ThreadLocalContext.getMerchantId();
        Long shopId = ThreadLocalContext.getShopId();

        int succed = 0;
        try {
            succed = businessOperationPlanFacade.updateSettlePrint(merchantId,shopId,lastSucceedPrintSeq,true);
        } catch (Exception e) {
            log.warn("清机报表打印成功回调 lastSucceedPrintSeq={} 异常",lastSucceedPrintSeq,e);
        }
        if(succed > 0){
            return Result.SUCESS();
        }

        return Result.FAILURE();
    }


    @RequestMapping(value = "/operation/checkBill",method = RequestMethod.GET)
    @ApiOperation(value = "验证是否可以打印清机报表",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<String> handOver(){
        Long shopId = ThreadLocalContext.getShopId();
        String names="";
        List<CashierHandoverRecordDTO> lists=cashierHandoverRecordReadFacade.findShopNoEndRecord(shopId);
        if(lists!=null&&lists.size()>0){
        	for (int i = 0; i < lists.size(); i++) {
        		names+=lists.get(i).getStaffFullName()+",";
			}
        	names=names.substring(0, names.length()-1);
        	names="收银员:"+names+"未结束收银";
        }
        return Result.SUCESS(names);
    }

    @RequestMapping(value = "/operation/listBusinessSettles",method = RequestMethod.GET)
    @ApiOperation(value = "获取清机报表记录",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<List<BusinessSettlePlanVO>> listBusinessSettles(){
        Long shopId = ThreadLocalContext.getShopId();
        List<BusinessSettlePlanDTO> planDTOS = businessOperationPlanFacade.queryByShop(shopId);

        return Result.SUCESS(ObjectConvertUtil.mapList(planDTOS,BusinessSettlePlanDTO.class,BusinessSettlePlanVO.class));
    }

    @RequestMapping(value = "/operation/printSettle",method = RequestMethod.GET)
    @ApiOperation(value = "打印清机记录",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<OrderCheckBillVO> printSettle(@ApiParam(name = "printSeq",value = "打印序列")@RequestParam(value = "printSeq")Long printSeq){
        Long shopId = ThreadLocalContext.getShopId();
        if(shopId == null || printSeq == null){
            return Result.FAILURE("参数为空");
        }

        OrderCheckBillDTO orderCheckBill = businessOperationPlanFacade.getOrderCheckBill(shopId,printSeq);

        return Result.SUCESS(convertToVO(orderCheckBill,null,null,null));
    }
    
    @RequestMapping(value = "/operation/getOrderCheckBill",method = RequestMethod.GET)
    @ApiOperation(value = "获取清机报表",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<OrderCheckBillVO> getOrderCheckBill(@ApiParam(name = "type", value = "报表类型(0日结1打印)") @RequestParam(value = "type",defaultValue="1",required = false) int type){
        Long merchantId = ThreadLocalContext.getMerchantId();
        Long shopId = ThreadLocalContext.getShopId();

        BusinessSettlePlanDTO nextSettlePrint = businessOperationPlanFacade.getNextSettlePrint(merchantId, shopId, null,type);
        Date sTime=nextSettlePrint.getStartTime();
        if(nextSettlePrint.getPrintStatus()==true){
        	sTime=nextSettlePrint.getEndTime();
        }
        Date endTime=new Date();
        log.info("sTime:{},eTime:{}",sTime,endTime);
        OrderCheckBillDTO orderCheckBill = this.businessOperationPlanFacade.getOrderCheckBill(merchantId,shopId, sTime, endTime);
        OrderCheckBillVO billVO = convertToVO(orderCheckBill,sTime,endTime,nextSettlePrint.getPrintSeq());
        //同步更新打印记录
        BusinessSettlePlanDTO update = new BusinessSettlePlanDTO();
        update.setAmount(billVO.getTotalAmount());
        update.setNum(billVO.getTotalNum());
        update.setPlanId(nextSettlePrint.getPlanId());
        businessOperationPlanFacade.updateSettlePrint(update);

        return Result.SUCESS(billVO);
    }

    private List<CashierPaymentReportVO> getCashierReports(List<PaymentReportDTO> reportDTOS){
        Map<Long, List<PaymentReportDTO>> cashierMap = ReportUtil.groupByKey(reportDTOS, t -> t.getCashierId());
        List<CashierPaymentReportVO> reportVOS = new ArrayList<>();
        for(Map.Entry<Long,List<PaymentReportDTO>> entry:cashierMap.entrySet()){
            CashierPaymentReportVO vo = new CashierPaymentReportVO();
            vo.setCashierId(entry.getKey());
            AuthShopStaffDTO staff = authShopStaffReadFacade.findById(entry.getKey());
            if(staff != null){
                vo.setCashierName(staff.getFullName());
            }
            vo.setPaymentReportVOList(ObjectConvertUtil.mapList(entry.getValue(),PaymentReportDTO.class,PaymentReportVO.class));
            reportVOS.add(vo);
        }

        return reportVOS;
    }

    private BusinessFullStatisticsVO convertToVO(BusinessFullStatisticsDTO dto){
        BusinessFullStatisticsVO statisticsVO = new BusinessFullStatisticsVO();
        if(dto != null){
            statisticsVO.setTotalAmount(dto.getTotalAmount());
            statisticsVO.setTotalNum(dto.getTotalNum());
            statisticsVO.setNumOfGuests(dto.getNumOfGuests());
            statisticsVO.setAvgAmountOfGuests(dto.getAvgAmountOfGuests());
            statisticsVO.setAvgNumOfGuests(dto.getAvgNumOfGuests());
            if(dto.getRealPayReports() != null){
                statisticsVO.setRealPayReports(ObjectConvertUtil.mapList(dto.getRealPayReports(),PaymentReportDTO.class,PaymentReportVO.class));
            }
            if(dto.getCouponPayReports() != null){
                statisticsVO.setCouponPayReports(ObjectConvertUtil.mapList(dto.getCouponPayReports(),PaymentReportDTO.class,PaymentReportVO.class));
            }
            if(dto.getPayAndRefundReports() != null){
                statisticsVO.setPayAndRefundReports(ObjectConvertUtil.mapList(dto.getPayAndRefundReports(),PaymentReportDTO.class,PaymentReportVO.class));
            }
            if(dto.getBenefitReports() != null){
                statisticsVO.setBenefitReports(ObjectConvertUtil.mapList(dto.getBenefitReports(),OrderBenefitReportDTO.class,OrderBenefitReportVO.class));
            }
            if(dto.getOrderSumReports() != null){
                List<OrderSumReportVO> reportVOS = new ArrayList<>();
                dto.getOrderSumReports().forEach(t ->{
                    OrderSumReportVO vo = ObjectConvertUtil.map(t,OrderSumReportVO.class);
                    vo.setOrderStatusEnum(OrderStatusEnum.getEnum(t.getOrderStatus()));
                    reportVOS.add(vo);
                });
                statisticsVO.setOrderSumReports(reportVOS);
            }

            if(dto.getCashierPaymentReports() != null){
                statisticsVO.setCashierPaymentReportVOS(getCashierReports(dto.getCashierPaymentReports()));
            }
            if(dto.getMemberAndFlowReportDTO() != null){
                statisticsVO.setMemberAndFlowReportVO(ObjectConvertUtil.map(dto.getMemberAndFlowReportDTO(),MemberAndFlowReportVO.class));
            }
            if(dto.getShopCategoryProduceReportDTOS() != null){
                statisticsVO.setCategoryProduceReportVOS(ObjectConvertUtil.mapList(dto.getShopCategoryProduceReportDTOS(),ShopCategoryProduceReportDTO.class,CategoryProduceReportVO.class));
            }
        }

        return statisticsVO;
    }

    private OrderCheckBillVO convertToVO(OrderCheckBillDTO orderCheckBill,Date startTime,Date endTime,Long printSeq) {
        OrderCheckBillVO billVO = new OrderCheckBillVO();
        if(orderCheckBill != null){
            billVO.setTotalAmount(orderCheckBill.getTotalAmount());
            billVO.setTotalNum(orderCheckBill.getTotalNum());
            billVO.setNumOfGuests(orderCheckBill.getNumOfGuests());
            billVO.setAvgAmountOfGuests(orderCheckBill.getAvgAmountOfGuests());
            billVO.setAvgNumOfGuests(orderCheckBill.getAvgNumOfGuests());
            billVO.setPrintSeq(printSeq);
            billVO.setEndTime(endTime);
            billVO.setStartTime(startTime);
           if(orderCheckBill.getRealPayReports() != null){
               billVO.setRealPayReports(ObjectConvertUtil.mapList(orderCheckBill.getRealPayReports(),PaymentReportDTO.class,PaymentReportVO.class));
           }
           if(orderCheckBill.getCouponPayReports() != null){
               billVO.setCouponPayReports(ObjectConvertUtil.mapList(orderCheckBill.getCouponPayReports(),PaymentReportDTO.class,PaymentReportVO.class));
           }
           if(orderCheckBill.getPayAndRefundReports() != null){
               billVO.setPayAndRefundReports(ObjectConvertUtil.mapList(orderCheckBill.getPayAndRefundReports(),PaymentReportDTO.class,PaymentReportVO.class));
           }
           if(orderCheckBill.getBenefitReports() != null){
               billVO.setBenefitReports(ObjectConvertUtil.mapList(orderCheckBill.getBenefitReports(),OrderBenefitReportDTO.class,OrderBenefitReportVO.class));
           }
           if(orderCheckBill.getOrderSumReports() != null){
               List<OrderSumReportVO> reportVOS = new ArrayList<>();
               orderCheckBill.getOrderSumReports().forEach(t ->{
                   OrderSumReportVO vo = ObjectConvertUtil.map(t,OrderSumReportVO.class);
                   vo.setOrderStatusEnum(OrderStatusEnum.getEnum(t.getOrderStatus()));
                   reportVOS.add(vo);
               });
               billVO.setOrderSumReports(reportVOS);
           }
        }
        return billVO;
    }

    @RequestMapping(value = "/operation/getBusinessBill",method = RequestMethod.POST)
    @ApiOperation(value = "营业报表",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<OrderCheckBillVO> getBusinessBill(
    		@ApiParam(name = "startTime", value = "开始时间(yyyy-mm-dd hh:ss:mm)") 
    		@RequestParam(value = "startTime",required = false) Long startTime
    		,@ApiParam(name = "endTime", value = "结束时间(yyyy-mm-dd hh:ss:mm)") 
    		@RequestParam(value = "endTime",required = false) Long endTime){
        Long merchantId = ThreadLocalContext.getMerchantId();
        Long shopId = ThreadLocalContext.getShopId();
        BusinessSettlePlanDTO nextSettlePrint = businessOperationPlanFacade.getNextSettlePrint(merchantId, shopId, null,1);
        Date sTime=DateUtils.getStartOfDay(new Date());
       
        if(startTime!=null){
        	sTime=new Date(startTime);
        }
        Date eTime=new Date();
        if(endTime!=null){
        	eTime=new Date(endTime);
        }
        log.info("sTime:{},eTime:{}",sTime,eTime);
        OrderCheckBillDTO orderCheckBill = this.businessOperationPlanFacade.getOrderCheckBill(merchantId,shopId, sTime, eTime);
        OrderCheckBillVO billVO = convertToVO(orderCheckBill,sTime,eTime,nextSettlePrint.getPrintSeq());

        return Result.SUCESS(billVO);
    }
   
    @RequestMapping(value = "/operation/getHandOverOrderBill",method = RequestMethod.GET)
    @ApiOperation(value = "获取员工当前交班清单",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<OrderCheckBillVO> getHandOverOrderBill(@ApiParam(name = "staffId", value = "员工ID") 
    	@RequestParam(value = "staffId",required = false) Long staffId){
        Long merchantId = ThreadLocalContext.getMerchantId();
        Long shopId = ThreadLocalContext.getShopId();
        if(staffId==null){
        	staffId = ThreadLocalContext.getStaffId();
        }
        CashierHandoverRecordDTO dto=cashierHandoverRecordReadFacade.findStaffLastRecord(staffId);
        if(dto==null){
        	 log.info("获取交接班时间失败 ,该员工开班收银记录不存在staffId:{}",staffId);
        	return Result.FAILURE("该员工未开启收银");
        }
        if(dto.getCashierStatus()==CashierStatusEnum.DISABLE.getCode()){
        	return Result.FAILURE("当前账户未开启收银");
        }
        Date endTime=new Date();
        
        log.info("sTime:{},eTime:{}",dto.getCashierEnableTime(),endTime);
        OrderCheckBillDTO orderCheckBill = this.businessOperationPlanFacade.getHandOverOrderBill(merchantId,shopId,staffId,
        		dto.getCashierEnableTime(), endTime);
        OrderCheckBillVO billVO = handOverToVO(staffId, merchantId, shopId, dto, endTime, orderCheckBill);

        return Result.SUCESS(billVO);
    }

    @RequestMapping(value = "/operation/getClearOrderBill",method = RequestMethod.GET)
    @ApiOperation(value = "结班清单",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<OrderCheckBillVO> getClearOrderBill(@ApiParam(name = "staffId", value = "员工ID") 
    	@RequestParam(value = "staffId",required = false) Long staffId){
        Long merchantId = ThreadLocalContext.getMerchantId();
        Long shopId = ThreadLocalContext.getShopId();
        if(staffId==null){
        	staffId = ThreadLocalContext.getStaffId();
        }
        CashierHandoverRecordDTO dto=cashierHandoverRecordReadFacade.findStaffLastRecord(staffId);
        if(dto==null){
        	 log.info("获取交接班时间失败 ,该员工开班收银记录不存在staffId:{}",staffId);
        	return Result.FAILURE("该员工未开启收银");
        }
        Date endTime=new Date();
        
        log.info("sTime:{},eTime:{}",dto.getCashierEnableTime(),endTime);
        OrderCheckBillDTO orderCheckBill = this.businessOperationPlanFacade.getHandOverOrderBill(merchantId,shopId,staffId,
        		dto.getCashierEnableTime(), endTime);
        OrderCheckBillVO billVO =handOverToVO(staffId,merchantId,shopId,dto,endTime,orderCheckBill);

        return Result.SUCESS(billVO);
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/operation/getCashierRepoerBill",method = RequestMethod.GET)
    @ApiOperation(value = "获取店铺收银员收银信息列表", notes = "获取店铺收银员收银信息列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PageVO<CashierReportVO>> getCashierRepoerBill(
            @ApiParam(name = "pageNum",value = "页码")@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
            @ApiParam(name = "pageSize",value = "每页条数")@RequestParam(value = "pageSize",defaultValue = "25")Integer pageSize){
        Long shopId = ThreadLocalContext.getShopId();
        
        PageParameter pageParameter = new PageParameter(pageNum,pageSize);
        PageInfo<CashierReportDTO> cashierReportDTOs = this.cashierReportReadFacade.selectFirstThirtyListByShopId(shopId, pageParameter);
        PageInfo pageInfo = cashierReportDTOs;
        List<CashierReportVO> cashierRepoerVOs = new ArrayList<>();
        if(cashierReportDTOs!=null&&cashierReportDTOs.getList() != null&&cashierReportDTOs.getList().size()>0){
        	cashierRepoerVOs = ObjectConvertUtil.mapList(cashierReportDTOs.getList(),CashierReportDTO.class,CashierReportVO.class);
        	pageInfo.setList(cashierRepoerVOs);
        }
        return Result.SUCESS(pageInfo);
    }
    
    

    @RequestMapping(value = "/operation/getCashierRepoerDetailBill",method = RequestMethod.GET)
    @ApiOperation(value = "获取收银交班清单详情",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<OrderCheckBillVO> getCashierRepoerDetailBill(@ApiParam(name = "cashierId", value = "收银ID") 
    	@RequestParam(value = "cashierId",required = true) Long cashierId){
        Long merchantId = ThreadLocalContext.getMerchantId();
        Long shopId = ThreadLocalContext.getShopId();
        
        CashierReportDTO dto=cashierReportReadFacade.selectByPrimaryKey(cashierId);
        if(dto==null){
        	 return Result.FAILURE("该收银信息不存在");
        }
        CashierHandoverRecordDTO cdto=cashierHandoverRecordReadFacade.findById(dto.getHandoverRecordId());
        if(cdto==null){
        	log.info("获取交接班时间失败 ,该员工收银记录不存在,收银ID:{}",cashierId);
        	return Result.FAILURE("获取交接班时间失败 ,该员工收银记录不存在");
        }
        log.info("sTime:{},eTime:{}",dto.getStartTime(), dto.getEndTime());
        OrderCheckBillDTO orderCheckBill = this.businessOperationPlanFacade.getHandOverOrderBill(merchantId,
        		shopId,dto.getStaffId(),dto.getStartTime(), dto.getEndTime());
        OrderCheckBillVO billVO = new OrderCheckBillVO();
        if(orderCheckBill != null){
            billVO.setTotalAmount(orderCheckBill.getTotalAmount());
            billVO.setTotalNum(orderCheckBill.getTotalNum());
            billVO.setNumOfGuests(orderCheckBill.getNumOfGuests());
            billVO.setAvgAmountOfGuests(orderCheckBill.getAvgAmountOfGuests());
            billVO.setAvgNumOfGuests(orderCheckBill.getAvgNumOfGuests());
            billVO.setStartTime(dto.getStartTime());
            billVO.setEndTime(dto.getEndTime());
            if(orderCheckBill.getRealPayReports() != null){
                billVO.setRealPayReports(ObjectConvertUtil.mapList(orderCheckBill.getRealPayReports(),PaymentReportDTO.class,PaymentReportVO.class));
            }
            if(orderCheckBill.getCouponPayReports() != null){
                billVO.setCouponPayReports(ObjectConvertUtil.mapList(orderCheckBill.getCouponPayReports(),PaymentReportDTO.class,PaymentReportVO.class));
            }
            if(orderCheckBill.getPayAndRefundReports() != null){
                billVO.setPayAndRefundReports(ObjectConvertUtil.mapList(orderCheckBill.getPayAndRefundReports(),PaymentReportDTO.class,PaymentReportVO.class));
            }
            if(orderCheckBill.getBenefitReports() != null){
                billVO.setBenefitReports(ObjectConvertUtil.mapList(orderCheckBill.getBenefitReports(),OrderBenefitReportDTO.class,OrderBenefitReportVO.class));
            }
            if(orderCheckBill.getOrderSumReports() != null){
                List<OrderSumReportVO> reportVOS = new ArrayList<>();
                orderCheckBill.getOrderSumReports().forEach(t ->{
                    OrderSumReportVO vo = ObjectConvertUtil.map(t,OrderSumReportVO.class);
                    vo.setOrderStatusEnum(OrderStatusEnum.getEnum(t.getOrderStatus()));
                    reportVOS.add(vo);
                });
                billVO.setOrderSumReports(reportVOS);
            }
            
            CashierVO cashier=new CashierVO();
            cashier.setStaffId(cdto.getStaffId());
            cashier.setStaffName(cdto.getStaffFullName());
            cashier.setStartTime(cdto.getCashierEnableTime());
            cashier.setZeroAmount(cdto.getSmallChangeAmount());
            cashier.setEndTime(cdto.getCashierDisableTime());
            cashier.setTotalAmount(cdto.getCashTotalAmount());
            
            PaymentReportQueryDTO queryDTO=new PaymentReportQueryDTO();
            queryDTO.setCashierId(cdto.getStaffId());
            queryDTO.setEndTime(cdto.getCashierDisableTime());
            queryDTO.setStartTime(cdto.getCashierEnableTime());
            queryDTO.setMerchantId(cdto.getMerchantId());
            queryDTO.setShopId(cdto.getShopId());
            queryDTO.setPaymentTypeCode(PaymentTypeEnum.PAYMENT_TYPE_CASH.code);
            PaymentReportStatisticDTO payDto=paymentReportFacade.getRealTimePaymentTypeStatistic(queryDTO,null);
            if(payDto!=null){
            	Long dif=payDto.getPayAmount()-payDto.getRefundAmount();
            	cashier.setCashReceivedAmount(dif);
            	dif=cdto.getCashTotalAmount()-cdto.getSmallChangeAmount()-dif;
                cashier.setDiffAmount(dif);
            }
            cashier.setDiffReason(cdto.getRemark());
            billVO.setCashier(cashier);
        }
        return Result.SUCESS(billVO);
    }
    
    @RequestMapping(value = "/operation/checkCashier",method = RequestMethod.GET)
    @ApiOperation(value = "核对收银信息",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<CashierDetailVO> checkCashier(@ApiParam(name = "totalAmount", value = "钱箱总额") 
    	@RequestParam(value = "totalAmount",required = true) Long totalAmount){
        Long staffId = ThreadLocalContext.getStaffId();
        CashierHandoverRecordDTO cdto=cashierHandoverRecordReadFacade.findStaffLastRecord(staffId);
        if(cdto==null){
        	log.info("获取交接班时间失败 ,该员工开班收银记录不存在staffId:{}",staffId);
        	return Result.FAILURE("获取交接开班时间失败");
        }
        CashierDetailVO vo=new CashierDetailVO();
        
        Date endTime=new Date();
        Long dif= 0L;
        PaymentReportQueryDTO queryDTO=new PaymentReportQueryDTO();
        queryDTO.setCashierId(staffId);
        queryDTO.setEndTime(endTime);
        queryDTO.setStartTime(cdto.getCashierEnableTime());
        queryDTO.setMerchantId(cdto.getMerchantId());
        queryDTO.setShopId(cdto.getShopId());
        queryDTO.setPaymentTypeCode(PaymentTypeEnum.PAYMENT_TYPE_CASH.code);
        PaymentReportStatisticDTO payDto=paymentReportFacade.getRealTimePaymentTypeStatistic(queryDTO,null);
        if(payDto!=null){
        	
        	dif=payDto.getPayAmount()-payDto.getRefundAmount();
        	vo.setAmount(dif);
        	
        	dif=totalAmount-cdto.getSmallChangeAmount()-dif;
        }
        vo.setTotalAmount(totalAmount);
        vo.setZeroAmount(cdto.getSmallChangeAmount());
        vo.setDiffAmount(dif);
        return Result.SUCESS(vo);
    }

    private OrderCheckBillVO handOverToVO(Long staffId, Long merchantId, Long shopId, CashierHandoverRecordDTO dto, Date endTime, OrderCheckBillDTO orderCheckBill) {
        OrderCheckBillVO billVO = new OrderCheckBillVO();
        if(orderCheckBill != null){
            billVO.setTotalAmount(orderCheckBill.getTotalAmount());
            billVO.setTotalNum(orderCheckBill.getTotalNum());
            billVO.setNumOfGuests(orderCheckBill.getNumOfGuests());
            billVO.setAvgAmountOfGuests(orderCheckBill.getAvgAmountOfGuests());
            billVO.setAvgNumOfGuests(orderCheckBill.getAvgNumOfGuests());
            billVO.setStartTime(dto.getCashierEnableTime());
            billVO.setEndTime(endTime);
            if(orderCheckBill.getRealPayReports() != null){
                billVO.setRealPayReports(ObjectConvertUtil.mapList(orderCheckBill.getRealPayReports(),PaymentReportDTO.class,PaymentReportVO.class));
            }
            if(orderCheckBill.getCouponPayReports() != null){
                billVO.setCouponPayReports(ObjectConvertUtil.mapList(orderCheckBill.getCouponPayReports(),PaymentReportDTO.class,PaymentReportVO.class));
            }
            if(orderCheckBill.getBenefitReports() != null){
                billVO.setBenefitReports(ObjectConvertUtil.mapList(orderCheckBill.getBenefitReports(),OrderBenefitReportDTO.class,OrderBenefitReportVO.class));
            }
            if(orderCheckBill.getPayAndRefundReports() != null){
                billVO.setPayAndRefundReports(ObjectConvertUtil.mapList(orderCheckBill.getPayAndRefundReports(),PaymentReportDTO.class,PaymentReportVO.class));
            }
            if(orderCheckBill.getOrderSumReports() != null){
                List<OrderSumReportVO> reportVOS = new ArrayList<>();
                orderCheckBill.getOrderSumReports().forEach(t ->{
                    OrderSumReportVO vo = ObjectConvertUtil.map(t,OrderSumReportVO.class);
                    vo.setOrderStatusEnum(OrderStatusEnum.getEnum(t.getOrderStatus()));
                    reportVOS.add(vo);
                });
                billVO.setOrderSumReports(reportVOS);
            }
            CashierVO cashier=new CashierVO();
            cashier.setStaffId(dto.getStaffId());
            cashier.setStaffName(dto.getStaffFullName());
            cashier.setZeroAmount(dto.getSmallChangeAmount());
            cashier.setStartTime(dto.getCashierEnableTime());
            cashier.setEndTime(endTime);

            PaymentReportQueryDTO queryDTO=new PaymentReportQueryDTO();
            queryDTO.setCashierId(staffId);
            queryDTO.setEndTime(endTime);
            queryDTO.setStartTime(dto.getCashierEnableTime());
            queryDTO.setMerchantId(merchantId);
            queryDTO.setShopId(shopId);
            queryDTO.setPaymentTypeCode(PaymentTypeEnum.PAYMENT_TYPE_CASH.code);
            PaymentReportStatisticDTO payDto=paymentReportFacade.getRealTimePaymentTypeStatistic(queryDTO,null);
            if(payDto!=null){
                Long dif=payDto.getPayAmount()-payDto.getRefundAmount();
                log.info("查看现金实收金额是否一致,dif:{},totalAmount:{}",dif,dto.getCashTotalAmount());
                cashier.setCashReceivedAmount(dif);
                cashier.setTotalAmount(dto.getCashTotalAmount());
                dif=dto.getCashTotalAmount()-dto.getSmallChangeAmount()-dif;
                cashier.setDiffAmount(dif);
            }
            if(dto.getCashierDisableTime()!=null){
                cashier.setEndTime(dto.getCashierDisableTime());
            }
            cashier.setDiffReason(dto.getRemark());
            billVO.setCashier(cashier);
        }
        return billVO;
    }
    @ApiOperation(value = "导出营业收入日报表excel",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/export/fullBusinessStatistics", method = RequestMethod.GET)
    public void exportShopGoodsSaleRankExcel(HttpServletRequest request, HttpServletResponse response,
    		@ApiParam(value = "商户ID", name = "merchantId", required = false)@RequestParam(name = "merchantId", required = false)Long merchantId,
    		@ApiParam(value = "门店ID", name = "shopId", required = true)@RequestParam(name = "shopId", required = true)Long shopId,
    		@ApiParam(name = "startTime",value = "开始时间")@RequestParam(value = "startTime")Date startTime,
            @ApiParam(name = "endTime",value = "结束时间")@RequestParam(value = "endTime")Date endTime) {
    	FreeMarkerExportExcelUtil util = new FreeMarkerExportExcelUtil(request, response, excelBuilPath);
    	Map<String, Object> dataMap = new HashMap<String, Object>();
    	try {
    		shopId = ThreadLocalContext.getShopId();
    		merchantId = ThreadLocalContext.getMerchantId();
    		BusinessFullStatisticsDTO fullBusinessStatistics = businessOperationPlanFacade.getFullBusinessStatistics(merchantId, shopId, startTime, endTime);
    		BusinessFullStatisticsVO statisticsVO = convertToVO(fullBusinessStatistics);
    		JSONArray rows = this.loadStatisticsProjectArray(statisticsVO);
    		dataMap.put("list", rows);
    		util.export("fullBusinessStatisticsTemplate.ftl", dataMap, "营业收入日报表.xls");
    	} catch (Exception e) {
    		log.error("导出营业收入日报表excel出错,merchantId:{},shopId:{},message={}", merchantId, shopId, e);
    	}
    }
    
    /**
     * 加载统计项目
     * @param name 名称
     * @param amount 金额
     * @param hasChild 是否有叶子
     * @param list 叶子列表
     * @return JSONObject
     * @author liaojw
     * @date 2018年8月8日 上午11:33:25
     */
    private JSONObject getStatisticsProject(String name, Object amount, Boolean hasChild, Boolean isAmount, String unit, List<?> list) {
    	JSONObject project = new JSONObject();
    	project.put("name", name);
		project.put("amount", amount);
		project.put("hasChild", hasChild);
		project.put("unit", unit);
		project.put("isAmount", isAmount);
		project.put("list", list);
		return project;
    }
    
    /**
     * 载入统计项目数据
     * @param statisticsVO
     * @return JSONArray
     * @author liaojw
     * @date 2018年8月8日 下午2:38:13
     */
    private JSONArray loadStatisticsProjectArray(BusinessFullStatisticsVO statisticsVO) {
    	if (statisticsVO == null) {
    		return new JSONArray();
    	}
    	JSONArray rows = new JSONArray();
		rows.add(this.getStatisticsProject("1.营业额", statisticsVO.getTotalAmount(), false, true, "元", null));
		rows.add(this.getStatisticsProject("2.营业订单数", statisticsVO.getTotalNum(), false, false, "笔", null));
		rows.add(this.getStatisticsProject("3.就餐人数", statisticsVO.getNumOfGuests(), false, false, "人", null));
		rows.add(this.getStatisticsProject("4.人均消费", statisticsVO.getAvgAmountOfGuests(), false, true, "元", null));
//		rows.add(this.getStatisticsProject("5.人均消费笔数", statisticsVO.getAvgNumOfGuests(), false, false, "笔", null));
		
		List<PaymentReportVO> realPayReports = statisticsVO.getRealPayReports();
		JSONArray realPayReportJSONArray = new JSONArray();
		if (!Collections.isEmpty(realPayReports)) {
			for (PaymentReportVO pr:realPayReports) {
				if (pr.getPaymentTypeCode() == null) continue;
    			PaymentTypeEnum paymentType = PaymentTypeEnum.get(pr.getPaymentTypeCode());
    			if (paymentType == null) {
    				continue;
    			}
    			realPayReportJSONArray.add(this.getStatisticsProject(paymentType.getMessage(), pr.getPayAmount(), false, true, "元", null));
    		}
		}
		rows.add(this.getStatisticsProject("5.实际收入", null, true,  true, null, realPayReportJSONArray));
		
		List<PaymentReportVO> couponPayReports = statisticsVO.getCouponPayReports();
		JSONArray couponPayReportJSONArray = new JSONArray();
		if (!Collections.isEmpty(couponPayReports)) {
			for (PaymentReportVO pr:couponPayReports) {
				if (pr.getPaymentTypeCode() == null) continue;
				PaymentTypeEnum paymentType = PaymentTypeEnum.get(pr.getPaymentTypeCode());
    			if (paymentType == null) {
    				continue;
    			}
				couponPayReportJSONArray.add(this.getStatisticsProject(paymentType.getMessage(), pr.getPayAmount(),  false,  true, "元", null));
			}
		}
		rows.add(this.getStatisticsProject("6.其他收入", null, true,  true, null, couponPayReportJSONArray));
		
		List<PaymentReportVO> payAndRefundReports = statisticsVO.getPayAndRefundReports();
		JSONArray payAndRefundReportJSONArray = new JSONArray();
		if (!Collections.isEmpty(payAndRefundReports)) {
			for (PaymentReportVO pr:payAndRefundReports) {
				if (pr.getPaymentTypeCode() == null) continue;
				PaymentTypeEnum paymentType = PaymentTypeEnum.get(pr.getPaymentTypeCode());
    			if (paymentType == null) {
    				continue;
    			}
    			payAndRefundReportJSONArray.add(this.getStatisticsProject(paymentType.getMessage(), pr.getRefundAmount(),  false,  true, "元", null));
			}
		}
		rows.add(this.getStatisticsProject("7.退款列表", null, true,  true, null, payAndRefundReportJSONArray));
		
		List<OrderBenefitReportVO> benefitReports = statisticsVO.getBenefitReports();
		JSONArray benefitReportJSONArray = new JSONArray();
		if (!Collections.isEmpty(benefitReports)) {
			for (OrderBenefitReportVO obr:benefitReports) {
				if (obr.getBenefitType() == null) continue;
				DiscountTypeNodeEnum discountType = DiscountTypeNodeEnum.getEnum(obr.getBenefitType());
				if (discountType == null) continue;
				benefitReportJSONArray.add(this.getStatisticsProject(discountType.getDescription(), obr.getTotalBenefitAmount(), false,  true, "元", null));
			}
		}
		rows.add(this.getStatisticsProject("8.优惠", null, true,  true, null, benefitReportJSONArray));
//		
//		List<OrderSumReportVO> orderSumReports = statisticsVO.getOrderSumReports();
//		JSONArray orderSumReportJSONArray = new JSONArray();
//		if (!Collections.isEmpty(orderSumReports)) {
//			for (OrderSumReportVO osr:orderSumReports) {
//				if (osr.getOrderStatusEnum() == null) continue;
//				orderSumReportJSONArray.add(this.getStatisticsProject(osr.getOrderStatusEnum().getDescription(), osr.getAmount(), false,  true, "元", null));
//			}
//		}
//		rows.add(this.getStatisticsProject("10.退款和取消订单", null, true,  true, null, orderSumReportJSONArray));
//		
		List<CategoryProduceReportVO> categoryProduceReports = statisticsVO.getCategoryProduceReportVOS();
		JSONArray categoryProduceReportJSONArray = new JSONArray();
		if (!Collections.isEmpty(categoryProduceReports)) {
			for (CategoryProduceReportVO category:categoryProduceReports) {
				categoryProduceReportJSONArray.add(this.getStatisticsProject(category.getCategoryName(), category.getTotalAmount(), false,  true, "元", null));
			}
		}
		rows.add(this.getStatisticsProject("9.菜品统计", null, true,  true, null, categoryProduceReportJSONArray));
		
		List<CashierPaymentReportVO> cashierPaymentReports = statisticsVO.getCashierPaymentReportVOS();
		JSONArray cashierPaymentReportJSONArray = new JSONArray();
		if (!Collections.isEmpty(cashierPaymentReports)) {
			for (CashierPaymentReportVO cashier:cashierPaymentReports) {
				Long cashierTotalAmount = 0L;//收银员金额汇总
				JSONArray cashierPaymentArray = new JSONArray();
				if (!Collections.isEmpty(cashier.getPaymentReportVOList())) {
					for (PaymentReportVO pr:cashier.getPaymentReportVOList()) {
						cashierTotalAmount += pr.getPayAmount();
						if (pr.getPaymentTypeCode() == null) continue;
	    				PaymentTypeEnum paymentType = PaymentTypeEnum.get(pr.getPaymentTypeCode());
	        			if (paymentType == null) {
	        				continue;
	        			}
						cashierPaymentArray.add(this.getStatisticsProject(paymentType.getMessage(), pr.getPayAmount(), false,  true, "元", null));
    				}
				}
				cashierPaymentReportJSONArray.add(this.getStatisticsProject(cashier.getCashierName(), cashierTotalAmount, true,  true, "元", cashierPaymentArray));
			}
		}
		rows.add(this.getStatisticsProject("10.收银员收款统计", null, true,  true, null, cashierPaymentReportJSONArray));
		
		MemberAndFlowReportVO memberAndFlowReports = statisticsVO.getMemberAndFlowReportVO();
		JSONArray memberAndFlowReportArray = new JSONArray();
		if (memberAndFlowReports != null) {
			memberAndFlowReportArray.add(this.getStatisticsProject("累计充值金额", memberAndFlowReports.getTotalRechargeAmount(), false,  true, "元", null));
			memberAndFlowReportArray.add(this.getStatisticsProject("充值笔数", memberAndFlowReports.getRechargeNum(), false,  false, "笔", null));
			memberAndFlowReportArray.add(this.getStatisticsProject("累计会员消费总额", memberAndFlowReports.getTotalCostRechargeAmount(), false,  true, "元", null));
    		memberAndFlowReportArray.add(this.getStatisticsProject("消费笔数", memberAndFlowReports.getOrderNum(), false,  false, "笔", null));
    		memberAndFlowReportArray.add(this.getStatisticsProject("新增会员数", memberAndFlowReports.getIncreaseMemberNum(), false,  false, "人", null));
    		memberAndFlowReportArray.add(this.getStatisticsProject("会员总数", memberAndFlowReports.getTotalMemberNum(), false,  false, "人", null));
		}
		rows.add(this.getStatisticsProject("11.会员统计", null, true,  true, null, memberAndFlowReportArray));
		return rows;
    }
}
