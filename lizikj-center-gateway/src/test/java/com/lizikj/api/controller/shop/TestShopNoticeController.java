package com.lizikj.api.controller.shop;

import com.alibaba.fastjson.JSONObject;
import com.lizikj.api.Bootstrap;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.shop.ShopAreaDeskSummaryVO;
import com.lizikj.api.vo.shop.ShopAreaSummaryVO;
import com.lizikj.api.vo.shop.ShopAreaVO;
import com.lizikj.api.vo.shop.ShopNoticeVO;
import com.lizikj.api.vo.shop.param.ShopAreaParamVO;
import com.lizikj.common.number.RandomUtils;
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
import java.util.List;

/**
 * @author zhoufe
 * @date 2017/8/12 15:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=Bootstrap.class)//, properties={"spring.profiles.active=dev"}
@ActiveProfiles({"dev"})//dev 环境，redis.singleton：redis集群与否
public class TestShopNoticeController {

    private static long shopId = 1120L;//1L;


    private static Logger logger = LoggerFactory.getLogger(TestShopNoticeController.class);

    @Autowired
    private ShopNoticeController shopNoticeController;

    @Before
    public void init(){
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, shopId);

    }

    /**
     * 测试获取店铺公告
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/9/6 12:06
     */
    @Test
    public void testGetShopNotice(){
        Result<ShopNoticeVO> shopNoticeVO = shopNoticeController.getShopNotice();
        logger.info("getShopNotice:"+JSONObject.toJSONString(shopNoticeVO));
    }

    /**
     * 测试更新店铺公告
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/9/19 15:17
     */
    @Test
    public void testUpdateShopNotice(){
        ShopNoticeVO shopNoticeVO = new ShopNoticeVO();
        shopNoticeVO.setContent("测试公告");
        Result<ShopNoticeVO> shopNoticeVOResult = shopNoticeController.updateShopNotice(shopNoticeVO);
        logger.info("updateShopNotice:"+JSONObject.toJSONString(shopNoticeVOResult));

    }


}
