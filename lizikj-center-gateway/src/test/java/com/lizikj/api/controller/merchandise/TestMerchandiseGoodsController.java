package com.lizikj.api.controller.merchandise;

import static com.lizikj.common.util.ThreadLocalContext.LOGIN_SHOP_ID;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lizikj.api.Bootstrap;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.merchandise.CalcPriceMethodVO;
import com.lizikj.api.vo.merchandise.CategoryVO;
import com.lizikj.api.vo.merchandise.CookingMethodVO;
import com.lizikj.api.vo.merchandise.GoodsAndCategoryVO;
import com.lizikj.api.vo.merchandise.GoodsVO;
import com.lizikj.api.vo.merchandise.SkuPropertyVO;
import com.lizikj.api.vo.merchandise.SkuValueVO;
import com.lizikj.api.vo.merchandise.SnackVO;
import com.lizikj.api.vo.merchandise.TagVO;
import com.lizikj.api.vo.merchandise.param.GoodsParamVO;
import com.lizikj.api.vo.merchandise.param.GoodsStateParamVO;
import com.lizikj.common.number.RandomUtils;
import com.lizikj.common.util.CollectionUtils;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.merchandise.enums.CalcMethodEnum;
import com.lizikj.merchandise.enums.PackageTypeEnum;
import com.lizikj.merchandise.enums.ShelveStateEnum;
import com.lizikj.merchandise.enums.SkuTypeEnum;

