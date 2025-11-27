package com.study.platform.service;

import com.study.platform.entity.StudyNote;

import java.util.List;

/**
 * 学习笔记Service接口
 * 
 * @author Student
 * @date 2024-11-25
 */
public interface StudyNoteService {

    void createNote(StudyNote studyNote);

    List<StudyNote> getNoteList(Long userId, Long courseId, String category);

    List<StudyNote> searchNotes(Long userId, String keyword);

    StudyNote getNoteById(Long id);

    void updateNote(StudyNote studyNote);

    void deleteNote(Long id);

}
