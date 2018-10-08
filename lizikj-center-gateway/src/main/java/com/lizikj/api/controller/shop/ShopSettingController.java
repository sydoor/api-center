package com.lizikj.api.controller.shop;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.shop.ShopSettingFeeVO;
import com.lizikj.api.vo.shop.ShopSettingVO;
import com.lizikj.api.vo.shop.param.QueryShopSettingVO;
import com.lizikj.api.vo.shop.param.ShopSettingParamVO;
import com.lizikj.common.exception.BaseException;
import com.lizikj.common.util.CollectionUtils;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.shop.api.dto.ShopSettingDTO;
import com.lizikj.shop.api.dto.ShopSettingFeeDTO;
import com.lizikj.shop.api.dto.param.QueryShopSettingDTO;
import com.lizikj.shop.api.dto.param.ShopSettingFeeParamDTO;
import com.lizikj.shop.api.enums.BizTypeEnum;
import com.lizikj.shop.api.enums.ShopErrorEnum;
import com.lizikj.shop.api.enums.ShopSettingCodeEnum;
import com.lizikj.shop.api.exception.ShopException;
import com.lizikj.shop.api.facade.IShopSettingFeeReadFacade;
import com.lizikj.shop.api.facade.IShopSettingReadFacade;
import com.lizikj.shop.api.facade.IShopSettingWriteFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;


/**
 * 店铺配置服务
 * @author zhoufe
 * @date 2017/07/10
 */
@Controller
@RequestMapping("/shop/setting")
@Api(value = "shop-setting", tags = "店铺配置API接口")
public class ShopSettingController {

	private static final Logger logger = LoggerFactory.getLogger(ShopSettingController.class);

	@Autowired
	private IShopSettingReadFacade shopSettingReadFacade;

	@Autowired
	private IShopSettingWriteFacade shopSettingWriteFacade;
	
	@Autowired
	private IShopSettingFeeReadFacade shopSettingFeeReadFacade;

