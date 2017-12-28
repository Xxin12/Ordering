package cn.gtapc.ordering.constant;

import cn.gtapc.util.common.PropertiesUtil;

import java.util.Properties;

/**
 * 配置項常量類
 */
public final class ConfigConstant {

    public static final String MANAGE_BASE_URL;

    public static final String COMMON_BASE_URL;

    static {

        Properties configProperties = PropertiesUtil.loadProperties("properties/config.properties");

        MANAGE_BASE_URL = configProperties.getProperty("manage.base.url");
        COMMON_BASE_URL = configProperties.getProperty("common.base.url");
    }

}
