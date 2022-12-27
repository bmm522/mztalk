package com.mztalk.auction.controller;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.mztalk.auction.domain.Result;
import com.mztalk.auction.domain.dto.*;
import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.domain.entity.Comment;
import com.mztalk.auction.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequestMapping("/auction")
@RestController
public class AuctionController {

    @Autowired
    private AuctionService auctionService;
    @ResponseBody


    //게시글 작성
    @PostMapping("/board")
    public ConcurrentHashMap<String, String> insertBoard(@RequestBody BoardRequestDto boardRequestDto) throws ParseException {
        ConcurrentHashMap<String, String> map=new ConcurrentHashMap<>();
        map.put("bId", String.valueOf(auctionService.insertBoard(boardRequestDto)));
        System.out.println(map.get("bId"));
        return map;
    }

    //게시글 수정
    @PatchMapping("/board/{bId}")
    public int updateBoard(@PathVariable Long bId, @RequestBody BoardEditDto boardEditDto) {
        return auctionService.updateBoard(bId, boardEditDto);
    }

    //게시글 검색
    @GetMapping("/board/keyword/{keyword}")
    public Result<?> searchBoard(@PathVariable String keyword) throws ParseException, UnsupportedEncodingException {
        System.out.println(keyword);
        return auctionService.searchBoard(keyword);
    }

    //전체 게시글 목록
    @GetMapping("/board")
    public Result<?> selectBoardList() throws ParseException {
        return auctionService.selectBoardList();
    }

    //게시물 디테일
    @GetMapping("/board/{bId}")
    public BoardDetailResponseDto selectBoard(@PathVariable Long bId) {
        auctionService.updateCount(bId); //조회수 증가

        return auctionService.selectBoard(bId);
    }

    //게시글 삭제
    @PatchMapping("/boardDelete/{bId}/{writer}")
    public int deleteBoard(@PathVariable Long bId, @PathVariable String writer) {

        return auctionService.deleteBoard(bId, writer);
    }

    //상세페이지 입찰가
    @PatchMapping("/board/price/{bId}")
    public int updatePrice(@PathVariable Long bId, @RequestBody BoardDto boardDto) {
        return auctionService.updatePrice(bId, boardDto);
    }

    //최신글 번호뽑아오기
    @GetMapping("/recent-board")
    public ConcurrentHashMap<String, String> getRecentBoardNo(){
//        System.out.println("bId: " + getRecentBoardNo().get("bId"));
        return auctionService.getRecentBoardNo();

    }

    //이미지
    @GetMapping("/images/{bId}")
    public void setImage(@PathVariable("bId")long bId){
        System.out.println("bId : " + bId );
    }

    //댓글 작성
    @PostMapping("/comment")
    public Comment insertComment(@RequestBody CommentRequestDto commentRequestDto) {
        return auctionService.insertComment(commentRequestDto);
    }

    //댓글 수정
    @PatchMapping("/comment/{cId}")
    public int updateComment(@PathVariable Long cId, @RequestBody CommentDto commentDto) {
        return auctionService.updateComment(cId, commentDto);
    }

    //댓글 삭제
    @PatchMapping("/deleteComment/{cId}")
    public int deleteComment(@PathVariable Long cId, @RequestBody CommentDto commentDto) {
        return auctionService.deleteComment(cId, commentDto);
    }

    //댓글 전체 조회
    @GetMapping("/comment")
    public Result<?> selectCommentList() {
        return auctionService.selectCommentList();
    }









}
