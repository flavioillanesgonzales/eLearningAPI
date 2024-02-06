package com.faig.elearningapi.service;

import com.faig.elearningapi.model.Question;
import com.faig.elearningapi.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getQuestionsByLessonId(Long lessonId) {
        return questionRepository.findByLessonId(lessonId);
    }

    public Question getQuestionById(Long questionId) {
        return questionRepository.findById(questionId).orElse(null);
    }
}
