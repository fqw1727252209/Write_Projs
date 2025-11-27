package com.study.platform.controller;

import com.study.platform.common.Result;
import com.study.platform.entity.StudyTask;
import com.study.platform.service.StudyTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学习任务Controller
 * 
 * @author Student
 * @date 2024-11-25
 */
@RestController
@RequestMapping("/task")
public class StudyTaskController {

    @Autowired
    private StudyTaskService studyTaskService;

    @PostMapping("/create")
    public Result<String> createTask(@RequestBody StudyTask studyTask) {
        studyTaskService.createTask(studyTask);
        return Result.success("创建成功");
    }

    @GetMapping("/list")
    public Result<List<StudyTask>> getTaskList(@RequestParam Long userId,
            @RequestParam(required = false) Long planId,
            @RequestParam(required = false) Integer status) {
        List<StudyTask> list = studyTaskService.getTaskList(userId, planId, status);
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result<StudyTask> getTaskById(@PathVariable Long id) {
        StudyTask task = studyTaskService.getTaskById(id);
        return Result.success(task);
    }

    @PutMapping("/update")
    public Result<String> updateTask(@RequestBody StudyTask studyTask) {
        studyTaskService.updateTask(studyTask);
        return Result.success("更新成功");
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteTask(@PathVariable Long id) {
        studyTaskService.deleteTask(id);
        return Result.success("删除成功");
    }

}
