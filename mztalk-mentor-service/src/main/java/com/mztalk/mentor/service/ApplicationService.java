package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.ApplicationDto;
import com.mztalk.mentor.domain.entity.Result;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface ApplicationService {

    Long save(ConcurrentHashMap<String, String> applicationMap);

    ApplicationDto findById(Long id);

    List<ApplicationDto> findAll();

    Long delete(Long id);

    Long updateApplication(Long id,ApplicationDto applicationMap);

    boolean isExist(Long userId);

}
