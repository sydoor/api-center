package com.lizikj.api.controller.shop;

import java.util.List;

import com.lizikj.common.exception.BaseException;
import com.lizikj.shop.api.facade.IShopSettingTemplateReadFacade;
import com.lizikj.shop.api.facade.IShopSettingTemplateWriteFacade;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.shop.ShopSettingTemplateVO;
import com.lizikj.api.vo.shop.ShopSettingTemplateValueVO;
import com.lizikj.common.exception.ArgumentCheckException;
import com.lizikj.common.util.CollectionUtils;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.shop.api.dto.ShopSettingTemplateDTO;
import com.lizikj.shop.api.dto.ShopSettingTemplateValueDTO;
import com.lizikj.shop.api.exception.ShopException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 店铺配置模板服务
 * @author zhoufe
 * @date 2017/07/10
 */
@Controller
@RequestMapping("/shop/setting/templte")
@Api(value = "shop-setting-templte", tags = "店铺配置模板API接口")
public class ShopSettingTemplateController {

    private static final Logger logger = LoggerFactory.getLogger(ShopSettingTemplateController.class);

	@Autowired
	private IShopSettingTemplateReadFacade shopSettingTemplateReadFacade;

	@Autowired
	private IShopSettingTemplateWriteFacade shopSettingTemplateWriteFacade;

	/**
     * 获取业务开关模板
     * @return Result<List<ShopSettingTemplateVO>>
     * @author zhoufe
     * @date 2017年7月19日 下午2:46:36
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/listShopSettingTemplates")
    @ApiOperation(value = "获取业务开关模板", notes = "获取业务开关模板", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<ShopSettingTemplateVO>> listShopSettingTemplates() {
        Result<List<ShopSettingTemplateVO>> result = null;
        List<ShopSettingTemplateVO> apiShopSettingTemplateVOs = null;
        List<ShopSettingTemplateDTO> shopSettingTemplateDtos = null;
        try {
        	shopSettingTemplateDtos = shopSettingTemplateReadFacade.listShopSettingTemplates();
        	apiShopSettingTemplateVOs = ObjectConvertUtil.copyListProperties(shopSettingTemplateDtos, ShopSettingTemplateVO.class);
        	assignShopSettingTemplateValueVOs(apiShopSettingTemplateVOs, shopSettingTemplateDtos);
        	result = Result.SUCESS(apiShopSettingTemplateVOs);
        } catch (BaseException e) {
            logger.error("listShopSettingTemplates Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        } 
        return result;
    }

	/**
	 * 根据settingCode查询模板配置
	 * @params [settingCode]
	 * @return com.lizikj.api.vo.Result<com.lizikj.api.vo.shop.ShopSettingTemplateVO>
	 * @author zhoufe
	 * @date 2017/8/12 15:08
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/getShopSettingTemplateBySettingCode/{settingCode}")
	@ApiOperation(value = "根据settingCode查询模板配置", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<ShopSettingTemplateVO> getShopSettingTemplateBySettingCode(
			@ApiParam(value = "settingCode", name = "模板编码")
			Integer settingCode) {
		Result<ShopSettingTemplateVO> result;
		try {
			ShopSettingTemplateDTO shopSettingTemplateDTO = shopSettingTemplateReadFacade.getShopSettingTemplateBySettingCode(settingCode);
			ShopSettingTemplateVO shopSettingTemplateVO = ObjectConvertUtil.copyProperties(ShopSettingTemplateVO.class, shopSettingTemplateDTO);
			List<ShopSettingTemplateValueDTO> shopSettingTemplateValueDTOs = shopSettingTemplateDTO.getShopSettingTemplateValues();
			if (shopSettingTemplateValueDTOs != null) {
				List<ShopSettingTemplateValueVO> shopSettingTemplateValueVOS = ObjectConvertUtil.copyListProperties(shopSettingTemplateValueDTOs, ShopSettingTemplateValueVO.class);
				shopSettingTemplateVO.setShopSettingTemplateValues(shopSettingTemplateValueVOS);
			}
			result = Result.SUCESS(shopSettingTemplateVO);
		} catch (BaseException e) {
			logger.error("getShopSettingTemplateBySettingCode Exception: {}", e);
			result = Result.FAILURE(e.getCode(), e.getMessage());
		}
		return result;
	}
    
	/**
	 * 赋值操作
	 * @param shopSettingTemplateVOs
	 * @param shopSettingTemplateDtos void
	 * @author zhoufe 
	 * @date 2017年7月11日 上午9:57:19
	 */
	private void assignShopSettingTemplateValueVOs(List<ShopSettingTemplateVO> shopSettingTemplateVOs,
			List<ShopSettingTemplateDTO> shopSettingTemplateDtos) {
		if(CollectionUtils.isListNotBlank(shopSettingTemplateVOs)){
			for (ShopSettingTemplateVO apiShopSettingTemplateVO : shopSettingTemplateVOs) {
				ShopSettingTemplateDTO shopSettingTemplateDto = findShopSettingTemplateDtoById(apiShopSettingTemplateVO.getSettingTemplateId(),shopSettingTemplateDtos);
				List<ShopSettingTemplateValueDTO> shopSettingTemplateValueDtos = shopSettingTemplateDto.getShopSettingTemplateValues();
				List<ShopSettingTemplateValueVO> shopSettingTemplateValueVOs = ObjectConvertUtil.copyListProperties(shopSettingTemplateValueDtos, ShopSettingTemplateValueVO.class);
				apiShopSettingTemplateVO.setShopSettingTemplateValues(shopSettingTemplateValueVOs);
			}
		}else{
			logger.warn("店铺业务开关模板VO为空！");
		}
	}

	/**
	 * 根据ID查找模板
	 * @param settingTemplateId
	 * @param shopSettingTemplateDtos
	 * @return ShopSettingTemplateDto
	 * @author zhoufe 
	 * @date 2017年7月11日 上午10:39:44
	 */
	private ShopSettingTemplateDTO findShopSettingTemplateDtoById(Long settingTemplateId,
			List<ShopSettingTemplateDTO> shopSettingTemplateDtos) {
		if(CollectionUtils.isListNotBlank(shopSettingTemplateDtos)){
			for (ShopSettingTemplateDTO shopSettingTemplateDto : shopSettingTemplateDtos) {
				if(shopSettingTemplateDto.getSettingTemplateId().equals(settingTemplateId)){
					return shopSettingTemplateDto;
				}
			}
		}else{
			logger.warn("({})店铺业务开关模板DTO为空！", settingTemplateId);
		}
		return null;
	}

    
}
