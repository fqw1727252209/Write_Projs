-- =============================================
-- 在校学生个人学习规划平台 - 创建数据表
-- =============================================

USE student_study_platform;

-- =============================================
-- 1. 用户表
-- =============================================
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `student_id` VARCHAR(50) NOT NULL COMMENT '学号',
    `username` VARCHAR(50) NOT NULL COMMENT '姓名',
    `password` VARCHAR(100) NOT NULL COMMENT '密码（MD5加密）',
    `major` VARCHAR(100) DEFAULT NULL COMMENT '专业',
    `grade` VARCHAR(20) DEFAULT NULL COMMENT '年级',
    `email` VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
    `phone` VARCHAR(20) DEFAULT NULL COMMENT '手机号',
    `avatar` VARCHAR(255) DEFAULT NULL COMMENT '头像URL',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_student_id` (`student_id`),
    KEY `idx_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- =============================================
-- 2. 学习计划表
-- =============================================
DROP TABLE IF EXISTS `study_plan`;
CREATE TABLE `study_plan` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `plan_name` VARCHAR(100) NOT NULL COMMENT '计划名称',
    `plan_goal` VARCHAR(500) DEFAULT NULL COMMENT '计划目标',
    `start_date` DATE NOT NULL COMMENT '开始日期',
    `end_date` DATE NOT NULL COMMENT '结束日期',
    `description` TEXT COMMENT '计划描述',
    `status` TINYINT DEFAULT 0 COMMENT '状态：0-进行中，1-已完成，2-已过期',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_status` (`status`),
    KEY `idx_start_date` (`start_date`),
    CONSTRAINT `fk_plan_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='学习计划表';

-- =============================================
-- 3. 学习任务表
-- =============================================
DROP TABLE IF EXISTS `study_task`;
CREATE TABLE `study_task` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `plan_id` BIGINT DEFAULT NULL COMMENT '所属计划ID（可为空）',
    `task_name` VARCHAR(100) NOT NULL COMMENT '任务名称',
    `priority` TINYINT DEFAULT 2 COMMENT '优先级：1-高，2-中，3-低',
    `deadline` DATETIME DEFAULT NULL COMMENT '截止时间',
    `content` TEXT COMMENT '任务内容',
    `status` TINYINT DEFAULT 0 COMMENT '状态：0-未开始，1-进行中，2-已完成',
    `complete_time` DATETIME DEFAULT NULL COMMENT '完成时间',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_plan_id` (`plan_id`),
    KEY `idx_status` (`status`),
    KEY `idx_priority` (`priority`),
    CONSTRAINT `fk_task_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_task_plan` FOREIGN KEY (`plan_id`) REFERENCES `study_plan` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='学习任务表';

-- =============================================
-- 4. 课程表
-- =============================================
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `course_name` VARCHAR(100) NOT NULL COMMENT '课程名称',
    `teacher` VARCHAR(50) DEFAULT NULL COMMENT '授课教师',
    `week_day` TINYINT NOT NULL COMMENT '星期几：1-7（周一到周日）',
    `start_section` TINYINT NOT NULL COMMENT '开始节次：1-12',
    `end_section` TINYINT NOT NULL COMMENT '结束节次：1-12',
    `location` VARCHAR(100) DEFAULT NULL COMMENT '上课地点',
    `credit` DECIMAL(3,1) DEFAULT NULL COMMENT '学分',
    `semester` VARCHAR(20) DEFAULT NULL COMMENT '学期：如2024-2025-1',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_week_day` (`week_day`),
    KEY `idx_semester` (`semester`),
    CONSTRAINT `fk_course_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='课程表';

-- =============================================
-- 5. 学习笔记表
-- =============================================
DROP TABLE IF EXISTS `study_note`;
CREATE TABLE `study_note` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `course_id` BIGINT DEFAULT NULL COMMENT '关联课程ID（可为空）',
    `title` VARCHAR(200) NOT NULL COMMENT '笔记标题',
    `content` TEXT COMMENT '笔记内容',
    `category` VARCHAR(50) DEFAULT '其他' COMMENT '分类：课堂笔记、读书笔记、心得体会、其他',
    `is_favorite` TINYINT DEFAULT 0 COMMENT '是否收藏：0-否，1-是',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_course_id` (`course_id`),
    KEY `idx_category` (`category`),
    KEY `idx_is_favorite` (`is_favorite`),
    FULLTEXT KEY `ft_title_content` (`title`, `content`) WITH PARSER ngram,
    CONSTRAINT `fk_note_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_note_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='学习笔记表';

-- =============================================
-- 6. 学习资源表
-- =============================================
DROP TABLE IF EXISTS `study_resource`;
CREATE TABLE `study_resource` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `course_id` BIGINT DEFAULT NULL COMMENT '关联课程ID（可为空）',
    `resource_name` VARCHAR(200) NOT NULL COMMENT '资源名称',
    `file_name` VARCHAR(255) NOT NULL COMMENT '文件名',
    `file_path` VARCHAR(500) NOT NULL COMMENT '文件存储路径',
    `file_type` VARCHAR(50) DEFAULT NULL COMMENT '文件类型：pdf、doc、ppt、video等',
    `file_size` BIGINT DEFAULT 0 COMMENT '文件大小（KB）',
    `resource_type` TINYINT DEFAULT 4 COMMENT '资源分类：1-课件，2-习题，3-参考资料，4-其他',
    `download_count` INT DEFAULT 0 COMMENT '下载次数',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_course_id` (`course_id`),
    KEY `idx_resource_type` (`resource_type`),
    CONSTRAINT `fk_resource_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_resource_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='学习资源表';

-- =============================================
-- 显示所有表
-- =============================================
SHOW TABLES;

SELECT '所有数据表创建成功！' AS message;

