package com.lizikj.api.controller.shop;

import com.alibaba.fastjson.JSONObject;
import com.lizikj.api.Bootstrap;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.shop.ShopServiceTimeLeftVO;
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
@ActiveProfiles({"dev","redis.singleton"})//dev 环境，redis.singleton：redis集群与否
public class TestShopValueAddedController {


    private static long shopId = 1L;


    private static Logger logger = LoggerFactory.getLogger(TestShopValueAddedController.class);

    @Autowired
    private ShopValueAddedController shopValueAddedController;

    @Before
    public void init(){
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, shopId);

    }

    @Test
    public void testGetShopExpiredTime(){

        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, 1158L);


        Result<ShopServiceTimeLeftVO> shopServiceTimeLeftVO = shopValueAddedController.getShopExpiredTime();
        logger.info("shopServiceTimeLeftVO:"+JSONObject.toJSONStringWithDateFormat(shopServiceTimeLeftVO, "yyyy-MM-dd HH:mm:ss"));

    }


}
