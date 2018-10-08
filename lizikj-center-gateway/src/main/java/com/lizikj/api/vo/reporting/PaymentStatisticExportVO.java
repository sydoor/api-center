package com.lizikj.api.vo.reporting;

/**
 * @author lxl
 * @Date 2018/1/11 9:45
 */
public class PaymentStatisticExportVO {
    private String reportTime;

    private Double refundAmount;
    private Integer refundNum;
    /**
     * 现金金额
     */
    private Double cashAmount;
    /**
     * 现金笔数
     */
    private Integer cashNums;
    /**
     * 支付宝金额
     */
    private Double aliAmount;
    /**
     * 支付宝笔数
     */
    private Integer aliNums;
    /**
     * 微信金额
     */
    private Double webchatAmount;
    /**
     * 微信笔数
     */
    private Integer webchatNums;
    /**
     * 刷卡金额
     */
    private Double cardAmount;
    /**
     * 刷卡笔数
     */
    private Integer cardNums;
    /**
     * 银联金额
     */
    private Double unionPayAmount;
    /**
     * 银联笔数
     */
    private Integer unionPayNums;
    /**
     * 美团代金券 金额
     */
    private Double meituanAmount;
    /**
     * 美团代金券 笔数
     */
    private Integer meituanNums;
    /**
     * 会员支付 金额
     */
    private Double memberPayAmount;
    /**
     * 会员支付 笔数
     */
    private Integer memberPayNums;
    /**
     * 股东 支付金额
     */
    private Double tenderAmount;
    /**
     * 股东 笔数
     */
    private Integer tenderNums;

    public PaymentStatisticExportVO() {
        this.refundAmount = 0.00D;
        this.refundNum = 0;
        this.cashAmount = 0.00D;
        this.cashNums = 0;
        this.aliAmount = 0.00D;
        this.aliNums = 0;
        this.webchatAmount = 0.00D;
        this.webchatNums = 0;
        this.cardAmount = 0.00D;
        this.cardNums = 0;
        this.unionPayAmount = 0.00D;
        this.unionPayNums = 0;
        this.memberPayAmount = 0.00D;
        this.memberPayNums = 0;
        this.tenderAmount = 0.00D;
        this.tenderNums = 0;
        this.meituanAmount = 0.00D;
        this.meituanNums = 0;
    }

    public void add(PaymentStatisticExportVO vo){
        this.refundAmount = this.refundAmount + vo.getRefundAmount();
        this.refundNum = this.refundNum + vo.getRefundNum();
        this.cashAmount = this.cashAmount + vo.getCashAmount();
        this.cashNums = this.cashNums + vo.getCardNums();
        this.aliAmount = this.aliAmount + vo.getAliAmount();
        this.aliNums = this.aliNums + vo.getAliNums();
        this.webchatAmount = this.webchatAmount + vo.getWebchatAmount();
        this.webchatNums = this.webchatNums + vo.getWebchatNums();
        this.cardAmount = this.cardAmount + vo.getCardAmount();
        this.cardNums = this.cardNums + vo.getCardNums();
        this.unionPayAmount = this.unionPayAmount + vo.getUnionPayAmount();
        this.unionPayNums = this.unionPayNums + vo.getUnionPayNums();
        this.memberPayAmount = this.memberPayAmount + vo.getMemberPayAmount();
        this.memberPayNums = this.memberPayNums + vo.getMemberPayNums();
        this.tenderAmount = this.tenderAmount + vo.getTenderAmount();
        this.tenderNums = this.tenderNums + vo.getTenderNums();
        this.meituanAmount = this.meituanAmount + vo.getMeituanAmount();
        this.meituanNums = this.meituanNums + vo.getMeituanNums();
    }

    public Double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Integer getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(Integer refundNum) {
        this.refundNum = refundNum;
    }

    public Double getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(Double cashAmount) {
        this.cashAmount = cashAmount;
    }

    public Integer getCashNums() {
        return cashNums;
    }

    public void setCashNums(Integer cashNums) {
        this.cashNums = cashNums;
    }

    public Double getAliAmount() {
        return aliAmount;
    }

    public void setAliAmount(Double aliAmount) {
        this.aliAmount = aliAmount;
    }

    public Integer getAliNums() {
        return aliNums;
    }

    public void setAliNums(Integer aliNums) {
        this.aliNums = aliNums;
    }

    public Double getWebchatAmount() {
        return webchatAmount;
    }

    public void setWebchatAmount(Double webchatAmount) {
        this.webchatAmount = webchatAmount;
    }

    public Integer getWebchatNums() {
        return webchatNums;
    }

    public void setWebchatNums(Integer webchatNums) {
        this.webchatNums = webchatNums;
    }

    public Double getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(Double cardAmount) {
        this.cardAmount = cardAmount;
    }

    public Integer getCardNums() {
        return cardNums;
    }

    public void setCardNums(Integer cardNums) {
        this.cardNums = cardNums;
    }

    public Double getUnionPayAmount() {
        return unionPayAmount;
    }

    public void setUnionPayAmount(Double unionPayAmount) {
        this.unionPayAmount = unionPayAmount;
    }

    public Integer getUnionPayNums() {
        return unionPayNums;
    }

    public void setUnionPayNums(Integer unionPayNums) {
        this.unionPayNums = unionPayNums;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public Double getMeituanAmount() {
        return meituanAmount;
    }

    public void setMeituanAmount(Double meituanAmount) {
        this.meituanAmount = meituanAmount;
    }

    public Integer getMeituanNums() {
        return meituanNums;
    }

    public void setMeituanNums(Integer meituanNums) {
        this.meituanNums = meituanNums;
    }

    public Double getMemberPayAmount() {
        return memberPayAmount;
    }

    public void setMemberPayAmount(Double memberPayAmount) {
        this.memberPayAmount = memberPayAmount;
    }

    public Integer getMemberPayNums() {
        return memberPayNums;
    }

    public void setMemberPayNums(Integer memberPayNums) {
        this.memberPayNums = memberPayNums;
    }

    public Double getTenderAmount() {
        return tenderAmount;
    }

    public void setTenderAmount(Double tenderAmount) {
        this.tenderAmount = tenderAmount;
    }

    public Integer getTenderNums() {
        return tenderNums;
    }

    public void setTenderNums(Integer tenderNums) {
        this.tenderNums = tenderNums;
    }
}
