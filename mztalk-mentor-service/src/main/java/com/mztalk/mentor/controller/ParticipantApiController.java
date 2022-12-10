package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.dto.ParticipantDto;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors")
public class ParticipantApiController {
    private final ParticipantService participantService;

    @PostMapping("/participant")
    public Long save(@RequestBody ParticipantDto participantDto){
        return participantService.save(participantDto);
    }

    @GetMapping("/participant/{id}")
    public ParticipantDto findParticipant(@PathVariable Long id){
        return participantService.findById(id);
    }

    @GetMapping("/participants")
    public Result findAll(){
        return participantService.findAll();
    }

    @PatchMapping("/participant/{id}")
    public Long cancel(@PathVariable Long id){
        return participantService.cancelParticipate(id);
    }
}
