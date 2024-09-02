package com.example.courseservice.lection.dto;

public record LectionCreateRequest(String name,String videoUrl,String supportFileUrl,Integer duration,String description) {
}
