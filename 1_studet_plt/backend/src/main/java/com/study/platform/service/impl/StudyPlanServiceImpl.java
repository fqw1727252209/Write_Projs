package com.study.platform.service.impl;

import com.study.platform.entity.StudyPlan;
import com.study.platform.mapper.StudyPlanMapper;
import com.study.platform.service.StudyPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学习计划Service实现类
 * 
 * @author Student
 * @date 2024-11-25
 */
@Service
public class StudyPlanServiceImpl implements StudyPlanService {

    @Autowired
    private StudyPlanMapper studyPlanMapper;

    @Override
    public void createPlan(StudyPlan studyPlan) {
        // 默认状态为进行中
        if (studyPlan.getStatus() == null) {
            studyPlan.setStatus(0);
        }

        int result = studyPlanMapper.insert(studyPlan);
        if (result == 0) {
            throw new RuntimeException("创建学习计划失败");
        }
    }

    @Override
    public List<StudyPlan> getPlanList(Long userId, Integer status) {
        if (status == null) {
            return studyPlanMapper.selectByUserId(userId);
        } else {
            return studyPlanMapper.selectByUserIdAndStatus(userId, status);
        }
    }

    @Override
    public StudyPlan getPlanById(Long id) {
        return studyPlanMapper.selectById(id);
    }

    @Override
    public void updatePlan(StudyPlan studyPlan) {
        int result = studyPlanMapper.update(studyPlan);
        if (result == 0) {
            throw new RuntimeException("更新学习计划失败");
        }
    }

    @Override
    public void deletePlan(Long id) {
        int result = studyPlanMapper.deleteById(id);
        if (result == 0) {
            throw new RuntimeException("删除学习计划失败");
        }
    }

}
