package com.example.courseservice.lection.service;

import com.example.courseservice.lection.dto.LectionCreateRequest;
import com.example.courseservice.lection.dto.LectionCreateResponse;
import com.example.courseservice.lection.dto.LectionDTO;
import com.example.courseservice.lection.exception.LectionNotFoundException;
import com.example.courseservice.lection.model.Lection;
import com.example.courseservice.lection.repo.LectionRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class LectionCommandServiceImpl implements LectionCommandService {

    private final LectionRepo lectionRepo;

    public LectionCommandServiceImpl(LectionRepo lectionRepo) {
        this.lectionRepo = lectionRepo;
    }

    @Override
    public LectionCreateResponse addLection(LectionCreateRequest lectionCreateRequest, String videoUrl, String supportFileUrl) {
        Optional<Lection> existingLection = lectionRepo.findByCode(lectionCreateRequest.code());
        if (existingLection.isPresent()) {
            throw new LectionNotFoundException("Lection with code " + lectionCreateRequest.code() + " already exists");
        }
        Lection addLection = Lection.builder()
                .code(lectionCreateRequest.code())
                .name(lectionCreateRequest.name())
                .description(lectionCreateRequest.description())
                .videoUrl(videoUrl)
                .supportFileUrl(supportFileUrl)
                .duration(lectionCreateRequest.duration())
                .build();

        lectionRepo.saveAndFlush(addLection);

        return LectionCreateResponse.builder()
                .code(addLection.getCode())
                .name(addLection.getName())
                .description(addLection.getDescription())
                .duration(addLection.getDuration())
                .videoUrl(addLection.getVideoUrl())
                .supportFileUrl(addLection.getSupportFileUrl())
                .build();
    }

    @Override
    public void updateLection(String codeLection, LectionDTO lectionDTO) {

    }

    @Override
    public void deleteLection(String codeLection) {

    }
}
