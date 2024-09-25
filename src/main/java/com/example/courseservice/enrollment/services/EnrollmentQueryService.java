package com.example.courseservice.enrollment.services;

import com.example.courseservice.enrollment.model.Enrollment;

import java.util.Optional;

public interface EnrollmentQueryService {

    Optional<Enrollment> findEnrollmentByCode(String code);

}
