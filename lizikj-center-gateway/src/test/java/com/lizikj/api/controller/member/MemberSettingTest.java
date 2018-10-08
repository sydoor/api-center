package com.lizikj.api.controller.member;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.lizikj.api.Bootstrap;
import com.lizikj.member.facade.IMemberSettingFacade;
import com.lizikj.merchant.dto.ShopMerchantExpandDTO;
import com.lizikj.merchant.facade.IShopMerchantReadFacade;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Bootstrap.class)//, properties={"spring.profiles.active=dev"}
@ActiveProfiles({"dev"})//dev 环境，redis.singleton：redis集群与否
public class MemberSettingTest {
    private final static Logger log = LoggerFactory.getLogger(MemberSettingTest.class);

    @Autowired
    IShopMerchantReadFacade shopMerchantFacade;
    @Autowired
    IMemberSettingFacade memberSettingFacade;

    @Test
    public void testInitialMerchantMemberDefault() throws Exception{
        List<ShopMerchantExpandDTO> merchantExpandDTOS = this.shopMerchantFacade.findList(null);
        if(merchantExpandDTOS != null){
            merchantExpandDTOS.forEach(t ->{
                this.memberSettingFacade.initialMerchantMemberDefault(t.getMerchantId());
                log.info("successful {}",t.getMerchantId());
            });
        }
    }
}
