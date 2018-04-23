package com.css.cloud.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringCloudApplication
@EnableEurekaServer
@EnableConfigServer
public class UdfEurekaConfigServerDemoApplication  {

    /**
     * 描述 : spring boot的入口
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        SpringApplication.run(UdfEurekaConfigServerDemoApplication.class, args);
    }

}