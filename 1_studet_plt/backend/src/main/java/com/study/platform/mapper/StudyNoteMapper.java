package com.study.platform.mapper;

import com.study.platform.entity.StudyNote;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学习笔记Mapper接口
 * 
 * @author Student
 * @date 2024-11-25
 */
public interface StudyNoteMapper {

    /**
     * 根据用户ID查询笔记列表
     */
    List<StudyNote> selectByUserId(@Param("userId") Long userId);

    /**
     * 根据用户ID和分类查询笔记列表
     */
    List<StudyNote> selectByUserIdAndCategory(@Param("userId") Long userId, @Param("category") String category);

    /**
     * 根据课程ID查询笔记列表
     */
    List<StudyNote> selectByCourseId(@Param("courseId") Long courseId);

    /**
     * 根据ID查询笔记
     */
    StudyNote selectById(@Param("id") Long id);

    /**
     * 搜索笔记（按标题或内容）
     */
    List<StudyNote> searchByKeyword(@Param("userId") Long userId, @Param("keyword") String keyword);

    /**
     * 插入笔记
     */
    int insert(StudyNote studyNote);

    /**
     * 更新笔记
     */
    int update(StudyNote studyNote);

    /**
     * 删除笔记
     */
    int deleteById(@Param("id") Long id);

}
