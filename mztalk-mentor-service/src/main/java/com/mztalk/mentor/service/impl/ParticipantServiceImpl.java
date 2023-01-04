package com.mztalk.mentor.service.impl;

import com.mztalk.mentor.domain.dto.BoardTransferDto;
import com.mztalk.mentor.domain.dto.MenteeTransferDto;
import com.mztalk.mentor.domain.dto.ParticipantResDto;
import com.mztalk.mentor.domain.dto.ParticipantReqDto;
import com.mztalk.mentor.domain.entity.Board;
import com.mztalk.mentor.domain.entity.Mentee;
import com.mztalk.mentor.domain.entity.Participant;
import com.mztalk.mentor.exception.ParticipantNotFoundException;
import com.mztalk.mentor.repository.BoardRepository;
import com.mztalk.mentor.repository.MenteeRepository;
import com.mztalk.mentor.repository.ParticipantRepository;
import com.mztalk.mentor.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public Long save(ParticipantReqDto participantReqDto) {
        Board board = boardRepository.findBoardByBoardId(participantReqDto.getBoardId());
        Mentee mentee = menteeRepository.findMenteeByUserId(participantReqDto.getUserId());

        Participant participant = participantReqDto.toEntity();
        participant.addBoard(board);
        participant.addMentee(mentee);

        Participant savedParticipant = participantRepository.save(participant);
        return savedParticipant.getId();
    }

    @Override
    public ParticipantResDto findById(Long id) {
        Participant participant = participantRepository.findById(id).orElseThrow(() -> new ParticipantNotFoundException("해당하는 참가자는 존재하지 않습니다."));
        ParticipantResDto participantResDto = new ParticipantResDto(participant,new MenteeTransferDto(participant.getMentee()),new BoardTransferDto(participant.getBoard()));
        return participantResDto;
    }

    @Override
    public List<ParticipantResDto> findParticipantsByMentorId(Long boardId) {
        List<Participant> participants = participantRepository.findParticipantsByMentorId(boardId);
        List<ParticipantResDto> collect = participants.stream()
                .map(p->new ParticipantResDto(p,new MenteeTransferDto(p.getMentee()),new BoardTransferDto(p.getBoard())))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public boolean existParticipant(Long boardId) {
        Participant participant = participantRepository.existParticipant(boardId);
        boolean existParticipant = participant == null ? false : true;
        return existParticipant;
    }

    @Override
    public List<ParticipantResDto> findAll() {
        List<Participant> participantList = participantRepository.findAll();
        List<ParticipantResDto> collect = participantList.stream()
                .map(p->new ParticipantResDto(p,new MenteeTransferDto(p.getMentee()),new BoardTransferDto(p.getBoard())))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    @Transactional
    public Long cancelParticipate(Long id) {
        Participant participant = participantRepository.findById(id).orElseThrow(() -> new ParticipantNotFoundException("해당하는 참가자는 존재하지 않습니다."));
        participant.cancel();
        return null;
    }
}
