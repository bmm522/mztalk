package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Board;
import com.mztalk.mentor.domain.entity.Mentee;
import com.mztalk.mentor.domain.entity.Participant;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDto {

    private Long id;
    private Board board;
    private Mentee mentee;
    private String name;
    private String phone;
    private String message;
    private Status status;

    public Participant toEntity(){
        Participant participant = Participant.builder()
                .id(id)
                .board(board)
                .mentee(mentee)
                .name(name)
                .phone(phone)
                .message(message)
                .status(Status.YES)
                .build();
        return participant;
    }

}
