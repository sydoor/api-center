package com.lizikj.api.controller.schedule;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.lizikj.api.Bootstrap;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.schedule.QueryScheduleQueueVO;
import com.lizikj.api.vo.schedule.ScheduleQueueVO;
import com.lizikj.common.enums.UserLoginSourceEnum;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.schedule.api.enums.QueueSourceEnum;
import com.lizikj.schedule.api.enums.QueueStatusEnum;
import com.lizikj.schedule.api.enums.QueueTypeEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author zhoufe
 * @date 2017/8/12 15:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=Bootstrap.class)//, properties={"spring.profiles.active=dev"}
@ActiveProfiles({"dev"})//dev 环境，redis.singleton：redis集群与否
public class TestScheduleQueueController {

    private static long shopId = 1L;


    private static Logger logger = LoggerFactory.getLogger(TestScheduleQueueController.class);

    @Autowired
    private ScheduleQueueController scheduleQueueController;

    @Before
    public void init(){

    }

    @Test
    public void testFindByScheduleQueueId(){

        Result<ScheduleQueueVO> scheduleQueueVOResult = scheduleQueueController.findByScheduleQueueId(173L);
        if (logger.isInfoEnabled()) {
            logger.info("findByScheduleQueueId-->.scheduleQueueVOResult:{}", JSONObject.toJSONString(scheduleQueueVOResult));
        }
    }


    @Test
    public void testH5UpdateScheduleQueue(){

        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SOURCE, UserLoginSourceEnum.H5.getValue());


        ScheduleQueueVO scheduleQueueParamVO = new ScheduleQueueVO();
        scheduleQueueParamVO.setScheduleQueueId(173L);
        scheduleQueueParamVO.setQueueStatus(QueueStatusEnum.CANCEL);

        scheduleQueueParamVO.setCancelOperatorId(1L);
        scheduleQueueParamVO.setShopId(1L);

        scheduleQueueController.updateScheduleQueue(scheduleQueueParamVO);

        testFindByScheduleQueueId();

    }


    @Test
    public void testPosUpdateScheduleQueue(){

        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, 1L);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SOURCE, UserLoginSourceEnum.POS.getValue());
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_STAFF_ID, 3L);


        ScheduleQueueVO scheduleQueueParamVO = new ScheduleQueueVO();
        long scheduleQueueId = 248L;
        scheduleQueueParamVO.setScheduleQueueId(scheduleQueueId);
        scheduleQueueParamVO.setQueueStatus(QueueStatusEnum.SEATED);

        scheduleQueueController.updateScheduleQueue(scheduleQueueParamVO);

        Result<ScheduleQueueVO> scheduleQueueVOResult = scheduleQueueController.findByScheduleQueueId(scheduleQueueId);
        if (logger.isInfoEnabled()) {
            logger.info("testPosUpdateScheduleQueue-->findByScheduleQueueId-->.scheduleQueueVOResult:{}", JSONObject.toJSONString(scheduleQueueVOResult));
        }

    }

    @Test
    public void testCleanScheduleQueue(){

        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, 8L);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SOURCE, UserLoginSourceEnum.POS.getValue());
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_STAFF_ID, 3L);

        scheduleQueueController.cleanScheduleQueue();

    }

    @Test
    public void testResetScheduleQueue(){

        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, 8L);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SOURCE, UserLoginSourceEnum.POS.getValue());
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_STAFF_ID, 3L);

        scheduleQueueController.resetScheduleQueue();

    }

    @Test
    public void testPosInsertScheduleQueue(){

        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, 8L);
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SOURCE, UserLoginSourceEnum.POS.getValue());
        ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_STAFF_ID, 3L);

        ScheduleQueueVO scheduleQueueParamVO = new ScheduleQueueVO();
        scheduleQueueParamVO.setPeoples(3);

        Result<ScheduleQueueVO> scheduleQueueVOResult = scheduleQueueController.insertScheduleQueue(scheduleQueueParamVO);

        if (logger.isInfoEnabled()) {
            logger.info("insertScheduleQueue-->.scheduleQueueVOResult:{}", JSONObject.toJSONString(scheduleQueueVOResult));
        }
    }



    @Test
    public void testH5InsertScheduleQueue(){

        ScheduleQueueVO scheduleQueueParamVO = new ScheduleQueueVO();
        scheduleQueueParamVO.setPeoples(3);
        scheduleQueueParamVO.setShopId(8L);
        scheduleQueueParamVO.setTakeUserId(4L);
        scheduleQueueParamVO.setQueueSource(QueueSourceEnum.H5);


        Result<ScheduleQueueVO> scheduleQueueVOResult = scheduleQueueController.insertScheduleQueue(scheduleQueueParamVO);

        if (logger.isInfoEnabled()) {
            logger.info("insertScheduleQueue-->.scheduleQueueVOResult:{}", JSONObject.toJSONString(scheduleQueueVOResult));
        }
    }


    @Test
    public void testPageListByCondition(){
        QueryScheduleQueueVO queryScheduleQueueVO = new QueryScheduleQueueVO();

        queryScheduleQueueVO.setShopId(9L);
        queryScheduleQueueVO.setQueueType(QueueTypeEnum.SMALL_DESK);
        queryScheduleQueueVO.setPageNum(1);
        queryScheduleQueueVO.setPageSize(5);

        Result<PageInfo<ScheduleQueueVO>> pageInfoResult = scheduleQueueController.listByCondition(queryScheduleQueueVO);
        if (logger.isInfoEnabled()) {
            logger.info("page 1-->.listByCondition:{}", JSONObject.toJSONString(pageInfoResult));
        }

        queryScheduleQueueVO.setPageNum(2);
        queryScheduleQueueVO.setPageSize(5);
        pageInfoResult = scheduleQueueController.listByCondition(queryScheduleQueueVO);
        if (logger.isInfoEnabled()) {
            logger.info("page 2-->.listByCondition:{}", JSONObject.toJSONString(pageInfoResult));
        }
    }

}
