package com.lizikj.api.controller.message;

import com.github.pagehelper.PageInfo;
import com.lizikj.api.utils.ReportUtil;
import com.lizikj.api.vo.PageVO;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.msg.*;
import com.lizikj.api.vo.reporting.TrendsChartVO;
import com.lizikj.api.vo.reporting.param.HeartBeatVO;
import com.lizikj.common.enums.ArgumentCheckErrorEnum;
import com.lizikj.common.enums.UserTypeEnum;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.PageParameter;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.login.interceptor.LoginExclude;
import com.lizikj.message.api.async.MsgAsyncPush;
import com.lizikj.message.api.dto.*;
import com.lizikj.message.api.enums.MessageErrorEnum;
import com.lizikj.message.api.enums.OnlineStatusEnum;
import com.lizikj.message.api.enums.StatusEnum;
import com.lizikj.message.api.enums.TerminalTypeEnum;
import com.lizikj.message.api.enums.ThirdPlatformTypeEnum;
import com.lizikj.message.api.exception.MessageException;
import com.lizikj.message.api.facade.IMsgPushFacade;
import com.lizikj.message.api.facade.IMsgPushRegisterFacade;
import com.lizikj.message.api.facade.IMsgReportFacade;
import com.lizikj.message.api.facade.IMsgSettingFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 消息系統REST API
 * Created by liangxiaolin on 2017/7/21.
 */
@RestController
@RequestMapping(value = "/message")
@Api(value = "msg-controller",tags = "消息系统接口")
public class MessageController {
    private final static Logger log = LoggerFactory.getLogger(MessageController.class);
    @Autowired
    IMsgPushFacade msgPushFacade;
    @Autowired
    IMsgPushRegisterFacade msgPushRegisterFacade;
    @Autowired
    MsgAsyncPush msgAsyncPush;
    @Autowired
    IMsgReportFacade msgReportFacade;
    @Autowired
    IMsgSettingFacade msgSettingFacade;


