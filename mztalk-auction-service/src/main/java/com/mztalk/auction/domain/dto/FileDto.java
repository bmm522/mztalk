package com.mztalk.auction.domain.dto;

import com.mztalk.auction.domain.entity.File;
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

    public File toEntity() {
        return File.builder()
                .fId(fId)
                .fileName(fileName)
                .filePath(filePath)
                .level(level)
                .build();
    }
}
