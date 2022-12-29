package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.SearchCondition;
import com.mztalk.mentor.domain.dto.BoardDto;
import com.mztalk.mentor.domain.dto.BoardMenteeDto;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors")
public class BoardApiController {

    private final BoardService boardService;

    @GetMapping("/boards")
    public ResponseEntity<?> findNullPaymentWithBeforeMentoringDate(){
        List<BoardDto> boards = boardService.findNullPaymentWithBeforeMentoringDate();
        return new ResponseEntity<>(new Result<>("모든 글 목록", boards), HttpStatus.OK);
    }

    @PostMapping("/board")
    public ResponseEntity<?> saveBoard(@RequestBody ConcurrentHashMap<String,String> boardMap){
        Long savedId = boardService.saveBoard(boardMap);
        return new ResponseEntity<>(new Result<>("글 등록 완료", savedId), HttpStatus.CREATED);
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<?> findById(@PathVariable("id")Long id){
        BoardDto board = boardService.findBoardByBoardId(id);
        return new ResponseEntity<>(new Result<>("해당 번호에 해당하는 글", board), HttpStatus.OK);
    }

    //멘티의 (완료된 멘토링 목록)
    @GetMapping("/board")
    public ResponseEntity<?> findBoardByUserId(@RequestParam("userId")Long userId){
        List<BoardMenteeDto> boards = boardService.findBoardByUserId(userId);
        return new ResponseEntity<>(new Result<>("해당 멘티의 완료된 멘토링 목록", boards), HttpStatus.OK);
    }

    //멘티의 (모든 목록)
    @GetMapping("/board/mentee/{menteeId}")
    public ResponseEntity<?> findBoardByMenteeId(@PathVariable("menteeId")Long MenteeId){
        List<BoardMenteeDto> boards = boardService.findBoardByMenteeId(MenteeId);
        return new ResponseEntity<>(new Result<>("해당 멘티가 신청한 모든 글 목록", boards), HttpStatus.OK);
    }

    @GetMapping("/board/mentor/{mentorId}")
    public ResponseEntity<?> findBoardByMentorId(@PathVariable("mentorId")Long mentorId){
        List<BoardDto> boards = boardService.findBoardByMentorId(mentorId);
        return new ResponseEntity<>(new Result<>("해당 멘토에 대한 모든 글", boards), HttpStatus.OK);
    }

    // 진짜로 삭제한다.
    @DeleteMapping("/board/{id}")
    public Long deleteBoard(@PathVariable("id")Long id){
        return boardService.delete(id);
    }

    // boardId로 글찾은 후 수정하기
    @PatchMapping("/board/edit/{id}")
    public Long updateBoard(@PathVariable("id")Long id,@RequestBody BoardDto boardDto){
        return boardService.updateBoard(id, boardDto);
    }

    @GetMapping("/board/search")
    public ResponseEntity<?> searchWithCondition(@ModelAttribute("SearchCondition")SearchCondition searchCondition){
        List<BoardDto> boards = boardService.searchWithCondition(searchCondition);
        return new ResponseEntity<>(new Result<>("글 검색 결과", boards), HttpStatus.OK);
    }

    @GetMapping("/board/latest")
    public ResponseEntity<?> latestBoard(){
        List<BoardDto> boards = boardService.latestBoard();
        return new ResponseEntity<>(new Result<>("최신 글 목록", boards), HttpStatus.OK);
    }


}
