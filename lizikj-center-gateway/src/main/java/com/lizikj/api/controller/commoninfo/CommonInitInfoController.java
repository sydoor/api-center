package com.lizikj.api.controller.commoninfo;

import static java.util.stream.Collectors.toList;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.commoninfo.AppShopInitInfoVO;
import com.lizikj.api.vo.commoninfo.CommonInfoVO;
import com.lizikj.api.vo.commoninfo.PosShopInitInfoVO;
import com.lizikj.api.vo.merchandise.CategoryVO;
import com.lizikj.api.vo.merchandise.GoodsAndCategoryVO;
import com.lizikj.api.vo.merchandise.GoodsVO;
import com.lizikj.api.vo.merchant.MerchantPaymentConfigVO;
import com.lizikj.api.vo.shop.H5QrCodeVO;
import com.lizikj.api.vo.shop.ShopAreaVO;
import com.lizikj.api.vo.shop.ShopDeskVO;
import com.lizikj.api.vo.shop.ShopPrinterVO;
import com.lizikj.api.vo.shop.ShopSettingVO;
import com.lizikj.common.exception.BaseException;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.merchandise.dto.CategoryDTO;
import com.lizikj.merchandise.dto.GoodsDTO;
import com.lizikj.merchandise.enums.CategoryCodeEnum;
import com.lizikj.merchandise.enums.YesOrNoEnum;
import com.lizikj.merchandise.facade.ICategoryReadFacade;
import com.lizikj.merchandise.facade.IGoodsReadFacade;
import com.lizikj.merchant.dto.AuthShopMenuTemplateDTO;
import com.lizikj.merchant.dto.MerchantPaymentConfigDTO;
import com.lizikj.merchant.dto.ShopDTO;
import com.lizikj.merchant.dto.ShopExpandDTO;
import com.lizikj.merchant.dto.ShopMerchantDTO;
import com.lizikj.merchant.enums.MenuTemplateAppTypeEnum;
import com.lizikj.merchant.enums.MenuTemplateObjectTypeEnum;
import com.lizikj.merchant.enums.MerchantErrorEnum;
import com.lizikj.merchant.enums.ShopMarketingEnableStatusEnum;
import com.lizikj.merchant.exception.MerchantException;
import com.lizikj.merchant.facade.IAuthShopMenuTemplateReadFacade;
import com.lizikj.merchant.facade.IMerchantPaymentConfigReadFacade;
import com.lizikj.merchant.facade.IShopExpandReadFacade;
import com.lizikj.merchant.facade.IShopMerchantReadFacade;
import com.lizikj.merchant.facade.IShopReadFacade;
import com.lizikj.message.api.enums.TerminalTypeEnum;
import com.lizikj.shop.api.dto.ShopAreaDTO;
import com.lizikj.shop.api.dto.ShopDeskDTO;
import com.lizikj.shop.api.dto.ShopPrinterDTO;
import com.lizikj.shop.api.dto.ShopSettingDTO;
import com.lizikj.shop.api.enums.BizTypeEnum;
import com.lizikj.shop.api.exception.ShopException;
import com.lizikj.shop.api.facade.IShopAreaReadFacade;
import com.lizikj.shop.api.facade.IShopDeskReadFacade;
import com.lizikj.shop.api.facade.IShopPrintReadFacade;
import com.lizikj.shop.api.facade.IShopSettingReadFacade;
import com.lizikj.user.constants.WechatQrCodeConstants;
import com.lizikj.user.facade.IWechatPublicAuthorizationFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ma.glasnost.orika.MapperFactory;

/**
 * @author dyh
 * @created at 2017 10 16 17:22
 */
@RestController
@RequestMapping("/initInfo")
@Api(value = "init-info", tags = "店铺初始化信息")
public class CommonInitInfoController {

    private static final Logger logger = LoggerFactory.getLogger(CommonInitInfoController.class);

    @Autowired
    private IShopSettingReadFacade shopSettingReadFacade;

