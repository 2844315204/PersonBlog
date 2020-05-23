package com.lxn.code;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.lxn.code.dao")
@SpringBootApplication
public class CodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeApplication.class, args);
    }

}
