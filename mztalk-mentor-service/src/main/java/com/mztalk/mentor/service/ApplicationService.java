package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.ApplicationDto;
import com.mztalk.mentor.domain.entity.Result;

public interface ApplicationService {

    Long save(ApplicationDto applicationDto);

    ApplicationDto findById(Long id);

    Result findAll();

    Long delete(Long id);

    Long updateApplication(Long id,ApplicationDto applicationDto);

}
