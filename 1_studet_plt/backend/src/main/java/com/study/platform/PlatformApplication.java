package com.study.platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 在校学生个人学习规划平台 - 主启动类
 * 
 * @author Student
 * @date 2024-11-25
 */
@SpringBootApplication
@MapperScan("com.study.platform.mapper")
public class PlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlatformApplication.class, args);
        System.out.println("========================================");
        System.out.println("   学生学习规划平台启动成功！");
        System.out.println("   访问地址: http://localhost:8080/api");
        System.out.println("========================================");
    }

}
