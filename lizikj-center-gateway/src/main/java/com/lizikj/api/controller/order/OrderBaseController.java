package com.lizikj.api.controller.order;

import com.alibaba.fastjson.JSONObject;
import com.lizikj.api.utils.CenterUtil;
import com.lizikj.api.utils.OrderDiscountUtil;
import com.lizikj.api.utils.OrderItemUtil;
import com.lizikj.api.vo.order.OrderDiscountVO;
import com.lizikj.api.vo.order.OrderInfoVO;
import com.lizikj.api.vo.order.OrderItemAttrVO;
import com.lizikj.api.vo.order.OrderItemVO;
import com.lizikj.api.vo.order.param.*;
import com.lizikj.common.enums.PaymentChannelEnum;
import com.lizikj.common.enums.PaymentSceneEnum;
import com.lizikj.common.enums.PaymentTypeEnum;
import com.lizikj.common.util.CollectionUtils;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.order.dto.OrderDiscountDTO;
import com.lizikj.order.dto.OrderInfoDTO;
import com.lizikj.order.dto.OrderItemDTO;
import com.lizikj.order.dto.param.*;
import com.lizikj.order.enums.DiscountStatusEnum;
import com.lizikj.order.enums.DiscountTypeEnum;
import com.lizikj.order.enums.DiscountTypeNodeEnum;
import com.lizikj.order.enums.QuickBuyBizTypeEnum;
import com.lizikj.tender.enums.AccountFlowSourceEnum;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * @author Michael.Huang
 * @date 2017/9/19 14:01
 */
public class OrderBaseController {

    /**
     * 增值服务以及会员充值通过此接口下单VO转DTO
     *
     * @param quickBuyParamVO
     * @return
     */
    protected QuickBuyParamDTO getQuickBuyParamDTO(QuickBuyParamVO quickBuyParamVO) {
        if (quickBuyParamVO == null) {
            return null;
        }
        QuickBuyParamDTO quickBuyParamDTO = new QuickBuyParamDTO();
        quickBuyParamDTO.setAmount(quickBuyParamVO.getAmount());
        quickBuyParamDTO.setData(quickBuyParamVO.getData());
        quickBuyParamDTO.setReceiveCashAmount(quickBuyParamVO.getReceiveCashAmount());
        quickBuyParamDTO.setCashChangeAmount(quickBuyParamVO.getCashChangeAmount());

        QuickBuyBizTypeEnum bizType = QuickBuyBizTypeEnum.getEnum(quickBuyParamVO.getBizType());
        quickBuyParamDTO.setBizType(bizType);
        PaymentSceneEnum paymentScene = PaymentSceneEnum.get(quickBuyParamVO.getPaymentScene());
        quickBuyParamDTO.setPaymentScene(paymentScene);
        PaymentTypeEnum paymentType = PaymentTypeEnum.get(quickBuyParamVO.getPaymentType());
        quickBuyParamDTO.setPaymentType(paymentType);
        quickBuyParamDTO.setShopId(quickBuyParamVO.getShopId());
        quickBuyParamDTO.setAuthCode(quickBuyParamVO.getAuthCode());
        return quickBuyParamDTO;
    }

    /**
     * 订单转换VO转DTO
     *
     * @return com.lizikj.order.dto.param.OrderInfoParamDTO
     * @params [orderInfoParamVO]
     * @author zhoufe
     * @date 2017/9/16 10:22
     */
    protected OrderInfoParamDTO infoVO2DTO(OrderInfoParamVO orderInfoParamVO) {
        if (orderInfoParamVO == null) {
            return null;
        }



        OrderInfoParamDTO orderInfoParamDTO = new OrderInfoParamDTO();

        orderInfoParamDTO.setRemoveOrderItemIds(orderInfoParamVO.getRemoveOrderItemIds());
        orderInfoParamDTO.setOrderId(orderInfoParamVO.getOrderId());

        orderInfoParamDTO.setOrderSource(orderInfoParamVO.getOrderSource());
        //orderInfoParamDTO.setShopId(orderInfoParamVO.getShopId());
        //orderInfoParamDTO.setUserId(orderInfoParamVO.getUserId());
        //orderInfoParamDTO.setMemberId(orderInfoParamVO.getMemberId());
        orderInfoParamDTO.setMerchantMemberId(orderInfoParamVO.getMerchantMemberId());
        orderInfoParamDTO.setPartnerMemberId(orderInfoParamVO.getPartnerMemberId());
        //orderInfoParamDTO.setSnNum(orderInfoParamVO.getSnNum());
        orderInfoParamDTO.setAreaDeskId(orderInfoParamVO.getAreaDeskId());
        orderInfoParamDTO.setCustomDeskNumber(orderInfoParamVO.getCustomDeskNumber());
        orderInfoParamDTO.setPeoples(orderInfoParamVO.getPeoples());
        orderInfoParamDTO.setRemark(orderInfoParamVO.getRemark());
        orderInfoParamDTO.setPackStatus(orderInfoParamVO.getPackStatus());
        orderInfoParamDTO.setWait4Serving(orderInfoParamVO.getWait4Serving());
        orderInfoParamDTO.setDiscountSelected(orderInfoParamVO.getDiscountSelected());
        orderInfoParamDTO.setMergeFlag(orderInfoParamVO.getMergeFlag());
        orderInfoParamDTO.setLevel(orderInfoParamVO.getLevel());
        orderInfoParamDTO.setCustomDis(orderInfoParamVO.getCustomDis());


        List<OrderDiscountVO> orderDiscountListVOS = orderInfoParamVO.getOrderDiscountList();
        List<OrderDiscountDTO> orderDiscountListDTOS = OrderDiscountUtil.listVoToListDto(orderDiscountListVOS);
        orderInfoParamDTO.setOrderDiscountList(orderDiscountListDTOS);
        orderInfoParamDTO.setPendingOrderType(orderInfoParamVO.getPendingOrderType());


        List<OrderItemParamVO> orderItemParamVOS = orderInfoParamVO.getItemParamList();
        List<OrderItemParamDTO> orderItemParamDTOS = OrderItemUtil.itemVO2DTO(orderItemParamVOS);
        orderInfoParamDTO.setItemParamList(orderItemParamDTOS);

        return orderInfoParamDTO;
    }




