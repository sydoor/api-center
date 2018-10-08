package com.lizikj.api.config;

import java.util.ArrayList;
import java.util.List;

import com.lizikj.common.enums.UserTypeEnum;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;

public class BaseSwaggerConfig {
	
	protected List<Parameter> getParameterList() {
		List<Parameter> parameterList = new ArrayList<Parameter>();
		parameterList.add(getTokenParameter());
		parameterList.add(getSnameParameter());
		parameterList.add(getSversionParameter());
		parameterList.add(getAversionParameter());
		parameterList.add(getDidParameter());
		parameterList.add(getLngParameter());
		parameterList.add(getLatParameter());
		parameterList.add(getTimestampParameter());
		parameterList.add(getAppkeyParameter());
		parameterList.add(getSignParameter());
		parameterList.addAll(getSysTypeParameter(UserTypeEnum.MERCHANT_USER.getCode()));
		
		return parameterList;
	}
	
	/**
	 * 创建token
	 * @return Parameter
	 * @author lijundong
	 * @date 2017年7月21日 下午3:43:25
	 */
	protected Parameter getTokenParameter(){
		ParameterBuilder tokenBuilder = new ParameterBuilder();
		tokenBuilder
                .parameterType("header") //参数类型支持header, cookie, body, query etc
                .name("lz-token") //参数名
                .defaultValue("token") //默认值
                .description("登录信息token")
                .modelRef(new ModelRef("string"))//指定参数值的类型
                .required(false).build(); //非必需，这里是全局配置，然而在登陆的时候是不用验证的
		springfox.documentation.service.Parameter build = tokenBuilder.build();
		return build;
	}
	
	/**
	 * 创建sname
	 * @return Parameter
	 * @author lijundong
	 * @date 2017年7月21日 下午3:43:54
	 */
	protected Parameter getSnameParameter(){
		ParameterBuilder tokenBuilder = new ParameterBuilder();
		tokenBuilder
                .parameterType("header") //参数类型支持header, cookie, body, query etc
                .name("lz-sname") //参数名
                .defaultValue("ios") //默认值
                .description("操作系统名称ios/android")
                .modelRef(new ModelRef("string"))//指定参数值的类型
                .required(false).build(); //非必需，这里是全局配置，然而在登陆的时候是不用验证的
		springfox.documentation.service.Parameter build = tokenBuilder.build();
		return build;
	}
	
	/**
	 * 创建sversion
	 * @return Parameter
	 * @author lijundong
	 * @date 2017年7月21日 下午3:43:54
	 */
	protected Parameter getSversionParameter(){
		ParameterBuilder tokenBuilder = new ParameterBuilder();
		tokenBuilder
                .parameterType("header") //参数类型支持header, cookie, body, query etc
                .name("lz-sversion") //参数名
                .defaultValue("10.0.1") //默认值
                .description("操作系统版本")
                .modelRef(new ModelRef("string"))//指定参数值的类型
                .required(false).build(); //非必需，这里是全局配置，然而在登陆的时候是不用验证的
		springfox.documentation.service.Parameter build = tokenBuilder.build();
		return build;
	}
	
	/**
	 * 创建aversion
	 * @return Parameter
	 * @author lijundong
	 * @date 2017年7月21日 下午3:43:54
	 */
	protected Parameter getAversionParameter(){
		ParameterBuilder tokenBuilder = new ParameterBuilder();
		tokenBuilder
                .parameterType("header") //参数类型支持header, cookie, body, query etc
                .name("lz-aversion") //参数名
                .defaultValue("1.0.1") //默认值
                .description("app/pos版本")
                .modelRef(new ModelRef("string"))//指定参数值的类型
                .required(false).build(); //非必需，这里是全局配置，然而在登陆的时候是不用验证的
		springfox.documentation.service.Parameter build = tokenBuilder.build();
		return build;
	}
	
	/**
	 * 创建did
	 * @return Parameter
	 * @author lijundong
	 * @date 2017年7月21日 下午3:43:54
	 */
	protected Parameter getDidParameter(){
		ParameterBuilder tokenBuilder = new ParameterBuilder();
		tokenBuilder
                .parameterType("header") //参数类型支持header, cookie, body, query etc
                .name("lz-did") //参数名
                .defaultValue("4564684641121") //默认值
                .description("设备id")
                .modelRef(new ModelRef("string"))//指定参数值的类型
                .required(false).build(); //非必需，这里是全局配置，然而在登陆的时候是不用验证的
		springfox.documentation.service.Parameter build = tokenBuilder.build();
		return build;
	}
	
