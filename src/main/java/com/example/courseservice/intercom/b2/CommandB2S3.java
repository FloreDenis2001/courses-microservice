package com.example.courseservice.intercom.b2;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "auth-service", url = "http://localhost:8080/server/api/v1/cloud/b2/")
public interface CommandB2S3 {
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                      @RequestParam("fileName") String fileName);
}

