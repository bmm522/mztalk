package com.mztalk.auction.controller;

import com.mztalk.auction.domain.dto.BoardDto;
import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auction")
@RestController
public class AuctionController {
    @Autowired
    private AuctionService auctionService;


    @PostMapping("/board")
    public Long insertBoard(@RequestBody BoardDto boardDto){
//      System.out.println("요청 들어옴");

        return auctionService.insertBoard(boardDto);
    }

    @PatchMapping("/board/{bId}")
    public void updateBoard(@PathVariable Long bId, @RequestBody BoardDto boardDto) {
        return auctionService.updateBoard(bId, boardDto);
    }



}
