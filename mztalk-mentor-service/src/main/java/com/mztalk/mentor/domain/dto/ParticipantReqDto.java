package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Participant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ParticipantReqDto {

    private Long userId;
    private Long boardId;
    private String name;
    private String phone;
    private String email;
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


}
