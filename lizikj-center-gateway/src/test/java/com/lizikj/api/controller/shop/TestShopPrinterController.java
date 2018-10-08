package com.lizikj.api.controller.shop;

import com.alibaba.fastjson.JSONObject;
import com.lizikj.api.Bootstrap;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.shop.ShopPrinterVO;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.shop.api.enums.DefaultStatusEnum;
import com.lizikj.shop.api.enums.ShopPrinterConectTypeEnum;
import com.lizikj.shop.api.enums.ShopPrinterTypeEnum;
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
import java.util.Random;

/**
 * @author zhoufe
 * @date 2017/8/12 15:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=Bootstrap.class)//, properties={"spring.profiles.active=dev"}
@ActiveProfiles({"dev"})//dev 环境，redis.singleton：redis集群与否
public class TestShopPrinterController {


    public static final String SN = "N9NL10244336";
    private static Logger logger = LoggerFactory.getLogger(TestShopPrinterController.class);

    @Autowired
    private ShopPrinterController shopPrinterController;
    private Random random = new Random(47);

    @Before
    public void init(){
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LZ_DID, SN);

    }

    /**
     * 获取区域打印机
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/9/10 16:02
     */
    @Test
    public  void testListShopPrinter(){
        Result<List<ShopPrinterVO>> listResult = shopPrinterController.listShopPrinter();
        if (logger.isInfoEnabled()){
            logger.info("listShopPrinter:"+JSONObject.toJSONString(listResult));
        }
    }


    /**
     * 获取区域打印机
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/9/10 16:02
     */
    @Test
    public  void testInsertShopPrinter(){
        ShopPrinterVO shopPrinterVO = getShopPrinterVO();
        Result<Long> longResult = shopPrinterController.insertShopPrinter(shopPrinterVO);
        if (logger.isInfoEnabled()){
            logger.info("insertShopPrinter:"+JSONObject.toJSONString(longResult));
        }
    }


    private ShopPrinterVO getShopPrinterVO() {
        ShopPrinterVO shopPrinterVO = new ShopPrinterVO();
        //shopPrinterDTO.setShopId(SHOP_ID);
        shopPrinterVO.setBrand(String.valueOf(random.nextInt(10)));
        shopPrinterVO.setConectType(ShopPrinterConectTypeEnum.WIFI);
        shopPrinterVO.setName("printername:"+String.valueOf(random.nextInt(100)));
        shopPrinterVO.setTicketSize(random.nextInt(10)+1);
        shopPrinterVO.setType(ShopPrinterTypeEnum.KITCHEN);
        shopPrinterVO.setWifiIpAddress("127.0.0."+String.valueOf(random.nextInt(255)));
        shopPrinterVO.setWifiPort(random.nextInt(4000));
        shopPrinterVO.setDefaultStatus(DefaultStatusEnum.UN_DEFAULT);

        shopPrinterVO.setElemeProducts(getTestRandomData());
        shopPrinterVO.setLiziProducts(getTestRandomData());
        shopPrinterVO.setMeituanProducts(getTestRandomData());
        return shopPrinterVO;
    }

    public String getTestRandomData() {

        List<LiziPrinter> liziPrinters = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            LiziPrinter liziPrinter = new LiziPrinter();
            liziPrinter.setId("ID:"+random.nextInt(1000));
            liziPrinter.setName("name:"+(random.nextInt(1000)));
            liziPrinters.add(liziPrinter);
        }

        return JSONObject.toJSONString(liziPrinters);
    }

    private static class ElemePrinter extends LiziPrinter{
    }

    private static class LiziPrinter{
        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private static class MeituanPrinter extends LiziPrinter{
    }


    @Test
    public void testUpdateShopPrinter(){
        ShopPrinterVO shopPrinterVO = new ShopPrinterVO();
        shopPrinterVO.setPrinterId(105L);
        shopPrinterVO.setMeituanProducts("");
        Result<Boolean> booleanResult = shopPrinterController.updateShopPrinter(shopPrinterVO);
        if (logger.isInfoEnabled()){
            logger.info("testUpdateShopPrinter:"+JSONObject.toJSONString(booleanResult));
        }
    }

    @Test
    public void testDeleteShopPrinter(){
        ShopPrinterVO shopPrinterVO = new ShopPrinterVO();
        shopPrinterVO.setPrinterId(105L);
        Result<Boolean> booleanResult = shopPrinterController.deleteShopPrinter(105L);
        if (logger.isInfoEnabled()){
            logger.info("testUpdateShopPrinter:"+JSONObject.toJSONString(booleanResult));
        }
    }


    @Test
    public void testFindById(){

        Result<ShopPrinterVO> shopPrinterVOResult = shopPrinterController.findById(1L);
        if (logger.isInfoEnabled()){
            logger.info("testFindById:"+JSONObject.toJSONString(shopPrinterVOResult));
        }

    }


}
