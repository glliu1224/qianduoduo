package com.media.his;

import com.media.his.config.web.SystemInitConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(basePackages = {"com.media.his.*"})
@MapperScan("com.media.his.mapper")
@Import({SystemInitConfig.class})
public class HisStartApplication {
    public static void main(String[] args) {
        SpringApplication.run(HisStartApplication.class, args);
    }

}
