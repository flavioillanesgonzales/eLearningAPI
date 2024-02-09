package com.faig.elearningapi.dto;

import lombok.Data;

@Data
public class LessonDTO {
    private Long id;
    private String title;
    private String content;

    private int passing_score;
    private Long lessonOrder;
}
