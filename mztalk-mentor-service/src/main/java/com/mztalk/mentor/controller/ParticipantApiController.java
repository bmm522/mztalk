package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.dto.ParticipantDto;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors")
public class ParticipantApiController {

    private final ParticipantService participantService;

    @PostMapping("/participant")
    public ResponseEntity<?> save(@RequestBody ConcurrentHashMap<String,String> participantMap){
        Long savedId = participantService.save(participantMap);
        return new ResponseEntity<>(new Result<>("신청이 완료되었습니다.", savedId), HttpStatus.CREATED);
    }

    @GetMapping("/participant/{id}")
    public ResponseEntity<?> findParticipant(@PathVariable("id") Long id){
        ParticipantDto participant = participantService.findById(id);
        return new ResponseEntity<>(new Result<>("해당 번호에 대한 참가자 정보", participant), HttpStatus.OK);
    }

    @GetMapping("/participant")
    public ResponseEntity<?> findParticipantsByMentorId(@RequestParam("mentorId")Long mentorId){
        List<ParticipantDto> participants = participantService.findParticipantsByMentorId(mentorId);
        return new ResponseEntity<>(new Result<>("해당 멘토에게 신청한 유저 목록", participants), HttpStatus.OK);
    }

    @GetMapping("/participants")
    public ResponseEntity<?> findAll(){
        List<ParticipantDto> participants = participantService.findAll();
        return new ResponseEntity<>(new Result<>("모든 참가자 목록", participants), HttpStatus.OK);
    }

    @PatchMapping("/participant/{id}")
    public Long cancel(@PathVariable("id") Long id){
        return participantService.cancelParticipate(id);
    }
}
