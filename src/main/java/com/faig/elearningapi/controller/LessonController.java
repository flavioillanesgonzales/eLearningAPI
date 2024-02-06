package com.faig.elearningapi.controller;

import com.faig.elearningapi.model.Lesson;
import com.faig.elearningapi.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonController {
    @Autowired
    private LessonService lessonService;

    @GetMapping("/course/{courseId}")
    public List<Lesson> getLessonsByCourseId(@PathVariable Long courseId) {
        return lessonService.getLessonsByCourseId(courseId);
    }

    @GetMapping("/{lessonId}")
    public Lesson getLessonById(@PathVariable Long lessonId) {
        return lessonService.getLessonById(lessonId);
    }
}
