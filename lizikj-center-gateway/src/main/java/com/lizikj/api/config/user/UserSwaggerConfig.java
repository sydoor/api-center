package com.lizikj.api.config.user;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import com.lizikj.api.config.BaseSwaggerConfig;
import com.lizikj.common.enums.UserTypeEnum;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author Michael.Huang
 * @date 2017/6/20
 */
@Configuration
@EnableSwagger2
public class UserSwaggerConfig extends BaseSwaggerConfig{

    @Bean
    @Order(1)
    public Docket MerchantUserApi() {
    	ArrayList<Parameter> list = new ArrayList<Parameter>();
    	list.add(getSystemParameter(UserTypeEnum.MERCHANT_USER.getCode()));
    	list.add(getTokenParameter());
    	list.add(getDidParameter());
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("user")
                .genericModelSubstitutes(DeferredResult.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .directModelSubstitute(org.joda.time.LocalDate.class, java.sql.Date.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
//                .pathMapping("/")//api测试请求地址
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .paths(PathSelectors.ant("/merchant/user/**, /code/**"))
                .paths(PathSelectors.regex("(/user/).*|(/[^/].*/code/).*"))
                .build()
                .apiInfo(merchantUserApiInfo()).globalOperationParameters(list);


    }

	private ApiInfo merchantUserApiInfo() {
        Contact contact = new Contact("李俊东", "http://www.lizikj.com/", "ljd@lizikj.com");
        ApiInfo apiInfo = new ApiInfo("用户登录/注销API接口",//大标题
                "REST风格API",//小标题
                "1.0",//版本
                "http://www.lizikj.com/",
                contact,//作者
                "李子官网",//链接显示文字
                ""//网站链接,
                , new ArrayList<VendorExtension>()
        );
        return apiInfo;
    }

}
