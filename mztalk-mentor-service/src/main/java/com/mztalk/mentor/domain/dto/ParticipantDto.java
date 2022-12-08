package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Board;
import com.mztalk.mentor.domain.entity.Mentee;
import lombok.*;

import javax.persistence.*;

@Data
public class ParticipantDto {

    private Long id;
    private Board board;
    private Mentee mentee;
    private String name;
    private String phone;
    private String message;
    private Status status;

}
