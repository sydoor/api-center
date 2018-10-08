package com.lizikj.api.controller.marketing;

import com.github.pagehelper.PageInfo;
import com.lizikj.api.utils.MarketingInitialTool;
import com.lizikj.api.vo.PageVO;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.marketing.coupon.*;
import com.lizikj.cache.Cache;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.PageParameter;
import com.lizikj.login.interceptor.LoginExclude;
import com.lizikj.marketing.api.dto.*;
import com.lizikj.marketing.api.enums.*;
import com.lizikj.marketing.api.facade.*;
import com.lizikj.merchant.dto.ShopDTO;
import com.lizikj.merchant.dto.ShopMerchantDTO;
import com.lizikj.merchant.dto.TradeAreaDTO;
import com.lizikj.merchant.facade.IShopMerchantReadFacade;
import com.lizikj.merchant.facade.IShopReadFacade;
import com.lizikj.merchant.facade.ITradeAreaReadFacade;
import com.lizikj.order.dto.LmwOrderStatisticsSummaryDTO;
import com.lizikj.order.enums.OrderSourceEnum;
import com.lizikj.order.facade.IOrderReadFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author lxl
 * @Date 2018/7/30 15:18
 */
@RestController
@RequestMapping("marketing")
@Api(value = "activity", tags = "营销活动编辑接口")
public class MarketingActivityController {
    private final static Logger log = LoggerFactory.getLogger(MarketingActivityController.class);
    @Autowired
    ICardWriteFacade cardWriteFacade;
    @Autowired
    ICardReadFacade cardReadFacade;
    @Autowired
    IRedPacketWriteFacade redPacketWriteFacade;
    @Autowired
    IRedPacketReadFacade redPacketReadFacade;
    @Autowired
    IActivityWriteFacade activityWriteFacade;
    @Autowired
    IActivityReadFacade activityReadFacade;
    @Autowired
    IShopMerchantReadFacade shopMerchantReadFacade;
    @Autowired
    ITradeAreaReadFacade tradeAreaReadFacade;
    @Autowired
    IShopReadFacade shopReadFacade;
    @Autowired
    IOrderReadFacade orderReadFacade;
    @Autowired
    IControlFacade controlFacade;
    @Autowired
    Cache cache;

