package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.ParticipantResDto;
import com.mztalk.mentor.domain.dto.ParticipantReqDto;

import java.util.List;

public interface ParticipantService {
    Long save(ParticipantReqDto participantReqDto);

    ParticipantResDto findById(Long id);

    List<ParticipantResDto> findAll();

    //신청 취소
    Long cancelParticipate(Long id);

    List<ParticipantResDto> findParticipantsByMentorId(Long mentorId);

    boolean existParticipant(Long boardId);
}
