package com.faig.elearningapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class AnswerRequestDTO {
    private Long questionId;
    private List<Long> selectedAnswerIds;
}