    /**
     * 获取店铺业务开关
     * @return Result<List<ShopSettingVO>>
     * @author zhoufe
     * @date 2017年7月19日 下午2:45:57
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/listShopSettings")
    @ApiOperation(value = "获取店铺的业务开关", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<ShopSettingVO>> listShopSettings() {
        Result<List<ShopSettingVO>> result;
        try {
			Long shopId = ThreadLocalContext.getShopId();
			Long merchantId = ThreadLocalContext.getMerchantId();
			//shopId 111 merchantId 111 //店铺，单店老板
			//shopId 0   merchantId 111 //多店老板
			//&& (null != merchantId && 0 != merchantId)
			List<ShopSettingDTO>  shopSettingDTOS;
			if (isShopAndSingleShop(shopId, merchantId)){//单店老板登录
				shopSettingDTOS = shopSettingReadFacade.listShopSettings(shopId);
			}else if (isChainShop(shopId, merchantId)){
				shopSettingDTOS = shopSettingReadFacade.listShopSettingsByMerchantId(merchantId);
			}else{
				throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, String.format("店铺ID(%s)和商户ID(%s)同时为空或者为0", shopId, merchantId));
			}
            if (CollectionUtils.isListBlank(shopSettingDTOS)){
				throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, String.format("店铺ID(%s)的配置为空", shopId));
			}
			if (CollectionUtils.isListBlank(shopSettingDTOS)){
				result = Result.SUCESS(new ArrayList<>());
				return result;
			}
			MapperFactory mapperFactory = ObjectConvertUtil.getMapperFactory();
			List<ShopSettingVO> shopSettingVOS = mapperFactory.getMapperFacade().mapAsList(shopSettingDTOS, ShopSettingVO.class);
			result = Result.SUCESS(shopSettingVOS);
        } catch (BaseException e) {
            logger.error("listShopSettings Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        
        return result;
    }

    /**
     * 获取店铺业务开关
     * @return Result<List<ShopSettingVO>>
     * @author zhoufe
     * @date 2017年7月19日 下午2:45:57
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/queryShopSettings")
    @ApiOperation(value = "查询店铺的业务开关", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PageInfo<ShopSettingVO>> queryShopSettings(
            @ApiParam(value = "paramVO", name = "paramVO", required = true)
    		@RequestBody QueryShopSettingVO queryShopSettingVO) {
        Result<PageInfo<ShopSettingVO>> result;
        try {

            if (null == queryShopSettingVO){
				return Result.SUCESS(null);
			}

			MapperFacade mapperFacade = ObjectConvertUtil.getMapperFactory().getMapperFacade();

			QueryShopSettingDTO queryShopSettingDTO = mapperFacade.map(queryShopSettingVO, QueryShopSettingDTO.class);
			PageInfo<ShopSettingDTO> page = shopSettingReadFacade.queryShopSettings(queryShopSettingDTO, queryShopSettingVO.getPageNum(), queryShopSettingVO.getPageSize());

			PageInfo pageInfo = page;

			if (null == pageInfo){
				return Result.SUCESS(null);
			}

			List<ShopSettingDTO> list = page.getList();
			List<ShopSettingVO> shopSettingVOS = mapperFacade.mapAsList(list, ShopSettingVO.class);
			pageInfo.setList(shopSettingVOS);
			result = Result.SUCESS(pageInfo);
        } catch (BaseException e) {
            logger.error("queryShopSettings Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }





	/**
	 * 根据IDS获取店铺业务开关
	 * @return Result<List<ShopSettingVO>>
	 * @author zhoufe
	 * @date 2017年7月19日 下午2:45:57
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/listShopSettingsByIds")
	@ApiOperation(value = "根据IDS获取店铺业务开关", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<ShopSettingVO>> listShopSettingsByIds(
			@ApiParam(name = "list", value = "list", required = true)
			@RequestBody List<Long> ids
	) {
		Result<List<ShopSettingVO>> result;
		try {
			Long shopId = ThreadLocalContext.getShopId();
			Long merchantId = ThreadLocalContext.getMerchantId();

			if (CollectionUtils.isListBlank(ids)){
				throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, "传入ID列表为空");
			}

			List<ShopSettingDTO> shopSettingDTOS = shopSettingReadFacade.listShopSettingsByIds(ids);
			if (CollectionUtils.isListBlank(shopSettingDTOS)){
				result = Result.SUCESS(new ArrayList<>());
				return result;
			}
			MapperFactory mapperFactory = ObjectConvertUtil.getMapperFactory();
			List<ShopSettingVO> shopSettingVOS = mapperFactory.getMapperFacade().mapAsList(shopSettingDTOS, ShopSettingVO.class);
			result = Result.SUCESS(shopSettingVOS);
		} catch (BaseException e) {
			logger.error("listShopSettingsByIds Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}

		return result;
	}




	/**
	 * 店铺获取连锁的业务开关
	 * @return Result<List<ShopSettingVO>>
	 * @author zhoufe
	 * @date 2017年7月19日 下午2:45:57
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/listChainShopSettings4Shop")
	@ApiOperation(value = "店铺获取连锁的业务开关：每个店铺获取连锁店铺的开关要实时去拿", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<ShopSettingVO>> listChainShopSettings4Shop() {
		Result<List<ShopSettingVO>> result;
		try {
			Long shopId = ThreadLocalContext.getShopId();
			Long merchantId = ThreadLocalContext.getMerchantId();
			if (isMerchantIdNull(merchantId)){
				throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, String.format("店铺ID(%s)的商户ID(%s)为空或者为0", shopId, merchantId));
			}
		    List<ShopSettingDTO> shopSettingDTOS = shopSettingReadFacade.listShopSettingsByMerchantId(merchantId);
			if (CollectionUtils.isListBlank(shopSettingDTOS)){
				result = Result.SUCESS(new ArrayList<>());
				return result;
			}
			MapperFactory mapperFactory = ObjectConvertUtil.getMapperFactory();
			List<ShopSettingVO> shopSettingVOS = mapperFactory.getMapperFacade().mapAsList(shopSettingDTOS, ShopSettingVO.class);
			result = Result.SUCESS(shopSettingVOS);
		} catch (BaseException e) {
			logger.error("listChainShopSettings4Shop Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}

		return result;
	}

	/**
	 * 是老板登录
	 * @params [shopId, merchantId]
	 * @return boolean
	 * @author zhoufe
	 * @date 2017/9/16 12:36
	 */
	private boolean isChainShop(Long shopId, Long merchantId) {
		return isShopIdNull(shopId) && isMerchantIdNotNull(merchantId);
	}


