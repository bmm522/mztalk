package com.mztalk.story.common;

import lombok.Data;

@Data
public class Result<T> {
    private T data;
    public Result(T data){
        this.data = data;
    }
}
