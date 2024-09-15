package com.example.courseservice.lection.service;

import com.example.courseservice.lection.dto.LectionCreateRequest;
import com.example.courseservice.lection.dto.LectionCreateResponse;
import com.example.courseservice.lection.dto.LectionDTO;

public interface LectionCommandService {



    LectionCreateResponse addLection(LectionCreateRequest lectionCreateRequest, String videoUrl, String supportFileUrl);

    void updateLection(String codeLection, LectionDTO lectionDTO);

    void deleteLection(String codeLection);
}
