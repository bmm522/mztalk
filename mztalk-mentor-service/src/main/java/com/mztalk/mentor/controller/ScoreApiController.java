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
    public Long saveScore(@RequestBody ConcurrentHashMap<String,String> scoreMap){
        return scoreService.save(scoreMap);
    }

    @GetMapping("/score/{id}")
    public ScoreDto findById(@PathVariable("id")Long id){
        return scoreService.findById(id);
    }

    //nickname으로 모든 리뷰 가져오기
    @GetMapping("/score")
    public Result findByNickname(@RequestParam("nickname")String nickname){
        return scoreService.findScoresByNickname(nickname);
    }

    //mentee의 userId로 작성한 리뷰 가져오기
    @GetMapping("/score/mentee/{userId}")
    public Result findByUserId(@PathVariable("userId")Long userId){
        return scoreService.findByUserId(userId);
    }

    //mentor의 userId로 작성된 리뷰 가져오기
    @GetMapping("/score/mentor/{mentorId}")
    public Result findByMentorId(@PathVariable("mentorId")Long mentorId){
        return scoreService.findByMentorId(mentorId);
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

    // 멘티가 해당 글에 대해 리뷰를 작성했는지 확인한다.
    @GetMapping("/score/mentee")
    public boolean isExist(@RequestParam("userId")Long userId, @RequestParam("boardId")Long boardId){
        return scoreService.isExist(userId, boardId);
    }
}
