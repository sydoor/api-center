package com.lizikj.api.utils;

import com.lizikj.api.vo.reporting.BusinessReportExportVO;
import com.lizikj.api.vo.reporting.OrderBenefitExportVO;
import com.lizikj.api.vo.reporting.OrderSourceReportExportVO;
import com.lizikj.api.vo.reporting.PaymentStatisticExportVO;
import com.lizikj.common.enums.PaymentTypeEnum;
import com.lizikj.common.util.DateUtils;
import com.lizikj.order.enums.DiscountTypeNodeEnum;
import com.lizikj.reporting.dto.BusinessReportStatisticDTO;
import com.lizikj.reporting.dto.OrderBenefitReportDTO;
import com.lizikj.reporting.dto.OrderSourceReportDTO;
import com.lizikj.reporting.dto.PaymentReportDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * @author lxl
 * @Date 2018/1/10 17:01
 */
public class ReportExportUtil {
    private final static Logger log = LoggerFactory.getLogger(ReportExportUtil.class);
    private final static DecimalFormat decimalFormat = new DecimalFormat("#.##");


    public static List<OrderBenefitExportVO> buildOrderBenefitExportVOs(Date startDate, Date endDate, List<OrderBenefitReportDTO> dtos){
        Map<String,OrderBenefitExportVO> voMap = new HashMap<>();
        String tempDateStr;
        for(Date tempDate=startDate;!tempDate.after(endDate);){
            tempDateStr = DateUtils.format(tempDate,DateUtils.FULL_SMALL_PATTERN);
            OrderBenefitExportVO exportVO = new OrderBenefitExportVO();
            exportVO.setReportTime(DateUtils.format(tempDate,DateUtils.DATE_PATTERN));
            voMap.put(tempDateStr,exportVO);
            tempDate = DateUtils.addDays(tempDate,1);
        }

        if(dtos != null){
            dtos.forEach(dto -> {
                OrderBenefitExportVO vo = voMap.get(DateUtils.format(dto.getReportDate(),DateUtils.FULL_SMALL_PATTERN));
                if(vo != null && dto.getBenefitType() != null){
                    Double amount = Double.valueOf(decimalFormat.format((double)dto.getTotalBenefitAmount()/100));
                    Integer nums = dto.getTotalBenefitNum();
                    DiscountTypeNodeEnum nodeEnum = DiscountTypeNodeEnum.getEnum(dto.getBenefitType().intValue());
                    switch (nodeEnum){
                        case FULL_CUT:
                            vo.setFullReduceAmount(vo.getFullReduceAmount() + amount);
                            vo.setFullReduceNums(vo.getFullReduceNums() + nums);
                            break;
                        case MEMBER_LEVEL:
                        case MEMBER_PRICE:
                            vo.setMemberAmount(vo.getMemberAmount() + amount);
                            vo.setMemberNums(vo.getMemberNums() + nums);
                            break;
                        case ZERO:
                            vo.setCutAmount(vo.getCutAmount() + amount);
                            vo.setCutNums(vo.getCutNums() + nums);
                            break;
                        case SINGLE_BARGAIN:
                        case FULL_BARGAIN:
                            vo.setBargainAmount(vo.getBargainAmount() + amount);
                            vo.setBargainNums(vo.getBargainNums() + nums);
                            break;
                        case TIME_DISCOUNT:
                            vo.setTimingAmount(vo.getTimingAmount() + amount);
                            vo.setTimingNums(vo.getTimingNums() + nums);
                            break;
                    }
                }
            });
        }

        List<OrderBenefitExportVO> unSorted = new ArrayList<>(voMap.values());

        return unSorted.stream().sorted(Comparator.comparing(OrderBenefitExportVO::getReportTime)).collect(toList());
    }

    public static OrderBenefitExportVO sumOrderBenefits(List<OrderBenefitExportVO> vos){
        OrderBenefitExportVO total = new OrderBenefitExportVO();
        if(vos != null){
            vos.forEach(vo -> {
                total.add(vo);
            });
        }

        return total;
    }

