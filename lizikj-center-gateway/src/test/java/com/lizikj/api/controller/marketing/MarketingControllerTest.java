package com.lizikj.api.controller.marketing;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lizikj.api.Bootstrap;
import com.lizikj.common.util.DateUtils;
import com.lizikj.marketing.api.dto.DrawParamDTO;
import com.lizikj.marketing.api.dto.RedPacketItemDTO;
import com.lizikj.marketing.api.enums.PlayCodeEnum;
import com.lizikj.marketing.api.facade.IRedPacketReadFacade;
import com.lizikj.marketing.api.facade.IRedPacketWriteFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author lxl
 * @Date 2018/8/29 11:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=Bootstrap.class)//, properties={"spring.profiles.active=dev"}
@ActiveProfiles({"dev"})//dev 环境，redis.singleton：redis集群与否
public class MarketingControllerTest {
    private final static Logger log = LoggerFactory.getLogger(MarketingControllerTest.class);
    @Autowired
    private IRedPacketWriteFacade redPacketWriteFacade;
    @Autowired
    private IRedPacketReadFacade redPacketReadFacade;
    private DrawStatistics drawStatistics = new DrawStatistics();
    ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("market_pool_%d").setDaemon(true).build();
    ThreadPoolExecutor executor = new ThreadPoolExecutor(1024,2048,
                                              60,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(2048),factory,new ThreadPoolExecutor.CallerRunsPolicy());

    @Test
    public void listCommonStatics() throws Exception {
    }

    @Test
    public void listOrderUsablePrizes() throws Exception {
    }

    @Test
    public void listOrderUsablePrizes1() throws Exception {
    }

    @Test
    public void listOrderUsableCoupons() throws Exception {
    }

    @Test
    public void listOrderUsablePackets() throws Exception {
    }

    @Test
    public void shareOrder() throws Exception {
    }

    @Test
    public void drawPackets() throws Exception {
    }



    @Test
    public void testDrawByPlayCode() throws Exception {
        Random random = new Random();
        executor.submit(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log();
                }
            }
        });
        for(int i =0;i < 100000000;i++){
            DrawParamDTO drawParamDTO = new DrawParamDTO();
            drawParamDTO.setDrawUserId(random.nextLong());
            drawParamDTO.setPlayCodeEnum(PlayCodeEnum.RED_PACKET_FROM_CODE);
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    draw(drawParamDTO);
                }
            });
        }
    }

    private void log(){
        drawStatistics.out();
    }

    private void draw(DrawParamDTO drawParamDTO){
        long start = System.currentTimeMillis();
        List<RedPacketItemDTO> redPacketItemDTOS = new ArrayList<>();
        try {
            redPacketItemDTOS = redPacketWriteFacade.drawPackets(drawParamDTO);
        } catch (Exception e) {
            log.info("draw fail time={} ",System.currentTimeMillis()-start,e);
            drawStatistics.err.addAndGet(1);
        }
        long detla = System.currentTimeMillis() - start;
        log.info("draw result num={} time={}ms",redPacketItemDTOS.size(),detla);
        drawStatistics.num.addAndGet(redPacketItemDTOS.size());
        if (redPacketItemDTOS.size() > 0) {
            drawStatistics.time.addAndGet(detla);
        }else{
            drawStatistics.zero.addAndGet(1);
        }

    }

    @Test
    public void drawRemaining() throws Exception {
    }

    @Test
    public void shareOrderDraw() throws Exception {
    }

    @Test
    public void listUserPackets() throws Exception {
    }

    @Test
    public void drawCoupons() throws Exception {
    }

    @Test
    public void drawCoupons1() throws Exception {
    }

    @Test
    public void listUserCoupons() throws Exception {
    }

    @Test
    public void listUserCouponStatics() throws Exception {
    }


    private class DrawStatistics{
        public AtomicLong num = new AtomicLong(0);
        public AtomicLong time = new AtomicLong(0);
        private AtomicLong err = new AtomicLong(0);
        private AtomicLong zero = new AtomicLong(0);

        public void out(){
            log.info("statistics {} draw num={} total={} zero={} err={} avg_time={} ",
                    DateUtils.format(new Date(),DateUtils.FULL_INDENT_PATTERN),num.get(),time.get(),zero.get(),err.get(),time.get()/num.get());
        }
    }
}