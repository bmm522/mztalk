package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.dto.ApplicationResDto;
import com.mztalk.mentor.domain.dto.ApplicationReqDto;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.service.ApplicationService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
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
@Api(tags = {"멘토 지원서 정보 제공 Controller"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors")
public class ApplicationApiController {

    private final ApplicationService applicationService;

    @ApiOperation(value = "지원서 저장", notes = "지원서를 저장하는 메소드입니다.", response = Result.class)
    @PostMapping("/application")
    public ResponseEntity<?> saveApplication(@RequestBody ApplicationReqDto applicationReqDto) {
        Long applicationId = applicationService.save(applicationReqDto);
        return new ResponseEntity<>(new Result<>("지원서 작성 성공",applicationId), HttpStatus.CREATED);
    }

    @ApiOperation(value = "지원서 정보 리턴", notes = "해당 번호에 해당하는 지원서를 리턴하는 메소드입니다.", response = Result.class)
    @ApiImplicitParam(name = "id", value = "지원서 식별자", required = true, dataType = "int", paramType = "path")
    @GetMapping("/application/{id}")
    public ResponseEntity<?> findById(@PathVariable("id")Long id){
        ApplicationResDto applicationResDto = applicationService.findById(id);
        return new ResponseEntity<>(new Result<>("해당 번호에 대한 지원서", applicationResDto),HttpStatus.OK);
    }

    @ApiOperation(value = "유저가 제출한 지원서 존재 확인", notes = "해당 유저가 제출한 지원서가 존재하는지 확인하는 메소드입니다.", response = boolean.class)
    @ApiImplicitParam(name = "userId", value = "사용자 식별자", required = true, dataType = "int", paramType = "query")
    @GetMapping("/application")
    public boolean isExist(@RequestParam("userId")Long userId){
        return applicationService.isExist(userId);
    }

    @ApiOperation(value = "모든 지원서 리턴", notes = "모든 지원서를 리턴하는 메소드입니다.", response = Result.class)
    @GetMapping("/applications")
    public ResponseEntity<?> findAll(){
        List<ApplicationResDto> applications = applicationService.findAll();
        return new ResponseEntity<>(new Result<>("모든 지원서 목록", applications), HttpStatus.OK);
    }

    @ApiOperation(value = "지원서 삭제", notes = "해당 번호에 해당하는 지원서를 삭제하는 메소드입니다.", response = Result.class)
    @ApiImplicitParam(name = "id", value = "지원서 식별자", required = true, dataType = "int", paramType = "path")
    @DeleteMapping("/application/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Long deleteId = applicationService.delete(id);
        return new ResponseEntity<>(new Result<>("해당 지원서가 삭제되었습니다.", deleteId), HttpStatus.OK);
    }

    @ApiOperation(value = "지원서 수정", notes = "해당 번호에 해당하는 지원서를 수정하는 메소드입니다.", response = Long.class)
    @ApiImplicitParam(name = "id", value = "지원서 식별자", required = true, dataType = "int", paramType = "path")
    @PatchMapping("/application/{id}")
    public Long updateApplication(@PathVariable("id") Long id,@RequestBody ApplicationResDto applicationResDto){
        return applicationService.updateApplication(id, applicationResDto);
    }

}
