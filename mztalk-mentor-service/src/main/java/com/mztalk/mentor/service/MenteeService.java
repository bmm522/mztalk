package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.MenteeDto;
import com.mztalk.mentor.domain.entity.Result;

public interface MenteeService {

    Long saveClient(MenteeDto menteeDto);

    MenteeDto findClient(Long id);

    Result findAll();
}
