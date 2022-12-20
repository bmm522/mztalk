package com.mztalk.auction.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardDetailResponseDto {
    private Long boardId;
    private String title;
    private String content;
    private String currentPrice;
    private List<ConcurrentHashMap<String, String>> imageInfo;
}
