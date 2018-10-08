package com.lizikj.api.utils;

import com.lizikj.api.vo.order.OrderRefundReasonInfoVO;
import com.lizikj.api.vo.order.OrderRefundReasonItemVO;
import com.lizikj.common.util.CollectionUtils;
import com.lizikj.order.dto.detail.OrderRefundReasonInfoDTO;
import com.lizikj.order.dto.detail.OrderRefundReasonItemDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoufe
 * @date 2017/9/28 14:45
 */
public class OrderRefundReasonInfoUtil {


    /**
     * vo 转 dto
     * @params [orderRefundReasonInfoVO]
     * @return com.lizikj.order.dto.detail.OrderRefundReasonInfoDTO
     * @author zhoufe
     * @date 2017/9/28 16:09
     */
    public static OrderRefundReasonInfoDTO voToDto(OrderRefundReasonInfoVO orderRefundReasonInfoVO) {

        if (null == orderRefundReasonInfoVO){
            return null;
        }
        OrderRefundReasonInfoDTO orderRefundReasonInfoDTO = new OrderRefundReasonInfoDTO();
        orderRefundReasonInfoDTO.setRefundReasonId(orderRefundReasonInfoVO.getRefundReasonId());
        orderRefundReasonInfoDTO.setShopId(orderRefundReasonInfoVO.getShopId());
        orderRefundReasonInfoDTO.setOrderId(orderRefundReasonInfoVO.getOrderId());
        orderRefundReasonInfoDTO.setOrderNo(orderRefundReasonInfoVO.getOrderNo());
        orderRefundReasonInfoDTO.setRefundAmount(orderRefundReasonInfoVO.getRefundAmount());
        orderRefundReasonInfoDTO.setRefundAmountType(orderRefundReasonInfoVO.getRefundAmountType());
        orderRefundReasonInfoDTO.setRefundType(orderRefundReasonInfoVO.getRefundType());
        orderRefundReasonInfoDTO.setOperatorStaffId(orderRefundReasonInfoVO.getOperatorStaffId());
        orderRefundReasonInfoDTO.setOperatorStaffName(orderRefundReasonInfoVO.getOperatorStaffName());
        orderRefundReasonInfoDTO.setAuthorCode(orderRefundReasonInfoVO.getAuthorCode());
        orderRefundReasonInfoDTO.setAuthorStaffId(orderRefundReasonInfoVO.getAuthorStaffId());
        orderRefundReasonInfoDTO.setAuthorStaffName(orderRefundReasonInfoVO.getAuthorStaffName());
        orderRefundReasonInfoDTO.setRefundReasonType(orderRefundReasonInfoVO.getRefundReasonType());
        orderRefundReasonInfoDTO.setRefundReason(orderRefundReasonInfoVO.getRefundReason());
        orderRefundReasonInfoDTO.setRefundTime(orderRefundReasonInfoVO.getRefundTime());
        orderRefundReasonInfoDTO.setRemark(orderRefundReasonInfoVO.getRemark());
        orderRefundReasonInfoDTO.setRemoveStatus(orderRefundReasonInfoVO.getRemoveStatus());
        //orderRefundReasonInfoDTO.setUpdateTime(orderRefundReasonInfoVO.getUpdateTime());
        //orderRefundReasonInfoDTO.setCreateTime(orderRefundReasonInfoVO.getCreateTime());
        orderRefundReasonInfoDTO.setOrderRefundReasonItems(refundReasonItemListVoToListDto(orderRefundReasonInfoVO.getOrderRefundReasonItems()));

        return orderRefundReasonInfoDTO;
    }

    /**
     * list vo 转 dto
     * @params [orderRefundReasonItems]
     * @return java.util.List<com.lizikj.order.dto.detail.OrderRefundReasonItemDTO>
     * @author zhoufe
     * @date 2017/9/28 16:24
     */
    private static List<OrderRefundReasonItemDTO> refundReasonItemListVoToListDto(List<OrderRefundReasonItemVO> orderRefundReasonItemVOS) {

        if (CollectionUtils.isListBlank(orderRefundReasonItemVOS)){
            return null;
        }

        List<OrderRefundReasonItemDTO> orderRefundReasonItemDTOS = new ArrayList<>();

        for (OrderRefundReasonItemVO orderRefundReasonItemVO : orderRefundReasonItemVOS){
            OrderRefundReasonItemDTO orderRefundReasonItemDTO = refundReasonItemVoToDto(orderRefundReasonItemVO);
            orderRefundReasonItemDTOS.add(orderRefundReasonItemDTO);
        }

        return orderRefundReasonItemDTOS;
    }


    /**
     * vo 转 dto
     * @params [orderRefundReasonItemVO]
     * @return com.lizikj.order.dto.detail.OrderRefundReasonItemDTO
     * @author zhoufe
     * @date 2017/9/28 16:27
     */
    private static OrderRefundReasonItemDTO refundReasonItemVoToDto(OrderRefundReasonItemVO orderRefundReasonItemVO) {

        if (null == orderRefundReasonItemVO){
            return null;
        }
        OrderRefundReasonItemDTO orderRefundReasonItemDTO = new OrderRefundReasonItemDTO();

//        orderRefundReasonItemDTO.setRefundReasonItemId(orderRefundReasonItemVO.getRefundReasonItemId());
//        orderRefundReasonItemDTO.setRefundReasonId(orderRefundReasonItemVO.getRefundReasonId());
//        orderRefundReasonItemDTO.setShopId(orderRefundReasonItemVO.getShopId());
        orderRefundReasonItemDTO.setOrderId(orderRefundReasonItemVO.getOrderId());
        orderRefundReasonItemDTO.setOrderNo(orderRefundReasonItemVO.getOrderNo());
        orderRefundReasonItemDTO.setOrderItemId(orderRefundReasonItemVO.getOrderItemId());
//        orderRefundReasonItemDTO.setGoodsId(orderRefundReasonItemVO.getGoodsId());
        orderRefundReasonItemDTO.setGoodsName(orderRefundReasonItemVO.getGoodsName());
        orderRefundReasonItemDTO.setOrderGoodsProblem(orderRefundReasonItemVO.getOrderGoodsProblem());
//        orderRefundReasonItemDTO.setRemoveStatus(orderRefundReasonItemVO.getRemoveStatus());
//        orderRefundReasonItemDTO.setUpdateTime(orderRefundReasonItemVO.getUpdateTime());
//        orderRefundReasonItemDTO.setCreateTime(orderRefundReasonItemVO.getCreateTime());

        return orderRefundReasonItemDTO;
    }
}
