package com.faig.elearningapi.dto;

import com.faig.elearningapi.model.Answer;
import lombok.Data;

import java.util.List;

@Data
public class LessonAnswersDTO {
    private Long lessonId;
    private List<Answer> answers;

}
