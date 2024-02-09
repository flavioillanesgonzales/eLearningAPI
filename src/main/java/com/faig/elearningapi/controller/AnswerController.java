package com.faig.elearningapi.controller;

import com.faig.elearningapi.dto.AnswerDTO;
import com.faig.elearningapi.service.AnswertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {
    @Autowired
    private AnswertService answerService;

    @GetMapping("/{answerId}")
    public AnswerDTO getAnswerById(@PathVariable Long answerId) {
        return answerService.getAnswerById(answerId);
    }

    @GetMapping("/question/{questionId}")
    public List<AnswerDTO> getAllAnswersForQuestion(@PathVariable Long questionId) {
        return answerService.getAllAnswersForQuestion(questionId);
    }

}
