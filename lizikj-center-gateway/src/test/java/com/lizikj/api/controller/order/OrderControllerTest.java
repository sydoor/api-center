package com.lizikj.api.controller.order;

import com.alibaba.fastjson.JSONObject;
import com.lizikj.api.Bootstrap;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.order.PendingOrderInfoVO;
import com.lizikj.api.vo.reporting.LmwOrderStatisticsSummaryVO;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author zhoufe
 * @date 2018/8/31 16:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Bootstrap.class)
@ActiveProfiles({"dev"})
public class OrderControllerTest {

    @Autowired
    private OrderController orderController;

    @Test
    public void getCirculationSummary() {

        LmwOrderStatisticsSummaryVO summaryParamVO = new LmwOrderStatisticsSummaryVO();
        summaryParamVO.setShopId(125L);
        DateTime datetime = new DateTime();
        Date start = datetime.plusDays(-60).toDate();
        Date end = datetime.toDate();
        summaryParamVO.setStartCreateTime(start);
        summaryParamVO.setEndCreateTime(end);
        Result<LmwOrderStatisticsSummaryVO> circulationSummary = orderController.getCirculationSummary(summaryParamVO);
        System.out.println("circulationSummary=" + JSONObject.toJSONString(circulationSummary));
    }

    @Test
    public void findPendingOrderById() {

        Result<PendingOrderInfoVO> pendingOrderInfoVOResult = orderController.findPendingOrderById(1L);
        System.out.println("pendingOrderInfoVOResult=" + JSONObject.toJSONString(pendingOrderInfoVOResult));
    }
}