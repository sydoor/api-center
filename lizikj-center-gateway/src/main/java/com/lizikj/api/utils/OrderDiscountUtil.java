package com.lizikj.api.utils;

import com.lizikj.api.vo.order.OrderDiscountVO;
import com.lizikj.common.util.CollectionUtils;
import com.lizikj.order.dto.OrderDiscountDTO;
import com.lizikj.order.enums.DiscountStatusEnum;
import com.lizikj.order.enums.DiscountTypeEnum;
import com.lizikj.order.enums.DiscountTypeNodeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoufe
 * @date 2017/9/28 14:22
 */
public class OrderDiscountUtil {

    /**
     * list vo 转 dto
     * @params [orderDiscountVOList]
     * @return java.util.List<com.lizikj.order.dto.OrderDiscountDTO>
     * @author zhoufe
     * @date 2017/9/28 14:23
     */
    public static List<OrderDiscountDTO> listVoToListDto(List<OrderDiscountVO> orderDiscountVOList) {
        List<OrderDiscountDTO> OrderDiscountDTOS = new ArrayList<>();
        if (CollectionUtils.isListNotBlank(orderDiscountVOList)) {
            for (OrderDiscountVO orderDiscountVO : orderDiscountVOList) {
                OrderDiscountDTO orderDiscountDTO = voToDto(orderDiscountVO);
                OrderDiscountDTOS.add(orderDiscountDTO);
            }
        }
        return OrderDiscountDTOS;
    }

    /**
     * vo 转 dto
     * @params [orderDiscountVO]
     * @return com.lizikj.order.dto.OrderDiscountDTO
     * @author zhoufe
     * @date 2017/9/28 14:24
     */
    public static OrderDiscountDTO voToDto(OrderDiscountVO orderDiscountVO) {
        if (null == orderDiscountVO){
            return null;
        }

        OrderDiscountDTO orderDiscountDTO = new OrderDiscountDTO();
        orderDiscountDTO.setBenefitAmount(orderDiscountVO.getBenefitAmount());
        orderDiscountDTO.setName(orderDiscountVO.getName());
        orderDiscountDTO.setOrderId(orderDiscountVO.getOrderId());
        orderDiscountDTO.setOrderNo(orderDiscountVO.getOrderNo());
        orderDiscountDTO.setShopId(orderDiscountVO.getShopId());
        DiscountStatusEnum status = orderDiscountVO.getStatus();
        orderDiscountDTO.setStatus(status);
        DiscountTypeEnum type = orderDiscountVO.getType();
        orderDiscountDTO.setType(type);
        DiscountTypeNodeEnum typeNode = orderDiscountVO.getTypeNode();
        orderDiscountDTO.setTypeNode(typeNode);
        orderDiscountDTO.setPayVersion(orderDiscountVO.getPayVersion());
        orderDiscountDTO.setBizData(orderDiscountVO.getBizData());

        orderDiscountDTO.setTradeAreaId(orderDiscountVO.getTradeAreaId());
        orderDiscountDTO.setCouponCode(orderDiscountVO.getCouponCode());
        orderDiscountDTO.setCouponName(orderDiscountVO.getCouponName());
        orderDiscountDTO.setIdleStatus(orderDiscountVO.getIdleStatus());

        return orderDiscountDTO;
    }


}
