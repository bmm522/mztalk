package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.entity.Mentee;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MenteeReqDto {

    @ApiModelProperty(value="사용자의 식별자", example = "1", required = true)
    private Long id;
    @ApiModelProperty(value="사용자의 닉네임", example = "강건강", required = true)
    private String nickname;

    public Mentee toEntity(){
        Mentee mentee = Mentee.builder()
                .id(id)
                .nickname(nickname)
                .build();
        return mentee;
    }
}
