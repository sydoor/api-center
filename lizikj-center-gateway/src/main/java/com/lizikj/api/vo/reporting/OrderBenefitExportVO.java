package com.lizikj.api.vo.reporting;

/**
 * @author lxl
 * @Date 2018/1/11 11:54
 */
public class OrderBenefitExportVO {
    private String reportTime;
    /**
     * 会员优惠
     */
    private Double memberAmount;
    /**
     * 会员优惠次数
     */
    private Integer memberNums;
    /**
     * 优惠券 优惠
     */
    private Double couponAmount;
    /**
     * 优惠券 次数
     */
    private Integer couponNums;
    /**
     * 砍价 优惠
     */
    private Double bargainAmount;
    /**
     * 砍价 次数
     */
    private Integer bargainNums;
    /**
     * 满减 优惠
     */
    private Double fullReduceAmount;
    /**
     * 满减 次数
     */
    private Integer fullReduceNums;
    /**
     * 分时优惠
     */
    private Double timingAmount;
    /**
     * 分时优惠 次数
     */
    private Integer timingNums;
    /**
     * 赠菜
     */
    private Double donateAmount;
    /**
     * 赠菜 次数
     */
    private Integer donateNums;
    /**
     * 自定义 优惠
     */
    private Double defineAmount;
    /**
     * 自定义优惠 次数
     */
    private Integer defineNums;
    /**
     * 抹零优惠
     */
    private Double cutAmount;
    /**
     * 抹零优惠 次数
     */
    private Integer cutNums;

    public OrderBenefitExportVO() {
        this.memberAmount = 0.00D;
        this.memberNums = 0;
        this.couponAmount = 0.00D;
        this.couponNums = 0;
        this.bargainAmount = 0.00D;
        this.bargainNums = 0;
        this.fullReduceAmount = 0.00D;
        this.fullReduceNums = 0;
        this.timingAmount = 0.00D;
        this.timingNums = 0;
        this.donateAmount = 0.00D;
        this.donateNums = 0;
        this.defineAmount = 0.00D;
        this.defineNums = 0;
        this.cutAmount = 0.00D;
        this.cutNums = 0;
    }

    public void add(OrderBenefitExportVO vo){
        this.memberAmount = this.memberAmount + vo.getMemberAmount();
        this.memberNums = this.memberNums + vo.getMemberNums();
        this.couponAmount = this.couponAmount + vo.getCouponAmount();
        this.couponNums = this.couponNums + vo.getCouponNums();
        this.bargainAmount = this.bargainAmount + vo.getBargainAmount();
        this.bargainNums = this.bargainNums + vo.getBargainNums();
        this.fullReduceAmount = this.fullReduceAmount + vo.getFullReduceAmount();
        this.fullReduceNums = this.fullReduceNums + vo.getFullReduceNums();
        this.timingAmount = this.timingAmount + vo.getTimingAmount();
        this.timingNums = this.timingNums + vo.getTimingNums();
        this.donateAmount = this.donateAmount + vo.getDonateAmount();
        this.donateNums = this.donateNums + vo.getDonateNums();
        this.defineAmount = this.defineAmount + vo.getDefineAmount();
        this.defineNums = this.defineNums + vo.getDonateNums();
        this.cutAmount = this.cutAmount + vo.getCutAmount();
        this.cutNums = this.cutNums + vo.getCutNums();
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public Double getMemberAmount() {
        return memberAmount;
    }

    public void setMemberAmount(Double memberAmount) {
        this.memberAmount = memberAmount;
    }

    public Integer getMemberNums() {
        return memberNums;
    }

    public void setMemberNums(Integer memberNums) {
        this.memberNums = memberNums;
    }

    public Double getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(Double couponAmount) {
        this.couponAmount = couponAmount;
    }

    public Integer getCouponNums() {
        return couponNums;
    }

    public void setCouponNums(Integer couponNums) {
        this.couponNums = couponNums;
    }

    public Double getBargainAmount() {
        return bargainAmount;
    }

    public void setBargainAmount(Double bargainAmount) {
        this.bargainAmount = bargainAmount;
    }

    public Integer getBargainNums() {
        return bargainNums;
    }

    public void setBargainNums(Integer bargainNums) {
        this.bargainNums = bargainNums;
    }

    public Double getFullReduceAmount() {
        return fullReduceAmount;
    }

    public void setFullReduceAmount(Double fullReduceAmount) {
        this.fullReduceAmount = fullReduceAmount;
    }

    public Integer getFullReduceNums() {
        return fullReduceNums;
    }

    public void setFullReduceNums(Integer fullReduceNums) {
        this.fullReduceNums = fullReduceNums;
    }

    public Double getTimingAmount() {
        return timingAmount;
    }

    public void setTimingAmount(Double timingAmount) {
        this.timingAmount = timingAmount;
    }

    public Integer getTimingNums() {
        return timingNums;
    }

    public void setTimingNums(Integer timingNums) {
        this.timingNums = timingNums;
    }

    public Double getDonateAmount() {
        return donateAmount;
    }

    public void setDonateAmount(Double donateAmount) {
        this.donateAmount = donateAmount;
    }

    public Integer getDonateNums() {
        return donateNums;
    }

    public void setDonateNums(Integer donateNums) {
        this.donateNums = donateNums;
    }

    public Double getDefineAmount() {
        return defineAmount;
    }

    public void setDefineAmount(Double defineAmount) {
        this.defineAmount = defineAmount;
    }

    public Integer getDefineNums() {
        return defineNums;
    }

    public void setDefineNums(Integer defineNums) {
        this.defineNums = defineNums;
    }

    public Double getCutAmount() {
        return cutAmount;
    }

    public void setCutAmount(Double cutAmount) {
        this.cutAmount = cutAmount;
    }

    public Integer getCutNums() {
        return cutNums;
    }

    public void setCutNums(Integer cutNums) {
        this.cutNums = cutNums;
    }
}
