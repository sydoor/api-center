package com.lizikj.api.controller.order;

import com.alibaba.fastjson.JSONObject;
import com.lizikj.api.vo.order.param.query.OrderInfoQueryParamVO;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.order.dto.param.query.OrderInfoQueryParamDTO;
import com.lizikj.order.enums.OrderByEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoufe
 * @date 2018/4/28 18:56
 */
public class TestMapConvert {
    public static void main(String[] args) {
        OrderInfoQueryParamVO queryParam = new OrderInfoQueryParamVO();

        queryParam.setAreaDeskId(111L);
        Map<String, OrderByEnum> sortMaps = new HashMap<>();
        sortMaps.put("dd", OrderByEnum.DESC);
        sortMaps.put("cc", OrderByEnum.ASC);
        queryParam.setSortMaps(sortMaps);

        OrderInfoQueryParamDTO queryParamDTO = ObjectConvertUtil.map(queryParam, OrderInfoQueryParamDTO.class);

        System.out.println("queryParamDTO=" + JSONObject.toJSONString(queryParamDTO));
    }
}
