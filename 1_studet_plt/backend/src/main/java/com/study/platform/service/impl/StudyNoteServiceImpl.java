package com.study.platform.service.impl;

import com.study.platform.entity.StudyNote;
import com.study.platform.mapper.StudyNoteMapper;
import com.study.platform.service.StudyNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学习笔记Service实现类
 * 
 * @author Student
 * @date 2024-11-25
 */
@Service
public class StudyNoteServiceImpl implements StudyNoteService {

    @Autowired
    private StudyNoteMapper studyNoteMapper;

    @Override
    public void createNote(StudyNote studyNote) {
        if (studyNote.getIsFavorite() == null) {
            studyNote.setIsFavorite(0);
        }

        int result = studyNoteMapper.insert(studyNote);
        if (result == 0) {
            throw new RuntimeException("创建学习笔记失败");
        }
    }

    @Override
    public List<StudyNote> getNoteList(Long userId, Long courseId, String category) {
        if (courseId != null) {
            return studyNoteMapper.selectByCourseId(courseId);
        } else if (category != null && !category.isEmpty()) {
            return studyNoteMapper.selectByUserIdAndCategory(userId, category);
        } else {
            return studyNoteMapper.selectByUserId(userId);
        }
    }

    @Override
    public List<StudyNote> searchNotes(Long userId, String keyword) {
        return studyNoteMapper.searchByKeyword(userId, keyword);
    }

    @Override
    public StudyNote getNoteById(Long id) {
        return studyNoteMapper.selectById(id);
    }

    @Override
    public void updateNote(StudyNote studyNote) {
        int result = studyNoteMapper.update(studyNote);
        if (result == 0) {
            throw new RuntimeException("更新学习笔记失败");
        }
    }

    @Override
    public void deleteNote(Long id) {
        int result = studyNoteMapper.deleteById(id);
        if (result == 0) {
            throw new RuntimeException("删除学习笔记失败");
        }
    }

}
