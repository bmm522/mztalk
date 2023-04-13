package com.mztalk.auction.domain.dto.board;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.mztalk.auction.domain.dto.ImageRestDto;
import com.mztalk.auction.domain.dto.TimeDto;
import com.mztalk.auction.domain.dto.board.BoardListResponseDto;
import com.mztalk.auction.domain.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class ListOfBoardListResponseDto {
    private List<BoardListResponseDto> boardListResponseDtoList = new ArrayList<>();

    public ListOfBoardListResponseDto(List<Board> boardList, List<TimeDto> duration, List<ImageRestDto> imageRestDtoList) throws ParseException {
        for (int i = 0; i < boardList.size(); i++) {
            this.boardListResponseDtoList.add(new BoardListResponseDto(boardList.get(i), duration.get(i), imageRestDtoList.get(i)));
        }
    }

}
