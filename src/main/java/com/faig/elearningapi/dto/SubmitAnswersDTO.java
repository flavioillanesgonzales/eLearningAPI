package com.faig.elearningapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class SubmitAnswersDTO {
    private Long lessonId;
    private List<QuestionAnswerDTO> answers;

}
