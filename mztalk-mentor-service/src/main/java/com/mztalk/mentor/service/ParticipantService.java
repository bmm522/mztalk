package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.ParticipantDto;
import com.mztalk.mentor.domain.dto.ParticipantReqDto;
import com.mztalk.mentor.domain.entity.Result;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface ParticipantService {
    Long save(ParticipantReqDto participantReqDto);

    ParticipantDto findById(Long id);

    List<ParticipantDto> findAll();

    //신청 취소
    Long cancelParticipate(Long id);

    List<ParticipantDto> findParticipantsByMentorId(Long mentorId);

}
