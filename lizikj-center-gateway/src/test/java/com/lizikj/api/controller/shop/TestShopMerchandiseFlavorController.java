package com.lizikj.api.controller.shop;

import com.alibaba.fastjson.JSONObject;
import com.lizikj.api.Bootstrap;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.shop.ShopMerchandiseFlavorVO;
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

import java.util.List;

/**
 * @author zhoufe
 * @date 2017/8/12 15:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=Bootstrap.class)//, properties={"spring.profiles.active=dev"}
@ActiveProfiles({"dev"})//dev 环境，redis.singleton：redis集群与否
public class TestShopMerchandiseFlavorController {

    private static long shopId = 1L;


    private static Logger logger = LoggerFactory.getLogger(TestShopMerchandiseFlavorController.class);

    @Autowired
    private ShopMerchandiseFlavorController shopMerchandiseFlavorController;
    private static Long shopAreaId = 1L;
    private static Long areaDeskId = 1L;

    @Before
    public void init(){
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, shopId);

    }

    /**
     * 查询新增
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/9/10 16:02
     */
    @Test
    public  void testInsertShopMerchandiseFlavor(){
        ShopMerchandiseFlavorParamVO shopMerchandiseFlavorParamVO = new ShopMerchandiseFlavorParamVO();
        shopMerchandiseFlavorParamVO.setTheFlavor("测试辣23");
        Result<Long> longResult = shopMerchandiseFlavorController.insertShopMerchandiseFlavor(shopMerchandiseFlavorParamVO);
        if (logger.isInfoEnabled()){
            logger.info("insertShopMerchandiseFlavor id:"+JSONObject.toJSONString(longResult));
        }
    }

    /**
     * 测试查询
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/10/17 15:16
     */
    @Test
    public void testListShopMerchandiseFlavor(){


        //shopId = 1159L;
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, shopId);

        Result<List<ShopMerchandiseFlavorVO>> listResult =
                shopMerchandiseFlavorController.listShopMerchandiseFlavor();

        if (logger.isInfoEnabled()){
            logger.info("listShopMerchandiseFlavor:"+JSONObject.toJSONString(listResult));
        }
    }

    /**
     * 测试更新
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/10/17 15:17
     */
    @Test
    public void testUpdateShopMerchandiseFlavor(){
        ShopMerchandiseFlavorParamVO  flavorParamVO = new ShopMerchandiseFlavorParamVO();
        flavorParamVO.setMerchandiseFlavorId(31L);
        flavorParamVO.setTheFlavor("测试辣212");
        Result<Boolean> booleanResult = shopMerchandiseFlavorController.updateShopMerchandiseFlavor(flavorParamVO);
        if (logger.isInfoEnabled()){
            logger.info("updateShopMerchandiseFlavor:"+JSONObject.toJSONString(booleanResult));
        }
    }

    /**
     * 参数删除
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/10/17 17:39
     */
    @Test
    public void testDeleteShopMerchandiseFlavor(){
        Long merchandiseFlavorId = 31L;
        Result<Boolean> booleanResult = shopMerchandiseFlavorController.deleteShopMerchandiseFlavor(merchandiseFlavorId);
        if (logger.isInfoEnabled()){
            logger.info("deleteShopMerchandiseFlavor:"+JSONObject.toJSONString(booleanResult));
        }
    }


}
