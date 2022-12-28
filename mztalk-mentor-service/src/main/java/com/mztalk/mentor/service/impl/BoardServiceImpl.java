package com.mztalk.mentor.service.impl;

import com.mztalk.mentor.domain.SearchCondition;
import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.dto.BoardDto;
import com.mztalk.mentor.domain.dto.MyBoardDto;
import com.mztalk.mentor.domain.entity.Board;
import com.mztalk.mentor.domain.entity.Mentor;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.exception.BoardNotFoundException;
import com.mztalk.mentor.exception.DuplicateException;
import com.mztalk.mentor.repository.BoardRepository;
import com.mztalk.mentor.repository.MentorRepository;
import com.mztalk.mentor.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final MentorRepository mentorRepository;

    @Override
    @Transactional
    public Long saveBoard(ConcurrentHashMap<String,String> boardMap) {
        Long userId = Long.parseLong(boardMap.get("userId"));
        Mentor mentor = mentorRepository.findMentorByUserId(userId);
        Board board = Board.builder().
                category(boardMap.get("category")).
                title(boardMap.get("title")).
                nickname(boardMap.get("nickname")).
                content(boardMap.get("content")).
                introduction(boardMap.get("introduction")).
                career(boardMap.get("career")).
                salary(Integer.parseInt(boardMap.get("salary"))).
                mentoringDate(LocalDateTime.parse(boardMap.get("mentoringDate"))).
                status(Status.YES).
                build();
        board.addMentor(mentor);
        return boardRepository.save(board).getId();
    }

    @Override
    public BoardDto findBoardByBoardId(Long id) {
        Board board = boardRepository.findBoardByBoardId(id);
        BoardDto boardDto = new BoardDto(board);
        return boardDto;
    }

    //멘티가 본인이 신청한 멘토링 글에 대해 보는 메소드.
    @Override
    public Result findBoardByUserId(Long userId) {
        LocalDateTime now = LocalDateTime.now();
        List<Board> boardList = boardRepository.findBoardByUserId(userId,now);
        List<BoardDto> collect = boardList.stream().map(BoardDto::new).collect(Collectors.toList());
        return new Result(collect);
    }

    @Override
    public Result latestBoard() {
        List<Board> boards = boardRepository.latestBoard();
        List<BoardDto> collect = boards.stream().map(BoardDto::new).collect(Collectors.toList());
        return new Result(collect);
    }

    @Override
    public Result findByMentoringDateBefore() {
        LocalDateTime now = LocalDateTime.now();
        List<Board> boards = boardRepository.findByMentoringDateBefore(now);
        List<BoardDto> collect = boards.stream().map(BoardDto::new).collect(Collectors.toList());
        return new Result(collect);
    }

    @Override
    public Result findBoardByMentorId(Long mentorId) {
        List<Board> boards = boardRepository.findBoardByMentorId(mentorId);
        List<BoardDto> collect = boards.stream().map(BoardDto::new).collect(Collectors.toList());
        return new Result(collect);
    }

    @Override
    public Result findByPaymentIsNull() {
        List<Board> boards = boardRepository.findByPaymentIsNull();
        List<BoardDto> collect = boards.stream().map(BoardDto::new).collect(Collectors.toList());
        return new Result(collect);
    }

    @Override
    @Transactional //상태만 수정한다. // 수정 후 Status = No여서보이면 안된다.
    public Long delete(Long id) {
        Board findBoard = boardRepository.findBoardByBoardId(id);
        boardRepository.delete(findBoard);
        return findBoard.getId();
    }

    @Override
    @Transactional
    public Long updateBoard(Long id, BoardDto boardDto) {
        Board savedBoard = boardRepository.findBoardByBoardId(id);
        savedBoard.updateBoard(boardDto);
        return savedBoard.getId();
    }

    @Override
    public Result searchWithCondition(SearchCondition searchCondition) {
        System.out.println(searchCondition.toString());
        List<Board> boardList = boardRepository.searchWithCondition(searchCondition);
        System.out.println("boardList = " + boardList.toString());
        List<BoardDto> collect = boardList.stream().map(BoardDto::new).collect(Collectors.toList());
        return new Result(collect);
    }
}
