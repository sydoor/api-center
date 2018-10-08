package com.lizikj.api.utils;

import com.lizikj.api.vo.order.SyncPayFlowAccountDetailVO;
import com.lizikj.api.vo.order.SyncPosPayFlowVO;
import com.lizikj.api.vo.order.SyncPosRefundOrderVO;
import com.lizikj.api.vo.order.param.OrderItemParamVO;
import com.lizikj.api.vo.order.param.SyncOrderContentParamVO;
import com.lizikj.api.vo.order.param.SyncOrderParamVO;
import com.lizikj.common.util.CollectionUtils;
import com.lizikj.order.dto.OrderDiscountDTO;
import com.lizikj.order.dto.SyncPosRefundOrderDTO;
import com.lizikj.order.dto.detail.OrderRefundReasonInfoDTO;
import com.lizikj.order.dto.param.OrderItemParamDTO;
import com.lizikj.order.dto.param.SyncOrderContentParamDTO;
import com.lizikj.order.dto.param.SyncOrderParamDTO;
import com.lizikj.payment.pay.dto.PayFlowAccountDetailDTO;
import com.lizikj.payment.pay.dto.PayFlowDTO;
import com.lizikj.payment.refund.dto.RefundOrderDTO;

import java.util.ArrayList;
import java.util.List; /**
 * @author zhoufe
 * @date 2017/9/28 11:07
 */
public class SyncOrderUtil {


    /**
     * list vo 转 list dto
     * @params [syncOrderParamVOS]
     * @return java.util.List<com.lizikj.order.dto.param.SyncOrderParamDTO>
     * @author zhoufe
     * @date 2017/9/28 11:12
     */
    public static List<SyncOrderParamDTO> syncOrderListVoToDto(List<SyncOrderParamVO> syncOrderParamVOS) {
        if (CollectionUtils.isListBlank(syncOrderParamVOS)){
            return null;
        }

        List<SyncOrderParamDTO> syncOrderParamDTOS = new ArrayList<>();
        for (SyncOrderParamVO syncOrderParamVO : syncOrderParamVOS) {
            SyncOrderParamDTO syncOrderParamDTO = syncOrderVoToDto(syncOrderParamVO);
            syncOrderParamDTOS.add(syncOrderParamDTO);
        }

        return syncOrderParamDTOS;
    }


    /**
     *  vo 转 dto
     * @params [syncOrderParamVO]
     * @return com.lizikj.order.dto.param.SyncOrderParamDTO
     * @author zhoufe
     * @date 2017/9/28 11:15
     */
    private static SyncOrderParamDTO syncOrderVoToDto(SyncOrderParamVO syncOrderParamVO) {
        if (null == syncOrderParamVO){
            return null;
        }

        SyncOrderParamDTO syncOrderParamDTO = new SyncOrderParamDTO();
        syncOrderParamDTO.setOrderNo(syncOrderParamVO.getOrderNo());
        syncOrderParamDTO.setOrderType(syncOrderParamVO.getOrderType());
        syncOrderParamDTO.setOrderStatus(syncOrderParamVO.getOrderStatus());
        syncOrderParamDTO.setOrderSource(syncOrderParamVO.getOrderSource());
        syncOrderParamDTO.setOrderBizType(syncOrderParamVO.getOrderBizType());
        SyncOrderContentParamVO syncOrderContentParamVO = syncOrderParamVO.getSyncOrderContent();
        List<OrderItemParamVO> itemParamVOList = syncOrderParamVO.getItemParamList();
        SyncOrderContentParamDTO syncOrderContentParamDTO = syncOrderContentVoToDto(itemParamVOList, syncOrderContentParamVO);
        syncOrderParamDTO.setSyncOrderContent(syncOrderContentParamDTO);
        //syncOrderParamDTO.setCreateTime(syncOrderParamVO.getCreateTime());
        //syncOrderParamDTO.setUpdateTime(syncOrderParamVO.getUpdateTime());
        syncOrderParamDTO.setOrderPersonId(syncOrderParamVO.getOrderPersonId());
        syncOrderParamDTO.setOrderTime(syncOrderParamVO.getOrderTime());
        syncOrderParamDTO.setRecTime(syncOrderParamVO.getRecTime());
        syncOrderParamDTO.setCloseStatus(syncOrderParamVO.getCloseStatus());
        syncOrderParamDTO.setInvoiceStatus(syncOrderParamVO.getInvoiceStatus());
        syncOrderParamDTO.setRemark(syncOrderParamVO.getRemark());
        syncOrderParamDTO.setMobile(syncOrderParamVO.getMobile());
        syncOrderParamDTO.setMerchantId(syncOrderParamVO.getMerchantId());
        syncOrderParamDTO.setTotalAmount(syncOrderParamVO.getTotalAmount());
        syncOrderParamDTO.setNeedAmount(syncOrderParamVO.getNeedAmount());
        syncOrderParamDTO.setCostAmount(syncOrderParamVO.getCostAmount());
        syncOrderParamDTO.setBenefitAmount(syncOrderParamVO.getBenefitAmount());
        syncOrderParamDTO.setPayAmount(syncOrderParamVO.getPayAmount());
        syncOrderParamDTO.setRefundAmount(syncOrderParamVO.getRefundAmount());
        syncOrderParamDTO.setSort(syncOrderParamVO.getSort());
        syncOrderParamDTO.setPayStatus(syncOrderParamVO.getPayStatus());
        syncOrderParamDTO.setRefundStatus(syncOrderParamVO.getRefundStatus());
        syncOrderParamDTO.setPeoples(syncOrderParamVO.getPeoples());
        syncOrderParamDTO.setCustomDeskNumber(syncOrderParamVO.getCustomDeskNumber());
        syncOrderParamDTO.setAreaDeskId(syncOrderParamVO.getAreaDeskId());
        syncOrderParamDTO.setReceiveCashAmount(syncOrderParamVO.getReceiveCashAmount());
        syncOrderParamDTO.setCashChangeAmount(syncOrderParamVO.getCashChangeAmount());
        return syncOrderParamDTO;
    }

