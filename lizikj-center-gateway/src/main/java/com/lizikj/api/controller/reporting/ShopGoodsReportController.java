package com.lizikj.api.controller.reporting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.lizikj.api.utils.FreeMarkerExportExcelUtil;
import com.lizikj.api.vo.PageVO;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.reporting.CategoryMerchandiseSaleRankVO;
import com.lizikj.api.vo.reporting.ShopCategoryProduceReportVO;
import com.lizikj.api.vo.reporting.ShopGoodsProduceReportVO;
import com.lizikj.api.vo.reporting.ShopGoodsSaleInfoVO;
import com.lizikj.api.vo.reporting.ShopStaffProduceReportVO;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.PageParameter;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.login.interceptor.LoginExclude;
import com.lizikj.reporting.dto.CategoryMerchandiseSaleRankReportDTO;
import com.lizikj.reporting.dto.ShopCategoryProduceReportDTO;
import com.lizikj.reporting.dto.ShopGoodsProduceReportDTO;
import com.lizikj.reporting.dto.ShopGoodsSaleInfoDTO;
import com.lizikj.reporting.dto.ShopStaffProduceReportDTO;
import com.lizikj.reporting.dto.param.MerchandiseSaleRankParam;
import com.lizikj.reporting.dto.param.ShopGoodsSaleDetailQueryParam;
import com.lizikj.reporting.dto.param.ShopQueryBaseParam;
import com.lizikj.reporting.enums.ReportingErrorEnum;
import com.lizikj.reporting.facade.IShopGoodsReportFacade;

import io.jsonwebtoken.lang.Collections;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author Michael.Huang
 * @date 2018/7/20 14:24
 */
@RestController
@RequestMapping(value = "/reporting/shop")
@Api(value = "shopGoodsReport", tags = "店铺物品统计相关接口汇总")
public class ShopGoodsReportController{
	@Value("${export.excel.template.path}")
	private String excelBuilPath;
    /**
     * @private
     */
    private static Logger logger = LoggerFactory.getLogger(ShopGoodsReportController.class);
    /**
     * 店铺物品统计服务引用
     */
    @Autowired
    IShopGoodsReportFacade shopGoodsReportFacade;


