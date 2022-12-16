package com.mztalk.resource.repository;

import com.mztalk.resource.domain.entity.File;

import java.util.List;

public interface FileCustomRepository {
    List<File> getFileInfo(long id);
}
