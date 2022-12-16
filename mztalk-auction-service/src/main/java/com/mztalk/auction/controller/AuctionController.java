package com.mztalk.auction.controller;

import com.mztalk.auction.domain.dto.BoardRequestDto;
import com.mztalk.auction.domain.dto.BoardDto;
import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RequestMapping("/auction")
@RestController
public class AuctionController {
    @Autowired
    private AuctionService auctionService;

    @ResponseBody
    //게시글 작성
    @PostMapping("/board")
    public ConcurrentHashMap<String, String> insertBoard(@RequestBody BoardRequestDto boardRequestDto){
        ConcurrentHashMap<String, String> map=new ConcurrentHashMap<>();
        map.put("bId", String.valueOf(auctionService.insertBoard(boardRequestDto)));
        return map;
    }

    //게시글 수정
    @PatchMapping("/board/{bId}")
    public int updateBoard(@PathVariable Long bId, @RequestBody BoardDto boardDto) {
        return auctionService.updateBoard(bId, boardDto);
    }

    //전체 게시글 목록
    @GetMapping("/board")
    public List<Board> selectBoardList() {
        return auctionService.selectBoardList();
    }

    //특정 게시물 조회
    @GetMapping("/board/{bId}")
    public Board selectBoard(@PathVariable Long bId) {
        auctionService.updateCount(bId); //조회수 증가
        return auctionService.selectBoard(bId);
    }

    //게시글 삭제
    @PatchMapping("/boardDelete/{bId}")
    public int deleteBoard(@PathVariable Long bId) {
        return auctionService.deleteBoard(bId);
    }

    //상세페이지 입찰가
    @PostMapping("/board/{bId}")
    public int updatePrice(@PathVariable Long bId, BoardDto boardDto) {
        return auctionService.updatePrice(bId, boardDto);
    }

    // 최신글 번호뽑아오기
    @GetMapping("/recent-board")
    public ConcurrentHashMap<String, String> getRecentBoardNo(){
        return auctionService.getRecentBoardNo();
    }

    @GetMapping("/images/{bId}")
    public void setImage(@PathVariable("bId")long bId){
        System.out.println("bId : " + bId );
    }





}
