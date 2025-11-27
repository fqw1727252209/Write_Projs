package com.study.platform.mapper;

import com.study.platform.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * 用户Mapper接口
 * 
 * @author Student
 * @date 2024-11-25
 */
public interface UserMapper {

    /**
     * 根据学号查询用户
     */
    User selectByStudentId(@Param("studentId") String studentId);

    /**
     * 根据ID查询用户
     */
    User selectById(@Param("id") Long id);

    /**
     * 插入用户
     */
    int insert(User user);

    /**
     * 更新用户信息
     */
    int update(User user);

    /**
     * 删除用户
     */
    int deleteById(@Param("id") Long id);

}
