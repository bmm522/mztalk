package com.mztalk.auction.controller;

import com.mztalk.auction.domain.dto.BoardDto;
import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@RequestMapping("/auction")
@RestController
public class AuctionController {
    @Autowired
    private AuctionService auctionService;

    //게시글 작성
    @PostMapping("/board")
    public Long insertBoard(@RequestBody BoardDto boardDto){
        return auctionService.insertBoard(boardDto);
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






}
