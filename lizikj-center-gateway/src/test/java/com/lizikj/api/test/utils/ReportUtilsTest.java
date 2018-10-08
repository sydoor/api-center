package com.lizikj.api.test.utils;

import com.lizikj.api.utils.MerchantImportUtils;
import com.lizikj.api.utils.ReportUtil;
import com.lizikj.api.vo.member.param.DateParam;
import com.lizikj.api.vo.reporting.PieChartVO;
import com.lizikj.api.vo.reporting.TrendsChartVO;
import com.lizikj.api.vo.reporting.TrendsMultiChartVO;
import com.lizikj.common.util.DateUtils;
import com.lizikj.login.util.JWTUtils;
import com.lizikj.reporting.dto.ShopOrderReportDTO;
import com.lizikj.reporting.enums.ReportingTypeEnum;
import com.sun.org.apache.regexp.internal.RE;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by liangxiaolin on 2017/8/22.
 */
public class ReportUtilsTest {
    private final static Logger log = LoggerFactory.getLogger(ReportUtilsTest.class);
    private final static List<ShopOrderReportDTO> shopOrderReportDTOS = new ArrayList<>();
    static {
        for(int i=0;i < 10;i++){
            ShopOrderReportDTO shopOrderReportDTO = new ShopOrderReportDTO();
            shopOrderReportDTO.setShopName("test");
            shopOrderReportDTO.setReceivedAmount(Long.valueOf(9999+i));
            shopOrderReportDTO.setValidNum(88+i);
            shopOrderReportDTO.setReportDate(DateUtils.addDays(new Date(),-i));
            shopOrderReportDTOS.add(shopOrderReportDTO);
        }
    }

    @Test
    public void testFormat() throws Exception{
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        log.info("formt {}",decimalFormat.format(80));
        String paymentNames = "1311231s";
        log.info("sub {}",paymentNames.substring(0,paymentNames.length()-1));
    }

    @Test
    public void testToken() throws Exception{
        Map<String, Object> claims = new HashMap();
        claims.put("ui", 13);
        claims.put("ls", 2);
        claims.put("ut", (byte)2);
        claims.put("si", 12);
        claims.put("mi", 7);
        claims.put("spi", 7);
        claims.put("agi", 4);

        claims.put("env", "dev");
        Date loginTime = new Date();
        String token = JWTUtils.createJWT(claims, loginTime);

        log.info("token: {}",token);
    }

    @Test
    public void testBuildTrendsChart() throws Exception{
        TrendsChartVO trendsChartVO = ReportUtil.buildTrendsChart(shopOrderReportDTOS, "getShopName", "getReportDate", "getReceivedAmount", ReportUtil.ValueTypeEnum.AMOUNT);
        trendsChartVO.getHorizontalAxis().forEach(v -> log.info("test {}",v));
        trendsChartVO.getVerticalAxis().forEach(v -> log.info("test {}",v));
        TrendsMultiChartVO multiChartVO = ReportUtil.buildMultiChart(shopOrderReportDTOS, "getShopName", "getReportDate", "getReceivedAmount", ReportUtil.ValueTypeEnum.AMOUNT);
        multiChartVO.getVerticalAxis().forEach(t -> log.info("test {} {}",t.getOwner(),t.getAxisValues()));

    }

    @Test
    public void testMemberImport() throws Exception{
        MerchantImportUtils.readFromExcel("D:\\workspace\\temp\\会员批量导入.xls");
    }

    @Test
    public void testStringFormat() throws Exception{
        log.info("format {}",String.format("%02d:00",6));
    }

    @Test
    public void testBuildPieChart() throws Exception{
        PieChartVO pieChartVO = ReportUtil.buildPieChart(shopOrderReportDTOS, "getShopName", "getReportDate", "getReceivedAmount", ReportUtil.ValueTypeEnum.AMOUNT);
        pieChartVO.getData().forEach(t -> log.info("tstttt name={} value={}",t.getName(),t.getValue()));
    }

    @Test
    public void testToYuan() throws Exception{
        System.out.println(String.format("toYuan = %s",ReportUtil.toYuan(99999999)));
    }
    @Test
    public void getDateParam() throws Exception{
        DateParam dateParam = ReportUtil.getDateParam(8400);
        System.out.println(String.format("startDate=%s  endDate=%s",dateParam.getStartDate(),dateParam.getEndDate()));
    }

    @Test
    public void testGetDateParam() throws Exception{
        DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.NEARLY_THIRTY_DAY,null);
        System.out.println(String.format("startDate=%s  endDate=%s",dateParam.getStartDate(),dateParam.getEndDate()));
    }

    @Test
    public void testReportUtils() throws Exception{
        DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(8500), null);
        System.out.println(String.format("startDate=%s  endDate=%s",dateParam.getStartDate(),dateParam.getEndDate()));
        if(8500 == ReportingTypeEnum.CURRENT_DAY.getCode()){
            System.out.println("testtettjjsjf");
        }
    }
}
