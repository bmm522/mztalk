package com.mztalk.bung.domain.entity;

import lombok.Data;

@Data
public class Result<T> {
    private T data;
    public Result(T data) {
        this.data = data;
    }
}
