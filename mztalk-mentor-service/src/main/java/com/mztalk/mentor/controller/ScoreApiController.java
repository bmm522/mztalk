package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.dto.ScoreDto;
import com.mztalk.mentor.domain.dto.ScoreMenteeDto;
import com.mztalk.mentor.domain.dto.ScoreModifyDto;
import com.mztalk.mentor.domain.dto.ScoreReqDto;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.service.ScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 201, message = "CREATED"),
        @ApiResponse(code = 400, message = "BAD REQUEST"),
        @ApiResponse(code = 500, message = "SERVER ERROR")
})
@Api(tags = {"리뷰 정보를 제공하는 Controller"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors")
public class ScoreApiController {
    private final ScoreService scoreService;

    @ApiOperation(value = "리뷰 저장", notes = "리뷰를 저장하는 메소드입니다.", response = Long.class)
    @PostMapping("/score")
    public ResponseEntity<?> saveScore(@RequestBody ScoreReqDto scoreDto){
        Long savedId = scoreService.save(scoreDto);
        return new ResponseEntity<>(new Result<>("해당 리뷰가 정상적으로 저장되었습니다.", savedId), HttpStatus.CREATED);
    }

    @ApiOperation(value = "리뷰 정보 리턴", notes = "해당 번호에 해당하는 리뷰 정보를 리턴하는 메소드입니다.", response = Result.class)
    @GetMapping("/score/{id}")
    public ResponseEntity<?> findById(@PathVariable("id")Long id){
        ScoreDto score = scoreService.findById(id);
        return new ResponseEntity<>(new Result<>("해당 번호에 대한 리뷰 정보 입니다.", score), HttpStatus.OK);
    }

    //nickname으로 모든 리뷰 가져오기
    @ApiOperation(value = "멘토의 모든 리뷰 리턴", notes = "해당 닉네임에 해당하는 모든 리뷰 정보를 리턴하는 메소드입니다.", response = Result.class)
    @GetMapping("/score")
    public ResponseEntity<?> findByNickname(@RequestParam("nickname")String nickname){
        List<ScoreDto> scores = scoreService.findScoresByNickname(nickname);
        return new ResponseEntity<>(new Result<>("멘토의 닉네임으로 검색한 리뷰 목록입니다.", scores), HttpStatus.OK);
    }

    //mentee의 userId로 작성한 리뷰 가져오기
    @ApiOperation(value = "사용자가 작성한 리뷰 리턴", notes = "해당 번호에 해당하는 참가자가 작성한 모든 리뷰를 리턴하는 메소드입니다.", response = Result.class)
    @GetMapping("/score/mentee/{userId}")
    public ResponseEntity<?> findByUserId(@PathVariable("userId")Long userId){
        List<ScoreMenteeDto> scores = scoreService.findByUserId(userId);
        return new ResponseEntity<>(new Result<>("해당 멘티가 작성한 모든 리뷰 목록 입니다.", scores), HttpStatus.OK);
    }

    //mentor의 userId로 작성된 리뷰 가져오기
    @ApiOperation(value = "멘토의 모든 리뷰 리턴", notes = "해당 번호에 해당하는 멘토의 모든리뷰를 리턴하는 메소드입니다.", response = Result.class)
    @GetMapping("/score/mentor/{mentorId}")
    public ResponseEntity<?> findByMentorId(@PathVariable("mentorId")Long mentorId){
        List<ScoreDto> scores = scoreService.findByMentorId(mentorId);
        return new ResponseEntity<>(new Result<>("멘토의 고유 식별자로 멘토에게 작성된 모든 리뷰 목록", scores), HttpStatus.OK);
    }

    @ApiOperation(value = "모든 리뷰 리턴", notes = "저장되어 있는 모든 리뷰를 리턴하는 메소드입니다.", response = Result.class)
    @GetMapping("/scores")
    public ResponseEntity<?> findScores(){
        List<ScoreDto> scores = scoreService.findAll();
        return new ResponseEntity<>(new Result<>("멘토 서비스내 작성된 모든 리뷰 목록입니다.", scores), HttpStatus.OK);
    }

    // 멘티가 작성한 리뷰 삭제하기
    @ApiOperation(value = "리뷰 삭제", notes = "해당 번호에 해당하는 리뷰를 삭제하는 메소드입니다.", response = Result.class)
    @DeleteMapping("/score/{id}")
    public ResponseEntity<?> deleteScore(@PathVariable("id")Long id){
        Long deleteId = scoreService.deleteScore(id);
        return new ResponseEntity<>(new Result<>("해당 번호의 리뷰가 삭제되었습니다.", deleteId), HttpStatus.OK);
    }

    @ApiOperation(value = "리뷰 수정", notes = "해당 번호에 해당하는 리뷰를 수정하는 메소드입니다.", response = Result.class)
    @PatchMapping("/score/{id}")
    public ResponseEntity<?> updateScore(@PathVariable("id")Long id, @RequestBody ScoreModifyDto scoreDto){
        Long modifiedId = scoreService.updateScore(id, scoreDto);
        return new ResponseEntity<>(new Result<>("해당 번호의 리뷰가 수정되었습니다.", modifiedId), HttpStatus.OK);
    }

    // 멘티가 해당 글에 대해 리뷰를 작성했는지 확인한다.
    @ApiOperation(value = "참가자 리뷰 작성 여부 확인", notes = "리뷰 작성 여부를 확인하는 메소드입니다.", response = boolean.class)
    @GetMapping("/score/mentee")
    public boolean isExist(@RequestParam("userId")Long userId, @RequestParam("boardId")Long boardId){
        return scoreService.isExist(userId, boardId);
    }
}
