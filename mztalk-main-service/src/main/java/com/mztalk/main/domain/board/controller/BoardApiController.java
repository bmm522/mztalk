package com.mztalk.main.domain.board.controller;

import com.mztalk.main.common.CMRespDto;
import com.mztalk.main.domain.board.Board;
import com.mztalk.main.domain.board.dto.BoardDto;
import com.mztalk.main.common.Result;
import com.mztalk.main.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> saveForm(@RequestBody BoardDto boardDto){

        Board board = boardService.save(boardDto);

        return new ResponseEntity<>(new CMRespDto<>(1, "댓글쓰기성공", board), HttpStatus.CREATED);
    }

    //글수정
    @PatchMapping("/update/{id}")
    public Long updateForm(@PathVariable("id") Long id, @RequestBody BoardDto boardDto) {

        return boardService.updateBoard(id, boardDto);
    }

    //글삭제
    @PatchMapping("/delete/{id}")
    public Long deleteForm(@PathVariable("id") Long id){

        return boardService.deleteBoard(id);
    }

}
