package com.lizikj.api.converter;

import com.lizikj.payment.pay.enums.PayFlowTypeEnum;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

/**
 *   PayFlowTypeEnum payFlowType            PayFlowTypeConverter
 //  PaymentTypeEnum paymentTypeCode        PaymentTypeCodeConverter
 //  PayStatusEnum payStatus                PayStatusConverter
 //  RefundEnableEnum refundEnable          RefundEnableConverter
 //  RefundStatusEnum refundStatus          RefundStatusConverter
 //  PayBizTypeEnum bizType                 BizTypeConverter
 * @author zhoufe
 * @date 2017/9/30 11:43
 */
public class PayFlowTypeConverter extends BidirectionalConverter<Byte, PayFlowTypeEnum> {
    @Override
    public PayFlowTypeEnum convertTo(Byte source, Type<PayFlowTypeEnum> destinationType, MappingContext mappingContext) {
        if (null == source) {
            return null;
        }
        return PayFlowTypeEnum.fromCode(source);
    }

    @Override
    public Byte convertFrom(PayFlowTypeEnum source, Type<Byte> destinationType, MappingContext mappingContext) {
        if (null == source) {
            return null;
        }

        return source.getType();
    }
}
