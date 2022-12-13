package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.dto.MenteeDto;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.service.MenteeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors")
public class MenteeApiController {
    private final MenteeService menteeService;

    @PostMapping("/mentee")
    public Long saveClient(@RequestBody MenteeDto menteeDto){
        return menteeService.saveClient(menteeDto);
    }

    @GetMapping("/mentee/{id}")
    public MenteeDto findClient(@PathVariable("id") Long id){
        return menteeService.findClient(id);
    }

    @GetMapping("/mentees")
    public Result findAll(){
        return menteeService.findAll();
    }


}
