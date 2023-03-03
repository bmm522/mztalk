package com.mztalk.auction.domain.dto.comment;

import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.sql.Timestamp;


@Getter
@Setter
public class CommentRequestDto {
    private Long boardId;
    private String content;
    private String writer;
    private Long userNo;

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .status("Y")
                .writer(writer)
                .userNo(userNo)
                .build();
    }

}
