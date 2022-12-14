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

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyServiceImpl implements ReplyService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Override
    @Transactional
    public Long replySave(Long id, ReplyRequestDto replyRequestDto) {

        Board boardEntity = boardRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("댓글쓰기 실패"));
        //replyRepository.save(boardEntity, replyRequestDto);
        return null;
    }
}
