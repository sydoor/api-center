package com.lizikj.api.controller.member;

import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.member.MerchantMemberLabelRelVO;
import com.lizikj.api.vo.member.MerchantMemberLabelVO;
import com.lizikj.common.enums.ArgumentCheckErrorEnum;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.member.dto.MerchantMemberLabelDTO;
import com.lizikj.member.dto.MerchantMemberLabelRelDTO;
import com.lizikj.member.enums.MemberErrorEnum;
import com.lizikj.member.exception.MemberException;
import com.lizikj.member.facade.IMerchantMemberLabelFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liangxiaolin on 2017/8/8.
 */
@RestController
@RequestMapping(value = "/member/label")
@Api(value = "member_label",tags = "会员标签API接口")
public class MemberLabelController {
    private final static Logger log = LoggerFactory.getLogger(MemberLabelController.class);

    @Autowired
    private IMerchantMemberLabelFacade merchantMemberLabelFacade;

    /**
     * 获取会员标签列表
     * @param merchantMemberId
     * @return
     */
    @RequestMapping(value = "/queryMemberLabel/{merchantMemberId}",method = RequestMethod.GET)
    @ApiOperation(value = "获取会员标签列表",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<List<MerchantMemberLabelRelVO>> queryLabelsByMemberId(@ApiParam(name = "merchantMemberId",value = "会员ID",required = true)@PathVariable Long merchantMemberId){
        log.info(String.format("/member/label/queryMemberLabel/%s",merchantMemberId));
        List<MerchantMemberLabelRelDTO> memberLabelRelDTOS = this.merchantMemberLabelFacade.getMemberLabelList(merchantMemberId);
        if(memberLabelRelDTOS == null){
            log.info("/member/label/queryMemberLabel 会员标签为空.");
            return Result.SUCESS(new ArrayList<>());
        }

        return Result.SUCESS(ObjectConvertUtil.copyListProperties(memberLabelRelDTOS,MerchantMemberLabelRelVO.class));
    }

    /**
     * 获取商户所有会员标签列表
     * @return
     */
    @RequestMapping(value = "/queryMerchantLabel",method = RequestMethod.GET)
    @ApiOperation(value = "获取商户所有会员标签列表",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<List<MerchantMemberLabelVO>> queryLabelsByMerchantId(){
        Long merchantId = ThreadLocalContext.getMerchantId();
        log.info(String.format("/member/label/queryMerchantLabel/%s",merchantId));
        List<MerchantMemberLabelDTO> memberLabelDTOS = this.merchantMemberLabelFacade.getMemberLabelListByMerchantId(merchantId);
        if(memberLabelDTOS == null){
            log.info("/member/label/queryMerchantLabel 会员标签为空.");
            return Result.SUCESS(new ArrayList<>());
        }

        return Result.SUCESS(ObjectConvertUtil.copyListProperties(memberLabelDTOS,MerchantMemberLabelDTO.class));
    }

    /**
     * 会员删除标签
     * @param relIds
     * @return
     */
    @RequestMapping(value = "/detachMemberLabels",method = RequestMethod.POST)
    @ApiOperation(value = "会员删除标签",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<Object> detachMemberLabels(@RequestBody List<Long> relIds){
        log.debug(String.format("/member/label/detachMemberLabels %s",relIds));
        if(relIds == null){
            log.info(String.format("会员标签关系ID字符串 不能为空"));
            return Result.FAILURE(MemberErrorEnum.MEMBER_MUST_PARAM_NOT_EXIST_ERROR.getCode(),MemberErrorEnum.MEMBER_MUST_PARAM_NOT_EXIST_ERROR.getMsg());
        }

        this.merchantMemberLabelFacade.detachMemberLabel(relIds);
        return Result.SUCESS();
    }

    @RequestMapping(value = "/removeMerchantMemberLabel/{labelId}",method = RequestMethod.POST)
    @ApiOperation(value = "商户删除会员标签",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<Object> removeMerchantMemberLabel(@ApiParam(name = "会员标签ID",value = "labelId",required = true)@PathVariable(required = true) Long labelId){
        try {
            this.merchantMemberLabelFacade.removeMerchantMemberLabel(labelId);
        } catch (MemberException e) {
            log.info("/member/label/detachMemberLabels/removeMerchantMemberLabel/{} [] ",labelId,e);
            return Result.FAILURE(e.getCode(),e.getMessage());
        }
        return Result.SUCESS();
    }

    /**
     * 会员打标签
     * @param merchantMemberLabelRelVO
     * @return
     */
    @RequestMapping(value = "/attachMemberLabel",method = RequestMethod.POST)
    @ApiOperation(value = "会员打标签",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<Object> attachMemberLabel(@RequestBody MerchantMemberLabelRelVO merchantMemberLabelRelVO){
        log.debug(String.format("/member/label/attachMemberLabel %s", merchantMemberLabelRelVO));

        try {
            this.merchantMemberLabelFacade.attachMemberLabel(ObjectConvertUtil.copyProperties(MerchantMemberLabelRelDTO.class, merchantMemberLabelRelVO));
        } catch (MemberException e) {
            log.info("会员打标签异常 {}",e);
            return Result.FAILURE(e.getCode(),e.getMessage());
        }
        return Result.SUCESS();
    }

    /**
     * 新增商户会员标签
     * @param merchantMemberLabelVO
     * @return
     */
    @RequestMapping(value = "/addMerchantMemberLabel",method = RequestMethod.POST)
    @ApiOperation(value = "新增商户会员标签",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<Object> addMerchantMemberLabel(@RequestBody MerchantMemberLabelVO merchantMemberLabelVO){
        log.info(String.format("/member/label/addMerchantMemberLabel %s", merchantMemberLabelVO));
        merchantMemberLabelVO.setMerchantId(ThreadLocalContext.getMerchantId());
        try {
            this.merchantMemberLabelFacade.addMerchantMemberLabel(ObjectConvertUtil.copyProperties(MerchantMemberLabelDTO.class, merchantMemberLabelVO));
        } catch (MemberException e) {
            log.info("新增会员标签异常 {}",e);
            return Result.FAILURE(e.getCode(),e.getMessage());
        }

        return Result.SUCESS();
    }

    /**
     *
     * @param merchantMemberLabelVO
     * @return
     */
    @RequestMapping(value = "/updateMerchantMemberLabel",method = RequestMethod.POST)
    @ApiOperation(value = "编辑商户会员标签",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<Object> updateMerchantMemberLabel(@RequestBody MerchantMemberLabelVO merchantMemberLabelVO){
        if(merchantMemberLabelVO == null){
            return Result.FAILURE(ArgumentCheckErrorEnum.INVALIDE_ARGUMENT.getCode(),ArgumentCheckErrorEnum.INVALIDE_ARGUMENT.getMessage());
        }

        merchantMemberLabelVO.setMerchantId(ThreadLocalContext.getMerchantId());
        log.info(String.format("/member/label/updateMerchantMemberLabel %s", merchantMemberLabelVO));
        try {
            this.merchantMemberLabelFacade.updateMerchantMemberLabel(ObjectConvertUtil.copyProperties(MerchantMemberLabelDTO.class, merchantMemberLabelVO));
        } catch (MemberException e) {
            log.info("编辑商户会员标签 {}",e);
            return Result.FAILURE(e.getCode(),e.getMessage());
        }

        return Result.SUCESS();
    }


    @RequestMapping(value = "/addMemberLabel",method = RequestMethod.POST)
    @ApiOperation(value = "新增会员标签（同时新增商户会员标签）",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<MerchantMemberLabelRelVO> addMemberLabel(@RequestBody MerchantMemberLabelRelVO merchantMemberLabelRelVO){
        log.info(String.format("/member/label/addMemberLabel %s", merchantMemberLabelRelVO));
        if(merchantMemberLabelRelVO == null || StringUtils.isBlank(merchantMemberLabelRelVO.getLabelName())){
            return Result.FAILURE(ArgumentCheckErrorEnum.MISSING_REQUIRED_ARGUMENT.getCode(),ArgumentCheckErrorEnum.MISSING_REQUIRED_ARGUMENT.getMessage());
        }

        MerchantMemberLabelRelDTO memberLabelRelDTO = null;
        try {
            memberLabelRelDTO = this.merchantMemberLabelFacade.addMemberLabel(ObjectConvertUtil.copyProperties(MerchantMemberLabelRelDTO.class, merchantMemberLabelRelVO));
        } catch (MemberException e) {
            log.info("添加会员标签失败 {}",e);
            String message = e.getMessage();
            if(e.getCode() == MemberErrorEnum.MERCHANT_DUPLICATE_LABEL_ERROR.getCode()){
                message = "标签名已存在，请重新输入";
            }
            return Result.FAILURE(e.getCode(),message);
        }

        return Result.SUCESS(ObjectConvertUtil.copyProperties(MerchantMemberLabelRelVO.class,memberLabelRelDTO));
    }
}
