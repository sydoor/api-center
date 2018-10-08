package com.lizikj.api.controller.shop;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.lizikj.api.vo.shop.param.QueryShopSettingVO;
import com.lizikj.common.enums.RemoveStatusEnum;
import com.lizikj.shop.api.dto.ShopSettingDTO;
import com.lizikj.shop.api.enums.*;
import org.joda.time.DateTime;
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
import com.lizikj.api.vo.shop.ShopSettingVO;
import com.lizikj.api.vo.shop.param.ShopSettingParamVO;
import com.lizikj.api.vo.shop.param.ShopSettingValueParamVO;
import com.lizikj.common.util.ThreadLocalContext;

/**
 * @author zhoufe
 * @date 2017/8/12 15:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=Bootstrap.class)//, properties={"spring.profiles.active=dev"}
@ActiveProfiles({"dev","redis.singleton"})//dev 环境，redis.singleton：redis集群与否
public class TestShopSettingController {

    /**
     * 会员优惠方式ID
     */
    public static Long SHOP_SETTING_ID = 20L;
    /**
     * 会员优惠方式的值的ID
     */
    public static Long SETTING_VALUE_ID = 7L;

    private static Logger logger = LoggerFactory.getLogger(TestShopSettingController.class);

    @Autowired
    private ShopSettingController shopSettingController;

    private static long shopId = 1142L;//1L;200L
    private static long merchantId = 1142L;//1L;200L


    private long sys_shopSettingId = 6L;
    private long custom_shopSettingId = 39L;


    public enum AddUpdateEnum {
        ADD,
        UPDATE,
        ;
    }

    @Before
    public void init(){
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, shopId);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_MERCHANT_ID, merchantId);


    }

    /**
     * 测试获取店铺配置
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/8/24 10:25
     */
    @Test
    public void testListShopSettings(){

        Result<List<ShopSettingVO>> listResult = shopSettingController.listShopSettings();

        logger.info(JSONObject.toJSONString(listResult));
    }

    /**
     * 测试获取店铺配置
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/8/24 10:25
     */
    @Test
    public void testListShopSettings4Shop(){
        //1117 1118
        //Long[][] shopIdsAndMerchantIds= {{50L, 50L},{55L, 55L},{84L, 84L}, {87L, 87L}};

        Long[][] shopIdsAndMerchantIds= {{1118L, 1117L}};
        for (Long[] shopIdsAndMerchantIdArr : shopIdsAndMerchantIds) {
            Long shopId = shopIdsAndMerchantIdArr[0];
            Long merchantId = shopIdsAndMerchantIdArr[1];
            ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, shopId);
            ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_MERCHANT_ID, merchantId);
            Result<List<ShopSettingVO>> listResult = shopSettingController.listShopSettings();
            logger.info("{}：{}成功？{}", shopId, merchantId, JSONObject.toJSONString(listResult));
        }

    }




    /**
     * 测试获取连锁配置
     * 模拟老板登录
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/8/24 10:25
     */
    @Test
    public void testListShopSettingsByMerchantId(){

        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, 0L);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_MERCHANT_ID, merchantId);

        Result<List<ShopSettingVO>> listResult = shopSettingController.listShopSettings();

        logger.info(JSONObject.toJSONString(listResult));
    }



    /**
     * 更新店铺配置
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/8/26 14:25
     */
    @Test
    public void testUpdateShopSettings(){

//        List<ShopSettingParamVO> shopSettingUpdateParamVOS = assemblyMemberDiscTypeTestData();

//        List<ShopSettingParamVO> shopSettingUpdateParamVOS = assemblyDeskManagerTestData();

//        List<ShopSettingParamVO> shopSettingUpdateParamVOS = assemblyTeaSeatFeeTestData();

//        List<ShopSettingParamVO> shopSettingUpdateParamVOS =  assemblyCustomFeeTestData(AddUpdateEnum.UPDATE);

//        List<ShopSettingParamVO> shopSettingUpdateParamVOS = assemblyDeskManagerTeaSeatTestData();

//        List<ShopSettingParamVO> shopSettingUpdateParamVOS = assemblyDeskAreaTestData();

//        List<ShopSettingParamVO> shopSettingUpdateParamVOS = assemblyBusiModeTestData();

        List<ShopSettingParamVO> shopSettingUpdateParamVOS = assemblyDeskStatusTestData();

        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, 1142L);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_MERCHANT_ID, 1142L);

        Result<List<ShopSettingVO>> listResult = shopSettingController.updateShopSettings(shopSettingUpdateParamVOS);
        logger.info(JSONObject.toJSONString(listResult));
    }

    /**
     * 组装桌台状态
     * @params []
     * @return java.util.List<com.lizikj.api.vo.shop.param.ShopSettingParamVO>
     * @author zhoufe
     * @date 2017/10/24 20:52
     */
    private List<ShopSettingParamVO> assemblyDeskStatusTestData() {

        List<ShopSettingParamVO> shopSettingParamVOS = new ArrayList<>();
        ShopSettingParamVO shopSettingParamVO = new ShopSettingParamVO();
        shopSettingParamVO.setShopSettingId(24079L);
        shopSettingParamVO.setSettingCode(ShopSettingCodeEnum.DESK_STATUS_CODE.getCode());
        shopSettingParamVO.setOpenStatus(OpenStatusEnum.OPEN);

        shopSettingParamVOS.add(shopSettingParamVO);

        return shopSettingParamVOS;
    }

    /**
     * 营业模式
     * @params []
     * @return java.util.List<com.lizikj.api.vo.shop.param.ShopSettingParamVO>
     * @author zhoufe
     * @date 2017/10/20 15:07
     */
    private List<ShopSettingParamVO> assemblyBusiModeTestData() {

        List<ShopSettingParamVO> shopSettingParamVOS = new ArrayList<>();
        ShopSettingParamVO shopSettingParamVO = new ShopSettingParamVO();
        shopSettingParamVO.setShopSettingId(24081L);
        shopSettingParamVO.setSettingCode(1004);

        List<ShopSettingValueParamVO> shopSettingValues = new ArrayList<>();
        ShopSettingValueParamVO shopSettingValueParamVO = new ShopSettingValueParamVO();

        shopSettingValueParamVO.setSettingValueId(10131L);
        shopSettingValueParamVO.setDefaultStatus(DefaultStatusEnum.DEFAULT);
        shopSettingValueParamVO.setValue(ShopBizModeEnum.EAT_FIRST.getCode());// 10132L ShopBizModeEnum.PAY_FIRST.getCode()

        shopSettingValues.add(shopSettingValueParamVO);
        shopSettingParamVO.setShopSettingValues(shopSettingValues);
        shopSettingParamVOS.add(shopSettingParamVO);
        return shopSettingParamVOS;
    }

    /**
     * 测试区域管理
     * @params []
     * @return java.util.List<com.lizikj.api.vo.shop.param.ShopSettingParamVO>
     * @author zhoufe
     * @date 2017/9/13 15:15
     */
    private List<ShopSettingParamVO> assemblyDeskAreaTestData() {
        SHOP_SETTING_ID = 14L;

        List<ShopSettingParamVO> shopSettingUpdateParamVOS = new ArrayList<>();
        ShopSettingParamVO shopSettingUpdateParamVO = new ShopSettingParamVO();
        shopSettingUpdateParamVO.setShopSettingId(SHOP_SETTING_ID);
        shopSettingUpdateParamVO.setOpenStatus(OpenStatusEnum.CLOSE);
        shopSettingUpdateParamVO.setSettingCode(ShopSettingCodeEnum.SHOP_AREA_CODE.getCode());

        shopSettingUpdateParamVOS.add(shopSettingUpdateParamVO);

        logger.info("修改区域管理：{}", JSONObject.toJSONString(shopSettingUpdateParamVOS));
        return shopSettingUpdateParamVOS;
    }

    /**
     * 组装桌台管理和茶位费
     * @params []
     * @return java.util.List<com.lizikj.api.vo.shop.param.ShopSettingParamVO>
     * @author zhoufe
     * @date 2017/9/13 11:50
     */
    private List<ShopSettingParamVO> assemblyDeskManagerTeaSeatTestData() {

        List<ShopSettingParamVO> shopSettingParamVOS = assemblyTeaSeatFeeTestData();
        shopSettingParamVOS.addAll(assemblyDeskManagerTestData());
        return shopSettingParamVOS;
    }

    /**
     * 组装茶位费开关
     * @params []
     * @return java.util.List<com.lizikj.api.vo.shop.param.ShopSettingParamVO>
     * @author zhoufe
     * @date 2017/9/13 11:50
     */
    private List<ShopSettingParamVO> assemblyTeaSeatFeeTestData() {

        SHOP_SETTING_ID = 16L;

        List<ShopSettingParamVO> shopSettingUpdateParamVOS = new ArrayList<>();
        ShopSettingParamVO shopSettingUpdateParamVO = new ShopSettingParamVO();
        //shopSettingUpdateParamVO.setShopId(shopId);
        shopSettingUpdateParamVO.setShopSettingId(SHOP_SETTING_ID);
        shopSettingUpdateParamVO.setOpenStatus(OpenStatusEnum.OPEN);
        shopSettingUpdateParamVO.setSettingCode(ShopSettingCodeEnum.TEA_CODE.getCode());

        shopSettingUpdateParamVOS.add(shopSettingUpdateParamVO);

        logger.info("修改茶位费：{}", JSONObject.toJSONString(shopSettingUpdateParamVOS));
        return shopSettingUpdateParamVOS;
    }


    /**
     * 封装修改桌台管理测试数据
     * @params []
     * @return java.util.List<com.lizikj.api.vo.shop.param.ShopSettingParamVO>
     * @author zhoufe
     * @date 2017/8/30 15:00
     */
    private List<ShopSettingParamVO> assemblyDeskManagerTestData() {
        List<ShopSettingParamVO> shopSettingUpdateParamVOS = new ArrayList<>();
        ShopSettingParamVO shopSettingUpdateParamVO = new ShopSettingParamVO();
        shopSettingUpdateParamVO.setShopSettingId(24078L);
        shopSettingUpdateParamVO.setOpenStatus(OpenStatusEnum.CLOSE);
        shopSettingUpdateParamVO.setSettingCode(ShopSettingCodeEnum.DESK_CODE.getCode());

        shopSettingUpdateParamVOS.add(shopSettingUpdateParamVO);

        logger.info("修改桌台管理：{}", JSONObject.toJSONString(shopSettingUpdateParamVOS));
        return shopSettingUpdateParamVOS;
    }

    /**
     * 封装修改会员优惠方式测试数据
     * @params []
     * @return java.util.List<com.lizikj.api.vo.shop.param.ShopSettingParamVO>
     * @author zhoufe
     * @date 2017/8/30 15:00
     */
