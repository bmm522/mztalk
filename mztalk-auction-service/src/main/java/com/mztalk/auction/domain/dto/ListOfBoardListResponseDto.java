package com.mztalk.auction.domain.dto;

import com.mztalk.auction.domain.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;



public class ListOfBoardListResponseDto {
    private List<BoardListResponseDto> boardListResponseDtoList = new ArrayList<>();

    public ListOfBoardListResponseDto(Page<Board> boardList, List<TimeDto> duration, List<ImageRestDto> imageRestDtoList) throws ParseException {

        for (int i = 0; i < boardList.getSize(); i++) {
            this.boardListResponseDtoList.add(new BoardListResponseDto(boardList.getContent().get(i), duration.get(i), imageRestDtoList.get(i).getImageUrl(), imageRestDtoList.get(i).getObjectKey()));
        }
    }

}
