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

    @OneToMany
    private List<Board> boards = new ArrayList<>();

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
