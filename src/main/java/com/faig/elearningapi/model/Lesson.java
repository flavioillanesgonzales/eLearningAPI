package com.faig.elearningapi.model;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @Column(name = "lesson_order")
    private Long lessonOrder;
    private int passing_score;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private List<Question> questions;
}
