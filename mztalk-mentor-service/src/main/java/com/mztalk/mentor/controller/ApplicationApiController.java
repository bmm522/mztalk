package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.dto.ApplicationDto;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors")
public class ApplicationApiController {

    private final ApplicationService applicationService;

    @PostMapping("/application")
    public Long saveApplication(@RequestBody ConcurrentHashMap<String, String> applicationMap, HttpServletRequest request) {
        Long applicationId = applicationService.save(applicationMap);
        return applicationId;
    }

    @GetMapping("/application/{id}")
    public ApplicationDto findById(@PathVariable("id")Long id){
        return applicationService.findById(id);
    }

    @GetMapping("/application")
    public boolean isExist(@RequestParam("userId")Long userId){
        return applicationService.isExist(userId);
    }

    @GetMapping("/applications")
    public Result findAll(){
        return applicationService.findAll();
    }

    @DeleteMapping("/application/{id}")
    public Long delete(@PathVariable("id") Long id){
        return applicationService.delete(id);
    }

    @PatchMapping("/application/{id}")
    public Long updateApplication(@PathVariable("id") Long id,@RequestBody ApplicationDto applicationDto){
        return applicationService.updateApplication(id,applicationDto);
    }

}
