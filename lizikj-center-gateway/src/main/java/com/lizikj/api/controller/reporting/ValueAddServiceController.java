package com.lizikj.api.controller.reporting;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lizikj.api.utils.ReportUtil;
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
import com.lizikj.api.vo.PageVO;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.reporting.ValueAddServiceStatisticVO;
import com.lizikj.api.vo.reporting.ValueAddedServiceVO;
import com.lizikj.api.vo.reporting.arg.ValueAddedParamVO;
import com.lizikj.api.vo.shop.ShopServiceTimeLeftVO;
import com.lizikj.common.util.DateUtils;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.PageParameter;
import com.lizikj.merchant.dto.AgentVasRuleDTO;
import com.lizikj.merchant.dto.ShopDTO;
import com.lizikj.merchant.dto.ShopMerchantDTO;
import com.lizikj.merchant.dto.ShopMerchantExpandDTO;
import com.lizikj.merchant.facade.IAgentVasReadFacade;
import com.lizikj.merchant.facade.IShopMerchantReadFacade;
import com.lizikj.merchant.facade.IShopReadFacade;
import com.lizikj.reporting.dto.ValueAddServiceStatisticDTO;
import com.lizikj.reporting.facade.IValueServiceReportFacade;
import com.lizikj.shop.api.dto.ShopServiceTimeLeftDTO;
import com.lizikj.shop.api.dto.ValueAddedServiceDTO;
import com.lizikj.shop.api.dto.param.ValueAddedParamDTO;
import com.lizikj.shop.api.enums.RenewStatusEnum;
import com.lizikj.shop.api.facade.IShopValueAddedReadFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Created by liangxiaolin on 2017/9/28.
 */
@RestController
@RequestMapping(value = "/reporting/values")
@Api(value = "report_values",tags = "增值服务接口")
public class ValueAddServiceController {
    private final static Logger log = LoggerFactory.getLogger(ValueAddServiceController.class);
    @Autowired
    IShopValueAddedReadFacade shopValueAddedReadFacade;
    @Autowired
    IValueServiceReportFacade valueServiceReportFacade;
    @Autowired
    IShopReadFacade shopReadFacade;
    @Autowired
    IShopMerchantReadFacade shopMerchantReadFacade;
    @Autowired
    IAgentVasReadFacade agentVasReadFacade;

