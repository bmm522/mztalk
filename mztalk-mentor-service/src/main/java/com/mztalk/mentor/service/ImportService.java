package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.ImportCancelReqDto;
import com.mztalk.mentor.domain.dto.ImportCancelResDto;

public interface ImportService {

    String getToken();

    ImportCancelResDto cancelPayment(ImportCancelReqDto importCancelReqDto);
}
