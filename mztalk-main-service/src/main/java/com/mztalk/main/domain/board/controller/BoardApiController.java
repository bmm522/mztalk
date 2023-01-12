package com.mztalk.main.domain.board.controller;

import com.mztalk.main.common.CMRespDto;
import com.mztalk.main.common.Result;
import com.mztalk.main.domain.board.dto.BoardNicknameModifyDto;
import com.mztalk.main.domain.board.dto.BoardRequestDto;
import com.mztalk.main.domain.board.dto.BoardResponseDto;
import com.mztalk.main.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/story")
public class BoardApiController {

    private final BoardService boardService;


    //퍼블릭인 글 목록
    @GetMapping("/{own}/{page}")
    public Result<?> findByStatusOrderByBoardIdDesc(@PathVariable("own") Long own, @PathVariable("page")int page){
        return boardService.findByStatusOrderByBoardIdDesc(own, page);
    }

    //글쓰기
    @ResponseBody
    @PostMapping ("/saveForm")
    public ResponseEntity<?> saveForm(@RequestBody BoardRequestDto boardRequestDto){
        Long savedId =  boardService.save(boardRequestDto);
        return new ResponseEntity<>(new CMRespDto<>(1, "글쓰기성공", savedId), HttpStatus.CREATED);
    }

    //글수정
    @PatchMapping("/update/{id}")
    public Long updateForm(@PathVariable("id") Long id, @RequestBody BoardResponseDto boardResponseDto) {return boardService.updateBoard(id, boardResponseDto);}

    //글삭제
    @PatchMapping("/delete/{id}")
    public Long deleteForm(@PathVariable("id") Long id){return boardService.deleteBoard(id);}

    //메인페이지 뿌리기?
    @GetMapping("/main/{own}/{page}")
    public Result findAllByBoardStory(@PathVariable("own") Long own, @PathVariable("page")int page){
        return boardService.findAllByBoardStory(own, page);
    }

    @PatchMapping("/board/nickname")
    public ResponseEntity<?> modifyNickname(@RequestBody BoardNicknameModifyDto boardNicknameModifyDto){
        return new ResponseEntity<>(new CMRespDto<>(1,"이름변경성공", boardService.modifyNickname(boardNicknameModifyDto)), HttpStatus.OK);
    }

}
