package com.lizikj.api.controller.shop;

import com.alibaba.fastjson.JSONObject;
import com.lizikj.api.Bootstrap;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.shop.ShopAreaDeskSummaryVO;
import com.lizikj.api.vo.shop.ShopAreaSummaryVO;
import com.lizikj.api.vo.shop.ShopAreaVO;
import com.lizikj.api.vo.shop.ShopDeskVO;
import com.lizikj.api.vo.shop.param.*;
import com.lizikj.common.number.RandomUtils;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.shop.api.enums.CallStatusEnum;
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
public class TestShopAreaController {

    private static long shopId = 1L;


    private static Logger logger = LoggerFactory.getLogger(TestShopAreaController.class);

    @Autowired
    private ShopAreaController shopAreaController;
    private static Long shopAreaId = 1L;
    private static Long areaDeskId = 1L;

    @Before
    public void init(){
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, shopId);

    }

    /**
     * 测试新增区域
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/9/6 12:06
     */
    @Test
    public void testInsertShopArea(){
        //for (int i = 0; i < 100; i++) {

            ShopAreaParamVO shopAreaParamVO = new ShopAreaParamVO();
            shopAreaParamVO.setAreaName("测试111");
            shopAreaParamVO.setTeaSeatFee(100);
            Result<Long> longResult = shopAreaController.insertShopArea(shopAreaParamVO);
            logger.info("longResult:"+JSONObject.toJSONString(longResult));
        //}
    }

    /**
     * 查询该店铺下的区域
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/9/6 12:43
     */
    @Test
    public void testListShopArea(){
        //shopId = 1142;
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, shopId);
        Result<List<ShopAreaVO>> listResult = shopAreaController.listShopArea();
        logger.info("listResult:"+JSONObject.toJSONString(listResult));

    }

    /**
     * 更新店铺区域
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/9/6 12:46
     */
    @Test
    public void testUpdateShopArea(){
        List<ShopAreaParamVO> shopAreaParamVOS = new ArrayList<>();
        ShopAreaParamVO shopAreaParamVO = new ShopAreaParamVO();
        shopAreaParamVO.setAreaName("测试111-3805847950");//测试111-3805847950  测试111-484293692 (自己)
        shopAreaParamVO.setTeaSeatFee(15L);
        shopAreaParamVO.setShopAreaId(1448L);
        shopAreaParamVOS.add(shopAreaParamVO);
        Result<Boolean> booleanResult = shopAreaController.updateShopArea(shopAreaParamVOS);
        logger.info("updateShopArea:"+JSONObject.toJSONString(booleanResult));

    }

    /**
     * 测试删除店铺区域
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/9/6 12:50
     */
    @Test
    public void testDeleteShopArea(){
        List<Long> ids = new ArrayList<>();

        ids.add(1453L);

        Result<Boolean> booleanResult = shopAreaController.deleteShopArea(ids);
        logger.info("deleteShopArea:"+JSONObject.toJSONString(booleanResult));

    }

    /**
     * 获取店铺下的区域并区域下的桌台
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/9/10 16:01
     */
    @Test
    public void testListAreaAndDesk(){
        Result<List<ShopAreaVO>> listResult = shopAreaController.listAreaAndDesk();
        if (logger.isInfoEnabled()){
            logger.info("listAreaAndDesk:"+JSONObject.toJSONString(listResult));
        }
    }


    /**
     * 获取店铺下的区域并区域下的桌台：根据状态
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/9/10 16:01
     */
    @Test
    public void testListAreaAndDeskByUsedStatuses(){
        List<DeskUsedStatusEnum> usedStatuses = new ArrayList<>();
        usedStatuses.add(DeskUsedStatusEnum.ALL);
        usedStatuses.add(DeskUsedStatusEnum.WAIT_DESK_CLEAN);
        usedStatuses.add(DeskUsedStatusEnum.FREE);

        Result<List<ShopAreaVO>> listResult = shopAreaController.listAreaAndDeskByUsedStatuses(usedStatuses);
        if (logger.isInfoEnabled()){
            logger.info("listAreaAndDeskByUsedStatuses:"+JSONObject.toJSONString(listResult));
        }
    }

    /**
     * 获取桌台统计：一次性查出区域，桌台，人数的统计
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/9/10 16:02
     */
    @Test
    public void testListShopAreaDeskSummaryAll(){
        Result<ShopAreaDeskSummaryVO> shopAreaDeskSummaryVOResult = shopAreaController.listShopAreaDeskSummaryAll();
        if (logger.isInfoEnabled()){
            logger.info("listShopAreaDeskSummaryAll:"+JSONObject.toJSONString(shopAreaDeskSummaryVOResult));
        }
    }

    /**
     * 获取区域桌台总览
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/9/10 16:02
     */
    @Test
    public  void testListShopAreaSummary(){
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, 1158L);
        Result<List<ShopAreaSummaryVO>> listResult = shopAreaController.listShopAreaSummary();
        if (logger.isInfoEnabled()){
            logger.info("listShopAreaSummary:"+JSONObject.toJSONString(listResult));
        }
    }


    @Test
    public void testFindShopAreaById(){

        Result<ShopAreaVO> shopAreaVOResult = shopAreaController.findShopAreaById(1L);

        if (logger.isInfoEnabled()){
            logger.info("findShopAreaById:"+JSONObject.toJSONString(shopAreaVOResult));
        }
    }

    @Test
    public void testListByIds(){

        Result<List<ShopAreaVO>> listResult = shopAreaController.listByIds(Arrays.asList(1L, 2L, 3L, 4L, 5L));

        if (logger.isInfoEnabled()){
            logger.info("listByIds:"+JSONObject.toJSONString(listResult));
        }
    }




}
