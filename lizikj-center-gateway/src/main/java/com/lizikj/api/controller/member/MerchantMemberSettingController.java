package com.lizikj.api.controller.member;

import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.member.MerchantMemberCreditSettingVO;
import com.lizikj.api.vo.member.MerchantMemberDiscountSettingVO;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.login.interceptor.LoginExclude;
import com.lizikj.member.dto.MerchantMemberCreditSettingDTO;
import com.lizikj.member.dto.MerchantMemberDiscountSettingDTO;
import com.lizikj.member.enums.MemberActionTypeEnum;
import com.lizikj.member.enums.MemberDiscountTypeEnum;
import com.lizikj.member.enums.MemberErrorEnum;
import com.lizikj.member.exception.MemberException;
import com.lizikj.member.facade.IMemberSettingFacade;
import com.lizikj.member.facade.IMerchantMemberAccountFacade;
import com.lizikj.merchant.facade.IShopMerchantReadFacade;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by liangxiaolin on 2017/8/8.
 */
@RequestMapping(value = "/member/setting")
@RestController
@Api(value = "member_setting",tags = "商户会员积分设置接口")
public class MerchantMemberSettingController {
    private final static Logger log = LoggerFactory.getLogger(MerchantMemberSettingController.class);

    @Autowired
    IMerchantMemberAccountFacade merchantMemberAccountFacade;
    @Autowired
    IMemberSettingFacade memberSettingFacade;
    @Autowired
    IShopMerchantReadFacade shopMerchantReadFacade;

