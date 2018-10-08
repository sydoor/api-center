package com.lizikj.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan(value = {"com.lizikj.**"})
@ImportResource(value = {"classpath:xml/dubbo-consumer.xml", "classpath:xml/login-consumer.xml"})
public class Bootstrap {

    private static final Logger log = LoggerFactory.getLogger(Bootstrap.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Bootstrap.class, args);
        log.info("center-gateway-api启动成功！");
    }
}
