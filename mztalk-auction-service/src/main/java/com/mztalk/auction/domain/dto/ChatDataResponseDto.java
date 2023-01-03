package com.mztalk.auction.domain.dto;

import com.mztalk.auction.domain.entity.ChatData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatDataResponseDto {
    private Long chatId;
    private Long boardId;
    private String writer;
    private String buyer;

    public ChatDataResponseDto(ChatData chatData) {
        this.chatId = chatData.getChatId();
        this.boardId = chatData.getBoardId();
        this.writer = chatData.getWriter();
        this.buyer = chatData.getBuyer();
    }

}
