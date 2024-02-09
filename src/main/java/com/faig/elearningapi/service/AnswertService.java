package com.faig.elearningapi.service;

import com.faig.elearningapi.dto.AnswerDTO;
import com.faig.elearningapi.model.Answer;
import com.faig.elearningapi.model.Lesson;
import com.faig.elearningapi.model.Question;
import com.faig.elearningapi.repository.AnswerRepository;
import com.faig.elearningapi.repository.LessonRepository;
import com.faig.elearningapi.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswertService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private LessonRepository lessonRepository;
    @Transactional
    public AnswerDTO getAnswerById(Long answerId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new RuntimeException("Respuesta no encontrada"));

        return mapToAnswerDTO(answer);
    }
    @Transactional
    public List<AnswerDTO> getAllAnswersForQuestion(Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Pregunta no encontrada"));

        return question.getAnswers().stream()
                .map(this::mapToAnswerDTO)
                .collect(Collectors.toList());
    }
    // Método de mapeo DTO para Answer
    private AnswerDTO mapToAnswerDTO(Answer answer) {
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setId(answer.getId());
        answerDTO.setText(answer.getText());
        answerDTO.setCorrect(answer.isCorrect());
        return answerDTO;
    }
    @Transactional
    public void submitAnswers(Long lessonId, List<Answer> answers) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lección no encontrada"));
        validateAnswers(lesson, answers);
        answerRepository.saveAll(answers);
    }

    private void validateAnswers(Lesson lesson, List<Answer> answers) {
        for (Answer answer : answers) {
            if (answer.getQuestion() == null || !answer.getQuestion().getLesson().equals(lesson)) {
                throw new IllegalArgumentException("Todas las preguntas deben pertenecer a la lección proporcionada.");
            }
        }
    }


}
