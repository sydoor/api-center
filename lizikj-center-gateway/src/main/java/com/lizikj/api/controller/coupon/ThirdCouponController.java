package com.lizikj.api.controller.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lizikj.api.vo.Result;
import com.lizikj.api.vo.coupon.MeituanCouponPrepareInfoVO;
import com.lizikj.cater.dto.meituan.MeituanCouponPrepareInfoDTO;
import com.lizikj.cater.exception.CaterException;
import com.lizikj.cater.facade.IThirdCouponWriteFacade;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.common.util.ThreadLocalContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 第三方优惠券API
 * @auth zone
 * @date 2017-12-14
 */
@Controller
@RequestMapping("marketing/third/coupon")
@Api(value = "thirdCoupon", tags = "第三方优惠券API")
public class ThirdCouponController {
	/**
	 * 团购券信息Facade
	 */
	@Autowired
	IThirdCouponWriteFacade thirdCouponWriteFacade;

	@ResponseBody
	@RequestMapping(value = "/meituan/prepare", method = RequestMethod.POST)
	@ApiOperation(value = "美团验券准备", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<MeituanCouponPrepareInfoVO> meituanPrepare(
			@ApiParam(name = "couponCode", value = "美团12位券码", required = true) @RequestParam(name = "couponCode", required = true) String couponCode) {
		if (couponCode.length() != 12) {
			return Result.FAILURE("券码错误");
		}

		Long shopId = ThreadLocalContext.getShopId();
		MeituanCouponPrepareInfoDTO meituanCouponPrepareInfoDTO = null;
		try {
			meituanCouponPrepareInfoDTO = thirdCouponWriteFacade.prepareMeituan(shopId, couponCode);
		} catch (CaterException e) {
			return Result.FAILURE(e.getCode(), e.getMessage());
		}

		MeituanCouponPrepareInfoVO vo = ObjectConvertUtil.copyProperties(MeituanCouponPrepareInfoVO.class, meituanCouponPrepareInfoDTO);
		return Result.SUCESS(vo);
	}
}
