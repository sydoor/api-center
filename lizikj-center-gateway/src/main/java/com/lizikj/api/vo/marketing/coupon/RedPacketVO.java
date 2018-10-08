package com.lizikj.api.vo.marketing.coupon;

import com.alibaba.fastjson.JSONObject;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.marketing.api.dto.RedPacketDTO;
import com.lizikj.marketing.api.enums.RedPacketStatusEnum;
import com.lizikj.marketing.api.enums.RedPacketTypeEnum;
import com.lizikj.marketing.api.enums.SourceEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lxl
 * @Date 2018/7/31 11:32
 */
@ApiModel(value = "红包")
public class RedPacketVO {
    @ApiModelProperty(value = "红包ID")
    private Long packetId;
    @ApiModelProperty(value = "红包编号")
    private String packetNo;
    @ApiModelProperty(value = "红包名称")
    private String packetName;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "红包类型")
    private RedPacketTypeEnum packetTypeEnum;
    @ApiModelProperty(value = "红包来源")
    private SourceEnum packetSourceEnum;
    @ApiModelProperty(value = "随机红包最低金额")
    private Long minRandomAmount;
    @ApiModelProperty(value = "随机红包最大金额")
    private Long maxRandomAmount;
    @ApiModelProperty(value = "固定额度")
    private Long fixedAmount;
    @ApiModelProperty(value = "红包总额")
    private Long totalAmount;
    @ApiModelProperty(value = "库存")
    private Integer stock;
    @ApiModelProperty(value = "发放数量")
    private Integer grantNum;
    @ApiModelProperty(value = "使用数量")
    private Integer useNum;
    @ApiModelProperty(value = "领取规则")
    private RulePayloadVO ruleInfoVO;
    @ApiModelProperty(value = "使用规则")
    private RulePayloadVO useRuleInfoVO;
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    @ApiModelProperty(value = "商圈ID")
    private Long tradeAreaId;
    @ApiModelProperty(value = "活动ID")
    private Long activityId;
    @ApiModelProperty(value = "开始时间")
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
    @ApiModelProperty(value = "红包状态")
    private RedPacketStatusEnum packetStatusEnum;
    @ApiModelProperty(value = "有效小时数")
    private Integer validHour;


    public RedPacketDTO convertToDTO(){
        RedPacketDTO dto = ObjectConvertUtil.map(this, RedPacketDTO.class);
        if (getRuleInfoVO() != null) {
            dto.setRuleInfo(JSONObject.toJSONString(getRuleInfoVO()));
        }
        if (getUseRuleInfoVO() != null) {
            dto.setUseRuleInfo(JSONObject.toJSONString(getUseRuleInfoVO()));
        }

        return dto;
    }

    public static RedPacketVO from(RedPacketDTO source){
        RedPacketVO vo = ObjectConvertUtil.map(source, RedPacketVO.class);
        if (source.getUseRuleInfo() != null) {
            vo.setUseRuleInfoVO(JSONObject.parseObject(source.getUseRuleInfo(),RulePayloadVO.class));
        }
        if (source.getRuleInfo() != null) {
            vo.setRuleInfoVO(JSONObject.parseObject(source.getRuleInfo(),RulePayloadVO.class));
        }

        return vo;
    }

    public static List<RedPacketVO> from(List<RedPacketDTO> sources){
        List<RedPacketVO> vos = new ArrayList<>();
        if(sources != null){
            sources.forEach(t -> {
                vos.add(from(t));
            });
        }

        return vos;
    }

    public Long getPacketId() {
        return packetId;
    }

    public void setPacketId(Long packetId) {
        this.packetId = packetId;
    }

    public String getPacketNo() {
        return packetNo;
    }

    public void setPacketNo(String packetNo) {
        this.packetNo = packetNo;
    }

    public String getPacketName() {
        return packetName;
    }

    public void setPacketName(String packetName) {
        this.packetName = packetName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public RedPacketTypeEnum getPacketTypeEnum() {
        return packetTypeEnum;
    }

    public void setPacketTypeEnum(RedPacketTypeEnum packetTypeEnum) {
        this.packetTypeEnum = packetTypeEnum;
    }

    public SourceEnum getPacketSourceEnum() {
        return packetSourceEnum;
    }

    public void setPacketSourceEnum(SourceEnum packetSourceEnum) {
        this.packetSourceEnum = packetSourceEnum;
    }

    public Long getMinRandomAmount() {
        return minRandomAmount;
    }

    public void setMinRandomAmount(Long minRandomAmount) {
        this.minRandomAmount = minRandomAmount;
    }

    public Long getMaxRandomAmount() {
        return maxRandomAmount;
    }

    public void setMaxRandomAmount(Long maxRandomAmount) {
        this.maxRandomAmount = maxRandomAmount;
    }

    public Long getFixedAmount() {
        return fixedAmount;
    }

    public void setFixedAmount(Long fixedAmount) {
        this.fixedAmount = fixedAmount;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getGrantNum() {
        return grantNum;
    }

    public void setGrantNum(Integer grantNum) {
        this.grantNum = grantNum;
    }

    public Integer getUseNum() {
        return useNum;
    }

    public void setUseNum(Integer useNum) {
        this.useNum = useNum;
    }

    public RulePayloadVO getRuleInfoVO() {
        return ruleInfoVO;
    }

    public void setRuleInfoVO(RulePayloadVO ruleInfoVO) {
        this.ruleInfoVO = ruleInfoVO;
    }

    public RulePayloadVO getUseRuleInfoVO() {
        return useRuleInfoVO;
    }

    public void setUseRuleInfoVO(RulePayloadVO useRuleInfoVO) {
        this.useRuleInfoVO = useRuleInfoVO;
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

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
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

    public RedPacketStatusEnum getPacketStatusEnum() {
        return packetStatusEnum;
    }

    public void setPacketStatusEnum(RedPacketStatusEnum packetStatusEnum) {
        this.packetStatusEnum = packetStatusEnum;
    }

    public Integer getValidHour() {
        return validHour;
    }

    public void setValidHour(Integer validHour) {
        this.validHour = validHour;
    }
}
