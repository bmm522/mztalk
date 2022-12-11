package com.mztalk.mentor.domain.entity;

import lombok.Data;

@Data
public class ResponseEntity<T> {
    private T data;

    public ResponseEntity(T data) {
        this.data = data;
    }
}