/**
 * 商品标签服务
 *
 * @author zhoufe
 * @date 2017/07/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=Bootstrap.class)//, properties={"spring.profiles.active=dev"}
@ActiveProfiles({"dev","redis.singleton"})//dev 环境，redis.singleton：redis集群与否
public class TestMerchandiseGoodsController {

	private static final Logger logger = LoggerFactory.getLogger(TestMerchandiseGoodsController.class);
	public static final String TEST_GOODSNAME_PREFIX = "测试商品_";

	private static long shopId = 1L;

	@Autowired
	private MerchandiseGoodsController merchandiseGoodsController;
	@Autowired
	private MerchandiseSkuController merchandiseSkuController;

	@Autowired
	private MerchandiseCategoryController merchandiseCategoryController;

	@Autowired
	private MerchandiseTagController merchandiseTagController;

	@Autowired
	private MerchandiseSnackController merchandiseSnackController;

	@Before
	public void init(){
		ThreadLocalContext.putThreadValue(LOGIN_SHOP_ID, shopId);
	}


	@Test
	public void testListAllGoodsAndCategories(){
		ThreadLocalContext.putThreadValue(LOGIN_SHOP_ID,42l);
		Result<GoodsAndCategoryVO> goodsAndCategoryVOResult = merchandiseGoodsController.listAllGoodsAndCategories_v2(null);
		logger.info("goodsAndCategoryVOResult："+ JSONObject.toJSONString(goodsAndCategoryVOResult));
	}

	@Test
	public void testlistGoodsByPackageType(){
		ThreadLocalContext.putThreadValue(LOGIN_SHOP_ID,1l);
		//Result result = merchandiseGoodsController.listGoodsByPackageType(PackageTypeEnum.DOUBLE_FIXED);
	}


	/**
	 * 测试售罄/沽清与否美食
	 * @params []
	 * @return void
	 * @author zhoufe
	 * @date 2017/9/4 21:04
	 */
	@Test
	public void testOnOutSell(){

		GoodsStateParamVO goodsStateParamVO = new GoodsStateParamVO();
		List<String> goodsIds = new ArrayList<>();
		goodsIds.add("59a63888472bb03310d6ce19");
		goodsIds.add("59a65c16472bb032a4c48203");
		goodsIds.add("59a65c30472bb02cd4040e60");
		//goodsStateParamVO.setGoodsIds(goodsIds);
		//goodsStateParamVO.setSellState(SellStateEnum.SELL_OUT);
		//merchandiseGoodsController.onOutSell(goodsStateParamVO);
	}



	/**
	 * 测试生成多规格商品
	 * @params []
	 * @return void
	 * @author zhoufe
	 * @date 2017/9/4 21:01
	 */
	@Test
	public void testSaveGoods(){
		GoodsParamVO goodsParamVO = new GoodsParamVO();
		//goodsParamVO.setChainGoodsId();
		goodsParamVO.setShopId(ThreadLocalContext.getShopId());
		goodsParamVO.setGoodsName(TEST_GOODSNAME_PREFIX + RandomUtils.getRandomNum(10));
		goodsParamVO.setCostPrice(10000L);
		goodsParamVO.setSellPrice(20000L);
		goodsParamVO.setMemberPrice(19000L);
		goodsParamVO.setPackageType(PackageTypeEnum.NONE);
		goodsParamVO.setShelveState(ShelveStateEnum.ON_LINE);
		goodsParamVO.setDescription(goodsParamVO.getGoodsName());
		goodsParamVO.setAlias(goodsParamVO.getGoodsName());
		goodsParamVO.setGoodsNumber(goodsParamVO.getGoodsName().replace(TEST_GOODSNAME_PREFIX, ""));
		List<Long> mediaIds = new ArrayList<>();
		mediaIds.add(1L);
		mediaIds.add(2L);
		mediaIds.add(2L);
		goodsParamVO.setMediaIds(mediaIds);
		CalcPriceMethodVO calcPriceMethodVO = new CalcPriceMethodVO();
		calcPriceMethodVO.setCalcMethod(CalcMethodEnum.NUMBER);
		calcPriceMethodVO.setMerchandiseUnitId(1L);
		goodsParamVO.setCalcPriceMethod(calcPriceMethodVO);
		goodsParamVO.setSkuType(SkuTypeEnum.COMPOSE);
		List<SkuPropertyVO> skuPropertyList = new ArrayList<>();
		SkuPropertyVO colorSkuPropertyVO = createProperty(1021, "颜色");
		List<SkuValueVO> colorSkuValueVOS = new ArrayList<>();
		createSkuValue(colorSkuPropertyVO, colorSkuValueVOS, 10211, "黑色");
		createSkuValue(colorSkuPropertyVO, colorSkuValueVOS, 10222, "白色");
		createSkuValue(colorSkuPropertyVO, colorSkuValueVOS, 10233, "蓝色");

		colorSkuPropertyVO.setValues(colorSkuValueVOS);

		SkuPropertyVO sizeSkuPropertyVO = createProperty(2022, "尺寸");
		List<SkuValueVO> sizeSkuValueVOS = new ArrayList<>();
		createSkuValue(sizeSkuPropertyVO, sizeSkuValueVOS, 20221, "M");
		createSkuValue(sizeSkuPropertyVO, sizeSkuValueVOS, 20222, "S");
		createSkuValue(sizeSkuPropertyVO, sizeSkuValueVOS, 20223, "XL");

		sizeSkuPropertyVO.setValues(sizeSkuValueVOS);

		skuPropertyList.add(sizeSkuPropertyVO);
		skuPropertyList.add(colorSkuPropertyVO);

		goodsParamVO.setSkuPropertyList(skuPropertyList);

//		Result<List<SkuVO>> listResult = merchandiseSkuController.generateSku(skuPropertyList);
//		goodsParamVO.setSkuList(listResult.getData());

		List<CategoryVO> categories = createCategories();
		goodsParamVO.setCategories(categories);

		List<SnackVO> snacks = createSnacks();
		goodsParamVO.setSnacks(snacks);

		List<TagVO> tags = createTags();
		goodsParamVO.setTags(tags);

		List<CookingMethodVO> cookingMethods = new ArrayList<>();
		CookingMethodVO cookingMethodVO = new CookingMethodVO();
		cookingMethodVO.setName("凉拌");
		cookingMethods.add(cookingMethodVO);
		goodsParamVO.setCookingMethods(cookingMethods);

		goodsParamVO.setStock(1000);

		Result<GoodsVO> stringResult = merchandiseGoodsController.saveGoods(goodsParamVO);
		logger.info("goodsId:"+ JSON.toJSONString(stringResult));
	}

	private List<TagVO> createTags() {
		Result<List<TagVO>> listResult = merchandiseTagController.listTags();
		List<TagVO> tagVOS = listResult.getData();
		if (CollectionUtils.isListBlank(tagVOS)){
			return null;
		}
		List<TagVO> newList = new ArrayList<>();
		TagVO tagVO = tagVOS.get(org.apache.commons.lang.math.RandomUtils.nextInt(tagVOS.size()));
		newList.add(tagVO);
		return newList;
	}

	private List<SnackVO> createSnacks() {
		Result<List<SnackVO>> listResult = merchandiseSnackController.listSnacks();
		List<SnackVO> snackVOS = listResult.getData();
		if (CollectionUtils.isListBlank(snackVOS)){
			return null;
		}
		List<SnackVO> newList = new ArrayList<>();
		SnackVO snackVO = snackVOS.get(org.apache.commons.lang.math.RandomUtils.nextInt(snackVOS.size()));
		newList.add(snackVO);
		return newList;
	}

	private List<CategoryVO> createCategories() {
		Result<List<CategoryVO>> listResult = merchandiseCategoryController.listCategories();
		List<CategoryVO> categoryVOS = listResult.getData();
		if (categoryVOS == null){
			return null;
		}

		CategoryVO categoryVO = categoryVOS.get(org.apache.commons.lang.math.RandomUtils.nextInt(categoryVOS.size()));
		List<CategoryVO> newList = new ArrayList<>();
		newList.add(categoryVO);
		return newList;
	}

	private void createSkuValue(SkuPropertyVO colorSkuPropertyVO, List<SkuValueVO> colorSkuValueVOS, int skuValueId, String skuValueName) {
		SkuValueVO skuValueVO1 = new SkuValueVO();
		//skuValueVO1.setId(skuValueId);
		//skuValueVO1.setSkuPropertyId(colorSkuPropertyVO.getId());
		skuValueVO1.setValue(skuValueName);
		colorSkuValueVOS.add(skuValueVO1);
	}

	private SkuPropertyVO createProperty(int id, String propertyName) {
		SkuPropertyVO colorSkuPropertyVO = new SkuPropertyVO();
		//colorSkuPropertyVO.setId(id);
		colorSkuPropertyVO.setName(propertyName);
		return colorSkuPropertyVO;
	}




}
