package com.mztalk.resource.domain.dto;

import com.mztalk.resource.domain.entity.File;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {

    private String fileNo;

    private String fileName;

    private String fileUrl;

    private Long id;

    public File toEntity(String fileName, ConcurrentHashMap<String, String> s3Map) {
            return File.builder()
                    .objectKey(s3Map.get("key"))
                    .fileName(fileName)
                    .fileUrl(s3Map.get("url"))
                    .id(id)
                    .build();
    }
}
