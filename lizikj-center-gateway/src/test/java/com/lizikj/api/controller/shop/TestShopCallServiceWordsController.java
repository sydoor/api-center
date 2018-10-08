package com.lizikj.api.controller.shop;

import com.alibaba.fastjson.JSONObject;
import com.lizikj.api.Bootstrap;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.shop.ShopCallServiceWordsVO;
import com.lizikj.api.vo.shop.param.ShopCallServiceWordsParamVO;
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
public class TestShopCallServiceWordsController {

    private static long shopId = 1L;


    private static Logger logger = LoggerFactory.getLogger(TestShopCallServiceWordsController.class);

    @Autowired
    private ShopCallServiceWordsController shopCallServiceWordsController;

    @Before
    public void init(){
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, shopId);

    }

    /**
     * 测试新增自定义话术
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/9/6 12:06
     */
    @Test
    public void testInsertShopCallServiceWordsa(){
        for (int i = 0; i < 10; i++) {
            ShopCallServiceWordsParamVO shopCallServiceWordsParamVO = new ShopCallServiceWordsParamVO();
            shopCallServiceWordsParamVO.setTheWords("测试话术"+i);
            Result<Long> longResult = shopCallServiceWordsController.insertShopCallServiceWords(shopCallServiceWordsParamVO);
            logger.info("longResult:"+JSONObject.toJSONString(longResult));
        }
    }

    /**
     * 测试查询该店铺下的呼叫服务
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/9/18 21:34
     */
    @Test
    public void testListShopCallServiceWords(){
        Result<List<ShopCallServiceWordsVO>> listResult = shopCallServiceWordsController.listShopCallServiceWords();
        logger.info("listResult:"+JSONObject.toJSONString(listResult));

    }

    /**
     * 测试更新
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/9/18 21:54
     */
    @Test
    public void testUpdateShopCallServiceWords(){

        ShopCallServiceWordsParamVO shopCallServiceWordsParamVO = new ShopCallServiceWordsParamVO();
        shopCallServiceWordsParamVO.setCallServiceWordsId(1L);
        shopCallServiceWordsParamVO.setTheWords("测试更新-1");
        Result<Boolean> booleanResult = shopCallServiceWordsController.updateShopCallServiceWords(shopCallServiceWordsParamVO);
        logger.info("booleanResult:"+JSONObject.toJSONString(booleanResult));

    }



    /**
     * 测试删除
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/9/18 21:55
     */
    @Test
    public void testDeleteShopCallServiceWords(){
        Result<Boolean> booleanResult = shopCallServiceWordsController.deleteShopCallServiceWords(1L);
        logger.info("booleanResult:"+JSONObject.toJSONString(booleanResult));

    }

    @Test
    public void insertAndUpdate(){
        ShopCallServiceWordsParamVO insertParamVO = new ShopCallServiceWordsParamVO();
        insertParamVO.setTheWords("测试新增更新");
        Result<Long> longResult = shopCallServiceWordsController.insertShopCallServiceWords(insertParamVO);
        if (logger.isInfoEnabled()){
            logger.info("insertShopCallServiceWords:"+JSONObject.toJSONString(longResult));
        }

        if (longResult != null){
            Long id = longResult.getData();
            ShopCallServiceWordsParamVO updateParamVO = new ShopCallServiceWordsParamVO();
            updateParamVO.setCallServiceWordsId(id);
            updateParamVO.setTheWords("测试新增更新1");
            shopCallServiceWordsController.updateShopCallServiceWords(updateParamVO);

        }

    }



}
