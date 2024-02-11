package com.faig.elearningapi.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name ="user_course")
public class UserCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "user_id")
    Long userId;
    @Column(name = "course_id")
    Long courseId;
    String status;

    @Column(name = "learning_score")
    int learningScore;
}
