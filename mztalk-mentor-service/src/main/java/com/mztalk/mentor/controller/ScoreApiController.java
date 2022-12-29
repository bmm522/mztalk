package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.dto.ScoreDto;
import com.mztalk.mentor.domain.dto.ScoreMenteeDto;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors")
public class ScoreApiController {
    private final ScoreService scoreService;

    @PostMapping("/score")
    public ResponseEntity<?> saveScore(@RequestBody ConcurrentHashMap<String,String> scoreMap){
        Long savedId = scoreService.save(scoreMap);
        return new ResponseEntity<>(new Result<>("해당 리뷰가 정상적으로 저장되었습니다.", savedId), HttpStatus.CREATED);
    }

    @GetMapping("/score/{id}")
    public ResponseEntity<?> findById(@PathVariable("id")Long id){
        ScoreDto score = scoreService.findById(id);
        return new ResponseEntity<>(new Result<>("해당 번호에 대한 리뷰 정보 입니다.", score), HttpStatus.OK);
    }

    //nickname으로 모든 리뷰 가져오기
    @GetMapping("/score")
    public ResponseEntity<?> findByNickname(@RequestParam("nickname")String nickname){
        List<ScoreDto> scores = scoreService.findScoresByNickname(nickname);
        return new ResponseEntity<>(new Result<>("멘토의 닉네임으로 검색한 리뷰 목록입니다.", scores), HttpStatus.OK);
    }

    //mentee의 userId로 작성한 리뷰 가져오기
    @GetMapping("/score/mentee/{userId}")
    public ResponseEntity<?> findByUserId(@PathVariable("userId")Long userId){
        List<ScoreMenteeDto> scores = scoreService.findByUserId(userId);
        return new ResponseEntity<>(new Result<>("해당 멘티가 작성한 모든 리뷰 목록 입니다.", scores), HttpStatus.OK);
    }

    //mentor의 userId로 작성된 리뷰 가져오기
    @GetMapping("/score/mentor/{mentorId}")
    public ResponseEntity<?> findByMentorId(@PathVariable("mentorId")Long mentorId){
        List<ScoreDto> scores = scoreService.findByMentorId(mentorId);
        return new ResponseEntity<>(new Result<>("멘토의 고유 식별자로 멘토에게 작성된 모든 리뷰 목록", scores), HttpStatus.OK);
    }

    @GetMapping("/scores")
    public ResponseEntity<?> findScores(){
        List<ScoreDto> scores = scoreService.findAll();
        return new ResponseEntity<>(new Result<>("멘토 서비스내 작성된 모든 리뷰 목록입니다.", scores), HttpStatus.OK);
    }

    // 멘티가 작성한 리뷰 삭제하기
    @DeleteMapping("/score/{id}")
    public ResponseEntity<?> deleteScore(@PathVariable("id")Long id){
        Long deleteId = scoreService.deleteScore(id);
        return new ResponseEntity<>(new Result<>("해당 번호의 리뷰가 삭제되었습니다.", deleteId), HttpStatus.OK);
    }

    @PatchMapping("/score/{id}")
    public ResponseEntity<?> updateScore(@PathVariable("id")Long id, @RequestBody ScoreDto scoreDto){
        Long modifiedId = scoreService.updateScore(id, scoreDto);
        return new ResponseEntity<>(new Result<>("해당 번호의 리뷰가 수정되었습니다.", modifiedId), HttpStatus.OK);
    }

    // 멘티가 해당 글에 대해 리뷰를 작성했는지 확인한다.
    @GetMapping("/score/mentee")
    public boolean isExist(@RequestParam("userId")Long userId, @RequestParam("boardId")Long boardId){
        return scoreService.isExist(userId, boardId);
    }
}
