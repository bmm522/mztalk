package com.mztalk.resource.service;

import org.springframework.http.ResponseEntity;

public interface SelectFileService {
    ResponseEntity<?> getFiles(long id);
}
