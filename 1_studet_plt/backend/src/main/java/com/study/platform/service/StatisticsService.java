package com.study.platform.service;

import java.util.Map;

/**
 * 统计Service接口
 * 
 * @author Student
 * @date 2024-11-25
 */
public interface StatisticsService {

    /**
     * 获取用户的学习统计数据
     */
    Map<String, Object> getUserStatistics(Long userId);

}
