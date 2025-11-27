package com.study.platform.service.impl;

import com.study.platform.mapper.CourseMapper;
import com.study.platform.mapper.StudyPlanMapper;
import com.study.platform.mapper.StudyTaskMapper;
import com.study.platform.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 统计Service实现类
 * 
 * @author Student
 * @date 2024-11-25
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StudyPlanMapper studyPlanMapper;

    @Autowired
    private StudyTaskMapper studyTaskMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Map<String, Object> getUserStatistics(Long userId) {
        Map<String, Object> statistics = new HashMap<>();

        // 计划统计
        int totalPlans = studyPlanMapper.countByUserId(userId);
        int completedPlans = studyPlanMapper.countCompletedByUserId(userId);
        double planCompletionRate = totalPlans > 0 ? (double) completedPlans / totalPlans * 100 : 0;

        statistics.put("totalPlans", totalPlans);
        statistics.put("completedPlans", completedPlans);
        statistics.put("planCompletionRate", String.format("%.1f", planCompletionRate));

        // 任务统计
        int totalTasks = studyTaskMapper.countByUserId(userId);
        int completedTasks = studyTaskMapper.countCompletedByUserId(userId);
        double taskCompletionRate = totalTasks > 0 ? (double) completedTasks / totalTasks * 100 : 0;

        statistics.put("totalTasks", totalTasks);
        statistics.put("completedTasks", completedTasks);
        statistics.put("taskCompletionRate", String.format("%.1f", taskCompletionRate));

        // 课程统计
        int totalCourses = courseMapper.countByUserId(userId);
        statistics.put("totalCourses", totalCourses);

        return statistics;
    }

}
