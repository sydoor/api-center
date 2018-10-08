package com.lizikj.api.config.reporting;

import com.lizikj.api.config.BaseSwaggerConfig;
import com.lizikj.common.enums.UserTypeEnum;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA. adept
 * User:  liuyuntao
 * Date: 2017/7/19
 * Time:  19:27
 */
@Configuration
@EnableSwagger2
public class ReportingSwaggerConfig extends BaseSwaggerConfig {
    @Bean
    public Docket reportingApi() {

        ArrayList<Parameter> list = new ArrayList<Parameter>();
        list.add(getSystemParameter(UserTypeEnum.MERCHANT_USER.getCode()));
        list.add(getTokenParameter());

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("reporting")
                .genericModelSubstitutes(DeferredResult.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .directModelSubstitute(org.joda.time.LocalDate.class, java.sql.Date.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
//                .pathMapping("/")//api测试请求地址
                .select()
                .paths(PathSelectors.ant("/reporting/**"))//过滤的接口
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build()
                .apiInfo(reportingApiInfo()).globalOperationParameters(list);


    }

    private ApiInfo reportingApiInfo() {
        Contact contact = new Contact("梁小林", "https://github.com/sydooor", "512900548@qq.com");
        ApiInfo apiInfo = new ApiInfo("李子网络科技统计报表服务API接口",//大标题
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
