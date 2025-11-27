package com.study.platform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 课程实体类
 * 
 * @author Student
 * @date 2024-11-25
 */
@Data
public class Course {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 授课教师
     */
    private String teacher;

    /**
     * 星期几：1-7
     */
    private Integer weekDay;

    /**
     * 开始节次
     */
    private Integer startSection;

    /**
     * 结束节次
     */
    private Integer endSection;

    /**
     * 上课地点
     */
    private String location;

    /**
     * 学分
     */
    private BigDecimal credit;

    /**
     * 学期
     */
    private String semester;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}
