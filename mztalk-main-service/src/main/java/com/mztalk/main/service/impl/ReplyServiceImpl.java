package com.mztalk.main.service.impl;

import com.mztalk.main.domain.dto.ReplyRequestDto;
import com.mztalk.main.domain.entity.Board;
import com.mztalk.main.domain.entity.Reply;
import com.mztalk.main.repository.BoardRepository;
import com.mztalk.main.repository.ReplyRepository;
import com.mztalk.main.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyServiceImpl implements ReplyService{

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;


    @Override
    @Transactional
    public Long replySave(Long id, ReplyRequestDto replyRequestDto) {

        boardRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다."));

        Reply reply = replyRequestDto.toEntity(id);

        return replyRepository.save(reply).getId();

    }

    @Override
    @Transactional
    public Long deleteReply(Long id, Long replyId) {

        boardRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다."));

        Reply reply = replyRepository.findById(replyId).orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다."));

        replyRepository.delete(reply);

        return reply.getId();
    }
}
