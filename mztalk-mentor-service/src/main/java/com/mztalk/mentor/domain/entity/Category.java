package com.mztalk.mentor.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="CATEGORY")
public class Category {

    @Id
    @Column(name = "category_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<Board> boards = new ArrayList<>();

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    //== 연관관계 편의 메소드==//
    public void addBoard(Board board){
        this.boards.add(board);
        if(board.getCategory() != this){
            board.addCategory(this);
        }
    }
}
