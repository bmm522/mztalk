package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.dto.MenteeDto;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.service.MenteeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors")
public class MenteeApiController {
    private final MenteeService menteeService;

    @PostMapping
    public ResponseEntity<?> saveClient(@RequestBody MenteeDto menteeDto){
        Long id = menteeService.saveClient(menteeDto);
        return new ResponseEntity<>(new Result<>("사용자가 서비스 등록되었습니다.",id), HttpStatus.CREATED);
    }

    @GetMapping("/mentee/{id}")
    public ResponseEntity<?> findClient(@PathVariable("id") Long id){
        MenteeDto mentee = menteeService.findClient(id);
        return new ResponseEntity<>(new Result<>("멘티에 관한 정보",mentee),HttpStatus.OK);
    }

    @GetMapping("/mentees")
    public ResponseEntity<?> findAll(){
        List<MenteeDto> mentees = menteeService.findAll();
        return new ResponseEntity<>(new Result<>("멘티 전체 목록", mentees), HttpStatus.OK);
    }



}
