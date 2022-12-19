package com.mztalk.resource.domain.response.dto;

import com.mztalk.resource.domain.entity.File;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileResponseDto {

    private String fileNo;

    private String fileName;

    private String objectKey;

    private String fileUrl;

    private Long id;
    public FileResponseDto(File f){
        this.fileNo = String.valueOf(f.getFileId());
        this.fileName = f.getFileName();
        this.objectKey = f.getObjectKey();
        this.fileUrl = f.getFileUrl();
        this.id = f.getId();
    }
}
