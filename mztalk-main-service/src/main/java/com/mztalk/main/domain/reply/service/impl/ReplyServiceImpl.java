package com.mztalk.main.domain.reply.service.impl;

import com.mztalk.main.domain.reply.Reply;
import com.mztalk.main.domain.reply.dto.ReplyResponseDto;
import com.mztalk.main.domain.reply.repository.ReplyRepository;
import com.mztalk.main.domain.reply.dto.ReplyRequestDto;
import com.mztalk.main.domain.board.repository.BoardRepository;
import com.mztalk.main.domain.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyServiceImpl implements ReplyService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;


    @Override
    @Transactional
    public Reply replySave(Long id, ReplyRequestDto replyRequestDto) {

        boardRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다."));

        return replyRepository.save(replyRequestDto.toEntity(id));
    }

    @Override
    @Transactional
    public Long deleteReply(Long id) {

        Reply reply = replyRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다."));

        replyRepository.delete(reply);

        return reply.getId();
    }

    @Override
    @Transactional
    public int changeNickname(Map<String, String> body) {

        return replyRepository.updateNickname(Long.parseLong(body.get("userNo")),body.get("nickname"));
    }
}
