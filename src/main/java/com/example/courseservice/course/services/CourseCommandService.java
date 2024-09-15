package com.example.courseservice.course.services;


import com.example.courseservice.course.dto.CourseCreateRequest;
import com.example.courseservice.course.dto.CourseResponse;

public interface CourseCommandService {
    CourseResponse createCourse(CourseCreateRequest courseCreateRequest,String imageUrl);
    CourseResponse updateCourse(String code, CourseCreateRequest courseCreateRequest);
    void deleteCourse(String code);
}
