package com.lizikj.api.utils;

import com.lizikj.api.vo.order.param.OrderItemAttrParamVO;
import com.lizikj.api.vo.order.param.OrderItemAttrValueParamVO;
import com.lizikj.api.vo.order.param.OrderItemParamVO;
import com.lizikj.common.util.CollectionUtils;
import com.lizikj.order.dto.param.OrderItemAttrParamDTO;
import com.lizikj.order.dto.param.OrderItemAttrValueParamDTO;
import com.lizikj.order.dto.param.OrderItemParamDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoufe
 * @date 2017/9/28 11:31
 */
public class OrderItemUtil {

    /**
     * 商品行VO转DTO
     *
     * @return java.util.List<com.lizikj.order.dto.param.OrderItemParamDTO>
     * @params [orderItemParamVOS]
     * @author zhoufe
     * @date 2017/9/16 18:32
     */
    public static List<OrderItemParamDTO> itemVO2DTO(List<OrderItemParamVO> orderItemParamVOS) {
        List<OrderItemParamDTO> orderItemParamDTOS = new ArrayList<>();
        if (CollectionUtils.isListNotBlank(orderItemParamVOS)) {
            for (OrderItemParamVO orderItemParamVO : orderItemParamVOS) {
                //orderItemParamVO.getCartGoodsId();
                //orderItemParamVO.getOrderItemId();

                OrderItemParamDTO orderItemParamDTO = new OrderItemParamDTO();
                orderItemParamDTO.setGoodsId(orderItemParamVO.getGoodsId());
                orderItemParamDTO.setTempName(orderItemParamVO.getTempName());
                orderItemParamDTO.setTempPrice(orderItemParamVO.getTempPrice());
                orderItemParamDTO.setMerchandiseVersion(orderItemParamVO.getMerchandiseVersion());
                orderItemParamDTO.setNums(orderItemParamVO.getNums().intValue());
                orderItemParamDTO.setRemark(orderItemParamVO.getRemark());
                orderItemParamDTO.setFreeDishStatus(orderItemParamVO.getFreeDishStatus());
                orderItemParamDTO.setPackStatus(orderItemParamVO.getPackStatus());
                orderItemParamDTO.setSkuId(orderItemParamVO.getSkuId());
                orderItemParamDTO.setSkuValueIds(orderItemParamVO.getSkuValueIds());
                orderItemParamDTO.setOrderItemId(orderItemParamVO.getOrderItemId());
                orderItemParamDTO.setAppendDishStatus(orderItemParamVO.getAppendDishStatus());

                List<OrderItemAttrParamDTO> itemAttrParamDTOS = itemAttrVO2DTO(orderItemParamVO);
                orderItemParamDTO.setItemAttrParamList(itemAttrParamDTOS);
                orderItemParamDTOS.add(orderItemParamDTO);
            }
        }
        return orderItemParamDTOS;
    }


    /**
     * 属性VO转DTO
     *
     * @return java.util.List<com.lizikj.order.dto.param.OrderItemAttrParamDTO>
     * @params [orderItemParamVO]
     * @author zhoufe
     * @date 2017/9/16 18:31
     */
    public static List<OrderItemAttrParamDTO> itemAttrVO2DTO(OrderItemParamVO orderItemParamVO) {
        List<OrderItemAttrParamVO> itemAttrParamVOS = orderItemParamVO.getItemAttrParamList();
        if (CollectionUtils.isListBlank(itemAttrParamVOS)){
            return null;
        }
        List<OrderItemAttrParamDTO> itemAttrParamDTOS = new ArrayList<>();
        for (OrderItemAttrParamVO orderItemAttrParamVO : itemAttrParamVOS) {
            OrderItemAttrParamDTO orderItemAttrParamDTO = new OrderItemAttrParamDTO();
            orderItemAttrParamDTO.setAttrId(orderItemAttrParamVO.getAttrId());
            List<OrderItemAttrValueParamDTO> orderItemAttrValueParamDTOS = itemAttrValueVO2DTO(orderItemAttrParamVO);
            orderItemAttrParamDTO.setItemAttrValueParamList(orderItemAttrValueParamDTOS);

            itemAttrParamDTOS.add(orderItemAttrParamDTO);
        }
        return itemAttrParamDTOS;
    }


    /**
     * 属性值VO转DTO
     *
     * @return java.util.List<com.lizikj.order.dto.param.OrderItemAttrValueParamDTO>
     * @params [orderItemAttrParamVO]
     * @author zhoufe
     * @date 2017/9/16 18:30
     */
    public static List<OrderItemAttrValueParamDTO> itemAttrValueVO2DTO(OrderItemAttrParamVO orderItemAttrParamVO) {
        List<OrderItemAttrValueParamDTO> orderItemAttrValueParamDTOS = new ArrayList<>();
        List<OrderItemAttrValueParamVO> itemAttrValueParamVOS = orderItemAttrParamVO.getItemAttrValueParamList();
        for (OrderItemAttrValueParamVO orderItemAttrValueParamVO : itemAttrValueParamVOS) {
            OrderItemAttrValueParamDTO orderItemAttrValueParamDTO = new OrderItemAttrValueParamDTO();
            orderItemAttrValueParamDTO.setAttrValueId(orderItemAttrValueParamVO.getAttrValueId());
            orderItemAttrValueParamDTO.setAttrValueName(orderItemAttrValueParamVO.getAttrValueName());
            orderItemAttrValueParamDTOS.add(orderItemAttrValueParamDTO);
        }
        return orderItemAttrValueParamDTOS;
    }

}