    /**
     * 结账结果VO转DTO
     *
     * @param orderPayParamVO
     * @return
     */
    protected OrderPayParamInfoDTO getOrderPayParamInfoDTO(OrderPayParamVO orderPayParamVO) {
        if (orderPayParamVO == null) {
            return null;
        }
        OrderPayParamInfoDTO orderPayParamInfoDTO = new OrderPayParamInfoDTO();
//        orderPayParamInfoDTO.setMerchantId(ThreadLocalContext.getMerchantId());
//        orderPayParamInfoDTO.setMerchantMemberId( ThreadLocalContext.getMerchantMemberId());
//        orderPayParamInfoDTO.setShopId(ThreadLocalContext.getShopId());
//        orderPayParamInfoDTO.setSnNum(ThreadLocalContext.getDid());
        orderPayParamInfoDTO.setVersion(ThreadLocalContext.getLZVersion());
        orderPayParamInfoDTO.setOrderDiscountList(getOrderDiscountDTOS(orderPayParamVO));
        orderPayParamInfoDTO.setOrderNo(orderPayParamVO.getOrderNo());
        orderPayParamInfoDTO.setPayParamList(getPayParamDTOS(orderPayParamVO));
        orderPayParamInfoDTO.setTotalAmount(orderPayParamVO.getTotalAmount());
        orderPayParamInfoDTO.setDiscountSelected(orderPayParamVO.getDiscountSelected());
        orderPayParamInfoDTO.setDsAmountJsonArray(orderPayParamVO.getDsAmountJsonArray());
        orderPayParamInfoDTO.setReceiveCashAmount(orderPayParamVO.getReceiveCashAmount());
        orderPayParamInfoDTO.setCashChangeAmount(orderPayParamVO.getCashChangeAmount());
        return orderPayParamInfoDTO;
    }

    protected List<PayParamDTO> getPayParamDTOS(OrderPayParamVO orderPayParamVO) {
        List<PayParamDTO> PayParamDTOS = new ArrayList<PayParamDTO>();
        List<PayParamVO> payParamList = orderPayParamVO.getPayParamList();
        if (CollectionUtils.isListNotBlank(payParamList)) {
            for (PayParamVO payParamVO : payParamList) {
                PayParamDTO payParamDTO = new PayParamDTO();
                payParamDTO.setBizData(payParamVO.getBizData());
                payParamDTO.setPayAmount(payParamVO.getPayAmount());
                PaymentSceneEnum paymentScene = PaymentSceneEnum.get(payParamVO.getPaymentScene());
                payParamDTO.setPaymentScene(paymentScene);
                PaymentTypeEnum paymentType = PaymentTypeEnum.get(payParamVO.getPaymentType());
                payParamDTO.setPaymentType(paymentType);
                AccountFlowSourceEnum accountFlowSource = AccountFlowSourceEnum.getEnumByCode(payParamVO.getAccountFlowSource());
                payParamDTO.setAccountFlowSource(accountFlowSource);
                payParamDTO.setFlowMemo(payParamVO.getFlowMemo());
                PayParamDTOS.add(payParamDTO);
            }
        }
        return PayParamDTOS;
    }

