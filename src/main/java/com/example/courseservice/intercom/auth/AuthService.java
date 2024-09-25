package com.example.courseservice.intercom.auth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

//@FeignClient(name = "auth-service", url = "http://localhost:8080/server/api/v1/")
@FeignClient(name = "auth-service",url = "http://${param.courses-auth-service}:8083/server/api/v1/")
public interface AuthService {
    @GetMapping("/getUserRole")
    ResponseEntity<String> getUserRole(@RequestHeader("Authorization") String token);

    @GetMapping("/findId")
    ResponseEntity<Long> findId(@RequestHeader("Authorization") String token);
}

