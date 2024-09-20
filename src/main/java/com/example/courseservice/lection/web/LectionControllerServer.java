package com.example.courseservice.lection.web;

import com.example.courseservice.course.dto.CourseResponse;
import com.example.courseservice.intercom.b2.CommandB2S3Adapter;
import com.example.courseservice.lection.dto.LectionCreateRequest;
import com.example.courseservice.lection.dto.LectionCreateResponse;
import com.example.courseservice.lection.service.LectionCommandServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("lection/api/v1/")
@AllArgsConstructor
public class LectionControllerServer {

    private final LectionCommandServiceImpl lectionCommandService;
    private final CommandB2S3Adapter commandB2S3Adapter;

    @PostMapping("/add")
    public ResponseEntity<LectionCreateResponse> addLection(@RequestPart("lection") LectionCreateRequest lectionCreateRequest, @RequestPart("video") MultipartFile video, @RequestPart("supportFile") MultipartFile supportFile) {
        try {
            String videoName = UUID.randomUUID() + "_" + video.getOriginalFilename();
            String videoStringUrl = commandB2S3Adapter.uploadFile(video, videoName);
            String supportName = UUID.randomUUID() + "_" + supportFile.getOriginalFilename();
            String supportStringFileUrl = commandB2S3Adapter.uploadFile(supportFile, supportName);

            LectionCreateResponse lectionCreateResponse = lectionCommandService.addLection(lectionCreateRequest, videoStringUrl, supportStringFileUrl);

            return ResponseEntity.ok(lectionCreateResponse);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
