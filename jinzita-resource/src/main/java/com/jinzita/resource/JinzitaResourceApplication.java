package com.jinzita.resource;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class JinzitaResourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(JinzitaResourceApplication.class, args);
        System.out.println("Hello world!");
    }
}