    @Autowired
    private IGoodsReadFacade goodsReadFacade;

    @Autowired
    private IShopPrintReadFacade shopPrintReadFacade;

    @Autowired
    private Environment environment;

    @Autowired
    private ICategoryReadFacade categoryReadFacade;

    @Autowired
    private IMerchantPaymentConfigReadFacade merchantPaymentConfigReadFacade;

    @Autowired
    private IShopReadFacade shopReadFacade;

    @Autowired
    private IShopExpandReadFacade shopExpandReadFacade;

    @Autowired
    private IShopAreaReadFacade shopAreaReadFacade;

    @Autowired
    private IShopDeskReadFacade shopDeskReadFacade;

    @Autowired
    private IShopMerchantReadFacade shopMerchantReadFacade;
	
	@Autowired
    private IWechatPublicAuthorizationFacade wechatPublicAuthorizationFacade;
	
	@Autowired
	private IAuthShopMenuTemplateReadFacade authShopMenuTemplateReadFacade;
	
    @RequestMapping("/commonInitInfo4POS")
    @ApiOperation(value = "POS 端获取该店铺的配置信息", notes = "POS 端获取该店铺的配置信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PosShopInitInfoVO> commonInitInfo4POS(){
        Result<PosShopInitInfoVO> result;

        try {
            Long shopId = ThreadLocalContext.getShopId();
            String sname = ThreadLocalContext.getSname();

            PosShopInitInfoVO posShopInitInfoVO = getPosShopInitInfoVO(shopId);

            GoodsAndCategoryVO goodsAndCategoryVO = new GoodsAndCategoryVO();
            List<GoodsDTO> goodsDTOS = goodsReadFacade.findByShopId(shopId, YesOrNoEnum.NO);

            //20180705zfe 新推送要求放回全部数据给前端
//            List<GoodsDTO> goodsDTOS2 = null;
//            if (!CollectionUtils.isEmpty(goodsDTOS)){
//                goodsDTOS2 = goodsDTOS.stream().filter(goodsDTO -> goodsDTO.getShelveState() == ShelveStateEnum.ON_LINE).collect(toList());
//            }

            List<CategoryDTO> categoryDTOS = categoryReadFacade.listCategoriesByCategoryCode(shopId, CategoryCodeEnum.QUICK_MENU);

            if (!CollectionUtils.isEmpty(goodsDTOS)) {
                convertResult(goodsAndCategoryVO, goodsDTOS);
            }

            setQuickMenu(goodsAndCategoryVO, categoryDTOS);

            List<ShopPrinterVO> shopPrinterVOS = getShopPrinterVOS(shopId);


            List<MerchantPaymentConfigVO> merchantPaymentConfigVOS = getChannelByShopId(shopId);
            List<ShopAreaVO> shopAreaVOS = getShopAreaVOS(shopId);
            List<ShopDeskVO> shopDeskVOS = getShopDeskVOS(shopId);

            CommonInfoVO commonInfoVO = new CommonInfoVO();
            commonInfoVO.setEnvironment(environment.getProperty("spring.profiles.active"));
            commonInfoVO.setCurrentTime(System.currentTimeMillis());
            posShopInitInfoVO.setCommonInfo(commonInfoVO);
            posShopInitInfoVO.setShopPrinterVOS(shopPrinterVOS);
            posShopInitInfoVO.setGoodsAndCategoryVO(goodsAndCategoryVO);

            posShopInitInfoVO.setMerchantPaymentConfigs(merchantPaymentConfigVOS);
            posShopInitInfoVO.setShopAreas(shopAreaVOS);
            posShopInitInfoVO.setShopDesks(shopDeskVOS);

            posShopInitInfoVO.setH5QrCode(getH5QrCodeVO());
            posShopInitInfoVO.setMenuTemplate(getMenuTemplate(shopId, sname));
            result = Result.SUCESS(posShopInitInfoVO);
        }catch (ShopException e){
            logger.error("commonInitInfo4POS Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    /**
     * 封装店铺设置数据
     * @params [shopId]
     * @return com.lizikj.api.vo.commoninfo.PosShopInitInfoVO
     * @author zhoufe
     * @date 2017/12/27 10:22
     */
    private PosShopInitInfoVO getPosShopInitInfoVO(Long shopId) {
        List<ShopSettingDTO> shopSettingDTOList = shopSettingReadFacade.listShopSettings(shopId);

        PosShopInitInfoVO posShopInitInfoVO = new PosShopInitInfoVO();

        if (!CollectionUtils.isEmpty(shopSettingDTOList)){
            Map<BizTypeEnum,List<ShopSettingDTO>> groupedShopSetting = shopSettingDTOList.stream().collect(Collectors.groupingBy(ShopSettingDTO::getBizType));
            for (Map.Entry<BizTypeEnum,List<ShopSettingDTO>> entry: groupedShopSetting.entrySet()) {
                BizTypeEnum bizType = entry.getKey();
                List<ShopSettingDTO> settingDTOS = entry.getValue();

                if (bizType == BizTypeEnum.SHOP){
                    posShopInitInfoVO.setShopSettings(ObjectConvertUtil.mapList(settingDTOS,ShopSettingDTO.class, ShopSettingVO.class));
                }

                if (bizType == BizTypeEnum.MERCHANDISE){
                    posShopInitInfoVO.setMerchandiseSettings(ObjectConvertUtil.mapList(settingDTOS,ShopSettingDTO.class, ShopSettingVO.class));
                }

                if (bizType == BizTypeEnum.MEMBER){
                    posShopInitInfoVO.setMemberSettings(ObjectConvertUtil.mapList(settingDTOS,ShopSettingDTO.class, ShopSettingVO.class));
                }

                if (bizType == BizTypeEnum.MARKETING){
                    posShopInitInfoVO.setMarketingSettings(ObjectConvertUtil.mapList(settingDTOS,ShopSettingDTO.class, ShopSettingVO.class));
                }
            }
        }
        return posShopInitInfoVO;
    }


    @RequestMapping("/commonInitInfo4PosOffLine")
    @ApiOperation(value = "POS 端获取该店铺的配置信息：离线信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PosShopInitInfoVO> commonInitInfo4PosOffLine(){
        Result<PosShopInitInfoVO> result;

        try {
            Long shopId = ThreadLocalContext.getShopId();
            PosShopInitInfoVO posShopInitInfoVO = getPosShopInitInfoVO(shopId);
            List<ShopPrinterVO> shopPrinterVOS = getShopPrinterVOS(shopId);
            List<MerchantPaymentConfigVO> merchantPaymentConfigVOS = getChannelByShopId(shopId);
            List<ShopAreaVO> shopAreaVOS = getShopAreaVOS(shopId);
            List<ShopDeskVO> shopDeskVOS = getShopDeskVOS(shopId);


            CommonInfoVO commonInfoVO = new CommonInfoVO();
            commonInfoVO.setEnvironment(environment.getProperty("spring.profiles.active"));
            commonInfoVO.setCurrentTime(System.currentTimeMillis());
            posShopInitInfoVO.setCommonInfo(commonInfoVO);
            posShopInitInfoVO.setShopPrinterVOS(shopPrinterVOS);
            posShopInitInfoVO.setMerchantPaymentConfigs(merchantPaymentConfigVOS);
            posShopInitInfoVO.setShopAreas(shopAreaVOS);
            posShopInitInfoVO.setShopDesks(shopDeskVOS);
            posShopInitInfoVO.setGoodsAndCategoryVO(null);
            posShopInitInfoVO.setH5QrCode(getH5QrCodeVO());
            
            result = Result.SUCESS(posShopInitInfoVO);
        } catch (BaseException e) {
            logger.error("commonInitInfo4PosOffLine Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    /**
     * 获取桌台数据
     * @params [shopId]
     * @return java.util.List<com.lizikj.api.vo.shop.ShopDeskVO>
     * @author zhoufe
     * @date 2017/12/27 11:45
     */
    private List<ShopDeskVO> getShopDeskVOS(Long shopId) {
        List<ShopDeskDTO> shopDeskDTOS = shopDeskReadFacade.listShopDesk(shopId);
        if (CollectionUtils.isEmpty(shopDeskDTOS)){
            return null;
        }
        MapperFactory mapperFactory = ObjectConvertUtil.getMapperFactory();
        return mapperFactory.getMapperFacade().mapAsList(shopDeskDTOS, ShopDeskVO.class);
    }

    /**
     * 获取店铺桌台
     * @params [shopId]
     * @return java.util.List<com.lizikj.api.vo.shop.ShopAreaVO>
     * @author zhoufe
     * @date 2017/12/27 11:03
     */
    private List<ShopAreaVO> getShopAreaVOS(Long shopId) {
        List<ShopAreaDTO> shopAreaDTOS = shopAreaReadFacade.listShopArea(shopId);
        if (CollectionUtils.isEmpty(shopAreaDTOS)){
            return null;
        }
        List<ShopAreaVO> shopAreaVOS = ObjectConvertUtil.getMapperFactory().getMapperFacade().mapAsList(shopAreaDTOS, ShopAreaVO.class);

        for (ShopAreaVO shopAreaVO : shopAreaVOS) {
            shopAreaVO.setDeskTotal(shopAreaReadFacade.countDeskTotal(shopAreaVO.getShopAreaId()));
        }

        return shopAreaVOS;
    }

    /**
     * 店铺配置的通道
     * @params [shopId]
     * @return java.util.List<com.lizikj.api.vo.merchant.MerchantPaymentConfigVO>
     * @author zhoufe
     * @date 2017/12/27 10:49
     */
    private List<MerchantPaymentConfigVO> getChannelByShopId(Long shopId) {
        List<MerchantPaymentConfigVO> channelList = null;
        ShopDTO shop = shopReadFacade.findById(shopId);
        ShopExpandDTO shopExpand = shopExpandReadFacade.findById(shopId);
        if (shop == null || shopExpand == null) {
            throw new MerchantException(MerchantErrorEnum.MERCHANT_COMMON_ERROR_DATA_NOT_FOUND, "店铺不存在");
        }
        Long bankAccountId = shopExpand.getBankAccountId();
        if (! isLongNull(bankAccountId)) {
            List<MerchantPaymentConfigDTO> paymentConfigList = merchantPaymentConfigReadFacade.findByAccountId(shop.getMerchantId(), bankAccountId);
            channelList = ObjectConvertUtil.copyListProperties(paymentConfigList, MerchantPaymentConfigVO.class);
        }

        return channelList;
    }

    private boolean isLongNull(Long id) {
        return null == id || 0 == id;
    }

    /**
     * 封装打印机数据
     * @params [shopId]
     * @return java.util.List<com.lizikj.api.vo.shop.ShopPrinterVO>
     * @author zhoufe
     * @date 2017/12/27 10:23
     */
    private List<ShopPrinterVO> getShopPrinterVOS(Long shopId) {
        List<ShopPrinterDTO> shopPrinterDTOS = shopPrintReadFacade.listByShopId(shopId);

        List<ShopPrinterVO> shopMerchandiseUnitVOS = ObjectConvertUtil.mapList(shopPrinterDTOS, ShopPrinterDTO.class, ShopPrinterVO.class);

        if (shopMerchandiseUnitVOS != null && shopMerchandiseUnitVOS.size() > 0) {
            shopMerchandiseUnitVOS.forEach((shopPrinterVO) -> {
                shopPrinterVO.setContent(shopPrinterVO.getLiziProducts());
            });
        }

        return shopMerchandiseUnitVOS;
    }


    private void convertResult(GoodsAndCategoryVO goodsAndCategoryVO, List<GoodsDTO> goodsDTOS) {
        if (CollectionUtils.isEmpty(goodsDTOS)){
            return;
        }
        List<GoodsDTO> goodsDTOS1 = new ArrayList<>();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        goodsDTOS.forEach(goodsDTO -> {
            goodsDTOS1.add(goodsDTO);
            if (!CollectionUtils.isEmpty(goodsDTO.getCategories())) {
                categoryDTOS.addAll(goodsDTO.getCategories());
            }
        });

        List<CategoryDTO> distinct = categoryDTOS.stream().distinct().collect(toList());

        List<GoodsVO>  goodsVOS= ObjectConvertUtil.mapList(goodsDTOS1,GoodsDTO.class,GoodsVO.class);
        List<CategoryVO> categoryVOS = ObjectConvertUtil.mapList(distinct, CategoryDTO.class,CategoryVO.class);

        goodsAndCategoryVO.setCategoryVOs(categoryVOS);
        goodsAndCategoryVO.setGoodsVOs(goodsVOS);
    }

    private void setQuickMenu(GoodsAndCategoryVO goodsAndCategoryVO, List<CategoryDTO> categoryDTOS) {
        boolean containQuickMenu = false;
        if (!CollectionUtils.isEmpty(goodsAndCategoryVO.getCategoryVOs())){
            for (CategoryVO categoryVO : goodsAndCategoryVO.getCategoryVOs()) {
                if (categoryVO.getCode() == CategoryCodeEnum.QUICK_MENU){
                    containQuickMenu = true;
                }
            }
        }else {
            containQuickMenu = false;
        }

        if (!containQuickMenu && !CollectionUtils.isEmpty(categoryDTOS)){
            List<CategoryVO> categoryVOS = goodsAndCategoryVO.getCategoryVOs();
            if (categoryVOS == null){
                categoryVOS = new ArrayList<>();
            }
            categoryVOS.add(ObjectConvertUtil.map(categoryDTOS.get(0),CategoryVO.class));
            goodsAndCategoryVO.setCategoryVOs(categoryVOS);
        }
    }

    @RequestMapping("/commonInitInfo4App")
    @ApiOperation(value = "App 端获取该店铺的配置信息", notes = "App 端获取该店铺的配置信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<AppShopInitInfoVO> commonInitInfo4App(){
        Result<AppShopInitInfoVO> result;

        try {
			Long shopId = ThreadLocalContext.getShopId();
			String sname = ThreadLocalContext.getSname();
        	 
            List<ShopSettingDTO> shopSettingDTOList = shopSettingReadFacade.listShopSettings(shopId);

            AppShopInitInfoVO appSettingVO = new AppShopInitInfoVO();

            List<ShopSettingVO> shopSettingVOS = null;
            List<ShopSettingDTO> shopSettingDTOS = new ArrayList<>();

            if (!CollectionUtils.isEmpty(shopSettingDTOList)){
                for (ShopSettingDTO shopSettingDTO : shopSettingDTOList) {
                    if (shopSettingDTO.getBizType() == BizTypeEnum.SHOP || shopSettingDTO.getBizType() == BizTypeEnum.MERCHANDISE){
                        shopSettingDTOS.add(shopSettingDTO);
                    }
                }
            }

            if (!CollectionUtils.isEmpty(shopSettingDTOS)){
                shopSettingVOS = ObjectConvertUtil.mapList(shopSettingDTOS,ShopSettingDTO.class,ShopSettingVO.class);
            }

            CommonInfoVO commonInfoVO = new CommonInfoVO();
            commonInfoVO.setEnvironment(environment.getProperty("spring.profiles.active"));
            commonInfoVO.setCurrentTime(System.currentTimeMillis());
            appSettingVO.setShopSettings(shopSettingVOS);
            appSettingVO.setCommonInfo(commonInfoVO);

            //设置h5二维码信息
            H5QrCodeVO h5QrCodeVO = getH5QrCodeVO();
            appSettingVO.setH5QrCode(h5QrCodeVO);
            
            appSettingVO.setMenuTemplate(getMenuTemplate(shopId, sname));
            result = Result.SUCESS(appSettingVO);
        }catch (ShopException e){
            logger.error("commonInitInfo4App Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;

    }

    private H5QrCodeVO getH5QrCodeVO(){
    	String deskPayCodeUrl = null;
		String scavengingNumberUrl = null;
		String scavengingOrderUrl = null;
		String orderTicketPayUrl = null;
        try {
            Long shopId = ThreadLocalContext.getShopId();
            Long merchantId = ThreadLocalContext.getMerchantId();
            ShopMerchantDTO merchantDTO = shopMerchantReadFacade.findById(merchantId);
            if(null != merchantDTO){
            	if(StringUtils.isBlank(merchantDTO.getWechantPublicKey())){
            		return null;
            	}
            	deskPayCodeUrl = MessageFormat.format(WechatQrCodeConstants.DESC_PAY_CODE_URL, String.valueOf(shopId), String.valueOf(merchantId));
            	
            	scavengingOrderUrl = MessageFormat.format(WechatQrCodeConstants.SCAVENGING_ORDER_URL, String.valueOf(shopId), String.valueOf(merchantId));

            	
            	scavengingNumberUrl = wechatPublicAuthorizationFacade.createLimitQrCode(merchantDTO.getWechantPublicKey(), WechatQrCodeConstants.getShopScavengingNumber(merchantId, shopId));
            	
            	orderTicketPayUrl = MessageFormat.format(WechatQrCodeConstants.ORDER_TICKET_PAY_URL, String.valueOf(shopId), String.valueOf(merchantId));
            }
        } catch (BaseException e) {
            logger.error("获取h5二维码 Exception: {}", e);
        }
        return new H5QrCodeVO(deskPayCodeUrl, scavengingNumberUrl, scavengingOrderUrl, orderTicketPayUrl);
    }
    
    /**
     * 获取设备应用相关菜单列表
     * @param shopId
     * @param sname
     * @return String
     * @author liaojw
     * @date 2018年8月21日 下午4:42:54
     */
    private String getMenuTemplate(Long shopId, String sname) {
    	if (shopId == null || StringUtils.isBlank(sname)) {
    		return null;
    	}
    	
    	TerminalTypeEnum terminalType = TerminalTypeEnum.getEnum(sname);
    	if (terminalType == null) {
    		return null;
    	}
    	
		AuthShopMenuTemplateDTO template = new AuthShopMenuTemplateDTO();
		// 未开通撩美味合作状态开关为普通撩客商户，开通则为撩美味商户
		ShopDTO shop = shopReadFacade.findById(shopId);
		MenuTemplateAppTypeEnum appType = null;
		switch (terminalType) {
		//app
		case IOS_TERMINAL:
			appType = MenuTemplateAppTypeEnum.APP;
			break;

		case ANDROID_TERMINAL:
			appType = MenuTemplateAppTypeEnum.APP;
			break;
		//pos
		case POS_TERMINAL:
			appType = MenuTemplateAppTypeEnum.POS;
			break;
		case ANDROID_POS:
			appType = MenuTemplateAppTypeEnum.POS;
			break;
		case IOS_POS:
			appType = MenuTemplateAppTypeEnum.POS;
			break;
		case BIG_POS:
			appType = MenuTemplateAppTypeEnum.POS;
			break;
	
		default:
			break;
		}
		if (shop.getMarketingEnableStatus() == null
				|| shop.getMarketingEnableStatus().byteValue() == ShopMarketingEnableStatusEnum.DISABLE.getCode()) {
			template = authShopMenuTemplateReadFacade.findByType(appType, MenuTemplateObjectTypeEnum.LK);
		} else {
			template = authShopMenuTemplateReadFacade.findByType(appType, MenuTemplateObjectTypeEnum.LMW);
		}
		if (template == null || StringUtils.isBlank(template.getJsonData())) {
			return null;
		}
		return template.getJsonData();
    }
}
