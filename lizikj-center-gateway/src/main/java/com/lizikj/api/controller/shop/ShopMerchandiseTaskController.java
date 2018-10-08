package com.lizikj.api.controller.shop;

import java.util.List;

import com.lizikj.common.exception.BaseException;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.shop.api.facade.IShopMerchandiseTaskReadFacade;
import com.lizikj.shop.api.facade.IShopMerchandiseTaskWriteFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.shop.ShopMerchandiseTaskVO;
import com.lizikj.common.exception.ArgumentCheckException;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.shop.api.dto.ShopMerchandiseTaskDTO;
import com.lizikj.shop.api.exception.ShopException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * 店铺商品任务服务
 * @author zhoufe
 * @date 2017/07/10
 */
@Controller
@RequestMapping("/shop/merchandise/task")
@Api(value = "shop-merchandise-task", tags = "店铺商品任务API接口")
public class ShopMerchandiseTaskController {

    private static final Logger logger = LoggerFactory.getLogger(ShopMerchandiseTaskController.class);

    @Autowired
    private IShopMerchandiseTaskReadFacade shopMerchandiseTaskReadFacade;

    @Autowired
    private IShopMerchandiseTaskWriteFacade shopMerchandiseTaskWriteFacade;



    /**
     *  获取延迟删除任务
     * @return Result<List<ShopMerchandiseTaskVO>>
     * @author zhoufe
     * @date 2017年7月18日 上午10:38:45
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/listShopMerchandiseTask")
    @ApiOperation(value = "获取延迟删除任务", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<ShopMerchandiseTaskVO>> listShopMerchandiseTask() {
        Result<List<ShopMerchandiseTaskVO>> result;
        try {
            Long shopId = ThreadLocalContext.getShopId();
            List<ShopMerchandiseTaskDTO> shopMerchandiseTaskDTOS = shopMerchandiseTaskReadFacade.listShopMerchandiseTask(shopId);
            List<ShopMerchandiseTaskVO> shopMerchandiseTaskVOS = ObjectConvertUtil.copyListProperties(shopMerchandiseTaskDTOS, ShopMerchandiseTaskVO.class);
        	result = Result.SUCESS(shopMerchandiseTaskVOS);
        } catch (BaseException e) {
            logger.error("listShopMerchandiseTask Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        
        return result;
    }

    /**
     * 取消延迟删除
     * @param shopMerchandiseTaskIds
     * @return Result<Boolean>
     * @author zhoufe
     * @date 2017年7月18日 上午10:40:22
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/cancelShopMerchandiseTask")
    @ApiOperation(value = "取消延迟删除", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> cancelShopMerchandiseTask(
  	        @ApiParam(name = "taskIds", value = "taskIds", required = true)
    		@RequestBody List<Long> shopMerchandiseTaskIds) {
        Result<Boolean> result;
        try {
        	Boolean isSuccess = shopMerchandiseTaskWriteFacade.cancelShopMerchandiseTask(shopMerchandiseTaskIds);
        	result = Result.SUCESS(isSuccess);
        } catch (BaseException e) {
            logger.error("cancelShopMerchandiseTask Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        } 
        return result;
    }
    
    /**
     * 删除美食模板
     * @param
     * @return Result<Boolean>
     * @author zhoufe
     * @date 2017年7月18日 上午10:52:30
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/deleteChainGoods")
    @ApiOperation(value = "删除美食模板", notes = "删除美食模板", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> deleteChainGoods(
           @ApiParam(name = "goodsId", value = "goodsId", required = true)
           @RequestBody String goodsId) {
        Result<Boolean> result;
        try {
            Long shopId = ThreadLocalContext.getShopId();
            Boolean isSuccess = shopMerchandiseTaskWriteFacade.cancelShopMerchandiseTaskByShopIdAndGoodsId(shopId,goodsId);
        	result = Result.SUCESS(isSuccess);
        } catch (BaseException e) {
            logger.error("deleteChainGoods Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        } 
        return result;
    }

}
