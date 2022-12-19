package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.dto.ScoreDto;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.domain.entity.Score;
import com.mztalk.mentor.repository.ScoreRepository;
import com.mztalk.mentor.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors")
public class ScoreApiController {
    private final ScoreService scoreService;

    @PostMapping("/score")
    public Long saveScore(@RequestBody ConcurrentHashMap<String,String> scoreMap){
        return scoreService.save(scoreMap);
    }

    //boardId로 평균구해오기 : board>mentor>score
    @GetMapping("/score/{id}")
    public Double findById(@PathVariable("id")Long id){
        return scoreService.findById(id);
    }

    //nickname으로 모든 리뷰 가져오기
    @GetMapping("/score")
    public Result findByNickname(@RequestParam("nickname")String nickname){
        return scoreService.findScoresByNickname(nickname);
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
