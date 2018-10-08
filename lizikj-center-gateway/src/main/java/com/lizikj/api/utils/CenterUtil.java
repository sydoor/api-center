package com.lizikj.api.utils;

import com.lizikj.api.constant.CenterConstants;
import com.lizikj.api.converter.*;
import com.lizikj.api.vo.order.detail.OrderPayFlowVO;
import com.lizikj.api.vo.shop.ShopPosDeviceVO;
import com.lizikj.merchant.dto.ShopEquipmentDTO;
import com.lizikj.payment.pay.dto.PayFlowDTO;
import com.lizikj.shop.api.common.ShopConstants;
import com.lizikj.shop.api.dto.converter.ActiveStatusConverter;
import com.lizikj.shop.api.dto.converter.OpenStatusConverter;
import com.lizikj.shop.api.dto.converter.PosDeviceRoleConverter;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/** 
 * @author zhoufe 
 * @date 2017年9月18日 下午1:09:50 
 */
public class CenterUtil {

	private CenterUtil(){}
	
	
    private static MapperFactory mapperFactory;

	static{
		mapperFactory = new DefaultMapperFactory.Builder().build();

		ConverterFactory converterFactory = mapperFactory.getConverterFactory();
		//pos
		converterFactory.registerConverter(ShopConstants.POS_DEVICE_ROLE_CONVERTER, new PosDeviceRoleConverter());
		converterFactory.registerConverter(ShopConstants.ACTIVE_STATUS_CONVERTER, new ActiveStatusConverter());
		converterFactory.registerConverter(ShopConstants.OPEN_STATUS_CONVERTER, new OpenStatusConverter());

		//支付流水
		converterFactory.registerConverter(CenterConstants.PAY_FLOW_TYPE_CONVERTER, new PayFlowTypeConverter());
		converterFactory.registerConverter(CenterConstants.PAYMENT_TYPE_CODE_CONVERTER, new PaymentTypeCodeConverter());
		converterFactory.registerConverter(CenterConstants.PAY_STATUS_CONVERTER, new PayStatusConverter());
		converterFactory.registerConverter(CenterConstants.REFUND_ENABLE_CONVERTER, new RefundEnableConverter());
		converterFactory.registerConverter(CenterConstants.REFUND_STATUS_CONVERTER, new RefundStatusConverter());
		converterFactory.registerConverter(CenterConstants.BIZ_TYPE_CONVERTER, new BizTypeConverter());


		registerEquipmentDTOWithDeviceVO();

		registerPayFlowDTOWithOrderPayFlowVO();
	}


	public static MapperFactory getMapperFactory(){
		return mapperFactory;
	}

	private static MapperFactory registerPayFlowDTOWithOrderPayFlowVO() {
        //  PayFlowTypeEnum payFlowType            PayFlowTypeConverter
		//  PaymentTypeEnum paymentTypeCode        PaymentTypeCodeConverter
		//  PayStatusEnum payStatus                PayStatusConverter
		//  RefundEnableEnum refundEnable          RefundEnableConverter
		//  RefundStatusEnum refundStatus          RefundStatusConverter
		//  PayBizTypeEnum bizType                 BizTypeConverter
		mapperFactory.classMap(PayFlowDTO.class, OrderPayFlowVO.class)
				.fieldMap(CenterConstants.PAY_FLOW_TYPE, CenterConstants.PAY_FLOW_TYPE).converter(CenterConstants.PAY_FLOW_TYPE_CONVERTER).add()
				.fieldMap(CenterConstants.PAYMENT_TYPE_CODE, CenterConstants.PAYMENT_TYPE_CODE).converter(CenterConstants.PAYMENT_TYPE_CODE_CONVERTER).add()
				.fieldMap(CenterConstants.PAY_STATUS, CenterConstants.PAY_STATUS).converter(CenterConstants.PAY_STATUS_CONVERTER).add()
				.fieldMap(CenterConstants.REFUND_ENABLE, CenterConstants.REFUND_ENABLE).converter(CenterConstants.REFUND_ENABLE_CONVERTER).add()
				.fieldMap(CenterConstants.REFUND_STATUS, CenterConstants.REFUND_STATUS).converter(CenterConstants.REFUND_STATUS_CONVERTER).add()
				.fieldMap(CenterConstants.BIZ_TYPE, CenterConstants.BIZ_TYPE).converter(CenterConstants.BIZ_TYPE_CONVERTER).add()
				.byDefault().register();
		return mapperFactory;
	}

    private static MapperFactory registerEquipmentDTOWithDeviceVO() {
        mapperFactory.classMap(ShopEquipmentDTO.class, ShopPosDeviceVO.class)
                .field(ShopConstants.SN, ShopConstants.SN_NUM)
                .fieldMap(ShopConstants.VOICED_STATUS, ShopConstants.VOICED_STATUS).converter(ShopConstants.OPEN_STATUS_CONVERTER).add()
                .fieldMap(ShopConstants.OPEN_PRINTED_STATUS, ShopConstants.OPEN_PRINTED_STATUS).converter(ShopConstants.OPEN_STATUS_CONVERTER).add()
                .fieldMap(ShopConstants.POS_DEVICE_ROLE, ShopConstants.POS_DEVICE_ROLE).converter(ShopConstants.POS_DEVICE_ROLE_CONVERTER).add()
                .fieldMap(ShopConstants.ACTIVE_STATUS, ShopConstants.ACTIVE_STATUS).converter(ShopConstants.ACTIVE_STATUS_CONVERTER).add()
                .byDefault().register();
        return mapperFactory;
    }

	public static String getSaveTempOrderPriceKey(String orderNo) {
		return CenterConstants.SAVE_TEMP_ORDER_PRICE_KEY + orderNo;
	}
}
