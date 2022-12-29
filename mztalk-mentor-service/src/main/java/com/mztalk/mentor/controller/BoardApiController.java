package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.SearchCondition;
import com.mztalk.mentor.domain.dto.BoardDto;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors")
public class BoardApiController {

    private final BoardService boardService;

    @GetMapping("/boards")
    public Result findNullPaymentWithBeforeMentoringDate(){
        return boardService.findNullPaymentWithBeforeMentoringDate();
    }

    @PostMapping("/board")
    public Long saveBoard(@RequestBody ConcurrentHashMap<String,String> boardMap){
        return boardService.saveBoard(boardMap);
    }

    @GetMapping("/board/{id}")
    public BoardDto findById(@PathVariable("id")Long id){
        return boardService.findBoardByBoardId(id);
    }

    //멘티가 본인이 신청한 멘토링 글에 대해 보는 메소드 멘토링 이후의 글만 출력되게 한다.(완료된 멘토링 목록)
    @GetMapping("/board")
    public Result findBoardByUserId(@RequestParam("userId")Long userId){
        return boardService.findBoardByUserId(userId);
    }

    //멘티가 본인이 신청한 멘토링 글에 대해 보는 메소드.
    @GetMapping("/board/mentee/{menteeId}")
    public Result findBoardByMenteeId(@PathVariable("menteeId")Long MenteeId){
        return boardService.findBoardByMenteeId(MenteeId);
    }

    @GetMapping("/board/mentor/{mentorId}")
    public Result findBoardByMentorId(@PathVariable("mentorId")Long mentorId){
        return boardService.findBoardByMentorId(mentorId);
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
    public Result searchWithCondition(@ModelAttribute("SearchCondition")SearchCondition searchCondition){
        return boardService.searchWithCondition(searchCondition);
    }

    @GetMapping("/board/latest")
    public Result latestBoard(){
        return boardService.latestBoard();
    }

//        return new ResponseEntity <> (boards, HttpStatus.OK);


}
