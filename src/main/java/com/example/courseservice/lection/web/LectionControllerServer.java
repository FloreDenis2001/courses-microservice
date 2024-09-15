package com.example.courseservice.lection.web;

import com.example.courseservice.lection.dto.LectionCreateRequest;
import com.example.courseservice.lection.dto.LectionCreateResponse;
import com.example.courseservice.lection.service.LectionCommandServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/server/api/v1/lection")
@AllArgsConstructor
public class LectionControllerServer {

    private final LectionCommandServiceImpl lectionCommandService;

//    @PostMapping("/add")
//    public ResponseEntity<LectionCreateResponse> addLection(@RequestBody LectionCreateRequest lectionCreateRequest,@RequestParam("file") MultipartFile videoUrl,@RequestParam("file") MultipartFile  supportFileUrl) {
//
//        LectionCreateResponse lectionCreateResponse = lectionCommandService.addLection(lectionCreateRequest,videoStringUrl,supportStringFileUrl);
//        return ResponseEntity.ok(lectionCreateResponse);
//    }


}
