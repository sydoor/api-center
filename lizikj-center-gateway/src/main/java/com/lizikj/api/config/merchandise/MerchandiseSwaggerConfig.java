package com.lizikj.api.config.merchandise;

import java.util.ArrayList;

import com.lizikj.api.config.BaseSwaggerConfig;
import com.lizikj.common.enums.UserTypeEnum;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.builders.PathSelectors;
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
public class MerchandiseSwaggerConfig extends BaseSwaggerConfig {

    @Bean
    @Order(1)
    public Docket merchandiseApi() {
        ArrayList<Parameter> list = new ArrayList<Parameter>();
        list.add(getSystemParameter(UserTypeEnum.MERCHANT_USER.getCode()));
        list.add(getTokenParameter());
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("merchandise")
                .genericModelSubstitutes(DeferredResult.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .directModelSubstitute(org.joda.time.LocalDate.class, java.sql.Date.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
//                .pathMapping("/")//api测试请求地址
                .select()
//                .paths(PathSelectors.regex("/resource/.*"))//过滤的接口
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.ant("/merchandise/**"))//过滤的接口
                .build()
                .apiInfo(merchandiseApiInfo()).globalOperationParameters(list);


    }

	private ApiInfo merchandiseApiInfo() {
        Contact contact = new Contact("周枫恩", "http://www.lizikj.com/", "zfe@lizikj.com");
        ApiInfo apiInfo = new ApiInfo("李子网络科技商品API接口",//大标题
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
