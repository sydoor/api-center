package com.lizikj.api.vo.marketing.coupon;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author lxl
 * @Date 2018/7/30 15:37
 */
@ApiModel(value = "规则条件列表")
public class RulePayloadVO {
    /**
     * 有效店铺
     */
    @ApiModelProperty(value = "有效店铺")
    private List<Long> inShops;
    @ApiModelProperty(value = "有效店铺名称")
    private List<String> inShopNames;
    /**
     * 无效店铺
     */
    @ApiModelProperty(value = "无效店铺")
    private List<Long> notShops;
    @ApiModelProperty(value = "无效店铺名称")
    private List<String> notShopNames;
    /**
     * 有效商户
     */
    @ApiModelProperty(value = "有效商户")
    private List<Long> inMerchants;
    @ApiModelProperty(value = "有效商户名称")
    private List<String> inMerchantNames;
    /**
     * 无效商户
     */
    @ApiModelProperty(value = "无效商户")
    private List<Long> notMerchants;
    @ApiModelProperty(value = "无效商户名称")
    private List<String> notMerchantNames;
    /**
     * 有效商圈
     */
    @ApiModelProperty(value = "有效商圈")
    private List<Long> inTradeAreas;
    @ApiModelProperty(value = "有效商圈名称")
    private List<String> inTradeAreaNames;
    /**
     * 无效商圈
     */
    @ApiModelProperty(value = "无效商圈")
    private List<Long> notTradeAreas;

    @ApiModelProperty(value = "无效商圈名称")
    private List<String> notTradeAreaNames;
    /**
     * 高于会员等级
     */
    @ApiModelProperty(value = "高于会员等级")
    private Integer memberLevelAbove;
    /**
     * 周几有效 1 2 3 4 5 6 7
     */
    @ApiModelProperty(value = "周几有效 1 2 3 4 5 6 7，周日开始")
    private List<Integer> weekDaysIn;
    /**
     * 参与会员VIP等级 1 普通会员 2 VIP会员 0 不是会员 3 普通会员和VIP会员
     */
    @ApiModelProperty(value = "参与会员VIP等级 1 普通会员 2 VIP会员 0 不是会员 3 普通会员和VIP会员")
    private List<Integer> memberVipLevelList;
    /**
     * 订单金额大于
     */
    @ApiModelProperty(value = "订单金额大于")
    private Long orderAmountAbove;
    /**
     * 一天距离凌晨的秒数 开始秒数
     */
    @ApiModelProperty(value = "一天距离凌晨的秒数 开始秒数")
    private Long startSeconds;
    /**
     * 一天距离凌晨的秒数 结束秒数
     */
    @ApiModelProperty(value = "一天距离凌晨的秒数 结束秒数")
    private Long endSeconds;
    /**
     * 领取限制
     */
    @ApiModelProperty(value = "领取次数限制")
    private Integer getLimit;
    /**
     * 领取时间窗口 时间窗口内只能领取1次
     */
    @ApiModelProperty(value = "领取时间窗口 时间窗口内只能领取1次")
    private Integer getTimeWindow;

    /**
     * 使用限制
     */
    @ApiModelProperty(value = "使用规则：使用限制")
    private Integer useLimitFilter;
    /**
     * 订单金额满多少累计一张
     */
    @ApiModelProperty(value = "使用规则：订单金额满多少累计一张 过滤条件")
    private Integer amountAbovePlusFilter;
    /**
     * 过滤规则（只支持And运算）
     */
    @ApiModelProperty(value = "过滤规则（只支持And运算）")
    private List<String> filterList;
    /**
     * 各个条件的逆波兰式
     */
    @ApiModelProperty(value = "各个条件的逆波兰式 操作符只有# & | ，如A&B = A#B&，A&(B|C) = B#C|A&")
    private String rpn;

    public List<Long> getInShops() {
        return inShops;
    }

    public void setInShops(List<Long> inShops) {
        this.inShops = inShops;
    }

    public List<Long> getNotShops() {
        return notShops;
    }

    public void setNotShops(List<Long> notShops) {
        this.notShops = notShops;
    }

    public List<Long> getInMerchants() {
        return inMerchants;
    }

