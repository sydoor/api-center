package com.lizikj.api.utils;

import com.lizikj.common.util.DateUtils;
import com.lizikj.marketing.api.dto.ActivityWithPrizeDTO;
import com.lizikj.marketing.api.dto.CouponDTO;
import com.lizikj.marketing.api.dto.RedPacketDTO;
import com.lizikj.marketing.api.enums.*;
import com.lizikj.marketing.api.facade.IActivityWriteFacade;
import com.lizikj.marketing.api.rule.DistributePayload;
import com.lizikj.marketing.api.rule.RulePayload;

import java.util.Collections;
import java.util.Date;

/**
 * @author lxl
 * @Date 2018/8/10 17:41
 */
public class MarketingInitialTool {
    private IActivityWriteFacade activityWriteFacade;

    public MarketingInitialTool(IActivityWriteFacade activityWriteFacade) {
        this.activityWriteFacade = activityWriteFacade;
    }

    public ActivityWithPrizeDTO insertActivity(PlayCodeEnum playCodeEnum,int stock){
        switch (playCodeEnum){
            case COUPON_FOR_NEW_FOCUS:
                return insertNewFocusActivity(stock);
            case RED_PACKET_FROM_CODE:
                return insertScanCodeActivity(stock);
            case RED_PACKET_FROM_SHARE:
                return insertShareActivity(stock);
            case COUPON_FOR_INVITE_FOCUS:
                return insertInviteFocusActivity(stock);
            case COUPON_FROM_RECHARGE:
                return insertRechargeActivity(stock);
        }

        return null;
    }

    public ActivityWithPrizeDTO insertRechargeActivity(int stock){
        ActivityWithPrizeDTO dto = new ActivityWithPrizeDTO();
        dto.setActivityName("VIP会员10张5元优惠券");
        dto.setSourceEnum(SourceEnum.PLATFORM);
        dto.setActivityStatusEnum(ActivityStatusEnum.ON_WAY);
        dto.setDescription("每月 10张 5元优惠券（共50元），每消费达20元可用1张券，不设数量限制;");
        dto.setStartTime(DateUtils.getStartOfDay(new Date()));
        dto.setEndTime(DateUtils.addDays(new Date(),99));
        DistributePayload payload = new DistributePayload();
        payload.setModeEnum(DistributeModeEnum.FIXED_NUM);
        payload.setFixNum(10);
        dto.setGrantRule(payload.toJson());
        dto.setPlayCode(PlayCodeEnum.COUPON_FROM_RECHARGE.getCode());

        CouponDTO couponDTO = new CouponDTO();
        couponDTO.setActivityId(7L);
        couponDTO.setReduceCost(500L);
        couponDTO.setLeastCost(2000L);
        couponDTO.setStartTime(new Date());
        couponDTO.setEndTime(DateUtils.addDays(new Date(),999));
        couponDTO.setCouponName("5元代金券");
        couponDTO.setCardVersion(1L);
        couponDTO.setCardType(CardTypeEnum.FULL_CUT);
        couponDTO.setCouponStatus(CouponStatusEnum.ENABLE);
        couponDTO.setStock(stock);
        couponDTO.setCardSource(SourceEnum.PLATFORM);
        couponDTO.setValidHour(30*24);
        couponDTO.setCanShare(true);

        RulePayload rulePayload = new RulePayload();
        rulePayload.setGetLimit(1);
        rulePayload.setRpn("getLimit");
        couponDTO.setRuleInfo(rulePayload.toJson());

        RulePayload couponPayload = new RulePayload();
        couponPayload.setAmountAbovePlusFilter(2000);
        couponPayload.setFilterList(Collections.singletonList("amountAbovePlusFilter"));
        couponDTO.setUseRuleInfo(couponPayload.toJson());
        dto.setCouponDTOS(Collections.singletonList(couponDTO));
        ActivityWithPrizeDTO activityWithPrizeDTO = activityWriteFacade.insertWithPrizes(dto);

        return activityWithPrizeDTO;
    }

