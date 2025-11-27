package com.study.platform.service;

import com.study.platform.entity.Course;

import java.util.List;

/**
 * 课程Service接口
 * 
 * @author Student
 * @date 2024-11-25
 */
public interface CourseService {

    void createCourse(Course course);

    List<Course> getCourseList(Long userId, String semester);

    Course getCourseById(Long id);

    void updateCourse(Course course);

    void deleteCourse(Long id);

}