	/**
	 * 创建lng
	 * @return Parameter
	 * @author lijundong
	 * @date 2017年7月21日 下午3:43:54
	 */
	protected Parameter getLngParameter(){
		ParameterBuilder tokenBuilder = new ParameterBuilder();
		tokenBuilder
                .parameterType("header") //参数类型支持header, cookie, body, query etc
                .name("lz-lng") //参数名
                .defaultValue("12.596898") //默认值
                .description("经度")
                .modelRef(new ModelRef("string"))//指定参数值的类型
                .required(false).build(); //非必需，这里是全局配置，然而在登陆的时候是不用验证的
		springfox.documentation.service.Parameter build = tokenBuilder.build();
		return build;
	}
	
	/**
	 * 创建lat
	 * @return Parameter
	 * @author lijundong
	 * @date 2017年7月21日 下午3:43:54
	 */
	protected Parameter getLatParameter(){
		ParameterBuilder tokenBuilder = new ParameterBuilder();
		tokenBuilder
                .parameterType("header") //参数类型支持header, cookie, body, query etc
                .name("lz-lat") //参数名
                .defaultValue("88.554645") //默认值
                .description("纬度")
                .modelRef(new ModelRef("string"))//指定参数值的类型
                .required(false).build(); //非必需，这里是全局配置，然而在登陆的时候是不用验证的
		springfox.documentation.service.Parameter build = tokenBuilder.build();
		return build;
	}
	
	/**
	 * 创建时间戳
	 * @return Parameter
	 * @author lijundong
	 * @date 2017年7月26日 上午11:24:04
	 */
	protected Parameter getTimestampParameter(){
		ParameterBuilder tokenBuilder = new ParameterBuilder();
		tokenBuilder
                .parameterType("header") //参数类型支持header, cookie, body, query etc
                .name("lz-timestamp") //参数名
                .defaultValue("19541621") //默认值
                .description("时间戳")
                .modelRef(new ModelRef("string"))//指定参数值的类型
                .required(false).build(); //非必需，这里是全局配置，然而在登陆的时候是不用验证的
		springfox.documentation.service.Parameter build = tokenBuilder.build();
		return build;
	}
	
	/**
	 * app加密算法key
	 * @return Parameter
	 * @author lijundong
	 * @date 2017年7月26日 上午11:27:07
	 */
	protected Parameter getAppkeyParameter(){
		ParameterBuilder tokenBuilder = new ParameterBuilder();
		tokenBuilder
                .parameterType("header") //参数类型支持header, cookie, body, query etc
                .name("lz-appkey") //参数名
                .defaultValue("11111111") //默认值
                .description("app加密算法key")
                .modelRef(new ModelRef("string"))//指定参数值的类型
                .required(false).build(); //非必需，这里是全局配置，然而在登陆的时候是不用验证的
		springfox.documentation.service.Parameter build = tokenBuilder.build();
		return build;
	}
	
	/**
	 * 创建sign
	 * @return Parameter
	 * @author lijundong
	 * @date 2017年7月21日 下午3:43:54
	 */
	protected Parameter getSignParameter(){
		ParameterBuilder tokenBuilder = new ParameterBuilder();
		tokenBuilder
                .parameterType("header") //参数类型支持header, cookie, body, query etc
                .name("lz-sign") //参数名
                .defaultValue("afd18b002118f6e4bb26c940cbf183918cc49330") //默认值
                .description("签名")
                .modelRef(new ModelRef("string"))//指定参数值的类型
                .required(false).build(); //非必需，这里是全局配置，然而在登陆的时候是不用验证的
		springfox.documentation.service.Parameter build = tokenBuilder.build();
		return build;
	}
	
	/**
	 * 创建system
	 * @return Parameter
	 * @author lijundong
	 * @date 2017年7月21日 下午3:43:54
	 */
	protected Parameter getSystemParameter(String code){
		ParameterBuilder tokenBuilder = new ParameterBuilder();
		tokenBuilder
                .parameterType("header") //参数类型支持header, cookie, body, query etc
                .name("lz-system") //参数名
                .defaultValue(code) //默认值
                .description("管理系统来源, agent=代理商，mch=商户，opt=运营中心，tender=撩美味")
                .modelRef(new ModelRef("string"))//指定参数值的类型
                .required(false).build(); //非必需，这里是全局配置，然而在登陆的时候是不用验证的
		springfox.documentation.service.Parameter build = tokenBuilder.build();
		return build;
	}
	
	/**
	 * 创建sysType
	 * @return Parameter
	 * @author lijundong
	 * @date 2017年7月27日 下午3:52:00
	 */
	protected List<Parameter> getSysTypeParameter(String code){
		ArrayList<Parameter> list = new ArrayList<Parameter>();
		list.add(getSystemParameter(code));
		list.add(getTokenParameter());
		return list;
	} 
}
