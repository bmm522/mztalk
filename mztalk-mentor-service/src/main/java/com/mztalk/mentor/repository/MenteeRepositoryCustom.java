package com.mztalk.mentor.repository;


import com.mztalk.mentor.domain.entity.Mentee;

public interface MenteeRepositoryCustom {

    Mentee findMenteeByUserId(Long userId);
}
