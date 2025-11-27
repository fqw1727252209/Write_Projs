package com.study.platform.controller;

import com.study.platform.common.Result;
import com.study.platform.entity.Course;
import com.study.platform.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程Controller
 * 
 * @author Student
 * @date 2024-11-25
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/create")
    public Result<String> createCourse(@RequestBody Course course) {
        courseService.createCourse(course);
        return Result.success("添加成功");
    }

    @GetMapping("/list")
    public Result<List<Course>> getCourseList(@RequestParam Long userId,
            @RequestParam(required = false) String semester) {
        List<Course> list = courseService.getCourseList(userId, semester);
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result<Course> getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        return Result.success(course);
    }

    @PutMapping("/update")
    public Result<String> updateCourse(@RequestBody Course course) {
        courseService.updateCourse(course);
        return Result.success("更新成功");
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return Result.success("删除成功");
    }

}
