package com.faig.elearningapi.controller;

import com.faig.elearningapi.dto.QuestionAnswerDTO;
import com.faig.elearningapi.dto.SubmitAnswersDTO;
import com.faig.elearningapi.service.LearningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/learning")
public class LearningController {
    @Autowired
    private LearningService learningService;

    @PostMapping("/submitAnswers/{userId}/{courseId}")
    public ResponseEntity<String> submitAnswers(@PathVariable Long userId , @PathVariable Long courseId, @RequestBody SubmitAnswersDTO submitAnswersDTO  ) {

        Long lessonId = submitAnswersDTO.getLessonId();
        List<QuestionAnswerDTO> questionAnswers = submitAnswersDTO.getAnswers();
        if (!questionAnswers.isEmpty()) {
            System.out.println("Mis ids controller" + userId + courseId);
            return learningService.processAnswers(lessonId, questionAnswers , userId , courseId);
        } else {
            return ResponseEntity.badRequest().body("Debes proporcionar respuestas para la lección");
        }
    }

}
