package com.lizikj.api.utils.payment.factory;

import com.lizikj.common.enums.PaymentChannelEnum;

/**
 * @author zhoufe
 * @date 2018/5/3 15:50
 */
public class ThirdTradeflowQueryFactory {

    public static IThridTradeflowQueryHandle create(PaymentChannelEnum paymentChannelEnum) {

        if (null == paymentChannelEnum){
            return null;
        }

        IThridTradeflowQueryHandle handle;

        switch (paymentChannelEnum){
            case PAY_CHANNEL_GUOTONG:{
                handle =  new GuotongQueryHandle();
            }
            break;

            case PAY_CHANNEL_HELIBAO:{
                handle = new HelibaoQueryHandle();
            }
            break;

            case PAY_CHANNEL_FUIOU:{
                handle = new FuyouQueryHandle();
            }
            break;

            default:{
                handle = null;
            }
            break;
        }

        return handle;
    }
}
