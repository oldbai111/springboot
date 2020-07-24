package com.jbwang.cwgl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.jbwang.cwgl.dao")
@SpringBootApplication
public class CwglApplication {

    public static void main(String[] args) {
        SpringApplication.run(CwglApplication.class, args);
        System.out.println("*****项目启动完成*****");
    }

}
