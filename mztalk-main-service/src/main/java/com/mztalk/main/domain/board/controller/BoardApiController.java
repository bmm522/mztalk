package com.mztalk.main.domain.board.controller;

import com.mztalk.main.common.CMRespDto;
import com.mztalk.main.domain.board.Board;
import com.mztalk.main.domain.board.dto.BoardDto;
import com.mztalk.main.common.Result;
import com.mztalk.main.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;


@RestController
@RequiredArgsConstructor
@RequestMapping("/story")
public class BoardApiController {

    private final BoardService boardService;


    //퍼블릭인 글 목록
    @GetMapping("/{own}/{page}")
    public Result findAllByOwn(@PathVariable("own") Long own, @PathVariable("page")int page){
        return boardService.findAllByOwn(own, page);
    }

    //글쓰기
    @ResponseBody
    @PostMapping ("/saveForm")
    public ResponseEntity<?> saveForm(@RequestBody BoardDto boardDto){
        Board board = boardService.save(boardDto);
        return new ResponseEntity<>(new CMRespDto<>(1, "글쓰기성공", board), HttpStatus.CREATED);
    }

    //글수정
    @PatchMapping("/update/{id}")
    public Long updateForm(@PathVariable("id") Long id, @RequestBody BoardDto boardDto) {return boardService.updateBoard(id, boardDto);}

    //글삭제
    @PatchMapping("/delete/{id}")
    public Long deleteForm(@PathVariable("id") Long id){return boardService.deleteBoard(id);}

    //메인페이지 뿌리기?
    @GetMapping("/main/{own}/{page}")
    public Result findAllByBoardStory(@PathVariable("own") Long own, @PathVariable("page")int page){
        return boardService.findAllByBoardStory(own, page);
    }


//    @GetMapping("/nameCheck/{nickname}")
//    public Long findByUserNo(@PathVariable("nickname") String nickname ){
//
//        Long own = boardService.findByUserNo(nickname);
//
//        return null;
//    }



}
