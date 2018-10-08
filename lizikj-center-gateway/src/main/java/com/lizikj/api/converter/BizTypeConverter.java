package com.lizikj.api.converter;

import com.lizikj.order.enums.OrderBizTypeEnum;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

/**
 *  //  PayFlowTypeEnum payFlowType            PayFlowTypeConverter
 //  PaymentTypeEnum paymentTypeCode        PaymentTypeCodeConverter
 //  PayStatusEnum payStatus                PayStatusConverter
 //  RefundEnableEnum refundEnable          RefundEnableConverter
 //  RefundStatusEnum refundStatus          RefundStatusConverter
 //  PayBizTypeEnum bizType                 BizTypeConverter
 * @author zhoufe
 * @date 2017/9/30 11:43
 */
public class BizTypeConverter extends BidirectionalConverter<Byte, OrderBizTypeEnum>  {
    @Override
    public OrderBizTypeEnum convertTo(Byte source, Type<OrderBizTypeEnum> destinationType, MappingContext mappingContext) {
        if (null == source){
            return null;
        }
        return OrderBizTypeEnum.getEnum(source);
    }

    @Override
    public Byte convertFrom(OrderBizTypeEnum source, Type<Byte> destinationType, MappingContext mappingContext) {
        if (null == source){
            return null;
        }

        return source.getBizType();
    }
}
