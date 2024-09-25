package com.example.courseservice.enrollment.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "enrollments")
@Entity(name = "Enrollment")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Data
public class Enrollment {

    @Id
    @SequenceGenerator(name = "enrollment_sequence", sequenceName = "enrollment_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enrollment_sequence")

    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "enrollment_code", nullable = false, unique = true, updatable = false)
    private String enrollmentCode;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "enrolled_at", nullable = false)
    private LocalDateTime enrolledAt;

    @PrePersist
    protected void onCreate() {
        this.enrollmentCode = UUID.randomUUID().toString();
    }
}
