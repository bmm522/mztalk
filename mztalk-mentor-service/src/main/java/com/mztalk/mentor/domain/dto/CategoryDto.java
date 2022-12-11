package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.entity.Board;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Long id;
    private String name;
    private List<Board> boards = new ArrayList<>();

}
