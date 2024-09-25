package com.example.courseservice.enrollment.services;

import com.example.courseservice.enrollment.model.Enrollment;
import com.example.courseservice.enrollment.repo.EnrollmentRepo;

import java.time.LocalDateTime;
import java.util.Optional;

public class EnrollmentCommandServiceImpl implements EnrollmentCommandService {

    private final EnrollmentRepo enrollmentRepo;

    public EnrollmentCommandServiceImpl(EnrollmentRepo enrollmentRepo) {
        this.enrollmentRepo = enrollmentRepo;
    }

    @Override
    public Enrollment createEnrollment(Long courseId, Long userId) {
        Enrollment enrollment = new Enrollment();
        enrollment.setCourseId(courseId);
        enrollment.setUserId(userId);
        enrollment.setEnrolledAt(LocalDateTime.now());
        return enrollment;

    }

    @Override
    public void deleteEnrollment(String enrollmentCode) {
        Optional<Enrollment> enrollment = enrollmentRepo.findByEnrollmentCode(enrollmentCode);
        if(enrollment.isEmpty()){
            throw new IllegalArgumentException("Enrollment not found");
        } else {
            enrollmentRepo.delete(enrollment.get());
        }
    }
}