//    private List<ShopSettingParamVO> assemblyMemberDiscTypeTestData() {
//        SHOP_SETTING_ID = 20L;
//        SETTING_VALUE_ID = 7L;
//        List<ShopSettingParamVO> shopSettingUpdateParamVOS = new ArrayList<>();
//        ShopSettingParamVO shopSettingUpdateParamVO = new ShopSettingParamVO();
//        shopSettingUpdateParamVO.setShopSettingId(SHOP_SETTING_ID);
//        shopSettingUpdateParamVO.setSettingCode(ShopSettingCodeEnum.MEMBER_DISCOUNT_CODE.getCode());
//
//        ShopSettingValueParamVO shopSettingValueParamVO = new ShopSettingValueParamVO();
//
//        shopSettingValueParamVO.setSettingValueId(SETTING_VALUE_ID);
//
//        shopSettingValueParamVO.setDefaultStatus(DefaultStatusEnum.DEFAULT);
//
//        List<ShopSettingValueParamVO> shopSettingValueParamVOS = new ArrayList<>();
//        shopSettingValueParamVOS.add(shopSettingValueParamVO);
//
//        shopSettingUpdateParamVO.setShopSettingValues(shopSettingValueParamVOS);
//        shopSettingUpdateParamVOS.add(shopSettingUpdateParamVO);
//
//        logger.info("修改会员优惠方式：{}", JSONObject.toJSONString(shopSettingUpdateParamVOS));
//        return shopSettingUpdateParamVOS;
//    }

    @Test
    public void testInsertShopSetting(){


        ShopSettingParamVO shopSettingParamVO = assemblyCustomFeeTestData(AddUpdateEnum.ADD).get(0);

        Result<ShopSettingVO> shopSettingVOResult = shopSettingController.insertShopSetting(shopSettingParamVO);
        logger.info(JSONObject.toJSONString(shopSettingVOResult));


    }

    private List<ShopSettingParamVO> assemblyCustomFeeTestData(AddUpdateEnum addUpdateEnum) {
        List<ShopSettingParamVO> shopSettingParamVOS = new ArrayList<>();

        ShopSettingParamVO shopSettingParamVO = new ShopSettingParamVO();
        if (AddUpdateEnum.UPDATE.equals(addUpdateEnum)){
            shopSettingParamVO.setShopSettingId(99L);
        }
        shopSettingParamVO.setOpenStatus(OpenStatusEnum.OPEN);
        shopSettingParamVO.setType(ShopSettingTypeEnum.CUSTOM);
        //shopSettingParamVO.setShopId(ThreadLocalContext.getShopId());
        List<ShopSettingValueParamVO> shopSettingValueParamVOS = new ArrayList<>();

        ShopSettingValueParamVO shopSettingValueParamVO = new ShopSettingValueParamVO();
        if (AddUpdateEnum.UPDATE.equals(addUpdateEnum)) {
            shopSettingValueParamVO.setSettingValueId(32L);
        }
        shopSettingValueParamVO.setDefaultStatus(DefaultStatusEnum.DEFAULT);
        shopSettingValueParamVO.setFeeMode(ShopSettingFeeModeEnum.PEOPLE_MODE);
        shopSettingValueParamVO.setValueName("自定义费用1");
        shopSettingValueParamVO.setValue("10");
        shopSettingValueParamVOS.add(shopSettingValueParamVO);

        shopSettingParamVO.setShopSettingValues(shopSettingValueParamVOS);
        shopSettingParamVOS.add(shopSettingParamVO);
        return shopSettingParamVOS;
    }

    @Test
    public void testGetShopSettingByBizType(){
        Result<ShopSettingVO> shopSettingVOResult = shopSettingController.getShopSettingByBizType(BizTypeEnum.SHOP);
        logger.info("信息："+JSONObject.toJSONString(shopSettingVOResult));

    }

    @Test
    public void testDeleteShopSetting(){
        Result<Boolean> booleanResult = shopSettingController.deleteShopSetting(custom_shopSettingId);
        logger.info("信息："+JSONObject.toJSONString(booleanResult));


    }

    @Test
    public void testListChainShopSettings4Shop(){
        Result<List<ShopSettingVO>> listResult = shopSettingController.listChainShopSettings4Shop();
        logger.info("listChainShopSettings4Shop：{}", JSONObject.toJSONString(listResult));

    }

    @Test
    public void testJsonDataUpdateShopSetting(){

        shopId = 1143L;
        merchantId = 1143L;

        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, shopId);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_MERCHANT_ID, merchantId);

        String iosJson = "[{\"usedType\":\"OTHER\",\"shopSettingId\":24102,\"shopSettingValues\":[{\"valueCode\":10041," +
                "\"settingValueId\":10140,\"valueName\":\"先食后付\",\"defaultStatus\":\"UN_DEFAULT\",\"orderNo\":0,\"value\":\"1\"," +
                "\"shopSettingId\":24102,\"feeStatus\":\"NO_FEE\",\"settingCode\":1004},{\"valueCode\":10042,\"settingValueId" +
                "\":10141,\"valueName\":\"先付后食\",\"defaultStatus\":\"DEFAULT\",\"orderNo\":0,\"value\":\"2\"," +
                "\"shopSettingId\":24102,\"feeStatus\":\"NO_FEE\",\"settingCode\":1004}],\"settingName\":\"营业模式\"," +
                "\"multiSelectStatus\":\"ONE_SELECT\",\"type\":\"SYSTEM\",\"settingCode\":1004,\"orderNo\":15,\"settingTemplateId\":15,\"tips\":" +
                "\"提示信息\",\"shopId\":1143,\"bizType\":0,\"openStatus\":\"CLOSE\",\"mutexGroup\":15},{\"usedType\":\"OTHER\",\"shopSettingId" +
                "\":24103,\"shopSettingValues\":[],\"settingName\":\"茶位费\",\"multiSelectStatus\":" +
                "\"ONE_SELECT\",\"type\":\"SYSTEM\",\"settingCode\":1005,\"orderNo\":16,\"settingTemplateId\":16,\"tips\":\"提示信息\"," +
                "\"shopId\":1143,\"bizType\":0,\"openStatus\":\"CLOSE\",\"mutexGroup\":16},{\"usedType\":\"OTHER\",\"shopSettingId\":24104," +
                "\"shopSettingValues\":[{\"valueCode\":10061,\"feeMode\":\"DESK_MODE\",\"settingValueId\":10142,\"valueName\":\"费用\",\"defaultStatus\":\"DEFAULT\",\"orderNo\":0,\"value\":\"1\",\"shopSettingId\":24104,\"feeStatus\":\"IS_FEE\",\"settingCode\":1006}],\"settingName\":\"打包费\",\"multiSelectStatus\":\"ONE_SELECT\",\"type\":\"SYSTEM\",\"settingCode\":1006,\"orderNo\":17,\"settingTemplateId\":17,\"tips\":\"提示信息\",\"shopId\":1143,\"bizType\":0,\"openStatus\":\"OPEN\",\"mutexGroup\":17}]";
        List<ShopSettingParamVO> iosShopSettingVOS = JSONObject.parseArray(iosJson, ShopSettingParamVO.class);
        Result<List<ShopSettingVO>> listResult = shopSettingController.updateShopSettings(iosShopSettingVOS);
        logger.info("updateShopSettings:{}", JSONObject.toJSONString(listResult));

    }

    @Test
    public void testQueryShopSettings(){

        QueryShopSettingVO queryShopSettingVO = new QueryShopSettingVO();


        //queryShopSettingVO.setSettingName("开关");
        queryShopSettingVO.setShopId(8L);

        queryShopSettingVO.setRemoveStatus(RemoveStatusEnum.REMOVED);

        DateTime dateTime = new DateTime();

        DateTime start = dateTime.plusDays(-60);

        queryShopSettingVO.setStartCreateTime(start.toDate());
        queryShopSettingVO.setEndCreateTime(dateTime.toDate());


        queryShopSettingVO.setPageNum(1);
        queryShopSettingVO.setPageSize(10);

        Result<PageInfo<ShopSettingVO>> pageInfoResult = shopSettingController.queryShopSettings(queryShopSettingVO);
        if (logger.isInfoEnabled()){
            logger.info("---.{}", JSONObject.toJSONString(pageInfoResult));
        }
    }

}
