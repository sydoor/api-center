package com.lizikj.api.vo.shop;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael.Huang
 * @date 2018/3/29 10:15
 */
@ApiModel
public class ShopMiniProgramConfigVO {

    /**
     * 店铺名称
     */
    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    /**
     * logo图片
     */
    @ApiModelProperty(value = "logo图片")
    private String logoImage;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private Address address;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String phone;

    /**
     * 营业时间
     */
    @ApiModelProperty(value = "营业时间")
    private String bizTime;

    /**
     * 平均消费文本
     */
    @ApiModelProperty(value = "平均消费文本")
    private String averageCost;

    /**
     * 环境图片
     */
    @ApiModelProperty(value = "环境图片")
    private List<ImageData> advImgs;

    /**
     * 环境图片
     */
    @ApiModelProperty(value = "环境图片")
    private List<ImageData> devImgs;

    /**
     * 特色美食
     */
    @ApiModelProperty(value = "特色美食")
    private List<ImageData> specialGoods;


    /**
     * 优惠信息列表
     */
    @ApiModelProperty(value = "优惠信息列表")
    private List<DiscountVo> discounts;

//    public static void main(String[] args) {
//        ShopMiniProgramConfigVO config = new ShopMiniProgramConfigVO();
//        config.setShopName("久久牛仔");
//        config.setLogoImage("http://p3.pstatp.com/large/pgc-image/15222036571067816f732ad");
//        config.setBizTime("上午10~下午20");
//        config.setAverageCost("人均消费:70元/人");
//        config.setPhone("020-33225566");
//        Address address = new Address();
//        address.setAddress("环球都汇广场");
//        address.setLatitude("112.344");
//        address.setLongitude("243.444");
//        config.setAddress(address);
//
//        List<ImageData> ls1 = new ArrayList<ImageData>();
//        for (int i = 0; i < 2; i++) {
//            ImageData data = new ImageData();
//            data.setImagePath("https://gma.alicdn.com/bao/uploaded/i3/124751810/TB2p2bjcTlYBeNjSszcXXbwhFXa_!!0-saturn_solar.jpg_540x540.jpg_.webp");
//            data.setName("广告" + (i + 1));
//            ls1.add(data);
//        }
//
//        List<ImageData> ls2 = new ArrayList<ImageData>();
//        for (int i = 0; i < 3; i++) {
//            ImageData data = new ImageData();
//            data.setImagePath("https://gma.alicdn.com/bao/uploaded/i3/124751810/TB2p2bjcTlYBeNjSszcXXbwhFXa_!!0-saturn_solar.jpg_540x540.jpg_.webp");
//            data.setName("环境" + (i + 1));
//            ls2.add(data);
//        }
//
//        List<ImageData> ls3 = new ArrayList<ImageData>();
//        for (int i = 0; i < 8; i++) {
//            ImageData data = new ImageData();
//            data.setImagePath("https://gma.alicdn.com/bao/uploaded/i3/124751810/TB2p2bjcTlYBeNjSszcXXbwhFXa_!!0-saturn_solar.jpg_540x540.jpg_.webp");
//            data.setName("特色美食" + (i + 1));
//            ls3.add(data);
//        }
//        config.setAdvImgs(ls1);
//        config.setDevImgs(ls2);
//        config.setSpecialGoods(ls3);
//
//        String s = JSON.toJSONString(config);
//        System.out.println(s);

//        String s = "{\"address\":{\"address\":\"环球都汇广场\",\"latitude\":\"112.344\",\"longitude\":\"243.444\"},\"advImgs\":[{\"imagePath\":\"https://gma.alicdn.com/bao/uploaded/i3/124751810/TB2p2bjcTlYBeNjSszcXXbwhFXa_!!0-saturn_solar.jpg_540x540.jpg_.webp\",\"name\":\"广告1\"},{\"imagePath\":\"https://gma.alicdn.com/bao/uploaded/i3/124751810/TB2p2bjcTlYBeNjSszcXXbwhFXa_!!0-saturn_solar.jpg_540x540.jpg_.webp\",\"name\":\"广告2\"}],\"averageCost\":\"人均消费:70元/人\",\"bizTime\":\"上午10~下午20\",\"devImgs\":[{\"imagePath\":\"https://gma.alicdn.com/bao/uploaded/i3/124751810/TB2p2bjcTlYBeNjSszcXXbwhFXa_!!0-saturn_solar.jpg_540x540.jpg_.webp\",\"name\":\"环境1\"},{\"imagePath\":\"https://gma.alicdn.com/bao/uploaded/i3/124751810/TB2p2bjcTlYBeNjSszcXXbwhFXa_!!0-saturn_solar.jpg_540x540.jpg_.webp\",\"name\":\"环境2\"},{\"imagePath\":\"https://gma.alicdn.com/bao/uploaded/i3/124751810/TB2p2bjcTlYBeNjSszcXXbwhFXa_!!0-saturn_solar.jpg_540x540.jpg_.webp\",\"name\":\"环境3\"}],\"logoImage\":\"http://p3.pstatp.com/large/pgc-image/15222036571067816f732ad\",\"phone\":\"020-33225566\",\"shopName\":\"久久牛仔\",\"specialGoods\":[{\"imagePath\":\"https://gma.alicdn.com/bao/uploaded/i3/124751810/TB2p2bjcTlYBeNjSszcXXbwhFXa_!!0-saturn_solar.jpg_540x540.jpg_.webp\",\"name\":\"特色美食1\"},{\"imagePath\":\"https://gma.alicdn.com/bao/uploaded/i3/124751810/TB2p2bjcTlYBeNjSszcXXbwhFXa_!!0-saturn_solar.jpg_540x540.jpg_.webp\",\"name\":\"特色美食2\"},{\"imagePath\":\"https://gma.alicdn.com/bao/uploaded/i3/124751810/TB2p2bjcTlYBeNjSszcXXbwhFXa_!!0-saturn_solar.jpg_540x540.jpg_.webp\",\"name\":\"特色美食3\"},{\"imagePath\":\"https://gma.alicdn.com/bao/uploaded/i3/124751810/TB2p2bjcTlYBeNjSszcXXbwhFXa_!!0-saturn_solar.jpg_540x540.jpg_.webp\",\"name\":\"特色美食4\"},{\"imagePath\":\"https://gma.alicdn.com/bao/uploaded/i3/124751810/TB2p2bjcTlYBeNjSszcXXbwhFXa_!!0-saturn_solar.jpg_540x540.jpg_.webp\",\"name\":\"特色美食5\"},{\"imagePath\":\"https://gma.alicdn.com/bao/uploaded/i3/124751810/TB2p2bjcTlYBeNjSszcXXbwhFXa_!!0-saturn_solar.jpg_540x540.jpg_.webp\",\"name\":\"特色美食6\"},{\"imagePath\":\"https://gma.alicdn.com/bao/uploaded/i3/124751810/TB2p2bjcTlYBeNjSszcXXbwhFXa_!!0-saturn_solar.jpg_540x540.jpg_.webp\",\"name\":\"特色美食7\"},{\"imagePath\":\"https://gma.alicdn.com/bao/uploaded/i3/124751810/TB2p2bjcTlYBeNjSszcXXbwhFXa_!!0-saturn_solar.jpg_540x540.jpg_.webp\",\"name\":\"特色美食8\"}]}";
//
//        ShopMiniProgramConfigVO config = JSON.parseObject(s,ShopMiniProgramConfigVO.class);
//
//        System.out.println(config);
//
//    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(String logoImage) {
        this.logoImage = logoImage;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBizTime() {
        return bizTime;
    }

