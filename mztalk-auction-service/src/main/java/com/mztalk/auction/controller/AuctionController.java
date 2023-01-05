package com.mztalk.auction.controller;

import com.mztalk.auction.domain.Result;
import com.mztalk.auction.domain.dto.*;
import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        System.out.println(boardRequestDto.getIsbn());
        return map;
    }

    //게시글 수정
    @PatchMapping("/board/{bId}")
    public int updateBoard(@PathVariable Long bId, @RequestBody BoardEditDto boardEditDto) {
        return auctionService.updateBoard(bId, boardEditDto);
    }

    //게시글 검색
    @GetMapping("/board/keyword/{keyword}/{page}")
    public Result<?> searchBoard(@PathVariable("keyword") String keyword,@PathVariable("page")int page) throws ParseException, UnsupportedEncodingException {

        return auctionService.searchBoard(keyword, page);
    }

    //전체 게시글 목록
    @GetMapping("/board/page/{page}")
    public Result<?> selectBoardList(@PathVariable("page") int page) throws ParseException {
        return auctionService.selectBoardList(page);
    }

    @GetMapping("/board-front/{page}")
    public Result<?> selectBoardListOfFront(@PathVariable("page") int page) throws ParseException {
        return auctionService.selectBoardListOfFront(page);
    }

    //입찰 마감 게시물 제외
    @GetMapping("/board/close/{page}")
    public Result<?> selectCloseBoardList(@PathVariable int page) throws ParseException {
        return auctionService.selectCloseBoardList(page);
    }

    //게시물 디테일
    @GetMapping("/board/{bId}/{writer}")
    public BoardDetailResponseDto selectBoard(@PathVariable Long bId, @PathVariable String writer) {
        auctionService.updateCount(bId, writer); //조회수 증가
        return auctionService.selectBoard(bId);
    }

    //게시글 삭제
    @PatchMapping("/boardDelete/{bId}/{writer}")
    public int deleteBoard(@PathVariable Long bId, @PathVariable String writer) {

        return auctionService.deleteBoard(bId, writer);
    }


    //상세페이지 입찰가
    @PatchMapping("/board/price")
    public BoardPriceDto updatePrice(@RequestBody BoardPriceDto boardPriceDto) {
        return auctionService.updatePrice(boardPriceDto);
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
    public CommentResponseDto insertComment(@RequestBody CommentRequestDto commentRequestDto) {
        return auctionService.insertComment(commentRequestDto);
    }

    //댓글 수정
    @PatchMapping("/comment/{cId}")
    public CommentResponseDto updateComment(@PathVariable Long cId, @RequestBody CommentUpdateRequestDto commentUpdateRequestDto) {
        return auctionService.updateComment(cId, commentUpdateRequestDto);
    }

    //댓글 삭제
    @PatchMapping("/deleteComment/{cId}")
    public int deleteComment(@PathVariable Long cId) {
        return auctionService.deleteComment(cId);
    }

    //댓글 전체 조회
    @GetMapping("/comment/{bId}")
    public Result<?> selectCommentList(@PathVariable Long bId) {
        return auctionService.selectCommentList(bId);
    }

    //특정 댓글 조회
    @GetMapping("selectComment/{cId}")
    public CommentResponseDto selectComment(@PathVariable Long cId) {
        return auctionService.selectComment(cId);
    }

    //지금 마감시키기
    @PatchMapping("/board/close")
    public int closeBoard(@RequestBody BoardCloseDto boardCloseDto) {
        System.out.println("close요청들어옴");
        return auctionService.closeBoard(boardCloseDto);
    }

    //입찰자와 채팅
    @PostMapping("/chat")
    public void chatConnection(BoardDto boardDto) {
        auctionService.postChatRoom(boardDto);
    }


}
