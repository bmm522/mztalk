package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.dto.MentorResDto;
import com.mztalk.mentor.domain.dto.MentorReqDto;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.service.MentorService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 201, message = "CREATED"),
        @ApiResponse(code = 400, message = "BAD REQUEST"),
        @ApiResponse(code = 500, message = "SERVER ERROR")
})
@Api(tags = {"멘토 등록된 유저 정보 제공 Controller"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors")
public class MentorApiController {

    private final MentorService mentorService;

    @ApiOperation(value = "멘토권한 부여", notes = "멘토 권한을 부여하는 메소드입니다.", response = Result.class)
    @PostMapping("/member") //멘토 신청 후 허락 버튼
    public ResponseEntity<?> save(@RequestBody MentorReqDto mentorReqDto){
        Long savedId = mentorService.save(mentorReqDto);
        return new ResponseEntity<>(new Result<>("해당 멘토가 등록되었습니다.", savedId), HttpStatus.CREATED);
    }

    @ApiOperation(value = "멘토 정보 리턴", notes = "해당번호의 멘토 정보를 리턴하는 메소드입니다.", response = Result.class)
    @ApiImplicitParam(name = "id", value = "멘토 식별자", required = true, dataType = "int", paramType = "path")
    @GetMapping("/member/{id}")
    public ResponseEntity<?> findById(@PathVariable("id")Long id){
        MentorResDto mentor = mentorService.findById(id);
        return new ResponseEntity<>(new Result<>("해당 번호에 대한 멘토 정보", mentor), HttpStatus.OK);
    }

    @ApiOperation(value = "멘토로 등록되어있는지 확인", notes = "해당번호의 이용자가 멘토인지를 확인하는 메소드입니다.", response = boolean.class)
    @ApiImplicitParam(name = "userId", value = "멘토 식별자", required = true, dataType = "int", paramType = "query")
    @GetMapping("/member")
    public boolean isExist(@RequestParam("userId")Long userId){
        return mentorService.isExist(userId);
    }

    @ApiOperation(value = "모든 멘토 정보 리턴", notes = "멘토로 등록되어 있는 모든 사용자 정보를 리턴하는 메소드입니다.", response = Result.class)
    @GetMapping("/members")
    public ResponseEntity<?> findAll(){
        List<MentorResDto> mentors = mentorService.findAll();
        return new ResponseEntity<>(new Result<>("멘토로 등록된 모든 사용자 목록", mentors), HttpStatus.OK);
    }

}
