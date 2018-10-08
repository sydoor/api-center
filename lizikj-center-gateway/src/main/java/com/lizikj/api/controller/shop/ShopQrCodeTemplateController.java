package com.lizikj.api.controller.shop;

import com.github.pagehelper.PageInfo;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.shop.ShopDeskQrcodeTemplateVO;
import com.lizikj.common.exception.BaseException;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;
import com.lizikj.merchant.dto.ShopDeskQrcodeTemplateDTO;
import com.lizikj.merchant.facade.IShopDeskQrcodeTemplateReadFacade;
import com.lizikj.merchant.facade.IShopWriteFacade;
import com.lizikj.opt.exception.OptException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 店铺二维码模板接口
 * @author zhoufe
 * @date 2017/07/10
 */
@Controller
@RequestMapping("/shop/qrcode")
@Api(value = "shop-qrcode", tags = "店铺二维码模板接口")
public class ShopQrCodeTemplateController {

    private static final Logger logger = LoggerFactory.getLogger(ShopQrCodeTemplateController.class);

    @Autowired
    private IShopDeskQrcodeTemplateReadFacade shopDeskQrcodeTemplateReadFacade;

    @Autowired
    private IShopWriteFacade shopWriteFacade;

    /**
     * 选择桌台的二维码模板
     * @return Result<List<ShopAreaVO>>
     * @author zhoufe
     * @date 2017年7月18日 下午3:31:23
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/desk/selectDeskQrCodeTemplate")
    @ApiOperation(value = "选择桌台的二维码模板", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> selectDeskQrCodeTemplate(
            @ApiParam(name = "qrCodeTempalteId", value = "模板ID", required = true)
            @RequestParam Long qrCodeTempalteId) {
        Result<Boolean> result;
        try {
            Long shopId = ThreadLocalContext.getShopId();
            Boolean succ = shopWriteFacade.selectDeskQrCodeTemplate(shopId, qrCodeTempalteId);
            result = Result.SUCESS(succ);
        } catch (BaseException e) {
            logger.error("selectDeskQrCodeTemplate Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }
        
        return result;
    }


    /**
     * 查询全部的桌台的二维码模
     * @return Result<List<ShopDeskQrcodeTemplateVO>>
     * @author zhoufe
     * @date 2017年7月18日 下午3:31:23
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/desk/template/listAll")
    @ApiOperation(value = "查询全部的桌台的二维码模板", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<ShopDeskQrcodeTemplateVO>> listAll() {
        Result<List<ShopDeskQrcodeTemplateVO>> result;
        try {
            List<ShopDeskQrcodeTemplateDTO> shopDeskQrcodeTemplateDTOS = shopDeskQrcodeTemplateReadFacade.listAll();
            List<ShopDeskQrcodeTemplateVO> shopDeskQrcodeTemplateVOS = ObjectConvertUtil.copyListProperties(shopDeskQrcodeTemplateDTOS, ShopDeskQrcodeTemplateVO.class);
            result = Result.SUCESS(shopDeskQrcodeTemplateVOS);
        } catch (BaseException e) {
            logger.error("listAll Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }


    /**
     * 查询所有模板-分页
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/desk/template/listAllPage/{pageNum}/{pageSize}",method = RequestMethod.GET)
    @ApiOperation(value = "查询所有模板-分页",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<PageInfo<ShopDeskQrcodeTemplateVO>> listAllPage(
            @ApiParam(name = "pageNum",value = "页码",required = true)
            @PathVariable Integer pageNum,
            @ApiParam(name = "pageSize",value = "页容量",required = true)
            @PathVariable Integer pageSize
    ){
        try{
            PageInfo<ShopDeskQrcodeTemplateDTO> pageDTOInfo = shopDeskQrcodeTemplateReadFacade.listAllPage(pageNum, pageSize);
            if (null == pageDTOInfo){
                PageInfo<Object> objectPageInfo = new PageInfo<>();
                objectPageInfo.setPageNum(pageNum);
                objectPageInfo.setPageSize(pageSize);
                return Result.SUCESS(objectPageInfo);
            }

            List<ShopDeskQrcodeTemplateDTO> list = pageDTOInfo.getList();
            List<ShopDeskQrcodeTemplateVO> shopDeskQrcodeTemplateVOS = ObjectConvertUtil.copyListProperties(list, ShopDeskQrcodeTemplateVO.class);
            PageInfo pageVOInfo = pageDTOInfo;
            pageVOInfo.setList(shopDeskQrcodeTemplateVOS);

            return Result.SUCESS(pageVOInfo);
        }catch (OptException e){
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            return Result.FAILURE(e.getCode(),e.getMessage());
        }
    }


    /**
     * 获取单个模板
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/desk/template/findById/{qrCodeTempalteId}",method = RequestMethod.GET)
    @ApiOperation(value = " 获取单个模板",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<ShopDeskQrcodeTemplateVO> findById(
            @ApiParam(name = "qrCodeTempalteId", value = "模板图片ID", required = true)
            @PathVariable Long qrCodeTempalteId
    ){
        try{
            ShopDeskQrcodeTemplateDTO shopDeskQrcodeTemplateDTO = shopDeskQrcodeTemplateReadFacade.findById(qrCodeTempalteId);
            if (null == shopDeskQrcodeTemplateDTO){
                return Result.SUCESS(null);
            }

            ShopDeskQrcodeTemplateVO shopDeskQrcodeTemplateVO = ObjectConvertUtil.copyProperties(ShopDeskQrcodeTemplateVO.class, shopDeskQrcodeTemplateDTO);
            return Result.SUCESS(shopDeskQrcodeTemplateVO);
        }catch (OptException e){
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            return Result.FAILURE(e.getCode(),e.getMessage());
        }
    }






}
