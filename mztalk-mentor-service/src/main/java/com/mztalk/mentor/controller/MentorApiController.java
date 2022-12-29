package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.dto.MentorDto;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors")
public class MentorApiController {

    private final MentorService mentorService;

    @PostMapping("/member") //멘토 신청 후 허락 버튼
    public ResponseEntity<?> save(@RequestBody MentorDto mentorDto){
        Long savedId = mentorService.save(mentorDto);
        return new ResponseEntity<>(new Result<>("해당 멘토가 등록되었습니다.", savedId), HttpStatus.CREATED);
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<?> findById(@PathVariable("id")Long id){
        MentorDto mentor = mentorService.findById(id);
        return new ResponseEntity<>(new Result<>("해당 번호에 대한 멘토 정보", mentor), HttpStatus.OK);
    }

    @GetMapping("/member")
    public boolean isExist(@RequestParam("userId")Long userId){
        return mentorService.isExist(userId);
    }

    @GetMapping("/members")
    public ResponseEntity<?> findAll(){
        List<MentorDto> mentors = mentorService.findAll();
        return new ResponseEntity<>(new Result<>("멘토로 등록된 모든 사용자 목록", mentors), HttpStatus.OK);
    }

    @PatchMapping("/member/{id}")
    public Long delete(@PathVariable("id")Long id){
        return mentorService.delete(id);
    }

}
