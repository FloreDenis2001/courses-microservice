package com.example.courseservice.enrollment.repo;

import com.example.courseservice.enrollment.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepo extends JpaRepository<Enrollment, Long> {

    Optional<List<Enrollment>> findAllByUserId(Long userId);
    Optional<Enrollment> findByEnrollmentCode(String enrollmentCode);

}
