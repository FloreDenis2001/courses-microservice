package com.example.courseservice.enrollment.services;

import com.example.courseservice.enrollment.model.Enrollment;

import java.util.Optional;

public interface EnrollmentCommandService {

        Enrollment createEnrollment(Long courseId, Long userId);

        void deleteEnrollment(String enrollmentCode);
}