	/**
	 * 是门店，单店老板的登录：单店店长的登录也是门店的登录
	 * @params [shopId, merchantId]
	 * @return boolean
	 * @author zhoufe
	 * @date 2017/9/16 12:33
	 */
	private boolean isShopAndSingleShop(Long shopId, Long merchantId) {
		return isShopIdNotNull(shopId) && isMerchantIdNotNull(merchantId);
	}

	private boolean isShopIdNotNull(Long shopId) {
		return null != shopId && 0 != shopId;
	}

	private boolean isMerchantIdNotNull(Long merchantId) {
		return null != merchantId && 0 != merchantId;
	}

	private boolean isMerchantIdNull(Long merchantId) {
		return null == merchantId || 0 == merchantId;
	}

	private boolean isShopIdNull(Long shopId) {
		return null == shopId || 0 == shopId;
	}


	/**
	 * 新增自定义配置
     * @param shopSettingParamVO
     * @return Result<ShopSettingVO>
     * @author zhoufe
     * @date 2017年7月19日 下午3:54:25
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/insertShopSetting")
    @ApiOperation(value = "新增自定义费用配置", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<ShopSettingVO> insertShopSetting(
 	       	   @ApiParam(name = "addParamVO", value = "addParamVO", required = true)
 	       	   @RequestBody ShopSettingParamVO shopSettingParamVO
    		){
    	Result<ShopSettingVO> result;

    	try {
			Long shopId = ThreadLocalContext.getShopId();
			Long merchantId = ThreadLocalContext.getMerchantId();

			if (isMerchantIdNull(merchantId)){
				throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, String.format("店铺(%s)的商户ID为空或者为0(%s)", shopId, merchantId));
			}

			MapperFactory mapperFactory = ObjectConvertUtil.getMapperFactory();
			ShopSettingDTO shopSettingDTO = mapperFactory.getMapperFacade().map(shopSettingParamVO, ShopSettingDTO.class);
			if (shopSettingDTO != null){
				shopSettingDTO.setShopId(shopId);
				shopSettingDTO.setMerchantId(merchantId);
			}
			ShopSettingDTO newCoustomShopSettingDTO = shopSettingWriteFacade.insertShopSetting(shopSettingDTO);
			ShopSettingVO shopSettingVO = mapperFactory.getMapperFacade().map(newCoustomShopSettingDTO, ShopSettingVO.class);

      		result = Result.SUCESS(shopSettingVO);
    	} catch (BaseException e) {
    		logger.error("insertShopSetting Exception: {}", e);
    		result = Result.FAILURE(e.getCode(), e.getMessage());
    	}

    	return result;
	}

	/**
     * 更新配置：返回被联动修改的开关
     * @param shopSettingParamVOS
     * @return Result<Boolean>
     * @author zhoufe
     * @date 2017年7月19日 下午4:30:28
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/updateShopSettings")
    @ApiOperation(value = "更新配置：返回被联动修改的开关", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<ShopSettingVO>> updateShopSettings(
	       	   @ApiParam(name = "updateParamVO", value = "updateParamVO", required = true)
	       	   @RequestBody List<ShopSettingParamVO> shopSettingParamVOS
 			){
		Result<List<ShopSettingVO>> result;

		try {
			long shopId = ThreadLocalContext.getShopId();
			if (0 == shopId){
				throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, "传入的店铺ID为0");
			}

			if(CollectionUtils.isListBlank(shopSettingParamVOS)){
				throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, "传入修改的参数为空");
			}

			MapperFactory mapperFactory = ObjectConvertUtil.getMapperFactory();
			List<ShopSettingDTO> shopSettingParamDTOS = mapperFactory.getMapperFacade().mapAsList(shopSettingParamVOS, ShopSettingDTO.class);
			if (CollectionUtils.isListNotBlank(shopSettingParamDTOS)){
				for (ShopSettingDTO shopSettingDTO : shopSettingParamDTOS) {
					shopSettingDTO.setShopId(shopId);

					//检查厨打开关：是不是主pos修改
					Integer settingCode = shopSettingDTO.getSettingCode();
					if (! ShopSettingCodeEnum.KITCHEN_PRINTER.getCode().equals(settingCode)) {
						continue;
					}
					String sn = ThreadLocalContext.getDid();
					shopSettingDTO.setSn(sn);

				}
			}

			List<ShopSettingVO> shopSettingVOS = new ArrayList<>();
			List<ShopSettingDTO> shopSettingDTOS = shopSettingWriteFacade.updateShopSettings(shopSettingParamDTOS);
            if (CollectionUtils.isListNotBlank(shopSettingDTOS)){
				shopSettingVOS = mapperFactory.getMapperFacade().mapAsList(shopSettingDTOS, ShopSettingVO.class);
			}

			result = Result.SUCESS(shopSettingVOS);
		} catch (BaseException e) {
			logger.error("updateShopSettings Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}

		return result;
	}

    
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/deleteShopSetting")
    @ApiOperation(value = "删除配置", notes = "删除配置", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> deleteShopSetting(
 	       	   @ApiParam(name = "shopSettingId", value = "店铺配置ID", required = true, type = "Long")
 	       	   @RequestBody Long shopSettingId
    		){
    	Result<Boolean> result;
    	try {
			Long shopId = ThreadLocalContext.getShopId();
			Boolean isSuccess = shopSettingWriteFacade.deleteShopSetting(shopId, shopSettingId);
      		result = Result.SUCESS(isSuccess);
    	} catch (BaseException e) {
    		logger.error("deleteShopSetting Exception: {}", e);
    		result = Result.FAILURE(e.getCode(), e.getMessage());
    	}    	
    	
    	return result;
	}
    
    
	/**
	 * 获取该店铺下该业务编码的设置
	 * @params [shopId, settingCode]
	 * @return com.lizikj.api.vo.Result<com.lizikj.api.vo.shop.ShopSettingVO>
	 * @author zhoufe
	 * @date 2017/7/24 15:13
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/getShopSetting/{settingCode}")
	@ApiOperation(value = "获取该店铺下该业务编码的设置：不会从模板生成", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<ShopSettingVO> getShopSetting(
			@ApiParam(name = "settingCode", value = "业务编码", required = true, type = "String")
			@PathVariable(name = "settingCode") Integer settingCode
	){

		Result<ShopSettingVO> result;

		try {
			Long shopId = ThreadLocalContext.getShopId();
			ShopSettingCodeEnum shopSettingCodeEnum = ShopSettingCodeEnum.fromCode(settingCode);
			if (null == shopSettingCodeEnum){
				throw new ShopException(ShopErrorEnum.PARAMETERS_ERROR, String.format("输入的settingCode(%s)在枚举ShopSettingCodeEnum中找不到值", settingCode));
			}
			ShopSettingDTO shopSettingDTO =  shopSettingReadFacade.findShopSettingByCode(shopId, shopSettingCodeEnum);

			MapperFactory mapperFactory = ObjectConvertUtil.getMapperFactory();
			ShopSettingVO shopSettingVO = mapperFactory.getMapperFacade().map(shopSettingDTO, ShopSettingVO.class);
			result = Result.SUCESS(shopSettingVO);
		} catch (BaseException e) {
			logger.error("getShopSetting Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}

		return result;
	}
     /**
	 * 获取该店铺下该所属业务的设置
	 * @params [shopId, settingCode]
	 * @return com.lizikj.api.vo.Result<com.lizikj.api.vo.shop.ShopSettingVO>
	 * @author zhoufe
	 * @date 2017/7/24 15:13
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/getShopSettingByBizType/{bizType}")
	@ApiOperation(value = "获取该店铺下该所属业务的设置：不会从模板生成", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<ShopSettingVO> getShopSettingByBizType(
			@ApiParam(name = "bizType", value = "所属业务：SHOP.店铺，.MERCHANDISE.美食，.MARKETING.营销，MEMBER.会员。", required = true)
			@PathVariable(name = "bizType") BizTypeEnum bizType
			){

		Result<ShopSettingVO> result;

		try {
			Long shopId = ThreadLocalContext.getShopId();
			List<ShopSettingDTO> shopSettingDTOS =  shopSettingReadFacade.findShopSettingByBizType(shopId, bizType);

			MapperFactory mapperFactory = ObjectConvertUtil.getMapperFactory();
			List<ShopSettingVO> shopSettingVOS = mapperFactory.getMapperFacade().mapAsList(shopSettingDTOS, ShopSettingVO.class);
			result = Result.SUCESS(shopSettingVOS);
		} catch (BaseException e) {
			logger.error("getShopSettingByBizType Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}

		return result;
	}

	/**
	 * 获取店铺茶位费
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getShopTeaFee")
	@ApiOperation(value = "获取店铺茶位费", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<ShopSettingFeeVO> getShopTeaFee() {
		Result<ShopSettingFeeVO> result;
		try {
			Long shopId = ThreadLocalContext.getShopId();
			ShopSettingFeeParamDTO shopTeaFeeParamDTO = new ShopSettingFeeParamDTO();
			shopTeaFeeParamDTO.setShopId(shopId);
			ShopSettingFeeDTO shopSettingFeeDTO = shopSettingFeeReadFacade.getShopTeaFee(shopTeaFeeParamDTO);
			if (shopSettingFeeDTO == null) {
				result = Result.FAILURE();
			} else {
				ShopSettingFeeVO shopSettingFeeVO = ObjectConvertUtil.map(shopSettingFeeDTO, ShopSettingFeeVO.class);
				result = Result.SUCESS(shopSettingFeeVO);
			}
		} catch (BaseException e) {
			logger.error("getShopTeaFee Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}

		return result;
	}


	/**
	 * 获取店铺茶位费V2
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getShopTeaFeeV2")
	@ApiOperation(value = "获取店铺茶位费V2：如果不传桌台ID，取的是默认区域的茶位费，传了桌台ID就取该桌台所属区域的茶位费", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<ShopSettingFeeVO> getShopTeaFeeV2(
			@ApiParam(name = "deskId",value = "桌台ID",required = true)
			@RequestParam(value = "deskId") Long deskId) {
		Result<ShopSettingFeeVO> result;
		try {

			Long shopId = ThreadLocalContext.getShopId();
			ShopSettingFeeParamDTO shopTeaFeeParamDTO = new ShopSettingFeeParamDTO();
			shopTeaFeeParamDTO.setShopId(shopId);
			shopTeaFeeParamDTO.setDeskId(deskId);
			ShopSettingFeeDTO shopSettingFeeDTO = shopSettingFeeReadFacade.getShopTeaFee(shopTeaFeeParamDTO);
			if (shopSettingFeeDTO == null) {
				result = Result.FAILURE();
			} else {
				ShopSettingFeeVO shopSettingFeeVO = ObjectConvertUtil.map(shopSettingFeeDTO, ShopSettingFeeVO.class);
				result = Result.SUCESS(shopSettingFeeVO);
			}
		} catch (BaseException e) {
			logger.error("getShopTeaFeeV2 Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}

		return result;
	}

}
