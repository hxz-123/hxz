package com.abc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.abc.repository")
public class ProviderApplication8081 {
public static void main(String[] args) {
        SpringApplication.run(ProviderApplication8081.class, args);
    }

}
