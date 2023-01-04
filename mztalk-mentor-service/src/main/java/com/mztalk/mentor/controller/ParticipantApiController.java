package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.dto.ParticipantResDto;
import com.mztalk.mentor.domain.dto.ParticipantReqDto;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.service.ParticipantService;
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
@Api(tags = {"멘토링 신청자 정보 제공 Controller"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors")
public class ParticipantApiController {

    private final ParticipantService participantService;

    @ApiOperation(value = "멘토링 참가 신청", notes = "결제 후 참가자정보를 저장하는 메소드입니다.", response = Result.class)
    @PostMapping("/participant")
    public ResponseEntity<?> save(@RequestBody ParticipantReqDto participantReqDto){
        System.out.println("participantReqDto = " + participantReqDto);
        Long savedId = participantService.save(participantReqDto);
        return new ResponseEntity<>(new Result<>("신청이 완료되었습니다.", savedId), HttpStatus.CREATED);
    }

    @ApiOperation(value = "참가자 정보 리턴", notes = "해당 번호에 해당하는 참가자 정보를 리턴하는 메소드입니다.", response = Result.class)
    @ApiImplicitParam(name = "id", value = "참가고유번호", required = true, dataType = "int", paramType = "path")
    @GetMapping("/participant/{id}")
    public ResponseEntity<?> findParticipant(@PathVariable("id") Long id){
        ParticipantResDto participant = participantService.findById(id);
        return new ResponseEntity<>(new Result<>("해당 번호에 대한 참가자 정보", participant), HttpStatus.OK);
    }

    @ApiOperation(value = "멘토에게 신청한 모든 참가자 정보 리턴", notes = "해당 멘토에게 신청기록이 존재하는 모든 참가가정보를 리턴하는 메소드입니다.", response = Result.class)
    @ApiImplicitParam(name = "mentorId", value = "멘토 식별자", required = true, dataType = "int", paramType = "query")
    @GetMapping("/participant")
    public ResponseEntity<?> findParticipantsByMentorId(@RequestParam("mentorId")Long mentorId){
        List<ParticipantResDto> participants = participantService.findParticipantsByMentorId(mentorId);
        return new ResponseEntity<>(new Result<>("해당 멘토에게 신청한 유저 목록", participants), HttpStatus.OK);
    }

    @ApiOperation(value = "멘토링 참가기록이 존재하는 모든 사용자 정보 리턴", notes = "멘토링 참가기록이 존재하는 모든 사용자 정보 리턴하는 메소드입니다.", response = Result.class)
    @GetMapping("/participants")
    public ResponseEntity<?> findAll(){
        List<ParticipantResDto> participants = participantService.findAll();
        return new ResponseEntity<>(new Result<>("모든 참가자 목록", participants), HttpStatus.OK);
    }

    @ApiOperation(value = "참가 취소", notes = "해당 번호에 해당하는 참가신청을 취소하는 메소드입니다.", response = Long.class)
    @ApiImplicitParam(name = "id", value = "참가자 식별자", required = true, dataType = "int", paramType = "path")
    @PatchMapping("/participant/{id}")
    public Long cancel(@PathVariable("id") Long id){
        return participantService.cancelParticipate(id);
    }

    @ApiOperation(value = "참석자 여부 확인", notes = "참석자여부를 리턴하는 메소드입니다.", response = boolean.class)
    @ApiImplicitParam(name = "boardId", value = "글 번호", required = true, dataType = "int", paramType = "path")
    @GetMapping("/participant/board/{boardId}")
    public boolean existParticipant(@PathVariable("boardId")Long boardId){
        return participantService.existParticipant(boardId);
    }
}
