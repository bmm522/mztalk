package com.mztalk.mentor.service.impl;

import com.mztalk.mentor.domain.SearchCondition;
import com.mztalk.mentor.domain.dto.BoardResDto;
import com.mztalk.mentor.domain.dto.BoardMenteeDto;
import com.mztalk.mentor.domain.dto.BoardReqDto;
import com.mztalk.mentor.domain.dto.MentorBoardDto;
import com.mztalk.mentor.domain.entity.Board;
import com.mztalk.mentor.domain.entity.Mentor;
import com.mztalk.mentor.repository.BoardRepository;
import com.mztalk.mentor.repository.MentorRepository;
import com.mztalk.mentor.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final MentorRepository mentorRepository;

    @Override
    @Transactional
    public Long saveBoard(BoardReqDto boardReqDto) {
        Mentor mentor = mentorRepository.findMentorByUserId(boardReqDto.getUserId());
        Board board = boardReqDto.toEntity();
        board.addMentor(mentor);
        return boardRepository.save(board).getId();
    }

    // 메인페이지 출력 메소드, 결제가 안되고 멘토링 전 글만 출력된다.
    @Override
    public List<BoardResDto> findNullPaymentWithBeforeMentoringDate() {
        LocalDateTime now = LocalDateTime.now();
        List<Board> boards = boardRepository.findNullPaymentWithBeforeMentoringDate(now);
        List<BoardResDto> collect = boards.stream().map(BoardResDto::new).collect(Collectors.toList());
        return collect;
    }

    // 멘티가 본인이 신청한 글에 대한 정보만 가져온다.
    @Override
    public List<BoardMenteeDto> findBoardByMenteeId(Long menteeId) {
        List<Board> boards = boardRepository.findBoardByMenteeId(menteeId);
        List<BoardMenteeDto> collect = boards.stream().map(BoardMenteeDto::new).collect(Collectors.toList());
        return collect;
    }

    @Override
    public BoardResDto findBoardByBoardId(Long id) {
        Board board = boardRepository.findBoardByBoardId(id);
        BoardResDto boardResDto = new BoardResDto(board,new MentorBoardDto(board.getMentor()));
        return boardResDto;
    }

    //멘티가 본인이 신청한 멘토링 글에 대해 보는 메소드 멘토링 이후의 글만 출력되게 한다.
    @Override
    public List<BoardMenteeDto> findBoardByUserId(Long userId) {
        LocalDateTime now = LocalDateTime.now();
        List<Board> boardList = boardRepository.findBoardByUserId(userId,now);
        List<BoardMenteeDto> collect = boardList.stream().map(BoardMenteeDto::new).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<BoardResDto> latestBoard() {
        List<Board> boards = boardRepository.latestBoard();
        List<BoardResDto> collect = boards.stream().map(BoardResDto::new).collect(Collectors.toList());
        return collect;
    }

    // 멘티가 멘토링 신청 후 멘토링 시간이 지난 후에 리뷰창에 나타난다.
    @Override
    public List<BoardResDto> findByMentoringDateBefore() {
        LocalDateTime now = LocalDateTime.now();
        List<Board> boards = boardRepository.findByMentoringDateBefore(now);
        List<BoardResDto> collect = boards.stream().map(BoardResDto::new).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<BoardResDto> findBoardByMentorId(Long mentorId) {
        List<Board> boards = boardRepository.findBoardByMentorId(mentorId);
        List<BoardResDto> collect = boards.stream().map(BoardResDto::new).collect(Collectors.toList());
        return collect;
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
    public Long updateBoard(Long id, BoardResDto boardResDto) {
        Board savedBoard = boardRepository.findBoardByBoardId(id);
        savedBoard.updateBoard(boardResDto);
        return savedBoard.getId();
    }

    @Override
    public List<BoardResDto> searchWithCondition(SearchCondition searchCondition) {
        List<Board> boardList = boardRepository.searchWithCondition(searchCondition);
        List<BoardResDto> collect = boardList.stream().map(BoardResDto::new).collect(Collectors.toList());
        return collect;
    }
}
