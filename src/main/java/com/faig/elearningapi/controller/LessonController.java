package com.faig.elearningapi.controller;

import com.faig.elearningapi.dto.LessonDTO;
import com.faig.elearningapi.model.Lesson;
import com.faig.elearningapi.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonController {
    @Autowired
    private LessonService lessonService;
    @GetMapping
    public List<LessonDTO> getAllLessons() {
        return lessonService.getAllLessons();
    }
    @GetMapping("/{lessonId}")
    public LessonDTO getLessonById(@PathVariable Long lessonId) {
        return lessonService.getLessonById(lessonId);
    }
    @PostMapping
    public ResponseEntity<Lesson> createLesson(@RequestBody Lesson lesson) {
        Lesson createdLesson = lessonService.createLesson(lesson);
        return ResponseEntity.ok(createdLesson);
    }

    @PutMapping("/{lessonId}")
    public ResponseEntity<Lesson> updateLesson(@PathVariable Long lessonId, @RequestBody Lesson updatedLesson) {
        Lesson updated = lessonService.updateLesson(lessonId, updatedLesson);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{lessonId}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long lessonId) {
        lessonService.deleteLesson(lessonId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/course/{courseId}")
    public List<LessonDTO> getAllLessonsForCourse(@PathVariable Long courseId) {
        return lessonService.getAllLessonsForCourse(courseId);
    }

}
