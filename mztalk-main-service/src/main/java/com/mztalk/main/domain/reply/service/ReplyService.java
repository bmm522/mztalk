package com.mztalk.main.domain.reply.service;


import com.mztalk.main.domain.reply.Reply;
import com.mztalk.main.domain.reply.dto.ReplyRequestDto;
import com.mztalk.main.domain.reply.dto.ReplyResponseDto;

import java.util.Map;

public interface ReplyService {


    Reply replySave(Long id, ReplyRequestDto replyRequestDto);

    Long deleteReply(Long id);

    int changeNickname(Map<String, String> body);
}
