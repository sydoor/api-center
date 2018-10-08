package com.lizikj.api.converter;

import com.lizikj.payment.pay.enums.PayFlowTypeEnum;
import com.lizikj.payment.pay.enums.PayStatusEnum;
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
public class PayStatusConverter extends BidirectionalConverter<Byte, PayStatusEnum>  {
    @Override
    public PayStatusEnum convertTo(Byte source, Type<PayStatusEnum> destinationType, MappingContext mappingContext) {
        if (null == source){
            return null;
        }
        return PayStatusEnum.getEnum(source);
    }

    @Override
    public Byte convertFrom(PayStatusEnum source, Type<Byte> destinationType, MappingContext mappingContext) {
        if (null == source){
            return null;
        }

        return source.getStatus();
    }
}
