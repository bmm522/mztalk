package com.mztalk.auction.domain.dto;

import com.mztalk.auction.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentUpdateRequestDto {
    private String content;

}
