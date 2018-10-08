package com.lizikj.api.utils;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import com.alibaba.fastjson.JSONObject;
import com.lizikj.common.util.DateUtils;
import com.lizikj.common.util.MD5Utils;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * freeMarker导出excel工具 
 * 
 * @author liaojw 
 * @date 2018年7月19日 上午10:53:10
 */
public class FreeMarkerExportExcelUtil {
	private static final Logger logger = LoggerFactory.getLogger(FreeMarkerExportExcelUtil.class);
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String templatePath;
	public FreeMarkerExportExcelUtil(HttpServletRequest request, HttpServletResponse response, String templatePath) {
		this.request = request;
		this.response = response;
		this.templatePath = templatePath;
	}
	
	public void export(String templateName, Map<String, Object> dataMap, String fileName) throws IOException {
		OutputStream out = null;
		BufferedInputStream inputStream = null;
		try {
			String realPath =templatePath;
			String excelBuildPath = templatePath + "/temp";
			File temp = new File(excelBuildPath);
			if (!temp.exists()) {
				temp.mkdirs();
			}
			
			Configuration config = new Configuration();
			config.setDefaultEncoding("utf-8");
			config.setDirectoryForTemplateLoading(new File(realPath));
			
			Writer writer = null;
			String []suffixSplit = fileName.split("\\.");
			String suffix = suffixSplit[1];
			StringBuilder buildFilePath = new StringBuilder(excelBuildPath);
			buildFilePath.append(File.separator);
			buildFilePath.append(MD5Utils.md5(fileName + DateUtils.getCurrent()));
			buildFilePath.append(".");
			buildFilePath.append(suffix);
			
			File buildFile = new File(buildFilePath.toString());
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(buildFile)));
			
			Template template = config.getTemplate(templateName);
			template.setEncoding("utf-8");
			template.process(dataMap, writer);//写入数据
			writer.flush();
			writer.close();
			
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "iso-8859-1"));
			
			//读出文件
			out = response.getOutputStream();
			FileInputStream input = new FileInputStream(buildFile);
			inputStream = new BufferedInputStream(input);
			byte[] buffer= new byte[1024];//缓存区
			int c = 0;
			while ((c  = inputStream.read(buffer)) != -1) {
				out.write(buffer, 0, c);
			}
			out.flush();
			
			if (out != null) {
				out.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
		}  catch (Exception e) {
			logger.error("导出excel出错,templateName:{},data:{},error:{}",templateName, JSONObject.toJSONString(dataMap), e);
		} finally {
			if (out != null) {
				out.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
		}
		
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}
	
	
}
