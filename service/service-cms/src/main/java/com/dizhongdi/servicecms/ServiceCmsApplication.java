package com.dizhongdi.servicecms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.dizhongdi"}) //指定扫描位置
@MapperScan("com.dizhongdi.servicecms.mapper")
public class ServiceCmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCmsApplication.class, args);
    }

}
