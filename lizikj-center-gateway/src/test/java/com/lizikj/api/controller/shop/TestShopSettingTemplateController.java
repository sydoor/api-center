package com.lizikj.api.controller.shop;

import com.alibaba.fastjson.JSONObject;
import com.lizikj.api.Bootstrap;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.shop.ShopSettingTemplateVO;
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
public class TestShopSettingTemplateController {

    @Autowired
    private ShopSettingTemplateController shopSettingTemplateController;
    private static Logger logger = LoggerFactory.getLogger(TestShopSettingTemplateController.class);

    @Test
    public void testGetShopSettingTemplateBySettingCode(){
        int settingCode = 2001;
        Result<ShopSettingTemplateVO> shopSettingTemplateVOResult =
                shopSettingTemplateController.getShopSettingTemplateBySettingCode(settingCode);
        logger.info(JSONObject.toJSONString(shopSettingTemplateVOResult));
    }


}
