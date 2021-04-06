package com.urms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@MapperScan("com.urms.mapper")
public class UrmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(UrmsApplication.class,args);
    }
}
