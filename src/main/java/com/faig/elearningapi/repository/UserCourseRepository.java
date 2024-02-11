package com.faig.elearningapi.repository;

import com.faig.elearningapi.model.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {
    //@Query("SELECT uc FROM UserCourse uc WHERE uc.userId = :user_id AND uc.courseId = :course_id")
    Optional<UserCourse> findByUserIdAndCourseId( @Param("userId")  Long userId, @Param("courseId")  Long courseId);

}