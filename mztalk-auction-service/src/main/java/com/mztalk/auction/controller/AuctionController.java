package com.mztalk.auction.controller;

import com.mztalk.auction.domain.Result;
import com.mztalk.auction.domain.dto.*;
import com.mztalk.auction.domain.dto.board.*;
import com.mztalk.auction.domain.dto.comment.CommentRequestDto;
import com.mztalk.auction.domain.dto.comment.CommentResponseDto;
import com.mztalk.auction.domain.dto.comment.CommentUpdateRequestDto;
import com.mztalk.auction.service.AuctionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.concurrent.ConcurrentHashMap;

@Api(tags = "Auction Service")
@RequestMapping("/auction")
@RestController
public class AuctionController {

    @Autowired
    private AuctionService auctionService;



    //게시글 작성
    @ApiOperation(value = "게시글 작성")
    @PostMapping("/board")
    public Long insertBoard(@RequestBody BoardRequestDto boardRequestDto) throws ParseException {
        return auctionService.insertBoard(boardRequestDto);
    }

    //게시글 수정
    @ApiOperation(value = "게시글 수정")
    @PatchMapping("/board/{bId}")
    public int updateBoard(@PathVariable Long bId, @RequestBody BoardEditDto boardEditDto) {
        return auctionService.updateBoard(bId, boardEditDto);
    }

    //게시글 검색
    @ApiOperation(value = "게시글 검색")
    @GetMapping("/board/keyword/{keyword}/{page}")
    public Result<?> searchBoard(@PathVariable("keyword") String keyword,@PathVariable("page")int page) throws ParseException, UnsupportedEncodingException {

        return auctionService.searchBoard(keyword, page);
    }

    //전체 게시글 목록
    @ApiOperation(value = "전체 게시글 목록")
    @GetMapping("/board/page/{page}")
    public Result<?> selectBoardList(@PathVariable("page") int page) throws ParseException {
        return auctionService.selectBoardList(page);
    }

    //페이징 처리
//    @ApiOperation(value = "페이징 처리")
//    @GetMapping("/board-front/{page}")
//    public Result<?> selectBoardListOfFront(@PathVariable("page") int page) throws ParseException {
//        return auctionService.selectBoardListOfFront(page);
//    }

    //입찰 마감 게시물 제외
    @ApiOperation(value = "입찰 마감 게시물 제외")
    @GetMapping("/board/close/{page}")
    public Result<?> selectCloseBoardList(@PathVariable int page) throws ParseException {
        return auctionService.selectCloseBoardList(page);
    }

    //게시물 디테일
    @ApiOperation(value = "게시글 상세 보기")
    @GetMapping("/board/{bId}/{writerId}")
    public BoardDetailResponseDto selectBoard(@PathVariable Long bId, @PathVariable Long writerId) {
        auctionService.updateCount(bId, writerId); //조회수 증가
        return auctionService.selectBoard(bId);
    }

    //게시글 삭제
    @ApiOperation(value = "게시글 삭제")
    @PatchMapping("/boardDelete/{bId}/{writer}")
    public int deleteBoard(@PathVariable Long bId, @PathVariable String writer) {

        return auctionService.deleteBoard(bId, writer);
    }


    //상세페이지 입찰가
    @ApiOperation(value = "입찰 기능")
    @PatchMapping("/board/price")
    public BoardPriceDto updatePrice(@RequestBody BoardPriceDto boardPriceDto) {
        return auctionService.updatePrice(boardPriceDto);
    }

    //최신글 번호뽑아오기
    @GetMapping("/recent-board")
    public ConcurrentHashMap<String, String> getRecentBoardNo(){
        return auctionService.getRecentBoardNo();

    }

//    //이미지
//
//    @GetMapping("/images/{bId}")
//    public void setImage(@PathVariable("bId")long bId){
//        System.out.println("bId : " + bId );
//    }

    //댓글 작성
    @ApiOperation(value = "댓글 작성")
    @PostMapping("/comment")
    public CommentResponseDto insertComment(@RequestBody CommentRequestDto commentRequestDto) {
        return auctionService.insertComment(commentRequestDto);
    }

    //댓글 수정글
    @ApiOperation(value = "댓글 수정")
    @PatchMapping("/comment/{cId}")
    public CommentResponseDto updateComment(@PathVariable Long cId, @RequestBody CommentUpdateRequestDto commentUpdateRequestDto) {
        return auctionService.updateComment(cId, commentUpdateRequestDto);
    }

    //댓글 삭제
    @ApiOperation(value = "댓글 삭제")
    @PatchMapping("/deleteComment/{cId}")
    public int deleteComment(@PathVariable Long cId) {
        return auctionService.deleteComment(cId);
    }

    //댓글 전체 조회
    @ApiOperation(value = "댓글 전체 조회")
    @GetMapping("/comment/{bId}")
    public Result<?> selectCommentList(@PathVariable Long bId) {
        return auctionService.selectCommentList(bId);
    }

    //특정 댓글 조회
    @ApiOperation(value = "특정 댓글 조회")
    @GetMapping("selectComment/{cId}")
    public CommentResponseDto selectComment(@PathVariable Long cId) {
        return auctionService.selectComment(cId);
    }

    //지금 마감시키기
    @ApiOperation(value = "사용자가 게시글을 마감")
    @PatchMapping("/board/close")
    public int closeBoard(@RequestBody BoardCloseDto boardCloseDto) {
        System.out.println("close요청들어옴");
        return auctionService.closeBoard(boardCloseDto);
    }

    //닉네임 변경 시
    @ApiOperation(value = "닉네임 변경 시 게시글에도 적용")
    @PatchMapping("/nickname")
    public void changedNickname(@RequestBody ChangedNicknameDto changedNicknameDto){
        auctionService.changedNickname(changedNicknameDto);
    }

    //입찰 현황 데이터 받아오기
    @ApiOperation(value = "입찰 현황 데이터 저장")
    @GetMapping("/currentPrice/{bId}")
    public Result<?> getCurrentPrice(@PathVariable("bId") Long bId) {
        return auctionService.getCurrentPrice(bId);
    }


}