    public ActivityWithPrizeDTO insertNewFocusActivity(int stock){
        ActivityWithPrizeDTO dto = new ActivityWithPrizeDTO();
        dto.setActivityName("新关注用户赠送2张5元券");
        dto.setSourceEnum(SourceEnum.PLATFORM);
        dto.setActivityStatusEnum(ActivityStatusEnum.ON_WAY);
        dto.setDescription("一人只能领一次");
        dto.setStartTime(DateUtils.getStartOfDay(new Date()));
        dto.setEndTime(DateUtils.addDays(new Date(),30));
        DistributePayload payload = new DistributePayload();
        payload.setModeEnum(DistributeModeEnum.FIXED_NUM);
        payload.setFixNum(2);
        dto.setGrantRule(payload.toJson());
        dto.setPlayCode(PlayCodeEnum.COUPON_FOR_NEW_FOCUS.getCode());

        CouponDTO couponDTO = new CouponDTO();
        couponDTO.setActivityId(7L);
        couponDTO.setReduceCost(500L);
        couponDTO.setLeastCost(2000L);
        couponDTO.setStartTime(new Date());
        couponDTO.setEndTime(DateUtils.addDays(new Date(),999));
        couponDTO.setCouponName("美味券");
        couponDTO.setCardVersion(1L);
        couponDTO.setCardType(CardTypeEnum.FULL_CUT);
        couponDTO.setCouponStatus(CouponStatusEnum.ENABLE);
        couponDTO.setStock(stock);
        couponDTO.setCardSource(SourceEnum.PLATFORM);
        couponDTO.setValidHour(30*24);

        RulePayload rulePayload = new RulePayload();
        rulePayload.setGetLimit(1);
        rulePayload.setRpn("getLimit");
        couponDTO.setRuleInfo(rulePayload.toJson());

        RulePayload couponPayload = new RulePayload();
        couponPayload.setAmountAbovePlusFilter(2000);
        couponPayload.setFilterList(Collections.singletonList("amountAbovePlusFilter"));
        couponDTO.setUseRuleInfo(couponPayload.toJson());
        dto.setCouponDTOS(Collections.singletonList(couponDTO));
        ActivityWithPrizeDTO activityWithPrizeDTO = activityWriteFacade.insertWithPrizes(dto);

        return activityWithPrizeDTO;
    }

    public ActivityWithPrizeDTO insertInviteFocusActivity(int stock){
        ActivityWithPrizeDTO dto = new ActivityWithPrizeDTO();
        dto.setActivityName("邀请朋友关注公众号赠送邀请人3张5元卡券");
        dto.setSourceEnum(SourceEnum.PLATFORM);
        dto.setActivityStatusEnum(ActivityStatusEnum.ON_WAY);
        dto.setDescription("邀请朋友关注公众号赠送邀请人3张5元卡券（待激活：关注用户领取卡券消费后才能激活）");
        dto.setStartTime(DateUtils.getStartOfDay(new Date()));
        dto.setEndTime(DateUtils.addDays(new Date(),999));
        DistributePayload payload = new DistributePayload();
        payload.setModeEnum(DistributeModeEnum.FIXED_NUM);
        payload.setFixNum(3);
        dto.setGrantRule(payload.toJson());
        dto.setPlayCode(PlayCodeEnum.COUPON_FOR_INVITE_FOCUS.getCode());

        CouponDTO couponDTO = new CouponDTO();
        couponDTO.setActivityId(7L);
        couponDTO.setReduceCost(500L);
        couponDTO.setLeastCost(2000L);
        couponDTO.setStartTime(new Date());
        couponDTO.setEndTime(DateUtils.addDays(new Date(),111));
        couponDTO.setCouponName("美味券");
        couponDTO.setCardVersion(1L);
        couponDTO.setCardType(CardTypeEnum.FULL_CUT);
        couponDTO.setCouponStatus(CouponStatusEnum.ENABLE);
        couponDTO.setStock(stock);
        couponDTO.setCardSource(SourceEnum.PLATFORM);
        couponDTO.setValidHour(30*24);

        RulePayload couponPayload = new RulePayload();
        couponPayload.setAmountAbovePlusFilter(2000);
        couponPayload.setFilterList(Collections.singletonList("amountAbovePlusFilter"));
        couponDTO.setUseRuleInfo(couponPayload.toJson());
        dto.setCouponDTOS(Collections.singletonList(couponDTO));
        ActivityWithPrizeDTO activityWithPrizeDTO = activityWriteFacade.insertWithPrizes(dto);

        return activityWithPrizeDTO;
    }

