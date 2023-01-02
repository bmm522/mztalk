package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Participant;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ParticipantReqDto {

    @ApiModelProperty(value="참가자의 식별자", example = "1", required = true)
    private Long userId;
    @ApiModelProperty(value="게시글의 식별자", example = "2", required = true)
    private Long boardId;
    @ApiModelProperty(value="참가자의 성함", example = "류라라", required = true)
    private String name;
    @ApiModelProperty(value="참가자의 연락처", example = "01022228888", required = true)
    private String phone;
    @ApiModelProperty(value="참가자의 이메일", example = "mztalk@gamil.com", required = true)
    private String email;
    @ApiModelProperty(value="참가자의 남기고 싶은말", example = "수학을 깨닫고 싶습니다.", required = true)
    private String message;

    public Participant toEntity(){
        Participant participant = Participant.builder()
                .name(name)
                .phone(phone)
                .email(email)
                .message(message)
                .status(Status.YES)
                .build();
        return participant;
    }

    @Override
    public String toString() {
        return "ParticipantReqDto{" +
                "userId=" + userId +
                ", boardId=" + boardId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
