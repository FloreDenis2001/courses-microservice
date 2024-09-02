package com.example.courseservice.course.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.checkerframework.common.aliasing.qual.Unique;

@Table(name = "courses")
@Entity(name = "Course")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Data
public class Course {

    @Id
    @SequenceGenerator(name = "course_sequence", sequenceName = "course_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence")

    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name="code", nullable = false)
    @Unique
    private String code;


    @Column(name = "name", nullable = false)
    private String name;


    @Column(name = "description", nullable = false)
    private String description;


    @Column(name = "price", nullable = false)
    private Double price;


    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "thumbnail", nullable = false)
    private String thumbnail;

    @Column(name = "created_at", nullable = false)
    private String createdAt;

    @Column(name = "updated_at", nullable = false)
    private String updatedAt;

}
