package com.example.courseservice.lection.service;

import com.example.courseservice.course.model.Course;
import com.example.courseservice.course.repo.CourseRepo;
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

    private final CourseRepo courseRepo;

    public LectionCommandServiceImpl(LectionRepo lectionRepo, CourseRepo courseRepo) {
        this.lectionRepo = lectionRepo;
        this.courseRepo = courseRepo;
    }

    @Override
    public LectionCreateResponse addLection(LectionCreateRequest lectionCreateRequest, String videoUrl, String supportFileUrl,String courseCode) {
        Optional<Course> existingCourse = courseRepo.findByCode(courseCode);
       
        Lection addLection = Lection.builder()
                .name(lectionCreateRequest.name())
                .description(lectionCreateRequest.description())
                .videoUrl(videoUrl)
                .supportFileUrl(supportFileUrl)
                .duration(lectionCreateRequest.duration())
                .course(existingCourse.get())
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
