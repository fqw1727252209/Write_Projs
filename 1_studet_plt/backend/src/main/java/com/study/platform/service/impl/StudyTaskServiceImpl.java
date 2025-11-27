package com.study.platform.service.impl;

import com.study.platform.entity.StudyTask;
import com.study.platform.mapper.StudyTaskMapper;
import com.study.platform.service.StudyTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 学习任务Service实现类
 * 
 * @author Student
 * @date 2024-11-25
 */
@Service
public class StudyTaskServiceImpl implements StudyTaskService {

    @Autowired
    private StudyTaskMapper studyTaskMapper;

    @Override
    public void createTask(StudyTask studyTask) {
        if (studyTask.getStatus() == null) {
            studyTask.setStatus(0);
        }
        if (studyTask.getPriority() == null) {
            studyTask.setPriority(2);
        }

        int result = studyTaskMapper.insert(studyTask);
        if (result == 0) {
            throw new RuntimeException("创建学习任务失败");
        }
    }

    @Override
    public List<StudyTask> getTaskList(Long userId, Long planId, Integer status) {
        if (planId != null) {
            return studyTaskMapper.selectByPlanId(planId);
        } else if (status != null) {
            return studyTaskMapper.selectByUserIdAndStatus(userId, status);
        } else {
            return studyTaskMapper.selectByUserId(userId);
        }
    }

    @Override
    public StudyTask getTaskById(Long id) {
        return studyTaskMapper.selectById(id);
    }

    @Override
    public void updateTask(StudyTask studyTask) {
        // 如果状态变为已完成，记录完成时间
        if (studyTask.getStatus() != null && studyTask.getStatus() == 2) {
            studyTask.setCompleteTime(new Date());
        }

        int result = studyTaskMapper.update(studyTask);
        if (result == 0) {
            throw new RuntimeException("更新学习任务失败");
        }
    }

    @Override
    public void deleteTask(Long id) {
        int result = studyTaskMapper.deleteById(id);
        if (result == 0) {
            throw new RuntimeException("删除学习任务失败");
        }
    }

}
