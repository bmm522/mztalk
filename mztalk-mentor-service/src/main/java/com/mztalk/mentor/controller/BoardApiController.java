package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.SearchCondition;
import com.mztalk.mentor.domain.dto.BoardDto;
import com.mztalk.mentor.domain.dto.BoardMenteeDto;
import com.mztalk.mentor.domain.dto.BoardReqDto;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 201, message = "CREATED"),
        @ApiResponse(code = 400, message = "BAD REQUEST"),
        @ApiResponse(code = 500, message = "SERVER ERROR")
})
@Api(tags = {"멘토링 글에 대한 정보를 제공하는 Controller"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors")
public class BoardApiController {

    private final BoardService boardService;

    @ApiOperation(value = "모든 글 리턴", notes = "결제정보가 없고, 멘토링 시작 전인 모든 글을 리턴하는 메소드입니다.", response = Result.class)
    @GetMapping("/boards")
    public ResponseEntity<?> findNullPaymentWithBeforeMentoringDate(){
        List<BoardDto> boards = boardService.findNullPaymentWithBeforeMentoringDate();
        return new ResponseEntity<>(new Result<>("모든 글 목록", boards), HttpStatus.OK);
    }

    @ApiOperation(value = "글 저장", notes = "글을 저장하는 메소드입니다.", response = Result.class)
    @PostMapping("/board")
    public ResponseEntity<?> saveBoard(@RequestBody BoardReqDto boardReqDto){
        Long savedId = boardService.saveBoard(boardReqDto);
        return new ResponseEntity<>(new Result<>("글 등록 완료", savedId), HttpStatus.CREATED);
    }

    @ApiOperation(value = "글 정보 리턴", notes = "해당 번호에 해당하는 글을 리턴하는 메소드입니다.", response = Result.class)
    @GetMapping("/board/{id}")
    public ResponseEntity<?> findById(@PathVariable("id")Long id){
        BoardDto board = boardService.findBoardByBoardId(id);
        return new ResponseEntity<>(new Result<>("해당 번호에 해당하는 글", board), HttpStatus.OK);
    }

    //멘티의 (완료된 멘토링 목록)
    @ApiOperation(value = "멘티의 완료된 글 리턴", notes = "해당번호에 해당하는 멘티의 완료된 글을 리턴하는 메소드입니다.", response = Result.class)
    @GetMapping("/board")
    public ResponseEntity<?> findBoardByUserId(@RequestParam("userId")Long userId){
        List<BoardMenteeDto> boards = boardService.findBoardByUserId(userId);
        return new ResponseEntity<>(new Result<>("해당 멘티의 완료된 멘토링 목록", boards), HttpStatus.OK);
    }

    //멘티의 (모든 목록)
    @ApiOperation(value = "멘티의 모든 글 리턴", notes = "해당번호에 해당하는 멘티가 신청한 모든 글을 리턴하는 메소드입니다.", response = Result.class)
    @GetMapping("/board/mentee/{menteeId}")
    public ResponseEntity<?> findBoardByMenteeId(@PathVariable("menteeId")Long MenteeId){
        List<BoardMenteeDto> boards = boardService.findBoardByMenteeId(MenteeId);
        return new ResponseEntity<>(new Result<>("해당 멘티가 신청한 모든 글 목록", boards), HttpStatus.OK);
    }

    @ApiOperation(value = "멘토의 모든 글 리턴", notes = "해당번호의 멘토가 작성한 모든글을 리턴하는 메소드입니다.", response = Result.class)
    @GetMapping("/board/mentor/{mentorId}")
    public ResponseEntity<?> findBoardByMentorId(@PathVariable("mentorId")Long mentorId){
        List<BoardDto> boards = boardService.findBoardByMentorId(mentorId);
        return new ResponseEntity<>(new Result<>("해당 멘토에 대한 모든 글", boards), HttpStatus.OK);
    }

    // 진짜로 삭제한다.
    @ApiOperation(value = "글 삭제", notes = "해당번호의 글을 삭제하는 메소드입니다.", response = Long.class)
    @DeleteMapping("/board/{id}")
    public Long deleteBoard(@PathVariable("id")Long id){
        return boardService.delete(id);
    }

    // boardId로 글찾은 후 수정하기
    @ApiOperation(value = "글 수정", notes = "해당번호의 글을 수정하는 메소드입니다.", response = Long.class)
    @PatchMapping("/board/edit/{id}")
    public Long updateBoard(@PathVariable("id")Long id,@RequestBody BoardDto boardDto){
        return boardService.updateBoard(id, boardDto);
    }

    @ApiOperation(value = "글 검색", notes = "검색조건에 맞는 글을 리턴하는 메소드입니다.", response = Result.class)
    @GetMapping("/board/search")
    public ResponseEntity<?> searchWithCondition(@ModelAttribute("SearchCondition")SearchCondition searchCondition){
        List<BoardDto> boards = boardService.searchWithCondition(searchCondition);
        return new ResponseEntity<>(new Result<>("글 검색 결과", boards), HttpStatus.OK);
    }

    @ApiOperation(value = "최신 글 리턴", notes = "작성된 글을 최신순으로 리턴하는 메소드입니다.", response = Result.class)
    @GetMapping("/board/latest")
    public ResponseEntity<?> latestBoard(){
        List<BoardDto> boards = boardService.latestBoard();
        return new ResponseEntity<>(new Result<>("최신 글 목록", boards), HttpStatus.OK);
    }


}
