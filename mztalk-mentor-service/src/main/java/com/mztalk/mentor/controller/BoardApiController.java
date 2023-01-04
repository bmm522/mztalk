package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.SearchCondition;
import com.mztalk.mentor.domain.dto.BoardResDto;
import com.mztalk.mentor.domain.dto.BoardTransferDto;
import com.mztalk.mentor.domain.dto.BoardReqDto;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.service.BoardService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 201, message = "CREATED"),
        @ApiResponse(code = 400, message = "BAD REQUEST"),
        @ApiResponse(code = 500, message = "SERVER ERROR")
})
@Api(tags = {"멘토링 글 정보 제공 Controller"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors")
public class BoardApiController {

    private final BoardService boardService;

    @ApiOperation(value = "모든 글 리턴", notes = "결제정보가 없고, 멘토링 시작 전인 모든 글을 리턴하는 메소드입니다.", response = Result.class)
    @GetMapping("/boards/{page}")
    public ResponseEntity<?> findNullPaymentWithBeforeMentoringDate(@PathVariable("page") int page){
        List<BoardResDto> boards = boardService.findNullPaymentWithBeforeMentoringDate(page);
        return new ResponseEntity<>(new Result<>("모든 글 목록", boards), HttpStatus.OK);
    }

    @ApiOperation(value = "글 저장", notes = "글을 저장하는 메소드입니다.", response = Result.class)
    @PostMapping("/board")
    public ResponseEntity<?> saveBoard(@RequestBody BoardReqDto boardReqDto){
        Long savedId = boardService.saveBoard(boardReqDto);
        return new ResponseEntity<>(new Result<>("글 등록 완료", savedId), HttpStatus.CREATED);
    }

    @ApiOperation(value = "글 정보 리턴", notes = "해당 번호에 해당하는 글을 리턴하는 메소드입니다.", response = Result.class)
    @ApiImplicitParam(name = "id", value = "글 식별자", required = true, dataType = "int", paramType = "path")
    @GetMapping("/board/{id}")
    public ResponseEntity<?> findById(@PathVariable("id")Long id){
        BoardResDto board = boardService.findBoardByBoardId(id);
        return new ResponseEntity<>(new Result<>("해당 번호에 해당하는 글", board), HttpStatus.OK);
    }

    //멘티의 (완료된 멘토링 목록)
    @ApiOperation(value = "멘티의 완료된 글 리턴", notes = "해당번호에 해당하는 멘티의 완료된 글을 리턴하는 메소드입니다.", response = Result.class) 
    @ApiImplicitParam(name = "userId", value = "멘티 식별자", required = true, dataType = "int", paramType = "query")
    @GetMapping("/board")
    public ResponseEntity<?> findBoardByUserId(@RequestParam("userId")Long userId){
        List<BoardTransferDto> boards = boardService.findBoardByUserId(userId);
        return new ResponseEntity<>(new Result<>("해당 멘티의 완료된 멘토링 목록", boards), HttpStatus.OK);
    }

    //멘티의 (모든 목록)
    @ApiOperation(value = "멘티의 모든 글 리턴", notes = "해당번호에 해당하는 멘티가 신청한 모든 글을 리턴하는 메소드입니다.", response = Result.class)
    @ApiImplicitParam(name = "menteeId", value = "멘티 식별자", required = true, dataType = "int", paramType = "path")
    @GetMapping("/board/mentee/{menteeId}")
    public ResponseEntity<?> findBoardByMenteeId(@PathVariable("menteeId")Long MenteeId){
        List<BoardResDto> boards = boardService.findBoardByMenteeId(MenteeId);
        return new ResponseEntity<>(new Result<>("해당 멘티가 신청한 모든 글 목록", boards), HttpStatus.OK);
    }

    @ApiOperation(value = "멘토의 모든 글 리턴", notes = "해당번호의 멘토가 작성한 모든글을 리턴하는 메소드입니다.", response = Result.class)
    @ApiImplicitParam(name = "mentorId", value = "멘토 식별자", required = true, dataType = "int", paramType = "path")
    @GetMapping("/board/mentor/{mentorId}")
    public ResponseEntity<?> findBoardByMentorId(@PathVariable("mentorId")Long mentorId){
        List<BoardResDto> boards = boardService.findBoardByMentorId(mentorId);
        return new ResponseEntity<>(new Result<>("해당 멘토에 대한 모든 글", boards), HttpStatus.OK);
    }

    @ApiOperation(value = "사용자 본인의 글인지 확인", notes = "해당번호의 멘토가 작성한 글인지 확인하는 메소드입니다.", response = boolean.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "사용자 식별자", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "boardId", value = "글 식별자", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/board/mentor")
    public boolean isOwner(@RequestParam("userId")Long userId,@RequestParam("boardId")Long boardId){
        boolean isOwner = boardService.isOwner(userId,boardId);
        return isOwner;
    }

    // 진짜로 삭제한다.
    @ApiOperation(value = "글 삭제", notes = "해당번호의 글을 삭제하는 메소드입니다.", response = Long.class)
    @ApiImplicitParam(name = "id", value = "글 식별자", required = true, dataType = "int", paramType = "path")
    @DeleteMapping("/board/{id}")
    public Long deleteBoard(@PathVariable("id")Long id){
        return boardService.delete(id);
    }

    // boardId로 글찾은 후 수정하기
    @ApiOperation(value = "글 수정", notes = "해당번호의 글을 수정하는 메소드입니다.", response = Long.class)
    @ApiImplicitParam(name = "id", value = "글 식별자", required = true, dataType = "int", paramType = "path")
    @PatchMapping("/board/edit/{id}")
    public Long updateBoard(@PathVariable("id")Long id,@RequestBody BoardResDto boardResDto){
        return boardService.updateBoard(id, boardResDto);
    }

    @ApiOperation(value = "글 검색", notes = "검색조건에 맞는 글을 리턴하는 메소드입니다.", response = Result.class)
    @GetMapping("/board/search")
    public ResponseEntity<?> searchWithCondition(@ModelAttribute("SearchCondition")SearchCondition searchCondition){
        List<BoardResDto> boards = boardService.searchWithCondition(searchCondition);
        return new ResponseEntity<>(new Result<>("글 검색 결과", boards), HttpStatus.OK);
    }

    @ApiOperation(value = "최신 글 리턴", notes = "작성된 글을 최신순으로 리턴하는 메소드입니다.", response = Result.class)
    @GetMapping("/board/latest/{page}")
    public ResponseEntity<?> latestBoard(@PathVariable("page") int page){
        List<BoardResDto> boards = boardService.latestBoard(page);
        return new ResponseEntity<>(new Result<>("최신 글 목록", boards), HttpStatus.OK);
    }


}