    public ActivityWithPrizeDTO insertScanCodeActivity(int stock){
        ActivityWithPrizeDTO dto = new ActivityWithPrizeDTO();
        dto.setActivityName("扫码领随机红包");
        dto.setSourceEnum(SourceEnum.PLATFORM);
        dto.setActivityStatusEnum(ActivityStatusEnum.ON_WAY);
        dto.setDescription("扫码领取红包（每天只能领一次）");
        dto.setStartTime(DateUtils.getStartOfDay(new Date()));
        dto.setEndTime(DateUtils.addDays(new Date(),30));
        DistributePayload payload = new DistributePayload();
        payload.setModeEnum(DistributeModeEnum.FIXED_NUM);
        payload.setFixNum(1);
        dto.setGrantRule(payload.toJson());
        dto.setPlayCode(PlayCodeEnum.RED_PACKET_FROM_CODE.getCode());

        RedPacketDTO redPacketDTO = new RedPacketDTO();
        redPacketDTO.setPacketSourceEnum(SourceEnum.PLATFORM);
        redPacketDTO.setPacketStatusEnum(RedPacketStatusEnum.ENABLE);
        redPacketDTO.setEndTime(DateUtils.addDays(new Date(),999));
        redPacketDTO.setPacketTypeEnum(RedPacketTypeEnum.RANDOM_AMOUNT);
        redPacketDTO.setMaxRandomAmount(100L);
        redPacketDTO.setStock(stock);
        redPacketDTO.setMinRandomAmount(1L);
        redPacketDTO.setValidHour(12);
        redPacketDTO.setPacketName("撩美味红包");

        RulePayload getPayload = new RulePayload();
        //12小时内只能领取一次
        getPayload.setUserDailyGetLimit(1);
        getPayload.setRpn("userDailyGetLimit");
        redPacketDTO.setRuleInfo(getPayload.toJson());

        RulePayload usePayload = new RulePayload();
        //只能使用一次
        usePayload.setUseLimitFilter(1);
        usePayload.setFilterList(Collections.singletonList("useLimitFilter"));
        redPacketDTO.setUseRuleInfo(usePayload.toJson());
        dto.setRedPacketDTOS(Collections.singletonList(redPacketDTO));
        ActivityWithPrizeDTO activityWithPrizeDTO = activityWriteFacade.insertWithPrizes(dto);

        return activityWithPrizeDTO;
    }


    public ActivityWithPrizeDTO insertShareActivity(int stock){
        ActivityWithPrizeDTO dto = new ActivityWithPrizeDTO();
        dto.setActivityName("分享扫码领随机红包");
        dto.setSourceEnum(SourceEnum.PLATFORM);
        dto.setActivityStatusEnum(ActivityStatusEnum.ON_WAY);
        dto.setDescription("分享领取红包（每笔订单可以领取一次）");
        dto.setStartTime(DateUtils.getStartOfDay(new Date()));
        dto.setEndTime(DateUtils.addDays(new Date(),30));
        DistributePayload payload = new DistributePayload();
        payload.setModeEnum(DistributeModeEnum.FIXED_NUM);
        payload.setFixNum(1);
        dto.setGrantRule(payload.toJson());
        dto.setPlayCode(PlayCodeEnum.RED_PACKET_FROM_SHARE.getCode());

        RedPacketDTO redPacketDTO = new RedPacketDTO();
        redPacketDTO.setPacketSourceEnum(SourceEnum.PLATFORM);
        redPacketDTO.setPacketStatusEnum(RedPacketStatusEnum.ENABLE);
        redPacketDTO.setEndTime(DateUtils.addDays(new Date(),999));
        redPacketDTO.setPacketTypeEnum(RedPacketTypeEnum.RANDOM_AMOUNT);
        redPacketDTO.setMaxRandomAmount(100L);
        redPacketDTO.setStock(stock);
        redPacketDTO.setMinRandomAmount(1L);
        redPacketDTO.setValidHour(12);
        redPacketDTO.setPacketName("撩美味红包");

        RulePayload getPayload = new RulePayload();
        //12小时内只能领取一次
        getPayload.setUserDailyGetLimit(1);
        getPayload.setRpn("userDailyGetLimit");
        redPacketDTO.setRuleInfo(getPayload.toJson());

        RulePayload usePayload = new RulePayload();
        //只能使用一次
        usePayload.setUseLimitFilter(1);
        usePayload.setFilterList(Collections.singletonList("useLimitFilter"));
        redPacketDTO.setUseRuleInfo(usePayload.toJson());
        dto.setRedPacketDTOS(Collections.singletonList(redPacketDTO));
        ActivityWithPrizeDTO activityWithPrizeDTO = activityWriteFacade.insertWithPrizes(dto);

        return activityWithPrizeDTO;
    }
}
