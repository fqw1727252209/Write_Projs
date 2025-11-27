package com.study.platform.service;

import com.study.platform.entity.StudyPlan;

import java.util.List;

/**
 * 学习计划Service接口
 * 
 * @author Student
 * @date 2024-11-25
 */
public interface StudyPlanService {

    /**
     * 创建学习计划
     */
    void createPlan(StudyPlan studyPlan);

    /**
     * 获取用户的学习计划列表
     */
    List<StudyPlan> getPlanList(Long userId, Integer status);

    /**
     * 根据ID获取学习计划
     */
    StudyPlan getPlanById(Long id);

    /**
     * 更新学习计划
     */
    void updatePlan(StudyPlan studyPlan);

    /**
     * 删除学习计划
     */
    void deletePlan(Long id);

}
