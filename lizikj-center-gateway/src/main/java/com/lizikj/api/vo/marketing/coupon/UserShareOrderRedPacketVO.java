package com.lizikj.api.vo.marketing.coupon;

import com.lizikj.api.vo.order.OrderInfoVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhoufe
 * @date 2018/7/25 15:04
 */
@ApiModel
public class UserShareOrderRedPacketVO implements Serializable {

    @ApiModelProperty(name = "shareUserName", value = "分享人用户名称")
    private String shareUserName;

    @ApiModelProperty(name = "shareUserPortraitUrl", value = "分享人用户头像链接")
    private String shareUserPortraitUrl;

    @ApiModelProperty(name = "orderInfo", value = "订单信息：所吃的美食信息取orderItems里面的goodsName和medisId")
    private OrderInfoVO orderInfo;

    @ApiModelProperty(name = "shopMarketingInfo", value = "撩美味店铺信息")
    private ShopMarketingInfoVO shopMarketingInfo;

//    @ApiModelProperty(name = "eatGoodsInfos", value = "所吃的美食信息取")
//    private List<GoodsSimpleRecommendVO> eatGoodsInfos;

    @ApiModelProperty(name = "shopGoodsRecommends", value = "店铺推荐美食信息")
    private List<GoodsSimpleRecommendVO> shopGoodsRecommends;

    @ApiModelProperty(name = "userRedPacketRecords", value = "用户领取红包记录")
    private List<UserRedPacketRecordVO>  userRedPacketRecords;

	@ApiModelProperty(name = "qrcodeUrl", value = "关注公众号的二维码")
	private String qrcodeUrl;


    public OrderInfoVO getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoVO orderInfo) {
        this.orderInfo = orderInfo;
    }

    public ShopMarketingInfoVO getShopMarketingInfo() {
        return shopMarketingInfo;
    }

    public void setShopMarketingInfo(ShopMarketingInfoVO shopMarketingInfo) {
        this.shopMarketingInfo = shopMarketingInfo;
    }

    public List<GoodsSimpleRecommendVO> getShopGoodsRecommends() {
        return shopGoodsRecommends;
    }

    public void setShopGoodsRecommends(List<GoodsSimpleRecommendVO> shopGoodsRecommends) {
        this.shopGoodsRecommends = shopGoodsRecommends;
    }

    public List<UserRedPacketRecordVO> getUserRedPacketRecords() {
        return userRedPacketRecords;
    }

    public void setUserRedPacketRecords(List<UserRedPacketRecordVO> userRedPacketRecords) {
        this.userRedPacketRecords = userRedPacketRecords;
    }

    public String getShareUserName() {
        return shareUserName;
    }

    public void setShareUserName(String shareUserName) {
        this.shareUserName = shareUserName;
    }

    public String getShareUserPortraitUrl() {
        return shareUserPortraitUrl;
    }

    public void setShareUserPortraitUrl(String shareUserPortraitUrl) {
        this.shareUserPortraitUrl = shareUserPortraitUrl;
    }

	public String getQrcodeUrl() {
		return qrcodeUrl;
	}

	public void setQrcodeUrl(String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
	}
}
