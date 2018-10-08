package com.lizikj.api.controller.shop;

import com.alibaba.fastjson.JSONObject;
import com.lizikj.api.Bootstrap;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.shop.param.ShopMerchandiseFlavorParamVO;
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
public class TestShopAuthCodeController {

    private static long shopId = 1L;


    private static Logger logger = LoggerFactory.getLogger(TestShopAuthCodeController.class);

    @Autowired
    private ShopAuthCodeController shopAuthCodeController;
    private static Long shopAreaId = 1L;
    private static Long areaDeskId = 1L;

    @Before
    public void init(){
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, shopId);

    }

    /**
     * 查询新增并获取
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/9/10 16:02
     */
    @Test
    public  void testGetAndCreateAuthCode(){

        for (int i = 0; i < 20000; i++) {
            Long value = new Long(i + 1);
            ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, value);
            ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_MERCHANT_ID, value);
            ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_STAFF_ID, value);

            Result<String> andCreateAuthCode = shopAuthCodeController.getAndCreateAuthCode();
            logger.info("getAndCreateAuthCode:{}"+andCreateAuthCode);
        }

    }

    @Test
    public void testVerifyAuthCode(){
        String authCode = "904240";
        Long value = 1L;
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, value);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_MERCHANT_ID, value);
        Result<Boolean> booleanResult = shopAuthCodeController.verifyAuthCode(authCode, null);
        logger.info("verifyAuthCode:{}"+JSONObject.toJSONString(booleanResult));

    }


}