    /**
     * 获取店铺美食消费明细统计报表
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    @RequestMapping(value = "/countShopGoodsProduceReport", method = RequestMethod.GET)
    @ApiOperation(value = "获取店铺美食消费明细统计报表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result<List<ShopGoodsProduceReportVO>> countShopGoodsProduceReport(@ApiParam(name = "startTime", value = "开始时间(yyyy-mm-dd hh:ss:mm)")
                                                                       @RequestParam(value = "startTime", required = false) Long startTime,
                                                                       @ApiParam(name = "endTime", value = "结束时间(yyyy-mm-dd hh:ss:mm)")
                                                                       @RequestParam(value = "endTime", required = false) Long endTime,
																	   @ApiParam(name = "categoryId", value = "美食分类Id")
																	   @RequestParam(value = "categoryId", required = false) String categoryId) {

        Long shopId = ThreadLocalContext.getShopId();
        if(null == ThreadLocalContext.getUserId() || null == shopId){
        	return Result.FAILURE("请登陆!");
        }
        ShopQueryBaseParam query = new ShopQueryBaseParam();
        query.setShopId(shopId);

        if (startTime != null) {
            query.setStartTime(new Date(startTime));
        }
        if (endTime != null) {
            query.setEndTime(new Date(endTime));
        }
        if(StringUtils.isNotEmpty(categoryId)){
            query.setCategoryId(categoryId);
        }
        try {


            List<ShopGoodsProduceReportDTO> list = shopGoodsReportFacade.countShopGoodsProduceReport(query);
            if (list != null) {
                List<ShopGoodsProduceReportVO> targetLs = ObjectConvertUtil.mapList(list, ShopGoodsProduceReportDTO.class, ShopGoodsProduceReportVO.class);
                return Result.SUCESS(targetLs);
            } else {
                return Result.SUCESS(null);
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("获取店铺美食消费明细统计报表,message={}", e);
            }

            return Result.FAILURE(e.getMessage());
        }
    }


    /**
     * 统计店铺美食分类出品情况
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    @RequestMapping(value = "/countShopCategoryProduceReport", method = RequestMethod.GET)
    @ApiOperation(value = "统计店铺美食分类出品情况", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result<List<ShopCategoryProduceReportVO>> countShopCategoryProduceReport(@ApiParam(name = "startTime", value = "开始时间(yyyy-mm-dd hh:ss:mm)")
                                                                             @RequestParam(value = "startTime", required = false) Long startTime,
                                                                             @ApiParam(name = "endTime", value = "结束时间(yyyy-mm-dd hh:ss:mm)")
                                                                             @RequestParam(value = "endTime", required = false) Long endTime,
                                                                             @ApiParam(name = "categoryId", value = "美食分类Id")
                                                                             @RequestParam(value = "categoryId", required = false) String categoryId) {
        Long shopId = ThreadLocalContext.getShopId();
        if(null == ThreadLocalContext.getUserId() || null == shopId){
        	return Result.FAILURE("请登陆!");
        }
        ShopQueryBaseParam query = new ShopQueryBaseParam();
        query.setShopId(shopId);
        if (startTime != null) {
            query.setStartTime(new Date(startTime));
        }
        if (endTime != null) {
            query.setEndTime(new Date(endTime));
        }
        if(StringUtils.isNotEmpty(categoryId)){
            query.setCategoryId(categoryId);
        }
        try {
            List<ShopCategoryProduceReportDTO> list = shopGoodsReportFacade.countShopCategoryProduceReport(query);

            if (list != null) {
                List<ShopCategoryProduceReportVO> targetLs = ObjectConvertUtil.mapList(list, ShopCategoryProduceReportDTO.class, ShopCategoryProduceReportVO.class);
                return Result.SUCESS(targetLs);
            } else {
                return Result.SUCESS(null);
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("统计店铺美食分类出品情况,message={}", e);
            }

            return Result.FAILURE(e.getMessage());
        }
    }

    /**
     * 店铺员工销售统计报表
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    @RequestMapping(value = "/countShopStaffSaleReport", method = RequestMethod.GET)
    @ApiOperation(value = "店铺员工销售统计报表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result<List<ShopStaffProduceReportVO>> countShopStaffSaleReport(@ApiParam(name = "startTime", value = "开始时间(yyyy-mm-dd hh:ss:mm)")
                                                                    @RequestParam(value = "startTime", required = false) Long startTime,
                                                                    @ApiParam(name = "endTime", value = "结束时间(yyyy-mm-dd hh:ss:mm)")
                                                                    @RequestParam(value = "endTime", required = false) Long endTime) {
        Long shopId = ThreadLocalContext.getShopId();
        if(null == ThreadLocalContext.getUserId() || null == shopId){
        	return Result.FAILURE("请登陆!");
        }
        ShopQueryBaseParam query = new ShopQueryBaseParam();
        query.setShopId(shopId);
        if (startTime != null) {
            query.setStartTime(new Date(startTime));
        }
        if (endTime != null) {
            query.setEndTime(new Date(endTime));
        }
        try {
            List<ShopStaffProduceReportDTO> list = shopGoodsReportFacade.countShopStaffSaleReport(query);
            if (list != null) {
                List<ShopStaffProduceReportVO> targetLs = ObjectConvertUtil.mapList(list, ShopStaffProduceReportDTO.class, ShopStaffProduceReportVO.class);
                return Result.SUCESS(targetLs);
            } else {
                return Result.SUCESS(null);
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("店铺员工销售统计报表,message={}", e);
            }

            return Result.FAILURE(e.getMessage());
        }
    }

    /**
     * 查询店铺美食销售详情
     *
     * @param startTime
     * @param endTime
     * @param staffId
     * @param orderNo
     * @param categoryId
     * @param goodsId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/queryShopGoodsSaleDetail", method = RequestMethod.GET)
    @ApiOperation(value = "店铺员工销售统计报表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result<PageVO<ShopGoodsSaleInfoVO>> queryShopGoodsSaleDetail(@ApiParam(name = "startTime", value = "开始时间(yyyy-mm-dd hh:ss:mm)")
                                                                 @RequestParam(value = "startTime", required = false) Long startTime,
                                                                 @ApiParam(name = "endTime", value = "结束时间(yyyy-mm-dd hh:ss:mm)")
                                                                 @RequestParam(value = "endTime", required = false) Long endTime,
                                                                 @ApiParam(name = "staffId", value = "员工ID，-1查询所有员工")
                                                                 @RequestParam(value = "staffId", required = false) Long staffId,
                                                                 @ApiParam(name = "orderNo", value = "订单编号")
                                                                 @RequestParam(value = "orderNo", required = false) String orderNo,
                                                                 @ApiParam(name = "categoryId", value = "分类Id")
                                                                 @RequestParam(value = "categoryId", required = false) String categoryId,
                                                                 @ApiParam(name = "goodsId", value = "物品Id")
                                                                 @RequestParam(value = "goodsId", required = false) String goodsId,
                                                                 @ApiParam(name = "pageNo", value = "页码")
                                                                 @RequestParam(value = "pageNo", required = false) int pageNo,
                                                                 @ApiParam(name = "pageSize", value = "页面size")
                                                                 @RequestParam(value = "pageSize", required = false) int pageSize) {

        ShopGoodsSaleDetailQueryParam query = new ShopGoodsSaleDetailQueryParam();

        Long shopId = ThreadLocalContext.getShopId();
        if(null == ThreadLocalContext.getUserId() || null == shopId){
        	return Result.FAILURE("请登陆!");
        }
        query.setShopId(shopId);
        if (startTime != null) {
            query.setStartTime(new Date(startTime));
        }
        if (endTime != null) {
            query.setEndTime(new Date(endTime));
        }
        query.setGoodsId(goodsId);
        query.setCategoryId(categoryId);
        query.setOrderNo(orderNo);
        query.setStaffId(staffId);
        PageParameter pageParameter = new PageParameter();
        if (pageSize <= 0) {
            pageParameter.setPageSize(10);
        } else {
            pageParameter.setPageSize(pageSize);
        }
        if (pageNo <= 0) {
            pageParameter.setPageNum(1);
        } else {
            pageParameter.setPageNum(pageNo);
        }
        try {
            PageInfo<ShopGoodsSaleInfoDTO> pageInfo = shopGoodsReportFacade.queryShopGoodsSaleDetail(query, pageParameter);

            List<ShopGoodsSaleInfoVO> targetLs = ObjectConvertUtil.mapList(pageInfo.getList(), ShopGoodsSaleInfoDTO.class, ShopGoodsSaleInfoVO.class);

            PageVO<ShopGoodsSaleInfoVO> pageVo = new PageVO<ShopGoodsSaleInfoVO>();
            pageVo.setPageSize(pageInfo.getPageSize());
            pageVo.setPageNo(pageInfo.getPageNum());
            pageVo.setTotal(pageInfo.getTotal());
            pageVo.setPages(pageInfo.getPages());
            pageVo.setList(targetLs);
            return Result.SUCESS(pageVo);

        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("查询店铺销售美食详情异常,message={}", e);
            }

            return Result.FAILURE(e.getMessage());
        }
    }
    
    @RequestMapping(value = "/queryShopSaleCommissionsReport", method = RequestMethod.POST)
    @ApiOperation(value = "查询门店销售明细表（含提成）", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<PageVO<ShopGoodsSaleInfoVO>> queryShopSaleCommissionsReport(@RequestBody ShopGoodsSaleDetailQueryParam queryParam,
    		 @ApiParam(name = "pageNum",value = "页码",defaultValue = "1") @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
             @ApiParam(name = "pageSize",value = "每页数量",defaultValue = "25") @RequestParam(value = "pageSize",defaultValue = "25",required = false) int pageSize) {
    	if (queryParam == null || queryParam.getShopId() == null) {
    		return Result.FAILURE(ReportingErrorEnum.PARAMETERS_ERROR.getMessage());
    	}
    	PageParameter pageParameter = new PageParameter(pageNum, pageSize);
    	ShopGoodsSaleDetailQueryParam query = new ShopGoodsSaleDetailQueryParam();
    	query.setCategoryId(queryParam.getCategoryId());
    	query.setGoodsId(queryParam.getGoodsId());
    	query.setOrderNo(queryParam.getOrderNo());
    	query.setShopId(queryParam.getShopId());
    	query.setStaffId(queryParam.getStaffId());
    	query.setStartTime(queryParam.getStartTime());
    	query.setEndTime(queryParam.getEndTime());
    	PageInfo<ShopGoodsSaleInfoDTO> pageInfo = shopGoodsReportFacade.queryShopGoodsSaleDetail(query, pageParameter);
    	List<ShopGoodsSaleInfoVO> list = ObjectConvertUtil.copyListProperties(pageInfo.getList(), ShopGoodsSaleInfoVO.class);
    	PageVO<ShopGoodsSaleInfoVO> pageVo = new PageVO<>(pageNum, pageSize, pageInfo.getPages(), pageInfo.getTotal(), list);
    	return Result.SUCESS(pageVo);
    }

	@RequestMapping(value = "/export/shopSaleCommissionsExcel", method = RequestMethod.GET)
	@ApiOperation(value = "导出门店销售明细表（含提成）", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void exportShopSaleCommissions(HttpServletRequest request, HttpServletResponse response,
			@ApiParam(name = "shopId", value = "门店ID")@RequestParam(name = "shopId", required = true)Long shopId,
			@ApiParam(name = "goodsId", value = "菜品ID")@RequestParam(name = "goodsId", required = false)String goodsId,
			@ApiParam(name = "stffId", value = "下单人员工ID")@RequestParam(name = "stffId", defaultValue = "-1", required = false)Long stffId,
			@ApiParam(name = "categoryId", value = "菜品分类ID")@RequestParam(name = "categoryId", required = false)String categoryId,
			@ApiParam(name = "startTime", value = "开始时间")@RequestParam(name = "startTime", required = false)Date startTime,
    		@ApiParam(name = "endTime", value = "结束时间")@RequestParam(name = "endTime", required = false)Date endTime,
			@ApiParam(name = "orderNo", value = "订单号")@RequestParam(name = "orderNo", required = false)String orderNo,
			@ApiParam(name = "pageNum", value = "页码", defaultValue = "1") @RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum,
			@ApiParam(name = "pageSize", value = "每页数量", defaultValue = "25") @RequestParam(value = "pageSize", defaultValue = "25", required = false) int pageSize) {
		FreeMarkerExportExcelUtil util = new FreeMarkerExportExcelUtil(request, response, excelBuilPath);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {
			shopId = ThreadLocalContext.getShopId();
			PageParameter pageParameter = new PageParameter(pageNum, pageSize);
			ShopGoodsSaleDetailQueryParam query = new ShopGoodsSaleDetailQueryParam();
			query.setCategoryId(categoryId);
			query.setGoodsId(goodsId);
			query.setOrderNo(orderNo);
			query.setShopId(shopId);
			query.setStaffId(stffId);
			query.setStartTime(startTime);
			query.setEndTime(endTime);
			PageInfo<ShopGoodsSaleInfoDTO> pageInfo = shopGoodsReportFacade.queryShopGoodsSaleDetail(query, pageParameter);

			List<ShopGoodsSaleInfoDTO> list = pageInfo.getList();
			if (!Collections.isEmpty(list)) {
				dataMap.put("list", JSONArray.toJSON(list));
			}
			util.export("shopSaleCommissionsTemplate.ftl", dataMap, "销售提成明细表.xls");
		} catch (IOException e) {
			logger.error("导出门店销售明细表（含提成）excel出错,shopId:{},message={}", shopId, e);
		}
	}
    
    @RequestMapping(value = "/getShopStaffSale4summaryReport", method = RequestMethod.POST)
    @ApiOperation(value = "查询门店员工销售提成明细表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<List<ShopStaffProduceReportVO>> getShopStaffSale4summaryReport(
    		@ApiParam(name = "shopId", value = "门店ID")@RequestParam(name = "shopId", required = true)Long shopId,
    		@ApiParam(name = "staffId", value = "员工ID")@RequestParam(name = "staffId", required = false)Long staffId,
			@ApiParam(name = "startTime", value = "开始时间")@RequestParam(name = "startTime", required = false)Date startTime,
    		@ApiParam(name = "endTime", value = "结束时间")@RequestParam(name = "endTime", required = false)Date endTime) {
    	ShopGoodsSaleDetailQueryParam query = new ShopGoodsSaleDetailQueryParam();
    	query.setShopId(shopId);
    	query.setStaffId(staffId);
    	query.setStartTime(startTime);
    	query.setEndTime(endTime);
    	List<ShopStaffProduceReportDTO> list = shopGoodsReportFacade.getShopStaffSale4summary(query);
    	List<ShopStaffProduceReportVO> vos = ObjectConvertUtil.copyListProperties(list, ShopStaffProduceReportVO.class);
    	return Result.SUCESS(vos);
    }
    
    @LoginExclude
    @RequestMapping(value = "/export/shopStaffSale4summaryExcel", method = RequestMethod.GET)
    @ApiOperation(value = "导出门店员工销售提成明细表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void exportShopStaffSale4summary(HttpServletRequest request, HttpServletResponse response,
    		@ApiParam(name = "shopId", value = "门店ID")@RequestParam(name = "shopId", required = true)Long shopId,
    		@ApiParam(name = "staffId", value = "员工ID")@RequestParam(name = "staffId", required = false)Long staffId,
			@ApiParam(name = "startTime", value = "开始时间")@RequestParam(name = "startTime", required = false)Date startTime,
    		@ApiParam(name = "endTime", value = "结束时间")@RequestParam(name = "endTime", required = false)Date endTime) {
    	FreeMarkerExportExcelUtil util = new FreeMarkerExportExcelUtil(request, response, excelBuilPath);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		ShopGoodsSaleDetailQueryParam query = new ShopGoodsSaleDetailQueryParam();
    	query.setShopId(shopId);
    	query.setStaffId(staffId);
    	query.setStartTime(startTime);
    	query.setEndTime(endTime);
    	try {
    		List<ShopStaffProduceReportDTO> list = shopGoodsReportFacade.getShopStaffSale4summary(query);
    		dataMap.put("list",  JSONArray.toJSON(list));
			util.export("shopStaffSale4summaryTemplate.ftl", dataMap, "门店员工销售提成明细表.xls");
		} catch (Exception e) {
			logger.error("导出门店员工销售提成明细表excel出错,shopId:{},message={}", shopId, e);
		}
    }
    
    @RequestMapping(value = "/countShopGoods4summaryReport", method = RequestMethod.POST)
    @ApiOperation(value = "查询菜品销售提成明细表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<List<ShopGoodsProduceReportVO>> countShopGoods4summaryReport(
    		@ApiParam(name = "shopId", value = "门店ID")@RequestParam(name = "shopId", required = true)Long shopId,
    		@ApiParam(name = "staffId", value = "员工ID")@RequestParam(name = "staffId", required = false)Long staffId,
			@ApiParam(name = "startTime", value = "开始时间")@RequestParam(name = "startTime", required = false)Date startTime,
    		@ApiParam(name = "endTime", value = "结束时间")@RequestParam(name = "endTime", required = false)Date endTime) {
    	ShopGoodsSaleDetailQueryParam query = new ShopGoodsSaleDetailQueryParam();
    	query.setShopId(shopId);
    	query.setStaffId(staffId);
    	query.setStartTime(startTime);
    	query.setEndTime(endTime);
    	List<ShopGoodsProduceReportDTO> list = shopGoodsReportFacade.countShopGoods4summary(query);
    	List<ShopGoodsProduceReportVO> vos = ObjectConvertUtil.copyListProperties(list, ShopGoodsProduceReportVO.class);
    	return Result.SUCESS(vos);
    }
    
    @RequestMapping(value = "/export/shopGoods4summaryExcel", method = RequestMethod.GET)
    @ApiOperation(value = "导出菜品销售提成明细表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void exportShopGoods4summary(HttpServletRequest request, HttpServletResponse response,
    		@ApiParam(name = "shopId", value = "门店ID")@RequestParam(name = "shopId", required = true)Long shopId,
    		@ApiParam(name = "staffId", value = "员工ID")@RequestParam(name = "staffId", required = false)Long staffId,
			@ApiParam(name = "startTime", value = "开始时间")@RequestParam(name = "startTime", required = false)Date startTime,
    		@ApiParam(name = "endTime", value = "结束时间")@RequestParam(name = "endTime", required = false)Date endTime) {
    	shopId = ThreadLocalContext.getShopId();
    	FreeMarkerExportExcelUtil util = new FreeMarkerExportExcelUtil(request, response, excelBuilPath);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		ShopGoodsSaleDetailQueryParam query = new ShopGoodsSaleDetailQueryParam();
    	query.setShopId(shopId);
    	query.setStaffId(staffId);
    	query.setStartTime(startTime);
    	query.setEndTime(endTime);
    	try {
    		List<ShopGoodsProduceReportDTO> list = shopGoodsReportFacade.countShopGoods4summary(query);
    		dataMap.put("list",  JSONArray.toJSON(list));
			util.export("shopGoods4summaryTemplate.ftl", dataMap, "菜品销售提成明细表.xls");
		} catch (Exception e) {
			logger.error("导出菜品销售提成明细表excel出错,shopId:{},message={}", shopId, e);
		}
    }
    
    @RequestMapping(value = "/export/shopStaffSaleReportExcel", method = RequestMethod.GET)
    @ApiOperation(value = "导出服务员消费明细表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void exportShopStaffSaleReport(HttpServletRequest request, HttpServletResponse response,
    		@ApiParam(name = "shopId", value = "门店ID")@RequestParam(name = "shopId", required = true)Long shopId,
    		@ApiParam(name = "staffId", value = "员工ID")@RequestParam(name = "staffId", required = false)Long staffId,
			@ApiParam(name = "startTime", value = "开始时间")@RequestParam(name = "startTime", required = false)Date startTime,
    		@ApiParam(name = "endTime", value = "结束时间")@RequestParam(name = "endTime", required = false)Date endTime) {
    	FreeMarkerExportExcelUtil util = new FreeMarkerExportExcelUtil(request, response, excelBuilPath);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		shopId = ThreadLocalContext.getShopId();
		ShopGoodsSaleDetailQueryParam query = new ShopGoodsSaleDetailQueryParam();
    	query.setShopId(shopId);
    	query.setStaffId(staffId);
    	query.setStartTime(startTime);
    	query.setEndTime(endTime);
    	try {
    		List<ShopStaffProduceReportDTO> list = shopGoodsReportFacade.countShopStaffSaleReport(query);
    		dataMap.put("list",  JSONArray.toJSON(list));
			util.export("shopStaffSaleReportTemplate.ftl", dataMap, "服务员消费明细表.xls");
		} catch (Exception e) {
			logger.error("导出服务员消费明细表excel出错,shopId:{},message={}", shopId, e);
		}
    }
    
    @RequestMapping(value = "/export/shopCategoryProduceReportExcel", method = RequestMethod.GET)
    @ApiOperation(value = "导出消费菜品类型汇总报表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void exportShopCategoryProduceReport(HttpServletRequest request, HttpServletResponse response,
    		@ApiParam(name = "shopId", value = "门店ID")@RequestParam(name = "shopId", required = true)Long shopId,
    		@ApiParam(name = "staffId", value = "员工ID")@RequestParam(name = "staffId", required = false)Long staffId,
			@ApiParam(name = "startTime", value = "开始时间")@RequestParam(name = "startTime", required = false)Date startTime,
    		@ApiParam(name = "endTime", value = "结束时间")@RequestParam(name = "endTime", required = false)Date endTime) {
    	FreeMarkerExportExcelUtil util = new FreeMarkerExportExcelUtil(request, response, excelBuilPath);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		shopId = ThreadLocalContext.getShopId();
		ShopGoodsSaleDetailQueryParam query = new ShopGoodsSaleDetailQueryParam();
    	query.setShopId(shopId);
    	query.setStaffId(staffId);
    	query.setStartTime(startTime);
    	query.setEndTime(endTime);
    	try {
    		List<ShopCategoryProduceReportDTO> list = shopGoodsReportFacade.countShopCategoryProduceReport(query);
    		dataMap.put("list",  JSONArray.toJSON(list));
			util.export("shopCategoryProduceReportTemplate.ftl", dataMap, "消费菜品类型汇总表.xls");
		} catch (Exception e) {
			logger.error("导出消费菜品类型汇总报表excel出错,shopId:{},message={}", shopId, e);
		}
    }
    
    @RequestMapping(value = "/export/shopGoodsProduceReportExcel", method = RequestMethod.GET)
    @ApiOperation(value = "导出消费菜品明细报表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void exportShopGoodsProduceReport(HttpServletRequest request, HttpServletResponse response,
    		@ApiParam(name = "shopId", value = "门店ID")@RequestParam(name = "shopId", required = true)Long shopId,
    		@ApiParam(name = "staffId", value = "员工ID")@RequestParam(name = "staffId", required = false)Long staffId,
			@ApiParam(name = "startTime", value = "开始时间")@RequestParam(name = "startTime", required = false)Date startTime,
    		@ApiParam(name = "endTime", value = "结束时间")@RequestParam(name = "endTime", required = false)Date endTime,
                                             @ApiParam(name = "categoryId", value = "美食分类Id")
                                                 @RequestParam(value = "categoryId", required = false) String categoryId) {
    	FreeMarkerExportExcelUtil util = new FreeMarkerExportExcelUtil(request, response, excelBuilPath);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		shopId = ThreadLocalContext.getShopId();
		ShopGoodsSaleDetailQueryParam query = new ShopGoodsSaleDetailQueryParam();
    	query.setShopId(shopId);
    	query.setStaffId(staffId);
    	query.setStartTime(startTime);
    	query.setEndTime(endTime);
    	if(org.apache.commons.lang.StringUtils.isNotEmpty(categoryId)){
    	    query.setCategoryId(categoryId);
        }
    	try {
    		List<ShopGoodsProduceReportDTO> list = shopGoodsReportFacade.countShopGoodsProduceReport(query);
    		dataMap.put("list",  JSONArray.toJSON(list));
			util.export("shopGoodsProduceReportTemplate.ftl", dataMap, "消费菜品明细报表.xls");
		} catch (Exception e) {
			logger.error("导出消费菜品明细报表excel出错,shopId:{},message={}", shopId, e);
		}
    }
    
    /**
     * 导出美食销售报表excel
     * @param request
     * @param response
     * @param param
     * @param pageNum
     * @param pageSize 
     * @return void
     * @author liaojw
     * @date 2018年7月24日 上午11:27:11
     */
    @ApiOperation(value = "导出美食销售报表excel",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/export/shopGoodsSaleRank", method = RequestMethod.GET)
    public void exportShopGoodsSaleRankExcel(HttpServletRequest request, HttpServletResponse response,
    		@ApiParam(name = "shopId", value = "门店ID")@RequestParam(name = "shopId", required = true)Long shopId,
    		@ApiParam(name = "categoryId", value = "分类ID 如果分类为套餐，该ID值为'套餐'")@RequestParam(name = "categoryId", required = false)String categoryId,
    		@ApiParam(name = "startTime", value = "开始时间")@RequestParam(name = "startTime", required = false)Date startTime,
    		@ApiParam(name = "endTime", value = "结束时间")@RequestParam(name = "endTime", required = false)Date endTime,
    		@ApiParam(name = "pageNum",value = "页码",defaultValue = "1") @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
            @ApiParam(name = "pageSize",value = "每页数量",defaultValue = "25") @RequestParam(value = "pageSize",defaultValue = "25",required = false) int pageSize) {
    	PageParameter pageParameter = new PageParameter(pageNum, pageSize); 
    	FreeMarkerExportExcelUtil util = new FreeMarkerExportExcelUtil(request, response, excelBuilPath);
    	Map<String, Object> dataMap = new HashMap<String, Object>();
    	shopId = ThreadLocalContext.getShopId();
    	MerchandiseSaleRankParam queryParam = new MerchandiseSaleRankParam();
    	queryParam.setShopId(shopId);
    	queryParam.setCategoryId(categoryId);
    	queryParam.setStartTime(startTime);
    	queryParam.setEndTime(endTime);
    	PageInfo<CategoryMerchandiseSaleRankReportDTO> pageInfo = shopGoodsReportFacade.getMerchandiseSaleRankList(queryParam, pageParameter);
    	List<CategoryMerchandiseSaleRankReportDTO> categoryList = pageInfo.getList() !=null?pageInfo.getList():new ArrayList<CategoryMerchandiseSaleRankReportDTO>();
		try {
			if (!Collections.isEmpty(categoryList)) {
				dataMap.put("totalRankList", JSONArray.toJSON(categoryList.get(0).getItems()));
				if (categoryList.size() > 1) {
					dataMap.put("categoryRankList", JSONArray.toJSON(categoryList.subList(1, categoryList.size())));
				} else {
					dataMap.put("categoryRankList", null);
				}
	    	}
			util.export("merchandisSaleRankTemplate.ftl", dataMap, "美食销售排行.xls");
		} catch (IOException e) {
			logger.error("导出美食销售排行excel出错,shopId:{},message={}", shopId, e);
		}
    }
    
    @ApiOperation(value = "查询美食销售报表",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value ="/getMerchandisSaleRankList", method = RequestMethod.GET)
    public Result<PageVO<CategoryMerchandiseSaleRankVO>> getMerchandisSaleRankList(HttpServletRequest request, HttpServletResponse response,
    		@ApiParam(name = "shopId", value = "门店ID")@RequestParam(name = "shopId", required = true)Long shopId,
    		@ApiParam(name = "categoryId", value = "分类ID 如果分类为套餐，该ID值为'套餐'")@RequestParam(name = "categoryId", required = false)String categoryId,
    		@ApiParam(name = "startTime", value = "开始时间")@RequestParam(name = "startTime", required = false)Date startTime,
    		@ApiParam(name = "endTime", value = "结束时间")@RequestParam(name = "endTime", required = false)Date endTime,
    		@ApiParam(name = "pageNum",value = "页码",defaultValue = "1") @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
            @ApiParam(name = "pageSize",value = "每页数量",defaultValue = "25") @RequestParam(value = "pageSize",defaultValue = "25",required = false) int pageSize) {
    	PageParameter pageParameter = new PageParameter(pageNum, pageSize); 
    	shopId = ThreadLocalContext.getShopId();
    	MerchandiseSaleRankParam queryParam = new MerchandiseSaleRankParam();
    	queryParam.setShopId(shopId);
    	queryParam.setCategoryId(categoryId);
    	queryParam.setStartTime(startTime);
    	queryParam.setEndTime(endTime);
    	PageInfo<CategoryMerchandiseSaleRankReportDTO> pageInfo = shopGoodsReportFacade.getMerchandiseSaleRankList(queryParam, pageParameter);
    	List<CategoryMerchandiseSaleRankVO> list = ObjectConvertUtil.copyListProperties(pageInfo.getList(), CategoryMerchandiseSaleRankVO.class);
    	PageVO<CategoryMerchandiseSaleRankVO> pageVo = new PageVO<>(pageNum, pageSize, pageInfo.getPages(), pageInfo.getTotal(), list);
    	return Result.SUCESS(pageVo);
    }
}
