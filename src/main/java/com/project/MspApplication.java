package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * 启动程序
 *
 * @author zhuyifa
 * @since 2019-06-05
 */
@SpringBootApplication
@EnableScheduling
public class MspApplication {

    /**
     * 程序入口
     *
     * @param args 字符串数组
     */
    public static void main(String[] args) {
        SpringApplication.run(MspApplication.class, args);
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}
