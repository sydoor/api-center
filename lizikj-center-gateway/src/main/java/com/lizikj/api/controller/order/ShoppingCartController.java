package com.lizikj.api.controller.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lizikj.api.dto.CommonParameterDTO;
import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.merchandise.CartGoodsVO;
import com.lizikj.api.vo.order.param.CartGoodsParamVO;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.merchandise.dto.CartGoodsDTO;
import com.lizikj.merchandise.facade.ICartGoodsReadFacade;
import com.lizikj.merchandise.facade.ICartGoodsWriteFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author Michael.Huang
 * @date 2017/8/17 14:54
 */
@Controller
@RequestMapping("/order/cart")
@Api(value = "/order/cart", tags = "购物车API接口")
public class ShoppingCartController {


    /**
     * 商品系统购物车Facade
     */
    @Autowired
    private ICartGoodsReadFacade cartGoodsReadFacade;
    @Autowired
    private ICartGoodsWriteFacade cartGoodsWriteFacade;
    /**
     * 获取购物车内容,此接口只在登录情况下有效
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getCartGoods/")
    @ApiOperation(value = "获取购物车内容,此接口只在登录情况下有效", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<List<CartGoodsVO>> getShoppingCart() {

        Long merchantUserId = CommonParameterDTO.buildParameters().getCurrentUserId();
        List<CartGoodsDTO> cartGoodsDTOList = cartGoodsReadFacade.listCartGoods(merchantUserId);
        List<CartGoodsVO> cartGoodsVOList = ObjectConvertUtil.mapList(cartGoodsDTOList, CartGoodsDTO.class, CartGoodsVO.class);


        return Result.SUCESS(cartGoodsVOList);
    }


    /**
     * 添加物品或更新到购物车
     *
     * @param cartGoodsParamList
     */
    @ResponseBody
    @RequestMapping(value = "/addToShoppingCart/")
    @ApiOperation(value = "添加购物车物品", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> addToShoppingCart(
            @ApiParam(name = "cartGoodsParamList", value = "购物车物品列表", required = true)
            @RequestBody
                    List<CartGoodsParamVO> cartGoodsParamList) {


//        shoppingCartFacade.addToShoppingCart(cartGoodsParamList);

        return Result.SUCESS();
    }


    /**
     * 删除购物车中的物品
     *
     * @param cartGoodsIds
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/remove/")
    @ApiOperation(value = "删除购物车中的物品", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> remove(@ApiParam(name = "cartGoodsIds", value = "购物车物品ID列表", required = true)
                                  @RequestBody
                                          List<String> cartGoodsIds) {
        Boolean flag = cartGoodsWriteFacade.deleteCartGoods(cartGoodsIds);
        return Result.SUCESS(flag);

    }
}
