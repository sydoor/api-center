package com.lizikj.api.controller.reporting;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lizikj.api.constant.CenterConstants;
import com.lizikj.api.dto.DownLoadDTO;
import com.lizikj.cache.Cache;
import com.lizikj.login.interceptor.LoginExclude;

import io.swagger.annotations.Api;

@Controller
@RequestMapping(value = "/reporting")
@Api(value = "report_download",tags = "统一文件下载接口")
public class ReportDownLoadController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReportDownLoadController.class);
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private Cache cache;
	
	/**
	 * 根据临时token获取真正的下载文件
	 * @param response
	 * @param token void
	 * @author lijundong
	 * @throws IOException 
	 * @date 2017年11月3日 上午10:17:04
	 */
	@RequestMapping(value = "/downLoad")
	@LoginExclude
	public void downLoad(HttpServletResponse response, @RequestParam(name = "token", required = true) String token) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		if(logger.isInfoEnabled()){
			logger.info("down load begin, token={}", token);
		}
		
		String cacheKey = CenterConstants.getDownLoadCacheKey(token);
		if(cache.hasKey(cacheKey)){
			DownLoadDTO downLoadDTO = (DownLoadDTO) cache.get(cacheKey);
			try {
	            String fileName = URLEncoder.encode(downLoadDTO.getFileName(), "utf-8");
	            response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(),"utf-8"));
	            response.setContentType("application/vnd.ms-excel;charset=utf-8");
	            //写出文件流
				response.getOutputStream().write(downLoadDTO.getBytes());
	        } catch (Exception e) {
	        	if(logger.isErrorEnabled()){
	        		logger.error("down load error, token={}", token);
	        	}
	        }
		}else{
			PrintWriter writer = response.getWriter();
			StringBuilder sb = new StringBuilder();
			sb.append("<html>");
			sb.append("<script>");
			sb.append("alert('下载令牌已失效');");
			sb.append("</script>");
			sb.append("</html>");
			writer.write(sb.toString());
		}
	}
}
