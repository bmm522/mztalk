package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.dto.ScoreDto;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors")
public class ScoreApiController {
    private final ScoreService scoreService;

    @PostMapping("/score")
    public Long saveScore(@RequestBody ConcurrentHashMap<String,String> scoreDto){
        return scoreService.save(scoreDto);
    }

    @GetMapping("/score/{id}")
    public ScoreDto findById(@PathVariable("id")Long id){
        return scoreService.findById(id);
    }

    @GetMapping("/scores")
    public Result findScores(){
        return scoreService.findAll();
    }

    @DeleteMapping("/score/{id}")
    public Long deleteScore(@PathVariable("id")Long id){
        return scoreService.deleteScore(id);
    }

    @PatchMapping("/score/{id}")
    public Long updateScore(@PathVariable("id")Long id, @RequestBody ScoreDto scoreDto){
        return scoreService.updateScore(id, scoreDto);
    }
}
