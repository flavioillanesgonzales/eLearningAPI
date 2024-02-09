package com.faig.elearningapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class LessonDetailsDTO {
    private Long id;
    private String title;
    private String content;
    private List<QuestionDTO> questions;
}
