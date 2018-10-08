package com.lizikj.api.controller.shop;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.lizikj.api.Bootstrap;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.shop.ShopAreaDeskSummaryVO;
import com.lizikj.api.vo.shop.ShopAreaSummaryVO;
import com.lizikj.api.vo.shop.ShopAreaVO;
import com.lizikj.api.vo.shop.ShopDeskQrcodeTemplateVO;
import com.lizikj.api.vo.shop.param.ShopAreaParamVO;
import com.lizikj.common.util.ThreadLocalContext;
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
public class TestShopQrCodeTemplateController {

    private static long shopId = 1L;


    private static Logger logger = LoggerFactory.getLogger(TestShopQrCodeTemplateController.class);

    @Autowired
    private ShopQrCodeTemplateController shopQrCodeTemplateController;

    @Before
    public void init(){
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, shopId);

    }

    @Test
    public void testListAll(){
        Result<List<ShopDeskQrcodeTemplateVO>> listResult = shopQrCodeTemplateController.listAll();
        if (logger.isInfoEnabled()){
            logger.info("listAll:"+JSONObject.toJSONString(listResult));
        }
    }

    @Test
    public void testListAllPage(){
        Result<PageInfo<ShopDeskQrcodeTemplateVO>> pageInfoResult = shopQrCodeTemplateController.listAllPage(1, 10);
        if (logger.isInfoEnabled()){
            logger.info("listAllPage:"+JSONObject.toJSONString(pageInfoResult));
        }
    }


    @Test
    public void testSelectDeskQrCodeTemplate(){
        Result<Boolean> booleanResult = shopQrCodeTemplateController.selectDeskQrCodeTemplate(122L);
        if (logger.isInfoEnabled()){
            logger.info("selectDeskQrCodeTemplate:"+JSONObject.toJSONString(booleanResult));
        }
    }





}
