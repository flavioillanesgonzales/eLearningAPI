package com.faig.elearningapi.service;

import com.faig.elearningapi.dto.LessonDTO;
import com.faig.elearningapi.model.Course;
import com.faig.elearningapi.model.Lesson;
import com.faig.elearningapi.repository.CourseRepository;
import com.faig.elearningapi.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<LessonDTO> getAllLessons() {
        List<Lesson> lessons = lessonRepository.findAll();
        return lessons.stream()
                .map(this::mapToLessonDTO)
                .collect(Collectors.toList());
    }




    public Lesson createLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public Lesson updateLesson(Long lessonId, Lesson updatedLesson) {
        return lessonRepository.findById(lessonId)
                .map(lesson -> {
                    lesson.setTitle(updatedLesson.getTitle());
                    lesson.setContent(updatedLesson.getContent());
                    return lessonRepository.save(lesson);
                })
                .orElse(null);
    }

    public void deleteLesson(Long lessonId) {
        lessonRepository.deleteById(lessonId);
    }
    public LessonDTO getLessonById(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lección no encontrada"));

        return mapToLessonDTO(lesson);
    }

    public List<LessonDTO> getAllLessonsForCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        return course.getLessons().stream()
                .map(this::mapToLessonDTO)
                .collect(Collectors.toList());
    }

    private LessonDTO mapToLessonDTO(Lesson lesson) {
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setId(lesson.getId());
        lessonDTO.setTitle(lesson.getTitle());
        lessonDTO.setContent(lesson.getContent());
        lessonDTO.setLessonOrder(lesson.getLessonOrder());
        lessonDTO.setPassing_score(lesson.getPassing_score());
        return lessonDTO;
    }
}
