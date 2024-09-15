package com.example.courseservice.course.dto;


import java.time.LocalDateTime;
import java.util.List;

public record CourseResponse(
        Long id,
        String code,
        String imageUrl,
        String name,
        String description,
        String story,
        String benefits,
        Double price,
        Integer duration,
        String additionalDetails,
        List<String> learningObjectives,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
