package com.lizikj.api.controller.shop;

import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.shop.ShopPrinterVO;
import com.lizikj.common.exception.BaseException;
import com.lizikj.common.util.CollectionUtils;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.shop.api.dto.ShopPrinterDTO;
import com.lizikj.shop.api.enums.ShopErrorEnum;
import com.lizikj.shop.api.exception.ShopException;
import com.lizikj.shop.api.facade.IShopPrintReadFacade;
import com.lizikj.shop.api.facade.IShopPrintWriteFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * 店铺打印机接口
 *
 * @author zhoufe
 * @date 2017/07/10
 */
@Controller
@RequestMapping("/shop/printer")
@Api(value = "shop-printer", tags = "店铺打印机API接口")
public class ShopPrinterController {

    private static final Logger logger = LoggerFactory.getLogger(ShopPrinterController.class);


    @Autowired
    private IShopPrintReadFacade shopPrintReadFacade;

    @Autowired
    private IShopPrintWriteFacade shopPrintWriteFacade;

    /**
     * 查询该店铺下的打印机
     *
     * @return Result<List<ShopPrinterVO>>
     * @author zhoufe
     * @date 2017年7月20日 下午3:31:40
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/listShopPrinter")
    @ApiOperation(value = "查询该店铺下的打印机", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<ShopPrinterVO>> listShopPrinter() {
        Result<List<ShopPrinterVO>> result;
        try {
            List<ShopPrinterDTO> shopPrinterDTOS;
            Long shopId = ThreadLocalContext.getShopId();
            if (shopId == null || shopId == 0) {
                String snNum = ThreadLocalContext.getDid();
                shopPrinterDTOS = shopPrintReadFacade.listByShopIdFirstBySn(snNum);
            } else {
                shopPrinterDTOS = shopPrintReadFacade.listByShopId(shopId);
            }

            if (CollectionUtils.isListBlank(shopPrinterDTOS)) {
                return result = Result.SUCESS(new ArrayList<>());
            }
            List<ShopPrinterVO> shopMerchandiseUnitVOS = ObjectConvertUtil.getMapperFactory().getMapperFacade().mapAsList(shopPrinterDTOS, ShopPrinterVO.class);

			if (shopMerchandiseUnitVOS != null && shopMerchandiseUnitVOS.size() > 0) {
				shopMerchandiseUnitVOS.forEach((shopPrinterVO) -> {
					if (StringUtils.isBlank(shopPrinterVO.getContent())) {
						shopPrinterVO.setContent(shopPrinterVO.getLiziProducts());
					}
				});
			}
            result = Result.SUCESS(shopMerchandiseUnitVOS);
        } catch (BaseException e) {
            logger.error("listShopPrinter Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    /**
     * 删除打印机
     *
     * @param printerId
     * @return Result<Boolean>
     * @author zhoufe
     * @date 2017年7月20日 下午3:40:18
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/deleteShopPrinter")
    @ApiOperation(value = "删除打印机", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> deleteShopPrinter(
            @ApiParam(name = "printerId", value = "店铺打印机ID", required = true, type = "Long")
            @RequestParam(name = "printerId") Long printerId) {
        Result<Boolean> result;
        try {
//            String snNum = ThreadLocalContext.getDid();
//            if (StringUtils.isBlank(snNum)) {
//                throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, "获取不到POS的SN号");
//            }
            Boolean isSuccess = shopPrintWriteFacade.deleteShopPrintFirstBySn(printerId);
            result = Result.SUCESS(isSuccess);
        } catch (BaseException e) {
            logger.error("deleteShopPrinter Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }

    /**
     * 新增打印机
     *
     * @param shopPrinterVO
     * @return Result<Long>
     * @author zhoufe
     * @date 2017年7月18日 下午3:45:20
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/insertShopPrinter")
    @ApiOperation(value = "新增打印机", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Long> insertShopPrinter(
            @ApiParam(name = "addParamVO", value = "addParamVO", required = true)
            @RequestBody ShopPrinterVO shopPrinterVO) {
        Result<Long> result;
		try {
			// 新版本没有主pos概念
			// String snNum = ThreadLocalContext.getDid();
			// if (StringUtils.isBlank(snNum)) {
			// throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, "获取不到POS的SN号");
			// }

            if (shopPrinterVO == null) {
                throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, "传入参数为空");
            }

            if(!StringUtils.isEmpty(shopPrinterVO.getContent())){
                shopPrinterVO.setLiziProducts(shopPrinterVO.getContent());
            }
            ShopPrinterDTO shopPrinterDTO = ObjectConvertUtil.getMapperFactory().getMapperFacade().map(shopPrinterVO, ShopPrinterDTO.class);
            shopPrinterDTO.setShopId(ThreadLocalContext.getShopId());
			Long printerId = shopPrintWriteFacade.insertShopPrinterFirstBySn(shopPrinterDTO);
            result = Result.SUCESS(printerId);
        } catch (BaseException e) {
            logger.error("insertShopPrinter Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }

    /**
     * 更新打印机
     *
     * @param shopPrinterVO
     * @return Result<Boolean>
     * @author zhoufe
     * @date 2017年7月18日 下午3:47:32
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/updateShopPrinter")
    @ApiOperation(value = "更新打印机", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> updateShopPrinter(
            @ApiParam(name = "updateParamVO", required = true)
            @RequestBody ShopPrinterVO shopPrinterVO) {
        Result<Boolean> result;
        try {
			// 新版本没有主pos概念
			// String snNum = ThreadLocalContext.getDid();
			// if (StringUtils.isBlank(snNum)) {
			// throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, "获取不到POS的SN号");
			// }

            if (shopPrinterVO == null) {
                throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, "传入参数为空");
            }
            if(!StringUtils.isEmpty(shopPrinterVO.getContent())){
                shopPrinterVO.setLiziProducts(shopPrinterVO.getContent());
            }

            ShopPrinterDTO shopPrinterDTO = ObjectConvertUtil.getMapperFactory().getMapperFacade().map(shopPrinterVO, ShopPrinterDTO.class);
            shopPrinterDTO.setShopId(ThreadLocalContext.getShopId());
			Boolean isSuccess = shopPrintWriteFacade.updateShopPrint(shopPrinterDTO);
			result = Result.SUCESS(isSuccess);
        } catch (BaseException e) {
            logger.error("updateShopPrinter Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }


    /**
     * 批量更新打印机
     *
     * @param shopPrinterVOList
     * @return Result<Boolean>
     * @author zhoufe
     * @date 2017年7月18日 下午3:47:32
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/updateShopPrinterList")
    @ApiOperation(value = "更新打印机", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> updateShopPrinterList(
            @ApiParam(name = "updateParamVOList", required = true)
            @RequestBody List<ShopPrinterVO> shopPrinterVOList) {
        Result<Boolean> result;
        try {

            if (CollectionUtils.isListBlank(shopPrinterVOList)) {
                throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, "传入参数为空");
            }

            Long shopId = ThreadLocalContext.getShopId();
            for (ShopPrinterVO shopPrinterVO : shopPrinterVOList){
                if( ! StringUtils.isEmpty(shopPrinterVO.getContent())){
                    shopPrinterVO.setLiziProducts(shopPrinterVO.getContent());
                    shopPrinterVO.setShopId(shopId);
                }
            }

            MapperFacade mapperFacade = ObjectConvertUtil.getMapperFactory().getMapperFacade();
            List<ShopPrinterDTO> shopPrinterDTOList = mapperFacade.mapAsList(shopPrinterVOList, ShopPrinterDTO.class);
            Boolean isSuccess = shopPrintWriteFacade.updateShopPrintList(shopPrinterDTOList);
            result = Result.SUCESS(isSuccess);
        } catch (BaseException e) {
            logger.error("updateShopPrinterList Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }

    /**
     * 根据ID查询打印机
     *
     * @param shopPrinterId
     * @return Result<Boolean>
     * @author zhoufe
     * @date 2017年7月18日 下午3:47:32
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/findById/{shopPrinterId}")
    @ApiOperation(value = "根据ID查询打印机", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<ShopPrinterVO> findById(
            @ApiParam(name = "shopPrinterId", value = "打印机ID", required = true)
            @PathVariable Long shopPrinterId) {
        Result<ShopPrinterVO> result;
        try {

            ShopPrinterDTO shopPrinterDTO = shopPrintReadFacade.findById(shopPrinterId);
            if (null == shopPrinterDTO) {
                return Result.SUCESS(null);
			}
			ShopPrinterVO shopPrinterVO = ObjectConvertUtil.getMapperFactory().getMapperFacade().map(shopPrinterDTO, ShopPrinterVO.class);
			if (StringUtils.isBlank(shopPrinterVO.getContent())) {
				shopPrinterVO.setContent(shopPrinterVO.getLiziProducts());
			}
			result = Result.SUCESS(shopPrinterVO);
		} catch (BaseException e) {
			logger.error("findById Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }


}
