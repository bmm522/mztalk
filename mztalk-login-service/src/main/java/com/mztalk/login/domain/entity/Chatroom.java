package com.mztalk.login.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chatroom {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long chatId;
    private String serviceName;


    @JoinColumn(name="id")
    @ManyToOne
    @JsonIgnore
    private User fromUser;

    private long toUserNo;

    private String status;
}
