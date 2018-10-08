package com.lizikj.api.controller.order;

import com.lizikj.api.Bootstrap;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.order.OrderPayResultVO;
import com.lizikj.api.vo.order.param.OrderPayParamVO;
import com.lizikj.api.vo.order.param.PayParamVO;
import com.lizikj.api.vo.order.param.QuickBuyParamVO;
import com.lizikj.common.enums.PaymentSceneEnum;
import com.lizikj.common.enums.PaymentTypeEnum;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.order.enums.DiscountTypeNodeEnum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoufe
 * @date 2017/12/7 17:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Bootstrap.class)
@ActiveProfiles("dev")
public class TestOrderPayController {

    private static final Logger logger = LoggerFactory.getLogger(TestOrderPayController.class);
    public static final long PARTNER_MEMBER_ID = 1L;

    @Autowired
    private OrderPayController orderPayController;

    @Test
    public void testQuickBuy(){

        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, 108L);

        QuickBuyParamVO quickBuyParamVO = new QuickBuyParamVO();
        quickBuyParamVO.setAmount(100L);
        quickBuyParamVO.setAuthCode("124365609653");
        quickBuyParamVO.setBizType((byte)2);
        quickBuyParamVO.setData("1");
        quickBuyParamVO.setMerchantId(PARTNER_MEMBER_ID);
        quickBuyParamVO.setPaymentChannel((byte)3);
        quickBuyParamVO.setPaymentScene((byte)4);
        quickBuyParamVO.setPaymentType((byte)2);
        Result<OrderPayResultVO> orderPayResultVOResult = orderPayController.quickBuy(quickBuyParamVO);

        if (logger.isInfoEnabled()) {
            logger.info("testQuickBuy：{}", orderPayResultVOResult);
        }
    }

    @Test
    public void testPayOrder(){

        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, 108L);
        OrderPayParamVO orderPayParamVO = new OrderPayParamVO();
        orderPayParamVO.setDiscountSelected(DiscountTypeNodeEnum.FULL_CUT);
        orderPayParamVO.setOrderNo("D2017121317470507687303940");

        List<PayParamVO> payParamList = new ArrayList<>();
        PayParamVO payParamVO = new PayParamVO();
        payParamVO.setPaymentScene(PaymentSceneEnum.H5_ONLINE_PAY.getCode());
        payParamVO.setPaymentType(PaymentTypeEnum.PAYMENT_TYPE_WECHAT.getCode());
        payParamList.add(payParamVO);
        orderPayParamVO.setPayParamList(payParamList);

        Result<OrderPayResultVO> orderPayResultVOResult = orderPayController.payOrder(orderPayParamVO);

        if (logger.isInfoEnabled()) {
            logger.info("testQuickBuy：{}", orderPayResultVOResult);
        }

    }

    @Test
    public void testFullCutPayOrder(){

        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, 108L);
        OrderPayParamVO orderPayParamVO = new OrderPayParamVO();
        orderPayParamVO.setDiscountSelected(DiscountTypeNodeEnum.FULL_CUT);
        orderPayParamVO.setOrderNo("D2017122010003163989596808");

        List<PayParamVO> payParamList = new ArrayList<>();
        PayParamVO payParamVO = new PayParamVO();
        payParamVO.setPaymentScene(PaymentSceneEnum.H5_ONLINE_PAY.getCode());
        payParamVO.setPaymentType(PaymentTypeEnum.PAYMENT_TYPE_WECHAT.getCode());
        payParamList.add(payParamVO);
        orderPayParamVO.setPayParamList(payParamList);

        Result<OrderPayResultVO> orderPayResultVOResult = orderPayController.payOrder(orderPayParamVO);

        if (logger.isInfoEnabled()) {
            logger.info("testQuickBuy：{}", orderPayResultVOResult);
        }

    }


    @Test
    public void testPartnerMemberAllMoneyPayOrder(){
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, 125);


        OrderPayParamVO orderPayParamVO = new OrderPayParamVO();
        orderPayParamVO.setDiscountSelected(DiscountTypeNodeEnum.PARTNER_MEMBER_LEVEL);
        orderPayParamVO.setOrderNo("D2017122115282549562231961");
        orderPayParamVO.setPartnerMemberId(PARTNER_MEMBER_ID);

        List<PayParamVO> payParamList = new ArrayList<>();
        PayParamVO payParamVO = new PayParamVO();
        payParamVO.setPaymentScene(PaymentSceneEnum.DEFAULT.getCode());
        payParamVO.setPaymentType(PaymentTypeEnum.PAYMENT_TYPE_PARTNER_MEMBER.getCode());
        payParamVO.setBizData(String.valueOf(PARTNER_MEMBER_ID));
        payParamList.add(payParamVO);
        orderPayParamVO.setPayParamList(payParamList);

        Result<OrderPayResultVO> orderPayResultVOResult = orderPayController.payOrder(orderPayParamVO);

        if (logger.isInfoEnabled()) {
            logger.info("testPartnerMemberAllMoneyPayOrder：{}", orderPayResultVOResult);
        }
    }

}
