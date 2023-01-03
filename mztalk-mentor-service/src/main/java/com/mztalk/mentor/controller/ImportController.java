package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.dto.ImportCancelReqDto;
import com.mztalk.mentor.domain.dto.ImportCancelResDto;
import com.mztalk.mentor.service.ImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mentors/api/import")
@RequiredArgsConstructor
public class ImportController {

    private final ImportService importService;

    @PostMapping("/cancel")
    public ImportCancelResDto cancelPayment(@RequestBody ImportCancelReqDto importCancelReqDto){
        return importService.cancelPayment(importCancelReqDto);
    }

}
