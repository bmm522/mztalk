package com.mztalk.auction.domain.dto;

import com.mztalk.auction.domain.entity.Comment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentDto {
    private String content;
    private Date createDate;
    private String nickname;

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .createDate(createDate)
                .nickname(nickname)
                .status("Y")
                .build();
    }
}
