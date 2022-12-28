package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.SearchCondition;
import com.mztalk.mentor.domain.dto.BoardDto;
import com.mztalk.mentor.domain.dto.MyBoardDto;
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

    //멘티가 본인이 신청한 멘토링 글에 대해 보는 메소드.
    @GetMapping("/board")
    public Result findBoardByUserId(@RequestParam("userId")Long userId){
        return boardService.findBoardByUserId(userId);
    }

    @GetMapping("/board/mentor/{mentorId}")
    public Result findBoardByMentorId(@PathVariable("mentorId")Long mentorId){
        return boardService.findBoardByMentorId(mentorId);
    }

    @DeleteMapping("/board/{id}") // 진짜로 삭제한다.
    public Long deleteBoard(@PathVariable("id")Long id){
        return boardService.delete(id);
    }

    @PatchMapping("/board/edit/{id}") //boardId로 글찾기
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

    @GetMapping("/board/notdeadline")
    public Result findByMentoringDateAfter(){
        return boardService.findByMentoringDateBefore();
    }

}
