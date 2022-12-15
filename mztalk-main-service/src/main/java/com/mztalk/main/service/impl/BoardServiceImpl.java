package com.mztalk.main.service.impl;

import com.mztalk.main.domain.dto.BoardDto;
import com.mztalk.main.domain.entity.Board;
import com.mztalk.main.domain.entity.Result;
import com.mztalk.main.repository.BoardRepository;
import com.mztalk.main.service.BoardService;
import com.mztalk.main.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    //전체글 불러오기
    @Override
    @Transactional(readOnly  = true)
    public Result findAllByOwn(Long own) {
        List<Board> boards = boardRepository.findAllByOwn(own);
        List<BoardDto> collect = boards.stream().map(BoardDto::new).collect(Collectors.toList());
        return new Result(collect);
    }

    //글쓰기
    @Override
    @Transactional
    public Long save(BoardDto boardDto) {

        return boardRepository.save(boardDto.toEntity()).getId();
    }

    //글수정
    @Override
    @Transactional
    public Long updateBoard(Long id, BoardDto boardDto) {

        Board savedBoard = boardRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다."));
        savedBoard.updateBoard(boardDto);
        return savedBoard.getId();
    }

    //글삭제(status만변화)
    @Override
    @Transactional
    public Long deleteBoard(Long id, BoardDto boardDto) {

        Board board = boardRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다."));
        board.changeStatus();
        return board.getId();

    }
    // 상세보기
//    public BoardDto getBoardDetails(long id){
//        BoardDto boardDto = new BoardDto(id);
//        Board board = boardDto.toEntityOfId();
//
////     Board board = new BoardDto(id).toEntityOfId();
//
////        return new BoardDto(boardRepository.findById(id).get());
//
//    }

}
