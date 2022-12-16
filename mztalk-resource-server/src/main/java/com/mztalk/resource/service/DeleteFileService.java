package com.mztalk.resource.service;

import org.springframework.http.ResponseEntity;

public interface DeleteFileService {
    ResponseEntity<?> deleteFile(long id);


}
