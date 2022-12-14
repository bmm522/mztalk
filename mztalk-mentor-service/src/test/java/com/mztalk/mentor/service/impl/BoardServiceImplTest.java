package com.mztalk.mentor.service.impl;


import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.dto.BoardDto;
import com.mztalk.mentor.domain.entity.Board;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.repository.BoardRepository;
import com.mztalk.mentor.service.BoardService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class BoardServiceImplTest {
    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardRepository boardrepository;

//    @Test
//    public void save() throws Exception {
//        //given
//        BoardDto boardDto1 = new BoardDto();
//        BoardDto boardDto2 = new BoardDto();
//
//        //when
//        Long savedId1 = boardService.saveBoard(boardDto1);
//        Long savedId2 = boardService.saveBoard(boardDto2);
//
//        //then
//        assertThat(savedId1).isEqualTo(1L);
//        assertThat(savedId2).isEqualTo(2L);
//    }
//
//    @Test
//    public void findById() throws Exception {
//        //given
//        BoardDto boardDto1 = new BoardDto();
//        BoardDto boardDto2 = new BoardDto();
//
//        Long savedId1 = boardService.saveBoard(boardDto1);
//        Long savedId2 = boardService.saveBoard(boardDto2);
//
//        //when
//        BoardDto findDto1 = boardService.findById(savedId1);
//        BoardDto findDto2 = boardService.findById(savedId2);
//
//        //then
//        assertThat(findDto1.getId()).isEqualTo(savedId1);
//        assertThat(findDto2.getId()).isEqualTo(savedId2);
//    }
//
//    @Test
//    public void findAll() throws Exception {
//        //given
//        BoardDto boardDto1 = new BoardDto();
//        BoardDto boardDto2 = new BoardDto();
//
//        //when
//        Long savedId1 = boardService.saveBoard(boardDto1);
//        Long savedId2 = boardService.saveBoard(boardDto2);
//
//        List<Board> boards = boardrepository.findAll();
//
//        //then
//        assertThat(boards.size()).isEqualTo(2);
//        assertThat(boards.get(0).getId()).isEqualTo(savedId1);
//        assertThat(boards.get(1).getId()).isEqualTo(savedId2);
//    }
//
//    @Test
//    public void delete() throws Exception {
//        //given
//        BoardDto boardDto1 = new BoardDto();
//        BoardDto boardDto2 = new BoardDto();
//
//        Long savedId1 = boardService.saveBoard(boardDto1);
//        Long savedId2 = boardService.saveBoard(boardDto2);
//
//        //when
//        Long deletedId1 = boardService.delete(savedId1);
//        Long deletedId2 = boardService.delete(savedId2);
//
//        Board board1 = boardrepository.findById(savedId1).get();
//        Board board2 = boardrepository.findById(savedId2).get();
//        //then
//        assertThat(savedId1).isEqualTo(deletedId1);
//        assertThat(savedId2).isEqualTo(deletedId2);
//        assertThat(board1.getStatus()).isEqualTo(Status.NO);
//        assertThat(board2.getStatus()).isEqualTo(Status.NO);
//    }





}