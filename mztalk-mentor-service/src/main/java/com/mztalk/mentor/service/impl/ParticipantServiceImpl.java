package com.mztalk.mentor.service.impl;

import com.mztalk.mentor.domain.dto.ParticipantDto;
import com.mztalk.mentor.domain.entity.Participant;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.exception.ParticipantNotFoundException;
import com.mztalk.mentor.repository.ParticipantRepository;
import com.mztalk.mentor.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;

    @Override
    @Transactional
    public Long save(ParticipantDto participantDto) {
        Participant savedParticipant = participantRepository.save(participantDto.toEntity());
        return savedParticipant.getId();
    }

    @Override
    public ParticipantDto findById(Long id) {
        Participant participant = participantRepository.findById(id).orElseThrow(() -> new ParticipantNotFoundException("해당하는 참가자는 존재하지 않습니다."));
        ParticipantDto participantDto = new ParticipantDto(participant);
        return participantDto;
    }

    @Override
    public Result findAll() {
        List<Participant> participantList = participantRepository.findAll();
        List<ParticipantDto> collect = participantList.stream().map(ParticipantDto::new).collect(Collectors.toList());
        return new Result(collect);
    }

    @Override
    @Transactional
    public Long cancelParticipate(Long id) {
        Participant participant = participantRepository.findById(id).orElseThrow(() -> new ParticipantNotFoundException("해당하는 참가자는 존재하지 않습니다."));
        participant.cancel();
        return null;
    }
}
