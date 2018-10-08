package com.lizikj.api.controller.order;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.lizikj.api.Bootstrap;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.order.*;
import com.lizikj.api.vo.order.param.*;
import com.lizikj.api.vo.order.param.query.OrderInfoQueryParamVO;
import com.lizikj.common.enums.PaymentChannelEnum;
import com.lizikj.common.enums.PaymentSceneEnum;
import com.lizikj.common.enums.PaymentTypeEnum;
import com.lizikj.common.number.RandomUtils;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.common.util.TradeNoUtil;
import com.lizikj.order.enums.*;
import com.lizikj.payment.enums.RefundSourceEnum;
import com.lizikj.payment.pay.enums.PayFlowTypeEnum;
import com.lizikj.payment.pay.enums.PayStatusEnum;
import com.lizikj.payment.pay.enums.RefundEnableEnum;
import com.lizikj.payment.refund.enums.RefundStatusEnum;
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

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoufe
 * @date 2017/8/12 15:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=Bootstrap.class)//, properties={"spring.profiles.active=dev"}
@ActiveProfiles({"dev"})//dev 环境，redis.singleton：redis集群与否
public class TestSyncOrderController {


    private static final Logger logger = LoggerFactory.getLogger(TestSyncOrderController.class);

    private static long shopId = 1L;
    private static long merchantId = 1L;
    private String SN = "N9NL10093833";


    @Autowired
    private OrderController orderController;
    private static Long shopAreaId = 1L;
    private static Long areaDeskId = 1L;
    private Long orderId = 1L;
    private String orderNo = "D2017093010542387584440265";//"D2017092216534597260501930";//"D2017092120122472412518936";

