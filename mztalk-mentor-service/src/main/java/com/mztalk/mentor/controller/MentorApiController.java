package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.dto.MentorDto;
import com.mztalk.mentor.domain.entity.Mentor;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors")
public class MentorApiController {

    private final MentorService mentorService;

    @PostMapping("/member") //멘토 신청 후 허락 버튼
    public Long save(@RequestBody MentorDto mentorDto){
        return mentorService.save(mentorDto);
    }

    @GetMapping("/member/{id}")
    public MentorDto findById(@PathVariable("id")Long id){
        return mentorService.findById(id);
    }

    @GetMapping("/members")
    public Result findAll(){
        return mentorService.findAll();
    }

    @PatchMapping("/member/{id}")
    public Long delete(@PathVariable("id")Long id){
        return mentorService.delete(id);
    }

}
