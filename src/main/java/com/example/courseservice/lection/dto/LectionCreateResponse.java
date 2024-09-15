package com.example.courseservice.lection.dto;

import lombok.Builder;

@Builder
public record LectionCreateResponse(String name,String code,String videoUrl,String supportFileUrl,Integer duration,String description) {
}
