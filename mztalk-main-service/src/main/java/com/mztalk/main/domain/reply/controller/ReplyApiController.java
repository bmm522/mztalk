package com.mztalk.main.domain.reply.controller;


import com.mztalk.main.common.CMRespDto;
import com.mztalk.main.domain.reply.Reply;
import com.mztalk.main.domain.reply.dto.ReplyResponseDto;
import com.mztalk.main.domain.reply.service.ReplyService;
import com.mztalk.main.domain.reply.dto.ReplyRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/story")
public class ReplyApiController {

    private final ReplyService replyService;


    //댓글쓰기
    @ResponseBody
    @PostMapping("/board/{id}/reply")
    public Reply replySave(@PathVariable("id") Long id, @RequestBody ReplyRequestDto replyRequestDto){
       return replyService.replySave(id, replyRequestDto);
//        return new ResponseEntity<>(new CMRespDto<>(1, "댓글쓰기성공", result), HttpStatus.CREATED);
    }

    //댓글삭제
    @DeleteMapping("/board/{id}/reply")
    public ResponseEntity<?> deleteScore(@PathVariable("id") Long id){
        replyService.deleteReply(id);
        return new ResponseEntity<>(new CMRespDto<>(1, "댓글삭제성공", null), HttpStatus.OK);
    }



}
