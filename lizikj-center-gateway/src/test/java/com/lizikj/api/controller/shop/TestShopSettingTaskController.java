package com.lizikj.api.controller.shop;

import com.alibaba.fastjson.JSONObject;
import com.lizikj.api.Bootstrap;
import com.lizikj.api.vo.Result;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.shop.api.enums.ShopSettingTaskTypeEnum;
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
public class TestShopSettingTaskController {

    private static long shopId = 1L;
    private static long merchantId = 1L;
    private static long staffId = 1L;


    private static Logger logger = LoggerFactory.getLogger(TestShopSettingTaskController.class);

    @Autowired
    private ShopSettingTaskController shopSettingTaskController;


    @Before
    public void init(){
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, shopId);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_MERCHANT_ID, merchantId);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_STAFF_ID, staffId);


    }

    /**
     * 测试创建自动关闭任务
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/9/10 16:02
     */
    @Test
    public  void testCreateShopSettingTask(){

        ShopSettingTaskTypeEnum taskType = ShopSettingTaskTypeEnum.SHOP_SELF;
        Long shopSettingId =11L;
        Result<Boolean> shopSettingTask = shopSettingTaskController.createShopSettingTask(shopSettingId, taskType);
        logger.info("createShopSettingTask:{}"+JSONObject.toJSONString(shopSettingTask));

    }


}