    protected List<OrderDiscountDTO> getOrderDiscountDTOS(OrderPayParamVO orderPayParamVO) {
        return OrderDiscountUtil.listVoToListDto(orderPayParamVO.getOrderDiscountList());
    }

    /**
     * DTO转换VO
     * 暂时json字段的转换
     *
     * @return java.util.List<com.lizikj.api.vo.order.OrderItemVO>
     * @params [orderInfoDto]
     * @author zhoufe
     * @date 2017/9/22 10:19
     */
    protected List<OrderItemVO> itemDTOList2VOList(List<OrderItemDTO> orderItemDTOS) {
        if (CollectionUtils.isListBlank(orderItemDTOS)) {
            return null;
        }

        List<OrderItemVO> orderItemVOS = new ArrayList<>();

//        Long minOrderVersion = getMinOrderVersion(orderItemDTOS);

        for (OrderItemDTO orderItemDTO : orderItemDTOS) {

            OrderItemVO orderItemVO = itemDTO2VO(orderItemDTO);
            if (null == orderItemVO) {
                continue;
            }

            //是否最小的那个相等，如果相等 不是加菜 否则 是
            //orderItemVO.setAppendDishStatus(isMin(orderItemVO.getOrderVersion(), minOrderVersion));

            orderItemVOS.add(orderItemVO);
        }

        return orderItemVOS;
    }

    /**
     * DTO 转 VO
     *
     * @return com.lizikj.api.vo.order.OrderItemVO
     * @params [orderItemDTO]
     * @author zhoufe
     * @date 2017/9/22 16:53
     */
    protected OrderItemVO itemDTO2VO(OrderItemDTO orderItemDTO) {
        if (null == orderItemDTO) {
            return null;
        }

        OrderItemVO orderItemVO = ObjectConvertUtil.getMapperFactory().getMapperFacade().map(orderItemDTO, OrderItemVO.class);

        //json  转 vo
        List<OrderItemAttrVO> orderItemAttrVOS = attrJson2VO(orderItemDTO.getOrderItemAttr());
        if (CollectionUtils.isListNotBlank(orderItemAttrVOS)) {
            orderItemVO.setItemAttrList(orderItemAttrVOS);
        }

        return orderItemVO;

    }

    /**
     * json 转换 vo
     *
     * @return java.util.List<com.lizikj.api.vo.order.OrderItemAttrVO>
     * @params [orderItemAttr]
     * @author zhoufe
     * @date 2017/9/22 10:33
     */
    protected List<OrderItemAttrVO> attrJson2VO(String orderItemAttr) {
        if (StringUtils.isBlank(orderItemAttr)) {
            return null;
        }

        List<OrderItemAttrVO> orderItemAttrVOS = JSONObject.parseArray(orderItemAttr, OrderItemAttrVO.class);
        return orderItemAttrVOS;
    }

    /**
     * 订单里的订单商品的 DTO转换VO
     * 暂时json字段的转换
     *
     * @return java.util.List<com.lizikj.api.vo.order.OrderItemVO>
     * @params [orderInfoDto]
     * @author zhoufe
     * @date 2017/9/22 10:19
     */
    protected List<OrderInfoVO> infoDTOList2VOList(List<OrderInfoDTO> orderInfoDTOS) {

        if (CollectionUtils.isListBlank(orderInfoDTOS)){
            return null;
        }

        List<OrderInfoVO> infoVOList = CenterUtil.getMapperFactory().getMapperFacade().mapAsList(orderInfoDTOS, OrderInfoVO.class);

        infoVOList.forEach(infoVO ->{
            Long orderId = infoVO.getOrderId();
            OrderInfoDTO infoDTO = getOrderDtoById(orderId, orderInfoDTOS);
            if(infoDTO != null){
                List<OrderItemVO> orderItemVOS = itemDTOList2VOList(infoDTO.getOrderItems());
                infoVO.setOrderItems(orderItemVOS);
            }
        });

        return infoVOList;
    }

    /**
     * 根据ID获取dto
     * @params [orderId, orderInfoDTOS]
     * @return com.lizikj.order.dto.OrderInfoDTO
     * @author zhoufe
     * @date 2018/8/6 14:07
     */
    private OrderInfoDTO getOrderDtoById(Long orderId, List<OrderInfoDTO> orderInfoDTOS) {

        if (isLongNull(orderId)) {
            return null;
        }

        if (CollectionUtils.isListBlank(orderInfoDTOS)) {
            return null;
        }

        for (OrderInfoDTO dto : orderInfoDTOS) {
            if (! orderId.equals(dto.getOrderId())) {
                continue;
            }
            return dto;
        }

        return null;

    }

    private boolean isLongNull(Long id) {
        return null == id || 0 == id;
    }




}