    /**
     * vo 转 dto
     * @params [syncOrderContentParamVO]
     * @return com.lizikj.order.dto.param.SyncOrderContentParamDTO
     * @author zhoufe
     * @date 2017/9/28 11:23
     */
    private static SyncOrderContentParamDTO syncOrderContentVoToDto(List<OrderItemParamVO> itemParamVOList, SyncOrderContentParamVO syncOrderContentParamVO) {
        SyncOrderContentParamDTO syncOrderContentParamDTO = new SyncOrderContentParamDTO();

        if (null != syncOrderContentParamVO){
            List<SyncPosRefundOrderDTO> syncPosRefundOrderDTOS = syncPosRefundOrderListVoToListDto(syncOrderContentParamVO.getRefundOrders());
            //syncOrderContentParamDTO.setSyncPosRefundOrders(syncPosRefundOrderDTOS);

            List<OrderDiscountDTO> orderDiscountDTOS = OrderDiscountUtil.listVoToListDto(syncOrderContentParamVO.getOrderDiscounts());
            syncOrderContentParamDTO.setOrderDiscounts(orderDiscountDTOS);

            List<PayFlowDTO> syncPosPayFlows = payFlowListVoToListDto(syncOrderContentParamVO.getPayFlows());
            syncOrderContentParamDTO.setPayFlows(syncPosPayFlows);
        }

        if (CollectionUtils.isListNotBlank(itemParamVOList)){
            List<OrderItemParamDTO> itemParamDTOList = OrderItemUtil.itemVO2DTO(itemParamVOList);
            //syncOrderContentParamDTO.setItemParamList(itemParamDTOList);
        }

        return syncOrderContentParamDTO;
    }

    private static List<PayFlowDTO> payFlowListVoToListDto(List<SyncPosPayFlowVO> syncPosPayFlowVOS) {
        if (CollectionUtils.isListBlank(syncPosPayFlowVOS)){
            return null;
        }

        List<PayFlowDTO> payFlowDTOS = new ArrayList<>();
        for (SyncPosPayFlowVO syncPosPayFlowVO : syncPosPayFlowVOS){

            PayFlowDTO payFlowDTO = new PayFlowDTO();
            payFlowDTO.setId(syncPosPayFlowVO.getId());
            payFlowDTO.setOrderNo(syncPosPayFlowVO.getOrderNo());
            payFlowDTO.setInnerTradeNo(syncPosPayFlowVO.getInnerTradeNo());
            payFlowDTO.setOutTradeNo(syncPosPayFlowVO.getOutTradeNo());
            payFlowDTO.setPayFlowType(syncPosPayFlowVO.getPayFlowType());
            payFlowDTO.setChannelCode(syncPosPayFlowVO.getChannelCode());
            payFlowDTO.setPaymentTypeCode(syncPosPayFlowVO.getPaymentTypeCode());
            payFlowDTO.setSceneCode(syncPosPayFlowVO.getSceneCode());
            payFlowDTO.setAmount(syncPosPayFlowVO.getAmount());
            payFlowDTO.setPayStatus(syncPosPayFlowVO.getPayStatus());
            payFlowDTO.setRefundEnable(syncPosPayFlowVO.getRefundEnable());
            payFlowDTO.setRefundEndTime(syncPosPayFlowVO.getRefundEndTime());
            payFlowDTO.setRefundStatus(syncPosPayFlowVO.getRefundStatus());
            payFlowDTO.setTradeTime(syncPosPayFlowVO.getTradeTime());
            payFlowDTO.setBizType(syncPosPayFlowVO.getBizType());
            payFlowDTO.setPayVersion(syncPosPayFlowVO.getPayVersion());
            payFlowDTO.setQuestionOrderRemark(syncPosPayFlowVO.getQuestionOrderRemark());
            //payFlowDTO.setQueryCount(syncPosPayFlowVO.getQueryCount());
            //payFlowDTO.setNextQueryTime(syncPosPayFlowVO.getNextQueryTime());
            //payFlowDTO.setNoticeStatus(syncPosPayFlowVO.getNoticeStatus());
            //payFlowDTO.setRemoveStatus(syncPosPayFlowVO.getRemoveStatus());
            //payFlowDTO.setCreateTime(syncPosPayFlowVO.getCreateTime());
            //payFlowDTO.setUpdateTime(syncPosPayFlowVO.getUpdateTime());
            //payFlowDTO.setPayStatusList(syncPosPayFlowVO.getPayStatusList());

            SyncPayFlowAccountDetailVO syncPayFlowAccountDetailVO = syncPosPayFlowVO.getSyncPayFlowAccountDetail();
            PayFlowAccountDetailDTO payFlowAccountDetailDTO = payFlowAccountDetailVoToDto(syncPayFlowAccountDetailVO);
            payFlowDTO.setAccountDetail(payFlowAccountDetailDTO);
            payFlowDTOS.add(payFlowDTO);

        }

        return payFlowDTOS;
    }

