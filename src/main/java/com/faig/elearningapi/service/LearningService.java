package com.faig.elearningapi.service;

import com.faig.elearningapi.dto.AnswerDTO;
import com.faig.elearningapi.dto.QuestionAnswerDTO;
import com.faig.elearningapi.model.Answer;
import com.faig.elearningapi.model.Lesson;
import com.faig.elearningapi.model.Question;
import com.faig.elearningapi.model.UserCourse;
import com.faig.elearningapi.repository.LessonRepository;
import com.faig.elearningapi.repository.UserCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LearningService {
    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private UserCourseRepository userCourseRepository;

    public ResponseEntity<String> processAnswers(Long lessonId, List<QuestionAnswerDTO> questionAnswers, Long userId, Long courseId) {
        Optional<Lesson> optionalLesson = lessonRepository.findById(lessonId);
        if (optionalLesson.isEmpty()) {
            return ResponseEntity.badRequest().body("Lección no encontrada");
        }
        Lesson lesson = optionalLesson.get();
        List<AnswerDTO> correctAnswers = getCorrectAnswers(lesson);
        // Verifica que las respuestas estén presentes y calcular la puntuación
        if (!questionAnswers.isEmpty()) {
            return calculateScore(questionAnswers, correctAnswers, lesson.getPassing_score(), userId, courseId);
        } else {
            return ResponseEntity.badRequest().body("Debes proporcionar respuestas para la lección");
        }
    }


    private List<AnswerDTO> getCorrectAnswers(Lesson lesson) {
        List<AnswerDTO> correctAnswers = new ArrayList<>();
        for (Question question : lesson.getQuestions()) {
            for (Answer answer : question.getAnswers()) {
                if (answer.isCorrect()) {
                    // Agrega la respuesta correcta a la lista
                    AnswerDTO answerDTO = new AnswerDTO();
                    answerDTO.setId(answer.getId());
                    answerDTO.setText(answer.getText());
                    answerDTO.setCorrect(true);
                    correctAnswers.add(answerDTO);
                }
            }
        }
        return correctAnswers;
    }

    private ResponseEntity<String> calculateScore(List<QuestionAnswerDTO> QuestionAnswers, List<AnswerDTO> correctAnswers, int passing_score, Long userId, Long courseId) {
        int score = 0;
        if (QuestionAnswers.size() != correctAnswers.size()) {
            return ResponseEntity.badRequest().body("Debes contestar todas las preguntas de la lección");
        }

        for (QuestionAnswerDTO submitAnswer : QuestionAnswers) {
            Optional<AnswerDTO> matchingCorrectAnswer = correctAnswers.stream()
                    .filter(correctAnswer -> correctAnswer.getId().equals(submitAnswer.getAnswerId()))
                    .findFirst();
            if (matchingCorrectAnswer.isPresent() && matchingCorrectAnswer.get().getId().equals(submitAnswer.getAnswerId())) {
                score = score + submitAnswer.getScore();
            }
        }
        if (score >= passing_score) {
            updateLearningScore(userId, courseId, score);
            return ResponseEntity.ok("Felicidades, ha pasadp la lección, su Puntuación es: " + score);
        } else {
            return ResponseEntity.ok("No pasó con la puntuación mínima, su puntuación es: " + score);
        }
    }
    private void updateLearningScore(Long userId, Long courseId, int newScore) {
        if (userId != null && courseId != null) {
            UserCourse userCourse = userCourseRepository.findByUserIdAndCourseId(courseId, courseId)
                    .orElseThrow(() -> new EntityNotFoundException("UserCourse no encontrado"));
            userCourse.setStatus("Aprobado");
            userCourse.setLearningScore(newScore);
            userCourseRepository.save(userCourse);
        }
    }


}

