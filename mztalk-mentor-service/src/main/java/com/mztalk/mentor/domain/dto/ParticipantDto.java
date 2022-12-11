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

    public ParticipantDto(Participant participant){
        this.id = participant.getId();
        this.board = participant.getBoard();
        this.mentee = participant.getMentee();
        this.name = participant.getName();
        this.phone = participant.getPhone();
        this.message = participant.getMessage();
        this.status = participant.getStatus();
    }

}
