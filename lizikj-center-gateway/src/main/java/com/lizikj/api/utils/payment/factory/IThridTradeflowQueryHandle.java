package com.lizikj.api.utils.payment.factory;

import com.lizikj.api.vo.payment.ThirdTQueryRequestParamVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhoufe
 * @date 2018/5/3 15:51
 */
public abstract class IThridTradeflowQueryHandle {

    private static final Logger logger = LoggerFactory.getLogger(IThridTradeflowQueryHandle.class);
    /**
     * 请求第三方
     * @params [url, thirdTQueryRequestParamVO]
     * @return java.lang.String
     * @author zhoufe
     * @date 2018/5/3 15:51
     */
    public abstract String request(String url, ThirdTQueryRequestParamVO thirdTQueryRequestParamVO) throws Exception;


    /**
     * 校验数据
     * @params [url, thirdTQueryRequestParamVO]
     * @return void
     * @author zhoufe
     * @date 2018/5/3 16:10
     */
    public static void checkData(String url, ThirdTQueryRequestParamVO thirdTQueryRequestParamVO) {
        if (StringUtils.isBlank(url)){
            RuntimeException e = new RuntimeException("传入的url为空！");
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            throw e;
        }
    }
}