    public static List<PaymentStatisticExportVO> buildPaymentExportVOs(Date startDate, Date endDate, List<PaymentReportDTO> dtos){
        Map<String,PaymentStatisticExportVO> voMap = new HashMap<>();
        String tempDateStr;
        for(Date tempDate=startDate;!tempDate.after(endDate);){
            tempDateStr = DateUtils.format(tempDate,DateUtils.FULL_SMALL_PATTERN);
            PaymentStatisticExportVO exportVO = new PaymentStatisticExportVO();
            exportVO.setReportTime(DateUtils.format(tempDate,DateUtils.DATE_PATTERN));
            voMap.put(tempDateStr,exportVO);
            tempDate = DateUtils.addDays(tempDate,1);
        }

        if(dtos != null){
            dtos.forEach(dto -> {
                PaymentStatisticExportVO vo = voMap.get(dto.getReportTime());
                if(vo != null && dto.getPaymentTypeCode() != null){
                    PaymentTypeEnum typeEnum = PaymentTypeEnum.get(dto.getPaymentTypeCode());
                    Double amount = Double.valueOf(decimalFormat.format((double)dto.getPayAmount()/100));
                    Integer nums = dto.getPayNums();
                    Double refundAmount = Double.valueOf(decimalFormat.format((double)dto.getRefundAmount()/100));
                    Integer refundNums = dto.getRefundNums();

                    switch (typeEnum){
                        case PAYMENT_TYPE_YINLIANQIANBAO:
                            vo.setUnionPayAmount(amount + vo.getUnionPayAmount());
                            vo.setUnionPayNums(nums + vo.getUnionPayNums());
                            break;
                        case PAYMENT_TYPE_SWIPECARD:
                            vo.setCardAmount(amount + vo.getCardAmount());
                            vo.setCardNums(nums + vo.getCardNums());
                            break;
                        case PAYMENT_TYPE_WECHAT:
                            vo.setWebchatAmount(amount + vo.getWebchatAmount());
                            vo.setWebchatNums(nums + vo.getWebchatNums());
                            break;
                        case PAYMENT_TYPE_ALIPAY:
                            vo.setAliAmount(amount + vo.getAliAmount());
                            vo.setAliNums(nums + vo.getAliNums());
                            break;
                        case PAYMENT_TYPE_CASH:
                            vo.setCashAmount(amount + vo.getCashAmount());
                            vo.setCardNums(nums + vo.getCardNums());
                            break;
                        case PAYMENT_TYPE_PARTNER_MEMBER:
                            vo.setTenderAmount(amount + vo.getTenderAmount());
                            vo.setTenderNums(nums + vo.getTenderNums());
                            break;
                        case PAYMENT_TYPE_REPLY_COUPON:
                            break;
                        case PAYMENT_TYPE_MEI_TUAN_COUPON:
                            vo.setMeituanAmount(amount + vo.getMeituanAmount());
                            vo.setMeituanNums(nums + vo.getMeituanNums());
                            break;
                        case PAYMENT_TYPE_MEMBER:
                            vo.setMemberPayAmount(amount + vo.getMemberPayAmount());
                            vo.setMemberPayNums(nums + vo.getMemberPayNums());
                            break;
                    }
                    vo.setRefundAmount(refundAmount + vo.getRefundAmount());
                    vo.setRefundNum(refundNums + vo.getRefundNum());
                }
            });
        }

        List<PaymentStatisticExportVO> unSorted = new ArrayList<>(voMap.values());

        return unSorted.stream().sorted(Comparator.comparing(PaymentStatisticExportVO::getReportTime)).collect(toList());
    }


    public static PaymentStatisticExportVO sumPaymentExportVOs(List<PaymentStatisticExportVO> vos){
        PaymentStatisticExportVO totalExportVO = new PaymentStatisticExportVO();
        if(vos != null){
            vos.forEach(vo ->{
                totalExportVO.add(vo);
            });
        }

        return totalExportVO;
    }

