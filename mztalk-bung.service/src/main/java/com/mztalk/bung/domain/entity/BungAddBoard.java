package com.mztalk.bung.domain.entity;

import com.mztalk.bung.domain.BoardStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name="BungAddBoard")
@Entity
public class BungAddBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addId;

    @Lob
    private String addContent;

    @Column(nullable = false, length = 100)
    private String addPhone;

    @Enumerated(EnumType.STRING)
    private BoardStatus boardStatus;

    @Column(nullable = false, length = 20)
    private String addNickName;

    @ManyToOne
    @JoinColumn(name="boardId")
    private BungBoard boardId;

    public void changeStatus() {
        this.boardStatus = BoardStatus.NO;
    }

}
