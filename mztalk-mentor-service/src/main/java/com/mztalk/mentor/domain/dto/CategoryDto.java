package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.entity.Board;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDto {

    private Long id;
    private String name;
    private List<Board> boards = new ArrayList<>();

}
