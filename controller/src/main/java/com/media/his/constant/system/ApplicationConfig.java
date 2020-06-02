package com.media.his.constant.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;

@Component
public class ApplicationConfig {
    /**
     * 系统默认编码
     */
    public static final Charset CHARSET = Charset.forName("UTF-8");
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfig.class);
    /**
     * 当前环境
     */
    public static String CURRENT_SYSTEM_ENV;
    /**
     * 是否为开发模式.该参数主要用于一些工具类的延迟加载。<br />
     * true: 不使用{@link }进行初始化
     * false: 当调用到相应的工具类，靠类加载器去加载
     */
    public static boolean DEV;

    /**
     * 系统环境
     */
    @Value("${spring.profiles.active}")
    private String profile;


    /**
     * 初始化
     */
    public synchronized void init() {
        long now = System.currentTimeMillis();
        LOGGER.info("初始ApplicationConfig开始...");
        LOGGER.info("初始化ApplicationConfig完成...消耗：{}毫秒!", System.currentTimeMillis() - now);

        CURRENT_SYSTEM_ENV = profile;
        if ("dev".equals(CURRENT_SYSTEM_ENV) || "uat".equals(CURRENT_SYSTEM_ENV)) {
            DEV = Boolean.TRUE;
        } else {
            DEV = Boolean.FALSE;
        }
        /*if (LOGGER.isDebugEnabled()) {
            LOGGER.info("初始ApplicationConfig开始...");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.info("初始化ApplicationConfig完成...消耗：{}毫秒!", System.currentTimeMillis() - now);
        }*/
    }
}
