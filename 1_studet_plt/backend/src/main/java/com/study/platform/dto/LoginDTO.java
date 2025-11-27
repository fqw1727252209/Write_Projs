package com.study.platform.dto;

import lombok.Data;

/**
 * 登录数据传输对象
 * 
 * @author Student
 * @date 2024-11-25
 */
@Data
public class LoginDTO {

    /**
     * 学号
     */
    private String studentId;

    /**
     * 密码
     */
    private String password;

}
