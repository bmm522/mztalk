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

    @PostMapping("/board")
    public Long saveBoard(@RequestBody ConcurrentHashMap<String,String> boardDto){
        return boardService.saveBoard(boardDto);
    }

    @GetMapping("/board/{id}")
    public BoardDto findById(@PathVariable("id")Long id){
        return boardService.findById(id);
    }

    @GetMapping("/boards")
    public Result findAll(){
        return boardService.findAll();
    }

    @PatchMapping("/board/{id}")
    public Long deleteBoard(@PathVariable("id")Long id){
        return boardService.delete(id);
    }

    @PatchMapping("/board/edit/{id}")
    public Long updateBoard(@PathVariable("id")Long id,@RequestBody BoardDto boardDto){
        return boardService.updateBoard(id, boardDto);
    }

    @GetMapping("/board/search")
    public Result searchWithCondition(@ModelAttribute("SearchCondition")SearchCondition searchCondition){
        return boardService.searchWithCondition(searchCondition);
    }



}
