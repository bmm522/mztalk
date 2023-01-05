package com.mztalk.bung.controller;

import com.mztalk.bung.domain.dto.BungAddBoardDto;
import com.mztalk.bung.domain.dto.BungBoardDto;
import com.mztalk.bung.domain.response.BungAddRequestDto;
import com.mztalk.bung.domain.response.BungBoardDetailResponseDto;
import com.mztalk.bung.domain.response.ResponseDto;
import com.mztalk.bung.service.BungBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.mztalk.bung.domain.entity.Result;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bung")
public class BungBoardApiController {

    private final BungBoardService bungBoardService;

    // 게시글 전체 조회
    @GetMapping("/mainBoards/{page}")
    public Result<?> mainSelectList(@PathVariable("page")int page) {
        return bungBoardService.mainSelectList(page);
    }

    @GetMapping("/mainBoards-main/{page}")
    public Result<?> mainSelectListOfMain(@PathVariable("page")int page) {
        return bungBoardService.mainSelectListOfMain(page);
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
    public BungBoardDetailResponseDto mainBoardSelect(@PathVariable("boardId") String bId) {
        bungBoardService.increaseCount(Long.parseLong(bId));
        return bungBoardService.mainBoardSelect(Long.parseLong(bId));
    }

    // 벙 신청 게시글 연결 기능
    @GetMapping("bungAddBoardRequest/{boardId}")
    public Long addBoardRequest(@PathVariable("boardId") Long bId) {
        return bungBoardService.findBungBoard(bId);
    }

    // 벙 신청 게시글 작성
    @ResponseBody
    @PostMapping("/bungAddBoard")
    public BungAddRequestDto addBungBoard(@RequestBody ConcurrentHashMap<String, String> bungAddBoardMap) {
        return bungBoardService.addBungBoard(bungAddBoardMap);
    }

    // 벙 신청 게시글 리스트 조회
    @GetMapping("/bungAddBoards/{boardWriter}")
    public Result addBungBoardsList(@PathVariable("boardWriter") String boardWriter) {
        return bungBoardService.addBungBoardsList(boardWriter);
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
    public BungAddRequestDto addBungBoardAccept(@PathVariable("addId") Long addId) {
        return bungBoardService.addBungBoardAccept(addId);
    }

    // 벙 신청 게시글 상세보기
    @GetMapping("/bungAddBoardSelect/{addId}")
    public BungAddBoardDto bungAddBoardSelect(@PathVariable("addId") Long addId) {
        return bungBoardService.bungAddBoardSelect(addId);
    }

    // 벙 게시글 수락된 신청자목록 보기
    @GetMapping("/bungRequestList/{boardId}")
    public Result bungRequestList(@PathVariable("boardId") Long bId) {
        return bungBoardService.bungRequestList(bId);
    }

    // 벙 게시물 현재인원 조회
    @GetMapping("/bungBoardNowGroup/{boardId}")
    public Long bungBoardNowGroup(@PathVariable("boardId") Long bId) {
        return bungBoardService.bungBoardNowGroup(bId);
    }

    // 벙 게시물 카테고리, 검색어 검색기능
//    @GetMapping("/bungBoardSearch")
//    public Result bungBoardSearch(@ModelAttribute("searchKeyWord") SearchKeyWord searchKeyWord) {
//        return bungBoardService.bungBoardSearch(searchKeyWord);
//    }
    // 벙 게시물 카테고리, 검색어 검색기능2
    @GetMapping("/search")
    public Result<?> bungBoardSearch(@RequestParam(value="category")String[] categories, @RequestParam("type")String type, @RequestParam("searchText")String searchText, @RequestParam("page")int page){
        return bungBoardService.search(categories, type, searchText, page);
    }


//    @GetMapping("/bungBoardSearch")
//    public Result bungBoardSearch(@RequestParam("category")String category, @RequestParam("boardTitle")String boardTitle) {
//       return bungBoardService.bungBoardSearch(new SearchKeyWord(category, boardTitle));
//        return null;
//    }

    // 벙 게시물 현인원 추방 기능
    @DeleteMapping("/bungAddBoardGroupDrop/{boardId}/{addId}")
    public Long bungAddBoardGroupDrop(@PathVariable("boardId") Long bId, @PathVariable("addId") Long aId) {
        return bungBoardService.bungAddBoardGroupDrop(bId, aId);
    }

    // 벙 게시글 작성 연결 기능
    @GetMapping("/recent-board")
    public ConcurrentHashMap<String, String> getRecentBoardNo(){
        return bungBoardService.getRecentBoardNo();
    }

    // 벙 신청 게시글 거절 기능
    @DeleteMapping("/addBungRefuse/{addId}")
    public BungAddRequestDto addBungRefuse(@PathVariable("addId") Long addId) {
        return bungBoardService.addBungRefuse(addId);
    }

    @GetMapping("/accept")
    public Result<?> getAcceptList(@RequestParam("nickname")String nickname){
        return bungBoardService.getAcceptList(nickname);
    }
}