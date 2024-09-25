package com.example.courseservice.intercom.auth;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

@Service
public class AuthServiceAdapter {

    private final AuthService authService;

    public AuthServiceAdapter(AuthService authService) {
        this.authService = authService;
    }

    public String getUserRole(String token) {
        ResponseEntity<String> response = authService.getUserRole(token);
        return response.getBody();
    }

    public Long findId(String token) {
        ResponseEntity<Long> response = authService.findId(token);
        return response.getBody();
    }
}
