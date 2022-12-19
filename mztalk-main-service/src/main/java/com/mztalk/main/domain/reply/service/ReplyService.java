package com.mztalk.main.domain.reply.service;


import com.mztalk.main.domain.reply.Reply;
import com.mztalk.main.domain.reply.dto.ReplyRequestDto;

public interface ReplyService {


    Reply replySave(Long id, ReplyRequestDto replyRequestDto);

    Long deleteReply(Long id, Long replyId);
}
