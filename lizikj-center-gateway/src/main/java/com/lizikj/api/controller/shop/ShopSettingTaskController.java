package com.lizikj.api.controller.shop;

import com.lizikj.api.vo.Result;
import com.lizikj.common.exception.ArgumentCheckException;
import com.lizikj.common.exception.BaseException;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.shop.api.dto.ShopSettingTaskDTO;
import com.lizikj.shop.api.enums.ShopSettingTaskTypeEnum;
import com.lizikj.shop.api.exception.ShopException;
import com.lizikj.shop.api.facade.IShopSettingTaskWriteFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 店铺配置任务
 * @author zhoufe
 * @date 2017/07/10
 */
@Controller
@RequestMapping("/shop/setting/task")
@Api(value = "shop-setting-task", tags = "店铺配置任务API接口")
public class ShopSettingTaskController {

    private static final Logger logger = LoggerFactory.getLogger(ShopSettingTaskController.class);

    @Autowired
    private IShopSettingTaskWriteFacade shopSettingTaskWriteFacade;

    /**
     * 生成定时任务
     * @return Result<Boolean>
     * @author zhoufe
     * @date 2017年7月11日 下午3:16:06
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/createShopSettingTask")
    @ApiOperation(value = "生成定时任务", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> createShopSettingTask(
            @ApiParam(name = "shopSettingId", value = "配置ID：那个配置要定期关闭", required = true)
            @RequestParam Long shopSettingId,

            @ApiParam(name = "taskType", value = "任务类型：见ShopSettingTaskTypeEnum：SHOP_SELF.店铺自有美食。", required = true)
            @RequestParam ShopSettingTaskTypeEnum taskType

    ) {
        Result<Boolean> result;
        try {
            Long merchantId = ThreadLocalContext.getMerchantId();
            Long shopId = ThreadLocalContext.getShopId();
            Long staffId = ThreadLocalContext.getStaffId();

            ShopSettingTaskDTO shopSettingTaskDTO = new ShopSettingTaskDTO();
            shopSettingTaskDTO.setMerchantId(merchantId);
            shopSettingTaskDTO.setShopId(shopId);
            shopSettingTaskDTO.setOperatorId(staffId);
            shopSettingTaskDTO.setShopSettingId(shopSettingId);
            shopSettingTaskDTO.setTaskType(taskType);
            Boolean isHas = shopSettingTaskWriteFacade.createShopSettingTask(shopSettingTaskDTO);
            result = Result.SUCESS(isHas);
        } catch (BaseException e) {
            logger.error("createShopSettingTask Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        return result;
    }
    
}