    public void setBizTime(String bizTime) {
        this.bizTime = bizTime;
    }

    public String getAverageCost() {
        return averageCost;
    }

    public void setAverageCost(String averageCost) {
        this.averageCost = averageCost;
    }

    public List<ImageData> getAdvImgs() {
        return advImgs;
    }

    public void setAdvImgs(List<ImageData> advImgs) {
        this.advImgs = advImgs;
    }

    public List<ImageData> getDevImgs() {
        return devImgs;
    }

    public void setDevImgs(List<ImageData> devImgs) {
        this.devImgs = devImgs;
    }

    public List<ImageData> getSpecialGoods() {
        return specialGoods;
    }

    public void setSpecialGoods(List<ImageData> specialGoods) {
        this.specialGoods = specialGoods;
    }
}

@ApiModel
class Address {
    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private String longitude;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private String latitude;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}

@ApiModel
class ImageData {
    /**
     * 图片路径
     */
    @ApiModelProperty(value = "图片路径")
    private String imagePath;

    /**
     * 图片名称
     */
    @ApiModelProperty(value = "图片名称")
    private String name;

    /**
     * 链接
     */
    @ApiModelProperty(value = "链接")
    private String link;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
@ApiModel
class DiscountVo{
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    String name;

    /**
     * 时间
     */
    @ApiModelProperty(value = "时间")
    String time;

    /**
     * 图片链接
     */
    @ApiModelProperty(value = "图片链接")
    String imgUrl;

    /**
     * 查看数量
     */
    @ApiModelProperty(value = "查看数量")
    Integer watch;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getWatch() {
        return watch;
    }

    public void setWatch(Integer watch) {
        this.watch = watch;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
