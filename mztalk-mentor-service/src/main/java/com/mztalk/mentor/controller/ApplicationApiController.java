package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.dto.ApplicationDto;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors")
public class ApplicationApiController {

    private final ApplicationService applicationService;

    @PostMapping("/application")
    public ResponseEntity<?> saveApplication(@RequestBody ConcurrentHashMap<String, String> applicationMap, HttpServletRequest request) {
        Long applicationId = applicationService.save(applicationMap);
        return new ResponseEntity<>(new Result<>("지원서 작성 성공",applicationId), HttpStatus.CREATED);
    }

    @GetMapping("/application/{id}")
    public ResponseEntity<?> findById(@PathVariable("id")Long id){
        ApplicationDto applicationDto = applicationService.findById(id);
        return new ResponseEntity<>(new Result<>("해당 번호에 대한 지원서",applicationDto),HttpStatus.OK);
    }

    @GetMapping("/application")
    public boolean isExist(@RequestParam("userId")Long userId){
        return applicationService.isExist(userId);
    }

    @GetMapping("/applications")
    public ResponseEntity<?> findAll(){
        List<ApplicationDto> applications = applicationService.findAll();
        return new ResponseEntity<>(new Result<>("모든 지원서 목록", applications), HttpStatus.OK);
    }

    @DeleteMapping("/application/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Long deleteId = applicationService.delete(id);
        return new ResponseEntity<>(new Result<>("해당 지원서가 삭제되었습니다.", deleteId), HttpStatus.OK);
    }

    @PatchMapping("/application/{id}")
    public Long updateApplication(@PathVariable("id") Long id,@RequestBody ApplicationDto applicationDto){
        return applicationService.updateApplication(id,applicationDto);
    }

}
