package com.mztalk.story.service;


import com.mztalk.story.domain.reply.dto.ReplyNicknameModifyDto;
import com.mztalk.story.domain.reply.dto.ReplyRequestDto;
import com.mztalk.story.domain.reply.dto.ReplyResponseDto;

public interface ReplyService {


    ReplyResponseDto replySave(Long id, ReplyRequestDto replyRequestDto);

    Long deleteReply(Long id);

    Long changeNickname(ReplyNicknameModifyDto replyNicknameModifyDto);
}
