package com.mztalk.resource.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ImagesResponseDto {

    private String statusCode;

    private String responseMessage;



    private List<ImagesDto> data;
}
