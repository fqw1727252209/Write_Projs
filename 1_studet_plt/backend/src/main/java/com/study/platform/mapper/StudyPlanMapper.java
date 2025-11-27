package com.study.platform.mapper;

import com.study.platform.entity.StudyPlan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学习计划Mapper接口
 * 
 * @author Student
 * @date 2024-11-25
 */
public interface StudyPlanMapper {

    /**
     * 根据用户ID查询学习计划列表
     */
    List<StudyPlan> selectByUserId(@Param("userId") Long userId);

    /**
     * 根据用户ID和状态查询学习计划列表
     */
    List<StudyPlan> selectByUserIdAndStatus(@Param("userId") Long userId, @Param("status") Integer status);

    /**
     * 根据ID查询学习计划
     */
    StudyPlan selectById(@Param("id") Long id);

    /**
     * 插入学习计划
     */
    int insert(StudyPlan studyPlan);

    /**
     * 更新学习计划
     */
    int update(StudyPlan studyPlan);

    /**
     * 删除学习计划
     */
    int deleteById(@Param("id") Long id);

    /**
     * 统计用户的计划数量
     */
    int countByUserId(@Param("userId") Long userId);

    /**
     * 统计用户已完成的计划数量
     */
    int countCompletedByUserId(@Param("userId") Long userId);

}
