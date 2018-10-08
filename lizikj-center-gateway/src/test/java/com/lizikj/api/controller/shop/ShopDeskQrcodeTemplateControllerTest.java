package com.lizikj.api.controller.shop;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.lizikj.api.Bootstrap;
import com.lizikj.api.controller.user.merchant.MerchantUserController;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.shop.ShopDeskQrcodeTemplateVO;
import com.lizikj.common.util.ThreadLocalContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lxl
 * @Date 2018/5/16 14:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=Bootstrap.class)//, properties={"spring.profiles.active=dev"}
@ActiveProfiles({"dev"})//dev 环境，redis.singleton：redis集群与否
public class ShopDeskQrcodeTemplateControllerTest {
    private static Logger logger = LoggerFactory.getLogger(ShopDeskQrcodeTemplateControllerTest.class);
    @Autowired
    ShopQrCodeTemplateController shopQrCodeTemplateController;


    @Test
    public void testFindById() throws Exception{
        Result<ShopDeskQrcodeTemplateVO> byId = shopQrCodeTemplateController.findById(4L);
        if (logger.isInfoEnabled()){
            logger.info("testFindById:{}", JSONObject.toJSONString(byId));
        }
    }

    @Test
    public void testSelectDeskQrCodeTemplate(){
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, 1L);
        Result<Boolean> booleanResult = shopQrCodeTemplateController.selectDeskQrCodeTemplate(1L);
        if (logger.isInfoEnabled()){
            logger.info("testSelectDeskQrCodeTemplate:{}", JSONObject.toJSONString(booleanResult));
        }
    }

}
