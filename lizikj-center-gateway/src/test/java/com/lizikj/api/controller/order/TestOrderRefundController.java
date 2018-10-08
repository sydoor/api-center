package com.lizikj.api.controller.order;

import com.lizikj.api.Bootstrap;
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
public class TestOrderRefundController {

    private static long shopId = 1L;


    private static Logger logger = LoggerFactory.getLogger(TestOrderRefundController.class);

    @Autowired
    private OrderRefundController  orderRefundController;


    @Before
    public void init(){
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, shopId);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LZ_DID, "N9NL10093833");
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_MERCHANT_MEMBER_ID, 34L);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_MEMBER_ID, 20L);


    }

    /**
     * 测试POS新增订单
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/9/6 12:06
     */
    @Test
    public void testAaddOrder(){
        orderRefundController.refundOrder(null);
        logger.info("addOrder:{}", "");
    }





}
