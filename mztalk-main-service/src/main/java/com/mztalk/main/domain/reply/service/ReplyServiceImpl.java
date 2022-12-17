package com.mztalk.main.domain.reply.service;

import com.mztalk.main.domain.reply.Reply;
import com.mztalk.main.domain.reply.repository.ReplyRepository;
import com.mztalk.main.domain.reply.dto.ReplyRequestDto;
import com.mztalk.main.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
