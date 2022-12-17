package com.mztalk.main.domain.board.controller;

import com.mztalk.main.domain.board.dto.BoardDto;
import com.mztalk.main.common.Result;
import com.mztalk.main.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/story")
public class BoardApiController {

    private final BoardService boardService;


    //글목록
    @GetMapping("/{own}")
    public Result findAllByOwn(@PathVariable Long own){
        return boardService.findAllByOwn(own);
    }


    //글쓰기
    @ResponseBody
    @PostMapping ("/saveForm")
    public Long saveForm(@RequestBody BoardDto boardDto){

        System.out.println("!!!!!!!!");
        System.out.println(boardDto);

        return boardService.save(boardDto);
    }

    //글수정
    @PatchMapping("/update/{id}")
    public Long updateForm(@PathVariable("id") Long id, @RequestBody BoardDto boardDto) {

        return boardService.updateBoard(id, boardDto);
    }

    //글삭제
    @PatchMapping("/delete/{id}")
    public Long deleteForm(@PathVariable("id") Long id, @RequestBody BoardDto boardDto){

        return boardService.deleteBoard(id, boardDto);
    }

}
