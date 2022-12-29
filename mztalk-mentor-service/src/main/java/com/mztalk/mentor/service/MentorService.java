package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.MentorDto;
import com.mztalk.mentor.domain.entity.Result;

import java.util.List;


public interface MentorService {

    Long save(MentorDto mentorDto);

    MentorDto findById(Long id);

    List<MentorDto> findAll();

    Long delete(Long id);

    boolean isExist(Long userId);
}
