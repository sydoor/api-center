package com.lizikj.api.controller.reporting;

import com.github.pagehelper.PageInfo;
import com.lizikj.api.utils.ReportUtil;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.member.LmwMemberStatisticsVO;
import com.lizikj.api.vo.member.MemberStatisticsVO;
import com.lizikj.api.vo.member.param.DateParam;
import com.lizikj.api.vo.reporting.*;
import com.lizikj.api.vo.reporting.param.ReportCommonParam;
import com.lizikj.common.util.CollectionUtils;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.PageParameter;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.login.interceptor.LoginExclude;
import com.lizikj.marketing.api.dto.CouponItemQueryParamDTO;
import com.lizikj.marketing.api.dto.RedPacketItemQueryParamDTO;
import com.lizikj.marketing.api.enums.CardUseEnum;
import com.lizikj.marketing.api.enums.RedPacketUseEnum;
import com.lizikj.marketing.api.facade.ICardReadFacade;
import com.lizikj.marketing.api.facade.IRedPacketReadFacade;
import com.lizikj.member.dto.LmwMemberStatisticsDTO;
import com.lizikj.member.facade.IMemberFacade;
import com.lizikj.merchant.dto.ShopMerchantDTO;
import com.lizikj.merchant.facade.IShopMerchantReadFacade;
import com.lizikj.order.dto.OrderStaticsDTO;
import com.lizikj.order.facade.IOrderReadFacade;
import com.lizikj.reporting.dto.*;
import com.lizikj.reporting.dto.param.MemberFlowQueryDTO;
import com.lizikj.reporting.enums.MemberPlatformEnum;
import com.lizikj.reporting.enums.ReportingErrorEnum;
import com.lizikj.reporting.enums.ReportingTypeEnum;
import com.lizikj.reporting.exception.ReportingException;
import com.lizikj.reporting.facade.IMemberCountConsumeReportReadFacade;
import com.lizikj.reporting.facade.IMemberCountConsumeReportWriteFacade;
import com.lizikj.reporting.facade.IMemberReportFacade;
import com.lizikj.reporting.facade.IMerchantMemberReportFacade;
import com.lizikj.user.dto.ThirdUserInfoDTO;
import com.lizikj.user.facade.IThirdUserInfoUserFacade;
import com.lizikj.user.facade.IWechatMerchantUserRelReadFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 会员统计
 * Created by liangxiaolin on 2017/8/10.
 */
@RestController
@RequestMapping(value = "/reporting/member")
@Api(value = "report_member",tags = "会员统计接口")
public class MemberReportController {
    private final static Logger log = LoggerFactory.getLogger(MemberReportController.class);
    @Autowired
    IMerchantMemberReportFacade merchantMemberReportFacade;
    @Autowired
    IMemberReportFacade memberReportFacade;
    @Autowired
    IShopMerchantReadFacade shopMerchantReadFacade;
    @Autowired
    IMemberCountConsumeReportWriteFacade memberCountConsumeReportWriteFacade;
    @Autowired
    IMemberCountConsumeReportReadFacade memberCountConsumeReportReadFacade;
    @Autowired
    IMemberFacade memberFacade;
    @Autowired
    IThirdUserInfoUserFacade thirdUserInfoUserFacade;
    @Autowired
    ICardReadFacade cardReadFacade;
    @Autowired
    IRedPacketReadFacade redPacketReadFacade;
    @Autowired
    IOrderReadFacade orderReadFacade;


