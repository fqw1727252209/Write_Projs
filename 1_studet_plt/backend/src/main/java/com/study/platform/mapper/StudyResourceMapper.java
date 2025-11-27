package com.study.platform.mapper;

import com.study.platform.entity.StudyResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学习资源Mapper接口
 * 
 * @author Student
 * @date 2024-11-25
 */
public interface StudyResourceMapper {

    /**
     * 根据用户ID查询资源列表
     */
    List<StudyResource> selectByUserId(@Param("userId") Long userId);

    /**
     * 根据用户ID和资源类型查询资源列表
     */
    List<StudyResource> selectByUserIdAndType(@Param("userId") Long userId,
            @Param("resourceType") Integer resourceType);

    /**
     * 根据课程ID查询资源列表
     */
    List<StudyResource> selectByCourseId(@Param("courseId") Long courseId);

    /**
     * 根据ID查询资源
     */
    StudyResource selectById(@Param("id") Long id);

    /**
     * 插入资源
     */
    int insert(StudyResource studyResource);

    /**
     * 更新资源
     */
    int update(StudyResource studyResource);

    /**
     * 删除资源
     */
    int deleteById(@Param("id") Long id);

    /**
     * 增加下载次数
     */
    int incrementDownloadCount(@Param("id") Long id);

}
