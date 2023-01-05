package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.MenteeReqDto;
import com.mztalk.mentor.domain.dto.MenteeResDto;
import com.mztalk.mentor.domain.dto.NicknameModifyDto;

import java.util.List;

public interface MenteeService {

    Long saveClient(MenteeReqDto menteeReqDto);

    MenteeResDto findClient(Long id);

    List<MenteeResDto> findAll();

    Long modifyNickname(NicknameModifyDto nicknameModifyDto);
}
