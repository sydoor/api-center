package com.lizikj.api.converter;

import com.lizikj.payment.pay.enums.PayFlowTypeEnum;
import com.lizikj.payment.refund.enums.RefundStatusEnum;
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
public class RefundStatusConverter extends BidirectionalConverter<Byte, RefundStatusEnum>  {
    @Override
    public RefundStatusEnum convertTo(Byte source, Type<RefundStatusEnum> destinationType, MappingContext mappingContext) {
        if (null == source){
            return null;
        }
        return RefundStatusEnum.getEnum(source);
    }

    @Override
    public Byte convertFrom(RefundStatusEnum source, Type<Byte> destinationType, MappingContext mappingContext) {
        if (null == source){
            return null;
        }

        return source.getStatus();
    }
}
