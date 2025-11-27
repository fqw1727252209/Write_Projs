package com.study.platform.mapper;

import com.study.platform.entity.StudyTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学习任务Mapper接口
 * 
 * @author Student
 * @date 2024-11-25
 */
public interface StudyTaskMapper {

    /**
     * 根据用户ID查询任务列表
     */
    List<StudyTask> selectByUserId(@Param("userId") Long userId);

    /**
     * 根据计划ID查询任务列表
     */
    List<StudyTask> selectByPlanId(@Param("planId") Long planId);

    /**
     * 根据用户ID和状态查询任务列表
     */
    List<StudyTask> selectByUserIdAndStatus(@Param("userId") Long userId, @Param("status") Integer status);

    /**
     * 根据ID查询任务
     */
    StudyTask selectById(@Param("id") Long id);

    /**
     * 插入任务
     */
    int insert(StudyTask studyTask);

    /**
     * 更新任务
     */
    int update(StudyTask studyTask);

    /**
     * 删除任务
     */
    int deleteById(@Param("id") Long id);

    /**
     * 统计用户的任务数量
     */
    int countByUserId(@Param("userId") Long userId);

    /**
     * 统计用户已完成的任务数量
     */
    int countCompletedByUserId(@Param("userId") Long userId);

}
