package com.mztalk.main.controller;

import com.mztalk.main.domain.dto.BoardDto;
import com.mztalk.main.domain.entity.Result;
import com.mztalk.main.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/story")
public class BoardApiController {

    private final BoardService boardService;

    //개인페이지
    @GetMapping("/story/{own}")
    public Result findAll(@PathVariable("own")Long own){
        return boardService.findAll();
    }

    //글쓰기
    @PostMapping ("/story/saveForm")
    public Long saveForm(@RequestBody BoardDto boardDto){
        return boardService.save(boardDto);
    }

    //글수정
    @PatchMapping("/story/update{id}")
    public Long updateForm(@PathVariable("id") Long id, @RequestBody BoardDto boardDto) {

        return boardService.updateBoard(id, boardDto);
    }
}
