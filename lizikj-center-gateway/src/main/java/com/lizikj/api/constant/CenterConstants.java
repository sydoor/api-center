package com.lizikj.api.constant;

/**
 * @author zhoufe
 * @date 2017/10/10 11:10
 */
public class CenterConstants {
    public static final String PAY_FLOW_TYPE_CONVERTER = "payFlowTypeConverter";
    public static final String PAYMENT_TYPE_CODE_CONVERTER = "paymentTypeCodeConverter";
    public static final String PAY_STATUS_CONVERTER = "payStatusConverter";
    public static final String REFUND_ENABLE_CONVERTER = "refundEnableConverter";
    public static final String REFUND_STATUS_CONVERTER = "refundStatusConverter";
    public static final String BIZ_TYPE_CONVERTER = "bizTypeConverter";
    public static final String PAY_FLOW_TYPE = "payFlowType";
    public static final String PAYMENT_TYPE_CODE = "paymentTypeCode";
    public static final String PAY_STATUS = "payStatus";
    public static final String REFUND_ENABLE = "refundEnable";
    public static final String REFUND_STATUS = "refundStatus";
    public static final String BIZ_TYPE = "bizType";
    public static final String SAVE_TEMP_ORDER_PRICE_KEY = "save:temp:order:price:";
    
    /**
     * 存放文件下载的临时redis key
     */
    private static final String DOWN_LOAD_CACHE_KEY = "DOWN_LOAD_CACHE_KEY:";
    
    public static String getDownLoadCacheKey(String token){
    	return DOWN_LOAD_CACHE_KEY + token;
    }
}