    /**
     * 新增商户会员积分规则
     * @param merchantMemberCreditSettingVO
     * @return
     */
    @RequestMapping(value = "/addMemberCreditSetting",method = RequestMethod.POST)
    @ApiOperation(value = "新增商户会员积分规则", notes = "新增商户会员积分规则",hidden = true,httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> addMerchantMemberCreditSetting(@RequestBody MerchantMemberCreditSettingVO merchantMemberCreditSettingVO){
        log.debug("/member/setting/addMemberCreditSetting {}", merchantMemberCreditSettingVO);
        if(merchantMemberCreditSettingVO == null || merchantMemberCreditSettingVO.getSourceValue() == null || merchantMemberCreditSettingVO.getTargetValue() == null){
            log.info(String.format("新增商户会员积分规则参数为空"));
            return Result.FAILURE(MemberErrorEnum.MEMBER_MUST_PARAM_NOT_EXIST_ERROR.getCode(),MemberErrorEnum.MEMBER_MUST_PARAM_NOT_EXIST_ERROR.getMsg());
        }
        this.merchantMemberAccountFacade.addMemberCreditSetting(ObjectConvertUtil.copyProperties(MerchantMemberCreditSettingDTO.class, merchantMemberCreditSettingVO));

        return Result.SUCESS();
    }

    /**
     * 获取商户会员积分规则
     * @param creditType
     * @return
     */
    @RequestMapping(value = "/getMemberCreditSetting",method = RequestMethod.GET)
    @ApiOperation(value = "获取商户会员积分规则", notes = "获取商户会员积分规则",httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<MerchantMemberCreditSettingVO> getMerchantMemberCreditSetting(
            @ApiParam(value = "积分规则类型 2100充值积分 2300 下单消费积分",example = "2300",name = "creditType",required = true)@RequestParam("creditType")Integer creditType){
        Long merchantId = ThreadLocalContext.getMerchantId();
        log.debug("/member/setting/getMemberCreditSetting {} {}",merchantId,creditType);
        MerchantMemberCreditSettingDTO memberCreditSettingDTO = this.merchantMemberAccountFacade.getMerchantCreditSetting(merchantId,creditType);
        if(memberCreditSettingDTO == null){
            log.info(String.format("商户没有设置积分规则 merchantId=%s creditType=%s",merchantId,creditType));
            return Result.FAILURE(MemberErrorEnum.MERCHANT_NO_CREDIT_SETTING.getCode(),MemberErrorEnum.MERCHANT_NO_CREDIT_SETTING.getMsg());
        }
        return Result.SUCESS(ObjectConvertUtil.copyProperties(MerchantMemberCreditSettingVO.class,memberCreditSettingDTO));
    }

    @RequestMapping(value = "/updateMemberCreditSetting",method = RequestMethod.POST)
    @ApiOperation(value = "更新商户会员积分规则", notes = "获取商户会员积分规则", hidden = true,httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> updateMerchantMemberCreditSetting(@RequestBody MerchantMemberCreditSettingVO merchantMemberCreditSettingVO){
        log.debug("/member/setting/updateMemberCreditSetting {}", merchantMemberCreditSettingVO);
        if(merchantMemberCreditSettingVO == null || merchantMemberCreditSettingVO.getSettingId() == null){
            log.info("/member/setting/updateMemberCreditSetting 参数为空.");
            return Result.FAILURE(MemberErrorEnum.MEMBER_MUST_PARAM_NOT_EXIST_ERROR.getCode(),MemberErrorEnum.MEMBER_MUST_PARAM_NOT_EXIST_ERROR.getMsg());
        }
        MerchantMemberCreditSettingDTO memberCreditSettingDTO = ObjectConvertUtil.copyProperties(MerchantMemberCreditSettingDTO.class, merchantMemberCreditSettingVO);
        this.merchantMemberAccountFacade.updateMemberCreditSetting(memberCreditSettingDTO);

        return Result.SUCESS();
    }

    @RequestMapping(value = "bindMemberCreditSetting",method = RequestMethod.POST)
    @ApiOperation(value = "绑定商户会员积分规则", notes = "绑定商户会员积分规则", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object>  bindMemberCreditSetting(@RequestBody MerchantMemberCreditSettingVO merchantMemberCreditSettingVO){
        log.debug("/member/setting/bindMemberCreditSetting {}", merchantMemberCreditSettingVO);

        if(merchantMemberCreditSettingVO == null || merchantMemberCreditSettingVO.getTargetValue() == null || merchantMemberCreditSettingVO.getSourceValue() == null
                ||merchantMemberCreditSettingVO.getSourceValue() == 0 || merchantMemberCreditSettingVO.getTargetValue() == 0){
            log.info("/member/setting/bindMemberCreditSetting {} 参数为空", merchantMemberCreditSettingVO);
            return Result.FAILURE(MemberErrorEnum.MEMBER_WRONG_PARAM_ERROR.getCode(),MemberErrorEnum.MEMBER_WRONG_PARAM_ERROR.getMsg());
        }

        merchantMemberCreditSettingVO.setMerchantId(ThreadLocalContext.getMerchantId());
        this.merchantMemberAccountFacade.bindMemberCreditSetting(ObjectConvertUtil.copyProperties(MerchantMemberCreditSettingDTO.class, merchantMemberCreditSettingVO));

        return Result.SUCESS();
    }

    @RequestMapping(value = "bindMerchantMemberDiscountSetting",method = RequestMethod.POST)
    @ApiOperation(value = "设置商户会员优惠方式",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<Object> bindMerchantMemberDiscountSetting(@RequestBody MerchantMemberDiscountSettingVO merchantMemberDiscountSettingVO){
        Long merchantId = ThreadLocalContext.getMerchantId();
        MerchantMemberDiscountSettingDTO merchantMemberDiscountSettingDTO = new MerchantMemberDiscountSettingDTO();
        merchantMemberDiscountSettingDTO.setMemberDiscountTypeEnum(merchantMemberDiscountSettingVO.getMemberDiscountTypeEnum());
        merchantMemberDiscountSettingDTO.setMerchantId(merchantId);
        merchantMemberDiscountSettingDTO.setSettingId(merchantMemberDiscountSettingVO.getSettingId());
        try{
            this.memberSettingFacade.addOrUpdateMerchantMemberDiscount(merchantMemberDiscountSettingDTO);
        }catch (MemberException e){
            return Result.FAILURE(e.getCode(),e.getMessage());
        }

        return Result.SUCESS();
    }


    @RequestMapping(value = "getMerchantMemberDiscountSetting",method = RequestMethod.GET)
    @ApiOperation(value = "获取商户会员优惠方式",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<MerchantMemberDiscountSettingVO> bindMerchantMemberDiscountSetting(){
        Long merchantId = ThreadLocalContext.getMerchantId();
        MerchantMemberDiscountSettingDTO merchantMemberDiscountSettingDTO = this.memberSettingFacade.findDiscountSettingByMerchant(merchantId);
        if(merchantMemberDiscountSettingDTO != null){
            MerchantMemberDiscountSettingVO merchantMemberDiscountSettingVO = ObjectConvertUtil.map(merchantMemberDiscountSettingDTO,MerchantMemberDiscountSettingVO.class);
            return Result.SUCESS(merchantMemberDiscountSettingVO);
        }

        MerchantMemberDiscountSettingVO defaultSetting = new MerchantMemberDiscountSettingVO();
        defaultSetting.setMemberDiscountTypeEnum(MemberDiscountTypeEnum.NONE_DISCOUNT);
        defaultSetting.setMerchantId(merchantId);

        return Result.SUCESS(defaultSetting);
    }


    @RequestMapping(value = "/initMerchantMemberSetting",method = RequestMethod.GET)
    @ApiOperation(value = "初始化商户会员数据", notes = "初始化商户会员数据", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> initMerchantMemberSetting(){

        return Result.FAILURE();
    }
}
