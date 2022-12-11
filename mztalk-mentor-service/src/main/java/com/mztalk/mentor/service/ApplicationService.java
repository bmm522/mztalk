package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.ApplicationDto;
import com.mztalk.mentor.domain.entity.ResponseEntity;

public interface ApplicationService {

    Long save(ApplicationDto applicationDto);

    ResponseEntity findById(Long id);

    ResponseEntity findAll();

    Long delete(Long id);

    Long updateApplication(Long id,ApplicationDto applicationDto);

}
