package com.example.courseservice.lection.service;

import com.example.courseservice.lection.dto.LectionDTO;

public interface LectionCommandService {

    void addLection(LectionDTO lectionDTO);

    void updateLection(String codeLection, LectionDTO lectionDTO);

    void deleteLection(String codeLection);
}
