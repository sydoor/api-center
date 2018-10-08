package com.lizikj.api.controller.reporting;

import com.alibaba.dubbo.common.json.JSON;
import com.lizikj.api.Bootstrap;
import com.lizikj.api.enums.ReportTargetEnum;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.reporting.MerchandiseReportVO;
import com.lizikj.api.vo.reporting.OrderBenefitReportVO;
import com.lizikj.api.vo.reporting.OrderCheckBillVO;
import com.lizikj.api.vo.reporting.OrderSumReportVO;
import com.lizikj.api.vo.reporting.PaymentReportVO;
import com.lizikj.api.vo.reporting.param.ReportCommonParam;
import com.lizikj.common.util.DateUtils;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.merchant.facade.ICashierHandoverRecordReadFacade;
import com.lizikj.order.enums.OrderStatusEnum;
import com.lizikj.reporting.dto.BusinessSettlePlanDTO;
import com.lizikj.reporting.dto.OrderBenefitReportDTO;
import com.lizikj.reporting.dto.OrderCheckBillDTO;
import com.lizikj.reporting.dto.PaymentReportDTO;
import com.lizikj.reporting.enums.ReportingTypeEnum;
import com.lizikj.reporting.facade.IBusinessOperationPlanFacade;
import com.lizikj.reporting.facade.ICashierReportReadFacade;
import com.lizikj.reporting.facade.ICaterOrderReportFacade;
import com.lizikj.reporting.facade.IOrderReportFacade;
import com.lizikj.reporting.facade.IPaymentReportFacade;
import com.lizikj.shop.api.facade.IShopAuthCodeReadFacade;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Bootstrap.class)
@ActiveProfiles("dev")
public class MerchandiseReportControllerTest {
    private final static Logger log = LoggerFactory.getLogger(MerchandiseReportControllerTest.class);
    @Autowired
    MerchandiseReportController merchandiseReportController;
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
    
    @Test
    public void queryMerchandiseReport() throws Exception {
        ReportCommonParam reportCommonParam = new ReportCommonParam();
        reportCommonParam.setTarget(ReportTargetEnum.MERCHANT.getCode());
        reportCommonParam.setReportType(ReportingTypeEnum.NEARLY_THIRTY_DAY.getCode());
        Result<List<MerchandiseReportVO>> listResult = this.merchandiseReportController.queryMerchandiseReport(reportCommonParam);
        listResult.getData().forEach(t -> log.info("test {}",t.toString()));
    }
    
    
    @Test
    public void getBusinessBill(){
    	Long merchantId = 147L;
        Long shopId = 125L;
        String startTime="2018-02-02 11:27:00";
        String endTime="2018-02-02 15:27:00";
        BusinessSettlePlanDTO nextSettlePrint = businessOperationPlanFacade.getNextSettlePrint(merchantId, shopId, null,1);
        Date sTime=DateUtils.getStartOfDay(new Date());
        if(!StringUtils.isBlank(startTime)){
        	sTime=DateUtils.parse(startTime, DateUtils.FULL_BAR_PATTERN);
        }
        Date eTime=new Date();
        if(!StringUtils.isBlank(endTime)){
        	eTime=DateUtils.parse(endTime, DateUtils.FULL_BAR_PATTERN);
        }
        
        OrderCheckBillDTO orderCheckBill = this.businessOperationPlanFacade.getOrderCheckBill(merchantId,shopId, sTime, eTime);
        OrderCheckBillVO billVO = new OrderCheckBillVO();
        if(orderCheckBill != null){
            billVO.setTotalAmount(orderCheckBill.getTotalAmount());
            billVO.setTotalNum(orderCheckBill.getTotalNum());
            billVO.setNumOfGuests(orderCheckBill.getNumOfGuests());
            billVO.setAvgAmountOfGuests(orderCheckBill.getAvgAmountOfGuests());
            billVO.setAvgNumOfGuests(orderCheckBill.getAvgNumOfGuests());
            billVO.setPrintSeq(nextSettlePrint.getPrintSeq());
            billVO.setEndTime(eTime);
            billVO.setStartTime(sTime);
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
        }
        System.out.println(com.alibaba.fastjson.JSON.toJSON(billVO));
        System.out.println(billVO.toString());
    }

}