package com.mztalk.auction.domain.dto;

import com.mztalk.auction.domain.entity.Images;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileDto {
    private Long fId;
    private String fileName;
    private String filePath;
    private Integer level;

    public Images toEntity() {
        return Images.builder()
                .iId(fId)
                .imageName(fileName)
                .imagePath(filePath)
                .level(level)
                .build();
    }
}
