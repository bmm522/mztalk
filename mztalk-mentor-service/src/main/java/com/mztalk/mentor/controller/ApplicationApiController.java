package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.dto.ApplicationDto;
import com.mztalk.mentor.domain.entity.ResponseEntity;
import com.mztalk.mentor.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors/applications")
public class ApplicationApiController {

    private final ApplicationService applicationService;

    @PostMapping
    public Long saveApplication(@RequestBody ApplicationDto applicationDto){
        return applicationService.save(applicationDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id")Long id){
        return applicationService.findById(id);
    }

    @GetMapping
    public ResponseEntity findAll(){
        return applicationService.findAll();
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable("id") Long id){
        return applicationService.delete(id);
    }

    @PatchMapping("/{id}")
    public Long updateApplication(@PathVariable("id") Long id,@RequestBody ApplicationDto applicationDto){
        return applicationService.updateApplication(id,applicationDto);
    }











}
