package com.lizikj.api.controller.shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lizikj.login.interceptor.LoginExclude;
import com.lizikj.payment.pay.facade.IMerchantShopSignPageFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 *
 * @auth zone
 * @date 2018-08-15
 */
@Controller
@RequestMapping("/shop/signPage")
@Api(value = "商户电签页面", tags = "商户电签页面API")
@LoginExclude
public class ShopElectronicProtocolController {
	private static final Logger logger = LoggerFactory.getLogger(ShopElectronicProtocolController.class);
	
	@Autowired
	IMerchantShopSignPageFacade merchantShopSignPageFacade;

	@RequestMapping(value = "/helibao")
	@ApiOperation(value = "获取合利宝电签页面", httpMethod = "GET")
	public void getHelibaoSignPage(@RequestParam long merchantId, HttpServletResponse response) {
		String pageInfo = "";
		try {
			Map<String, String> param = merchantShopSignPageFacade.getSignPageInfoParam(merchantId);
			pageInfo = initSDK(param);
			logger.info("获取合利宝电签页面, pageInfo is \n{}", pageInfo);
		} catch (Exception e) {
			logger.error("获取页面信息异常", e);
		}

		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.write(pageInfo);
		} catch (IOException e) {
			logger.error("输出页面信息异常", e);
		}
	}
	
	private String initSDK(Map<String, String> reqParams) {
		String URL = "http://pay.trx.helipay.com/trx/merchantAgreement/interface.action";
		StringBuffer html = new StringBuffer();
		html.append("<form id=\"payForm\" ref=\"payForm\" style=\"display:none\" method=\"post\" action=\""+ URL +"\">");
		for(Map.Entry<String, String> entry: reqParams.entrySet()){
			html.append("<input type=\"hidden\" name=\"" + entry.getKey() + "\" value=\"" + StringEscapeUtils.escapeHtml(entry.getValue()) + "\"/>");
		}
		html.append("<input type=\"submit\" value=\"确认\">");
		html.append("</form>");
		html.append("<script>" + 
				"window.onload = function(){" + 
				"document.getElementById(\"payForm\").submit();" + 
				"}" + 
				"</script>");
		
		return html.toString();
	}
}
