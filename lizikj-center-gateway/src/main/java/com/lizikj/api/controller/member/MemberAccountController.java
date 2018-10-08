package com.lizikj.api.controller.member;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.lizikj.api.enums.ReportTargetEnum;
import com.lizikj.api.vo.PageVO;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.member.MemberConsumeFlowVO;
import com.lizikj.api.vo.member.MerchantMemberAccountFlowDetailVO;
import com.lizikj.api.vo.member.param.MerchantMemberFlowQueryVO;
import com.lizikj.api.vo.pay.PayFlowVO;
import com.lizikj.common.enums.ArgumentCheckErrorEnum;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.PageParameter;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.member.dto.BalancePayCodeDTO;
import com.lizikj.member.dto.MerchantMemberAccountFlowDetailDTO;
import com.lizikj.member.dto.MerchantMemberDTO;
import com.lizikj.member.dto.MerchantMemberFlowQueryDTO;
import com.lizikj.member.exception.MemberException;
import com.lizikj.member.facade.IMerchantMemberAccountFacade;
import com.lizikj.member.facade.IMerchantMemberFacade;
import com.lizikj.order.dto.OrderInfoDTO;
import com.lizikj.order.dto.param.query.OrderInfoQueryParamDTO;
import com.lizikj.order.enums.OrderBizTypeEnum;
import com.lizikj.order.enums.OrderStatusEnum;
import com.lizikj.order.exception.OrderException;
import com.lizikj.order.facade.IOrderReadFacade;
import com.lizikj.payment.facade.IPaymentReadFacade;
import com.lizikj.payment.pay.dto.PayFlowDTO;
import com.lizikj.payment.pay.dto.PayReportQueryDTO;
import com.lizikj.payment.pay.enums.PayStatusEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * Created by liangxiaolin on 2017/8/5.
 */
@RestController
@RequestMapping("/member/account")
@Api(value = "member_account", tags = "会员账号API接口")
public class MemberAccountController {
    private final static Logger log = LoggerFactory.getLogger(MemberAccountController.class);
    @Autowired
    IMerchantMemberAccountFacade merchantMemberAccountFacade;
    @Autowired
    IMerchantMemberFacade merchantMemberFacade;
    @Autowired
    IOrderReadFacade orderReadFacade;
    @Autowired
    IPaymentReadFacade paymentReadFacade;


