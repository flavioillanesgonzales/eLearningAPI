package com.faig.elearningapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.Transient;
import lombok.Data;

@Data
public class AnswerDTO {
    private Long id;
    private String text;
    private boolean correct;
    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long questionId;
}
