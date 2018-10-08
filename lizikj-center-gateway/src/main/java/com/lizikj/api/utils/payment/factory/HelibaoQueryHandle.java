package com.lizikj.api.utils.payment.factory;

import com.lizikj.api.utils.WebUtils;
import com.lizikj.api.vo.payment.ThirdTQueryRequestParamVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoufe
 * @date 2018/5/3 15:59
 */
public class HelibaoQueryHandle extends IThridTradeflowQueryHandle {

    private static final Logger logger = LoggerFactory.getLogger(HelibaoQueryHandle.class);

    @Override
    public String request(String url, ThirdTQueryRequestParamVO thirdTQueryRequestParamVO) throws Exception {

        checkData(url, thirdTQueryRequestParamVO);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("tradeNo", thirdTQueryRequestParamVO.getTradeNo());
        String response = WebUtils.call(url, parameters);

        return response;

    }
}
