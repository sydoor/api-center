package com.lizikj.api.config.shop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhoufe
 * @date 2018/1/15 20:38
 */
@Configuration
public class VirtualDataConfig {

    @Value("${shopinfo.xml.path}")
    public String shopinfoXmlPath;
    @Value("${shopinfo.xlsx.path}")
    public String shopinfoXlsxPath;
}
