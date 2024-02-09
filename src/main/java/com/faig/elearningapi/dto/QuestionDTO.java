package com.faig.elearningapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {
    private Long id;
    private String text;
    private String type;
    private int score;
    private List<AnswerDTO> answers;
}
