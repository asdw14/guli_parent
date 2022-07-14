package com.dizhongdi.servicemsm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.alibaba.nacos.NacosDiscoveryAutoConfiguration;
import org.springframework.cloud.alibaba.nacos.discovery.NacosDiscoveryClientAutoConfiguration;
import org.springframework.cloud.alibaba.nacos.endpoint.NacosDiscoveryEndpointAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.dizhongdi"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class ServiceMsmApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMsmApplication.class, args);
    }

}
