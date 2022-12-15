package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.ApplicationDto;
import com.mztalk.mentor.domain.entity.Result;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface ApplicationService {

    Long save(ConcurrentHashMap<String, String> applicationDto);

    ApplicationDto findById(Long id);

    Result findAll();

    Long delete(Long id);

    Long updateApplication(Long id,ApplicationDto applicationDto);

    boolean isExist(Long userId);

}
