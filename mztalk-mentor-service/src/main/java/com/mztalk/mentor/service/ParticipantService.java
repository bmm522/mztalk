package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.ParticipantDto;
import com.mztalk.mentor.domain.entity.Result;

import java.util.concurrent.ConcurrentHashMap;

public interface ParticipantService {
    Long save(ConcurrentHashMap<String,String> participantDto);

    ParticipantDto findById(Long id);

    Result findAll();

    //신청 취소
    Long cancelParticipate(Long id);

}
