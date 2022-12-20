package com.mztalk.auction.service.impl;

import com.mztalk.auction.domain.dto.BoardRequestDto;
import com.mztalk.auction.domain.dto.BoardDto;
import com.mztalk.auction.domain.dto.CommentDto;
import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.domain.entity.Comment;
import com.mztalk.auction.repository.BoardRepository;
import com.mztalk.auction.repository.CommentRepository;
import com.mztalk.auction.service.AuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    //게시글 작성
    @Transactional
    @Override
    public Long insertBoard(BoardRequestDto boardRequestDto) {

        return boardRepository.save(boardRequestDto.toEntity()).getBId();
    }

    //게시글 수정
    @Override
    public int updateBoard(Long bId, BoardDto boardDto) {
        return boardRepository.boardUpdate(bId, boardDto);
    }

    //전체 게시글 조회
    @Override
    public List<Board> selectBoardList() {
        return boardRepository.findAll();
    }

    //게시물 삭제
    @Override
    public int deleteBoard(Long bId) {
        return boardRepository.deleteBoard(bId);
    }

    //특정 게시물 조회
    @Override
    public Board selectBoard(Long bId) {

        return boardRepository.findBybId(bId);
    }

    //입찰가
    @Override
    public int updatePrice(Long bId, BoardDto boardDto) {
        return boardRepository.updatePrice(bId, boardDto);
    }

    //조회수
    @Override
    public int updateCount(Long bId) {
        return boardRepository.updateCount(bId);
    }

    //최신 글 번호 받아오기
    @Override
    public ConcurrentHashMap<String, String> getRecentBoardNo() {
        long bId =  boardRepository.getRecentBoardNo();
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
//        map.put("bId", String.valueOf(bId+1));
        return map;
    }

    //댓글 작성
    @Override
    public Comment insertComment(CommentDto commentDto, Long bId) {
        return commentRepository.save(commentDto.toEntity());
    }

    //댓글 수정
    @Override
    public int updateComment(Long cId, CommentDto commentDto) {
        return commentRepository.updateComment(cId, commentDto);
    }

    //댓글 삭제
    @Override
    public int deleteComment(Long cId, CommentDto commentDto) {
        return commentRepository.deleteComment(cId, commentDto);
    }


}
