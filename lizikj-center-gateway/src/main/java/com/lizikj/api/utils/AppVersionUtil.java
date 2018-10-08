package com.lizikj.api.utils;

import com.lizikj.message.api.enums.ThirdPlatformTypeEnum;
import org.apache.commons.lang.StringUtils;

/**
 * @author lxl
 * @Date 2018/4/13 10:53
 */
public class AppVersionUtil {

    public static ThirdPlatformTypeEnum fromVersion(String version,String above){
        //2018-07-10
        if(StringUtils.isBlank(version) || above == null){
            return ThirdPlatformTypeEnum.MQTT;
        }
        try {
            int i = Integer.valueOf(above);
            int versionNum = Integer.valueOf(version);
            if(versionNum > i)
            {
                return ThirdPlatformTypeEnum.MQTT;
            }
        } catch (NumberFormatException e) {
            //可以忽略
        }

        return ThirdPlatformTypeEnum.MPUSH;
    }
}