    @RequestMapping(value = "queryValueAddServiceTimeLeft",method = RequestMethod.POST)
    @ApiOperation(value = "查询增值服务剩余时间",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<PageVO<ShopServiceTimeLeftVO>> queryValueAddServiceTimeLeft(@RequestBody ValueAddedParamVO valueAddedParamVO,
                                                                              @ApiParam(name = "pageNum",value = "页码",defaultValue = "1") @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
                                                                              @ApiParam(name = "pageSize",value = "每页数量",defaultValue = "25") @RequestParam(value = "pageSize",defaultValue = "25",required = false) int pageSize,
                                                                              @ApiParam(name = "orderBy",value = "排序字段") @RequestParam(value = "orderBy",required = false) String orderBy){
        Map<Long,String> shopNameMap = new HashMap<>();
        Map<Long,String> merchantNameMap = new HashMap<>();
        ValueAddedParamDTO valueAddedParamDTO = this.buildValueAddParam(valueAddedParamVO,shopNameMap,merchantNameMap);
        if(valueAddedParamDTO == null){
            return Result.SUCESS(ReportUtil.buildEmptyPage());
        }
        PageParameter pageParameter = new PageParameter(pageNum,pageSize,orderBy);

        PageInfo<ShopServiceTimeLeftDTO> timeLeftDTOPageInfo = this.shopValueAddedReadFacade.listServiceTimeLeft(valueAddedParamDTO, pageParameter);
        if(timeLeftDTOPageInfo == null || timeLeftDTOPageInfo.getList() == null ||
                timeLeftDTOPageInfo.getList().size() <= 0){
            return Result.SUCESS(ReportUtil.buildEmptyPage());
        }
        PageVO pageVO = new PageVO(timeLeftDTOPageInfo.getPageNum(),timeLeftDTOPageInfo.getPageSize(),timeLeftDTOPageInfo.getPages(),timeLeftDTOPageInfo.getTotal());
        if(timeLeftDTOPageInfo != null && timeLeftDTOPageInfo.getList() != null){
            List<ShopServiceTimeLeftVO> shopServiceTimeLeftVOS = new ArrayList<>();
            timeLeftDTOPageInfo.getList().forEach(t -> {
                ShopServiceTimeLeftVO shopServiceTimeLeftVO = ObjectConvertUtil.map(t,ShopServiceTimeLeftVO.class);
                ShopDTO shopDTO = this.shopReadFacade.findById(t.getShopId());
                if(shopDTO != null){
                    shopServiceTimeLeftVO.setShopName(shopDTO.getShopName());
                    ShopMerchantDTO shopMerchantDTO = this.shopMerchantReadFacade.findById(shopDTO.getMerchantId());
                    if(shopMerchantDTO != null){
                        shopServiceTimeLeftVO.setMerchantName(shopMerchantDTO.getMerchantName());
                    }
                }
                shopServiceTimeLeftVOS.add(shopServiceTimeLeftVO);
            });

            pageVO.setList(shopServiceTimeLeftVOS);
        }

        return Result.SUCESS(pageVO);

    }

    @RequestMapping(value = "/queryValueAddServiceRecord",method = RequestMethod.POST)
    @ApiOperation(value = "查询增值服务开通记录",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<PageVO<ValueAddedServiceVO>> queryValueAddServiceRecord(@RequestBody ValueAddedParamVO valueAddedParamVO,
                                                                          @ApiParam(name = "pageNum",value = "页码",defaultValue = "1") @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
                                                                          @ApiParam(name = "pageSize",value = "每页数量",defaultValue = "25") @RequestParam(value = "pageSize",defaultValue = "25",required = false) int pageSize,
                                                                          @ApiParam(name = "orderBy",value = "排序字段") @RequestParam(value = "orderBy",required = false) String orderBy){
        PageParameter pageParameter = new PageParameter(pageNum,pageSize,orderBy);
        Map<Long,String> shopNameMap = new HashMap<>();
        Map<Long,String> merchantNameMap = new HashMap<>();
        ValueAddedParamDTO valueAddedParamDTO = this.buildValueAddParam(valueAddedParamVO,shopNameMap,merchantNameMap);
        if(valueAddedParamDTO == null){
            return Result.SUCESS(ReportUtil.buildEmptyPage());
        }

        PageInfo<ValueAddedServiceDTO> serviceDTOPageInfo = this.shopValueAddedReadFacade.listValueAddedService(valueAddedParamDTO, pageParameter);
        if(serviceDTOPageInfo == null || serviceDTOPageInfo.getList() == null || serviceDTOPageInfo.getList().size() <= 0){
            return Result.SUCESS(ReportUtil.buildEmptyPage());
        }

        PageVO pageVO = new PageVO(serviceDTOPageInfo.getPageNum(),serviceDTOPageInfo.getPageSize(),serviceDTOPageInfo.getPages(),serviceDTOPageInfo.getTotal());
        if(serviceDTOPageInfo != null && serviceDTOPageInfo.getList() != null){
            List<ValueAddedServiceVO> valueAddedServiceVOS = new ArrayList<>();
            serviceDTOPageInfo.getList().forEach(t -> {
                AgentVasRuleDTO vasRuleDTO = agentVasReadFacade.findRuleById(t.getAgentChargeModeRuleId());
                ValueAddedServiceVO valueAddedServiceVO = new ValueAddedServiceVO();
                valueAddedServiceVO.setMerchantId(t.getMerchantId());
                valueAddedServiceVO.setShopId(t.getShopId());
                valueAddedServiceVO.setAgentChargeModeRuleId(t.getAgentChargeModeRuleId());
                valueAddedServiceVO.setAgentChargeModeRuleType(t.getAgentChargeModeRuleType());
                valueAddedServiceVO.setDaysRemaining(t.getDaysRemaining());
                valueAddedServiceVO.setAgentId(t.getAgentId());
                valueAddedServiceVO.setPaymentDate(t.getPaymentDate());
                valueAddedServiceVO.setExpiredTime(t.getExpiredTime());
                if (t.getPayType() != null) {
                    valueAddedServiceVO.setPaymentTypeCode(t.getPayType().getCode());
                    valueAddedServiceVO.setPaymentTypeName(t.getPayType().getMessage());
                }
                if(t.getRenewStatus() != null){
                    valueAddedServiceVO.setRenewStatus(t.getRenewStatus().getCode());
                }
                String shopName = shopNameMap.get(t.getShopId());
                if(shopName == null){
                    ShopDTO shopDTO = this.shopReadFacade.findById(t.getShopId());
                    if(shopDTO != null) {
                        shopName = shopDTO.getShopName();
                    }

                }
                valueAddedServiceVO.setShopName(shopName);

                String merchantName = merchantNameMap.get(t.getMerchantId());
                if(merchantName == null){
                    ShopMerchantDTO shopMerchantDTO = this.shopMerchantReadFacade.findById(t.getMerchantId());
                    if(shopMerchantDTO != null) {
                        merchantName = shopMerchantDTO.getMerchantName();
                    }
                }

                valueAddedServiceVO.setMerchantName(merchantName);

                valueAddedServiceVO.setBaseAmount(t.getCostAmount());
                valueAddedServiceVO.setSaleAmount(t.getPayAmount());
                valueAddedServiceVO.setAgentChargeModeRuleName(null == vasRuleDTO ? null : vasRuleDTO.getTitle());

                valueAddedServiceVOS.add(valueAddedServiceVO);
            });

            pageVO.setList(valueAddedServiceVOS);
        }

        return Result.SUCESS(pageVO);
    }

    @RequestMapping(value = "/queryValueAddServiceCurrentStatistic",method = RequestMethod.POST)
    @ApiOperation(value = "查询增值服务开通记录",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<List<ValueAddServiceStatisticVO>> queryValueAddServiceCurrentStatistic(){
        Date endToday = DateUtils.getEndOfDay(new Date());
        Date startMonth = DateUtils.getStartOfMonth(endToday);

        List<ValueAddServiceStatisticDTO> currentServiceStatistic = this.valueServiceReportFacade.getMonthStatisticByType(startMonth,endToday);
        if(currentServiceStatistic != null){
            List<ValueAddServiceStatisticVO> valueAddServiceStatisticVOS = new ArrayList<>();
            ValueAddServiceStatisticVO totalStatistic = new ValueAddServiceStatisticVO();
            totalStatistic.setAgentChargeModeRuleType(new BigDecimal(0));
            currentServiceStatistic.forEach(t -> {
                ValueAddServiceStatisticVO valueAddServiceStatisticVO = ObjectConvertUtil.map(t,ValueAddServiceStatisticVO.class);
                totalStatistic.add(valueAddServiceStatisticVO);
                valueAddServiceStatisticVOS.add(valueAddServiceStatisticVO);
            });

            valueAddServiceStatisticVOS.add(totalStatistic);
            return Result.SUCESS(valueAddServiceStatisticVOS);
        }

        return Result.SUCESS();
    }

    @RequestMapping(value = "/queryValueAddServiceTotalStatistic",method = RequestMethod.POST)
    @ApiOperation(value = "查询增值服务开通记录",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<ValueAddServiceStatisticVO> queryValueAddServiceTotalStatistic(){
        Date endToday = DateUtils.getEndOfDay(new Date());
        Date startMonth = DateUtils.getStartOfMonth(endToday);

        ValueAddServiceStatisticDTO currentServiceStatistic = null;
        try {
            currentServiceStatistic = this.valueServiceReportFacade.getTotalStatisticByMonth(startMonth,endToday);
        } catch (Exception e) {
            if(log.isInfoEnabled()){
                log.info("统计查询失败 {}",e);
            }
        }

        ValueAddServiceStatisticVO valueAddServiceStatisticVO = null;
        if(currentServiceStatistic == null){
            valueAddServiceStatisticVO = new ValueAddServiceStatisticVO();
        }else{
            valueAddServiceStatisticVO = ObjectConvertUtil.map(currentServiceStatistic,ValueAddServiceStatisticVO.class);
        }

        return Result.SUCESS(valueAddServiceStatisticVO);
    }

    /**
     * 参数转换
     * @param valueAddedParamVO
     * @return
     */
    private ValueAddedParamDTO buildValueAddParam(ValueAddedParamVO valueAddedParamVO,final  Map<Long,String> shopNameMap,final Map<Long,String> merchantNameMap){
        ValueAddedParamDTO valueAddedParamDTO = new ValueAddedParamDTO();
        if(valueAddedParamVO != null){
            valueAddedParamDTO.setEndTime(valueAddedParamVO.getEndTime());
            valueAddedParamDTO.setStartTime(valueAddedParamVO.getStartTime());
            if (valueAddedParamVO.getVasType() != null) {
                valueAddedParamDTO.setVasType(BigDecimal.valueOf(valueAddedParamVO.getVasType()));
            }
            if(valueAddedParamVO.getRenewStatus() != null){
                valueAddedParamDTO.setRenewStatus(RenewStatusEnum.fromCode(valueAddedParamVO.getRenewStatus()));
            }
        }
        List<Long> shopIds = new ArrayList<>();

        if(!StringUtils.isBlank(valueAddedParamVO.getShopName())){
            ShopDTO query = new ShopDTO();
            query.setShopName(valueAddedParamVO.getShopName());
            List<ShopDTO> shopDTOS = this.shopReadFacade.findList(query);
            if(shopDTOS != null){
                shopDTOS.forEach(t -> {
                    shopIds.add(t.getShopId());
                    shopNameMap.put(t.getShopId(),t.getShopName());
                });
            }
            //没有店铺，不用继续查询
            if(shopDTOS == null || shopDTOS.size() <= 0){
                return null;
            }
            valueAddedParamDTO.setShopIds(shopIds);
        }
        List<Long> merchantIds = new ArrayList<>();
        if(!StringUtils.isBlank(valueAddedParamVO.getMerchantName())){
            ShopMerchantExpandDTO shopMerchantDTO = new ShopMerchantExpandDTO();
            shopMerchantDTO.setMerchantName(valueAddedParamVO.getMerchantName());
            List<ShopMerchantExpandDTO> shopMerchantDTOS = this.shopMerchantReadFacade.findList(shopMerchantDTO);
            if(shopMerchantDTOS != null){
                shopMerchantDTOS.forEach(t -> {
                    merchantIds.add(t.getMerchantId());
                    merchantNameMap.put(t.getMerchantId(),t.getMerchantName());
                });
            }

            if(shopMerchantDTOS == null || shopMerchantDTOS.size() <= 0){
                return null;
            }
            valueAddedParamDTO.setMerchantIds(merchantIds);
        }

        return valueAddedParamDTO;
    }


}
