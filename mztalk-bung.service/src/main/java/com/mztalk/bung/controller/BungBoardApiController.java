package com.mztalk.bung.controller;

import com.mztalk.bung.domain.dto.BungBoardDto;
import com.mztalk.bung.service.BungBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.mztalk.bung.domain.entity.Result;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bung")
public class BungBoardApiController {

    private final BungBoardService bungBoardService;

    @GetMapping("/mainBoards")
    public Result mainSelectList() {
        return bungBoardService.mainSelectList();
    }

    @ResponseBody
    @PostMapping("/mainInsertBoard")
        public Long mainInsertBoard(@RequestBody BungBoardDto bungBoardDto) {
        return bungBoardService.mainInsertBoard(bungBoardDto);
    }

    @ResponseBody
    @PatchMapping("/mainBoardUpdate/{boardWriter}")
    public Long mainBoardUpdate(@PathVariable("boardId") Long bId, @RequestBody BungBoardDto bungBoardDto) {
        return bungBoardService.mainBoardUpdate(bId, bungBoardDto);
    }

}
