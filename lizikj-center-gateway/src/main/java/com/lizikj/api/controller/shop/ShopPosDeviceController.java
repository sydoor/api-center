package com.lizikj.api.controller.shop;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lizikj.api.utils.CenterUtil;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.shop.ShopPosDeviceVO;
import com.lizikj.common.exception.BaseException;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.merchant.dto.ShopEquipmentDTO;
import com.lizikj.merchant.enums.EquipmentEnum;
import com.lizikj.merchant.facade.IShopEquipmentReadFacade;
import com.lizikj.merchant.facade.IShopEquipmentWriteFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ma.glasnost.orika.MapperFactory;


/**
 * 店铺POS接口和商户POS出值同一张数据库表
 * @author zhoufe
 * @date 2017/07/10
 */
@Controller
@RequestMapping("/shop/pos/device")
@Api(value = "shop-pos-device", tags = "店铺POSAPI接口")
public class ShopPosDeviceController {

    private static final Logger logger = LoggerFactory.getLogger(ShopPosDeviceController.class);

    @Autowired
    private IShopEquipmentReadFacade shopEquipmentReadFacade;
    @Autowired
    private IShopEquipmentWriteFacade shopEquipmentWriteFacade;
    /**
     * 查询该店铺下的POS
     * @return Result<List<ShopPosDeviceVO>>
     * @author zhoufe
     * @date 2017年7月18日 下午3:31:23
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/listShopPosDevice")
    @ApiOperation(value = "查询该店铺下的设备", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<ShopPosDeviceVO>> listShopPosDevice() {
        Result<List<ShopPosDeviceVO>> result;
        try {
            List<ShopEquipmentDTO> shopEquipmentDTOS;
            List<ShopEquipmentDTO> results = new ArrayList<ShopEquipmentDTO>();
            Long shopId = ThreadLocalContext.getShopId();
            if(shopId != null){
                shopEquipmentDTOS = shopEquipmentReadFacade.findByShopId(shopId);
            }else{
                String snNum = ThreadLocalContext.getDid();
                shopEquipmentDTOS = shopEquipmentReadFacade.findByShopIdFirstBySn(snNum);
            }
            if (shopEquipmentDTOS == null || shopEquipmentDTOS.size() ==0) {
            	result = Result.SUCESS(new ArrayList<ShopPosDeviceVO>());
                return result;
            }
            for (ShopEquipmentDTO record:shopEquipmentDTOS) {
            	if (record.getEquipmentStatus().byteValue() == EquipmentEnum.USE_ING.getCode()) {//只加载使用中的设备 2017/11/15 liaojw
            		results.add(record);
            	}
            }
            MapperFactory mapperFactory = CenterUtil.getMapperFactory();
            List<ShopPosDeviceVO> shopPosDeviceVOS = mapperFactory.getMapperFacade().mapAsList(results, ShopPosDeviceVO.class);

            result = Result.SUCESS(shopPosDeviceVOS);
        } catch (BaseException e) {
            logger.error("listShopPosDevice Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        
        return result;
    }

    /**
     * 更改主POS
     * 原来的主POS查询出来改成现在的主POS
     * @return Result<List<ShopPosDeviceVO>>
     * @author zhoufe
     * @date 2017年7月18日 下午3:31:23
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/setMasterDevice")
    @ApiOperation(value = "更改主POS", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> setMasterDevice(
            @ApiParam(name = "posDeviceId", value = "门店POS设备的ID")
            @RequestParam Long posDeviceId
    ) {
        Result<Boolean> result;
        try {
            Long shopId = ThreadLocalContext.getShopId();
            Boolean isSuccess;
            if (shopId != null){
                isSuccess = shopEquipmentWriteFacade.setMasterDevice(shopId, posDeviceId);
            }else{
                String snNum = ThreadLocalContext.getDid();
                isSuccess = shopEquipmentWriteFacade.setMasterDeviceFirstBySn(snNum, posDeviceId);
            }
            result = Result.SUCESS(isSuccess);
        } catch (BaseException e) {
            logger.error("setMasterDevice Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

}
