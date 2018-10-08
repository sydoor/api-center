package com.lizikj.api.controller.shop;

import com.alibaba.fastjson.JSONObject;
import com.lizikj.api.Bootstrap;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.shop.ShopAreaDeskSummaryVO;
import com.lizikj.api.vo.shop.ShopAreaSummaryVO;
import com.lizikj.api.vo.shop.ShopAreaVO;
import com.lizikj.api.vo.shop.param.ShopAreaParamVO;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.shop.api.common.ShopConstants;
import com.lizikj.shop.api.enums.DeskUsedStatusEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhoufe
 * @date 2017/8/12 15:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=Bootstrap.class)//, properties={"spring.profiles.active=dev"}
@ActiveProfiles({"dev"})//dev 环境，redis.singleton：redis集群与否
public class TestShopVirtualDataController {

    private static Logger logger = LoggerFactory.getLogger(TestShopVirtualDataController.class);

    @Autowired
    private ShopVirtualDataController shopVirtualDataController;


    @Test
    public void testCleanData(){
        Result<String> stringResult = shopVirtualDataController.cleanData(ShopConstants.VIRTUAL_DATA_KEY);
        if (logger.isInfoEnabled()){
            logger.info("cleanData:"+JSONObject.toJSONString(stringResult));
        }
    }

    @Test
    public void testGenData(){
        Result<String> stringResult = shopVirtualDataController.genData(ShopConstants.VIRTUAL_DATA_KEY);
        if (logger.isInfoEnabled()){
            logger.info("genData:"+JSONObject.toJSONString(stringResult));
        }
    }




}
