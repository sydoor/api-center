package com.lizikj.api.vo.reporting;

import com.lizikj.api.vo.reporting.param.ReportingTotalDetailVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by IntelliJ IDEA. adept
 * User:  liuyuntao
 * Date: 2017/7/18
 * Time:  20:57
 */
@ApiModel(value = "ReportingTotalVO", description = "报表统计汇总信息")
public class ReportingTotalVO {
    @ApiModelProperty(name="totalAmount", value="支付方式")
    private Double totalAmount;//总金额
    @ApiModelProperty(name="nums", value="总笔数")
    private Long nums;//总笔数
    @ApiModelProperty(name="tradeSource", value="交易来源")
    private Byte tradeSource;//交易来源 TradeSourceEnum.java中的值
    @ApiModelProperty(name="reportingTotalDetailVOS", value="汇总明细")
    private List<ReportingTotalDetailVO> reportingTotalDetailVOS;//汇总明细

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getNums() {
        return nums;
    }

    public void setNums(Long nums) {
        this.nums = nums;
    }

    public Byte getTradeSource() {
        return tradeSource;
    }

    public void setTradeSource(Byte tradeSource) {
        this.tradeSource = tradeSource;
    }

    public List<ReportingTotalDetailVO> getReportingTotalDetailVOS() {
        return reportingTotalDetailVOS;
    }

    public void setReportingTotalDetailVOS(List<ReportingTotalDetailVO> reportingTotalDetailVOS) {
        this.reportingTotalDetailVOS = reportingTotalDetailVOS;
    }
}
