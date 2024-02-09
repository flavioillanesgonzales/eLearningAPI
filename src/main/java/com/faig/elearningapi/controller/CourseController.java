package com.faig.elearningapi.controller;

import com.faig.elearningapi.dto.CourseDTO;
import com.faig.elearningapi.dto.LessonDTO;
import com.faig.elearningapi.dto.LessonDetailsDTO;
import com.faig.elearningapi.model.Course;
import com.faig.elearningapi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long courseId) {
        return courseService.getCourseById(courseId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course createdCourse = courseService.createCourse(course);
        return ResponseEntity.ok(createdCourse);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long courseId, @RequestBody Course updatedCourse) {
        Course updated = courseService.updateCourse(courseId, updatedCourse);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/user/{userId}")
    public List<CourseDTO> getAllCoursesForUser(@PathVariable Long userId) {
        return courseService.getAllCoursesForUser(userId);
    }
    @GetMapping("/{courseId}/{userId}")
    public CourseDTO getCourseById(@PathVariable Long courseId, @PathVariable Long userId) {
        return courseService.getCourseById(courseId, userId);
    }

    @GetMapping("/{courseId}/lessons")
    public List<LessonDTO> getLessonsForCourse(@PathVariable Long courseId) {
        return courseService.getLessonsForCourse(courseId);
    }


    @GetMapping("/{courseId}/lessons/{lessonId}")
    public LessonDetailsDTO getLessonDetails(@PathVariable Long courseId, @PathVariable Long lessonId) {
        return courseService.getLessonDetails(courseId, lessonId);
    }

    @PostMapping("/{courseId}/enroll")
    public void enrollInCourse(@PathVariable Long courseId, @RequestBody Map<String, Long> requestBody) {
        Long userId = requestBody.get("userId");
        courseService.enrollInCourse(userId, courseId);
    }
}
