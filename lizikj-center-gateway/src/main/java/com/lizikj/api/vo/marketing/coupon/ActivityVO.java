package com.lizikj.api.vo.marketing.coupon;

import com.alibaba.fastjson.JSONObject;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.marketing.api.dto.ActivityDTO;
import com.lizikj.marketing.api.dto.ActivityWithPrizeDTO;
import com.lizikj.marketing.api.dto.CouponDTO;
import com.lizikj.marketing.api.dto.RedPacketDTO;
import com.lizikj.marketing.api.enums.ActivityStatusEnum;
import com.lizikj.marketing.api.enums.PlayCodeEnum;
import com.lizikj.marketing.api.enums.SourceEnum;
import com.lizikj.marketing.api.rule.DistributePayload;
import com.lizikj.marketing.api.rule.RulePayload;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lxl
 * @Date 2018/7/31 16:50
 */
@ApiModel(value = "活动")
public class ActivityVO {
    @ApiModelProperty(value = "活动ID")
    private Long activityId;
    @ApiModelProperty(value = "活动模板ID")
    private Long activityTemplateId;
    @ApiModelProperty(value = "活动名称")
    private String activityName;
    @ApiModelProperty(value = "活动描述")
    private String description;
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    @ApiModelProperty(value = "商圈ID")
    private Long tradeAreaId;
    @ApiModelProperty(value = "开始时间")
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
    @ApiModelProperty(value = "活动状态")
    private ActivityStatusEnum activityStatusEnum;
    @ApiModelProperty(value = "参与邀请码")
    private String playCode;
    @ApiModelProperty(value = "活动来源")
    private SourceEnum sourceEnum;
    @ApiModelProperty(value = "参与规则")
    private RulePayloadVO playRulePayloadVO;
    @ApiModelProperty(value = "发放规则")
    private DistributePayloadVO grantRulePayloadVO;
    @ApiModelProperty(value = "参与活动邀请码类型")
    private PlayCodeEnum playCodeEnum;
    @ApiModelProperty(value = "活动卡券类别")
    List<CouponVO> couponVOS;
    @ApiModelProperty(value = "活动红包列表")
    List<RedPacketVO> redPacketVOS;
    @ApiModelProperty(value = "要绑定的红包编号列表")
    List<String> packetNoList;
    @ApiModelProperty(value = "要绑定的卡券编号列表")
    List<String> cardIdList;

    public ActivityDTO convertToDTO(){
        ActivityDTO dto = ObjectConvertUtil.map(this, ActivityDTO.class);
        if (getPlayCodeEnum() != null) {
            dto.setPlayCode(getPlayCodeEnum().getCode());
        }
        if (getGrantRulePayloadVO() != null) {
            dto.setGrantRulePayload(ObjectConvertUtil.map(this.getGrantRulePayloadVO(), DistributePayload.class));
        }
        if (getPlayRulePayloadVO() != null) {
            dto.setPlayRulePayload(ObjectConvertUtil.map(this.getPlayRulePayloadVO(), RulePayload.class));
        }
        return dto;
    }

    public ActivityWithPrizeDTO convertToDTOWithPrizes(){
        ActivityWithPrizeDTO dto = ObjectConvertUtil.map(this, ActivityWithPrizeDTO.class);
        if (getPlayCodeEnum() != null) {
            dto.setPlayCode(getPlayCodeEnum().getCode());
        }
        if (getGrantRulePayloadVO() != null) {
            dto.setGrantRulePayload(ObjectConvertUtil.map(this.getGrantRulePayloadVO(), DistributePayload.class));
            dto.setGrantRule(JSONObject.toJSONString(getGrantRulePayloadVO()));
        }
        if (getPlayRulePayloadVO() != null) {
            dto.setPlayRulePayload(ObjectConvertUtil.map(this.getPlayRulePayloadVO(), RulePayload.class));
            dto.setPlayRule(JSONObject.toJSONString(getPlayRulePayloadVO()));
        }
        if (getCouponVOS() != null) {
            dto.setCouponDTOS(ObjectConvertUtil.mapList(getCouponVOS(),CouponVO.class, CouponDTO.class));
        }
        if(getRedPacketVOS() != null){
            dto.setRedPacketDTOS(ObjectConvertUtil.mapList(getRedPacketVOS(),RedPacketVO.class, RedPacketDTO.class));
        }

        return dto;
    }

