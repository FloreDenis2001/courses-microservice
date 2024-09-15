package com.example.courseservice.course.services;

import com.example.courseservice.course.dto.CourseResponse;
import com.example.courseservice.course.exceptions.CourseNotFoundException;
import com.example.courseservice.course.model.Course;
import com.example.courseservice.course.repo.CourseRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseQuerryServiceImpl implements CourseQuerryService {

    private final CourseRepo courseRepo;

    public CourseQuerryServiceImpl(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @Override
    public CourseResponse findByCode(String code) {
        Optional<Course> course = courseRepo.findByCode(code);
        if (course.isEmpty()) {
            throw new CourseNotFoundException("Course with code " + code + " not found");
        }
        return convertToDto(course.get());
    }


    private CourseResponse convertToDto(Course course) {
        return new CourseResponse(
                course.getId(),
                course.getCode(),
                course.getImageUrl(),
                course.getName(),
                course.getDescription(),
                course.getStory(),
                course.getBenefits(),
                course.getPrice(),
                course.getDuration(),
                course.getAdditionalDetails(),
                course.getLearningObjectives(),
                course.getCreatedAt(),
                course.getUpdatedAt()
        );
    }
}
