package com.mztalk.main.controller;

import com.mztalk.main.domain.dto.CMRespDto;
import com.mztalk.main.domain.dto.ReplyRequestDto;
import com.mztalk.main.domain.entity.Reply;
import com.mztalk.main.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reply")
public class ReplyApiController {

    private final ReplyService replyService;


    @PostMapping("/{boardId}/reply")
    public String saveReply(ReplyRequestDto replyRequestDto){

        return null;
    }
}
