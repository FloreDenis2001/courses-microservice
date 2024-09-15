package com.example.courseservice.course.dto;

import java.io.Serializable;
import java.util.List;

public record CourseCreateRequest(
        String name,
        String description,
        String story,
        String benefits,
        Integer duration,
        Double price,
        String additionalDetails,
        List<String> learningObjectives
) implements Serializable {
}
