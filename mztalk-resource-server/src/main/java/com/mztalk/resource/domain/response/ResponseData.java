package com.mztalk.resource.domain.response;

import com.mztalk.resource.domain.response.dto.ImagesResponseDto;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseData<T> {


    @ApiModelProperty(notes = "statusCode", example = "200")
    private int statusCode;


    @ApiModelProperty(notes = "responseMessage", example = "데이터 조회 성공")
    private String responseMessage;

    @ApiModelProperty(notes = "data")
    private T data;

    public ResponseData(final int statusCode, final String responseMessage) {
        this.statusCode = statusCode;
        this.responseMessage = responseMessage;
        this.data = null;
    }

    public static<T>  ResponseData<T> res(final int statusCode, final String responseMessage) {
        return res(statusCode, responseMessage, null);
    }

    public static<T>  ResponseData<T> res(final int statusCode, final String responseMessage, final T t) {
        return ResponseData.<T>builder()
                .data(t)
                .statusCode(statusCode)
                .responseMessage(responseMessage)
                .build();
    }


}
