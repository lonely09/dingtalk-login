package com.smec.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DingdingloginApplication {

    public static void main(String[] args) {
        SpringApplication.run(DingdingloginApplication.class, args);
    }

}
