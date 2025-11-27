package com.study.platform.controller;

import com.study.platform.common.Result;
import com.study.platform.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 统计Controller
 * 
 * @author Student
 * @date 2024-11-25
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    /**
     * 获取用户学习统计数据
     */
    @GetMapping("/user/{userId}")
    public Result<Map<String, Object>> getUserStatistics(@PathVariable Long userId) {
        Map<String, Object> statistics = statisticsService.getUserStatistics(userId);
        return Result.success(statistics);
    }

}
