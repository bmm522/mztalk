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
//    @GetMapping("/story/{own}")
//    public Result findAll(@PathVariable("own")Long own){
//        return boardService.findAll();
//    }
    //글목록
    @GetMapping("/{own}")
    public Result findAllByOwn(@PathVariable("own")Long own){
        return boardService.findAllByOwn(own);
    }


    //글쓰기
    @ResponseBody
    @PostMapping ("/write")
    public Long saveForm(@RequestBody BoardDto boardDto){
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
