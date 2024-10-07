package com.example.courseservice.course.web;

import com.example.courseservice.course.dto.CourseCreateRequest;
import com.example.courseservice.course.dto.CourseResponse;
import com.example.courseservice.course.services.CourseCommandServiceImpl;
import com.example.courseservice.course.services.CourseQuerryServiceImpl;
import com.example.courseservice.intercom.auth.AuthServiceAdapter;
import com.example.courseservice.intercom.b2.CommandB2S3Adapter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


@RestController
@CrossOrigin
@RequestMapping("/courses/api/v1/")
@Slf4j
public class CourseControllerServer {

    private final CourseQuerryServiceImpl courseQuerryService;

    private final CourseCommandServiceImpl courseCommandService;

    private final CommandB2S3Adapter commandB2S3Adapter;

    private final AuthServiceAdapter authServiceAdapter;

    public CourseControllerServer(CourseQuerryServiceImpl courseQuerryService, CourseCommandServiceImpl courseCommandService, CommandB2S3Adapter commandB2S3Adapter, AuthServiceAdapter authServiceAdapter) {
        this.courseQuerryService = courseQuerryService;
        this.courseCommandService = courseCommandService;
        this.commandB2S3Adapter = commandB2S3Adapter;
        this.authServiceAdapter = authServiceAdapter;
    }


    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CourseResponse> createCourse(
            @RequestPart("file") MultipartFile file,
            @RequestPart("course") String courseJson ,
            @RequestHeader("Authorization") String token
             ) {
        log.info("Se incercă adăugarea unui curs");
        String userRole = authServiceAdapter.getUserRole(token);

        if (!"ADMIN".equals(userRole)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        CourseCreateRequest courseCreateRequest;
        try {
            courseCreateRequest = objectMapper.readValue(courseJson, CourseCreateRequest.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }


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



    @GetMapping("/code")
    public ResponseEntity<CourseResponse> getCourseByCode(@RequestParam String code) {
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



}
