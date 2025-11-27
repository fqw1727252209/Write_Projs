package com.study.platform.service.impl;

import com.study.platform.dto.LoginDTO;
import com.study.platform.dto.RegisterDTO;
import com.study.platform.entity.User;
import com.study.platform.mapper.UserMapper;
import com.study.platform.service.UserService;
import com.study.platform.util.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户Service实现类
 * 
 * @author Student
 * @date 2024-11-25
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(RegisterDTO registerDTO) {
        // 检查学号是否已存在
        User existUser = userMapper.selectByStudentId(registerDTO.getStudentId());
        if (existUser != null) {
            throw new RuntimeException("该学号已被注册");
        }

        // 创建用户对象
        User user = new User();
        BeanUtils.copyProperties(registerDTO, user);

        // 密码MD5加密
        user.setPassword(MD5Util.encrypt(registerDTO.getPassword()));

        // 插入数据库
        int result = userMapper.insert(user);
        if (result == 0) {
            throw new RuntimeException("注册失败");
        }
    }

    @Override
    public User login(LoginDTO loginDTO) {
        // 根据学号查询用户
        User user = userMapper.selectByStudentId(loginDTO.getStudentId());
        if (user == null) {
            throw new RuntimeException("学号不存在");
        }

        // 验证密码
        String encryptPassword = MD5Util.encrypt(loginDTO.getPassword());
        if (!encryptPassword.equals(user.getPassword())) {
            throw new RuntimeException("密码错误");
        }

        // 清空密码字段，不返回给前端
        user.setPassword(null);

        return user;
    }

    @Override
    public User getUserById(Long id) {
        User user = userMapper.selectById(id);
        if (user != null) {
            // 清空密码字段
            user.setPassword(null);
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        int result = userMapper.update(user);
        if (result == 0) {
            throw new RuntimeException("更新用户信息失败");
        }
    }

    @Override
    public void updatePassword(Long userId, String oldPassword, String newPassword) {
        // 查询用户
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        // 验证旧密码
        String encryptOldPassword = MD5Util.encrypt(oldPassword);
        if (!encryptOldPassword.equals(user.getPassword())) {
            throw new RuntimeException("原密码错误");
        }

        // 更新密码
        user.setPassword(MD5Util.encrypt(newPassword));
        int result = userMapper.update(user);
        if (result == 0) {
            throw new RuntimeException("修改密码失败");
        }
    }

}
