package com.media.his.config.web;

import com.media.his.api.IServiceException;
import com.media.his.constant.system.ApplicationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SystemInitConfig implements CommandLineRunner,EnvironmentAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemInitConfig.class);

    @Autowired
    private IServiceException serviceException;
    @Autowired
    private ApplicationConfig applicationConfig;

    /**
     * 初始化项目
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        long now = System.currentTimeMillis();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("初始化 HisApplication开始");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("初始化 HisApplication完成...消耗： {}毫秒",System.currentTimeMillis() - now);
        }
    }

    /**
     * 在服务器启动后执行，会在@Bean实例化之后执行
     */
    @Override
    public void run(String... Strings) throws Exception{
        //初始化项目信息
        applicationConfig.init();
        //初始化缓存信息
        serviceException.initServiceExceptionToCache();
    }
}
