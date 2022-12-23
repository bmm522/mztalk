package com.mztalk.bung.controller;

import com.mztalk.bung.domain.dto.BungAddBoardDto;
import com.mztalk.bung.domain.dto.BungBoardDto;
import com.mztalk.bung.domain.response.BungBoardDetailResponseDto;
import com.mztalk.bung.service.BungBoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.mztalk.bung.domain.entity.Result;

import javax.ws.rs.DELETE;
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
        // 현재인원 실시간 체크 메소드
//        Long bId = bungBoardDto.getBoardId();
//
//        System.out.println(bId);
//        Long nowGroup = bungBoardService.bungBoardNowGroup(bId);
//        System.out.println(nowGroup);

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
    public BungBoardDetailResponseDto mainBoardSelect(@PathVariable("boardId") String bId) {
        bungBoardService.increaseCount(Long.parseLong(bId));
        return bungBoardService.mainBoardSelect(Long.parseLong(bId));
    }

    // 벙 신청 게시글 작성
    @ResponseBody
    @PostMapping("/bungAddBoard")
    public Long addBungBoard(@RequestBody ConcurrentHashMap<String, String> bungAddBoardMap) {
        return bungBoardService.addBungBoard(bungAddBoardMap);
    }

    // 벙 신청 게시글 리스트 조회
    @GetMapping("/bungAddBoards")
    public Result addBungBoardsList() {
        return bungBoardService.addBungBoardsList();
    }

    // 벙 신청 게시글 수정
    @ResponseBody
    @PatchMapping("/bungAddBoardUpdate/{addId}")
    public Long addBungBoardUpdate(@PathVariable("addId") Long addId, @RequestBody BungAddBoardDto bungAddBoardDto) {
        return bungBoardService.addBungBoardUpdate(addId, bungAddBoardDto);
    }

    // 벙 신청 게시글 삭제
    @DeleteMapping("/bungAddBoardDelete/{addId}")
    public Long addBungBoardDelete(@PathVariable("addId") Long addId) {
        return bungBoardService.addBungBoardDelete(addId);
    }

    // 벙 게시글 작성자가 신청 게시글 수락
    @ResponseBody
    @PatchMapping("/bungAddBoardAccept/{addId}")
    public Long addBungBoardAccept(@PathVariable("addId") Long addId) {
        return bungBoardService.addBungBoardAccept(addId);
    }

    // 벙 신청 게시글 상세보기
    @GetMapping("/bungAddBoardSelect/{addId}")
    public BungAddBoardDto bungAddBoardSelect(@PathVariable("addId") Long addId) {
        return bungBoardService.bungAddBoardSelect(addId);
    }

    // 벙 게시글 수락된 신청자목록 보기 // 수정해야됨 Status Y인것들만 뽑아서 보이는 걸로
    @GetMapping("/bungRequestList/{boardId}")
    public Result bungRequestList(@PathVariable("boardId") Long bId) {
        return bungBoardService.bungRequestList(bId);
    }

    // 벙 게시물 현재인원 조회
    @GetMapping("bungBoardNowGroup/{boardId}")
    public Long bungBoardNowGroup(@PathVariable("boardId") Long bId) {
        return bungBoardService.bungBoardNowGroup(bId);
    }

    @GetMapping("/recent-board")
    public ConcurrentHashMap<String, String> getRecentBoardNo(){
        return bungBoardService.getRecentBoardNo();
    }
}