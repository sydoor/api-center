package com.lizikj.api.controller.shop;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.lizikj.api.vo.shop.ShopDeskVO;
import com.lizikj.api.vo.shop.param.ShopDeskBatchParamVO;
import com.lizikj.api.vo.shop.param.ShopDeskCallServiceParamVO;
import com.lizikj.api.vo.shop.param.ShopDeskChangeUsedStatusParamVO;
import com.lizikj.api.vo.shop.param.ShopDeskParamVO;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.login.util.JWTUtils;
import com.lizikj.shop.api.enums.CallStatusEnum;
import com.lizikj.shop.api.enums.DeskUsedStatusEnum;

import io.jsonwebtoken.Claims;

/**
 * @author zhoufe
 * @date 2017/8/12 15:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=Bootstrap.class)//, properties={"spring.profiles.active=dev"}
@ActiveProfiles({"dev","redis.singleton"})//dev 环境，redis.singleton：redis集群与否
public class TestShopDeskController {


    public static final String HAD_SEE_THE_WORLD = "测试桌子2";
    private static long shopId = 1098;//1L;


    private static Logger logger = LoggerFactory.getLogger(TestShopDeskController.class);

    @Autowired
    private ShopDeskController shopDeskController;
    private static Long shopAreaId = 1L;
    private static Long areaDeskId = 1L;

    @Before
    public void init(){
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, shopId);

    }

    public static void main(String[] args) {
        //findShopIdByToken 1098
        //String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1aSI6MTE0Miwic2kiOjIxOTIsImxzIjoxLCJtaSI6MTA5OCwiZXhwIjoxNTA2NDc2MTE5LCJzcGkiOjEwOTgsImlhdCI6MTUwNTg3MTMxOSwidXQiOjJ9._EI82SXYIYYofEOvBjqvS7XAux92xCirxXnZvqHgrrM";

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1aSI6OCwibW1iaSI6MCwibWJpIjowLCJscyI6MywibWkiOjEsImV4cCI6MTUwNjczNzY0MSwic3BpIjoxLCJpYXQiOjE1MDYxMzI4NDEsInV0Ijo0fQ.UjGj1RGHk6GUfDy_-UxbVFTR3suoB8wZDBs43gVXa_o";
        Claims claims = JWTUtils.parseJWT(token);
        System.out.println("shopId="+claims.get(JWTUtils.SHOP_ID));

    }


    /**
     * 测试查询桌台
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/8/29 17:05
     */
    @Test
    public void testListShopDesk(){

        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, 1158L);

        Result<List<ShopDeskVO>> listResult = shopDeskController.listShopDesk();

       logger.info("{}", JSONObject.toJSONString(listResult));
   }

    /**
     * 测试新增桌台
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/8/29 17:06
     */
    @Test
    public void testInsertShopDesk(){

        ShopDeskParamVO shopDeskParamVO = new ShopDeskParamVO();

        shopDeskParamVO.setShopAreaId(shopAreaId);
        shopDeskParamVO.setDeskName(HAD_SEE_THE_WORLD);
        shopDeskParamVO.setSeatNum(8);

        Result<Long> longResult = shopDeskController.insertShopDesk(shopDeskParamVO);
        logger.info("{}", JSONObject.toJSONString(longResult));
    }

    /**
     * 测试批量增加
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/8/29 17:21
     */
    @Test
    public void testBatchInsertShopDesk(){
        ShopDeskBatchParamVO shopDeskBatchParamVO = new ShopDeskBatchParamVO();
        shopDeskBatchParamVO.setEndIndex(10);
        shopDeskBatchParamVO.setNamePrefix(HAD_SEE_THE_WORLD);
        shopDeskBatchParamVO.setSeatNum(8);
        shopDeskBatchParamVO.setShopAreaId(shopAreaId);
        shopDeskBatchParamVO.setStartIndex(1);
        Result<Boolean> booleanResult = shopDeskController.batchInsertShopDesk(shopDeskBatchParamVO);
        logger.info("{}", JSONObject.toJSONString(booleanResult));
    }

    /**
     * 测试更新桌台
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/8/29 18:18
     */
    @Test
    public void testUpdateShopDesks(){

        List<ShopDeskParamVO> sameNameTestData = assemblySameNameTestData();
        Result<Boolean> booleanResult = shopDeskController.updateShopDesks(sameNameTestData);
        logger.info("信息：{}", JSONObject.toJSONString(booleanResult));

    }

    /**
     *  封装相同名称测试数据
     * @params []
     * @return java.util.List<com.lizikj.api.vo.shop.param.ShopDeskParamVO>
     * @author zhoufe
     * @date 2017/8/30 15:01
     */
    private List<ShopDeskParamVO> assemblySameNameTestData() {
        List<ShopDeskParamVO> shopDeskParamVOS = new ArrayList<>();

        ShopDeskParamVO shopDeskParamVO = new ShopDeskParamVO();
        shopDeskParamVO.setAreaDeskId(areaDeskId);
        shopDeskParamVO.setDeskName(HAD_SEE_THE_WORLD);//失败的
        shopDeskParamVOS.add(shopDeskParamVO);

        return shopDeskParamVOS;
    }


    /**
     * 测试呼叫服务
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/8/29 20:54
     */
    @Test
    public void testChangeCallService(){
        ShopDeskCallServiceParamVO shopDeskCallServiceParamVO = new ShopDeskCallServiceParamVO();

        shopDeskCallServiceParamVO.setAreaDeskId(areaDeskId);
        shopDeskCallServiceParamVO.setCallStatus(CallStatusEnum.HAD_CALL);

        Result<Boolean> booleanResult = shopDeskController.changeCallService(shopDeskCallServiceParamVO);
        logger.info("{}", JSONObject.toJSONString(booleanResult));

    }

    /**
     * 测试改变桌台状态
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/8/29 20:55
     */
    @Test
    public void testChangeUsedStatus(){
        ShopDeskChangeUsedStatusParamVO shopDeskChangeUsedStatusParamVO = new ShopDeskChangeUsedStatusParamVO();
        areaDeskId = 186L;
        shopDeskChangeUsedStatusParamVO.setAreaDeskId(areaDeskId);
        //shopDeskChangeUsedStatusParamVO.setUsedStatus(DeskUsedStatusEnum.ALL);
        //shopDeskChangeUsedStatusParamVO.setUsedStatus(DeskUsedStatusEnum.LOCKED);
        shopDeskChangeUsedStatusParamVO.setUsedStatus(DeskUsedStatusEnum.FREE);
        //shopDeskChangeUsedStatusParamVO.setLockReason("手动测试桌台原因001");
        Result<Boolean> booleanResult = shopDeskController.changeUsedStatus(shopDeskChangeUsedStatusParamVO);
        logger.info("{}", JSONObject.toJSONString(booleanResult));

    }

     /**
      * 根据区域ID，桌台名称模糊查询桌台：当shopAreaId == null时查询全部区域符合的桌台
      * @params []
      * @return void
      * @author zhoufe
      * @date 2017/9/10 17:44
      */
    @Test
    public void testListByAreaIdAndDeskName(){
        Result<List<ShopDeskVO>> listResult = shopDeskController.listByAreaIdAndDeskName(103L,"测试桌子9");
        if (logger.isInfoEnabled()){
            logger.info("listByAreaIdAndDeskName："+JSONObject.toJSONString(listResult));
        }
    }


    /**
     * 根据区域获取桌台：当shopAreaId为空取全部的区域的桌台listShopDesk接口
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/9/10 18:04
     */
    @Test
    public void testListShopDeskByArea(){
        Result<List<ShopDeskVO>> listResult = shopDeskController.listShopDeskByArea(103L);
        if (logger.isInfoEnabled()){
            logger.info("listShopDeskByArea："+JSONObject.toJSONString(listResult));
        }
    }

    /**
     * 根据座位数获取桌台：点击全部时调listShopDesk
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/9/10 18:19
     */
    @Test
    public void testListShopDeskBySeatNum(){
        Result<List<ShopDeskVO>> listResult = shopDeskController.listShopDeskBySeatNum(1, 2);
        if (logger.isInfoEnabled()){
            logger.info("listShopDeskBySeatNum："+JSONObject.toJSONString(listResult));
        }
    }

    @Test
    public void testListShopDeskByStatus(){

        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, 1158L);

