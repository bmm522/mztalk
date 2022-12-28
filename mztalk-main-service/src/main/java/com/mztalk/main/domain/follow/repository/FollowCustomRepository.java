package com.mztalk.main.domain.follow.repository;

import com.mztalk.main.domain.follow.dto.MatpalListResponseDto;

import java.util.List;

public interface FollowCustomRepository {


   List<MatpalListResponseDto> findByMatpalList(Long fromUserId);


}
