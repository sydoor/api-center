package com.lizikj.api.converter;

import com.lizikj.common.enums.PaymentTypeEnum;
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
public class PaymentTypeCodeConverter extends BidirectionalConverter<Byte, PaymentTypeEnum> {
    @Override
    public PaymentTypeEnum convertTo(Byte source, Type<PaymentTypeEnum> destinationType, MappingContext mappingContext) {
        if (null == source) {
            return null;
        }
        return PaymentTypeEnum.get(source);
    }

    @Override
    public Byte convertFrom(PaymentTypeEnum source, Type<Byte> destinationType, MappingContext mappingContext) {
        if (null == source) {
            return null;
        }

        return source.getCode();
    }
}
