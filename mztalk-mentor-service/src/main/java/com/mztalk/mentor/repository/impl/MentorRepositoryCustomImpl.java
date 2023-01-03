package com.mztalk.mentor.repository.impl;

import com.mztalk.mentor.domain.entity.Mentor;
import com.mztalk.mentor.repository.MentorRepositoryCustom;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
public class MentorRepositoryCustomImpl implements MentorRepositoryCustom {

    private final EntityManager entityManager;

}
