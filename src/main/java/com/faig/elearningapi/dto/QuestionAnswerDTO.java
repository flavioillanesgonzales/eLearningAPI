package com.faig.elearningapi.dto;

import lombok.Data;

@Data
public class QuestionAnswerDTO {
    private Long questionId;
    private Long answerId;
    private int score;
}
