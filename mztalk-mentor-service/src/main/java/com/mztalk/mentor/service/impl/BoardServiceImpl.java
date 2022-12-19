package com.mztalk.mentor.service.impl;

import com.mztalk.mentor.domain.SearchCondition;
import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.dto.BoardDto;
import com.mztalk.mentor.domain.dto.BoardDto2;
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
        if(findBoardByMentorId(userId)){
            throw new DuplicateException("이미 작성한 게시글이 존재합니다.");
        }
        Mentor mentor = mentorRepository.findMentorByUserId(userId);
        Board board = Board.builder().
                category(boardMap.get("category")).
                title(boardMap.get("title")).
                nickname(boardMap.get("nickname")).
                content(boardMap.get("content")).
                introduction(boardMap.get("introduction")).
                career(boardMap.get("career")).
                salary(Integer.parseInt(boardMap.get("salary"))).
                status(Status.YES).
                build();
        board.addMentor(mentor);
        return boardRepository.save(board).getId();
    }

    @Override
    public BoardDto findById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException("해당 번호의 글이 존재하지 않습니다."));
        BoardDto boardDto = new BoardDto(board);
        return boardDto;
    }

    //멘티가 본인이 신청한 멘토링 글에 대한 참가자를 보는 메소드.
    @Override
    public Result findBoardByUserId(Long userId) {
        List<Board> boardList = boardRepository.findBoardByUserId(userId);
        List<BoardDto> collect = boardList.stream().map(BoardDto::new).collect(Collectors.toList());
        return new Result(collect);
    }


    // 순수하게 본인이 작성한 글만 불러오기
    @Override
    public BoardDto2 getBoardByMentorId(Long mentorId) {
        Board findBoard = boardRepository.getBoardByMentorId(mentorId);
        BoardDto2 boardDto = new BoardDto2(findBoard);
        return boardDto;
    }

    @Override
    public boolean findBoardByMentorId(Long mentorId) {
        Board board = boardRepository.findBoardByMentorId(mentorId);
        boolean isTrue = board == null ? false : true; //이미 작성한 글이 존재하면 true반환.
        return isTrue;
    }

    @Override
    public Result findAll() {
        List<Board> boards = boardRepository.findAll();
        List<BoardDto> collect = boards.stream().map(BoardDto::new).collect(Collectors.toList());
        return new Result(collect);
    }

    @Override
    @Transactional //상태만 수정한다. // 수정 후 Status = No여서보이면 안된다.
    public Long delete(Long mentorId) {
        Board findBoard = boardRepository.getBoardByMentorId(mentorId);
        boardRepository.delete(findBoard);
        return findBoard.getId();
    }

    @Override
    @Transactional
    public Long updateBoard(Long id, BoardDto boardDto) {
        Board savedBoard = boardRepository.getBoardByMentorId(id);
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
