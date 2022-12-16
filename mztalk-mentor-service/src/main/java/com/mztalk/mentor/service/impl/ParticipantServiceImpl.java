package com.mztalk.mentor.service.impl;

import com.mztalk.mentor.domain.dto.ParticipantDto;
import com.mztalk.mentor.domain.entity.Board;
import com.mztalk.mentor.domain.entity.Mentee;
import com.mztalk.mentor.domain.entity.Participant;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.exception.ParticipantNotFoundException;
import com.mztalk.mentor.repository.BoardRepository;
import com.mztalk.mentor.repository.MenteeRepository;
import com.mztalk.mentor.repository.MentorRepository;
import com.mztalk.mentor.repository.ParticipantRepository;
import com.mztalk.mentor.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;
    private final MenteeRepository menteeRepository;
    private final BoardRepository boardRepository;

    @Override
    @Transactional
    public Long save(ConcurrentHashMap<String,String> participantDto) {
        Long boardId = Long.parseLong(participantDto.get("boardId"));
        Long userId = Long.parseLong(participantDto.get("userId"));
        Board board = boardRepository.findBoardByBoardId(boardId);
        Mentee mentee = menteeRepository.findMenteeByUserId(userId);
        Participant participant = Participant.createParticipant(participantDto, mentee, board);
        return participantRepository.save(participant).getId();
    }

    @Override
    public ParticipantDto findById(Long id) {
        Participant participant = participantRepository.findById(id).orElseThrow(() -> new ParticipantNotFoundException("해당하는 참가자는 존재하지 않습니다."));
        ParticipantDto participantDto = new ParticipantDto(participant);
        return participantDto;
    }

    @Override
    public Result findParticipantsByMentorId(Long boardId) {
        List<Participant> participants = participantRepository.findParticipantsByMentorId(boardId);
        List<ParticipantDto> collect = participants.stream().map(ParticipantDto::new).collect(Collectors.toList());
        return new Result(collect);
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
