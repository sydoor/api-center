package com.lizikj.api.filter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.lizikj.api.vo.Result;
import com.lizikj.common.exception.BaseException;
import com.lizikj.common.util.HttpRequestLogConfig;
import com.lizikj.merchant.enums.MerchantErrorEnum;

import io.swagger.annotations.ApiModel;

/**
 * 接口请求出入参日志
 * 
 * @author zone 
 * @date 2017年10月31日
 */
@Aspect
@Component
public class CenterGatewayControllerFilter {
	private static final Logger log = LoggerFactory.getLogger(CenterGatewayControllerFilter.class);

	@Pointcut("execution(* com.lizikj.api.controller..*.*(..))")
	public void excudeService() {
	}

	@Around("excudeService()")
	public Object interceptor(ProceedingJoinPoint point) throws Throwable{
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        Long version = System.currentTimeMillis();//当前请求标识号
        String method = request.getMethod();
        String uri = request.getRequestURI();
        StringBuilder queryParams = new StringBuilder("");//参数字符串
        Object[] args = point.getArgs();//json参数
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method _method = methodSignature.getMethod();
        Parameter[] parameters = _method.getParameters();
      //普通url参数
        for (Parameter p:parameters) {
//        	if (!p.getType().isPrimitive()) {//非原始类型不拼装
//        		continue;
//        	}
//        	Annotation [] as = p.getAnnotations();
        	HttpRequestLogConfig lc = p.getAnnotation(HttpRequestLogConfig.class);
        	if (lc !=null && lc.isIgnore()) {//忽略参数日志输出
        		break;
        	}
        	if (queryParams.length()!=0) {
        		queryParams.append("&");
        	}
        	queryParams.append(p.getName()+"="+request.getParameter(p.getName()));
        }
        //获取RequestBody参数
        for (Object arg:args) {
        	if (arg == null) {
        		break;
        	}
        	Class _class = arg.getClass();
        	ApiModel apiModel = (ApiModel) _class.getAnnotation(ApiModel.class);
        	if (apiModel !=null) {
//        		queryParams.append("&[RequestBody-params]"+JSONObject.toJSONString(arg));
        		Field [] fields = _class.getDeclaredFields();
        		for (Field field:fields) {
        			field.setAccessible(true);//访问权限
        			Object value = field.get(arg);
        			if (value ==null) {
        				continue;
        			}
        			HttpRequestLogConfig argLogConfig = field.getAnnotation(HttpRequestLogConfig.class);
        			if (argLogConfig !=null && argLogConfig.isIgnore()) {//参数是否忽略
        				continue;
                	}
        			if (queryParams.length()!=0) {
                		queryParams.append("&");
                	}
        			String keyValue = field.getName()+"="+value;
        			queryParams.append(keyValue);
        		}
        	}
        }
        if (log.isInfoEnabled()) {
        	log.info("【"+method.toUpperCase()+" request-"+version+"】"+uri+" 【Params】"+queryParams.toString());//入参日志
        }
        Object result = null;
        try {
        	   result = point.proceed();
        	   if (log.isInfoEnabled()) {
        		   log.info("【Return request-"+version+"】"+uri+" 【Result】"+JSONObject.toJSONString(result));//出参日志
        	   }
        } catch (Throwable e) {
        	if (e instanceof BaseException) {
        		BaseException me = (BaseException) e;
        		result = Result.FAILURE(me.getCode(), me.getMessage());
        	} else {
        		result = Result.FAILURE(MerchantErrorEnum.MERCHANT_COMMON_ERROR_NETWORK.getCode(), MerchantErrorEnum.MERCHANT_COMMON_ERROR_NETWORK.getMessage()+":"+e);
        	}
        	if (log.isErrorEnabled()) {
        		log.error("【Error request-"+version+"】"+uri+" 【Params】"+queryParams.toString()+",错误信息：", e);//错误日志
        	}
        }
		return result;
	}
}
