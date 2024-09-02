package com.example.courseservice.lection.service;

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
    public void addLection(LectionDTO lectionDTO) {
         Optional<Lection> lection = lectionRepo.findByCodeLection(lectionDTO.codeLection());
        if (lection.isPresent()) {
            throw new LectionNotFoundException("Lection with code " + lectionDTO.codeLection() + " already exists");
        }
//        lectionRepo.save(Lection.builder()
//                .code(lectionDTO.codeLection())
//                .description(lectionDTO.description())
//                .duration(lectionDTO.duration())
//                .name(lectionDTO.name())
//                .url(lectionDTO.url())
//                .build());

    }


    @Override
    public void updateLection(String codeLection, LectionDTO lectionDTO) {

    }

    @Override
    public void deleteLection(String codeLection) {

    }
}
