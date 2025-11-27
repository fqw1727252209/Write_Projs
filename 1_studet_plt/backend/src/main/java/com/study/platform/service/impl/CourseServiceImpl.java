package com.study.platform.service.impl;

import com.study.platform.entity.Course;
import com.study.platform.mapper.CourseMapper;
import com.study.platform.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 课程Service实现类
 * 
 * @author Student
 * @date 2024-11-25
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public void createCourse(Course course) {
        int result = courseMapper.insert(course);
        if (result == 0) {
            throw new RuntimeException("添加课程失败");
        }
    }

    @Override
    public List<Course> getCourseList(Long userId, String semester) {
        if (semester != null && !semester.isEmpty()) {
            return courseMapper.selectByUserIdAndSemester(userId, semester);
        } else {
            return courseMapper.selectByUserId(userId);
        }
    }

    @Override
    public Course getCourseById(Long id) {
        return courseMapper.selectById(id);
    }

    @Override
    public void updateCourse(Course course) {
        int result = courseMapper.update(course);
        if (result == 0) {
            throw new RuntimeException("更新课程失败");
        }
    }

    @Override
    public void deleteCourse(Long id) {
        int result = courseMapper.deleteById(id);
        if (result == 0) {
            throw new RuntimeException("删除课程失败");
        }
    }

}