    @RequestMapping(value = "/code/sendValidCode",method = RequestMethod.POST)
    @LoginExclude
    @ApiOperation(value = "发送验证码",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<CodeRequestVO> sendValidCode(@RequestBody CodeRequestVO codeRequestVO){
        boolean invalid = codeRequestVO == null || codeRequestVO.getMobile() == null || codeRequestVO.getMsgBizCode() == null;
        if(invalid){
            return Result.FAILURE(MessageErrorEnum.PARAMETER_ERROR.getCode(),"参数不正确");
        }

        boolean success;
        try {
            success = msgPushFacade.getAndSendValidCode(codeRequestVO.getMobile(), codeRequestVO.getMsgBizCode(), codeRequestVO.getContent());
        } catch (Exception e) {
            log.info("send valid code exception {}",e);
            success = false;
        }

        codeRequestVO.setSuccess(success);

        return Result.SUCESS(codeRequestVO);
    }

    /**
     * 杰威开放
     * @param confirmVO
     * @return
     */
    @RequestMapping(value = "/code/confirmValidCode",method = RequestMethod.POST)
    @LoginExclude
    @ApiOperation(value = "确认验证码",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<CodeConfirmVO> confirmValidCode(@RequestBody CodeConfirmVO confirmVO){
        boolean invalid = confirmVO == null || confirmVO.getMobile() == null || confirmVO.getMsgBizCode() == null || confirmVO.getCode() == null;
        if(invalid){
            return Result.FAILURE(MessageErrorEnum.PARAMETER_ERROR.getCode(),"参数不正确");
        }

        boolean success;
        try {
            success = msgPushFacade.confirmValidCode(confirmVO.getMobile(), confirmVO.getMsgBizCode(),confirmVO.getCode());
        } catch (Exception e) {
            log.info("confirm valid code exception {}",e);
            success = false;
        }

        confirmVO.setValidMode(success);

        return Result.SUCESS(confirmVO);
    }

    @RequestMapping(value = "/device/syncMsg",method = RequestMethod.POST)
    @ApiOperation(value = "同步消息（拉取必达未回调消息）",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<MsgPullVO> syncMsg(@RequestBody MsgPullQueryVO msgPullQueryVO){
        MsgPullQueryDTO queryDTO = new MsgPullQueryDTO();
        if (msgPullQueryVO != null && msgPullQueryVO.getCursorMsgId() != null) {
            queryDTO.setCursorMsgId(msgPullQueryVO.getCursorMsgId());
        }
        queryDTO.setSn(ThreadLocalContext.getDid());
        queryDTO.setShopId(ThreadLocalContext.getShopId());
        queryDTO.setMustArrivalEnum(StatusEnum.YES);
        queryDTO.setConfirmStatusEnum(StatusEnum.NO);
        MsgPullDTO pullDTO = this.msgReportFacade.pullMsg(queryDTO);
        MsgPullVO pullVO = new MsgPullVO();
        pullVO.setCursorMsgId(pullDTO.getCursorMsgId());
        pullVO.setDetailVOList(ObjectConvertUtil.mapList(pullDTO.getDetailDTOList(),MsgPushDetailDTO.class,MsgPushDetailVO.class));

        return Result.SUCESS(pullVO);
    }

    @RequestMapping(value = "/device/addReport",method = RequestMethod.POST)
    @ApiOperation(value = "提交设备情况统计",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> addOrUpdateDeviceReport(@RequestBody MsgDeviceReportVO msgDeviceReportVO){
        if(msgDeviceReportVO == null || msgDeviceReportVO.getSn() == null || msgDeviceReportVO.getPrintSuccessRate() == null){
            return Result.FAILURE("提交失败，缺少必要参数");
        }

        MsgDeviceReportDTO deviceReportDTO = ObjectConvertUtil.map(msgDeviceReportVO,MsgDeviceReportDTO.class);

        this.msgReportFacade.addOrUpdateReport(deviceReportDTO);

        return Result.SUCESS();
    }

    @RequestMapping(value = "device/addOrUpdateReminder",method = RequestMethod.POST)
    @ApiOperation(value = "业务提醒功能开关",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> addOrUpdateReminder(@ApiParam(name = "reminder",value = "业务提醒功能开关")@RequestParam(value = "reminder")Integer reminder){
        if(reminder == null || reminder < 0){
            return Result.FAILURE("参数有误");
        }

        Long shopId = ThreadLocalContext.getShopId();
        String sn = ThreadLocalContext.getDid();
        DeviceReminderDTO deviceReminderDTO = new DeviceReminderDTO();
        deviceReminderDTO.setReminderBit(reminder);
        deviceReminderDTO.setSn(sn);
        deviceReminderDTO.setShopId(shopId);
        try {
            this.msgSettingFacade.addOrUpdateReminder(deviceReminderDTO);
        } catch (Exception e) {
            log.info("业务提醒功能开关 设置失败 {} {}",deviceReminderDTO,e);
            Result.FAILURE("业务提醒功能开关 设置失败");
        }

        return Result.SUCESS(true);
    }

    @RequestMapping(value = "/device/heartBeat",method = RequestMethod.POST)
    @ApiOperation(value = "设备心跳请求",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> heartBeat(@RequestBody HeartBeatVO heartBeatVO){
        if(heartBeatVO == null ){
            return Result.FAILURE("缺少必要参数");
        }
        Long shopId = ThreadLocalContext.getShopId();
        Long userId = ThreadLocalContext.getUserId();
        Long merchantId = ThreadLocalContext.getMerchantId();
        Byte userType = ThreadLocalContext.getUserType();
        //ios android pos
        String sname = ThreadLocalContext.getSname();
        DeviceHeartBeatDTO deviceHeartBeatDTO = new DeviceHeartBeatDTO();
        deviceHeartBeatDTO.setShopId(shopId);
        deviceHeartBeatDTO.setUserId(userId);
        deviceHeartBeatDTO.setMerchantId(merchantId);
        deviceHeartBeatDTO.setUserTypeEnum(UserTypeEnum.getEnum(userType));
        deviceHeartBeatDTO.setTerminalTypeEnum(TerminalTypeEnum.getEnum(sname));
        deviceHeartBeatDTO.setReminderBit(heartBeatVO.getReminderBit());
        deviceHeartBeatDTO.setSn(ThreadLocalContext.getDid());
        deviceHeartBeatDTO.setUserAlias(heartBeatVO.getUserAlias());
        deviceHeartBeatDTO.setClientVersion(ThreadLocalContext.getAversion());

        try {
            this.msgPushFacade.beatDevice(deviceHeartBeatDTO,heartBeatVO.getOnlineStatusEnum());
        } catch (Exception e) {
            log.info("心跳记录失败 {} {}",deviceHeartBeatDTO,e);
            Result.FAILURE("心跳记录失败");
        }

        return Result.SUCESS(true);
    }

    @RequestMapping(value = "queryDeviceRecords",method = RequestMethod.POST)
    @ApiOperation(value = "设备登录记录",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PageVO<DeviceAndUserRecordVO>> queryDeviceRecords(@RequestBody UserRecordQueryVO userRecordQueryVO,
                                             @ApiParam(name = "pageNum",value = "页码",defaultValue = "1") @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
                                             @ApiParam(name = "pageSize",value = "每页数量",defaultValue = "25") @RequestParam(value = "pageSize",defaultValue = "25",required = false) int pageSize){
        UserRecordQueryParamDTO paramDTO = null;
        if(userRecordQueryVO != null){
            paramDTO = ObjectConvertUtil.map(userRecordQueryVO,UserRecordQueryParamDTO.class);
        }
        PageParameter pageParameter = new PageParameter(pageNum,pageSize);
        PageInfo<DeviceAndUserRecordDTO> page = this.msgReportFacade.findUserRecordByPage(paramDTO, pageParameter);
        PageVO pageVO = new PageVO(page.getPageNum(),page.getPageSize(),page.getPages(),page.getTotal());
        if(page.getList() != null){
            pageVO.setList(ObjectConvertUtil.mapList(page.getList(),DeviceAndUserRecordDTO.class,DeviceAndUserRecordVO.class));
        }

        return Result.SUCESS(pageVO);
    }
    @RequestMapping(value = "rePushMsg",method = RequestMethod.POST)
    @ApiOperation(value = "消息重发",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<MsgRePushParamVO> rePushMsg(@RequestBody MsgRePushParamVO msgRePushParamVO){
        if(msgRePushParamVO == null){
            return Result.SUCESS(msgRePushParamVO);
        }

        MsgRePushParamDTO pushParamDTO = new MsgRePushParamDTO();
        if (msgRePushParamVO.getRecordIds() != null && !msgRePushParamVO.getRecordIds().isEmpty()) {
            List<Long> recordIds = new ArrayList<>();
            msgRePushParamVO.getRecordIds().forEach(t -> {
                if(t != null){
                    recordIds.add(t);
                }
            });
            if (!recordIds.isEmpty()) {
                pushParamDTO.setRecordIds(recordIds);
            }
        }
        if(msgRePushParamVO.getMsgIds() != null && !msgRePushParamVO.getMsgIds().isEmpty()){
            List<Long> msgIds = new ArrayList<>();
            msgRePushParamVO.getMsgIds().forEach(t -> {
                if (t != null){
                    msgIds.add(t);
                }
            });
            if (!msgIds.isEmpty()) {
                pushParamDTO.setMsgIds(msgIds);
            }
        }

        try {
            this.msgPushFacade.rePushMsgToDevice(pushParamDTO);
        } catch (Exception e) {
            log.info("重发消息失败 {} {}",pushParamDTO,e);
            return Result.FAILURE(msgRePushParamVO.toString());
        }

        return Result.SUCESS(msgRePushParamVO);
    }


    @RequestMapping(value = "getMsgPushChartByHour",method = RequestMethod.POST)
    @ApiOperation(value = "消息推送到达率统计",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<TrendsChartVO> getMsgPushChartByHour(@RequestBody MsgPushStatisticQueryVO msgPushStatisticQueryVO){
        MsgPushStatisticQueryDTO queryDTO = null;
        if (msgPushStatisticQueryVO != null) {
            queryDTO = ObjectConvertUtil.map(msgPushStatisticQueryVO,MsgPushStatisticQueryDTO.class);
        }

        List<MsgPushStatisticDTO> dtos = this.msgReportFacade.queryMsgStatisticByHour(queryDTO);

        List<MsgPushStatisticDTO> afterSorted = new ArrayList<>();
        if (dtos != null && dtos.size() > 0) {
            afterSorted = dtos.stream()
                    .sorted(Comparator.comparing(MsgPushStatisticDTO::getReportDateStr))
                    .collect(Collectors.toList());
        }

        return Result.SUCESS(ReportUtil.buildTrendsChart(afterSorted,"到达率","getReportDateStr","getMustArrivalSucceedRate", ReportUtil.ValueTypeEnum.RATE));
    }

    @RequestMapping(value = "listMsgPushReportByDevice",method = RequestMethod.POST)
    @ApiOperation(value = "消息推送统计查询（按设备统计）",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PageVO<MsgPushStatisticVO>> listMsgPushReportByDevice(@RequestBody MsgPushStatisticQueryVO msgPushStatisticQueryVO,
                                                    @ApiParam(name = "pageNum",value = "页码",defaultValue = "1") @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
                                                    @ApiParam(name = "pageSize",value = "每页数量",defaultValue = "25") @RequestParam(value = "pageSize",defaultValue = "25",required = false) int pageSize){
        MsgPushStatisticQueryDTO queryDTO = null;
        if (msgPushStatisticQueryVO != null) {
            queryDTO = ObjectConvertUtil.map(msgPushStatisticQueryVO,MsgPushStatisticQueryDTO.class);
        }
        PageParameter pageParameter = new PageParameter(pageNum,pageSize);
        PageInfo<MsgPushStatisticDTO> statisticDTOPageInfo = this.msgReportFacade.queryMsgStatisticByDevice(queryDTO,pageParameter);
        PageVO pageVO = new PageVO(statisticDTOPageInfo.getPageNum(),statisticDTOPageInfo.getPageSize(),statisticDTOPageInfo.getPages(),statisticDTOPageInfo.getTotal());
        pageVO.setList(ObjectConvertUtil.mapList(statisticDTOPageInfo.getList(),MsgPushStatisticDTO.class,MsgPushStatisticVO.class));

        return Result.SUCESS(pageVO);
    }

    @RequestMapping(value = "getMsgPushReport",method = RequestMethod.POST)
    @ApiOperation(value = "消息推送统计",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<MsgPushStatisticVO> getMsgPushReport(@RequestBody MsgPushStatisticQueryVO msgPushStatisticQueryVO){
        MsgPushStatisticQueryDTO queryDTO = null;
        if (msgPushStatisticQueryVO != null) {
            queryDTO = ObjectConvertUtil.map(msgPushStatisticQueryVO,MsgPushStatisticQueryDTO.class);
        }

        MsgPushStatisticDTO msgStatistic = this.msgReportFacade.getMsgStatistic(queryDTO);
        MsgPushStatisticVO statisticVO = ObjectConvertUtil.map(msgStatistic,MsgPushStatisticVO.class);

        return Result.SUCESS(statisticVO);
    }

    @RequestMapping(value = "listMsg",method = RequestMethod.POST)
    @ApiOperation(value = "消息推送查询",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PageVO<MsgPushDetailVO>> listMsg(@RequestBody MsgReportQueryVO msgReportQueryVO,
                                  @ApiParam(name = "pageNum",value = "页码",defaultValue = "1") @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
                                  @ApiParam(name = "pageSize",value = "每页数量",defaultValue = "25") @RequestParam(value = "pageSize",defaultValue = "25",required = false) int pageSize,
                                  @ApiParam(name = "orderBy",value = "排序字段") @RequestParam(value = "orderBy",required = false) String orderBy){
        MsgReportQueryDTO msgReportQueryDTO = null;
        if(msgReportQueryVO != null){
            msgReportQueryDTO = ObjectConvertUtil.map(msgReportQueryVO,MsgReportQueryDTO.class);
        }
        PageParameter pageParameter = new PageParameter(pageNum,pageSize,orderBy);
        PageInfo<MsgPushDetailDTO> pushMsg = this.msgReportFacade.findPushMsg(msgReportQueryDTO, pageParameter);
        PageVO<MsgPushDetailVO> pageVO = new PageVO(pushMsg.getPageNum(),pushMsg.getPageSize(),pushMsg.getPages(),pushMsg.getTotal());
        if(pushMsg.getList() != null){
            pageVO.setList(ObjectConvertUtil.mapList(pushMsg.getList(),MsgPushDetailDTO.class,MsgPushDetailVO.class));
        }

        return Result.SUCESS(pageVO);
    }


    @RequestMapping(value = "listSms",method = RequestMethod.POST)
    @ApiOperation(value = "短信推送查询",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<PageVO<PhoneMsgRecordVO>> listMsg(@RequestBody PhoneReportQueryVO phoneReportQueryVO,
                                                    @ApiParam(name = "pageNum",value = "页码",defaultValue = "1") @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
                                                    @ApiParam(name = "pageSize",value = "每页数量",defaultValue = "25") @RequestParam(value = "pageSize",defaultValue = "25",required = false) int pageSize,
                                                    @ApiParam(name = "orderBy",value = "排序字段") @RequestParam(value = "orderBy",required = false) String orderBy){
        PhoneReportQueryDTO phoneReportQueryDTO = null;
        if(phoneReportQueryVO != null){
            phoneReportQueryDTO = ObjectConvertUtil.map(phoneReportQueryVO,PhoneReportQueryDTO.class);
        }
        PageParameter pageParameter = new PageParameter(pageNum,pageSize,orderBy);
        PageInfo<PhoneMsgRecordDTO> pushMsg = this.msgReportFacade.findPhoneMsg(phoneReportQueryDTO, pageParameter);
        PageVO<PhoneMsgRecordVO> pageVO = new PageVO(pushMsg.getPageNum(),pushMsg.getPageSize(),pushMsg.getPages(),pushMsg.getTotal());
        if(pushMsg.getList() != null){
            pageVO.setList(ObjectConvertUtil.mapList(pushMsg.getList(),PhoneMsgRecordDTO.class,PhoneMsgRecordVO.class));
        }

        return Result.SUCESS(pageVO);
    }

    @RequestMapping(value = "/pushMsg",method = RequestMethod.POST)
    @LoginExclude
    @ApiOperation(value = "消息推送",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<Object> pushMsg(@RequestBody AsyncMsgVO asyncMsgVO){
        if(asyncMsgVO == null || asyncMsgVO.getMsgContent() == null || asyncMsgVO.getTargetIds() == null){
            log.info(String.format("参数为空"));
            return Result.FAILURE(ArgumentCheckErrorEnum.MISSING_REQUIRED_ARGUMENT.getCode(),ArgumentCheckErrorEnum.MISSING_REQUIRED_ARGUMENT.getMessage());
        }

        AsyncMsgDTO asyncMsgDTO = new AsyncMsgDTO();
        asyncMsgDTO.setTargetIds(asyncMsgVO.getTargetIds());
        asyncMsgDTO.setMsgContent(asyncMsgVO.getMsgContent());
        asyncMsgDTO.setExtras(asyncMsgVO.getExtras());
        asyncMsgDTO.setIsScheduled(asyncMsgVO.getIsScheduled());
        asyncMsgDTO.setMustArrive(asyncMsgVO.getMustArrive());
        asyncMsgDTO.setMsgTitle(asyncMsgVO.getMsgTitle());
        asyncMsgDTO.setTargetGroupEnum(asyncMsgVO.getTargetGroupEnum());
        asyncMsgDTO.setMessageTypeEnum(asyncMsgVO.getMessageTypeEnum());
        asyncMsgDTO.setMemo(asyncMsgVO.getMemo());
        asyncMsgDTO.setTerminalTypeEnum(asyncMsgVO.getTerminalTypeEnum());

        try {
            int i = this.msgAsyncPush.pushAsyncMsg(asyncMsgDTO);
        } catch (MessageException e) {
            return Result.FAILURE(e.getCode(),e.getMessage());
        }

        return Result.SUCESS();
    }

    /**
     * 消息推送回调接口
     * @param sn
     * @param thirdMsgId
     * @return
     */
    @RequestMapping(value = "/push/callback",method = RequestMethod.POST)
    @LoginExclude
    @ApiOperation(value = "消息推送回调接口",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<Object> pushCallBack(
            @ApiParam(name = "sn",value = "snNum或app唯一标识",required = true) @RequestParam(value = "sn",required = true) String sn,
            @ApiParam(name = "thirdMsgId",value = "第三方消息ID",required = false) @RequestParam(value = "thirdMsgId",required = false) String thirdMsgId,
            @ApiParam(name = "msgId",value = "消息ID",required = false) @RequestParam(value = "msgId",required = false) Long msgId,
            @ApiParam(name = "receivedTime",value = "接收时间 需转换成服务器时间") @RequestParam(value = "receivedTime",required = false)Long receivedTime){
        if(StringUtils.isBlank(sn) || (thirdMsgId == null && msgId == null)){
            log.info(String.format("snNum 或者 thirdMsgId 不能为null"));
            return Result.FAILURE(ArgumentCheckErrorEnum.MISSING_REQUIRED_ARGUMENT.getCode(),ArgumentCheckErrorEnum.MISSING_REQUIRED_ARGUMENT.getMessage());
        }

        if(receivedTime == null){
            receivedTime = System.currentTimeMillis();
        }

        log.info("POST /message/push/callback sn={} thirdMsgId={} msgId={}",sn,thirdMsgId,msgId);
        int succeed = 0;
        try{
            succeed = this.msgPushFacade.reportMsgReceived(msgId,thirdMsgId,sn,receivedTime);
        }catch (MessageException e){
            log.info("POST /message/push/callback 发生异常 {}",e.getMessage());
            return Result.FAILURE(e.getCode(),e.getMessage());
        }

        if(succeed == 0){
            return Result.FAILURE("回调失败");
        }

        return Result.SUCESS();
    }

    @RequestMapping(value = "/v2/register",method = RequestMethod.POST)
    @ApiOperation(value = "消息服务注册接口",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<Object> register_v2(@RequestBody MsgRegisterVO msgRegisterVO){
        if(msgRegisterVO == null && StringUtils.isBlank(msgRegisterVO.getUserAlias())){
            log.info("user alias 不能为null {}",msgRegisterVO);
            return Result.FAILURE(ArgumentCheckErrorEnum.MISSING_REQUIRED_ARGUMENT.getCode(),ArgumentCheckErrorEnum.MISSING_REQUIRED_ARGUMENT.getMessage());
        }
        msgRegisterVO.setTerminalTypeEnum(TerminalTypeEnum.getEnum(ThreadLocalContext.getSname()));
        msgRegisterVO.setUserId(ThreadLocalContext.getUserId());
        MsgPushServiceRegisterDTO dto = ObjectConvertUtil.map(msgRegisterVO,MsgPushServiceRegisterDTO.class);
        int i = this.msgPushRegisterFacade.msgPushServiceSignUp(dto);
        if(i <= 0){
            return Result.FAILURE("注册失败");
        }

        return Result.SUCESS();
    }


    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @LoginExclude
    @ApiOperation(value = "消息服务注册接口（极光）",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<Object> register(
            @ApiParam(name = "sn",value = "snNum或app唯一标识",required = true) @RequestParam(value = "sn") String sn,
            @ApiParam(name = "terminalType",value = "终端类型（1 IOS 2 Android 3 winphone  4 pos）",required = true) @RequestParam(value = "terminalType") Short terminalType,
            @ApiParam(name = "registerId",value = "第三方（极光）注册ID",required = true) @RequestParam(value = "registerId") String registerId){
        if(StringUtils.isBlank(sn) || registerId == null){
            log.info(String.format("snNum 或者 registerId 不能为null"));
            return Result.FAILURE(ArgumentCheckErrorEnum.MISSING_REQUIRED_ARGUMENT.getCode(),ArgumentCheckErrorEnum.MISSING_REQUIRED_ARGUMENT.getMessage());
        }
        log.info(String.format("POST /message/push/callback sn[%s] thirdMsgId[%s]",sn,registerId));
        try{
            this.msgPushRegisterFacade.jpushRegister(sn,registerId, TerminalTypeEnum.getEnum(terminalType));
        }catch (MessageException e){
            log.info("POST /message/push/callback 发生异常 {}",e.getMessage());
            return Result.FAILURE(e.getCode(),e.getMessage());
        }

        return Result.SUCESS();
    }


    @RequestMapping(value = "/aliyun/register",method = RequestMethod.POST)
    @LoginExclude
    @ApiOperation(value = "消息服务注册接口（阿里云）",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<Object> registerAliPush(
            @ApiParam(name = "sn",value = "snNum或app唯一标识",required = true) @RequestParam(value = "sn") String sn,
            @ApiParam(name = "terminalType",value = "终端类型（1 IOS 2 Android 3 winphone  4 pos）",required = true) @RequestParam(value = "terminalType") Short terminalType,
            @ApiParam(name = "registerId",value = "第三方（极光）注册ID",required = true) @RequestParam(value = "registerId") String registerId){
        if(StringUtils.isBlank(sn) || registerId == null){
            log.info(String.format("snNum 或者 registerId 不能为null"));
            return Result.FAILURE(ArgumentCheckErrorEnum.MISSING_REQUIRED_ARGUMENT.getCode(),ArgumentCheckErrorEnum.MISSING_REQUIRED_ARGUMENT.getMessage());
        }
        log.info(String.format("POST /message/aliyun/register sn[%s] thirdMsgId[%s]",sn,registerId));
        try{
            this.msgPushRegisterFacade.aliPushRegister(sn,registerId, TerminalTypeEnum.getEnum(terminalType));
        }catch (MessageException e){
            log.info("POST /message/push/callback 发生异常 {}",e.getMessage());
            return Result.FAILURE(e.getCode(),e.getMessage());
        }

        return Result.SUCESS();
    }
}