//        Result<List<ShopDeskVO>> listResultFree = shopDeskController.listShopDeskByStatus(null, DeskUsedStatusEnum.FREE);
//        if (logger.isInfoEnabled()){
//            logger.info("listShopDeskByStatus FREE："+JSONObject.toJSONString(listResultFree));
//        }

        Result<List<ShopDeskVO>> listResult = shopDeskController.listShopDeskByStatus(null, DeskUsedStatusEnum.ALL);
        if (logger.isInfoEnabled()){
            logger.info("listShopDeskByStatus ALL："+JSONObject.toJSONString(listResult));
        }
    }

    @Test
    public void testFindAreaDeskById(){
        Result<ShopDeskVO> shopDeskVOResult = shopDeskController.findAreaDeskById(1L);
        if (logger.isInfoEnabled()){
            logger.info("findAreaDeskById：{}", JSONObject.toJSONString(shopDeskVOResult));
        }
    }

    @Test
    public void testDeleteShopDesks(){
        List<Long> areaDeskIds = new ArrayList<>();
        areaDeskIds.add(1L);
        Result<Boolean> booleanResult = shopDeskController.deleteShopDesks(areaDeskIds);
        if (logger.isInfoEnabled()){
            logger.info("deleteShopDesks：{}", JSONObject.toJSONString(booleanResult));
        }
    }


    @Test
    public void testListByIds(){
        Result<List<ShopDeskVO>> listResult = shopDeskController.listByIds(Arrays.asList(1L, 2L, 3L, 4L, 5L));
        if (logger.isInfoEnabled()){
            logger.info("listByIds：{}", JSONObject.toJSONString(listResult));
        }
    }


}
