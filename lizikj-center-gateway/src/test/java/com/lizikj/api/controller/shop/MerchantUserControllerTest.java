package com.lizikj.api.controller.shop;

import com.lizikj.api.Bootstrap;
import com.lizikj.api.controller.user.merchant.MerchantUserController;
import com.lizikj.api.vo.user.param.LoginParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lxl
 * @Date 2018/5/16 14:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=Bootstrap.class)//, properties={"spring.profiles.active=dev"}
@ActiveProfiles({"dev"})//dev 环境，redis.singleton：redis集群与否
public class MerchantUserControllerTest {
    private static Logger logger = LoggerFactory.getLogger(MerchantUserControllerTest.class);
    @Autowired
    MerchantUserController merchantUserController;


    @Test
    public void testLogin() throws Exception{
        merchantUserController.findMobileCode("13616464");
    }

}