    /**
     * vo 转 dto
     * @params [syncPayFlowAccountDetailVO]
     * @return com.lizikj.payment.pay.dto.PayFlowAccountDetailDTO
     * @author zhoufe
     * @date 2017/9/28 12:02
     */
    private static PayFlowAccountDetailDTO payFlowAccountDetailVoToDto(SyncPayFlowAccountDetailVO syncPayFlowAccountDetailVO) {
        if (null == syncPayFlowAccountDetailVO){
            return null;
        }

        PayFlowAccountDetailDTO payFlowAccountDetailDTO = new PayFlowAccountDetailDTO();
        payFlowAccountDetailDTO.setId(syncPayFlowAccountDetailVO.getId());
        payFlowAccountDetailDTO.setPayFlowId(syncPayFlowAccountDetailVO.getPayFlowId());
        payFlowAccountDetailDTO.setMerchantId(syncPayFlowAccountDetailVO.getMerchantId());
        payFlowAccountDetailDTO.setMerchantName(syncPayFlowAccountDetailVO.getMerchantName());
        payFlowAccountDetailDTO.setShopId(syncPayFlowAccountDetailVO.getShopId());
        payFlowAccountDetailDTO.setShopName(syncPayFlowAccountDetailVO.getShopName());
        payFlowAccountDetailDTO.setUserId(syncPayFlowAccountDetailVO.getUserId());
        payFlowAccountDetailDTO.setCashierId(syncPayFlowAccountDetailVO.getCashierId());
        payFlowAccountDetailDTO.setMemberId(syncPayFlowAccountDetailVO.getMemberId());
        payFlowAccountDetailDTO.setMerchantMemberId(syncPayFlowAccountDetailVO.getMerchantMemberId());
        payFlowAccountDetailDTO.setOpenId(syncPayFlowAccountDetailVO.getOpenId());
        payFlowAccountDetailDTO.setAccount(syncPayFlowAccountDetailVO.getAccount());
        payFlowAccountDetailDTO.setBankCardType(syncPayFlowAccountDetailVO.getBankCardType());
        payFlowAccountDetailDTO.setSnNum(syncPayFlowAccountDetailVO.getSnNum());
        payFlowAccountDetailDTO.setPayInfo(syncPayFlowAccountDetailVO.getPayInfo());

        return payFlowAccountDetailDTO;
    }

    /**
     * list vo 转 dto
     * @params [syncPosRefundOrderVOS]
     * @return java.util.List<com.lizikj.order.dto.SyncPosRefundOrderDTO>
     * @author zhoufe
     * @date 2017/9/28 11:29
     */
    private static List<SyncPosRefundOrderDTO> syncPosRefundOrderListVoToListDto(List<SyncPosRefundOrderVO> syncPosRefundOrderVOS) {

        if (CollectionUtils.isListBlank(syncPosRefundOrderVOS)){
            return null;
        }

        List<SyncPosRefundOrderDTO> syncPosRefundOrderDTOS = new ArrayList<>();

        for (SyncPosRefundOrderVO syncPosRefundOrderVO : syncPosRefundOrderVOS){
            SyncPosRefundOrderDTO syncPosRefundOrderDTO = syncPosRefundOrderVoToDto(syncPosRefundOrderVO);
            syncPosRefundOrderDTOS.add(syncPosRefundOrderDTO);
        }

        return syncPosRefundOrderDTOS;
    }

    private static SyncPosRefundOrderDTO syncPosRefundOrderVoToDto(SyncPosRefundOrderVO syncPosRefundOrderVO) {
        if (null == syncPosRefundOrderVO){
            return null;
        }

        SyncPosRefundOrderDTO syncPosRefundOrderDTO = new SyncPosRefundOrderDTO();

        OrderRefundReasonInfoDTO orderRefundReasonInfoDTO = OrderRefundReasonInfoUtil.voToDto(syncPosRefundOrderVO.getOrderRefundReasonInfo());
        syncPosRefundOrderDTO.setOrderRefundReasonInfo(orderRefundReasonInfoDTO);

        RefundOrderDTO refundOrderDTO = RefundOrderUtil.voToDto(syncPosRefundOrderVO);
        syncPosRefundOrderDTO.setRefundOrder(refundOrderDTO);
        return syncPosRefundOrderDTO;
    }


}
