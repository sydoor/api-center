package com.lizikj.api.vo.marketing.coupon;

import com.lizikj.marketing.api.enums.GetSceneEnum;
import com.lizikj.marketing.api.enums.PlayCodeEnum;
import com.lizikj.marketing.api.enums.ShareDrawTypeEnum;
import com.lizikj.marketing.api.enums.SourceSceneEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lxl
 * @Date 2018/7/25 17:10
 */
@ApiModel(value = "分享领取红包或券")
public class DrawParamVO {
    @ApiModelProperty(value = "分享类型")
    private ShareDrawTypeEnum drawTypeEnum;
    @ApiModelProperty(value = "订单号")
    private String orderNo;
    @ApiModelProperty(value = "分享人")
    private Long shareUserId;
    @ApiModelProperty(value = "活动邀请码")
    private PlayCodeEnum playCodeEnum;
    @ApiModelProperty(value = "红包来源场景")
    private SourceSceneEnum sourceSceneEnum;
    @ApiModelProperty(value = "领取场景")
    private GetSceneEnum getSceneEnum;
    @ApiModelProperty(value = "来源卡券码")
    private String donateCouponCode;
    @ApiModelProperty(value = "来源卡券码")
    private String sourceCouponCode;
    @ApiModelProperty(value = "来源红包码")
    private String sourcePacketNo;
    @ApiModelProperty(value = "失效时间 秒级")
    private Long expireTime;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getShareUserId() {
        return shareUserId;
    }

    public void setShareUserId(Long shareUserId) {
        this.shareUserId = shareUserId;
    }

    public PlayCodeEnum getPlayCodeEnum() {
        return playCodeEnum;
    }

    public void setPlayCodeEnum(PlayCodeEnum playCodeEnum) {
        this.playCodeEnum = playCodeEnum;
    }

    public SourceSceneEnum getSourceSceneEnum() {
        return sourceSceneEnum;
    }

    public void setSourceSceneEnum(SourceSceneEnum sourceSceneEnum) {
        this.sourceSceneEnum = sourceSceneEnum;
    }

    public GetSceneEnum getGetSceneEnum() {
        return getSceneEnum;
    }

    public void setGetSceneEnum(GetSceneEnum getSceneEnum) {
        this.getSceneEnum = getSceneEnum;
    }

    public String getSourceCouponCode() {
        return sourceCouponCode;
    }

    public void setSourceCouponCode(String sourceCouponCode) {
        this.sourceCouponCode = sourceCouponCode;
    }

    public String getSourcePacketNo() {
        return sourcePacketNo;
    }

    public void setSourcePacketNo(String sourcePacketNo) {
        this.sourcePacketNo = sourcePacketNo;
    }

    public ShareDrawTypeEnum getDrawTypeEnum() {
        return drawTypeEnum;
    }

    public void setDrawTypeEnum(ShareDrawTypeEnum drawTypeEnum) {
        this.drawTypeEnum = drawTypeEnum;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public String getDonateCouponCode() {
        return donateCouponCode;
    }

    public void setDonateCouponCode(String donateCouponCode) {
        this.donateCouponCode = donateCouponCode;
    }
}
