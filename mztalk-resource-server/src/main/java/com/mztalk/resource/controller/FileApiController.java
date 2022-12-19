package com.mztalk.resource.controller;


import com.mztalk.resource.domain.request.dto.FileRequestDto;
import com.mztalk.resource.domain.response.ResponseData;
import com.mztalk.resource.service.DeleteFileService;
import com.mztalk.resource.service.InsertFileService;
import com.mztalk.resource.service.SelectFileService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/resource")
@RestController
@RequiredArgsConstructor
@Api(tags = "file-api")
@ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "bad request"),
        @ApiResponse(code = 500, message = "server error")
})
public class FileApiController {


    private final InsertFileService insertFileService;

    private final SelectFileService selectFileService;

    private final DeleteFileService deleteFileService;
    // 파일 단일업로드
    @ApiOperation(value="파일 단일 업로드", notes = "파일을 단일로 보냅니다.", response = ResponseData.class)
    @PostMapping(value = "/file", consumes = "multipart/form-data",  produces = "application/json")
    public ResponseEntity<?> insertFile(@RequestParam("file")MultipartFile multipartFile, FileRequestDto fileRequestDto){
        return insertFileService.insertFile(multipartFile, fileRequestDto);
    }

    // 파일 다중업로드
    @ApiOperation(value="파일 다중 업로드", notes = "파일을 다중으로 보냅니다.", response = ResponseData.class)
    @PostMapping(value = "/files",  consumes = "multipart/form-data",  produces = "application/json")
    public ResponseEntity<?> insertFiles(@RequestParam("file")List<MultipartFile> multipartFileList, FileRequestDto fileRequestDto){
        return insertFileService.insertFiles(multipartFileList, fileRequestDto);
    }

    // 해당 유저의 모든 파일 불러오기
    @ApiOperation(value="해당 아이디 파일 정보 조회", notes = "해당 아이디에 해당하는 모든 파일을 리스트로 가져옵니다.", consumes = "text/html", produces = "application/json", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="id", value="해당 아이디")
    })
    @GetMapping(value = "/files" , consumes = "text/html", produces = "application/json")
    public ResponseEntity<?> getFiles(@RequestParam("id")long id){
        return selectFileService.getFiles(id);
    }


    @ApiOperation(value="게시글 파일 삭제", notes = "해당 아이디에 해당하는 모든 파일을 삭제합니다.", consumes = "text/html", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="id", value="해당 아이디"),
    })
    @DeleteMapping(value= "/files", consumes = "text/html")
    public ResponseEntity<?> deleteFile(@RequestParam("id")long id){
        return deleteFileService.deleteFile(id);
    }

}
