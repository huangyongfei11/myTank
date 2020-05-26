package com.hyf.tank.util;

import java.io.IOException;
import java.util.Properties;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author hyf
 * @version [版本号, 2020/5/21]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class PropertiesUtil {
    private static Properties properties;
    static {
        try {
            properties = new Properties();
            properties.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String get(String proper){
        return String.valueOf(properties.get(proper));
    }

    private PropertiesUtil(){}
}
