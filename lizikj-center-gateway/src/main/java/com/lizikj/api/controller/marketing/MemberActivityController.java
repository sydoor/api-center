package com.lizikj.api.controller.marketing;

import com.github.pagehelper.PageInfo;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.marketing.MemberRechargeCashBackVO;
import com.lizikj.api.vo.marketing.RechargeBackKVVO;
import com.lizikj.api.vo.marketing.RechargeCashBackVO;
import com.lizikj.common.exception.BaseException;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.marketing.api.dto.MemberRechargeCashBackDTO;
import com.lizikj.marketing.api.dto.ShopActivityDTO;
import com.lizikj.marketing.api.enums.ActivityTemplateEnum;
import com.lizikj.marketing.api.enums.MarketingActivityStatusEnum;
import com.lizikj.marketing.api.exception.MarketingException;
import com.lizikj.marketing.api.facade.IActivityTemplateReadFacade;
import com.lizikj.marketing.api.facade.IActivityTemplateWriteFacade;
import com.lizikj.marketing.api.utils.MemberRechargeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lxl
 * @Date 2018/5/2 11:56
 */
@RestController
@RequestMapping("marketing/member")
@Api(value = "marketing", tags = "会员活动接口")
public class MemberActivityController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IActivityTemplateReadFacade activityTemplateReadFacade;

    @Autowired
    private IActivityTemplateWriteFacade activityTemplateWriteFacade;


    @RequestMapping(value = "/recharge/cashback", method = RequestMethod.POST)
    @ApiOperation(value = "新增充值满送", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> addCashBack(@RequestBody @ApiParam(name = "充值满送表单", value = "JSON格式表单", required = true) MemberRechargeCashBackVO rechargeCashBackVO){
        long shopId = ThreadLocalContext.getShopId();
        if(logger.isInfoEnabled()){
            logger.info("新增满减优惠 , shopId={}, fullDiscountActivityDetailCreateVO={}", shopId, rechargeCashBackVO);
        }

        if(!validateParam(rechargeCashBackVO)){
            return Result.FAILURE("参数有误");
        }

        rechargeCashBackVO.setMerchantId(ThreadLocalContext.getMerchantId());
        rechargeCashBackVO.setShopId(ThreadLocalContext.getShopId());

        try {
            //封装活动属性对象
            MemberRechargeCashBackDTO dto = convertToDTO(rechargeCashBackVO);
            //封装店铺活动对象
            ShopActivityDTO shopActivity = new ShopActivityDTO();
            shopActivity.setShopId(shopId);
            shopActivity.setTemplateId(ActivityTemplateEnum.RECHARGE_CASH_BACK.getValue());
            shopActivity.setStatus(MarketingActivityStatusEnum.CONDUCTING.getStatus());
            shopActivity.setStartTime(rechargeCashBackVO.getStartTime());
            shopActivity.setEndTime(rechargeCashBackVO.getEndTime());

            activityTemplateWriteFacade.add(shopId, ActivityTemplateEnum.RECHARGE_CASH_BACK.getValue(), dto, shopActivity);
        } catch (BaseException e) {
            return Result.FAILURE(e.getCode(), e.getMessage());
        }
        return Result.SUCESS();
    }


    @RequestMapping(value = "/recharge/getCashBack")
    @ApiOperation(value = "充值返利计算接口", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<RechargeCashBackVO> getCashBack(@RequestBody RechargeCashBackVO cashBackVO){
        if(cashBackVO == null && cashBackVO.getRecharge() <= 0){
            return Result.FAILURE("充值金额小于零");
        }

        long shopId = ThreadLocalContext.getShopId();
        List<MemberRechargeCashBackDTO> memberRechargeCashBackDTOS = listAll(shopId, MarketingActivityStatusEnum.CONDUCTING.getStatus());
        Long mostRebate = 0L;
        Long mostActivityId = null;
        if(memberRechargeCashBackDTOS != null && !memberRechargeCashBackDTOS.isEmpty()){
            for(MemberRechargeCashBackDTO dto:memberRechargeCashBackDTOS){
                List<RechargeBackKVVO> rebateItems = getRebateItems(dto);
                for(RechargeBackKVVO kv:rebateItems){
                    if(kv.getAmount() < cashBackVO.getRecharge() && mostRebate < kv.getRebate()){
                        mostRebate = kv.getRebate();
                        mostActivityId = dto.getActivityId();
                    }
                }
            }
        }

        cashBackVO.setRebate(mostRebate);
        cashBackVO.setActivityId(mostActivityId);

        return Result.SUCESS(cashBackVO);
    }

    @RequestMapping(value = "/recharge/listUsables", method = RequestMethod.POST)
    @ApiOperation(value = "获取可用充值返利", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<RechargeBackKVVO>> listUsables(){
        long shopId = ThreadLocalContext.getShopId();
        List<MemberRechargeCashBackDTO> dtos = listAll(shopId, MarketingActivityStatusEnum.CONDUCTING.getStatus());
        Map<Long,RechargeBackKVVO> kvMap = new HashMap<>();
        if(dtos != null){
            dtos.forEach(dto -> {
                List<RechargeBackKVVO> rebateItems = getRebateItems(dto);
                rebateItems.forEach(item ->{
                    kvMap.put(item.getAmount(),item);
                });
            });
		}

		List<RechargeBackKVVO> resultList = new ArrayList<>(kvMap.values());
		resultList.sort((RechargeBackKVVO vo1, RechargeBackKVVO vo2) -> vo1.getAmount().compareTo(vo2.getAmount()));

		return Result.SUCESS(resultList);
    }
    
    @RequestMapping(value = "/recharge/editCashBack", method = RequestMethod.POST)
    @ApiOperation(value = "编辑充值满送", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> editCashBack(@RequestBody @ApiParam(name = "编辑充值满送", value = "JSON格式表单", required = true) MemberRechargeCashBackVO rechargeCashBackVO){
        long shopId = ThreadLocalContext.getShopId();
        logger.info("新增满减优惠 , shopId={}, editCashBack={}", shopId, rechargeCashBackVO);

        if(!validateParam(rechargeCashBackVO)){
            return Result.FAILURE("参数有误");
        }

        rechargeCashBackVO.setMerchantId(ThreadLocalContext.getMerchantId());
        rechargeCashBackVO.setShopId(ThreadLocalContext.getShopId());
        try {
            //封装活动属性对象
            MemberRechargeCashBackDTO dto = convertToDTO(rechargeCashBackVO);
            if(dto.getActivityId() != null && dto.getRechargeBackKVs().isEmpty()){
                activityTemplateWriteFacade.endActivity(shopId,ActivityTemplateEnum.RECHARGE_CASH_BACK.getValue(),dto.getActivityId());
                return Result.SUCESS("删除活动成功");
            }
            //封装店铺活动对象
            ShopActivityDTO shopActivity = new ShopActivityDTO();
            shopActivity.setShopId(shopId);
            shopActivity.setActivityId(dto.getActivityId());
            shopActivity.setTemplateId(ActivityTemplateEnum.RECHARGE_CASH_BACK.getValue());
            shopActivity.setStatus(MarketingActivityStatusEnum.CONDUCTING.getStatus());
            shopActivity.setStartTime(rechargeCashBackVO.getStartTime());
            shopActivity.setEndTime(rechargeCashBackVO.getEndTime());

            if(dto.getActivityId() == null && !dto.getRechargeBackKVs().isEmpty()){
                activityTemplateWriteFacade.add(shopId, ActivityTemplateEnum.RECHARGE_CASH_BACK.getValue(), dto, shopActivity);
                return Result.SUCESS("新增活动成功");
            }

            activityTemplateWriteFacade.update(shopId, ActivityTemplateEnum.RECHARGE_CASH_BACK.getValue(), dto, shopActivity,false,true);
        } catch (BaseException e) {
            return Result.FAILURE(e.getCode(), e.getMessage());
        }
        return Result.SUCESS("更新成功");
    }

    @RequestMapping(value = "/recharge/deleteCashBack/{activityId}", method = RequestMethod.POST)
    @ApiOperation(value = "删除充值满送", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> delete(@PathVariable(name = "activityId", required = true) Long activityId){
        long shopId = ThreadLocalContext.getShopId();
        if(logger.isInfoEnabled()){
            logger.info("删除分时段优惠 , shopId={}, timeDiscountId={}", shopId, activityId);
        }
        try {
            activityTemplateWriteFacade.endActivity(shopId, ActivityTemplateEnum.RECHARGE_CASH_BACK.getValue(), activityId);
        } catch (BaseException e) {
            return Result.FAILURE(e.getCode(), e.getMessage());
        }
        return Result.SUCESS();
    }

    @RequestMapping(value = "/recharge/listCashBack", method = RequestMethod.POST)
    @ApiOperation(value = "获取充值满送列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<MemberRechargeCashBackVO>> listCashBack(){
        long shopId = ThreadLocalContext.getShopId();
        if(logger.isInfoEnabled()){
            logger.info("获取充值满送列表, shopId={}", shopId);
        }
        List<MemberRechargeCashBackVO> list = null;
        try {
            List<MemberRechargeCashBackDTO> dtolist = listAll(shopId,MarketingActivityStatusEnum.CONDUCTING.getStatus());
            list = convertToVOs(dtolist);
        } catch (BaseException e) {
            return Result.FAILURE(e.getCode(), e.getMessage());
        }
        return Result.SUCESS(list);
    }

    /**
     * 获取充值返利活动
     * @param shopId
     * @param statusArr
     * @return
     */
    private List<MemberRechargeCashBackDTO> listAll(long shopId,Byte... statusArr){
        PageInfo<ShopActivityDTO> pageInfo = activityTemplateReadFacade.list(0, 0, shopId, ActivityTemplateEnum.RECHARGE_CASH_BACK.getValue(), Arrays.asList(statusArr));
        List<MemberRechargeCashBackDTO> dtolist = MemberRechargeUtils.fromActivityList(pageInfo.getList());

        return dtolist;
    }

    /**
     * 获取充值返利KV
     * @param dto
     * @return
     */
    private List<RechargeBackKVVO> getRebateItems(MemberRechargeCashBackDTO dto){
        List<RechargeBackKVVO> kvvos = new ArrayList<>();
        for(String kvStr:dto.getRechargeBackKVs()){
            String[] kvArr = kvStr.split(MemberRechargeUtils.KV_SEPARATOR);
            if(kvArr.length >= 2){
                try {
                    RechargeBackKVVO kvvo = new RechargeBackKVVO();
                    kvvo.setAmount(Long.valueOf(kvArr[0]));
                    kvvo.setRebate(Long.valueOf(kvArr[1]));
                    kvvo.setActivityId(dto.getActivityId());
                    kvvos.add(kvvo);
                } catch (NumberFormatException e) {
                    logger.info("充值返利存储类型有误 {}",dto);
                }
            }
        }

        kvvos.sort((RechargeBackKVVO vo1, RechargeBackKVVO vo2) -> vo1.getAmount().compareTo(vo2.getAmount()));
        return kvvos;
    }

    /**
     * 从充值满送项目构建充值活动
     * @param kvvos
     * @return
     */
    private List<MemberRechargeCashBackDTO> buildFromItems(List<RechargeBackKVVO> kvvos){
        Map<Long,MemberRechargeCashBackDTO> cashBackMap = new HashMap<>();
        for(RechargeBackKVVO kvvo:kvvos){
            if(kvvo == null){
                continue;
            }
            MemberRechargeCashBackDTO dto = cashBackMap.get(kvvo.getActivityId());
            if(dto == null){
                dto = new MemberRechargeCashBackDTO();
                dto.setActivityId(kvvo.getActivityId());
                cashBackMap.put(kvvo.getActivityId(),dto);
            }
            boolean valid = kvvo.getAmount() != null && kvvo.getAmount() > 0
                    && kvvo.getRebate() != null && kvvo.getRebate() > 0;
            if(valid){
                dto.addRechargeBack(kvvo.getAmount(),kvvo.getRebate());
            }
        }

        return new ArrayList<>(cashBackMap.values());
    }

    private List<MemberRechargeCashBackVO> convertToVOs(List<MemberRechargeCashBackDTO> dtos){

        return dtos.stream().map(dto -> convertToVO(dto)).collect(Collectors.toList());
    }

    private MemberRechargeCashBackVO convertToVO(MemberRechargeCashBackDTO dto){
        MemberRechargeCashBackVO vo = ObjectConvertUtil.map(dto,MemberRechargeCashBackVO.class);
        vo.setKvvos(getRebateItems(dto));
        return vo;
    }

    private MemberRechargeCashBackDTO convertToDTO(MemberRechargeCashBackVO vo){
        MemberRechargeCashBackDTO dto = new MemberRechargeCashBackDTO();
        dto.setMerchantId(vo.getMerchantId());
        dto.setShopId(vo.getShopId());
        dto.setActivityId(vo.getActivityId());
        dto.setRechargeBackKVs(new ArrayList<>());
        Map<Long,Long> sources = new HashMap<>();
        if (vo.getKvvos() != null) {
            for(RechargeBackKVVO kv:vo.getKvvos()){
                sources.put(kv.getAmount(),kv.getRebate());
            }
        }

        for(Map.Entry<Long,Long> entry:sources.entrySet()){
            dto.addRechargeBack(entry.getKey(),entry.getValue());
        }

        return dto;
    }

    private boolean validateParam(MemberRechargeCashBackVO rechargeCashBackVO){
        if(rechargeCashBackVO == null){
            return false;
        }
        List<RechargeBackKVVO> kvvos = rechargeCashBackVO.getKvvos();

        if (kvvos != null) {
            for(RechargeBackKVVO kv:kvvos){
                if(kv == null || kv.getAmount() == null || kv.getRebate() == null){
                    return false;
                }
                if(kv.getRebate() <= 0 || kv.getAmount() <= 0){
                    return false;
                }
            }
        }
        return true;
    }
}
