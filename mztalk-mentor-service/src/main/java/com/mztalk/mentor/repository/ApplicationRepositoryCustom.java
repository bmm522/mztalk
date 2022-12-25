package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.entity.Application;

import java.util.List;

public interface ApplicationRepositoryCustom {
    List<Application> fetchMenteeApplication();
}