    public static List<OrderSourceReportExportVO> buildOrderSourceExportVOs(Date startDate, Date endDate, List<OrderSourceReportDTO> dtos){
        Map<String,OrderSourceReportExportVO> voMap = new HashMap<>();
        String tempDateStr;
        for(Date tempDate=startDate;!tempDate.after(endDate);){
            tempDateStr = DateUtils.format(tempDate,DateUtils.FULL_SMALL_PATTERN);
            OrderSourceReportExportVO exportVO = new OrderSourceReportExportVO();
            exportVO.setReportDateStr(DateUtils.format(tempDate,DateUtils.DATE_PATTERN));
            voMap.put(tempDateStr,exportVO);
            tempDate = DateUtils.addDays(tempDate,1);
        }

        if(dtos != null){
            dtos.forEach(dto ->{
                OrderSourceReportExportVO vo = voMap.get(dto.getReportDateStr());
                if(vo != null){
                    switch (dto.getOrderSourceReportingEnum()){
                        case ELEME:
                            vo.setElemeAmount(Double.valueOf(decimalFormat.format((double)dto.getReceivedAmount()/100)));
                            vo.setElemeNum(dto.getValidNum());
                            break;
                        case MEITUAN:
                            vo.setMeituanAmount(Double.valueOf(decimalFormat.format((double)dto.getReceivedAmount()/100)));
                            vo.setMeituanNum(dto.getValidNum());
                            break;
                        case EAT_HERE:
                            vo.setHereAmount(Double.valueOf(decimalFormat.format((double)dto.getReceivedAmount()/100)));
                            vo.setHereNum(dto.getValidNum());
                            break;
                    }
                }
            });
        }

        return new ArrayList<>(voMap.values()).stream().sorted(Comparator.comparing(OrderSourceReportExportVO::getReportDateStr)).collect(toList());
    }

    public static OrderSourceReportExportVO sumOrderSourceReports(List<OrderSourceReportExportVO> vos){
        OrderSourceReportExportVO totalVO = new OrderSourceReportExportVO();
        if(vos != null){
            vos.forEach(vo ->{
                totalVO.add(vo);
            });
        }

        return totalVO;
    }

    public static List<BusinessReportExportVO> buildBusinessExportVOs(Date startDate,Date endDate,List<BusinessReportStatisticDTO> dtos){
        Map<String,BusinessReportExportVO> voMap = new HashMap<>();
        String tempDateStr;
        for(Date tempDate=startDate;!tempDate.after(endDate);){
            tempDateStr = DateUtils.format(tempDate,DateUtils.FULL_SMALL_PATTERN);
            BusinessReportExportVO exportVO = new BusinessReportExportVO();
            exportVO.setReportDate(tempDate);
            exportVO.setReportDateStr(tempDateStr);
            voMap.put(tempDateStr,exportVO);
            tempDate = DateUtils.addDays(tempDate,1);
        }

        if(dtos != null){
            dtos.forEach(dto ->{
                if (dto.getReportTime() != null) {
                    BusinessReportExportVO vo = new BusinessReportExportVO();
                    vo.setTotalAmount(Double.valueOf(decimalFormat.format(dto.getTotalAmount()/100)));
                    vo.setReceivedAmount(Double.valueOf(decimalFormat.format(dto.getReceivedAmount()/100)));
                    vo.setValidNum(dto.getValidNum());
                    vo.setReportDateStr(dto.getReportTime());
                    vo.setGoalAmount(Double.valueOf(decimalFormat.format(dto.getTotalAmount()/100)));
                    voMap.put(dto.getReportTime(),vo);
                }
            });
        }

        return new ArrayList<>(voMap.values()).stream().sorted(Comparator.comparing(BusinessReportExportVO::getReportDateStr)).collect(toList());
    }

    public static BusinessReportExportVO sumBusinessExportVOs(List<BusinessReportStatisticDTO> dtos){
        BusinessReportExportVO totalExportVO = new BusinessReportExportVO();
        if(dtos != null){
            dtos.forEach(dto ->{
                totalExportVO.setValidNum(dto.getValidNum() + totalExportVO.getValidNum());
                totalExportVO.setReceivedAmount(dto.getReceivedAmount() + totalExportVO.getReceivedAmount());
                totalExportVO.setTotalAmount(dto.getTotalAmount() + totalExportVO.getTotalAmount());
            });
        }

        return totalExportVO;
    }
}