    @Before
    public void init(){
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, shopId);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_MERCHANT_ID, merchantId);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LZ_DID, SN);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_MERCHANT_MEMBER_ID, 34L);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_MEMBER_ID, 20L);


    }

    @Test
    public void testSync(){

        //String json = "[{\"areaDeskId\":49,\"benefitAmount\":0,\"costAmount\":0,\"createTime\":1510885163793,\"cutPriceFlag\":\"UNABLE\",\"deskInfo\":{\"areaDeskId\":49,\"areaDeskName\":\"013\",\"orderSort\":\"6\"},\"invoiceStatus\":\"NO\",\"memberId\":0,\"merchantId\":3,\"minCutAmount\":0,\"needAmount\":6,\"orderBizType\":\"MERCHANDISE\",\"orderId\":253,\"orderItems\":[{\"appendDishStatus\":false,\"cutPriceFlag\":\"UNABLE\",\"freeDishStatus\":false,\"goodsId\":\"5a0563a4c641722cefcf2aa7\",\"goodsName\":\"青菜\",\"merchandiseVersion\":1510302628622,\"operationType\":\"NONE\",\"orderItemId\":505,\"orderVersion\":1510885163830,\"packStatus\":\"UN_PACK\",\"printStatus\":false,\"recStatus\":false,\"remark\":\"\",\"saleAmount\":0,\"sellPrice\":1,\"sellVolume\":1,\"skuId\":55,\"tempDishStatus\":false,\"totalAmount\":1,\"unit\":\"份\",\"weight\":0,\"weightStatus\":\"NO\"},{\"appendDishStatus\":false,\"cutPriceFlag\":\"UNABLE\",\"freeDishStatus\":false,\"goodsId\":\"5a05631fc641722cefcf2aa6\",\"goodsName\":\"白米饭\",\"merchandiseVersion\":1510302495905,\"operationType\":\"NONE\",\"orderItemId\":506,\"orderVersion\":1510885163830,\"packStatus\":\"UN_PACK\",\"printStatus\":false,\"recStatus\":false,\"remark\":\"\",\"saleAmount\":0,\"sellPrice\":2,\"sellVolume\":1,\"skuId\":54,\"tempDishStatus\":false,\"totalAmount\":2,\"unit\":\"份\",\"weight\":0,\"weightStatus\":\"NO\"},{\"appendDishStatus\":false,\"cutPriceFlag\":\"UNABLE\",\"freeDishStatus\":false,\"goodsId\":\"5a0567d1a165f00a70ae5a2a\",\"goodsName\":\"小炒肉\",\"merchandiseVersion\":1510303697240,\"operationType\":\"NONE\",\"orderItemId\":507,\"orderVersion\":1510885163830,\"packStatus\":\"UN_PACK\",\"printStatus\":false,\"recStatus\":false,\"remark\":\"\",\"saleAmount\":0,\"sellPrice\":3,\"sellVolume\":1,\"skuId\":56,\"tempDishStatus\":false,\"totalAmount\":3,\"unit\":\"份\",\"weight\":0,\"weightStatus\":\"NO\"}],\"orderNo\":\"D2017111710192383032353219\",\"orderSource\":\"POS\",\"orderStatus\":\"FINISHED\",\"orderTime\":1510885163830,\"orderType\":\"PAY_FIRST\",\"packStatus\":\"UN_PACK\",\"payAmount\":0,\"payInfo\":{\"payAmount\":6,\"payFlowList\":[{\"amount\":6,\"bizType\":1,\"channelCode\":1,\"innerTradeNo\":\"D2017111710192383032353219676\",\"orderNo\":\"D2017111710192383032353219\",\"payFlowType\":1,\"payStatus\":1,\"payVersion\":1510885237343,\"paymentTypeCode\":4,\"sceneCode\":7,\"tradeTime\":1510885237343},{\"accountDetail\":{\"account\":\"6235290025011060524\",\"cashierId\":4,\"merchantId\":3,\"payInfo\":\"{\\\"cardNo\\\":\\\"6235290025011060524\\\",\\\"date\\\":\\\"1117\\\",\\\"referenceNo\\\":\\\"732110831982\\\",\\\"voucherNo\\\":\\\"000238\\\"}\",\"shopId\":2,\"snNum\":\"N9NL10093820\"},\"amount\":6,\"bizType\":1,\"channelCode\":1,\"innerTradeNo\":\"D2017111710192383032353219290\",\"orderNo\":\"D2017111710192383032353219\",\"outTradeNo\":\"\",\"payFlowType\":1,\"payStatus\":3,\"payVersion\":1510885326060,\"paymentTypeCode\":4,\"sceneCode\":7,\"tradeTime\":1510885430324}],\"totalAmount\":6},\"payStatus\":\"PAY_SUCCESS\",\"peoples\":4,\"personInfo\":{\"orderPersonId\":4,\"orderPersonName\":\"张姐\",\"orderPersonRoleName\":\"店长\"},\"refundStatus\":\"NORMAL\",\"removeStatus\":\"UN_REMOVED\",\"shopId\":2,\"snNum\":\"N9NL10093820\",\"totalAmount\":6,\"userId\":0,\"wait4Serving\":false}]";

        String json = "[{\"areaDeskId\":4262,\"benefitAmount\":0,\"buyerInfo\":{\"memberId\":0,\"mobile\":\"\",\"userId\":0},\"costAmount\":0,\"createTime\":\"1512630485000\",\"cutPriceFlag\":\"UNABLE\",\"deskInfo\":{\"areaDeskId\":4262,\"areaDeskName\":\"饿货6\",\"customDeskNumber\":\"\",\"orderSort\":\"4\"},\"invoiceStatus\":\"NO\",\"isOffline\":false,\"memberId\":0,\"merchantId\":125,\"merchantMemberId\":0,\"minCutAmount\":0,\"mobile\":\"\",\"needAmount\":1,\"orderBizType\":\"MERCHANDISE\",\"orderId\":591,\"orderItems\":[{\"appendDishStatus\":false,\"cutPriceFlag\":\"UNABLE\",\"freeDishStatus\":false,\"goodsId\":\"5a0aa01d340c1912194c10a2\",\"goodsName\":\"0.01菜\",\"isChange\":false,\"isCountChange\":false,\"isNewAppend\":false,\"itemAttrList\":[],\"mChangedVolume\":0,\"merchandiseVersion\":\"1510645789843\",\"orderItemId\":1530,\"orderVersion\":1512630484983,\"packStatus\":\"UN_PACK\",\"printStatus\":false,\"recStatus\":false,\"remark\":\"\",\"saleAmount\":0,\"sellPrice\":1,\"sellVolume\":1,\"skuId\":5472,\"snackTotalPrice\":0,\"tempDishStatus\":false,\"totalAmount\":1,\"unit\":\"份\",\"weight\":0,\"weightStatus\":\"NO\"}],\"orderNo\":\"D2017120715080498254863151\",\"orderSource\":\"POS\",\"orderStatus\":\"FINISHED\",\"orderTime\":\"1512630485000\",\"orderType\":\"EAT_FIRST\",\"packStatus\":\"UN_PACK\",\"payAmount\":0,\"payInfo\":{\"benefitAmount\":0,\"payAmount\":0,\"payFlowList\":[{\"questionOrderRemark\":\"{dsfsdfsdfsd}\",\"accountDetail\":{\"account\":\"\",\"bankCardType\":\"1\",\"cashierId\":\"173\",\"merchantId\":\"125\",\"payInfo\":\"{\\\"mername\\\":\\\"星际联盟开发者12\\\",\\\"merid\\\":\\\"856020000000501\\\",\\\"termid\\\":\\\"95031227\\\",\\\"batchno\\\":\\\"000001\\\",\\\"systraceno\\\":\\\"000092\\\",\\\"orderid_scan\\\":\\\"000000015536599\\\",\\\"translocaltime\\\":\\\"150807\\\",\\\"translocaldate\\\":\\\"20171207\\\",\\\"transamount\\\":\\\"0.01\\\"}\",\"shopId\":108,\"snNum\":\"N7NL00235013\"},\"amount\":1,\"bizType\":\"MERCHANDISE\",\"channelCode\":\"9\",\"innerTradeNo\":\"P2017120715080498254863151400\",\"orderNo\":\"D2017120715080498254863151\",\"outTradeNo\":\"000092\",\"payFlowType\":\"ORDER_CALL\",\"payStatus\":\"PAY_SUCCESS\",\"payVersion\":\"1512630504591\",\"paymentTypeCode\":\"PAYMENT_TYPE_ALIPAY\",\"sceneCode\":\"4\",\"tradeTime\":\"1512630513790\"}],\"snNum\":\"N7NL00235013\",\"totalAmount\":1},\"payStatus\":\"PAY_SUCCESS\",\"peoples\":3,\"personInfo\":{\"orderPersonId\":173,\"orderPersonName\":\"你铁哥\",\"orderPersonRoleName\":\"店长\"},\"posInfo\":{\"snNum\":\"N7NL00235013\"},\"refundAmount\":0,\"refundStatus\":\"NORMAL\",\"remark\":\"\",\"removeStatus\":\"UN_REMOVED\",\"shopId\":108,\"shopInfo\":{\"logoPicId\":76073,\"merchantId\":125,\"merchantName\":\"威哥的商户\",\"shopId\":108,\"shopName\":\"威哥的门店\"},\"snNum\":\"N7NL00235013\",\"totalAmount\":1,\"updateTime\":\"1512630484000\",\"userId\":0,\"wait4Serving\":false}]";

        List<OrderInfoVO> orderInfoVOS = JSONObject.parseArray(json, OrderInfoVO.class);
        Result<Boolean> booleanResult = orderController.syncOrder(orderInfoVOS);
        logger.info("ddd："+ booleanResult);
    }

    @Test
    public void testQuerySyncOrder(){

        QuerySyncOrderParamVO querySyncOrderParamVO = new QuerySyncOrderParamVO();


        querySyncOrderParamVO.setShopId(108L);

        querySyncOrderParamVO.setPageNum(1);
        querySyncOrderParamVO.setPageSize(10);

        Result<PageInfo<SyncPosOrderInfoVO>> arg = orderController.querySyncOrder(querySyncOrderParamVO);
        logger.info("dddd:-->{}", JSONObject.toJSONString(arg));
    }


}