    public static ActivityVO from(ActivityWithPrizeDTO activityWithPrizeDTO){
        ActivityVO activityVO = ObjectConvertUtil.map(activityWithPrizeDTO,ActivityVO.class);
        activityVO.setPlayCodeEnum(PlayCodeEnum.from(activityWithPrizeDTO.getPlayCode()));
        if (activityWithPrizeDTO.getGrantRulePayload() != null) {
            activityVO.setGrantRulePayloadVO(ObjectConvertUtil.map(activityWithPrizeDTO.getGrantRulePayload(), DistributePayloadVO.class));
        }
        if (activityWithPrizeDTO.getPlayRulePayload() != null) {
            activityVO.setPlayRulePayloadVO(ObjectConvertUtil.map(activityWithPrizeDTO.getPlayRulePayload(), RulePayloadVO.class));
        }
        if(activityWithPrizeDTO.getRedPacketDTOS() != null){
            activityVO.setRedPacketVOS(RedPacketVO.from(activityWithPrizeDTO.getRedPacketDTOS()));
        }
        if(activityWithPrizeDTO.getCouponDTOS() != null){
            activityVO.setCouponVOS(CouponVO.from(activityWithPrizeDTO.getCouponDTOS()));
        }

        return activityVO;
    }

    public static ActivityVO from(ActivityDTO activityDTO){
        ActivityVO activityVO = ObjectConvertUtil.map(activityDTO,ActivityVO.class);
        activityVO.setPlayCodeEnum(PlayCodeEnum.from(activityDTO.getPlayCode()));
        if (activityDTO.getGrantRulePayload() != null) {
            activityVO.setGrantRulePayloadVO(ObjectConvertUtil.map(activityDTO.getGrantRulePayload(), DistributePayloadVO.class));
        }
        if (activityDTO.getPlayRulePayload() != null) {
            activityVO.setPlayRulePayloadVO(ObjectConvertUtil.map(activityDTO.getPlayRulePayload(), RulePayloadVO.class));
        }

        return activityVO;
    }

    public static List<ActivityVO> from(List<ActivityDTO> activityDTOS){
        List<ActivityVO> activityVOS = new ArrayList<>();
        activityDTOS.forEach(t -> activityVOS.add(from(t)));

        return activityVOS;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getActivityTemplateId() {
        return activityTemplateId;
    }

    public void setActivityTemplateId(Long activityTemplateId) {
        this.activityTemplateId = activityTemplateId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getTradeAreaId() {
        return tradeAreaId;
    }

    public void setTradeAreaId(Long tradeAreaId) {
        this.tradeAreaId = tradeAreaId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public ActivityStatusEnum getActivityStatusEnum() {
        return activityStatusEnum;
    }

    public void setActivityStatusEnum(ActivityStatusEnum activityStatusEnum) {
        this.activityStatusEnum = activityStatusEnum;
    }

    public String getPlayCode() {
        return playCode;
    }

    public void setPlayCode(String playCode) {
        this.playCode = playCode;
    }

    public SourceEnum getSourceEnum() {
        return sourceEnum;
    }

    public void setSourceEnum(SourceEnum sourceEnum) {
        this.sourceEnum = sourceEnum;
    }

    public RulePayloadVO getPlayRulePayloadVO() {
        return playRulePayloadVO;
    }

    public void setPlayRulePayloadVO(RulePayloadVO playRulePayloadVO) {
        this.playRulePayloadVO = playRulePayloadVO;
    }

    public DistributePayloadVO getGrantRulePayloadVO() {
        return grantRulePayloadVO;
    }

    public void setGrantRulePayloadVO(DistributePayloadVO grantRulePayloadVO) {
        this.grantRulePayloadVO = grantRulePayloadVO;
    }

    public PlayCodeEnum getPlayCodeEnum() {
        return playCodeEnum;
    }

    public void setPlayCodeEnum(PlayCodeEnum playCodeEnum) {
        this.playCodeEnum = playCodeEnum;
    }

    public List<CouponVO> getCouponVOS() {
        return couponVOS;
    }

    public void setCouponVOS(List<CouponVO> couponVOS) {
        this.couponVOS = couponVOS;
    }

    public List<RedPacketVO> getRedPacketVOS() {
        return redPacketVOS;
    }

    public void setRedPacketVOS(List<RedPacketVO> redPacketVOS) {
        this.redPacketVOS = redPacketVOS;
    }

    public List<String> getPacketNoList() {
        return packetNoList;
    }

    public void setPacketNoList(List<String> packetNoList) {
        this.packetNoList = packetNoList;
    }

    public List<String> getCardIdList() {
        return cardIdList;
    }

    public void setCardIdList(List<String> cardIdList) {
        this.cardIdList = cardIdList;
    }
}
