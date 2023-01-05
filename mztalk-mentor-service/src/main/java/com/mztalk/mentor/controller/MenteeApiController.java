package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.dto.MenteeReqDto;
import com.mztalk.mentor.domain.dto.MenteeResDto;
import com.mztalk.mentor.domain.dto.NicknameModifyDto;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.service.MenteeService;
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
@Api(tags = {"서비스 이용자 정보 제공 Controller"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors")
public class MenteeApiController {
    private final MenteeService menteeService;

    @ApiOperation(value = "멘토 서비스 사용자 등록", notes = "서비스 사용자 등록 메소드입니다.", response = Result.class)
    @PostMapping
    public ResponseEntity<?> saveClient(@RequestBody MenteeReqDto menteeReqDto){
        Long id = menteeService.saveClient(menteeReqDto);
        return new ResponseEntity<>(new Result<>("사용자가 서비스 등록되었습니다.",id), HttpStatus.CREATED);
    }

    @ApiOperation(value = "사용자 정보 리턴", notes = "해당번호의 사용자정보를 리턴하는 메소드입니다.", response = Result.class)
    @ApiImplicitParam(name = "id", value = "사용자 식별자", required = true, dataType = "int", paramType = "path")
    @GetMapping("/mentee/{id}")
    public ResponseEntity<?> findClient(@PathVariable("id") Long id){
        MenteeResDto mentee = menteeService.findClient(id);
        return new ResponseEntity<>(new Result<>("멘티에 관한 정보",mentee),HttpStatus.OK);
    }

    @ApiOperation(value = "사용자 닉네임 변경", notes = "해당번호의 사용자의 닉네임을 변경하는 메소드입니다.", response = Result.class)
    @ApiImplicitParam(name = "id", value = "사용자 식별자", required = true, dataType = "int", paramType = "path")
    @PatchMapping("/mentee/{id}")
    public ResponseEntity<?> modifyNickname(@PathVariable("id") Long id, @RequestBody NicknameModifyDto nicknameModifyDto){
        Long userId = menteeService.modifyNickname(nicknameModifyDto);
        return new ResponseEntity<>(new Result<>("멘티에 관한 정보",userId),HttpStatus.OK);
    }

    @ApiOperation(value = "모든 사용자 정보 리턴", notes = "모든 서비스 사용자 정보를 리턴하는 메소드입니다.", response = Result.class)
    @GetMapping("/mentees")
    public ResponseEntity<?> findAll(){
        List<MenteeResDto> mentees = menteeService.findAll();
        return new ResponseEntity<>(new Result<>("멘티 전체 목록", mentees), HttpStatus.OK);
    }



}
