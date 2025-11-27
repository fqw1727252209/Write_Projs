package com.study.platform.service;

import com.study.platform.dto.LoginDTO;
import com.study.platform.dto.RegisterDTO;
import com.study.platform.entity.User;

/**
 * 用户Service接口
 * 
 * @author Student
 * @date 2024-11-25
 */
public interface UserService {

    /**
     * 用户注册
     */
    void register(RegisterDTO registerDTO);

    /**
     * 用户登录
     */
    User login(LoginDTO loginDTO);

    /**
     * 根据ID查询用户
     */
    User getUserById(Long id);

    /**
     * 更新用户信息
     */
    void updateUser(User user);

    /**
     * 修改密码
     */
    void updatePassword(Long userId, String oldPassword, String newPassword);

}
