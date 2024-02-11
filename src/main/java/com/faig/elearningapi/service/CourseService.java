package com.faig.elearningapi.service;

import com.faig.elearningapi.dto.*;
import com.faig.elearningapi.model.*;
import com.faig.elearningapi.model.Course;
import com.faig.elearningapi.repository.CourseRepository;
import com.faig.elearningapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public Optional<CourseDTO> getCourseById(Long courseId) {
        return courseRepository.findById(courseId).map(this::mapToCourse);
    }

    @Transactional
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Transactional
    public Course updateCourse(Long courseId, Course updatedCourse) {
        return courseRepository.findById(courseId)
                .map(course -> {
                    course.setName(updatedCourse.getName());
                    course.setDescription(updatedCourse.getDescription());
                    return courseRepository.save(course);
                })
                .orElse(null);
    }
    @Transactional
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }
    @Transactional
    public List<CourseDTO> getAllCoursesForUser(Long userId) {
        List<Course> allCourses = courseRepository.findAll();
        // Obtener cursos en los que está inscrito el usuario
        Set<Course> enrolledCourses = userRepository.findById(userId)
                .map(User::getCourses)
                .orElse(Collections.emptySet());
        // Mapear a DTO y marcar si el usuario está inscrito en cada curso
        return allCourses.stream()
                .map(course -> mapToCourseDTO(course, enrolledCourses.contains(course)))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(this::mapToCourse)
                .collect(Collectors.toList());
    }

    // Método de mapeo DTO para Course
    public CourseDTO mapToCourse(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setDescription(course.getDescription());
        // Otros mapeos según sea necesario
        return courseDTO;
    }

    @Transactional
    public CourseDTO getCourseById(Long courseId, Long userId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return mapToCourseDTO(course, isEnrolled(user, course));
    }
    @Transactional
    public void enrollInCourse(Long userId, Long courseId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        user.enrollInCourse(course);
        userRepository.save(user);
    }
    @Transactional
    public List<LessonDTO> getLessonsForCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        return course.getLessons().stream()
                .map(this::mapToLessonDTO)
                .collect(Collectors.toList());
    }
    @Transactional
    public LessonDetailsDTO getLessonDetails(Long courseId, Long lessonId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        Lesson lesson = course.getLessons().stream()
                .filter(l -> l.getId().equals(lessonId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Lección no encontrada"));
        return mapToLessonDetailsDTO(lesson);
    }



    private CourseDTO mapToCourseDTO(Course course, boolean enrolled) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setDescription(course.getDescription());
        courseDTO.setEnrolled(enrolled);
        return courseDTO;
    }
    private LessonDTO mapToLessonDTO(Lesson lesson) {
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setId(lesson.getId());
        lessonDTO.setTitle(lesson.getTitle());
        lessonDTO.setContent(lesson.getContent());
        lessonDTO.setLessonOrder(lesson.getLessonOrder());
        return lessonDTO;
    }


    private LessonDetailsDTO mapToLessonDetailsDTO(Lesson lesson) {
        LessonDetailsDTO lessonDetailsDTO = new LessonDetailsDTO();
        lessonDetailsDTO.setId(lesson.getId());
        lessonDetailsDTO.setTitle(lesson.getTitle());
        lessonDetailsDTO.setContent(lesson.getContent());

        // Lógica para mapear preguntas a QuestionDTO y agregarlas a LessonDetailsDTO
        List<QuestionDTO> questionDTOs = lesson.getQuestions().stream()
                .map(this::mapToQuestionDTO)
                .collect(Collectors.toList());
        lessonDetailsDTO.setQuestions(questionDTOs);

        return lessonDetailsDTO;
    }

    // Método de mapeo DTO para Question
    private QuestionDTO mapToQuestionDTO(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setText(question.getText());
        questionDTO.setType(question.getType());

        // Lógica para mapear respuestas a AnswerDTO y agregarlas a QuestionDTO
        List<AnswerDTO> answerDTOs = question.getAnswers().stream()
                .map(this::mapToAnswerDTO)
                .collect(Collectors.toList());
        questionDTO.setAnswers(answerDTOs);

        return questionDTO;
    }

    // Método de mapeo DTO para Answer
    private AnswerDTO mapToAnswerDTO(Answer answer) {
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setId(answer.getId());
        answerDTO.setText(answer.getText());
        answerDTO.setCorrect(answer.isCorrect());
        return answerDTO;
    }
    // Método para verificar si el usuario está inscrito en un curso
    private boolean isEnrolled(User user, Course course) {
        return user.getCourses().contains(course);
    }
}
