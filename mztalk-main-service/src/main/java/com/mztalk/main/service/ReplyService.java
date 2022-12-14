package com.mztalk.main.service;


import com.mztalk.main.domain.dto.ReplyRequestDto;
import com.mztalk.main.domain.entity.Reply;

public interface ReplyService {

    Long replySave(Long id, ReplyRequestDto replyRequestDto);
}
