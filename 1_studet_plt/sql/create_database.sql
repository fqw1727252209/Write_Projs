-- =============================================
-- 在校学生个人学习规划平台 - 创建数据库
-- =============================================

-- 如果数据库已存在则删除（注意：这会删除所有数据！）
DROP DATABASE IF EXISTS student_study_platform;

-- 创建数据库
CREATE DATABASE student_study_platform 
    DEFAULT CHARACTER SET utf8mb4 
    DEFAULT COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE student_study_platform;

-- 显示创建成功信息
SELECT '数据库 student_study_platform 创建成功！' AS message;

