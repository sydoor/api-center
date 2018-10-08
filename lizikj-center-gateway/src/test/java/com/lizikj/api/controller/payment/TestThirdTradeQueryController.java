package com.lizikj.api.controller.payment;

import com.alibaba.fastjson.JSONObject;
import com.lizikj.api.Bootstrap;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.payment.PaymentGatewayCallbackFlowDetailVO;
import com.lizikj.api.vo.payment.ThirdQueryCommonVO;
import com.lizikj.common.enums.PaymentChannelEnum;
import com.lizikj.common.enums.PaymentSceneEnum;
import com.lizikj.common.enums.PaymentTypeEnum;
import com.lizikj.common.util.ThreadLocalContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhoufe
 * @date 2017/8/12 15:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=Bootstrap.class)//, properties={"spring.profiles.active=dev"}
@ActiveProfiles({"dev"})//dev 环境，redis.singleton：redis集群与否
public class TestThirdTradeQueryController {

    private static long shopId = 1L;


    private static Logger logger = LoggerFactory.getLogger(TestThirdTradeQueryController.class);

    @Autowired
    private ThirdTradeQueryController thirdTradeQueryController;

    @Before
    public void init(){
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, shopId);
    }


    @Test
    public void testQueryCardOrder(){
        ThirdQueryCommonVO thirdQueryCommonVO = new ThirdQueryCommonVO();
        thirdQueryCommonVO.setChannelCode(PaymentChannelEnum.PAY_CHANNEL_GUOTONG.getCode());
        thirdQueryCommonVO.setPaymentTypeCode(PaymentTypeEnum.PAYMENT_TYPE_SWIPECARD.getCode());
        thirdQueryCommonVO.setSceneCode(PaymentSceneEnum.SWIPECARD_CARD.getCode());
        thirdQueryCommonVO.setInnerTradeNo("SP2018052114262542389178803681");
        thirdQueryCommonVO.setMerchantId(1147L);//订单滴滴
        thirdQueryCommonVO.setShopId(1125L);
        Result<String> stringResult = thirdTradeQueryController.queryCardOrder(thirdQueryCommonVO);

        if (logger.isInfoEnabled()) {
            logger.info("testQueryCardOrder:{}", JSONObject.toJSONString(stringResult));
        }
    }

    @Test
    public void testQueryCallBack(){
        ThirdQueryCommonVO thirdQueryCommonVO = new ThirdQueryCommonVO();
        thirdQueryCommonVO.setInnerTradeNo("P2017111022565529728784067811");
        Result<PaymentGatewayCallbackFlowDetailVO> result = thirdTradeQueryController.queryCallBack(thirdQueryCommonVO);
        if (logger.isInfoEnabled()) {
            logger.info("result:{}", JSONObject.toJSONString(result));
        }
    }

}
