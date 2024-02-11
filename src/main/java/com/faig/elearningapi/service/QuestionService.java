package com.faig.elearningapi.service;

import com.faig.elearningapi.dto.AnswerDTO;
import com.faig.elearningapi.dto.QuestionDTO;
import com.faig.elearningapi.model.Answer;
import com.faig.elearningapi.model.Lesson;
import com.faig.elearningapi.model.Question;
import com.faig.elearningapi.repository.LessonRepository;
import com.faig.elearningapi.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private LessonRepository lessonRepository;

    public List<QuestionDTO> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream()
                .map(this::mapToQuestionDTO)
                .collect(Collectors.toList());
    }

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question updateQuestion(Long questionId, Question updatedQuestion) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    question.setText(updatedQuestion.getText());
                    question.setType(updatedQuestion.getType());
                    return questionRepository.save(question);
                })
                .orElse(null);
    }

    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }


    public QuestionDTO getQuestionById(Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Pregunta no encontrada"));
        return mapToQuestionDTO(question);
    }

    public List<QuestionDTO> getAllQuestionsForLesson(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lección no encontrada"));

        return lesson.getQuestions().stream()
                .map(this::mapToQuestionDTO)
                .collect(Collectors.toList());
    }


    private QuestionDTO mapToQuestionDTO(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setText(question.getText());
        questionDTO.setType(question.getType());
        questionDTO.setScore(question.getScore());
        // Lógica para mapear respuestas a AnswerDTO y agregarlas a QuestionDTO
        List<AnswerDTO> answerDTOs = question.getAnswers().stream()
                .map(this::mapToAnswerDTO)
                .collect(Collectors.toList());
        questionDTO.setAnswers(answerDTOs);

        return questionDTO;
    }

    private AnswerDTO mapToAnswerDTO(Answer answer) {
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setId(answer.getId());
        answerDTO.setText(answer.getText());
        answerDTO.setCorrect(answer.isCorrect());
        return answerDTO;
    }
}