    /**
     * 获取李子平台会员统计
     * @return
     */
    @RequestMapping(value = "/sumTodayAndHis4Lizi",method = RequestMethod.GET)
    @ApiOperation(value = "获取李子平台会员统计",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<MemberCountConsumeReportVO> sumTodayAndHis4Lizi(){
        Result<MemberCountConsumeReportVO> result;
        try {

            MemberCountConsumeReportDTO reportDTO = memberCountConsumeReportReadFacade.sumTodayAndHis(MemberPlatformEnum.LIZI);
            if (null == reportDTO){
                return Result.SUCESS(null);
            }
            MemberCountConsumeReportVO reportVO = ObjectConvertUtil.map(reportDTO, MemberCountConsumeReportVO.class);
            result = Result.SUCESS(reportVO);
        }catch (Exception e){
            if (log.isErrorEnabled()) {
                log.error("sumTodayAndHis4Lizi获取李子平台会员统计错误：", e);
            }

            result = Result.FAILURE("获取李子平台会员统计错误！");
        }

        return result;
    }


    /**
     * 获取李子平台会员统计信息
     * @return
     */
    @RequestMapping(value = "/listLmwMemberStatistics",method = RequestMethod.POST)
    @ApiOperation(value = "获取李子平台会员统计信息",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<PageInfo<LmwMemberStatisticsVO>> listLmwMemberStatistics(
            @ApiParam(name = "param", value = "param", required = true)
            @RequestBody LmwMemberStatisticsVO param
    ){
        Result<PageInfo<LmwMemberStatisticsVO>> result;
        try {

            if (null == param){
                throw new ReportingException(ReportingErrorEnum.PARAMETERS_ERROR, "传入参数为空");
            }

            LmwMemberStatisticsDTO lmwMemberStatisticsDTO = ObjectConvertUtil.map(param, LmwMemberStatisticsDTO.class);
            PageParameter pageParameter = new PageParameter();
            pageParameter.setPageNum(param.getPageNum());
            pageParameter.setPageSize(param.getPageSize());
            PageInfo<LmwMemberStatisticsDTO> dtoPageInfo = memberFacade.listLmwMemberStatistics(lmwMemberStatisticsDTO, pageParameter);
            List<LmwMemberStatisticsDTO> dtoList = dtoPageInfo.getList();
            PageInfo voPageInfo = dtoPageInfo;
            if (CollectionUtils.isListNotBlank(dtoList)){
                List<LmwMemberStatisticsVO> voList = ObjectConvertUtil.mapList(dtoList, LmwMemberStatisticsDTO.class, LmwMemberStatisticsVO.class);
                dtoToVo(voList);
                voPageInfo.setList(voList);
            }
            result = Result.SUCESS(voPageInfo);
        }catch (Exception e){
            if (log.isErrorEnabled()) {
                log.error("listLmwMemberStatistics获取李子平台会员统计信息：", e);
            }

            result = Result.FAILURE("获取李子平台会员统计信息！");
        }

        return result;
    }

    /**
     * 李子平台会员统计信息返回前端
     * @params [voList]
     * @return void
     * @author zhoufe
     * @date 2018/8/6 9:17
     */
    private void dtoToVo(List<LmwMemberStatisticsVO> voList) {
        if (CollectionUtils.isListBlank(voList)) {
           return;
        }

        voList.forEach(vo ->{
        //以下都是统计得到的东西
        Long memberId = vo.getMemberId();
        if (! isLongNull(memberId)) {
            ThirdUserInfoDTO userInfoDTO = thirdUserInfoUserFacade.selectByMemberId(memberId);
            if (userInfoDTO != null && ! isLongNull(userInfoDTO.getUserId())){
                vo.setUserId(userInfoDTO.getUserId());
                OrderStaticsDTO orderStaticsDTO = orderReadFacade.statisticsLmwMember(userInfoDTO.getUserId(), memberId);
                if (orderStaticsDTO != null) {
                    vo.setPurchaseTimes(orderStaticsDTO.getPurchaseTimes());
                    vo.setTotalDiscountAmount(orderStaticsDTO.getTotalDiscountAmount());
                    vo.setTotalCostAmount(orderStaticsDTO.getTotalCostAmount());
                }
                CardUseEnum toUse = CardUseEnum.TO_USE;
                int cardAvailableCouponTotal = getCardCouponTotal(userInfoDTO.getUserId(), toUse);
                vo.setAvailableCouponTotal(cardAvailableCouponTotal);
                CardUseEnum used = CardUseEnum.TO_USE;
                int cardUsedCouponTotal = getCardCouponTotal(userInfoDTO.getUserId(), used);
                vo.setUsedCouponTotal(cardUsedCouponTotal);
                RedPacketUseEnum draw = RedPacketUseEnum.DRAW;
                int availableRedPacketTotal = getRedPacketTotal(userInfoDTO.getUserId(), draw);
                RedPacketUseEnum redPackUsed = RedPacketUseEnum.USED;
                int usedRedPacketTotal = getRedPacketTotal(userInfoDTO.getUserId(), redPackUsed);
                vo.setAvailableRedPacketTotal(availableRedPacketTotal);
                vo.setUsedRedPacketTotal(usedRedPacketTotal);
                //vo.setTotalCredit();
                //vo.setCurrentCredit();
                //vo.setCurrentAmount();
            }
        }
    });
    }

    private int getRedPacketTotal(Long userId, RedPacketUseEnum draw) {
        RedPacketItemQueryParamDTO paramDTO = new RedPacketItemQueryParamDTO();
        paramDTO.setUserId(userId);
        paramDTO.setUseStatusEnum(draw);
        return redPacketReadFacade.countByParam(paramDTO);
    }

    private int getCardCouponTotal(Long userId, CardUseEnum toUse) {
        CouponItemQueryParamDTO paramDTO = new CouponItemQueryParamDTO();
        paramDTO.setUserId(userId);
        paramDTO.setUseStatusEnum(toUse);
        return cardReadFacade.countByParam(paramDTO);
    }

    private boolean isLongNull(Long memberId) {
        return null == memberId || 0 == memberId;
    }


    /**
     * 获取李子平台会员每天的统计轨迹
     * @return
     */
    @RequestMapping(value = "/listMemberCountConsumeReportByDate",method = RequestMethod.GET)
    @ApiOperation(value = "获取李子平台会员每天的统计轨迹",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<List<MemberCountConsumeReportVO>> listMemberCountConsumeReportByDate(
            @ApiParam(name = "startDate",value = "开始时间")
            @RequestParam(value = "startDate",required = false)
            @DateTimeFormat(pattern="yyyy-MM-dd")Date startDate,
            @ApiParam(name = "endDate",value = "结束时间")
            @RequestParam(value = "endDate",required = false)
            @DateTimeFormat(pattern="yyyy-MM-dd")Date endDate
    ){
        Result<List<MemberCountConsumeReportVO>> result;
        try {

            List<MemberCountConsumeReportDTO> reportDTOS = memberCountConsumeReportReadFacade.listByDate(MemberPlatformEnum.LIZI, startDate, endDate);
            if (null == reportDTOS){
                return Result.SUCESS(null);
            }
            List<MemberCountConsumeReportVO> reportVOS = ObjectConvertUtil.mapList(reportDTOS, MemberCountConsumeReportDTO.class, MemberCountConsumeReportVO.class);
            result = Result.SUCESS(reportVOS);
        }catch (Exception e){
            if (log.isErrorEnabled()) {
                log.error("listMemberCountConsumeReportByDate获取李子平台会员统计错误：", e);
            }

            result = Result.FAILURE("获取李子平台会员统计错误！");
        }

        return result;
    }

    /**
     * 按日期生成汇总统计历史
     * @return
     */
    @RequestMapping(value = "/genMemberCountConsumeReportEveryDay",method = RequestMethod.GET)
    @ApiOperation(value = "按日期生成汇总统计历史：如果endDate为空，就以今天为止",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @LoginExclude
    public Result<Boolean> genMemberCountConsumeReportEveryDay(
            @ApiParam(name = "startDate",value = "开始时间")
            @RequestParam(value = "startDate",required = false)
            @DateTimeFormat(pattern="yyyy-MM-dd")Date startDate,
            @ApiParam(name = "endDate",value = "结束时间")
            @RequestParam(value = "endDate",required = false)
            @DateTimeFormat(pattern="yyyy-MM-dd")Date endDate){
        Result<Boolean> result;
        try {
            if (null == startDate){
                throw new ReportingException(ReportingErrorEnum.PARAMETERS_ERROR, "传入的开始时间不能为空");
            }

            if (null == endDate){
                endDate = new Date();
            }
            memberCountConsumeReportWriteFacade.insertOrUpdateReportingEveryDay(MemberPlatformEnum.LIZI, startDate, endDate);
            result = Result.SUCESS(true);
        }catch (Exception e){
            if (log.isErrorEnabled()) {
                log.error("genMemberCountConsumeReportEveryDay获取李子平台会员统计错误：", e);
            }

            result = Result.FAILURE("获取李子平台会员统计错误！");
        }

        return result;
    }


    /**
     * 获取平台会员统计
     * @param reportCommonParam
     * @return
     */
    @RequestMapping(value = "/queryMemberReport",method = RequestMethod.GET)
    @ApiOperation(value = "获取商户会员统计",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<List<MerchantMemberReportVO>> queryMemberReport(@ModelAttribute ReportCommonParam reportCommonParam){
        if(reportCommonParam == null || reportCommonParam.getTarget() == null ){
            log.info("/report/member/queryMemberReport 参数为NUL.");
            return Result.FAILURE(String.valueOf(ReportingErrorEnum.NULL_PARAMETER_ERROR.getCode()),ReportingErrorEnum.NULL_PARAMETER_ERROR.getMessage());
        }
        if(reportCommonParam.getReportType() != null){
            DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(reportCommonParam.getReportType()),reportCommonParam.getReportDate());
            reportCommonParam.setEndDate(dateParam.getEndDate());
            reportCommonParam.setStartDate(dateParam.getStartDate());
        }
        List<MerchantMemberReportDTO> memberReportDTOS = new ArrayList<>();
        if(reportCommonParam.getTarget() == 2 || reportCommonParam.getTarget() == 3 ){
            memberReportDTOS = this.merchantMemberReportFacade.queryMemberReportByPeriod(ThreadLocalContext.getMerchantId(),reportCommonParam.getStartDate(),reportCommonParam.getEndDate());
        }

        return Result.SUCESS(ObjectConvertUtil.copyListProperties(memberReportDTOS,MerchantMemberReportVO.class));
    }

    @RequestMapping(value = "/getShopMemberReportStatistic",method = RequestMethod.GET)
    @ApiOperation(value = "获取会员消费统计（汇总））",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<ShopMemberReportStatisticVO> getShopMemberReportStatistic(@ModelAttribute ReportCommonParam reportCommonParam){
        Long shopId = ThreadLocalContext.getShopId();
        Long merchantId = ThreadLocalContext.getMerchantId();
        if(reportCommonParam.getReportType() != null){
            DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(reportCommonParam.getReportType()),reportCommonParam.getReportDate());
            reportCommonParam.setEndDate(dateParam.getEndDate());
            reportCommonParam.setStartDate(dateParam.getStartDate());
        }

        MerchantMemberReportDTO merchantMemberReportDTO = this.merchantMemberReportFacade.getMemberReportStatistic(merchantId,shopId,reportCommonParam.getStartDate(),reportCommonParam.getEndDate());

        return Result.SUCESS(ObjectConvertUtil.copyProperties(ShopMemberReportStatisticVO.class,merchantMemberReportDTO));
    }

    @RequestMapping(value = "/getPlatformMemberReportStatistic",method = RequestMethod.GET)
    @ApiOperation(value = "获取平台会员消费统计（汇总））",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<MemberStatisticsVO> getPlatformMemberReportStatistic(@ModelAttribute ReportCommonParam reportCommonParam) {
        if (reportCommonParam.getReportType() != null) {
            DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(reportCommonParam.getReportType()),reportCommonParam.getReportDate());
            reportCommonParam.setEndDate(dateParam.getEndDate());
            reportCommonParam.setStartDate(dateParam.getStartDate());
        }

        MemberStatisticsDTO memberStatisticsDTO = null;
        try {
            memberStatisticsDTO = this.memberReportFacade.getMemberStatistic(reportCommonParam.getStartDate(), reportCommonParam.getEndDate());
        } catch (Exception e) {
            e.printStackTrace();
            if(log.isInfoEnabled()){
                log.info("获取会员统计失败 {}",e);
            }
            memberStatisticsDTO = new MemberStatisticsDTO();
        }

        return Result.SUCESS(ObjectConvertUtil.copyProperties(MemberStatisticsVO.class, memberStatisticsDTO));
    }

    @RequestMapping(value = "/getMerchantMemberCurrentStatistic",method = RequestMethod.GET)
    @ApiOperation(value = "获取当前商户会员分布统计",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<MemberCurrentStatisticVO> getMerchantMemberCurrentStatistic() {
        Long merchantId = ThreadLocalContext.getMerchantId();

        MemberCurrentStatisticDTO memberStatisticsDTO = this.merchantMemberReportFacade.gerRealMemberCurrentStatistic(merchantId);
        MemberCurrentStatisticVO memberCurrentStatisticVO = ObjectConvertUtil.copyProperties(MemberCurrentStatisticVO.class, memberStatisticsDTO);
        if(memberStatisticsDTO != null){
            memberCurrentStatisticVO.setMemberCountRateVOS(ObjectConvertUtil.mapList(memberStatisticsDTO.getMemberCountRateDTOS(), MemberCountRateDTO.class, MemberCountRateVO.class));
        }

        return Result.SUCESS(memberCurrentStatisticVO);
    }

    @RequestMapping(value = "/queryMemberConsumeReport",method = RequestMethod.GET)
    @ApiOperation(value = "获取会员消费统计（维度：会员）",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<PageInfo<MemberConsumeReportVO>> queryMemberConsumeReport(
            @ApiParam(name = "mobile",value = "手机号")@RequestParam(value = "mobile",required = false)String mobile,
            @ApiParam(name = "merchantName",value = "商户名称")@RequestParam(value = "merchantName",required = false)String merchantName,
            @ApiParam(name = "startDate",value = "开始时间")@RequestParam(value = "startDate",required = false) @DateTimeFormat(pattern="yyyy-MM-dd")Date startDate,
            @ApiParam(name = "endDate",value = "结束时间")@RequestParam(value = "endDate",required = false)@DateTimeFormat(pattern="yyyy-MM-dd")Date endDate,
            @ApiParam(name = "pageNum",value = "页码")@RequestParam(value = "pageNum",defaultValue = "0")Integer pageNum,
            @ApiParam(name = "pageSize",value = "每页条数")@RequestParam(value = "pageSize",defaultValue = "0")Integer pageSize) {
        PageParameter pageParameter = new PageParameter(pageNum,pageSize);
        PageInfo<MemberConsumeReportDTO> consumeReportDTOS  = this.memberReportFacade.queryConsumeReportByMember(mobile,merchantName,startDate,endDate,pageParameter);
        PageInfo pageInfo = consumeReportDTOS;

        if (consumeReportDTOS != null && consumeReportDTOS.getList() != null) {
            pageInfo.setList(ObjectConvertUtil.mapList(consumeReportDTOS.getList(),MemberConsumeReportDTO.class,MemberConsumeReportVO.class));
        }

        return Result.SUCESS(pageInfo);
    }

    @RequestMapping(value = "/queryMemberConsumeInMerchant",method = RequestMethod.GET)
    @ApiOperation(value = "获取会员在商户的消费统计（维度：会员）",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<PageInfo<MemberConsumeReportVO>> queryMemberConsumeInMerchant(
            @ApiParam(name = "memberId",value = "会员ID")@RequestParam(value = "memberId",required = false)Long memberId,
            @ApiParam(name = "startDate",value = "开始时间")@RequestParam(value = "startDate",required = false) @DateTimeFormat(pattern="yyyy-MM-dd")Date startDate,
            @ApiParam(name = "endDate",value = "结束时间")@RequestParam(value = "endDate",required = false)@DateTimeFormat(pattern="yyyy-MM-dd")Date endDate,
            @ApiParam(name = "pageNum",value = "页码")@RequestParam(value = "pageNum",defaultValue = "0")Integer pageNum,
            @ApiParam(name = "pageSize",value = "每页条数")@RequestParam(value = "pageSize",defaultValue = "0")Integer pageSize) {
        PageParameter pageParameter = new PageParameter(pageNum,pageSize);
        PageInfo<MemberConsumeReportDTO> consumeReportDTOS  = this.memberReportFacade.queryConsumeReportByMerchant(memberId,startDate,endDate,pageParameter);
        PageInfo pageInfo = consumeReportDTOS;

        this.fillReportWithMerchantName(consumeReportDTOS.getList());
        if (consumeReportDTOS != null && consumeReportDTOS.getList() != null) {
            pageInfo.setList(ObjectConvertUtil.mapList(consumeReportDTOS.getList(),MemberConsumeReportDTO.class,MemberConsumeReportVO.class));
        }

        return Result.SUCESS(pageInfo);
    }

    @RequestMapping(value = "/getMemberRecharge",method = RequestMethod.GET)
    @ApiOperation(value = "获取会员充值统计",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<MerchantMemberFlowStatisticVO> getMemberRecharge( @ApiParam(name = "reportType",value = "统计类型 8000 小时 8100 日 8200 月 8300 年 8400 当天 8500 近七天（不包括当天） 8600 近30天（不包括当天）9999 全部") @RequestParam("reportType")Integer reportType,
                                             @ApiParam(name = "reportDate",value = "统计日期 YYYY-MM-DD") @RequestParam(value = "reportDate",required = false)String reportDate) {
        MemberFlowQueryDTO queryDTO = new MemberFlowQueryDTO();
        Long shopId = ThreadLocalContext.getShopId();
        if(shopId != null && shopId != 0){
            queryDTO.setShopId(shopId);
        }
        queryDTO.setMerchantId(ThreadLocalContext.getMerchantId());
        DateParam dateParam = ReportUtil.getDateParam(ReportingTypeEnum.getEnumByCode(reportType),reportDate);
        queryDTO.setStartTime(dateParam.getStartDate());
        queryDTO.setEndTime(dateParam.getEndDate());
        MerchantMemberFlowStatisticDTO flowStatistic = this.merchantMemberReportFacade.getRealTimeFlowStatistic(queryDTO);
        MerchantMemberFlowStatisticVO statisticVO = null;
        if(flowStatistic != null){
            statisticVO = ObjectConvertUtil.map(flowStatistic,MerchantMemberFlowStatisticVO.class);
        }

        return Result.SUCESS(statisticVO);
    }

    private void fillReportWithMerchantName(final List<MemberConsumeReportDTO> memberConsumeReportDTOS){
        if(memberConsumeReportDTOS == null){
            return;
        }
        memberConsumeReportDTOS.forEach(t -> {
            ShopMerchantDTO shopMerchantDTO = this.shopMerchantReadFacade.findById(t.getMerchantId());
            if(shopMerchantDTO != null){
                t.setMerchantName(shopMerchantDTO.getMerchantName());
            }
        });
    }
}
