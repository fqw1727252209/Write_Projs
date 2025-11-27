package com.study.platform.service;

import com.study.platform.entity.StudyTask;

import java.util.List;

/**
 * 学习任务Service接口
 * 
 * @author Student
 * @date 2024-11-25
 */
public interface StudyTaskService {

    void createTask(StudyTask studyTask);

    List<StudyTask> getTaskList(Long userId, Long planId, Integer status);

    StudyTask getTaskById(Long id);

    void updateTask(StudyTask studyTask);

    void deleteTask(Long id);

}
