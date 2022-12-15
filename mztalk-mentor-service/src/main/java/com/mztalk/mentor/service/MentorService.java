package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.MentorDto;
import com.mztalk.mentor.domain.entity.Result;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface MentorService {

    Long save(MentorDto mentorDto);

    MentorDto findById(Long id);

    Result findAll();

    Long delete(Long id);

    boolean isExist(Long userId);
}
