package com.study.platform.controller;

import com.study.platform.common.Result;
import com.study.platform.entity.StudyPlan;
import com.study.platform.service.StudyPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学习计划Controller
 * 
 * @author Student
 * @date 2024-11-25
 */
@RestController
@RequestMapping("/plan")
public class StudyPlanController {

    @Autowired
    private StudyPlanService studyPlanService;

    /**
     * 创建学习计划
     */
    @PostMapping("/create")
    public Result<String> createPlan(@RequestBody StudyPlan studyPlan) {
        studyPlanService.createPlan(studyPlan);
        return Result.success("创建成功");
    }

    /**
     * 获取学习计划列表
     */
    @GetMapping("/list")
    public Result<List<StudyPlan>> getPlanList(@RequestParam Long userId,
            @RequestParam(required = false) Integer status) {
        List<StudyPlan> list = studyPlanService.getPlanList(userId, status);
        return Result.success(list);
    }

    /**
     * 获取学习计划详情
     */
    @GetMapping("/{id}")
    public Result<StudyPlan> getPlanById(@PathVariable Long id) {
        StudyPlan plan = studyPlanService.getPlanById(id);
        return Result.success(plan);
    }

    /**
     * 更新学习计划
     */
    @PutMapping("/update")
    public Result<String> updatePlan(@RequestBody StudyPlan studyPlan) {
        studyPlanService.updatePlan(studyPlan);
        return Result.success("更新成功");
    }

    /**
     * 删除学习计划
     */
    @DeleteMapping("/{id}")
    public Result<String> deletePlan(@PathVariable Long id) {
        studyPlanService.deletePlan(id);
        return Result.success("删除成功");
    }

}
