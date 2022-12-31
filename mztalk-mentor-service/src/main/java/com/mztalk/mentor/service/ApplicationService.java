package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.ApplicationResDto;
import com.mztalk.mentor.domain.dto.ApplicationReqDto;

import java.util.List;

public interface ApplicationService {

    Long save(ApplicationReqDto applicationReqDto);

    ApplicationResDto findById(Long id);

    List<ApplicationResDto> findAll();

    Long delete(Long id);

    Long updateApplication(Long id, ApplicationResDto applicationMap);

    boolean isExist(Long userId);

}
