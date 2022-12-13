package com.mztalk.mentor.repository.impl;

import com.mztalk.mentor.repository.MenteeRepositoryCustom;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
public class MenteeRepositoryCustomImpl implements MenteeRepositoryCustom {
    private final EntityManager entityManager;


}
