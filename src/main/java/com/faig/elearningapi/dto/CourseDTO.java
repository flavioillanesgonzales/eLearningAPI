package com.faig.elearningapi.dto;

import lombok.Data;

@Data
public class CourseDTO {
    private Long id;
    private String name;
    private String description;
    private boolean enrolled;
}
