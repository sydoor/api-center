package com.lizikj.api.controller.order;

import java.util.ArrayList;
import java.util.List;

import com.lizikj.api.vo.order.*;
import com.lizikj.common.util.CollectionUtils;
import com.lizikj.member.dto.MerchantMemberDTO;
import com.lizikj.member.facade.IMerchantMemberFacade;
import com.lizikj.order.enums.*;
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
import com.github.pagehelper.PageInfo;
import com.lizikj.api.Bootstrap;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.order.param.OrderInfoParamVO;
import com.lizikj.api.vo.order.param.OrderItemAttrParamVO;
import com.lizikj.api.vo.order.param.OrderItemAttrValueParamVO;
import com.lizikj.api.vo.order.param.OrderItemParamVO;
import com.lizikj.api.vo.order.param.SyncOrderContentParamVO;
import com.lizikj.api.vo.order.param.SyncOrderParamVO;
import com.lizikj.api.vo.order.param.query.OrderInfoQueryParamVO;
import com.lizikj.common.enums.PaymentChannelEnum;
import com.lizikj.common.enums.PaymentSceneEnum;
import com.lizikj.common.enums.PaymentTypeEnum;
import com.lizikj.common.number.RandomUtils;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.common.util.TradeNoUtil;
import com.lizikj.payment.enums.RefundSourceEnum;
import com.lizikj.payment.pay.enums.PayFlowTypeEnum;
import com.lizikj.payment.pay.enums.PayStatusEnum;
import com.lizikj.payment.pay.enums.RefundEnableEnum;
import com.lizikj.payment.refund.enums.RefundStatusEnum;

