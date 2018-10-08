package com.lizikj.api.utils;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;

/**
 * @author lxl
 * @Date 2018/7/26 14:20
 */
public class MarketingUtil {
    private static String SALT = "lizikejimarketingcard";

    public static String md5(String message){

        return md5(message,SALT);
    }

    public static String md5(String message,String salt){
        String md5 = "";
        if (!StringUtils.isBlank(message)) {
            try {
                if (!StringUtils.isEmpty(salt)) {
                    message = message + "{" + salt + "}";
                }
                StringBuilder buffer = new StringBuilder();
                MessageDigest messageDigest = MessageDigest.getInstance("md5");
                char[] ch = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
                byte[] b = messageDigest.digest(message.getBytes());

                for(int i = 0; i < b.length; ++i) {
                    int x = b[i] >>> 4 & 15;
                    buffer.append(ch[x]);
                    x = b[i] & 15;
                    buffer.append(ch[x]);
                }

                md5 =  buffer.toString();
            } catch (Exception var8) {
                //
            }
        }

        return md5;
    }
}
