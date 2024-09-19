package com.example.courseservice.course.services;

import com.example.courseservice.course.dto.CourseCreateRequest;
import com.example.courseservice.course.dto.CourseResponse;
import com.example.courseservice.course.exceptions.CourseNotFoundException;
import com.example.courseservice.course.model.Course;
import com.example.courseservice.course.repo.CourseRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class CourseCommandServiceImpl implements CourseCommandService {

    private final CourseRepo courseRepo;


    public CourseCommandServiceImpl(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @Override
    public CourseResponse createCourse(CourseCreateRequest courseCreateRequest , String imageUrl) {
        Course course = convertToEntity(courseCreateRequest);
        course.setCode(generateCourseCode());
        course.setImageUrl(imageUrl);
        course.setCreatedAt(LocalDateTime.now());
        course.setUpdatedAt(LocalDateTime.now());
        Course savedCourse = courseRepo.saveAndFlush(course);
        return convertToDto(savedCourse);
    }

    @Override
    public CourseResponse updateCourse(String code, CourseCreateRequest courseCreateRequest) {
        Course course = courseRepo.findByCode(code)
                .orElseThrow(() -> new CourseNotFoundException("Course with code " + code + " not found"));
        Course updatedCourse = convertToEntity(courseCreateRequest);
        updatedCourse.setId(course.getId());
        updatedCourse.setCode(code);
        updatedCourse.setCreatedAt(course.getCreatedAt());
        updatedCourse.setUpdatedAt(LocalDateTime.now());
        Course savedCourse = courseRepo.save(updatedCourse);
        return convertToDto(savedCourse);
    }

    @Override
    public void deleteCourse(String code) {
        Course course = courseRepo.findByCode(code)
                .orElseThrow(() -> new CourseNotFoundException(code));
        courseRepo.delete(course);
    }

    private Course convertToEntity(CourseCreateRequest request) {
        Course course = new Course();
        course.setName(request.name());
        course.setDescription(request.description());
        course.setStory(request.story());
        course.setBenefits(request.benefits());
        course.setDuration(request.duration());
        course.setPrice(request.price());
        course.setAdditionalDetails(request.additionalDetails());
        course.setLearningObjectives(request.learningObjectives());
        return course;
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

    private String generateCourseCode() {
        return "COURSE-" + System.currentTimeMillis();
    }
}
