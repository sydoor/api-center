package com.lizikj.api.utils;

import com.lizikj.api.vo.order.RefundOrderItemVO;
import com.lizikj.api.vo.order.SyncPosRefundOrderVO;
import com.lizikj.common.util.CollectionUtils;
import com.lizikj.payment.refund.dto.RefundOrderDTO;
import com.lizikj.payment.refund.dto.RefundOrderDetailDTO;
import com.lizikj.payment.refund.dto.RefundOrderItemDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoufe
 * @date 2017/9/28 14:47
 */
public class RefundOrderUtil {

    /**
     * vo 转 dto
     * @params [syncPosRefundOrderVO]
     * @return com.lizikj.payment.refund.dto.RefundOrderDTO
     * @author zhoufe
     * @date 2017/9/28 14:48
     */
    public static RefundOrderDTO voToDto(SyncPosRefundOrderVO syncPosRefundOrderVO) {

        if (null == syncPosRefundOrderVO){
            return null;
        }

        RefundOrderDTO refundOrderDTO = new RefundOrderDTO();

        refundOrderDTO.setInnerRefundNo(syncPosRefundOrderVO.getInnerRefundNo());
        refundOrderDTO.setOutRefundNo(syncPosRefundOrderVO.getOutRefundNo());
        refundOrderDTO.setOrderNo(syncPosRefundOrderVO.getOrderNo());
        refundOrderDTO.setInnerTradeNo(syncPosRefundOrderVO.getInnerTradeNo());
        refundOrderDTO.setMerchantId(syncPosRefundOrderVO.getMerchantId());
        refundOrderDTO.setMerchantName(syncPosRefundOrderVO.getMerchantName());
        refundOrderDTO.setShopId(syncPosRefundOrderVO.getShopId());
        refundOrderDTO.setShopName(syncPosRefundOrderVO.getShopName());
        refundOrderDTO.setUid(syncPosRefundOrderVO.getUid());
        refundOrderDTO.setCashierId(syncPosRefundOrderVO.getCashierId());
        refundOrderDTO.setMemberId(syncPosRefundOrderVO.getMemberId());
        refundOrderDTO.setMerchantMemberId(syncPosRefundOrderVO.getMerchantMemberId());
        refundOrderDTO.setRefundAmount(syncPosRefundOrderVO.getRefundAmount());
        refundOrderDTO.setRefundType(syncPosRefundOrderVO.getRefundType());
        refundOrderDTO.setRefundAmountType(syncPosRefundOrderVO.getRefundAmountType());
        refundOrderDTO.setChannelCode(syncPosRefundOrderVO.getChannelCode());
        refundOrderDTO.setPaymentTypeCode(syncPosRefundOrderVO.getPaymentTypeCode());
        refundOrderDTO.setSceneCode(syncPosRefundOrderVO.getSceneCode());
        refundOrderDTO.setRefundSource(syncPosRefundOrderVO.getRefundSource());
        refundOrderDTO.setStatus(syncPosRefundOrderVO.getStatus());
        refundOrderDTO.setRemark(syncPosRefundOrderVO.getRemark());
        refundOrderDTO.setQuestionOrderRemark(syncPosRefundOrderVO.getQuestionOrderRemark());
//        refundOrderDTO.setQueryCount(syncPosRefundOrderVO.getQueryCount());
//        refundOrderDTO.setNextQueryTime(syncPosRefundOrderVO.getNextQueryTime());
//        refundOrderDTO.setNoticeStatus(syncPosRefundOrderVO.getNoticeStatus());
//        refundOrderDTO.setCreateTime(syncPosRefundOrderVO.getCreateTime());
//        refundOrderDTO.setUpdateTime(syncPosRefundOrderVO.getUpdateTime());
        refundOrderDTO.setOrderBizType(syncPosRefundOrderVO.getOrderBizType());

        //List<RefundOrderDetailDTO> refundOrderDetailDTOS = RefundOrderDetailUtil.listVoToListDto(syncPosRefundOrderVO.getRefundOrderDetails());
        //refundOrderDTO.setDetailList(refundOrderDetailDTOS);

        refundOrderDTO.setRefundOrderItemList(refundOrderItemListVoToListDto(syncPosRefundOrderVO.getRefundOrderItemList()));


        return refundOrderDTO;
    }

    /**
     * 退款物品详情 vo 转 dto
     * @params [refundOrderItemList]
     * @return java.util.List<com.lizikj.payment.refund.dto.RefundOrderItemDTO>
     * @author zhoufe
     * @date 2017/9/28 15:44
     */
    private static List<RefundOrderItemDTO> refundOrderItemListVoToListDto(List<RefundOrderItemVO> refundOrderItemList) {
        if (CollectionUtils.isListBlank(refundOrderItemList)){
            return null;
        }

        List<RefundOrderItemDTO> refundOrderItemDTOS = new ArrayList<>();
        for (RefundOrderItemVO refundOrderItemVO : refundOrderItemList) {
            RefundOrderItemDTO refundOrderItemDTO = refundOrderItemVoToDto(refundOrderItemVO);
            refundOrderItemDTOS.add(refundOrderItemDTO);
        }

        return refundOrderItemDTOS;
    }

    private static RefundOrderItemDTO refundOrderItemVoToDto(RefundOrderItemVO refundOrderItemVO) {
        if (null == refundOrderItemVO){
            return null;
        }

        RefundOrderItemDTO refundOrderItemDTO = new RefundOrderItemDTO();
        refundOrderItemDTO.setGoodsId(refundOrderItemVO.getGoodsId());
        refundOrderItemDTO.setGoodsNum(refundOrderItemVO.getGoodsNum());
        return refundOrderItemDTO;
    }
}
