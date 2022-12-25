package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.dto.ParticipantDto;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors")
public class ParticipantApiController {
    private final ParticipantService participantService;

    @PostMapping("/participant")
    public Long save(@RequestBody ConcurrentHashMap<String,String> participantMap){
        return participantService.save(participantMap);
    }

    @GetMapping("/participant/{id}")
    public ParticipantDto findParticipant(@PathVariable("id") Long id){
        return participantService.findById(id);
    }

    @GetMapping("/participant")
    public Result findParticipantsByMentorId(@RequestParam("mentorId")Long mentorId){
        return participantService.findParticipantsByMentorId(mentorId);
    }

    @GetMapping("/participants")
    public Result findAll(){
        return participantService.findAll();
    }

    @PatchMapping("/participant/{id}")
    public Long cancel(@PathVariable("id") Long id){
        return participantService.cancelParticipate(id);
    }
}