    public void setInMerchants(List<Long> inMerchants) {
        this.inMerchants = inMerchants;
    }

    public List<Long> getNotMerchants() {
        return notMerchants;
    }

    public void setNotMerchants(List<Long> notMerchants) {
        this.notMerchants = notMerchants;
    }

    public List<Long> getInTradeAreas() {
        return inTradeAreas;
    }

    public void setInTradeAreas(List<Long> inTradeAreas) {
        this.inTradeAreas = inTradeAreas;
    }

    public List<Long> getNotTradeAreas() {
        return notTradeAreas;
    }

    public void setNotTradeAreas(List<Long> notTradeAreas) {
        this.notTradeAreas = notTradeAreas;
    }

    public Integer getMemberLevelAbove() {
        return memberLevelAbove;
    }

    public void setMemberLevelAbove(Integer memberLevelAbove) {
        this.memberLevelAbove = memberLevelAbove;
    }

    public List<Integer> getWeekDaysIn() {
        return weekDaysIn;
    }

    public void setWeekDaysIn(List<Integer> weekDaysIn) {
        this.weekDaysIn = weekDaysIn;
    }

    public Long getOrderAmountAbove() {
        return orderAmountAbove;
    }

    public void setOrderAmountAbove(Long orderAmountAbove) {
        this.orderAmountAbove = orderAmountAbove;
    }

    public Long getStartSeconds() {
        return startSeconds;
    }

    public void setStartSeconds(Long startSeconds) {
        this.startSeconds = startSeconds;
    }

    public Long getEndSeconds() {
        return endSeconds;
    }

    public void setEndSeconds(Long endSeconds) {
        this.endSeconds = endSeconds;
    }

    public Integer getGetLimit() {
        return getLimit;
    }

    public void setGetLimit(Integer getLimit) {
        this.getLimit = getLimit;
    }

    public Integer getGetTimeWindow() {
        return getTimeWindow;
    }

    public void setGetTimeWindow(Integer getTimeWindow) {
        this.getTimeWindow = getTimeWindow;
    }

    public Integer getUseLimitFilter() {
        return useLimitFilter;
    }

    public void setUseLimitFilter(Integer useLimitFilter) {
        this.useLimitFilter = useLimitFilter;
    }

    public Integer getAmountAbovePlusFilter() {
        return amountAbovePlusFilter;
    }

    public void setAmountAbovePlusFilter(Integer amountAbovePlusFilter) {
        this.amountAbovePlusFilter = amountAbovePlusFilter;
    }

    public List<String> getFilterList() {
        return filterList;
    }

    public void setFilterList(List<String> filterList) {
        this.filterList = filterList;
    }

    public String getRpn() {
        return rpn;
    }

    public void setRpn(String rpn) {
        this.rpn = rpn;
    }

    public List<Integer> getMemberVipLevelList() {
        return memberVipLevelList;
    }

    public void setMemberVipLevelList(List<Integer> memberVipLevelList) {
        this.memberVipLevelList = memberVipLevelList;
    }

    public List<String> getInShopNames() {
        return inShopNames;
    }

    public void setInShopNames(List<String> inShopNames) {
        this.inShopNames = inShopNames;
    }

    public List<String> getNotShopNames() {
        return notShopNames;
    }

    public void setNotShopNames(List<String> notShopNames) {
        this.notShopNames = notShopNames;
    }

    public List<String> getInMerchantNames() {
        return inMerchantNames;
    }

    public void setInMerchantNames(List<String> inMerchantNames) {
        this.inMerchantNames = inMerchantNames;
    }

    public List<String> getNotMerchantNames() {
        return notMerchantNames;
    }

    public void setNotMerchantNames(List<String> notMerchantNames) {
        this.notMerchantNames = notMerchantNames;
    }

    public List<String> getInTradeAreaNames() {
        return inTradeAreaNames;
    }

    public void setInTradeAreaNames(List<String> inTradeAreaNames) {
        this.inTradeAreaNames = inTradeAreaNames;
    }

    public List<String> getNotTradeAreaNames() {
        return notTradeAreaNames;
    }

    public void setNotTradeAreaNames(List<String> notTradeAreaNames) {
        this.notTradeAreaNames = notTradeAreaNames;
    }
}
