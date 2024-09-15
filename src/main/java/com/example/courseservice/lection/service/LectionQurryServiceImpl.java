package com.example.courseservice.lection.service;

import com.example.courseservice.lection.exception.LectionNotFoundException;
import com.example.courseservice.lection.model.Lection;
import com.example.courseservice.lection.repo.LectionRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LectionQurryServiceImpl implements LectionQuerryService{

    private final LectionRepo lectionRepo;

    public LectionQurryServiceImpl(LectionRepo lectionRepo) {
        this.lectionRepo = lectionRepo;
    }

    @Override
    public Optional<Lection> findByCodeLection(String codeLection) {
        Optional<Lection> lection = lectionRepo.findByCode(codeLection);
        if (lection.isEmpty()) {
            throw new LectionNotFoundException("Lection with code " + codeLection + " not found");
        }
        return lection;
    }
}
