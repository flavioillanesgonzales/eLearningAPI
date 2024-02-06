package com.faig.elearningapi.controller;

import com.faig.elearningapi.model.Question;
import com.faig.elearningapi.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/lesson/{lessonId}")
    public List<Question> getQuestionsByLessonId(@PathVariable Long lessonId) {
        return questionService.getQuestionsByLessonId(lessonId);
    }

    @GetMapping("/{questionId}")
    public Question getQuestionById(@PathVariable Long questionId) {
        return questionService.getQuestionById(questionId);
    }
}
