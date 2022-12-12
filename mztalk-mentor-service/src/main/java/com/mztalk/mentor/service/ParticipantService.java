package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.ParticipantDto;
import com.mztalk.mentor.domain.entity.Result;

public interface ParticipantService {
    Long save(ParticipantDto participantDto);

    ParticipantDto findById(Long id);

    Result findAll();

    //신청 취소
    Long cancelParticipate(Long id);

}
