package com.lizikj.api.controller.merchandise;

import com.alibaba.fastjson.JSONObject;
import com.lizikj.api.Bootstrap;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.merchandise.TagVO;
import com.lizikj.api.vo.merchandise.param.TagParamVO;
import com.lizikj.common.util.ThreadLocalContext;
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
 * 商品标签服务
 * 
 * @author zhoufe
 * @date 2017/07/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=Bootstrap.class)//, properties={"spring.profiles.active=dev"}
@ActiveProfiles({"dev","redis.singleton"})//dev 环境，redis.singleton：redis集群与否
public class TestMerchandiseTagController {

	private static final Logger logger = LoggerFactory.getLogger(TestMerchandiseTagController.class);
	public static final String TEST_TAG = "测试分类7758";

	private static long shopId = 1L;

	@Autowired
	private MerchandiseTagController merchandiseTagController;

	@Before
	public void init(){
		ThreadLocalContext.putThreadValue(ThreadLocalContext.LOGIN_SHOP_ID, shopId);
	}

	@Test
	public void testSaveTag(){

		TagParamVO tagParamVO = new TagParamVO();
		tagParamVO.setName(TEST_TAG);
		Result<String> stringResult = merchandiseTagController.saveTag(tagParamVO);
		logger.info("stringResult:"+ JSONObject.toJSONString(stringResult));

	}

	@Test
	public void testListTags(){

		Result<List<TagVO>> listResult = merchandiseTagController.listTags();
		logger.info("stringResult:"+JSONObject.toJSONString(listResult));
	}



}
