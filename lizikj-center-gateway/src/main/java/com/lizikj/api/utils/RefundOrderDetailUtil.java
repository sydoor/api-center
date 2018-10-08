package com.lizikj.api.utils;

import com.lizikj.api.vo.order.RefundOrderDetailVO;
import com.lizikj.common.util.CollectionUtils;
import com.lizikj.payment.refund.dto.RefundOrderDetailDTO;

import java.util.ArrayList;
import java.util.List; /**
 * @author zhoufe
 * @date 2017/9/28 15:32
 */
public class RefundOrderDetailUtil {

    /**
     * list vo 转 dto
     * @params [refundOrderDetails]
     * @return java.util.List<com.lizikj.payment.refund.dto.RefundOrderDetailDTO>
     * @author zhoufe
     * @date 2017/9/28 15:34
     */
    public static List<RefundOrderDetailDTO> listVoToListDto(List<RefundOrderDetailVO> refundOrderDetailVOS) {
        if (CollectionUtils.isListBlank(refundOrderDetailVOS)){
            return null;
        }

        return null;

    }
}