/**
 * @author zhoufe
 * @date 2017/8/12 15:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=Bootstrap.class)//, properties={"spring.profiles.active=dev"}
@ActiveProfiles({"dev"})//dev 环境，redis.singleton：redis集群与否
public class TestOrderController {

    private static long shopId = 1L;
    private static long merchantId = 1L;
    private String SN = "N9NL10093833";


    private static Logger logger = LoggerFactory.getLogger(TestOrderController.class);

    @Autowired
    private OrderController orderController;
    private static Long shopAreaId = 1L;
    private static Long areaDeskId = 1L;
    private Long orderId = 1L;
    private String orderNo = "D2017093010542387584440265";//"D2017092216534597260501930";//"D2017092120122472412518936";
    private Long merchantMemberId;
    private DiscountTypeNodeEnum discountSelected;

    @Autowired
    private IMerchantMemberFacade merchantMemberFacade;

    @Before
    public void init(){
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, shopId);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_MERCHANT_ID, merchantId);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LZ_DID, SN);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_MERCHANT_MEMBER_ID, 34L);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_MEMBER_ID, 20L);


    }

    /**
     * 测试POS新增订单
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/9/6 12:06
     */
    @Test
    public void testAaddOrder(){
        OrderInfoParamVO orderInfoParamVO = new OrderInfoParamVO();

        orderInfoParamVO.setAreaDeskId(areaDeskId);
        //orderInfoParamVO.setCustomDeskNumber();//如果没有传桌台ID可以传
        orderInfoParamVO.setOrderSource(OrderSourceEnum.POS);
        orderInfoParamVO.setPackStatus(PackStatusEnum.PACK);
        orderInfoParamVO.setPeoples(8L);
        orderInfoParamVO.setRemark("测试订单备注");
        orderInfoParamVO.setWait4Serving(true);

        List<OrderItemParamVO> itemParamList = assemblyItemList();
        orderInfoParamVO.setItemParamList(itemParamList);

        Result<OrderInfoVO> orderInfoVOResult = orderController.addOrder(orderInfoParamVO);
        logger.info("addOrder:{}", orderInfoVOResult);
    }


   @Test
   public void testGetOrderInfoByOrderId(){
       //orderId = 262756L;
       orderId = 887016L;
       Result<OrderInfoVO> infoVOResult = orderController.getOrderInfoByOrderId(orderId, OrderInfoDetailEnum.ALL.getCode());
       logger.info("testGetOrderInfoByOrderId:{}", JSONObject.toJSONString(infoVOResult));
   }

    @Test
    public void testGetOrderInfoByOrderNo(){
        //orderNo = "D2017100915030687221052612";
        //orderNo = "D2017101014534774481207817";
        //orderNo = "D2017101120384473320492357";
        //orderNo = "D2017102009482800728504521";
        //orderNo = "D2017102519250817477310348";
        //orderNo = "D2017102711414599685934492";
        orderNo = "D2017111023025159015896763";
        Result<OrderInfoVO> infoVOResult = orderController.getOrderInfoByOrderNo(orderNo, OrderInfoDetailEnum.ALL.getCode());
        logger.info("testGetOrderInfoByOrderNo:{}", JSONObject.toJSONString(infoVOResult));
    }

    @Test
    public void testListOrderInfo(){
        OrderInfoQueryParamVO queryParam = new OrderInfoQueryParamVO();
        shopId = 108L;

        queryParam.setShopId(shopId);
        queryParam.setOrderStatus(OrderStatusEnum.FINISHED);
        //queryParam.setOrderType(OrderTypeEnum.EAT_FIRST);
        //queryParam.setOrderSource(OrderSourceEnum.POS);

        //queryParam.setOrderNo("028194");
        List<OrderBizTypeEnum> orderBizTypes = new ArrayList<>();
        orderBizTypes.add(OrderBizTypeEnum.MERCHANDISE);
        queryParam.setOrderBizTypes(orderBizTypes);

        queryParam.setCurrentPage(1);
        queryParam.setPageSize(30);
        Result<PageInfo<OrderInfoVO>> pageInfoResult = orderController.listOrderInfo(queryParam);
        logger.info("listOrderInfo:{}", JSONObject.toJSONString(pageInfoResult));

    }




    private List<OrderItemParamVO> assemblyItemList() {
        List<OrderItemParamVO> orderItemParamVOS = new ArrayList<>();
        OrderItemParamVO orderItemParamVO = setPackage();//设置一个套餐
        orderItemParamVOS.add(orderItemParamVO);

        return orderItemParamVOS;
    }

    /**
     * 设置一个套餐
     * @params []
     * @return com.lizikj.api.vo.order.param.OrderItemParamVO
     * @author zhoufe
     * @date 2017/9/14 17:55
     */
    private OrderItemParamVO setPackage() {
        //一个套餐
        OrderItemParamVO orderItemParamVO = new OrderItemParamVO();
        //orderItemParamVO.setCartGoodsId();
        //orderItemParamVO.setOrderItemId();
        orderItemParamVO.setFreeDishStatus(true);
        orderItemParamVO.setGoodsId("59b91ced340c192ebf4378b3");//goodsName=阿基拉
        orderItemParamVO.setMerchandiseVersion(1505303789618L);
        orderItemParamVO.setNums(2d);
        orderItemParamVO.setPackStatus(PackStatusEnum.PACK);
        orderItemParamVO.setRemark("1505303789618");
        orderItemParamVO.setSkuId(1690516949l);
        //orderItemParamVO.setSkuValueIds();//组合多选型套餐才用传
        //orderItemParamVO.setTempName("测试临时菜");
        //orderItemParamVO.setTempPrice(1000L);
        List<OrderItemAttrParamVO> orderItemAttrParamVOS = new ArrayList<>();


        //如果是普通商品，套餐，解析其sku，可能是属性值ID，商品ID
        //不需要前端传入这个
        //NORMAL_ATTR_ID(-88805, "普通商品"),
        //PACKAGE_ATTR_ID(-88806, "套餐"),

        //需要前端传入这个
        //SNACK_ATTR_ID(-88801, "加料"),
        //FLAVOR_ATTR_ID(-88802, "口味"),
        //CUSTOM_REMARK_ATTR_ID(-88803, "自定义备注"),
        //COOKING_METHODS_ATTR_ID(-88804, "做法"),;

        //OrderItemAttrParamVO snackAttrAndValueAttrParamVO = getSnackAttrAndValue();//套餐没有加料
        //orderItemAttrParamVOS.add(snackAttrAndValueAttrParamVO);

        OrderItemAttrParamVO flavorAttrAndValueAttrParamVO = getFlavorAttrAndValue();
        orderItemAttrParamVOS.add(flavorAttrAndValueAttrParamVO);

        OrderItemAttrParamVO customRemarkAttrParamVO = getCustomRemarkAttrAndValue();
        orderItemAttrParamVOS.add(customRemarkAttrParamVO);

        OrderItemAttrParamVO cookingMethodAttrParamVO = getCookingMethodAttrAndValue();//套餐没有做法?
        orderItemAttrParamVOS.add(cookingMethodAttrParamVO);


        orderItemParamVO.setItemAttrParamList(orderItemAttrParamVOS);

        List<Long> skuValueIds = new ArrayList<>();
        //3 x 2
        skuValueIds.add(638575335l);
        skuValueIds.add(1195088452l);
        //4 x 3
        skuValueIds.add(167177174l);
        skuValueIds.add(288635760l);
        skuValueIds.add(638575335l);
        orderItemParamVO.setSkuValueIds(skuValueIds);

        return orderItemParamVO;
    }

    /**
     * 设置做法
     * @params []
     * @return com.lizikj.api.vo.order.param.OrderItemAttrParamVO
     * @author zhoufe
     * @date 2017/9/14 17:53
     */
    private OrderItemAttrParamVO getCookingMethodAttrAndValue() {
        String attrId = String.valueOf(AttrRegardedEnum.COOKING_METHODS_ATTR_ID.getCode());
        //String attrValueId = null;//自定义备注没有ID

        OrderItemAttrParamVO orderItemAttrParamVO = new OrderItemAttrParamVO();
        orderItemAttrParamVO.setAttrId(attrId);

        OrderItemAttrValueParamVO orderItemAttrValueParamVO = new OrderItemAttrValueParamVO();
        //orderItemAttrValueParamVO.setAttrValueId(attrValueId);
        orderItemAttrValueParamVO.setAttrValueName("炒");
        List<OrderItemAttrValueParamVO> orderItemAttrValueParamVOS = new ArrayList<>();
        orderItemAttrValueParamVOS.add(orderItemAttrValueParamVO);

        orderItemAttrParamVO.setItemAttrValueParamList(orderItemAttrValueParamVOS);

        return orderItemAttrParamVO;
    }

    /**
     * 设置自定义备注
     * @params []
     * @return com.lizikj.api.vo.order.param.OrderItemAttrParamVO
     * @author zhoufe
     * @date 2017/9/14 17:53
     */
    private OrderItemAttrParamVO getCustomRemarkAttrAndValue() {
        String attrId = String.valueOf(AttrRegardedEnum.CUSTOM_REMARK_ATTR_ID.getCode());
        //String attrValueId = null;//自定义备注没有ID

        OrderItemAttrParamVO orderItemAttrParamVO = new OrderItemAttrParamVO();
        orderItemAttrParamVO.setAttrId(attrId);

        OrderItemAttrValueParamVO orderItemAttrValueParamVO = new OrderItemAttrValueParamVO();
        //orderItemAttrValueParamVO.setAttrValueId(attrValueId);
        orderItemAttrValueParamVO.setAttrValueName("自定义备注测试1");
        List<OrderItemAttrValueParamVO> orderItemAttrValueParamVOS = new ArrayList<>();
        orderItemAttrValueParamVOS.add(orderItemAttrValueParamVO);

        orderItemAttrParamVO.setItemAttrValueParamList(orderItemAttrValueParamVOS);

        return orderItemAttrParamVO;
    }
    /**
     * 设置加料
     * @params []
     * @return com.lizikj.api.vo.order.param.OrderItemAttrParamVO
     * @author zhoufe
     * @date 2017/9/14 17:53
     */
    private OrderItemAttrParamVO getSnackAttrAndValue() {
        String attrId = String.valueOf(AttrRegardedEnum.SNACK_ATTR_ID.getCode());
        String attrValueId = "xxxx";//mongodb的ID

        OrderItemAttrParamVO orderItemAttrParamVO = new OrderItemAttrParamVO();
        orderItemAttrParamVO.setAttrId(attrId);

        OrderItemAttrValueParamVO orderItemAttrValueParamVO = new OrderItemAttrValueParamVO();
        orderItemAttrValueParamVO.setAttrValueId(attrValueId);
        List<OrderItemAttrValueParamVO> orderItemAttrValueParamVOS = new ArrayList<>();
        orderItemAttrValueParamVOS.add(orderItemAttrValueParamVO);

        orderItemAttrParamVO.setItemAttrValueParamList(orderItemAttrValueParamVOS);

        return orderItemAttrParamVO;
    }

    /**
     * 设置口味
     * @params []
     * @return com.lizikj.api.vo.order.param.OrderItemAttrParamVO
     * @author zhoufe
     * @date 2017/9/14 17:53
     */
    private OrderItemAttrParamVO getFlavorAttrAndValue() {
        String attrId = String.valueOf(AttrRegardedEnum.FLAVOR_ATTR_ID.getCode());
        String attrValueId = "5";//mysql的ID

        OrderItemAttrParamVO orderItemAttrParamVO = new OrderItemAttrParamVO();
        orderItemAttrParamVO.setAttrId(attrId);

        OrderItemAttrValueParamVO orderItemAttrValueParamVO = new OrderItemAttrValueParamVO();
        orderItemAttrValueParamVO.setAttrValueId(attrValueId);
        List<OrderItemAttrValueParamVO> orderItemAttrValueParamVOS = new ArrayList<>();
        orderItemAttrValueParamVOS.add(orderItemAttrValueParamVO);

        orderItemAttrParamVO.setItemAttrValueParamList(orderItemAttrValueParamVOS);

        return orderItemAttrParamVO;
    }

    /**
     * 测试订单同步
     * @params []
     * @return void
     * @author zhoufe
     * @date 2017/9/28 16:47
     */
    @Test
    public void testSyncOrder() throws InterruptedException {

        //List<SyncOrderParamVO> syncOrderparamVOS = createSyncOrderparamVOS(5);

//        String text = "[{\"createTime\":\"1510023468755\",\"isOffline\":false,\"merchantId\":1119," +
//                "\"orderNo\":\"D2017110710574875132377801\",\"orderBizType\":\"MONEY\",\"orderSource\":\"QUICK\",\"orderStatus\":\"WAIT_PAY\"," +
//                "\"payInfo\":{\"payAmount\":0,\"payFlowList\":[{\"amount\":0,\"channelCode\":\"0\",\"innerTradeNo\":\"P2017110710574875132377801439\",\"orderNo\":\"D2017110710574875132377801\"," +
//                "\"payStatus\":\"PAYING\",\"paymentTypeCode\":\"PAYMENT_TYPE_SWIPECARD\",\"sceneCode\":\"0\"},{\"accountDetail\":{\"account\":\"622848******0478\"," +
//                "\"cashierId\":\"2334\",\"payInfo\":\"{\\\"cardNo\\\":\\\"622848******0478\\\",\\\"referenceNo\\\":\\\"201711070349812\\\"," +
//                "\\\"voucherNo\\\":\\\"000070\\\"}\",\"snNum\":\"N9NL10087249\"},\"amount\":1,\"channelCode\":\"7\"" +
//                ",\"innerTradeNo\":\"P2017110710574875132377801439\",\"orderNo\":\"D2017110710574875132377801\",\"payStatus\":\"PAY_SUCCESS\",\"" +
//                "paymentTypeCode\":\"PAYMENT_TYPE_SWIPECARD\",\"sceneCode\":\"7\",\"tradeTime\":\"1510023554247\"}],\"totalAmount\":0}," +
//                "\"payStatus\":\"PAY_SUCCESS\",\"peoples\":-1,\"shopId\":1120,\"totalAmount\":0}]";


        //String text = "[{\"createTime\":\"1510039708390\",\"isOffline\":false,\"merchantId\":1119,\"orderBizType\":\"MONEY\",\"orderNo\":\" "+ genPosOrderNo +"\",\"orderSource\":\"QUICK\",\"orderStatus\":\"FINISHED\",\"orderTime\":\"1510039708390\",\"orderType\":\"NORMAL\",\"payInfo\":{\"payAmount\":1,\"payFlowList\":[{\"accountDetail\":{\"account\":\"622848******0478\",\"cashierId\":\"2334\",\"merchantId\":\"1119\",\"payInfo\":\"{\\\"cardNo\\\":\\\"622848******0478\\\",\\\"referenceNo\\\":\\\"201711070349883\\\",\\\"voucherNo\\\":\\\"000078\\\"}\",\"shopId\":1120,\"snNum\":\"N9NL10087249\"},\"amount\":1,\"bizType\":\"MONEY\",\"channelCode\":\"7\",\"innerTradeNo\":\"P2017110715280927468977302999\",\"orderNo\":\" "+ genPosOrderNo +"\",\"outTradeNo\":\"201711070349883\",\"payFlowType\":\"POS_CALL\",\"payStatus\":\"PAY_SUCCESS\",\"paymentTypeCode\":\"PAYMENT_TYPE_SWIPECARD\",\"sceneCode\":\"7\",\"tradeTime\":\"1510039750361\"}],\"totalAmount\":1},\"payStatus\":\"PAY_SUCCESS\",\"peoples\":-1,\"shopId\":1120,\"totalAmount\":1,\"needAmount\":1}]";
       
        //生成订单号
        String genPosOrderNo = TradeNoUtil.genOrderNo();
        //测试疑问订单备注
        
        String text ="[{\"createTime\":\"1510039708390\",\"isOffline\":false,\"merchantId\":1119,\"orderBizType\":\"MONEY\",\"orderNo\":\" "+ genPosOrderNo +"\",\"orderSource\":\"QUICK\",\"orderStatus\":\"FINISHED\",\"orderTime\":\"1510039708390\",\"orderType\":\"NORMAL\",\"payInfo\":{\"payAmount\":1,\"payFlowList\":[{\"accountDetail\":{\"account\":\"622848******0478\",\"cashierId\":\"2334\",\"merchantId\":\"1119\",\"payInfo\":\"{\\\"cardNo\\\":\\\"622848******0478\\\",\\\"referenceNo\\\":\\\"201711070349883\\\",\\\"voucherNo\\\":\\\"000078\\\"}\",\"shopId\":1120,\"snNum\":\"N9NL10087249\"},\"amount\":1,\"bizType\":\"MONEY\",\"channelCode\":\"7\",\"innerTradeNo\":\"P2017110715280927468977302999\",\"orderNo\":\" "+ genPosOrderNo +"\",\"outTradeNo\":\"201711070349883\",\"payFlowType\":\"POS_CALL\",\"payStatus\":\"PAY_SUCCESS\",\"paymentTypeCode\":\"PAYMENT_TYPE_SWIPECARD\",\"sceneCode\":\"7\",\"tradeTime\":\"1510039750361\"}],\"totalAmount\":1},\"payStatus\":\"PAY_SUCCESS\",\"peoples\":-1,\"shopId\":1120,\"totalAmount\":1,\"needAmount\":1}]";
        logger.info("json:{}", text);
        List<OrderInfoVO> orderInfoVOS = getOrderInfoVOS(genPosOrderNo, text);
        Result<Boolean> booleanResult = orderController.syncOrder(orderInfoVOS);
        if (logger.isInfoEnabled()){
            logger.info("booleanResult:{}", JSONObject.toJSONString(booleanResult));
        }


    }

    /**
     * 产生退款流水测试数据
     * @params [genPosOrderNo, text]
     * @return java.util.List<com.lizikj.api.vo.order.OrderInfoVO>
     * @author zhoufe
     * @date 2018/6/29 10:04
     */
    private List<OrderInfoVO> getOrderInfoVOS(String genPosOrderNo, String text) {
        List<OrderInfoVO> orderInfoVOS = JSONObject.parseArray(text, OrderInfoVO.class);

        for (OrderInfoVO orderInfoVO : orderInfoVOS) {
            List<RefundOrderVO> refundOrders = orderInfoVO.getRefundOrders();
            if (! CollectionUtils.isListBlank(refundOrders)){
                continue;
            }
            List<RefundOrderVO> newRefundOrders = new ArrayList<>();

            RefundOrderVO refundOrderVO = new RefundOrderVO();

            //refundOrderVO.setRefundId();
            refundOrderVO.setInnerRefundNo("IR"+genPosOrderNo);
            refundOrderVO.setOutRefundNo("OR"+genPosOrderNo);
            refundOrderVO.setOrderNo(genPosOrderNo);
            refundOrderVO.setInnerTradeNo("P"+genPosOrderNo);
            refundOrderVO.setMerchantId(1L);
            refundOrderVO.setMerchantName("测试商户名称001");
            refundOrderVO.setShopId(1L);
            refundOrderVO.setShopName("测试店铺001");
            refundOrderVO.setUid(1L);
            refundOrderVO.setCashierId(1L);
            refundOrderVO.setMemberId(1L);
            refundOrderVO.setMerchantMemberId(1L);
            refundOrderVO.setRefundAmount(1L);
            refundOrderVO.setRefundType(RefundTypeEnum.CASH_REFUND.getCode());
            refundOrderVO.setRefundAmountType(RefundAmountTypeEnum.ALL.getCode());
            refundOrderVO.setChannelCode(PaymentChannelEnum.PAY_CHANNEL_GUOTONG.getCode());
            refundOrderVO.setPaymentTypeCode(PaymentTypeEnum.PAYMENT_TYPE_CASH.getCode());
            refundOrderVO.setSceneCode(PaymentSceneEnum.PAYMENT_TYPE_CASHE.getCode());
            refundOrderVO.setRefundSource(RefundSourceEnum.MERCHANT_REFUND.getType());
            refundOrderVO.setStatus(RefundStatusEnum.SUCCESS.getStatus());
            refundOrderVO.setRemark(null);
            refundOrderVO.setQuestionOrderRemark("\"{\\\"test1\\\":\\\"123\\\",\\\"test2\\\":\\\"456\\\"}\"");
            //refundOrderVO.setQueryCount();
            //refundOrderVO.setNextQueryTime();
            //refundOrderVO.setNoticeStatus();
            //refundOrderVO.setDetailList();
            refundOrderVO.setOrderBizType(OrderBizTypeEnum.MERCHANDISE.getBizType());
            //refundOrderVO.setRefundOrderItemList();
            refundOrderVO.setRefundTime(new DateTime().toDate());

            newRefundOrders.add(refundOrderVO);
            orderInfoVO.setRefundOrders(newRefundOrders);
        }
        return orderInfoVOS;
    }

    /**
     * 组装测试pos订单 list
     * @params [len]
     * @return java.util.List<com.lizikj.api.vo.order.param.SyncOrderParamVO>
     * @author zhoufe
     * @date 2017/9/29 15:46
     */
    private List<SyncOrderParamVO> createSyncOrderparamVOS(int len) {

        List<SyncOrderParamVO> syncOrderParamVOS = new ArrayList<>();
        for (int i = 0; i < len; i++){
            SyncOrderParamVO syncOrderParamVO = createSyncOrderparamVO();
            syncOrderParamVOS.add(syncOrderParamVO);
        }
        return syncOrderParamVOS;
    }

    private SyncOrderParamVO createSyncOrderparamVO() {

        SyncOrderParamVO syncOrderParamVO = new SyncOrderParamVO();

        DateTime currentDateTime = new DateTime();
        String randomNum = RandomUtils.getRandomNum(11);
        String posOrderNo = TradeNoUtil.genPosOrderNo(SN);
        syncOrderParamVO.setOrderNo(posOrderNo);
        syncOrderParamVO.setOrderType(OrderTypeEnum.EAT_FIRST);
        syncOrderParamVO.setOrderStatus(OrderStatusEnum.FINISHED);
        syncOrderParamVO.setOrderSource(OrderSourceEnum.POS);
        syncOrderParamVO.setOrderBizType(OrderBizTypeEnum.MERCHANDISE);
        SyncOrderContentParamVO syncOrderContentParamVO = new SyncOrderContentParamVO();

        syncOrderParamVO.setSyncOrderContent(syncOrderContentParamVO);
        syncOrderParamVO.setOrderPersonId(1L);
        syncOrderParamVO.setOrderTime(currentDateTime.toDate());
        syncOrderParamVO.setRecTime(currentDateTime.toDate());
        syncOrderParamVO.setCloseStatus(CloseStatusEnum.OPENED);
        syncOrderParamVO.setInvoiceStatus(InvoiceStatusEnum.NO);
        syncOrderParamVO.setRemark("测试备注"+randomNum);
        syncOrderParamVO.setMobile(randomNum);
        syncOrderParamVO.setMerchantId(1L);
        syncOrderParamVO.setTotalAmount(10000L);
        syncOrderParamVO.setNeedAmount(9000L);
        syncOrderParamVO.setCostAmount(4000L);
        syncOrderParamVO.setBenefitAmount(1000L);
        syncOrderParamVO.setPayAmount(9000L);
        syncOrderParamVO.setRefundAmount(0L);
        syncOrderParamVO.setSort(randomNum);
        syncOrderParamVO.setPayStatus(PayStatusEnum.PAY_SUCCESS);
        syncOrderParamVO.setRefundStatus(RefundStatusEnum.NORMAL);
        syncOrderParamVO.setPackStatus(PackStatusEnum.UN_PACK);
        syncOrderParamVO.setPeoples(8L);
        syncOrderParamVO.setCustomDeskNumber(null);
        syncOrderParamVO.setAreaDeskId(1L);

        syncOrderContentParamVO.setOrderDiscounts(assemblyDiscounts(syncOrderParamVO, 5));
        syncOrderContentParamVO.setPayFlows(assemblyPayFlows(syncOrderParamVO, 5));
        syncOrderContentParamVO.setRefundOrders(assemblyRefunds(syncOrderParamVO, 5));

        List<OrderItemParamVO> itemParamList = assemblyItemList();
        syncOrderParamVO.setItemParamList(itemParamList);
        return syncOrderParamVO;
    }

    /**
     *  组装退款 list
     * @params []
     * @return java.util.List<com.lizikj.api.vo.order.SyncPosRefundOrderVO>
     * @author zhoufe
     * @date 2017/9/29 14:42
     */
    private List<SyncPosRefundOrderVO> assemblyRefunds(SyncOrderParamVO syncOrderParamVO, int len) {
        List<SyncPosRefundOrderVO> syncPosRefundOrderVOS = new ArrayList<>();
        for (int i = 0; i < len; i++){
            SyncPosRefundOrderVO syncPosRefundOrderVO = assemblyRefund(syncOrderParamVO);
            syncPosRefundOrderVOS.add(syncPosRefundOrderVO);
        }
        return syncPosRefundOrderVOS;
    }

    /**
     * 组装退款
     * @params [syncOrderParamVO]
     * @return com.lizikj.api.vo.order.SyncPosRefundOrderVO
     * @author zhoufe
     * @date 2017/9/29 17:45
     */
    private SyncPosRefundOrderVO assemblyRefund(SyncOrderParamVO syncOrderParamVO) {
        SyncPosRefundOrderVO syncPosRefundOrderVO = new SyncPosRefundOrderVO();

        syncPosRefundOrderVO.setOutRefundNo("ddddddd");
        syncPosRefundOrderVO.setInnerRefundNo(TradeNoUtil.genRefundTradeNo("ddddddxxxxx"));
        syncPosRefundOrderVO.setOrderNo(syncOrderParamVO.getOrderNo());
        syncPosRefundOrderVO.setInnerTradeNo(TradeNoUtil.genRefundTradeNo("ddddddxxxxxdddd"));
        syncPosRefundOrderVO.setMerchantId(ThreadLocalContext.getMerchantId());
        //syncPosRefundOrderVO.setMerchantName();
        syncPosRefundOrderVO.setShopId(ThreadLocalContext.getShopId());
        //syncPosRefundOrderVO.setShopName();
        //syncPosRefundOrderVO.setUid();
        //syncPosRefundOrderVO.setCashierId();
        syncPosRefundOrderVO.setMemberId(ThreadLocalContext.getMemberId());
        syncPosRefundOrderVO.setMerchantMemberId(ThreadLocalContext.getMerchantMemberId());
        syncPosRefundOrderVO.setRefundAmount(1000L);
        syncPosRefundOrderVO.setRefundType(RefundTypeEnum.ORG_REFUND.getCode());
        syncPosRefundOrderVO.setRefundAmountType(RefundAmountTypeEnum.PART.getCode());
        syncPosRefundOrderVO.setChannelCode(PaymentChannelEnum.PAY_CHANNEL_XINGYE.getCode());
        syncPosRefundOrderVO.setPaymentTypeCode(PaymentTypeEnum.PAYMENT_TYPE_WECHAT.getCode());
        syncPosRefundOrderVO.setSceneCode(PaymentSceneEnum.DESK_PAY_CODE.getCode());
        syncPosRefundOrderVO.setRefundSource(RefundSourceEnum.MERCHANT_REFUND.getType());
        syncPosRefundOrderVO.setStatus(RefundStatusEnum.SUCCESS.getStatus());
        syncPosRefundOrderVO.setRemark("测试备注"+ RandomUtils.getRandomNum(3));
        syncPosRefundOrderVO.setOrderBizType(OrderBizTypeEnum.MERCHANDISE.getBizType());
        syncPosRefundOrderVO.setQuestionOrderRemark("{\"test1\":\"123\",\"test2\":\"456\"}");

        List<RefundOrderItemVO> refundOrderItemList = assemblyRefundOrderItems();
        syncPosRefundOrderVO.setRefundOrderItemList(refundOrderItemList);

        OrderRefundReasonInfoVO orderRefundReasonInfo = assemblyRefundReason(syncOrderParamVO);
        syncPosRefundOrderVO.setOrderRefundReasonInfo(orderRefundReasonInfo);
        return syncPosRefundOrderVO;
    }

    /**
     * 组装 退款原因
     * @params [syncOrderParamVO]
     * @return com.lizikj.api.vo.order.OrderRefundReasonInfoVO
     * @author zhoufe
     * @date 2017/9/29 18:15
     */
    private OrderRefundReasonInfoVO assemblyRefundReason(SyncOrderParamVO syncOrderParamVO) {
        OrderRefundReasonInfoVO orderRefundReasonInfoVO = new OrderRefundReasonInfoVO();
        //orderRefundReasonInfoVO.setRefundReasonId();
        orderRefundReasonInfoVO.setShopId(ThreadLocalContext.getShopId());
        //orderRefundReasonInfoVO.setOrderId();
        orderRefundReasonInfoVO.setOrderNo(syncOrderParamVO.getOrderNo());
        orderRefundReasonInfoVO.setRefundAmount(1000L);
        orderRefundReasonInfoVO.setRefundAmountType(RefundAmountTypeEnum.PART);
        orderRefundReasonInfoVO.setRefundType(RefundTypeEnum.ORG_REFUND);
        orderRefundReasonInfoVO.setOperatorStaffId(1L);
        //orderRefundReasonInfoVO.setOperatorStaffName();
        orderRefundReasonInfoVO.setAuthorCode("904240");
        orderRefundReasonInfoVO.setAuthorStaffId(1L);
        //orderRefundReasonInfoVO.setAuthorStaffName();
        orderRefundReasonInfoVO.setRefundReasonType(RefundReasonTypeEnum.GOODS_PROBLEM);
        String randomNum = RandomUtils.getRandomNum(3);
        orderRefundReasonInfoVO.setRefundReason("测试原因" + randomNum);
        orderRefundReasonInfoVO.setRefundTime(new DateTime().toDate());
        orderRefundReasonInfoVO.setRemark("测试备注" + randomNum);
        //orderRefundReasonInfoVO.setRemoveStatus();
//        orderRefundReasonInfoVO.setUpdateTime();
//        orderRefundReasonInfoVO.setCreateTime();
        List<OrderRefundReasonItemVO> orderRefundReasonItems = assemblyRefundReasonItems(syncOrderParamVO);
        orderRefundReasonInfoVO.setOrderRefundReasonItems(orderRefundReasonItems);
        return orderRefundReasonInfoVO;
    }

    /**
     * 组装退款物品
     * @params [syncOrderParamVO]
     * @return java.util.List<com.lizikj.api.vo.order.OrderRefundReasonItemVO>
     * @author zhoufe
     * @date 2017/9/29 18:22
     */
    private List<OrderRefundReasonItemVO> assemblyRefundReasonItems(SyncOrderParamVO syncOrderParamVO) {

        List<OrderRefundReasonItemVO> orderRefundReasonItemVOS = new ArrayList<>();

        OrderRefundReasonItemVO orderRefundReasonItemVO = new OrderRefundReasonItemVO();
        String randomNum = RandomUtils.getRandomNum(4);
        orderRefundReasonItemVO.setOrderId(Long.valueOf(RandomUtils.getRandomNum(4)));
        orderRefundReasonItemVO.setOrderNo(syncOrderParamVO.getOrderNo());
        orderRefundReasonItemVO.setGoodsName("测试物品名称"+ randomNum);
        orderRefundReasonItemVO.setOrderGoodsProblem("测试菜品问题描述"+randomNum);

        orderRefundReasonItemVOS.add(orderRefundReasonItemVO);

        return orderRefundReasonItemVOS;
    }

    private List<RefundOrderItemVO> assemblyRefundOrderItems() {
        List<RefundOrderItemVO> refundOrderItemVOS = new ArrayList<>();
        RefundOrderItemVO refundOrderItemVO1 = new RefundOrderItemVO();
        refundOrderItemVO1.setGoodsId("xxxxx");
        refundOrderItemVO1.setGoodsNum(1);
        refundOrderItemVOS.add(refundOrderItemVO1);

        return refundOrderItemVOS;
    }

    /**
     * 组装支付流水
     * @params []
     * @return java.util.List<com.lizikj.api.vo.order.SyncPosPayFlowVO>
     * @author zhoufe
     * @date 2017/9/29 14:42
     */
    private List<SyncPosPayFlowVO> assemblyPayFlows(SyncOrderParamVO syncOrderParamVO, int len) {
        List<SyncPosPayFlowVO> syncPosPayFlowVOS = new ArrayList<>();

        //for (int i = 0; i < len; i++) {
            SyncPosPayFlowVO syncPosPayFlowVO = assemblyPayFlow(syncOrderParamVO);
            syncPosPayFlowVOS.add(syncPosPayFlowVO);
        //}

        return syncPosPayFlowVOS;
    }

    private SyncPosPayFlowVO assemblyPayFlow(SyncOrderParamVO syncOrderParamVO) {
        SyncPosPayFlowVO syncPosPayFlowVO = new SyncPosPayFlowVO();

                syncPosPayFlowVO.setOrderNo(syncOrderParamVO.getOrderNo());
                syncPosPayFlowVO.setOutTradeNo("O"+RandomUtils.getRandomNum(8));
                syncPosPayFlowVO.setInnerTradeNo(TradeNoUtil.genPayTradeNo(syncPosPayFlowVO.getOutTradeNo()));
                syncPosPayFlowVO.setPayFlowType(PayFlowTypeEnum.ORDER_CALL.getType());
                syncPosPayFlowVO.setChannelCode(PaymentChannelEnum.PAY_CHANNEL_XINGYE.getCode());
                syncPosPayFlowVO.setPaymentTypeCode(PaymentTypeEnum.PAYMENT_TYPE_WECHAT.getCode());
                syncPosPayFlowVO.setSceneCode(PaymentSceneEnum.DESK_PAY_CODE.getCode());
                syncPosPayFlowVO.setAmount(1000L);
                syncPosPayFlowVO.setPayStatus(PayStatusEnum.PAY_SUCCESS.getStatus());
                syncPosPayFlowVO.setRefundEnable(RefundEnableEnum.CAN_REFUND.getValue());
                syncPosPayFlowVO.setRefundEndTime(null);
                syncPosPayFlowVO.setRefundStatus(RefundStatusEnum.NORMAL.getStatus());
                syncPosPayFlowVO.setTradeTime(new DateTime().toDate());
                syncPosPayFlowVO.setBizType(OrderBizTypeEnum.MERCHANDISE.getBizType());
                syncPosPayFlowVO.setPayVersion(new DateTime().getMillis());

                SyncPayFlowAccountDetailVO syncPayFlowAccountDetail = assemblypayFlowAccDetail(syncOrderParamVO, syncPosPayFlowVO);
                syncPosPayFlowVO.setSyncPayFlowAccountDetail(syncPayFlowAccountDetail);

        return syncPosPayFlowVO;
    }

    /**
     * 组装支付流水账户详情
     * @params [syncOrderParamVO, syncPosPayFlowVO]
     * @return com.lizikj.api.vo.order.SyncPayFlowAccountDetailVO
     * @author zhoufe
     * @date 2017/9/29 15:00
     */
    private SyncPayFlowAccountDetailVO assemblypayFlowAccDetail(SyncOrderParamVO syncOrderParamVO, SyncPosPayFlowVO syncPosPayFlowVO) {
        SyncPayFlowAccountDetailVO syncPayFlowAccountDetailVO = new SyncPayFlowAccountDetailVO();

        //syncPayFlowAccountDetailVO.setPayFlowId();
        syncPayFlowAccountDetailVO.setMerchantId(ThreadLocalContext.getMerchantId());
        //syncPayFlowAccountDetailVO.setMerchantName();
        syncPayFlowAccountDetailVO.setShopId(ThreadLocalContext.getShopId());
        //syncPayFlowAccountDetailVO.setShopName();
        syncPayFlowAccountDetailVO.setUserId(1L);
        syncPayFlowAccountDetailVO.setCashierId(1L);
        syncPayFlowAccountDetailVO.setMemberId(ThreadLocalContext.getMemberId());
        syncPayFlowAccountDetailVO.setMerchantMemberId(ThreadLocalContext.getMerchantMemberId());
        String randomNum = RandomUtils.getRandomNum(3);
        syncPayFlowAccountDetailVO.setOpenId("1111112222"+ randomNum);
        syncPayFlowAccountDetailVO.setAccount("1111"+randomNum);
        syncPayFlowAccountDetailVO.setSnNum(SN);
        syncPayFlowAccountDetailVO.setPayInfo("PayInfo"+randomNum);

        return syncPayFlowAccountDetailVO;
    }

    /**
     *  组装优惠信息
     * @params []
     * @return java.util.List<com.lizikj.api.vo.order.OrderDiscountVO>
     * @author zhoufe
     * @date 2017/9/29 14:42
     */
    private List<OrderDiscountVO> assemblyDiscounts(SyncOrderParamVO syncOrderParamVO, int len) {

        List<OrderDiscountVO> orderDiscountVOS = new ArrayList<>();

        for (int i = 0; i < len; i++){
            OrderDiscountVO orderDiscountVO = assemblyDiscount(syncOrderParamVO);
            orderDiscountVOS.add(orderDiscountVO);
        }

        return orderDiscountVOS;
    }

    private OrderDiscountVO assemblyDiscount(SyncOrderParamVO syncOrderParamVO) {
        OrderDiscountVO orderDiscountVO = new OrderDiscountVO();
        //orderDiscountVO.setOrderDiscountId(source.getOrderDiscountId());
        orderDiscountVO.setOrderId(Long.valueOf(RandomUtils.getRandomNum(4)));//上传
        orderDiscountVO.setOrderNo(syncOrderParamVO.getOrderNo());
        orderDiscountVO.setName("活动名称测试"+ RandomUtils.getRandomNum(3));
        orderDiscountVO.setBenefitAmount(1000L);
        orderDiscountVO.setShopId(ThreadLocalContext.getShopId());
        orderDiscountVO.setPayVersion(new DateTime().getMillis());//支付版本？
        orderDiscountVO.setType(DiscountTypeEnum.MEMBER_DIS);
        orderDiscountVO.setTypeNode(DiscountTypeNodeEnum.MEMBER_LEVEL);
        //orderDiscountVO.setRemoveStatus(RemoveStatusEnum.getEnum(source.getRemoveStatus()));
        orderDiscountVO.setStatus(DiscountStatusEnum.VALID);
        return orderDiscountVO;
    }

    @Test
    public void testCalculatePrice(){

        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, 108L);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_MERCHANT_ID, null);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LZ_DID, null);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_MERCHANT_MEMBER_ID, null);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_MEMBER_ID, null);
        //String str =  "{\"areaDeskId\":\"202\",\"customDeskNumber\":\"\",\"orderSource\":\"H5\",\"packStatus\":\"PACK\",\"peoples\":\"1\",\"remark\":\"\",\"wait4Serving\":false,\"itemParamList\":[{\"cartGoodsId\":\"\",\"freeDishStatus\":false,\"goodsId\":\"59ded249340c1911c56bb019\",\"itemAttrParamList\":[],\"merchandiseVersion\":\"1507777482641\",\"nums\":2,\"packStatus\":\"PACK\",\"remark\":\"\",\"skuId\":3673,\"skuValueIds\":[],\"tempName\":\"\",\"tempPrice\":0}]}";
        //没套餐的
        // String str = "{\"areaDeskId\":\"202\",\"customDeskNumber\":\"\",\"orderSource\":\"H5\",\"peoples\":\"1\",\"remark\":\"\",\"wait4Serving\":false,\"itemParamList\":[{\"cartGoodsId\":\"\",\"freeDishStatus\":false,\"goodsId\":\"59ded249340c1911c56bb019\",\"itemAttrParamList\":[],\"merchandiseVersion\":\"1507777482641\",\"nums\":1,\"packStatus\":\"PACK\",\"remark\":\"\",\"skuId\":3673,\"skuValueIds\":[],\"tempName\":\"\",\"tempPrice\":0}]}";
        //有套餐的

        //String str =  "{\"areaDeskId\":\"202\",\"customDeskNumber\":\"\",\"orderSource\":\"H5\",\"orderId\":262756,\"peoples\":\"1\",\"remark\":\"\",\"wait4Serving\":false,\"itemParamList\":[{\"cartGoodsId\":\"\",\"freeDishStatus\":false,\"goodsId\":\"59ded249340c1911c56bb019\",\"itemAttrParamList\":[],\"merchandiseVersion\":\"1509589982632\",\"nums\":1,\"packStatus\":\"PACK\",\"remark\":\"\",\"skuId\":3673,\"skuValueIds\":[],\"tempName\":\"\",\"tempPrice\":0}]}";

        String str =  "{\"partnerMemberId\":\"9999\",\"areaDeskId\":4261,\"itemParamList\":[{\"appendDishStatus\":false,\"freeDishStatus\":false,\"goodsId\":\"5a0aa01d340c1912194c10a2\",\"merchandiseVersion\":1510645789843,\"nums\":2,\"packStatus\":\"UN_PACK\",\"skuId\":5472,\"tempPrice\":0},{\"appendDishStatus\":false,\"freeDishStatus\":false,\"goodsId\":\"5a06e24c340c1932b13a8abf\",\"merchandiseVersion\":1510400588014,\"nums\":1,\"packStatus\":\"UN_PACK\",\"skuId\":5427,\"tempPrice\":0}],\"memberId\":0,\"orderPersonId\":173,\"orderSource\":\"POS\",\"orderUpdateSource\":\"NONE\",\"peoples\":4,\"shopId\":108,\"snNum\":\"N7NL00235013\",\"userId\":0,\"wait4Serving\":false}";

        OrderInfoParamVO orderInfoParamVO = JSONObject.parseObject(str, OrderInfoParamVO.class);
        Result<OrderInfoVO> infoVOResult = orderController.calculatePrice(orderInfoParamVO);
        if (logger.isInfoEnabled()){
            logger.info("infoVOResult:{}", JSONObject.toJSONString(infoVOResult));
        }
    }

    @Test
    public void testDiscountSelectCalculatePrice(){

        discountSelected = DiscountTypeNodeEnum.MEMBER_LEVEL;
        //模拟登录会员
        if (logger.isInfoEnabled()){
            logger.info("discountSelected为会员：----》");
        }
        testFullCutMemberCalculatePrice();


        if (logger.isInfoEnabled()){
            logger.info("discountSelected为满减：----》");
        }
        discountSelected = DiscountTypeNodeEnum.FULL_CUT;
        //模拟登录会员
        testFullCutMemberCalculatePrice();


        discountSelected = null;
        //模拟登录会员
        if (logger.isInfoEnabled()){
            logger.info("discountSelected为null：----》");
        }
        testFullCutMemberCalculatePrice();
    }

    @Test
    public void testFullCutMemberCalculatePrice(){

        MerchantMemberDTO merchantMemberDTO = merchantMemberFacade.getMerchantMemberByMobile(147L, "18988999132");
        merchantMemberId = merchantMemberDTO.getMerchantMemberId();
        //模拟登录会员
        testFullCutCalculatePrice();
    }

    @Test
    public void testFullCutCalculatePrice(){

        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, 108L);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_MERCHANT_ID, null);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LZ_DID, null);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_MERCHANT_MEMBER_ID, null);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_MEMBER_ID, null);
        //String str =  "{\"areaDeskId\":\"202\",\"customDeskNumber\":\"\",\"orderSource\":\"H5\",\"packStatus\":\"PACK\",\"peoples\":\"1\",\"remark\":\"\",\"wait4Serving\":false,\"itemParamList\":[{\"cartGoodsId\":\"\",\"freeDishStatus\":false,\"goodsId\":\"59ded249340c1911c56bb019\",\"itemAttrParamList\":[],\"merchandiseVersion\":\"1507777482641\",\"nums\":2,\"packStatus\":\"PACK\",\"remark\":\"\",\"skuId\":3673,\"skuValueIds\":[],\"tempName\":\"\",\"tempPrice\":0}]}";
        //没套餐的
        // String str = "{\"areaDeskId\":\"202\",\"customDeskNumber\":\"\",\"orderSource\":\"H5\",\"peoples\":\"1\",\"remark\":\"\",\"wait4Serving\":false,\"itemParamList\":[{\"cartGoodsId\":\"\",\"freeDishStatus\":false,\"goodsId\":\"59ded249340c1911c56bb019\",\"itemAttrParamList\":[],\"merchandiseVersion\":\"1507777482641\",\"nums\":1,\"packStatus\":\"PACK\",\"remark\":\"\",\"skuId\":3673,\"skuValueIds\":[],\"tempName\":\"\",\"tempPrice\":0}]}";
        //有套餐的

        //String str =  "{\"areaDeskId\":\"202\",\"customDeskNumber\":\"\",\"orderSource\":\"H5\",\"orderId\":262756,\"peoples\":\"1\",\"remark\":\"\",\"wait4Serving\":false,\"itemParamList\":[{\"cartGoodsId\":\"\",\"freeDishStatus\":false,\"goodsId\":\"59ded249340c1911c56bb019\",\"itemAttrParamList\":[],\"merchandiseVersion\":\"1509589982632\",\"nums\":1,\"packStatus\":\"PACK\",\"remark\":\"\",\"skuId\":3673,\"skuValueIds\":[],\"tempName\":\"\",\"tempPrice\":0}]}";

        String str = getFullCutMemberJson();

        OrderInfoParamVO orderInfoParamVO = JSONObject.parseObject(str, OrderInfoParamVO.class);
        Result<OrderInfoVO> infoVOResult = orderController.calculatePrice(orderInfoParamVO);
        if (logger.isInfoEnabled()){
            logger.info("infoVOResult:{}", JSONObject.toJSONString(infoVOResult));
        }
    }

    private String getFullCutMemberJson() {
        String merchantMemberIdStr = "";
        if (null != merchantMemberId){
            merchantMemberIdStr = "merchantMemberId:"+merchantMemberId+",";
        }

        String discountSelectedStr = "";
        if(null != discountSelected){
            discountSelectedStr = "discountSelected:\""+discountSelected+"\",";
        }

        return "{"+discountSelectedStr+merchantMemberIdStr+"\"areaDeskId\":4261,\"itemParamList\":[{\"appendDishStatus\":false,\"freeDishStatus\":false,\"goodsId\":\"5a0aa01d340c1912194c10a2\",\"merchandiseVersion\":1510645789843,\"nums\":2,\"packStatus\":\"UN_PACK\",\"skuId\":5472,\"tempPrice\":0},{\"appendDishStatus\":false,\"freeDishStatus\":false,\"goodsId\":\"5a06e24c340c1932b13a8abf\",\"merchandiseVersion\":1510400588014,\"nums\":1,\"packStatus\":\"UN_PACK\",\"skuId\":5427,\"tempPrice\":0}],\"memberId\":0,\"orderPersonId\":173,\"orderSource\":\"POS\",\"orderUpdateSource\":\"NONE\",\"peoples\":4,\"shopId\":108,\"snNum\":\"N7NL00235013\",\"userId\":0,\"wait4Serving\":false}";
    }

    @Test
    public void testAddOrder(){

        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, 108L);// 1158L
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_MERCHANT_ID, 147L);//1160
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LZ_DID, null);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_MERCHANT_MEMBER_ID, null);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_MEMBER_ID, null);

        //String str =  "{\"areaDeskId\":\"202\",\"customDeskNumber\":\"\",\"orderSource\":\"H5\",\"packStatus\":\"PACK\",\"peoples\":\"1\",\"remark\":\"\",\"wait4Serving\":false,\"itemParamList\":[{\"cartGoodsId\":\"\",\"freeDishStatus\":false,\"goodsId\":\"59ded249340c1911c56bb019\",\"itemAttrParamList\":[],\"merchandiseVersion\":\"1507777482641\",\"nums\":2,\"packStatus\":\"PACK\",\"remark\":\"\",\"skuId\":3673,\"skuValueIds\":[],\"tempName\":\"\",\"tempPrice\":0}]}";
        //String str = "{\"areadeskid\":184,\"customdesknumber\":\"\",\"ordersource\":\"H5\",\"peoples\":\"1\",\"remark\":\"\",\"wait4serving\":false,\"itemparamlist\":[{\"cartgoodsid\":\"\",\"freedishstatus\":false,\"goodsid\":\"59ded249340c1911c56bb019\",\"itemattrparamlist\":[],\"merchandiseversion\":\"1508407872788\",\"nums\":1,\"packstatus\":\"UN_PACK\",\"remark\":\"\",\"skuid\":3673,\"skuvalueids\":[],\"tempname\":\"\",\"tempprice\":0}]}";

        MerchantMemberDTO merchantMemberDTO = merchantMemberFacade.getMerchantMemberByMobile(147L, "18988999132");
        merchantMemberId = merchantMemberDTO.getMerchantMemberId();

        String str = getFullCutMemberJson();
        OrderInfoParamVO orderInfoParamVO = JSONObject.parseObject(str, OrderInfoParamVO.class);
        Result<OrderInfoVO> infoVOResult = orderController.addOrder(orderInfoParamVO);
        if (logger.isInfoEnabled()){
            logger.info("infoVOResult:{}", JSONObject.toJSONString(infoVOResult));
        }
    }

    @Test
    public void testAddOrderAndGetOrder(){

        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, 1158L);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_MERCHANT_ID, null);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LZ_DID, null);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_MERCHANT_MEMBER_ID, null);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_MEMBER_ID, null);
        String str =  "{\"areaDeskId\":\"202\",\"customDeskNumber\":\"\",\"orderSource\":\"H5\",\"packStatus\":\"PACK\",\"peoples\":\"1\",\"remark\":\"\",\"wait4Serving\":false,\"itemParamList\":[{\"cartGoodsId\":\"\",\"freeDishStatus\":false,\"goodsId\":\"59ded249340c1911c56bb019\",\"itemAttrParamList\":[],\"merchandiseVersion\":\"1507777482641\",\"nums\":2,\"packStatus\":\"PACK\",\"remark\":\"\",\"skuId\":3673,\"skuValueIds\":[],\"tempName\":\"\",\"tempPrice\":0}]}";
        OrderInfoParamVO orderInfoParamVO = JSONObject.parseObject(str, OrderInfoParamVO.class);
        Result<OrderInfoVO> infoVOResult = orderController.addOrder(orderInfoParamVO);
        if (logger.isInfoEnabled()){
            logger.info("infoVOResult:{}", JSONObject.toJSONString(infoVOResult));
        }
        int detailFlag = (OrderInfoDetailEnum.BASE.getCode() | OrderInfoDetailEnum.CONTENT.getCode());
        Result<OrderInfoVO> orderInfoByOrderNo = orderController.getOrderInfoByOrderNo(infoVOResult.getData().getOrderNo(), detailFlag);
        if (logger.isInfoEnabled()){
            logger.info("getOrderInfoByOrderNo:{}", JSONObject.toJSONString(orderInfoByOrderNo));
        }

    }

    /**
    * 测试追加订单
    * @params []
    * @return void
    * @author zhoufe
    * @date 2017/10/17 14:39
    */
    @Test
    public void testAppendOrder(){
        //任意双拼赠菜
        //String json = "{\"itemParamList\":[{\"freeDishStatus\":true,\"goodsId\":\"59e1b8f4340c196ab5aad9d4\",\"merchandiseVersion\":\"1507965172140\",\"nums\":1,\"orderItemId\":\"1025034\",\"packStatus\":\"UN_PACK\",\"tempPrice\":0}],\"orderId\":\"256176\",\"orderSource\":\"POS\",\"peoples\":\"0\",\"wait4Serving\":false}";
        //String json = "{\"itemParamList\":[{\"freeDishStatus\":false,\"goodsId\":\"59e073e4340c1902069ef0f0\",\"itemAttrParamList\":[{\"attrId\":\"915\",\"itemAttrValueParamList\":[{\"attrValueId\":\"59df1af3e1f301294c5dce15\",\"attrValueName\":\"cee66e\"},{\"attrValueId\":\"59df5fe2e1f3011ba09628af\",\"attrValueName\":\"cee6ww6e\"}]},{\"attrId\":\"916\",\"itemAttrValueParamList\":[{\"attrValueId\":\"59ded44c340c1911c56bb01a\",\"attrValueName\":\"普通二\"}]}],\"merchandiseVersion\":\"1507881956657\",\"nums\":1,\"packStatus\":\"UN_PACK\",\"remark\":\"\",\"skuId\":\"3741\",\"tempPrice\":0}],\"orderId\":\"256187\",\"orderSource\":\"POS\",\"peoples\":\"0\",\"removeOrderItemIds\":[],\"wait4Serving\":false}";
        //String json = "{\"itemParamList\":[{\"freeDishStatus\":true,\"goodsId\":\"59e073e4340c1902069ef0f0\",\"merchandiseVersion\":\"1507881956657\",\"nums\":1,\"orderItemId\":\"1025033\",\"packStatus\":\"UN_PACK\",\"remark\":\"\",\"skuId\":\"3741\",\"tempPrice\":0}],\"orderId\":\"256176\",\"orderSource\":\"POS\",\"peoples\":\"0\",\"wait4Serving\":false}";
        //String json = "{\"itemParamList\":[{\"freeDishStatus\":true,\"goodsId\":\"59e0314f340c1950e3b5c713\",\"merchandiseVersion\":\"1507864911165\",\"nums\":1,\"orderItemId\":\"1025031\",\"packStatus\":\"UN_PACK\",\"remark\":\"\",\"skuId\":\"3715\",\"tempPrice\":0}],\"orderId\":\"256175\",\"orderSource\":\"POS\",\"peoples\":\"0\",\"wait4Serving\":false}";
        //没套餐的
        //String json = "{\"itemParamList\":[{\"freeDishStatus\":true,\"goodsId\":\"59ded44c340c1911c56bb01a\",\"itemAttrParamList\":[],\"merchandiseVersion\":\"1507775564305\",\"nums\":1,\"orderItemId\":\"4\",\"packStatus\":\"UN_PACK\",\"remark\":\"\",\"skuId\":\"3674\",\"tempPrice\":0}],\"merchantMemberId\":0,\"orderId\":\"1\",\"orderSource\":\"POS\",\"peoples\":\"-1\",\"wait4Serving\":false}\n";

        String json = "{\"areaDeskId\":202,\"customDeskNumber\":\"\",\"orderSource\":\"H5\",\"peoples\":1,\"remark\":\"\",\"wait4Serving\":false,\"itemParamList\":[{\"cartGoodsId\":\"\",\"freeDishStatus\":false,\"goodsId\":\"59ded249340c1911c56bb019\",\"itemAttrParamList\":[{\"attrId\":-88802,\"itemAttrValueParamList\":[{\"attrValueId\":22,\"attrValueName\":\"辣\"}]}],\"merchandiseVersion\":\"1508407872788\",\"nums\":1,\"packStatus\":\"PACK\",\"remark\":\"\",\"skuId\":3673,\"skuValueIds\":[],\"tempName\":\"\",\"tempPrice\":0}],\"orderId\":256421}";

        //有套餐的
        OrderInfoParamVO orderInfoParamVO = JSONObject.parseObject(json, OrderInfoParamVO.class);
        Result<OrderInfoVO> infoVOResult = orderController.appendOrder(orderInfoParamVO);
        if (logger.isInfoEnabled()){
            logger.info("appendOrder:{}", JSONObject.toJSONString(infoVOResult));
        }
    }
    
    
    @Test
    public void testCountOrderStatus(){
    	
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, 1158L);

    	Result<List<OrderInfoStatusCountVO>> countOrderStatus = orderController.countOrderStatus();
    	if (logger.isInfoEnabled()){
            logger.info("countOrderStatus:{}", JSONObject.toJSONString(countOrderStatus));
        }
    	
    }


    @Test
    public void testSyncOrderPrinterTicketFlag(){

        String orderNo = "D2017122711561664289017700";

        int code = PrintTicketFlagEnum.ADD_ORDER.getCode();
        syncPrintTicket(orderNo, code);

        code = PrintTicketFlagEnum.MEMBER_PAY.getCode();
        syncPrintTicket(orderNo, code);

        code = PrintTicketFlagEnum.ONLINE_PAY.getCode();
        syncPrintTicket(orderNo, code);

        code = PrintTicketFlagEnum.REFUND_ORDER.getCode();
        syncPrintTicket(orderNo, code);

        code = PrintTicketFlagEnum.ON_POS_SUCC.getCode();
        syncPrintTicket(orderNo, code);

        code = PrintTicketFlagEnum.ELME_ORDER.getCode();
        syncPrintTicket(orderNo, code);

        code = PrintTicketFlagEnum.MEITUAN_ORDER.getCode();
        syncPrintTicket(orderNo, code);

    }

    private void syncPrintTicket(String orderNo, int code) {
        orderController.syncOrderPrinterTicketFlag(orderNo, code);
        Result<OrderInfoVO> orderInfoByOrderNo = orderController.getOrderInfoByOrderNo(orderNo, OrderInfoDetailEnum.BASE.getCode());
        if (logger.isInfoEnabled()){
            logger.info("testSyncOrderPrinterTicketFlag :{}", orderInfoByOrderNo.getData().getPrintTicketFlag());
        }
    }

    @Test
    public void testSyncOrderItemPrintFlag4incr(){

        List<Long> orderItemIds = new ArrayList<>();
        orderItemIds.add(1L);
        orderItemIds.add(1L);
        orderItemIds.add(1L);
        orderItemIds.add(1L);
        orderItemIds.add(1L);
        orderItemIds.add(1L);
        orderController.syncOrderItemPrintFlag4incr(orderItemIds);

        syncPrintTicket("D2017111022531891036879806", OrderInfoDetailEnum.BASE.getCode()|OrderInfoDetailEnum.CONTENT.getCode());
    }

    @Test
    public void testMakePayStatusPaying2UnPay(){

        String orderNo = "D2018052120313481742426075";
        Result<Boolean> booleanResult = orderController.makePayStatusPaying2UnPay(orderNo);

        if (logger.isInfoEnabled()){
            logger.info("testMakeOrderPayStatus :{}", booleanResult);
        }
    }

    @Test
    public void testRefundOrderQuery(){
        Result<OrderInfoVO> result = orderController.getOrderInfoByOrderNo("D2018062910362807630312688", OrderInfoDetailEnum.ALL.getCode());
        if (logger.isInfoEnabled()){
            logger.info("result :{}", JSONObject.toJSONString(result));
        }

    }

    @Test
    public void testQuery4detail(){
        OrderInfoQueryParamVO queryParam = new OrderInfoQueryParamVO();
        queryParam.setOrderNo("D2018071616150719502456773");
        queryParam.setShopId(125L);
        queryParam.setCurrentPage(1);
        queryParam.setPageSize(10);
        Result<PageInfo<OrderInfoVO>> pageInfoResult = orderController.listOrderInfo4Detail(queryParam);
        if (logger.isInfoEnabled()){
            logger.info("testQuery4detail :{}", JSONObject.toJSONString(pageInfoResult));
        }
    }

}
