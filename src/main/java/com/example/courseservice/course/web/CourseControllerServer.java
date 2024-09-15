package com.example.courseservice.course.web;

import com.example.courseservice.course.dto.CourseCreateRequest;
import com.example.courseservice.course.dto.CourseResponse;
import com.example.courseservice.course.services.CourseCommandServiceImpl;
import com.example.courseservice.course.services.CourseQuerryServiceImpl;
import com.example.courseservice.intercom.b2.CommandB2S3Adapter;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


@RestController
@CrossOrigin
@RequestMapping("/courses/api/v1/")
public class CourseControllerServer {

    private final CourseQuerryServiceImpl courseQuerryService;

    private final CourseCommandServiceImpl courseCommandService;

    private final CommandB2S3Adapter commandB2S3Adapter;

    public CourseControllerServer(CourseQuerryServiceImpl courseQuerryService, CourseCommandServiceImpl courseCommandService, CommandB2S3Adapter commandB2S3Adapter) {
        this.courseQuerryService = courseQuerryService;
        this.courseCommandService = courseCommandService;
        this.commandB2S3Adapter = commandB2S3Adapter;
    }


    @PostMapping("/addFile")
    public String addFile(@RequestPart("file") MultipartFile file) {

        return "File uploaded successfully";

    }

    @PostMapping("/addCourse")
    public String addCourse(@RequestPart("course") CourseCreateRequest courseCreateRequest) {
        return "Course added successfully";
    }


    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CourseResponse> createCourse(@RequestPart("file") MultipartFile file,
                                                       @RequestPart("course")  CourseCreateRequest courseCreateRequest
    ) {

        System.out.println("CourseCreateRequest: " + courseCreateRequest);

        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            String imageUrl = commandB2S3Adapter.uploadFile(file, fileName);
            CourseResponse createdCourse = courseCommandService.createCourse(courseCreateRequest, imageUrl);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping("/{code}")
    public ResponseEntity<CourseResponse> getCourseByCode(@PathVariable String code) {
        return ResponseEntity.ok(courseQuerryService.findByCode(code));
    }


    @PutMapping("/{code}")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable String code, @RequestBody CourseCreateRequest courseCreateRequest) {
        CourseResponse updatedCourse = courseCommandService.updateCourse(code, courseCreateRequest);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String code) {
        courseCommandService.deleteCourse(code);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/test")
    public String test() {
        return "Test";
    }
}
