package com.mztalk.bung.controller;

import com.mztalk.bung.domain.dto.BungAddBoardDto;
import com.mztalk.bung.domain.dto.BungBoardDto;
import com.mztalk.bung.service.BungBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.mztalk.bung.domain.entity.Result;

import javax.ws.rs.Path;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bung")
public class BungBoardApiController {

    private final BungBoardService bungBoardService;

    // 게시글 전체 조회
    @GetMapping("/mainBoards")
    public Result mainSelectList() {
        return bungBoardService.mainSelectList();
    }

    // 게시글 등록
    @ResponseBody
    @PostMapping("/mainInsertBoard")
    public Long mainInsertBoard(@RequestBody BungBoardDto bungBoardDto) {
        return bungBoardService.mainInsertBoard(bungBoardDto);
    }

    // 게시글 수정
    @ResponseBody
    @PatchMapping("/mainBoardUpdate/{boardId}")
    public Long mainBoardUpdate(@PathVariable("boardId") Long bId, @RequestBody BungBoardDto bungBoardDto) {
        return bungBoardService.mainBoardUpdate(bId, bungBoardDto);
    }

    // 게시글 삭제
    @ResponseBody
    @PatchMapping("/mainBoardDelete/{boardId}")
    public Long mainBoardDelete(@PathVariable("boardId") Long bId) {
        return bungBoardService.mainBoardDelete(bId);
    }

    // 게시글 상세보기
    @GetMapping("/mainBoardSelect/{boardId}")
    public BungBoardDto mainBoardSelect(@PathVariable("boardId") Long bId) {
        bungBoardService.increaseCount(bId);
        return bungBoardService.mainBoardSelect(bId);
    }
    // 벙 신청 게시글 작성
    @ResponseBody
    @PostMapping("/addBungBoard")
    public Long addBungBoard(@RequestBody BungAddBoardDto bungAddBoardDto) {
        return bungBoardService.addBungBoard(bungAddBoardDto);
    }

    @GetMapping("/recent-board")
    public ConcurrentHashMap<String, String> getRecentBoardNo(){
        return bungBoardService.getRecentBoardNo();
    }
}