package com.example.courseservice.lection.web;

import com.example.courseservice.lection.dto.LectionCreateRequest;
import com.example.courseservice.lection.dto.LectionCreateResponse;
import com.example.courseservice.lection.service.LectionCommandServiceImpl;
import com.example.courseservice.intercom.b2.CommandB2S3Adapter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LectionCreateResponse> addLection(
            @RequestPart("lection") String lectionJson,
            @RequestPart("video") MultipartFile video,
            @RequestPart("supportFile") MultipartFile supportFile,
            @RequestPart("courseCode") String courseCode
    ) {

        ObjectMapper objectMapper = new ObjectMapper();
        LectionCreateRequest lectionCreateRequest;

        try {
            lectionCreateRequest = objectMapper.readValue(lectionJson, LectionCreateRequest.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try {
            String videoName = UUID.randomUUID() + "_" + video.getOriginalFilename();
            String videoStringUrl = commandB2S3Adapter.uploadFile(video, videoName);
            String supportName = UUID.randomUUID() + "_" + supportFile.getOriginalFilename();
            String supportStringFileUrl = commandB2S3Adapter.uploadFile(supportFile, supportName);

            LectionCreateResponse lectionCreateResponse = lectionCommandService.addLection(lectionCreateRequest, videoStringUrl, supportStringFileUrl , courseCode);

            return ResponseEntity.ok(lectionCreateResponse);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
