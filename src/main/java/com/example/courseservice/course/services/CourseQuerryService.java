package com.example.courseservice.course.services;


import com.example.courseservice.course.dto.CourseResponse;
import com.example.courseservice.course.model.Course;

import java.util.Optional;

public interface CourseQuerryService {
    CourseResponse findByCode(String code);
}
