package com.lizikj.api.controller.shop;

import com.alibaba.fastjson.JSONObject;
import com.lizikj.api.Bootstrap;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.shop.ShopMerchandiseUnitVO;
import com.lizikj.api.vo.shop.param.ShopMerchandiseFlavorParamVO;
import com.lizikj.api.vo.shop.param.ShopMerchandiseUnitParamVO;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.merchandise.enums.CalcMethodEnum;
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
public class TestShopMerchandiseUnitController {

    private static long shopId = 1L;


    private static Logger logger = LoggerFactory.getLogger(TestShopMerchandiseUnitController.class);

    @Autowired
    private ShopMerchandiseUnitController shopMerchandiseUnitController;
    private static Long shopAreaId = 1L;
    private static Long areaDeskId = 1L;

    @Before
    public void init(){
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, shopId);

    }

    /**
     * 测试新增
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/9/10 16:02
     */
    @Test
    public void testInsertShopMerchandiseUnit(){
        ShopMerchandiseUnitParamVO shopMerchandiseUnitParamVO = new ShopMerchandiseUnitParamVO();
        shopMerchandiseUnitParamVO.setPriceMothed(CalcMethodEnum.NUMBER);
        shopMerchandiseUnitParamVO.setPriceUnit("测试单位212");
        Result<Long> longResult = shopMerchandiseUnitController.insertShopMerchandiseUnit(shopMerchandiseUnitParamVO);
        if (logger.isInfoEnabled()){
            logger.info("insertShopMerchandiseUnit id:"+JSONObject.toJSONString(longResult));
        }
    }

    @Test
    public void testUpdateUnit(){
        ShopMerchandiseUnitParamVO shopMerchandiseUnitParamVO = new ShopMerchandiseUnitParamVO();
        shopMerchandiseUnitParamVO.setMerchandiseUnitId(7741L);
        shopMerchandiseUnitParamVO.setPriceUnit("测试单位222");
        Result<Boolean> booleanResult = shopMerchandiseUnitController.updateShopMerchandiseUnit(shopMerchandiseUnitParamVO);
        if (logger.isInfoEnabled()){
            logger.info("updateShopMerchandiseUnit:"+JSONObject.toJSONString(booleanResult));
        }
    }

    /**
     * 测试删除单位
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/9/14 16:55
     */
    @Test
    public  void testDeleteShopMerchandiseUnit(){
        Long merchandiseUnitId = 7741L;
        Result<Boolean> booleanResult = shopMerchandiseUnitController.deleteShopMerchandiseUnit(merchandiseUnitId);
        if (logger.isInfoEnabled()){
            logger.info("deleteShopMerchandiseUnit:"+JSONObject.toJSONString(booleanResult));
        }
    }

    @Test
    public  void testListShopMerchandiseUnits(){


        shopId = 1159L;
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, shopId);

        Result<List<ShopMerchandiseUnitVO>> listResult = shopMerchandiseUnitController.listShopMerchandiseUnits();
        if (logger.isInfoEnabled()){
            logger.info("listShopMerchandiseUnits:"+JSONObject.toJSONString(listResult));
        }
    }



}
