package com.imooc.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {
    public static void main(String[]args){
        SpringApplication.run(EurekaApplication.class,args);
    }
}
//运行jar包  java -jar *************.jar --spring.profiles.active=*****
