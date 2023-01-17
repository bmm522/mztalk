package com.mztalk.main.domain.reply.controller;


import com.mztalk.main.common.CMRespDto;
import com.mztalk.main.domain.reply.Reply;
import com.mztalk.main.domain.reply.dto.ReplyNicknameModifyDto;
import com.mztalk.main.domain.reply.dto.ReplyResponseDto;
import com.mztalk.main.domain.reply.repository.ReplyRepository;
import com.mztalk.main.domain.reply.service.ReplyService;
import com.mztalk.main.domain.reply.dto.ReplyRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 201, message = "CREATED"),
        @ApiResponse(code = 400, message = "BAD REQUEST"),
        @ApiResponse(code = 500, message = "SERVER ERROR")
})
@Api(tags = {"댓글 정보를 제공하는 Controller"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/story")
public class ReplyApiController {

    private final ReplyService replyService;

    //댓글쓰기
    @ResponseBody
    @PostMapping("/board/{id}/reply")
    public ReplyResponseDto replySave(@PathVariable("id") Long id, @RequestBody ReplyRequestDto replyRequestDto){
       return replyService.replySave(id, replyRequestDto);
//        return new ResponseEntity<>(new CMRespDto<>(1, "댓글쓰기성공", result), HttpStatus.CREATED);
    }

    //댓글삭제
    @DeleteMapping("/board/{id}/reply")
    public ResponseEntity<?> deleteScore(@PathVariable("id") Long id){
        replyService.deleteReply(id);
        return new ResponseEntity<>(new CMRespDto<>(1, "댓글삭제성공", null), HttpStatus.OK);
    }

    @PatchMapping("/nickname")
    public ResponseEntity<?> changeNickname(@RequestBody ReplyNicknameModifyDto replyNicknameModifyDto){
        return new ResponseEntity<>(new CMRespDto<>(1,"이름변경성공", replyService.changeNickname(replyNicknameModifyDto)), HttpStatus.OK);
        }
    }