    @RequestMapping(value = "/getMerchantMemberPayCode",method = RequestMethod.GET)
    @ApiOperation(value = "获取会员支付码",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<Object> getMerchantMemberPayCode(@ApiParam(name = "merchantMemberId",value = "会员ID",required = true)@RequestParam(value = "merchantMemberId") Long merchantMemberId,
                                                   @ApiParam(name = "mobile",value = "手机号",required = true)@RequestParam(value = "mobile") String mobile,
                                                   @ApiParam(name = "orderNo",value = "订单编号",required = true)@RequestParam(value = "orderNo") String orderNo,
                                                   @ApiParam(name = "payAmount",value = "支付金额",required = true)@RequestParam(value = "payAmount") Long payAmount){
        BalancePayCodeDTO balancePayCodeDTO = new BalancePayCodeDTO();
        balancePayCodeDTO.setMobile(mobile);
        balancePayCodeDTO.setOrderNo(orderNo);
        balancePayCodeDTO.setMerchantMemberId(merchantMemberId);
        balancePayCodeDTO.setPayAmount(payAmount);
        String s = this.merchantMemberAccountFacade.sendBalancePayCode(balancePayCodeDTO);
        boolean succeed = StringUtils.isBlank(s);
        if(succeed){
            return Result.FAILURE("发送支付码失败");
        }

        return Result.SUCESS(succeed);
    }


    @RequestMapping(value = "/checkMerchantMemberPayCode",method = RequestMethod.GET)
    @ApiOperation(value = "获取会员支付码",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<Object> checkMerchantMemberPayCode(@ApiParam(name = "merchantMemberId",value = "会员ID",required = true)@RequestParam(value = "merchantMemberId") Long merchantMemberId,
                                                   @ApiParam(name = "payCode",value = "支付验证码",required = true)@RequestParam(value = "payCode") String payCode,
                                                   @ApiParam(name = "orderNo",value = "订单编号",required = true)@RequestParam(value = "orderNo") String orderNo){
        boolean validPayCode = this.merchantMemberAccountFacade.checkValidPayCode(orderNo,merchantMemberId,payCode);
        if(!validPayCode){
            return Result.FAILURE("验证失败");
        }
        return Result.SUCESS(validPayCode);
    }

    /**
     * 获取会员流水记录（包括充值、消费、积分记录）
     * APP端-会员信息页
     * @param merchantMemberFlowQueryVO
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @return
     */
    @RequestMapping(value = "/queryMemberAccountFlowRecords",method = RequestMethod.POST)
    @ApiOperation(value = "获取会员流水记录（包括充值、消费、积分记录）",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<PageVO<MerchantMemberAccountFlowDetailVO>> queryMemberAccountFlowRecords(
            @RequestBody MerchantMemberFlowQueryVO merchantMemberFlowQueryVO,
            @ApiParam(name = "targetEnum",value = "查询目标类型（商户、平台、店铺）") @RequestParam(value = "targetEnum",required = false) ReportTargetEnum targetEnum,
            @ApiParam(name = "pageNum",value = "页码",defaultValue = "1") @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
            @ApiParam(name = "pageSize",value = "每页数量",defaultValue = "25") @RequestParam(value = "pageSize",defaultValue = "25",required = false) int pageSize,
            @ApiParam(name = "orderBy",value = "排序字段") @RequestParam(value = "orderBy",required = false) String orderBy){
        log.info("/member/queryMemberAccountFlowRecords {}", merchantMemberFlowQueryVO);

        PageParameter pageParameter = new PageParameter(pageNum,pageSize);
        pageParameter.setOrderBy(orderBy);
        MerchantMemberFlowQueryDTO queryDTO = ObjectConvertUtil.copyProperties(MerchantMemberFlowQueryDTO.class, merchantMemberFlowQueryVO);
        if(queryDTO == null){
            queryDTO = new MerchantMemberFlowQueryDTO();
        }
        if (targetEnum != ReportTargetEnum.PLATFORM) {
            queryDTO.setMerchantId(ThreadLocalContext.getMerchantId());
            Long shopId = ThreadLocalContext.getShopId();
            if (shopId != null && shopId != 0) {
                queryDTO.setShopId(shopId);
            }
        }
        PageInfo<MerchantMemberAccountFlowDetailDTO> memberAccountFlowDetailDTOPageInfo = this.merchantMemberAccountFacade.getFlowRecords(queryDTO,pageParameter);
        PageVO pageInfo = new PageVO(memberAccountFlowDetailDTOPageInfo.getPageNum(),memberAccountFlowDetailDTOPageInfo.getPageSize(),
                memberAccountFlowDetailDTOPageInfo.getPages(),memberAccountFlowDetailDTOPageInfo.getTotal());

        List<MerchantMemberAccountFlowDetailVO> flowDetailVOS = buildFlowDetailVOList(memberAccountFlowDetailDTOPageInfo);


        pageInfo.setList(flowDetailVOS);

        return Result.SUCESS(pageInfo);
    }


    @RequestMapping(value = "/v2/queryMemberConsumeFlowRecords",method = RequestMethod.GET)
    @ApiOperation(value = "获取会员消费交易流水记录",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<PageVO<MemberConsumeFlowVO>> queryMemberConsumeFlowRecords_v2(
            @ApiParam(name = "merchantMemberId",value = "会员ID",required = true)@RequestParam(value = "merchantMemberId",required = true) Long merchantMemberId,
            @ApiParam(name = "pageNum",value = "页码",defaultValue = "1") @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
            @ApiParam(name = "pageSize",value = "每页数量",defaultValue = "25") @RequestParam(value = "pageSize",defaultValue = "25",required = false) int pageSize,
            @ApiParam(name = "orderBy",value = "排序字段") @RequestParam(value = "orderBy",required = false) String orderBy){
        Long shopId = ThreadLocalContext.getShopId();
        Long merchantId = ThreadLocalContext.getMerchantId();
        log.info(String.format("/member/queryMemberConsumeFlowRecords merchantMemberId=%s shopId=%s ",merchantMemberId,shopId));
        OrderInfoQueryParamDTO queryParamDTO = new OrderInfoQueryParamDTO();
        if(merchantId != null && merchantId != 0){
            queryParamDTO.setMerchantId(merchantId);
        }
        if(shopId != null && shopId != 0){
            queryParamDTO.setShopId(shopId);
        }
        queryParamDTO.setMerchantMemberId(merchantMemberId);
        List<OrderBizTypeEnum> bizTypes = new ArrayList<>();
        bizTypes.add(OrderBizTypeEnum.MONEY);
        bizTypes.add(OrderBizTypeEnum.MERCHANDISE);
        queryParamDTO.setOrderBizTypes(bizTypes);
        List<OrderStatusEnum> orderStatusEnums = new ArrayList<>();
        orderStatusEnums.add(OrderStatusEnum.REFUND);
        orderStatusEnums.add(OrderStatusEnum.FINISHED);
        queryParamDTO.setOrderStatusList(orderStatusEnums);
        PageInfo<OrderInfoDTO> pageInfo = null;
        try {
            pageInfo = orderReadFacade.query(queryParamDTO, pageNum, pageSize);
        } catch (OrderException e) {
            log.info("订单查询失败 {} {}",queryParamDTO,e);
            return Result.FAILURE("订单查询失败");
        }

        PageVO pageVO = new PageVO(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getPages(),pageInfo.getTotal());
        List<MemberConsumeFlowVO> consumeFlowVOS = new ArrayList<>();
        if(pageInfo.getList() != null){
            pageInfo.getList().forEach(t -> {
                MemberConsumeFlowVO flowVO = new MemberConsumeFlowVO();
                flowVO.setMerchantMemberId(t.getMerchantMemberId());
                flowVO.setMemberId(t.getMemberId());
                flowVO.setMobile(t.getMobile());
                flowVO.setNeedAmount(t.getNeedAmount());
                flowVO.setOrderTime(t.getOrderTime());
                flowVO.setOrderId(t.getOrderId());
                flowVO.setOrderNo(t.getOrderNo());
                flowVO.setPayAmount(t.getPayAmount());
                flowVO.setOrderStatusEnum(t.getOrderStatus());
                consumeFlowVOS.add(flowVO);
            });
        }

        pageVO.setList(consumeFlowVOS);
        return Result.SUCESS(pageVO);
    }

    /**
     * 获取会员消费记录
     * APP端-会员信息
     * @param merchantMemberId
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @return
     */
    @RequestMapping(value = "/queryMemberConsumeFlowRecords",method = RequestMethod.GET)
    @ApiOperation(value = "获取会员消费交易流水记录",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<PageVO<PayFlowVO>> queryMemberConsumeFlowRecords(
            @ApiParam(name = "merchantMemberId",value = "会员ID",required = true)@RequestParam(value = "merchantMemberId",required = true) Long merchantMemberId,
            @ApiParam(name = "pageNum",value = "页码",defaultValue = "1") @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
            @ApiParam(name = "pageSize",value = "每页数量",defaultValue = "25") @RequestParam(value = "pageSize",defaultValue = "25",required = false) int pageSize,
            @ApiParam(name = "orderBy",value = "排序字段") @RequestParam(value = "orderBy",required = false) String orderBy){
        Long shopId = ThreadLocalContext.getShopId();
        Long merchantId = ThreadLocalContext.getMerchantId();
        log.info(String.format("/member/queryMemberConsumeFlowRecords merchantMemberId=%s shopId=%s ",merchantMemberId,shopId));
        PageParameter pageParameter = new PageParameter(pageNum,pageSize);
        pageParameter.setOrderBy(orderBy);

        PayReportQueryDTO payReportQueryDTO = new PayReportQueryDTO();
        List<Byte> bizTypes = new ArrayList<>();
        bizTypes.add(OrderBizTypeEnum.MONEY.getBizType());
        bizTypes.add(OrderBizTypeEnum.MERCHANDISE.getBizType());
        payReportQueryDTO.setOrderBizTypeList(bizTypes);
        if(merchantId != null && merchantId != 0)
            payReportQueryDTO.setMerchantId(merchantId);
        if(shopId != null && shopId != 0)
            payReportQueryDTO.setShopId(shopId);
        payReportQueryDTO.setMerchantMemberId(merchantMemberId);
        payReportQueryDTO.setPayStatusEnum(PayStatusEnum.PAY_SUCCESS);
        PageInfo<PayFlowDTO> pageInfo = this.paymentReadFacade.listPayFlowByReport(pageNum, pageSize, payReportQueryDTO);
        PageVO pageVO = new PageVO(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getPages(),pageInfo.getTotal());
        if(pageInfo.getList() != null){
            pageVO.setList(ObjectConvertUtil.mapList(pageInfo.getList(),PayFlowDTO.class,PayFlowVO.class));
        }

        return Result.SUCESS(pageVO);
    }

    /**
     * 获取会员积分消费记录
     * @param merchantMemberId
     * @param allRecords
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @return
     */
    @RequestMapping(value = "/queryMemberCreditFlowRecords",method = RequestMethod.GET)
    @ApiOperation(value = "获取会员积分记录",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<PageVO<MerchantMemberAccountFlowDetailVO>> queryMemberCreditFlowRecords(
            @ApiParam(name = "merchantMemberId",value = "会员ID",required = true)@RequestParam(value = "merchantMemberId",required = true) Long merchantMemberId,
            @ApiParam(name = "allRecords",value = "是否查询所有店铺(1 是 其他否) ")@RequestParam(value = "allRecords",required = false,defaultValue = "0") Integer allRecords,
            @ApiParam(name = "pageNum",value = "页码",defaultValue = "1") @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
            @ApiParam(name = "pageSize",value = "每页数量",defaultValue = "25") @RequestParam(value = "pageSize",defaultValue = "25",required = false) int pageSize,
            @ApiParam(name = "orderBy",value = "排序字段") @RequestParam(value = "orderBy",required = false) String orderBy){
        Long shopId = ThreadLocalContext.getShopId();
        log.info(String.format("/member/queryMemberCreditFlowRecords merchantMemberId=%s shopId=%s allRecords=%s",merchantMemberId,shopId,allRecords));
        if(allRecords == 1){
            shopId = null;
        }
        PageParameter pageParameter = new PageParameter(pageNum,pageSize);
        pageParameter.setOrderBy(orderBy);
        PageInfo<MerchantMemberAccountFlowDetailDTO> memberAccountFlowDetailDTOPageInfo = this.merchantMemberAccountFacade.getCreditList(merchantMemberId,shopId,pageParameter);
        PageVO pageInfo = new PageVO(memberAccountFlowDetailDTOPageInfo.getPageNum(),memberAccountFlowDetailDTOPageInfo.getPageSize(),
                memberAccountFlowDetailDTOPageInfo.getPages(),memberAccountFlowDetailDTOPageInfo.getTotal());
        pageInfo.setList(ObjectConvertUtil.copyListProperties(memberAccountFlowDetailDTOPageInfo.getList(),MerchantMemberAccountFlowDetailVO.class));

        return Result.SUCESS(pageInfo);
    }

    /**
     * 获取会员充值记录
     * @param merchantMemberId
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @return
     */
    @RequestMapping(value = "/queryMemberRechargeFlowRecords",method = RequestMethod.GET)
    @ApiOperation(value = "获取会员充值记录",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<PageVO<MerchantMemberAccountFlowDetailVO>> queryMemberRechargeFlowRecords(
            @ApiParam(name = "merchantMemberId",value = "会员ID",required = true)@RequestParam(value = "merchantMemberId",required = true) Long merchantMemberId,
            @ApiParam(name = "pageNum",value = "页码",defaultValue = "1") @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
            @ApiParam(name = "pageSize",value = "每页数量",defaultValue = "25") @RequestParam(value = "pageSize",defaultValue = "25",required = false) int pageSize,
            @ApiParam(name = "orderBy",value = "排序字段") @RequestParam(value = "orderBy",required = false) String orderBy){
        log.info(String.format("/member/queryMemberRechargeFlowRecords merchantMemberId=%s  ",merchantMemberId));
        PageParameter pageParameter = new PageParameter(pageNum,pageSize);
        pageParameter.setOrderBy(orderBy);
        PageInfo<MerchantMemberAccountFlowDetailDTO> memberAccountFlowDetailDTOPageInfo = this.merchantMemberAccountFacade.getRechargeList(merchantMemberId,pageParameter);
        PageVO pageInfo = new PageVO(memberAccountFlowDetailDTOPageInfo.getPageNum(),memberAccountFlowDetailDTOPageInfo.getPageSize(),
                memberAccountFlowDetailDTOPageInfo.getPages(),memberAccountFlowDetailDTOPageInfo.getTotal());
        pageInfo.setList(this.buildFlowDetailVOList(memberAccountFlowDetailDTOPageInfo));

        return Result.SUCESS(pageInfo);
    }

    @RequestMapping(value = "/queryShopCreditExchangeRecords",method = RequestMethod.GET)
    @ApiOperation(value = "获取店铺积分兑换记录",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<PageVO<MerchantMemberAccountFlowDetailVO>> queryShopCreditExchangeRecords(
            @ApiParam(name = "pageNum",value = "页码",defaultValue = "1") @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
            @ApiParam(name = "pageSize",value = "每页数量",defaultValue = "25") @RequestParam(value = "pageSize",defaultValue = "25",required = false) int pageSize,
            @ApiParam(name = "orderBy",value = "排序字段") @RequestParam(value = "orderBy",required = false) String orderBy){
        Long shopId = ThreadLocalContext.getShopId();
        log.info(String.format("/member/queryShopCreditExchangeRecords shopId=%s  ",shopId));
        PageParameter pageParameter = new PageParameter(pageNum,pageSize);
        pageParameter.setOrderBy(orderBy);
        //TODO
        PageInfo<MerchantMemberAccountFlowDetailDTO> memberAccountFlowDetailDTOPageInfo = this.merchantMemberAccountFacade.getShopCreditList(shopId,pageParameter);
        PageVO pageInfo = new PageVO(memberAccountFlowDetailDTOPageInfo.getPageNum(),memberAccountFlowDetailDTOPageInfo.getPageSize(),
                memberAccountFlowDetailDTOPageInfo.getPages(),memberAccountFlowDetailDTOPageInfo.getTotal());
        pageInfo.setList(ObjectConvertUtil.copyListProperties(memberAccountFlowDetailDTOPageInfo.getList(),MerchantMemberAccountFlowDetailVO.class));

        return Result.SUCESS(pageInfo);
    }

    @RequestMapping(value = "/exchangeMemberCredit",method = RequestMethod.POST)
    @ApiOperation(value = "店铺兑换会员积分（根据产品原型，兑换金额操作由线下处理）",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<Object> exchangeMemberCredit(
            @ApiParam(name = "merchantMemberId",value = "会员ID",required = true)@RequestParam(value = "merchantMemberId",required = true) Long merchantMemberId,
            @ApiParam(name = "credit",value = "兑换积分",required = true)@RequestParam(value = "credit",required = true)Long credit){
        Long shopId = ThreadLocalContext.getShopId();
        Long userId = ThreadLocalContext.getUserId();
        if(credit <= 0){
            log.info("/member/exchangeMemberCredit 兑换积分小于零");
            return Result.FAILURE(ArgumentCheckErrorEnum.INVALIDE_ARGUMENT.getCode(),ArgumentCheckErrorEnum.INVALIDE_ARGUMENT.getMessage());
        }
        try{
            this.merchantMemberAccountFacade.exchangeMemberCreditOffline(userId,shopId,merchantMemberId,credit);
        }catch (MemberException e){
            log.info("/member/exchangeMemberCredit failed {}",e);
            return Result.FAILURE(e.getCode(),e.getMessage());
        }

        return Result.SUCESS();
    }

    /**
     * 流水回填会员信息
     * @param memberAccountFlowDetailDTOPageInfo
     * @return
     */
    private List<MerchantMemberAccountFlowDetailVO> buildFlowDetailVOList(PageInfo<MerchantMemberAccountFlowDetailDTO> memberAccountFlowDetailDTOPageInfo) {
        List<MerchantMemberAccountFlowDetailVO> flowDetailVOS = new ArrayList<>();
        if(memberAccountFlowDetailDTOPageInfo.getList() != null){
            memberAccountFlowDetailDTOPageInfo.getList().forEach(dto ->{
                MerchantMemberAccountFlowDetailVO detailVO = ObjectConvertUtil.map(dto,MerchantMemberAccountFlowDetailVO.class);
                MerchantMemberDTO memberDTO = this.merchantMemberFacade.getById(dto.getMerchantMemberId());
                if(memberDTO != null){
                    detailVO.setRealName(memberDTO.getRealName());
                    detailVO.setMemberNum(memberDTO.getMemberNum());
                }
                flowDetailVOS.add(detailVO);
            });
        }
        return flowDetailVOS;
    }
}
