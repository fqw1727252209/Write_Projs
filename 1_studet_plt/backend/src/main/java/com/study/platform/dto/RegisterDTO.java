package com.study.platform.dto;

import lombok.Data;

/**
 * 注册数据传输对象
 * 
 * @author Student
 * @date 2024-11-25
 */
@Data
public class RegisterDTO {

    /**
     * 学号
     */
    private String studentId;

    /**
     * 姓名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 专业
     */
    private String major;

    /**
     * 年级
     */
    private String grade;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

}
