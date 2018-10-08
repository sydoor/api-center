package com.lizikj.api.controller.shop;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.lizikj.api.Bootstrap;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.shop.ShopPosDeviceVO;
import com.lizikj.common.util.ThreadLocalContext;
/**
 * @author zhoufe
 * @date 2017/8/12 15:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=Bootstrap.class)//, properties={"spring.profiles.active=dev"}
@ActiveProfiles({"dev","redis.singleton"})//dev 环境，redis.singleton：redis集群与否
public class TestShopPosDeviceController {


    private static Logger logger = LoggerFactory.getLogger(TestShopPosDeviceController.class);

    @Autowired
    private ShopPosDeviceController shopPosDeviceController;

    public static String suNum = "20170822153626746-854";
    private static Long shopId = 1L;

    @Before
    public void init(){
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, shopId);

        ThreadLocalContext.putThreadValue(ThreadLocalContext.LZ_DID, suNum);

    }


    /**
     * 测试查询POS
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/8/29 17:05
     */
    @Test
    public void testListShopPosDevice(){


        Result<List<ShopPosDeviceVO>> listResult = shopPosDeviceController.listShopPosDevice();

        logger.info("信息：{}", JSONObject.toJSONString(listResult));
   }


    /**
     * 测试设置主设备
     * @params []
     * @return void`
     * @author zhoufe
     * @date 2017/9/4 14:08
     */
    @Test
    public void testSetMasterPosDevice(){
        Result<Boolean> listResult = shopPosDeviceController.setMasterDevice(183L);

        logger.info("信息：{}", JSONObject.toJSONString(listResult));
    }



}
