package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.Entity.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void saveTest() throws Exception {
        //given
        Board board1 = new Board();
        Board board2 = new Board();

        //when
        Board savedBoard1 = boardRepository.save(board1);
        Board savedBoard2 = boardRepository.save(board2);

        //then
        assertThat(savedBoard1).isEqualTo(board1);
        assertThat(savedBoard2).isEqualTo(board2);
    }

    @Test
    public void findTest() throws Exception {
        //given
        Board board1 = new Board();
        Board board2 = new Board();

        board1.setTitle("수학");
        board2.setTitle("영어");

        boardRepository.save(board1);
        boardRepository.save(board2);
        //when
        Board findBoard1 = boardRepository.findById(board1.getId()).get();
        Board findBoard2 = boardRepository.findById(board2.getId()).get();

        //then
        assertThat(board1).isEqualTo(findBoard1);
        assertThat(board2).isEqualTo(findBoard2);
        assertThat(findBoard1.getTitle()).isEqualTo("수학");
        assertThat(findBoard2.getTitle()).isEqualTo("영어");
    }

}