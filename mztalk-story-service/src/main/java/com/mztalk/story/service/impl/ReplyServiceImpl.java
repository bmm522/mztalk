package com.mztalk.story.service.impl;

import com.mztalk.story.domain.board.Board;
import com.mztalk.story.domain.reply.Reply;
import com.mztalk.story.domain.reply.dto.ReplyNicknameModifyDto;
import com.mztalk.story.domain.reply.dto.ReplyResponseDto;
import com.mztalk.story.repository.ReplyRepository;
import com.mztalk.story.domain.reply.dto.ReplyRequestDto;
import com.mztalk.story.repository.BoardRepository;
import com.mztalk.story.service.ReplyService;
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
    public ReplyResponseDto replySave(Long id, ReplyRequestDto replyRequestDto) {

        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        Reply reply = replyRepository.save(replyRequestDto.toEntity(board));
        ReplyResponseDto replyResponseDto = new ReplyResponseDto(reply);

        return replyResponseDto;
    }

    @Override
    @Transactional
    public Long deleteReply(Long id) {

        Reply reply = replyRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다."));

        replyRepository.delete(reply);

        return reply.getId();
    }
    //닉네임 요청시 변경
    @Override
    @Transactional
    public Long changeNickname(ReplyNicknameModifyDto replyNicknameModifyDto) {

        Reply reply = replyRepository.findByReplyUserNo(replyNicknameModifyDto.getReplyUserNo());
        reply.modifyNickname(replyNicknameModifyDto.getReplyNickname());
        return reply.getReplyUserNo();
    }
}
