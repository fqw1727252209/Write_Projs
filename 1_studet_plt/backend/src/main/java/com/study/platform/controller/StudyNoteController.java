package com.study.platform.controller;

import com.study.platform.common.Result;
import com.study.platform.entity.StudyNote;
import com.study.platform.service.StudyNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学习笔记Controller
 * 
 * @author Student
 * @date 2024-11-25
 */
@RestController
@RequestMapping("/note")
public class StudyNoteController {

    @Autowired
    private StudyNoteService studyNoteService;

    @PostMapping("/create")
    public Result<String> createNote(@RequestBody StudyNote studyNote) {
        studyNoteService.createNote(studyNote);
        return Result.success("创建成功");
    }

    @GetMapping("/list")
    public Result<List<StudyNote>> getNoteList(@RequestParam Long userId,
            @RequestParam(required = false) Long courseId,
            @RequestParam(required = false) String category) {
        List<StudyNote> list = studyNoteService.getNoteList(userId, courseId, category);
        return Result.success(list);
    }

    @GetMapping("/search")
    public Result<List<StudyNote>> searchNotes(@RequestParam Long userId,
            @RequestParam String keyword) {
        List<StudyNote> list = studyNoteService.searchNotes(userId, keyword);
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result<StudyNote> getNoteById(@PathVariable Long id) {
        StudyNote note = studyNoteService.getNoteById(id);
        return Result.success(note);
    }

    @PutMapping("/update")
    public Result<String> updateNote(@RequestBody StudyNote studyNote) {
        studyNoteService.updateNote(studyNote);
        return Result.success("更新成功");
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteNote(@PathVariable Long id) {
        studyNoteService.deleteNote(id);
        return Result.success("删除成功");
    }

}
