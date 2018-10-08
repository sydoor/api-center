package com.lizikj.api.controller.shop;

import com.lizikj.api.config.shop.VirtualDataConfig;
import com.lizikj.api.vo.Result;
import com.lizikj.common.enums.VirtualEnum;
import com.lizikj.common.exception.BaseException;
import com.lizikj.login.interceptor.LoginExclude;
import com.lizikj.shop.api.common.ShopConstants;
import com.lizikj.shop.api.dto.ShopInitDTO;
import com.lizikj.shop.api.dto.VDShopInfoDTO;
import com.lizikj.shop.api.exception.ShopException;
import com.lizikj.shop.api.facade.IShopVirtualDataWriteFacade;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 店铺增值服务接口
 * @author zhoufe
 * @date 2017/07/10
 */
@Controller
@RequestMapping("/virtual/shop/data")
@Api(value = "shop-virtual-data", tags = "店铺VD数据API接口")
public class ShopVirtualDataController {

    private static final Logger logger = LoggerFactory.getLogger(ShopVirtualDataController.class);


    @Autowired
    private VirtualDataConfig virtualDataConfig;

    @Autowired
    private IShopVirtualDataWriteFacade shopVirtualDataWriteFacade;



    /**
     * 产生数据
     * @return Result<String>
     * @author zhoufe
     * @date 2017年7月18日 下午3:31:23
     */
    @LoginExclude
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/genData")
    @ApiOperation(value = "许许多多的的", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> genData(
            @ApiParam(name = "key", value = "关键字", required = true)
            @RequestParam(name = "key") String key
    ) {
        Result<String> result;
        try {
            if (!ShopConstants.VIRTUAL_DATA_KEY.equals(key)) {
                throw new ShopException("传入的口令不正确！");
            }

            String shopinfoXmlPath = virtualDataConfig.shopinfoXmlPath;
            String shopinfoXlsxPath = virtualDataConfig.shopinfoXlsxPath;

            List<VDShopInfoDTO> vdShopInfoDTOS = getShopInfosFormExecls(shopinfoXmlPath, shopinfoXlsxPath);

            if (CollectionUtils.isEmpty(vdShopInfoDTOS)){
                if (logger.isWarnEnabled()) {
                    logger.warn("VD数据表格为空");
                }
                return Result.SUCESS("VD数据表格为空");
            }

            for (VDShopInfoDTO vdShopInfoDTO : vdShopInfoDTOS){
                vdShopInfoDTO.setVirtual(VirtualEnum.YES);
            }

            String retkey = shopVirtualDataWriteFacade.genData(key, vdShopInfoDTOS);
            result = Result.SUCESS(retkey);
        } catch (BaseException e) {
            logger.error("genData Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    /**
     * 删除数据
     * @return Result<String>
     * @author zhoufe
     * @date 2017年7月18日 下午3:31:23
     */
    @LoginExclude
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/cleanData")
    @ApiOperation(value = "许许多多的的威尔而我", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> cleanData(
            @ApiParam(name = "key", value = "关键字", required = true)
            @RequestParam(name = "key") String key
    ) {
        Result<String> result;
        try {

            String retkey = shopVirtualDataWriteFacade.cleanData(key);
            result = Result.SUCESS(retkey);
        } catch (BaseException e) {
            logger.error("cleanData Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    /**
     * 从excel获取店铺数据
     * @params [shopinfoXmlPath, shopinfoXlsxPath]
     * @return java.util.List<com.lizikj.shop.api.dto.VDShopInfoDTO>
     * @author zhoufe
     * @date 2018/1/15 20:46
     */
    private List<VDShopInfoDTO> getShopInfosFormExecls(String shopinfoXmlPath, String shopinfoXlsxPath) {
        List<VDShopInfoDTO> shopInfoList = new ArrayList<>();
        FileInputStream in = null;
        InputStream inputXml = null;
        FileInputStream in1 = null;
        InputStream inputXlsx = null;
        try{

            in = new FileInputStream(shopinfoXmlPath);
            inputXml = new BufferedInputStream(in);
            in1 = new FileInputStream(shopinfoXlsxPath);
            inputXlsx = new BufferedInputStream(in1);

            Map<String, Object> beans = new HashMap<>();

            beans.put("vdshopInfoList", shopInfoList);

            XLSReader mainReader = ReaderBuilder.buildFromXML(inputXml);
            XLSReadStatus readStatus = mainReader.read(inputXlsx, beans);

        }catch (Exception e){
            throw new RuntimeException("excels/ShopInfo.xlsx 文件错误："+e.getMessage(), e);
        }finally {
            closeInputStream(in);
            closeInputStream(inputXml);
            closeInputStream(in1);
            closeInputStream(inputXlsx);
        }
        return shopInfoList;
    }

    private void closeInputStream(InputStream in) {
        try {
            if (in != null){
                in.close();
            }
        }catch (Throwable e){
            if (logger.isErrorEnabled()) {
                logger.error("关闭流错误："+e.getMessage(), e);
            }
        }
    }


    /**
     * 清空ID的key
     * @return Result<String>
     * @author zhoufe
     * @date 2017年7月18日 下午3:31:23
     */
    @LoginExclude
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/cleanVdId")
    @ApiOperation(value = "许许多多订单数的的威尔而我", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> cleanVdId(
            @ApiParam(name = "key", value = "关键字", required = true)
            @RequestParam(name = "key") String key
    ) {
        Result<Boolean> result;
        try {

            boolean retkey = shopVirtualDataWriteFacade.cleanVdId(key);
            result = Result.SUCESS(retkey);
        } catch (BaseException e) {
            logger.error("cleanVdId Exception: {}", e);
            result = Result.FAILURE(e.getCode(), e.getMessage());
        }

        return result;
    }

    
   

}
