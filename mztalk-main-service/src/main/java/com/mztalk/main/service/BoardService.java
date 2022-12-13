package com.mztalk.main.service;

import com.mztalk.main.domain.dto.BoardDto;
import com.mztalk.main.domain.entity.Board;
import com.mztalk.main.domain.entity.Result;
import com.mztalk.main.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
@Transactional(readOnly  = true)
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardDto boardDto) {

        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional
    public Long updateBoard(Long id, BoardDto boardDto) {

        Board boards = boardRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));

        boards.updateBoard(boardDto);

        return id;
    }

    @Transactional(readOnly  = true)
    public Result findAll() {

        List<Board> boards = boardRepository.findAll();
        List<BoardDto> collect = boards.stream().map(BoardDto::new).collect(Collectors.toList());

        return new Result(collect);
    }

    public Result findAllByOwn(Long own) {
        List<Board> boards = boardRepository.findAllByOwn(own);
        List<BoardDto> collect = boards.stream().map(BoardDto::new).collect(Collectors.toList());
        return new Result(collect);
    }
}