    @RequestMapping(value = "/tool/clearCache", method = RequestMethod.GET)
    @ApiOperation(value = "清除营销限制", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> clearCache(){
        List<String> pres = Arrays.asList(new String[]{"rst:*","GW:*","DG:*","GET:*",});
        pres.forEach(t -> {
            Set<String> scan = cache.scan(t, 1000);
            for(;!scan.isEmpty();){
                scan.forEach(s -> {
                    cache.delete(s);
                });

                scan = cache.scan(t, 1000);
            }
        });

        return Result.SUCESS("清除完成");
    }

    @RequestMapping(value = "/tool/updateRedPacketCommonSetting", method = RequestMethod.POST)
    @ApiOperation(value = "更新红包通用设置", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<RedPacketVO>> updateRedPacketCommonSetting(@RequestBody RedPacketCommonSettingVO commonSettingVO){
        List<RedPacketDTO> redPacketDTOS = redPacketWriteFacade.updateRedPacketCommonSetting(ObjectConvertUtil.map(commonSettingVO, RedPacketCommonSettingDTO.class));

        return Result.SUCESS(RedPacketVO.from(redPacketDTOS));
    }

    @RequestMapping(value = "/setting/changeRedPacketDailyMaxAmount", method = RequestMethod.POST)
    @ApiOperation(value = "更新红包每日限额", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> changeRedPacketDailyMaxAmount(@ApiParam(name = "amount",value = "每日红包限额") @RequestParam(value = "amount")Long amount){
        GrantGlobalControlDTO controlDTO = new GrantGlobalControlDTO();
        controlDTO.setPrizeType(PrizeTypeEnum.RED_PACKET);
        controlDTO.setControlValue(amount);
        controlDTO.setControlType(ControlTypeEnum.DAILY_AMOUNT_CTRL);
        try {
            GrantGlobalControlDTO control = controlFacade.addOrUpdate(controlDTO);
        } catch (Exception e) {
            log.info("更新红包每日限额失败 ",e);
            return Result.FAILURE("更新失败");
        }

        return Result.SUCESS(amount);
    }

    @RequestMapping(value = "/setting/getByType", method = RequestMethod.POST)
    @ApiOperation(value = "获取控制规则", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<GrantGlobalControlVO> getControlByType(@RequestBody GrantGlobalControlVO grantGlobalControlVO){
        if(grantGlobalControlVO.getPrizeType() == null || grantGlobalControlVO.getControlType() == null){
            return Result.FAILURE("参数有误");
        }

        GrantGlobalControlDTO controlDTO = controlFacade.getByTye(grantGlobalControlVO.getPrizeType(), grantGlobalControlVO.getControlType());
        if(controlDTO == null){
            return Result.FAILURE("查询失败");
        }

        return Result.SUCESS(ObjectConvertUtil.map(controlDTO,GrantGlobalControlVO.class));
    }

    @RequestMapping(value = "/coupon/{cardId}", method = RequestMethod.GET)
    @ApiOperation(value = "获取卡券", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<CouponVO> getCoupon(@ApiParam(name = "cardId",value = "卡券编码")@PathVariable(name = "cardId")String cardId){
        CouponDTO couponDTO = cardReadFacade.getByNo(cardId);

        if(couponDTO == null){
            return Result.FAILURE("卡券不存在");
        }
        return Result.SUCESS(CouponVO.from(couponDTO));
    }

    @RequestMapping(value = "/coupon/delete/{cardId}", method = RequestMethod.POST)
    @ApiOperation(value = "删除卡券", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<CouponVO> deleteCoupon(@ApiParam(name = "cardId",value = "卡券编码")@PathVariable(name = "cardId")String cardId){
        boolean succeed = cardWriteFacade.deleteCoupon(cardId);

        return Result.SUCESS(succeed);
    }

    @RequestMapping(value = "/coupon/item/{cardCode}", method = RequestMethod.GET)
    @ApiOperation(value = "获取卡券实体", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<CouponItemVO> getCouponItem(@ApiParam(name = "cardCode",value = "卡券领取码")@PathVariable(name = "cardCode")String cardCode){
        CouponItemDTO couponItemDTO = cardReadFacade.getByCode(cardCode);
        if(couponItemDTO == null){
            return Result.FAILURE("卡券不存在");
        }

        return Result.SUCESS(ObjectConvertUtil.map(couponItemDTO,CouponItemVO.class));
    }


    @RequestMapping(value = "/coupon/totalStatistics", method = RequestMethod.GET)
    @ApiOperation(value = "获取卡券整体使用统计", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<CouponTotalStatisticsVO> getCouponTotalStatistics(@ApiParam(name = "startTime",value = "开始时间")@RequestParam(value = "startTime",required = false) Date startTime,
                                                                    @ApiParam(name = "endTime",value = "结束时间")@RequestParam(value = "endTime",required = false) Date endTime){
        CouponTotalStatisticsDTO couponTotalStatistics = cardReadFacade.getCouponTotalStatistics(startTime,endTime);

        return Result.SUCESS(ObjectConvertUtil.map(couponTotalStatistics,CouponTotalStatisticsVO.class));
    }

    @RequestMapping(value = "/coupon/changeStatus", method = RequestMethod.POST)
    @ApiOperation(value = "修改卡券状态", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<CouponVO> changeStatus(@ApiParam(name = "cardId",value = "卡券编码",defaultValue = "1")@RequestParam(value = "cardId")String cardId,
                                         @ApiParam(name = "couponStatusEnum",value = "状态",defaultValue = "1")@RequestParam(value = "couponStatusEnum")CouponStatusEnum couponStatusEnum){
        CouponDTO dto = new CouponDTO();
        dto.setCardId(cardId);
        dto.setCouponStatus(couponStatusEnum);
        CouponDTO couponDTO = cardWriteFacade.addOrUpdate(dto);

        return Result.SUCESS(ObjectConvertUtil.map(couponDTO,CouponVO.class));
    }

    @RequestMapping(value = "/packet/changeStatus", method = RequestMethod.POST)
    @ApiOperation(value = "修改红包状态", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<RedPacketVO> changePacketStatus(@ApiParam(name = "packetNo",value = "红包编码",defaultValue = "1")@RequestParam(value = "packetNo")String packetNo,
                                         @ApiParam(name = "redPacketStatusEnum",value = "状态",defaultValue = "1")@RequestParam(value = "redPacketStatusEnum")RedPacketStatusEnum redPacketStatusEnum){

        RedPacketDTO redPacketDTO = redPacketWriteFacade.changeStatus(packetNo, redPacketStatusEnum);

        return Result.SUCESS(ObjectConvertUtil.map(redPacketDTO,RedPacketVO.class));
    }

    @RequestMapping(value = "/coupon/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增卡券", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<CouponVO> insertCoupon(@RequestBody CouponVO couponVO) {
        if(!checkCoupon(couponVO)){
            return Result.FAILURE("参数校验失败");
        }

        CouponDTO couponDTO = null;
        try {
            couponDTO = cardWriteFacade.addOrUpdate(couponVO.convertToDTO());
        } catch (Exception e) {
            log.info("新增卡券失败",e);
        }

        couponVO.setCardId(couponDTO.getCardId());

        return Result.SUCESS(couponVO);
    }


    @RequestMapping(value = "/coupon/modify", method = RequestMethod.POST)
    @ApiOperation(value = "修改卡券", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<CouponVO> updateCoupon(@RequestBody CouponVO couponVO) {
        if(!checkCoupon(couponVO)){
            return Result.FAILURE("参数校验失败");
        }
        try {
            cardWriteFacade.addOrUpdate(couponVO.convertToDTO());
        } catch (Exception e) {
            log.info("更新卡券失败",e);
        }


        return Result.SUCESS(couponVO);
    }

    @RequestMapping(value = "/coupon/list", method = RequestMethod.POST)
    @ApiOperation(value = "卡券查询", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PageVO<CouponVO>> listCoupons(@RequestBody CouponQueryParamVO queryParamVO,
                                                      @ApiParam(name = "pageNum",value = "页码",defaultValue = "1") @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
                                                      @ApiParam(name = "pageSize",value = "每页数量",defaultValue = "25") @RequestParam(value = "pageSize",defaultValue = "25",required = false) int pageSize){
        PageParameter pageParameter = new PageParameter(pageNum,pageSize);
        PageInfo<CouponDTO> dtoPageInfo = cardReadFacade.listByParam(ObjectConvertUtil.map(queryParamVO, CouponQueryParamDTO.class), pageParameter);
        PageVO pageVO = new PageVO(dtoPageInfo.getPageNum(),dtoPageInfo.getPageSize(),dtoPageInfo.getPages(),dtoPageInfo.getTotal());
        pageVO.setList(CouponVO.from(dtoPageInfo.getList()));

        return Result.SUCESS(pageVO);
    }

    @RequestMapping(value = "/packet/totalStatistics", method = RequestMethod.GET)
    @ApiOperation(value = "获取红包整体使用统计", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<RedPacketTotalStatisticsVO> getRedPacketTotalStatistics(@ApiParam(name = "startTime",value = "开始时间")@RequestParam(value = "startTime",required = false) Date startTime,
                                                                          @ApiParam(name = "endTime",value = "结束时间")@RequestParam(value = "endTime",required = false) Date endTime){
        RedPacketTotalStatisticsDTO redPacketTotalStatistics = redPacketReadFacade.getRedPacketTotalStatistics(startTime,endTime);
        RedPacketTotalStatisticsVO result = ObjectConvertUtil.map(redPacketTotalStatistics, RedPacketTotalStatisticsVO.class);
        try {
            LmwOrderStatisticsSummaryDTO circulationSummary = orderReadFacade.getCirculationSummary(null, OrderSourceEnum.LMW, startTime, endTime);
            result.setOrderTotalAmount(circulationSummary.getTotalTotalAmount());
        } catch (Exception e) {
            log.info("查询数据有误 ",e);
        }

        return Result.SUCESS(result);
    }

    @RequestMapping(value = "/packet/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增红包", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<RedPacketVO> insertRedPacket(@RequestBody RedPacketVO redPacketVO){
        if(!checkRedPacket(redPacketVO)){
            return Result.FAILURE("参数校验失败");
        }

        RedPacketDTO redPacketDTO = null;
        try {
            redPacketDTO = redPacketWriteFacade.addOrUpdate(redPacketVO.convertToDTO());
        } catch (Exception e) {
            log.info("新增红包发生异常 {}",redPacketVO,e);
            return Result.FAILURE("新增红包发生异常");
        }

        return Result.SUCESS(RedPacketVO.from(redPacketDTO));
    }

    @RequestMapping(value = "/packet/{packetNo}", method = RequestMethod.GET)
    @ApiOperation(value = "获取红包", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<RedPacketVO> getRedPacket(@ApiParam(name = "packetNo",value = "红包编码")@PathVariable(name = "packetNo")String packetNo){
        RedPacketDTO redPacketDTO = redPacketReadFacade.getByNo(packetNo);

        if(redPacketDTO == null){
            return Result.FAILURE("红包不存在");
        }

        return Result.SUCESS(RedPacketVO.from(redPacketDTO));
    }

    @RequestMapping(value = "/packet/delete/{packetNo}", method = RequestMethod.POST)
    @ApiOperation(value = "删除红包", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<RedPacketVO> deleteRedPacket(@ApiParam(name = "packetNo",value = "红包编码")@PathVariable(name = "packetNo")String packetNo){
        boolean b = redPacketWriteFacade.deleteRedPacket(packetNo);

        return Result.SUCESS(b);
    }

    @RequestMapping(value = "/packet/item/{packetCode}", method = RequestMethod.GET)
    @ApiOperation(value = "获取红包实体", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<RedPacketItemVO> getRedPacketItem(@ApiParam(name = "packetCode",value = "红包领取码")@PathVariable(name = "packetCode")String packetCode){
        RedPacketItemDTO redPacketItemDTO = redPacketReadFacade.getByCode(packetCode);

        if(redPacketItemDTO == null){
            return Result.FAILURE("红包实体不存在");
        }

        return Result.SUCESS(ObjectConvertUtil.map(redPacketItemDTO,RedPacketItemVO.class));
    }


    @RequestMapping(value = "/packet/modify", method = RequestMethod.POST)
    @ApiOperation(value = "修改红包", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<RedPacketVO> updateRedPacket(@RequestBody RedPacketVO redPacketVO){
        if(!checkRedPacket(redPacketVO)){
            return Result.FAILURE("参数校验失败");
        }

        try {
            redPacketWriteFacade.addOrUpdate(redPacketVO.convertToDTO());
        } catch (Exception e) {
            log.info("修改红包发生异常 {}",redPacketVO,e);
        }

        return Result.SUCESS(redPacketVO);
    }


    @RequestMapping(value = "/activity/insertWithPrizes", method = RequestMethod.POST)
    @ApiOperation(value = "新增活动", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<ActivityVO> insertWithPrizes(@RequestBody ActivityVO activityVO){
        if(!checkActivity(activityVO)){
            return Result.FAILURE("参数校验失败");
        }

        ActivityWithPrizeDTO activityWithPrizeDTO = null;
        try {
            activityWithPrizeDTO = activityWriteFacade.insertWithPrizes(activityVO.convertToDTOWithPrizes());
        } catch (Exception e) {
            log.info("新增活动发生异常 {}",activityVO,e);
            return Result.FAILURE("新增活动发生异常");
        }

        return Result.SUCESS(ActivityVO.from(activityWithPrizeDTO));
    }

    @RequestMapping(value = "/activity/firstInitial", method = RequestMethod.GET)
    @ApiOperation(value = "新增活动", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @LoginExclude
    public Result<ActivityVO> firstInitial(@ApiParam(name = "key",value = "初始化秘钥")@RequestParam(value = "key")String key,
                                           @ApiParam(name = "playCodeEnum",value = "活动邀请码")@RequestParam(value = "playCodeEnum")PlayCodeEnum playCodeEnum,
                                           @ApiParam(name = "stock",value = "库存")@RequestParam(value = "stock",defaultValue = "2000000")Integer stock){
        if(!"lzkjmarketing".equals(key) || playCodeEnum == null){
            return Result.FAILURE("参数校验失败");
        }

        MarketingInitialTool tool = new MarketingInitialTool(this.activityWriteFacade);
        ActivityWithPrizeDTO activityWithPrizeDTO = null;
        try {
            activityWithPrizeDTO = tool.insertActivity(playCodeEnum,stock);
        } catch (Exception e) {
            log.info("新增活动发生异常",e);
            return Result.FAILURE("新增活动发生异常");
        }

        return Result.SUCESS(ActivityVO.from(activityWithPrizeDTO));
    }

    @RequestMapping(value = "/activity/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增活动", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<ActivityVO> insertActivity(@RequestBody ActivityVO activityVO){
        if(!checkActivity(activityVO)){
            return Result.FAILURE("参数校验失败");
        }

        ActivityDTO activityDTO = null;
        try {
            activityDTO = activityWriteFacade.addOrUpdate(activityVO.convertToDTO());
        } catch (Exception e) {
            log.info("新增活动发生异常 {}",activityVO,e);
            return Result.FAILURE("新增活动发生异常");
        }

        return Result.SUCESS(ObjectConvertUtil.map(activityDTO,ActivityVO.class));
    }

    @RequestMapping(value = "/activity/modify", method = RequestMethod.POST)
    @ApiOperation(value = "修改活动", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<ActivityVO> updateActivity(@RequestBody ActivityVO activityVO){
        if(!checkActivity(activityVO)){
            return Result.FAILURE("参数校验失败");
        }

        try {
            activityWriteFacade.addOrUpdate(activityVO.convertToDTO());
        } catch (Exception e) {
            log.info("修改活动发生异常 {}",activityVO,e);
            return Result.FAILURE("修改活动发生异常");
        }

        return Result.SUCESS(activityVO);
    }

    @RequestMapping(value = "/activity/listWithPrize", method = RequestMethod.POST)
    @ApiOperation(value = "查询活动(带奖品)", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PageVO<ActivityVO>> listActivityWithPrizes(@RequestBody ActivityQueryParamVO queryParamVO,
                                           @ApiParam(name = "pageNum",value = "页码",defaultValue = "1") @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
                                           @ApiParam(name = "pageSize",value = "每页数量",defaultValue = "25") @RequestParam(value = "pageSize",defaultValue = "25",required = false) int pageSize){
        PageParameter pageParameter = new PageParameter(pageNum,pageSize);
        PageInfo<ActivityWithPrizeDTO> pageInfo = activityReadFacade.listWithPrizesByParam(ObjectConvertUtil.map(queryParamVO,ActivityQueryParamDTO.class),pageParameter);
        PageVO pageVO = new PageVO(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getPages(),pageInfo.getTotal());
        List<ActivityVO> activityVOS = new ArrayList<>();
        pageInfo.getList().forEach(t -> {
            activityVOS.add(getAndSetNames(ActivityVO.from(t)));
        });
        pageVO.setList(activityVOS);

        return Result.SUCESS(pageVO);
    }

    @RequestMapping(value = "/activity/list", method = RequestMethod.POST)
    @ApiOperation(value = "查询活动", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PageVO<ActivityVO>> listActivities(@RequestBody ActivityQueryParamVO queryParamVO,
                                                     @ApiParam(name = "pageNum",value = "页码",defaultValue = "1") @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
                                                     @ApiParam(name = "pageSize",value = "每页数量",defaultValue = "25") @RequestParam(value = "pageSize",defaultValue = "25",required = false) int pageSize){
        PageParameter pageParameter = new PageParameter(pageNum,pageSize);
        PageInfo<ActivityDTO> pageInfo = activityReadFacade.listByParam(ObjectConvertUtil.map(queryParamVO, ActivityQueryParamDTO.class), pageParameter);
        PageVO pageVO = new PageVO(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getPages(),pageInfo.getTotal());
        pageVO.setList(ActivityVO.from(pageInfo.getList()));

        return Result.SUCESS(pageVO);
    }

    @RequestMapping(value = "/activity/delete/{activityId}", method = RequestMethod.POST)
    @ApiOperation(value = "删除活动", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> deleteActivity(@ApiParam(name = "activityId",value = "活动ID",defaultValue = "1") @PathVariable(value = "activityId") Long activityId){
        boolean b = activityWriteFacade.deleteActivity(activityId);

        return Result.SUCESS(b);
    }

    @RequestMapping(value = "/activity/delete/{activityId}", method = RequestMethod.GET)
    @ApiOperation(value = "查询活动", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<ActivityVO> getActivity(@ApiParam(name = "activityId",value = "活动ID",defaultValue = "1") @PathVariable(value = "activityId") Long activityId){
        ActivityDTO activityDTO = activityReadFacade.getById(activityId);
        if(activityDTO == null){
            return Result.FAILURE("活动不存在");
        }

        return Result.SUCESS(ActivityVO.from(activityDTO));
    }

    @RequestMapping(value = "/packet/list", method = RequestMethod.POST)
    @ApiOperation(value = "查询红包", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PageVO<RedPacketVO>> listRedPackets(@RequestBody RedPacketQueryParamVO queryParamVO,
                                                     @ApiParam(name = "pageNum",value = "页码",defaultValue = "1") @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
                                                     @ApiParam(name = "pageSize",value = "每页数量",defaultValue = "25") @RequestParam(value = "pageSize",defaultValue = "25",required = false) int pageSize){
        PageParameter pageParameter = new PageParameter(pageNum,pageSize);
        PageInfo<RedPacketDTO> dtoPageInfo = redPacketReadFacade.listByParam(ObjectConvertUtil.map(queryParamVO, RedPacketQueryParamDTO.class), pageParameter);
        PageVO pageVO = new PageVO(dtoPageInfo.getPageNum(),dtoPageInfo.getPageSize(),dtoPageInfo.getPages(),dtoPageInfo.getTotal());
        pageVO.setList(RedPacketVO.from(dtoPageInfo.getList()));

        return Result.SUCESS(pageVO);
    }

    /**
     * 查询名称
     * @param activityVO
     * @return
     */
    private ActivityVO getAndSetNames(ActivityVO activityVO){
        getAndSetNames(activityVO.getPlayRulePayloadVO());
        if(activityVO.getRedPacketVOS() != null){
            activityVO.getRedPacketVOS().forEach(t -> {
                getAndSetNames(t.getRuleInfoVO());
                getAndSetNames(t.getUseRuleInfoVO());
            });
        }
        if(activityVO.getCouponVOS() != null){
            activityVO.getCouponVOS().forEach(t -> {
                getAndSetNames(t.getRuleInfoVO());
                getAndSetNames(t.getUseRuleInfoVO());
            });
        }

        return activityVO;
    }

    private void getAndSetNames(RulePayloadVO rulePayloadVO){
        if(rulePayloadVO == null){
            return;
        }
        rulePayloadVO.setInMerchantNames(getMerchantNames(rulePayloadVO.getInMerchants()));
        rulePayloadVO.setNotMerchantNames(getMerchantNames(rulePayloadVO.getNotMerchants()));
        rulePayloadVO.setInShopNames(getShopNames(rulePayloadVO.getInShops()));
        rulePayloadVO.setNotShopNames(getShopNames(rulePayloadVO.getNotShops()));
        rulePayloadVO.setNotTradeAreaNames(getTradeAreaNames(rulePayloadVO.getNotTradeAreas()));
        rulePayloadVO.setInTradeAreaNames(getTradeAreaNames(rulePayloadVO.getInTradeAreas()));
    }

    private List<String> getTradeAreaNames(List<Long> tradeAreaIdList){
        List<String> names = new ArrayList<>();
        if(tradeAreaIdList != null){
            tradeAreaIdList.forEach(t -> {
                TradeAreaDTO tradeAreaDTO = tradeAreaReadFacade.findById(t);
                if(tradeAreaDTO != null){
                    names.add(tradeAreaDTO.getTradeAreaName());
                }
            });
        }

        return names;
    }

    private List<String> getShopNames(List<Long> shopIdList){
        List<String> names = new ArrayList<>();
        if(shopIdList != null){
            shopIdList.forEach(t -> {
                ShopDTO shopDTO = shopReadFacade.findById(t);
                if(shopDTO != null){
                    names.add(shopDTO.getShopName());
                }
            });
        }

        return names;
    }

    private List<String> getMerchantNames(List<Long> merchantIdList){
        List<String> names = new ArrayList<>();
        if(merchantIdList != null){
            merchantIdList.forEach(t -> {
                ShopMerchantDTO shopMerchantDTO = shopMerchantReadFacade.findById(t);
                if(shopMerchantDTO != null){
                    names.add(shopMerchantDTO.getMerchantName());
                }
            });
        }

        return names;
    }

    private boolean checkActivity(ActivityVO activityVO){

        return true;
    }

    private boolean checkRedPacket(RedPacketVO redPacketVO){

        return true;
    }


    private boolean checkCoupon(CouponVO couponVO){
        if(couponVO == null){
            return false;
        }

        boolean timeCheck = couponVO.getStartTime() != null && couponVO.getEndTime() != null
                && couponVO.getEndTime().getTime() <= couponVO.getStartTime().getTime();

        if(timeCheck){
            return false;
        }

        boolean stockFail = couponVO.getStock() == null || couponVO.getStock() <= 0;
        if(stockFail){
            return false;
        }

        boolean costFail = couponVO.getLeastCost() != null && couponVO.getLeastCost() < 0;
        if(costFail){
            return false;
        }

        if(couponVO.getCardType() == null){
            return false;
        }

        switch (couponVO.getCardType()){
            case DISCOUNT_COUPON:
                return couponVO.getDiscount() != null
                        && couponVO.getDiscount().compareTo(new BigDecimal(10.00)) < 1
                        && couponVO.getDiscount().compareTo(new BigDecimal(0.00)) > 0;
            case RANDOM_CUT:
            case FULL_CUT:
            case CASH_COUPON:
            case RED_PACKET:
                return couponVO.getReduceCost() > 0;
            case GIFT_COUPON:
                return ! StringUtils.isBlank(couponVO.getGifts());
        }

        return true;
    }
}
