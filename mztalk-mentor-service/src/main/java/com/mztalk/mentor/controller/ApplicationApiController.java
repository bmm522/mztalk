package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.dto.ApplicationDto;
import com.mztalk.mentor.domain.entity.PostEntity;
import com.mztalk.mentor.domain.entity.ResponseEntity;
import com.mztalk.mentor.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors/application")
public class ApplicationApiController {

    private final ApplicationService applicationService;

    @PostMapping
    public PostEntity saveApplication(@RequestBody ApplicationDto applicationDto){
        Long savedId = applicationService.save(applicationDto);
        return new PostEntity(savedId);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id")Long id){
        return applicationService.findById(id);
    }









}
