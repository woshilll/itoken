package com.funtl.itoken.service.posts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 李洋
 * @date 2019-08-31 14:35
 */
@EnableSwagger2
@SpringBootApplication
@EnableEurekaClient
@MapperScan(value = {"com.funtl.itoken.common.service.mapper", "com.funtl.itoken.service.posts.mapper"})
public class ServicePostsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicePostsApplication.class, args);
    }
}
