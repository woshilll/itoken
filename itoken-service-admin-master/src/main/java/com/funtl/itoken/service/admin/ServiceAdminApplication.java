package com.funtl.itoken.service.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 李洋
 * @date 2019-08-22 10:47
 */
@EnableSwagger2
@SpringBootApplication(scanBasePackages = "com.funtl.itoken")
@EnableEurekaClient
@MapperScan(basePackages = {"com.funtl.itoken.common.service.mapper", "com.funtl.itoken.service.admin.mapper"})
public class ServiceAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAdminApplication.class , args);
    }
}
