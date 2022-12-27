package com.mztalk.main.domain.board.service;

import com.mztalk.main.domain.board.dto.BoardDto;
import com.mztalk.main.domain.board.Board;
import com.mztalk.main.common.Result;
import com.mztalk.main.domain.board.repository.BoardRepository;
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


    //퍼블릭글 불러오기
    @Override
    @Transactional(readOnly  = true)
    public Result findAllByOwn(Long own) {
        List<Board> boards = boardRepository.findAllByOwn(own);
        List<BoardDto> collect = boards.stream().map(BoardDto::new).collect(Collectors.toList());
        return new Result(collect);
    }

    @Override
    @Transactional(readOnly  = true)
    public Result findByOwn(Long own) {
        List<Board> boards = boardRepository.findByOwn(own);
        List<BoardDto> collect = boards.stream().map(BoardDto::new).collect(Collectors.toList());
        return new Result(collect);
    }



    //글쓰기
    @Override
    @Transactional
    public Board save(BoardDto boardDto) {
       // System.out.println("@@@@");
        //System.out.println(boardDto.getNickname());
        //System.out.println(boardDto.getOwn());

        return boardRepository.save(boardDto.toEntity());
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
    public Long deleteBoard(Long id) {

        Board board = boardRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다."));
        board.changeStatus();
        return board.getId();

    }




}
