package com.mztalk.mentor.domain.entity;


public class Result<T> {
    private T data;

    public Result(T data) {
        this.data = data;
    }
}
