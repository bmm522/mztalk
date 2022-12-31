package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Mentor;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MentorReqDto {

    @ApiModelProperty(value="신청자의 식별자", example = "1", required = true)
    private Long userId;

    public Mentor toEntity(){
        Mentor mentor = Mentor.builder()
                .userId(userId)
                .status(Status.YES)
                .build();
        return mentor;
    }
}
