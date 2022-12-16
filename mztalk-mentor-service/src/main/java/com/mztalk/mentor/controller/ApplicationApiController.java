package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.dto.ApplicationDto;
import com.mztalk.mentor.domain.dto.ImageDto;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.service.ApplicationService;
import com.mztalk.mentor.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors")
public class ApplicationApiController {

    private final ApplicationService applicationService;
    private final ImageService imageService;

    @GetMapping("/application")
    public String saveForm(){
        return null;
    }

    @PostMapping("/application")
    public Long saveApplication(@RequestBody ConcurrentHashMap<String, String> applicationDto) {
        System.out.println(applicationDto);
        return applicationService.save(applicationDto);
    }

    public void saveImage(@ModelAttribute MultipartFile file,HttpServletRequest request){
        ImageDto imageDto = new ImageDto().saveFile(file, request);
        imageService.saveImage(imageDto);
    }

    @GetMapping("/application/{id}")
    public ApplicationDto findById(@PathVariable("id")Long id){
        return applicationService.findById(id);
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
