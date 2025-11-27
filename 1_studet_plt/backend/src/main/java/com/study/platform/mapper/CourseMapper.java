package com.study.platform.mapper;

import com.study.platform.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课程Mapper接口
 * 
 * @author Student
 * @date 2024-11-25
 */
public interface CourseMapper {

    /**
     * 根据用户ID查询课程列表
     */
    List<Course> selectByUserId(@Param("userId") Long userId);

    /**
     * 根据用户ID和学期查询课程列表
     */
    List<Course> selectByUserIdAndSemester(@Param("userId") Long userId, @Param("semester") String semester);

    /**
     * 根据ID查询课程
     */
    Course selectById(@Param("id") Long id);

    /**
     * 插入课程
     */
    int insert(Course course);

    /**
     * 更新课程
     */
    int update(Course course);

    /**
     * 删除课程
     */
    int deleteById(@Param("id") Long id);

    /**
     * 统计用户的课程数量
     */
    int countByUserId(@Param("userId") Long userId);

}
