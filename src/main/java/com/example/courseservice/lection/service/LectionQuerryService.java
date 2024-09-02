package com.example.courseservice.lection.service;

import com.example.courseservice.lection.model.Lection;

import java.util.Optional;

public interface LectionQuerryService {
    Optional<Lection> findByCodeLection(String codeLection);
}
