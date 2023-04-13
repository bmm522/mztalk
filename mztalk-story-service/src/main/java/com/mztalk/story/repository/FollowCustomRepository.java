package com.mztalk.story.repository;

import com.mztalk.story.domain.follow.dto.MatpalListResponseDto;

import java.util.List;

public interface FollowCustomRepository {


   List<MatpalListResponseDto> findByMatpalList(Long fromUserId);


}
