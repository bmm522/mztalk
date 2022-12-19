package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.SearchCondition;
import com.mztalk.mentor.domain.dto.BoardDto;
import com.mztalk.mentor.domain.dto.BoardDto2;
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

    @PostMapping("/board")
    public Long saveBoard(@RequestBody ConcurrentHashMap<String,String> boardMap){
        return boardService.saveBoard(boardMap);
    }

    @GetMapping("/board/{id}")
    public BoardDto findById(@PathVariable("id")Long id){
        return boardService.findById(id);
    }

    //멘티가 본인이 신청한 멘토링 글을 보는 메소드
    @GetMapping("/board")
    public Result findBoardByUserId(@RequestParam("userId")Long userId){
        return boardService.findBoardByUserId(userId);
    }

    //멘토가 작성한 글이 있는지 확인하는 메소드
    @GetMapping("/board/mentor/{mentorId}")
    public boolean findBoardByMentorId(@PathVariable("mentorId")Long mentorId){
        return boardService.findBoardByMentorId(mentorId);
    }

    //멘토가 작성한 글만 가져오는 메소드
    @GetMapping("/board/mentor")
    public BoardDto2 getBoardByMentorId(@RequestParam("mentorId")Long mentorId){
        BoardDto2 findBoardDto = boardService.getBoardByMentorId(mentorId);
        return findBoardDto;
    }

    @GetMapping("/boards")
    public Result findAll(){
        return boardService.findAll();
    }

    @DeleteMapping("/board/delete/{id}") // 진짜로 삭제한다.
    public Long deleteBoard(@PathVariable("id")Long mentorId){
        return boardService.delete(mentorId);
    }

    @PatchMapping("/board/edit/{id}") //멘토 아이디로 글찾고 정보 수정
    public Long updateBoard(@PathVariable("id")Long id,@RequestBody BoardDto boardDto){
        return boardService.updateBoard(id, boardDto);
    }

    @GetMapping("/board/search")
    public Result searchWithCondition(@ModelAttribute("SearchCondition")SearchCondition searchCondition){
        return boardService.searchWithCondition(searchCondition);
    }



}
